package com.tbea.ic.operation.service.nyzbscqk.nyzbscjg;

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
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.identifier.nyzbscqk.nycompminingareamatch.NyCompMiningAreaMatchDao;
import com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscjg.NyzbscjgDao;
import com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscjg.NyzbscjgDaoImpl;
import com.tbea.ic.operation.model.entity.nyzbscqk.NyCompMiningAreaMatchEntity;
import com.tbea.ic.operation.model.entity.nyzbscqk.NyzbscjgEntity;

@Service(NyzbscjgServiceImpl.NAME)
@Transactional("transactionManager")
public class NyzbscjgServiceImpl implements NyzbscjgService {
	@Resource(name=NyzbscjgDaoImpl.NAME)
	NyzbscjgDao nyzbscjgDao;

	@Autowired
	NyCompMiningAreaMatchDao nyCompMiningAreaMatchDao;
	
	public final static String NAME = "NyzbscjgServiceImpl";

	@Override
	public List<List<String>> getNyzbscjg(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();

		List<NyCompMiningAreaMatchEntity> miningAreaMatch = nyCompMiningAreaMatchDao
				.getMiningArea(company);
		for (int i = 0; i < miningAreaMatch.size(); ++i) {
			NyCompMiningAreaMatchEntity entity = miningAreaMatch.get(i);
			List<String> list = new ArrayList<String>();
			Util.resize(list, 14);
			list.set(0, entity.getKq().getName());
			list.set(1, entity.getMz().getName());
			List<NyzbscjgEntity> entities = nyzbscjgDao.getByYear(d, company, entity.getKq().getId(), entity.getMz().getId());
			for (NyzbscjgEntity nyEntity : entities){
				list.set(nyEntity.getYf() + 1, "" + nyEntity.getJg());
			}
			result.add(list);
			if (i % 3 == 2){
				list = new ArrayList<String>();
				Util.resize(list, 14);
				list.set(0, entity.getKq().getName());
				list.set(1, "加权平均价");
				for (int j = 0; j < 12; ++j){
					list.set(j + 2, "" + MathUtil.division(MathUtil.sum(new Double[]{
						MathUtil.toDouble(result.get(result.size() - 1).get(j + 2)),
						MathUtil.toDouble(result.get(result.size() - 2).get(j + 2)),
						MathUtil.toDouble(result.get(result.size() - 3).get(j + 2))	
					}), 3.0));
				}
				result.add(list);
			}
		}
		return result;
	}

	@Override
	public List<List<String>> getNyzbscjgEntry(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();

		List<NyCompMiningAreaMatchEntity> miningAreaMatch = nyCompMiningAreaMatchDao
				.getMiningArea(company);
		for (NyCompMiningAreaMatchEntity entity : miningAreaMatch) {
			List<String> list = new ArrayList<String>();
			Util.resize(list, 4);
			list.set(0, "" + entity.getId());
			list.set(1, entity.getKq().getName());
			list.set(2, entity.getMz().getName());
			result.add(list);
		}

		List<NyzbscjgEntity> entities = nyzbscjgDao.getByDate(d, company);
		for (NyzbscjgEntity entity : entities) {
			for (int j = 0; j < miningAreaMatch.size(); ++j) {
				if (miningAreaMatch.get(j).getKq().getId() == entity.getKq()
						&& miningAreaMatch.get(j).getMz().getId() == entity
								.getMz()) {
					result.get(j).set(3, "" + entity.getJg());
					miningAreaMatch.remove(entity);
					break;
				}
			}
		}

		return result;
	}

	public ErrorCode entryNyzbscjg(Date d, JSONArray data, Company company, ZBStatus zt) {
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			NyCompMiningAreaMatchEntity entity = nyCompMiningAreaMatchDao.getById(Integer.valueOf(row.getString(0)));
			NyzbscjgEntity nyEntity = nyzbscjgDao.getByDate(d, company, entity.getKq().getId(), entity.getMz().getId());
			if (null == nyEntity){
				nyEntity = new NyzbscjgEntity();
				EasyCalendar ec = new EasyCalendar(d);
				nyEntity.setDwid(company.getId());
				nyEntity.setNf(ec.getYear());
				nyEntity.setYf(ec.getMonth());
				nyEntity.setKq(entity.getKq().getId());
				nyEntity.setMz(entity.getMz().getId());
			}
			nyEntity.setZt(zt.ordinal());
			nyEntity.setJg(MathUtil.toDouble(row.getString(1)));
			nyzbscjgDao.merge(nyEntity);
		}
		return ErrorCode.OK;
	}
	@Override
	public ErrorCode saveNyzbscjg(Date d, JSONArray data, Company company) {
		return entryNyzbscjg(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitNyzbscjg(Date d, JSONArray data, Company company) {
		return entryNyzbscjg(d, data, company, ZBStatus.SUBMITTED);
	}

}
