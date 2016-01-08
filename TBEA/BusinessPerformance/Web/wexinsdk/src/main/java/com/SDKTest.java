package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tbea.ic.auth.Connection;
import com.tbea.ic.contacts.Department;
import com.tbea.ic.contacts.DepartmentManager;
import com.tbea.ic.contacts.Employee;
import com.tbea.ic.contacts.EmployeeManager;
import com.tbea.ic.message.Text;
import com.tbea.ic.message.sender.Messager;
import com.tbea.ic.structure.Node;
import com.tbea.ic.structure.Node.Visitor;

public class SDKTest {

	public static void main(String[] args) {
			
		try {
			Connection.getInstance().open("wx40b71464a42adcf3", "BW-Tuxi3fYjgjOOQv2d9iR_7Cz0mRSDVfVaHtjE-2Z1GaHQQIwV0awLsO17zPnPy");
			final DepartmentManager depMgr = DepartmentManager.query(1);
			depMgr.getDepartment().reverseAccept(new Visitor<Department>(){

				public boolean visit(Node<Department> node) {
					for (int i = 0, depth = node.depth(); i < depth; ++i){
						System.out.print("-");
					}
					System.out.println(JSONObject.fromObject(node.getData()));
					return true;
				}
			});
			
			depMgr.getDepartment().accept(new Visitor<Department>(){

				public boolean visit(Node<Department> node) {
					for (int i = 0, depth = node.depth(); i < depth; ++i){
						System.out.print("-");
					}
					System.out.println(JSONObject.fromObject(node.getData()));
					return true;
				}
			});
			
			EmployeeManager emplMgr = new EmployeeManager();
			List<Employee> emp = emplMgr.queryByDepartId(9);
			System.out.println(JSONArray.fromObject(emp));
			
			for (int i = 0; i < emp.size(); ++i){
				for (Node<Department> node : emp.get(i).getDeparts(depMgr)){
					System.out.println(JSONObject.fromObject(node.getData()));
				}
			}
			
			Messager msger = new Messager(25);
			msger.text(new Text("Hello!")).toUser("sunfuda").send();

			Department depa = new Department();
			depa.setName("test");
			depa.setParentid(1);
			
			Node<Department> nd1 = new Node<Department>();
			nd1.setData(depa);
			
			depa = new Department();
			depa.setName("test1");
			
			Node<Department> nd2 = new Node<Department>();
			nd2.setData(depa);
			
			nd1.append(nd2);
			
			depa = new Department();
			depa.setName("test3");
			
			nd2 = new Node<Department>();
			nd2.setData(depa);
			
			nd1.append(nd2);
			
			
			nd1.accept(new Visitor<Department>(){

				public boolean visit(Node<Department> node) {
					if (node.getParent() != null){
						Node<Department> p = node.getParent();
						node.getData().setParentid(p.getData().getId());
					}
					depMgr.update(node.getData());
					return true;
				}
				
			});
			
			Employee employee = new Employee();
			employee.setUserid("test1");
			List<Integer> deps = new ArrayList<Integer>();
			deps.add(23);
			employee.setDepartment(deps);
			employee.setName("大大");
			employee.setMobile("18655993625");
			emplMgr.create(employee);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
