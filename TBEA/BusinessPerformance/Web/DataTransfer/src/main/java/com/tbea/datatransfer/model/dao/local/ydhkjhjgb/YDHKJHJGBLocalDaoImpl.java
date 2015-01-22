package com.tbea.datatransfer.model.dao.local.ydhkjhjgb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.YDHKJHJGBLocal;

@Transactional("transactionManager")
public class YDHKJHJGBLocalDaoImpl extends AbstractReadWriteDaoImpl<YDHKJHJGBLocal>
		implements YDHKJHJGBLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDHKJHJGBLocal> getAllYDHKJHJGBLocal() {
		String sql = "From YDHKJHJGBLocal";
		Query query = getEntityManager().createQuery(sql);
		List<YDHKJHJGBLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteYDHKJHJGBLocalByQY(int qybh) {
		String sql = "Delete From YDHKJHJGBLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

	@Override
	public Double getHKJHZEByQY(String date, int qybh) {
		Double result = null;
		String sql = "select qbkhyqyszk +  qbkhyqk +  qbkhwdqyszk +  qbkhwdqk"
				+ " +  zqkhyqyszk +  zqkhyqk +  zqkhwdqyszk +  zqkhwdqk"
				+ " from YDHKJHJGBLocal where DATEDIFF(mm, gxrq, :date) = 0"
				+ " Where datediff(mm, hkrq, :date) = 0"
				+ " and datediff(dd, hkrq, :date) >= 0" + " and qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("date", date);
		query.setParameter("qybh", qybh);
		try {
			Object resultObject = query.getSingleResult();
			result = Double.valueOf(String.valueOf(resultObject));
		} catch (NoResultException noResultException) {
			noResultException.printStackTrace();
			result = 0.0D;
		}
		return result;
	}
	
	@Override
	public YDHKJHJGBLocal getHKJHByQY(String date, int qybh) {
		YDHKJHJGBLocal result = null;
		String sql = "From YDHKJHJGBLocal where DATEDIFF(mm, gxrq, :date) = 0"
				+ " Where datediff(mm, hkrq, :date) = 0"
				+ " and datediff(dd, hkrq, :date) >= 0" + " and qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("date", date);
		query.setParameter("qybh", qybh);
		try {
			result = (YDHKJHJGBLocal) query.getSingleResult();
		} catch (NoResultException noResultException) {
			noResultException.printStackTrace();
		}
		return result;
	}

}