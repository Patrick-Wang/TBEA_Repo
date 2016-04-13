package com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm;


import com.tbea.ic.operation.model.entity.identifier.chgb.JykcxmEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm.JykcxmDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(JykcxmDaoImpl.NAME)
@Transactional("transactionManager")
public class JykcxmDaoImpl extends AbstractReadWriteDaoImpl<JykcxmEntity> implements JykcxmDao {
	public final static String NAME = "JykcxmDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
