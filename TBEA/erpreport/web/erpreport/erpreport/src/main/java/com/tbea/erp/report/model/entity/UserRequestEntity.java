package com.tbea.erp.report.model.entity;

import com.speed.frame.model.entity.AbstractReadWriteEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "CUX_USREREQUEST_T")
public class UserRequestEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Integer usageId;
	Timestamp requestTime;
	Timestamp responseTime;
	String url;
	Integer isAjax;
	
	public Integer getUsageId() {
		return usageId;
	}
	public void setUsageId(Integer usageId) {
		this.usageId = usageId;
	}

	
	public Timestamp getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}
	public Timestamp getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Timestamp responseTime) {
		this.responseTime = responseTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIsAjax() {
		return isAjax;
	}
	public void setIsAjax(Integer isAjax) {
		this.isAjax = isAjax;
	}
	
}
