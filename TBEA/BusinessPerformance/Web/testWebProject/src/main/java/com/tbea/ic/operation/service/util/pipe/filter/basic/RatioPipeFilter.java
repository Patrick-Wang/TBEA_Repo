package com.tbea.ic.operation.service.util.pipe.filter.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.util.tools.MathUtil;

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
							&& MathUtil.isPositive(subs[i])
							&& MathUtil.isPositive(bases[i])
							&& !isExcluded(destZb, i)) {
						zbRow[i] = subs[i] / bases[i];
					}
				}
			}

		}};
	
	public RatioPipeFilter() {
		init(GSZB.RJLR62.value(), new int[]{GSZB.LRZE1.value(), GSZB.RS61.value()}, oper);
		init(GSZB.RJSR63.value(), new int[]{GSZB.XSSR6.value(), GSZB.RS61.value()}, oper);
		init(GSZB.SXFYL_65.value(), new int[]{GSZB.SXFY64.value(), GSZB.XSSR6.value()}, oper);
		init(GSZB.XSLRL_28.value(), new int[]{GSZB.LRZE1.value(), GSZB.XSSR6.value()}, oper);
		init(GSZB.FZL185.value(), new int[]{GSZB.FZZEQMS184.value(), GSZB.ZCZE179.value()}, oper);
		init(GSZB.ZZYSXFYL231.value(), new int[]{GSZB.ZZY_YWKJ_228.value(), GSZB.XSSR_ZZYSR7.value()}, oper);
		init(GSZB.GC_XSYWSXFYL232.value(), new int[]{GSZB.GC_XSYW229.value(), GSZB.XSSR_GCXMSR12.value()}, oper);
		init(GSZB.WLMYSXFYL233.value(), new int[]{GSZB.WLMY230.value(), GSZB.XSSR_WLMYSR16.value()}, oper);
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
