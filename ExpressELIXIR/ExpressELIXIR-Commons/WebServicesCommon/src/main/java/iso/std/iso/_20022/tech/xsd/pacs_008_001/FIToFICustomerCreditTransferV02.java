
package iso.std.iso._20022.tech.xsd.pacs_008_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FIToFICustomerCreditTransferV02 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FIToFICustomerCreditTransferV02">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}GroupHeader33"/>
 *         &lt;element name="CdtTrfTxInf" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02}CreditTransferTransactionInformation11"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FIToFICustomerCreditTransferV02", propOrder = {
    "grpHdr",
    "cdtTrfTxInf"
})
public class FIToFICustomerCreditTransferV02 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader33 grpHdr;
    @XmlElement(name = "CdtTrfTxInf", required = true)
    protected CreditTransferTransactionInformation11 cdtTrfTxInf;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeader33 }
     *     
     */
    public GroupHeader33 getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeader33 }
     *     
     */
    public void setGrpHdr(GroupHeader33 value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the cdtTrfTxInf property.
     * 
     * @return
     *     possible object is
     *     {@link CreditTransferTransactionInformation11 }
     *     
     */
    public CreditTransferTransactionInformation11 getCdtTrfTxInf() {
        return cdtTrfTxInf;
    }

    /**
     * Sets the value of the cdtTrfTxInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditTransferTransactionInformation11 }
     *     
     */
    public void setCdtTrfTxInf(CreditTransferTransactionInformation11 value) {
        this.cdtTrfTxInf = value;
    }

}
