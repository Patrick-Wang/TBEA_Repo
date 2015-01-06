package com.tbea.test.testWebProject.model.dao.ztyszkfx;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.entity.XLNWFKFS;
import com.tbea.test.testWebProject.model.entity.ZTYSZKFX;

@Repository
@Transactional("transactionManager")
public class ZTYSZKFXDaoImpl  extends AbstractReadWriteDaoImpl<ZTYSZKFX> implements ZTYSZKFXDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<ZTYSZKFX> getZtyszkfxData(Date d) {
		Query q = this.getEntityManager().createQuery("select z from ZTYSZKFX z where DateDiff(mm, z.gxrq, :date) = 0");
		q.setParameter("date", d);
		return q.getResultList();
	}

	@Override
	public ZTYSZKFX getLatestYszk() {
		Query q = getEntityManager().createQuery(
				"from ZTYSZKFX order by gxrq desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<ZTYSZKFX> yszks = q.getResultList();
		if (!yszks.isEmpty()){
			return yszks.get(0);
		}
		return null;
	}
	
}
