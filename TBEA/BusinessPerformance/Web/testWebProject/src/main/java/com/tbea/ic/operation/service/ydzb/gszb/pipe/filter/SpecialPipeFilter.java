package com.tbea.ic.operation.service.ydzb.gszb.pipe.filter;

import java.util.HashSet;
import java.util.Set;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;

public class SpecialPipeFilter implements IPipeFilter {

	Integer lrzeRow;
	Integer xssrRow;
	Integer rsRow;
	Integer sxfyRow;
	Set<Integer> excludeCols = new HashSet<Integer>();

	public SpecialPipeFilter exclude(Integer col) {
		excludeCols.add(col);
		return this;
	}

	private Double[] getRow(GszbPipe pipe, int row, int zbId) {
		Double[] dret = null;
		if (GSZB.LRZE.getValue() == zbId) {
			lrzeRow = row;
		} else if (GSZB.XSSR.getValue() == zbId) {
			xssrRow = row;
		} else if (GSZB.RS.getValue() == zbId) {
			rsRow = row;
		} else if (GSZB.SXFY.getValue() == zbId) {
			sxfyRow = row;
		} else if (GSZB.RJLR.getValue() == zbId || 
			GSZB.RJSR.getValue() == zbId || 
			GSZB.SXFYL.getValue() == zbId || 
			GSZB.XSLRL.getValue() == zbId) {
			dret = pipe.getZb(row);
		}

		return dret;
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		int zbId = pipe.getZbId(row);
		Double[] zbRow = getRow(pipe, row, zbId);
		if (null != zbRow) {
			updateZb(pipe, zbId, zbRow);
		}
	}

	private void updateZb(GszbPipe pipe, int zbId, Double[] zbRow) {

		

		if (GSZB.RJLR.getValue() == zbId) {
			Double[] rs = pipe.getZb(rsRow);
			Double[] lrze = pipe.getZb(lrzeRow);
			for (int i = 0; i < zbRow.length; ++i) {
				if (null != lrze[i] && 
					null != rs[i]  && 
					!excludeCols.contains(i) && 
					Util.isPositive(lrze[i]) &&
					Util.isPositive(rs[i])) {
					zbRow[i] = lrze[i] / rs[i];
				}
			}
		} else if (GSZB.RJSR.getValue() == zbId) {
			Double[] rs = pipe.getZb(rsRow);
			Double[] xssr = pipe.getZb(xssrRow);
			for (int i = 0; i < zbRow.length; ++i) {
				if (rs[i] != null &&
					xssr[i] != null &&
					!excludeCols.contains(i) && 
					Util.isPositive(rs[i]) && 
					Util.isPositive(xssr[i])) {
					
					zbRow[i] = xssr[i] / rs[i];
				}
			}
		} else if (GSZB.SXFYL.getValue() == zbId) {
			Double[] xssr = pipe.getZb(xssrRow);
			Double[] sxfy = pipe.getZb(sxfyRow);
			for (int i = 0; i < zbRow.length; ++i) {
				if (sxfy[i] != null &&
					xssr[i] != null &&
					!excludeCols.contains(i) && 
					Util.isPositive(xssr[i]) && 
					Util.isPositive(sxfy[i])) {
					
					zbRow[i] = sxfy[i] / xssr[i];
				}
			}
		} else if (GSZB.XSLRL.getValue() == zbId) {
			Double[] xssr = pipe.getZb(xssrRow);
			Double[] lrze = pipe.getZb(lrzeRow);
			for (int i = 0; i < zbRow.length; ++i) {
				if (lrze[i] != null &&
					xssr[i] != null &&
					!excludeCols.contains(i) &&
					Util.isPositive(xssr[i]) &&
					Util.isPositive(lrze[i])) {
					zbRow[i] = lrze[i] / xssr[i];
				}
			}
		}
	}

}
