package iso.std.iso._20022.tech.xsd.pacs_002_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.bsb.elixir.express.util.ExternalReturnReason1Code;
import pl.bsb.elixir.express.util.SRPNRejectionReasonCode;

/**
 * <p>Java class for StatusReason6Choice complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="StatusReason6Choice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Cd" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}ExternalStatusReason1Code"/>
 *         &lt;element name="Prtry">
 *           &lt;simpleType>
 *             &lt;restriction base="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}Max35Text">
 *               &lt;enumeration value="CANM"/>
 *               &lt;enumeration value="DANM"/>
 *               &lt;enumeration value="CADU"/>
 *               &lt;enumeration value="CASU"/>
 *               &lt;enumeration value="DATU"/>
 *               &lt;enumeration value="DTNR"/>
 *               &lt;enumeration value="DTCP"/>
 *               &lt;enumeration value="DTRJ"/>
 *               &lt;enumeration value="DTIP"/>
 *               &lt;enumeration value="DTCL"/>
 *               &lt;enumeration value="DTRF"/>
 *               &lt;enumeration value="IVTX"/>
 *               &lt;enumeration value="MVER"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusReason6Choice", propOrder = {
  "cd",
  "prtry"
})
public class StatusReason6Choice {

  @XmlElement(name = "Cd")
  protected String cd;
  @XmlElement(name = "Prtry")
  protected String prtry;

  /**
   * Gets the value of the cd property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getCd() {
    return cd;
  }

  /**
   * Sets the value of the cd property.
   *
   * @param value allowed object is {@link String }
   *
   */
  public void setCd(String value) {
    this.cd = value;
  }

  /**
   * Gets the value of the prtry property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getPrtry() {
    return prtry;
  }

  /**
   * Sets the value of the prtry property.
   *
   * @param value allowed object is {@link String }
   *
   */
  public void setPrtry(String value) {
    this.prtry = value;
  }

  public static StatusReason6Choice instance(
          String reasonCode,
          SRPNRejectionReasonCode srpnRejectionReasonCode) {
    StatusReason6Choice statusReason6Choice = new StatusReason6Choice();
    if ((reasonCode == null) && (srpnRejectionReasonCode != null)) {
      statusReason6Choice.setPrtry(srpnRejectionReasonCode.toString());
    }
    else {
      if (reasonCode != null) {
        statusReason6Choice.setCd(reasonCode);
      }
    }
    return statusReason6Choice;
  }
}
