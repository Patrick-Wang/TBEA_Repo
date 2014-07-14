package com.excel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class IndependentTitleLinearLayout extends LinearLayout {
	Paint paint = Sheet.getEdgePaint();
	
	public IndependentTitleLinearLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.setWillNotDraw(false);//±ØÐë  
		// TODO Auto-generated constructor stub
	}

	public IndependentTitleLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setWillNotDraw(false);//±ØÐë  
	}

	public IndependentTitleLinearLayout(Context context) {
		super(context);
		this.setWillNotDraw(false);//±ØÐë  
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawLine(
				0, 
				0, 0, getHeight() - 1, paint);	
		canvas.drawLine(
				0, getHeight() - 1,
				getWidth() - 1, getHeight() - 1, paint);
		canvas.drawLine(
				getWidth() - 1, getHeight() - 1,
				getWidth() - 1, 0, paint);
		canvas.drawLine(
				getWidth() - 1, 0,
				0, 0, paint);
		
	}
}
