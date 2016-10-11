
package com.apex.livebos.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>lbMetaData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="lbMetaData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="colInfo" type="{http://ws.livebos.apex.com/}colInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="columnCount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lbMetaData", propOrder = {
    "colInfo",
    "columnCount"
})
public class LbMetaData {

    @XmlElement(nillable = true)
    protected List<ColInfo> colInfo;
    protected int columnCount;

    /**
     * Gets the value of the colInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the colInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ColInfo }
     * 
     * 
     */
    public List<ColInfo> getColInfo() {
        if (colInfo == null) {
            colInfo = new ArrayList<ColInfo>();
        }
        return this.colInfo;
    }

    /**
     * 获取columnCount属性的值。
     * 
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * 设置columnCount属性的值。
     * 
     */
    public void setColumnCount(int value) {
        this.columnCount = value;
    }

}
