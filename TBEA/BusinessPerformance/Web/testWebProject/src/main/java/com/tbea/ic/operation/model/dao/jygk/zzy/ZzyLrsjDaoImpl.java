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

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class ZzyLrsjDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyFxJkcbJsjb> implements ZzyLrsjDao{
		
	static HashMap<String, String> querySql;
//	static HashMap 
	static{
		querySql = new HashMap<String, String>();
		querySql.put("10001", "select sr,mle from JygkZzyFxCpylspDqddmlqk where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10002", "select cz,cl,zbmll,yjyhhmle,yjyhhmll from JygkZzyFxCpylspHqlyddzl where zzyfl_id = :id and nf = :nf and jd = :yf and dwid = :comp");
		querySql.put("10003", "select cz,cl,zbmll,yjyhhmle,yjyhhmll from JygkZzyFxCpylspHqlyddzl where zzyfl_id = :id and nf = :nf and jd = :yf and dwid = :comp");
		querySql.put("10004", "select ndjh,ydjh,ydwc as e from JygkZzyFxJkcbZbwcqk where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10005", "select scts,yhts,jgcsyhjb,cltdjb,qtjb from JygkZzyFxJkcbJsjb where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10006", "select scts,yhts,jgcsyhjb,cltdjb,qtjb from JygkZzyFxJkcbJsjb where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10007", "select ndjh,ydjh,ydwc from JygkZzyFxJkcbCgjb where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10008", "select sjlyl,fl from JygkZzyFxJkcbScjb where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10009", "select syl,sje,dyl,dje,zqyl,zqje,rqyl,rqje from JygkZzyFxJkcbZtnhqk where nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10010", "select syl,sje,dyl,dje,zqyl,zqje,rqyl,rqje from JygkZzyFxJkcbZtnhqk where nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10011", "select tc,tpzbz,yhfkfs,qxkhzd,qt from JygkZzyFxJkcbXsjb where nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10012", "select cl,cz from JygkZzyCcCcwcqk where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10013", "select cl,cz from JygkZzyCcCcwcqk where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10014", "select gs,gs as e from JygkZzyCcCcwcqkGs where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10015", "select yccnlcz,yccnlcl,kglyddzcz,kglyddzcl,ndkglyddzcz,ndkglyddzcl,n1cz,n1cl,n2cz,n2cl,n3cz,n3cl,n4cz,n4cl,n5cz,n5cl,n6cz,n6cl,n6hcz,n6hcl,ddcz,ddcl "
				+ "from JygkZzyCcKglyddcbqk where nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10016", "select yccnlcz,kglyddzcz,ndkglyddzcz,n1cz,n1czn,n2cz,n2czn,n3cz,n3czn,n3hcz,ddcz,wxcz "
				+ "from JygkZzyCcKglyddcbqk where nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10017", "select ycl,bcp,sjkc,yfhwkfp,qhfdy,qhfdk,wkhykp,qt,hj from JygkZzyChChjgjnh where nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10018", "select n5sycl,n5sbcp,n5sccp,n5sqt,n4z5ycl,n4z5bcp,n4z5ccp,n4z5qt,n3z4ycl,n3z4bcp,n3z4ccp,n3z4qt,n2z3ycl,n2z3bcp,n2z3ccp,n2z3qt,n1z2ycl,n1z2bcp,n1z2ccp,n1z2qt,n1ycl,n1bcp,n1ccp,n1qt,hj "
				+ "from JygkZzyChZljj where nf = :nf and yf = :yf and dwid = :comp");
		querySql.put("10019", "select jzydkcje,nckcje from JygkZzyChYclch where zzyfl_id = :id and nf = :nf and yf = :yf and dwid = :comp");
	}
	
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	@Override
	//录入的表格列表
	public List<JygkZzyBglx> getLrsjBgList() {
		Query q = this.getEntityManager().createQuery("from JygkZzyBglx where id<20000 order by id");
		return q.getResultList();
	}
	
	@Override
	//单个指标数据
	public Object[] getZb(Integer zb, Date date, int company, int bglx) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(bglx == 10009 || bglx == 10010 || bglx == 10011 || bglx == 10015 || bglx == 10016 || bglx == 10017 || bglx == 10018){
			Query q = this.getEntityManager().createQuery(querySql.get(String.valueOf(bglx)));
			q.setParameter("nf", cal.get(Calendar.YEAR));
			q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
			q.setParameter("comp", company);
			List<Object[]> zbs = q.getResultList();
			if (!zbs.isEmpty()){
				return zbs.get(0);
			}
		} else {
			Query q = this.getEntityManager().createQuery(querySql.get(String.valueOf(bglx)));
			q.setParameter("id", zb);
			q.setParameter("nf", cal.get(Calendar.YEAR));
			q.setParameter("yf", cal.get(Calendar.MONTH) + 1);
			q.setParameter("comp", company);
			List<Object[]> zbs = q.getResultList();
			if (!zbs.isEmpty()){
				return zbs.get(0);
			}
		}
		return null;
	}
	
	@Override
	public Object getZbObject(Integer zb, Date date, int company, String classname, String bglx) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String sql = "from " + classname + " where ";
		if(!("10009".equals(bglx) || "10010".equals(bglx) || "10011".equals(bglx) || "10015".equals(bglx) || "10016".equals(bglx) || "10017".equals(bglx) || "10018".equals(bglx))){
			sql += "zzyfl_id = :id and ";
		}
		sql += "nf = :nf and ";
		if("10002".equals(bglx) || "10003".equals(bglx)){
			sql += "jd";
		} else {
			sql += "yf";
		}
		sql += " = :yf and dwid = :comp";
		Query q = this.getEntityManager().createQuery(sql);
		if(!("10009".equals(bglx) || "10010".equals(bglx) || "10011".equals(bglx) || "10015".equals(bglx) || "10016".equals(bglx) || "10017".equals(bglx) || "10018".equals(bglx))){
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
		q.setParameter("bglxid", bglx);
		return q.getResultList();
	}
	
	@Override
	//取指定表格类型可以录入的单位数据
	public List<Object[]> getBgCompanies(String bglx) {
		Query q = this.getEntityManager().createQuery("select distinct dw.id,dw.name,dw.parent from DWXX dw,JygkZzyDwReferBglx bg where dw.id=bg.dwid and bg.wvtype='01' and bg.bglxid=:bglxid order by dw.id");
		q.setParameter("bglxid", Integer.parseInt(bglx));
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
		q.setParameter("bglx", bglx);
		return q.getResultList();
	}
}
