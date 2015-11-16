package com.tbea.ic.greet.model.dao.cameluser;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.greet.model.entity.CamelUser;

@Repository
@Transactional("hrTransactionManager")
public class CamelUserDaoImpl implements CamelUserDao {

	@PersistenceContext(unitName = "hrEntityManagerFactory")
	EntityManager entityManager;
	
	public CamelUser getByName(String name) {
		Query q =entityManager.createNativeQuery("SELECT shortname, username, epassword FROM employee where shortname=:name or username = :name and status = 1");
		q.setParameter("name", name);
		List<Object[]> accounts = q.getResultList();
		if (accounts.isEmpty()){
			return null;
		}else{
			//String shortName = accounts.get(0)[0] == null ? null : accounts.get(0)[0].toString();
			//String userName = accounts.get(0)[1] == null ? null : accounts.get(0)[0].toString();
			return new CamelUser(String.valueOf(accounts.get(0)[0]), String.valueOf(accounts.get(0)[1]), accounts.get(0)[2].toString());
		}
	}

}
