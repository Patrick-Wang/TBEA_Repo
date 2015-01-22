package com.tbea.datatransfer.model.dao.zjsb.mrhkhz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjsb.MRHKHZSB;
import com.tbea.datatransfer.model.entity.zjtb.MRHKHZTB;

@Transactional("transactionManagersb")
public class MRHKHZSBDaoImpl extends AbstractReadOnlyDaoImpl<MRHKHZSB>
		implements MRHKHZSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKHZSB> getAllMRHKHZSB() {
		String sql = "From MRHKHZSB";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKHZSB> resultList = query.getResultList();
		return resultList;
	}

}
