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
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyCcKglyddcbqk;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFl;
@Repository
@Transactional("transactionManager")
public class CcKglyddcbqkDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyCcKglyddcbqk> implements CcKglyddcbqkDao{
		
	static HashMap<String, String> querySql;
//	static HashMap 
	static{
		querySql = new HashMap<String, String>();
		querySql.put("20015", "select yccnlcz,yccnlcl,kglyddzcz,kglyddzcl,ndkglyddzcz,ndkglyddzcl,n1cz,n1cl,n2cz,n2cl,n3cz,n3cl,n4cz,n4cl,n5cz,n5cl,n6cz,n6cl,n6hcz,n6hcl,ddcz,ddcl,wxcz,wxcn "
				+ "from JygkZzyCcKglyddcbqk where nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("20016", "select yccnlcz,kglyddzcz,ndkglyddzcz,n1cz,n1czn,n2cz,n2czn,n3cz,n3czn,n3hcz,ddcz,wxcz "
				+ "from JygkZzyCcKglyddcbqk where nf = :nf and yf = :yf and dwid = :comp");
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
}
