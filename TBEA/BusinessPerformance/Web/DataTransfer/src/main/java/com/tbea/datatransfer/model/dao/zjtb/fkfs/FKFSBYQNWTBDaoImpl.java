package com.tbea.datatransfer.model.dao.zjtb.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQNWBYQ;

@Transactional("transactionManagertb2")
public class FKFSBYQNWTBDaoImpl extends AbstractReadOnlyDaoImpl<FKFSBYQNWBYQ>
		implements FKFSBYQNWTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
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
