package com.tbea.ic.contacts.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tbea.ic.auth.Connection;
import com.tbea.ic.contacts.entity.Department;
import com.tbea.ic.structure.Node;
import com.tbea.ic.structure.Node.Visitor;

public class DepartmentQueryCache {
	Map<Integer, Node<Department>> idMap = new HashMap<Integer, Node<Department>>();
	Node<Department> node;

	private static DepartmentQueryCache ins = new DepartmentQueryCache();

	public static DepartmentQueryCache getInstance() {
		return ins;
	}
	
	public void refresh() {
		this.node = query(1);
	}

	private DepartmentQueryCache() {
		refresh();
	}

	public static Node<Department> build(int rootId, List<Department> departs) {
		Node<Department> root = null;
		for (int i = 0; i < departs.size(); ++i) {
			if (departs.get(i).getId() == rootId) {
				root = new Node<Department>();
				root.setData(departs.get(i));
				break;
			}
		}
		buildTree(root, departs);
		return root;
	}
	
	private static void buildTree(Node<Department> root,
			List<Department> departs) {
		if (null != root) {
			for (int i = 0; i < departs.size(); ++i) {
				if (departs.get(i).getParentid() == root.getData().getId()) {
					Node<Department> child = new Node<Department>();
					child.setData(departs.get(i));
					root.append(child);
					buildTree(child, departs);
				}
			}
		}
	}
	
	private static Node<Department> query(int departId) {
		String resp = Connection.getInstance().httpsGet(
				"https://qyapi.weixin.qq.com/cgi-bin/department/list?id="
						+ departId + "&");
		JSONObject jResp = JSONObject.fromObject(resp);
		if (0 == jResp.getInt("errcode")) {
			JSONArray jDeparts = jResp.getJSONArray("department");
			return build(departId, toDeparts(jDeparts));
		}
		return null;
	}

	public Node<Department> get(final int departId) {

		if (!idMap.containsKey(departId)) {
			node.accept(new Visitor<Department>() {
				public boolean visit(Node<Department> node) {
					idMap.put(node.getData().getId(), node);

					if (node.getData().getId() == departId) {
						return false;
					}
					return true;
				}
			});
		}

		return idMap.get(departId);
	}

	public Node<Department> getDepartment() {
		return node;
	}

	private static List<Department> toDeparts(JSONArray jDeparts) {
		List<Department> departs = new ArrayList<Department>();
		for (int i = 0; i < jDeparts.size(); ++i) {
			departs.add((Department) JSONObject.toBean(
					jDeparts.getJSONObject(i), Department.class));
		}
		return departs;
	}
}
