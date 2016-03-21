package com.tbea.ic.scanner.page;

import android.annotation.SuppressLint;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.page.core.Page;
import com.squareup.otto.Subscribe;
import com.tbea.ic.scaner.R;
import com.tbea.ic.scanner.page.camera.CameraPage;
import com.tbea.ic.scanner.page.camera.ScanEvent;

public class WebPage extends Page {

	private static final String BASE_URL = "file:///android_asset/";

	public class Scanner {

		@JavascriptInterface
		public void scan(String id) {
			if (null == scanId) {
				scanId = id;
				Page page = new CameraPage();
				navigateTo(page);
			} else {
				delayNotifyScan(id);
			}
		}

		void delayNotifyScan(final String id) {
			delayed(new Runnable() {
				public void run() {
					notifyScaned(id, null);
				}
			});
		}

	}

	Scanner scanner = new Scanner();
	String scanId;

	@Override
	protected int onGetLayoutId() {
		return R.layout.web;
	}

	@SuppressLint("SetJavaScriptEnabled")
	public void initView(WebView webView, String url,
			WebViewClient webViewClient) {

		// myWebView.getSettings().setAllowFileAccess(true);
		// //如果访问的页面中有Javascript，则webview必须设置支持Javascript
		// myWebView.getSettings().setJavaScriptEnabled(true);
		// myWebView.getSettings().setUserAgentString(MyApplication.getUserAgent());
		// myWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		// myWebView.getSettings().setAllowFileAccess(true);
		// myWebView.getSettings().setAppCacheEnabled(true);
		// myWebView.getSettings().setDomStorageEnabled(true);
		// myWebView.getSettings().setDatabaseEnabled(true);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webView.getSettings().setNeedInitialFocus(true);
		webView.getSettings().setAllowFileAccess(true);
		webView.getSettings().setAppCacheEnabled(true);
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setDatabaseEnabled(true);
		webView.addJavascriptInterface(scanner, "Scanner");
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		webView.setBackgroundColor(getResources().getColor(
				android.R.color.transparent));
		webView.setWebViewClient(webViewClient);
		webView.loadUrl(url);
	}

	void notifyScaned(String id, String code) {
		String js = null;
		if (code != null) {
			js = "javascript: camera.onScanned('" + id + "','" + code
					+ "')";
		} else {
			js = "javascript: camera.onScanned('" + id + "')";
		}
		query().id(R.id.webHost).getWebView().loadUrl(js);
		
	}

	@Subscribe
	public void onScanFinished(ScanEvent event) {
		if (scanId != null){
			if (event.getResult() != null) {
				notifyScaned(scanId, event.getResult().getText());
			} else {
				notifyScaned(scanId, null);
			}
			scanId = null;
		}
	}

	@Override
	protected void onInitialize() {
		initView(query().id(R.id.webHost).getWebView(), BASE_URL + "test.html",
				new WebViewClient() {
					@Override
					public void onPageFinished(WebView view, String url) {

					}
				});
	}
}
