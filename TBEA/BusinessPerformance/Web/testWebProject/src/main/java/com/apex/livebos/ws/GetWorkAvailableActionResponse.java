
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getWorkAvailableActionResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getWorkAvailableActionResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AvailableWorkActionResponse" type="{http://ws.livebos.apex.com/}availableWorkActionResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getWorkAvailableActionResponse", propOrder = {
    "availableWorkActionResponse"
})
public class GetWorkAvailableActionResponse {

    @XmlElement(name = "AvailableWorkActionResponse")
    protected AvailableWorkActionResponse availableWorkActionResponse;

    /**
     * 获取availableWorkActionResponse属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AvailableWorkActionResponse }
     *     
     */
    public AvailableWorkActionResponse getAvailableWorkActionResponse() {
        return availableWorkActionResponse;
    }

    /**
     * 设置availableWorkActionResponse属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AvailableWorkActionResponse }
     *     
     */
    public void setAvailableWorkActionResponse(AvailableWorkActionResponse value) {
        this.availableWorkActionResponse = value;
    }

}
