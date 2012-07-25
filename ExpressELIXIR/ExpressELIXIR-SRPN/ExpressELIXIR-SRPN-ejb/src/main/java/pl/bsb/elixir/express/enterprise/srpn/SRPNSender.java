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

  private static final Logger logger = Logger.getLogger(SRPNSender.class.getName());
  static final long serialVersionUID = 1L;

    @Override
  public Document rejectTransfer(String url, iso.std.iso._20022.tech.xsd.pacs_004_001.Document rejectDocument) {
    logger.log(Level.INFO,
            "SRPN starting to reject transfer - id : "
            .concat(rejectDocument.getPmtRtr().getTxInf().getOrgnlTxId()));
    try {
      return createService(url).rejectTransfer(rejectDocument);
    } catch (RuntimeException | MalformedURLException ex) {
      logger.log(Level.SEVERE, "Failed to reject transfer with message : {0}", ex.getMessage());
    }
    return null;
  }

  @Override
  public Document acknowledgeCredit(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document acknowledgeDocument) {
    logger.log(Level.INFO,
            "SRPN starting to acknowledge credit - id : "
            .concat(acknowledgeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    try {
      return createService(url).acknowledgeCredit(acknowledgeDocument);
    } catch (RuntimeException | MalformedURLException ex) {
      logger.log(Level.SEVERE, "Failed to acknowledge credit with message : {0}", ex.getMessage());
    }
    return null;
  }

  @Override
  public Document acknowledgeDebit(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document acknowledgeDocument) {
    logger.log(Level.INFO,
            "SRPN starting to acknowledge debit - id : "
            .concat(acknowledgeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    try {
      return createService(url).acknowledgeDebit(acknowledgeDocument);
    } catch (RuntimeException | MalformedURLException ex) {
      logger.log(Level.SEVERE, "Failed to acknowledge debit with message : {0}", ex.getMessage());
    }
    return null;
  }

  @Override
  public Document authorizeTransfer(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document authorizeDocument) {
    logger.log(Level.INFO,
            "SRPN starting to authorize transfer - id : "
            .concat(authorizeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    try {
      return createService(url).authorizeTransfer(authorizeDocument);
    } catch (RuntimeException | MalformedURLException ex) {
      logger.log(Level.SEVERE, "Failed to authorize transfer with message : {0}", ex.getMessage());
    }
    return null;
  }

  private AgentService createService(String url) throws MalformedURLException {
    //TODO z konfiguracji od usera brac
      AgentService_Service agentService = new AgentService_Service(new URL(url));
    logger.log(Level.FINE, "Constructed AgentService reference (url - ".concat(url).concat(" )"));
    if (agentService == null) {
      throw new RuntimeException("Service reference is null");
    }
    return agentService.getAgentServicePort();
  }
}
