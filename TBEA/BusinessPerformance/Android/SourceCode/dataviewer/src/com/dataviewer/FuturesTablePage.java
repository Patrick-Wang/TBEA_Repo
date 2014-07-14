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
		{"沈变公司","CU1407","350","50500.00","48690.00","-3.58","-633500.00"},
		{"沈变公司","CU1408","100","44420.00","48260.00","8.64","384000.00"},
		{"沈变公司","CU1409","500","47750.00","47930.00","0.38","90000.00"},
		{"沈变公司","CU1410","500","47430.00","47760.00","0.70","165000.00"},
		{"衡变公司","CU1408","125","45794.00","48260.00","5.38","308250.00"},
		{"衡变公司","CU1409","425","44955.18","47930.00","6.62","1264298.60"},
		{"衡变公司","CU1410","15","50853.33","47760.00","-6.08","-46399.95"},
		{"衡变公司","CU1411","5","44520.00","47610.00","6.94","15450.00"},
		{"衡变公司","CU1412","115","51233.04","47510.00","-7.27","-428149.95"},
		{"衡变公司","CU1501","5","50890.00","47440.00","-6.78","-17250.00"},
		{"鲁缆公司","CU1407","400","50761.00","48690.00","-4.08","-828400.00"}
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
