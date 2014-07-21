package com.dataviewer;

import com.androidquery.AQuery;
import com.tbea.dataviewer.R;
import com.dataviewer.sheetAdapter.GreenCellAdapter;
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
	static String[][] records = new String[][] {
			{ "公司", "类型", "持仓量", "持仓均价", "现价", "盈亏比例", "盈亏金额" },
			{ "沈变公司", "CU1407", "300", "5,000,000", "4,000,000", "-3",
					"-6,000,000" },
			{ "沈变公司", "CU1408", "200", "4,000,000", "4,000,000", "8", "30,000,000" },
			{ "沈变公司", "CU1409", "600", "4,000,000", "4,000,000", "8", "9,000,000" },
			{ "沈变公司", "CU1410", "300", "4,000,000", "4,000,000", "7", "10,000,000" },
			{ "衡变公司", "CU1408", "200", "4,000,000", "4,000,000", "5", "30,000,000" },
			{ "衡变公司", "CU1409", "400", "4,000,018", "4,000,000", "6", "10,000,000" },
			{ "衡变公司", "CU1410", "150", "5,000,033", "4,000,000", "-6",
					"-4,000,000" },
			{ "衡变公司", "CU1411", "500", "4,000,000", "4,000,000", "6", "1,000,000" },
			{ "衡变公司", "CU1412", "110", "5,000,004", "4,000,000", "-7",
					"-40,000,000" },
			{ "衡变公司", "CU1501", "500", "5,000,000", "4,000,000", "-6",
					"-1,000,000" },
			{ "鲁缆公司", "CU1407", "200", "5,000,000", "4,000,000", "-4",
					"-80,000,000" } };

	String[] strArr = new String[] { "天变", "衡变", "鲁缆", "沈变", "新变", "新缆", "德缆" };
	boolean[] boolArr = new boolean[] { true, true, true, true, true, true,
			true };

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.futures_table_page, container, false);
	}

	@Override
	protected void onViewPrepared(AQuery aq, View fragView) {
		Sheet sheet = (Sheet) aq.id(R.id.mysheet).getView();
		sheet.lockColum(2);
		sheet.lockRow(1);
		sheet.setAdapter(new GreenCellAdapter());
		sheet.addTableBlock(records);
		aq.id(R.id.company).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						FuturesTablePage.this.getActivity());
				builder.setTitle("公司");
				builder.setMultiChoiceItems(strArr, boolArr,
						new OnMultiChoiceClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1,
									boolean arg2) {

							}
						});
				builder.setPositiveButton("确定", null);
				builder.setNegativeButton("取消", null);
				builder.create().show();

			}
		});
	}
}
