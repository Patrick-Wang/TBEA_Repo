package com.tbea.ic.greet.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "account")
public class Account  extends AbstractReadWriteEntity{
	
	String name;
	String password;
	String jxglName;
	String jxglPassword;
	String OAName;
	String OAPassword;
	String jygkName;
	String jygkPassword;
	String zhyhName;
	String zhyhPassword;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJxglName() {
		return jxglName;
	}

	public void setJxglName(String jxglName) {
		this.jxglName = jxglName;
	}

	public String getJxglPassword() {
		return jxglPassword;
	}

	public void setJxglPassword(String jxglPassword) {
		this.jxglPassword = jxglPassword;
	}

	public String getOAName() {
		return OAName;
	}

	public void setOAName(String oAName) {
		OAName = oAName;
	}

	public String getOAPassword() {
		return OAPassword;
	}

	public void setOAPassword(String oAPassword) {
		OAPassword = oAPassword;
	}

	public String getJygkName() {
		return jygkName;
	}

	public void setJygkName(String jygkName) {
		this.jygkName = jygkName;
	}

	public String getJygkPassword() {
		return jygkPassword;
	}

	public void setJygkPassword(String jygkPassword) {
		this.jygkPassword = jygkPassword;
	}

	public String getZhyhName() {
		return zhyhName;
	}

	public void setZhyhName(String zhyhName) {
		this.zhyhName = zhyhName;
	}

	public String getZhyhPassword() {
		return zhyhPassword;
	}

	public void setZhyhPassword(String zhyhPassword) {
		this.zhyhPassword = zhyhPassword;
	}
}
