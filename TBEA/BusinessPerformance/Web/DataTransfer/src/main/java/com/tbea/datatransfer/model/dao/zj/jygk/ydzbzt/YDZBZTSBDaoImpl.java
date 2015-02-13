package com.tbea.datatransfer.model.dao.zj.jygk.ydzbzt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zj.jygk.YDZBZT;

@Transactional("transactionManagersb")
public class YDZBZTSBDaoImpl extends AbstractReadOnlyDaoImpl<YDZBZT> implements
		YDZBZTDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDZBZT> getAllYDZBZT() {
		String sql = "From YDZBZT";
		Query query = getEntityManager().createQuery(sql);
		List<YDZBZT> resultList = query.getResultList();
		return resultList;
	}

}
