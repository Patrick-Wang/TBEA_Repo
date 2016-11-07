package com.tbea.ic.operation.service.sbdscqyqk.xfscqy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.sbdscqyqk.SbdscqyqkType;
import com.tbea.ic.operation.model.dao.dl.sbdqy.DlQyDao;
import com.tbea.ic.operation.model.dao.identifier.scfxgb.hy.HyDao;
import com.tbea.ic.operation.model.dao.identifier.scfxgb.hy.HyDaoImpl;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfscqy.XfscqyDao;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfscqy.XfscqyDaoImpl;
import com.tbea.ic.operation.model.entity.identifier.scfxgb.HyEntity;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfscqyEntity;
import com.tbea.ic.operation.service.report.HBWebService;

@Service(XfscqyServiceImpl.NAME)
@Transactional("transactionManager")
public class XfscqyServiceImpl implements XfscqyService {
	@Resource(name=HyDaoImpl.NAME)
	HyDao hyDao;

	@Resource(name=XfscqyDaoImpl.NAME)
	XfscqyDao xfscqyDao;

	public final static String NAME = "XfscqyServiceImpl";

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	DlQyDao dlqyDao;
	
	
	@Override
	public List<List<String>> getXfscqy(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		
		List<HyEntity> hys = hyDao.getAll();
		for (HyEntity entity: hys){
			List<String> list = new ArrayList<String>();
			Util.resize(list, 14);
			list.set(0, entity.getName());
			result.add(list);
		}
		List<String> listHj = new ArrayList<String>();
		Util.resize(listHj, 14);
		listHj.set(0, "合计");
		result.add(listHj);
		for (int i = 0; i < 13; ++i){
			List<XfscqyEntity> entities = null;
			if (companyManager.getBMDBOrganization().owns(company)){
				entities = xfscqyDao.getByDate(new Date(cal.getTimeInMillis()), company);
			}else{
				entities = xfscqyDao.getSumByDate(new Date(cal.getTimeInMillis()), company.getSubCompanies());
			}
			
			Double sum = null;
			for (XfscqyEntity entity: entities){
				result.get(entity.getHyid()).set(i + 1, "" + entity.getQye());
				sum = Util.sum(new Double[]{sum, entity.getQye()});
			}
			listHj.set(i + 1, "" + sum);
			cal.add(Calendar.MONTH, 1);
		}
		return result;
	}

	@Override
	public List<List<String>> getXfscqyEntry(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<HyEntity> hys = hyDao.getAll();
		for (HyEntity entity: hys){
			List<String> list = new ArrayList<String>();
			Util.resize(list, 2);
			list.set(0, entity.getName());
			result.add(list);
		}
		
		List<XfscqyEntity> entities = xfscqyDao.getByDate(d, company);
		for (XfscqyEntity entity: entities){
			result.get(entity.getHyid()).set(1, "" + entity.getQye());
		}
		return result;
	}

	private ErrorCode entryXfscqy(Date d, JSONArray data, Company company, ZBStatus zt){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		for (int i = 0; i < data.size(); ++i){
			XfscqyEntity entity = xfscqyDao.getByDate(d, company, i);
			if (null == entity){
				entity = new XfscqyEntity();
				entity.setDwid(company.getId());
				entity.setHyid(i);
				entity.setZt(zt.ordinal());
				entity.setNf(cal.get(Calendar.YEAR)); 
				entity.setYf(cal.get(Calendar.MONTH) + 1); 
			}
			entity.setQye(Util.toDoubleNull(data.getJSONArray(i).getString(0)));
			xfscqyDao.merge(entity);
		}
		return ErrorCode.OK;
	}
	
	@Override
	public ErrorCode saveXfscqy(Date d, JSONArray data, Company company) {
		return entryXfscqy(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitXfscqy(Date d, JSONArray data, Company company) {
		return entryXfscqy(d, data, company, ZBStatus.SUBMITTED);
	}
	
	@Override
	public void importHBScqy(Date d) {
		HBWebService hbws = new HBWebService();
		List<String> cols = new ArrayList<String>();
		cols.add("industry");
		cols.add("contract_volume");
		List<Object[]> result = hbws.getHBScqy(cols, d);
		Company comp = companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS);
		importScqy(d, result, comp);
	}
	
	@Override
	public void importDLScqy(Date d) {
		List<Object[]> result = dlqyDao.getScqy(d);
		Company comp = companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS);
		importScqy(d, result, comp);
	}


	public void importScqy(Date d, List<Object[]> result, Company comp) {
		
		EasyCalendar ec = new EasyCalendar(d);
		List<XfscqyEntity> entities = xfscqyDao.getByDate(d, comp);
		ZBStatus status = ZBStatus.SUBMITTED;
		if (null != entities && !entities.isEmpty()){
			status = ZBStatus.valueOf(entities.get(0).getZt());
		}
		for (Object[] r: result){
			HyEntity mc = hyDao.getByName((String)r[0]);
			if (null == mc){
				LoggerFactory.getLogger("WEBSERVICE").info("importScqy 系统中无法找到行业 : " + (String)r[0]);
			}else{
				XfscqyEntity entity= xfscqyDao.getByDate(d, comp, mc.getId());
				
				if (null == entity){
					entity = new XfscqyEntity();

					entity.setNf(ec.getYear());
					entity.setYf(ec.getMonth());
					entity.setDwid(comp.getId());
					entity.setHyid(mc.getId());
				}

				entity.setZt(status.ordinal());
				entity.setQye(Util.toDoubleNull((r[1].toString()).replaceAll(",", "")));
				xfscqyDao.merge(entity);
			}
		}
	}

}
