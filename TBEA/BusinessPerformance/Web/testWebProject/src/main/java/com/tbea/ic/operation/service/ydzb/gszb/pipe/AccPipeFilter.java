package com.tbea.ic.operation.service.ydzb.gszb.pipe;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.gszb.GSZB;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;

public class AccPipeFilter implements IPipeFilter {
	protected List<Double> cacheValues;
	protected int col;
	protected Date dateStart;
	protected Date dateEnd;
	protected Set<Integer> excludeZbs;
	protected Set<Company> excludeComps;
	protected IAccumulator accumulator;
	public AccPipeFilter(IAccumulator accumulator, int col, Date dateStart, Date dateEnd) {
		this.col = col;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.accumulator = accumulator;
		excludeZbs = new HashSet<Integer>();
		excludeZbs.add(GSZB.YSZK.getValue());
		excludeZbs.add(GSZB.CH.getValue());
		excludeZbs.add(GSZB.RJLR.getValue());
		excludeZbs.add(GSZB.RJSR.getValue());
		excludeZbs.add(GSZB.SXFYL.getValue());
		
		excludeComps = new HashSet<Company>();
	}
	
	public AccPipeFilter removeExclude(GSZB zb){
		excludeZbs.remove(zb.getValue());
		return this;
	}
	
	public AccPipeFilter addExclude(GSZB zb){
		excludeZbs.add(zb.getValue());
		return this;
	}
	
	public AccPipeFilter addZbExclude(List<Integer> zbIds){
		excludeZbs.addAll(zbIds);
		return this;
	}
	
	
	public AccPipeFilter addExclude(Company comp){
		excludeComps.add(comp);
		return this;
	}
	
	public AccPipeFilter addExclude(List<Company> comps){
		excludeComps.addAll(comps);
		return this;
	}
	
	
	private void filterZbs(List<Integer> zbs, List<Integer> zbsTmp, List<Integer> excludeList){
		int zbId = 0;
		for (int i = 0, len = zbs.size(); i < len; ++i) {
			zbId = zbs.get(i);
			if (excludeZbs.contains(zbId)) {
				excludeList.add(i);
			} else {
				zbsTmp.add(zbId);
			}
		}
	}
	
	private void fillZbs(List<Integer> excludeList){
		for (int i = 0, len = excludeList.size(); i < len; ++i) {
			cacheValues.add(excludeList.get(i), null);
		}
	}
	
	private List<Company> filterCompanies(List<Company> comps){
		if (excludeComps.isEmpty()){
			return comps;
		} else{
			List<Company> compTmps = new ArrayList<Company>();
			for (Company comp : comps){
				if (!excludeComps.contains(comp)){
					compTmps.add(comp);
				}
			}
			return compTmps;
		}
	}

	private void updateCacheValues(GszbPipe pipe) {
		if (null == cacheValues) {
			List<Integer> zbsTmp = new ArrayList<Integer>();
			List<Integer> excludeList = new ArrayList<Integer>();
			filterZbs(pipe.getZbIds(), zbsTmp, excludeList);
			cacheValues = accumulator.compute(dateStart, dateEnd, zbsTmp, filterCompanies(pipe.getCompanies()));
			fillZbs(excludeList);
		}
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		int zbId = pipe.getZbId(row);
		if (!excludeZbs.contains(zbId)){
			updateCacheValues(pipe);
			updateZb(row, zbId, pipe.getZb(row));
		}
	}

	private void updateZb(int row, int zbId, Double[] zbRow) {
		zbRow[col] = Util.valueOf(zbRow[col]) + cacheValues.get(row);
	}
}
