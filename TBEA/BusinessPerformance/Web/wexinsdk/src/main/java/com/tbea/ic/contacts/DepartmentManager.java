package com.tbea.ic.contacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tbea.ic.auth.Connection;
import com.tbea.ic.structure.Node;
import com.tbea.ic.structure.Node.Visitor;
import com.tbea.ic.util.JSON;

public class DepartmentManager {
	
	Map<Integer, Node<Department>> idMap = new HashMap<Integer, Node<Department>>();
	Node<Department> node;
	private DepartmentManager(Node<Department> node){
		node.accept(new Visitor<Department>(){

			public boolean visit(Node<Department> node) {
				idMap.put(node.getData().getId(), node);
				return true;
			}
			
		});
		this.node = node;
	}
	
	private static void buildTree(Node<Department> root, List<Department> departs) {
		if (null != root){
			for (int i = 0; i < departs.size(); ++i){
				if (departs.get(i).getParentid() == root.getData().getId()){
					Node<Department> child = new Node<Department>();
					child.setData(departs.get(i));
					root.append(child);
					buildTree(child, departs);
				}
			}
		}
	}

	private static List<Department> toDeparts(JSONArray jDeparts) {
		List<Department> departs = new ArrayList<Department>();
		for (int i = 0; i < jDeparts.size(); ++i){
			departs.add((Department) JSONObject.toBean(jDeparts.getJSONObject(i), Department.class));
		}
		return departs;
	}
	
	public static DepartmentManager query(int departId){
		String resp = Connection.getInstance().httpsGet("https://qyapi.weixin.qq.com/cgi-bin/department/list?id=" + departId + "&");
		JSONObject jResp = JSONObject.fromObject(resp);
		if (0 == jResp.getInt("errcode")){
			JSONArray jDeparts = jResp.getJSONArray("department");
			List<Department> departs = toDeparts(jDeparts);
			Node<Department> root = null;
			for (int i = 0; i < departs.size(); ++i){
				if (departs.get(i).getId() == departId){
					root = new Node<Department>();
					root.setData(departs.get(i));
					break;
				}
			}
			buildTree(root, departs);
			return new DepartmentManager(root);
		}
		return null;
	}

	public Node<Department> get(int departId){
		return idMap.get(departId);
	}

	public Node<Department> getDepartment(){
		return node;
	}
	
	public boolean update(Department dep){
		if (dep.getId() != null){

				String result = Connection.getInstance().httpsPost(
						"https://qyapi.weixin.qq.com/cgi-bin/department/update?", 
						JSON.stringify(dep));
				JSONObject jo = JSONObject.fromObject(result);
				if (jo.getInt("errcode") == 0){
					return true;
				}
		}else{
			if (dep.getName() != null && dep.getParentid() != null){
				String result = Connection.getInstance().httpsPost(
						"https://qyapi.weixin.qq.com/cgi-bin/department/create?", 
						JSON.stringify(dep));
				JSONObject jo = JSONObject.fromObject(result);
				if (jo.getInt("errcode") == 0){
					dep.setId(jo.getInt("id"));
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean delete(Department dep){
		if (dep.getId() != null){
			String result = Connection.getInstance().httpsGet(
					"https://qyapi.weixin.qq.com/cgi-bin/department/create?id=" + dep.getId() + "&");
			JSONObject jo = JSONObject.fromObject(result);
			if (jo.getInt("errcode") == 0){
				return true;
			}
		}
		return false;
	}

}
