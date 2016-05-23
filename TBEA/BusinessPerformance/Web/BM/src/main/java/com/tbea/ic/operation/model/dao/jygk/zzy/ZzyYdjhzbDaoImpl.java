package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.YDJHZB;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class ZzyYdjhzbDaoImpl extends AbstractReadWriteDaoImpl<YDJHZB> implements ZzyYdjhzbDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	@Override
	public List<YDJHZB> getDataListByDwDate(String dwxxs,String zbidstrs,int nf,int yf) {		
		Query q = this.getEntityManager().createQuery("select zbxx.id,sum(ydjhz) as ydjhz from YDJHZB where dwxx.id in (" + dwxxs + ") and nf = :nf and yf = :yf and zbxx.id in (" + zbidstrs + ")  group by zbxx.id");
		q.setParameter("nf", nf);
		q.setParameter("yf", yf);
		List<Object[]> objectList=q.getResultList();
		List<YDJHZB> retList=new ArrayList<YDJHZB>();
		for(Object[] oa:objectList){
			YDJHZB j=new YDJHZB();
			ZBXX zbxx=new ZBXX();
			zbxx.setId((int)oa[0]);
			j.setZbxx(zbxx);			
			j.setYdjhz((Double)oa[1]);			
			retList.add(j);
		}
		return retList;
	}
}
