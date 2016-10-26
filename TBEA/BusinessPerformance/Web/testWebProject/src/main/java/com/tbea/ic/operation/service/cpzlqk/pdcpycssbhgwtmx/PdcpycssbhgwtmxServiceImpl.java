package com.tbea.ic.operation.service.cpzlqk.pdcpycssbhgwtmx;

import java.sql.Date;
import java.sql.Timestamp;
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
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.dwmc.DwmcDao;
import com.tbea.ic.operation.model.dao.cpzlqk.pdbhgwtmx.PdBhgwtmxDao;
import com.tbea.ic.operation.model.dao.cpzlqk.pdbhgwtmx.PdBhgwtmxDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.pdbhglb.PdBhglbDao;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.pdbhglb.PdBhglbDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.pdzrlb.PdZrlbDao;
import com.tbea.ic.operation.model.entity.cpzlqk.PdBhgwtmxEntity;
import com.tbea.ic.operation.model.entity.identifier.cpzlqk.PdBhglbEntity;
import com.tbea.ic.operation.model.entity.identifier.cpzlqk.PdZrlbEntity;

@Service(PdcpycssbhgwtmxServiceImpl.NAME)
@Transactional("transactionManager")
public class PdcpycssbhgwtmxServiceImpl implements PdcpycssbhgwtmxService {
	@Resource(name=PdBhglbDaoImpl.NAME)
	PdBhglbDao pdBhglbDao;

	@Resource(name=PdBhgwtmxDaoImpl.NAME)
	PdBhgwtmxDao pdBhgwtmxDao;
	
	@Autowired
	PdZrlbDao pdZrlbDao;
	
	@Autowired
	DwmcDao dwmcDao;

	public final static String NAME = "PdcpycssbhgwtmxServiceImpl";

	@Override
	public List<List<String>> getPdcpycssbhgwtmx(Date d,
			YDJDType yjType, List<Integer> zts) {
		List<PdBhgwtmxEntity> entities = null;
		if (yjType == YDJDType.YD){
			entities = pdBhgwtmxDao.getByYd(d, zts);
		}else{
			entities = pdBhgwtmxDao.getByJd(d, zts);
		}
		List<List<String>> result = new ArrayList<List<String>>();
		for (PdBhgwtmxEntity entity : entities){
			result.add(toList(entity));
		}
		return result;
	}
	
	private List<String> toList(PdBhgwtmxEntity entity) {
		List<String> row = new ArrayList<String>();
		row.add(dwmcDao.getByDwid(entity.getDwid()).getDwmc().getName());
		row.add(entity.getNf() + "年" + entity.getYf() + "月");
		row.add(entity.getCplx());
		row.add(entity.getSch());
		row.add(entity.getCpxh());
		row.add(entity.getSybhgxx());
		row.add(entity.getBhglb().getName());
		row.add(entity.getYyfx());
		row.add(entity.getClcs());
		row.add(entity.getCljg());
		row.add(entity.getZrlb().getName());
		return row;
	}
	
	private List<String> toEntryList(PdBhgwtmxEntity entity) {
		List<String> row = new ArrayList<String>();
		row.add("" + entity.getId());
		row.add(entity.getCplx());
		row.add(entity.getSch());
		row.add(entity.getCpxh());
		row.add(entity.getSybhgxx());
		row.add(entity.getBhglb().getName());
		row.add(entity.getYyfx());
		row.add(entity.getClcs());
		row.add(entity.getCljg());
		row.add(entity.getZrlb().getName());
		return row;
	}

	@Override
	public List<List<String>> getPdcpycssbhgwtmxEntry(Date d, Company company) {
		List<PdBhgwtmxEntity> entities = pdBhgwtmxDao.getByDate(d, company);
		List<List<String>> result = new ArrayList<List<String>>(); 
		for (PdBhgwtmxEntity entity : entities){
			result.add(toEntryList(entity));
		}
		return result;
	}

	@Override
	public ErrorCode approvePdcpycssbhgwtmx(Date d, JSONArray data,
			Company company) {
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer id = Util.toIntNull(row.getString(0));
			PdBhgwtmxEntity entity = pdBhgwtmxDao.getById(id);
			if (null != entity){
				entity.setZt(ZBStatus.APPROVED.ordinal());
				pdBhgwtmxDao.merge(entity);
			}
		}
		return ErrorCode.OK;
	}

	@Override
	public ZBStatus getStatus(Date d, Company company) {
		PdBhgwtmxEntity entity = pdBhgwtmxDao.getFirstBhgwtmx(d, company);
		if (null != entity){
			return ZBStatus.valueOf(entity.getZt());
		}
		return ZBStatus.NONE;
	}
	
	private ErrorCode entryPdcpycssbhgwtmx(Date d, JSONArray data,
			Company company, ZBStatus zt) {
		EasyCalendar ec = new EasyCalendar(d);
		Timestamp ts = new EasyCalendar().getTimestamp();
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			PdBhgwtmxEntity entity = null;
			if (!row.getString(0).contains("add")){
				Integer id = Util.toIntNull(row.getString(0));
				entity = pdBhgwtmxDao.getById(id);
			}
			
			if (null == entity){
				entity = new PdBhgwtmxEntity();
				entity.setNf(ec.getYear());
				entity.setYf(ec.getMonth());
				entity.setDwid(company.getId());
			}
			int start = 1;
			entity.setZt(zt.ordinal());
			entity.setCplx(row.getString(start++));
			entity.setSch(row.getString(start++));
			entity.setCpxh(row.getString(start++));
			entity.setSybhgxx(row.getString(start++));
			entity.setBhglb(pdBhglbDao.getByName(row.getString(start++)));
			entity.setYyfx(row.getString(start++));
			entity.setClcs(row.getString(start++));
			entity.setCljg(row.getString(start++));
			entity.setZrlb(pdZrlbDao.getByName(row.getString(start++)));
			entity.setXgsj(ts);
			pdBhgwtmxDao.merge(entity);
			
		}
		return ErrorCode.OK;
	}
	
	@Override
	public ErrorCode submitPdcpycssbhgwtmx(Date d, JSONArray data,
			Company company) {
		return entryPdcpycssbhgwtmx(d, data, company, ZBStatus.SUBMITTED);
	}

	@Override
	public ErrorCode savePdcpycssbhgwtmx(Date d, JSONArray data,
			Company company) {
		return entryPdcpycssbhgwtmx(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public List<String> getZrlb() {
		List<String> ret = new ArrayList<String>();
		List<PdZrlbEntity> entities = pdZrlbDao.getAll();
		for (PdZrlbEntity entity : entities){
			ret.add(entity.getName());
		}
		return ret;
	}

	@Override
	public List<String> getBhglx() {
		List<String> ret = new ArrayList<String>();
		List<PdBhglbEntity> entities = pdBhglbDao.getAll();
		for (PdBhglbEntity entity : entities){
			ret.add(entity.getName());
		}
		return ret;
	}

	@Override
	public ErrorCode unapprovePdcpycssbhgwtmx(Date d, JSONArray data,
			Company company) {
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer id = Util.toIntNull(row.getString(0));
			PdBhgwtmxEntity entity = pdBhgwtmxDao.getById(id);
			if (null != entity){
				entity.setZt(ZBStatus.SUBMITTED.ordinal());
				pdBhgwtmxDao.merge(entity);
			}
		}
		return ErrorCode.OK;
	}

	@Override
	public List<List<String>> getPdcpycssbhgwtmx(Date d, YDJDType yjType,
			Company company, List<Integer> zts) {
		List<PdBhgwtmxEntity> entities = null;
		if (yjType == YDJDType.YD){
			entities = pdBhgwtmxDao.getByYd(d, company, zts);
		}else{
			entities = pdBhgwtmxDao.getByJd(d, company, zts);
		}
		List<List<String>> result = new ArrayList<List<String>>();
		for (PdBhgwtmxEntity entity : entities){
			result.add(toList(entity));
		}
		return result;
	}

}
