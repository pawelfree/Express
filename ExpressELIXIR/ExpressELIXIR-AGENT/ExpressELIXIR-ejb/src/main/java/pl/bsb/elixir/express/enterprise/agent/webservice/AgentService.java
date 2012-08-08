package pl.bsb.elixir.express.enterprise.agent.webservice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger logger = LoggerFactory.getLogger(AgentService.class);  
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
    logger.debug("Starting to acknowledge debit - transactionId : "
            .concat(document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    return acknowledgeDebit.process(document);
  }

  @Interceptors(AuthorizeTransferInterceptor.class)
  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document authorizeTransfer(iso.std.iso._20022.tech.xsd.pacs_008_001.Document document) {
    logger.debug("Starting to authorize transfer - transaction Id : "
            .concat(document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    return authorizeTransfer.process(document);
  }

  public String ping() {
    logger.debug("AgentService - ping  called at : ".concat(simpleDateFormat.format(Calendar.getInstance().getTime())));
    return "pong";
  }

  @Interceptors(RejectTransferInterceptor.class)
  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document rejectTransfer(iso.std.iso._20022.tech.xsd.pacs_004_001.Document document) {
    logger.debug("Starting to reject transfer - transactionId : ".concat(document.getPmtRtr().getTxInf().getOrgnlTxId()));
    return rejectTransfer.process(document);

  }

  @Interceptors(AcknowledgeCreditInterceptor.class)
  public iso.std.iso._20022.tech.xsd.pacs_002_001.Document acknowledgeCredit(iso.std.iso._20022.tech.xsd.pacs_008_001.Document document) {
    logger.debug("Starting to acknowledge credit - transactionId : "
            .concat(document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId()));
    return acknowledgeCredit.process(document);
  }
}
