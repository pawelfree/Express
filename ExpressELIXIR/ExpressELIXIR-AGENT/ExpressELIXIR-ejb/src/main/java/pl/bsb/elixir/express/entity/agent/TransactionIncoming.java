package pl.bsb.elixir.express.entity.agent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author paweld
 */
@NamedQueries({
  @NamedQuery(name = "findTransactionIncomingById", query = "SELECT t FROM TransactionIncoming t WHERE t.transactionId = :transactionId"),
  @NamedQuery(name = "findTransactionsIncomingByAccount", query = "SELECT t FROM TransactionIncoming t WHERE t.receiverAccount = :account ORDER BY t.transactionDate DESC"),
  @NamedQuery(name = "findTransactionsIncomingByUser", query = "SELECT t FROM TransactionIncoming t WHERE t.receiverAccount IN (SELECT accnt FROM User u JOIN u.accounts as accnt WHERE u = :user ) ORDER BY t.transactionDate DESC")
})
@Entity
@DiscriminatorValue("Incoming")
public class TransactionIncoming extends Transaction {

  @ManyToOne
  private Account receiverAccount;

  public Account getReceiverAccount() {
    return receiverAccount;
  }

  public void setReceiverAccount(Account receiverAccount) {
    this.receiverAccount = receiverAccount;
  }
}
