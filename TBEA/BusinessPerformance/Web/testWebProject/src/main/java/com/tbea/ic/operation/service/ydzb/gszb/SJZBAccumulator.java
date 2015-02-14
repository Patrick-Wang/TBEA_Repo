package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;



public class SJZBAccumulator {

	public interface Algorithm{
		Double onCompute(Integer id, Integer compId, Date date, double acc, double value);
	}
	
	
	SJZBDao sjzbDao;
	
	YJ20ZBDao yj20zbDao;
	
	YJ28ZBDao yj28zbDao;
	
	YDZBZTDao ydzbztDao;
	
	Map<Integer, Integer> zbMap = new HashMap<Integer, Integer>();
	
	Algorithm algorithm = new Algorithm(){
		@Override
		public Double onCompute(Integer id, Integer compId, Date date, double acc,
				double value) {
			return acc + value;
		}
		
	};
	
	public SJZBAccumulator(SJZBDao sjzbDao, YJ20ZBDao yj20zbDao,
			YJ28ZBDao yj28zbDao, YDZBZTDao ydzbztDao) {
		super();
		this.sjzbDao = sjzbDao;
		this.yj20zbDao = yj20zbDao;
		this.yj28zbDao = yj28zbDao;
		this.ydzbztDao = ydzbztDao;
	}



	public List<Double> compute(Date start, Date end, List<Company> companies, List<Integer> zbs){
		List<Double> ret = new ArrayList<Double>();
		for (int i = 0, len = zbs.size(); i < len; ++i){
			zbMap.put(zbs.get(i), i);
			ret.add(0.0);
		}
		
		
		
		List<YDZBZT> ydzbzts = ydzbztDao.getYdzbzt(companies, start, end);
		List<YDZBZT> yd20zbzts = new ArrayList<YDZBZT>();
		List<YDZBZT> yd28zbzts = new ArrayList<YDZBZT>();
		List<YDZBZT> sjzbzts = new ArrayList<YDZBZT>();
		for (YDZBZT ydzbzt : ydzbzts){
			switch(ydzbzt.getZt()){
			case 1:
				yd20zbzts.add(ydzbzt);
				break;
			case 2:
				yd28zbzts.add(ydzbzt);
				break;
			case 3:
				sjzbzts.add(ydzbzt);
				break;
			}
		}
		
		List<YJ20ZB> yj20zbs = yj20zbDao.getYj20zbs(yd20zbzts);
		List<YJ28ZB> yj28zbs = yj20zbDao.getYj28zbs(yd28zbzts);
		List<SJZB> sjzbs = yj20zbDao.getSjzbs(sjzbzts);
		
		for (int i = 0, len = yj20zbs.size(); i < len; ++i){
			YJ20ZB yj20Zb = yj20zbs.get(i);
			Double val = ret.get(zbMap.get(yj20Zb.getId()));
			algorithm.onCompute(
					yj20Zb.getId(), 
					yj20Zb.getDwxx().getId(), 
					Date.valueOf(yj20Zb.getNf() + "-" + yj20Zb.getYf() + "-1"), 
					val, 
					yj20Zb.getYj20z());
		}
		
		for (int i = 0, len = yj28zbs.size(); i < len; ++i){
			
		}

		for (int i = 0, len = sjzbs.size(); i < len; ++i){
			
		}
		
		return ret;
	}


	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}
	
}
