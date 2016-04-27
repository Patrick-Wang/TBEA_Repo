package com.tbea.ic.operation.model.dao.identifier.cwgb.cpfl;


import java.util.List;

import com.tbea.ic.operation.model.entity.identifier.cwgb.CpflEntity;
import com.tbea.ic.operation.model.entity.identifier.cwgb.CyEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.identifier.cwgb.cpfl.CpflDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(CpflDaoImpl.NAME)
@Transactional("transactionManager")
public class CpflDaoImpl extends AbstractReadWriteDaoImpl<CpflEntity> implements CpflDao {
	public final static String NAME = "CpflDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<CyEntity> getByCy(int ordinal) {
		// TODO Auto-generated method stub
		return null;
	}
}
