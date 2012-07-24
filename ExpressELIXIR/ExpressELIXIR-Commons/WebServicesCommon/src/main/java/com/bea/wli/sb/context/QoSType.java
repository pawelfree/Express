
package com.bea.wli.sb.context;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QoSType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="QoSType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="best-effort"/>
 *     &lt;enumeration value="exactly-once"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QoSType")
@XmlEnum
public enum QoSType {

    @XmlEnumValue("best-effort")
    BEST_EFFORT("best-effort"),
    @XmlEnumValue("exactly-once")
    EXACTLY_ONCE("exactly-once");
    private final String value;

    QoSType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QoSType fromValue(String v) {
        for (QoSType c: QoSType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
