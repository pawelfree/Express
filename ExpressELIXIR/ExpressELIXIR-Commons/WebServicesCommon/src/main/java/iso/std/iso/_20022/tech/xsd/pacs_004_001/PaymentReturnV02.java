
package iso.std.iso._20022.tech.xsd.pacs_004_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentReturnV02 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentReturnV02">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GrpHdr" type="{urn:iso:std:iso:20022:tech:xsd:pacs.004.001.02}GroupHeader38"/>
 *         &lt;element name="TxInf" type="{urn:iso:std:iso:20022:tech:xsd:pacs.004.001.02}PaymentTransactionInformation27"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentReturnV02", propOrder = {
    "grpHdr",
    "txInf"
})
public class PaymentReturnV02 {

    @XmlElement(name = "GrpHdr", required = true)
    protected GroupHeader38 grpHdr;
    @XmlElement(name = "TxInf", required = true)
    protected PaymentTransactionInformation27 txInf;

    /**
     * Gets the value of the grpHdr property.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeader38 }
     *     
     */
    public GroupHeader38 getGrpHdr() {
        return grpHdr;
    }

    /**
     * Sets the value of the grpHdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeader38 }
     *     
     */
    public void setGrpHdr(GroupHeader38 value) {
        this.grpHdr = value;
    }

    /**
     * Gets the value of the txInf property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTransactionInformation27 }
     *     
     */
    public PaymentTransactionInformation27 getTxInf() {
        return txInf;
    }

    /**
     * Sets the value of the txInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTransactionInformation27 }
     *     
     */
    public void setTxInf(PaymentTransactionInformation27 value) {
        this.txInf = value;
    }

}
