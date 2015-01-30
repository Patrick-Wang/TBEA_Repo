
package com.apex.livebos.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryResult complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="queryResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.livebos.apex.com/}lbeResult">
 *       &lt;sequence>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hasMore" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="metaData" type="{http://ws.livebos.apex.com/}lbMetaData" minOccurs="0"/>
 *         &lt;element name="queryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="records" type="{http://ws.livebos.apex.com/}lbRecord" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * ��ȡcount���Ե�ֵ��
     * 
     */
    public int getCount() {
        return count;
    }

    /**
     * ����count���Ե�ֵ��
     * 
     */
    public void setCount(int value) {
        this.count = value;
    }

    /**
     * ��ȡhasMore���Ե�ֵ��
     * 
     */
    public boolean isHasMore() {
        return hasMore;
    }

    /**
     * ����hasMore���Ե�ֵ��
     * 
     */
    public void setHasMore(boolean value) {
        this.hasMore = value;
    }

    /**
     * ��ȡmetaData���Ե�ֵ��
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
     * ����metaData���Ե�ֵ��
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
     * ��ȡqueryId���Ե�ֵ��
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
     * ����queryId���Ե�ֵ��
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
