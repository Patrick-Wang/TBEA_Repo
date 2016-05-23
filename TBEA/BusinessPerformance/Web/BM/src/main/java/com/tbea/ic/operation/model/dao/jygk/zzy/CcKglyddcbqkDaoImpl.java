package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyCcKglyddcbqk;
@Repository
@Transactional("transactionManager")
public class CcKglyddcbqkDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyCcKglyddcbqk> implements CcKglyddcbqkDao{
		
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	//指标数据
	public Object[] getViewDataByq(String year, String month, String dwxxid) {
		String sql = "select yccnlcz, yccnlcl, kglyddzcz, kglyddzcl, ndkglyddzcz,";
		sql += " ndkglyddzcl,n1cz, n1cl, n2cz, n2cl, n3cz, n3cl, n4cz, n4cl,";
		sql += " n5cz, n5cl, n6cz, n6cl, n6hcz, n6hcl, ddcz, ddcl";
		sql += " from JygkZzyCcKglyddcbqk";
		sql += " where nf = ";
		sql += year;
		sql += " and yf = ";
		sql += month;
		sql += " and dwid in(";
		sql += dwxxid;
		sql += ")";
		
		Query q = this.getEntityManager().createQuery(sql);
		List<Object[]> list = q.getResultList();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	//指标数据
	public Object[] getViewDataXl(String year, String month, String dwxxid) {
		String sql = "select yccnlcz, kglyddzcz, ndkglyddzcz, n1cz, n1czn,";
		sql += " n2cz, n2czn, n3cz, n3czn, n3hcz, ddcz, wxcz";
		sql += " from JygkZzyCcKglyddcbqk";
		sql += " where nf = ";
		sql += year;
		sql += " and yf = ";
		sql += month;
		sql += " and dwid in(";
		sql += dwxxid;
		sql += ")";
		
		Query q = this.getEntityManager().createQuery(sql);
		List<Object[]> list = q.getResultList();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
}
