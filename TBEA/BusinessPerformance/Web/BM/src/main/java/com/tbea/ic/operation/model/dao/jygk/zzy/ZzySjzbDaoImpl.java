package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;

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
	public List<SJZB> getDataListByDwDate(String dwxxs,String zbidstrs,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("select zbxx.id,sum(sjz) as sjz from SJZB where dwxx.id in (" + dwxxs + ") and nf = :nf and yf = :yf and zbxx.id in (" + zbidstrs + ") group by zbxx.id");
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<Object[]> objectList=q.getResultList();
		List<SJZB> retList=new ArrayList<SJZB>();
		for(Object[] oa:objectList){
			SJZB j=new SJZB();
			ZBXX zbxx=new ZBXX();
			zbxx.setId((int)oa[0]);
			j.setZbxx(zbxx);			
			j.setSjz((Double)oa[1]);			
			retList.add(j);
		}
		return retList;
	}	
	
	@Override
	public List<SJZB> readDataLjByDwFlDate(String dwxxs,int zbid,int nf,int yf){		
		Query q = this.getEntityManager().createQuery("select zbxx.id,sum(sjz) as sjz from SJZB where dwxx.id in (" + dwxxs + ") and nf = :nf and yf>=1 and yf<="+yf + " and zbxx.id=:zbid  group by zbxx.id");
		q.setParameter("zbid", zbid);
		q.setParameter("nf", nf);		
		List<Object[]> objectList=q.getResultList();
		List<SJZB> retList=new ArrayList<SJZB>();
		for(Object[] oa:objectList){
			SJZB j=new SJZB();
			ZBXX zbxx=new ZBXX();
			zbxx.setId((int)oa[0]);
			j.setZbxx(zbxx);			
			j.setSjz((Double)oa[1]);			
			retList.add(j);
		}
		return retList;
	}
	
	@Override
	public SJZB readDataByDwFlDate(String dwxxs,int zbid,int nf,int yf){		
		Query q = this.getEntityManager().createQuery("select zbxx.id,sum(sjz) as sjz from SJZB where dwxx.id in (" + dwxxs + ") and nf = :nf and yf=:yf and zbxx.id=:zbid group by zbxx.id");
		q.setParameter("zbid", zbid);
		q.setParameter("nf", nf);	
		q.setParameter("yf", yf);
		List<Object[]> objectList = q.getResultList();
		if (!objectList.isEmpty()){
			Object[] oa=objectList.get(0);
			SJZB j=new SJZB();
			ZBXX zbxx=new ZBXX();
			zbxx.setId((int)oa[0]);
			j.setZbxx(zbxx);			
			j.setSjz((Double)oa[1]);
			return j;
		}
		return null;
	}
	
	@Override
	public List<SJZB> readDataQnByDwFlDate(String dwxxs,int zbid,int nf){		
		Query q = this.getEntityManager().createQuery("select zbxx.id,sum(sjz) as sjz from SJZB where dwxx.id in (" + dwxxs + ") and nf = :nf and zbxx.id=:zbid group by zbxx.id");
		q.setParameter("zbid", zbid);
		q.setParameter("nf", nf);
		List<Object[]> objectList=q.getResultList();
		List<SJZB> retList=new ArrayList<SJZB>();
		for(Object[] oa:objectList){
			SJZB j=new SJZB();
			ZBXX zbxx=new ZBXX();
			zbxx.setId((int)oa[0]);
			j.setZbxx(zbxx);			
			j.setSjz((Double)oa[1]);			
			retList.add(j);
		}
		return retList;
	}
}
