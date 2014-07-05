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
		this.setWillNotDraw(false);//±ÿ–Î  
		// TODO Auto-generated constructor stub
	}

	public IndependentTitleLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setWillNotDraw(false);//±ÿ–Î  
	}

	public IndependentTitleLinearLayout(Context context) {
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
