package com.tbea.ic.operation.service.cpzlqk.xlacptjjg;

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
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.xlacptjjg.XlAcptjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoCacheProxy;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqYdAcptjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.XlAcptjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;

@Service(XlacptjjgServiceImpl.NAME)
@Transactional("transactionManager")
public class XlacptjjgServiceImpl implements XlacptjjgService {
	@Resource(name=ZltjjgDaoImpl.NAME)
	ZltjjgDao zltjjgDao;

	@Autowired
	XlAcptjjgDao xlacptjjgDao;
	
	public final static String NAME = "XlacptjjgServiceImpl";

	@Override
	public List<List<String>> getXlacptjjg(Date d, Company company,
			YDJDType yjType,  List<Integer> zts) {
		List<Integer> ids = null;
		if (company.getType() == CompanyType.XLCY){
			ids = new ArrayList<Integer>();
			for (Company comp : company.getSubCompanies()){
				ids.add(comp.getId());
			}
		}
		List<XlAcptjjgEntity> entities = xlacptjjgDao.getAll();
		ZltjjgEntity tjjg = null;
		ZltjjgEntity tjjg1 = null;
		List<List<String>> result = new ArrayList<List<String>>();
		ZltjjgDao tjjgDao = new ZltjjgDaoCacheProxy(zltjjgDao, company.getId());
		for (XlAcptjjgEntity entity : entities){
			if (yjType == YDJDType.YD){
				if (ids == null){
					tjjg = tjjgDao.getByDate(d, entity.getCpxl().getId(), company, zts);
					tjjg1 = tjjgDao.getYearAcc(d, entity.getCpxl().getId(), company, zts);
				}else{
					tjjg = tjjgDao.getByDate(d, entity.getCpxl().getId(), ids, zts);
					tjjg1 = tjjgDao.getYearAcc(d, entity.getCpxl().getId(), ids, zts);
				}
			}else{
				if (ids == null){
					tjjg = tjjgDao.getJdAcc(d, entity.getCpxl().getId(), company, zts);
					tjjg1 = tjjgDao.getJdAccQntq(d, entity.getCpxl().getId(), company, zts);
				}else{
					tjjg = tjjgDao.getJdAcc(d, entity.getCpxl().getId(), ids, zts);
					tjjg1 = tjjgDao.getJdAccQntq(d, entity.getCpxl().getId(), ids, zts);
				}
			}
			result.add(toList(entity, tjjg, tjjg1));
		}
		return result;
	}

	private int setZltjjg(List<String> row, int start, ZltjjgEntity zltjjg){
		if (null != zltjjg){
			row.set(start++, "" + zltjjg.getBhgs());
			row.set(start++, "" + zltjjg.getZs());
			row.set(start++, "" + MathUtil.division(MathUtil.minus(zltjjg.getZs(), zltjjg.getBhgs()), zltjjg.getZs()));
		}else{
			start += 3;
		}
		return start;
	}
	private List<String> toList(XlAcptjjgEntity entity, ZltjjgEntity tjjg, ZltjjgEntity tjjg1) {
		List<String> row = new ArrayList<String>();
		Util.resize(row, 8);
		int start = 0;
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());	
		start = setZltjjg(row, start, tjjg);
		start = setZltjjg(row, start, tjjg1);
		return row;
	}

	@Override
	public List<List<String>> getXlacptjjgEntry(Date d, Company company) {
		List<XlAcptjjgEntity> entities = xlacptjjgDao.getAll();
		ZltjjgEntity tjjg = null;
		List<List<String>> result = new ArrayList<List<String>>();
		for (XlAcptjjgEntity entity : entities){
			tjjg = zltjjgDao.getByDateIgnoreStatus(d, entity.getCpxl().getId(), company);
			result.add(toEntryList(entity, tjjg));
		}
		return result;
	}

	private List<String> toEntryList(XlAcptjjgEntity entity, ZltjjgEntity tjjg) {
		List<String> row = new ArrayList<String>();
		Util.resize(row, 5);
		int start = 0;
		row.set(start++, "" + entity.getCpxl().getId());
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());	
		if (null != tjjg){
			row.set(start++, "" + tjjg.getBhgs());
			row.set(start++, "" + tjjg.getZs());
		}
		return row;
	}

	public ErrorCode entryXlacptjjg(Date d, JSONArray data, Company company, ZBStatus zt) {
		ZltjjgEntity zltjjg = null;
		EasyCalendar ec = new EasyCalendar(d);
		Timestamp ts = new EasyCalendar().getTimestamp();
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer cpid = Integer.valueOf(row.getInt(0));
			zltjjg = zltjjgDao.getByDateIgnoreStatus(d, cpid, company);
			if (null == zltjjg){
				zltjjg = new ZltjjgEntity();
				zltjjg.setNf(ec.getYear());
				zltjjg.setYf(ec.getMonth());
				zltjjg.setDwid(company.getId());
				zltjjg.setCpid(cpid);
			}
			zltjjg.setZt(zt.ordinal());
			zltjjg.setBhgs(Util.toIntNull(row.getString(1)));
			zltjjg.setZs(Util.toIntNull(row.getString(2)));
			zltjjg.setXgsj(ts);
			zltjjgDao.merge(zltjjg);
		}
		return ErrorCode.OK;
	}
	
	@Override
	public ErrorCode saveXlacptjjg(Date d, JSONArray data, Company company) {
		return entryXlacptjjg(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitXlacptjjg(Date d, JSONArray data, Company company) {
		return entryXlacptjjg(d, data, company, ZBStatus.SUBMITTED);
	}

	@Override
	public List<WaveItem> getWaveValues(Date d, Company company,  List<Integer> zts) {
		List<Integer> ids = null;
		if (company.getType() == CompanyType.XLCY){
			ids = new ArrayList<Integer>();
			for (Company comp : company.getSubCompanies()){
				ids.add(comp.getId());
			}
		}
		List<WaveItem> ret = new ArrayList<WaveItem>();
		List<String> row = null;
		EasyCalendar ec = new EasyCalendar(d);
		List<XlAcptjjgEntity> entities = xlacptjjgDao.getAll();
		ZltjjgDao tjjgDao = new ZltjjgDaoCacheProxy(zltjjgDao, company.getId());
		List<Integer> cpIds = Util.resize(new ArrayList<Integer>(), 1);
		for (XlAcptjjgEntity entity : entities){

			row = Util.resize(new ArrayList<String>(), 12);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				cpIds.set(0, entity.getCpxl().getId());
				ZltjjgEntity zltjjg = null;
				if (null == ids){
					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpIds, company, zts);
				}else{
					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpIds, ids, zts);
				}
				if (null != zltjjg){
					row.set(i, "" + MathUtil.division(MathUtil.minus(zltjjg.getZs(), zltjjg.getBhgs()), zltjjg.getZs()));
				}else{
					row.set(i, null);
				}
				ec.addMonth(1);
			}
			ec.addMonth(-1);
			ret.add(new WaveItem(entity.getCpxl().getName(), row));		
		}
		return ret;
	}

	@Override
	public ErrorCode approveXlacptjjg(Date d, JSONArray data, Company company) {
		ZltjjgEntity zltjjg = null;
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer cpid = Integer.valueOf(row.getInt(0));
			zltjjg = zltjjgDao.getByDateIgnoreStatus(d, cpid, company);
			if (null != zltjjg){
				zltjjg.setZt(ZBStatus.APPROVED.ordinal());
				zltjjgDao.merge(zltjjg);
			}
		}
		return ErrorCode.OK;
	}
	
	@Override
	public ZBStatus getStatus(Date d, Company company) {
		List<XlAcptjjgEntity> entities = xlacptjjgDao.getAll();
		ZltjjgEntity zltjjg = zltjjgDao.getByDateIgnoreStatus(d, entities.get(0).getCpxl().getId(), company);
		if (zltjjg != null){
			return ZBStatus.valueOf(zltjjg.getZt());
		}
		return ZBStatus.NONE;
	}

	@Override
	public ErrorCode unapproveXlacptjjg(Date d, JSONArray data, Company company) {
		ZltjjgEntity zltjjg = null;
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer cpid = Integer.valueOf(row.getInt(0));
			zltjjg = zltjjgDao.getByDateIgnoreStatus(d, cpid, company);
			if (null != zltjjg){
				zltjjg.setZt(ZBStatus.SUBMITTED.ordinal());
				zltjjgDao.merge(zltjjg);
			}
		}
		return ErrorCode.OK;
	}

}
