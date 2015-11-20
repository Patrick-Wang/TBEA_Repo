package com.tbea.ic.operation.service.util.pipe.filter.basic;

import java.util.Map;
import java.util.Set;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;

public class RatioPipeFilter extends RowPipeFilter {

//	Integer lrzeRow;
//	Integer xssrRow;
//	Integer rsRow;
//	Integer sxfyRow;
//	Integer fzzczeqms;
//	Integer zcze;
//	
//	//制造业
//	Integer zzysxfy;
//	Integer zzyxssr;
//	
//	//工程修饰业务
//	Integer gcxsywsxfy;
//	Integer gcxsywxssr;
//	
//	//物流贸易
//	Integer wlmysxfy;
//	Integer wlmyxssr;
//	Set<Integer> excludeCols = new HashSet<Integer>();
//	Map<Integer, Integer[]> ratioComputeMap = new HashMap<Integer, Integer[]>();
//	Map<Integer, Integer> indicatorMap = new HashMap<Integer, Integer>();
	private operator oper = new operator(){

		@Override
		public void invoke(IPipe pipe, Double[] zbRow, Integer[] src,
				Set<Integer> excludeCols, Map<Integer, Integer> indicatorMap) {
			if (indicatorMap.get(src[0]) != null && indicatorMap.get(src[1]) != null) {
				Double[] subs = pipe.getRow(indicatorMap.get(src[0]));
				Double[] bases = pipe.getRow(indicatorMap.get(src[1]));
				for (int i = 0; i < zbRow.length; ++i) {
					if (null != subs[i] && null != bases[i]
							&& !excludeCols.contains(i)
							&& Util.isPositive(subs[i])
							&& Util.isPositive(bases[i])) {
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

	public RatioPipeFilter exclude(Integer col) {
		excludeCols.add(col);
		return this;
	}


//	private Double[] getRow(IPipe pipe, int row, int zbId) {
//		Double[] dret = null;
//		if (indicatorMap.containsKey(indicatorMap.containsKey(zbId))){
//			indicatorMap.put(zbId, row);
//		} 
//		
//		if(ratioComputeMap.containsKey(zbId)){
//			dret = pipe.getRow(row);
//		}
//
////		if (GSZB.LRZE1 == zbId) {
////			lrzeRow = row;
////		} else if (GSZB.XSSR6.getValue() == zbId) {
////			xssrRow = row;
////		} else if (GSZB.RS61.getValue() == zbId) {
////			rsRow = row;
////		} else if (GSZB.SXFY64.getValue() == zbId) {
////			sxfyRow = row;
////		} else if (GSZB.FZZEQMS184.getValue() == zbId) {
////			fzzczeqms = row;
////		} else if (GSZB.ZCZE179.getValue() == zbId) {
////			zcze = row;
////		}  else if (GSZB.ZZY_YWKJ_228.getValue() == zbId) {
////			zzysxfy = row;
////		} else if (GSZB.XSSR_ZZYSR7.getValue() == zbId) {
////			zzyxssr = row;
////		}  else if (GSZB.GC_XSYW229.getValue() == zbId) {
////			gcxsywsxfy = row;
////		} else if (GSZB.XSSR_GCXMSR12.getValue() == zbId) {
////			gcxsywxssr = row;
////		}  else if (GSZB.WLMY230.getValue() == zbId) {
////			wlmysxfy = row;
////		} else if (GSZB.XSSR_WLMYSR16.getValue() == zbId) {
////			wlmyxssr = row;
////		} else if (GSZB.RJLR62.getValue() == zbId 
////				|| GSZB.RJSR63.getValue() == zbId
////				|| GSZB.SXFYL_65.getValue() == zbId
////				|| GSZB.XSLRL_28.getValue() == zbId
////				|| GSZB.FZL185.getValue() == zbId
////				|| GSZB.ZZYSXFYL231.getValue() == zbId
////				|| GSZB.GC_XSYWSXFYL232.getValue() == zbId
////				|| GSZB.WLMYSXFYL233.getValue() == zbId) {
////			dret = pipe.getRow(row);
////		}
//
//		return dret;
//	}
//
//	@Override
//	public void filter(int row, IPipe pipe) {
//		int zbId = pipe.getIndicator(row);
//		Double[] zbRow = getRow(pipe, row, zbId);
//		if (null != zbRow) {
//			updateZb(pipe, zbId, zbRow);
//		}
//	}

//	private void updateRatio(IPipe pipe, Double[] zbRow, Integer sub, Integer base){
//		
//	}
//	
//	private void updateZb(IPipe pipe, int zbId, Double[] zbRow) {
//		if (this.ratioComputeMap.containsKey(zbId)){
//			updateRatio(pipe, zbRow, indicatorMap.get(ratioComputeMap.get(zbId)[0]), indicatorMap.get(ratioComputeMap.get(zbId)[1]));
//		}
//
////		if (GSZB.RJLR62.getValue() == zbId) {
////			if (rsRow != null && lrzeRow != null) {
////				updateRatio(pipe, zbRow, lrzeRow, rsRow);
//////				Double[] rs = pipe.getRow(rsRow);
//////				Double[] lrze = pipe.getRow(lrzeRow);
//////				for (int i = 0; i < zbRow.length; ++i) {
//////					if (null != lrze[i] && null != rs[i]
//////							&& !excludeCols.contains(i)
//////							&& Util.isPositive(lrze[i])
//////							&& Util.isPositive(rs[i])) {
//////						zbRow[i] = lrze[i] / rs[i];
//////					}
//////				}
////			}
////		} else if (GSZB.RJSR63.getValue() == zbId) {
////			if (rsRow != null && xssrRow != null) {
////				updateRatio(pipe, zbRow, xssrRow, rsRow);
//////				Double[] rs = pipe.getRow(rsRow);
//////				Double[] xssr = pipe.getRow(xssrRow);
//////				for (int i = 0; i < zbRow.length; ++i) {
//////					if (rs[i] != null && xssr[i] != null
//////							&& !excludeCols.contains(i)
//////							&& Util.isPositive(rs[i])
//////							&& Util.isPositive(xssr[i])) {
//////
//////						zbRow[i] = xssr[i] / rs[i];
//////					}
//////				}
////			}
////		} else if (GSZB.SXFYL_65.getValue() == zbId) {
////			if (xssrRow != null && sxfyRow != null) {
////				updateRatio(pipe, zbRow, sxfyRow, xssrRow);
//////				Double[] xssr = pipe.getRow(xssrRow);
//////				Double[] sxfy = pipe.getRow(sxfyRow);
//////				for (int i = 0; i < zbRow.length; ++i) {
//////					if (sxfy[i] != null && xssr[i] != null
//////							&& !excludeCols.contains(i)
//////							&& Util.isPositive(xssr[i])
//////							&& Util.isPositive(sxfy[i])) {
//////
//////						zbRow[i] = sxfy[i] / xssr[i];
//////					}
//////				}
////			}
////		} else if (GSZB.XSLRL_28.getValue() == zbId) {
////				updateRatio(pipe, zbRow, gcxsywsxfy, gcxsywxssr);
//////				Double[] xssr = pipe.getRow(xssrRow);
//////				Double[] lrze = pipe.getRow(lrzeRow);
//////				for (int i = 0; i < zbRow.length; ++i) {
//////					if (lrze[i] != null && xssr[i] != null
//////							&& !excludeCols.contains(i)
//////							&& Util.isPositive(xssr[i])
//////							&& Util.isPositive(lrze[i])) {
//////						zbRow[i] = lrze[i] / xssr[i];
//////					}
//////				}
////		}else if (GSZB.FZL185.getValue() == zbId) {
////			if (fzzczeqms != null && zcze != null) {
////				updateRatio(pipe, zbRow, fzzczeqms, zcze);
//////				Double[] fzzczequses = pipe.getRow(fzzczeqms);
//////				Double[] zczes = pipe.getRow(zcze);
//////				for (int i = 0; i < zbRow.length; ++i) {
//////					if (fzzczequses[i] != null && zczes[i] != null
//////							&& !excludeCols.contains(i)
//////							&& !Util.isZero(zczes[i])) {
//////						zbRow[i] = fzzczequses[i] / zczes[i];
//////					}
//////				}
////			}
////		}else if (GSZB.ZZYSXFYL231.getValue() == zbId) {
////			updateRatio(pipe, zbRow, zzysxfy, zzyxssr);
////		}else if (GSZB.GC_XSYWSXFYL232.getValue() == zbId) {
////			updateRatio(pipe, zbRow, gcxsywsxfy, gcxsywxssr);
////		}else if (GSZB.WLMYSXFYL233.getValue() == zbId) {
////			updateRatio(pipe, zbRow, wlmysxfy, wlmyxssr);
////		}
//	}

}
