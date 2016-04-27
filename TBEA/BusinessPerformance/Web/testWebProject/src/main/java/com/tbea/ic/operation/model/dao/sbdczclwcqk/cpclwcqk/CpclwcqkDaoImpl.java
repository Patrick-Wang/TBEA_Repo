package com.tbea.ic.operation.model.dao.sbdczclwcqk.cpclwcqk;


import com.tbea.ic.operation.model.entity.sbdczclwcqk.CpclwcqkEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpclwcqk.CpclwcqkDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(CpclwcqkDaoImpl.NAME)
@Transactional("transactionManager")
public class CpclwcqkDaoImpl extends AbstractReadWriteDaoImpl<CpclwcqkEntity> implements CpclwcqkDao {
	public final static String NAME = "CpclwcqkDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
