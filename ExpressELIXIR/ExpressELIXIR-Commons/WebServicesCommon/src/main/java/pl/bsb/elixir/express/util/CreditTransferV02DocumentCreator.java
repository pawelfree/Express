/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.bsb.elixir.express.util;

import iso.std.iso._20022.tech.xsd.pacs_004_001.GroupHeader38;
import iso.std.iso._20022.tech.xsd.pacs_004_001.PaymentReturnV02;
import iso.std.iso._20022.tech.xsd.pacs_004_001.PaymentTransactionInformation27;
import iso.std.iso._20022.tech.xsd.pacs_004_001.ReturnReason5Choice;
import iso.std.iso._20022.tech.xsd.pacs_004_001.ReturnReasonInformation9;
import iso.std.iso._20022.tech.xsd.pacs_008_001.ActiveCurrencyAndAmount;
import iso.std.iso._20022.tech.xsd.pacs_008_001.ActiveOrHistoricCurrencyAndAmount;
import iso.std.iso._20022.tech.xsd.pacs_008_001.BranchAndFinancialInstitutionIdentification4;
import iso.std.iso._20022.tech.xsd.pacs_008_001.CashAccount16;
import iso.std.iso._20022.tech.xsd.pacs_008_001.ChargeBearerType1Code;
import iso.std.iso._20022.tech.xsd.pacs_008_001.CreditTransferTransactionInformation11;
import iso.std.iso._20022.tech.xsd.pacs_008_001.Document;
import iso.std.iso._20022.tech.xsd.pacs_008_001.FIToFICustomerCreditTransferV02;
import iso.std.iso._20022.tech.xsd.pacs_008_001.GroupHeader33;
import iso.std.iso._20022.tech.xsd.pacs_008_001.PartyIdentification32;
import iso.std.iso._20022.tech.xsd.pacs_008_001.PaymentIdentification3;
import iso.std.iso._20022.tech.xsd.pacs_008_001.PaymentTypeInformation21;
import iso.std.iso._20022.tech.xsd.pacs_008_001.SettlementInformation13;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 *
 * @author paweld
 */
public class CreditTransferV02DocumentCreator {

  private static final Logger logger = Logger.getLogger(CreditTransferV02DocumentCreator.class.getName());

  //TODO rejectTransfer do innego kreatora przeniesc
  
  //TODO tylko CADU TAPF jako String srpnRejectionReasonCode
  public static iso.std.iso._20022.tech.xsd.pacs_004_001.Document createRejectTransferDocument(
          String externalReturnReasonCode,
          String srpnRejectionReasonCode,
          Document authorizeTransferDocument) {
    try {
      PaymentReturnV02 paymentReturnV02 = new PaymentReturnV02();
      CreditTransferTransactionInformation11 transactionInfo = authorizeTransferDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf();
      paymentReturnV02.setGrpHdr(createGroupHeader38("KIRSPRNX",
              transactionInfo.getCdtrAgt().getFinInstnId().getOthr().getId(),
              transactionInfo.getPmtId().getTxId().concat(Instruction.rjctTrf.toString())));

      paymentReturnV02.setTxInf(createPaymentTransactionInformation27(
              externalReturnReasonCode, 
              srpnRejectionReasonCode, 
              authorizeTransferDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf()));

      iso.std.iso._20022.tech.xsd.pacs_004_001.Document document = new iso.std.iso._20022.tech.xsd.pacs_004_001.Document();
      document.setPmtRtr(paymentReturnV02);
      return document;
    } catch (DatatypeConfigurationException ex) {
      logger.log(Level.SEVERE,
              "Cant create rejectTransfer document with message : {0}",
              ex.getMessage());
    }
    return null;
  }

    public static Document createAcknowledgeDebitDocument(Document sendTransferDocument) {
    try {
      return createDocumentFromSendTransferDocument(sendTransferDocument,Instruction.ackDebt);
    } catch (DatatypeConfigurationException ex) {
      logger.log(Level.SEVERE,
              "Cant create acknowledgeDebit document with message : {0}",
              ex.getMessage());
    }
    return null;    
  }
  
  public static Document createAcknowledgeCreditDocument(Document sendTransferDocument) {
    try {
      return createDocumentFromSendTransferDocument(sendTransferDocument,Instruction.ackCrdt);
    } catch (DatatypeConfigurationException ex) {
      logger.log(Level.SEVERE,
              "Cant create acknowledgeCredit document with message : {0}",
              ex.getMessage());
    }
    return null;    
  }
 
  public static Document createAuthorizeTransferDocument(Document sendTransferDocument) {
    try {
      return createDocumentFromSendTransferDocument(sendTransferDocument,Instruction.authTrf);
    } catch (DatatypeConfigurationException ex) {
      logger.log(Level.SEVERE,
              "Cant create authorizeTransfer document with message : {0}",
              ex.getMessage());
    }
    return null;
  }

  private static Document createDocumentFromSendTransferDocument(Document sendTransferDocument, Instruction instruction) throws DatatypeConfigurationException {
      FIToFICustomerCreditTransferV02 fitficctv = new FIToFICustomerCreditTransferV02();
      CreditTransferTransactionInformation11 transactionInfo = sendTransferDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf();
      //TODO knr odbiorcy trzeba brac z ustawien
      fitficctv.setGrpHdr(createGroupHeader33("KIRSRPNX",
              transactionInfo.getCdtrAgt().getFinInstnId().getOthr().getId(),
//              transactionInfo.getPmtId().getTxId().concat(instruction.toString())));
                Long.toString(System.currentTimeMillis()).concat(instruction.toString())));
      fitficctv.setCdtTrfTxInf(
              createCreditTransferTransactionInformation11(
              instruction, sendTransferDocument.getFIToFICstmrCdtTrf().getCdtTrfTxInf()));
      Document document = new Document();
      document.setFIToFICstmrCdtTrf(fitficctv);
      return document;    
  }
  
  public static Document createSendTransferDocument(
          String mainKNR,
          String transactionId,
          BigDecimal amount,
          String senderIban,
          String senderKNR,
          String receiverKNR,
          String receiverIban) {
    try {
      FIToFICustomerCreditTransferV02 fitficctv = new FIToFICustomerCreditTransferV02();
      fitficctv.setGrpHdr(createGroupHeader33(mainKNR, "KIRSRPNX", transactionId.concat(Instruction.sendTrf.toString())));
      fitficctv.setCdtTrfTxInf(
              createCreditTransferTransactionInformation11(
              Instruction.sendTrf,
              transactionId,
              amount,
              senderIban,
              senderKNR,
              receiverKNR,
              receiverIban));
      Document document = new Document();
      document.setFIToFICstmrCdtTrf(fitficctv);
      return document;
    } catch (DatatypeConfigurationException ex) {
      logger.log(Level.SEVERE,
              "Cant create sendTransfer document with message : {0}",
              ex.getMessage());
    }
    return null;
  }

  // <editor-fold defaultstate="collapsed" desc="helper methods"> 
  private static PaymentTransactionInformation27 createPaymentTransactionInformation27(String externalReturnReasonCode, String srpnRejectionReasonCode, CreditTransferTransactionInformation11 oryginalStatement) {
    PaymentTransactionInformation27 paymentTransactionInformation27 = new PaymentTransactionInformation27();
    paymentTransactionInformation27.setOrgnlTxId(oryginalStatement.getPmtId().getTxId());
    paymentTransactionInformation27.setOrgnlTxRef(iso.std.iso._20022.tech.xsd.pacs_004_001.OriginalTransactionReference13.buildFromCreditTransferReference(oryginalStatement));
    ReturnReasonInformation9 reasonInformation9 = new ReturnReasonInformation9();
    ReturnReason5Choice returnReason5Choice = new ReturnReason5Choice();
    returnReason5Choice.setCd(externalReturnReasonCode);
    returnReason5Choice.setPrtry(srpnRejectionReasonCode);
    reasonInformation9.setRsn(returnReason5Choice);
    reasonInformation9.setAddtlInf("Dodatkowa informacja o błędzie");
    paymentTransactionInformation27.setRtrRsnInf(reasonInformation9);
    paymentTransactionInformation27.setRtrdIntrBkSttlmAmt(iso.std.iso._20022.tech.xsd.pacs_004_001.ActiveCurrencyAndAmount.instance(oryginalStatement.getIntrBkSttlmAmt().getValue()));
    return paymentTransactionInformation27;
  }

  private static CreditTransferTransactionInformation11 createCreditTransferTransactionInformation11(
          Instruction instruction,
          CreditTransferTransactionInformation11 oryginalStatement) {
    //TODO przerobic po prostu na clone
    //CreditTransferTransactionInformation11 creditTransferTransactionInformation11 = oryginalStatement.clone();

    CreditTransferTransactionInformation11 creditTransferTransactionInformation11 = new CreditTransferTransactionInformation11();
    creditTransferTransactionInformation11.setPmtId(createPaymentIdentification3(instruction.toString(), oryginalStatement.getPmtId().getTxId()));
    creditTransferTransactionInformation11.setPmtTpInf(PaymentTypeInformation21.instance());
    //kwota do rozliczenia
    creditTransferTransactionInformation11.setIntrBkSttlmAmt(ActiveCurrencyAndAmount.instance(oryginalStatement.getIntrBkSttlmAmt().getValue()));
    //kwota transakcji
    creditTransferTransactionInformation11.setInstdAmt(ActiveOrHistoricCurrencyAndAmount.instance(oryginalStatement.getInstdAmt().getValue()));
    //stałe - strona ponosząca koszty
    creditTransferTransactionInformation11.setChrgBr(ChargeBearerType1Code.SLEV);
    //TODO dane płatnika
    creditTransferTransactionInformation11.setDbtr(PartyIdentification32.instance("Płatnik"));
    //numer rachunku płatnika
    creditTransferTransactionInformation11.setDbtrAcct(oryginalStatement.getDbtrAcct().clone());
    //numer rozliczeniowy jednostki płatnika
    creditTransferTransactionInformation11.setDbtrAgt(oryginalStatement.getDbtrAgt().clone());
    //numer rozliczeniowy jednostki beneficjenta
    creditTransferTransactionInformation11.setCdtrAgt(oryginalStatement.getCdtrAgt().clone());
    //numer rachunku beneficjenta
    creditTransferTransactionInformation11.setCdtrAcct(oryginalStatement.getCdtrAcct().clone());
    //TODO dane beneficjenta
    creditTransferTransactionInformation11.setCdtr(PartyIdentification32.instance("Beneficjent"));
    return creditTransferTransactionInformation11;
  }

  private static CreditTransferTransactionInformation11 createCreditTransferTransactionInformation11(
          Instruction instruction,
          String transactionId,
          BigDecimal amount,
          String senderIban,
          String senderKNR,
          String receiverKNR,
          String receiverIban) {

    CreditTransferTransactionInformation11 creditTransferTransactionInformation11 = new CreditTransferTransactionInformation11();

    creditTransferTransactionInformation11.setPmtId(createPaymentIdentification3(instruction.toString(), transactionId));
    creditTransferTransactionInformation11.setPmtTpInf(PaymentTypeInformation21.instance());
    //kwota do rozliczenia
    creditTransferTransactionInformation11.setIntrBkSttlmAmt(ActiveCurrencyAndAmount.instance(amount));
    //kwota transakcji
    creditTransferTransactionInformation11.setInstdAmt(ActiveOrHistoricCurrencyAndAmount.instance(amount));
    creditTransferTransactionInformation11.setChrgBr(ChargeBearerType1Code.SLEV);
    //TODO dane płatnika
    creditTransferTransactionInformation11.setDbtr(PartyIdentification32.instance("Płatnik"));
    creditTransferTransactionInformation11.setDbtrAcct(CashAccount16.instance(senderIban));
    creditTransferTransactionInformation11.setDbtrAgt(BranchAndFinancialInstitutionIdentification4.instance(senderKNR));
    creditTransferTransactionInformation11.setCdtrAgt(BranchAndFinancialInstitutionIdentification4.instance(receiverKNR));
    creditTransferTransactionInformation11.setCdtrAcct(CashAccount16.instance(receiverIban));
    //TODO dane beneficjenta
    creditTransferTransactionInformation11.setCdtr(PartyIdentification32.instance("Beneficjent"));
    return creditTransferTransactionInformation11;
  }

  private static PaymentIdentification3 createPaymentIdentification3(String instruction, String trasactionId) {
    //TODO sprawdzac długość transactionID - 1 do 23
    PaymentIdentification3 paymentIdentification3 = new PaymentIdentification3();

    paymentIdentification3.setInstrId(instruction);
    paymentIdentification3.setEndToEndId(trasactionId);
    paymentIdentification3.setTxId(trasactionId);

    return paymentIdentification3;
  }

  private static GroupHeader38 createGroupHeader38(String nadawcaKNR, String odbiorcaKNR, String messageId) throws DatatypeConfigurationException {
    GroupHeader38 groupHeader38 = new GroupHeader38();
    groupHeader38.setMsgId(messageId);
    groupHeader38.setCreDtTm(CalendarHelper.getCalendar(System.currentTimeMillis()));
    groupHeader38.setNbOfTxs("1");
    //niezmienne
    groupHeader38.setSttlmInf(iso.std.iso._20022.tech.xsd.pacs_004_001.SettlementInformation13.instance());
    groupHeader38.setInstgAgt(iso.std.iso._20022.tech.xsd.pacs_004_001.BranchAndFinancialInstitutionIdentification4.instance(nadawcaKNR));
    //zawsze KIRSRPNX w komunikatach wysyłanych do KIR
    groupHeader38.setInstdAgt(iso.std.iso._20022.tech.xsd.pacs_004_001.BranchAndFinancialInstitutionIdentification4.instance(odbiorcaKNR));
    return groupHeader38;
  }

  private static GroupHeader33 createGroupHeader33(String nadawcaKNR, String odbiorcaKNR, String messageId) throws DatatypeConfigurationException {
    GroupHeader33 groupHeader33 = new GroupHeader33();
    groupHeader33.setMsgId(messageId);
    groupHeader33.setCreDtTm(CalendarHelper.getCalendar(System.currentTimeMillis()));
    groupHeader33.setNbOfTxs("1");
    //niezmienne
    groupHeader33.setSttlmInf(SettlementInformation13.instance());
    groupHeader33.setInstgAgt(BranchAndFinancialInstitutionIdentification4.instance(nadawcaKNR));
    //zawsze KIRSRPNX w komunikatach wysyłanych do KIR
    groupHeader33.setInstdAgt(BranchAndFinancialInstitutionIdentification4.instance(odbiorcaKNR));
    return groupHeader33;
  }
  // </editor-fold>
}
