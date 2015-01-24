package com.tbea.datatransfer.model.dao.zjdl.yszkpzgh;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.YSZKPZGHXL;

@Transactional("transactionManagerdl")
public class YSZKPZGHDLDaoImpl extends AbstractReadOnlyDaoImpl<YSZKPZGHXL>
		implements YSZKPZGHDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
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
