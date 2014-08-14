package com.dataviewer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.androidquery.AQuery;
import com.tbea.dataviewer.R;
import com.common.StringUtil;
import com.dataviewer.sheetAdapter.FuturesTableAdapter;
import com.dataviewer.sheetAdapter.GreenCellAdapter;
import com.excel.Sheet;
import com.excel.TableSource;
import com.javaBean.QHMXBean;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class FuturesTablePage extends AQueryFragment {

	static String[][] nameMap = { //{ "企业编号", "qybh", "n" },
			{ "企业名称", "qymc", "n" }, { "类型", "type", "n" },
			{ "持仓量 (吨)", "ccl", "n" }, { "持仓均价 (元)", "ccjj", "y" },
			{ "现价 (元)", "price", "y" }, { "盈亏比例", "yk", "n" },
			{ "盈亏金额 (元)", "ykje", "y" } };
	private Sheet sheet = null;
	boolean[] companySel = null;
	String companyNames[] = null;
	public List<QHMXBean> qhmxBeans = new ArrayList<QHMXBean>();

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.futures_table_page, container, false);
	}
	
	public void setQHMXBeans(List<QHMXBean> qhmxBeans){
		this.qhmxBeans = qhmxBeans;
		int len = qhmxBeans.size();
		Set<String> nameSet = new HashSet<String>();
		for (int i = 0; i < len - 1; ++i) {
			nameSet.add(qhmxBeans.get(i).getQymc());
		}
		
		companyNames = new String[nameSet.size()];
		int index = 0;
		for(String name : nameSet){
			companyNames[index++] = name;
		}

		companySel = new boolean[companyNames.length];
		for (int i = 0; i < companyNames.length; ++i) {
			companySel[i] = true;
		}
	}

	@Override
	protected void onViewPrepared(AQuery aq, View fragView) {
		sheet = (Sheet) aq.id(R.id.mysheet).getView();
		sheet.lockColum(2);
		sheet.lockRow(1);
		sheet.setAdapter(new FuturesTableAdapter());

		aq.id(R.id.company).clicked(new OnClickListener() {


			@Override
			public void onClick(View v) {

				final boolean[] companybeforeSel = new boolean[companySel.length];
				for (int i = 0; i < companybeforeSel.length; ++i) {
					companybeforeSel[i] = companySel[i];
				}

				

				final AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				builder.setCancelable(false);
				builder.setTitle("公司");
				builder.setMultiChoiceItems(companyNames, companybeforeSel, new OnMultiChoiceClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {
						// TODO Auto-generated method stub
						
					}});

				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int which) {
								boolean bAllCancel = true;
								for (boolean item : companybeforeSel) {
									if (item) {
										bAllCancel = false;
										break;
									}
								}

								if (bAllCancel) {
									Toast.makeText(getActivity(), "请至少选择一个公司",
											Toast.LENGTH_SHORT).show();

								} else {

									boolean bChanged = false;
									for (int i = 0; i < companybeforeSel.length; ++i) {
										if (companybeforeSel[i] != companySel[i]) {
											bChanged = true;
											break;
										}
									}

									if (bChanged) {
										
										List<String> filters = new LinkedList<String>();
										for (int i = 0; i < companySel.length; ++i) {
											companySel[i] = companybeforeSel[i];
											if (!companySel[i]){
												filters.add(companyNames[i]);
											}
										}
										
										GreenCellAdapter adapter = (GreenCellAdapter) sheet.getAdapter();
										adapter.addFilter(0, filters);
										
										int sumIndex = 0;
										for (int i = 1, len = sheet.getSizeManager().getRowCount(); i < len; ++i){
											if (sheet.isVisiable(i)){
												sumIndex++;
												
												if (sumIndex % 2 == 0){
													sheet.setRowColor(i, Color.GRAY);
												} else{
													sheet.setRowColor(i, Color.LTGRAY);
												}
											}
										}
									}
								}
							}
						});
				builder.setNegativeButton("取消", null);
				builder.create().show();
			}
		});
	
		updateTable();
	}

	protected void updateTable() {
		sheet.clean();
		sheet.addTable(new TableSource() {

			@Override
			public int getRowCount() {
				return qhmxBeans.size() + 1;
			}

			@Override
			public int getColumCount() {
				return nameMap.length;
			}

			@Override
			public String getItem(int row, int colum) {
				if (0 == row) {
					return nameMap[colum][0];
				} else {
					String result = "";
					try {
						Method method = QHMXBean.class.getMethod("get"
								+ nameMap[colum][1].substring(0, 1)
										.toUpperCase()
								+ nameMap[colum][1].substring(1));
						result = (String) method.invoke(qhmxBeans.get(row - 1));
						
						if (result != null){
							if (nameMap[colum][2].equals("y")) {
								result = StringUtil.financeFormat(result);
							}
						}
						
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (result == null){
						return "--";
					}
					
					return result;
				}
			}
		});
		
		for (int i = 1, len = sheet.getSizeManager().getRowCount(); i < len; ++i){
			if (i % 2 == 0){
				sheet.setRowColor(i, Color.GRAY);
			} else{
				sheet.setRowColor(i, Color.LTGRAY);
			}
		}
	}
}
