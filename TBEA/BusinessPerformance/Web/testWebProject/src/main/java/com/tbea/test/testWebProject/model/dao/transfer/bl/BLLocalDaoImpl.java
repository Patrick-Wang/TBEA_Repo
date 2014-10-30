package com.tbea.test.testWebProject.model.dao.transfer.bl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.model.entity.local.BLLocal;

//@Repository
@Transactional("transactionManager")
public class BLLocalDaoImpl extends AbstractReadWriteDaoImpl<BLLocal> implements
		BLLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BLLocal> getAllBLLocal() {
		String sql = "From BLLocal";
		Query query = getEntityManager().createQuery(sql);
		List<BLLocal> resultList = query.getResultList();
		return resultList;
	}

}
