package com.dataviewer.sheetAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.common.DisplayUtil;
import com.common.Pair;
import com.excel.CellTextView;
import com.excel.ContentLinearLayout;
import com.excel.Sheet;
import com.excel.Sheet.SheetArea;
import com.excel.SizeManager;
import com.excel.StandardAdapter;
import com.tbea.dataviewer.R;

public class GreenCellAdapter extends StandardAdapter {

	private Sheet sheet = null;
	Paint cellBKPaint = null;
	Paint titleBKPaint = null;
//	Paint grayBKPaint = null;
//	Paint lightGrayBKPaint = null;
	boolean finished = false;
	Map<Integer, List<String>> filters = new HashMap<Integer, List<String>>();
	List<Integer> hiddenRows = new LinkedList<Integer>();
	String previousText = "";
	List<Pair<Integer, Integer>> mergePairs = new LinkedList<Pair<Integer, Integer>>();
	Pair<Integer, Integer> curPair = new Pair<Integer, Integer>(0, 0);
	public GreenCellAdapter() {
		cellBKPaint = new Paint(Paint.DITHER_FLAG);
		cellBKPaint.setColor(Color.GREEN);
		cellBKPaint.setStrokeWidth(1);

		titleBKPaint = new Paint(Paint.DITHER_FLAG);
		titleBKPaint.setColor(Color.BLACK);
		titleBKPaint.setStrokeWidth(1);
	}

	public void addFilter(int colum, List<String> keyWords) {
		filters.put(colum, keyWords);
		List<Integer> hiddenBefore = hiddenRows;
		hiddenRows = new LinkedList<Integer>();
		for (int i = sheet.getSizeManager().getRowCount() - 1; i >= 0; --i) {
			if (match(colum, sheet.cell(i, colum).getText().toString())) {
				hiddenRows.add(i);
			}
		}
		makeHidden(hiddenBefore);
	}

	private void makeHidden(List<Integer> hiddenBefore) {
		if (null != hiddenBefore) {
			for (int i = hiddenBefore.size() - 1; i >= 0; --i) {
				if (!hiddenRows.contains(hiddenBefore.get(i))) {
					sheet.showRow(hiddenBefore.get(i));
				}
			}
		}

		for (int i = hiddenRows.size() - 1; i >= 0; --i) {
			sheet.hideRow(hiddenRows.get(i));
		}
	}

	private boolean match(int colum, String word) {
		for (Integer key : filters.keySet()) {
			if (key == colum) {
				for (String val : filters.get(key)) {
					if (word.contains(val)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public CellTextView getCell(LayoutInflater inflater, Sheet sheet, int row,
			int colum, String text) {
		this.sheet = sheet;
		CellTextView ctv = (CellTextView) inflater.inflate(R.layout.cell, null);

		if (null != text && text.length() > 1 && text.charAt(0) == '-' && text.charAt(1) != '-') {
			ctv.setBKPaint(cellBKPaint);
		}

		if (0 == row) {
			ctv.setTextColor(Color.WHITE);
			DisplayMetrics metric = sheet.getContext().getResources()
					.getDisplayMetrics();
			ctv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
			ctv.setPadding(DisplayUtil.dip2px(5, metric.density),
					DisplayUtil.dip2px(5, metric.density),
					DisplayUtil.dip2px(5, metric.density),
					DisplayUtil.dip2px(5, metric.density));
		}
//		else if (!allCellCenter && colum == 1){
//			ctv.setGravity(Gravity.LEFT);
//		}
		
		ctv.setText(text);

		return ctv;
	}

	@Override
	public void onFinish(Sheet sheet) {
		makeHidden(null);
	}

	
	private void mergeCells(Sheet sheet, int startRow, int endRow){
		LinearLayout colum_title = sheet.getSheetLayout(SheetArea.Colum_title);
		int lockRowCount = sheet.getLockRowCount();
		int lockColumCount = sheet.getLockColumCount();
		LinearLayout ll = new LinearLayout(sheet.getContext());
		ll.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(param);
		
		LinearLayout lltemp = new LinearLayout(sheet.getContext());
		ll.setOrientation(LinearLayout.HORIZONTAL);
		param = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lltemp.setLayoutParams(param);
		
		LinearLayout firstChild = (LinearLayout) colum_title.getChildAt(startRow - lockRowCount);
		
		
//		
		for (int i = startRow - lockColumCount; i >= 1; --i){
			View view = colum_title.getChildAt(i);
			firstChild.removeView(view);
			lltemp.addView(view);
		}
//		
		ll.addView(lltemp);
//		
		SizeManager sm = sheet.getSizeManager();
		int sumHeight = sm.getHeight(startRow);
		for (int i = startRow + 1; i < endRow; --i){
			LinearLayout llrow = (LinearLayout) colum_title.getChildAt(i- lockColumCount);
			colum_title.removeView(llrow);
			ll.addView(llrow);
			llrow.getChildAt(0).setVisibility(View.GONE);
			sumHeight += sm.getHeight(i);
		}
		
		firstChild.addView(ll);
		
		firstChild.getChildAt(0).getLayoutParams().height = sumHeight;
	}
	
	private void mergeCells(Sheet sheet) {
		for (int i = this.mergePairs.size() - 1; i >= 0; --i){
			if (mergePairs.get(i).getSecond() > mergePairs.get(i).getFirst()){
				mergeCells(sheet, mergePairs.get(i).getFirst(), mergePairs.get(i).getSecond());
			}
			else {
				mergePairs.remove(i);
			}
		}
	}

	@Override
	public void adjustHeight(Sheet sheet, CellTextView cell, int row,
			int colum, int height) {
		super.adjustHeight(sheet, cell, row, colum, height);
//		if (colum == 0 && row >= sheet.getLockRowCount()) {
//			if (cell.getText().toString().equals(previousText)) {
//				curPair.setSecond(row);
//				if (row == sheet.getSizeManager().getRowCount() - 1) {
//					mergePairs.add(new Pair<Integer, Integer>(curPair.getFirst(),
//							curPair.getSecond()));
//				}
//			} else {
//				mergePairs.add(new Pair<Integer, Integer>(curPair.getFirst(),
//						curPair.getSecond()));
//				curPair.setFirst(row);
//				curPair.setSecond(row);
//				previousText = cell.getText().toString();
//			}
//		}

//		if (row == (sheet.getSizeManager().getRowCount() - 1)
//				&& colum == (sheet.getSizeManager().getColumCount() - 1)) {
//			mergeCells(sheet);
//		}
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
		} else {
//			if (index % 2 == 0) {
//				cll.setBKPaint(grayBKPaint);
//			} else {
//				cll.setBKPaint(lightGrayBKPaint);
//			}
		}

		return cll;
	}
}
