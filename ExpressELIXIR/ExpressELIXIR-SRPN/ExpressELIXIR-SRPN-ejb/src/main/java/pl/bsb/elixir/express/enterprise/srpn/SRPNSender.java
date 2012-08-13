package pl.bsb.elixir.express.enterprise.srpn;

import iso.std.iso._20022.tech.xsd.pacs_002_001.Document;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import pl.bsb.elixir.express.enterprise.srpn.interfaces.ISRPNSender;
import pl.com.kir.srpn.soap.AgentService;
import pl.com.kir.srpn.soap.AgentService_Service;

/**
 *
 * @author paweld
 */
@Stateless
@Local
public class SRPNSender implements ISRPNSender {

  private static final Logger LOGGER = Logger.getLogger(SRPNSender.class.getName());    
  static final long serialVersionUID = 1L;

    @Override
  public Document rejectTransfer(String url, iso.std.iso._20022.tech.xsd.pacs_004_001.Document rejectDocument) {
    LOGGER.fine("SRPN starting to send reject transfer message to client - transaction id : "
            .concat(rejectDocument.getPmtRtr().getTxInf().getOrgnlTxId()));
    try {
      return createService(url).rejectTransfer(rejectDocument);
    } catch (RuntimeException | MalformedURLException ex) {
      LOGGER.log(Level.SEVERE,"Failed to reject transfer - transaction id "
              .concat(rejectDocument.getPmtRtr().getTxInf().getOrgnlTxId())
              .concat(" {}"), ex);
    }
    return null;
  }

  @Override
  public Document acknowledgeCredit(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document acknowledgeDocument) {
    LOGGER.fine("SRPN starting to send acknowledge credit message to client - transaction id : "
            .concat(acknowledgeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    try {
      return createService(url).acknowledgeCredit(acknowledgeDocument);
    } catch (RuntimeException | MalformedURLException ex) {
      LOGGER.log(Level.SEVERE,"Failed to acknowledge credit - transaction id "
              .concat(acknowledgeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId())
              .concat(" {}"), ex);
    }
    return null;
  }

  @Override
  public Document acknowledgeDebit(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document acknowledgeDocument) {
    LOGGER.fine("SRPN starting to send acknowledge debit message to client - transaction id : "
            .concat(acknowledgeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    try {
      return createService(url).acknowledgeDebit(acknowledgeDocument);
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE,"Failed to acknowledge debit message - transaction id "
              .concat(acknowledgeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId())
              , ex);
    }
    return null;
  }

  @Override
  public Document authorizeTransfer(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document authorizeDocument) {
    LOGGER.fine("SRPN starting to send authorize transfer message to client - id : "
            .concat(authorizeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    try {
      return createService(url).authorizeTransfer(authorizeDocument);
    } catch (RuntimeException | MalformedURLException ex) {
      LOGGER.log(Level.SEVERE,"Failed to authorize transfer - transaction id "
              .concat(authorizeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId())
              .concat(" {}"), ex);
    }
    return null;
  }

  private AgentService createService(String url) throws MalformedURLException {
    //TODO z konfiguracji od usera brac
      AgentService_Service agentService = new AgentService_Service(new URL(url));
    LOGGER.fine("Constructed AgentService reference (url - ".concat(url).concat(" )"));
    if (agentService == null) {
      throw new RuntimeException("Service reference is null");
    }
    return agentService.getAgentServicePort();
  }
}
