package com.dataviewer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.androidquery.AQuery;
import com.tbea.dataviewer.R;
import com.webservice.Server;
import com.common.StringUtil;
import com.dataviewer.sheetAdapter.GreenCellAdapter;
import com.excel.Sheet;
import com.excel.TableSource;
import com.javaBean.YSZKBean;

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

public class FundsTablePage extends AQueryFragment implements OnClickListener {

	static String[][] nameMap = {
			{ "企业名称", "qymc", "n" }, { "应收余额(万元)", "ysye", "y" },
			{ "逾期款(万元)", "yqk", "y" }, { "日回款(万元)", "rhk", "y" },
			{ "日签约(万元)", "rqy", "y" }, { "月回款(万元)", "yhk", "y" },
			{ "月签约 (万元)", "yqy", "y" } };

	Sheet sheet = null;
	boolean[] companySel = null;
	List<YSZKBean> yszkBeans = null;
	String[] companyNames = null;

	public void setData(List<YSZKBean> yszks) {
		yszkBeans = yszks;
		int len = yszks.size();
		Set<String> nameSet = new HashSet<String>();
		for (int i = 0; i < len - 1; ++i) {
			nameSet.add(yszks.get(i).getQymc());
		}

		companyNames = new String[nameSet.size()];
		int index = 0;
		for (String name : nameSet) {
			companyNames[index++] = name;
		}
		
		companySel = new boolean[companyNames.length];
		for (int i = 0; i < companyNames.length; ++i) {
			companySel[i] = true;
		}
	}

	protected void updateTable(final List<YSZKBean> beans) {
		sheet.clean();
		sheet.addTable(new TableSource() {

			@Override
			public int getRowCount() {
				return beans.size() + 1;
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
						Method method = YSZKBean.class.getMethod("get"
								+ nameMap[colum][1].substring(0, 1)
										.toUpperCase()
								+ nameMap[colum][1].substring(1));
						result = (String) method.invoke(beans.get(row - 1));

						if (null != result) {
							if (nameMap[colum][2].equals("y")) {
								result = StringUtil.financeFormat(result);
							}
						}

					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					if (result == null) {
						return "--";
					}
					return result;
				}

			}

		});

		updateTableRowColor();
	}

	protected void updateTableRowColor() {
		int sumIndex = 0;
		for (int i = 1, len = sheet.getSizeManager().getRowCount(); i < len; ++i) {
			if (sheet.isVisiable(i)) {
				sumIndex++;
				if (sumIndex % 2 == 0) {
					sheet.setRowColor(i, Color.GRAY);
				} else {
					sheet.setRowColor(i, Color.LTGRAY);
				}
			}
		}
	}

	@Override
	protected void onViewPrepared(AQuery aq, View fragView) {
		sheet = (Sheet) aq.id(R.id.mysheet).getView();
		sheet.setAdapter(new GreenCellAdapter());
		sheet.lockColum(1);
		sheet.lockRow(1);
		aq.id(R.id.company).clicked(this);
		aq.id(R.id.receivable_data_date).textColor(Color.RED).text("数据更新日期: " + Server.getServerDataUpdateTime());
		updateTable(yszkBeans);
	}

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.funds_chart_table, container, false);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.company:
			onClickCompany();
			break;
		}

	}

	private void onClickCompany() {

		final boolean[] companybeforeSel = new boolean[companySel.length];
		for (int i = 0; i < companybeforeSel.length; ++i) {
			companybeforeSel[i] = companySel[i];
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(
				this.getActivity());
		builder.setTitle("公司");
		builder.setCancelable(false);
		builder.setMultiChoiceItems(companyNames, companybeforeSel, new OnMultiChoiceClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which,
					boolean isChecked) {
				// TODO Auto-generated method stub
				
			}});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

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
							if (!companySel[i]) {
								filters.add(companyNames[i]);
							}
						}

						GreenCellAdapter adapter = (GreenCellAdapter) sheet
								.getAdapter();
						adapter.addFilter(0, filters);
						updateTableRowColor();
					}
				}

			}
		});
		builder.setNegativeButton("取消", null);
		builder.create().show();
	}
}
