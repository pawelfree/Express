package pl.bsb.elixir.express.entity.srpn.provider;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import pl.bsb.elixir.express.entity.srpn.SRPNTransaction;
import pl.bsb.proELIX.common.utils.criteria.BaseSearchCriteria;

/**
 *
 * @author paweld
 */
@Stateless
@LocalBean
public class SRPNTransactionProvider extends Provider<SRPNTransaction,BaseSearchCriteria> {

  private static final long serialVersionUID = 15L;

  public SRPNTransactionProvider() {
    super(SRPNTransaction.class);
  }
}
