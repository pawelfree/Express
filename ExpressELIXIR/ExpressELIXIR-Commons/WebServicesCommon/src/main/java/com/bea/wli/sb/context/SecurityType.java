
package com.bea.wli.sb.context;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SecurityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SecurityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="transportClient" type="{http://www.bea.com/wli/sb/context}SubjectType" minOccurs="0"/>
 *         &lt;element name="messageLevelClient" type="{http://www.bea.com/wli/sb/context}SubjectType" minOccurs="0"/>
 *         &lt;element name="doOutboundWss" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecurityType", propOrder = {

})
public class SecurityType {

    protected SubjectType transportClient;
    protected SubjectType messageLevelClient;
    protected Boolean doOutboundWss;

    /**
     * Gets the value of the transportClient property.
     * 
     * @return
     *     possible object is
     *     {@link SubjectType }
     *     
     */
    public SubjectType getTransportClient() {
        return transportClient;
    }

    /**
     * Sets the value of the transportClient property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubjectType }
     *     
     */
    public void setTransportClient(SubjectType value) {
        this.transportClient = value;
    }

    /**
     * Gets the value of the messageLevelClient property.
     * 
     * @return
     *     possible object is
     *     {@link SubjectType }
     *     
     */
    public SubjectType getMessageLevelClient() {
        return messageLevelClient;
    }

    /**
     * Sets the value of the messageLevelClient property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubjectType }
     *     
     */
    public void setMessageLevelClient(SubjectType value) {
        this.messageLevelClient = value;
    }

    /**
     * Gets the value of the doOutboundWss property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDoOutboundWss() {
        return doOutboundWss;
    }

    /**
     * Sets the value of the doOutboundWss property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDoOutboundWss(Boolean value) {
        this.doOutboundWss = value;
    }

}
