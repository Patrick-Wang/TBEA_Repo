package com.page.core;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.AdapterView;

import com.androidquery.AQuery;
import com.squareup.otto.Bus;

public abstract class Page extends Fragment {
	View mPage = null;
	AQuery mAQuery = null;
	boolean mInit = false;
	protected PageActivity activity() {
		return (PageActivity) getActivity();
	}

	@Override
	public void onAttach(Activity activity) {
		bus().register(this);
		super.onAttach(activity);
	}

	protected void delayed(Runnable run){
		delayed(run, 0);
	}
	
	protected void delayed(Runnable run, long time){
		activity().delayed(run, time);
	}
	
	@Override
	public void onDetach() {
		bus().unregister(this);
		super.onDetach();
	}

	protected void update(View v) {
		mPage = v;
	}

	protected View page() {
		return mPage;
	}

	protected AQuery query(int id) {
		return mAQuery.id(id);
	}

	protected Bus bus() {
		return activity().bus();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mPage == null) {
			mPage = inflater.inflate(onGetLayoutId(), container, false);
			mAQuery = new AQuery(mPage);
		}
		return mPage;
	}


	
	public void updateLayout(){
		activity().coordinatePreProcess(mPage);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (!mInit){
			mInit = true;
			updateLayout();
			onInitialize();	
		}
	}

	abstract protected void onInitialize();

	abstract protected int onGetLayoutId();

	protected void goBack(){
		getFragmentManager().popBackStack();
	}
	
	protected void navigateTo(Page page) { 
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(activity().hostId(), page).addToBackStack(null);
		ft.commit();
	}

	protected void leaveTo(Page page) {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(activity().hostId(), page);
		ft.commit();
	}

	protected View detachView(View v) {
		ViewParent currentParent = v.getParent();
		if (currentParent != null) {
			((ViewGroup) currentParent).removeView(v);
		}
		return v;
	}

	@SuppressWarnings("deprecation")
	protected void unbindDrawables(View view) {
		if (view.getBackground() != null) {
			view.getBackground().setCallback(null);
			view.setBackgroundDrawable(null);
		}
		if (view instanceof ViewGroup && !(view instanceof AdapterView)
				&& !(view instanceof WebView)) {
			for (int i = ((ViewGroup) view).getChildCount() - 1; i >= 0; --i) {
				unbindDrawables(((ViewGroup) view).getChildAt(i));
				((ViewGroup) view).removeViewAt(i);
			}
		}
	}

	@Override
	public void onDestroy() {
		unbindDrawables(mPage);
		mPage = null;
		mAQuery = null;
		mInit = false;
		super.onDestroy();
	}
}
