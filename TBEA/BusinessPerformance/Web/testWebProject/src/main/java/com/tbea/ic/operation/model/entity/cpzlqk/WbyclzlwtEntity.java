package com.tbea.ic.operation.model.entity.cpzlqk;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;


@Entity
@Table(name = "zl_wbyclzlwt")
public class WbyclzlwtEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	private static final long serialVersionUID = 1L;

	String serial_num;
	String company_name;
	Date issue_happen_date;
	String product_type;
	String production_num;
	String production_model;
	String issue_type;
	String sub_issue_type;
	String category_code;
	String quality_phenomenon;
	String detail;
	Double material_count;
	String measurement_units;
	String suppier;
	String responsibility_department;
	String filling_personnel;
	Date product_delivery_date;
	String failure_subject;
	String material_treatment_measure;
	String onsite_treatment_measure;
	String onsite_treatment_result;
	String user_unit;
	String onsite_after_sales;
	String after_sales_tel;
	String source;
	Timestamp time;
	Integer zt;
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public Date getIssue_happen_date() {
		return issue_happen_date;
	}
	public void setIssue_happen_date(Date issue_happen_date) {
		this.issue_happen_date = issue_happen_date;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getProduction_num() {
		return production_num;
	}
	public void setProduction_num(String production_num) {
		this.production_num = production_num;
	}
	public String getProduction_model() {
		return production_model;
	}
	public void setProduction_model(String production_model) {
		this.production_model = production_model;
	}
	public String getIssue_type() {
		return issue_type;
	}
	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}
	public String getSub_issue_type() {
		return sub_issue_type;
	}
	public void setSub_issue_type(String sub_issue_type) {
		this.sub_issue_type = sub_issue_type;
	}
	public String getQuality_phenomenon() {
		return quality_phenomenon;
	}
	public void setQuality_phenomenon(String quality_phenomenon) {
		this.quality_phenomenon = quality_phenomenon;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Double getMaterial_count() {
		return material_count;
	}
	public void setMaterial_count(Double material_count) {
		this.material_count = material_count;
	}
	public String getMeasurement_units() {
		return measurement_units;
	}
	public void setMeasurement_units(String measurement_units) {
		this.measurement_units = measurement_units;
	}
	public String getSuppier() {
		return suppier;
	}
	public void setSuppier(String suppier) {
		this.suppier = suppier;
	}
	public String getResponsibility_department() {
		return responsibility_department;
	}
	public void setResponsibility_department(String responsibility_department) {
		this.responsibility_department = responsibility_department;
	}
	public String getFilling_personnel() {
		return filling_personnel;
	}
	public void setFilling_personnel(String filling_personnel) {
		this.filling_personnel = filling_personnel;
	}
	public Date getProduct_delivery_date() {
		return product_delivery_date;
	}
	public void setProduct_delivery_date(Date product_delivery_date) {
		this.product_delivery_date = product_delivery_date;
	}
	public String getFailure_subject() {
		return failure_subject;
	}
	public void setFailure_subject(String failure_subject) {
		this.failure_subject = failure_subject;
	}
	public String getMaterial_treatment_measure() {
		return material_treatment_measure;
	}
	public void setMaterial_treatment_measure(String material_treatment_measure) {
		this.material_treatment_measure = material_treatment_measure;
	}
	public String getOnsite_treatment_measure() {
		return onsite_treatment_measure;
	}
	public void setOnsite_treatment_measure(String onsite_treatment_measure) {
		this.onsite_treatment_measure = onsite_treatment_measure;
	}
	public String getOnsite_treatment_result() {
		return onsite_treatment_result;
	}
	public void setOnsite_treatment_result(String onsite_treatment_result) {
		this.onsite_treatment_result = onsite_treatment_result;
	}
	public String getUser_unit() {
		return user_unit;
	}
	public void setUser_unit(String user_unit) {
		this.user_unit = user_unit;
	}
	public String getOnsite_after_sales() {
		return onsite_after_sales;
	}
	public void setOnsite_after_sales(String onsite_after_sales) {
		this.onsite_after_sales = onsite_after_sales;
	}
	public String getAfter_sales_tel() {
		return after_sales_tel;
	}
	public void setAfter_sales_tel(String after_sales_tel) {
		this.after_sales_tel = after_sales_tel;
	}
	public String getSerial_num() {
		return serial_num;
	}
	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}
