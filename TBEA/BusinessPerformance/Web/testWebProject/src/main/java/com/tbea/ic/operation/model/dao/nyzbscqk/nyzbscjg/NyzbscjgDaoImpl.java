package com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscjg;


import com.tbea.ic.operation.model.entity.nyzbscqk.NyzbscjgEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscjg.NyzbscjgDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(NyzbscjgDaoImpl.NAME)
@Transactional("transactionManager")
public class NyzbscjgDaoImpl extends AbstractReadWriteDaoImpl<NyzbscjgEntity> implements NyzbscjgDao {
	public final static String NAME = "NyzbscjgDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
