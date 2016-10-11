
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>updateResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="updateResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LBEResult" type="{http://ws.livebos.apex.com/}lbeResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateResponse", propOrder = {
    "lbeResult"
})
public class UpdateResponse {

    @XmlElement(name = "LBEResult")
    protected LbeResult lbeResult;

    /**
     * 获取lbeResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LbeResult }
     *     
     */
    public LbeResult getLBEResult() {
        return lbeResult;
    }

    /**
     * 设置lbeResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LbeResult }
     *     
     */
    public void setLBEResult(LbeResult value) {
        this.lbeResult = value;
    }

}
