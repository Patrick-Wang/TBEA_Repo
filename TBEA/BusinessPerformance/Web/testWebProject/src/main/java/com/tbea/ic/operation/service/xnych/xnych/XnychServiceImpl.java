package com.tbea.ic.operation.service.xnych.xnych;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.xnych.xnych.XnychDao;
import com.tbea.ic.operation.model.dao.xnych.xnych.XnychDaoImpl;
import com.tbea.ic.operation.model.entity.xnych.XnychEntity;

@Service(XnychServiceImpl.NAME)
@Transactional("transactionManager")
public class XnychServiceImpl implements XnychService {
	@Resource(name=XnychDaoImpl.NAME)
	XnychDao xnychDao;

	public final static String NAME = "XnychServiceImpl";

	@Override
	public List<List<String>> getXnych(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<XnychEntity> entities = xnychDao.getByDate(d, company);
		for (XnychEntity entity : entities){
			result.add(toList(entity));
		}
		return result;
	}

	private List<String> toList(XnychEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getZmjehj());
		list.add("" + entity.getEpcxmch());
		list.add("" + entity.getYzrzyxmch());
		list.add("" + entity.getWzrzyxmch());
		list.add("" + entity.getYzgzyxmch());
		list.add("" + entity.getZbhqqfy());
		return list;
	}
	
	private List<String> toEntryList(XnychEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getId());
		list.add("" + entity.getZmjehj());
		list.add("" + entity.getEpcxmch());
		list.add("" + entity.getYzrzyxmch());
		list.add("" + entity.getWzrzyxmch());
		list.add("" + entity.getYzgzyxmch());
		list.add("" + entity.getZbhqqfy());
		return list;
	}

	@Override
	public List<List<String>> getXnychEntry(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<XnychEntity> entities = xnychDao.getByDate(d, company);
		for (XnychEntity entity : entities){
			result.add(toEntryList(entity));
		}
		return result;
	}
	ErrorCode entryXnych(Date d, JSONArray data, Company company, ZBStatus zt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		ErrorCode err = ErrorCode.OK;
		int index = 0;
		for (int i = 0;i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			XnychEntity entity = null;
			if (!row.getString(0).contains("add")){
				entity = xnychDao.getById(Integer.valueOf(row.getString(0)));
			}
			if (null == entity){
				entity = new XnychEntity();
				entity.setNf(cal.get(Calendar.YEAR));
				entity.setYf(cal.get(Calendar.MONTH) + 1);
				entity.setDwid(company.getId());
			}
			index = 1;
			entity.setZt(zt.ordinal());
			entity.setZmjehj(Util.toDoubleNull(row.getString(index++)));
			entity.setEpcxmch(Util.toDoubleNull(row.getString(index++)));
			entity.setYzrzyxmch(Util.toDoubleNull(row.getString(index++)));
			entity.setWzrzyxmch(Util.toDoubleNull(row.getString(index++)));
			entity.setYzgzyxmch(Util.toDoubleNull(row.getString(index++)));
			entity.setZbhqqfy(Util.toDoubleNull(row.getString(index++)));
			xnychDao.merge(entity);
		}
		
		return err;
	}
	@Override
	public ErrorCode saveXnych(Date d, JSONArray data, Company company) {
		return entryXnych(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitXnych(Date d, JSONArray data, Company company) {
		return entryXnych(d, data, company, ZBStatus.SUBMITTED);
	}

}
