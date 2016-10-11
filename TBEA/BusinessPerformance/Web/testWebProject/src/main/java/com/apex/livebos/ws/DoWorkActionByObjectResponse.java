
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>doWorkActionByObjectResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="doWorkActionByObjectResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WorkActionResult" type="{http://ws.livebos.apex.com/}workActionResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doWorkActionByObjectResponse", propOrder = {
    "workActionResult"
})
public class DoWorkActionByObjectResponse {

    @XmlElement(name = "WorkActionResult")
    protected WorkActionResult workActionResult;

    /**
     * 获取workActionResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WorkActionResult }
     *     
     */
    public WorkActionResult getWorkActionResult() {
        return workActionResult;
    }

    /**
     * 设置workActionResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WorkActionResult }
     *     
     */
    public void setWorkActionResult(WorkActionResult value) {
        this.workActionResult = value;
    }

}
