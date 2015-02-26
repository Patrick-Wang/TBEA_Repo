package com.tbea.ic.operation.model.dao.ydzb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.entity.XJL;
import com.tbea.ic.operation.model.entity.XLNWFKFS;
import com.tbea.ic.operation.model.entity.YDZBBean;
import com.tbea.ic.operation.model.entity.local.XJLRB;
import com.tbea.ic.operation.model.entity.local.YDZBFDW;
import com.tbea.ic.operation.model.entity.local.ZBHZ;

@Repository
public class YDZBDAOJPAImpl implements YDZBDao {

	EntityManager entityManager;

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<YDZBBean> getYDZB(Calendar cal, Company company) {
		String query = "select y from YDZBFDW y where y.ny = ?1";
		query += " and( y.qybh = " + company.getId();

		if (!company.getSubCompanys().isEmpty()) {

			List<Company> cys = company.getSubCompanys();
			for (int i = 0; i < cys.size(); ++i) {
				query += " or y.qybh = " + cys.get(i).getId();
			}
		}

		if (company.getType() == CompanyType.JT) {
			List<Company> cys = company.getLeaves();
			for (int i = 0; i < cys.size(); ++i) {
				if (cys.get(i).getType() != CompanyType.ZHGS) {
					query += " or y.qybh = " + cys.get(i).getId();
				}
			}
			//
			// query += " or y.qybh = " +
			// company.get(Company.Type.SBDCY).getId();
			// query += " or y.qybh = " +
			// company.get(Company.Type.NYCY).getId();
			// query += " or y.qybh = " +
			// company.get(Company.Type.XNYCY).getId();
			// query += " or y.qybh = " + company.get(Company.Type.GCL).getId();
		}

		query += ")";

		Query q = entityManager.createQuery(query);
		Date d = Date.valueOf(cal.get(Calendar.YEAR) + "-"
				+ (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH));
		q.setParameter(1, Util.format(d));
		List<YDZBFDW> ydzbfdws = q.getResultList();
		List<YDZBBean> ydzbs = new ArrayList<YDZBBean>();

		for (YDZBFDW ydzbfdw : ydzbfdws) {
			YDZBBean ydzbbean = new YDZBBean();
			ydzbbean.setXh(ydzbfdw.getQybh() + "");
			ydzbbean.setZblx(ydzbfdw.getZbbh() + "");
			ydzbbean.setZbmc(ydzbfdw.getZbmc() + "");
			ydzbbean.setByjh(ydzbfdw.getByjhz() + "");
			ydzbbean.setBywc(ydzbfdw.getBywcz() + "");
			ydzbbean.setJhwcl(ydzbfdw.getJhwcl() + "");
			ydzbbean.setQntq(ydzbfdw.getQntqz() + "");
			ydzbbean.setJqntqzzb(ydzbfdw.getJtqzzb() + "");
			ydzbbean.setJdjh(ydzbfdw.getJdjh() + "");
			ydzbbean.setJdlj(ydzbfdw.getJdlj() + "");
			ydzbbean.setJdjhwcl(ydzbfdw.getJdwcl() + "");
			ydzbbean.setNdjh(ydzbfdw.getNdjhz() + "");
			ydzbbean.setNdlj(ydzbfdw.getNdljwcz() + "");
			ydzbbean.setNdjhwcl(ydzbfdw.getNdwcl() + "");
			ydzbbean.setQntqlj(ydzbfdw.getQntqlj() + "");
			ydzbbean.setJqntqljzzb(ydzbfdw.getJqntqljzzb() + "");
			ydzbs.add(ydzbbean);
		}
		return ydzbs;
	}

	@Override
	public List<YDZBBean> getYDZB_V2(Calendar cal, Company company) {
		String query = "select z from ZBHZ z where z.ny = ?1";
		Query q = entityManager.createQuery(query);
		Date d = Date.valueOf(cal.get(Calendar.YEAR) + "-"
				+ (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH));
		q.setParameter(1, Util.format(d));
		List<ZBHZ> zbhzs = q.getResultList();
		List<YDZBBean> ydzbs = new ArrayList<YDZBBean>();
		for (ZBHZ zbhz : zbhzs) {
			YDZBBean ydzbbean = new YDZBBean();
			ydzbbean.setZblx(zbhz.getZbbh() + "");
			ydzbbean.setZbmc(zbhz.getZbmc() + "");
			ydzbbean.setByjh(zbhz.getByjhz() + "");
			ydzbbean.setBywc(zbhz.getBywcz() + "");
			ydzbbean.setJhwcl(zbhz.getJhwcl() + "");
			ydzbbean.setQntq(zbhz.getQntqz() + "");
			ydzbbean.setJqntqzzb(zbhz.getJtqzzb() + "");
			ydzbbean.setJdjh(zbhz.getJdjh() + "");
			ydzbbean.setJdlj(zbhz.getJdlj() + "");
			ydzbbean.setJdjhwcl(zbhz.getJdwcl() + "");
			ydzbbean.setNdjh(zbhz.getNdjhz() + "");
			ydzbbean.setNdlj(zbhz.getNdljwcz() + "");
			ydzbbean.setNdjhwcl(zbhz.getNdwcl() + "");
			ydzbbean.setQntqlj(zbhz.getQntqlj() + "");
			ydzbbean.setJqntqljzzb(zbhz.getJqntqljzzb() + "");
			ydzbs.add(ydzbbean);
		}
		return ydzbs;
	}

	@Override
	public List<XJL> getXJL(Calendar cal) {
		Query q = entityManager
				.createQuery("select x from XJLRB x where x.rq = ?1 and x.jgmc != '中疆物流'");
		Date d = Date.valueOf(cal.get(Calendar.YEAR) + "-"
				+ (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH));
		q.setParameter(1, d);
		List<XJLRB> xjlrbs = q.getResultList();
		List<XJL> xjls = new ArrayList<XJL>();

		for (XJLRB xjlrb : xjlrbs) {
			XJL xjl = new XJL();
			xjl.setDrlr("" + xjlrb.getDrlr());
			xjl.setDrlc("" + xjlrb.getDrlc());
			xjl.setDrjll("" + xjlrb.getDrjll());
			xjl.setDylr("" + xjlrb.getDylr());
			xjl.setDylc("" + xjlrb.getDylc());
			xjl.setDyjll("" + xjlrb.getDyjll());
			xjl.setDnlr("" + xjlrb.getDnlr());
			xjl.setDnlc("" + xjlrb.getDnlc());
			xjl.setDnjll("" + xjlrb.getDnjll());
			xjl.setBytzs("" + xjlrb.getBytzs());
			xjls.add(xjl);
		}

		return xjls;
	}

	@Override
	public ZBHZ getLatestZbhj() {
		Query q = entityManager.createQuery(
				"from ZBHZ order by ny desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<ZBHZ> zbs = q.getResultList();
		if (!zbs.isEmpty()){
			return zbs.get(0);
		}
		return null;
	}

	@Override
	public YDZBFDW getLatestYdzbfdw() {
		Query q = entityManager.createQuery(
				"from YDZBFDW order by ny desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<YDZBFDW> ydzbs = q.getResultList();
		if (!ydzbs.isEmpty()){
			return ydzbs.get(0);
		}
		return null;
	}

	@Override
	public XJLRB getLatestXjlrb() {
		Query q = entityManager.createQuery(
				"from XJLRB where jgmc != '中疆物流' order by rq desc");
		q.setFirstResult(0);
		q.setMaxResults(1);
		List<XJLRB> rbs = q.getResultList();
		if (!rbs.isEmpty()){
			return rbs.get(0);
		}
		return null;
	}

}
