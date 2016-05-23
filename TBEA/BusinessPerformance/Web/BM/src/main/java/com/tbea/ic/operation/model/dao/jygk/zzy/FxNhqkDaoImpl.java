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

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyBglx;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbJsjb;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbZtnhqk;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class FxNhqkDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxJkcbZtnhqk> implements FxNhqkDao{
		
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	//录入的表格列表
	public List<JygkZzyBglx> getCksjBgList() {
		Query q = this.getEntityManager().createQuery("from JygkZzyBglx where id>=20000 order by id");
		return q.getResultList();
	}
	
	@Override
	//单个指标数据
	public Object[] getZb(Integer zb, Date date, int company, int bglx) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Object[] result = new Object[1];
			Query q = this.getEntityManager().createQuery(""/*querySql.get(String.valueOf(bglx))*/);
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
		if(!("20009".equals(bglx) || "20010".equals(bglx) || "20011".equals(bglx) || "20015".equals(bglx) || "20016".equals(bglx) || "20017".equals(bglx) || "20018".equals(bglx))){
			sql += "zzyfl_id = :id and ";
		}
		sql += "nf = :nf and ";
		if("20002".equals(bglx) || "20003".equals(bglx)){
			sql += "jd";
		} else {
			sql += "yf";
		}
		sql += " = :yf and dwid = :comp";
		Query q = this.getEntityManager().createQuery(sql);
		if(!("20009".equals(bglx) || "20010".equals(bglx) || "20011".equals(bglx) || "20015".equals(bglx) || "20016".equals(bglx) || "20017".equals(bglx) || "20018".equals(bglx))){
			q.setParameter("id", zb);
		}
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
	//取指定表格类型可以录入的单位数据
	public List<Object[]> getBgCompanies(String bglx) {
		Query q = this.getEntityManager().createQuery("select distinct dw.id,dw.name,dw.parent from DWXX dw,JygkZzyDwReferBglx bg where dw.id=bg.dwid and bg.wvtype='01' order by dw.id");
//		q.setParameter("bglxid", Integer.parseInt(bglx) - 10000);
		return q.getResultList();
	}
	
	@Override
	//取单位数据
	public Object[] getBgCompanie(String id) {
		Query q = this.getEntityManager().createQuery("select dw.id,dw.name,dw.parent from DWXX dw where dw.id=:id");
		q.setParameter("id", Integer.parseInt(id));
		List< Object[]> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
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
