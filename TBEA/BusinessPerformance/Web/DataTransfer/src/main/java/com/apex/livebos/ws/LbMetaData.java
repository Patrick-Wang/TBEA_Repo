
package com.apex.livebos.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>lbMetaData complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="lbMetaData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="colInfo" type="{http://ws.livebos.apex.com/}colInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="columnCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * ��ȡcolumnCount���Ե�ֵ��
     * 
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * ����columnCount���Ե�ֵ��
     * 
     */
    public void setColumnCount(int value) {
        this.columnCount = value;
    }

}
