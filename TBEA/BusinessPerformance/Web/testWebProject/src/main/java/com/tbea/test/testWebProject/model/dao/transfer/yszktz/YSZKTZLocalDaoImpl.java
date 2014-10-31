package com.tbea.test.testWebProject.model.dao.transfer.yszktz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.model.entity.local.YSZKTZLocal;

//@Repository
@Transactional("transactionManager")
public class YSZKTZLocalDaoImpl extends AbstractReadWriteDaoImpl<YSZKTZLocal>
		implements YSZKTZLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKTZLocal> getAllYSZKTZLocal() {
		String sql = "From YSZKTZLocal";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKTZLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public Double getCQK(Integer startTime, Integer endTime,
			List<String> sshyList, boolean isIncluded, boolean isTotal)
			throws Exception {
		Double result = 0.0D;
		String sql = "select sum(ysje - yhxje) from YSZKTZLocal"
				+ " where fhrq is not null";
		if (null != startTime) {
			sql += " and DateDiff(dd, fhrq, getdate()) > :startTime";
		}
		if (null != endTime) {
			sql += " and DateDiff(dd, fhrq, getdate()) <= :endTime";
		}
		if (!isTotal) {
			sql += " and Khsshy";
			if (!isIncluded) {
				sql += " not";
			}
			sql += " in (:sshyList)";
		}
		Query query = getEntityManager().createQuery(sql);
		if (null != startTime) {
			query.setParameter("startTime", startTime);
		}
		if (null != endTime) {
			query.setParameter("endTime", endTime);
		}
		if (!isTotal) {
			query.setParameter("sshyList", sshyList);
		}
		try {
			result = (Double) query.getSingleResult();
		} catch (NoResultException e) {
			result = 0.0D;
		}
		return result;
	}

	@Override
	public Double getYQK(String baseMonth, Integer startTime, Integer endTime)
			throws Exception {
		Double result = 0.0D;
		String sql = "select sum(ysje - yhxje) from YSZKTZLocal"
				+ " where dqrq is not null";
		if (null != startTime) {
			sql += " and DateDiff(mm, dqrq, :baseMonth) >= :startTime";
		}
		if (null != endTime) {
			sql += " and DateDiff(mm, dqrq, :baseMonth) < :endTime";
		}
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("baseMonth", baseMonth + "01");
		if (null != startTime) {
			query.setParameter("startTime", startTime);
		}
		if (null != endTime) {
			query.setParameter("endTime", endTime);
		}
		try {
			result = (Double) query.getSingleResult();
		} catch (NoResultException e) {
			result = 0.0D;
		}
		return result;
	}

	@Override
	public Double getYSZKJE(String baseMonth, List<String> sshyList,
			boolean isIncluded, boolean isTotal) throws Exception {
		Double result = 0.0D;
		String sql = "select sum(ysje) from YSZKTZLocal";
		if (!isTotal) {
			sql += " where khsshy";
			if (!isIncluded) {
				sql += " not";
			}
			sql += " in (:sshyList)";
		}
		Query query = getEntityManager().createQuery(sql);
		if (!isTotal) {
			query.setParameter("sshyList", sshyList);
		}
		try {
			result = (Double) query.getSingleResult();
		} catch (NoResultException e) {
			result = 0.0D;
		}
		return result;
	}

	@Override
	public Double getYSZKJG(String baseMonth, Integer startTime,
			Integer endTime, List<String> sshyList, boolean isIncluded,
			boolean isTotal, boolean isKXLB, boolean isZBJ) throws Exception {
		Double result = 0.0D;
		String sql = "select sum(ysje - yhxje) from YSZKTZLocal"
				+ " where dqrq is not null";
		if (null != startTime) {
			sql += " and DateDiff(mm, dqrq, :baseMonth) >= :startTime";
		}
		if (null != endTime) {
			sql += " and DateDiff(mm, dqrq, :baseMonth) < :endTime";
		}
		if (!isTotal) {
			sql += " and khsshy";
			if (!isIncluded) {
				sql += " not";
			}
			sql += " in (:sshyList)";
		}
		if (isKXLB) {
			if (isZBJ) {
				sql += " and kxlb = 10";
			} else {
				sql += " and kxlb <> 10";
			}
		}
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("baseMonth", baseMonth + "01");
		if (null != startTime) {
			query.setParameter("startTime", startTime);
		}
		if (null != endTime) {
			query.setParameter("endTime", endTime);
		}
		if (!isTotal) {
			query.setParameter("sshyList", sshyList);
		}
		try {
			result = (Double) query.getSingleResult();
		} catch (NoResultException e) {
			result = 0.0D;
		}
		return result;
	}

}
