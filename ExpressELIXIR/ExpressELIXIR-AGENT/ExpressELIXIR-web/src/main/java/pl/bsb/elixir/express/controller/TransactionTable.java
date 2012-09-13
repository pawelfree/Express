package pl.bsb.elixir.express.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.domain.StatementDataModel;
import pl.bsb.elixir.express.domain.TransactionDataModel;
import pl.bsb.elixir.express.enterprise.agent.UniqueTransactionId;
import pl.bsb.elixir.express.enterprise.agent.interfaces.ISendTransfer;
import pl.bsb.elixir.express.entity.agent.Account;
import pl.bsb.elixir.express.entity.agent.Money;
import pl.bsb.elixir.express.entity.agent.Statement;
import pl.bsb.elixir.express.entity.agent.Transaction;
import pl.bsb.elixir.express.entity.agent.TransactionOutgoing;
import pl.bsb.elixir.express.entity.agent.User;
import pl.bsb.elixir.express.entity.agent.provider.AccountProvider;
import pl.bsb.elixir.express.entity.agent.provider.StatementProvider;
import pl.bsb.elixir.express.entity.agent.provider.TransactionIncomingProvider;
import pl.bsb.elixir.express.entity.agent.provider.TransactionOutgoingProvider;

/**
 *
 * @author paweld
 */
@ManagedBean
@SessionScoped
public class TransactionTable implements Serializable {

    //TODO jak sie wysypie webservice to próba dodania zdetachowanej encji zamiast nowej
    private static final Logger logger = LoggerFactory.getLogger(TransactionTable.class);
    private static final long serialVersionUID = 3L;
    @EJB
    TransactionOutgoingProvider transactionOutgoingProvider;
    @EJB
    TransactionIncomingProvider transactionIncomingProvider;
    @EJB
    ISendTransfer sendTransferProcessor;
    @EJB
    UniqueTransactionId uniqueTransactionId;
    @EJB
    StatementProvider statementProvider;
    @EJB
    AccountProvider accountProvider;

    @Inject
    MainView mainView;
    private TransactionOutgoing newTransaction;
    private boolean userTransactions = false;
    private boolean outgoingTansactions = true;
    private boolean sendTransactionsFromFile = false;
    private TransactionDataModel transactionDataModel;
    private StatementDataModel statementDataModel;
    private Transaction selectedTransaction;
    private Statement selectedStatement;

    public boolean isOutgoingTansactions() {
        return outgoingTansactions;
    }

    public void setOutgoingTansactions(boolean outgoingTansactions) {
        this.outgoingTansactions = outgoingTansactions;
    }

    public Transaction getNewTransaction() {
        return newTransaction;
    }

    public void setNewTransaction(TransactionOutgoing newTransaction) {
        this.newTransaction = newTransaction;
    }

    @PostConstruct
    public void initialize() {
        newTransaction = new TransactionOutgoing();
    }

    private void refreshTransactions() {
        List<Transaction> transactions;
        if (isUserTransactions()) {
            if (isOutgoingTansactions()) {
                transactions = transactionOutgoingProvider.getTransactionsByUser(mainView.getUser());
            } else {
                transactions = transactionIncomingProvider.getTransactionsByUser(mainView.getUser());
            }
        } else {
            if (isOutgoingTansactions()) {
                transactions = transactionOutgoingProvider.getTransactionsByAccount(mainView.getSelectedAccount());
            } else {
                transactions = transactionIncomingProvider.getTransactionsByAccount(mainView.getSelectedAccount());
            }
        }
        transactionDataModel = new TransactionDataModel(transactions);
    }

    public TransactionDataModel getTransactions() {
        refreshTransactions();
        return transactionDataModel;
    }

    public void sendNewTransaction() {
        Account account = mainView.getNewTransactionAccount();
        if (sendTransactionsFromFile) {
            if (!transactionsFromFile.isEmpty()) {
                sendTransferProcessor.process(transactionsFromFile);
            }
        } else {
            User user = mainView.getUser();
            Money amount = newTransaction.getTransactionAmount();
            if (!account.canIncreaseBlockedAmount(amount)) {
                //TODO ustawić parametry walidacji w sesji
                return;
            }
            //TODO błędy
            sendTransferProcessor.process(new TransactionOutgoing(
                    uniqueTransactionId.nextMessageId(user.getParticipant().getMainKNR()),
                    account,
                    amount,
                    newTransaction.getReceiverIban(),
                    user.getParticipant().getMainKNR()));
        }
        //TODO tu nie ustawianie tylko odswiezanie
        mainView.setSelectedAccount(account);
        newTransaction = new TransactionOutgoing();
    }
    private List<TransactionOutgoing> transactionsFromFile = Collections.EMPTY_LIST;

    public void handleFileUpload(FileUploadEvent event) {
        //Account account = mainView.getNewTransactionAccount();
        Account account = new Account();
        User user = mainView.getUser();        
        transactionsFromFile = new ArrayList();
        try {
            List<String> contents = inputStream2List(event.getFile().getInputstream());            
            String[] line;
            Money amount;
            String senderIBAN;
            String receiverIBAN;
            String mainKNR = user.getParticipant().getMainKNR();
            for (String b : contents) {
                line = b.split(";");
                //TODO to sprawdzanie powinno byc lepsze np. amount > 0
                if ((line[0] == null) || (line[1] == null) || (line[0].length() != 26) || (line[1].length() == 0)) {
                    logger.info("Error reading file");
                    return;
                }
                amount = new Money(new BigDecimal(line[1]));
                receiverIBAN = line[0];
                senderIBAN = line[2];
                logger.info("receiverIBAN : " + receiverIBAN + ", amount : " + amount + ", senderIBAN : " + senderIBAN);
                account = accountProvider.getAccountByIBAN(senderIBAN);
                if (!account.canIncreaseBlockedAmount(amount)) {
                    transactionsFromFile = new ArrayList();
                    return;
                }
                transactionsFromFile.add(new TransactionOutgoing(
                        uniqueTransactionId.nextMessageId(mainKNR),
                        account,
                        amount,
                        receiverIBAN,
                        mainKNR));
            }
        } catch (IOException | NullPointerException e) {
            logger.error("Cant read file", e);
        }
    }

    private List<String> inputStream2List(InputStream is) {        
        List<String> listaPrzelewow = new ArrayList<>();

        try {
            DataInputStream in = new DataInputStream(is);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String linia;

            while ((linia = br.readLine()) != null) {               
                listaPrzelewow.add(linia);
            }
        } catch (IOException ex) {
            logger.error("Error while processing InputStream : " + ex.getMessage());
        }

        return listaPrzelewow;
    }

    public void updateSettings() {
    }

    public boolean isUserTransactions() {
        return userTransactions;
    }

    public void setUserTransactions(boolean userTransactions) {
        this.userTransactions = userTransactions;
    }

    public void refreshAll() {
        mainView.refreshUser();
    }

    public Transaction getSelectedTransaction() {
        return selectedTransaction;
    }

    public void setSelectedTransaction(Transaction selectedTransaction) {
        this.selectedTransaction = selectedTransaction;
    }

    public StatementDataModel getStatements() {
        String transactionId;
        if (selectedTransaction != null) {
            transactionId = selectedTransaction.getTransactionId();
        } else {
            transactionId = "";
        }
        statementDataModel = new StatementDataModel(statementProvider.getStatementsByTransactionId(transactionId));
        return statementDataModel;
    }

    public Statement getSelectedStatement() {
        return selectedStatement;
    }

    public void setSelectedStatement(Statement selectedStatement) {
        this.selectedStatement = selectedStatement;
    }

    public boolean isSendTransactionsFromFile() {
        return sendTransactionsFromFile;
    }

    public void setSendTransactionsFromFile(boolean sendTransactionsFromFile) {
        this.sendTransactionsFromFile = sendTransactionsFromFile;
    }
}
