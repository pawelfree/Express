package pl.bsb.elixir.express.enterprise.agent;

import iso.std.iso._20022.tech.xsd.pacs_002_001.Document;
import iso.std.iso._20022.tech.xsd.pacs_002_001.TransactionIndividualStatus3Code;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.enterprise.agent.interfaces.IRejectTransfer;
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
public class RejectTransfer implements IRejectTransfer {

  private static final Logger logger = LoggerFactory.getLogger(RejectTransfer.class);
  private static final long serialVersionUID = 17L;
  @EJB
  TransactionOutgoingProvider transactionOutgoingProvider;

  //TODO słownik mainKNR - KNRy
  //TODO zweryfikować czy obsługiwane są wszystkie przypadki błędów - dorobić szczegółowe statusy

  @Override
  public Document process(iso.std.iso._20022.tech.xsd.pacs_004_001.Document document) {
    Document response;
    String transactionId = document.getPmtRtr().getTxInf().getOrgnlTxId();
    TransactionOutgoing transactionOutgoing = transactionOutgoingProvider.getTransactionById(transactionId);
    if ((transactionOutgoing != null) && (transactionOutgoing.getStatus().equals(InternalStatus.ACCEPTED))) {
      transactionOutgoing.setStatus(InternalStatus.REJECT_TRANSFER);
      transactionOutgoing.releaseBlockade(transactionOutgoing.getTransactionAmount());
      response = ResponseDocumentCreator.createRejectTransferResponse(document.getPmtRtr(), TransactionIndividualStatus3Code.ACSC);
      transactionOutgoing.setStatus(InternalStatus.REJECT_TRANSFER_ACCEPTED);
    } else {
      if (transactionOutgoing == null) {
        logger.warn("Cant find transaction with id : ".concat(transactionId).concat(" to reject transfer."));
      } else {
        //TODO na pewno są inne będy
        logger.warn("Other reason to reject transfer.");
      }
      response = ResponseDocumentCreator.createRejectTransferResponse(
              ExternalStatusReason1Code.FF01,
              document.getPmtRtr(),
              TransactionIndividualStatus3Code.RJCT);
    }
    return response;
  }
}
