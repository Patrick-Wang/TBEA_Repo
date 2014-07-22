package com.dataviewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.javaBean.QHMXBean;
import com.javaBean.UserBean;
import com.tbea.dataviewer.R;
import com.webservice.Companys;
import com.webservice.Server;
import com.webservice.Server.OnFuturesResponseListener;

public class FuturesChartPage extends AQueryFragment implements
        OnCheckedChangeListener {

    public WebView webView = null;

    public Handler handler = new Handler();

    public ProgressDialog dialog = null;

    private UserBean userBean = new UserBean();

    public List<QHMXBean> qhmxBeans_Copper = new ArrayList<QHMXBean>();

    public List<QHMXBean> qhmxBeans_Aluminium = new ArrayList<QHMXBean>();

    private JSONArray legends_Copper = null;

    private JSONArray xAxisArray_Copper = null;

    private double yAxisMin_Copper = 0.0D;

    private double yAxisMax_Copper = 0.0D;

    private JSONArray blankArray_Copper = null;

    private JSONArray dataArray_Copper = null;

    private static List<String> normalCompanyList = Arrays.asList("5", "6",
            "7", "8", "9", "10", "11");

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

    private void transformOfCopper(List<String> companyAuthorityList)
            throws JSONException {
        String tempCompanyId = null;
        String tempCompanyName = null;
        String tempDate = null;
        String profit_Lost_Amount = null;
        List<Double> tempValues = new ArrayList<Double>();
        Map<String, Double> tempMap = null;

        Set<String> companyNames = new TreeSet<String>();
        Set<String> dateSet = new TreeSet<String>();
        Map<String, String> valueMap = new HashMap<String, String>();

        for (QHMXBean qhmxBean_Copper : qhmxBeans_Copper) {
            tempCompanyId = qhmxBean_Copper.getQybh();
            if (companyAuthorityList.contains(tempCompanyId)) {
                if ("4" == tempCompanyId) {
                    // TODO total
                } else if (normalCompanyList.contains(tempCompanyId)) {
                    tempCompanyName = qhmxBean_Copper.getQymc();
                    companyNames.add(tempCompanyName);
                    tempDate = qhmxBean_Copper.getDate();
                    dateSet.add(tempDate);
                    profit_Lost_Amount = qhmxBean_Copper.getYkje();
                    tempValues.add(new Double(profit_Lost_Amount));
                    valueMap.put(tempCompanyName + tempDate, profit_Lost_Amount);
                } else {
                    // TODO 1000 id?
                    continue;
                }
            } else {
                continue;
            }
        }

        legends_Copper = new JSONArray(companyNames);
        xAxisArray_Copper = new JSONArray(dateSet);
        tempMap = sortData(tempValues);
        yAxisMin_Copper = tempMap.get("min");
        yAxisMax_Copper = tempMap.get("max");

        List<JSONObject> dataObjects_Copper = new ArrayList<JSONObject>();
        List<JSONObject> blankObjects_Copper = new ArrayList<JSONObject>();

        String tempValue = null;
        List<String> valueList = new ArrayList<String>();
        List<String> blankList = new ArrayList<String>();
        for (String companyName : companyNames) {
            valueList.clear();
            blankList.clear();
            for (String date : dateSet) {
                tempValue = valueMap.get(companyName + date);
                if (null != tempValue) {
                    valueList.add(tempValue);
                } else {
                    valueList.add("0");
                }
                blankList.add("0");
            }
            dataObjects_Copper
                    .add(new JSONObject(
                            "{name : '"
                                    + companyName
                                    + "', type : 'line', symbolSize: 3, itemStyle: {normal: {lineStyle: {width: 2}}},data : "
                                    + valueList + "}"));
            blankObjects_Copper
                    .add(new JSONObject(
                            "{name : '"
                                    + companyName
                                    + "', type : 'line', symbolSize: 0, itemStyle: {normal: {lineStyle: {width: 0}}},data : "
                                    + blankList + "}"));
        }

        dataArray_Copper = new JSONArray(dataObjects_Copper);
        blankArray_Copper = new JSONArray(blankObjects_Copper);
    }

    private void initData() {
        try {
            List<String> companyList = getCompanyList();
            transformOfCopper(companyList);
        } catch (JSONException e) {
            Toast.makeText(getActivity(), "数据错误，请重试", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onViewPrepared(AQuery aq, View fragView) {
        dialog = ProgressDialog.show(getActivity(), null, "数据加载中，请稍侯...");

        Server server = Server.getInstance();
        server.getFutures(Companys.getCompanys(),
                new OnFuturesResponseListener() {

                    @Override
                    public void onFutures(
                            List<QHMXBean> receivedQhmxBeans_Copper,
                            List<QHMXBean> receivedQhmxBeans_Aluminium,
                            AjaxStatus status) {
                        qhmxBeans_Copper = receivedQhmxBeans_Copper;

                        qhmxBeans_Aluminium = receivedQhmxBeans_Aluminium;

                        initData();
                    }
                });

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

        webView = new WebView(getActivity());
        LayoutParams params = new LayoutParams(0, 0, 0, 0);
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        webView.setLayoutParams(params);
        ((LinearLayout) aq.id(R.id.profit_lost_webview).getView())
                .addView(webView);

        initView("Profit_Lost_Copper",
        // "file:///android_asset/Copy_of_profit_lost_copper.html");
                "file:///android_asset/profit_lost_copper.html");
    }

    @Override
    public View onLoadView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
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
        if (null != webView) {
            detachView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }

    public void initView(String jsInterfaceName, String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setNeedInitialFocus(false);
        webView.addJavascriptInterface(this, jsInterfaceName);
        webView.setBackgroundColor(getResources().getColor(
                android.R.color.transparent));
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                refresh();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                    String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });

        webView.loadUrl(url);
    }

    public void refresh() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                List<String> values = new ArrayList<String>();
                for (int i = 1; i <= 7; i++) {
                    values.add(String.valueOf(i));
                }
                String url = "javascript:refreshAxis(" + legends_Copper + ","
                        + xAxisArray_Copper + "," + yAxisMin_Copper + ","
                        + yAxisMax_Copper + "," + blankArray_Copper + ");";
                webView.loadUrl(url);
            }
        });

    }

    public void refreshData(int id) {
        // switch (id) {
        // case 1:
        handler.post(new Runnable() {
            @Override
            public void run() {
                String url = "javascript:refreshData(" + dataArray_Copper
                        + ");";
                webView.loadUrl(url);
            }
        });
        // break;
        // case 2:
        // handler.post(new Runnable() {
        // @Override
        // public void run() {
        // webView.loadUrl("javascript:refreshData("
        // + monthlyPaymentDataArray + ","
        // + monthlyContractDataArray + ");");
        // }
        // });
        //
        // break;
        // default:
        // dialog.hide();
        // break;
        // }
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
