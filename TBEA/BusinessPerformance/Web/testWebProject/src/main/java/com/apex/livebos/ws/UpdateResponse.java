
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>updateResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡlbeResult���Ե�ֵ��
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
     * ����lbeResult���Ե�ֵ��
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
