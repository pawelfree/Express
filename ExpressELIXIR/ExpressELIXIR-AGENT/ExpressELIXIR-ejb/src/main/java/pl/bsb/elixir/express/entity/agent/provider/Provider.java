package pl.bsb.elixir.express.entity.agent.provider;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.bsb.proELIX.common.serverutils.dao.DBTemplate;
import pl.bsb.proELIX.common.utils.criteria.BaseSearchCriteria;

/**
 *
 * @author paweld
 */

public class Provider<T, C extends BaseSearchCriteria> extends DBTemplate<T, C> {
 
  @PersistenceContext
  EntityManager em;
  
  public Provider(Class<T> entity) {
    super(entity);
  }
  
  @Override
  public EntityManager getEntityManager() throws IllegalStateException {
    if (em == null) {
      throw new IllegalStateException("No entity manager specified");
    }
    return em;
  }  
}
