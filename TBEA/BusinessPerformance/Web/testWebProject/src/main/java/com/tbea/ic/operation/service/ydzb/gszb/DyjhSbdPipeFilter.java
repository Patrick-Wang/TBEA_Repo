package com.tbea.ic.operation.service.ydzb.gszb;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;

public class DyjhSbdPipeFilter implements IPipeFilter {

	YDJHZBDao ydjhzbDao;
	List<Double> cacheValues;
	int col;

	public DyjhSbdPipeFilter(YDJHZBDao ndjhzbDao, int col) {
		this.ydjhzbDao = ndjhzbDao;
		this.col = col;
	}

	
	private void filterZbs(List<Integer> zbs, List<Integer> zbsTmp, List<Integer> indexList){
		int zbId = 0;
		for (int i = 0, len = zbs.size(); i < len; ++i) {
			zbId = zbs.get(i);
			if (GSZB.YSZK.getValue() == zbId ||
				GSZB.CH.getValue() == zbId) {
				zbsTmp.add(zbId);
			} else {
				indexList.add(i);
			}
		}
	}
	
	private void fillZbs(List<Integer> indexList){
		for (int i = 0, len = indexList.size(); i < len; ++i) {
			cacheValues.add(i, null);
		}
	}
	
	private void updateCacheValues(GszbPipe pipe) {
		if (null == cacheValues) {
			List<Integer> zbsTmp = new ArrayList<Integer>();
			List<Integer> indexList = new ArrayList<Integer>();
			filterZbs(pipe.getZbIds(), zbsTmp, indexList);
			cacheValues = ydjhzbDao.getDyjhz(pipe.getDate(), zbsTmp,
					pipe.getCompanies());
			fillZbs(indexList);
		}
	}

	private Double[] getRow(GszbPipe pipe, int row, int zbId) {
		return pipe.getZb(row);
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		int zbId = pipe.getZbId(row);
		if (GSZB.YSZK.getValue() == zbId || GSZB.CH.getValue() == zbId) {
			updateCacheValues(pipe);

			Double[] zbRow = getRow(pipe, row, zbId);
			updateZb(row, zbId, zbRow);
		}
	}

	private void updateZb(int row, int zbId, Double[] zbRow) {
		if (GSZB.YSZK.getValue() == zbId) {
			//zbRow[col] = cacheValues.get(lrzeRow) / cacheValues.get(rsRow);
		} else if (GSZB.CH.getValue() == zbId) {
			//zbRow[col] = cacheValues.get(xssrRow) / cacheValues.get(rsRow);
		} 
	}

}
