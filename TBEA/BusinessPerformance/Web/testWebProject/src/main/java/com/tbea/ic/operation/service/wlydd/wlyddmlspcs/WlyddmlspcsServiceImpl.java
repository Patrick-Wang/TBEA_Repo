package com.tbea.ic.operation.service.wlydd.wlyddmlspcs;

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
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs.WlyddmlspcsDao;
import com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs.WlyddmlspcsDaoImpl;
import com.tbea.ic.operation.model.entity.wlydd.wlyddmslspcs.WlyddmlspcsEntity;
import com.util.tools.MathUtil;

import net.sf.json.JSONArray;


@Service(WlyddmlspcsServiceImpl.NAME)
@Transactional("transactionManager")
public class WlyddmlspcsServiceImpl implements WlyddmlspcsService {
	@Resource(name = WlyddmlspcsDaoImpl.NAME)
	WlyddmlspcsDao wlyddmlspcsDao;
	
	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	
	public final static String NAME = "WlyddmlspcsServiceImpl";
	private List<Integer> getHjList(WlyddType type) {
		
		List<Integer> hjList = new ArrayList<Integer>();
	    
		switch (type) {

		case YLFX_WLYMLSP_BYQ_ZXYW: {
			hjList.add(MLSPCS_BYQ_ZH_Type.MLSPCS_BYQ_ZH_CTXM_GN.value());
			hjList.add(MLSPCS_BYQ_ZH_Type.MLSPCS_BYQ_ZH_CTXM_GJ.value());
			hjList.add(MLSPCS_BYQ_ZH_Type.MLSPCS_BYQ_ZH_WLMY.value());
			hjList.add(MLSPCS_BYQ_ZH_Type.MLSPCS_BYQ_ZH_FWL.value());
			hjList.add(MLSPCS_BYQ_ZH_Type.MLSPCS_BYQ_ZH_QT.value());
			break;
		}
		case YLFX_WLYMLSP_XL_ZXYW: {
			hjList.add(MLSPCS_XL_ZH_Type.MLSPCS_XL_ZH_CTXM_GN.value());
			hjList.add(MLSPCS_XL_ZH_Type.MLSPCS_XL_ZH_CTXM_GJ.value());
			hjList.add(MLSPCS_XL_ZH_Type.MLSPCS_XL_ZH_WLMY.value());
			hjList.add(MLSPCS_XL_ZH_Type.MLSPCS_XL_ZH_FWL.value());
			hjList.add(MLSPCS_XL_ZH_Type.MLSPCS_XL_ZH_QT.value());
			break;
		}

		case YLFX_WLYMLSP_BYQ_ZZY: {
			hjList.add(MLSPCS_BYQ_ZZY_Type.MLSPCS_BYQ_DYDJ_JLBYQ.value());
			hjList.add(MLSPCS_BYQ_ZZY_Type.MLSPCS_BYQ_DYDJ_ZLBYQ.value());
			hjList.add(MLSPCS_BYQ_ZZY_Type.MLSPCS_BYQ_DYDJ_DKQ.value());
			hjList.add(MLSPCS_BYQ_ZZY_Type.MLSPCS_BYQ_CPFL_GSBYQ.value());
			hjList.add(MLSPCS_BYQ_ZZY_Type.MLSPCS_BYQ_CPFL_77.value());
			hjList.add(MLSPCS_BYQ_ZZY_Type.MLSPCS_BYQ_CPFL_81.value());
			hjList.add(MLSPCS_BYQ_ZZY_Type.MLSPCS_BYQ_CPFL_TZBYQ.value());
			hjList.add(MLSPCS_BYQ_ZZY_Type.MLSPCS_BYQ_CPFL_YSL.value());
			break;
		}
		default: 
			break;

		}
		
		return hjList;
	}

	
	public static List<Integer> getCpIdList(WlyddType type) {
		
		List<Integer> cpIdList = new ArrayList<Integer>();
	    
		switch (type) {

		case YLFX_WLYMLSP_BYQ_ZXYW: {
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
		case YLFX_WLYMLSP_XL_ZXYW: {
			for (MLSPCS_XL_ZH_Type cp : MLSPCS_XL_ZH_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WLYMLSP_XL_ZZY: {
			for (MLSPCS_XL_CPFL_Type cp : MLSPCS_XL_CPFL_Type.values()) {
				cpIdList.add(cp.value());
			}
			break;
		}
		case YLFX_WLYMLSP_BYQ_ZZY: {
			for (MLSPCS_BYQ_ZZY_Type cp : MLSPCS_BYQ_ZZY_Type.values()) {
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
		Double ret = MathUtil.mul(MathUtil.division(MathUtil.minus(sr, cb),sr), 100d);
		if (null != ret){
			return String.format("%.1f", ret) + "%";
		}
		return null;
	}
	

	
	@Override
	public List<List<String>> getWlyddmlspcs(Date d, Company company, WlyddType type) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<Integer> cpIdList = getCpIdList(type);
		List<List<Double>> finalListTemp = new ArrayList<List<Double>>();
		List<Boolean> finalListNullOrNot = new ArrayList<Boolean>();
		List<Integer> hjList = getHjList(type);
		for (int i = 0; i < 13; ++i){
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
			//cal.add(Calendar.MONTH, 1);
			List<WlyddmlspcsEntity> entities = null;
			if (companyManager.getBMDBOrganization().owns(company)){
				entities = wlyddmlspcsDao.getByDate(new Date(cal.getTimeInMillis()), d, company, type, cpIdList.get(cp));
			}else{
				if (company.getType() == CompanyType.SBDCYJT){
					entities = wlyddmlspcsDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies(), WlyddType.YLFX_WLYMLSP_BYQ_ZXYW, WlyddType.YLFX_WLYMLSP_XL_ZXYW, cpIdList.get(cp));
				}else{
					entities = wlyddmlspcsDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies(), type, cpIdList.get(cp));
				}
				
			}
		//	List<WlyddmlspcsEntity> entities= wlyddmlspcsDao.getByDate(new Date(cal.getTimeInMillis()), d, company, type, cpIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(cpmcDao.getById(cpIdList.get(cp)).getName());
			
			for (int i = 0; i < 13; ++i){

				Boolean bFind = false;
				for (WlyddmlspcsEntity entity : entities){
					if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
						bFind = true;
						oneLine.add("" + getMll(entity.getCb(), entity.getSr()));


						if (hjList.isEmpty() || hjList.contains(entity.getCpmc().getId())){
							finalListTemp.get(i).set(0, MathUtil.sum(finalListTemp.get(i).get(0), entity.getCb()));
							finalListTemp.get(i).set(1, MathUtil.sum(finalListTemp.get(i).get(1), entity.getSr()));
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
		for (int i = 0; i < 13; ++i){
		
			if (!finalListNullOrNot.get(i)) {

				finalLine.add("" + getMll(finalListTemp.get(i).get(0), finalListTemp.get(i).get(1)));
			} else {
				
				finalLine.add("null");
			}
		}
		
		if (type != WlyddType.YLFX_WLYMLSP_BYQ_CPFL) {

			result.add(finalLine);
		}
		
		return result;
	}

	@Override
	public List<List<String>> getWlyddmlspcsEntry(Date d, Company company, WlyddType type) {

		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			
			WlyddmlspcsEntity entity = wlyddmlspcsDao.getByDate(d, company, type, cpIdList.get(cp));
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

	ErrorCode entryWlyddmlspcs(Date d, Company company, WlyddType type, JSONArray data, ZBStatus status) {

		ErrorCode err = ErrorCode.OK;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<Integer> cpIdList = getCpIdList(type);
		
		for (int cp = 0; cp < cpIdList.size(); cp++) {
			WlyddmlspcsEntity entity= wlyddmlspcsDao.getByDate(d, company, type, cpIdList.get(cp));
			
			if (null == entity){
				entity = new WlyddmlspcsEntity();

				entity.setNf(cal.get(Calendar.YEAR));
				entity.setYf(cal.get(Calendar.MONTH) + 1);
				entity.setDwxx(dwxxDao.getById(company.getId()));
				entity.setCpmc(cpmcDao.getById(cpIdList.get(cp)));
				entity.setTjfs(type.value());
			}

			entity.setZt(status.ordinal());
			entity.setCb(Util.toDoubleNull(data.getJSONArray(cp).getString(0)));
			entity.setSr(Util.toDoubleNull(data.getJSONArray(cp).getString(1)));
			
			wlyddmlspcsDao.merge(entity);
		}
		
		return err;
	}
	
	
	@Override
	public ErrorCode saveWlyddmlspcs(Date d, Company company, WlyddType type, JSONArray data){

		return entryWlyddmlspcs(d, company, type, data, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitWlyddmlspcs(Date d, Company company, WlyddType type, JSONArray data) {

		return entryWlyddmlspcs(d, company, type, data, ZBStatus.SUBMITTED);
	}
	
	@Override
	public ZBStatus getWlyddmlspcsStatus(Date d, Company comp, WlyddType type) {
		return ZBStatus.SAVED;
	}
}
