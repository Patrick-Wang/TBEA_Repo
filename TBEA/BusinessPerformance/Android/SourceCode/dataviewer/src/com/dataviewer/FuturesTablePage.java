package com.dataviewer;

import com.example.dataviewer.R;
import com.excel.Sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FuturesTablePage extends AQueryFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.futures_table_page, container, false);
		update(v);
		
		return v;
	}
}
