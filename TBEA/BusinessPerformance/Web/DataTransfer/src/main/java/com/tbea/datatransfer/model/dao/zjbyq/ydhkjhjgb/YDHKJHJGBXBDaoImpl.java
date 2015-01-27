package com.tbea.datatransfer.model.dao.zjbyq.ydhkjhjgb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.YDHKJHJGBBYQ;

@Transactional("transactionManagerxb")
public class YDHKJHJGBXBDaoImpl extends AbstractReadOnlyDaoImpl<YDHKJHJGBBYQ>
		implements YDHKJHJGBBYQDao {

	@Override
	@PersistenceContext(unitName = "xbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDHKJHJGBBYQ> getAllYDHKJHJGB() {
		String sql = "From YDHKJHJGBBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<YDHKJHJGBBYQ> resultList = query.getResultList();
		return resultList;
	}

}
