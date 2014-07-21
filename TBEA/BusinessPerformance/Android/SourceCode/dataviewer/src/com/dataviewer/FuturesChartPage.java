package com.dataviewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.Toast;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.androidquery.AQuery;
import com.javaBean.QHMXBean;
import com.javaBean.UserBean;
import com.javaBean.YSZKBean;
import com.tbea.dataviewer.R;

public class FuturesChartPage extends AQueryFragment implements
        OnCheckedChangeListener {

    public WebView profit_Lost_Copper_WebView = null;

    public Handler handler = new Handler();

    public ProgressDialog dialog = null;

    private UserBean userBean = new UserBean();

    public List<QHMXBean> qhmxBeans_Copper = new ArrayList<QHMXBean>();

    public List<QHMXBean> qhmxBeans_Aluminium = new ArrayList<QHMXBean>();

    private static List<String> normalCompanyList = Arrays.asList("5", "6",
            "7", "8", "9", "10", "11");

    private List getCompanyList() {
        List<String> companyList = null;
        if (null != userBean) {
            String[] resultArray = userBean.getCompanyqx().split(",");
            companyList = Arrays.asList(resultArray);
        } else {
            companyList = new ArrayList<String>();
        }
        return companyList;
    }

//    private void initData() {
//        try {
//            List<String> companyNames = new ArrayList<String>();
//            List<String> companyList = getCompanyList();
//            String companyId = null;
//            String companyName = null;
//
//            List<String> tempList = null;
//            Map<String, Double> tempMap = null;
//
//            for (QHMXBean qhmxBean : qhmxBeans) {
//                companyId = qhmxBean.getQybh();
//                if (companyList.contains(companyId)) {
//                    if ("4" == companyId) {
//                        // TODO total
//                    } else if (normalCompanyList.contains(companyId)) {
//                        companyName = qhmxBean.getQymc();
//                        companyNames.add(companyName);
//                    } else {
//                        continue;
//                    }
//                } else {
//                    continue;
//                }
//            }
//
//        } catch (/* JSON */Exception e) {
//            Toast.makeText(getActivity(), "数据错误，请重试", Toast.LENGTH_LONG).show();
//        }
//    }

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
        ((LinearLayout) aq.id(R.id.profit_lost_webview).getView())
                .addView(profit_Lost_Copper_WebView);

        initView("Profit_Lost_Copper",
                "file:///android_asset/Copy_of_profit_lost_copper.html");
        // "file:///android_asset/profit_lost_copper.html");
    }

    @Override
    public View onLoadView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
//        initData();
        return inflater.inflate(R.layout.futures_chart_page, container, false);
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

    public void refresh2(int id) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                List<String> values = new ArrayList<String>();
                for (int i = 1; i <= 7; i++) {
                    values.add(String.valueOf(i));
                }
                profit_Lost_Copper_WebView.loadUrl("javascript:refreshView2("
                        + values + ");");
            }
        });

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

    public List<QHMXBean> getQhmxBeans_Copper() {
        return qhmxBeans_Copper;
    }

    public void setQhmxBeans_Copper(List<QHMXBean> qhmxBeans_Copper) {
        this.qhmxBeans_Copper = qhmxBeans_Copper;
    }

    public List<QHMXBean> getQhmxBeans_Aluminium() {
        return qhmxBeans_Aluminium;
    }

    public void setQhmxBeans_Aluminium(List<QHMXBean> qhmxBeans_Aluminium) {
        this.qhmxBeans_Aluminium = qhmxBeans_Aluminium;
    }

}
