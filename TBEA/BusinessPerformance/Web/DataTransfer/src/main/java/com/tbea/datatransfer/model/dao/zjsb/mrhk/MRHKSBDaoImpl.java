package com.tbea.datatransfer.model.dao.zjsb.mrhk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjsb.MRHKSB;
import com.tbea.datatransfer.model.entity.zjtb.MRHKTB;

@Transactional("transactionManagersb")
public class MRHKSBDaoImpl extends AbstractReadOnlyDaoImpl<MRHKSB> implements
		MRHKSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKSB> getAllMRHKSB() {
		String sql = "From MRHKSB";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKSB> resultList = query.getResultList();
		return resultList;
	}

}
