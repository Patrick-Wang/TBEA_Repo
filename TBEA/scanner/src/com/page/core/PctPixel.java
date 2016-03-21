package com.page.core;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.DisplayMetrics;

public class PctPixel {
	private int visibleX = 0;
	private int visibleY = 0;
	
	public PctPixel(Context context) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		this.visibleY = displayMetrics.heightPixels - getStatusBarH(context);
		this.visibleX = displayMetrics.widthPixels;
	}

	public static int getStatusBarH(Context context){
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sbar;
	}
		
	public boolean isX(int size){
		int xy = size / 100000;
		return xy == 1;
	}
	
	public boolean isY(int size){
		int xy = size / 100000;
		return xy == 2;
	}
	
	public double toPct(int size){
		int dot = size % 100000 / 10000;
		int val = size % 10000;
		int dotCount = (int) Math.pow(10, dot);
		int low = val % dotCount;
		int high = val / dotCount;
		double pct = Double.valueOf(high + "." + low);
		return pct / 100;
	}
	
	public int toPixel(int pix){
		if (isX(pix)){
			return Pct2PixelX(toPct(pix));
		}else if(isY(pix)){
			return Pct2PixelY(toPct(pix));
		}else{
			return pix;
		}
	}
	
	public int Pct2PixelX(Double pctx){
		Double val = pctx * visibleX;
		return (int) Math.round(val);
	}
	
	public int Pct2PixelY(Double pcty){
		Double val = pcty * visibleY;
		return (int) Math.round(val);
	}
}
