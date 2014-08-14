package com.dataviewer;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.AbsoluteLayout.LayoutParams;

import com.androidquery.AQuery;
import com.common.CommonUtil;
import com.tbea.dataviewer.R;
import com.webservice.Server;

public class HelpPage extends AQueryFragment {


	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.help_page, null);
	}

	@Override
	protected void onViewPrepared(AQuery aq, View fragView) {
				
		WebView webView = new WebView(getActivity());
		LayoutParams params = new LayoutParams(0, 0, 0, 0);
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.MATCH_PARENT;
		webView.setLayoutParams(params);
		((LinearLayout)aq.id(R.id.help_root_view).getView()).addView(webView);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setAllowFileAccess(true);
		webView.getSettings().setNeedInitialFocus(false);
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//		webView.setBackgroundColor(getResources().getColor(
//		android.R.color.transparent));
		webView.setWebViewClient(new WebViewClient(){});
		webView.loadUrl(Server.getInstance().getConfigurations().getHome() + "/mobile/Guide.html");
	}

}
