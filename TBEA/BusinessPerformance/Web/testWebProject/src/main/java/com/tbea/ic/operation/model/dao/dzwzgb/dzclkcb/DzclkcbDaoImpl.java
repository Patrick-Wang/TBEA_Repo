package com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb;


import com.tbea.ic.operation.model.entity.dzwzgb.DzclkcbEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb.DzclkcbDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(DzclkcbDaoImpl.NAME)
@Transactional("transactionManager")
public class DzclkcbDaoImpl extends AbstractReadWriteDaoImpl<DzclkcbEntity> implements DzclkcbDao {
	public final static String NAME = "DzclkcbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
