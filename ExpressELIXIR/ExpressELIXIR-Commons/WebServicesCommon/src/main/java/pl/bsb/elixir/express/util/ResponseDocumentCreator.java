package pl.bsb.elixir.express.util;

import iso.std.iso._20022.tech.xsd.pacs_002_001.BranchAndFinancialInstitutionIdentification4;
import iso.std.iso._20022.tech.xsd.pacs_002_001.Document;
import iso.std.iso._20022.tech.xsd.pacs_002_001.FIToFIPaymentStatusReportV03;
import iso.std.iso._20022.tech.xsd.pacs_002_001.GroupHeader37;
import iso.std.iso._20022.tech.xsd.pacs_002_001.OriginalGroupInformation20;
import iso.std.iso._20022.tech.xsd.pacs_002_001.OriginalTransactionReference13;
import iso.std.iso._20022.tech.xsd.pacs_002_001.PaymentTransactionInformation26;
import iso.std.iso._20022.tech.xsd.pacs_002_001.TransactionIndividualStatus3Code;
import iso.std.iso._20022.tech.xsd.pacs_004_001.PaymentReturnV02;
import iso.std.iso._20022.tech.xsd.pacs_008_001.FIToFICustomerCreditTransferV02;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 *
 * @author paweld
 */
public class ResponseDocumentCreator {

  private static final Logger logger = Logger.getLogger(ResponseDocumentCreator.class.getName());

  public static Document createAcknowledgeCreditDebitResponse(
          FIToFICustomerCreditTransferV02 fitficctv,
          TransactionIndividualStatus3Code txSts) {
    return createAcknowledgeCreditDebitResponse(null, fitficctv, txSts);
  }

  public static Document createAcknowledgeCreditDebitResponse(
          ExternalStatusReason1Code externalStatusReasonCode,
          FIToFICustomerCreditTransferV02 fitficctv,
          TransactionIndividualStatus3Code txSts) {
    String reasonCode = null;
    if (externalStatusReasonCode != null) {
      reasonCode = externalStatusReasonCode.value();
    }    
    return createResponseDocument(
            reasonCode,
            null,
            null,
            fitficctv.getGrpHdr().getInstdAgt().getFinInstnId().getOthr().getId(),
            "KIRSRPNX",
            fitficctv,
            txSts);
  }

  public static Document createRejectTransferResponse(
          PaymentReturnV02 paymentReturnV02,
          TransactionIndividualStatus3Code txSts) {
    return createRejectTransferResponse(null, paymentReturnV02, txSts);
  }

  public static Document createRejectTransferResponse(
          ExternalStatusReason1Code externalStatusReasonCode,
          PaymentReturnV02 paymentReturnV02,
          TransactionIndividualStatus3Code txSts) {
    String reasonCode = null;
    if (externalStatusReasonCode != null) {
      reasonCode = externalStatusReasonCode.value();
    }     
    return createResponseDocument(
            reasonCode,
            null,
            null,
            paymentReturnV02.getGrpHdr().getInstdAgt().getFinInstnId().getOthr().getId(),
            "KIRSRPNX",
            paymentReturnV02,
            txSts);
  }

  public static Document createAuthorizeTransferResponse(
          FIToFICustomerCreditTransferV02 fitficctv,
          TransactionIndividualStatus3Code txSts) {//status transakcji {"RJCT", "ACCP"}
    //TODO nadawca musi odtyczyć "TEGO" interfejsu - na razie biore z komunikatu oryginalnego
    return createAuthorizeTransferResponse(null, null, fitficctv, txSts);
  }

  public static Document createAuthorizeTransferResponse(
          ExternalReturnReason1Code externalReturnReasonCode,
          SRPNRejectionReasonCode srpnRejectionReasonCode,
          FIToFICustomerCreditTransferV02 fitficctv,
          TransactionIndividualStatus3Code txSts) {//status transakcji {"RJCT", "ACCP"}
    //TODO nadawca musi odtyczyć "TEGO" interfejsu - na razie biore z komunikatu oryginalnego
    String reasonCode = null;
    if (externalReturnReasonCode != null) {
      reasonCode = externalReturnReasonCode.value();
    }
    return createResponseDocument(
            reasonCode,
            srpnRejectionReasonCode,
            null,
            fitficctv.getGrpHdr().getInstdAgt().getFinInstnId().getOthr().getId(),
            "KIRSRPNX",
            fitficctv,
            txSts);
  }

  public static Document createSendTransferResponse(
          FIToFICustomerCreditTransferV02 fitficctv,
          TransactionIndividualStatus3Code txSts) {//status transakcji {"RJCT", "ACSP"}
    return createResponseDocument(
            null,
            null,
            null,
            "KIRSRPNX",
            fitficctv.getGrpHdr().getInstgAgt().getFinInstnId().getOthr().getId(),
            fitficctv,
            txSts);
  }

  private static Document createResponseDocument(
          String externalReasonCode,
          SRPNRejectionReasonCode srpnRejectionReasonCode,
          String additionalInfo,
          String nadawcaKNR,
          String odbiorcaKNR,
          PaymentReturnV02 paymentReturnV02,
          TransactionIndividualStatus3Code txSts) {
    return createResponseDocument(
            externalReasonCode,
            srpnRejectionReasonCode,
            additionalInfo,
            nadawcaKNR,
            odbiorcaKNR,
            paymentReturnV02.getGrpHdr().getMsgId(),
            "pacs.004.001.02",
            Instruction.rjctTrf.value(),
            paymentReturnV02.getTxInf().getOrgnlTxId(),
            txSts,
            OriginalTransactionReference13.buildFromCreditTransferReference(paymentReturnV02.getTxInf().getOrgnlTxRef()));
  }

  //TODO tylko jeden z kodów błedu może być różny od null
  private static Document createResponseDocument(
          String externalReasonCode,
          SRPNRejectionReasonCode srpnRejectionReasonCode,
          String additionalInfo,
          String nadawcaKNR,
          String odbiorcaKNR,
          FIToFICustomerCreditTransferV02 fitficctv,
          TransactionIndividualStatus3Code txSts) {
    return createResponseDocument(
            externalReasonCode,
            srpnRejectionReasonCode,
            additionalInfo,
            nadawcaKNR,
            odbiorcaKNR,
            fitficctv.getGrpHdr().getMsgId(),
            "pacs.008.001.02",
            fitficctv.getCdtTrfTxInf().getPmtId().getInstrId(),
            fitficctv.getCdtTrfTxInf().getPmtId().getTxId(),
            txSts,
            OriginalTransactionReference13.buildFromCreditTransferReference(fitficctv.getCdtTrfTxInf()));
  }

  private static Document createResponseDocument(
          String externalReasonCode,
          SRPNRejectionReasonCode srpnRejectionReasonCode,
          String additionalInfo,
          String nadawcaKNR,
          String odbiorcaKNR,
          String orgnMsgId, //identyfikator oryginalnego komunikatu
          String orgnMsgType, //typ oryginalnego komunikatu { "pacs.004.001.02", "pacs.008.001.02"}
          String orgnInstrId, //oryginalna instrukcja {sendTrf, authTrf, ackCrdt, ackDebt, rjctTrf}
          String orgnlTxId, //identyfikator tranaskcji
          TransactionIndividualStatus3Code txSts, //status transakcji {"RJCT", "ACCP", "ACSP", "ACSC"}
          OriginalTransactionReference13 orgnlTxRef) //referencja do oryginalnej transakcji
  {
    try {
      FIToFIPaymentStatusReportV03 fitfipsrv = new FIToFIPaymentStatusReportV03();
      //TODO unique transaction Id
      fitfipsrv.setGrpHdr(createGroupHeader37(odbiorcaKNR, nadawcaKNR, Long.toString(System.currentTimeMillis())));
      //identyfikacja oryginalnego komunikatu
      fitfipsrv.setOrgnlGrpInfAndSts(OriginalGroupInformation20.instance(orgnMsgId, orgnMsgType));
      PaymentTransactionInformation26 paymentTransactionInformation26;
      if (!txSts.equals(TransactionIndividualStatus3Code.RJCT)) {
        paymentTransactionInformation26 = PaymentTransactionInformation26.instance(
                orgnInstrId,
                orgnlTxId,
                txSts,
                orgnlTxRef);
      } else {
        paymentTransactionInformation26 = PaymentTransactionInformation26.instance(
                externalReasonCode,
                srpnRejectionReasonCode,
                additionalInfo,
                orgnInstrId,
                orgnlTxId,
                txSts,
                orgnlTxRef);
      }
      fitfipsrv.setTxInfAndSts(paymentTransactionInformation26);
      Document document = new Document();
      document.setFIToFIPmtStsRpt(fitfipsrv);
      return document;
    } catch (DatatypeConfigurationException ex) {
      logger.log(Level.SEVERE,
              "Cant create response document with message : {0}",
              ex.getMessage());
    }
    return null;

  }

  private static GroupHeader37 createGroupHeader37(String odbiorcaKNR, String nadawcaKNR, String msgId) throws DatatypeConfigurationException {
    GroupHeader37 groupHeader37 = new GroupHeader37();

    groupHeader37.setCreDtTm(CalendarHelper.getCalendar(System.currentTimeMillis()));
    //odbiorca
    groupHeader37.setInstdAgt(BranchAndFinancialInstitutionIdentification4.instance(odbiorcaKNR));
    //nadawca
    groupHeader37.setInstgAgt(BranchAndFinancialInstitutionIdentification4.instance(nadawcaKNR));
    groupHeader37.setMsgId(msgId);

    return groupHeader37;
  }
}
