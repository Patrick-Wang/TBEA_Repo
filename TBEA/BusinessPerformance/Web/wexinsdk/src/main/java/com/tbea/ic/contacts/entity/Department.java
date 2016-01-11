package com.tbea.ic.contacts.entity;

import java.util.Iterator;

import net.sf.json.JSONObject;

import com.tbea.ic.auth.Connection;

public class Department {
//	   	"name": "广州研发中心",
//	   	"parentid": "1",
//	   	"order": "1",
//	   	"id": "1"
	String name;
	Integer parentid;
	Integer order;
	Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
