package com.excel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class ContentLineLinearLayout extends LinearLayout {
	Paint paint = Sheet.getEdgePaint();

	public ContentLineLinearLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.setWillNotDraw(false);//必须 
	}

	public ContentLineLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setWillNotDraw(false);//必须 
	}

	public ContentLineLinearLayout(Context context) {
		super(context);
		this.setWillNotDraw(false);//必须 
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//super.onDraw(canvas);
		// paint.setStyle(Style.STROKE);// 设置非填充


		canvas.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1, paint);
		for (int j = 0; j < getChildCount(); ++j) {
			View child = getChildAt(j);
			canvas.drawLine(child.getRight(), child.getTop(), child.getRight() - 1,
					child.getBottom(), paint);
		}

	}
}
