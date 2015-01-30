
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>workAction complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="workAction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="actionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stepId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="stepName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * ��ȡactionId���Ե�ֵ��
     * 
     */
    public int getActionId() {
        return actionId;
    }

    /**
     * ����actionId���Ե�ֵ��
     * 
     */
    public void setActionId(int value) {
        this.actionId = value;
    }

    /**
     * ��ȡactionName���Ե�ֵ��
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
     * ����actionName���Ե�ֵ��
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

    /**
     * ��ȡstepName���Ե�ֵ��
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
     * ����stepName���Ե�ֵ��
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
