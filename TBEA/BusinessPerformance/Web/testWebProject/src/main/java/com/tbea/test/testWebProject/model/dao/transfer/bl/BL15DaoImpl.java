package com.tbea.test.testWebProject.model.dao.transfer.bl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.test.testWebProject.model.entity.yszk15.BL15;

//@Repository
@Transactional("transactionManager2")
public class BL15DaoImpl extends AbstractReadOnlyDaoImpl<BL15> implements
		BL15Dao {

	@Override
	@PersistenceContext(unitName = "15DB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BL15> getAllBL15() {
		String sql = "From BL15";
		Query query = getEntityManager().createQuery(sql);
		List<BL15> resultList = query.getResultList();
		return resultList;
	}

}
