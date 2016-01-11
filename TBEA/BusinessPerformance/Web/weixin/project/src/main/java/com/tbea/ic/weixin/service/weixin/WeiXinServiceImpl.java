package com.tbea.ic.weixin.service.weixin;

import com.tbea.ic.weixin.service.weixin.WeiXinService;
import com.tbea.ic.weixin.service.weixin.WeiXinService;
import com.tbea.ic.weixin.service.weixin.WeiXinService;
import com.tbea.ic.structure.Node;
import com.tbea.ic.structure.Node.Visitor;
import com.tbea.ic.weixin.model.dao.persion.PersionDao;
import com.tbea.ic.weixin.model.dao.oragnization.OragnizationDao;
import com.tbea.ic.weixin.model.entity.OrganizationEntity;
import com.tbea.ic.weixin.model.entity.PersionEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.auth.AuthException;
import com.tbea.ic.auth.Connection;
import com.tbea.ic.contacts.dao.DepartmentManager;
import com.tbea.ic.contacts.dao.DepartmentQueryCache;
import com.tbea.ic.contacts.dao.EmployeeManager;
import com.tbea.ic.contacts.entity.Department;
import com.tbea.ic.contacts.entity.Employee;
import com.tbea.ic.weixin.service.weixin.WeiXinService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("transactionManager")
public class WeiXinServiceImpl implements WeiXinService {
	
	@Autowired
	PersionDao persionDao;

	@Autowired
	OragnizationDao oragnizationDao;

	
	@Autowired
	public void init() throws AuthException, IOException{
		Connection.getInstance().open("wx40b71464a42adcf3", "BW-Tuxi3fYjgjOOQv2d9iR_7Cz0mRSDVfVaHtjE-2Z1GaHQQIwV0awLsO17zPnPy");
	}
	
	@Override
	public void transferOrg() {
		List<OrganizationEntity> orgs = oragnizationDao.getByFatherPK("1001");
		List<Department> deps = new ArrayList<Department>();

		Department rootDep = new Department();
		rootDep.setId(1);
		rootDep.setName("");
		deps.add(rootDep);
			
		for (OrganizationEntity entity : orgs){
			rootDep = new Department();
			rootDep.setId(Integer.valueOf(entity.getPk().replace(" ", "")));
			rootDep.setParentid(1);
			rootDep.setName(entity.getOname());
			deps.add(rootDep);
		}
		

		Node<Department> node = DepartmentQueryCache.build(1, deps);
		node.accept(new Visitor<Department>(){

			@Override
			public boolean visit(Node<Department> node) {
				DepartmentManager depMgr = DepartmentManager.getInstance();
				Department dep = node.getData();
				if (dep.getId() != 1){
					depMgr.create(dep);
				}
				return false;
			}
		});
	}

	@Override
	public void transferPersion() {
		List<PersionEntity> person = persionDao.getAllPersion();
		for (PersionEntity per : person){
			Employee employ = new Employee();
			employ.setName(per.getPsnname());
			employ.setMobile(per.getMobile());
			employ.addDepartment(Integer.valueOf(per.getPk_corp().replace(" ", "")));
			employ.setUserid(per.getPsncode());
			EmployeeManager.getInstance().create(employ);
		}
		System.out.println(person.size() + "");
	}


}
