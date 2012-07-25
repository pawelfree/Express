package iso.std.iso._20022.tech.xsd.pacs_002_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.bsb.elixir.express.util.SRPNRejectionReasonCode;

/**
 * <p>Java class for StatusReasonInformation8 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="StatusReasonInformation8">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Rsn" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}StatusReason6Choice"/>
 *         &lt;element name="AddtlInf" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}Max105Text" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusReasonInformation8", propOrder = {
  "rsn",
  "addtlInf"
})
public class StatusReasonInformation8 {

  @XmlElement(name = "Rsn", required = true)
  protected StatusReason6Choice rsn;
  @XmlElement(name = "AddtlInf")
  protected String addtlInf;

  /**
   * Gets the value of the rsn property.
   *
   * @return possible object is {@link StatusReason6Choice }
   *
   */
  public StatusReason6Choice getRsn() {
    return rsn;
  }

  /**
   * Sets the value of the rsn property.
   *
   * @param value allowed object is {@link StatusReason6Choice }
   *
   */
  public void setRsn(StatusReason6Choice value) {
    this.rsn = value;
  }

  /**
   * Gets the value of the addtlInf property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getAddtlInf() {
    return addtlInf;
  }

  /**
   * Sets the value of the addtlInf property.
   *
   * @param value allowed object is {@link String }
   *
   */
  public void setAddtlInf(String value) {
    this.addtlInf = value;
  }

  public static StatusReasonInformation8 instance(
          String reasonCode,
          SRPNRejectionReasonCode srpnRejectionReasonCode,
          String addtlInf) {
    StatusReasonInformation8 statusReasonInformation8 = new StatusReasonInformation8();
    statusReasonInformation8.setAddtlInf(addtlInf);
    statusReasonInformation8.setRsn(StatusReason6Choice.instance(reasonCode, srpnRejectionReasonCode)); 
    return statusReasonInformation8; 
  }
}
