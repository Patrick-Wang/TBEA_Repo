package com.tbea.ic.operation.model.dao.hkjhjgb;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.HKJHJG;
import com.tbea.ic.operation.model.entity.QYZJK;
import com.tbea.ic.operation.model.entity.XLNWFKFS;
import com.tbea.ic.operation.model.entity.local.CQK;


@Repository
@Transactional("transactionManager")
public class HKJHJGDaoImpl  extends AbstractReadWriteDaoImpl<QYZJK> implements HKJHJGDao{


	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<HKJHJG> getHkjhjg(Date d, Company comp) {
		Query q = getEntityManager().createQuery("select h from HKJHJG h where h.qybh = :compId and h.ny = :ny");
		q.setParameter("compId", comp.getId());
		q.setParameter("ny", Util.format(d));
		return q.getResultList();
	}

	@Override
	public HKJHJG getLatestHkjg() {
		Query q = getEntityManager().createQuery(
				"from HKJHJG order by ny desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<HKJHJG> hkjhs = q.getResultList();
		if (!hkjhs.isEmpty()){
			return hkjhs.get(0);
		}
		return null;
	}

}
