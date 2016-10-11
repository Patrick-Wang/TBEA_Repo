
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>logoutResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取logoutResult属性的值。
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
     * 设置logoutResult属性的值。
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
