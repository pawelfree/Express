package pl.bsb.elixir.express.interceptor;

import javax.ejb.EJB;
import pl.bsb.elixir.express.entity.agent.InternalStatus;
import pl.bsb.elixir.express.entity.agent.Statement;
import pl.bsb.elixir.express.entity.agent.provider.StatementProvider;
import pl.bsb.elixir.express.util.JAXBMarshaller;

/**
 *
 * @author paweld
 */
public class StatementInterceptorHelper {
  
  @EJB
  StatementProvider statementProvider;
  
  /**
   * Rejestruje komunikat typu iso.std.iso._20022.tech.xsd.pacs_008_001.Document
   * 
   * @param document komunikat do zarejestrowania
   * @param status status komunikatu
   */
  protected void addStatement(iso.std.iso._20022.tech.xsd.pacs_008_001.Document document, InternalStatus status) {
    addStatement(JAXBMarshaller.marshallToBase64(document),
            document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().getPmtId().getTxId(),
            document.getFIToFICstmrCdtTrf().getGrpHdr().getMsgId(),
            status);
  }
  
  /**
   * Rejestruje komunikat typu iso.std.iso._20022.tech.xsd.pacs_002_001.Document
   * 
   * @param document komunikat do zarejestrowania
   * @param status status komunikatu
   */
  protected void addStatement(iso.std.iso._20022.tech.xsd.pacs_002_001.Document document, InternalStatus status) {
    addStatement(JAXBMarshaller.marshallToBase64(document),
            document.getFIToFIPmtStsRpt().getTxInfAndSts().getOrgnlTxId(),
            document.getFIToFIPmtStsRpt().getGrpHdr().getMsgId(),
            status);
  }
  
  /**
   * Rejestruje komunikat typu iso.std.iso._20022.tech.xsd.pacs_004_001.Document
   * 
   * @param document komunikat do zarejestrowania
   * @param status status komunikatu
   */
  protected void addStatement(iso.std.iso._20022.tech.xsd.pacs_004_001.Document document, InternalStatus status) {
    addStatement(JAXBMarshaller.marshallToBase64(document),
            document.getPmtRtr().getTxInf().getOrgnlTxId(),
            document.getPmtRtr().getGrpHdr().getMsgId(),
            status);
  }
  
  private void addStatement(String document ,String transactionId, String messageId, InternalStatus status) {
    statementProvider.add(new Statement(document, transactionId, messageId, status));
  }
}
