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
public class AuthorizeTransferInterceptor extends StatementInterceptorHelper {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizeTransferInterceptor.class);

    @AroundInvoke
    private Object logStatement(final InvocationContext ic) throws Exception {
        logger.debug("authorize transfer interceptor invoked on target {} and method {}",
                ic.getTarget().toString(),
                ic.getMethod().getName());
        try {
            Object[] params = ic.getParameters();
            if (params[0] != null) {
                addStatement((iso.std.iso._20022.tech.xsd.pacs_008_001.Document) params[0], InternalStatus.AUTHORIZE_TRANSFER);
            } else {
                logger.error(ic.getMethod().getName().concat(" invocation parameter is null"));
            }

            iso.std.iso._20022.tech.xsd.pacs_002_001.Document authorizeTransferResponse =
                    (iso.std.iso._20022.tech.xsd.pacs_002_001.Document) ic.proceed();

            if (authorizeTransferResponse != null) {
                InternalStatus status;
                if (authorizeTransferResponse.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts().equals(TransactionIndividualStatus3Code.ACCP)) {
                    status = InternalStatus.AUTHORIZE_ACCEPTED;
                } else {
                    status = InternalStatus.AUTHORIZE_REJECTED;
                }
                addStatement(((iso.std.iso._20022.tech.xsd.pacs_008_001.Document) params[0]).getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId(),authorizeTransferResponse, status);
            } else {
                logger.error("Return from ".concat(ic.getMethod().getName()).concat(" is null"));
            }
            return authorizeTransferResponse;
        } catch (Exception ex) {
            System.err.println("!!! authorize transfer interceptor To nie powinno sie przydażyć");  
            throw ex;
        } finally {
        }
    }
}
