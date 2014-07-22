package com.dataviewer.sheetAdapter;

import java.util.LinkedList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import com.common.DisplayUtil;
import com.common.Pair;
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
	
	List<Pair<Integer, List<String>>> filters = new LinkedList<Pair<Integer, List<String>>>();
	List<Integer> hiddenRows = new LinkedList<Integer>();
	String previousText = "";
	List<Pair<Integer, Integer>> pairs = new LinkedList<Pair<Integer, Integer>>();
	Pair<Integer, Integer> curPair = new Pair<Integer, Integer>(0, 0);
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

	
	public void addFilter(int colum, List<String> keyWords){
		filters.add(new Pair<Integer, List<String>>(colum, keyWords));
	}
	
	
	private boolean match(int colum, String word){
		for(Pair<Integer, List<String>> pair : filters){
			if (pair.getFirst() == colum){
				for (String val : pair.getSecond()){
					if (val.equals(word)){
						return true;
					}
				}
			}
		}
		return false;
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
		if (!hiddenRows.contains(row)){
			if (match(colum, text)){
				hiddenRows.add(row);
			}
		}
		
		return ctv;
	}

	
	
	@Override
	public void onFinish(Sheet sheet) {
		for(int i = hiddenRows.size() - 1; i >= 0; --i){
			sheet.hideRow(hiddenRows.get(i));
		}
	}


	private void mergeCells(Sheet sheet){
//		if (textViewList.size() > 1){
//			int sumHeight = 0;
//			for(CellTextView cell : textViewList){
//				sumHeight += cell.getLayoutParams().height = 0;
//			}
//			textViewList.get(0).getLayoutParams().height = sumHeight;
//		}
//		startRow += textViewList.size();
//		textViewList.clear();
	}
	
	@Override
	public void adjustHeight(Sheet sheet, CellTextView cell, int row,
			int colum, int height) {
		super.adjustHeight(sheet, cell, row, colum, height);
		if (colum == 0 && row >= sheet.getLockRowCount()){
			if (cell.getText().toString().equals(previousText)){
				curPair.setSecond(row);
				if (row == sheet.getSizeManager().getRowCount() - 1){
					pairs.add(new Pair<Integer, Integer>(curPair.getFirst(), curPair.getSecond()));
				}
			}
			else{
				pairs.add(new Pair<Integer, Integer>(curPair.getFirst(), curPair.getSecond()));
				curPair.setFirst(row);
				curPair.setSecond(row);
				previousText = cell.getText().toString();
			}
		}
		
		if (row == (sheet.getSizeManager().getRowCount() - 1) && colum == (sheet.getSizeManager().getColumCount() - 1)){
			mergeCells(sheet);
		}
	}

	@Override
	public CellTextView cellAt(Sheet sheet, int row, int colum) {
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
