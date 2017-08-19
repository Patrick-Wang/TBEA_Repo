package com.tbea.ic.operation.model.dao.dbversion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.DBVersion;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

@Repository
@Transactional("transactionManager")
public class DBVersionDaoImpl extends AbstractReadWriteDaoImpl<DBVersion> implements DBVersionDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	


	@Override
	public DBVersion getLatestVersion() {
		Query q = this.getEntityManager().createQuery("from DBVersion order by deployTime desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List ret = q.getResultList();
		return (DBVersion) ret.get(0);
	}

}
