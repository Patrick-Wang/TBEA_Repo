
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryOption complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="queryOption">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="batchSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orderBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="queryCount" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="queryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valueOption" type="{http://ws.livebos.apex.com/}valueOption" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * ��ȡbatchNo���Ե�ֵ��
     * 
     */
    public int getBatchNo() {
        return batchNo;
    }

    /**
     * ����batchNo���Ե�ֵ��
     * 
     */
    public void setBatchNo(int value) {
        this.batchNo = value;
    }

    /**
     * ��ȡbatchSize���Ե�ֵ��
     * 
     */
    public int getBatchSize() {
        return batchSize;
    }

    /**
     * ����batchSize���Ե�ֵ��
     * 
     */
    public void setBatchSize(int value) {
        this.batchSize = value;
    }

    /**
     * ��ȡorderBy���Ե�ֵ��
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
     * ����orderBy���Ե�ֵ��
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
     * ��ȡqueryCount���Ե�ֵ��
     * 
     */
    public boolean isQueryCount() {
        return queryCount;
    }

    /**
     * ����queryCount���Ե�ֵ��
     * 
     */
    public void setQueryCount(boolean value) {
        this.queryCount = value;
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
     * ��ȡvalueOption���Ե�ֵ��
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
     * ����valueOption���Ե�ֵ��
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
