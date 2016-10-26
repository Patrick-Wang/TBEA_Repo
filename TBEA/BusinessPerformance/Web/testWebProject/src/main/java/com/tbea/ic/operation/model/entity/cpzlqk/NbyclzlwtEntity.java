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
@Table(name = "zl_nbyclzlwt")
public class NbyclzlwtEntity extends AbstractReadWriteEntity implements Serializable {
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
	String material_quality_phenomenon;
	String detail;
	String material_happen_phase;
	Double material_count;
	String measurement_units;
	String suppier_id;
	String suppier;
	String issue_process;
	String responsibility_department;
	String material_treatment_measure;
	String onsite_treatment_measure;
	String onsite_treatment_result;
	String causa_analysis;
	String assessment;
	String filling_personnel;
	String source;
	Timestamp time;
	Timestamp shsj;
	Integer zt;
	public String getSuppier_id() {
		return suppier_id;
	}
	public void setSuppier_id(String suppier_id) {
		this.suppier_id = suppier_id;
	}
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
	public String getMaterial_quality_phenomenon() {
		return material_quality_phenomenon;
	}
	public void setMaterial_quality_phenomenon(String material_quality_phenomenon) {
		this.material_quality_phenomenon = material_quality_phenomenon;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getMaterial_happen_phase() {
		return material_happen_phase;
	}
	public void setMaterial_happen_phase(String material_happen_phase) {
		this.material_happen_phase = material_happen_phase;
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
	public String getIssue_process() {
		return issue_process;
	}
	public void setIssue_process(String issue_process) {
		this.issue_process = issue_process;
	}
	public String getResponsibility_department() {
		return responsibility_department;
	}
	public void setResponsibility_department(String responsibility_department) {
		this.responsibility_department = responsibility_department;
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
	public String getCausa_analysis() {
		return causa_analysis;
	}
	public void setCausa_analysis(String causa_analysis) {
		this.causa_analysis = causa_analysis;
	}
	public String getAssessment() {
		return assessment;
	}
	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}
	public String getFilling_personnel() {
		return filling_personnel;
	}
	public void setFilling_personnel(String filling_personnel) {
		this.filling_personnel = filling_personnel;
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
	public Timestamp getShsj() {
		return shsj;
	}
	public void setShsj(Timestamp shsj) {
		this.shsj = shsj;
	}
}
