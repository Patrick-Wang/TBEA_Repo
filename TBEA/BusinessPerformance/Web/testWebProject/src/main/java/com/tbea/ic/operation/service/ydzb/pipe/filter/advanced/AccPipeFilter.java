package com.tbea.ic.operation.service.ydzb.pipe.filter.advanced;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;

public class AccPipeFilter implements IPipeFilter {
	protected List<Double> cacheValues;
	protected int col;
	protected Date dateStart;
	protected Date dateEnd;
	protected List<Integer> zbs;
	protected Company comp;
	protected Set<Company> includeComps;
	protected IAccumulator accumulator;
	public AccPipeFilter(IAccumulator accumulator, int col, List<Integer> zbs, Company comp, Date dateStart, Date dateEnd) {
		this(accumulator, col, zbs, comp);
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	
	public AccPipeFilter(IAccumulator accumulator, int col, List<Integer> zbs, Company comp, Date date) {
		this(accumulator, col, zbs, comp, date, date);
	}
	
	public AccPipeFilter(IAccumulator accumulator, int col, List<Integer> zbs, Company comp) {
		this.col = col;
		this.zbs = zbs;
		this.comp = comp;
		this.accumulator = accumulator;

		includeComps = new HashSet<Company>();
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

	
	protected List<Double> computeCacheValue(List<Integer> zbs, List<Company> companies){
		return accumulator.compute(col, dateStart, dateEnd, zbs, companies);
	}
	
	private void updateCacheValues(IPipe pipe) {
		if (null == cacheValues) {
			if (dateStart == null){
				dateStart = dateEnd = pipe.getDate();
			}
			cacheValues = computeCacheValue(zbs, filterCompanies(pipe.getCompanies()));
		}
	}

	@Override
	public void filter(int row, IPipe pipe) {
		if (null == cacheValues && this.comp.getType().ordinal() == pipe.getRowId(row)){
			updateCacheValues(pipe);
			for (int i = 0; i < this.zbs.size(); ++i){
				updateZb(pipe, i, pipe.getRow(this.zbs.get(i), this.comp));
			}
		}
	}

	protected void updateZb(IPipe pipe, int cacheRow, Double[] zbRow) {
		if (null != cacheValues.get(cacheRow)){
			zbRow[col] = Util.valueOf(zbRow[col]) + cacheValues.get(cacheRow);
		}
	}

}
