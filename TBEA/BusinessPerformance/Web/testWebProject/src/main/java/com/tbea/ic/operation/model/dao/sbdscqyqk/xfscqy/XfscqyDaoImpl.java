package com.tbea.ic.operation.model.dao.sbdscqyqk.xfscqy;


import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfscqyEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.sbdscqyqk.xfscqy.XfscqyDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(XfscqyDaoImpl.NAME)
@Transactional("transactionManager")
public class XfscqyDaoImpl extends AbstractReadWriteDaoImpl<XfscqyEntity> implements XfscqyDao {
	public final static String NAME = "XfscqyDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<XfscqyEntity> getByDate(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XfscqyEntity getByDate(Date d, Company company, int hy) {
		// TODO Auto-generated method stub
		return null;
	}
}
