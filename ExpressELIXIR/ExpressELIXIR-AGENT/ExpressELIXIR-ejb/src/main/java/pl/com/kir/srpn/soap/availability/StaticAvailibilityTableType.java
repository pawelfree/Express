//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.07.25 at 02:34:50 PM CEST 
//


package pl.com.kir.srpn.soap.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StaticAvailibilityTableType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StaticAvailibilityTableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PeriodList" type="{http://soap.srpn.kir.com.pl/availability}PeriodListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StaticAvailibilityTableType", propOrder = {
    "periodList"
})
public class StaticAvailibilityTableType {

    @XmlElement(name = "PeriodList", required = true)
    protected PeriodListType periodList;

    /**
     * Gets the value of the periodList property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodListType }
     *     
     */
    public PeriodListType getPeriodList() {
        return periodList;
    }

    /**
     * Sets the value of the periodList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodListType }
     *     
     */
    public void setPeriodList(PeriodListType value) {
        this.periodList = value;
    }

}