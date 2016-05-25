package com.tbea.ic.operation.service.cpzlqk.byqacptjjg;

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
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.byqjdacptjjg.ByqJdAcptjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.byqjdacptjjg.ByqJdAcptjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.byqydacptjjg.ByqYdAcptjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.byqydacptjjg.ByqYdAcptjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoCacheProxy;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqJdAcptjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqYdAcptjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;

@Service(ByqacptjjgServiceImpl.NAME)
@Transactional("transactionManager")
public class ByqacptjjgServiceImpl implements ByqacptjjgService {
	@Resource(name=ByqJdAcptjjgDaoImpl.NAME)
	ByqJdAcptjjgDao byqJdAcptjjgDao;

	@Resource(name=ByqYdAcptjjgDaoImpl.NAME)
	ByqYdAcptjjgDao byqYdAcptjjgDao;

	@Resource(name=ZltjjgDaoImpl.NAME)
	ZltjjgDao zltjjgDao;

	public final static String NAME = "ByqacptjjgServiceImpl";

	@Override
	public List<List<String>> getByqacptjjg(Date d, Company company,
			YDJDType yjType) {
		if (yjType == YDJDType.YD){
			return getByqYdAcptjjg(d, company, yjType);
		}
		return getByqJdAcptjjg(d, company, yjType);
	}

	private List<List<String>> getByqYdAcptjjg(Date d, Company company,
			YDJDType yjType) {
		List<ByqYdAcptjjgEntity> entities = byqYdAcptjjgDao.getAll();
		List<List<String>> result = new ArrayList<List<String>>();
		ZltjjgDao tjjgDao = new ZltjjgDaoCacheProxy(zltjjgDao, company.getId());
		for (ByqYdAcptjjgEntity entity : entities){
			result.add(toList(tjjgDao, entity, d, company));
		}
		return result;
	}

	private List<String> toList(ZltjjgDao tjjgDao, ByqYdAcptjjgEntity entity, Date d, Company company) {
		List<String> row = new ArrayList<String>();
		Util.resize(row, 8);
		int start = 0;
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());
	
		ZltjjgEntity zltjjg = tjjgDao.getByDate(d, entity.getCpxl().getId(), company);
		start = setZltjjg(row, start, zltjjg);

		zltjjg = tjjgDao.getYearAcc(d, entity.getCpxl().getId(), company);
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
	
	private List<List<String>> getByqJdAcptjjg(Date d, Company company,
			YDJDType yjType) {
		List<ByqJdAcptjjgEntity> entities = byqJdAcptjjgDao.getAll();
		FormulaClientJd client = new FormulaClientJd(this, zltjjgDao, company, d);
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>(client);
		for (ByqJdAcptjjgEntity entity : entities){
			Formula formula = new Formula(entity.getFormul());
			client.add(formula, entity);
			fs.addFormul(formula);
		}
		fs.run();
		return client.getResult();
	}

	
	void setRow(List<String> row, ByqJdAcptjjgEntity entity, ZltjjgEntity tj1, ZltjjgEntity tj2){
		int start = 0;
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
	}
	

	public ErrorCode entryByqacptjjg(Date d, JSONArray data, Company company, ZBStatus zt) {
		ZltjjgEntity zltjjg = null;
		EasyCalendar ec = new EasyCalendar(d);
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer cpid = Integer.valueOf(row.getInt(0));
			zltjjg = zltjjgDao.getByDate(d, cpid, company);
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
	public ErrorCode saveByqacptjjg(Date d, JSONArray data, Company company) {
		return entryByqacptjjg(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitByqacptjjg(Date d, JSONArray data, Company company) {
		return entryByqacptjjg(d, data, company, ZBStatus.SUBMITTED);
	}

	private List<String> toEntryList(ZltjjgDao tjjgDao, ByqYdAcptjjgEntity entity, Date d, Company company) {
		List<String> row = new ArrayList<String>();
		Util.resize(row, 5);
		int start = 0;
		row.set(start++, "" + entity.getCpxl().getId());
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());
		ZltjjgEntity zltjjg = tjjgDao.getByDate(d, entity.getCpxl().getId(), company);
		if (null != zltjjg){
			row.set(start++, "" + zltjjg.getBhgs());
			row.set(start++, "" + zltjjg.getZs());
		}
		return row;
	}
	
	@Override
	public List<List<String>> getByqacptjjgEntry(Date d, Company company) {
		List<ByqYdAcptjjgEntity> entities = byqYdAcptjjgDao.getAll();
		List<List<String>> result = new ArrayList<List<String>>();
		ZltjjgDao tjjgDao = new ZltjjgDaoCacheProxy(zltjjgDao, company.getId());
		for (ByqYdAcptjjgEntity entity : entities){
			result.add(toEntryList(tjjgDao, entity, d, company));
		}
		return result;
	}

	@Override
	public List<WaveItem> getWaveValues(Date d, Company company) {
		List<WaveItem> ret = new ArrayList<WaveItem>();
		List<String> row = null;
		EasyCalendar ec = new EasyCalendar(d);
		List<ByqYdAcptjjgEntity> entities = byqYdAcptjjgDao.getAll();
		ZltjjgDao tjjgDao = new ZltjjgDaoCacheProxy(zltjjgDao, company.getId());
		List<Integer> cpids = new ArrayList<Integer>();
		String cpName = null;
		for (ByqYdAcptjjgEntity entity : entities){
			if (cpName == null || entity.getCpdl().getName().equals(cpName)){
				cpids.add(entity.getCpxl().getId());
				cpName = entity.getCpdl().getName();
				continue;
			}
			row = Util.resize(new ArrayList<String>(), 12);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				
				ZltjjgEntity zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company);
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
				ZltjjgEntity zltjjg = tjjgDao.getByDateTotal(ec.getDate(), cpids, company);
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
	public ErrorCode approveByqacptjjg(Date d, JSONArray data, Company company) {
		ZltjjgEntity zltjjg = null;
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			Integer cpid = Integer.valueOf(row.getInt(0));
			zltjjg = zltjjgDao.getByDate(d, cpid, company);
			if (null != zltjjg){
				zltjjg.setZt(ZBStatus.APPROVED.ordinal());
				zltjjgDao.merge(zltjjg);
			}
		}
		return ErrorCode.OK;
	}

	@Override
	public ZBStatus getStatus(Date d, Company company) {
		ZltjjgEntity zltjjg = zltjjgDao.getFirstTjjg(d, company);
		if (zltjjg != null){
			return ZBStatus.valueOf(zltjjg.getZt());
		}
		return ZBStatus.NONE;
	}


}