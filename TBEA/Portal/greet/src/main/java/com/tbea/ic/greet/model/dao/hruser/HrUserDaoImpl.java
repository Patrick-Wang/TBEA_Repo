package com.tbea.ic.greet.model.dao.hruser;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.greet.model.entity.HrUser;

@Repository
@Transactional("hrTransactionManager")
public class HrUserDaoImpl implements HrUserDao {

	@PersistenceContext(unitName = "hrEntityManagerFactory")
	EntityManager entityManager;
	
	public HrUser getByName(String name) {
		Query q =entityManager.createNativeQuery("SELECT right(id,8) as psw ,BASGROUPDEF16 FROM V_compsn where BASGROUPDEF16=:name and psnclcod != 10301");
		q.setParameter("name", name);
		List<Object[]> accounts = q.getResultList();
		if (accounts.isEmpty()){
			return null;
		}else{
			return new HrUser(name, accounts.get(0)[0].toString());
		}
	}

}
