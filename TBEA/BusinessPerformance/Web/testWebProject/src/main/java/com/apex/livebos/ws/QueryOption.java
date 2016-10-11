
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryOption complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="queryOption"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="batchNo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="batchSize" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="orderBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="queryCount" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="queryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="valueOption" type="{http://ws.livebos.apex.com/}valueOption" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryOption", propOrder = {
    "batchNo",
    "batchSize",
    "orderBy",
    "queryCount",
    "queryId",
    "valueOption"
})
public class QueryOption {

    protected int batchNo;
    protected int batchSize;
    protected String orderBy;
    protected boolean queryCount;
    protected String queryId;
    @XmlSchemaType(name = "string")
    protected ValueOption valueOption;

    /**
     * 获取batchNo属性的值。
     * 
     */
    public int getBatchNo() {
        return batchNo;
    }

    /**
     * 设置batchNo属性的值。
     * 
     */
    public void setBatchNo(int value) {
        this.batchNo = value;
    }

    /**
     * 获取batchSize属性的值。
     * 
     */
    public int getBatchSize() {
        return batchSize;
    }

    /**
     * 设置batchSize属性的值。
     * 
     */
    public void setBatchSize(int value) {
        this.batchSize = value;
    }

    /**
     * 获取orderBy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置orderBy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderBy(String value) {
        this.orderBy = value;
    }

    /**
     * 获取queryCount属性的值。
     * 
     */
    public boolean isQueryCount() {
        return queryCount;
    }

    /**
     * 设置queryCount属性的值。
     * 
     */
    public void setQueryCount(boolean value) {
        this.queryCount = value;
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
     * 获取valueOption属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ValueOption }
     *     
     */
    public ValueOption getValueOption() {
        return valueOption;
    }

    /**
     * 设置valueOption属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ValueOption }
     *     
     */
    public void setValueOption(ValueOption value) {
        this.valueOption = value;
    }

}
