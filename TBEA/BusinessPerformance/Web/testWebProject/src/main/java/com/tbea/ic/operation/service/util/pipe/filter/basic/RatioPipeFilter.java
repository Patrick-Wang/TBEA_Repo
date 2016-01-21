package com.tbea.ic.operation.service.util.pipe.filter.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;

public class RatioPipeFilter extends RowPipeFilter {

	Map<Integer, List<Integer>> excludeMap = new HashMap<Integer, List<Integer>>();
	
	private boolean isExcluded(Integer destZb, Integer col){
		if (excludeMap.containsKey(destZb)){
			if (excludeMap.get(destZb).contains(col)){
				return true;
			}
		}
		return false;
	}
	
	private operator oper = new operator(){

		@Override
		public void invoke(IPipe pipe, Integer destZb, Double[] zbRow, Integer[] src,
				Set<Integer> excludeCols, Map<Integer, Integer> indicatorMap) {
			if (indicatorMap.get(src[0]) != null && indicatorMap.get(src[1]) != null) {
				Double[] subs = pipe.getRow(indicatorMap.get(src[0]));
				Double[] bases = pipe.getRow(indicatorMap.get(src[1]));
				for (int i = 0; i < zbRow.length; ++i) {
					if (null != subs[i] && null != bases[i]
							&& !excludeCols.contains(i)
							&& Util.isPositive(subs[i])
							&& Util.isPositive(bases[i])
							&& !isExcluded(destZb, i)) {
						zbRow[i] = subs[i] / bases[i];
					}
				}
			}

		}};
	
	public RatioPipeFilter() {
		init(GSZB.RJLR62, new GSZB[]{GSZB.LRZE1, GSZB.RS61}, oper);
		init(GSZB.RJSR63, new GSZB[]{GSZB.XSSR6, GSZB.RS61}, oper);
		init(GSZB.SXFYL_65, new GSZB[]{GSZB.SXFY64, GSZB.XSSR6}, oper);
		init(GSZB.XSLRL_28, new GSZB[]{GSZB.LRZE1, GSZB.XSSR6}, oper);
		init(GSZB.FZL185, new GSZB[]{GSZB.FZZEQMS184, GSZB.ZCZE179}, oper);
		init(GSZB.ZZYSXFYL231, new GSZB[]{GSZB.ZZY_YWKJ_228, GSZB.XSSR_ZZYSR7}, oper);
		init(GSZB.GC_XSYWSXFYL232, new GSZB[]{GSZB.GC_XSYW229, GSZB.XSSR_GCXMSR12}, oper);
		init(GSZB.WLMYSXFYL233, new GSZB[]{GSZB.WLMY230, GSZB.XSSR_WLMYSR16}, oper);
	}

	public RatioPipeFilter excludeCol(Integer col) {
		excludeCols.add(col);
		return this;
	}
	
	public RatioPipeFilter exclude(Integer zb, Integer col) {
		if (excludeMap.containsKey(zb)){
			excludeMap.get(zb).add(col);
		}else{
			excludeMap.put(zb, new ArrayList<Integer>());
			excludeMap.get(zb).add(col);
		}
		return this;
	}
}
