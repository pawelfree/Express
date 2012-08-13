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
public class RejectTransferInterceptor extends StatementInterceptorHelper {

    private static final Logger logger = LoggerFactory.getLogger(RejectTransferInterceptor.class);

    @AroundInvoke
    private Object logStatement(final InvocationContext ic) throws Exception {
        logger.debug("reject transfer interceptor invoked on target {} and method {}",
                ic.getTarget().toString(),
                ic.getMethod().getName());
        try {
            Object[] params = ic.getParameters();
            if (params[0] != null) {
                addStatement((iso.std.iso._20022.tech.xsd.pacs_004_001.Document) params[0], InternalStatus.REJECT_TRANSFER);
            } else {
                logger.error(ic.getMethod().getName().concat(" invocation parameter is null"));
            }

            iso.std.iso._20022.tech.xsd.pacs_002_001.Document rejectTransferResponse =
                    (iso.std.iso._20022.tech.xsd.pacs_002_001.Document) ic.proceed();

            if (rejectTransferResponse != null) {
                InternalStatus status;
                if (rejectTransferResponse.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.ACSC)) {
                    status = InternalStatus.REJECT_TRANSFER_ACCEPTED;
                } else {
                    status = InternalStatus.REJECT_TRANSFER_REJECTED;
                }
                addStatement(rejectTransferResponse, status);
            } else {
                logger.error("Return from ".concat(ic.getMethod().getName()).concat(" is null"));
            }
            return rejectTransferResponse;
        } catch (Exception ex) {
            System.err.println("!!! reject transfer interceptor To nie powinno sie przydażyć");  
            throw ex;
        } finally {
        }
    }
}
