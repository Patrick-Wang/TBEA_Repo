package com.tbea.ic.contacts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import com.tbea.ic.auth.Connection;
import com.tbea.ic.structure.Node;

public class Employee {
	// {
	// "userid": "zhangsan",
	// "name": "张三",
	// "department": [1, 2],
	// "position": "产品经理",
	// "mobile": "15913215421",
	// "gender": "1",
	// "email": "zhangsan@gzdev.com",
	// "weixinid": "zhangsan4dev",
	// "avatar_mediaid": "2-G6nrLmr5EC3MNb_-zL1dDdzkd0p7cNliYu9V5w7o8K0",
	// "extattr":
	// {"attrs":[{"name":"爱好","value":"旅游"},{"name":"卡号","value":"1234567234"}]}
	// }
	String userid;
	String name;
	List<Integer> department;
	String position;
	String mobile;
	String gender;
	String email;
	String weixinid;
	String avatar_mediaid;
	String avatar;
	String extattr;
	Integer status;// 关注状态: 1=已关注，2=已禁用，4=未关注

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getDepartment() {
		return department;
	}

	public void setDepartment(List<Integer> department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getAvatar_mediaid() {
		return avatar_mediaid;
	}

	public void setAvatar_mediaid(String avatar_mediaid) {
		this.avatar_mediaid = avatar_mediaid;
	}

	public String getExtattr() {
		return extattr;
	}

	public void setExtattr(String extattr) {
		this.extattr = extattr;
	}

	public List<Node<Department>> getDeparts(DepartmentManager querier) {
		List<Node<Department>> deps = new ArrayList<Node<Department>>();
		for (int i = 0; i < department.size(); ++i) {
			deps.add(querier.get(department.get(i)));
		}
		return deps;
	}

}
