package com.tbea.ic.operation.service.sbdczclwcqk.cpclwcqk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpclwcqk.CpclwcqkDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpclwcqk.CpclwcqkDaoImpl;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.CpclwcqkEntity;

@Service(CpclwcqkServiceImpl.NAME)
@Transactional("transactionManager")
public class CpclwcqkServiceImpl implements CpclwcqkService {
	@Resource(name=CpclwcqkDaoImpl.NAME)
	CpclwcqkDao cpclwcqkDao;

	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	public final static String NAME = "CpclwcqkServiceImpl";

	
	private List<Integer> getCpIdList(SbdczclwcqkType type) {
		
		List<Integer> cpIdList = new ArrayList<Integer>();
		
		switch (type) {

		case SBDCZCLWCQK_CL_BYQ: {
			for (SBDCZCLWCQK_CL_BYQ_Type cp : SBDCZCLWCQK_CL_BYQ_Type.values()) {
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
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.YEAR, -1);
			cal.add(Calendar.MONTH, 1);
			
			List<CpclwcqkEntity> entities= cpclwcqkDao.getByDate(new Date(cal.getTimeInMillis()), d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			for (int i = 0; i < 12; ++i){

				Boolean bFind = false;
				for (CpclwcqkEntity entity : entities){
					if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
						bFind = true;
						oneLine.add("" + entity.getCl());
						
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
		
		return result;
	}
	
	@Override
	public List<List<String>> getCpclwcqkEntry(Date d, Company company, SbdczclwcqkType type) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			CpclwcqkEntity entity = cpclwcqkDao.getByDate(d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();
			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			if (entity == null) {
				oneLine.add("");
			} else {
				Boolean bFind = false;

				if (entity.getNf() == cal.get(Calendar.YEAR)
						&& entity.getYf() == cal.get(Calendar.MONTH) + 1) {
					bFind = true; 
					oneLine.add("" + entity.getCl());
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
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			CpclwcqkEntity entity= cpclwcqkDao.getByDate(d, company, type, cpIdList.get(cp));
			
			if (null == entity){
				entity = new CpclwcqkEntity();

				entity.setNf(cal.get(Calendar.YEAR));
				entity.setYf(cal.get(Calendar.MONTH) + 1);
				entity.setDwxx(dwxxDao.getById(company.getId()));
				entity.setCpmc(cpmcDao.getById(cpIdList.get(cp)));
				entity.setTjfs(type.value());
			}

			entity.setZt(status.ordinal());
			entity.setCl(Util.toDoubleNull(data.getJSONArray(cp).getString(1)));
			
			cpclwcqkDao.merge(entity);
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
