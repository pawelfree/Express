
package iso.std.iso._20022.tech.xsd.pacs_004_001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActiveCurrencyCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ActiveCurrencyCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;pattern value="[A-Z]{3,3}"/>
 *     &lt;enumeration value="PLN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ActiveCurrencyCode")
@XmlEnum
public enum ActiveCurrencyCode {

    PLN;

    public String value() {
        return name();
    }

    public static ActiveCurrencyCode fromValue(String v) {
        return valueOf(v);
    }

}
