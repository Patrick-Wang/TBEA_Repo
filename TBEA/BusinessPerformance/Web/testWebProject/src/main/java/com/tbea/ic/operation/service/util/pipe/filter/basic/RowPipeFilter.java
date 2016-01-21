package com.tbea.ic.operation.service.util.pipe.filter.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;

public class RowPipeFilter implements IPipeFilter {

	public interface operator{
		void invoke(IPipe pipe, Integer destZb, Double[] zbRow, Integer[] src, Set<Integer> excludeCols, Map<Integer, Integer> indicatorMap);
	}
	
	class Formula{
		Integer[] params;
		operator oper;
		public Formula(Integer[] params, operator oper) {
			super();
			this.params = params;
			this.oper = oper;
		}
		
		public void calculate(IPipe pipe, Integer destZb, Double[] zbRow, Set<Integer> excludeCols, Map<Integer, Integer> indicatorMap){
			oper.invoke(pipe, destZb, zbRow, params, excludeCols, indicatorMap);
		}
	}
	
	Set<Integer> excludeCols = new HashSet<Integer>();
	Map<Integer, Formula> computeMap = new HashMap<Integer, Formula>();
	Map<Integer, Integer> indicatorMap = new HashMap<Integer, Integer>();

	protected void init(GSZB dest, GSZB[] zbs, operator oper){
		Integer[] intZbs = new Integer[zbs.length];
		for (int i = 0; i < zbs.length; ++i){
			indicatorMap.put(zbs[i].getValue(), null);
			intZbs[i] = zbs[i].getValue();
		}
		computeMap.put(dest.getValue(), new Formula(intZbs, oper));
	}

	public RowPipeFilter excludeCol(Integer col) {
		excludeCols.add(col);
		return this;
	}


	private Double[] getRow(IPipe pipe, int row, int zbId) {
		Double[] dret = null;
		if (indicatorMap.containsKey(zbId)){
			indicatorMap.put(zbId, row);
		} 
		
		if(computeMap.containsKey(zbId)){
			dret = pipe.getRow(row);
		}
		return dret;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		int zbId = pipe.getIndicator(row);
		Double[] zbRow = getRow(pipe, row, zbId);
		if (null != zbRow) {
			updateZb(pipe, zbId, zbRow);
		}
	}

	private void updateZb(IPipe pipe, int zbId, Double[] zbRow) {
		if (this.computeMap.containsKey(zbId)){
			computeMap.get(zbId).calculate(pipe, zbId, zbRow, excludeCols, indicatorMap);
		}
	}

}
