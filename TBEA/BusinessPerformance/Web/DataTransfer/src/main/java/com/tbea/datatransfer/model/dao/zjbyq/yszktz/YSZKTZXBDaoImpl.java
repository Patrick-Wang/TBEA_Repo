package com.tbea.datatransfer.model.dao.zjbyq.yszktz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.YSZKTZBYQ;

@Transactional("transactionManagerxb")
public class YSZKTZXBDaoImpl extends AbstractReadOnlyDaoImpl<YSZKTZBYQ>
		implements YSZKTZBYQDao {

	@Override
	@PersistenceContext(unitName = "xbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKTZBYQ> getAllYSZKTZ() {
		// TODO
		// gxrq <= 2
//		String sql = "From YSZKTZTB Where DateDiff(dd,gxrq,getDate())<=2";
		String sql = "From YSZKTZBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKTZBYQ> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKTZBYQ> getCurrentYSZKTZ() {
		String sql = "From YSZKTZBYQ where DateDiff(dd, gxrq, (select MAX(gxrq) from YSZKTZBYQ)) = 0";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKTZBYQ> resultList = query.getResultList();
		return resultList;
	}

}
