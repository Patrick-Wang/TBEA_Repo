package com.tbea.ic.operation.service.wgcpqk.yclbfqk;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.wgcpqk.WgcpqkType;
import com.tbea.ic.operation.model.dao.wgcpqk.yclbfqk.YclbfqkDao;
import com.tbea.ic.operation.model.dao.wgcpqk.yclbfqk.YclbfqkDaoImpl;
import com.tbea.ic.operation.model.dao.wgcpqk.yclbfqk.dwxxrefclmc.DwxxRefClmcDao;
import com.tbea.ic.operation.model.entity.wgcpqk.YclbfqkEntity;
import com.tbea.ic.operation.model.entity.wgcpqk.wgcpylnlspcs.WgcpylnlspcsEntity;
import com.tbea.ic.operation.model.entity.wgcpqk.yclbfqk.DwxxRefClmcEntity;

@Service(YclbfqkServiceImpl.NAME)
@Transactional("transactionManager")
public class YclbfqkServiceImpl implements YclbfqkService {
	@Resource(name = YclbfqkDaoImpl.NAME)
	YclbfqkDao yclbfqkDao;

	@Autowired
	DwxxRefClmcDao dwrefclDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	public final static String NAME = "YclbfqkServiceImpl";

	@Override
	public List<List<String>> getYclbfqk(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ecCur = new EasyCalendar(d);
		EasyCalendar ec = new EasyCalendar(d);
		ec.addYear(-1);
		
		List<DwxxRefClmcEntity> clmcs = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			clmcs = dwrefclDao.getByCompany(company);
		}else{
			if (company.getType() == CompanyType.BYQCY){
				clmcs = dwrefclDao.getByCompany(companyManager.getBMDBOrganization().getCompany(CompanyType.SBGS));
			}else{
				clmcs = dwrefclDao.getByCompany(companyManager.getBMDBOrganization().getCompany(CompanyType.LLGS));
			}			
		}

		for (int i = 0; i < clmcs.size(); ++i) {
			List<String> list = new ArrayList<String>();
			Util.resize(list, 17);
			list.set(0, clmcs.get(i).getClmc().getName());
			result.add(list);
		}

		for (int i = 0; i < 13; ++i) {

			List<YclbfqkEntity> entities = null;
			if (companyManager.getBMDBOrganization().owns(company)){
				entities = yclbfqkDao.getByDate(ec.getDate(), company);
			}else{
				entities = yclbfqkDao.getSumByDate(ec.getDate(), company.getSubCompanies());		
			}
			
			for (YclbfqkEntity entity : entities) {
				List<String> list = null;
				for (int j = 0; j < clmcs.size(); ++j) {
					if (clmcs.get(j).getClmc().getId() == entity.getClid()) {
						list = result.get(j);
						break;
					}
				}

				if (null != list) {
					if (ec.getMonth() == ecCur.getMonth()
							&& ec.getYear() == ecCur.getYear()) {
						list.set(1, "" + entity.getLyl());
						list.set(2, "" + entity.getFl());
						list.set(3,	"" + Util.division(entity.getFl(),
												entity.getLyl()));
					}
					list.set(i + 4,
							"" + Util.division(entity.getFl(), entity.getLyl()));
				}
			}

			ec.addMonth(1);
		}
		return result;
	}

	private ErrorCode entryYclbfqk(Date d, JSONArray data, Company company,
			ZBStatus zt) {
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			YclbfqkEntity entity = yclbfqkDao.getByDate(d, company, Integer.valueOf(row.getString(0)));
			if (null == entity){
				entity = new YclbfqkEntity();
				EasyCalendar ec = new EasyCalendar(d);
				entity.setClid(Integer.valueOf(row.getString(0)));
				entity.setNf(ec.getYear());
				entity.setYf(ec.getMonth());
				entity.setDwid(company.getId());
				entity.setZt(zt.ordinal());
			}
			entity.setLyl(Util.toDoubleNull(row.getString(1)));
			entity.setFl(Util.toDoubleNull(row.getString(2)));
			yclbfqkDao.merge(entity);
		}
		return ErrorCode.OK;
	}

	@Override
	public ErrorCode submitYclbfqk(Date d, JSONArray data, Company company) {

		return entryYclbfqk(d, data, company, ZBStatus.SUBMITTED);
	}

	@Override
	public ErrorCode saveYclbfqk(Date d, JSONArray data, Company company) {
		return entryYclbfqk(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public List<List<String>> getYclbfqkEntry(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<DwxxRefClmcEntity> clmcs = dwrefclDao.getByCompany(company);
		for (int i = 0; i < clmcs.size(); ++i) {
			List<String> list = new ArrayList<String>();
			Util.resize(list, 4);
			list.set(0, "" + clmcs.get(i).getClmc().getId());
			list.set(1, clmcs.get(i).getClmc().getName());
			result.add(list);
		}

		List<YclbfqkEntity> entities = yclbfqkDao.getByDate(d, company);
		for (YclbfqkEntity entity : entities) {
			for (int j = 0; j < clmcs.size(); ++j) {
				if (clmcs.get(j).getClmc().getId() == entity.getClid()) {
					List<String> list = result.get(j);
					list.set(2, "" + entity.getLyl());
					list.set(3, "" + entity.getFl());
					break;
				}
			}
		}

		return result;
	}

}
