package com.tbea.ic.operation.model.dao.cwgbjyxxjl.jyxxjl;


import com.tbea.ic.operation.model.entity.cwgbjyxxjl.JyxxjlEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.cwgbjyxxjl.jyxxjl.jyxxjlDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(jyxxjlDaoImpl.NAME)
@Transactional("transactionManager")
public class jyxxjlDaoImpl extends AbstractReadWriteDaoImpl<JyxxjlEntity> implements jyxxjlDao {
	public final static String NAME = "jyxxjlDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
