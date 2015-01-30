
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>loginResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="loginResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LoginResult" type="{http://ws.livebos.apex.com/}loginResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loginResponse", propOrder = {
    "loginResult"
})
public class LoginResponse {

    @XmlElement(name = "LoginResult")
    protected LoginResult loginResult;

    /**
     * ��ȡloginResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link LoginResult }
     *     
     */
    public LoginResult getLoginResult() {
        return loginResult;
    }

    /**
     * ����loginResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link LoginResult }
     *     
     */
    public void setLoginResult(LoginResult value) {
        this.loginResult = value;
    }

}
