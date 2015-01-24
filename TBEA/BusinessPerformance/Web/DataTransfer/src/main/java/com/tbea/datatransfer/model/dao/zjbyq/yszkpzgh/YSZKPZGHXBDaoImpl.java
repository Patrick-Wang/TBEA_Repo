package com.tbea.datatransfer.model.dao.zjbyq.yszkpzgh;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.YSZKPZGHBYQ;

@Transactional("transactionManagerxb")
public class YSZKPZGHXBDaoImpl extends AbstractReadOnlyDaoImpl<YSZKPZGHBYQ>
		implements YSZKPZGHBYQDao {

	@Override
	@PersistenceContext(unitName = "xbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKPZGHBYQ> getAllYSZKPZGH() {
		String sql = "From YSZKPZGHBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKPZGHBYQ> resultList = query.getResultList();
		return resultList;
	}

}
