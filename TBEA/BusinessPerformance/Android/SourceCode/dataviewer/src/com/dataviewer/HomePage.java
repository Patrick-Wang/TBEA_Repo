package com.dataviewer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.common.JsonUtil;
import com.javaBean.QHMXBean;
import com.javaBean.UserBean;
import com.javaBean.YDZBBean;
import com.javaBean.YSZKBean;
import com.tbea.dataviewer.R;

public class HomePage extends AQueryFragment {

	private int functionId = 0;

	private static String year = String.valueOf(Calendar.getInstance().get(
			Calendar.YEAR));

	private static String month = String.valueOf(Calendar.getInstance().get(
			Calendar.MONTH));

	private static String outerUrl = "http://218.84.134.160:8081/mobile/dataTransfer";

	private static String innerUrl = "http://192.168.7.22/mobile/dataTransfer";

	public UserBean userBean = new UserBean();

	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.home_page, null);
	}

	@Override
	protected void onViewPrepared(AQuery aq, View fragView) {

		if (userBean.getMenuqx().contains("1")) {
			aq.id(R.id.f1).clicked(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					functionId = R.id.f1;
					fetchData("1", outerUrl);

					// FragmentTransaction ft =
					// getActivity().getFragmentManager()
					// .beginTransaction();
					// ft.replace(R.id.host, new
					// FundsChartPage()).addToBackStack(
					// null);
					// ft.commit();
				}

			});
		} else {
			aq.id(R.id.f1t).getTextView().setTextColor(Color.GRAY);
			aq.id(R.id.f1).getView().setEnabled(false);
		}

		if (userBean.getMenuqx().contains("2")) {
			aq.id(R.id.f2).clicked(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					functionId = R.id.f2;
					fetchData("2", outerUrl);

					// FragmentTransaction ft =
					// getActivity().getFragmentManager()
					// .beginTransaction();
					// ft.replace(R.id.host, new FuturesChartPage())
					// .addToBackStack(null);
					// ft.commit();
				}

			});
		} else {
			aq.id(R.id.f2t).getTextView().setTextColor(Color.GRAY);
			aq.id(R.id.f2).getView().setEnabled(false);
		}

		if (userBean.getMenuqx().contains("3")) {
			aq.id(R.id.f3).clicked(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					functionId = R.id.f3;
					fetchData("3", outerUrl);

					// FragmentTransaction ft =
					// getActivity().getFragmentManager()
					// .beginTransaction();
					// ft.replace(R.id.host, new
					// QuotaTablePage()).addToBackStack(
					// null);
					// ft.commit();
				}

			});
		} else {
			aq.id(R.id.f3t).getTextView().setTextColor(Color.GRAY);
			aq.id(R.id.f3).getView().setEnabled(false);
		}

	}

	private void fetchData(final String menuqx, final String url) {
		// String url = "http://192.168.7.22/mobile/loginServlet";

		Map<String, String> map = new HashMap<String, String>();

		map.put("menuqx", menuqx);
		map.put("companyID", userBean.getCompanyID());
		map.put("companyQX", userBean.getCompanyqx());
		map.put("year", year);
		map.put("month", month);
		mAq.ajax(url, map, JSONArray.class, new AjaxCallback<JSONArray>() {

			@Override
			public void callback(String urlret, JSONArray json,
					AjaxStatus status) {
				try {
					if (json != null) {
						leadPage(json);
					} else {
						// ajax error, show error code
						if (urlret.equals(outerUrl)) {
							HomePage.this.fetchData(menuqx, innerUrl);
						} else {
							Toast.makeText(getActivity(), "网络连接错误，请检查您的网络",
									Toast.LENGTH_LONG).show();
						}
					}

				} catch (Exception e) {

				}
			}

		});
	}

	private void leadPage(JSONArray json) throws JSONException {
		JSONObject jsonObject = null;
		FragmentTransaction ft = null;
		switch (functionId) {
		case R.id.f1:
			List<YSZKBean> yszkBeans = new ArrayList<YSZKBean>();
			for (int i = 0; i < json.length(); i++) {
				jsonObject = json.getJSONObject(i);
				YSZKBean yszkBean = (YSZKBean) JsonUtil.jsonToBean(jsonObject,
						YSZKBean.class);
				yszkBeans.add(yszkBean);
			}

			ft = getActivity().getFragmentManager().beginTransaction();
			FundsChartPage fundsChartPage = new FundsChartPage();
			fundsChartPage.setUserBean(userBean);
			fundsChartPage.setYszkBeans(yszkBeans);
			ft.replace(R.id.host, fundsChartPage).addToBackStack(null);
			ft.commit();
			break;
		case R.id.f2:

			List<QHMXBean> qhmxBeans = new ArrayList<QHMXBean>();
			for (int i = 0; i < json.length(); i++) {
				jsonObject = json.getJSONObject(i);
				QHMXBean qhmxBean = (QHMXBean) JsonUtil.jsonToBean(jsonObject,
						QHMXBean.class);
				qhmxBeans.add(qhmxBean);
			}

			ft = getActivity().getFragmentManager().beginTransaction();
			FuturesChartPage futuresChartPage = new FuturesChartPage();
			futuresChartPage.setUserBean(userBean);
			futuresChartPage.setQhmxBeans(qhmxBeans);
			ft.replace(R.id.host, futuresChartPage).addToBackStack(null);
			ft.commit();
			break;
		case R.id.f3:

			List<YDZBBean> ydzbBeans = new ArrayList<YDZBBean>();
			for (int i = 0; i < json.length(); i++) {
				jsonObject = json.getJSONObject(i);
				YDZBBean ydzbBean = (YDZBBean) JsonUtil.jsonToBean(jsonObject,
						YDZBBean.class);
				ydzbBeans.add(ydzbBean);
			}
			ft = getActivity().getFragmentManager().beginTransaction();
			QuotaTablePage quotaTablePage = new QuotaTablePage();
			quotaTablePage.setYdzbBeans(ydzbBeans);
			ft.replace(R.id.host, quotaTablePage).addToBackStack(null);
			ft.commit();

			break;

		default:
			ft = null;
			break;
		}
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
