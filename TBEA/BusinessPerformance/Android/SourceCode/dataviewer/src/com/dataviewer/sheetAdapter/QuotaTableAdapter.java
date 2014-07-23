package com.dataviewer.sheetAdapter;

import android.view.Gravity;
import android.view.LayoutInflater;

import com.excel.CellTextView;
import com.excel.Sheet;

public class QuotaTableAdapter extends GreenCellAdapter {

	public QuotaTableAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public CellTextView getCell(LayoutInflater inflater, Sheet sheet, int row,
			int colum, String text) {
		CellTextView ctv = super.getCell(inflater, sheet, row, colum, text);
		if (row > 0 && colum == 1) {
			ctv.setGravity(Gravity.LEFT);
		}
		return ctv;
	}

}
