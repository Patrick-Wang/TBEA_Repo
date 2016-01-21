package com.tbea.ic.operation.service.util.pipe.filter.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;

public class CopyPipeFilter implements IPipeFilter {
	Map<Integer, List<Integer[]>> copyMap = new HashMap<Integer, List<Integer[]>>();
	public CopyPipeFilter() {

	}
	
	public CopyPipeFilter(GSZB zb, int colFrom, int colTo) {
		add(zb, colFrom, colTo);
	}
	
	public CopyPipeFilter add(Integer zb, int colFrom, int colTo){
		List<Integer[]> colMap = this.copyMap.get(zb);
		if (null == colMap){
			colMap = new ArrayList<Integer[]>();
			this.copyMap.put(zb, colMap);
		}
		colMap.add(new Integer[]{colFrom, colTo});
		return this;
	}
	
	public CopyPipeFilter add(GSZB zb, int colFrom, int colTo){
		return add(zb.getValue(), colFrom, colTo);
	}
	
	public CopyPipeFilter add(List<Integer> zbs, int colFrom, int colTo){
		for (Integer zb : zbs){
			add(zb, colFrom, colTo);
		}
		return this;
	}
	
	@Override
	public void filter(int row, IPipe pipe) {
		Integer zbId = pipe.getIndicator(row);
		if (copyMap.containsKey(zbId)){
			updateZb(zbId, pipe.getRow(row));
		}
	}

	private void updateZb(int zbId, Double[] zbRow) {
		List<Integer[]> colMap = copyMap.get(zbId);
		for (Integer[] cols : colMap){
			zbRow[cols[1]] = zbRow[cols[0]];
		}
	}
}
