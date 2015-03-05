package com.tbea.ic.operation.model.dao.jygk.zbxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class ZBXXDaoImpl extends AbstractReadWriteDaoImpl<ZBXX> implements ZBXXDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<ZBXX> getZbs(List<Integer> gsztzbs) {
		Query q = this.getEntityManager().createQuery("from ZBXX where id in ( " + Util.toInteger(gsztzbs) + " )");
		return q.getResultList();
	}
	
	@Override
	public ZBXX getZbByName(String name) {
		Query q = this.getEntityManager().createQuery("from ZBXX where trim(name) = :name");
		q.setParameter("name", name);
		List<ZBXX> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}

}
