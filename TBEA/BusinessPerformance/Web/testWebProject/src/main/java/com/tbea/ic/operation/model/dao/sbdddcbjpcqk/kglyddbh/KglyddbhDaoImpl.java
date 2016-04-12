package com.tbea.ic.operation.model.dao.sbdddcbjpcqk.kglyddbh;


import com.tbea.ic.operation.model.entity.sbdddcbjPcqk.KglyddbhEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.kglyddbh.KglyddbhDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(KglyddbhDaoImpl.NAME)
@Transactional("transactionManager")
public class KglyddbhDaoImpl extends AbstractReadWriteDaoImpl<KglyddbhEntity> implements KglyddbhDao {
	public final static String NAME = "KglyddbhDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
