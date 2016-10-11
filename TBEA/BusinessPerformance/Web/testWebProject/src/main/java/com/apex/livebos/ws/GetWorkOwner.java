
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getWorkOwner complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取sessionId属性的值。
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
     * 设置sessionId属性的值。
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
     * 获取instanceId属性的值。
     * 
     */
    public long getInstanceId() {
        return instanceId;
    }

    /**
     * 设置instanceId属性的值。
     * 
     */
    public void setInstanceId(long value) {
        this.instanceId = value;
    }

    /**
     * 获取stepId属性的值。
     * 
     */
    public int getStepId() {
        return stepId;
    }

    /**
     * 设置stepId属性的值。
     * 
     */
    public void setStepId(int value) {
        this.stepId = value;
    }

}
