package com.tbea.ic.operation.service.cpzlqk.pdcpycssbhgxxfb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.dwmc.DwmcDao;
import com.tbea.ic.operation.model.dao.cpzlqk.pdbhgwtmx.PdBhgwtmxDao;
import com.tbea.ic.operation.model.dao.cpzlqk.pdbhgwtmx.PdBhgwtmxDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.pdbhglb.PdBhglbDao;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.pdbhglb.PdBhglbDaoImpl;
import com.tbea.ic.operation.model.entity.identifier.cpzlqk.PdBhglbEntity;
import com.util.tools.ListUtil;
import com.util.tools.MathUtil;
import com.xml.frame.report.util.EasyCalendar;

@Service(PdcpycssbhgxxfbServiceImpl.NAME)
@Transactional("transactionManager")
public class PdcpycssbhgxxfbServiceImpl implements PdcpycssbhgxxfbService {
	@Resource(name = PdBhglbDaoImpl.NAME)
	PdBhglbDao pdBhglbDao;

	@Resource(name = PdBhgwtmxDaoImpl.NAME)
	PdBhgwtmxDao pdBhgwtmxDao;

	@Autowired
	DwmcDao dwmcDao;

	public final static String NAME = "PdcpycssbhgxxfbServiceImpl";

	private List<List<String>> getPdcpycssbhgxxfb(Date d, YDJDType yjType, List<Object[]> entities, List<Object[]> hjEntities){
		List<List<String>> result = new ArrayList<List<String>>();
		if (!entities.isEmpty()) {

			List<PdBhglbEntity> bhglbs = pdBhglbDao.getAll();
			Map<Integer, Integer> bhglbsIds = new HashMap<Integer, Integer>();
			for (int i = 0; i < bhglbs.size(); ++i) {
				bhglbsIds.put(bhglbs.get(i).getId(), i + 1);
			}

			List<Integer> hj = ListUtil.resize(new ArrayList<Integer>(),
					bhglbs.size() + 1);

			Map<Integer, Integer> dwCpMap = new HashMap<Integer, Integer>();

			for (Object[] entity : entities) {
				if (!dwCpMap.containsKey((Integer) entity[0])) {
					List<String> list = ListUtil.resize(new ArrayList<String>(),
							2 + bhglbs.size());
					result.add(list);
					list.set(0, dwmcDao.getByDwid((Integer) entity[0])
							.getDwmc().getName());

					dwCpMap.put((Integer) entity[0], result.size() - 1);
				}

				List<String> row = result.get(dwCpMap.get((Integer) entity[0]));
				
				
				row.set(bhglbsIds.get((Integer) entity[1]), ""
						+ ((Long) entity[2]).intValue());
				
				hj.set(bhglbsIds.get((Integer) entity[1]) - 1, MathUtil.sum(
						hj.get(bhglbsIds.get((Integer) entity[1]) - 1),
						((Long) entity[2]).intValue()));
			}
			
			for (int i = 0; i < hjEntities.size(); ++i){
				if (dwCpMap.containsKey(hjEntities.get(i)[0])){
					List<String> row = result.get(dwCpMap.get(hjEntities.get(i)[0]));
					row.set(row.size() - 1, "" + ((Long)hjEntities.get(i)[1]).intValue());
					hj.set(hj.size() - 1, MathUtil.sum(
							hj.get(hj.size() - 1),
							((Long)hjEntities.get(i)[1]).intValue()));
				}	
			}

			List<String> list = new ArrayList<String>();
			result.add(list);
			list.add("合计");
			for (int i = 0; i < hj.size(); ++i) {
				list.add("" + hj.get(i));
			}
		}
		return result;
	}
	
	
	@Override
	public List<List<String>> getPdcpycssbhgxxfb(Date d, YDJDType yjType,  List<Integer> zts) {
		List<Object[]> entities = null;// [Integer dwid, Integer bhglxid, Long
										// count]
		List<Object[]> hjEntities = null;
		if (yjType == YDJDType.YD) {
			hjEntities = pdBhgwtmxDao.getByYdFbHj(d, zts);
			entities = pdBhgwtmxDao.getByYdFb(d, zts);
		} else {
			hjEntities = pdBhgwtmxDao.getByJdFbHj(d, zts);
			entities = pdBhgwtmxDao.getByJdFb(d, zts);
		}
		return this.getPdcpycssbhgxxfb(d, yjType, entities, hjEntities);
	}

	@Override
	public List<String> getBhglbs() {
		List<PdBhglbEntity> bhglbs = pdBhglbDao.getAll();
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < bhglbs.size(); ++i) {
			result.add(bhglbs.get(i).getName());
		}
		return result;
	}

	@Override
	public List<List<String>> getPdcpycssbhgxxfb(Date d, YDJDType yjType, Company company,  List<Integer> zts) {
		List<Object[]> entities = null;// [Integer dwid, Integer bhglxid, Long
		// count]
		List<Object[]> hjEntities = null;//[Integer dwid, Long
		                          		// count]
		if (yjType == YDJDType.YD) {
			hjEntities = pdBhgwtmxDao.getByYdFbHj(d, company,
					zts);
			entities = pdBhgwtmxDao.getByYdFb(d, company,
					zts);
		} else {
			hjEntities = pdBhgwtmxDao.getByJdFbHj(d, company,
					zts);
			entities = pdBhgwtmxDao.getByJdFb(d, company,
					zts);
		}
		return this.getPdcpycssbhgxxfb(d, yjType, entities, hjEntities);
	}

	private WaveItem getWaveItem(List<WaveItem> wis, String name){
		for (int i = 0; i < wis.size(); ++i){
			if (wis.get(i).getName().equals(name)){
				return wis.get(i);
			}
		}
		WaveItem item = new WaveItem(name, ListUtil.resize(new ArrayList<String>(), 12, "0"));
		wis.add(item);
		return item;
	}
	
	
	@Override
	public List<WaveItem> getWaveItems(Date d, YDJDType yjType,  List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		EasyCalendar cal = new EasyCalendar();
		cal.setTime(d);
		cal.setMonth(1);
		List<Object[]> hjEntities = null;//[Integer dwid, Long
  		// count]
		for (int i = 0; i < 12; ++i){
			hjEntities = pdBhgwtmxDao.getByYdFbHj(cal.getDate(), zts);
			for (int j = 0; j < hjEntities.size(); ++j){
				getWaveItem(wis, dwmcDao.getByDwid((Integer) hjEntities.get(j)[0])
						.getDwmc().getName())
						.getData()
						.set(i, ((Long)hjEntities.get(j)[1]).intValue() + "");
			}
			
			cal.addMonth(1);
		}
		return wis;
	}


	@Override
	public List<WaveItem> getWaveItems(Date d, YDJDType yjType, Company company,  List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		EasyCalendar cal = new EasyCalendar();
		cal.setTime(d);
		cal.setMonth(1);
		List<Object[]> hjEntities = null;//[Integer dwid, Long
  		// count]
		for (int i = 0; i < 12; ++i){
			hjEntities = pdBhgwtmxDao.getByYdFbHj(cal.getDate(), company, zts);
			
			for (int j = 0; j < hjEntities.size(); ++j){
				getWaveItem(wis, company.getName())
						.getData()
						.set(i, ((Long)hjEntities.get(j)[1]).intValue() + "");
			}
			cal.addMonth(1);
		}
		return wis;
	}
}
