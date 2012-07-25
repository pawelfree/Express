package pl.bsb.elixir.express.interceptor;

import iso.std.iso._20022.tech.xsd.pacs_002_001.TransactionIndividualStatus3Code;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.entity.agent.InternalStatus;

/**
 *
 * @author paweld
 */
public class AcknowledgeDebitInterceptor extends StatementInterceptorHelper {

  private static final Logger logger = LoggerFactory.getLogger(AcknowledgeDebitInterceptor.class);

  @AroundInvoke
  private Object logStatement(final InvocationContext ic) throws Exception {
    logger.info("acknowledge debit interceptor invoked on target {0} and method {1}",
            new Object[]{ic.getTarget().toString(),
              ic.getMethod().getName()});
    try {
      Object[] params = ic.getParameters();
      addStatement((iso.std.iso._20022.tech.xsd.pacs_008_001.Document) params[0], InternalStatus.ACKNOWLEDGE_DEBIT);

      iso.std.iso._20022.tech.xsd.pacs_002_001.Document acknowledgeDebitResponse =
              (iso.std.iso._20022.tech.xsd.pacs_002_001.Document) ic.proceed();
      InternalStatus status;
      if (acknowledgeDebitResponse.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.ACSC)) {
        status = InternalStatus.ACKNOWLEDGE_DEBIT_ACCEPTED;
      } else {
        status = InternalStatus.ACKNOWLEDGE_DEBIT_REJECTED;
      }
      addStatement(acknowledgeDebitResponse, status);
      return acknowledgeDebitResponse;
    } finally {
    }
  }
}
