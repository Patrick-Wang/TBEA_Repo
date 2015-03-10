package com.tbea.datatransfer.model.dao.local.yszktz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.YSZKTZLocal;

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
	public void deleteYSZKTZLocalByQY(int qybh) {
		String sql = "Delete From YSZKTZLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

	@Override
	public Double getCQK(String baseMonth, Integer startTime, Integer endTime,
			List<String> sshyList, boolean isIncluded, boolean isTotal)
			throws Exception {
		Double result = 0.0D;
		String sql = "select sum(yfhje - isnull(yhxje,0)) from YSZKTZLocal"
				+ " where fhrq is not null and yfhje - isnull(yhxje, 0) > 0";
		if (null != startTime) {
			sql += " and DateDiff(dd, fhrq, :baseMonth) > :startTime";
		}
		if (null != endTime) {
			sql += " and DateDiff(dd, fhrq, :baseMonth) <= :endTime";
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
			query.setParameter("baseMonth", baseMonth + "01");
			query.setParameter("startTime", startTime);
		}
		if (null != endTime) {
			query.setParameter("baseMonth", baseMonth + "01");
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

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Double> getCQKByQY(String baseMonth, Integer startTime,
			Integer endTime, List<String> sshyList, boolean isIncluded,
			boolean isTotal) throws Exception {
		String sql = "select qybh, sum(yfhje - isnull(yhxje,0)) from YSZKTZLocal"
				+ " where fhrq is not null and yfhje - isnull(yhxje, 0) > 0";
		if (null != startTime) {
			sql += " and DateDiff(dd, fhrq, :baseMonth) > :startTime";
		}
		if (null != endTime) {
			sql += " and DateDiff(dd, fhrq, :baseMonth) <= :endTime";
		}
		if (!isTotal) {
			sql += " and Khsshy";
			if (!isIncluded) {
				sql += " not";
			}
			sql += " in (:sshyList)";
		}
		sql += " group by qybh";
		Query query = getEntityManager().createQuery(sql);
		if (null != startTime) {
			query.setParameter("baseMonth", baseMonth + "01");
			query.setParameter("startTime", startTime);
		}
		if (null != endTime) {
			query.setParameter("baseMonth", baseMonth + "01");
			query.setParameter("endTime", endTime);
		}
		if (!isTotal) {
			query.setParameter("sshyList", sshyList);
		}
		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		Map<String, Double> resultMap = new HashMap<String, Double>();
		for (Object[] result : resultList) {
			resultMap.put(result[0].toString(),
					Double.valueOf(result[1].toString()));
		}
		return resultMap;
	}

	@Override
	public Double getYQK(String baseMonth, Integer startTime, Integer endTime)
			throws Exception {
		Double result = 0.0D;
		String sql = "select sum(yfhje - isnull(yhxje,0)) from YSZKTZLocal"
				+ " where yfhje - isnull(yhxje, 0) > 0";
		if (null != startTime || null != endTime) {
			sql += " and dqrq is not null";
		}
		if (null != startTime) {
			sql += " and DateDiff(mm, dqrq, :baseMonth) >= :startTime";
		}
		if (null != endTime) {
			sql += " and DateDiff(mm, dqrq, :baseMonth) < :endTime";
		}
		Query query = getEntityManager().createQuery(sql);
		if (null != startTime) {
			query.setParameter("baseMonth", baseMonth + "01");
			query.setParameter("startTime", startTime);
		}
		if (null != endTime) {
			query.setParameter("baseMonth", baseMonth + "01");
			query.setParameter("endTime", endTime);
		}
		try {
			result = (Double) query.getSingleResult();
		} catch (NoResultException e) {
			result = 0.0D;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Double> getYQKByQY(String baseMonth, Integer startTime,
			Integer endTime) throws Exception {
		String sql = "select qybh, sum(yfhje - isnull(yhxje,0)) from YSZKTZLocal"
				+ " where yfhje - isnull(yhxje, 0) > 0";
		if (null != startTime || null != endTime) {
			sql += " and dqrq is not null";
		}
		if (null != startTime) {
			sql += " and DateDiff(mm, dqrq, :baseMonth) >= :startTime";
		}
		if (null != endTime) {
			sql += " and DateDiff(mm, dqrq, :baseMonth) < :endTime";
		}
		sql += " group by qybh";
		Query query = getEntityManager().createQuery(sql);
		if (null != startTime) {
			query.setParameter("baseMonth", baseMonth + "01");
			query.setParameter("startTime", startTime);
		}
		if (null != endTime) {
			query.setParameter("baseMonth", baseMonth + "01");
			query.setParameter("endTime", endTime);
		}
		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		Map<String, Double> resultMap = new HashMap<String, Double>();
		for (Object[] result : resultList) {
			resultMap.put(result[0].toString(),
					Double.valueOf(result[1].toString()));
		}
		return resultMap;
	}

	@Override
	public Double getYSZKJG(String baseMonth, Integer startTime,
			Integer endTime, List<String> sshyList, boolean isIncluded,
			boolean isTotal, boolean isKXLB, boolean isZBJ) throws Exception {
		Double result = 0.0D;
		String sql = "select sum(yfhje - isnull(yhxje,0)) from YSZKTZLocal"
				+ " where yfhje - isnull(yhxje, 0) > 0";
		if (null != startTime || null != endTime) {
			sql += " and dqrq is not null";
		}
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
		if (null != startTime) {
			query.setParameter("baseMonth", baseMonth + "01");
			query.setParameter("startTime", startTime);
		}
		if (null != endTime) {
			query.setParameter("baseMonth", baseMonth + "01");
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

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Double> getYSZKJGByQY(String baseMonth,
			Integer startTime, Integer endTime, List<String> sshyList,
			boolean isIncluded, boolean isTotal, boolean isKXLB, boolean isZBJ)
			throws Exception {
		String sql = "select qybh, sum(yfhje - isnull(yhxje,0)) from YSZKTZLocal"
				+ " where yfhje - isnull(yhxje, 0) > 0";
		if (null != startTime || null != endTime) {
			sql += " and dqrq is not null";
		}
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
		sql += " group by qybh";
		Query query = getEntityManager().createQuery(sql);
		if (null != startTime) {
			query.setParameter("baseMonth", baseMonth + "01");
			query.setParameter("startTime", startTime);
		}
		if (null != endTime) {
			query.setParameter("baseMonth", baseMonth + "01");
			query.setParameter("endTime", endTime);
		}
		if (!isTotal) {
			query.setParameter("sshyList", sshyList);
		}
		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		Map<String, Double> resultMap = new HashMap<String, Double>();
		for (Object[] result : resultList) {
			resultMap.put(result[0].toString(),
					Double.valueOf(result[1].toString()));
		}
		return resultMap;
	}

}
