package com.tbea.ic.operation.service.sbdczclwcqk.cpczwcqk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;
import com.tbea.ic.operation.controller.servlet.wgcpqk.WgcpqkType;
import com.tbea.ic.operation.model.dao.identifier.ylfx.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.ylfx.CpmcDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpczwcqk.CpczwcqkDaoImpl;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpczwcqk.CpczwcqkDao;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.CpczwcqkEntity;
import com.tbea.ic.operation.model.entity.wgcpqk.wgcpylnlspcs.WgcpylnlspcsEntity;
import com.tbea.ic.operation.service.sbdczclwcqk.cpczwcqk.CpczwcqkService;
import com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs.WGCPYLNL_BYQ_CPFL_T1_Type;
import com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs.WGCPYLNL_BYQ_CPFL_Type;
import com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs.WGCPYLNL_BYQ_DYDJ_Type;
import com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs.WGCPYLNL_BYQ_ZH_Type;
import com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs.WGCPYLNL_XL_CPFL_Type;
import com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs.WGCPYLNL_XL_ZH_Type;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(CpczwcqkServiceImpl.NAME)
@Transactional("transactionManager")

public class CpczwcqkServiceImpl implements CpczwcqkService {
	@Resource(name=CpczwcqkDaoImpl.NAME)
	CpczwcqkDao cpczwcqkDao;
	
	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;

	public final static String NAME = "CpczwcqkServiceImpl";
	
	private List<Integer> getCpIdList(SbdczclwcqkType type) {
		
		List<Integer> cpIdList = new ArrayList<Integer>();
		
		switch (type) {

		case SBDCZCLWCQK_CZ_BYQ: {
			for (SBDCZCLWCQK_CZ_BYQ_Type cp : SBDCZCLWCQK_CZ_BYQ_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case SBDCZCLWCQK_CZ_XL: {
			for (SBDCZCLWCQK_CZ_XL_Type cp : SBDCZCLWCQK_CZ_XL_Type.values()) {
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
	public List<List<String>> getCpczwcqk(Date d, Company company, SbdczclwcqkType type) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.YEAR, -1);
			cal.add(Calendar.MONTH, 1);
			
			List<CpczwcqkEntity> entities= cpczwcqkDao.getByDate(new Date(cal.getTimeInMillis()), d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			for (int i = 0; i < 12; ++i){

				Boolean bFind = false;
				for (CpczwcqkEntity entity : entities){
					if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
						bFind = true;
						oneLine.add("" + entity.getCz());
						
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
	public List<List<String>> getCpczwcqkEntry(Date d, Company company, SbdczclwcqkType type) {
		return null;
	}

	@Override
	public ErrorCode saveCpczwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data) {
		return null;
	}

	@Override
	public ErrorCode submitCpczwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data) {
		return null;		
	}
	
	@Override
	public ZBStatus getCpczwcqkStatus(Date d, Company comp, SbdczclwcqkType type) {
		return null;		
	}

}
