
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getWorkAvailableActionResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getWorkAvailableActionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AvailableWorkActionResponse" type="{http://ws.livebos.apex.com/}availableWorkActionResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * ��ȡavailableWorkActionResponse���Ե�ֵ��
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
     * ����availableWorkActionResponse���Ե�ֵ��
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
