package com.tbea.ic.operation.service.ydzb.pipe.filter.composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;

public class RankPipeFilter implements IPipeFilter {
	List<Integer[]> reference = new ArrayList<Integer[]>();
	Map<Integer, List<Integer>> sortMap = new HashMap<Integer, List<Integer>>();

	public RankPipeFilter add(int ref, int rank) {
		reference.add(new Integer[] { ref, rank });
		return this;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		if (row == pipe.getRowCount() - 1) {
			for (int i = 0; i < reference.size(); ++i) {
				rank(reference.get(i)[0], reference.get(i)[1], pipe);
			}
		}
	}

	private int max(List<Integer> rows, Integer refCol, IPipe pipe) {
		Double maxVal = 0.0;
		Integer maxRow = 0;
		Double tmpVal = null;
		for (int i = 0; i < rows.size(); ++i) {
			tmpVal = pipe.getRow(rows.get(i))[refCol];
			if (null != tmpVal && maxVal < tmpVal) {
				maxRow = i;
				maxVal = tmpVal;
			}
		}
		return maxRow;
	}

	private void rank(Integer refCol, Integer destCol, IPipe pipe) {
		List<Integer> rows = new ArrayList<Integer>();

		int len = pipe.getRowCount();
		for (int i = 0; i < len; ++i) {
			rows.add(i);
		}

		Integer maxRow;
		for (int i = 0; i < len; ++i) {
			maxRow = max(rows, refCol, pipe);
			pipe.getRow(rows.get(maxRow))[destCol] = (double) (i + 1);
			rows.remove(maxRow.intValue());
		}
	}
}
