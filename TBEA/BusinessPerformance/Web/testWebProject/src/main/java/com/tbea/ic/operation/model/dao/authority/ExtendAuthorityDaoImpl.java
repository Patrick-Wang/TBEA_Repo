package com.tbea.ic.operation.model.dao.authority;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.ExtendAuthority;

@Repository
@Transactional("transactionManager")
public class ExtendAuthorityDaoImpl extends AbstractReadWriteDaoImpl<ExtendAuthority> implements ExtendAuthorityDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
}
