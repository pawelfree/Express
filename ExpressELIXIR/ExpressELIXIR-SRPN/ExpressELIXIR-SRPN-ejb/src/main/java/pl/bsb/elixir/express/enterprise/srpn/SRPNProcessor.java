package pl.bsb.elixir.express.enterprise.srpn;

import iso.std.iso._20022.tech.xsd.pacs_002_001.TransactionIndividualStatus3Code;
import iso.std.iso._20022.tech.xsd.pacs_008_001.Document;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bsb.elixir.express.enterprise.srpn.interfaces.ISRPNProcessor;
import pl.bsb.elixir.express.enterprise.srpn.interfaces.ISRPNSender;
import pl.bsb.elixir.express.entity.srpn.SRPNInternalStatus;
import pl.bsb.elixir.express.entity.srpn.SRPNStatement;
import pl.bsb.elixir.express.entity.srpn.SRPNTransaction;
import pl.bsb.elixir.express.util.CreditTransferV02DocumentCreator;
import pl.bsb.elixir.express.util.JAXBMarshaller;

/**
 *
 * @author paweld
 */
@Stateless
@Local
public class SRPNProcessor implements ISRPNProcessor {
  
  //TODO !!!! messageId tworzy automatycznie dokument Long.toString(System.currentTimeMillis())

  private static final Logger logger = LoggerFactory.getLogger(SRPNProcessor.class);  
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  //TODO tak sobie wymyśliłem
  private static final int RETRY_COUNTER = 3;
  
  //TODO update bazodanowy
  
  @EJB
  ISRPNSender iSRPNSender;
  @EJB
  SRPNTransactionHelper transactionHelper;

  @Override
  @Asynchronous
  public void processTransfer(Document sendTransferDocument, SRPNTransaction transaction) {
    logger.info("SRPN asynchronous transfer processing. Transaction id - ".concat(transaction.getTransactionId()));
    TransactionIndividualStatus3Code response = authorizeTransfer(sendTransferDocument, transaction);
    if ((response == null) || (response == TransactionIndividualStatus3Code.RJCT)) {
      logger.warn("Cant authorize transaction "
              .concat(transaction.getTransactionId()));
      //odrzucona
      //TODO dla zabawy asynchroniczna
      rejectTransfer(sendTransferDocument, transaction);
    } else {
      //zaakceptowana
      //TODO a tutaj celowo asynchroniczne wywołanie
      acknowledgeCredit(sendTransferDocument, transaction);
      acknowledgeDebit(sendTransferDocument, transaction);
    }

  }

  @Asynchronous
  private void acknowledgeDebit(Document sendTransferDocument, SRPNTransaction transaction) {
    logger.info("SRPN started to acknowledge debit "
            .concat(transaction.getTransactionId())
            .concat(" at ")
            .concat(simpleDateFormat.format(Calendar.getInstance().getTime())));
    Document acknowledgeDocument = CreditTransferV02DocumentCreator.createAcknowledgeDebitDocument(sendTransferDocument);
    transaction.setAcknowledgeDebitStatus(SRPNInternalStatus.ACKNOWLEDGE_DEBIT);
    transactionHelper.addStatement(new SRPNStatement(JAXBMarshaller.marshallToBase64(acknowledgeDocument), transaction));
    String url = "http://localhost:8080/AgentService/AgentService".concat(transaction.getReceiverKNR()).concat("?wsdl");
    iso.std.iso._20022.tech.xsd.pacs_002_001.Document response = null;
    for (int i = 0; i < RETRY_COUNTER; i++) {
      response = iSRPNSender.acknowledgeDebit(url, acknowledgeDocument);
      if (response == null) {
        i++;
        transactionHelper.updateTransaction(transaction);
        logger.warn("Cant acknowledge debit because of some technical reason. Consult log for more information.");
      } else {
        break;
      }
    }
    if (response == null) {
      return;
    }
    TransactionIndividualStatus3Code responseStatus = response.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts();
    logger.info("SRPN Transaction : "
            .concat(response.getFIToFIPmtStsRpt().getTxInfAndSts().getOrgnlTxId())
            .concat(" acknowledge debit status : ")
            .concat(responseStatus.value()));
    switch (responseStatus) {
      case RJCT: {
        transaction.setExternalRejectionCode(response.getFIToFIPmtStsRpt().getTxInfAndSts().getStsRsnInf().getRsn().getCd());
        transaction.setSrpnRejectionCode(response.getFIToFIPmtStsRpt().getTxInfAndSts().getStsRsnInf().getRsn().getPrtry());
        transaction.setAcknowledgeDebitStatus(SRPNInternalStatus.ACKNOWLEDGE_DEBIT_REJECTED);
        break;
      }
      case ACSC: {
        transaction.setAcknowledgeDebitStatus(SRPNInternalStatus.ACKNOWLEDGE_DEBIT_ACCEPTED);
        break;
      }
      default: {
      }
    }
    transactionHelper.updateTransactionStatusAndResponse(transaction, response, transaction.getAcknowledgeDebitStatus());
  }

  @Asynchronous
  public void acknowledgeCredit(Document sendTransferDocument, SRPNTransaction transaction) {
    logger.info("SRPN started to acknowledge credit "
            .concat(transaction.getTransactionId())
            .concat(" at ")
            .concat(simpleDateFormat.format(Calendar.getInstance().getTime())));
    Document acknowledgeDocument = CreditTransferV02DocumentCreator.createAcknowledgeCreditDocument(sendTransferDocument);
    transaction.setAcknowledgeCreditStatus(SRPNInternalStatus.ACKNOWLEDGE_CREDIT);
    transactionHelper.addStatement(new SRPNStatement(JAXBMarshaller.marshallToBase64(acknowledgeDocument), transaction));
    String url = "http://localhost:8080/AgentService/AgentService".concat(transaction.getReceiverKNR()).concat("?wsdl");

    iso.std.iso._20022.tech.xsd.pacs_002_001.Document response = null;
    for (int i = 0; i < RETRY_COUNTER; i++) {
      response = iSRPNSender.acknowledgeCredit(url, acknowledgeDocument);
      if (response == null) {
        i++;
        transactionHelper.updateTransaction(transaction);
        logger.warn("Cant acknowledge credit because of some technical reason. Consult log for more information.");
      } else {
        break;
      }
    }
    if (response == null) {
      return;
    }
    TransactionIndividualStatus3Code responseStatus = response.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts();
    logger.info("SRPN Transaction : "
            .concat(response.getFIToFIPmtStsRpt().getTxInfAndSts().getOrgnlTxId())
            .concat(" acknowledge credit status : ")
            .concat(responseStatus.value()));
    switch (responseStatus) {
      case RJCT: {
        transaction.setExternalRejectionCode(response.getFIToFIPmtStsRpt().getTxInfAndSts().getStsRsnInf().getRsn().getCd());
        transaction.setSrpnRejectionCode(response.getFIToFIPmtStsRpt().getTxInfAndSts().getStsRsnInf().getRsn().getPrtry());
        transaction.setAcknowledgeCreditStatus(SRPNInternalStatus.ACKNOWLEDGE_CREDIT_REJECTED);
        break;
      }
      case ACSC: {
        transaction.setAcknowledgeCreditStatus(SRPNInternalStatus.ACKNOWLEDGE_CREDIT_ACCEPTED);
        break;
      }
      default: {
      }
    }
    transactionHelper.updateTransactionStatusAndResponse(transaction, response, transaction.getAcknowledgeCreditStatus());
  }

  @Asynchronous
  private void rejectTransfer(Document sendTransferDocument, SRPNTransaction transaction) {
    logger.info("SRPN started to reject transaction "
            .concat(transaction.getTransactionId())
            .concat(" at ")
            .concat(simpleDateFormat.format(Calendar.getInstance().getTime())));
    iso.std.iso._20022.tech.xsd.pacs_004_001.Document rejectDocument = CreditTransferV02DocumentCreator.createRejectTransferDocument(
            transaction.getExternalRejectionCode(),
            transaction.getSrpnRejectionCode(),
            sendTransferDocument);
    transaction.setStatus(SRPNInternalStatus.REJECT_TRANSFER);
    transactionHelper.addStatement(new SRPNStatement(JAXBMarshaller.marshallToBase64(rejectDocument), transaction));
    String url = "http://localhost:8080/AgentService/AgentService".concat(transaction.getSenderKNR()).concat("?wsdl");
    iso.std.iso._20022.tech.xsd.pacs_002_001.Document response = null;
    for (int i = 0; i < RETRY_COUNTER; i++) {
      response = iSRPNSender.rejectTransfer(url, rejectDocument);
      if (response == null) {
        i++;
        transactionHelper.updateTransaction(transaction);
        logger.warn("Cant reject transaction because of some technical reason. Consult log for more information.");
      } else {
        break;
      }
    }
    if (response == null) {
      return;
    }
    //TODO zapisz odpowiedz i coś z nią zrób
    TransactionIndividualStatus3Code responseStatus = response.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts();
    logger.info("SRPN Reject transaction id : "
            .concat(response.getFIToFIPmtStsRpt().getTxInfAndSts().getOrgnlTxId())
            .concat(", status : ")
            .concat(responseStatus.value()));
    switch (responseStatus) {
      case RJCT: {
        transaction.setStatus(SRPNInternalStatus.RJCT_RTR);
        break;
      }
      case ACSC: {
        transaction.setStatus(SRPNInternalStatus.ACSC_RTR);
        break;
      }
      default: {
      }
    }
    transactionHelper.updateTransactionStatusAndResponse(transaction, response);
  }

  private TransactionIndividualStatus3Code authorizeTransfer(Document sendTransferDocument, SRPNTransaction transaction) {
    logger.info("SRPN started to authorize transaction "
            .concat(transaction.getTransactionId())
            .concat(" at ")
            .concat(simpleDateFormat.format(Calendar.getInstance().getTime())));

    Document authorizeDocument = CreditTransferV02DocumentCreator.createAuthorizeTransferDocument(sendTransferDocument);
    transaction.setStatus(SRPNInternalStatus.AUTHORIZE);
    transactionHelper.addStatement(new SRPNStatement(JAXBMarshaller.marshallToBase64(authorizeDocument), transaction));
    String url = "http://localhost:8080/AgentService/AgentService".concat(transaction.getReceiverKNR()).concat("?wsdl");
    iso.std.iso._20022.tech.xsd.pacs_002_001.Document response = null;
    for (int i = 0; i < RETRY_COUNTER; i++) {
      response = iSRPNSender.authorizeTransfer(url, authorizeDocument);
      if (response == null) {
        i++;
        transactionHelper.updateTransaction(transaction);
        logger.warn("Cant authorize transaction because of some technical reason. Consult log for more information.");
      } else {
        break;
      }
    }
    if (response == null) {
      return null;
    }
    TransactionIndividualStatus3Code responseStatus = response.getFIToFIPmtStsRpt().getTxInfAndSts().getTxSts();
    logger.info("SRPN Transaction : "
            .concat(response.getFIToFIPmtStsRpt().getTxInfAndSts().getOrgnlTxId())
            .concat(" authorization status : ")
            .concat(responseStatus.value()));
    switch (responseStatus) {
      case RJCT: {
        transaction.setExternalRejectionCode(response.getFIToFIPmtStsRpt().getTxInfAndSts().getStsRsnInf().getRsn().getCd());
        transaction.setSrpnRejectionCode(response.getFIToFIPmtStsRpt().getTxInfAndSts().getStsRsnInf().getRsn().getPrtry());
        transaction.setStatus(SRPNInternalStatus.RJCT_AU);
        break;
      }
      case ACCP: {
        transaction.setStatus(SRPNInternalStatus.ACCP_AU);
        break;
      }
      default: {
      }
    }
    transactionHelper.updateTransactionStatusAndResponse(transaction, response);
    return responseStatus;
  }
}
