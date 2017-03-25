package com.tbea.ic.operation.model.entity.hr;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "hr_org")
public class Org extends AbstractReadWriteEntity implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	String	name	;
	String	code	;
	String	shortname	;
	String	type	;
	String	legalPerson	;
	String	responsiblePersonCode	;
	String	responsiblePersonName	;
	String	secondResponsiblePersonCode	;
	String	secondResponsiblePersonName	;
	String	parentOrgCode	;
	String	parentOrgName	;
	String	status	;
	String	englistName	;
	String	responsiblePersonPostCode	;
	String	secondResponsiblePersonPostCode	;
	String	isSealed	;
	String	isCanceled	;
	String	cancelDate	;
	String	isLegalEntity	;

	String mdmCode;
	String mdmDataUuid;
	String mdmBatch;
	Long updateTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getResponsiblePersonCode() {
		return responsiblePersonCode;
	}

	public void setResponsiblePersonCode(String responsiblePersonCode) {
		this.responsiblePersonCode = responsiblePersonCode;
	}

	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	public String getSecondResponsiblePersonCode() {
		return secondResponsiblePersonCode;
	}

	public void setSecondResponsiblePersonCode(String secondResponsiblePersonCode) {
		this.secondResponsiblePersonCode = secondResponsiblePersonCode;
	}

	public String getSecondResponsiblePersonName() {
		return secondResponsiblePersonName;
	}

	public void setSecondResponsiblePersonName(String secondResponsiblePersonName) {
		this.secondResponsiblePersonName = secondResponsiblePersonName;
	}

	public String getParentOrgCode() {
		return parentOrgCode;
	}

	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnglistName() {
		return englistName;
	}

	public void setEnglistName(String englistName) {
		this.englistName = englistName;
	}

	public String getResponsiblePersonPostCode() {
		return responsiblePersonPostCode;
	}

	public void setResponsiblePersonPostCode(String responsiblePersonPostCode) {
		this.responsiblePersonPostCode = responsiblePersonPostCode;
	}

	public String getSecondResponsiblePersonPostCode() {
		return secondResponsiblePersonPostCode;
	}

	public void setSecondResponsiblePersonPostCode(String secondResponsiblePersonPostCode) {
		this.secondResponsiblePersonPostCode = secondResponsiblePersonPostCode;
	}

	public String getIsSealed() {
		return isSealed;
	}

	public void setIsSealed(String isSealed) {
		this.isSealed = isSealed;
	}

	public String getIsCanceled() {
		return isCanceled;
	}

	public void setIsCanceled(String isCanceled) {
		this.isCanceled = isCanceled;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getIsLegalEntity() {
		return isLegalEntity;
	}

	public void setIsLegalEntity(String isLegalEntity) {
		this.isLegalEntity = isLegalEntity;
	}

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
