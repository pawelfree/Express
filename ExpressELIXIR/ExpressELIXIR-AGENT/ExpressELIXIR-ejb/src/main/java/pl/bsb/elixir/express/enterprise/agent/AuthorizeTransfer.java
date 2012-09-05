package pl.bsb.elixir.express.enterprise.agent;

import iso.std.iso._20022.tech.xsd.pacs_002_001.Document;
import iso.std.iso._20022.tech.xsd.pacs_002_001.TransactionIndividualStatus3Code;
import iso.std.iso._20022.tech.xsd.pacs_008_001.CreditTransferTransactionInformation11;
import iso.std.iso._20022.tech.xsd.pacs_008_001.FIToFICustomerCreditTransferV02;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.enterprise.agent.interfaces.IAuthorizeTransfer;
import pl.bsb.elixir.express.entity.agent.Account;
import pl.bsb.elixir.express.entity.agent.InternalStatus;
import pl.bsb.elixir.express.entity.agent.Money;
import pl.bsb.elixir.express.entity.agent.TransactionIncoming;
import pl.bsb.elixir.express.entity.agent.provider.AccountProvider;
import pl.bsb.elixir.express.entity.agent.provider.TransactionIncomingProvider;
import pl.bsb.elixir.express.util.ExternalReturnReason1Code;
import pl.bsb.elixir.express.util.ResponseDocumentCreator;
import pl.bsb.proELIX.common.utils.StringUtil;

/**
 *
 * @author paweld
 */
@Stateless
@Local
//TODO przerobić jak AcknowledgeDebit z uwagi na optimisticlock jesli SRPN przyśle nawał
public class AuthorizeTransfer implements IAuthorizeTransfer {

  private static final Logger logger = LoggerFactory.getLogger(AuthorizeTransfer.class);
  
  private static final long serialVersionUID = 16L;
  @EJB
  AccountProvider accountProvider;
  @EJB
  TransactionIncomingProvider transactionIncomingProvider;

  @Override
  public Document process(iso.std.iso._20022.tech.xsd.pacs_008_001.Document document) {
    Document response;

    //TODO zweryfikować czy obsługiwane są wszystkie przypadki błędów - dorobić szczegółowe statusy
    CreditTransferTransactionInformation11 ctti = document.getFIToFICstmrCdtTrf().getCdtTrfTxInf();
    //TODO co jesli pod spodem zwróci zdublowaną transakcję!!! to jest przypadek biznesowy
    TransactionIncoming transactionIncoming = transactionIncomingProvider.getTransactionById(ctti.getPmtId().getTxId());
    Account account = getAccountIfExist(ctti.getCdtrAcct().getId().getIBAN());
    //Status nie jest ważny - nie powinno być takiej transakcji
    //TODO !!!! to jest nie dobrze bo liczy sie jeszcze knr nadawcy przelewu - przy wszystkich transakcjach
    if ((transactionIncoming == null) && (account != null)) {
      transactionIncoming = createTransaction(document.getFIToFICstmrCdtTrf(), account);
      response = ResponseDocumentCreator.createAuthorizeTransferResponse(
              document.getFIToFICstmrCdtTrf(),
              TransactionIndividualStatus3Code.ACCP);
      transactionIncoming.setStatus(InternalStatus.AUTHORIZE_ACCEPTED);
    } else {
      ExternalReturnReason1Code reasonCode;
      if (transactionIncoming != null) {
        logger.warn("Transaction with id : "
                .concat(transactionIncoming.getTransactionId())
                .concat(" is already registerd."));
        reasonCode = ExternalReturnReason1Code.AM05;
      } else if (account == null) {
        logger.warn("Cant find account with IBAN : "
                .concat(ctti.getCdtrAcct().getId().getIBAN())
                .concat(" to authorize transfer."));
        reasonCode = ExternalReturnReason1Code.AC01;
      } else {
        //TODO inne przyczyny teraz tutaj nie wejdzie
        logger.warn("Other reason to reject authorize transfer.");
        reasonCode = ExternalReturnReason1Code.MS03;
      }
      response = ResponseDocumentCreator.createAuthorizeTransferResponse(
              reasonCode,
              null,
              document.getFIToFICstmrCdtTrf(),
              TransactionIndividualStatus3Code.RJCT);
    }

    return response;
  }

  private TransactionIncoming createTransaction(FIToFICustomerCreditTransferV02 fitficctv, Account account) {
    TransactionIncoming transactionIncoming = new TransactionIncoming();
    transactionIncoming.setReceiverAccount(account);
    transactionIncoming.setStatus(InternalStatus.AUTHORIZE_TRANSFER);
    transactionIncoming.setTransactionAmount(new Money(fitficctv.getCdtTrfTxInf().getInstdAmt().getValue()));
    transactionIncoming.setTransactionDate(new Date());
    transactionIncoming.setTransactionId(fitficctv.getCdtTrfTxInf().getPmtId().getTxId());
    transactionIncoming.setReceiverIban(fitficctv.getCdtTrfTxInf().getCdtrAcct().getId().getIBAN().replaceAll("PL", ""));
    transactionIncoming.setSenderIban(fitficctv.getCdtTrfTxInf().getDbtrAcct().getId().getIBAN().replaceAll("PL", ""));
    transactionIncomingProvider.add(transactionIncoming);
    return transactionIncoming;
  }

  private Account getAccountIfExist(String accountNumber) {
    return accountProvider.getAccountByIBAN(StringUtil.stripWhitespaces(accountNumber.replaceAll("PL", "")));
  }
}
