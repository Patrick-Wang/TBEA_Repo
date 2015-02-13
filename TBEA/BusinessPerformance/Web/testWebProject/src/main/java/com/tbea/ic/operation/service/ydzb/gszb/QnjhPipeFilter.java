package com.tbea.ic.operation.service.ydzb.gszb;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;

public class QnjhPipeFilter implements IPipeFilter {

	NDJHZBDao ndjhzbDao;
	List<Double> qnjhValues;
	Integer lrzeRow;
	Integer xssrRow;
	Integer rsRow;
	Integer sxfyRow;
	int col;
	public QnjhPipeFilter(NDJHZBDao ndjhzbDao, int col) {
		this.ndjhzbDao = ndjhzbDao;
		this.col = col;
	}

	private void updateQnjhValues(GszbPipe pipe){
		if (null == qnjhValues){
			List<Integer> zbs = pipe.getZbIds();
			List<Integer> zbsTmp = new ArrayList<Integer>();
			List<Integer> indexList = new ArrayList<Integer>();
			int zbId = 0;
			for (int i = 0, len = zbs.size(); i < len;++i){
				zbId = zbs.get(i);
				switch(zbId){
				case 0:
				case 1:
				case 2:
					indexList.add(i);
					break;
				default:
					zbsTmp.add(zbId);
					break;
				}
			}
			qnjhValues = ndjhzbDao.getQnjhz(pipe.getDate(), zbsTmp, pipe.getCompanies());
			for (int i = 0, len = indexList.size(); i < len; ++i){
				qnjhValues.add(i, null);
			}
		}
	}
	
	private Double[] getRow(GszbPipe pipe, int row, int zbId){
		switch(zbId){
		case 0:
			lrzeRow = row;
			break;
		case 1:
			xssrRow = row;
			break;
		case 2:
			rsRow = row;
			break;
		case 3:
			sxfyRow = row;
			break;
		}
		return pipe.getZb(row);
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		updateQnjhValues(pipe);
		int zbId = pipe.getZbId(row);
		Double[] zbRow = getRow(pipe, row, zbId);
		updateZb(row, zbId, zbRow);
	}

	private void updateZb(int row, int zbId, Double[] zbRow) {
		switch(zbId){
		case 0:
			zbRow[col] = qnjhValues.get(lrzeRow)/qnjhValues.get(rsRow);
			break;
		case 1:
			zbRow[col] = qnjhValues.get(xssrRow)/qnjhValues.get(rsRow);
			break;
		case 2:
			zbRow[col] = qnjhValues.get(sxfyRow)/qnjhValues.get(rsRow);
			break;
		default:
			zbRow[col] = qnjhValues.get(row);
			break;
		}
	}
}
