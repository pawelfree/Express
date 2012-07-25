package pl.bsb.elixir.express.entity.agent.provider;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.entity.agent.Account;
import pl.bsb.elixir.express.entity.agent.Transaction;
import pl.bsb.elixir.express.entity.agent.TransactionOutgoing;
import pl.bsb.elixir.express.entity.agent.User;
import pl.bsb.proELIX.common.utils.criteria.BaseSearchCriteria;

/**
 *
 * @author paweld
 */
@Stateless
@LocalBean
public class TransactionOutgoingProvider extends Provider<TransactionOutgoing,BaseSearchCriteria> {

  private static final long serialVersionUID = 6L;
  private static final Logger logger = LoggerFactory.getLogger(TransactionOutgoingProvider.class);

  public TransactionOutgoingProvider() {
    super(TransactionOutgoing.class);
  }

  public List<Transaction> getTransactionsByUser(User user) {
    Query queryTransactionsByUser = this.getEntityManager().createNamedQuery("findTransactionsOutgoingByUser");
    queryTransactionsByUser.setParameter("user", user);
    return queryTransactionsByUser.getResultList();
  }

  public List<Transaction> getTransactionsByAccount(Account account) {
    Query queryTransactionsByUser = this.getEntityManager().createNamedQuery("findTransactionsOutgoingByAccount");
    queryTransactionsByUser.setParameter("account", account);
    return queryTransactionsByUser.getResultList();
  }

  public TransactionOutgoing getTransactionById(String transactionId) {
    Query queryTransactionById = this.getEntityManager().createNamedQuery("findTransactionOutgoingById");
    queryTransactionById.setParameter("transactionId", transactionId);
    try {
      return (TransactionOutgoing) queryTransactionById.getSingleResult();
    } catch (NoResultException | NonUniqueResultException ex) {
      logger.info("Cant find transaction by transactionId : ".concat(transactionId),ex);
      return null;
    }
  }
}
