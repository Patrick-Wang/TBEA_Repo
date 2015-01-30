
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryWorkflowDescribeResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="queryWorkflowDescribeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserInfo" type="{http://ws.livebos.apex.com/}workflowDescribeResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryWorkflowDescribeResponse", propOrder = {
    "userInfo"
})
public class QueryWorkflowDescribeResponse {

    @XmlElement(name = "UserInfo")
    protected WorkflowDescribeResponse userInfo;

    /**
     * ��ȡuserInfo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link WorkflowDescribeResponse }
     *     
     */
    public WorkflowDescribeResponse getUserInfo() {
        return userInfo;
    }

    /**
     * ����userInfo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link WorkflowDescribeResponse }
     *     
     */
    public void setUserInfo(WorkflowDescribeResponse value) {
        this.userInfo = value;
    }

}
