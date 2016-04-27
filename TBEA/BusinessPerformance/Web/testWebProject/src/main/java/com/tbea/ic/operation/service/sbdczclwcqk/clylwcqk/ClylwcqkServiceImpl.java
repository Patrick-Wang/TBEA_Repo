package com.tbea.ic.operation.service.sbdczclwcqk.clylwcqk;

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
import com.tbea.ic.operation.model.dao.identifier.common.clmc.ClmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.clmc.ClmcDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.clylwcqk.ClylwcqkDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.clylwcqk.ClylwcqkDaoImpl;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.ClylwcqkEntity;

@Service(ClylwcqkServiceImpl.NAME)
@Transactional("transactionManager")
public class ClylwcqkServiceImpl implements ClylwcqkService {
	@Resource(name=ClylwcqkDaoImpl.NAME)
	ClylwcqkDao clylwcqkDao;

	@Resource(name=ClmcDaoImpl.NAME)
	ClmcDao clmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	public final static String NAME = "ClylwcqkServiceImpl";

	private List<Integer> getCpIdList(SbdczclwcqkType type) {
		
		List<Integer> cpIdList = new ArrayList<Integer>();
		
		switch (type) {

		case SBDCZCLWCQK_CL_XL: {
			for (SBDCZCLWCQK_CL_XL_Type cp : SBDCZCLWCQK_CL_XL_Type.values()) {
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
	public List<List<String>> getClylwcqk(Date d, Company company, SbdczclwcqkType type) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.YEAR, -1);
			cal.add(Calendar.MONTH, 1);
			
			List<ClylwcqkEntity> entities= clylwcqkDao.getByDate(new Date(cal.getTimeInMillis()), d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(clmcDao.getById(cpIdList.get(cp)).getName());
			
			for (int i = 0; i < 12; ++i){

				Boolean bFind = false;
				for (ClylwcqkEntity entity : entities){
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
	public List<List<String>> getClylwcqkEntry(Date d, Company company, SbdczclwcqkType type) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			ClylwcqkEntity entity = clylwcqkDao.getByDate(d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();
			oneLine.add(clmcDao.getById(cpIdList.get(cp)).getName());
			
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

	ErrorCode entryClylwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data, ZBStatus status) {

		ErrorCode err = ErrorCode.OK;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			ClylwcqkEntity entity= clylwcqkDao.getByDate(d, company, type, cpIdList.get(cp));
			
			if (null == entity){
				entity = new ClylwcqkEntity();

				entity.setNf(cal.get(Calendar.YEAR));
				entity.setYf(cal.get(Calendar.MONTH) + 1);
				entity.setDwxx(dwxxDao.getById(company.getId()));
				entity.setClmc(clmcDao.getById(cpIdList.get(cp)));
				entity.setTjfs(type.value());
			}

			entity.setZt(status.ordinal());
			entity.setCl(Util.toDoubleNull(data.getJSONArray(cp).getString(1)));
			
			clylwcqkDao.merge(entity);
		}
		
		return err;
	}
	
	@Override
	public ErrorCode saveClylwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data) {

		return entryClylwcqk(d, company, type, data, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitClylwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data) {

		return entryClylwcqk(d, company, type, data, ZBStatus.SUBMITTED);		
	}
	
	@Override
	public ZBStatus getClylwcqkStatus(Date d, Company comp, SbdczclwcqkType type) {
		return ZBStatus.SAVED;	
	}
}
