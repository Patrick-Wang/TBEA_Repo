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
	public List<Object[]> getXJLRBByDate(Calendar date) throws Exception {
		String sql = "exec yszk_xjlrb :date";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("date", date);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllYDZBFDW(Integer year, Integer month)
			throws Exception {
		String sql = "exec p_jysj2014_zbfdwhz :year, :month, :qylb, :zblx, :userid";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("year", year);
		query.setParameter("month", month);
		query.setParameter("qylb", "5;6;7;8;9;10;11;30;29;66;25;74;23;70;27");
		query.setParameter("zblx", 0);
		query.setParameter("userid", 0);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllZBHZ(Integer year, Integer month)
			throws Exception {
		String sql = "exec p_jysj2014_zbhzcxV2 :year, :month, :qylb, :zblx, :userid";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("year", year);
		query.setParameter("month", month);
		query.setParameter("qylb", "5;6;7;8;9;10;11;30;29;66;25;74;23;70;27");
		query.setParameter("zblx", 0);
		query.setParameter("userid", 0);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}

}
