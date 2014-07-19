package com.dataviewer;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.androidquery.AQuery;
import com.tbea.dataviewer.R;

public class FuturesChartPage extends AQueryFragment implements
		OnCheckedChangeListener {

	public WebView profit_Lost_Copper_WebView = null;

	public Handler handler = new Handler();

	public ProgressDialog dialog = null;

	@Override
	protected void onViewPrepared(AQuery aq, View fragView) {
		dialog = ProgressDialog.show(getActivity(), null, "数据加载中，请稍侯...");

		((RadioGroup) aq.id(R.id.rg_tab_ac).getView())
				.setOnCheckedChangeListener(this);

		aq.id(R.id.detailbtn).clicked(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.host, new FuturesTablePage()).addToBackStack(
						null);
				ft.commit();
			}
		});

	
		profit_Lost_Copper_WebView = new WebView(getActivity());
		LayoutParams params = new LayoutParams(0, 0, 0, 0);
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.MATCH_PARENT;
		profit_Lost_Copper_WebView.setLayoutParams(params);
		((LinearLayout)aq.id(R.id.profit_lost_webview).getView()).addView(profit_Lost_Copper_WebView);
		
		initView("Profit_Lost_Copper",
				"file:///android_asset/Copy_of_receivable_ratio.html");
	}

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater
				.inflate(R.layout.futures_chart_page, container, false);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.cu:
			break;
		case R.id.al:
			break;
		default:
			break;
		}
	}

	@Override
	public void onDestroy() {
		if (null != dialog) {
			dialog.dismiss();
			dialog = null;
		}
		if (null != profit_Lost_Copper_WebView) {
			detachView(profit_Lost_Copper_WebView);
			profit_Lost_Copper_WebView.destroy();
			profit_Lost_Copper_WebView = null;
		}
		super.onDestroy();
	}
	
	public void initView(String jsInterfaceName, String url) {
		profit_Lost_Copper_WebView.getSettings().setJavaScriptEnabled(true);
		profit_Lost_Copper_WebView.getSettings().setAllowFileAccess(true);
		profit_Lost_Copper_WebView.getSettings().setNeedInitialFocus(false);
		profit_Lost_Copper_WebView
				.addJavascriptInterface(this, jsInterfaceName);
		profit_Lost_Copper_WebView.setBackgroundColor(getResources().getColor(
				android.R.color.transparent));
		profit_Lost_Copper_WebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				refresh();
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// TODO Auto-generated method stub
				super.onReceivedError(view, errorCode, description, failingUrl);
			}
			
			
		});

		profit_Lost_Copper_WebView.loadUrl(url);
	}

	public void refresh() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				List<String> values = new ArrayList<String>();
				for (int i = 1; i <= 7; i++) {
					values.add(String.valueOf(i));
				}
				profit_Lost_Copper_WebView.loadUrl("javascript:refreshView("
						+ values + ");");
			}
		});

	}

	public void afterRefresh() {
		dialog.hide();
	}

}
