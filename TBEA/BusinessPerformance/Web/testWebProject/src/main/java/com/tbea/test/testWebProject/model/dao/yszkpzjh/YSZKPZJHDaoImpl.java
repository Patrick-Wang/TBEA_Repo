package com.tbea.test.testWebProject.model.dao.yszkpzjh;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.entity.TBBZJXX;
import com.tbea.test.testWebProject.model.entity.YSZKPZGH;

@Repository
@Transactional("transactionManager")
public class YSZKPZJHDaoImpl extends AbstractReadWriteDaoImpl<YSZKPZGH> implements YSZKPZJHDao{

	private static Map<String, Integer> compMap = new HashMap<String, Integer>();
	static {
		compMap.put(Company.get(Company.Type.SB).getId(), 1);
		compMap.put(Company.get(Company.Type.HB).getId(), 2);
		compMap.put(Company.get(Company.Type.XB).getId(), 3);

	}
	
	
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<YSZKPZGH> getPzjhData(Date d, Company comp) {
		Integer compId = compMap.get(comp.getId());
		if (null == compId){
			return new ArrayList<YSZKPZGH>();
		}
		else{
			Query q = this.getEntityManager().createQuery("select y from YSZKPZGH y where y.yf = :date and y.qybh = :comp");
			q.setParameter("date", Util.format(d));
			q.setParameter("comp", compId);
			return q.getResultList();
		}
		
	}

}
