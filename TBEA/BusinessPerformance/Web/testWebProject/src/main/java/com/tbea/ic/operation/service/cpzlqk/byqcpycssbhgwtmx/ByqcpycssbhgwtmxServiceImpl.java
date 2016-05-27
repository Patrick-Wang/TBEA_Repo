package com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgwtmx;

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
import com.tbea.ic.operation.controller.servlet.cpzlqk.ByqBhgType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx.ByqBhgwtmxDao;
import com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx.ByqBhgwtmxDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.dwmc.DwmcDao;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.byqbhglb.ByqBhglbDao;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.byqbhglb.ByqBhglbDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.byqzrlb.ByqZrlbDao;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqBhgwtmxEntity;
import com.tbea.ic.operation.model.entity.identifier.cpzlqk.ByqBhglbEntity;
import com.tbea.ic.operation.model.entity.identifier.cpzlqk.ByqZrlbEntity;

@Service(ByqcpycssbhgwtmxServiceImpl.NAME)
@Transactional("transactionManager")
public class ByqcpycssbhgwtmxServiceImpl implements ByqcpycssbhgwtmxService {
	@Resource(name=ByqBhglbDaoImpl.NAME)
	ByqBhglbDao byqBhglbDao;

	@Resource(name=ByqBhgwtmxDaoImpl.NAME)
	ByqBhgwtmxDao byqBhgwtmxDao;
	
	@Autowired
	ByqZrlbDao byqZrlbDao;
	
	@Autowired
	DwmcDao dwmcDao;

	public final static String NAME = "ByqcpycssbhgwtmxServiceImpl";

	@Override
	public List<List<String>> getByqcpycssbhgwtmx(Date d,
			YDJDType yjType, ByqBhgType bhgType) {
		List<ByqBhgwtmxEntity> entities = null;
		if (yjType == YDJDType.YD){
			entities = byqBhgwtmxDao.getByYd(d, bhgType.ordinal(), ZBStatus.APPROVED);
		}else{
			entities = byqBhgwtmxDao.getByJd(d, bhgType.ordinal(), ZBStatus.APPROVED);
		}
		List<List<String>> result = new ArrayList<List<String>>();
		for (ByqBhgwtmxEntity entity : entities){
			result.add(toList(entity));
		}
		return result;
	}
	
	private List<String> toList(ByqBhgwtmxEntity entity) {
		List<String> row = new ArrayList<String>();
		row.add(dwmcDao.getByDwid(entity.getDwid()).getDwmc().getName());
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
	
	private List<String> toEntryList(ByqBhgwtmxEntity entity) {
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
	public List<List<String>> getByqcpycssbhgwtmxEntry(Date d, Company company, ByqBhgType bhgType) {
		List<ByqBhgwtmxEntity> entities = byqBhgwtmxDao.getByDate(d, company, bhgType.ordinal());
		List<List<String>> result = new ArrayList<List<String>>(); 
		for (ByqBhgwtmxEntity entity : entities){
			result.add(toEntryList(entity));
		}
		return result;
	}

	@Override
	public ErrorCode approveByqcpycssbhgwtmx(Date d, JSONArray data,
			Company company) {
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer id = Util.toIntNull(row.getString(0));
			ByqBhgwtmxEntity entity = byqBhgwtmxDao.getById(id);
			if (null != entity){
				entity.setZt(ZBStatus.APPROVED.ordinal());
				byqBhgwtmxDao.merge(entity);
			}
		}
		return ErrorCode.OK;
	}

	@Override
	public ZBStatus getStatus(Date d, Company company, ByqBhgType bhgType) {
		ByqBhgwtmxEntity entity = byqBhgwtmxDao.getFirstBhgwtmx(d, company, bhgType.ordinal());
		if (null != entity){
			return ZBStatus.valueOf(entity.getZt());
		}
		return ZBStatus.NONE;
	}
	
	private ErrorCode entryByqcpycssbhgwtmx(Date d, JSONArray data,
			Company company, ZBStatus zt, ByqBhgType bhgType) {
		EasyCalendar ec = new EasyCalendar(d);
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			ByqBhgwtmxEntity entity = null;
			if (!row.getString(0).contains("add")){
				Integer id = Util.toIntNull(row.getString(0));
				entity = byqBhgwtmxDao.getById(id);
			}
			
			if (null == entity){
				entity = new ByqBhgwtmxEntity();
				entity.setNf(ec.getYear());
				entity.setYf(ec.getMonth());
				entity.setDwid(company.getId());
			}
			int start = 1;
			entity.setZt(zt.ordinal());
			entity.setTjfs(bhgType.ordinal());
			entity.setCplx(row.getString(start++));
			entity.setSch(row.getString(start++));
			entity.setCpxh(row.getString(start++));
			entity.setSybhgxx(row.getString(start++));
			entity.setBhglb(byqBhglbDao.getByName(row.getString(start++)));
			entity.setYyfx(row.getString(start++));
			entity.setClcs(row.getString(start++));
			entity.setCljg(row.getString(start++));
			entity.setZrlb(byqZrlbDao.getByName(row.getString(start++)));
			byqBhgwtmxDao.merge(entity);
			
		}
		return ErrorCode.OK;
	}
	
	@Override
	public ErrorCode submitByqcpycssbhgwtmx(Date d, JSONArray data,
			Company company, ByqBhgType bhgType) {
		return entryByqcpycssbhgwtmx(d, data, company, ZBStatus.SUBMITTED, bhgType);
	}

	@Override
	public ErrorCode saveByqcpycssbhgwtmx(Date d, JSONArray data,
			Company company, ByqBhgType bhgType) {
		return entryByqcpycssbhgwtmx(d, data, company, ZBStatus.SAVED, bhgType);
	}

	@Override
	public List<String> getZrlb() {
		List<String> ret = new ArrayList<String>();
		List<ByqZrlbEntity> entities = byqZrlbDao.getAll();
		for (ByqZrlbEntity entity : entities){
			ret.add(entity.getName());
		}
		return ret;
	}

	@Override
	public List<String> getBhglx() {
		List<String> ret = new ArrayList<String>();
		List<ByqBhglbEntity> entities = byqBhglbDao.getAll();
		for (ByqBhglbEntity entity : entities){
			ret.add(entity.getName());
		}
		return ret;
	}

	@Override
	public ErrorCode unapproveByqcpycssbhgwtmx(Date d, JSONArray data,
			Company company) {
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer id = Util.toIntNull(row.getString(0));
			ByqBhgwtmxEntity entity = byqBhgwtmxDao.getById(id);
			if (null != entity){
				entity.setZt(ZBStatus.SUBMITTED.ordinal());
				byqBhgwtmxDao.merge(entity);
			}
		}
		return ErrorCode.OK;
	}

	@Override
	public List<List<String>> getByqcpycssbhgwtmx(Date d, YDJDType yjType,
			ByqBhgType bhgType, Company company) {
		List<ByqBhgwtmxEntity> entities = null;
		if (yjType == YDJDType.YD){
			entities = byqBhgwtmxDao.getByYd(d, bhgType.ordinal(), company, ZBStatus.APPROVED);
		}else{
			entities = byqBhgwtmxDao.getByJd(d, bhgType.ordinal(), company, ZBStatus.APPROVED);
		}
		List<List<String>> result = new ArrayList<List<String>>();
		for (ByqBhgwtmxEntity entity : entities){
			result.add(toList(entity));
		}
		return result;
	}

}
