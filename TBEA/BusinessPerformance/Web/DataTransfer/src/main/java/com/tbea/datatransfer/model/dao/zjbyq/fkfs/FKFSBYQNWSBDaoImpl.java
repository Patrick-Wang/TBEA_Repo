package com.tbea.datatransfer.model.dao.zjbyq.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQNWBYQ;

@Transactional("transactionManagersb")
public class FKFSBYQNWSBDaoImpl extends AbstractReadOnlyDaoImpl<FKFSBYQNWBYQ>
		implements FKFSBYQNWBYQDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQNWBYQ> getAllFKFSBYQNW() {
		String sql = "From FKFSBYQNWBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQNWBYQ> resultList = query.getResultList();
		return resultList;
	}

}
