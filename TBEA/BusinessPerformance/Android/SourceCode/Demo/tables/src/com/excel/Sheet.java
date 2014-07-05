package com.excel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ctrl.AssociationHorizontalScrollView;
import com.ctrl.AssociationVerticalScrollView;
import com.excel.sheet.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Sheet extends LinearLayout {

	private LinearLayout lh_title_row = null;
	private LinearLayout lv_independent_title = null;
	private LinearLayout lv_title_colum = null;
	private LinearLayout lv_content = null;
	private int columCount = 0;
	private int rowCount = 0;
	private List<Integer> columWidth = new LinkedList<Integer>();
	private List<Integer> rowHeight = new LinkedList<Integer>();
	private LayoutInflater inflater = null;
	static Paint paint = null;// 创建一个画笔
	
	public static void setEdgeColor(int color){
		 getEdgePaint().setColor(color);
	}
	
	public static Paint getEdgePaint(){
		if (paint == null){
			paint = new Paint(Paint.DITHER_FLAG);// 创建一个画笔
			paint.setColor(Color.BLACK);// 设置为红笔
			paint.setStrokeWidth(1);// 笔宽5像素
		}
		return paint;
	}
	
	public Sheet(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public Sheet(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Sheet(Context context) {
		super(context);
		init();
	}

	private void init() {
		this.setWillNotDraw(false);//必须  
		inflater = LayoutInflater.from(getContext());
		addView(inflater.inflate(R.layout.title, null));
		addView(inflater.inflate(R.layout.body, null));
		lh_title_row = (LinearLayout) findViewById(R.id.title_row);
		lv_independent_title = (LinearLayout) findViewById(R.id.independent_title);
		lv_title_colum = (LinearLayout) findViewById(R.id.title_colum);
		lv_content = (LinearLayout) findViewById(R.id.content);
		AssociationHorizontalScrollView title_row_scr = (AssociationHorizontalScrollView) findViewById(R.id.title_row_scr);
		AssociationHorizontalScrollView content_hscr = (AssociationHorizontalScrollView) findViewById(R.id.content_hscr);
		title_row_scr.association(content_hscr);
		content_hscr.association(title_row_scr);
		AssociationVerticalScrollView title_colum_scr = (AssociationVerticalScrollView) findViewById(R.id.title_colum_vscr);
		AssociationVerticalScrollView content_vscr = (AssociationVerticalScrollView) findViewById(R.id.content_vscr);
		title_colum_scr.association(content_vscr);
		content_vscr.association(title_colum_scr);
	}

	public CellTextView cell(int row, int colum) {
		View ret = null;
		if (row == 0 && colum == 0) {
			ret = lv_independent_title.getChildAt(0);
		} else if (colum == 0) {
			ret = lv_title_colum.getChildAt(row - 1);
		} else if (row == 0) {
			ret = lh_title_row.getChildAt(colum - 1);
		} else {
			ret = ((LinearLayout) lv_content.getChildAt(row - 1))
					.getChildAt(colum - 1);
		}
		return (CellTextView) ret;
	}

	public int getColumCount() {
		return columCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	private void updateColumWidth(int colum, int width) {
		columWidth.set(colum, width);
		for (int i = getRowCount() - 1; i >= 0; --i) {
			cell(i, colum).getLayoutParams().width = width;
		}
	}

	private void updateRowHeight(int row, int height) {
		rowHeight.set(row, height);
		for (int i = getColumCount() - 1; i >= 0; --i) {
			cell(row, i).getLayoutParams().height = height;
		}
	}

	private void updateCellSize(List<Integer> tmpColumWidth,
			List<Integer> tmpRowHeight) {

		for (int i = 0, len = tmpColumWidth.size(); i < len; ++i) {
			if (!columWidth.isEmpty() && columWidth.size() >= (i + 1)) {
				if (columWidth.get(i) < tmpColumWidth.get(i)) {
					updateColumWidth(i, tmpColumWidth.get(i));
				}
				else {
					cell(rowCount - 1, i).getLayoutParams().width = columWidth.get(i);
				}
			} else {
				columWidth.add(0);
				updateColumWidth(i, tmpColumWidth.get(i));
			}
		}

		for (int i = 0, len = tmpRowHeight.size(); i < len; ++i) {
			if (!rowHeight.isEmpty() && rowHeight.size() >= (i + 1)) {
				if (rowHeight.get(i) < tmpRowHeight.get(i)) {
					updateRowHeight(i, tmpRowHeight.get(i));
				}else {
					cell(i, columCount - 1).getLayoutParams().height = rowHeight.get(i);
				}
			} else {
				rowHeight.add(0);
				updateRowHeight(i, tmpRowHeight.get(i));
			}
		}
	}

	private void updateSizeList(View view, int row, int colum,
			List<Integer> tmpColumWidth, List<Integer> tmpRowHeight) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		tmpRowHeight.set(row, view.getMeasuredHeight());
		tmpColumWidth.set(colum, view.getMeasuredWidth());
	}

	public void AddRecord(String[] record) {
		CellTextView tv = null;
		++rowCount;
		columCount = record.length;
		List<Integer> tmpColumWidth = new ArrayList<Integer>(columCount);
		List<Integer> tmpRowHeight = new ArrayList<Integer>(rowCount);
		for (int i = 0; i < columCount; ++i) {
			tmpColumWidth.add(0);
		}
		for (int i = 0; i < rowCount; ++i) {
			tmpRowHeight.add(0);
		}

		tv = (CellTextView) inflater.inflate(R.layout.cell, null);
		tv.setText(record[0]);

		if (lv_independent_title.getChildCount() == 0) {
			lv_independent_title.addView(tv);
			tv.adjust(1,  1,  1, 1);
		} else {
			lv_title_colum.addView(tv);
			tv.adjust(1,  0,  1, 1);
		}
		updateSizeList(tv, rowCount - 1, 0, tmpColumWidth, tmpRowHeight);

		if (lh_title_row.getChildCount() == 0) {
			for (int i = 1, len = record.length; i < len; ++i) {
				tv = (CellTextView) inflater.inflate(R.layout.cell, null);
				tv.setText(record[i]);
				updateSizeList(tv, rowCount - 1, i, tmpColumWidth, tmpRowHeight);
				lh_title_row.addView(tv);
				tv.adjust(0,  1,  1, 1);
			}
		} else {
			LinearLayout ll = new ContentLineLinearLayout(getContext());
			ll.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			ll.setLayoutParams(param);
			lv_content.addView(ll);

			for (int i = 1, len = record.length; i < len; ++i) {
				tv = (CellTextView) inflater.inflate(R.layout.cell, null);
				tv.setText(record[i]);
				updateSizeList(tv, rowCount - 1, i, tmpColumWidth, tmpRowHeight);
				ll.addView(tv);
				tv.adjust(0,  0,  1, 1);
			}
		}
		updateCellSize(tmpColumWidth, tmpRowHeight);
	}

}
