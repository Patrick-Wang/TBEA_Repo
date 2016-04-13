package com.tbea.ic.operation.model.dao.sbdddcbjpcqk.kglydd;


import com.tbea.ic.operation.model.entity.sbdddcbjPcqk.KglyddEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.kglydd.KglyddDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(KglyddDaoImpl.NAME)
@Transactional("transactionManager")
public class KglyddDaoImpl extends AbstractReadWriteDaoImpl<KglyddEntity> implements KglyddDao {
	public final static String NAME = "KglyddDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
