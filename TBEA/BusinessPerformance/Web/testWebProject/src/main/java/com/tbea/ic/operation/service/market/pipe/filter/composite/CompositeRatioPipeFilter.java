package com.tbea.ic.operation.service.market.pipe.filter.composite;

import java.util.HashSet;
import java.util.Set;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.market.Indicator;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.filter.composite.AbstractPipeFilter;

public class CompositeRatioPipeFilter extends AbstractPipeFilter {

	Integer tbjeRow;
	Integer qyjeRow;
	Set<Integer> excludeCols = new HashSet<Integer>();

	public CompositeRatioPipeFilter(int rowStart, int step) {
		this.includeRow(rowStart, step);
	}

	
	public CompositeRatioPipeFilter exclude(Integer col) {
		excludeCols.add(col);
		return this;
	}

	
	private Double[] getRow(IPipe pipe, int row, int zbId) {
		Double[] dret = null;
		if (Indicator.TBJE.ordinal() == zbId) {
			tbjeRow = row;
		} else if (Indicator.QYJE.ordinal() == zbId) {
			qyjeRow = row;
		} else if (Indicator.ZBL.ordinal() == zbId) {
			dret = pipe.getRow(row);
		}

		return dret;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		if (contains(row)){
			int zbId = pipe.getIndicator(row);
			Double[] zbRow = getRow(pipe, row, zbId);
			if (null != zbRow) {
				updateZb(pipe, zbId, zbRow);
			}
		}
	}

	private void updateZb(IPipe pipe, int zbId, Double[] zbRow) {
		if (Indicator.ZBL.ordinal() == zbId) {
			if (tbjeRow != null && qyjeRow != null) {
				Double[] tb = pipe.getRow(tbjeRow);
				Double[] qy = pipe.getRow(qyjeRow);
				for (int i = 0; i < zbRow.length; ++i) {
					if (null != tb[i] && null != qy[i]
							&& !excludeCols.contains(i)
							&& Util.isPositive(qy[i])
							&& Util.isPositive(tb[i])) {
						zbRow[i] = qy[i] / tb[i];
					}
				}
			}
		}		
	}
}
