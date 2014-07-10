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
		{"zblx","zbmc","byjh","by","jhwcl","sy","jsyzzb","sjdjz","jsjdzzb","qnpj","jqnjzzzb","qntq","jqntqzzb","jdjh","jdj","jdhwcl","qntjd","jqntjdzzb","sjd","jsjdzzb","ndjh","ndj"},
		{"指示完成情况","利润总额","13580","-","0","-","0","3577.92","0","8276.26","0","9236.2","0","50070","7705.8","15.39%","26891.18","-71.34%","-","0.00%","142500","18439.50%"},
		{"指示完成情况","销售收入","317600","-","0","-","0","195424","0","177156","0","168057","0","1.04E+06","202547","19.56%","547993.72","-63.04%","-","0.00%","2.65E+08","788818"},
		{"指示完成情况","经营现金流","149286","-","0","195424","0","177156","0","168057","0","283000","-43777.5","-","22034.43","-","-","0","142500","-358228", "0", "0"},
		{"指示完成情况","不含税产值","230537","-","0","-","0","133666","0","142345","0","163089","0","740537","146241","19.75%","475792.73","-69.26%","-","0.00%","1.91E+06","547239"},
		{"指示完成情况","产量","0","-","0","-","0","0","0","0","0","0","0","0","0","0.00%","0","-","-","0.00%","15290","0"},
		{"指示完成情况","合同签约额","573549","-","0","-","0","204922","0","235800","0","314177","0","-","-","NULL","-","-","-","NULL","-","0"},
		{"指示完成情况","    其中：国内签约","-","-","0","-","0","-","0","-","0","-","0","-","-","0.00%","-","0","-","0.00%","0","0"},
		{"指示完成情况","    其中：国际签约","-","-","0","-","0","-","0","-","0","-","0","-","-","0.00%","-","0","-","0.00%","0","0"},
		{"指示完成情况","资金回笼","430598","-","0","-","0","149043","0","197095","0","264068","0","1.33E+06","171770","12.95%","640488.31","-73.18%","-","0.00%","3.10E+06","618898"},
		{"指示完成情况","应收账款","125499","-","0","-","0","54160","0","495465","0","390666","0","125499","-","0.00%","390665.84","0","594166","0.00%","0","0"},
		{"指示完成情况","存货","92899","-","0","-","0","275433","0","292367","0","292508","0","92899","-","0.00%","292507.99","0","268608","0.00%","0","0"}		};

	String[] strArr = new String[] { "沈变", "衡变", "新变", "天变", "鲁缆", "新缆", "德缆"};
	boolean[] boolArr = new boolean[]{
		true, true,true,true,true,true,true
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
