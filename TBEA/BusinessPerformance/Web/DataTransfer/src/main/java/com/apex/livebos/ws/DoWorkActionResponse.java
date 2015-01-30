
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>doWorkActionResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="doWorkActionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WorkActionResult" type="{http://ws.livebos.apex.com/}workActionResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doWorkActionResponse", propOrder = {
    "workActionResult"
})
public class DoWorkActionResponse {

    @XmlElement(name = "WorkActionResult")
    protected WorkActionResult workActionResult;

    /**
     * ��ȡworkActionResult���Ե�ֵ��
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
     * ����workActionResult���Ե�ֵ��
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
