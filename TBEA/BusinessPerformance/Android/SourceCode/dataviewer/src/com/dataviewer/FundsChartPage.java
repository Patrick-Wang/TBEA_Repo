package com.dataviewer;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.androidquery.AQuery;
import com.tbea.dataviewer.R;

public class FundsChartPage extends AQueryFragment implements
		OnCheckedChangeListener {

	public WebView receiveable_Ratio_WebView = null;
	public WebView daily_Payment_WebView = null;
	public WebView monthly_Payment_WebView = null;

	public Handler handler = new Handler();

	public ProgressDialog dialog = null;

	@Override
	public void onDestroy() {
		if (null != dialog) {
			dialog.dismiss();
		}
		if (null != receiveable_Ratio_WebView) {
			receiveable_Ratio_WebView.destroy();
		}
		if (null != daily_Payment_WebView) {
			daily_Payment_WebView.destroy();
		}
		if (null != monthly_Payment_WebView) {
			monthly_Payment_WebView.destroy();
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

		receiveable_Ratio_WebView = new WebView(getActivity());

		LayoutParams params = new LayoutParams(0, 0, 0, 0);
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.MATCH_PARENT;
		receiveable_Ratio_WebView.setLayoutParams(params);


		((RadioGroup) aq.id(R.id.rg_tab).getView())
				.setOnCheckedChangeListener(this);

		((LinearLayout) aq.id(R.id.receiveable_ratio_webview).getView())
				.addView(receiveable_Ratio_WebView);

		initView(receiveable_Ratio_WebView,
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

			refresh(R.id.receiveable_ratio_webview);
			break;
		case R.id.day_signed:
			getAQ().id(R.id.receivableratio).visibility(View.GONE);
			getAQ().id(R.id.daily_payment).visibility(View.VISIBLE);
			getAQ().id(R.id.monthly_payment).visibility(View.GONE);

			if (null == daily_Payment_WebView) {

				daily_Payment_WebView = new WebView(getActivity());
				LayoutParams params = new LayoutParams(0, 0, 0, 0);
				params.width = LayoutParams.MATCH_PARENT;
				params.height = LayoutParams.MATCH_PARENT;
				daily_Payment_WebView.setLayoutParams(params);

				((LinearLayout) getAQ().id(R.id.daily_payment_webview).getView())
						.addView(daily_Payment_WebView);

				initView(daily_Payment_WebView,
						"file:///android_asset/daily_payment.html",
						new WebViewClient() {
							@Override
							public void onPageFinished(WebView view, String url) {
								refresh(R.id.daily_payment_webview);
							}
						});
			} else {
				refresh(R.id.daily_payment_webview);
			}
			break;
		case R.id.month_sigend:
			getAQ().id(R.id.receivableratio).visibility(View.GONE);
			getAQ().id(R.id.daily_payment).visibility(View.GONE);
			getAQ().id(R.id.monthly_payment).visibility(View.VISIBLE);

			if (null == monthly_Payment_WebView) {

				monthly_Payment_WebView = new WebView(getActivity());
				LayoutParams params = new LayoutParams(0, 0, 0, 0);
				params.width = LayoutParams.MATCH_PARENT;
				params.height = LayoutParams.MATCH_PARENT;
				monthly_Payment_WebView.setLayoutParams(params);

				((LinearLayout) getAQ().id(R.id.monthly_payment_webview).getView())
						.addView(monthly_Payment_WebView);

				initView(monthly_Payment_WebView,
						"file:///android_asset/monthly_payment.html",
						new WebViewClient() {
							@Override
							public void onPageFinished(WebView view, String url) {
								refresh(R.id.monthly_payment_webview);
							}
						});
			} else {
				refresh(R.id.monthly_payment_webview);
			}
			break;
		default:
			dialog.hide();
			break;
		}
	}

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
					List<String> values = new ArrayList<String>();
					for (int i = 1; i <= 7; i++) {
						values.add(String.valueOf(i));
					}
					receiveable_Ratio_WebView.loadUrl("javascript:refreshView("
							+ values + ");");
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
					daily_Payment_WebView.loadUrl("javascript:refreshView("
							+ values + ");");
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
					monthly_Payment_WebView.loadUrl("javascript:refreshView("
							+ values + ");");
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
}
