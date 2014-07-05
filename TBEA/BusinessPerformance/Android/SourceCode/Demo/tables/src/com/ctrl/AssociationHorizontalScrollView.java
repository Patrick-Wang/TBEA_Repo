package com.ctrl;

import java.util.LinkedList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class AssociationHorizontalScrollView extends HorizontalScrollView implements IScrollLocker {

	private boolean enableScroll = true;
	
	public AssociationHorizontalScrollView(Context context) {
		super(context);
	}

	public AssociationHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AssociationHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	private LinkedList<AssociationHorizontalScrollView> mList = new LinkedList<AssociationHorizontalScrollView>();
	
	
	public void association(AssociationHorizontalScrollView asv){
		mList.offer(asv);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		
		for (int i = 0; i < mList.size(); i++) {
			if (mList.get(i).getScrollX() != l) {
				mList.get(i).scrollTo(l, (int) mList.get(i).getScrollY());
			}
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
	@Override
	public void fling(int velocityX) {

		super.fling(0);
	}
		
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (enableScroll) {
			return super.onInterceptTouchEvent(ev);
		}
		else{
			return false;
		}
	}

	public boolean isEnableScroll() {
		return enableScroll;
	}

	public void setEnableScroll(boolean enableScroll) {
		this.enableScroll = enableScroll;
	}

	
}
