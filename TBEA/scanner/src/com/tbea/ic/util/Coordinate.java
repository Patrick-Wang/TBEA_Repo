package com.tbea.ic.util;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.DisplayMetrics;

public class Coordinate {
	private float standardX = 0;
	private float standardY = 0;
	private int currentVisibleX = 0;
	private int currentVisibleY = 0;
	private Context context = null;

	public int getCurrentVisibleX() {
		return currentVisibleX;
	}

	public int getCurrentVisibleY() {
		return currentVisibleY;
	}

	public float getScaleLandscape(){
		return currentVisibleX / standardX;
	}
	
	public float getScalePortrait(){
		return currentVisibleY / standardY;
	}
	
	public float getStandardX(){
		return standardX;
	}
	
	public float getStandardY(){
		return standardY;
	}


	public Coordinate(Context context, int standardX, int standardY){
		this.context = context;
		this.standardX = standardX;
		this.standardY = standardY;
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		this.currentVisibleY = displayMetrics.heightPixels - getStatusBarH();
		this.currentVisibleX = displayMetrics.widthPixels;
	}
	
	public int getX(int x) {
		return (int) (currentVisibleX * x / this.standardX);
	}

	public int getY(int y){
		return (int) (currentVisibleY * y / this.standardY );
	}
	
	public int getStatusBarH(){
		return getStatusBarH(context);
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
}
