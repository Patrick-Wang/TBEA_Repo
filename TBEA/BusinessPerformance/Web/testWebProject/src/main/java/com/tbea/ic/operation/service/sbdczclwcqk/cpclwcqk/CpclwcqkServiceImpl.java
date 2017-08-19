package com.tbea.ic.operation.service.sbdczclwcqk.cpclwcqk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpclwcqk.CpclwcqkDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpclwcqk.CpclwcqkDaoImpl;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpczwcqk.CpczwcqkDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpczwcqk.CpczwcqkDaoImpl;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.CpclwcqkEntity;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.CpczwcqkEntity;
import com.tbea.ic.operation.service.sbdczclwcqk.SBDCZCLWCQK_CZCL_BYQ_CPMC;
import com.tbea.ic.operation.service.sbdczclwcqk.SBDCZCLWCQK_CZCL_XL_CPMC;

import net.sf.json.JSONArray;

@Service(CpclwcqkServiceImpl.NAME)
@Transactional("transactionManager")
public class CpclwcqkServiceImpl implements CpclwcqkService {
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Resource(name=CpclwcqkDaoImpl.NAME)
	CpclwcqkDao cpclwcqkDao;

	@Resource(name=CpczwcqkDaoImpl.NAME)
	CpczwcqkDao cpczwcqkDao;
	
	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	public final static String NAME = "CpclwcqkServiceImpl";

	
	private List<Integer> getHjList(SbdczclwcqkType type) {
		
		List<Integer> hjList = new ArrayList<Integer>();
		
		switch (type) {

		case SBDCZCLWCQK_CL_BYQ: {
			hjList.add(SBDCZCLWCQK_CZCL_BYQ_CPMC.MLSPCS_BYQ_DYDJ_JLBYQ.value());
			hjList.add(SBDCZCLWCQK_CZCL_BYQ_CPMC.MLSPCS_BYQ_DYDJ_ZLBYQ.value());
			hjList.add(SBDCZCLWCQK_CZCL_BYQ_CPMC.MLSPCS_BYQ_DYDJ_DKQ.value());
			hjList.add(SBDCZCLWCQK_CZCL_BYQ_CPMC.MLSPCS_BYQ_CPFL_GSBYQ.value());
			hjList.add(SBDCZCLWCQK_CZCL_BYQ_CPMC.MLSPCS_BYQ_CPFL_77.value());
			hjList.add(SBDCZCLWCQK_CZCL_BYQ_CPMC.MLSPCS_BYQ_CPFL_81.value());
			hjList.add(SBDCZCLWCQK_CZCL_BYQ_CPMC.MLSPCS_BYQ_CPFL_TZBYQ.value());
			hjList.add(SBDCZCLWCQK_CZCL_BYQ_CPMC.MLSPCS_BYQ_CPFL_YSL.value());
			break;
		}
		case SBDCZCLWCQK_CL_XL: {
//			hjList.add(SBDCZCLWCQK_CL_XL_Type.SBDCZCLWCQK_CZ_XL_ZH_QT.value());
			break;
		}
		default: 
			break;

		}
		return hjList;
	}
	
	public static List<Integer> getCpIdList(SbdczclwcqkType type) {
		
		List<Integer> cpIdList = new ArrayList<Integer>();
		
		switch (type) {

		case SBDCZCLWCQK_BYQ:
		case SBDCZCLWCQK_CL_BYQ: {
			for (SBDCZCLWCQK_CZCL_BYQ_CPMC cp : SBDCZCLWCQK_CZCL_BYQ_CPMC.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		
		case SBDCZCLWCQK_XL:
		case SBDCZCLWCQK_CL_XL: {
			for (SBDCZCLWCQK_CZCL_XL_CPMC cp : SBDCZCLWCQK_CZCL_XL_CPMC.values()) {
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
	public List<List<String>> getCpclwcqk(Date d, Company company, SbdczclwcqkType type) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<Integer> cpIdList = getCpIdList(type);
		List<Integer> hjList = getHjList(type);
		
		List<Double> finalListTemp = new ArrayList<Double>();
		List<Boolean> finalListNullOrNot = new ArrayList<Boolean>();
		
		for (int i = 0; i < 13; ++i){
			finalListTemp.add(0.0);
			finalListNullOrNot.add(true);
		}
		
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.YEAR, -1);
			//cal.add(Calendar.MONTH, 1);

			List<CpclwcqkEntity> entities = null;
			if (companyManager.getBMDBOrganization().owns(company)){
				entities = cpclwcqkDao.getByDate(new Date(cal.getTimeInMillis()), d, company, type, cpIdList.get(cp));
			}else{
				entities = cpclwcqkDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies(), type, cpIdList.get(cp));
			}
			
			
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			for (int i = 0; i < 13; ++i){

				Boolean bFind = false;
				for (CpclwcqkEntity entity : entities){
					if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
						bFind = true;
						oneLine.add("" + entity.getCl());
						
						if (hjList.isEmpty() || hjList.contains(entity.getCpmc().getId())){
							finalListTemp.set(i, MathUtil.sum(finalListTemp.get(i), entity.getCl()));
							finalListNullOrNot.set(i, false);
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
		
		List<String> finalLine = new ArrayList<String>();
		finalLine.add("合计");

		for (int i = 0; i < 13; ++i) {

			if (!finalListNullOrNot.get(i)) {

				finalLine.add("" + finalListTemp.get(i));
			} else {

				finalLine.add("null");
			}
		}

		result.add(finalLine);
		
		return result;
	}
	
	@Override
	public List<List<String>> getCpclwcqkEntry(Date d, Company company, SbdczclwcqkType type) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		List<Integer> cpIdList = getCpIdList(type);
		
		SbdczclwcqkType czType;
		SbdczclwcqkType clType;
		if (SbdczclwcqkType.SBDCZCLWCQK_BYQ == type){
			czType = SbdczclwcqkType.SBDCZCLWCQK_CZ_BYQ;
			clType = SbdczclwcqkType.SBDCZCLWCQK_CL_BYQ;
		}else{
			czType = SbdczclwcqkType.SBDCZCLWCQK_CZ_XL;
			clType = SbdczclwcqkType.SBDCZCLWCQK_CL_XL;
		}
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			CpczwcqkEntity entityCz = cpczwcqkDao.getByDate(d, company, czType, cpIdList.get(cp));
			CpclwcqkEntity entityCl = cpclwcqkDao.getByDate(d, company, clType, cpIdList.get(cp));
			
			List<String> oneLine = new ArrayList<String>();
			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			if (entityCz == null) {
				oneLine.add("");
			} else {
				Boolean bFind = false;

				if (entityCz.getNf() == cal.get(Calendar.YEAR)
						&& entityCz.getYf() == cal.get(Calendar.MONTH) + 1) {
					bFind = true; 
					oneLine.add("" + entityCz.getCz());
				}

				if (!bFind) {
					oneLine.add("");
				}

			}	
			
			if (entityCl == null) {
				oneLine.add("");
			} else {
				Boolean bFind = false;

				if (entityCl.getNf() == cal.get(Calendar.YEAR)
						&& entityCl.getYf() == cal.get(Calendar.MONTH) + 1) {
					bFind = true; 
					oneLine.add("" + entityCl.getCl());
				}

				if (!bFind) {
					oneLine.add("");
				}

			}	
			
			result.add(oneLine);
		}		
		
		return result;
	}

	ErrorCode entryCpclwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data, ZBStatus status) {

		ErrorCode err = ErrorCode.OK;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<Integer> cpIdList = getCpIdList(type);
		
		SbdczclwcqkType czType;
		SbdczclwcqkType clType;
		if (SbdczclwcqkType.SBDCZCLWCQK_BYQ == type){
			czType = SbdczclwcqkType.SBDCZCLWCQK_CZ_BYQ;
			clType = SbdczclwcqkType.SBDCZCLWCQK_CL_BYQ;
		}else{
			czType = SbdczclwcqkType.SBDCZCLWCQK_CZ_XL;
			clType = SbdczclwcqkType.SBDCZCLWCQK_CL_XL;
		}
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			CpczwcqkEntity entityCz = cpczwcqkDao.getByDate(d, company, czType, cpIdList.get(cp));
			CpclwcqkEntity entityCl = cpclwcqkDao.getByDate(d, company, clType, cpIdList.get(cp));
						
			if (null == entityCz){
				entityCz = new CpczwcqkEntity();

				entityCz.setNf(cal.get(Calendar.YEAR));
				entityCz.setYf(cal.get(Calendar.MONTH) + 1);
				entityCz.setDwxx(dwxxDao.getById(company.getId()));
				entityCz.setCpmc(cpmcDao.getById(cpIdList.get(cp)));
				entityCz.setTjfs(czType.value());
			}

			entityCz.setZt(status.ordinal());
			entityCz.setCz(Util.toDoubleNull(data.getJSONArray(cp).getString(0)));
			
			cpczwcqkDao.merge(entityCz);
			
			if (null == entityCl){
				entityCl = new CpclwcqkEntity();

				entityCl.setNf(cal.get(Calendar.YEAR));
				entityCl.setYf(cal.get(Calendar.MONTH) + 1);
				entityCl.setDwxx(dwxxDao.getById(company.getId()));
				entityCl.setCpmc(cpmcDao.getById(cpIdList.get(cp)));
				entityCl.setTjfs(clType.value());
			}

			entityCl.setZt(status.ordinal());
			entityCl.setCl(Util.toDoubleNull(data.getJSONArray(cp).getString(1)));
			
			cpclwcqkDao.merge(entityCl);
		}
		
		return err;
	}
	
	@Override
	public ErrorCode saveCpclwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data) {

		return entryCpclwcqk(d, company, type, data, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitCpclwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data) {

		return entryCpclwcqk(d, company, type, data, ZBStatus.SUBMITTED);		
	}
	
	@Override
	public ZBStatus getCpclwcqkStatus(Date d, Company comp, SbdczclwcqkType type) {
		return ZBStatus.SAVED;	
	}
}
