package com.tbea.datatransfer.model.dao.zjtb.tbbzjxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.TBBZJXXBYQ;

@Transactional("transactionManagertb2")
public class TBBZJXXTBDaoImpl extends AbstractReadOnlyDaoImpl<TBBZJXXBYQ>
		implements TBBZJXXTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TBBZJXXBYQ> getAllTBBZJXX() {
		String sql = "From TBBZJXXBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<TBBZJXXBYQ> resultList = query.getResultList();
		return resultList;
	}

}
