
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>execBizProcessResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡbizProcessResult���Ե�ֵ��
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
     * ����bizProcessResult���Ե�ֵ��
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
