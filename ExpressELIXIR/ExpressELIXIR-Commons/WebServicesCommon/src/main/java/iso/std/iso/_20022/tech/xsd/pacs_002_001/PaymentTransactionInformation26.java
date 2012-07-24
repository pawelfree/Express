package iso.std.iso._20022.tech.xsd.pacs_002_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.bsb.elixir.express.util.ExternalReturnReason1Code;
import pl.bsb.elixir.express.util.SRPNRejectionReasonCode;

/**
 * <p>Java class for PaymentTransactionInformation26 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PaymentTransactionInformation26">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrgnlInstrId">
 *           &lt;simpleType>
 *             &lt;restriction base="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}Max35Text">
 *               &lt;enumeration value="sendTrf"/>
 *               &lt;enumeration value="authTrf"/>
 *               &lt;enumeration value="ackCrdt"/>
 *               &lt;enumeration value="ackDebt"/>
 *               &lt;enumeration value="rjctTrf"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OrgnlTxId" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}Max23Text"/>
 *         &lt;element name="TxSts" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}TransactionIndividualStatus3Code"/>
 *         &lt;element name="StsRsnInf" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}StatusReasonInformation8" minOccurs="0"/>
 *         &lt;element name="OrgnlTxRef" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}OriginalTransactionReference13"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentTransactionInformation26", propOrder = {
  "orgnlInstrId",
  "orgnlTxId",
  "txSts",
  "stsRsnInf",
  "orgnlTxRef"
})
public class PaymentTransactionInformation26 {

  @XmlElement(name = "OrgnlInstrId", required = true)
  protected String orgnlInstrId;
  @XmlElement(name = "OrgnlTxId", required = true)
  protected String orgnlTxId;
  @XmlElement(name = "TxSts", required = true)
  protected TransactionIndividualStatus3Code txSts;
  @XmlElement(name = "StsRsnInf")
  protected StatusReasonInformation8 stsRsnInf;
  @XmlElement(name = "OrgnlTxRef", required = true)
  protected OriginalTransactionReference13 orgnlTxRef;

  /**
   * Gets the value of the orgnlInstrId property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getOrgnlInstrId() {
    return orgnlInstrId;
  }

  /**
   * Sets the value of the orgnlInstrId property.
   *
   * @param value allowed object is {@link String }
   *
   */
  public void setOrgnlInstrId(String value) {
    this.orgnlInstrId = value;
  }

  /**
   * Gets the value of the orgnlTxId property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getOrgnlTxId() {
    return orgnlTxId;
  }

  /**
   * Sets the value of the orgnlTxId property.
   *
   * @param value allowed object is {@link String }
   *
   */
  public void setOrgnlTxId(String value) {
    this.orgnlTxId = value;
  }

  /**
   * Gets the value of the txSts property.
   *
   * @return possible object is {@link TransactionIndividualStatus3Code }
   *
   */
  public TransactionIndividualStatus3Code getTxSts() {
    return txSts;
  }

  /**
   * Sets the value of the txSts property.
   *
   * @param value allowed object is {@link TransactionIndividualStatus3Code }
   *
   */
  public void setTxSts(TransactionIndividualStatus3Code value) {
    this.txSts = value;
  }

  /**
   * Gets the value of the stsRsnInf property.
   *
   * @return possible object is {@link StatusReasonInformation8 }
   *
   */
  public StatusReasonInformation8 getStsRsnInf() {
    return stsRsnInf;
  }

  /**
   * Sets the value of the stsRsnInf property.
   *
   * @param value allowed object is {@link StatusReasonInformation8 }
   *
   */
  public void setStsRsnInf(StatusReasonInformation8 value) {
    this.stsRsnInf = value;
  }

  /**
   * Gets the value of the orgnlTxRef property.
   *
   * @return possible object is {@link OriginalTransactionReference13 }
   *
   */
  public OriginalTransactionReference13 getOrgnlTxRef() {
    return orgnlTxRef;
  }

  /**
   * Sets the value of the orgnlTxRef property.
   *
   * @param value allowed object is {@link OriginalTransactionReference13 }
   *
   */
  public void setOrgnlTxRef(OriginalTransactionReference13 value) {
    this.orgnlTxRef = value;
  }

  public static PaymentTransactionInformation26 instance(
          String orgnInstrId,
          String orgnlTxId,
          TransactionIndividualStatus3Code txSts,
          OriginalTransactionReference13 orgnlTxRef) {
    PaymentTransactionInformation26 pti = new PaymentTransactionInformation26();

    pti.orgnlInstrId = orgnInstrId;
    pti.orgnlTxId = orgnlTxId;
    pti.txSts = txSts;
    pti.orgnlTxRef = orgnlTxRef;
    return pti;
  }

  public static PaymentTransactionInformation26 instance(
          String externalReasonCode,
          SRPNRejectionReasonCode srpnRejectionReasonCode,
          String additionalInfo,
          String orgnInstrId,
          String orgnlTxId,
          TransactionIndividualStatus3Code txSts,
          OriginalTransactionReference13 orgnlTxRef) {
    PaymentTransactionInformation26 pti = new PaymentTransactionInformation26();
    pti.orgnlInstrId = orgnInstrId;
    pti.orgnlTxId = orgnlTxId;
    pti.txSts = txSts;
    pti.orgnlTxRef = orgnlTxRef;
    pti.stsRsnInf = StatusReasonInformation8.instance(
            externalReasonCode,
            srpnRejectionReasonCode, 
            additionalInfo);
    return pti;
  }
}
