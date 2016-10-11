
package com.apex.livebos.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="queryResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ws.livebos.apex.com/}lbeResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="hasMore" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="metaData" type="{http://ws.livebos.apex.com/}lbMetaData" minOccurs="0"/&gt;
 *         &lt;element name="queryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="records" type="{http://ws.livebos.apex.com/}lbRecord" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryResult", propOrder = {
    "count",
    "hasMore",
    "metaData",
    "queryId",
    "records"
})
public class QueryResult
    extends LbeResult
{

    protected int count;
    protected boolean hasMore;
    protected LbMetaData metaData;
    protected String queryId;
    @XmlElement(nillable = true)
    protected List<LbRecord> records;

    /**
     * 获取count属性的值。
     * 
     */
    public int getCount() {
        return count;
    }

    /**
     * 设置count属性的值。
     * 
     */
    public void setCount(int value) {
        this.count = value;
    }

    /**
     * 获取hasMore属性的值。
     * 
     */
    public boolean isHasMore() {
        return hasMore;
    }

    /**
     * 设置hasMore属性的值。
     * 
     */
    public void setHasMore(boolean value) {
        this.hasMore = value;
    }

    /**
     * 获取metaData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LbMetaData }
     *     
     */
    public LbMetaData getMetaData() {
        return metaData;
    }

    /**
     * 设置metaData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LbMetaData }
     *     
     */
    public void setMetaData(LbMetaData value) {
        this.metaData = value;
    }

    /**
     * 获取queryId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryId() {
        return queryId;
    }

    /**
     * 设置queryId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryId(String value) {
        this.queryId = value;
    }

    /**
     * Gets the value of the records property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the records property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LbRecord }
     * 
     * 
     */
    public List<LbRecord> getRecords() {
        if (records == null) {
            records = new ArrayList<LbRecord>();
        }
        return this.records;
    }

}
