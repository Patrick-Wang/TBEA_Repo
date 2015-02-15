package com.tbea.ic.operation.service.ydzb.gszb.pipe;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tbea.ic.operation.service.ydzb.gszb.GSZB;

public class CopyPipeFilter implements IPipeFilter {
	Map<Integer, Map<Integer, Integer>> copyMap = new HashMap<Integer, Map<Integer, Integer>>();
	public CopyPipeFilter() {

	}
	
	public CopyPipeFilter(GSZB zb, int colFrom, int colTo) {
		Map<Integer, Integer> colMap = new HashMap<Integer, Integer>();
		colMap.put(colFrom, colTo);
		this.copyMap.put(zb.getValue(), colMap);
	}
	
	public CopyPipeFilter add(GSZB zb, int colFrom, int colTo){
		Map<Integer, Integer> colMap = this.copyMap.get(zb.getValue());
		if (null == colMap){
			 colMap = new HashMap<Integer, Integer>();
			 copyMap.put(zb.getValue(), colMap);
		}
		colMap.put(colFrom, colTo);
		return this;
	}
	
	@Override
	public void filter(int row, GszbPipe pipe) {
		Integer zbId = pipe.getZbId(row);
		if (copyMap.containsKey(zbId)){
			updateZb(zbId, pipe.getZb(row));
		}
	}

	private void updateZb(int zbId, Double[] zbRow) {
		Map<Integer, Integer> colMap = copyMap.get(zbId);
		for (Entry<Integer, Integer> entry : colMap.entrySet()){
			zbRow[entry.getValue()] = zbRow[entry.getKey()];
		}
	}
}
