package com.tbea.ic.operation.service.ydzb.pipe.filter.composite;

import java.util.ArrayList;
import java.util.List;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;

public class RankPipeFilter implements IPipeFilter {
	List<Integer[]> reference = new ArrayList<Integer[]>();
	DoubleArrayComparator oper = new DoubleArrayComparator();
	
	public RankPipeFilter add(int ref, int rank) {
		reference.add(new Integer[] { ref, rank });
		return this;
	}

	private List<Double[]> cloneData(IPipe pipe){
		List<Double[]> result = new ArrayList<Double[]>();
		for (int i = 0; i < pipe.getRowCount(); ++i){
			result.add(pipe.getRow(i));
		}
		return result;
	}
	
	private boolean isLastRow(int row, IPipe pipe){
		return row == pipe.getRowCount() - 1;
	}
	
	@Override
	public void filter(int row, IPipe pipe) {
		if (isLastRow(row, pipe)) {
			List<Double[]> tmpTable = cloneData(pipe);
			for (int i = 0; i < reference.size(); ++i) {
				rank(tmpTable, reference.get(i)[0], reference.get(i)[1]);
			}
		}
	}

	private void rank(List<Double[]> list, Integer ref, Integer dest) {
		oper.setIndex(ref);
		list.sort(oper);
		for (int i = 0, size = list.size(); i < size; ++i){
			list.get(i)[dest] = (double) (size - i);
		}
	}
}
