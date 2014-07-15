package com.dataviewer;

import com.example.dataviewer.R;
import com.excel.Sheet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class FuturesTablePage extends AQueryFragment {
	
	static String[][] records = new String[][]{
		{"公司","类型","持仓量","持仓均价","现价","盈亏比例","盈亏金额"},
		{"沈变公司","CU1407","300","50000.00","40000.00","-3","-60000.00"},
		{"沈变公司","CU1408","200","40000.00","40000.00","8","300000.00"},
		{"沈变公司","CU1409","600","40000.00","40000.00","8","90000.00"},
		{"沈变公司","CU1410","300","40000.00","40000.00","7","100000.00"},
		{"衡变公司","CU1408","200","40000.00","40000.00","5","300000.00"},
		{"衡变公司","CU1409","400","40000.18","40000.00","6","100000.00"},
		{"衡变公司","CU1410","150","50000.33","40000.00","-6","-40000.00"},
		{"衡变公司","CU1411","500","40000.00","40000.00","6","10000.00"},
		{"衡变公司","CU1412","110","50000.04","40000.00","-7","-400000.00"},
		{"衡变公司","CU1501","500","50000.00","40000.00","-6","-10000.00"},
		{"鲁缆公司","CU1407","200","50000.00","40000.00","-4","-800000.00"}
	};
		
		
	String[] strArr = new String[] { "天变", "衡变", "鲁缆", "沈变", "新变", "新缆", "德缆"};
	boolean[] boolArr = new boolean[]{
		true, true, true, true, true, true, true
	};

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.futures_table_page, container, false);
		update(v);
		Sheet sheet = (Sheet) aq.id(R.id.mysheet).getView();
		for (String[] rec : records) {
			sheet.AddRecord(rec);
		}

		sheet.fix();
		
		
		aq.id(R.id.company).clicked(new OnClickListener(){

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(FuturesTablePage.this.getActivity());
				builder.setTitle("公司");
				builder.setMultiChoiceItems(strArr, boolArr, new OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
						
					}});
				builder.setPositiveButton("确定", null);
				builder.setNegativeButton("取消", null);
				builder.create().show();
				
			}});
		
		return v;
	}
}
