package com.tbea.datatransfer.model.dao.local.jygk.ydzbzt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.jygk.YDZBZTLocal;

@Transactional("transactionManager")
public class YDZBZTLocalDaoImpl extends AbstractReadWriteDaoImpl<YDZBZTLocal>
		implements YDZBZTLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDZBZTLocal> getAllYDZBZTLocal() {
		String sql = "From YDZBZTLocal";
		Query query = getEntityManager().createQuery(sql);
		List<YDZBZTLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void truncateYDZBZTLocal() {
		String sql = "truncate table jygk_ydzbzt";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void deleteYDZBZTLocalByDW(List<Integer> dwidList) {
		String sql = "Delete From YDZBZTLocal Where dwid in (:dwidList)";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("dwidList", dwidList);
		query.executeUpdate();
	}

	@Override
	public void deleteYDZBZTLocalByDWAndDate(List<Integer> dwidList, int nf,
			int yf) {
		String sql = "Delete From YDZBZTLocal Where dwid in (:dwidList)"
				+ " and (nf > :nf or (nf = :nf and yf > :yf))";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("dwidList", dwidList);
		query.setParameter("nf", nf);
		query.setParameter("yf", yf);
		query.executeUpdate();
	}

}
