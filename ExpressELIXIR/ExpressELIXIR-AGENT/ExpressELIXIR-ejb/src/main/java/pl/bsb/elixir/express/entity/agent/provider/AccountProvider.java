package pl.bsb.elixir.express.entity.agent.provider;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.entity.agent.Account;
import pl.bsb.proELIX.common.utils.ReadPropertyFile;
import pl.bsb.proELIX.common.utils.criteria.BaseSearchCriteria;

/**
 *
 * @author paweld
 */
@Stateless
@LocalBean
public class AccountProvider extends Provider<Account,BaseSearchCriteria> {
    
    private static final long serialVersionUID = 16L;
    private static final Logger logger = LoggerFactory.getLogger(ReadPropertyFile.class);
    
    public AccountProvider() {
        super(Account.class);
    }    
    
    
  /**
   * Wyszukuje jeden rachunek zgodnie z podanym numerem IBAN
   *
   * @param iban numer poszukiwanego rachunku
   * @return rachunek jeśli znalziony, w przeciwnym wypadku null
   */
  public Account getAccountByIBAN(String iban) {
        Query queryAccountByIBAN = this.getEntityManager().createNamedQuery("findAccountByIBAN");
        queryAccountByIBAN.setParameter("iban", iban);
        try {
          return (Account) queryAccountByIBAN.getSingleResult();
        }
        catch (NoResultException nre) {
          logger.info("Cant find account : ".concat(iban));
          return null;
        } 
    }     
}
