package com.tbea.ic.operation.service.util.pipe.filter.composite;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;

public class AccPipeFilter extends AbstractPipeFilter {
	protected List<Double> cacheValues;
	protected int col;
	protected Date dateStart;
	protected Date dateEnd;
	protected List<Integer> zbs;
	protected Set<String> includeComps = new HashSet<String>();
	protected int rowStart;
	protected IAccumulator accumulator;
	public AccPipeFilter(IAccumulator accumulator, int col, List<Integer> zbs, int rowStart, int offset, Date dateStart, Date dateEnd) {
		this(accumulator, col, zbs, rowStart, offset);
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	
	public AccPipeFilter(IAccumulator accumulator, int col, List<Integer> zbs, int rowStart, int step, Date date) {
		this(accumulator, col, zbs, rowStart, step, date, date);
	}
	
	
	public AccPipeFilter include(Company comp){
		includeComps.add(comp.getUniqueId());
		return this;
	}
	
	public AccPipeFilter includeCompanies(List<Company> comps){
		for(Company tmpComp : comps){
			include(tmpComp);
		}
		return this;
	}
	
	
	public AccPipeFilter(IAccumulator accumulator, int col, List<Integer> zbs, int rowStart, int step) {
		this.col = col;
		this.zbs = zbs;
		this.rowStart = rowStart;
		this.accumulator = accumulator;
		this.includeRow(rowStart, step);
	}
	

	
	private List<Company> filterCompanies(List<Company> comps){
		List<Company> compTmps = new ArrayList<Company>();
		if (includeComps.isEmpty()){
			return compTmps;
		} else{
			for (Company comp : comps){
				if (includeComps.contains(comp.getUniqueId())){
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
		if (cacheValues == null && contains(row)){
			updateCacheValues(pipe);
			//在第一次计算时计算出所有指标行值，以免后续与之关联的指标计算时无法取到值
			for (int i = 0; i < zbs.size(); ++i){
				updateZb(pipe, i, pipe.getRow(rowInner2Outer(i, 0)));
			}
		}
	}

	protected void updateZb(IPipe pipe, int cacheRow, Double[] zbRow) {
		if (null != cacheValues.get(cacheRow)){
			zbRow[col] = Util.valueOf(zbRow[col]) + cacheValues.get(cacheRow);
		}
	}

}
