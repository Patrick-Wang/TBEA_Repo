package com.tbea.ic.operation.model.dao.nc;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.NCZB;

@Repository
@Transactional("transactionManager")
public class NCZBDaoImpl extends AbstractReadWriteDaoImpl<NCZB> implements
		NCZBDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public NCZB getNCZB(Company company, int zbid, int nf, int yf) {
		NCZB nczb = null;
		String sql = "From NCZB Where dwxx.id = :dwid and zbxx.id = :zbid"
				+ " and nf = :nf and yf = :yf";
		Query q = this.getEntityManager().createQuery(sql);
		q.setParameter("dwid", company.getId());
		q.setParameter("zbid", zbid);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		try {
			nczb = (NCZB) q.getSingleResult();
		} catch (NoResultException e) {
			nczb = null;
		}
		return nczb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NCZB> getNCZBByDate(int nf, int yf) {
		String sql = "From NCZB Where nf = :nf and yf = :yf";
		Query q = this.getEntityManager().createQuery(sql);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<NCZB> resultList = null;
		try {
			resultList = q.getResultList();
		} catch (Exception e) {
			resultList = new ArrayList<NCZB>();
		}
		return resultList;
	}

}
