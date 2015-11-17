package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglx;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class ReferBglxflDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyDwReferBglxfl> implements ReferBglxflDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<JygkZzyDwReferBglxfl> getDataList(int dwxxId,int bglxId) {
		Query q = this.getEntityManager().createQuery("from JygkZzyDwReferBglxfl where dwid = :dwid and bglxid = :bglxid order by sx");
		q.setParameter("dwid", dwxxId);
		q.setParameter("bglxid", bglxId);
		return q.getResultList();
	}	
}
