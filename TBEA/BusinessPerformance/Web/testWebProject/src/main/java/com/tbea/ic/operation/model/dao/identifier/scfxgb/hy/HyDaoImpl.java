package com.tbea.ic.operation.model.dao.identifier.scfxgb.hy;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.scfxgb.HyEntity;



@Repository(HyDaoImpl.NAME)
@Transactional("transactionManager")
public class HyDaoImpl extends AbstractReadWriteDaoImpl<HyEntity> implements HyDao {
	public final static String NAME = "HyDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<HyEntity> getAll() {
		Query q = this.getEntityManager().createQuery("from HyEntity");
		return q.getResultList();
	}

	@Override
	public HyEntity getByName(String hyName) {
		Query q = this.getEntityManager().createQuery("from HyEntity where trim(name) = :name");
		q.setParameter("name", hyName);
		List ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return (HyEntity) ret.get(0);
	}
}
