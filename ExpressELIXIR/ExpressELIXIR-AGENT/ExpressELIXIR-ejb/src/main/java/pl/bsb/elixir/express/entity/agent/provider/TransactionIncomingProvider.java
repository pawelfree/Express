package pl.bsb.elixir.express.entity.agent.provider;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.entity.agent.Account;
import pl.bsb.elixir.express.entity.agent.Transaction;
import pl.bsb.elixir.express.entity.agent.TransactionIncoming;
import pl.bsb.elixir.express.entity.agent.User;
import pl.bsb.proELIX.common.utils.criteria.BaseSearchCriteria;

/**
 *
 * @author paweld
 */
@Stateless
@LocalBean
public class TransactionIncomingProvider extends Provider<TransactionIncoming,BaseSearchCriteria> {

  private static final long serialVersionUID = 17L;
  private static final Logger logger = LoggerFactory.getLogger(TransactionIncomingProvider.class);

  public TransactionIncomingProvider() {
    super(TransactionIncoming.class);
  }

  public TransactionIncoming getTransactionById(String transactionId) {
    Query queryTransactionIncomingById = this.getEntityManager().createNamedQuery("findTransactionIncomingById");
    queryTransactionIncomingById.setParameter("transactionId", transactionId);
    try {
        //TODO może zwracać non unique exception nie wiadomo czemu
      return (TransactionIncoming) queryTransactionIncomingById.getSingleResult();
    } catch (NoResultException ex) {
      logger.info("Cant find transaction by transactionId : ".concat(transactionId));
      return null;
    }
  }
  
  public List<Transaction> getTransactionsByUser(User user) {
    Query queryTransactionsByUser = this.getEntityManager().createNamedQuery("findTransactionsIncomingByUser");
    queryTransactionsByUser.setParameter("user", user);
    return queryTransactionsByUser.getResultList();
  }

  public List<Transaction> getTransactionsByAccount(Account account) {
    Query queryTransactionsByUser = this.getEntityManager().createNamedQuery("findTransactionsIncomingByAccount");
    queryTransactionsByUser.setParameter("account", account);
    return queryTransactionsByUser.getResultList();
  }
  
}
