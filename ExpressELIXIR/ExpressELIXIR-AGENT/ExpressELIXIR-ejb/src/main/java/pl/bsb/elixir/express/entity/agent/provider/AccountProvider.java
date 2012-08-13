package pl.bsb.elixir.express.entity.agent.provider;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.entity.agent.Account;
import pl.bsb.elixir.express.entity.agent.InternalStatus;
import pl.bsb.elixir.express.entity.agent.Money;
import pl.bsb.elixir.express.entity.agent.TransactionOutgoing;
import pl.bsb.proELIX.common.utils.ReadPropertyFile;
import pl.bsb.proELIX.common.utils.criteria.BaseSearchCriteria;

/**
 *
 * @author paweld
 */
@Stateless
@LocalBean
public class AccountProvider extends Provider<Account, BaseSearchCriteria> {

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
        } catch (NoResultException nre) {
            logger.info("Cant find account : ".concat(iban));
            return null;
        }
    }

    //TODO optimistic lock  
//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//    public void debitAndReleaseBlockade(Account account, Money amount) {
//        try {
//            account.debit(amount);
//            account.subtractFromBlockedBalance(amount);
//        } catch (Exception ex) {
//            System.err.println("!!! debitAndRelease ".concat(account.getFormattedAccountNumber()).concat(" ") + ex.getClass() + "cause " + ex.getCause());
//        }
//    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    //TODO optimistic lock
    public void addToBlockedBalance(Account account, Money amount) {
        try {
            account = get(account.getId());
            try {
                account.addToBlockedBalance(amount);
            } catch (OptimisticLockException ex) {
                logger.info("Cant add to blockade on account ".concat(account.getIban())
                        .concat(" amount ").concat(amount.toString()), ex);

            }
        } catch (Exception ex) {
            System.err.println("!!! addToBlockade exception " + ex.getClass() + " cause " + ex.getCause());
        }
    }

    public void debitTransaction(TransactionOutgoing transaction) {
        try {
            Account account = get(transaction.getSenderAccount().getId());
            debitTransactionInNewTransaction(account, transaction);
 
        } catch (Exception e) {
            System.err.println("!!! debit transaction ".concat(transaction.getTransactionId()).concat(" ") + e.getClass() + " cause " + e.getCause());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void debitTransactionInNewTransaction(Account account, TransactionOutgoing transaction) {
        debit(account, transaction.getTransactionAmount());
        releaseBlockade(account, transaction.getTransactionAmount());
        //TODO pobrac wlasciwa + OptimisticLock
        transaction.setStatus(InternalStatus.ACKNOWLEDGE_DEBIT_ACCEPTED);
                   getEntityManager().flush(); 

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Account releaseBlockade(Account account, Money amount) {
        try {
            account.subtractFromBlockedBalance(amount);
        } catch (Exception ex) {
            System.err.println("!!! release ".concat(account.getFormattedAccountNumber()).concat(" ") + ex.getClass() + " cause " + ex.getCause());
        }

        return account;
    }

    //@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    //TODO optimistic lock
    public Account debit(Account account, Money amount) {
        try {
            account.debit(amount);
        } catch (Exception ex) {
            System.err.println("!!! debit ".concat(account.getFormattedAccountNumber()).concat(" ") + ex.getClass() + " cause " + ex.getCause());
        }
        return account;
    }
}
