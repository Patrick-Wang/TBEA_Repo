
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>createResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="createResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreateResult" type="{http://ws.livebos.apex.com/}createResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * ��ȡcreateResult���Ե�ֵ��
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
     * ����createResult���Ե�ֵ��
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
