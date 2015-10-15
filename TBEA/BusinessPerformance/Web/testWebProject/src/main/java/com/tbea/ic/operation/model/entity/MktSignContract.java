package com.tbea.ic.operation.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//field order cannot be changed
@Entity
@Table(name = "mkt_sign_contract")
public class MktSignContract implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "company_name")
	String companyName;

	@Id
	@Column(name = "contract_no")
	String contractNo;

	@Column(name = "office_name")
	String officeName;

	@Column(name = "sign_date")
	Date signDate;

	@Column(name = "industry_category")
	String industryCategory;

	@Column(name = "system_classfication")
	String systemClassfication;

	@Column(name = "project_area")
	String projectArea;

	@Column(name = "project_name")
	String projectName;

	@Column(name = "owner_name")
	String ownerName;

	@Column(name = "product_model")
	String productModel;

	@Column(name = "product_level")
	String productLevel;

	@Column(name = "product_amount")
	String productAmount;

	@Column(name = "product_volume")
	String productVolume;

	@Column(name = "product_price")
	String productPrice;

	@Column(name = "payment_method")
	String paymentMethod;

	@Column(name = "sign_person")
	String signPerson;

	@Column(name = "specific_sign_company")
	String specificSignCompany;

	@Column(name = "whether_instant_payment")
	String whetherInstantPayment;

	@Column(name = "whether_manufacturing_industry")
	String whetherManufacturingIndustry;

	@Column(name = "[startdate]")
	Date startdate;
	
	@Column(name = "[enddate]")
	Date enddate;
	
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @return the contractNo
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @return the officeName
	 */
	public String getOfficeName() {
		return officeName;
	}

	public Date getSignDate() {
		return signDate;
	}

	/**
	 * @return the industryCategory
	 */
	public String getIndustryCategory() {
		return industryCategory;
	}

	/**
	 * @return the systemClassfication
	 */
	public String getSystemClassfication() {
		return systemClassfication;
	}

	/**
	 * @return the projectArea
	 */
	public String getProjectArea() {
		return projectArea;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @return the productModel
	 */
	public String getProductModel() {
		return productModel;
	}

	/**
	 * @return the productLevel
	 */
	public String getProductLevel() {
		return productLevel;
	}

	/**
	 * @return the productAmount
	 */
	public String getProductAmount() {
		return productAmount;
	}

	/**
	 * @return the productVolume
	 */
	public String getProductVolume() {
		return productVolume;
	}

	/**
	 * @return the productPrice
	 */
	public String getProductPrice() {
		return productPrice;
	}

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @return the signPerson
	 */
	public String getSignPerson() {
		return signPerson;
	}

	/**
	 * @return the specificSignCompany
	 */
	public String getSpecificSignCompany() {
		return specificSignCompany;
	}

	/**
	 * @return the whetherInstantPayment
	 */
	public String getWhetherInstantPayment() {
		return whetherInstantPayment;
	}

	/**
	 * @return the whetherManufacturingIndustry
	 */
	public String getWhetherManufacturingIndustry() {
		return whetherManufacturingIndustry;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @param contractNo
	 *            the contractNo to set
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @param officeName
	 *            the officeName to set
	 */
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	/**
	 * @param industryCategory
	 *            the industryCategory to set
	 */
	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}

	/**
	 * @param systemClassfication
	 *            the systemClassfication to set
	 */
	public void setSystemClassfication(String systemClassfication) {
		this.systemClassfication = systemClassfication;
	}

	/**
	 * @param projectArea
	 *            the projectArea to set
	 */
	public void setProjectArea(String projectArea) {
		this.projectArea = projectArea;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @param ownerName
	 *            the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @param productModel
	 *            the productModel to set
	 */
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	/**
	 * @param productLevel
	 *            the productLevel to set
	 */
	public void setProductLevel(String productLevel) {
		this.productLevel = productLevel;
	}

	/**
	 * @param productAmount
	 *            the productAmount to set
	 */
	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}

	/**
	 * @param productVolume
	 *            the productVolume to set
	 */
	public void setProductVolume(String productVolume) {
		this.productVolume = productVolume;
	}

	/**
	 * @param productPrice
	 *            the productPrice to set
	 */
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @param paymentMethod
	 *            the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @param signPerson
	 *            the signPerson to set
	 */
	public void setSignPerson(String signPerson) {
		this.signPerson = signPerson;
	}

	/**
	 * @param specificSignCompany
	 *            the specificSignCompany to set
	 */
	public void setSpecificSignCompany(String specificSignCompany) {
		this.specificSignCompany = specificSignCompany;
	}

	/**
	 * @param whetherInstantPayment
	 *            the whetherInstantPayment to set
	 */
	public void setWhetherInstantPayment(String whetherInstantPayment) {
		this.whetherInstantPayment = whetherInstantPayment;
	}

	/**
	 * @param whetherManufacturingIndustry
	 *            the whetherManufacturingIndustry to set
	 */
	public void setWhetherManufacturingIndustry(
			String whetherManufacturingIndustry) {
		this.whetherManufacturingIndustry = whetherManufacturingIndustry;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
}
