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

public class QuotaTablePage extends AQueryFragment implements OnClickListener {
	static String[][] records = new String[][]{

		{"指标名称","本月计划","本月完成","计划完成率","上月完成","较上月增长比","上季度净值","较上季度增长比","去年平均","较去年净值增长比","去年同期","较去年同期增长比","季度计划","季度累计","季度计划完成率","去年同季度","较去年同季度增长比","上季度","较上季度增长比","年度计划","年度累计", "年度计划完成率", "去年同期累计", "较去年同期累计增长比"},
		{"利润总额","13580","-","0","-","0","3577.92","0","8276.26","0","9236.2","0","50070","7705.8","15.39%","26891.18","-71.34%","-","0.00%","142500","18439.5", "-", "-", "-"},
		{"销售收入","317600","-","0","-","0","195424","0","177156","0","168057","0","1.04E+06","202547","19.56%","547993.72","-63.04%","-","0.00%","2.65E+08","788818", "-", "-", "-"},
		{"经营现金流","149286","-","0","195424","0","177156","0","168057","0","283000","-43777.5","-","22034.43","-","-","0","142500","-358228","142500","1400", "-", "-", "-"},
		{"不含税产值","230537","-","0","-","0","133666","0","142345","0","163089","0","740537","146241","19.75%","475792.73","-69.26%","-","0.00%","1.91E+06","547239", "-", "-", "-"},
		{"产量","0","-","0","-","0","0","0","0","0","0","0","0","0","0.00%","0","-","-","0.00%","15290","0", "-", "-", "-"},
		{"合同签约额","573549","-","0","-","0","204922","0","235800","0","314177","0","-","-","NULL","-","-","-","NULL","-","0", "-", "-", "-"},
		{"\t其中：国内签约","-","-","0","-","0","-","0","-","0","-","0","-","-","0.00%","-","0","-","0.00%","0","0", "-", "-", "-"},
		{"\t其中：国际签约","-","-","0","-","0","-","0","-","0","-","0","-","-","0.00%","-","0","-","0.00%","0","0", "-", "-", "-"},
		{"资金回笼","430598","-","0","-","0","149043","0","197095","0","264068","0","1.33E+06","171770","12.95%","640488.31","-73.18%","-","0.00%","3.10E+06","618898", "-", "-", "-"},
		{"应收账款","125499","-","0","-","0","54160","0","495465","0","390666","0","125499","-","0.00%","390665.84","0","594166","0.00%","0","0", "-", "-", "-"},
		{"存货","92899","-","0","-","0","275433","0","292367","0","292508","0","92899","-","0.00%","292507.99","0","268608","0.00%","0","0", "-", "-", "-"}};
	String[] strArr = new String[] { "天变", "衡变", "鲁缆", "沈变", "新变", "新缆", "德缆"};
	boolean[] boolArr = new boolean[]{
		true, true, true, true, true, true, true
	};
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.quota_table_page, container, false);
		update(v);
		Sheet sheet = (Sheet) aq.id(R.id.mysheet).getView();

		for (String[] rec : records) {
			sheet.AddRecord(rec);
		}

		sheet.fix();
		
		
		aq.id(R.id.company).clicked(this);
		aq.id(R.id.month).clicked(this);
		aq.id(R.id.year).clicked(this);

		return v;
	}

	@Override
	public void onClick(View v) {


		switch (v.getId()) {
		case R.id.company:
			onClickCompony();
			break;
		case R.id.month:
			onClickMonth();
			break;
		case R.id.year:
			onClickYear();
			break;
		}

	}

	private void onClickYear() {
		String[] stringYear = new String[] { "2012年", "2013年", "2014年" };
		AlertDialog.Builder builder = new AlertDialog.Builder(
				this.getActivity());
		builder.setTitle("年度");

		builder.setSingleChoiceItems(stringYear, 1,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

					}

				});

		builder.setPositiveButton("确定", null);
		builder.setNegativeButton("取消", null);
		builder.create().show();
	}

	private void onClickMonth() {

		String[] stringMonth = new String[] { "1月", "2月", "3月", "4月", "5月",
				"6月", "7月", "8月", "9月", "10月", "11月", "12月" };
		AlertDialog.Builder builder = new AlertDialog.Builder(
				this.getActivity());
		builder.setTitle("月份");

		builder.setSingleChoiceItems(stringMonth, 1,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

					}

				});

		builder.setPositiveButton("确定", null);
		builder.setNegativeButton("取消", null);
		builder.create().show();
	}

	private void onClickCompony() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				this.getActivity());
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

}
