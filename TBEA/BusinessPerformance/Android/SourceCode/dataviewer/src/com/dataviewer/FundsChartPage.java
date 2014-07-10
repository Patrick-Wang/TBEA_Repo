package com.dataviewer;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.dataviewer.R;

public class FundsChartPage extends AQueryFragment implements
		OnCheckedChangeListener {

	public WebView webView = null;

	public Handler handler = new Handler();

	public ProgressDialog dialog = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.funds_chart_page, container, false);
		update(v);
		((RadioGroup) aq.id(R.id.rg_tab).getView())
				.setOnCheckedChangeListener(this);

		webView = (WebView) v.findViewById(R.id.receiveable_ratio_webview);
		initView("file:///android_asset/receivable_ratio.html");
		// refresh();

		return v;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.receivable_money:
			aq.id(R.id.receivableratio).visibility(View.VISIBLE);
			aq.id(R.id.daily_payment).visibility(View.GONE);
			aq.id(R.id.monthly_payment).visibility(View.GONE);

			webView = (WebView) aq.id(R.id.receiveable_ratio_webview).getView();
			initView("file:///android_asset/receivable_ratio.html");
			// refresh();

			break;
		case R.id.day_signed:
			aq.id(R.id.receivableratio).visibility(View.GONE);
			aq.id(R.id.daily_payment).visibility(View.VISIBLE);
			aq.id(R.id.monthly_payment).visibility(View.GONE);

			webView = (WebView) aq.id(R.id.daily_payment_webview).getView();
			initView("file:///android_asset/daily_payment.html");
			// refresh();

			break;
		case R.id.month_sigend:
			aq.id(R.id.receivableratio).visibility(View.GONE);
			aq.id(R.id.daily_payment).visibility(View.GONE);
			aq.id(R.id.monthly_payment).visibility(View.VISIBLE);

			webView = (WebView) aq.id(R.id.monthly_payment_webview).getView();
			initView("file:///android_asset/monthly_payment.html");
			// refresh();

			break;
		default:
			break;
		}
	}

	public void initView(/* WebView webView, */String url) {
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setAllowFileAccess(true);
		CookieManager.getInstance().setAcceptCookie(true);
		webView.getSettings().setNeedInitialFocus(false);
		webView.addJavascriptInterface(this, "SurveyUtil");
		// webView.setWebViewClient(new WebViewClient() {
		// @Override
		// public void onPageFinished(WebView view, String url) {
		// List<String> values = new ArrayList<String>();
		// for (int i = 1; i <= 7; i++) {
		// values.add(String.valueOf(i));
		// }
		// webView.loadUrl("javascript:refreshView(" + values + ");");
		// }
		// });
		dialog = ProgressDialog.show(getActivity(),null,"页面加载中，请稍后..");
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				dialog.dismiss();
			}
		});
		// webView.setWebChromeClient(new WebChromeClientSelf(getActivity()));
		// webView.setBackgroundColor(getResources().getColor(
		// android.R.color.transparent));
		webView.loadUrl(url);
	}

	public void refresh() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				List<String> values = new ArrayList<String>();
				for (int i = 1; i <= 7; i++) {
					values.add(String.valueOf(i));
				}
				webView.loadUrl("javascript:refreshView(" + values + ");");
			}
		});
	}
}
