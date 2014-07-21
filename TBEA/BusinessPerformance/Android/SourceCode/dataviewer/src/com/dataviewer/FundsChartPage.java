package com.dataviewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
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
import com.javaBean.UserBean;
import com.javaBean.YSZKBean;
import com.tbea.dataviewer.R;

public class FundsChartPage extends AQueryFragment implements
		OnCheckedChangeListener {

	private WebView webView = null;

	private Handler handler = new Handler();

	private ProgressDialog dialog = null;

	private UserBean userBean = new UserBean();

	private List<YSZKBean> yszkBeans = new ArrayList<YSZKBean>();

	private List<String> companyList = null;

	private static List<String> normalCompanyList = Arrays.asList("5", "6",
			"7", "8", "9", "10", "11");

	private JSONArray receivableRatioLegendArray = null;

	private JSONArray receivableRatioDataArray = null;

	private JSONArray dailyPaymentXAxisArray = null;

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
		}
		return webView;
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

		try {
			List<String> legends = new ArrayList<String>();
			List<JSONObject> receivableRatioDataObjects = new ArrayList<JSONObject>();
			String companyId = null;
			String companyName = null;
			double receiveable_ratio = 0.0D;
			double amount_receivable = 0.0D;
			double overdue_payment = 0.0D;
			for (YSZKBean yszkBean : yszkBeans) {
				companyId = yszkBean.getQybh();
				if (getCompanyList().contains(companyId)) {
					amount_receivable = Double.valueOf(yszkBean.getYsye());
					overdue_payment = Double.valueOf(yszkBean.getYqk());
					receiveable_ratio = ((amount_receivable - overdue_payment) / amount_receivable);
					if ("4" == companyId) {
						// TODO total
					} else if (normalCompanyList.contains(companyId)) {

						companyName = yszkBean.getQymc();
						legends.add(companyName);
						receivableRatioDataObjects.add(new JSONObject(
								"{value : " + receiveable_ratio + ",name : '"
										+ companyName + "'}"));
					} else {
						continue;
					}
				} else {
					continue;
				}
			}

			receivableRatioLegendArray = new JSONArray(legends);
			dailyPaymentXAxisArray = new JSONArray(legends);
			receivableRatioDataArray = new JSONArray(receivableRatioDataObjects);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getActivity(), "数据错误，请重试", Toast.LENGTH_LONG).show();
		}

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
						refresh(R.id.receiveable_ratio_webview);
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
					List<String> values = new ArrayList<String>();
					for (int i = 1; i <= 7; i++) {
						values.add(String.valueOf(i));
					}
					webView.loadUrl("javascript:refreshView(" + values + ");");
				}
			});

			break;
		case R.id.monthly_payment_webview:
			handler.post(new Runnable() {
				@Override
				public void run() {
					List<String> values = new ArrayList<String>();
					for (int i = 1; i <= 7; i++) {
						values.add(String.valueOf(i));
					}
					webView.loadUrl("javascript:refreshView(" + values + ");");
				}
			});

			break;
		default:
			dialog.hide();
			break;
		}
	}

	public void afterRefresh() {
		dialog.hide();
	}

	public UserBean getUserBean() {
		return userBean;
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

	public List<String> getCompanyList() {
		if (null != userBean) {
			String[] resultArray = userBean.getCompanyqx().split(",");
			companyList = Arrays.asList(resultArray);
		} else {
			companyList = new ArrayList<String>();
		}
		return companyList;
	}

	public void setCompanyList(List<String> companyList) {
		this.companyList = companyList;
	}

}
