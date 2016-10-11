
package com.apex.livebos.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>workOwnerResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="workOwnerResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ws.livebos.apex.com/}lbeResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="approveMode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="owners" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workOwnerResponse", propOrder = {
    "approveMode",
    "owners"
})
public class WorkOwnerResponse
    extends LbeResult
{

    protected int approveMode;
    @XmlElement(nillable = true)
    protected List<String> owners;

    /**
     * 获取approveMode属性的值。
     * 
     */
    public int getApproveMode() {
        return approveMode;
    }

    /**
     * 设置approveMode属性的值。
     * 
     */
    public void setApproveMode(int value) {
        this.approveMode = value;
    }

    /**
     * Gets the value of the owners property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the owners property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOwners().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOwners() {
        if (owners == null) {
            owners = new ArrayList<String>();
        }
        return this.owners;
    }

}
