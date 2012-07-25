//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.07.25 at 02:34:50 PM CEST 
//


package pl.com.kir.srpn.soap.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PeriodListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PeriodListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="PeriodDay" type="{http://soap.srpn.kir.com.pl/availability}PeriodDayType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PeriodWeek" type="{http://soap.srpn.kir.com.pl/availability}PeriodWeekType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PeriodMonth" type="{http://soap.srpn.kir.com.pl/availability}PeriodMonthType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="GivenDate" type="{http://soap.srpn.kir.com.pl/availability}GivenDateType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeriodListType", propOrder = {
    "periodDay",
    "periodWeek",
    "periodMonth",
    "givenDate"
})
public class PeriodListType {

    @XmlElement(name = "PeriodDay")
    protected List<PeriodDayType> periodDay;
    @XmlElement(name = "PeriodWeek")
    protected List<PeriodWeekType> periodWeek;
    @XmlElement(name = "PeriodMonth")
    protected List<PeriodMonthType> periodMonth;
    @XmlElement(name = "GivenDate")
    protected List<GivenDateType> givenDate;

    /**
     * Gets the value of the periodDay property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the periodDay property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeriodDay().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeriodDayType }
     * 
     * 
     */
    public List<PeriodDayType> getPeriodDay() {
        if (periodDay == null) {
            periodDay = new ArrayList<PeriodDayType>();
        }
        return this.periodDay;
    }

    /**
     * Gets the value of the periodWeek property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the periodWeek property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeriodWeek().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeriodWeekType }
     * 
     * 
     */
    public List<PeriodWeekType> getPeriodWeek() {
        if (periodWeek == null) {
            periodWeek = new ArrayList<PeriodWeekType>();
        }
        return this.periodWeek;
    }

    /**
     * Gets the value of the periodMonth property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the periodMonth property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeriodMonth().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeriodMonthType }
     * 
     * 
     */
    public List<PeriodMonthType> getPeriodMonth() {
        if (periodMonth == null) {
            periodMonth = new ArrayList<PeriodMonthType>();
        }
        return this.periodMonth;
    }

    /**
     * Gets the value of the givenDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the givenDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGivenDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GivenDateType }
     * 
     * 
     */
    public List<GivenDateType> getGivenDate() {
        if (givenDate == null) {
            givenDate = new ArrayList<GivenDateType>();
        }
        return this.givenDate;
    }

}
