
package com.apex.livebos.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>doWorkAction complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="doWorkAction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workflowName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instanceId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="actionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="params" type="{http://ws.livebos.apex.com/}lbParameter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="caller" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doWorkAction", propOrder = {
    "sessionId",
    "workflowName",
    "instanceId",
    "actionId",
    "params",
    "caller",
    "summary"
})
public class DoWorkAction {

    protected String sessionId;
    protected String workflowName;
    protected long instanceId;
    protected int actionId;
    protected List<LbParameter> params;
    protected String caller;
    protected String summary;

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
     * ��ȡworkflowName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowName() {
        return workflowName;
    }

    /**
     * ����workflowName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowName(String value) {
        this.workflowName = value;
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
     * Gets the value of the params property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the params property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParams().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LbParameter }
     * 
     * 
     */
    public List<LbParameter> getParams() {
        if (params == null) {
            params = new ArrayList<LbParameter>();
        }
        return this.params;
    }

    /**
     * ��ȡcaller���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaller() {
        return caller;
    }

    /**
     * ����caller���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaller(String value) {
        this.caller = value;
    }

    /**
     * ��ȡsummary���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummary() {
        return summary;
    }

    /**
     * ����summary���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummary(String value) {
        this.summary = value;
    }

}
