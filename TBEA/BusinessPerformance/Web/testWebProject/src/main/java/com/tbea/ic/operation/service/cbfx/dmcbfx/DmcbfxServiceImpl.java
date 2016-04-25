package com.tbea.ic.operation.service.cbfx.dmcbfx;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cbfx.dmcbfx.DmcbfxDao;
import com.tbea.ic.operation.model.dao.cbfx.dmcbfx.DmcbfxDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cbfx.cbfl.Cbfl;
import com.tbea.ic.operation.model.dao.identifier.cbfx.cbfl.CbflDao;
import com.tbea.ic.operation.model.entity.cbfx.DmcbfxEntity;

@Service(DmcbfxServiceImpl.NAME)
@Transactional("transactionManager")
public class DmcbfxServiceImpl implements DmcbfxService {
	@Resource(name=DmcbfxDaoImpl.NAME)
	DmcbfxDao dmcbfxDao;

	@Autowired
	CbflDao cbflDao;
	
	public final static String NAME = "DmcbfxServiceImpl";

	@Override
	public List<List<String>> getDmcbfx(Date d, Company company) {
		List<DmcbfxEntity> entities = dmcbfxDao.getByDate(d, company);
		List<List<String>> result  = new ArrayList<List<String>>();
		Util.resize(result, Cbfl.END.ordinal());
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		
		for (int i = 0; i < Cbfl.END.ordinal(); ++i){
			DmcbfxEntity entityTmp = null;
			for (DmcbfxEntity entity : entities){
				if (entity.getCbflid() == i){
					entityTmp = entity;
					entities.remove(entity);
					break;
				}
			}
			if (null != entityTmp){
			result.set(entityTmp.getCbflid(), toList(entityTmp, 
					dmcbfxDao.getByCpfl(entityTmp.getCbflid(), new Date(cal.getTimeInMillis()), company)));
			}else{
				List<String> list = new ArrayList<String>();
				Util.resize(list, 7);
				list.set(0, cbflDao.getById(i).getName());
				result.set(i, list);
			}
		}
		
		return result;
	}

	private List<String> toList(DmcbfxEntity entity, DmcbfxEntity qn) {
		List<String> list = new ArrayList<String>();
		list.add("" + cbflDao.getById(entity.getCbflid()).getName());
		list.add("" + entity.getJhz());
		list.add("" + entity.getSjz());
		list.add("" + Util.division(entity.getSjz(), entity.getJhz()));
		list.add("" + qn.getSjz());
		list.add("" + Util.minus(entity.getSjz(), qn.getSjz()));
		list.add("" + Util.division(Util.minus(entity.getSjz(), qn.getSjz()), entity.getSjz()));
		return list;
	}

	@Override
	public List<List<String>> getDmcbfxEntry(Date d, Company company) {
		List<DmcbfxEntity> entities = dmcbfxDao.getByDate(d, company);
		List<List<String>> result  = new ArrayList<List<String>>();
		Util.resize(result, Cbfl.END.ordinal());
		for (DmcbfxEntity entity : entities){
			result.set(entity.getCbflid(), toList(entity));
		}
		return result;
	}

	private List<String> toList(DmcbfxEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getCbflid());
		list.add("" + cbflDao.getById(entity.getCbflid()).getName());
		list.add("" + entity.getJhz());
		return list;
	}
	
	private ErrorCode entryDmcbfx(Date d, JSONArray data, Company company, ZBStatus zt){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			DmcbfxEntity entity = dmcbfxDao.getByCpfl(Integer.valueOf(row.getString(0)), d, company);
			if (entity == null){
				entity = new DmcbfxEntity();
				entity.setDwid(company.getId());
				entity.setCbflid(Integer.valueOf(row.getString(0)));
				entity.setNf(cal.get(Calendar.YEAR));
				entity.setYf(cal.get(Calendar.MONTH) + 1);
				entity.setZt(zt.ordinal());
			}

			entity.setJhz(Double.valueOf(data.getString(1)));
			dmcbfxDao.merge(entity);
		}
		return ErrorCode.OK;
	}

	@Override
	public ErrorCode saveDmcbfx(Date d, JSONArray data, Company company) {
		return entryDmcbfx(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitDmcbfx(Date d, JSONArray data, Company company) {
		return entryDmcbfx(d, data, company, ZBStatus.SUBMITTED);
	}

	@Override
	public List<List<String>> getDmcbqsfx(Date d, Company company) {
		List<DmcbfxEntity> entities = dmcbfxDao.getByYear(d, company);
		List<List<String>> result  = new ArrayList<List<String>>();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		for (int i = 0; i < Cbfl.END.ordinal(); ++i){
			List<String> list = new ArrayList<String>();
			Util.resize(list, 17);
			result.add(list);
			list.set(0, cbflDao.getById(i).getName());
		}
		
		for (DmcbfxEntity entity : entities){
			setQsList(entity, result.get(entity.getCbflid()));
		}
		
		return result;
	}

	private void setQsList(DmcbfxEntity entity, List<String> list) {
		int area = (entity.getYf() - 1) / 3;
		int offset = (entity.getYf() - 1) % 3;
		list.set(area * 4 + offset + 1, "" + entity.getSjz());
		
		list.set(3 * 4 + 3 + 1, "" + Util.division(Util.sum(new Double[]{
				entity.getSjz(), Util.toDouble(list.get(3 * 4 + 3))
		}), (3 + 1) * 3d));
		if (area < 3){
			list.set(2 * 4 + 3 + 1, "" + Util.division(Util.sum(new Double[]{
					entity.getSjz(), Util.toDouble(list.get(2 * 4 + 3))
			}), (2 + 1) * 3d));
		}
		
		if (area < 2){
			list.set(4 + 3 + 1, "" + Util.division(Util.sum(new Double[]{
					entity.getSjz(), Util.toDouble(list.get(4 + 3))
			}), (1 + 1) * 3d));
		}
		
		if (area < 1){
			list.set(3 + 1, "" + Util.division(Util.sum(new Double[]{
					entity.getSjz(), Util.toDouble(list.get(3))
			}), 3d)); 
		}
	}

}
