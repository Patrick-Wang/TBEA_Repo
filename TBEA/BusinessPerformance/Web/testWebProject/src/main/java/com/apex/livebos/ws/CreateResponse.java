
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>createResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="createResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CreateResult" type="{http://ws.livebos.apex.com/}createResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createResponse", propOrder = {
    "createResult"
})
public class CreateResponse {

    @XmlElement(name = "CreateResult")
    protected CreateResult createResult;

    /**
     * 获取createResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CreateResult }
     *     
     */
    public CreateResult getCreateResult() {
        return createResult;
    }

    /**
     * 设置createResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CreateResult }
     *     
     */
    public void setCreateResult(CreateResult value) {
        this.createResult = value;
    }

}
