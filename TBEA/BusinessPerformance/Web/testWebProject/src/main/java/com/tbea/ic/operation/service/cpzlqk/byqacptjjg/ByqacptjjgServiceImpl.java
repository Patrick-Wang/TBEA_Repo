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
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.byqjdacptjjg.ByqJdAcptjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.byqjdacptjjg.ByqJdAcptjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.byqydacptjjg.ByqYdAcptjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.byqydacptjjg.ByqYdAcptjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
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
		for (ByqYdAcptjjgEntity entity : entities){
			result.add(toList(entity, d, company));
		}
		return result;
	}

	private List<String> toList(ByqYdAcptjjgEntity entity, Date d, Company company) {
		List<String> row = new ArrayList<String>();
		Util.resize(row, 8);
		int start = 0;
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());
	
		ZltjjgEntity zltjjg = zltjjgDao.getByDate(d, entity.getCpxl().getId(), company);
		start = setZltjjg(row, start, zltjjg);

		zltjjg = zltjjgDao.getYearAcc(d, entity.getCpxl().getId(), company);
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
		List<List<String>> result = new ArrayList<List<String>>();
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>();
		List<FormulaClientJd> clients = new ArrayList<FormulaClientJd>();
		for (ByqJdAcptjjgEntity entity : entities){
			ZltjjgEntity dj = zltjjgDao.getJdAcc(d, entity.getCpxl().getId(), company);
			ZltjjgEntity tq = zltjjgDao.getJdAccQntq(d, entity.getCpxl().getId(), company);
			clients.add(new FormulaClientJd(this, entity, dj, tq));
			fs.addRule(new Formula(entity.getFormul()), clients.get(clients.size() - 1));
		}
		

		fs.run();
		
		for (FormulaClientJd client : clients){
			List<String> list = client.getRow();
			if (null != list){
				result.add(list);
			}
		}
		
		return result;
	}

	
	void setRow(List<String> row, ByqJdAcptjjgEntity entity, ZltjjgEntity tj1, ZltjjgEntity tj2){
		int start = 0;
		row.set(start++, entity.getCpdl().getName());
		row.set(start++, entity.getCpxl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
	}
	
	@Override
	public ErrorCode saveByqacptjjg(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode submitByqacptjjg(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getByqacptjjgEntry(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WaveItem> getWaveValues(Date d, Company company) {
		List<WaveItem> ret = new ArrayList<WaveItem>();
		List<String> row = Util.resize(new ArrayList<String>(), 12);
		EasyCalendar ec = new EasyCalendar(d);
		List<ByqYdAcptjjgEntity> entities = byqYdAcptjjgDao.getAll();
		ec.setMonth(1);
		for (ByqYdAcptjjgEntity entity : entities){
			for (int i = 0; i < 12; ++i){
				ZltjjgEntity zltjjg = zltjjgDao.getByDate(d, entity.getCpxl().getId(), company);
				if (null != zltjjg){
					row.set(i, "" + MathUtil.division(MathUtil.minus(zltjjg.getZs(), zltjjg.getBhgs()), zltjjg.getZs()));
				}else{
					row.set(i, null);
				}
			}
			ret.add(new WaveItem(entity.getCpxl().getName(), row));
		}
		return ret;
	}

}
