package com.tbea.datatransfer.model.dao.zj.jygk.ydjhzb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zj.jygk.SJZB;
import com.tbea.datatransfer.model.entity.zj.jygk.YDJHZB;

@Transactional("transactionManagersb2")
public class YDJHZBSBDaoImpl extends AbstractReadOnlyDaoImpl<YDJHZB> implements
		YDJHZBDao {

	@Override
	@PersistenceContext(unitName = "sbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDJHZB> getAllYDJHZB() {
		String sql = "From YDJHZB";
		Query query = getEntityManager().createQuery(sql);
		List<YDJHZB> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<YDJHZB> getNewYDJHZB(int nf, int yf) {
		String sql = "From YDJHZB Where nf > :nf or (nf = :nf and yf > :yf)";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("nf", nf);
		query.setParameter("yf", yf);
		List<YDJHZB> resultList = query.getResultList();
		return resultList;
	}

}
