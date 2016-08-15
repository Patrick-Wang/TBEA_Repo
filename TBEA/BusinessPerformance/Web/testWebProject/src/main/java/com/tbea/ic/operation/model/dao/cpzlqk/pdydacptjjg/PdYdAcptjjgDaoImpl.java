package com.tbea.ic.operation.model.dao.cpzlqk.pdydacptjjg;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cpzlqk.PdAcptjEntryEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.PdYdAcptjjgEntity;



@Repository(PdYdAcptjjgDaoImpl.NAME)
@Transactional("transactionManager")
public class PdYdAcptjjgDaoImpl extends AbstractReadWriteDaoImpl<PdYdAcptjjgEntity> implements PdYdAcptjjgDao {
	public final static String NAME = "PdYdAcptjjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<PdYdAcptjjgEntity> getAll() {
        Query q = getEntityManager().createQuery("from PdYdAcptjjgEntity");
		return q.getResultList();
	}

	@Override
	public List<PdAcptjEntryEntity> getEntryEntities(Integer dwid) {
		Query q = getEntityManager().createQuery("from PdAcptjEntryEntity where dwid = :dwid");
		q.setParameter("dwid", dwid);
		return q.getResultList();
	}
}
