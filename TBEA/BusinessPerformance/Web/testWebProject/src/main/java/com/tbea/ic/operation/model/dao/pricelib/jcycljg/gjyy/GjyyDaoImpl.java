package com.tbea.ic.operation.model.dao.pricelib.jcycljg.gjyy;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;



@Repository(GjyyDaoImpl.NAME)
@Transactional("transactionManager")
public class GjyyDaoImpl extends AbstractReadWriteDaoImpl<GjyyEntity> implements GjyyDao {
	public final static String NAME = "GjyyDaoImpl";

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
