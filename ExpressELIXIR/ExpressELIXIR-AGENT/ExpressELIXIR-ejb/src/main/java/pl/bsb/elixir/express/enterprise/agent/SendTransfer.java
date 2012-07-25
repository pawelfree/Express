package pl.bsb.elixir.express.enterprise.agent;

import iso.std.iso._20022.tech.xsd.pacs_008_001.Document;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.enterprise.agent.interfaces.IAgentSender;
import pl.bsb.elixir.express.enterprise.agent.interfaces.ISendTransfer;
import pl.bsb.elixir.express.entity.agent.InternalStatus;
import pl.bsb.elixir.express.entity.agent.TransactionOutgoing;
import pl.bsb.elixir.express.entity.agent.provider.TransactionOutgoingProvider;
import pl.bsb.elixir.express.util.CreditTransferV02DocumentCreator;

/**
 *
 * @author paweld
 */
@Stateless
@Local
public class SendTransfer implements ISendTransfer {

  private static final Logger logger = LoggerFactory.getLogger(SendTransfer.class);
  private static final long serialVersionUID = 4L;
  //TODO do i need to initialize them?
  @EJB
  TransactionOutgoingProvider transactionOutgoingProvider;
  @EJB
  IAgentSender agentSender;

  @Override
  public boolean process(TransactionOutgoing transactionOutgoing) {
    transactionOutgoing.setStatus(InternalStatus.WAITING_TO_SEND);
    transactionOutgoingProvider.add(transactionOutgoing);
    Document request = prepareSendTransfer(transactionOutgoing);
    iso.std.iso._20022.tech.xsd.pacs_002_001.Document response = sendTransfer(request);
    boolean result = false;
    if (response == null) {
      //TODO nie udało się połączenie/wysłanie ustawić retry
      //TODO jesli próbował za dużo ustawić błąd
      logger.warn("Unknown sending error. Empty response");
      return result;
    }
    //TODO odpowiednia akcja w zaleznosci od odpowiedzi
    switch (response.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts()) {
      case ACSP: //PRZYJETY
        transactionOutgoing.setStatus(InternalStatus.ACCEPTED);
        result = true;
        break;
      case RJCT: //NIEPRZYJETY
        //TODO obsłużyć powód odrzucenia
        transactionOutgoing.setStatus(InternalStatus.REJECTED);
        break;
      default:
        logger.warn(String.format("Unknown status : %s for agent", response.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().name()));
        //TODO obsłużyc ten wątpliwy przypadek
        return false;
    }
    logger.info(String.format("Transaction with id : %s is %s by SRPN", 
            transactionOutgoing.getTransactionId(), 
            response.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().name()));
    
    return result;
  }

  // <editor-fold defaultstate="collapsed" desc="helper methods">        
  private iso.std.iso._20022.tech.xsd.pacs_002_001.Document sendTransfer(Document document) {
    return agentSender.sendTransferDocument(document);
  }

  private Document prepareSendTransfer(TransactionOutgoing transaction) {
    logger.debug("prepareSendTransfer. ".concat(transaction.toString()));
    return CreditTransferV02DocumentCreator.createSendTransferDocument(
            transaction.getMainKNR(),
            transaction.getTransactionId(),
            transaction.getTransactionAmount().getAmount(),
            "PL".concat(transaction.getSenderAccount().getIban()),
            transaction.getSenderKNR(),
            transaction.getReceiverKNR(),
            "PL".concat(transaction.getReceiverIban()));
  }
  // </editor-fold>
}
