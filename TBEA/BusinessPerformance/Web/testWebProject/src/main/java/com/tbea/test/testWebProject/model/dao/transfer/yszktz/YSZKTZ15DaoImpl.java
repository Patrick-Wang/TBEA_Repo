package com.tbea.test.testWebProject.model.dao.transfer.yszktz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.test.testWebProject.model.entity.yszk15.YSZKTZ15;

@Transactional("transactionManager2")
public class YSZKTZ15DaoImpl extends AbstractReadOnlyDaoImpl<YSZKTZ15>
		implements YSZKTZ15Dao {

	@Override
	@PersistenceContext(unitName = "15DB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKTZ15> getAllYSZKTZ15() {
		String sql = "From YSZKTZ15 Where DateDiff(dd,gxrq,getDate())<=2";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKTZ15> resultList = query.getResultList();
		return resultList;
	}

}
