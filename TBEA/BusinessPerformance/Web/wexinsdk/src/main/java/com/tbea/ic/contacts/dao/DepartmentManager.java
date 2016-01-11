package com.tbea.ic.contacts.dao;

import net.sf.json.JSONObject;

import com.tbea.ic.auth.Connection;
import com.tbea.ic.contacts.entity.Department;
import com.tbea.ic.util.JSON;

public class DepartmentManager {

	private static DepartmentManager ins = new DepartmentManager();

	public static DepartmentManager getInstance() {
		return ins;
	}

	public boolean update(Department dep) {
		if (dep.getId() != null) {
			String result = Connection.getInstance().httpsPost(
					"https://qyapi.weixin.qq.com/cgi-bin/department/update?",
					JSON.stringify(dep));
			JSONObject jo = JSONObject.fromObject(result);
			if (jo.getInt("errcode") == 0) {
				return true;
			}
		}
		return false;
	}

	public boolean create(Department dep) {
		if (dep.getName() != null && dep.getParentid() != null) {
			String result = Connection.getInstance().httpsPost(
					"https://qyapi.weixin.qq.com/cgi-bin/department/create?",
					JSON.stringify(dep));
			JSONObject jo = JSONObject.fromObject(result);
			if (jo.getInt("errcode") == 0 && jo.containsKey("id")) {
				dep.setId(jo.getInt("id"));
				return true;
			}
		}
		return false;
	}

	public boolean delete(Department dep) {
		if (dep.getId() != null) {
			String result = Connection.getInstance().httpsGet(
					"https://qyapi.weixin.qq.com/cgi-bin/department/create?id="
							+ dep.getId() + "&");
			JSONObject jo = JSONObject.fromObject(result);
			if (jo.getInt("errcode") == 0) {
				return true;
			}
		}
		return false;
	}

}
