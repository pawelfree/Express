package pl.bsb.elixir.express.entity.srpn.provider;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import pl.bsb.elixir.express.entity.srpn.SRPNInternalStatus;
import pl.bsb.elixir.express.entity.srpn.SRPNStatement;
import pl.bsb.elixir.express.entity.srpn.SRPNTransaction;
import pl.bsb.proELIX.common.utils.criteria.BaseSearchCriteria;

/**
 *
 * @author paweld
 */
@Stateless
@LocalBean
public class SRPNStatementProvider extends Provider<SRPNStatement,BaseSearchCriteria> {
    
    private static final long serialVersionUID = 13L;
    
    public SRPNStatementProvider() {
        super(SRPNStatement.class);
    }    
    
    public SRPNStatement getSrpnStatementByTransactionAndStatus(SRPNTransaction srpnTransaction, SRPNInternalStatus status) {
        Query querySrpnStatementByTransactionAndStatus = this.getEntityManager().createNamedQuery("findSrpnStatementByTransactionAndStatus");
        querySrpnStatementByTransactionAndStatus.setParameter("status", status);
        querySrpnStatementByTransactionAndStatus.setParameter("srpnTransaction", srpnTransaction);
        return (SRPNStatement) querySrpnStatementByTransactionAndStatus.getSingleResult();
    }     
}
