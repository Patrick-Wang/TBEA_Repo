package com.tbea.datatransfer.model.dao.zjtb.mrhk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjtb.MRHKTB;

@Transactional("transactionManagertb")
public class MRHKTBDaoImpl extends AbstractReadOnlyDaoImpl<MRHKTB> implements
		MRHKTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKTB> getAllMRHKTB() {
		String sql = "From MRHKTB";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKTB> resultList = query.getResultList();
		return resultList;
	}

}
