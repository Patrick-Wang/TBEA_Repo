package com.dataviewer;

import com.example.dataviewer.R;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class FuturesChartPage extends AQueryFragment implements
		OnCheckedChangeListener {

	public WebView profit_Lost_Copper_WebView = null;

	public Handler handler = new Handler();

	public ProgressDialog dialog = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		dialog = ProgressDialog.show(getActivity(), null, "数据加载中，请稍后...");

		View v = inflater.inflate(R.layout.futures_chart_page, container,
				false);
		update(v);
		((RadioGroup) aq.id(R.id.rg_tab_ac).getView())
				.setOnCheckedChangeListener(this);

		aq.id(R.id.detailbtn).clicked(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				ft.hide(FuturesChartPage.this);
				ft.replace(R.id.host, new FuturesTablePage()).addToBackStack(
						null);
				ft.commit();
			}
		});

//		if (aq == null) {
			
			profit_Lost_Copper_WebView = (WebView) v
					.findViewById(R.id.profit_lost_webview);
			initView("Profit_Lost_Copper",
					"file:///android_asset/profit_lost_copper.html");
//		} else {
//			refresh();
//		}

		return v;
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

	public void initView(String jsInterfaceName, String url) {
		profit_Lost_Copper_WebView.getSettings().setJavaScriptEnabled(true);
		profit_Lost_Copper_WebView.getSettings().setAllowFileAccess(true);
		profit_Lost_Copper_WebView.getSettings().setNeedInitialFocus(false);
		profit_Lost_Copper_WebView
				.addJavascriptInterface(this, jsInterfaceName);
//		profit_Lost_Copper_WebView.setBackgroundColor(getResources().getColor(
//				android.R.color.transparent));
		profit_Lost_Copper_WebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				refresh();
			}
		});

		profit_Lost_Copper_WebView.loadUrl(url);
	}

	public void refresh() {
		handler.post(new Runnable() {
			@Override
			public void run() {
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO: handle exception
//				}
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
		dialog.dismiss();
	}

}
