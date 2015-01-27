package com.tbea.datatransfer.model.dao.zjbyq.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQGWBYQ;

@Transactional("transactionManagertb2")
public class FKFSBYQGWTBDaoImpl extends AbstractReadOnlyDaoImpl<FKFSBYQGWBYQ>
		implements FKFSBYQGWBYQDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQGWBYQ> getAllFKFSBYQGW() {
		String sql = "From FKFSBYQGWBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQGWBYQ> resultList = query.getResultList();
		return resultList;
	}

}
