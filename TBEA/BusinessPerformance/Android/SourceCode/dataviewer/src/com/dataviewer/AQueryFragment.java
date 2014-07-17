package com.dataviewer;

import com.androidquery.AQuery;
import com.tbea.dataviewer.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class AQueryFragment extends Fragment {
	AQuery mAq = null;
	View mFragView = null;
	protected void update(View v){
		mAq = new AQuery(v);
		mFragView = v;
	}
	
	protected View getFragementView(){
		return mFragView;
	}
	
	protected AQuery getAQ(){
		return mAq;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (mFragView == null){
			update(onLoadView(inflater, container, savedInstanceState));
			onViewPrepared(mAq, mFragView);
		}
		//System.gc();
		return mFragView;
	}

	protected View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return null;
	}
	
	
	protected void onViewPrepared(AQuery aq, View fragView){
		
	}
	
	
	protected View detachView(View v){
		ViewParent currentParent = v.getParent();
		if (currentParent != null) {
				((ViewGroup) currentParent).removeView(v);
		}
		return v;
	}
	
	
}
