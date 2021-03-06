package com.tbea.ic.operation.model.dao.yszkpzjh;

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
import com.tbea.ic.operation.model.entity.YSZKPZGH;

@Repository
@Transactional("transactionManager")
public class YSZKPZJHDaoImpl extends AbstractReadWriteDaoImpl<YSZKPZGH> implements YSZKPZJHDao{

	
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	public List<YSZKPZGH> getPzjhData(Date d, Company comp) {
		Query q = this.getEntityManager().createQuery("select y from YSZKPZGH y where DateDiff(mm, y.gxrq, :date) = 0 and y.gsbm = :comp");
		q.setParameter("date", d);
		q.setParameter("comp", "0" + comp.getId());
		return q.getResultList();
	}

	@Override
	public YSZKPZGH getLatestDate() {
		Query q = getEntityManager().createQuery(
				"from YSZKPZGH order by gxrq desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<YSZKPZGH> yszk = q.getResultList();
		if (!yszk.isEmpty()){
			return yszk.get(0);
		}
		return null;
	}
}
