package com.tbea.datatransfer.model.dao.zjxl.yszkpzgh;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.YSZKPZGHXL;

@Transactional("transactionManagerxl")
public class YSZKPZGHXLDaoImpl extends AbstractReadOnlyDaoImpl<YSZKPZGHXL>
		implements YSZKPZGHXLDao {

	@Override
	@PersistenceContext(unitName = "xlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKPZGHXL> getAllYSZKPZGH() {
		String sql = "From YSZKPZGHXL";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKPZGHXL> resultList = query.getResultList();
		return resultList;
	}

}
