package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyBglx;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyChZljj;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbJsjb;
@Repository
@Transactional("transactionManager")
public class ChZljjDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyChZljj> implements ChZljjDao{
		
	static HashMap<String, String> querySql;
//	static HashMap 
	static{
		querySql = new HashMap<String, String>();
		querySql.put("20018", "select n5sycl,n5sbcp,n5sccp,n5sqt,n4z5ycl,n4z5bcp,n4z5ccp,n4z5qt,n3z4ycl,n3z4bcp,n3z4ccp,n3z4qt,n2z3ycl,n2z3bcp,n2z3ccp,n2z3qt,n1z2ycl,n1z2bcp,n1z2ccp,n1z2qt,n1ycl,n1bcp,n1ccp,n1qt,hj "
				+ "from JygkZzyChZljj where nf = :nf and yf = :yf and dwid = :comp");
	}
	
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	
	@Override
	//单个指标数据
	public Object[] getZb(Integer zb, Date date, int company, int bglx) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Query q = this.getEntityManager().createQuery(querySql.get(String.valueOf(bglx)));
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company);
		List<Object[]> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}
	
	@Override
	public Object getZbObject(Integer zb, Date date, int company, String classname, String bglx) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String sql = "from " + classname + " where ";
		sql += "zzyfl_id = :id and ";
		
		sql += "nf = :nf and ";
		sql += "yf";
		sql += " = :yf and dwid = :comp";
		Query q = this.getEntityManager().createQuery(sql);
		q.setParameter("id", zb);
		q.setParameter("nf", cal.get(Calendar.YEAR));
		q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
		q.setParameter("comp", company);
		List zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}
	
	@Override
	//根据单位和表格类型取表格分类数据
	public List<JygkZzyFl> getZbfl(Company company, int bglx) {
		Query q = this.getEntityManager().createQuery("select fl.id,fl.name,fl.viewname from JygkZzyFl fl,JygkZzyDwReferBglxfl bg where fl.id=bg.jygkZzyFl.id and bg.dwid=:dwid and bg.bglxid=:bglxid order by bg.sx");
		q.setParameter("dwid", company.getId());
		q.setParameter("bglxid", bglx - 10000);
		return q.getResultList();
	}
	
	@Override
	//根据分类ID取分类指标
	public JygkZzyFl getZbfl(int flid) {
		Query q = this.getEntityManager().createQuery("from JygkZzyFl where id=:flid");
		q.setParameter("flid", flid);
		List<JygkZzyFl> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}

	@Override
	//根据单位和表格类型取表格类型分类
	public List<JygkZzyDwReferBglxfl> getDwReferBglxfl(int company, int bglx) {
		Query q = this.getEntityManager().createQuery("from JygkZzyDwReferBglxfl where bglxid=:bglx and dwid = :comp order by sx");
		q.setParameter("comp", company);
		q.setParameter("bglx", bglx - 10000);
		return q.getResultList();
	}
}
