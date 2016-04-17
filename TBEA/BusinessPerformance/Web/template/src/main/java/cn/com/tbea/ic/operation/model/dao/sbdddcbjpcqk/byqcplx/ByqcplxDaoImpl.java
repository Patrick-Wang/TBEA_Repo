package com.tbea.ic.operation.model.dao.sbdddcbjpcqk.byqcplx;


import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.byqcplx.ByqcplxDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ByqcplxDaoImpl.NAME)
@Transactional("transactionManager")
public class ByqcplxDaoImpl implements ByqcplxDao {
	public final static String NAME = "ByqcplxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	EntityManager entityManager;
}
