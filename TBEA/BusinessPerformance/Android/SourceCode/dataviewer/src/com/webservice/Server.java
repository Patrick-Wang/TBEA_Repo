package com.webservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.common.JsonUtil;
import com.javaBean.QHMXBean;
import com.javaBean.UserBean;
import com.javaBean.YDZBBean;

public class Server {

	public interface OnLoginResponseListener {
		void onLogin(UserBean userBean, AjaxStatus status);
	}

	public interface OnMonthQuotaResponseListener {
		void onMonthQuota(List<YDZBBean> ydzbBeans, AjaxStatus status);
	}

	public interface OnFuturesResponseListener {
		void onFutures(List<QHMXBean> qhmxBeans, AjaxStatus status);

	}

	private AQuery aq;

	private final static String transfer_outer_url = "http://218.84.134.160:8081/mobile/dataTransfer";

	private static String login_outer_url = "http://218.84.134.160:8081/mobile/loginServlet";

	private static String login_inner_url = "http://192.168.7.22/mobile/loginServlet";

	private static Server instance = null;

	private UserBean userBean = null;

	public static void reset(Activity activity) {
		instance = new Server(new AQuery(activity));
	}

	public static Server getInstance() {
		return instance;
	}

	private Server(AQuery aq) {
		super();
		this.aq = aq;
	}

	private void login(String url, String userName, String password,
			final OnLoginResponseListener onLoginResponseListener) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", userName);
		map.put("password", password);
		aq.ajax(url, map, JSONObject.class, new AjaxCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {

				if (json != null) {
					userBean = (UserBean) JsonUtil.jsonToBean(json,
							UserBean.class);
				} else {
					userBean = null;
				}
				onLoginResponseListener.onLogin(userBean, status);
			}

		});
	}

	public void login_outer(String userName, String password,
			final OnLoginResponseListener onLoginResponseListener) {
		login(login_outer_url, userName, password, onLoginResponseListener);
	}

	public void login_inner(String userName, String password,
			final OnLoginResponseListener onLoginResponseListener) {
		login(login_inner_url, userName, password, onLoginResponseListener);
	}

	public void getMonthQuota(List<Company> companys, String year,
			String month,
			final OnMonthQuotaResponseListener onMonthQuotaResponseListener) {
		Map<String, String> request = new HashMap<String, String>();
		request.put("menuqx", "3");
		request.put("companyID", userBean.getCompanyID());
		String companyQX = "";
		for (int i = companys.size() - 1; i >= 0; --i) {
			companyQX += companys.get(i).getId() + ",";
		}
		request.put("companyQX", companyQX.substring(0, companyQX.length() - 1));
		request.put("year", year);
		request.put("month", month);

		aq.ajax(transfer_outer_url, request, JSONArray.class,
				new AjaxCallback<JSONArray>() {
					@Override
					public void callback(String urlret, JSONArray json,
							AjaxStatus status) {
						List<YDZBBean> ydzbBeans = null;
						if (json != null) {
							ydzbBeans = new ArrayList<YDZBBean>(json.length());
							try {
								for (int i = 0, len = json.length(); i < len; i++) {
									ydzbBeans.add((YDZBBean) JsonUtil
											.jsonToBean(json.getJSONObject(i),
													YDZBBean.class));
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
						onMonthQuotaResponseListener.onMonthQuota(ydzbBeans,
								status);
					}
				});
	}

	public void getFutures(List<Company> companys,
			final OnFuturesResponseListener onFuturesResponseListener) {
		Map<String, String> request = new HashMap<String, String>();
		request.put("menuqx", "3");
		request.put("companyID", userBean.getCompanyID());
		String companyQX = "";
		for (int i = companys.size() - 1; i >= 0; --i) {
			companyQX += companys.get(i).getId() + ",";
		}
		request.put("companyQX", companyQX.substring(0, companyQX.length() - 1));
		request.put("year", "" + Calendar.getInstance().get(Calendar.YEAR));
		request.put("month", "" + (Calendar.getInstance().get(Calendar.MONTH)));

		aq.ajax(transfer_outer_url, request, JSONArray.class,
				new AjaxCallback<JSONArray>() {
					@Override
					public void callback(String urlret, JSONArray json,
							AjaxStatus status) {
						List<QHMXBean> qhmxBeans = null;
						if (json != null) {
							qhmxBeans = new ArrayList<QHMXBean>(json.length());
							try {
								for (int i = 0, len = json.length(); i < len; i++) {
									qhmxBeans.add((QHMXBean) JsonUtil
											.jsonToBean(json.getJSONObject(i),
													QHMXBean.class));
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
						onFuturesResponseListener.onFutures(qhmxBeans, status);
					}
				});
	}
}
