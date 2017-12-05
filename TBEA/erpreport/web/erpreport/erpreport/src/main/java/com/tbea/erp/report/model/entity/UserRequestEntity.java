package com.tbea.erp.report.model.entity;

import com.speed.frame.model.entity.AbstractReadWriteEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


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
	Date requestTime;
	Date responseTime;
	String url;
	Integer isAjax;
	
	public Integer getUsageId() {
		return usageId;
	}
	public void setUsageId(Integer usageId) {
		this.usageId = usageId;
	}

	
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
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
