package com.tbea.datatransfer.model.dao.zjsb.tbbzjxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.TBBZJXXBYQ;

@Transactional("transactionManagersb")
public class TBBZJXXSBDaoImpl extends AbstractReadOnlyDaoImpl<TBBZJXXBYQ>
		implements TBBZJXXSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TBBZJXXBYQ> getAllTBBZJXX() {
		String sql = "From TBBZJXXBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<TBBZJXXBYQ> resultList = query.getResultList();
		return resultList;
	}

}
