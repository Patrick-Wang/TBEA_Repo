package com.tbea.datatransfer.model.dao.zjdl.ydhkjhjgb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjdl.YDHKJHJGBDL;

@Transactional("transactionManagerdl")
public class YDHKJHJGBDLDaoImpl extends AbstractReadOnlyDaoImpl<YDHKJHJGBDL>
		implements YDHKJHJGBDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDHKJHJGBDL> getAllYDHKJHJGBDL() {
		String sql = "From YDHKJHJGBDL";
		Query query = getEntityManager().createQuery(sql);
		List<YDHKJHJGBDL> resultList = query.getResultList();
		return resultList;
	}

}
