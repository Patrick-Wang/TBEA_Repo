package com.excel;

import android.view.LayoutInflater;

import com.excel.Sheet.Adapter;
import com.excel.Sheet.SheetArea;
import com.tbea.dataviewer.R;

public class StandardAdapter implements Adapter {

	@Override
	public CellTextView getCell(LayoutInflater inflater, Sheet Sheet,
			int row, int colum, String text) {
		return (CellTextView) inflater.inflate(R.layout.cell, null);
	}

	@Override
	public ContentLinearLayout getContent(LayoutInflater inflater,
			Sheet sheet, SheetArea sheetArea, int index) {
		return new ContentLinearLayout(sheet.getContext());
	}

	@Override
	public void adjustWidth(Sheet sheet, CellTextView cell, int row,
			int colum, int width) {
		cell.getLayoutParams().width = width;
	}

	@Override
	public void adjustHeight(Sheet sheet, CellTextView cell, int row,
			int colum, int height) {
		cell.getLayoutParams().height = height;		
	}

}
