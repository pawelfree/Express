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
        logger.debug("send transfer interceptor invoked on target {} and method {}",
                ic.getTarget().toString(),
                ic.getMethod().getName());
        try {
            Object[] params = ic.getParameters();
            if (params[0] != null) {
                addStatement((iso.std.iso._20022.tech.xsd.pacs_008_001.Document) params[0], InternalStatus.WAITING_TO_SEND);
            } else {
                logger.error(ic.getMethod().getName().concat(" invocation parameter is null"));
            }

            iso.std.iso._20022.tech.xsd.pacs_002_001.Document sendTransferResponse =
                    (iso.std.iso._20022.tech.xsd.pacs_002_001.Document) ic.proceed();

            if (sendTransferResponse != null) {
                InternalStatus status;
                if (sendTransferResponse.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.ACSP)) {
                    status = InternalStatus.ACCEPTED;
                } else {
                    status = InternalStatus.REJECTED;
                }
                addStatement(sendTransferResponse, status);
            } else {
                logger.error("Return from ".concat(ic.getMethod().getName()).concat(" is null"));
            }
            return sendTransferResponse;
        } catch (Exception ex) {
            System.err.println("!!! send transfer interceptor To nie powinno sie przydażyć");  
            throw ex;
        } finally {
        }
    }
}
