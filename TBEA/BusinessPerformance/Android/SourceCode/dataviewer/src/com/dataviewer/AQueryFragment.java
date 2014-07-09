package com.dataviewer;

import com.androidquery.AQuery;

import android.app.Fragment;
import android.view.View;

public class AQueryFragment extends Fragment {
	AQuery aq = null;
	
	protected void update(View v){
		aq = new AQuery(v);
	}
	
}
