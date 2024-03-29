package pl.bsb.elixir.express.entity.agent;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author paweld
 */
@NamedQueries({
        @NamedQuery(name = "findTransactionsOutgoingByUser", query = "SELECT t FROM TransactionOutgoing t WHERE t.senderAccount IN (SELECT accnt FROM User u JOIN u.accounts as accnt WHERE u = :user ) ORDER BY t.transactionDate DESC"),
        @NamedQuery(name = "findTransactionsOutgoingByAccount", query = "SELECT t FROM TransactionOutgoing t WHERE t.senderAccount = :account ORDER BY t.transactionDate DESC"),
        @NamedQuery(name = "findTransactionOutgoingById",query="SELECT t FROM TransactionOutgoing t WHERE t.transactionId = :transactionId")
})
@Entity
@DiscriminatorValue("Outgoing")
public class TransactionOutgoing extends Transaction {

    private static final long serialVersionUID = 17L;  
    @ManyToOne
    @NotNull
    private Account senderAccount;
    @Column
    @Size(min = 8, max = 8)
    @NotNull
    private String senderKNR;
    @Column
    @Size(min = 8, max = 8)
    @NotNull
    private String mainKNR;
    
    public TransactionOutgoing() {}
    
    public TransactionOutgoing(String transactionId, Account account, Money transactionAmount, String receiverIBAN, String knr) {
        setTransactionDate(new Date());
        setTransactionId(transactionId);
        setSenderAccount(account);
        setSenderIban(account.getIban());
        mainKNR = knr;
        setTransactionAmount(transactionAmount);
        setReceiverIban(receiverIBAN);
    }
    
    public String getReceiverKNR() {
        if (getReceiverIban() == null) {
            return "";
        } else {
            return getReceiverIban().substring(2, 10);
        }
    }
    
    public String getMainKNR() {
        return mainKNR;
    }

    public void setMainKNR(String mainKNR) {
        this.mainKNR = mainKNR;
    }

    public String getSenderKNR() {
        return senderKNR;
    }

    public void setSenderKNR(String senderKNR) {
        this.senderKNR = senderKNR;
    }
    
    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
        setSenderKNR(senderAccount.getIban().substring(2,10));
    }    
  
}
