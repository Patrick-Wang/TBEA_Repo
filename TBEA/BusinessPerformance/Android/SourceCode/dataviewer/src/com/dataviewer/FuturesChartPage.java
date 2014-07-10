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
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dataviewer.R;

public class FuturesChartPage extends AQueryFragment {

	public WebView webView = null;

	public Handler handler = new Handler();
	
	public ProgressDialog dialog = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.futures_chart_page, container, false);
		update(v);

		webView = (WebView) v.findViewById(R.id.profit_lost_webview);
		initView("file:///android_asset/profit_lost_copper.html");
		 refresh();

		return v;
	}

	public void initView(String url) {
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setAllowFileAccess(true);
		CookieManager.getInstance().setAcceptCookie(true);
		webView.getSettings().setNeedInitialFocus(false);
		webView.addJavascriptInterface(this, "SurveyUtil1");
//		webView.setWebViewClient(new WebViewClient());
		dialog = ProgressDialog.show(getActivity(),null,"页面加载中，请稍后..");
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				dialog.dismiss();
				// refresh();
			}
		});

//		refresh();
//		webView.setWebChromeClient(new WebChromeClient() {
//			@Override
//			public void onProgressChanged(WebView view, int newProgress) {
//				if (100 == newProgress) {
//					refresh();
//				}
//			}
//		});

		// webView.setWebChromeClient(new WebChromeClient());
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
