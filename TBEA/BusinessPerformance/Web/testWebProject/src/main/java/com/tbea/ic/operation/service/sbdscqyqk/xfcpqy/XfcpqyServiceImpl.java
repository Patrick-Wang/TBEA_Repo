package com.tbea.ic.operation.service.sbdscqyqk.xfcpqy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.sbdscqyqk.SbdscqyqkType;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy.XfcpqyDao;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy.XfcpqyDaoImpl;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfcpqyEntity;

import net.sf.json.JSONArray;

@Service(XfcpqyServiceImpl.NAME)
@Transactional("transactionManager")
public class XfcpqyServiceImpl implements XfcpqyService {
	
	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	@Resource(name = XfcpqyDaoImpl.NAME)
	XfcpqyDao xfcpqyDao;

	public final static String NAME = "XfcpqyServiceImpl";

	
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
		List<Double> finalListTemp = new ArrayList<Double>();
		List<Boolean> finalListNullOrNot = new ArrayList<Boolean>();
		
		for (int i = 0; i < 12; ++i){
			finalListTemp.add(0.0);
			finalListNullOrNot.add(true);
		}
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.YEAR, -1);
			cal.add(Calendar.MONTH, 1);
			
			List<XfcpqyEntity> entities= xfcpqyDao.getByDate(new Date(cal.getTimeInMillis()), d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			for (int i = 0; i < 12; ++i){

				Boolean bFind = false;
				for (XfcpqyEntity entity : entities){
					if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
						bFind = true;
						oneLine.add("" + entity.getQye());
						
						finalListTemp.set(i, finalListTemp.get(i) + entity.getQye());
						
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

				finalLine.add("" + finalListTemp.get(i));
			} else {
				
				finalLine.add("null");
			}
		}
		
		if (type == SbdscqyqkType.YLFX_WGCPYLNL_XL ) {

			result.add(finalLine);
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

	

}
