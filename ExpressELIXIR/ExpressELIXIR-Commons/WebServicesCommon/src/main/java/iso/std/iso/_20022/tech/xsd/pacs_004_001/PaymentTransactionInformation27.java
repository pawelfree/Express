
package iso.std.iso._20022.tech.xsd.pacs_004_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentTransactionInformation27 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentTransactionInformation27">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrgnlTxId" type="{urn:iso:std:iso:20022:tech:xsd:pacs.004.001.02}Max23Text"/>
 *         &lt;element name="RtrdIntrBkSttlmAmt" type="{urn:iso:std:iso:20022:tech:xsd:pacs.004.001.02}ActiveCurrencyAndAmount"/>
 *         &lt;element name="RtrRsnInf" type="{urn:iso:std:iso:20022:tech:xsd:pacs.004.001.02}ReturnReasonInformation9"/>
 *         &lt;element name="OrgnlTxRef" type="{urn:iso:std:iso:20022:tech:xsd:pacs.004.001.02}OriginalTransactionReference13"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentTransactionInformation27", propOrder = {
    "orgnlTxId",
    "rtrdIntrBkSttlmAmt",
    "rtrRsnInf",
    "orgnlTxRef"
})
public class PaymentTransactionInformation27 {

    @XmlElement(name = "OrgnlTxId", required = true)
    protected String orgnlTxId;
    @XmlElement(name = "RtrdIntrBkSttlmAmt", required = true)
    protected ActiveCurrencyAndAmount rtrdIntrBkSttlmAmt;
    @XmlElement(name = "RtrRsnInf", required = true)
    protected ReturnReasonInformation9 rtrRsnInf;
    @XmlElement(name = "OrgnlTxRef", required = true)
    protected OriginalTransactionReference13 orgnlTxRef;

    /**
     * Gets the value of the orgnlTxId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgnlTxId() {
        return orgnlTxId;
    }

    /**
     * Sets the value of the orgnlTxId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgnlTxId(String value) {
        this.orgnlTxId = value;
    }

    /**
     * Gets the value of the rtrdIntrBkSttlmAmt property.
     * 
     * @return
     *     possible object is
     *     {@link ActiveCurrencyAndAmount }
     *     
     */
    public ActiveCurrencyAndAmount getRtrdIntrBkSttlmAmt() {
        return rtrdIntrBkSttlmAmt;
    }

    /**
     * Sets the value of the rtrdIntrBkSttlmAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActiveCurrencyAndAmount }
     *     
     */
    public void setRtrdIntrBkSttlmAmt(ActiveCurrencyAndAmount value) {
        this.rtrdIntrBkSttlmAmt = value;
    }

    /**
     * Gets the value of the rtrRsnInf property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnReasonInformation9 }
     *     
     */
    public ReturnReasonInformation9 getRtrRsnInf() {
        return rtrRsnInf;
    }

    /**
     * Sets the value of the rtrRsnInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnReasonInformation9 }
     *     
     */
    public void setRtrRsnInf(ReturnReasonInformation9 value) {
        this.rtrRsnInf = value;
    }

    /**
     * Gets the value of the orgnlTxRef property.
     * 
     * @return
     *     possible object is
     *     {@link OriginalTransactionReference13 }
     *     
     */
    public OriginalTransactionReference13 getOrgnlTxRef() {
        return orgnlTxRef;
    }

    /**
     * Sets the value of the orgnlTxRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalTransactionReference13 }
     *     
     */
    public void setOrgnlTxRef(OriginalTransactionReference13 value) {
        this.orgnlTxRef = value;
    }

}
