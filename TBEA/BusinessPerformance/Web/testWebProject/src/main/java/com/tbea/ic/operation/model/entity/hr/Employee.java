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
	String	workNum	;
	String	name	;
    String	postCode	;
	String	sax	;
	String	companyCode	;
	String	companyName	;
	String  operationUnitCode;
    String  operationUnitName;
	String	departCode	;
	String	departName	;
	String  post;
    String	isInPost	;
    String	employeeCategoryCode	;
    String	employeeCategory	;
    String	firstEnterDate	;
    String mdmCode;
    String mdmDataUuid;
    String approveTime;
	String mdmBatch;
	Long updateTime;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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

    public String getOperationUnitCode() {
        return operationUnitCode;
    }

    public void setOperationUnitCode(String operationUnitCode) {
        this.operationUnitCode = operationUnitCode;
    }

    public String getOperationUnitName() {
        return operationUnitName;
    }

    public void setOperationUnitName(String operationUnitName) {
        this.operationUnitName = operationUnitName;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        post = post;
    }

    public String getIsInPost() {
        return isInPost;
    }

    public void setIsInPost(String isInPost) {
        this.isInPost = isInPost;
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

    public String getFirstEnterDate() {
        return firstEnterDate;
    }

    public void setFirstEnterDate(String firstEnterDate) {
        this.firstEnterDate = firstEnterDate;
    }

    public String getMdmCode() {
        return mdmCode;
    }

    public void setMdmCode(String mdmCode) {
        this.mdmCode = mdmCode;
    }

    public String getMdmDataUuid() {
        return mdmDataUuid;
    }

    public void setMdmDataUuid(String mdmDataUuid) {
        this.mdmDataUuid = mdmDataUuid;
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
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
