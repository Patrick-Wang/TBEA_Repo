package com.tbea.datatransfer.model.dao.zj.jygk.ndjhzb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zj.jygk.NDJHZB;

@Transactional("transactionManagersb")
public class NDJHZBSBDaoImpl extends AbstractReadOnlyDaoImpl<NDJHZB> implements
		NDJHZBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NDJHZB> getAllNDJHZB() {
		String sql = "From NDJHZB";
		Query query = getEntityManager().createQuery(sql);
		List<NDJHZB> resultList = query.getResultList();
		return resultList;
	}

}
