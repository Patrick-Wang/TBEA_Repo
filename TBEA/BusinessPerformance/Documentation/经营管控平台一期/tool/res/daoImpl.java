package dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository([className].NAME)
@Transactional("transactionManager")
public class daoImpl implements AccountDao {
	public final static String NAME = "[className]";

	@PersistenceContext(unitName = "localDB")
	EntityManager entityManager;
}
