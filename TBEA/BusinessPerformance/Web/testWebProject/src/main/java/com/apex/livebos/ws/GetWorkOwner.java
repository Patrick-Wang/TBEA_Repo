
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getWorkOwner complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getWorkOwner"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="instanceId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="stepId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getWorkOwner", propOrder = {
    "sessionId",
    "instanceId",
    "stepId"
})
public class GetWorkOwner {

    protected String sessionId;
    protected long instanceId;
    protected int stepId;

    /**
     * ��ȡsessionId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * ����sessionId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * ��ȡinstanceId���Ե�ֵ��
     * 
     */
    public long getInstanceId() {
        return instanceId;
    }

    /**
     * ����instanceId���Ե�ֵ��
     * 
     */
    public void setInstanceId(long value) {
        this.instanceId = value;
    }

    /**
     * ��ȡstepId���Ե�ֵ��
     * 
     */
    public int getStepId() {
        return stepId;
    }

    /**
     * ����stepId���Ե�ֵ��
     * 
     */
    public void setStepId(int value) {
        this.stepId = value;
    }

}
