package com.dataviewer.sheetAdapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;

import com.excel.CellTextView;
import com.excel.ContentLinearLayout;
import com.excel.Sheet;
import com.excel.Sheet.SheetArea;
import com.excel.StandardAdapter;
import com.tbea.dataviewer.R;

public class GreenCellAdapter extends StandardAdapter {


	Paint cellBKPaint = null;
	Paint titleBKPaint = null;
	public GreenCellAdapter(){
		cellBKPaint = new Paint(Paint.DITHER_FLAG);
		cellBKPaint.setColor(Color.GREEN);
		cellBKPaint.setStrokeWidth(1);
		
		titleBKPaint = new Paint(Paint.DITHER_FLAG);
		titleBKPaint.setColor(Color.BLACK);
		titleBKPaint.setStrokeWidth(1);
	}
	
	@Override
	public CellTextView getCell(LayoutInflater inflater, Sheet Sheet, int row,
			int colum, String text) {
		CellTextView ctv = (CellTextView) inflater.inflate(R.layout.cell, null);

		if (text.length() > 1 && text.charAt(0) == '-') {
			ctv.setBKPaint(cellBKPaint);
		}

		if (0 == row){
			ctv.setTextColor(Color.WHITE);
		}
		
		return ctv;
	}

	@Override
	public ContentLinearLayout getContent(LayoutInflater inflater, Sheet sheet,
			SheetArea sheetArea, int index) {
		ContentLinearLayout cll = new ContentLinearLayout(sheet.getContext());
		if ((sheetArea == SheetArea.Independent && 0 == index) || (sheetArea == SheetArea.Row_title && 0 == index)){
			cll.setBKPaint(titleBKPaint);
		}
		return cll;
	}
}
