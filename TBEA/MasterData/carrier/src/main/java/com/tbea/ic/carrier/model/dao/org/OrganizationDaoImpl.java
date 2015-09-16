package com.tbea.ic.carrier.model.dao.org;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.carrier.model.entity.Organization;

@Repository
@Transactional("transactionManager")
public class OrganizationDaoImpl implements OrganizationDao{

	@PersistenceContext(unitName = "localDB")
	EntityManager manager;
	
	public void update(Organization orgEntity) {
		manager.merge(orgEntity);
	}

	public List<Organization> getByName(String name) {
		Query q = manager.createQuery("from Organization where jgmc like :name");
		q.setParameter("name", "%" + name + "%");
		return q.getResultList();
	}

	public List<Organization> getByNameExactly(String compName) {
		Query q = manager.createQuery("from Organization where jgmc = :name");
		q.setParameter("name", compName);
		return q.getResultList();
	}

}
