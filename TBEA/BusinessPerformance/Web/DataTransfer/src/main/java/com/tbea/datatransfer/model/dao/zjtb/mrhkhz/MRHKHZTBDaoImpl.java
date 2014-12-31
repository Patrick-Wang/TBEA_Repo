package com.tbea.datatransfer.model.dao.zjtb.mrhkhz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjtb.MRHKHZTB;

@Transactional("transactionManagertb2")
public class MRHKHZTBDaoImpl extends AbstractReadOnlyDaoImpl<MRHKHZTB>
		implements MRHKHZTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MRHKHZTB> getAllMRHKHZTB() {
		String sql = "From MRHKHZTB";
		Query query = getEntityManager().createQuery(sql);
		List<MRHKHZTB> resultList = query.getResultList();
		return resultList;
	}

}
