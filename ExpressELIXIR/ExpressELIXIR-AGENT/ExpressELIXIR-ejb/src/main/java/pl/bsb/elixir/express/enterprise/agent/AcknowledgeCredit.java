package pl.bsb.elixir.express.enterprise.agent;

import iso.std.iso._20022.tech.xsd.pacs_002_001.Document;
import iso.std.iso._20022.tech.xsd.pacs_002_001.TransactionIndividualStatus3Code;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.enterprise.agent.interfaces.IAcknowledgeCredit;
import pl.bsb.elixir.express.entity.agent.InternalStatus;
import pl.bsb.elixir.express.entity.agent.TransactionIncoming;
import pl.bsb.elixir.express.entity.agent.provider.TransactionIncomingProvider;
import pl.bsb.elixir.express.util.ExternalStatusReason1Code;
import pl.bsb.elixir.express.util.ResponseDocumentCreator;

/**
 *
 * @author paweld
 */
@Stateless
@Local
public class AcknowledgeCredit implements IAcknowledgeCredit {

  private static final Logger logger = LoggerFactory.getLogger(AcknowledgeCredit.class);
  private static final long serialVersionUID = 16L;

  @EJB
  TransactionIncomingProvider transactionIncomingProvider;

  @Override
  public Document process(iso.std.iso._20022.tech.xsd.pacs_008_001.Document document) {
    Document response;
    String transactionId = document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId();
    TransactionIncoming transactionIncoming = transactionIncomingProvider.getTransactionById(transactionId);
    if ((transactionIncoming != null) && (transactionIncoming.getStatus().equals(InternalStatus.AUTHORIZE_ACCEPTED))) {
      transactionIncoming.setStatus(InternalStatus.ACKNOWLEDGE_CREDIT);
      transactionIncoming.credit(transactionIncoming.getTransactionAmount());
      //TODO sprawdzać czy kwota w transakcji jest taka sama jak kwota w komunikacie uznania rachunku
      response = ResponseDocumentCreator.createAcknowledgeCreditDebitResponse(document.getFIToFICstmrCdtTrf(), TransactionIndividualStatus3Code.ACSC);
      transactionIncoming.setStatus(InternalStatus.ACKNOWLEDGE_CREDIT_ACCEPTED);
      logger.info("Account "
              .concat(transactionIncoming.getReceiverAccount().getFormattedAccountNumber())
              .concat(" credited with amount ")
              .concat(transactionIncoming.getTransactionAmount().getAmount().toString()));
    } else {
      if (transactionIncoming == null) {
        logger.info("Cant find transaction with id : "
                .concat(transactionId)
                .concat(" to acknowledge credit."));
  
      } else {
        logger.warn("Transaction with id : "
                .concat(transactionId)
                .concat(" found but it has ")
                .concat(transactionIncoming.getStatus().value())
                .concat(" status, but should have staus of ")
                .concat(InternalStatus.AUTHORIZE_ACCEPTED.value()));        
      }
      response = ResponseDocumentCreator.createAcknowledgeCreditDebitResponse(
              ExternalStatusReason1Code.FF01,
              document.getFIToFICstmrCdtTrf(),
              TransactionIndividualStatus3Code.RJCT);
    }
    return response;
  }
}
