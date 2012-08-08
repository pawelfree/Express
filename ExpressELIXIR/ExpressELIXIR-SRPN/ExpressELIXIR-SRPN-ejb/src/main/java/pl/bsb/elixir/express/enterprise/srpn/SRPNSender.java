package pl.bsb.elixir.express.enterprise.srpn;

import iso.std.iso._20022.tech.xsd.pacs_002_001.Document;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.Local;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger logger = LoggerFactory.getLogger(SRPNSender.class);    
  static final long serialVersionUID = 1L;

    @Override
  public Document rejectTransfer(String url, iso.std.iso._20022.tech.xsd.pacs_004_001.Document rejectDocument) {
    logger.debug("SRPN starting to send reject transfer message to client - transaction id : "
            .concat(rejectDocument.getPmtRtr().getTxInf().getOrgnlTxId()));
    try {
      return createService(url).rejectTransfer(rejectDocument);
    } catch (RuntimeException | MalformedURLException ex) {
      logger.error("Failed to reject transfer - transaction id "
              .concat(rejectDocument.getPmtRtr().getTxInf().getOrgnlTxId()), ex);
    }
    return null;
  }

  @Override
  public Document acknowledgeCredit(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document acknowledgeDocument) {
    logger.debug("SRPN starting to send acknowledge credit message to client - transaction id : "
            .concat(acknowledgeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    try {
      return createService(url).acknowledgeCredit(acknowledgeDocument);
    } catch (RuntimeException | MalformedURLException ex) {
      logger.error("Failed to acknowledge credit - transaction id "
              .concat(acknowledgeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()), ex);
    }
    return null;
  }

  @Override
  public Document acknowledgeDebit(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document acknowledgeDocument) {
    logger.debug("SRPN starting to send acknowledge debit message to client - transaction id : "
            .concat(acknowledgeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    try {
      return createService(url).acknowledgeDebit(acknowledgeDocument);
    } catch (RuntimeException | MalformedURLException ex) {
      logger.error("Failed to acknowledge debit message - transaction id "
              .concat(acknowledgeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()), ex);
    }
    return null;
  }

  @Override
  public Document authorizeTransfer(String url, iso.std.iso._20022.tech.xsd.pacs_008_001.Document authorizeDocument) {
    logger.debug("SRPN starting to send authorize transfer message to client - id : "
            .concat(authorizeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    try {
      return createService(url).authorizeTransfer(authorizeDocument);
    } catch (RuntimeException | MalformedURLException ex) {
      logger.error("Failed to authorize transfer - transaction id "
              .concat(authorizeDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()), ex);
    }
    return null;
  }

  private AgentService createService(String url) throws MalformedURLException {
    //TODO z konfiguracji od usera brac
      AgentService_Service agentService = new AgentService_Service(new URL(url));
    logger.debug("Constructed AgentService reference (url - ".concat(url).concat(" )"));
    if (agentService == null) {
      throw new RuntimeException("Service reference is null");
    }
    return agentService.getAgentServicePort();
  }
}
