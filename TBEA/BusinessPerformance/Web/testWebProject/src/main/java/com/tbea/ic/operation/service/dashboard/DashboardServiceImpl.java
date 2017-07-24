package com.tbea.ic.operation.service.dashboard;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;

@Service
@Transactional("transactionManager")
public class DashboardServiceImpl implements DashboardService {

	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	SJZBDao sjzbDao;
	
	CompanyType[] sbdcomps = new CompanyType[]{
			CompanyType.SBGS,
			CompanyType.HBGS,
			CompanyType.XBC,
			CompanyType.LLGS,
			CompanyType.XLC,
			CompanyType.DLGS
	};
	
	@Override
	public List<Double> getScqyLjzb(Date date) {
		List<Double> sjzbs = sjzbDao.getLjSjzbs(BMDepartmentDB.getJydw(companyManager), new Integer[]{
				GSZB.HTQYE48.value(),
				GSZB.HTQY_ZZYQY290.value(),
				GSZB.JCFWYW_HGCHJCXS_QY299.value(),
				GSZB.HTQY_QT304.value()
		}, date);
		return sjzbs;
	}

	@Override
	public List<Double> getScqyZtydzb(Integer nf) {
		List<Double> ret = new ArrayList<Double>();
		for (int i = 0; i < 12; ++i){
			ret.add(sjzbDao.getYdSjzbs(BMDepartmentDB.getJydw(companyManager), GSZB.HTQYE48.value(), nf, i + 1));
		}
		return ret;
	}
	

	private String getNumber0(String str){
		if ("null".equals(str)){
			return "--";
		}
		return String.format("%.0f",  (Double.valueOf(str)));		
	}
	@Override
	public List<String[]> getSbdgnscqye(Date date) {
		List<Company> sbds = BMDepartmentDB.valueOf(companyManager, sbdcomps);
		List<Object[]> ret = sjzbDao.getZbpm(sbds, new Integer[]{
				GSZB.QZ_GNQY291.value(),
				GSZB.QZ_JCFWYWGNQY300.value(),
				GSZB.QZ_GNQY305.value()
		}, date);
		List<String[]> result = new ArrayList<String[]>();
		for (Object[] zbpm : ret){
			result.add(new String[]{
				(String) zbpm[0], getNumber0((String) (zbpm[1] + ""))
			});
			for (int i = 0; i < sbds.size(); ++i){
				if (sbds.get(i).getName().equals((String) zbpm[0])){
					sbds.remove(i);
					break;
				}
			}
		}
		
		for (int i = 0; i < sbds.size(); ++i){
			result.add(new String[]{sbds.get(i).getName(), "--"});
		}
		
		return result;
	}

	@Override
	public List<String[]> getSbdgjscqye(Date date) {
		List<Company> sbds = BMDepartmentDB.valueOf(companyManager, sbdcomps);
		List<Object[]> ret = sjzbDao.getZbpm(sbds, new Integer[]{
				GSZB.QZ_GJQY295.value(),
				GSZB.QZ_JCFWYWGJQY302.value(),
				GSZB.QZ_GJQY306.value()
		}, date);
		List<String[]> result = new ArrayList<String[]>();
		for (Object[] zbpm : ret){
			result.add(new String[]{
				(String) zbpm[0], getNumber0((String) (zbpm[1] + ""))
			});
			for (int i = 0; i < sbds.size(); ++i){
				if (sbds.get(i).getName().equals((String) zbpm[0])){
					sbds.remove(i);
					break;
				}
			}
		}
		
		for (int i = 0; i < sbds.size(); ++i){
			result.add(new String[]{sbds.get(i).getName(), "--"});
		}
		
		return result;
	}

	@Override
	public Double getSbdztqye(Date current) {
		List<Company> sbds = BMDepartmentDB.valueOf(companyManager, sbdcomps);
		return sjzbDao.getZbzt(sbds, GSZB.HTQYE48.value(), current);
	}

	

}
