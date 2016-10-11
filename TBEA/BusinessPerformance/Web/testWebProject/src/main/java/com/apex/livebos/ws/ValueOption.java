
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>valueOption的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="valueOption"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="VALUE"/&gt;
 *     &lt;enumeration value="DISPLAY"/&gt;
 *     &lt;enumeration value="BOTH"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "valueOption")
@XmlEnum
public enum ValueOption {

    VALUE,
    DISPLAY,
    BOTH;

    public String value() {
        return name();
    }

    public static ValueOption fromValue(String v) {
        return valueOf(v);
    }

}
