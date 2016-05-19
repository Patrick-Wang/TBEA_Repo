package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyChZljj;
@Repository
@Transactional("transactionManager")
public class ChZljjDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyChZljj> implements ChZljjDao{
		
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	//单个指标数据
	public Object[] getViewData(String year, String month, String dwxxid) {
		String sql = "select sum(n5sycl), sum(n5sbcp), sum(n5sccp), sum(n5sqt),";
		sql += " sum(n4z5ycl), sum(n4z5bcp), sum(n4z5ccp), sum(n4z5qt), sum(n3z4ycl),";
		sql += " sum(n3z4bcp), sum(n3z4ccp), sum(n3z4qt), sum(n2z3ycl), sum(n2z3bcp),";
		sql += " sum(n2z3ccp), sum(n2z3qt), sum(n1z2ycl), sum(n1z2bcp), sum(n1z2ccp),";
		sql += " sum(n1z2qt), sum(n1ycl), sum(n1bcp), sum(n1ccp), sum(n1qt), sum(hj) ";
		sql += " from JygkZzyChZljj where nf = ";
		sql += year;
		sql += " and yf = ";
		sql += month;
		sql += " and dwid in (";
		sql += dwxxid;
		sql += ") group by nf,yf";
		Query q = this.getEntityManager().createQuery(sql);
		List<Object[]> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}
}
