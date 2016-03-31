package com.tbea.ic.operation.model.dao.pricelib.jcycljg.zhb;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.ZhbEntity;



@Repository(ZhbDaoImpl.NAME)
@Transactional("transactionManager")
public class ZhbDaoImpl extends AbstractReadWriteDaoImpl<ZhbEntity> implements ZhbDao {
	public final static String NAME = "ZhbDaoImpl";

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
