package com.excel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.excel.Sheet.Adapter;
import com.excel.Sheet.SheetArea;
import com.tbea.dataviewer.R;

public class StandardAdapter implements Adapter {

	@Override
	public CellTextView getCell(LayoutInflater inflater, Sheet Sheet, int row,
			int colum, String text) {
		CellTextView ctv = (CellTextView) inflater.inflate(R.layout.cell, null);
		ctv.setText(text);
		return ctv;
	}

	@Override
	public ContentLinearLayout getContent(LayoutInflater inflater, Sheet sheet,
			SheetArea sheetArea, int index) {
		return new ContentLinearLayout(sheet.getContext());
	}

	@Override
	public void adjustWidth(Sheet sheet, CellTextView cell, int row, int colum,
			int width) {
		cell.getLayoutParams().width = width;
	}

	@Override
	public void adjustHeight(Sheet sheet, CellTextView cell, int row,
			int colum, int height) {
		cell.getLayoutParams().height = height;
	}

	@Override
	public CellTextView cellAt(Sheet sheet, int row, int colum) {
		View ret = null;
		if (row < sheet.getLockRowCount() && colum < sheet.getLockColumCount()) {
			ret = ((LinearLayout) sheet.getSheetLayout(SheetArea.Independent).getChildAt(row))
					.getChildAt(colum);
		} else if (colum < sheet.getLockColumCount()) {
			ret = ((LinearLayout) sheet.getSheetLayout(SheetArea.Colum_title).getChildAt(row
					- sheet.getLockRowCount())).getChildAt(colum);
		} else if (row < sheet.getLockRowCount()) {
			ret = ((LinearLayout) sheet.getSheetLayout(SheetArea.Row_title).getChildAt(row))
					.getChildAt(colum - sheet.getLockColumCount());
		} else {
			ret = ((ViewGroup) sheet.getSheetLayout(SheetArea.Content)
					.getChildAt(row - sheet.getLockRowCount()))
					.getChildAt(colum - sheet.getLockColumCount());
		}	
		return (CellTextView) ret;
	}

	@Override
	public void onFinish(Sheet sheet) {
		// TODO Auto-generated method stub
		
	}
}
