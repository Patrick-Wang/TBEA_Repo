package com.dataviewer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.tbea.dataviewer.R;
import com.webservice.Company;
import com.webservice.Companys;
import com.webservice.Server;
import com.webservice.Server.OnMonthQuotaResponseListener;
import com.common.StringUtil;
import com.dataviewer.sheetAdapter.QuotaTableAdapter;
import com.excel.Sheet;
import com.excel.TableSource;
import com.javaBean.YDZBBean;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class QuotaTablePage extends AQueryFragment implements OnClickListener {

	static String[][] nameMap = { { "序号", "xh", "n" }, { "指标名称", "zbmc", "n" },
			{ "本月计划 (万元)", "byjh", "y" }, { "本月完成 (万元)", "bywc", "y" },
			{ "计划完成率", "jhwcl", "n" }, { "上月完成 (万元)", "sywc", "y" },
			{ "较上月增长比", "jsyzzb", "n" }, { "去年同期 (万元)", "qntq", "y" },
			{ "较去年同期增长比", "jqntqzzb", "n" }, { "季度计划 (万元)", "jdjh", "y" },
			{ "季度累计 (万元)", "jdlj", "y" }, { "季度计划完成率", "jdjhwcl", "n" },
			{ "年度计划 (万元)", "ndjh", "y" }, { "年度累计 (万元)", "ndlj", "y" },
			{ "年度计划完成率", "ndjhwcl", "n" }, { "去年同期累计 (万元)", "qntqlj", "y" },
			{ "较去年同期累计增长比", "jqntqljzzb", "n" } };
	Sheet sheet = null;
	boolean[] companySel = null;
	List<Company> companys = null;

	private void updateDataFromServer() {

		List<Company> companyList = new LinkedList<Company>();
		for (int i = 0; i < companySel.length; ++i) {
			if (companySel[i]) {
				companyList.add(companys.get(i));
			}
		}

		String year = (String) getAQ().id(R.id.year).getText();
		year = year.substring(0, year.length() - 2);

		String month = (String) getAQ().id(R.id.month).getText();
		month = month.substring(0, month.length() - 2);

		final ProgressDialog dialog = ProgressDialog.show(getActivity(), null,
				"数据加载中，请稍侯...");
		Server.getInstance().getMonthQuota(companyList, year, month,
				new OnMonthQuotaResponseListener() {

					@Override
					public void onMonthQuota(List<YDZBBean> ydzbBeans,
							AjaxStatus status) {
						if (ydzbBeans != null) {
							updateTable(ydzbBeans);
						}
						dialog.hide();
					}
				});

	}

	protected void updateTable(final List<YDZBBean> ydzbBeans) {
		sheet.clean();
		sheet.addTable(new TableSource() {

			@Override
			public int getRowCount() {
				return ydzbBeans.size() + 1;
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
						result = (String) method.invoke(ydzbBeans.get(row - 1));

						if (null != result) {
							if (nameMap[colum][2].equals("y")) {
								result = StringUtil.financeFormat(result);
							}

							if (1 == colum) {
								if (result.contains("其中:国内签约")) {
									result = "合同签约额 (国内)";
								} else if (result.contains("其中:国际签约")) {
									result = "合同签约额 (国际)";
								}
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

		for (int i = 1, len = sheet.getSizeManager().getRowCount(); i < len; ++i) {
			if (i % 2 == 0) {
				sheet.setRowColor(i, Color.GRAY);
			} else {
				sheet.setRowColor(i, Color.LTGRAY);
			}
		}
	}

	@Override
	protected void onViewPrepared(AQuery aq, View fragView) {
		getAQ().id(R.id.month)
				.getButton()
				.setText(
						((Calendar.getInstance().get(Calendar.MONTH) + 1))
								+ " 月");
		getAQ().id(R.id.year).getButton()
				.setText((Calendar.getInstance().get(Calendar.YEAR)) + " 年");
		sheet = (Sheet) aq.id(R.id.mysheet).getView();
		sheet.setAdapter(new QuotaTableAdapter());
		sheet.lockColum(2);
		sheet.lockRow(1);
		aq.id(R.id.company).clicked(this);
		aq.id(R.id.month).clicked(this);
		aq.id(R.id.year).clicked(this);

		companys = Companys.getCompanys(Server.getInstance().getUserBean()
				.getCompanyqx());

		companySel = new boolean[companys.size()];
		for (int i = 0; i < companySel.length; ++i) {
			companySel[i] = true;
		}
		updateDataFromServer();
	}

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.quota_table_page, container, false);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.company:
			onClickCompany();
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

		final String yearBeforeSel = (String) getAQ().id(R.id.year).getText();

		int selItem = 0;
		final String[] stringYear = new String[] { "2012 年", "2013 年", "2014 年" };
		for (String item : stringYear) {
			if (item.equals(yearBeforeSel)) {
				break;
			}
			++selItem;
		}

		final int currentItem = selItem;
		AlertDialog.Builder builder = new AlertDialog.Builder(
				this.getActivity());
		builder.setTitle("年度");
		final int[] selItems = new int[] { currentItem };
		builder.setSingleChoiceItems(stringYear, currentItem,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						selItems[0] = which;

					}
				});

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (currentItem != selItems[0]) {
					getAQ().id(R.id.year).getTextView()
							.setText(stringYear[selItems[0]]);
					updateDataFromServer();
				}
			}
		});

		builder.setNegativeButton("取消", null);
		builder.create().show();

	}

	private void onClickMonth() {

		final String monthBeforeSel = (String) getAQ().id(R.id.month).getText();

		int selItem = 0;
		final String[] stringMonth = new String[] { "1 月", "2 月", "3 月", "4 月",
				"5 月", "6 月", "7 月", "8 月", "9 月", "10 月", "11 月", "12 月" };
		for (String item : stringMonth) {
			if (item.equals(monthBeforeSel)) {
				break;
			}
			++selItem;
		}

		final int currentItem = selItem;
		AlertDialog.Builder builder = new AlertDialog.Builder(
				this.getActivity());
		builder.setTitle("月份");
		final int[] selItems = new int[] { currentItem };
		builder.setSingleChoiceItems(stringMonth, currentItem,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						selItems[0] = which;

					}
				});

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (currentItem != selItems[0]) {
					getAQ().id(R.id.month).getTextView()
							.setText(stringMonth[selItems[0]]);
					updateDataFromServer();
				}
			}
		});

		builder.setNegativeButton("取消", null);
		builder.create().show();
	}

	private void onClickCompany() {

		final boolean[] companybeforeSel = new boolean[companySel.length];
		for (int i = 0; i < companybeforeSel.length; ++i) {
			companybeforeSel[i] = companySel[i];
		}

		String companyNames[] = new String[Companys.count()];

		for (int i = 0; i < companyNames.length; ++i) {
			companyNames[i] = Companys.getCompany(i).getName();
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(
				this.getActivity());
		builder.setTitle("公司");
		builder.setMultiChoiceItems(companyNames, companybeforeSel,
				new OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int which,
							boolean isChecked) {
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
						}
					}
				});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

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
}
