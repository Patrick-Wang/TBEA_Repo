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
	String code;
	String name;
	String spell;
	String sex;
	String category;
	String companyId;
	String company;
	String departId;
	String depart;
	String citizenId;
	String telephone;
	String cellphone;
	String e_mail;
	String position;
	String post;
	String isInPost;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getIsInPost() {
		return isInPost;
	}

	public void setIsInPost(String isInPost) {
		this.isInPost = isInPost;
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
