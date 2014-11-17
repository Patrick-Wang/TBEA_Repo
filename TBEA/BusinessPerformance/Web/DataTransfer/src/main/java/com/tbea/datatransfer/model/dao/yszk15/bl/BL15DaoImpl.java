package com.tbea.datatransfer.model.dao.yszk15.bl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.yszk15.BL15;

@Transactional("transactionManager2")
public class BL15DaoImpl extends AbstractReadOnlyDaoImpl<BL15> implements
		BL15Dao {

	@Override
	@PersistenceContext(unitName = "15DB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BL15> getAllBL15() {
		String sql = "From BL15";
		Query query = getEntityManager().createQuery(sql);
		List<BL15> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getXJLRBByDate(Calendar date) {
		String sql = "exec yszk_xjlrb :date";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("date", date);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}

}
