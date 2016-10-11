
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>execBizProcessResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="execBizProcessResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BizProcessResult" type="{http://ws.livebos.apex.com/}bizProcessResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "execBizProcessResponse", propOrder = {
    "bizProcessResult"
})
public class ExecBizProcessResponse {

    @XmlElement(name = "BizProcessResult")
    protected BizProcessResult bizProcessResult;

    /**
     * 获取bizProcessResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BizProcessResult }
     *     
     */
    public BizProcessResult getBizProcessResult() {
        return bizProcessResult;
    }

    /**
     * 设置bizProcessResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BizProcessResult }
     *     
     */
    public void setBizProcessResult(BizProcessResult value) {
        this.bizProcessResult = value;
    }

}
