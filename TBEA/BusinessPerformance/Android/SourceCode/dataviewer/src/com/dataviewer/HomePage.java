package com.dataviewer;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.androidquery.AQuery;
import com.tbea.dataviewer.R;
import com.webservice.Server;

public class HomePage extends AQueryFragment {


	@Override
	public View onLoadView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.home_page, null);
	}

	@Override
	protected void onViewPrepared(AQuery aq, View fragView) {

		String menuqx = Server.getInstance().getUserBean().getMenuqx();
		if (menuqx.contains("1")) {
			aq.id(R.id.f1).clicked(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					FragmentTransaction ft = getActivity().getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.host, new FundsChartPage()).addToBackStack(
							null);
					ft.commit();
				}

			});
		} else {
			aq.id(R.id.f1t).getTextView().setTextColor(Color.GRAY);
			aq.id(R.id.f1).getView().setEnabled(false);
		}

		if (menuqx.contains("2")) {
			aq.id(R.id.f2).clicked(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					FragmentTransaction ft = getActivity().getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.host, new FuturesChartPage())
							.addToBackStack(null);
					ft.commit();
				}

			});
		} else {
			aq.id(R.id.f2t).getTextView().setTextColor(Color.GRAY);
			aq.id(R.id.f2).getView().setEnabled(false);
		}

		if (menuqx.contains("3")) {
			aq.id(R.id.f3).clicked(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					FragmentTransaction ft = getActivity().getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.host, new QuotaTablePage()).addToBackStack(
							null);
					ft.commit();
				}

			});
		} else {
			aq.id(R.id.f3t).getTextView().setTextColor(Color.GRAY);
			aq.id(R.id.f3).getView().setEnabled(false);
		}

	}

//	private void fetchData(final String menuqx, final String url) {
//		Map<String, String> map = new HashMap<String, String>();
//
//		map.put("menuqx", menuqx);
//		map.put("companyID", userBean.getCompanyID());
//		map.put("companyQX", userBean.getCompanyqx());
//		map.put("year", year);
//		map.put("month", month);
//		mAq.ajax(url, map, JSONArray.class, new AjaxCallback<JSONArray>() {
//
//			@Override
//			public void callback(String urlret, JSONArray json,
//					AjaxStatus status) {
//				try {
//					if (json != null) {
//						leadPage(json);
//					} else {
//						// ajax error, show error code
//						if (urlret.equals(outerUrl)) {
//							HomePage.this.fetchData(menuqx, innerUrl);
//						} else {
//							Toast.makeText(getActivity(), "网络连接错误，请检查您的网络",
//									Toast.LENGTH_LONG).show();
//						}
//					}
//
//				} catch (Exception e) {
//
//				}
//			}
//
//		});
//	}
//
//	private void leadPage(JSONArray json) throws JSONException {
//		JSONObject jsonObject = null;
//		FragmentTransaction ft = null;
//		switch (functionId) {
//		case R.id.f1:
//			List<YSZKBean> yszkBeans = new ArrayList<YSZKBean>();
//			for (int i = 0; i < json.length(); i++) {
//				jsonObject = json.getJSONObject(i);
//				YSZKBean yszkBean = (YSZKBean) JsonUtil.jsonToBean(jsonObject,
//						YSZKBean.class);
//				yszkBeans.add(yszkBean);
//			}
//
//			ft = getActivity().getFragmentManager().beginTransaction();
//			FundsChartPage fundsChartPage = new FundsChartPage();
//			fundsChartPage.setUserBean(userBean);
//			fundsChartPage.setYszkBeans(yszkBeans);
//			ft.replace(R.id.host, fundsChartPage).addToBackStack(null);
//			ft.commit();
//			break;
//		case R.id.f2:
//
//			List<QHMXBean> qhmxBeans_Copper = new ArrayList<QHMXBean>();
//			List<QHMXBean> qhmxBeans_Aluminium = new ArrayList<QHMXBean>();
//			JSONArray qhmxBeans_Copper_JsonArray = json.getJSONArray(0);
//			JSONArray qhmxBeans_Aluminium_JsonArray = json.getJSONArray(1);
//			for (int i = 0; i < qhmxBeans_Copper_JsonArray.length(); i++) {
//				jsonObject = qhmxBeans_Copper_JsonArray.getJSONObject(i);
//				QHMXBean qhmxBean = (QHMXBean) JsonUtil.jsonToBean(jsonObject,
//						QHMXBean.class);
//				qhmxBeans_Copper.add(qhmxBean);
//			}
//			for (int i = 0; i < qhmxBeans_Aluminium_JsonArray.length(); i++) {
//				jsonObject = qhmxBeans_Aluminium_JsonArray.getJSONObject(i);
//				QHMXBean qhmxBean = (QHMXBean) JsonUtil.jsonToBean(jsonObject,
//						QHMXBean.class);
//				qhmxBeans_Aluminium.add(qhmxBean);
//			}
//
//			ft = getActivity().getFragmentManager().beginTransaction();
//			FuturesChartPage futuresChartPage = new FuturesChartPage();
//			futuresChartPage.setUserBean(userBean);
//			futuresChartPage.setQhmxBeans_Copper(qhmxBeans_Copper);
//			futuresChartPage.setQhmxBeans_Aluminium(qhmxBeans_Aluminium);
//			ft.replace(R.id.host, futuresChartPage).addToBackStack(null);
//			ft.commit();
//			break;
//		case R.id.f3:
//
//			List<YDZBBean> ydzbBeans = new ArrayList<YDZBBean>();
//			for (int i = 0; i < json.length(); i++) {
//				jsonObject = json.getJSONObject(i);
//				YDZBBean ydzbBean = (YDZBBean) JsonUtil.jsonToBean(jsonObject,
//						YDZBBean.class);
//				ydzbBeans.add(ydzbBean);
//			}
//			ft = getActivity().getFragmentManager().beginTransaction();
//			QuotaTablePage quotaTablePage = new QuotaTablePage();
//			// quotaTablePage.setYdzbBeans(ydzbBeans);
//			ft.replace(R.id.host, quotaTablePage).addToBackStack(null);
//			ft.commit();
//
//			break;
//
//		default:
//			ft = null;
//			break;
//		}
//	}
}
