package com.tbea.ic.operation.model.dao.jygk.yjzbzt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;

@Repository
@Transactional("transactionManager")
public class YDZBZTDaoImpl extends AbstractReadWriteDaoImpl<YDZBZT> implements YDZBZTDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	
	@Override
	public YDZBZT getYdzbzt(Company comp, int nf, int yf) {
		Query q = this.getEntityManager().createQuery("from YDZBZT where nf = :nf and yf = :yf and dwxx.id = :comp");
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		q.setParameter("comp", comp.getId());
		List<YDZBZT> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}

}
