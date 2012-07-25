package pl.bsb.elixir.express.enterprise.agent;

import iso.std.iso._20022.tech.xsd.pacs_002_001.Document;
import iso.std.iso._20022.tech.xsd.pacs_002_001.TransactionIndividualStatus3Code;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.enterprise.agent.interfaces.IAcknowledgeDebit;
import pl.bsb.elixir.express.entity.agent.InternalStatus;
import pl.bsb.elixir.express.entity.agent.TransactionOutgoing;
import pl.bsb.elixir.express.entity.agent.provider.TransactionOutgoingProvider;
import pl.bsb.elixir.express.util.ExternalStatusReason1Code;
import pl.bsb.elixir.express.util.ResponseDocumentCreator;

/**
 *
 * @author paweld
 */
@Stateless
@Local
public class AcknowledgeDebit implements IAcknowledgeDebit {
  
  private static final Logger logger = LoggerFactory.getLogger(AcknowledgeDebit.class);
  private static final long serialVersionUID = 17L;
  
  @EJB
  TransactionOutgoingProvider transactionOutgoingProvider;

  //TODO słownik mainKNR - KNRy
  //TODO sprawdzać czy komunikat jest właściwy - chyba po rodzaju operacji do wykonania
  
  
  @Override
  public Document process(iso.std.iso._20022.tech.xsd.pacs_008_001.Document document) {
    Document response;
    String transactionId = document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId();
    TransactionOutgoing transactionOutgoing = transactionOutgoingProvider.getTransactionById(transactionId);
    //TODO czy sprawdzać ujemne saldo??
    if ((transactionOutgoing != null) && (transactionOutgoing.getStatus().equals(InternalStatus.ACCEPTED))) {
      transactionOutgoing.setStatus(InternalStatus.ACKNOWLEDGE_DEBIT);
      transactionOutgoing.debitAndReleaseBlockade(transactionOutgoing.getTransactionAmount());
      //TODO sprawdzać czy kwota w transakcji jest taka sama jak kwota w komunikacie uznania rachunku
      response = ResponseDocumentCreator.createAcknowledgeCreditDebitResponse(document.getFIToFICstmrCdtTrf(), TransactionIndividualStatus3Code.ACSC); 
      transactionOutgoing.setStatus(InternalStatus.ACKNOWLEDGE_DEBIT_ACCEPTED);
      logger.info("Account "
              .concat(transactionOutgoing.getSenderAccount().getFormattedAccountNumber())
              .concat(" debited with amount ")
              .concat(transactionOutgoing.getTransactionAmount().getAmount().toString()));      
    } else {
      if (transactionOutgoing == null) {
      logger.warn("Cant find transaction with id : "
              .concat(transactionId)
              .concat(" to acknowledge debit."));        
      } else {
        logger.warn("Transaction with id : "
                .concat(transactionId)
                .concat(" found but it has ")
                .concat(transactionOutgoing.getStatus().value())
                .concat(" status, but should have staus of ")
                .concat(InternalStatus.ACCEPTED.value()));         
      }
      response = ResponseDocumentCreator.createAcknowledgeCreditDebitResponse(
              ExternalStatusReason1Code.FF01,
              document.getFIToFICstmrCdtTrf(),
              TransactionIndividualStatus3Code.RJCT); 
    }
    return response;
  }

}
