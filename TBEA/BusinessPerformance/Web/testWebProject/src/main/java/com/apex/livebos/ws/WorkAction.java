
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>workAction complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="workAction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="actionId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="actionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stepId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="stepName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workAction", propOrder = {
    "actionId",
    "actionName",
    "stepId",
    "stepName"
})
public class WorkAction {

    protected int actionId;
    protected String actionName;
    protected int stepId;
    protected String stepName;

    /**
     * 获取actionId属性的值。
     * 
     */
    public int getActionId() {
        return actionId;
    }

    /**
     * 设置actionId属性的值。
     * 
     */
    public void setActionId(int value) {
        this.actionId = value;
    }

    /**
     * 获取actionName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * 设置actionName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionName(String value) {
        this.actionName = value;
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

    /**
     * 获取stepName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStepName() {
        return stepName;
    }

    /**
     * 设置stepName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStepName(String value) {
        this.stepName = value;
    }

}
