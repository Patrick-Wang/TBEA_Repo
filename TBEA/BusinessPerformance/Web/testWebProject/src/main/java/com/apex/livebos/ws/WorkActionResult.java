
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>workActionResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="workActionResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ws.livebos.apex.com/}lbeResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="instanceId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workActionResult", propOrder = {
    "instanceId"
})
public class WorkActionResult
    extends LbeResult
{

    protected long instanceId;

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

}
