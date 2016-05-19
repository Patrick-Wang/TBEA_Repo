package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbJsjb;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbXsjb;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class FxJkcbXsjbDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxJkcbXsjb> implements FxJkcbXsjbDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<Object[]> getViewDataList(String year, String month, String dwxxid) {
		String sql = "select tc,tpzbz,yhfkfs,qxkhzd,qt from JygkZzyFxJkcbXsjb where nf = ";
		sql += year;
		sql += " and yf = ";
		sql += month;
		sql += " and dwid in(";
		sql += dwxxid;
		sql += ")";
		Query q = this.getEntityManager().createQuery(sql);
		List<Object[]> list = q.getResultList();
		return list;
	}
}
