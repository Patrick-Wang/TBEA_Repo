package com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.indicatorbased;

import java.util.HashSet;
import java.util.Set;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.IndicatorBasedPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.IPipeFilter;

public class RatioPipeFilter implements IPipeFilter {

	Integer lrzeRow;
	Integer xssrRow;
	Integer rsRow;
	Integer sxfyRow;
	Set<Integer> excludeCols = new HashSet<Integer>();

	public RatioPipeFilter exclude(Integer col) {
		excludeCols.add(col);
		return this;
	}

	private Double[] getRow(IPipe pipe, int row, int zbId) {
		Double[] dret = null;
		if (GSZB.LRZE.getValue() == zbId) {
			lrzeRow = row;
		} else if (GSZB.XSSR.getValue() == zbId) {
			xssrRow = row;
		} else if (GSZB.RS.getValue() == zbId) {
			rsRow = row;
		} else if (GSZB.SXFY.getValue() == zbId) {
			sxfyRow = row;
		} else if (GSZB.RJLR.getValue() == zbId || GSZB.RJSR.getValue() == zbId
				|| GSZB.SXFYL.getValue() == zbId
				|| GSZB.XSLRL.getValue() == zbId) {
			dret = pipe.getData(row);
		}

		return dret;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		int zbId = pipe.getRowId(row);
		Double[] zbRow = getRow(pipe, row, zbId);
		if (null != zbRow) {
			updateZb(pipe, zbId, zbRow);
		}
	}

	private void updateZb(IPipe pipe, int zbId, Double[] zbRow) {
		if (GSZB.RJLR.getValue() == zbId) {
			if (rsRow != null && lrzeRow != null) {
				Double[] rs = pipe.getData(rsRow);
				Double[] lrze = pipe.getData(lrzeRow);
				for (int i = 0; i < zbRow.length; ++i) {
					if (null != lrze[i] && null != rs[i]
							&& !excludeCols.contains(i)
							&& Util.isPositive(lrze[i])
							&& Util.isPositive(rs[i])) {
						zbRow[i] = lrze[i] / rs[i];
					}
				}
			}
		} else if (GSZB.RJSR.getValue() == zbId) {
			if (rsRow != null && xssrRow != null) {
				Double[] rs = pipe.getData(rsRow);
				Double[] xssr = pipe.getData(xssrRow);
				for (int i = 0; i < zbRow.length; ++i) {
					if (rs[i] != null && xssr[i] != null
							&& !excludeCols.contains(i)
							&& Util.isPositive(rs[i])
							&& Util.isPositive(xssr[i])) {

						zbRow[i] = xssr[i] / rs[i];
					}
				}
			}
		} else if (GSZB.SXFYL.getValue() == zbId) {
			if (xssrRow != null && sxfyRow != null) {
				Double[] xssr = pipe.getData(xssrRow);
				Double[] sxfy = pipe.getData(sxfyRow);
				for (int i = 0; i < zbRow.length; ++i) {
					if (sxfy[i] != null && xssr[i] != null
							&& !excludeCols.contains(i)
							&& Util.isPositive(xssr[i])
							&& Util.isPositive(sxfy[i])) {

						zbRow[i] = sxfy[i] / xssr[i];
					}
				}
			}
		} else if (GSZB.XSLRL.getValue() == zbId) {
			if (xssrRow != null && lrzeRow != null) {
				Double[] xssr = pipe.getData(xssrRow);
				Double[] lrze = pipe.getData(lrzeRow);
				for (int i = 0; i < zbRow.length; ++i) {
					if (lrze[i] != null && xssr[i] != null
							&& !excludeCols.contains(i)
							&& Util.isPositive(xssr[i])
							&& Util.isPositive(lrze[i])) {
						zbRow[i] = lrze[i] / xssr[i];
					}
				}
			}
		}
	}

}
