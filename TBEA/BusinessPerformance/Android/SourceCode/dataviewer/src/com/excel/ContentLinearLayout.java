package com.excel;

import android.content.Context;
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
	

	

}
