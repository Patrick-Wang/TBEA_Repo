package com.tbea.ic.operation.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//field order cannot be changed
@Entity
@Table(name = "[mkt_bidinfo]")
public class MktBidInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "[company_name]")
	String companyName;

	@Id
	@Column(name = "[bid_no]")
	String bidNo;

	@Column(name = "[project_no]")
	String projectNo;

	@Column(name = "[authorization_no]")
	String authorizationNo;

	@Column(name = "[office_name]")
	String officeName;

	@Column(name = "[bid_month]")
	String bidMonth;

	@Column(name = "[bid_date]")
	String bidDate;

	@Column(name = "[industry_category]")
	String industryCategory;

	@Column(name = "[system_classification]")
	String systemClassification;

	@Column(name = "[project_area]")
	String projectArea;

	@Column(name = "[project_name]")
	String projectName;

	@Column(name = "[owner_name]")
	String ownerName;

	@Column(name = "[product_model]")
	String productModel;

	@Column(name = "[product_amount]")
	String productAmount;

	@Column(name = "[product_level]")
	String productLevel;

	@Column(name = "[product_volume]")
	String productVolume;

	@Column(name = "[bid_price]")
	String bidPrice;

	@Column(name = "[successful_bidder_name]")
	String successfulBidderName;

	@Column(name = "[sucessful_bidder_price]")
	String sucessfulBidderPrice;

	@Column(name = "[analysis_of_cause]")
	String analysisOfCause;

	@Column(name = "[successful_bidder_month]")
	String successfulBidderMonth;

	@Column(name = "[bid_status]")
	String bidStatus;

	@Column(name = "[whether_feedback_bid_summary]")
	String whetherFeedbackBidSummary;

	@Column(name = "[specific_bid_company_name]")
	String specificBidCompanyName;

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
	 * @return the bidNo
	 */
	public String getBidNo() {
		return bidNo;
	}

	/**
	 * @return the projectNo
	 */
	public String getProjectNo() {
		return projectNo;
	}

	/**
	 * @return the authorizationNo
	 */
	public String getAuthorizationNo() {
		return authorizationNo;
	}

	/**
	 * @return the officeName
	 */
	public String getOfficeName() {
		return officeName;
	}

	/**
	 * @return the bidMonth
	 */
	public String getBidMonth() {
		return bidMonth;
	}

	/**
	 * @return the bidDate
	 */
	public String getBidDate() {
		return bidDate;
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
	 * @return the productAmount
	 */
	public String getProductAmount() {
		return productAmount;
	}

	/**
	 * @return the productLevel
	 */
	public String getProductLevel() {
		return productLevel;
	}

	/**
	 * @return the productVolume
	 */
	public String getProductVolume() {
		return productVolume;
	}

	/**
	 * @return the bidPrice
	 */
	public String getBidPrice() {
		return bidPrice;
	}

	/**
	 * @return the successfulBidderName
	 */
	public String getSuccessfulBidderName() {
		return successfulBidderName;
	}

	/**
	 * @return the sucessfulBidderPrice
	 */
	public String getSucessfulBidderPrice() {
		return sucessfulBidderPrice;
	}

	/**
	 * @return the analysisOfCause
	 */
	public String getAnalysisOfCause() {
		return analysisOfCause;
	}

	/**
	 * @return the successfulBidderMonth
	 */
	public String getSuccessfulBidderMonth() {
		return successfulBidderMonth;
	}

	/**
	 * @return the bidStatus
	 */
	public String getBidStatus() {
		return bidStatus;
	}

	/**
	 * @return the whetherFeedbackBidSummary
	 */
	public String getWhetherFeedbackBidSummary() {
		return whetherFeedbackBidSummary;
	}

	/**
	 * @return the specificBidCompanyName
	 */
	public String getSpecificBidCompanyName() {
		return specificBidCompanyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @param bidNo
	 *            the bidNo to set
	 */
	public void setBidNo(String bidNo) {
		this.bidNo = bidNo;
	}

	/**
	 * @param projectNo
	 *            the projectNo to set
	 */
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	/**
	 * @param authorizationNo
	 *            the authorizationNo to set
	 */
	public void setAuthorizationNo(String authorizationNo) {
		this.authorizationNo = authorizationNo;
	}

	/**
	 * @param officeName
	 *            the officeName to set
	 */
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	/**
	 * @param bidMonth
	 *            the bidMonth to set
	 */
	public void setBidMonth(String bidMonth) {
		this.bidMonth = bidMonth;
	}

	/**
	 * @param bidDate
	 *            the bidDate to set
	 */
	public void setBidDate(String bidDate) {
		this.bidDate = bidDate;
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
	 * @param productAmount
	 *            the productAmount to set
	 */
	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}

	/**
	 * @param productLevel
	 *            the productLevel to set
	 */
	public void setProductLevel(String productLevel) {
		this.productLevel = productLevel;
	}

	/**
	 * @param productVolume
	 *            the productVolume to set
	 */
	public void setProductVolume(String productVolume) {
		this.productVolume = productVolume;
	}

	/**
	 * @param bidPrice
	 *            the bidPrice to set
	 */
	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}

	/**
	 * @param successfulBidderName
	 *            the successfulBidderName to set
	 */
	public void setSuccessfulBidderName(String successfulBidderName) {
		this.successfulBidderName = successfulBidderName;
	}

	/**
	 * @param sucessfulBidderPrice
	 *            the sucessfulBidderPrice to set
	 */
	public void setSucessfulBidderPrice(String sucessfulBidderPrice) {
		this.sucessfulBidderPrice = sucessfulBidderPrice;
	}

	/**
	 * @param analysisOfCause
	 *            the analysisOfCause to set
	 */
	public void setAnalysisOfCause(String analysisOfCause) {
		this.analysisOfCause = analysisOfCause;
	}

	/**
	 * @param successfulBidderMonth
	 *            the successfulBidderMonth to set
	 */
	public void setSuccessfulBidderMonth(String successfulBidderMonth) {
		this.successfulBidderMonth = successfulBidderMonth;
	}

	/**
	 * @param bidStatus
	 *            the bidStatus to set
	 */
	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	/**
	 * @param whetherFeedbackBidSummary
	 *            the whetherFeedbackBidSummary to set
	 */
	public void setWhetherFeedbackBidSummary(String whetherFeedbackBidSummary) {
		this.whetherFeedbackBidSummary = whetherFeedbackBidSummary;
	}

	/**
	 * @param specificBidCompanyName
	 *            the specificBidCompanyName to set
	 */
	public void setSpecificBidCompanyName(String specificBidCompanyName) {
		this.specificBidCompanyName = specificBidCompanyName;
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
