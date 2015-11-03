package com.tbea.ic.operation.model.dao.ysdaily;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional("transactionManager")
public class YSDAILYDaoImpl implements YSDAILYDao{

	@PersistenceContext(unitName = "localDB")
	EntityManager entityManager;

}
