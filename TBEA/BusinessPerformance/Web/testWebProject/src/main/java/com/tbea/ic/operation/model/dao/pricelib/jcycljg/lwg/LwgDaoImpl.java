package com.tbea.ic.operation.model.dao.pricelib.jcycljg.lwg;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.LwgEntity;



@Repository(LwgDaoImpl.NAME)
@Transactional("transactionManager")
public class LwgDaoImpl extends AbstractReadWriteDaoImpl<LwgEntity> implements LwgDao {
	public final static String NAME = "LwgDaoImpl";

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
