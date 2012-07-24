
package iso.std.iso._20022.tech.xsd.pacs_002_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FIToFIPaymentStatusReportV03 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FIToFIPaymentStatusReportV03">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}GroupHeader37"/>
 *         &lt;element name="OrgnlGrpInfAndSts" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}OriginalGroupInformation20"/>
 *         &lt;element name="TxInfAndSts" type="{urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03}PaymentTransactionInformation26"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FIToFIPaymentStatusReportV03", propOrder = {
    "grpHdr",
    "orgnlGrpInfAndSts",
    "txInfAndSts"
})
public class FIToFIPaymentStatusReportV03 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader37 grpHdr;
    @XmlElement(name = "OrgnlGrpInfAndSts", required = true)
    protected OriginalGroupInformation20 orgnlGrpInfAndSts;
    @XmlElement(name = "TxInfAndSts", required = true)
    protected PaymentTransactionInformation26 txInfAndSts;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeader37 }
     *     
     */
    public GroupHeader37 getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeader37 }
     *     
     */
    public void setGrpHdr(GroupHeader37 value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the orgnlGrpInfAndSts property.
     * 
     * @return
     *     possible object is
     *     {@link OriginalGroupInformation20 }
     *     
     */
    public OriginalGroupInformation20 getOrgnlGrpInfAndSts() {
        return orgnlGrpInfAndSts;
    }

    /**
     * Sets the value of the orgnlGrpInfAndSts property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalGroupInformation20 }
     *     
     */
    public void setOrgnlGrpInfAndSts(OriginalGroupInformation20 value) {
        this.orgnlGrpInfAndSts = value;
    }

    /**
     * Gets the value of the txInfAndSts property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTransactionInformation26 }
     *     
     */
    public PaymentTransactionInformation26 getTxInfAndSts() {
        return txInfAndSts;
    }

    /**
     * Sets the value of the txInfAndSts property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTransactionInformation26 }
     *     
     */
    public void setTxInfAndSts(PaymentTransactionInformation26 value) {
        this.txInfAndSts = value;
    }

}
