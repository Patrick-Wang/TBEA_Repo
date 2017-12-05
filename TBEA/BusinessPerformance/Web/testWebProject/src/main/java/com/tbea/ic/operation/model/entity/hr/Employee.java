package com.tbea.ic.operation.model.entity.hr;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "hr_employee")
public class Employee extends AbstractReadWriteEntity implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	String source;
	String	workNum	;
	String	name	;
	String	sax	;
	String	companyCode	;
	String	companyName	;
	String	parentCompanyCode	;
	String	parentCompanyName	;
	String	departCode	;
	String	departName	;
	String	parentDepartCode	;
	String	parentDepartName	;
	String	postCode	;
	String	postName	;
	String	jobLevel	;
	String	enterDate	;
	String	leaveDate	;
	String	isInDuty	;
	String	transferStatus	;
	String	employeeCategoryCode	;
	String	employeeCategory	;
	String	country	;
	String	range	;
	String	certificateType	;
	String	jobIndicate	;
	String	firstEnterDate	;
	String	stopTag	;
	String mdmCode;
	String mdmDataUuid;
	String mdmBatch;
	Long updateTime;

	public String getMdmDataUuid() {
		return mdmDataUuid;
	}

	public void setMdmDataUuid(String mdmDataUuid) {
		this.mdmDataUuid = mdmDataUuid;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getWorkNum() {
		return workNum;
	}

	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSax() {
		return sax;
	}

	public void setSax(String sax) {
		this.sax = sax;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getParentCompanyCode() {
		return parentCompanyCode;
	}

	public void setParentCompanyCode(String parentCompanyCode) {
		this.parentCompanyCode = parentCompanyCode;
	}

	public String getParentCompanyName() {
		return parentCompanyName;
	}

	public void setParentCompanyName(String parentCompanyName) {
		this.parentCompanyName = parentCompanyName;
	}

	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getParentDepartCode() {
		return parentDepartCode;
	}

	public void setParentDepartCode(String parentDepartCode) {
		this.parentDepartCode = parentDepartCode;
	}

	public String getParentDepartName() {
		return parentDepartName;
	}

	public void setParentDepartName(String parentDepartName) {
		this.parentDepartName = parentDepartName;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getIsInDuty() {
		return isInDuty;
	}

	public void setIsInDuty(String isInDuty) {
		this.isInDuty = isInDuty;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getEmployeeCategoryCode() {
		return employeeCategoryCode;
	}

	public void setEmployeeCategoryCode(String employeeCategoryCode) {
		this.employeeCategoryCode = employeeCategoryCode;
	}

	public String getEmployeeCategory() {
		return employeeCategory;
	}

	public void setEmployeeCategory(String employeeCategory) {
		this.employeeCategory = employeeCategory;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getJobIndicate() {
		return jobIndicate;
	}

	public void setJobIndicate(String jobIndicate) {
		this.jobIndicate = jobIndicate;
	}

	public String getFirstEnterDate() {
		return firstEnterDate;
	}

	public void setFirstEnterDate(String firstEnterDate) {
		this.firstEnterDate = firstEnterDate;
	}

	public String getStopTag() {
		return stopTag;
	}

	public void setStopTag(String stopTag) {
		this.stopTag = stopTag;
	}

	public String getMdmCode() {
		return mdmCode;
	}

	public void setMdmCode(String mdmCode) {
		this.mdmCode = mdmCode;
	}

	public String getMdmBatch() {
		return mdmBatch;
	}

	public void setMdmBatch(String mdmBatch) {
		this.mdmBatch = mdmBatch;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
}
