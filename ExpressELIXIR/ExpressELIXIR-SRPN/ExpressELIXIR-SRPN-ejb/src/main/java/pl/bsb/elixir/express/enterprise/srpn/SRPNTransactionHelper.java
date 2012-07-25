package pl.bsb.elixir.express.enterprise.srpn;

import iso.std.iso._20022.tech.xsd.pacs_008_001.Document;
import iso.std.iso._20022.tech.xsd.pacs_008_001.FIToFICustomerCreditTransferV02;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import pl.bsb.elixir.express.entity.srpn.SRPNInternalStatus;
import pl.bsb.elixir.express.entity.srpn.SRPNStatement;
import pl.bsb.elixir.express.entity.srpn.SRPNTransaction;
import pl.bsb.elixir.express.entity.srpn.provider.SRPNStatementProvider;
import pl.bsb.elixir.express.entity.srpn.provider.SRPNTransactionProvider;
import pl.bsb.elixir.express.util.JAXBMarshaller;

/**
 *
 * @author paweld
 */
@Stateless
@LocalBean
public class SRPNTransactionHelper {

  @EJB
  SRPNTransactionProvider transactionProvider;
  @EJB
  SRPNStatementProvider statementProvider;

  public Document getSendTransferTransaction(SRPNTransaction transaction) {
    SRPNStatement statement = statementProvider.getSrpnStatementByTransactionAndStatus(transaction, SRPNInternalStatus.SENDTRANSFER);
    return JAXBMarshaller.unmarshallSendTransferFromBase64(statement.getStatementData());
  }

  public SRPNTransaction updateTransactionStatusAndResponse(SRPNTransaction transaction,
          iso.std.iso._20022.tech.xsd.pacs_002_001.Document document) {
    return updateTransactionStatusAndResponse(transaction, document, transaction.getStatus());
  }

  public SRPNTransaction updateTransactionStatusAndResponse(
          SRPNTransaction transaction,
          iso.std.iso._20022.tech.xsd.pacs_002_001.Document document,
          SRPNInternalStatus status) {

    SRPNTransaction trans = transactionProvider.update(transaction);
    statementProvider.add(new SRPNStatement(JAXBMarshaller.marshallToBase64(document), trans, status));

    return trans;
  }

  public SRPNTransaction createSRPNTransaction(Document document) {

    FIToFICustomerCreditTransferV02 fitficctv = document.getFIToFICstmrCdtTrf();

    SRPNTransaction transaction = new SRPNTransaction();
    transaction.setOriginalTransactionDate(fitficctv.getGrpHdr().getCreDtTm().toGregorianCalendar().getTime());
    transaction.setReceiverKNR(fitficctv.getCdtTrfTxInf().getCdtrAgt().getFinInstnId().getOthr().getId());
    transaction.setSenderKNR(fitficctv.getCdtTrfTxInf().getDbtrAgt().getFinInstnId().getOthr().getId());
    transaction.setStatus(SRPNInternalStatus.SENDTRANSFER);
    transaction.setTransactionId(fitficctv.getCdtTrfTxInf().getPmtId().getTxId());
    int retryCounter = 3;
    transaction.setAcknowledgeCreditRetryCounter(retryCounter);
    transaction.setAcknowledgeDebitRetryCounter(retryCounter);
    transaction.setAcknowledgeDebitStatus(SRPNInternalStatus.NO_STATUS);
    transaction.setAcknowledgeCreditStatus(SRPNInternalStatus.NO_STATUS);
    transactionProvider.add(transaction);
    if (transaction != null) {
      statementProvider.add(new SRPNStatement(JAXBMarshaller.marshallToBase64(document), transaction));
    }
    return transaction;
  }

  public SRPNTransaction updateTransaction(SRPNTransaction transaction) {
    return transactionProvider.update(transaction);
  }

  public void addStatement(SRPNStatement statement) {
    statementProvider.add(statement);
  }
}
