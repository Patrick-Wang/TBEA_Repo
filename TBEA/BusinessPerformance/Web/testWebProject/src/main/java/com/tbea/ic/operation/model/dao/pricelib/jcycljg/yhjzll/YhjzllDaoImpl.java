package com.tbea.ic.operation.model.dao.pricelib.jcycljg.yhjzll;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YhjzllEntity;



@Repository(YhjzllDaoImpl.NAME)
@Transactional("transactionManager")
public class YhjzllDaoImpl extends AbstractReadWriteDaoImpl<YhjzllEntity> implements YhjzllDao {
	public final static String NAME = "YhjzllDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<?> getEntities(Date start, Date end) {
		// TODO Auto-generated method stub
		return null;
	}
}
