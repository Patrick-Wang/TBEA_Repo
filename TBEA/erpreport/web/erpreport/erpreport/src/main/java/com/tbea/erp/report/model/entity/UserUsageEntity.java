package com.tbea.erp.report.model.entity;

import com.speed.frame.model.entity.AbstractReadWriteEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "CUX_USREUSAGE_T")
public class UserUsageEntity extends AbstractReadWriteEntity implements Serializable {
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

	String userName; 
	Timestamp loginTime;
	Timestamp logoutTime;
	Timestamp lastAccessedTime;
	String ip;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public Timestamp getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}
	public Timestamp getLastAccessedTime() {
		return lastAccessedTime;
	}
	public void setLastAccessedTime(Timestamp lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

}
