package com.tbea.ic.operation.service.cpzlqk.xkacptjjg;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Formula;
import com.tbea.ic.operation.common.FormulaServer;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.xkjdacptjjg.XkJdAcptjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.xkjdacptjjg.XkJdAcptjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.xkydacptjjg.XkYdAcptjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.xkydacptjjg.XkYdAcptjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoCacheProxy;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.XkJdAcptjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.XkYdAcptjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;
import com.util.tools.ListUtil;
import com.util.tools.MathUtil;
import com.util.tools.Pair;
import com.xml.frame.report.util.EasyCalendar;

import net.sf.json.JSONArray;

@Service(XkacptjjgServiceImpl.NAME)
@Transactional("transactionManager")
public class XkacptjjgServiceImpl implements XkacptjjgService {
	@Resource(name=XkJdAcptjjgDaoImpl.NAME)
	XkJdAcptjjgDao xkJdAcptjjgDao;

	@Resource(name=XkYdAcptjjgDaoImpl.NAME)
	XkYdAcptjjgDao xkYdAcptjjgDao;

	@Resource(name=ZltjjgDaoImpl.NAME)
	ZltjjgDao zltjjgDao;

	public final static String NAME = "XkacptjjgServiceImpl";

	@Override
	public List<List<String>> getXkacptjjg(Date d, Company company,
			YDJDType yjType,  List<Integer> zts) {
		if (yjType == YDJDType.YD){
			return getXkYdAcptjjg(d, company, yjType, zts);
		}
		return getXkJdAcptjjg(d, company, yjType, zts);
	}

	private List<List<String>> getXkYdAcptjjg(Date d, Company company,
			YDJDType yjType,  List<Integer> zts) {
		
		List<XkYdAcptjjgEntity> entities = xkYdAcptjjgDao.getAll();
		FormulaClientYd client = new FormulaClientYd(this, zltjjgDao, company, d, zts);
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>(client);
		for (XkYdAcptjjgEntity entity : entities){
			Formula formula = new Formula(entity.getFormul());
			client.add(formula, entity);
			fs.addFormul(formula);
		}
		fs.run();
		return client.getResult();
	}

	private List<String> toList(ZltjjgDao tjjgDao, XkYdAcptjjgEntity entity, Date d, Company company,  List<Integer> zts) {
		List<String> row = new ArrayList<String>();
		ListUtil.resize(row, 8);
		int start = 0;
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());
	
		ZltjjgEntity zltjjg = tjjgDao.getByDate(d, entity.getCpxl().getId(), company, zts);
		start = setZltjjg(row, start, zltjjg);

		zltjjg = tjjgDao.getYearAcc(d, entity.getCpxl().getId(), company, zts);
		start = setZltjjg(row, start, zltjjg);
		return row;
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
	
	private List<List<String>> getXkJdAcptjjg(Date d, Company company,
			YDJDType yjType,  List<Integer> zts) {
		List<XkJdAcptjjgEntity> entities = xkJdAcptjjgDao.getAll();
		FormulaClientJd client = new FormulaClientJd(this, zltjjgDao, company, d, zts);
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>(client);
		for (XkJdAcptjjgEntity entity : entities){
			Formula formula = new Formula(entity.getFormul());
			client.add(formula, entity);
			fs.addFormul(formula);
		}
		fs.run();
		return client.getResult();
	}

	
	void setRow(List<String> row, XkJdAcptjjgEntity entity, ZltjjgEntity tj1, ZltjjgEntity tj2){
		int start = 0;
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
	}
	

	public ErrorCode entryXkacptjjg(Date d, JSONArray data, Company company, ZBStatus zt) {
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
			zltjjg.setBhgs(Util.toDoubleNull(row.getString(1)));
			zltjjg.setZs(Util.toDoubleNull(row.getString(2)));
			zltjjg.setXgsj(ts);
			zltjjgDao.merge(zltjjg);
		}
		return ErrorCode.OK;
	}

	
	@Override
	public ErrorCode saveXkacptjjg(Date d, JSONArray data, Company company) {
		return entryXkacptjjg(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitXkacptjjg(Date d, JSONArray data, Company company) {
		return entryXkacptjjg(d, data, company, ZBStatus.SUBMITTED);
	}

	private List<String> toEntryList(ZltjjgDao tjjgDao, XkYdAcptjjgEntity entity, Date d, Company company) {
		List<String> row = new ArrayList<String>();
		ListUtil.resize(row, 5);
		int start = 0;
		row.set(start++, "" + entity.getCpxl().getId());
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());
		ZltjjgEntity zltjjg = tjjgDao.getByDateIgnoreStatus(d, entity.getCpxl().getId(), company);
		if (null != zltjjg){
			row.set(start++, "" + zltjjg.getBhgs());
			row.set(start++, "" + zltjjg.getZs());
		}
		return row;
	}
	
	@Override
	public List<List<String>> getXkacptjjgEntry(Date d, Company company) {
		List<XkYdAcptjjgEntity> entities = xkYdAcptjjgDao.getAll();
		List<List<String>> result = new ArrayList<List<String>>();
		ZltjjgDao tjjgDao = new ZltjjgDaoCacheProxy(zltjjgDao, company.getId());
		for (XkYdAcptjjgEntity entity : entities){
			if (null == entity.getFormul() || "this".equals(entity.getFormul())){
				result.add(toEntryList(tjjgDao, entity, d, company));
			}
		}
		return result;
	}

	
	@Override
	public ErrorCode updateStatus(Date d, Company company, ZBStatus zt) {
		List<ZltjjgEntity> entities = zltjjgDao.getByDateIgnoreStatus(d, company);
		Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
		for (ZltjjgEntity entity : entities){
			entity.setZt(zt.ordinal());
			entity.setXgsj(ts);
			zltjjgDao.merge(entity);
		}
		return ErrorCode.OK;
	}
	
	@Override
	public List<WaveItem> getJdWaveValues(Date d, Company company,  List<Integer> zts) {
		List<Integer> ids = null;
		if (company.getType() == CompanyType.BYQCY){
			ids = new ArrayList<Integer>();
			for (Company comp : company.getSubCompanies()){
				ids.add(comp.getId());
			}
		}
		List<WaveItem> ret = new ArrayList<WaveItem>();
//		List<String> row = null;
//		EasyCalendar ec = new EasyCalendar(d);
//		List<XkJdAcptjjgEntity> entities = xkJdAcptjjgDao.getAll();
//		ZltjjgDao tjjgDao = new ZltjjgDaoCacheProxy(zltjjgDao, company.getId());
//		List<Integer> cpids = new ArrayList<Integer>();
//		String cpName = null;
//		for (XkJdAcptjjgEntity entity : entities){
//			if (cpName == null || entity.getCpdl().getName().equals(cpName)){
//				cpids.add(entity.getCpxl().getId());
//				cpName = entity.getCpdl().getName();
//				continue;
//			}
//			row = ListUtil.resize(new ArrayList<String>(), 12);
//			ec.setMonth(1);
//			for (int i = 0; i < 12; ++i){
//				
//				ZltjjgEntity zltjjg = null;
//				if (ids == null){
//					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company, ZBStatus.APPROVED);
//				}else{
//					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, ids, ZBStatus.APPROVED);
//				}
//				
//				if (null != zltjjg){
//					row.set(i, "" + MathUtil.division(MathUtil.minus(zltjjg.getZs(), zltjjg.getBhgs()), zltjjg.getZs()));
//				}else{
//					row.set(i, null);
//				}
//				ec.addMonth(1);
//			}
//			ec.addMonth(-1);
//			ret.add(new WaveItem(cpName, row));
//			cpids.clear();
//			cpids.add(entity.getCpxl().getId());
//			cpName = entity.getCpdl().getName();
//			
//		}
//		
//		if (cpName != null){
//			row = ListUtil.resize(new ArrayList<String>(), 12);
//			ec.setMonth(1);
//			for (int i = 0; i < 12; ++i){
//				ZltjjgEntity zltjjg = null;
//				if (ids == null){
//					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company, ZBStatus.APPROVED);
//				}else{
//					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, ids, ZBStatus.APPROVED);
//				}
//				if (null != zltjjg){
//					row.set(i, "" + MathUtil.division(MathUtil.minus(zltjjg.getZs(), zltjjg.getBhgs()), zltjjg.getZs()));
//				}else{
//					row.set(i, null);
//				}
//				ec.addMonth(1);
//			}
//			ec.addMonth(-1);
//			ret.add(new WaveItem(cpName, row));
//		}
		
		return ret;
	}
	
	@Override
	public List<WaveItem> getWaveValues(Date d, Company company,  List<Integer> zts) {
		List<Integer> ids = null;
		if (company.getType() == CompanyType.BYQCY){
			ids = new ArrayList<Integer>();
			for (Company comp : company.getSubCompanies()){
				ids.add(comp.getId());
			}
		}
		List<WaveItem> ret = new ArrayList<WaveItem>();
		List<String> row = null;
		EasyCalendar ec = new EasyCalendar(d);
		List<XkYdAcptjjgEntity> entities = xkYdAcptjjgDao.getAll();
		ZltjjgDao tjjgDao = new ZltjjgDaoCacheProxy(zltjjgDao, company.getId());
		List<Integer> cpids = new ArrayList<Integer>();
		String cpName = null;
		for (XkYdAcptjjgEntity entity : entities){
			if (cpName == null || entity.getCpdl().getName().equals(cpName)){
				cpids.add(entity.getCpxl().getId());
				cpName = entity.getCpdl().getName();
				continue;
			}
			row = ListUtil.resize(new ArrayList<String>(), 12);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				ZltjjgEntity zltjjg = null;
				if (ids == null){
					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company, zts);
				}else{
					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, ids, zts);
				}
				if (null != zltjjg){
					row.set(i, "" + MathUtil.division(MathUtil.minus(zltjjg.getZs(), zltjjg.getBhgs()), zltjjg.getZs()));
				}else{
					row.set(i, null);
				}
				ec.addMonth(1);
			}
			ec.addMonth(-1);
			ret.add(new WaveItem(cpName, row));
			cpids.clear();
			cpids.add(entity.getCpxl().getId());
			cpName = entity.getCpdl().getName();
			
		}
		
		if (cpName != null){
			row = ListUtil.resize(new ArrayList<String>(), 12);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				ZltjjgEntity zltjjg = null;
				if (ids == null){
					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company, zts);
				}else{
					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, ids, zts);
				}
				if (null != zltjjg){
					row.set(i, "" + MathUtil.division(MathUtil.minus(zltjjg.getZs(), zltjjg.getBhgs()), zltjjg.getZs()));
				}else{
					row.set(i, null);
				}
				ec.addMonth(1);
			}
			ec.addMonth(-1);
			ret.add(new WaveItem(cpName, row));
		}
		
		return ret;
	}

	@Override
	public ErrorCode approveXkacptjjg(Date d, JSONArray data, Company company, ZBStatus zbStatus) {
		ZltjjgEntity zltjjg = null;
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer cpid = Integer.valueOf(row.getInt(0));
			zltjjg = zltjjgDao.getByDateIgnoreStatus(d, cpid, company);
			if (null != zltjjg){
				zltjjg.setZt(zbStatus.ordinal());
				zltjjgDao.merge(zltjjg);
			}
		}
		return ErrorCode.OK;
	}

	@Override
	public ErrorCode unapproveXkacptjjg(Date d, JSONArray data, Company company) {
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

	public void setRow(List<String> row, XkYdAcptjjgEntity entity,
			ZltjjgEntity tj1, ZltjjgEntity tj2) {
		int start = 0;
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
		
	}


}
