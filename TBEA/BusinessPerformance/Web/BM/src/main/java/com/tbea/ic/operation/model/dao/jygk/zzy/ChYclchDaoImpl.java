package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyChYclch;
@Repository
@Transactional("transactionManager")
public class ChYclchDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyChYclch> implements ChYclchDao{
		
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	//单个指标数据
	public List<Object[]> getViewDataList(String year, String month, String dwxxid) {
		String sql = "select zzyflId, sum(jzydkcje), sum(nckcje)";
		sql += " from JygkZzyChYclch where nf = ";
		sql += year;
		sql += " and yf = ";
		sql += month;
		sql += " and dwid in (";
		sql += dwxxid;
		sql += ") group by zzyflId";
		Query q = this.getEntityManager().createQuery(sql);
		List<Object[]> list = q.getResultList();
		return list;
	}
}
