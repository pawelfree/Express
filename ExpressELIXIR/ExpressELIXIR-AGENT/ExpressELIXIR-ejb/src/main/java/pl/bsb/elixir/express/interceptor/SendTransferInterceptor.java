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
public class SendTransferInterceptor extends StatementInterceptorHelper {

  private static final Logger logger = LoggerFactory.getLogger(SendTransferInterceptor.class);

  @AroundInvoke
  private Object logStatement(final InvocationContext ic) throws Exception {
    logger.info("send transfer interceptor invoked on target {0} and method {1}",
            new Object[]{ic.getTarget().toString(),
              ic.getMethod().getName()});
    try {
      Object[] params = ic.getParameters();
      addStatement((iso.std.iso._20022.tech.xsd.pacs_008_001.Document) params[0], InternalStatus.WAITING_TO_SEND);
      
      iso.std.iso._20022.tech.xsd.pacs_002_001.Document acknowledgeCreditResponse = 
              (iso.std.iso._20022.tech.xsd.pacs_002_001.Document) ic.proceed();
      InternalStatus status;
      if (acknowledgeCreditResponse.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.ACSP)) {
        status = InternalStatus.ACCEPTED;
      } else {
        status = InternalStatus.REJECTED;
      }
      addStatement(acknowledgeCreditResponse, status);
      return acknowledgeCreditResponse;
      
    } finally {
    }
  }  
}
