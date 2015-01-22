package com.tbea.datatransfer.model.dao.local.mrhkhz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.MRHKHZLocal;

@Transactional("transactionManager")
public class MRHKHZLocalDaoImpl extends AbstractReadWriteDaoImpl<MRHKHZLocal>
		implements MRHKHZLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKHZLocal> getAllMRHKHZLocal() {
		String sql = "From MRHKHZLocal";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKHZLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteMRHKHZLocalByQY(int qybh) {
		String sql = "Delete From MRHKHZLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

	@Override
	public Double getYLJHKByQY(String date, int qybh) {
		Double result = null;
		String sql = "Select sum(hkje) as yljhk From MRHKHZLocal"
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
	public Object[] getYLJBCByQY(String date, int qybh) {
		String sql = "Select sum(qzqbbc) as yljqbbc"
				+ ", sum(qzzqbc) as ylzqbc"
				+ ", sum(qzqbbc + qzzqbc) as yljhjbc From MRHKHZLocal"
				+ " Where datediff(mm, hkrq, :date) = 0"
				+ " and datediff(dd, hkrq, :date) < 0" + " and qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("date", date);
		query.setParameter("qybh", qybh);
		Object[] resultList = (Object[]) query.getSingleResult();
		return resultList;
	}

}