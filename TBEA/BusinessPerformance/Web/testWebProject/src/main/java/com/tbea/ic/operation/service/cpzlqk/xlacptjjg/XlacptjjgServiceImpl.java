package com.tbea.ic.operation.service.cpzlqk.xlacptjjg;

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
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.xlacptjjg.XlAcptjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoImpl;
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
			YDJDType yjType) {
		List<XlAcptjjgEntity> entities = xlacptjjgDao.getAll();
		ZltjjgEntity tjjg = null;
		ZltjjgEntity tjjg1 = null;
		List<List<String>> result = new ArrayList<List<String>>();
		for (XlAcptjjgEntity entity : entities){
			if (yjType == YDJDType.YD){
				tjjg = zltjjgDao.getByDate(d, entity.getCpxl().getId(), company);
				tjjg1 = zltjjgDao.getYearAcc(d, entity.getCpxl().getId(), company);
			}else{
				tjjg = zltjjgDao.getJdAcc(d, entity.getCpxl().getId(), company);
				tjjg1 = zltjjgDao.getJdAccQntq(d, entity.getCpxl().getId(), company);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode saveXlacptjjg(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode submitXlacptjjg(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WaveItem> getWaveValues(Date d, Company company) {
		List<WaveItem> ret = new ArrayList<WaveItem>();
		List<String> row = null;
		EasyCalendar ec = new EasyCalendar(d);
		List<XlAcptjjgEntity> entities = xlacptjjgDao.getAll();
		
		for (XlAcptjjgEntity entity : entities){

			row = Util.resize(new ArrayList<String>(), 12);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				
				ZltjjgEntity zltjjg = zltjjgDao.getByDate(d, entity.getCpxl().getId(), company);
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

}
