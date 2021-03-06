package com.dataviewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.common.StringUtil;
import com.javaBean.UserBean;
import com.javaBean.YSZKBean;
import com.tbea.dataviewer.R;
import com.webservice.Companys;
import com.webservice.Server;
import com.webservice.Server.OnFundsResponseListener;

public class FundsChartPage extends AQueryFragment implements
		OnCheckedChangeListener, Callback {

	private WebView webView = null;

	private Handler handler = new Handler(this);

	private ProgressDialog dialog = null;

	private UserBean userBean = new UserBean();

	private List<YSZKBean> yszkBeans = new ArrayList<YSZKBean>();

//	private static List<String> normalCompanyList = Arrays.asList("5", "6",
//			"7", "8", "9", "10", "11");

	private JSONArray receivableRatioLegendArray = null;

	private JSONArray receivableRatioDataArray = null;

	private JSONArray dailyPaymentXAxisArray = null;

	private JSONArray dailyPaymentDataArray = null;

	private JSONArray dailyContractDataArray = null;

	private double dailyPaymentYAxisMin = 0.0D;

	private double dailyPaymentYAxisMax = 0.0D;

	private JSONArray monthlyPaymentXAxisArray = null;

	private JSONArray monthlyPaymentDataArray = null;

	private JSONArray monthlyContractDataArray = null;

	private double monthlyPaymentYAxisMin = 0.0D;

	private double monthlyPaymentYAxisMax = 0.0D;

	private WebView provideWebView(int id) {
		WebView web = createWebView();
		LinearLayout parent = (LinearLayout) getAQ().id(id).getView();
		ViewParent currentParent = web.getParent();
		if (currentParent != null) {
			if (currentParent != parent) {
				((ViewGroup) currentParent).removeView(web);

			} else {
				return web;
			}
		}
		parent.addView(web);
		return web;
	}

	private WebView createWebView() {
		if (webView == null) {
			webView = new WebView(getActivity());
			LayoutParams params = new LayoutParams(0, 0, 0, 0);
			params.width = LayoutParams.MATCH_PARENT;
			params.height = LayoutParams.MATCH_PARENT;
			webView.setLayoutParams(params);
			//webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.); 
			//webView.getSettings().set
			//webView.getSettings().setBuiltInZoomControls(true);
			//webView.getSettings().setSupportZoom(false);
		}
		return webView;
	}

	private List<String> getCompanyList() {
		List<String> companyList = null;
		String[] resultArray = Server.getInstance().getUserBean()
				.getCompanyqx().split(",");
		companyList = Arrays.asList(resultArray);
		return companyList;
	}

	private Map<String, Double> sortData(List<Double> inputList) {
		Map<String, Double> resultMap = null;
		if (null != inputList && inputList.size() > 0) {
			Collections.sort(inputList);
			resultMap = new HashMap<String, Double>();
			resultMap.put("min", inputList.get(0));
			resultMap.put("max", inputList.get(inputList.size() - 1));
		}
		return resultMap;
	}

	private void initData() {
		try {
			List<String> companyNames = new ArrayList<String>();
			List<String> companyList = getCompanyList();
			List<JSONObject> receivableRatioDataObjects = new ArrayList<JSONObject>();
			String companyId = null;
			String companyName = null;

			List<Double> tempList = null;
			Map<String, Double> tempMap = null;

			// receiveable_ratio
			// double receiveable_ratio = 0.0D;
			String amount_receivable = null;
			String overdue_payment = null;

			// daily
			String dailyPaymentData = null;
			String dailyContractData = null;
			List<Double> dailyPaymentDatas = new ArrayList<Double>();
			List<Double> dailyContractDatas = new ArrayList<Double>();

			// monthly
			String monthlyPaymentData = null;
			String monthlyContractData = null;
			List<Double> monthlyPaymentDatas = new ArrayList<Double>();
			List<Double> monthlyContractDatas = new ArrayList<Double>();

			for (YSZKBean yszkBean : yszkBeans) {
				companyId = yszkBean.getQybh();
				if (companyList.contains(companyId)) {
					// receiveable_ratio
					amount_receivable = yszkBean.getYsye();
					overdue_payment = yszkBean.getYqk();
					// receiveable_ratio = ((amount_receivable -
					// overdue_payment) / amount_receivable);
					// daily
					dailyPaymentData = yszkBean.getRhk();
					dailyContractData = yszkBean.getRqy();
					// monthly
					monthlyPaymentData = yszkBean.getYhk();
					monthlyContractData = yszkBean.getYqy();
					if ("4".equals(companyId)) {

						getAQ().id(R.id.receiveable_ratio_webview_details_title)
								.getTextView().setText("总计:");

						getAQ().id(R.id.receiveable_ratio_webview_details1)
								.getTextView()
								.setText(
										"\t应收余额: "
												+ StringUtil
														.financeFormat(amount_receivable)
												+ " 万元");
						getAQ().id(R.id.receiveable_ratio_webview_details2)
								.getTextView()
								.setText(
										"\t逾期款: "
												+ StringUtil
														.financeFormat(overdue_payment)
												+ " 万元");
						getAQ().id(R.id.daily_payment_webview_details_title)
								.getTextView().setText("总计:");
						getAQ().id(R.id.daily_payment_webview_details1)
								.getTextView()
								.setText(
										"\t日回款: "
												+ StringUtil
														.financeFormat(dailyPaymentData)
												+ " 万元");
						getAQ().id(R.id.daily_payment_webview_details2)
								.getTextView()
								.setText(
										"\t日签约: "
												+ StringUtil
														.financeFormat(dailyContractData)
												+ " 万元");
						getAQ().id(R.id.monthly_payment_webview_details_title)
								.getTextView().setText("总计:");
						getAQ().id(R.id.monthly_payment_webview_details1)
								.getTextView()
								.setText(
										"\t月回款: "
												+ StringUtil
														.financeFormat(monthlyPaymentData)
												+ " 万元");
						getAQ().id(R.id.monthly_payment_webview_details2)
								.getTextView()
								.setText(
										"\t月签约: "
												+ StringUtil
														.financeFormat(monthlyContractData)
												+ " 万元");

					} else if (Companys.hasCompany(companyId)) {
						companyName = yszkBean.getQymc();
						companyNames.add(companyName);
						// receiveable_ratio
						String js = "{value : "
								+ overdue_payment
								+ ",name : '"
								+ companyName
								+ "',tooltip : {trigger : 'item', transitionDuration : 0, formatter: '"
								+ companyName + " (万元)<br/>应收余额: "
								+ StringUtil.financeFormat(amount_receivable)
								+ "<br/>逾期款: "
								+ StringUtil.financeFormat(overdue_payment)
								+ "'}}";
						JSONObject receiveable_ratio_JSONObject = new JSONObject(
								js);
						receivableRatioDataObjects
								.add(receiveable_ratio_JSONObject);
						// daily
						dailyPaymentDatas.add(Double.valueOf(dailyPaymentData));
						dailyContractDatas.add(Double
								.valueOf(dailyContractData));

						// monthly
						monthlyPaymentDatas.add(Double
								.valueOf(monthlyPaymentData));
						monthlyContractDatas.add(Double
								.valueOf(monthlyContractData));
					} else {
						continue;
					}
				} else {
					continue;
				}
			}

			// receiveable_ratio
			receivableRatioLegendArray = new JSONArray(companyNames);
			receivableRatioDataArray = new JSONArray(receivableRatioDataObjects);

			// daily
			dailyPaymentXAxisArray = new JSONArray(companyNames);
			dailyPaymentDataArray = new JSONArray(dailyPaymentDatas);
			dailyContractDataArray = new JSONArray(dailyContractDatas);
			tempList = new ArrayList<Double>();
			tempList.addAll(dailyPaymentDatas);
			tempList.addAll(dailyContractDatas);
			tempMap = sortData(tempList);
			dailyPaymentYAxisMin = tempMap.get("min");
			dailyPaymentYAxisMax = tempMap.get("max");

			// monthly
			monthlyPaymentXAxisArray = new JSONArray(companyNames);
			monthlyPaymentDataArray = new JSONArray(monthlyPaymentDatas);
			monthlyContractDataArray = new JSONArray(monthlyContractDatas);
			tempList.clear();
			tempList.addAll(monthlyPaymentDatas);
			tempList.addAll(monthlyContractDatas);
			tempMap.clear();
			tempMap = sortData(tempList);
			monthlyPaymentYAxisMin = tempMap.get("min");
			monthlyPaymentYAxisMax = tempMap.get("max");

		} catch (JSONException e) {
			dialog.hide();
			Toast.makeText(getActivity(), "数据错误，请重试", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onDestroy() {
		if (null != dialog) {
			dialog.dismiss();
			dialog = null;
		}
		if (null != webView) {
			detachView(webView);
			webView.destroy();
			webView = null;
		}
		super.onDestroy();
	}

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.funds_chart_page, container, false);
	}

	@Override
	protected void onViewPrepared(AQuery aq, View fragView) {
		dialog = ProgressDialog.show(getActivity(), null, "数据加载中，请稍后...");
			((RadioGroup) aq.id(R.id.rg_tab).getView())
				.setOnCheckedChangeListener(this);
		initView(provideWebView(R.id.receiveable_ratio_webview),
				"file:///android_asset/receivable_ratio.html",
				new WebViewClient() {
					@Override
					public void onPageFinished(WebView view, String url) {
						Server server = Server.getInstance();
						server.getFunds(new OnFundsResponseListener() {

							@Override
							public void onFunds(
									List<YSZKBean> receivedYszkBeans,
									AjaxStatus status) {
								// TODO Auto-generated method stub
								yszkBeans = receivedYszkBeans;
								initData();
								refresh(R.id.receiveable_ratio_webview);
								OnClickListener listener = new OnClickListener(){
									@Override
									public void onClick(View v) {
										FragmentTransaction ft = getActivity().getFragmentManager()
												.beginTransaction();
										FundsTablePage fundsTablePage = new FundsTablePage();
										fundsTablePage.setData(yszkBeans);
										//ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN); 
										ft.replace(R.id.host, fundsTablePage).addToBackStack(null);
										ft.commit();
									}
								};
								
								getAQ().id(R.id.detail_day).visibility(View.VISIBLE);
								getAQ().id(R.id.detail_match).visibility(View.VISIBLE);
								getAQ().id(R.id.detail_receivable).visibility(View.VISIBLE);
								getAQ().id(R.id.receivable_data_date).textColor(Color.RED).text("数据更新日期: " + Server.getServerDataUpdateTime());
								getAQ().id(R.id.detailbtn_receivable).clicked(listener);
								getAQ().id(R.id.detailbtn_day).clicked(listener);
								getAQ().id(R.id.detailbtn_match).clicked(listener);
							}
						});
					}
				});
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (null != dialog) {
			dialog.show();
		}
		switch (checkedId) {
		case R.id.receivable_money:
			getAQ().id(R.id.receivableratio).visibility(View.VISIBLE);
			getAQ().id(R.id.daily_payment).visibility(View.GONE);
			getAQ().id(R.id.monthly_payment).visibility(View.GONE);

			initView(provideWebView(R.id.receiveable_ratio_webview),
					"file:///android_asset/receivable_ratio.html",
					new WebViewClient() {
						@Override
						public void onPageFinished(WebView view, String url) {
							refresh(R.id.receiveable_ratio_webview);
						}
					});
			break;
		case R.id.day_signed:
			getAQ().id(R.id.receivableratio).visibility(View.GONE);
			getAQ().id(R.id.daily_payment).visibility(View.VISIBLE);
			getAQ().id(R.id.monthly_payment).visibility(View.GONE);

			initView(provideWebView(R.id.daily_payment_webview),
					"file:///android_asset/daily_payment.html",
					new WebViewClient() {
						@Override
						public void onPageFinished(WebView view, String url) {
							refresh(R.id.daily_payment_webview);
						}
					});
			break;
		case R.id.month_sigend:
			getAQ().id(R.id.receivableratio).visibility(View.GONE);
			getAQ().id(R.id.daily_payment).visibility(View.GONE);
			getAQ().id(R.id.monthly_payment).visibility(View.VISIBLE);

			initView(provideWebView(R.id.monthly_payment_webview),
					"file:///android_asset/monthly_payment.html",
					new WebViewClient() {
						@Override
						public void onPageFinished(WebView view, String url) {
							refresh(R.id.monthly_payment_webview);
						}
					});
			break;
		default:
			dialog.hide();
			break;
		}
	}

	@SuppressLint("SetJavaScriptEnabled")
	public void initView(WebView webView, String url,
			WebViewClient webViewClient) {
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setAllowFileAccess(true);
		webView.getSettings().setNeedInitialFocus(false);
		webView.addJavascriptInterface(this, "FundsChartPage");
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		webView.setBackgroundColor(getResources().getColor(
				android.R.color.transparent));
		webView.setWebViewClient(webViewClient);
		webView.loadUrl(url);
	}

	public void refresh(int id) {
		switch (id) {
		case R.id.receiveable_ratio_webview:
			handler.post(new Runnable() {
				@Override
				public void run() {
					webView.loadUrl("javascript:refreshView("
							+ receivableRatioLegendArray + ","
							+ receivableRatioDataArray + ");");
				}
			});
			break;
		case R.id.daily_payment_webview:
			handler.post(new Runnable() {
				@Override
				public void run() {
					webView.loadUrl("javascript:refreshAxis("
							+ dailyPaymentXAxisArray + ","
							+ dailyPaymentYAxisMin + "," + dailyPaymentYAxisMax
							+ ");");
				}
			});

			break;
		case R.id.monthly_payment_webview:
			handler.post(new Runnable() {
				@Override
				public void run() {
					webView.loadUrl("javascript:refreshAxis("
							+ monthlyPaymentXAxisArray + ","
							+ monthlyPaymentYAxisMin + ","
							+ monthlyPaymentYAxisMax + ");");
				}
			});

			break;
		default:
			dialog.hide();
			break;
		}
	}

	public void refreshData(int id) {
		switch (id) {
		case 1:
			handler.post(new Runnable() {
				@Override
				public void run() {
					String url = "javascript:refreshData("
							+ dailyPaymentDataArray + ","
							+ dailyContractDataArray + ");";
					webView.loadUrl(url);
				}
			});
			break;
		case 2:
			handler.post(new Runnable() {
				@Override
				public void run() {
					String url = "javascript:refreshData("
							+ monthlyPaymentDataArray + ","
							+ monthlyContractDataArray + ");";
					webView.loadUrl(url);
				}
			});

			break;
		default:
			dialog.hide();
			break;
		}
	}

	public void afterRefresh() {
		handler.sendEmptyMessage(1000);

	}

	public UserBean getUserBean() {
		return userBean;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public List<YSZKBean> getYszkBeans() {
		return yszkBeans;
	}

	public void setYszkBeans(List<YSZKBean> yszkBeans) {
		this.yszkBeans = yszkBeans;
	}

	@Override
	public boolean handleMessage(Message arg0) {
		if (arg0.what == 1000) {
			dialog.hide();
		}
		return false;
	}

}
