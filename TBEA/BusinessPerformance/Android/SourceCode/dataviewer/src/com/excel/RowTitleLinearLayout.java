package com.excel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class RowTitleLinearLayout extends LinearLayout {

	Paint paint = Sheet.getEdgePaint();
	
	public RowTitleLinearLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.setWillNotDraw(false);//±ÿ–Î  
		// TODO Auto-generated constructor stub
	}

	public RowTitleLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setWillNotDraw(false);//±ÿ–Î  
	}

	public RowTitleLinearLayout(Context context) {
		super(context);
		this.setWillNotDraw(false);//±ÿ–Î  
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//paint.setStyle(Style.STROKE);// …Ë÷√∑«ÃÓ≥‰
//		paint.setStrokeWidth(1);// ± øÌ5œÒÀÿ
//		paint.setColor(Color.RED);// …Ë÷√Œ™∫Ï± 
		//paint.setAntiAlias(true);// æ‚≥›≤ªœ‘ æ
		canvas.drawLine(
				0, 
				getHeight() - 1, 
				getWidth() - Sheet.blank_row_width,
				getHeight() - 1, paint);
		
		canvas.drawLine(
				0, 
				0, 
				getWidth()- Sheet.blank_row_width,
				0, paint);
		
	
		for (int i = 0; i < getChildCount(); ++i) {
			View child = getChildAt(i);
			canvas.drawLine(child.getRight() - 1, child.getTop(), child.getRight() - 1,
					child.getBottom(), paint);
		}
		
		
	}

}
