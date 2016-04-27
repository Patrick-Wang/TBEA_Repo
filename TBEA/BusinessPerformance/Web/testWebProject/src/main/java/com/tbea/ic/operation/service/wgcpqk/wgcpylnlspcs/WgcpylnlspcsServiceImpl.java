package com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.wgcpqk.WgcpqkType;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.wgcpqk.wgcpylnlspcs.WgcpylnlspcsDao;
import com.tbea.ic.operation.model.dao.wgcpqk.wgcpylnlspcs.WgcpylnlspcsDaoImpl;
import com.tbea.ic.operation.model.entity.wgcpqk.wgcpylnlspcs.WgcpylnlspcsEntity;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDaoImpl;


@Service(WgcpylnlspcsServiceImpl.NAME)
@Transactional("transactionManager")
public class WgcpylnlspcsServiceImpl implements WgcpylnlspcsService {
	@Resource(name = WgcpylnlspcsDaoImpl.NAME)
	WgcpylnlspcsDao wgcpylnlspcsDao;
	
	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	public final static String NAME = "WgcpylnlspcsServiceImpl";

	private List<Integer> getCpIdList(WgcpqkType type) {
		
		List<Integer> cpIdList = new ArrayList<Integer>();
	    
		switch (type) {

		case YLFX_WGCPYLNL_BYQ_ZH: {
			for (WGCPYLNL_BYQ_ZH_Type cp : WGCPYLNL_BYQ_ZH_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WGCPYLNL_BYQ_DYDJ: {
			for (WGCPYLNL_BYQ_DYDJ_Type cp : WGCPYLNL_BYQ_DYDJ_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WGCPYLNL_BYQ_CPFL: {
			for (WGCPYLNL_BYQ_CPFL_Type cp : WGCPYLNL_BYQ_CPFL_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WGCPYLNL_BYQ_CPFL_T1: {
			for (WGCPYLNL_BYQ_CPFL_T1_Type cp : WGCPYLNL_BYQ_CPFL_T1_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WGCPYLNL_XL_ZH: {
			for (WGCPYLNL_XL_ZH_Type cp : WGCPYLNL_XL_ZH_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WGCPYLNL_XL_CPFL: {
			for (WGCPYLNL_XL_CPFL_Type cp : WGCPYLNL_XL_CPFL_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		default: 
			break;

		}
		
		return cpIdList;
	}

	private String getMll(Double cb, Double sr) {
		
		return String.format("%.1f", (Double.valueOf((sr - cb)/sr)) * 100) + "%";
	}
	
	@Override
	public List<List<String>> getWgcpylnlspcs(Date d, Company company, WgcpqkType type) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<Integer> cpIdList = getCpIdList(type);
		List<List<Double>> finalListTemp = new ArrayList<List<Double>>();
		List<Boolean> finalListNullOrNot = new ArrayList<Boolean>();
		
		for (int i = 0; i < 12; ++i){
			List<Double> tmp = new ArrayList<Double>();
			tmp.add(0.0);
			tmp.add(0.0);
			finalListTemp.add(tmp);
			finalListNullOrNot.add(true);
		}
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.YEAR, -1);
			cal.add(Calendar.MONTH, 1);
			
			List<WgcpylnlspcsEntity> entities= wgcpylnlspcsDao.getByDate(new Date(cal.getTimeInMillis()), d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			for (int i = 0; i < 12; ++i){

				Boolean bFind = false;
				for (WgcpylnlspcsEntity entity : entities){
					if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
						bFind = true;
						oneLine.add("" + getMll(entity.getCb(), entity.getSr()));
						
						finalListTemp.get(i).set(0, finalListTemp.get(i).get(0) + entity.getCb());
						finalListTemp.get(i).set(1, finalListTemp.get(i).get(1) + entity.getSr());
						
						finalListNullOrNot.set(i, false);
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
		for (int i = 0; i < 12; ++i){
		
			if (!finalListNullOrNot.get(i)) {

				finalLine.add("" + getMll(finalListTemp.get(i).get(0), finalListTemp.get(i).get(1)));
			} else {
				
				finalLine.add("null");
			}
		}
		
		if (type != WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL && type != WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL_T1) {

			result.add(finalLine);
		}
		
		return result;
	}

	@Override
	public List<List<String>> getWgcpylnlspcsEntry(Date d, Company company, WgcpqkType type) {

		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			WgcpylnlspcsEntity entity = wgcpylnlspcsDao.getByDate(d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();
			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			if (entity == null) {
				oneLine.add("");
				oneLine.add("");
			} else {
				Boolean bFind = false;

				if (entity.getNf() == cal.get(Calendar.YEAR)
						&& entity.getYf() == cal.get(Calendar.MONTH) + 1) {
					bFind = true; 
					oneLine.add("" + entity.getCb());
					oneLine.add("" + entity.getSr());
				}

				if (!bFind) {
					oneLine.add("");
					oneLine.add("");
				}

			}				
			result.add(oneLine);
		}		
		
		return result;
	}

	ErrorCode entryWgcpylnlspcs(Date d, Company company, WgcpqkType type, JSONArray data, ZBStatus status) {

		ErrorCode err = ErrorCode.OK;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			WgcpylnlspcsEntity entity= wgcpylnlspcsDao.getByDate(d, company, type, cpIdList.get(cp));
			
			if (null == entity){
				entity = new WgcpylnlspcsEntity();

				entity.setNf(cal.get(Calendar.YEAR));
				entity.setYf(cal.get(Calendar.MONTH) + 1);
				entity.setDwxx(dwxxDao.getById(company.getId()));
				entity.setCpmc(cpmcDao.getById(cpIdList.get(cp)));
				entity.setTjfs(type.value());
			}

			entity.setZt(status.ordinal());
			entity.setCb(Util.toDoubleNull(data.getJSONArray(cp).getString(1)));
			entity.setSr(Util.toDoubleNull(data.getJSONArray(cp).getString(2)));
			
			wgcpylnlspcsDao.merge(entity);
		}
		
		return err;
	}
	
	
	@Override
	public ErrorCode saveWgcpylnlspcs(Date d, Company company, WgcpqkType type, JSONArray data){

		return entryWgcpylnlspcs(d, company, type, data, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitWgcpylnlspcs(Date d, Company company, WgcpqkType type, JSONArray data) {

		return entryWgcpylnlspcs(d, company, type, data, ZBStatus.SUBMITTED);
	}
	
	@Override
	public ZBStatus getWgcpylnlspcsStatus(Date d, Company comp, WgcpqkType type) {
		return ZBStatus.SAVED;
	}
}
