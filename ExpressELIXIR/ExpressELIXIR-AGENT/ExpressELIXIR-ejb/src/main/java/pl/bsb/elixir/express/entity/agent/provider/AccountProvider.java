package pl.bsb.elixir.express.entity.agent.provider;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.entity.agent.Account;
import pl.bsb.elixir.express.entity.agent.InternalStatus;
import pl.bsb.elixir.express.entity.agent.Money;
import pl.bsb.elixir.express.entity.agent.TransactionIncoming;
import pl.bsb.elixir.express.entity.agent.TransactionOutgoing;
import pl.bsb.proELIX.common.utils.criteria.BaseSearchCriteria;

/**
 *
 * @author paweld
 */
@Stateless
@LocalBean
public class AccountProvider extends Provider<Account, BaseSearchCriteria> {

    private static final long serialVersionUID = 16L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountProvider.class);

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
            LOGGER.info("Cant find account : ".concat(iban));
            return null;
        }
    }

    /**
     * Dodaje kwotę do blokady na rachunku. Nie obsługuje wielowątkowego modyfikowania account.
     *
     * @param account rachunek dla którego zwiększyć blokadę
     * @param amount kwota o którą zwiększyć blokadę
     */
    public void addToBlockedBalance(Account account, Money amount) {
        account = get(account.getId());
        account.addToBlockedBalance(amount);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void creditTransaction(TransactionIncoming transaction) {
        Account account = get(transaction.getReceiverAccount().getId());
        account.credit(transaction.getTransactionAmount());
        transaction.setStatus(InternalStatus.ACKNOWLEDGE_CREDIT_ACCEPTED);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void debitTransaction(TransactionOutgoing transaction) {
        Account account = get(transaction.getSenderAccount().getId());
        account.debit(transaction.getTransactionAmount());
        releaseBlockade(account, transaction.getTransactionAmount());
        transaction.setStatus(InternalStatus.ACKNOWLEDGE_DEBIT_ACCEPTED);

    }

    /**
     * Zwalnia blokadę na rachunku o podaną kwotę
     *
     * @param account rachunek którego blokadę należy zmniejszyć
     * @param amount kwota o którą należy zmniejszyć blokadę
     */
    public void releaseBlockade(Account account, Money amount) {
        try {
            account.subtractFromBlockedBalance(amount);
        } catch (Exception ex) {
            LOGGER.error("Release blockade for account".concat(account.getFormattedAccountNumber())
                    .concat(" ") + ex.getClass() + " cause " + ex.getCause());
        }
    }
}
