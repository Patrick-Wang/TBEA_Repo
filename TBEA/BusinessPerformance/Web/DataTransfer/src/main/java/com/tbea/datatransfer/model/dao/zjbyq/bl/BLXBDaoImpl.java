package com.tbea.datatransfer.model.dao.zjbyq.bl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.BLBYQ;

//@Repository
@Transactional("transactionManagerxb")
public class BLXBDaoImpl extends AbstractReadOnlyDaoImpl<BLBYQ> implements
		BLBYQDao {

	@Override
	@PersistenceContext(unitName = "xbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BLBYQ> getAllBL() {
		String sql = "From BLBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<BLBYQ> resultList = query.getResultList();
		return resultList;
	}

}
