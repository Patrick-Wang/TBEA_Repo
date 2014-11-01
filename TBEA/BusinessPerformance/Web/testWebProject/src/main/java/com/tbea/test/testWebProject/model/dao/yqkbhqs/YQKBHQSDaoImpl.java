package com.tbea.test.testWebProject.model.dao.yqkbhqs;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.entity.CQK;
import com.tbea.test.testWebProject.model.entity.YQKBHQS;

@Repository
@Transactional("transactionManager2")
public class YQKBHQSDaoImpl extends AbstractReadWriteDaoImpl<YQKBHQS> implements
		YQKBHQSDao {

	@Override
	@PersistenceContext(unitName = "15DB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YQKBHQS> getYqkbhqsOfThisYear(Calendar cur) {
		Query q = getEntityManager().createQuery(
				"select y from YQKBHQS y where y.ny >= ?1 and y.ny <= ?2");
		Calendar yearBegin = Calendar.getInstance();
		yearBegin.set(cur.get(Calendar.YEAR), 0, 1);
		Calendar yearEnd = Calendar.getInstance();
		yearEnd.set(cur.get(Calendar.YEAR), 11, 1);
		String timeBegin = Util.format(yearBegin.getTime());
		String timeEnd = Util.format(yearEnd.getTime());
		q.setParameter(1, timeBegin);
		q.setParameter(2, timeEnd);
		return q.getResultList();
	}

}