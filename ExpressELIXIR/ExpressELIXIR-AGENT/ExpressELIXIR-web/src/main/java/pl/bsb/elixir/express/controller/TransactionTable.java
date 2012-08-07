package pl.bsb.elixir.express.controller;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import pl.bsb.elixir.express.domain.StatementDataModel;
import pl.bsb.elixir.express.domain.TransactionDataModel;
import pl.bsb.elixir.express.enterprise.agent.interfaces.ISendTransfer;
import pl.bsb.elixir.express.entity.agent.Account;
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
    private static final Logger logger = Logger.getLogger(TransactionTable.class.getName());
    private static final long serialVersionUID = 3L;
    @EJB
    TransactionOutgoingProvider transactionOutgoingProvider;
    @EJB
    TransactionIncomingProvider transactionIncomingProvider;
    @EJB
    ISendTransfer sendTransferProcessor;
    @EJB
    AccountProvider accountProvider;
    @EJB
    StatementProvider statementProvider;
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
        if (sendTransactionsFromFile) {
        } else {
            User user = mainView.getUser();

            if (!mainView.getNewTransactionAccount().canIncreaseBlockedAmount(newTransaction.getTransactionAmount())) {
                //TODO ustawić parametry walidacji w sesji
                return;
            }
            newTransaction.setTransactionDate(new Date());
            newTransaction.setTransactionId(Long.toString(System.currentTimeMillis()));
            newTransaction.setSenderAccount(mainView.getNewTransactionAccount());
            newTransaction.setMainKNR(user.getParticipant().getMainKNR());
            newTransaction.setSenderIban(mainView.getNewTransactionAccount().getIban());
            //TODO błędy
            Boolean result = sendTransferProcessor.process(newTransaction);
            if (result) {
                Account transactionAccount = mainView.getNewTransactionAccount();
                transactionAccount.addToBlockedBalance(newTransaction.getTransactionAmount());
                accountProvider.update(transactionAccount);
                mainView.setSelectedAccount(transactionAccount);
            }
        }
        newTransaction = new TransactionOutgoing();
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            System.err.println(event.getFile().getFileName());
            Path path = Paths.get(event.getFile().getFileName());
            System.err.println("1");
            List<String> contents = Files.readAllLines(path, Charset.defaultCharset());
            System.err.println("2");
            for (String b : contents) {
                System.out.println(b);
            }
        } catch (IOException | NullPointerException e) {
            logger.warning(e.getMessage());
        }
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
