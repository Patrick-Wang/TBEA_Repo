package com.tbea.datatransfer.model.dao.zjsb.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQFDWBYQ;

@Transactional("transactionManagersb")
public class FKFSBYQFDWSBDaoImpl extends AbstractReadOnlyDaoImpl<FKFSBYQFDWBYQ>
		implements FKFSBYQFDWSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQFDWBYQ> getAllFKFSBYQFDW() {
		String sql = "From FKFSBYQFDWBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQFDWBYQ> resultList = query.getResultList();
		return resultList;
	}

}
