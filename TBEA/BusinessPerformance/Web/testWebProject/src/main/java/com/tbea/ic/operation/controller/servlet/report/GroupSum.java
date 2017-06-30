package com.tbea.ic.operation.controller.servlet.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupSum {
	
	public List<Object[]> xnyqyGroupSum(List<Object[]> items, List<Object[]> sums){
		List<Object[]> result = new ArrayList<Object[]>();
		
//		Object dtStart = null;
//		Object dtEnd = null;
//		Object[] row  = null;
//		int id = -10;
		
		Map<Integer, List<Object>> rowsMap = new HashMap<Integer, List<Object>>();
		for (int i = 0; i < items.size(); ++i){
			for (int j = 0; j < sums.size(); ++j){
				if (((String)items.get(i)[1]).compareToIgnoreCase((String) sums.get(j)[0]) >= 0 && 
					((String)items.get(i)[2]).compareToIgnoreCase((String) sums.get(j)[1]) <= 0){
					if (!rowsMap.containsKey(j)){
						rowsMap.put(j, new ArrayList<Object>());
					}
					rowsMap.get(j).add(items.get(i));
					break;
				}
			}
		}
		
		for (int i = 0; i < sums.size(); ++i){
			if (rowsMap.containsKey(i)){
				result.addAll((List)rowsMap.get(i));
			}
			result.add(merge(-i, 9, sums.get(i)[0], sums.get(i)[1], sums.get(i)));
		}
		
//		for (int i = 0; i < items.size(); ++i){
//			
//			if (dtStart == null && dtEnd == null){
//				dtStart = items.get(i)[1];
//				dtEnd = items.get(i)[2];
//				row = items.get(i);
//				result.add(row);
//			}else{
//				if (!dtStart.equals(items.get(i)[1]) || dtEnd.equals(items.get(i)[2])){
//					row = merge(id, items.get(i).length, dtStart, dtEnd, sums.get(-(id + 10)));
//					dtStart = items.get(i)[1];
//					dtEnd = items.get(i)[2];
//					--id;
//					result.add(row);
//					result.add(items.get(i));
//				}else{
//					row = items.get(i);
//					result.add(row);
//				}
//			}
//			
//		}
//		if (!items.isEmpty()){
//			row = items.get(items.size() - 1);
//			row = merge(id, row.length, row[1], row[2], sums.get(-(id + 10)));
//			result.add(row);
//		}
		
		return result;
	}

	private Object[] merge(int id, int size, Object dtStart, Object dtEnd,
			Object[] sums) {
		Object[] row = new Object[size];
		row[0] = id;
		row[1] = dtStart;
		row[2] = dtEnd;
		row[3] = "合计";
		row[6] = sums[2];
		row[7] = sums[3];
		row[8] = sums[4];
		return row;
	}
	
	public List<Object[]> yszkhkzbGroupSum(List<Object[]> items, List<Object[]> sums){
		List<Object[]> result = new ArrayList<Object[]>();
		return xnyqyGroupSum(items, sums);
//		Object dtStart = null;
//		Object dtEnd = null;
//		Object[] row  = null;
//		int id = -10;
//		
//		for (int i = 0; i < items.size(); ++i){
//			
//			if (dtStart == null && dtEnd == null){
//				dtStart = items.get(i)[1];
//				dtEnd = items.get(i)[2];
//				row = items.get(i);
//				result.add(row);
//			}else{
//				if (dtStart.equals(items.get(i)[1]) && dtEnd.equals(items.get(i)[2])){
//					row = items.get(i);
//					result.add(row);
//				}else{
//					row = mergeYszkhkz(id, items.get(i).length, dtStart, dtEnd, sums.get(-(id + 10)));
//					dtStart = items.get(i)[1];
//					dtEnd = items.get(i)[2];
//					result.add(row);
//					result.add(items.get(i));
//					--id;
//				}
//			}
//			
//		}
//		if (!items.isEmpty()){
//			row = items.get(items.size() - 1);
//			row = mergeYszkhkz(id, row.length, row[1], row[2], sums.get(-(id + 10)));
//			result.add(row);
//		}
//		
//		return result;
	}

	private Object[] mergeYszkhkz(int id, int length, Object dtStart,
			Object dtEnd, Object[] sums) {
		Object[] row = new Object[length];
		row[0] = id;
		row[1] = dtStart;
		row[2] = dtEnd;
		row[3] = "合计";
		row[6] = sums[2];
		row[7] = sums[3];
		row[8] = sums[4];
		
		return row;
	}
}
