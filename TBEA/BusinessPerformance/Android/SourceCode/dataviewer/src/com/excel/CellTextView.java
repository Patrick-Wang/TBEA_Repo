package com.excel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class CellTextView extends TextView {
    Paint bkpaint = null;
	
	int adjustleft = 0;
	int adjustright = 0;
	int adjusttop = 0;
	int adjustbottom = 0;
    
    public CellTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.setWillNotDraw(false);//必须  
		this.setTextColor(Color.rgb(0, 0, 0));
		// TODO Auto-generated constructor stub
	}

	public CellTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setWillNotDraw(false);//必须  
		this.setTextColor(Color.rgb(0, 0, 0));
	}

	public CellTextView(Context context) {
		super(context);
		this.setWillNotDraw(false);//必须  
		this.setTextColor(Color.rgb(0, 0, 0));
		// TODO Auto-generated constructor stub
	}

	public CellTextView setBKColor(int color){
		bkpaint = new Paint(Paint.DITHER_FLAG);// 创建一个画笔
		bkpaint.setColor(color);// 设置为红笔
		bkpaint.setStrokeWidth(1);// 笔宽5像素
		this.invalidate();
		return this;
	}
	
	public void adjust(int l, int t, int r, int b){
		adjustleft = l;
		adjusttop = t;
		adjustright = r;
		adjustbottom = b;	

	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		if (this.getText().length() > 1 && this.getText().charAt(0) == '-'){
			setBKColor(Color.GREEN);
		}
		
		if (bkpaint != null){
			canvas.drawRect(adjustleft, adjusttop, getWidth() - adjustright, getHeight() - adjustbottom, bkpaint);
		}
		
		
		
		super.onDraw(canvas);
		
	}
}
