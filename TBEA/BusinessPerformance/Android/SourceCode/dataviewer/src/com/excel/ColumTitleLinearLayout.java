package com.excel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class ColumTitleLinearLayout extends LinearLayout {
	Paint paint = Sheet.getEdgePaint();

	public ColumTitleLinearLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.setWillNotDraw(false);// ±ÿ–Î
		// TODO Auto-generated constructor stub
	}

	public ColumTitleLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setWillNotDraw(false);// ±ÿ–Î
	}

	public ColumTitleLinearLayout(Context context) {
		super(context);
		this.setWillNotDraw(false);// ±ÿ–Î
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// paint.setStyle(Style.STROKE);// …Ë÷√∑«ÃÓ≥‰
		// paint.setStrokeWidth(1);// ± øÌ5œÒÀÿ
		// paint.setColor(Color.RED);// …Ë÷√Œ™∫Ï± 
		// paint.setAntiAlias(true);// æ‚≥›≤ªœ‘ æ

//		int width = getWidth() - 1
//				- this.getChildAt(this.getChildCount() - 1).getWidth();
		
		int height = getHeight() - 1 - Sheet.blank_row_height;
				//- this.getChildAt(this.getChildCount() - 1).getWidth();
		
		canvas.drawLine(0, 0, 0, height, paint);

		canvas.drawLine(getWidth() - 1, 0, getWidth() - 1, height, paint);

		for (int i = 0; i < getChildCount() - 1; ++i) {
			View child = getChildAt(i);
			canvas.drawLine(0, child.getBottom() - 1, child.getRight() - 1,
					child.getBottom() - 1, paint);
		}

	}
}
