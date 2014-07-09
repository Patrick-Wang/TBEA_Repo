package com.excel;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ContentLinearLayout extends LinearLayout {

	Sheet sheet = null;
	Paint paint = Sheet.getEdgePaint();
	public ContentLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setWillNotDraw(false);// ±ØÐë
	}

	public ContentLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setWillNotDraw(false);// ±ØÐë
	}

	public ContentLinearLayout(Context context) {
		super(context);
		this.setWillNotDraw(false);// ±ØÐë
	}

	
	public void setSheet(Sheet sheet){
		this.sheet = sheet;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		//super.onDraw(canvas);
		
//		List<Integer> rowHeight = sheet.getRowHeight();
//		List<Integer> columWidth = sheet.getColumWidth();
//		int width = sheet.getContentWidth();
//		int height = sheet.getContentHeight();
//		
//		for (int i = columWidth.size() - 1; i >= 1; --i){
//			canvas.drawLine(width - 1, 0, width, height, paint);
//			width -= columWidth.get(i);
//		}
//		
//		for (int j = rowHeight.size() - 1; j >= 1; --j){
//			canvas.drawLine(0, height, width, height, paint);
//			height -= rowHeight.get(j);
//		}
		
		

		
	}
//	
	

}
