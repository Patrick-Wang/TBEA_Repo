package com.tbea.ic.operation.service.cpzlqk.xlbhgcpmx;

import java.sql.Date;
import java.sql.Timestamp;
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
import com.tbea.ic.operation.model.dao.cpzlqk.dwmc.DwmcDao;
import com.tbea.ic.operation.model.dao.cpzlqk.xlbhgwtmx.XlBhgwtmxDao;
import com.tbea.ic.operation.model.dao.cpzlqk.xlbhgwtmx.XlBhgwtmxDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlbhglb.XlBhglbDao;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlbhglb.XlBhglbDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlzrlb.XlZrlbDao;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlzrlb.XlZrlbDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.XlBhgwtmxEntity;
import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XlBhglbEntity;
import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XlZrlbEntity;
import com.xml.frame.report.util.EasyCalendar;

import net.sf.json.JSONArray;

@Service(XlbhgcpmxServiceImpl.NAME)
@Transactional("transactionManager")
public class XlbhgcpmxServiceImpl implements XlbhgcpmxService {
	@Resource(name=XlBhgwtmxDaoImpl.NAME)
	XlBhgwtmxDao xlBhgwtmxDao;

	@Resource(name=XlZrlbDaoImpl.NAME)
	XlZrlbDao xlZrlbDao;

	@Resource(name=XlBhglbDaoImpl.NAME)
	XlBhglbDao xlBhglbDao;

	@Autowired
	DwmcDao dwmcDao;
	
	public final static String NAME = "XlbhgcpmxServiceImpl";

	@Override
	public List<List<String>> getXlbhgcpmx(Date d, List<Integer> zts) {
		List<XlBhgwtmxEntity> entities = xlBhgwtmxDao.getByDate(d, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		for (XlBhgwtmxEntity entity : entities){
			result.add(toList(entity));
		}
		return result;
	}
	
	private List<String> toList(XlBhgwtmxEntity entity) {
		List<String> row = new ArrayList<String>();
		row.add(dwmcDao.getByDwid(entity.getDwid()).getDwmc().getName());
		row.add(entity.getNf() + "年" + entity.getYf() + "月");
		row.add(entity.getCplx());
		row.add(entity.getSch());
		row.add(entity.getCpxh());
		row.add("" + entity.getBhgsl());
		row.add(entity.getSybhgxx());
		row.add(entity.getBhglx().getName());
		row.add(entity.getYyfx());
		row.add(entity.getClcs());
		row.add(entity.getCljg());
		row.add(entity.getZrlb().getName());
		return row;
	}

	@Override
	public List<List<String>> getXlbhgcpmxEntry(Date d, Company company) {
		List<XlBhgwtmxEntity> entities = xlBhgwtmxDao.getByDate(d, company);
		List<List<String>> result = new ArrayList<List<String>>(); 
		for (XlBhgwtmxEntity entity : entities){
			result.add(toEntryList(entity));
		}
		return result;
	}

	private List<String> toEntryList(XlBhgwtmxEntity entity) {
		List<String> row = new ArrayList<String>();
		row.add("" + entity.getId());
		row.add(entity.getCplx());
		row.add(entity.getSch());
		row.add(entity.getCpxh());
		row.add("" + entity.getBhgsl());
		row.add(entity.getSybhgxx());
		row.add(entity.getBhglx().getName());
		row.add(entity.getYyfx());
		row.add(entity.getClcs());
		row.add(entity.getCljg());
		row.add(entity.getZrlb().getName());
		return row;
	}

	@Override
	public ErrorCode approveXlbhgcpmx(Date d, JSONArray data, Company company) {
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer id = Util.toIntNull(row.getString(0));
			XlBhgwtmxEntity entity = xlBhgwtmxDao.getById(id);
			if (null != entity){
				entity.setZt(ZBStatus.APPROVED.ordinal());
				xlBhgwtmxDao.merge(entity);
			}
		}
		return ErrorCode.OK;
	}

	@Override
	public ZBStatus getStatus(Date d, Company company) {
		XlBhgwtmxEntity entity = xlBhgwtmxDao.getFirstBhgwtmx(d, company);
		if (null != entity){
			return ZBStatus.valueOf(entity.getZt());
		}
		return ZBStatus.NONE;
	} 

	@Override
	public ErrorCode submitXlbhgcpmx(Date d, JSONArray data, Company company) {
		return entryXlbhgcpmx(d, data, company, ZBStatus.SUBMITTED);
	}

	@Override
	public ErrorCode saveXlbhgcpmx(Date d, JSONArray data, Company company) {
		return entryXlbhgcpmx(d, data, company, ZBStatus.SAVED);
	}

	private ErrorCode entryXlbhgcpmx(Date d, JSONArray data, Company company,
			ZBStatus zt) {
		EasyCalendar ec = new EasyCalendar(d);
		Timestamp ts = new EasyCalendar().getTimestamp();
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			XlBhgwtmxEntity entity = null;
			if (!row.getString(0).contains("add")){
				Integer id = Util.toIntNull(row.getString(0));
				entity = xlBhgwtmxDao.getById(id);
			}
			
			if (null == entity){
				entity = new XlBhgwtmxEntity();
				entity.setNf(ec.getYear());
				entity.setYf(ec.getMonth());
				entity.setDwid(company.getId());
			}
			int start = 1;
			entity.setZt(zt.ordinal());
			entity.setCplx(row.getString(start++));
			entity.setSch(row.getString(start++));
			entity.setCpxh(row.getString(start++));
			entity.setBhgsl(Util.toDoubleNull(row.getString(start++)));
			entity.setSybhgxx(row.getString(start++));
			entity.setBhglx(xlBhglbDao.getByName(row.getString(start++)));
			entity.setYyfx(row.getString(start++));
			entity.setClcs(row.getString(start++));
			entity.setCljg(row.getString(start++));
			entity.setZrlb(xlZrlbDao.getByName(row.getString(start++)));
			entity.setXgsj(ts);
			xlBhgwtmxDao.merge(entity);
		}
		return ErrorCode.OK;
	}

	@Override
	public List<String> getZrlb() {
		List<String> ret = new ArrayList<String>();
		List<XlZrlbEntity> entities = xlZrlbDao.getAll();
		for (XlZrlbEntity entity : entities){
			ret.add(entity.getName());
		}
		return ret;
	}

	@Override
	public List<String> getBhglx() {
		List<String> ret = new ArrayList<String>();
		List<XlBhglbEntity> entities = xlBhglbDao.getAll();
		for (XlBhglbEntity entity : entities){
			ret.add(entity.getName());
		}
		return ret;
	}

	@Override
	public ErrorCode unapproveXlbhgcpmx(Date d, JSONArray data, Company company) {
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer id = Util.toIntNull(row.getString(0));
			XlBhgwtmxEntity entity = xlBhgwtmxDao.getById(id);
			if (null != entity){
				entity.setZt(ZBStatus.SUBMITTED.ordinal());
				xlBhgwtmxDao.merge(entity);
			}
		}
		return ErrorCode.OK;
	}

	@Override
	public List<List<String>> getXlbhgcpmx(Date d, Company company, List<Integer> zts) {
		List<XlBhgwtmxEntity> entities = xlBhgwtmxDao.getByDate(d, company, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		for (XlBhgwtmxEntity entity : entities){
			result.add(toList(entity));
		}
		return result;
	}

	@Override
	public ErrorCode updateStatus(Date d, Company company, ZBStatus zt) {
		List<XlBhgwtmxEntity> entities = xlBhgwtmxDao.getByDate(d, company);
		Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
		for (XlBhgwtmxEntity entity : entities){
			entity.setZt(zt.ordinal());
			entity.setXgsj(ts);
			xlBhgwtmxDao.merge(entity);
		}
		return ErrorCode.OK;
	}

}
