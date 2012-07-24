
package com.bea.wli.sb.context;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PipelinePathType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PipelinePathType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="request-pipeline"/>
 *     &lt;enumeration value="response-pipeline"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PipelinePathType")
@XmlEnum
public enum PipelinePathType {

    @XmlEnumValue("request-pipeline")
    REQUEST_PIPELINE("request-pipeline"),
    @XmlEnumValue("response-pipeline")
    RESPONSE_PIPELINE("response-pipeline");
    private final String value;

    PipelinePathType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PipelinePathType fromValue(String v) {
        for (PipelinePathType c: PipelinePathType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
