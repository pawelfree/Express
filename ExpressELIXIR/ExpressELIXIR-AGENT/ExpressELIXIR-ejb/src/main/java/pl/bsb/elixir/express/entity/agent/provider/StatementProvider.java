package pl.bsb.elixir.express.entity.agent.provider;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import pl.bsb.elixir.express.entity.agent.Statement;
import pl.bsb.proELIX.common.utils.criteria.BaseSearchCriteria;

/**
 *
 * @author paweld
 */
@Stateless
@LocalBean
public class StatementProvider extends Provider<Statement,BaseSearchCriteria> {

  private static final long serialVersionUID = 5L;

  public StatementProvider() {
    super(Statement.class);
  }

  public List<Statement> getStatementsByTransactionId(String transactionId) {
    Query queryStatementsByTransactionId = this.getEntityManager().createNamedQuery("getStatementsByTransactionId");
    queryStatementsByTransactionId.setParameter("transactionId", transactionId);
    return queryStatementsByTransactionId.getResultList();
  }
}
