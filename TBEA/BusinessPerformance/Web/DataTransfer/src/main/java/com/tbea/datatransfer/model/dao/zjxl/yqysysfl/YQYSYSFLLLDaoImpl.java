package com.tbea.datatransfer.model.dao.zjxl.yqysysfl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.YQYSYSFLXL;

@Transactional("transactionManagerll")
public class YQYSYSFLLLDaoImpl extends AbstractReadOnlyDaoImpl<YQYSYSFLXL>
		implements YQYSYSFLXLDao {

	@Override
	@PersistenceContext(unitName = "llDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YQYSYSFLXL> getAllYQYSYSFL() {
		String sql = "From YQYSYSFLXL";
		Query query = getEntityManager().createQuery(sql);
		List<YQYSYSFLXL> resultList = query.getResultList();
		return resultList;
	}

}
