
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getWorkOwnerResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getWorkOwnerResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WorkOwnerResponse" type="{http://ws.livebos.apex.com/}workOwnerResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getWorkOwnerResponse", propOrder = {
    "workOwnerResponse"
})
public class GetWorkOwnerResponse {

    @XmlElement(name = "WorkOwnerResponse")
    protected WorkOwnerResponse workOwnerResponse;

    /**
     * ��ȡworkOwnerResponse���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link WorkOwnerResponse }
     *     
     */
    public WorkOwnerResponse getWorkOwnerResponse() {
        return workOwnerResponse;
    }

    /**
     * ����workOwnerResponse���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link WorkOwnerResponse }
     *     
     */
    public void setWorkOwnerResponse(WorkOwnerResponse value) {
        this.workOwnerResponse = value;
    }

}
