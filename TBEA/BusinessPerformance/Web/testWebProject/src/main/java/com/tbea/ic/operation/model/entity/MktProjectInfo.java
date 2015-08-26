package com.tbea.ic.operation.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//field order cannot be changed
@Entity
@Table(name = "mkt_projectinfo")
public class MktProjectInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "company_name")
	String companyName;

	@Column(name = "office_name")
	String officeName;

	@Id
	@Column(name = "project_no")
	String projectNo;

	@Column(name = "industry_category")
	String industryCategory;

	@Column(name = "system_classification")
	String systemClassification;

	@Column(name = "project_name")
	String projectName;

	@Column(name = "owner_name")
	String ownerName;

	@Column(name = "product_model")
	String productModel;

	@Column(name = "product_amount")
	String productAmount;

	@Column(name = "excepted_bid_cost")
	Double exceptedBidCost;

	@Column(name = "excepted_bid_time")
	String exceptedBidTime;

	@Column(name = "project_area")
	String projectArea;

	@Column(name = "project_summary")
	String projectSummary;

	@Column(name = "project_advancement")
	String projectAdvancement;

	@Column(name = "chief_info")
	String chiefInfo;

	@Column(name = "leader_info")
	String leaderInfo;
	
	@Column(name = "other_company_name")
	String otherCompanyName;

	@Column(name = "bid_situation")
	String bidSituation;

	@Column(name = "bid_restrict")
	String bidRestrict;

	@Column(name = "remark")
	String remark;

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @return the officeName
	 */
	public String getOfficeName() {
		return officeName;
	}

	/**
	 * @return the projectNo
	 */
	public String getProjectNo() {
		return projectNo;
	}

	/**
	 * @return the industryCategory
	 */
	public String getIndustryCategory() {
		return industryCategory;
	}

	/**
	 * @return the systemClassification
	 */
	public String getSystemClassification() {
		return systemClassification;
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
	 * @return the productAmount
	 */
	public String getProductAmount() {
		return productAmount;
	}

	/**
	 * @return the exceptedBidCost
	 */
	public Double getExceptedBidCost() {
		return exceptedBidCost;
	}

	/**
	 * @return the exceptedBidTime
	 */
	public String getExceptedBidTime() {
		return exceptedBidTime;
	}

	/**
	 * @return the projectArea
	 */
	public String getProjectArea() {
		return projectArea;
	}

	/**
	 * @return the projectSummary
	 */
	public String getProjectSummary() {
		return projectSummary;
	}

	/**
	 * @return the projectAdvancement
	 */
	public String getProjectAdvancement() {
		return projectAdvancement;
	}

	/**
	 * @return the chiefInfo
	 */
	public String getChiefInfo() {
		return chiefInfo;
	}

	/**
	 * @return the otherCompanyName
	 */
	public String getOtherCompanyName() {
		return otherCompanyName;
	}

	/**
	 * @return the bidSituation
	 */
	public String getBidSituation() {
		return bidSituation;
	}

	/**
	 * @return the bidRestrict
	 */
	public String getBidRestrict() {
		return bidRestrict;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @param officeName
	 *            the officeName to set
	 */
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	/**
	 * @param projectNo
	 *            the projectNo to set
	 */
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	/**
	 * @param industryCategory
	 *            the industryCategory to set
	 */
	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}

	/**
	 * @param systemClassification
	 *            the systemClassification to set
	 */
	public void setSystemClassification(String systemClassification) {
		this.systemClassification = systemClassification;
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
	 * @param productAmount
	 *            the productAmount to set
	 */
	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}

	/**
	 * @param exceptedBidCost
	 *            the exceptedBidCost to set
	 */
	public void setExceptedBidCost(Double exceptedBidCost) {
		this.exceptedBidCost = exceptedBidCost;
	}

	/**
	 * @param exceptedBidTime
	 *            the exceptedBidTime to set
	 */
	public void setExceptedBidTime(String exceptedBidTime) {
		this.exceptedBidTime = exceptedBidTime;
	}

	/**
	 * @param projectArea
	 *            the projectArea to set
	 */
	public void setProjectArea(String projectArea) {
		this.projectArea = projectArea;
	}

	/**
	 * @param projectSummary
	 *            the projectSummary to set
	 */
	public void setProjectSummary(String projectSummary) {
		this.projectSummary = projectSummary;
	}

	/**
	 * @param projectAdvancement
	 *            the projectAdvancement to set
	 */
	public void setProjectAdvancement(String projectAdvancement) {
		this.projectAdvancement = projectAdvancement;
	}

	/**
	 * @param chiefInfo
	 *            the chiefInfo to set
	 */
	public void setChiefInfo(String chiefInfo) {
		this.chiefInfo = chiefInfo;
	}

	/**
	 * @param otherCompanyName
	 *            the otherCompanyName to set
	 */
	public void setOtherCompanyName(String otherCompanyName) {
		this.otherCompanyName = otherCompanyName;
	}

	/**
	 * @param bidSituation
	 *            the bidSituation to set
	 */
	public void setBidSituation(String bidSituation) {
		this.bidSituation = bidSituation;
	}

	/**
	 * @param bidRestrict
	 *            the bidRestrict to set
	 */
	public void setBidRestrict(String bidRestrict) {
		this.bidRestrict = bidRestrict;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the leaderInfo
	 */
	public String getLeaderInfo() {
		return leaderInfo;
	}

	/**
	 * @param leaderInfo the leaderInfo to set
	 */
	public void setLeaderInfo(String leaderInfo) {
		this.leaderInfo = leaderInfo;
	}
}
