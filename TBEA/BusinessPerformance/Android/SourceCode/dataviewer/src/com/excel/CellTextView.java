package com.excel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class CellTextView extends TextView {
	Paint bkpaint = null;
	public CellTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setWillNotDraw(false);
		this.setTextColor(Color.rgb(0, 0, 0));
	}

	public CellTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setWillNotDraw(false);
		this.setTextColor(Color.rgb(0, 0, 0));
	}

	public CellTextView(Context context) {
		super(context);
		this.setWillNotDraw(false);
		this.setTextColor(Color.rgb(0, 0, 0));
	}
	
	public void setBKPaint(Paint paint) {
		bkpaint = paint;
		this.invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {

		if (bkpaint != null) {
			canvas.drawRect(0, 0, getWidth() - 2, getHeight() - 2, bkpaint);
		}

		super.onDraw(canvas);
	}
}
