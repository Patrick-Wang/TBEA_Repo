
package com.apex.livebos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="queryResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="QueryResult" type="{http://ws.livebos.apex.com/}queryResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryResponse", propOrder = {
    "queryResult"
})
public class QueryResponse {

    @XmlElement(name = "QueryResult")
    protected QueryResult queryResult;

    /**
     * 获取queryResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link QueryResult }
     *     
     */
    public QueryResult getQueryResult() {
        return queryResult;
    }

    /**
     * 设置queryResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link QueryResult }
     *     
     */
    public void setQueryResult(QueryResult value) {
        this.queryResult = value;
    }

}
