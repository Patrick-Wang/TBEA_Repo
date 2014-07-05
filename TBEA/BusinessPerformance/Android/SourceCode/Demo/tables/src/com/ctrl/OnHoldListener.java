package com.ctrl;

import android.view.MotionEvent;
import android.view.View;

public interface OnHoldListener {
	public void onHold(View v, MotionEvent e);

	public void onHoldMove(View v, MotionEvent e);

	public void onHoldRelease(View v, MotionEvent e);

	public void onFling(View v, MotionEvent e1, MotionEvent e2,
			float velocityX, float velocityY);
	
	public void onTab(View v, MotionEvent e);
}