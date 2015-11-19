package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.SJZB;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class ZzySjzbDaoImpl extends AbstractReadWriteDaoImpl<SJZB> implements ZzySjzbDao{
		
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	@Override
	public List<SJZB> getDataListByDwDate(int dwxxId,String zbidstrs,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("from SJZB where dwxx.id = :dwid and nf = :nf and yf = :yf and zbxx.id in (" + zbidstrs + ")");
		q.setParameter("dwid", dwxxId);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		return q.getResultList();
	}
	@Override
	public SJZB readDataByDwFlData(int dwxxId,int zbid,int nf,int yf){		
		Query q = this.getEntityManager().createQuery("from SJZB where dwxx.id = :dwid and nf = :nf and yf = :yf and zbxx.id=:zbid");
		q.setParameter("dwid", dwxxId);
		q.setParameter("zbid", zbid);
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<SJZB> SJZBList = q.getResultList();
		if (!SJZBList.isEmpty()){
			return SJZBList.get(0);
		}
		return null;
	}
	@Override
	public List<SJZB> readDataLjByDwFlData(int dwxxId,int zbid,int nf,int yf){		
		Query q = this.getEntityManager().createQuery("from SJZB where dwxx.id = :dwid and nf = :nf and zbxx.id=:zbid and yf>=1 and yf<="+yf);
		q.setParameter("dwid", dwxxId);
		q.setParameter("zbid", zbid);
		q.setParameter("nf", nf);		
		return q.getResultList();
	}
	@Override
	public List<SJZB> readDataQnByDwFlData(int dwxxId,int zbid,int nf){		
		Query q = this.getEntityManager().createQuery("from SJZB where dwxx.id = :dwid and nf = :nf and zbxx.id=:zbid");
		q.setParameter("dwid", dwxxId);
		q.setParameter("zbid", zbid);
		q.setParameter("nf", nf);
		return q.getResultList();
	}
}
