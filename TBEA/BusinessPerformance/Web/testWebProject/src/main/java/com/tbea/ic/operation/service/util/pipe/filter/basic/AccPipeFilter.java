package com.tbea.ic.operation.service.util.pipe.filter.basic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;

public class AccPipeFilter implements IPipeFilter {
	protected List<Double> cacheValues;
	protected int col;
	protected Date dateStart;
	protected Date dateEnd;
	protected Set<Integer> includeZbs;
	protected Set<Company> includeComps;
	protected IAccumulator accumulator;
	public AccPipeFilter(IAccumulator accumulator, int col, Date dateStart, Date dateEnd) {
		this(accumulator, col);
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	
	public AccPipeFilter(IAccumulator accumulator, int col, Date date) {
		this(accumulator, col, date, date);
	}
	
	public AccPipeFilter(IAccumulator accumulator, int col) {
		this.col = col;
		this.accumulator = accumulator;
		includeZbs = new HashSet<Integer>();
		includeComps = new HashSet<Company>();
	}
	
	public AccPipeFilter exclude(GSZB zb){
		includeZbs.remove(zb.getValue());
		return this;
	}
	
	public AccPipeFilter excludeZbs(List<Integer> zbIds){
		for (Integer zb : zbIds){
			includeZbs.remove(zb);
		}
		return this;
	}
	
	public AccPipeFilter cleanZb(){
		includeZbs.clear();
		return this;
	}
	
	
	public AccPipeFilter include(GSZB zb){
		includeZbs.add(zb.getValue());
		return this;
	}
	
	public AccPipeFilter include(Integer zb){
		includeZbs.add(zb);
		return this;
	}
	
	public AccPipeFilter includeZbs(List<Integer> zbIds){
		includeZbs.addAll(zbIds);
		return this;
	}
	
	public AccPipeFilter exclude(Company comp){
		includeComps.remove(comp);
		return this;
	}
	
	public AccPipeFilter excludeCompanies(List<Company> comps){
		for (Company comp : comps){
			includeComps.remove(comp);
		}
		return this;
	}
	
	public AccPipeFilter include(Company comp){
		includeComps.add(comp);
		return this;
	}
	
	public AccPipeFilter includeCompanies(List<Company> comps){
		includeComps.addAll(comps);
		return this;
	}
	
	public AccPipeFilter cleanCompany(){
		includeComps.clear();
		return this;
	}
	
	
	private void filterZbs(List<Integer> zbs, List<Integer> zbsTmp, List<Integer> excludeList){
		int zbId = 0;
		for (int i = 0, len = zbs.size(); i < len; ++i) {
			zbId = zbs.get(i);
			if (!includeZbs.contains(zbId)) {
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
		List<Company> compTmps = new ArrayList<Company>();
		if (includeComps.isEmpty()){
			return compTmps;
		} else{
			for (Company comp : comps){
				if (includeComps.contains(comp)){
					compTmps.add(comp);
				}
			}
			return compTmps;
		}
	}

	
	protected void computeCacheValue(List<Integer> zbs, List<Company> companies){
		cacheValues = accumulator.compute(col, dateStart, dateEnd, zbs, companies);
	}
	
	private void updateCacheValues(IPipe pipe) {
		if (null == cacheValues) {
			List<Integer> zbsTmp = new ArrayList<Integer>();
			List<Integer> excludeList = new ArrayList<Integer>();
			filterZbs(pipe.getIndicators(), zbsTmp, excludeList);
			if (dateStart == null){
				dateStart = dateEnd = pipe.getDate();
			}
			
			computeCacheValue(zbsTmp, filterCompanies(pipe.getCompanies()));
			
			fillZbs(excludeList);
		}
	}

	@Override
	public void filter(int row, IPipe pipe) {
		int zbId = pipe.getIndicator(row);
		if (includeZbs.contains(zbId)){
			updateCacheValues(pipe);
			updateZb(row, zbId, pipe.getRow(row));
		}
	}

	protected void updateZb(int row, int zbId, Double[] zbRow) {
		if (null != cacheValues.get(row)){
			zbRow[col] = Util.valueOf(zbRow[col]) + cacheValues.get(row);
		}
	}

}
