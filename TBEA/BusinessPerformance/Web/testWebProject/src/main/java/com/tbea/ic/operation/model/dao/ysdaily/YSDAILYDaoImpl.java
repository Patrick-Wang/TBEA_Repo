package com.tbea.ic.operation.model.dao.ysdaily;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.YSDAILY;
import com.tbea.ic.operation.model.entity.jygk.YSDAILYPK;

@Repository
@Transactional("transactionManager")
public class YSDAILYDaoImpl implements YSDAILYDao{

	@PersistenceContext(unitName = "localDB")
	EntityManager entityManager;

	@Override
	public YSDAILY getYsdaily(Date date, DWXX dwxx) {
		YSDAILYPK ysdaily = new YSDAILYPK();
		ysdaily.setDwxx(dwxx);
		ysdaily.setDate(date);
		Query q = entityManager.createQuery("from YSDAILY where key = :key");
		q.setParameter("key", ysdaily);
		List<YSDAILYPK> yspks = q.getResultList();
		if (yspks.isEmpty()){
			yspks.get(0);
		}
		return null;
	}

	@Override
	public void update(YSDAILY daily) {
		entityManager.merge(daily);		
	}

}
