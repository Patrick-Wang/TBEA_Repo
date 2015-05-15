package com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.companybased;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.IPipeFilter;

public class AccPipeFilter implements IPipeFilter {
	protected List<Double> cacheValues;
	protected int col;
	protected Date dateStart;
	protected Date dateEnd;
	protected int zb;
	protected CompanyType comp;
	protected Set<Company> includeComps;
	protected IAccumulator accumulator;
	public AccPipeFilter(IAccumulator accumulator, int col, int zb, CompanyType comp, Date dateStart, Date dateEnd) {
		this(accumulator, col, zb, comp);
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	
	public AccPipeFilter(IAccumulator accumulator, int col, int zb, CompanyType comp, Date date) {
		this(accumulator, col, zb, comp, date, date);
	}
	
	public AccPipeFilter(IAccumulator accumulator, int col, int zb, CompanyType comp) {
		this.col = col;
		this.zb = zb;
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

	
	protected void computeCacheValue(List<Company> companies){
		List<Integer> zbs = new ArrayList<Integer>();
		zbs.add(this.zb);
		cacheValues = accumulator.compute(col, dateStart, dateEnd, zbs, companies);
	}
	
	private void updateCacheValues(IPipe pipe) {
		if (null == cacheValues) {
			if (dateStart == null){
				dateStart = dateEnd = pipe.getDate();
			}
			computeCacheValue(filterCompanies(pipe.getCompanies()));
		}
	}

	@Override
	public void filter(int row, IPipe pipe) {
		if (this.comp == pipe.getCompanies().get(row).getType()){
			updateCacheValues(pipe);
			updateZb(row, pipe.getData(row));
		}
	}

	protected void updateZb(int row, Double[] zbRow) {
		if (null != cacheValues.get(0)){
			zbRow[col] = Util.valueOf(zbRow[col]) + cacheValues.get(0);
		}
	}

}
