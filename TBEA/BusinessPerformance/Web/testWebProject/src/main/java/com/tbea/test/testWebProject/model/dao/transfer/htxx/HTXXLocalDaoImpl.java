package com.tbea.test.testWebProject.model.dao.transfer.htxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.model.entity.local.HTXXLocal;

//@Repository
@Transactional("transactionManager")
public class HTXXLocalDaoImpl extends AbstractReadWriteDaoImpl<HTXXLocal> implements
		HTXXLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HTXXLocal> getAllHTXXLocal() {
		String sql = "From HTXXLocal";
		Query query = getEntityManager().createQuery(sql);
		List<HTXXLocal> resultList = query.getResultList();
		return resultList;
	}

}
