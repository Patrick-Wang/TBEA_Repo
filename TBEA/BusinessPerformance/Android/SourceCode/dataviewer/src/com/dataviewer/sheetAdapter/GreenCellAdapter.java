package com.dataviewer.sheetAdapter;

import java.util.LinkedList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import com.common.DisplayUtil;
import com.excel.CellTextView;
import com.excel.ContentLinearLayout;
import com.excel.Sheet;
import com.excel.Sheet.SheetArea;
import com.excel.StandardAdapter;
import com.tbea.dataviewer.R;

public class GreenCellAdapter extends StandardAdapter {

	Paint cellBKPaint = null;
	Paint titleBKPaint = null;
	Paint grayBKPaint = null;
	Paint lightGrayBKPaint = null;
	
	String previousText = "";
	List<CellTextView> textViewList = new LinkedList<CellTextView>();
	
	public GreenCellAdapter() {
		cellBKPaint = new Paint(Paint.DITHER_FLAG);
		cellBKPaint.setColor(Color.GREEN);
		cellBKPaint.setStrokeWidth(1);

		titleBKPaint = new Paint(Paint.DITHER_FLAG);
		titleBKPaint.setColor(Color.BLACK);
		titleBKPaint.setStrokeWidth(1);
		
		grayBKPaint = new Paint(Paint.DITHER_FLAG);
		grayBKPaint.setColor(Color.GRAY);

		
		lightGrayBKPaint = new Paint(Paint.DITHER_FLAG);
		lightGrayBKPaint.setColor(Color.LTGRAY);

	}

	@Override
	public CellTextView getCell(LayoutInflater inflater, Sheet Sheet, int row,
			int colum, String text) {
		CellTextView ctv = (CellTextView) inflater.inflate(R.layout.cell, null);

		if (text.length() > 1 && text.charAt(0) == '-' && text.charAt(1) != '-') {
			ctv.setBKPaint(cellBKPaint);
		}

		if (0 == row) {
			ctv.setTextColor(Color.WHITE);
			DisplayMetrics metric = Sheet.getContext().getResources().getDisplayMetrics();
			ctv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
			ctv.setPadding(DisplayUtil.dip2px(5, metric.density),
					DisplayUtil.dip2px(5, metric.density), DisplayUtil.dip2px(5, metric.density),
					DisplayUtil.dip2px(5, metric.density));
		}

		ctv.setText(text);
		
		return ctv;
	}

	
	private void mergeCells(){
		if (textViewList.size() > 1){
			int sumHeight = 0;
			for(CellTextView cell : textViewList){
				sumHeight += cell.getLayoutParams().height = 0;
			}
			textViewList.get(0).getLayoutParams().height = sumHeight;
		}
		textViewList.clear();
	}
	
	@Override
	public void adjustHeight(Sheet sheet, CellTextView cell, int row,
			int colum, int height) {
		super.adjustHeight(sheet, cell, row, colum, height);
//		if (colum == 0 && row >= sheet.getLockRowCount()){
//			if (cell.getText().toString().equals(previousText)){
//				textViewList.add(cell);				
//				if (row == sheet.getSizeManager().getRowCount() - 1){
//					mergeCells();
//				}
//			}
//			else{
//				mergeCells();
//				previousText = cell.getText().toString();
//				textViewList.add(cell);
//			}
//		}
	}

	@Override
	public CellTextView cellAt(Sheet sheet, int row, int colum) {
		// TODO Auto-generated method stub
		return super.cellAt(sheet, row, colum);
	}

	@Override
	public ContentLinearLayout getContent(LayoutInflater inflater, Sheet sheet,
			SheetArea sheetArea, int index) {
		ContentLinearLayout cll = new ContentLinearLayout(sheet.getContext());
		if ((sheetArea == SheetArea.Independent && 0 == index)
				|| (sheetArea == SheetArea.Row_title && 0 == index)) {
			cll.setBKPaint(titleBKPaint);
		} else{
			if (index % 2 == 0){
				cll.setBKPaint(grayBKPaint);
			} else{
				cll.setBKPaint(lightGrayBKPaint);
			}
		}
		
		return cll;
	}
}
