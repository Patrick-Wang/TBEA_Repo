
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>logoutResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="logoutResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LogoutResult" type="{http://ws.livebos.apex.com/}logoutResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "logoutResponse", propOrder = {
    "logoutResult"
})
public class LogoutResponse {

    @XmlElement(name = "LogoutResult")
    protected LogoutResult logoutResult;

    /**
     * ��ȡlogoutResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link LogoutResult }
     *     
     */
    public LogoutResult getLogoutResult() {
        return logoutResult;
    }

    /**
     * ����logoutResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link LogoutResult }
     *     
     */
    public void setLogoutResult(LogoutResult value) {
        this.logoutResult = value;
    }

}
