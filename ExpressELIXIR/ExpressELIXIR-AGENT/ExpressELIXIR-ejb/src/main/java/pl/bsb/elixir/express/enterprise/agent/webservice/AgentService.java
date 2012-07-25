package pl.bsb.elixir.express.enterprise.agent.webservice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import pl.bsb.elixir.express.enterprise.agent.interfaces.IAcknowledgeCredit;
import pl.bsb.elixir.express.enterprise.agent.interfaces.IAcknowledgeDebit;
import pl.bsb.elixir.express.enterprise.agent.interfaces.IAuthorizeTransfer;
import pl.bsb.elixir.express.enterprise.agent.interfaces.IRejectTransfer;
import pl.bsb.elixir.express.interceptor.AcknowledgeCreditInterceptor;
import pl.bsb.elixir.express.interceptor.AcknowledgeDebitInterceptor;
import pl.bsb.elixir.express.interceptor.AuthorizeTransferInterceptor;
import pl.bsb.elixir.express.interceptor.RejectTransferInterceptor;

/**
 *
 * @author paweld
 */
public class AgentService {

  private static final Logger logger = Logger.getLogger(AgentService.class.getName());
  @EJB
  IAuthorizeTransfer authorizeTransfer;
  @EJB
  IRejectTransfer rejectTransfer;
  @EJB
  IAcknowledgeCredit acknowledgeCredit;
  @EJB
  IAcknowledgeDebit acknowledgeDebit;
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

  @Interceptors(AcknowledgeDebitInterceptor.class)
  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document acknowledgeDebit(iso.std.iso._20022.tech.xsd.pacs_008_001.Document document) {
    logger.log(Level.INFO,
            "Starting to acknowledge debit - transactionId : "
            .concat(document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    return acknowledgeDebit.process(document);
  }

  @Interceptors(AuthorizeTransferInterceptor.class)
  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document authorizeTransfer(iso.std.iso._20022.tech.xsd.pacs_008_001.Document document) {
    logger.log(Level.INFO,
            "Starting to authorize transfer - transactionId : "
            .concat(document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    return authorizeTransfer.process(document);
  }

  public String ping() {
    logger.log(Level.INFO,
            "AgentService - ping  called at : {0}",
            simpleDateFormat.format(Calendar.getInstance().getTime()));
    return "pong";
  }

  @Interceptors(RejectTransferInterceptor.class)
  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document rejectTransfer(iso.std.iso._20022.tech.xsd.pacs_004_001.Document document) {
    logger.log(Level.INFO,
            "Starting to reject transfer - transactionId : ".concat(document.getPmtRtr().getTxInf().getOrgnlTxId()));
    return rejectTransfer.process(document);

  }

  @Interceptors(AcknowledgeCreditInterceptor.class)
  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document acknowledgeCredit(iso.std.iso._20022.tech.xsd.pacs_008_001.Document document) {
    logger.log(Level.INFO,
            "Starting to acknowledge credit - transactionId : "
            .concat(document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    return acknowledgeCredit.process(document);
  }
}
