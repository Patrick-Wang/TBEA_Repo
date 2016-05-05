package com.tbea.ic.operation.model.dao.cwgb.jyxxjl;


import com.tbea.ic.operation.model.entity.cwgb.JyxxjlEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.cwgb.jyxxjl.JyxxjlDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(JyxxjlDaoImpl.NAME)
@Transactional("transactionManager")
public class JyxxjlDaoImpl extends AbstractReadWriteDaoImpl<JyxxjlEntity> implements JyxxjlDao {
	public final static String NAME = "JyxxjlDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
