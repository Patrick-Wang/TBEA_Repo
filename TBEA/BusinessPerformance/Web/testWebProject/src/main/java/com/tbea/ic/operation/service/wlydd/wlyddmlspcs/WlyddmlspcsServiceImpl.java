package com.tbea.ic.operation.service.wlydd.wlyddmlspcs;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Hashtable;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm.JykcxmDao;
import com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm.JykcxmDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs.WlyddmlspcsDao;
import com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs.WlyddmlspcsDaoImpl;
import com.tbea.ic.operation.model.entity.wlydd.wlyddmslspcs.WlyddmlspcsEntity;
import com.tbea.ic.operation.model.dao.identifier.ylfx.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.ylfx.CpmcDaoImpl;


@Service(WlyddmlspcsServiceImpl.NAME)
@Transactional("transactionManager")
public class WlyddmlspcsServiceImpl implements WlyddmlspcsService {
	@Resource(name = WlyddmlspcsDaoImpl.NAME)
	WlyddmlspcsDao wlyddmlspcsDao;
	
	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	public final static String NAME = "WlyddmlspcsServiceImpl";

	private List<Integer> getCpIdList(WlyddType type) {
		
		List<Integer> cpIdList = new ArrayList<Integer>();
	    
		switch (type) {

		case YLFX_WLYMLSP_BYQ_ZH: {
			for (MLSPCS_BYQ_ZH_Type cp : MLSPCS_BYQ_ZH_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WLYMLSP_BYQ_DYDJ: {
			for (MLSPCS_BYQ_DYDJ_Type cp : MLSPCS_BYQ_DYDJ_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WLYMLSP_BYQ_CPFL: {
			for (MLSPCS_BYQ_CPFL_Type cp : MLSPCS_BYQ_CPFL_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WLYMLSP_XL_ZH: {
			for (MLSPCS_XL_ZH_Type cp : MLSPCS_XL_ZH_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WLYMLSP_XL_CPFL: {
			for (MLSPCS_XL_CPFL_Type cp : MLSPCS_XL_CPFL_Type.values()) {
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
	public List<List<String>> getWlyddmlspcs(Date d, Company company, WlyddType type) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			List<WlyddmlspcsEntity> entities= wlyddmlspcsDao.getByDate(new Date(cal.getTimeInMillis()), d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			for (int i = 0; i < 12; ++i){

				for (WlyddmlspcsEntity entity : entities){
					if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
						
						oneLine.add("" + (entity.getSr() - entity.getCb()));
						
						entities.remove(entity);
						break;
					}
				}
				if (result.get(result.size() - 1).isEmpty()){
					Util.resize(result.get(result.size() - 1), 7);
				}
				cal.add(Calendar.MONTH, 1);
			}
			
			result.add(oneLine);
		}		
		
		return result;
	}

	@Override
	public List<List<String>> getWlyddmlspcsEntry(Date d, Company company, WlyddType type) {

		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			List<WlyddmlspcsEntity> entities= wlyddmlspcsDao.getByDate(new Date(cal.getTimeInMillis()), d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();
			
			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			for (int i = 0; i < 12; ++i){
				Boolean bFind = false;
				for (WlyddmlspcsEntity entity : entities){
					if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
						bFind = true;
						oneLine.add("" + (entity.getSr() - entity.getCb()));
						entities.remove(entity);
						break;
					}
				}
				
				if (!bFind) {
					oneLine.add("");
				}
				
				cal.add(Calendar.MONTH, 1);
			}
			
			result.add(oneLine);
		}		
		
		return result;
	}

	@Override
	public ErrorCode saveWlyddmlspcs(Date d, Company company, WlyddType type, JSONArray data){

		return null;
	}

	@Override
	public ErrorCode submitWlyddmlspcs(Date d, Company company, WlyddType type, JSONArray data) {

		return null;
	}
	
	@Override
	public ZBStatus getWlyddmlspcsStatus(Date d, Company comp, WlyddType type) {

		return null;
	}
}
