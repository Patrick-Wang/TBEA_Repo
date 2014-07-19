package com.excel;

import com.excel.Sheet.SheetArea;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class ContentLinearLayout extends LinearLayout {

	Paint bkPaint = null;
	
	public ContentLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setWillNotDraw(false);
	}

	public ContentLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setWillNotDraw(false);
	}

	public ContentLinearLayout(Context context) {
		super(context);
		this.setWillNotDraw(false);
	}

	protected SheetArea sheetArea;
	protected int lineIndex = 0;

	public void setLinePos(SheetArea sheetArea, int lineIndex) {
		this.sheetArea = sheetArea;
		this.lineIndex = lineIndex;
	}

	public void setBKPaint(Paint paint) {
		bkPaint = paint;
		this.invalidate();
	}
	
	protected void onDraw(Canvas canvas) {

		int childCount = 0;
		int width = 0;
		if (SheetArea.Independent == sheetArea) {
			childCount = getChildCount();
			width = getWidth() - 1;

			canvas.drawLine(0, 0, width, 0, Sheet.getEdgePaint());
			canvas.drawLine(0, 0, 0, getHeight() - 1, Sheet.getEdgePaint());

		} else if (SheetArea.Content == sheetArea) {
			childCount = getChildCount() - 1;
			width = getWidth() - 1
					- this.getChildAt(this.getChildCount() - 1).getWidth();
		} else if (SheetArea.Colum_title == sheetArea) {
			childCount = getChildCount();
			width = getWidth() - 1;
			canvas.drawLine(0, 0, 0, getHeight() - 1, Sheet.getEdgePaint());
		} else if (SheetArea.Row_title == sheetArea) {
			childCount = getChildCount() - 1;
			width = getWidth() - 1
					- this.getChildAt(this.getChildCount() - 1).getWidth();
			canvas.drawLine(0, 0, width, 0, Sheet.getEdgePaint());
		}

		canvas.drawLine(0, getHeight() - 1, width, getHeight() - 1,
				Sheet.getEdgePaint());
		for (int j = 0; j < childCount; ++j) {
			View child = getChildAt(j);
			canvas.drawLine(child.getRight(), child.getTop(),
					child.getRight() - 1, child.getBottom(),
					Sheet.getEdgePaint());
		}

		if (bkPaint != null){
			canvas.drawRect(0, 0, width, getHeight() - 1, bkPaint);
		}
		
		super.onDraw(canvas);
	}
}
