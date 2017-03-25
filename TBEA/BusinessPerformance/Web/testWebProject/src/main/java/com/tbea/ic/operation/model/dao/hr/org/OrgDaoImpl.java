package com.tbea.ic.operation.model.dao.hr.org;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.hr.org.OrgDao;
import com.tbea.ic.operation.model.entity.hr.Org;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class OrgDaoImpl   extends AbstractReadWriteDaoImpl<Org> implements OrgDao {
	
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public Org getByCode(String code) {
		Query q = this.getEntityManager().createQuery("from Org where code=:code");
		q.setParameter("code", code);
		List ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return (Org) ret.get(0);
	}
}
