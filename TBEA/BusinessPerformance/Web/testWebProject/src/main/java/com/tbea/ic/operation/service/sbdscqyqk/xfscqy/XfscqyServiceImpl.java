package com.tbea.ic.operation.service.sbdscqyqk.xfscqy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.identifier.scfxgb.hy.HyDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.scfxgb.hy.HyDao;

import javax.annotation.Resource;

import com.tbea.ic.operation.model.dao.sbdscqyqk.xfscqy.XfscqyDaoImpl;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfscqy.XfscqyDao;
import com.tbea.ic.operation.model.entity.identifier.scfxgb.HyEntity;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfscqyEntity;
import com.tbea.ic.operation.service.sbdscqyqk.xfscqy.XfscqyService;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(XfscqyServiceImpl.NAME)
@Transactional("transactionManager")
public class XfscqyServiceImpl implements XfscqyService {
	@Resource(name=HyDaoImpl.NAME)
	HyDao hyDao;

	@Resource(name=XfscqyDaoImpl.NAME)
	XfscqyDao xfscqyDao;

	public final static String NAME = "XfscqyServiceImpl";

	@Override
	public List<List<String>> getXfscqy(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		
		List<HyEntity> hys = hyDao.getAll();
		for (HyEntity entity: hys){
			List<String> list = new ArrayList<String>();
			Util.resize(list, 13);
			list.set(0, entity.getName());
			result.add(list);
		}
		List<String> listHj = new ArrayList<String>();
		Util.resize(listHj, 13);
		listHj.set(0, "合计");
		result.add(listHj);
		for (int i = 0; i < 12; ++i){
			List<XfscqyEntity> entities = xfscqyDao.getByDate(new Date(cal.getTimeInMillis()), company);
			Double sum = null;
			for (XfscqyEntity entity: entities){
				result.get(entity.getHyid()).set(i + 1, "" + entity.getQye());
				sum = Util.sum(new Double[]{sum, entity.getQye()});
			}
			listHj.set(i + 1, "" + sum);
			cal.add(Calendar.MONTH, 1);
		}
		return result;
	}

	@Override
	public List<List<String>> getXfscqyEntry(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<HyEntity> hys = hyDao.getAll();
		for (HyEntity entity: hys){
			List<String> list = new ArrayList<String>();
			Util.resize(list, 2);
			list.set(0, entity.getName());
			result.add(list);
		}
		
		List<XfscqyEntity> entities = xfscqyDao.getByDate(d, company);
		for (XfscqyEntity entity: entities){
			result.get(entity.getHyid()).set(1, "" + entity.getQye());
		}
		return result;
	}

	private ErrorCode entryXfscqy(Date d, JSONArray data, Company company, ZBStatus zt){
		for (int i = 0; i < data.size(); ++i){
			XfscqyEntity entity = xfscqyDao.getByDate(d, company, i);
			if (null == entity){
				entity = new XfscqyEntity();
				entity.setDwid(company.getId());
				entity.setHyid(i);
				entity.setZt(zt.ordinal());
				entity.setNf(1); 
			}
		}
		
		
		return ErrorCode.OK;
	}
	
	@Override
	public ErrorCode saveXfscqy(Date d, JSONArray data, Company company) {
		return entryXfscqy(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitXfscqy(Date d, JSONArray data, Company company) {
		return entryXfscqy(d, data, company, ZBStatus.SUBMITTED);
	}

}
