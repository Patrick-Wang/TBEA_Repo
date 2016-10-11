
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryWorkflowDescribeResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="queryWorkflowDescribeResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UserInfo" type="{http://ws.livebos.apex.com/}workflowDescribeResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * 获取userInfo属性的值。
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
     * 设置userInfo属性的值。
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
