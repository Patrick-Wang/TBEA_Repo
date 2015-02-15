package com.tbea.ic.operation.service.ydzb.gszb.pipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tbea.ic.operation.common.GSZB;

public class CopyPipeFilter implements IPipeFilter {
	Map<Integer, List<Integer[]>> copyMap = new HashMap<Integer, List<Integer[]>>();
	public CopyPipeFilter() {

	}
	
	public CopyPipeFilter(GSZB zb, int colFrom, int colTo) {
		List<Integer[]> colMap = new ArrayList<Integer[]>();
		colMap.add(new Integer[]{colFrom, colTo});
		this.copyMap.put(zb.getValue(), colMap);
	}
	
	public CopyPipeFilter add(GSZB zb, int colFrom, int colTo){
		List<Integer[]> colMap = this.copyMap.get(zb.getValue());
		if (null == colMap){
			colMap = new ArrayList<Integer[]>();
			this.copyMap.put(zb.getValue(), colMap);
		}
		colMap.add(new Integer[]{colFrom, colTo});
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
		List<Integer[]> colMap = copyMap.get(zbId);
		for (Integer[] cols : colMap){
			zbRow[cols[1]] = zbRow[cols[0]];
		}
	}
}
