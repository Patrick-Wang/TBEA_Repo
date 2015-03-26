package com.tbea.datatransfer.model.dao.zj.jygk.sjzb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zj.jygk.SJZB;

@Transactional("transactionManagersb2")
public class SJZBSBDaoImpl extends AbstractReadOnlyDaoImpl<SJZB> implements
		SJZBDao {

	@Override
	@PersistenceContext(unitName = "sbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SJZB> getAllSJZB() {
		String sql = "From SJZB";
		Query query = getEntityManager().createQuery(sql);
		List<SJZB> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<SJZB> getNewSJZB(int nf, int yf) {
		String sql = "From SJZB Where nf > :nf or (nf = :nf and yf > :yf)";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("nf", nf);
		query.setParameter("yf", yf);
		List<SJZB> resultList = query.getResultList();
		return resultList;
	}

}
