package com.tbea.ic.operation.model.dao.hr.employee;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.hr.Employee;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class EmployeeDaoImpl   extends AbstractReadWriteDaoImpl<Employee> implements EmployeeDao {
	
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public Employee getByCode(String code) {
		Query q = this.getEntityManager().createQuery("from Employee where code=:code");
		q.setParameter("code", code);
		List ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return (Employee) ret.get(0);
	}
}
