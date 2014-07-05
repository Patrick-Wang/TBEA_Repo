package com.ctrl;
import java.util.LinkedList;

import android.content.Context;
import android.util.AttributeSet;



public class AssociationVerticalScrollView extends EnhanceScroll {

	public AssociationVerticalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public AssociationVerticalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public AssociationVerticalScrollView(Context context) {
		super(context);
	}

	private LinkedList<AssociationVerticalScrollView> mList = new LinkedList<AssociationVerticalScrollView>();
	
	
	public void association(AssociationVerticalScrollView asv){
		mList.offer(asv);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {

		for (int i = mList.size() - 1; i >= 0; --i) {
			if (mList.get(i).getScrollY() != t) {
				mList.get(i).scrollTo(mList.get(i).getScrollX(), t);
			}
		}
		
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
	@Override
	public void fling(int velocityY) {
		super.fling(0);
	}

}
