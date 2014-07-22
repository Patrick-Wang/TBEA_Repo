package com.dataviewer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.tbea.dataviewer.R;
import com.webservice.Company;
import com.webservice.Companys;
import com.webservice.Server;
import com.webservice.Server.OnFuturesResponseListener;
import com.common.StringUtil;
import com.dataviewer.sheetAdapter.GreenCellAdapter;
import com.excel.Sheet;
import com.excel.TableSource;
import com.javaBean.QHMXBean;
import com.javaBean.YDZBBean;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class FuturesTablePage extends AQueryFragment {

	static String[][] nameMap = { { "企业编号", "qybh", "n" },
			{ "企业名称", "qymc", "n" }, { "类型（铜、铝）", "type", "n" },
			{ "持仓量", "ccl", "n" }, { "持仓均价", "ccjj", "y" },
			{ "现价", "price", "y" }, { "盈亏比例", "yk", "n" },
			{ "盈亏金额", "ykje", "y" }, { "日期(年月)", "date", "n" } };
	private Sheet sheet = null;
	boolean[] companySel = new boolean[] { true, true, true, true, true, true,
			true };
	public List<QHMXBean> qhmxBeans = new ArrayList<QHMXBean>();

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.futures_table_page, container, false);
	}

	@Override
	protected void onViewPrepared(AQuery aq, View fragView) {
		sheet = (Sheet) aq.id(R.id.mysheet).getView();
		sheet.lockColum(2);
		sheet.lockRow(1);
		sheet.setAdapter(new GreenCellAdapter());

		aq.id(R.id.company).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final boolean[] companybeforeSel = new boolean[companySel.length];
				for (int i = 0; i < companybeforeSel.length; ++i) {
					companybeforeSel[i] = companySel[i];
				}

				String companyNames[] = new String[Companys.count()];
				for (int i = 0; i < companyNames.length; ++i) {
					companyNames[i] = Companys.getCompany(i).getName();
				}

				final AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				builder.setTitle("公司");
				builder.setMultiChoiceItems(companyNames, companybeforeSel,
						new OnMultiChoiceClickListener() {

							@Override
							public void onClick(DialogInterface arg0,
									int which, boolean isChecked) {
								boolean bAllCancel = true;
								for (boolean item : companySel) {
									if (item) {
										bAllCancel = false;
										break;
									}
								}

								if (bAllCancel) {
									Toast.makeText(getActivity(), "请至少选择一个公司",
											Toast.LENGTH_SHORT).show();
								}
							}
						});

				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int which) {

								boolean bAllCancel = true;
								for (boolean item : companySel) {
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
										for (int i = 0; i < companySel.length; ++i) {
											companySel[i] = companybeforeSel[i];
										}

										updateDataFromServer();
									}
								}
							}

						});
				builder.setNegativeButton("取消", null);
				builder.create().show();
			}
		});
	}

	private void updateDataFromServer() {

		List<Company> companys = new LinkedList<Company>();
		for (int i = 0; i < companySel.length; ++i) {
			if (companySel[i]) {
				companys.add(Companys.getCompany(i));
			}
		}

		String year = (String) getAQ().id(R.id.year).getText();
		year = year.substring(0, year.length() - 2);

		String month = (String) getAQ().id(R.id.month).getText();
		month = month.substring(0, month.length() - 2);

		final ProgressDialog dialog = ProgressDialog.show(getActivity(), null,
				"数据加载中，请稍侯...");
		Server server = Server.getInstance();
		server.getFutures(companys, new OnFuturesResponseListener() {

			@Override
			public void onFutures(List<QHMXBean> qhmxBeans, AjaxStatus status) {
				if (qhmxBeans != null) {
					FuturesTablePage.this.qhmxBeans = qhmxBeans;
					updateTable();
				}
				dialog.hide();
			}
		});
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
						Method method = YDZBBean.class.getMethod("get"
								+ nameMap[colum][1].substring(0, 1)
										.toUpperCase()
								+ nameMap[colum][1].substring(1));
						result = (String) method.invoke(qhmxBeans.get(row - 1));

						if (nameMap[colum][2].equals("y")) {
							result = StringUtil.financeFormat(result);
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

					return result;
				}
			}
		});
	}
}
