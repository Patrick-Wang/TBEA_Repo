package com.dataviewer;

import com.androidquery.AQuery;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
		return mFragView;
	}

	protected View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return null;
	}
	
	
	protected void onViewPrepared(AQuery aq, View fragView){
		
	}
	
	
	
}
