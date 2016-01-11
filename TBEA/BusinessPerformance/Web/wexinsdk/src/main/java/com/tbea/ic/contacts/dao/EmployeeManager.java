package com.tbea.ic.contacts.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tbea.ic.auth.Connection;
import com.tbea.ic.contacts.entity.Department;
import com.tbea.ic.contacts.entity.Employee;
import com.tbea.ic.structure.Node;
import com.tbea.ic.util.JSON;

public class EmployeeManager {

	
	private static EmployeeManager ins = new EmployeeManager();
	
	public static EmployeeManager getInstance(){
		return ins;
	}
	
	public List<Employee> queryByDepartId(int departId){
		String resp = Connection.getInstance().httpsGet("https://qyapi.weixin.qq.com/cgi-bin/user/list?department_id=" + departId + "&fetch_child=0&status=0&");
		JSONObject jResp = JSONObject.fromObject(resp);
		if (0 == jResp.getInt("errcode")){
			return toList(jResp.getJSONArray("userlist"));
		}
		return null;
	}
	
	private static List<Employee> toList(JSONArray jArr) {
		List<Employee> list = new ArrayList<Employee>();
		for (int i = 0; i < jArr.size(); ++i){
			list.add((Employee) JSONObject.toBean(jArr.getJSONObject(i), Employee.class));
		}
		return list;
	}
	
	public Employee queryByUserId(String userId ){
		String resp = Connection.getInstance().httpsGet("https://qyapi.weixin.qq.com/cgi-bin/user/get?userid=" + userId + "&");
		JSONObject jResp = JSONObject.fromObject(resp);
		if (0 == jResp.getInt("errcode")){
			return (Employee) JSONObject.toBean(jResp, Employee.class);
		}	
		return null;
	}
	

	public boolean update(Employee emplyee) {
		if (emplyee.getUserid() != null){
			String result = Connection.getInstance().httpsPost(
					"https://qyapi.weixin.qq.com/cgi-bin/user/update?",
					JSON.stringify(emplyee));
			JSONObject jo = JSONObject.fromObject(result);
			if (jo.getInt("errcode") == 0) {
				return true;
			}
		}
		return false;
	}

	//{"errcode":60113,"errmsg":"mobile\/email\/weixin must not be all empty"}
	public boolean create(Employee employee) {
		if (employee.getUserid() != null && employee.getName() != null){
			String result = Connection.getInstance().httpsPost(
					"https://qyapi.weixin.qq.com/cgi-bin/user/create?",
					JSON.stringify(employee));
			JSONObject jo = JSONObject.fromObject(result);
			if (jo.getInt("errcode") == 0) {
				return true;
			}
		}
		return false;
	}

	public boolean delete(Employee employee) {
		if (employee.getUserid() != null) {
			String result = Connection.getInstance().httpsGet(
					"https://qyapi.weixin.qq.com/cgi-bin/user/delete?userid="
							+ employee.getUserid() + "&");
			JSONObject jo = JSONObject.fromObject(result);
			if (jo.getInt("errcode") == 0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean delete(List<Employee> employee) {
		List<String> users = new ArrayList<String>();
		for (Employee emp : employee) {
			if (null != emp.getUserid()) {
				users.add(emp.getUserid());
			}
		}

		String result = Connection.getInstance().httpsPost(
				"https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?",
				JSONArray.fromObject(users).toString());
		JSONObject jo = JSONObject.fromObject(result);
		if (jo.getInt("errcode") == 0) {
			return true;
		}

		return false;
	}
	
	public List<Node<Department>> getDeparts(Employee employee, DepartmentQueryCache querier) {
		List<Node<Department>> deps = new ArrayList<Node<Department>>();
		for (int i = 0; i < employee.getDepartment().size(); ++i) {
			deps.add(querier.get(employee.getDepartment().get(i)));
		}
		return deps;
	}
}
