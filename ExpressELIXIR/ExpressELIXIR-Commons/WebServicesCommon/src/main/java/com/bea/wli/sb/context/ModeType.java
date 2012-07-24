
package com.bea.wli.sb.context;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ModeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="request"/>
 *     &lt;enumeration value="request-response"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ModeType")
@XmlEnum
public enum ModeType {

    @XmlEnumValue("request")
    REQUEST("request"),
    @XmlEnumValue("request-response")
    REQUEST_RESPONSE("request-response");
    private final String value;

    ModeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ModeType fromValue(String v) {
        for (ModeType c: ModeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
