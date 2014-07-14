package com.ctrl;

import java.util.LinkedList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class AssociationVerticalScrollView extends EnhanceScroll {

	private boolean scrollFinished = true;

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

	OnScrollFinished scrollFinishedEvent = null;

	public void setOnScrollFinished(OnScrollFinished event) {
		scrollFinishedEvent = event;
	}

	private LinkedList<AssociationVerticalScrollView> mList = new LinkedList<AssociationVerticalScrollView>();

	public void association(AssociationVerticalScrollView asv) {
		mList.offer(asv);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		scrollFinished = false;
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

	private void scrollFinished() {
		scrollFinished = true;
		if (scrollFinishedEvent != null) {
			scrollFinishedEvent.onScrollFinished(this);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_UP) {
			if (!scrollFinished) {
				scrollFinished();
			}
		}
		return super.onTouchEvent(ev);
	}

}
