package com.tbea.ic.operation.model.dao.identifier.cwgb.km;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.cwgb.KmEntity;



@Repository(KmDaoImpl.NAME)
@Transactional("transactionManager")
public class KmDaoImpl extends AbstractReadWriteDaoImpl<KmEntity> implements KmDao {
	public final static String NAME = "KmDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public KmEntity getById(Integer id) {
		Query q = this.getEntityManager().createQuery("from KmEntity where id=:kmId");
		q.setParameter("kmId", id);
		List<KmEntity> result = q.getResultList();
		if (result == null) {
			return null;
		}
		return result.get(0);
	}
}
