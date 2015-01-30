
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>workActionResult complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="workActionResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.livebos.apex.com/}lbeResult">
 *       &lt;sequence>
 *         &lt;element name="instanceId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
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

}
