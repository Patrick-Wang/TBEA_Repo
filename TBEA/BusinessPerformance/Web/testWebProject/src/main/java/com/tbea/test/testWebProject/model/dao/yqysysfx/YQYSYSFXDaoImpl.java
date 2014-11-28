package com.tbea.test.testWebProject.model.dao.yqysysfx;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.model.entity.YQYSYSFX;
import com.tbea.test.testWebProject.model.entity.local.YQK;

@Repository
@Transactional("transactionManager")
public class YQYSYSFXDaoImpl extends AbstractReadWriteDaoImpl<YQYSYSFX> implements YQYSYSFXDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<YQYSYSFX> getYqysysfxList(Date d) {
		Query q = this.getEntityManager().createQuery("select y from YQYSYSFX y");
		return q.getResultList();
	}

}
