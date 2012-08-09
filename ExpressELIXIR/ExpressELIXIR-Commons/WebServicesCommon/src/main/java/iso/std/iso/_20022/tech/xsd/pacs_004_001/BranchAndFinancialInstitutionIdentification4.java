
package iso.std.iso._20022.tech.xsd.pacs_004_001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BranchAndFinancialInstitutionIdentification4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BranchAndFinancialInstitutionIdentification4">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FinInstnId" type="{urn:iso:std:iso:20022:tech:xsd:pacs.004.001.02}FinancialInstitutionIdentification7"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BranchAndFinancialInstitutionIdentification4", propOrder = {
    "finInstnId"
})
public class BranchAndFinancialInstitutionIdentification4 {

    @XmlElement(name = "FinInstnId", required = true)
    protected FinancialInstitutionIdentification7 finInstnId;

    /**
     * Gets the value of the finInstnId property.
     * 
     * @return
     *     possible object is
     *     {@link FinancialInstitutionIdentification7 }
     *     
     */
    public FinancialInstitutionIdentification7 getFinInstnId() {
        return finInstnId;
    }

    /**
     * Sets the value of the finInstnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancialInstitutionIdentification7 }
     *     
     */
    public void setFinInstnId(FinancialInstitutionIdentification7 value) {
        this.finInstnId = value;
    }
    public static BranchAndFinancialInstitutionIdentification4 instance(String value) {
        //TODO sprawdzać czy value ma 8 znaków
        BranchAndFinancialInstitutionIdentification4 bafii = new BranchAndFinancialInstitutionIdentification4();
        FinancialInstitutionIdentification7 fii = new FinancialInstitutionIdentification7();
        GenericFinancialIdentification1 gfi = new GenericFinancialIdentification1();
        gfi.setId(value);
        fii.setOthr(gfi);
        bafii.setFinInstnId(fii);
        return bafii;
    }
    
    @Override
    public BranchAndFinancialInstitutionIdentification4 clone() {
        return BranchAndFinancialInstitutionIdentification4.instance(this.getFinInstnId().getOthr().getId());
    }    
}
