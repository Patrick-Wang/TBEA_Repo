package com.tbea.ic.operation.model.dao.sbdczclwcqk.clylwcqk;


import com.tbea.ic.operation.model.entity.sbdczclwcqk.ClylwcqkEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.clylwcqk.ClylwcqkDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ClylwcqkDaoImpl.NAME)
@Transactional("transactionManager")
public class ClylwcqkDaoImpl extends AbstractReadWriteDaoImpl<ClylwcqkEntity> implements ClylwcqkDao {
	public final static String NAME = "ClylwcqkDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
