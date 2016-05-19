package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyChChjgjnh;
@Repository
@Transactional("transactionManager")
public class ChChjgjnhDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyChChjgjnh> implements ChChjgjnhDao{
	
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	//标数据
	public Object[] getViewData(String year, String month, String dwxxid) {
		String sql = "select sum(ycl) as ycl, sum(bcp) as bcp, sum(sjkc) as sjkc,";
		sql += " sum(yfhwkfp) as yfhwkfp, sum(qhfdy) as qhfdy, sum(qhfdk) as qhfdk,";
		sql += " sum(wkhykp) as wkhykp, sum(qt) as qt, sum(hj) as hj";
		sql += " from JygkZzyChChjgjnh where nf = ";
		sql += year;
		sql += " and yf = ";
		sql += month;
		sql += " and dwid in (";
		sql += dwxxid;
		sql += ") group by nf,yf";
		Query q = this.getEntityManager().createQuery(sql);
		List<Object[]> list = q.getResultList();
		if (!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
}
