package com.tbea.ic.operation.service.cpzlqk.pdacptjjg;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Formula;
import com.tbea.ic.operation.common.FormulaServer;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Pair;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.pdjdacptjjg.PdJdAcptjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.pdjdacptjjg.PdJdAcptjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.pdydacptjjg.PdYdAcptjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.pdydacptjjg.PdYdAcptjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoCacheProxy;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.PdAcptjEntryEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.PdJdAcptjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.PdYdAcptjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;

@Service(PdacptjjgServiceImpl.NAME)
@Transactional("transactionManager")
public class PdacptjjgServiceImpl implements PdacptjjgService {
	@Resource(name=PdJdAcptjjgDaoImpl.NAME)
	PdJdAcptjjgDao pdJdAcptjjgDao;

	@Resource(name=PdYdAcptjjgDaoImpl.NAME)
	PdYdAcptjjgDao pdYdAcptjjgDao;

	@Resource(name=ZltjjgDaoImpl.NAME)
	ZltjjgDao zltjjgDao;

	public final static String NAME = "PdacptjjgServiceImpl";

	@Override
	public List<List<String>> getPdacptjjg(Date d, Company company,
			YDJDType yjType, List<Integer> zts) {
		if (yjType == YDJDType.YD){
			return getPdYdAcptjjg(d, company, yjType, zts);
		}
		return getPdJdAcptjjg(d, company, yjType, zts);
	}

	private List<List<String>> getPdYdAcptjjg(Date d, Company company,
			YDJDType yjType, List<Integer> zts) {
		
		List<PdYdAcptjjgEntity> entities = pdYdAcptjjgDao.getAll();
		FormulaClientYd client = new FormulaClientYd(this, zltjjgDao, company, d, zts);
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>(client);
		for (PdYdAcptjjgEntity entity : entities){
			Formula formula = new Formula(entity.getFormul());
			client.add(formula, entity);
			fs.addFormul(formula);
		}
		fs.run();
		return client.getResult();
	}

//	private List<String> toList(ZltjjgDao tjjgDao, PdYdAcptjjgEntity entity, Date d, Company company, List<Integer> zts) {
//		List<String> row = new ArrayList<String>();
//		Util.resize(row, 8);
//		int start = 0;
//		row.set(start++, entity.getCpdl().getName());
//		row.set(start++, entity.getCpxl().getName());
//	
//		ZltjjgEntity zltjjg = tjjgDao.getByDate(d, entity.getCpxl().getId(), company, zts);
//		start = setZltjjg(row, start, zltjjg);
//
//		zltjjg = tjjgDao.getYearAcc(d, entity.getCpxl().getId(), company, zts);
//		start = setZltjjg(row, start, zltjjg);
//		return row;
//	}

	
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
	
	private List<List<String>> getPdJdAcptjjg(Date d, Company company,
			YDJDType yjType, List<Integer> zts) {
		List<PdJdAcptjjgEntity> entities = pdJdAcptjjgDao.getAll();
		FormulaClientJd client = new FormulaClientJd(this, zltjjgDao, company, d, zts);
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>(client);
		for (PdJdAcptjjgEntity entity : entities){
			Formula formula = new Formula(entity.getFormul());
			client.add(formula, entity);
			fs.addFormul(formula);
		}
		fs.run();
		return client.getResult();
	}

	
	void setRow(List<String> row, PdJdAcptjjgEntity entity, ZltjjgEntity tj1, ZltjjgEntity tj2){
		int start = 0;
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
	}
	

	public ErrorCode entryPdacptjjg(Date d, JSONArray data, Company company, ZBStatus zt) {
		ZltjjgEntity zltjjg = null;
		EasyCalendar ec = new EasyCalendar(d);
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
			zltjjgDao.merge(zltjjg);
		}
		return ErrorCode.OK;
	}

	
	@Override
	public ErrorCode savePdacptjjg(Date d, JSONArray data, Company company) {
		return entryPdacptjjg(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitPdacptjjg(Date d, JSONArray data, Company company) {
		return entryPdacptjjg(d, data, company, ZBStatus.SUBMITTED);
	}

	private List<String> toEntryList(ZltjjgDao tjjgDao, PdYdAcptjjgEntity entity, Date d, Company company) {
		List<String> row = new ArrayList<String>();
		Util.resize(row, 5);
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
	
	private boolean contains(List<PdAcptjEntryEntity> entryEntities, Integer tjid){
		for (PdAcptjEntryEntity entity : entryEntities){
			if(entity.getTjjgid().equals(tjid)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<List<String>> getPdacptjjgEntry(Date d, Company company) {
		List<PdYdAcptjjgEntity> entities = pdYdAcptjjgDao.getAll();
		List<PdAcptjEntryEntity> entryEntities = pdYdAcptjjgDao.getEntryEntities(company.getId());
		List<List<String>> result = new ArrayList<List<String>>();
		ZltjjgDao tjjgDao = new ZltjjgDaoCacheProxy(zltjjgDao, company.getId());
		for (PdYdAcptjjgEntity entity : entities){
			if (contains(entryEntities, entity.getId())){
				result.add(toEntryList(tjjgDao, entity, d, company));
			}
		}
		return result;
	}

	@Override
	public List<WaveItem> getJdWaveValues(Date d, Company company, List<Integer> zts) {
		List<WaveItem> ret = new ArrayList<WaveItem>();
		List<String> row = null;
		EasyCalendar ec = new EasyCalendar(d);
		List<PdJdAcptjjgEntity> entities = pdJdAcptjjgDao.getAll();
		ZltjjgDao tjjgDao = new ZltjjgDaoCacheProxy(zltjjgDao, company.getId());
		List<Integer> cpids = new ArrayList<Integer>();
		String cpName = null;
		for (PdJdAcptjjgEntity entity : entities){
			if (cpName == null || entity.getCpdl().getName().equals(cpName)){
				cpids.add(entity.getCpxl().getId());
				cpName = entity.getCpdl().getName();
				continue;
			}
			row = Util.resize(new ArrayList<String>(), 12);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				
				ZltjjgEntity zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company, zts);
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
			row = Util.resize(new ArrayList<String>(), 12);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				ZltjjgEntity zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company, zts);
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
	public List<WaveItem> getWaveValues(Date d, Company company, List<Integer> zts) {
		List<Integer> ids = null;
		if (company.getType() == CompanyType.PDCY){
			ids = new ArrayList<Integer>();
			for (Company comp : company.getSubCompanies()){
				ids.add(comp.getId());
			}
		}
		List<WaveItem> ret = new ArrayList<WaveItem>();
		List<String> row = null;
		EasyCalendar ec = new EasyCalendar(d);
		List<PdYdAcptjjgEntity> entities = pdYdAcptjjgDao.getAll();
		ZltjjgDao tjjgDao = new ZltjjgDaoCacheProxy(zltjjgDao, company.getId());
		List<Integer> cpids = new ArrayList<Integer>();
		String cpName = null;
		for (PdYdAcptjjgEntity entity : entities){
			if (cpName == null || entity.getCpdl().getName().equals(cpName)){
				cpids.add(entity.getCpxl().getId());
				cpName = entity.getCpdl().getName();
				continue;
			}
			row = Util.resize(new ArrayList<String>(), 12);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				ZltjjgEntity zltjjg = null;
				if (ids == null){
					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company, zts);
				}else{
					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, ids, zts);
				}
//				ZltjjgEntity zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company, ZBStatus.APPROVED);
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
			row = Util.resize(new ArrayList<String>(), 12);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				ZltjjgEntity zltjjg = null;
				if (ids == null){
					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company, zts);
				}else{
					zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, ids, zts);
				}
//				ZltjjgEntity zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company, ZBStatus.APPROVED);
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
	public ErrorCode approvePdacptjjg(Date d, JSONArray data, Company company) {
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
		List<PdYdAcptjjgEntity> entities = pdYdAcptjjgDao.getAll();
		List<PdAcptjEntryEntity> entryEntities = pdYdAcptjjgDao.getEntryEntities(company.getId());
		Integer cpid = null;
		for (PdYdAcptjjgEntity entity : entities){
			if (contains(entryEntities, entity.getId())){
				cpid = entity.getCpxl().getId();
				break;
			}
		}
		ZltjjgEntity zltjjg = zltjjgDao.getByDateIgnoreStatus(d, cpid, company);
		if (zltjjg != null){
			return ZBStatus.valueOf(zltjjg.getZt());
		}
		return ZBStatus.NONE;
	}

	@Override
	public ErrorCode unapprovePdacptjjg(Date d, JSONArray data, Company company) {
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

	public void setRow(List<String> row, PdYdAcptjjgEntity entity,
			ZltjjgEntity tj1, ZltjjgEntity tj2) {
		int start = 0;
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
		
	}


}
