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
//TODO interceptor musi dopuszczac nule jesli wywali sie funkcja w srodku
public class AcknowledgeCreditInterceptor extends StatementInterceptorHelper {

    private static final Logger logger = LoggerFactory.getLogger(AcknowledgeCreditInterceptor.class);

    @AroundInvoke
    private Object logStatement(final InvocationContext ic) throws Exception {
        logger.debug("acknowledge credit interceptor invoked on target {} and method {}",
                ic.getTarget().toString(),
                ic.getMethod().getName());
        try {
            Object[] params = ic.getParameters();
            if (params[0] != null) {
                addStatement((iso.std.iso._20022.tech.xsd.pacs_008_001.Document) params[0], InternalStatus.ACKNOWLEDGE_CREDIT);
            } else {
                logger.error(ic.getMethod().getName().concat(" invocation parameter is null"));
            }
            
            iso.std.iso._20022.tech.xsd.pacs_002_001.Document acknowledgeCreditResponse =
                    (iso.std.iso._20022.tech.xsd.pacs_002_001.Document) ic.proceed();
            
            if (acknowledgeCreditResponse != null) {
                InternalStatus status;
                if (acknowledgeCreditResponse.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.ACSC)) {
                    status = InternalStatus.ACKNOWLEDGE_CREDIT_ACCEPTED;
                } else {
                    status = InternalStatus.ACKNOWLEDGE_CREDIT_REJECTED;
                }
                addStatement(acknowledgeCreditResponse, status);
            } else {
                logger.error("Return from ".concat(ic.getMethod().getName()).concat(" is null"));
            }
            return acknowledgeCreditResponse;
        } finally {
        }
    }
}
