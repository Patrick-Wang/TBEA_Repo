package com.tbea.datatransfer.model.dao.zjbyq.byqtb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.BYQTB;

@Transactional("transactionManagerxb")
public class BYQTBXBDaoImpl extends AbstractReadOnlyDaoImpl<BYQTB> implements
		BYQTBDao {

	@Override
	@PersistenceContext(unitName = "xbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BYQTB> getAllBYQTB() {
		String sql = "From BYQTB";
		Query query = getEntityManager().createQuery(sql);
		List<BYQTB> resultList = query.getResultList();
		return resultList;
	}

}
