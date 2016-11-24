package com.tbea.ic.operation.service.sbdscqyqk.xfcpqy;

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
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.sbdscqyqk.SbdscqyqkType;
import com.tbea.ic.operation.model.dao.dl.sbdqy.DlQyDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy.XfcpqyDao;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy.XfcpqyDaoImpl;
import com.tbea.ic.operation.model.dao.xl.sbdqy.XlQyDao;
import com.tbea.ic.operation.model.entity.identifier.common.CpmcEntity;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfcpqyEntity;
import com.tbea.ic.operation.service.report.HBWebService;

@Service(XfcpqyServiceImpl.NAME)
@Transactional("transactionManager")
public class XfcpqyServiceImpl implements XfcpqyService {
	
	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	@Resource(name = XfcpqyDaoImpl.NAME)
	XfcpqyDao xfcpqyDao;

	@Autowired
	DlQyDao dlqyDao;
	
	@Autowired
	XlQyDao xlqyDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	public final static String NAME = "XfcpqyServiceImpl";

	private List<Integer> getHjList(SbdscqyqkType type) {
		
		List<Integer> hjList = new ArrayList<Integer>();
	    
		switch (type) {

		case YLFX_WGCPYLNL_BYQ: {
			hjList.add(SBDCPQY_XFCP_BYQ.MLSPCS_BYQ_DYDJ_JLBYQ.value());
			hjList.add(SBDCPQY_XFCP_BYQ.MLSPCS_BYQ_DYDJ_ZLBYQ.value());
			hjList.add(SBDCPQY_XFCP_BYQ.MLSPCS_BYQ_DYDJ_DKQ.value());
			hjList.add(SBDCPQY_XFCP_BYQ.SBDCZCLWCQK_CZ_BYQ_DYDJ_GSBYQ.value());
			hjList.add(SBDCPQY_XFCP_BYQ.SBDCZCLWCQK_CZ_BYQ_DYDJ_77.value());
			hjList.add(SBDCPQY_XFCP_BYQ.SBDCZCLWCQK_CZ_BYQ_DYDJ_81.value());
			hjList.add(SBDCPQY_XFCP_BYQ.SBDCZCLWCQK_CZ_BYQ_DYDJ_TZBYQ.value());
			hjList.add(SBDCPQY_XFCP_BYQ.SBDCZCLWCQK_CZ_BYQ_DYDJ_YSL.value());
			break;
		}
		default: 
			break;

		}
		
		return hjList;
	}
	
	private List<Integer> getCpIdList(SbdscqyqkType type) {
		
		List<Integer> cpIdList = new ArrayList<Integer>();
	    
		switch (type) {

		case YLFX_WGCPYLNL_BYQ: {
			for (SBDCPQY_XFCP_BYQ cp : SBDCPQY_XFCP_BYQ.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WGCPYLNL_XL: {
			for (SBDCPQY_XFCP_XL cp : SBDCPQY_XFCP_XL.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		default: 
			break;

		}
		
		return cpIdList;
	}

	@Override
	public List<List<String>> getXfcpqy(Date d, Company company, SbdscqyqkType type) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<Integer> cpIdList = getCpIdList(type);
//		List<Double> finalListTemp1 = new ArrayList<Double>();
//		List<Boolean> finalListNullOrNot1 = new ArrayList<Boolean>();
		
//		List<Double> finalListTemp2 = new ArrayList<Double>();
//		List<Boolean> finalListNullOrNot2 = new ArrayList<Boolean>();
		
		List<Double> finalListTempAll = new ArrayList<Double>();
		List<Boolean> finalListNullOrNotAll = new ArrayList<Boolean>();
		List<Integer> hjlist = getHjList(type);
		for (int i = 0; i < 13; ++i){
//			finalListTemp1.add(0.0);
//			finalListNullOrNot1.add(true);
//			
//			finalListTemp2.add(0.0);
//			finalListNullOrNot2.add(true);

			finalListTempAll.add(0.0);
			finalListNullOrNotAll.add(true);
		}
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.YEAR, -1);
//			cal.add(Calendar.MONTH, 1);
			List<XfcpqyEntity> entities = null;
			if (companyManager.getBMDBOrganization().owns(company)){
				entities = xfcpqyDao.getByDate(new Date(cal.getTimeInMillis()), d, company, type, cpIdList.get(cp));
			}else{
				entities = xfcpqyDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies(), type, cpIdList.get(cp));
			}
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			for (int i = 0; i < 13; ++i){

				Boolean bFind = false;
				for (XfcpqyEntity entity : entities){
					if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
						bFind = true;
						oneLine.add("" + entity.getQye());
						
						if (type == SbdscqyqkType.YLFX_WGCPYLNL_XL) {
							
							if (cp <= SBDCPQY_XFCP_XL.SBDCZCLWCQK_CZ_XL_ZH_QT.ordinal()) {

//								finalListTemp1.set(i, finalListTemp1.get(i) + entity.getQye());
//								finalListNullOrNot1.set(i, false);						
								
								finalListTempAll.set(i, MathUtil.sum(finalListTempAll.get(i), entity.getQye()));
								finalListNullOrNotAll.set(i, false);
								
							} 
//							else if (cp <= SBDCPQY_XFCP_XL.SBDCZCLWCQK_CZ_XL_GJGC.ordinal()) {
//
//								finalListTemp2.set(i, finalListTemp2.get(i) + entity.getQye());
//								finalListNullOrNot2.set(i, false);				
//								
//								finalListTempAll.set(i, finalListTempAll.get(i) + entity.getQye());
//								finalListNullOrNotAll.set(i, false);
//								
//							} else if (cp <= SBDCPQY_XFCP_XL.SBDCZCLWCQK_CZ_XL_FWL.ordinal()) {
//								
//								finalListTempAll.set(i, finalListTempAll.get(i) + entity.getQye());
//								finalListNullOrNotAll.set(i, false);							
//							}
						}else{
							if (hjlist.contains(entity.getCpmc().getId())){
								finalListTempAll.set(i, MathUtil.sum(finalListTempAll.get(i), entity.getQye()));
								finalListNullOrNotAll.set(i, false);
							}
						}

						entities.remove(entity);
						break;
					}
				}
				if (!bFind) {
					oneLine.add("null");
				}
				
				cal.add(Calendar.MONTH, 1);
			}
			
			result.add(oneLine);
		}		
		
//		List<String> finalLine1 = new ArrayList<String>();
//		finalLine1.add("小计");
//		for (int i = 0; i < 13; ++i){
//		
//			if (!finalListNullOrNot1.get(i)) {
//
//				finalLine1.add("" + finalListTemp1.get(i));
//			} else {
//				
//				finalLine1.add("null");
//			}
//		}
//		
//		List<String> finalLine2 = new ArrayList<String>();
//		finalLine2.add("小计");
//		for (int i = 0; i < 13; ++i){
//		
//			if (!finalListNullOrNot2.get(i)) {
//
//				finalLine2.add("" + finalListTemp2.get(i));
//			} else {
//				
//				finalLine2.add("null");
//			}
//		}
		
		List<String> finalLineAll = new ArrayList<String>();
		finalLineAll.add("合计");
		for (int i = 0; i < 13; ++i){
		
			if (!finalListNullOrNotAll.get(i)) {

				finalLineAll.add("" + finalListTempAll.get(i));
			} else {
				
				finalLineAll.add("null");
			}
		}
		
		if (type == SbdscqyqkType.YLFX_WGCPYLNL_XL ) {

//			result.add(SBDCPQY_XFCP_XL.SBDCZCLWCQK_CZ_XL_GJGC.ordinal() + 1, finalLine2);
//			
//			result.add(SBDCPQY_XFCP_XL.SBDCZCLWCQK_CZ_XL_ZH_QT.ordinal() + 1, finalLine1);

			result.add(finalLineAll);
		}else{
			result.add(finalLineAll);
		}
		
		return result;
	}
	
	@Override
	public List<List<String>> getXfcpqyEntry(Date d, Company company, SbdscqyqkType type) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			XfcpqyEntity entity = xfcpqyDao.getByDate(d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();
			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			if (entity == null) {
				oneLine.add("");
			} else {
				Boolean bFind = false;

				if (entity.getNf() == cal.get(Calendar.YEAR)
						&& entity.getYf() == cal.get(Calendar.MONTH) + 1) {
					bFind = true; 
					oneLine.add("" + entity.getQye());
				}

				if (!bFind) {
					oneLine.add("");
				}

			}				
			result.add(oneLine);
		}		
		
		return result;
	}

	ErrorCode entryXfcpqy(Date d, Company company, SbdscqyqkType type, JSONArray data, ZBStatus status) {

		ErrorCode err = ErrorCode.OK;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			XfcpqyEntity entity= xfcpqyDao.getByDate(d, company, type, cpIdList.get(cp));
			
			if (null == entity){
				entity = new XfcpqyEntity();

				entity.setNf(cal.get(Calendar.YEAR));
				entity.setYf(cal.get(Calendar.MONTH) + 1);
				entity.setDwxx(dwxxDao.getById(company.getId()));
				entity.setCpmc(cpmcDao.getById(cpIdList.get(cp)));
				entity.setTjfs(type.value());
			}

			entity.setZt(status.ordinal());
			entity.setQye(Util.toDoubleNull(data.getJSONArray(cp).getString(0)));
			
			xfcpqyDao.merge(entity);
		}
		
		return err;
	}
	
	@Override
	public ErrorCode saveXfcpqy(Date d, Company company, SbdscqyqkType type, JSONArray data) {

		return entryXfcpqy(d, company, type, data, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitXfcpqy(Date d, Company company, SbdscqyqkType type, JSONArray data) {

		return entryXfcpqy(d, company, type, data, ZBStatus.SUBMITTED);
	}
	
	@Override
	public ZBStatus getXfcpqyStatus(Date d, Company comp, SbdscqyqkType type) {
		return ZBStatus.SAVED;
	}

	
	private void importCpqy(Date d, List<Object[]> result, Company comp, SbdscqyqkType type){
		EasyCalendar ec = new EasyCalendar(d);
		List<XfcpqyEntity> entities = xfcpqyDao.getByDate(d, comp, type);
		ZBStatus status = ZBStatus.SUBMITTED;
		if (null != entities && !entities.isEmpty()){
			status = ZBStatus.valueOf(entities.get(0).getZt());
		}
		List<Integer> byqCps = this.getCpIdList(type);
		List<CpmcEntity> cps = getCpList(byqCps);
		for (Object[] r: result){
			CpmcEntity mc = findCp(cps, (String)r[0]);
			if (null == mc){
				LoggerFactory.getLogger("WEBSERVICE").info("importCpqy 无法找到产品 : " + r[0]);
			}else{
				XfcpqyEntity entity= xfcpqyDao.getByDate(d, comp, type, mc.getId());

				if (null == entity){
					entity = new XfcpqyEntity();

					entity.setNf(ec.getYear());
					entity.setYf(ec.getMonth());
					entity.setDwxx(dwxxDao.getById(comp.getId()));
					entity.setCpmc(mc);
					entity.setTjfs(type.value());
				}

				entity.setZt(status.ordinal());
				entity.setQye(Util.toDoubleNull(r[1].toString().replaceAll(",", "")));
				xfcpqyDao.merge(entity);
				
			}
		}
	}
	
	@Override
	public void importDLCpqy(Date d) {

		LoggerFactory.getLogger("WEBSERVICE").info("importDLCpqy");
		List<Object[]> result = dlqyDao.getCpqy(d);
		Company comp = companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS);
		importCpqy(d, result, comp, SbdscqyqkType.YLFX_WGCPYLNL_XL);
	}

	
	@Override
	public void importHBCpqy(Date d) {
		LoggerFactory.getLogger("WEBSERVICE").info("importHBCpqy");
		HBWebService hbws = new HBWebService();
		List<String> cols = new ArrayList<String>();
		cols.add("product_type");
		cols.add("contract_volume");
		List<Object[]> result = hbws.getHBCpqy(cols, d);
		Company comp = companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS);
		importCpqy(d, result, comp, SbdscqyqkType.YLFX_WGCPYLNL_BYQ);
	}
	
	private CpmcEntity findCp(List<CpmcEntity> cps, String name){
		for (CpmcEntity cp : cps){
			if (cp.getName().trim().equals(name)){
				return cp;
			}
		}
		return null;
	}

	private List<CpmcEntity> getCpList(List<Integer> byqCps) {
		List<CpmcEntity> ret  = new ArrayList<CpmcEntity>();
		for (Integer cp : byqCps){
			ret.add(cpmcDao.getById(cp));
		}
		return ret;
	}

	@Override
	public void importXLCpqy(Date d) {

		LoggerFactory.getLogger("WEBSERVICE").info("importXLCpqy");
		List<Object[]> result = xlqyDao.getCpqy(d);
		Company comp = companyManager.getBMDBOrganization().getCompany(CompanyType.XLC);
		importCpqy(d, result, comp, SbdscqyqkType.YLFX_WGCPYLNL_XL);
	}
	
	

}
