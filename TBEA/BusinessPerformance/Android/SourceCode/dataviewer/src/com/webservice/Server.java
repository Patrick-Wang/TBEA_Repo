package com.webservice;

import java.io.File;
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
import com.javaBean.YSZKBean;

public class Server {

	public enum PasswrodChangeResponse {
		SUCCESS, OLD_PASSWORD_ERROR, FAILED
	}

	public enum Type {
		LAN, WAN, UNKNOWN
	}

	public interface OnLoginResponseListener {
		void onLogin(UserBean userBean, AjaxStatus status);
	}

	public interface OnMonthQuotaResponseListener {
		void onMonthQuota(List<YDZBBean> ydzbBeans, AjaxStatus status);
	}

	public interface OnFuturesResponseListener {
		void onFutures(List<QHMXBean> qhmxBeans_Copper, List<QHMXBean> qhmxBeans_Aluminium, AjaxStatus status);
	}

	public interface OnFundsResponseListener {
		void onFunds(List<YSZKBean> yszkBeans, AjaxStatus status);
	}

	public interface OnPackageDownloaded {
		void onDownloaded(boolean bSuccess, String path);
	}

	public interface OnVersionUpdated {
		void onVersionUpdated(boolean hasNewVersion, String url);
	}

	public interface OnPasswordChanged {
		void onPasswordChanged(PasswrodChangeResponse result);
	}

	public interface OnRegistered {
		void onRegistered(Boolean ret);
	}

	public interface OnUserValidated {
		void onUserValidated(Boolean result);
	}

	private AQuery aq = null;
	private Configurations config = null;
	private UserBean userBean = null;
	private static Server instance = null;
	private String userName = "";

	public String getUserName() {
		return userName;
	}

	public static void resetAsLANServer(Activity activity) {
		instance = new Server(new AQuery(activity), new LANConfigurations());
	}

	public static void resetAsWANServer(Activity activity) {
		instance = new Server(new AQuery(activity), new WANConfigurations());
	}

	public static String getServerDataUpdateTime() {
		Calendar calendar = Calendar.getInstance();
		
		int delta = 2;
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour >= 2) {
			delta = 1;
		}
		
		int day = calendar.get(Calendar.DAY_OF_MONTH) - delta;
		return calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
				+ day;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public static Server getInstance() {
		return instance;
	}

	private Server(AQuery aq, Configurations config) {
		super();
		this.aq = aq;
		this.config = config;
	}

	public void register(String userName, String verificationCode, String password,
			final OnRegistered onReigsteredCallback) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("IMEI", userName);
		map.put("verificationCode", verificationCode);
		map.put("initialPwd", password);
		aq.ajax(config.getRegisterUrl(), map, String.class, new AjaxCallback<String>() {

			@Override
			public void callback(String url, String ret, AjaxStatus status) {
				if (ret != null) {
					Server.this.userName = ret;
					onReigsteredCallback.onRegistered(!"false".equals(ret));
				} else {
					onReigsteredCallback.onRegistered(null);
				}
			}
		});
	}

	public void validateUser(String userName, final OnUserValidated onUserValidatedCallback) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("IMEI", userName);
		aq.ajax(config.getUserValidationUrl(), map, String.class, new AjaxCallback<String>() {
			@Override
			public void callback(String url, String ret, AjaxStatus status) {

				if (ret != null) {
					Server.this.userName = ret;
					onUserValidatedCallback.onUserValidated(!"false".equals(ret));
					
				} else {
					onUserValidatedCallback.onUserValidated(null);
				}
			}
		});
	}

	public void downloadPackage(String url, final String fileName, final OnPackageDownloaded onPackageDownloaded) {
		File file = new File(fileName);
		aq.download(url, file, new AjaxCallback<File>() {
			@Override
			public void callback(String url, File file, AjaxStatus status) {
				onPackageDownloaded.onDownloaded(200 == status.getCode(), fileName);
			}
		});
	}

	public Type serverType() {
		if ("LAN".equals(config.getNetType())) {
			return Type.LAN;
		} else {
			return Type.WAN;
		}
	}

	public void hasNewVersion(int version, final OnVersionUpdated onVersionUpdated) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("version", "" + version);
		aq.ajax(config.getUpdateUrl(), map, String.class, new AjaxCallback<String>() {
			@Override
			public void callback(String url, String ret, AjaxStatus status) {

				if (status.getCode() == 200) {
					onVersionUpdated.onVersionUpdated(!ret.equals("null"), config.getHome() + ret);
				} else {
					onVersionUpdated.onVersionUpdated(false, null);
				}
			}
		}.timeout(3000));
	}

	public void login(String userName, String password, final OnLoginResponseListener onLoginResponseListener) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", userName);
		map.put("password", password);
		aq.ajax(config.getLoginUrl(), map, JSONObject.class, new AjaxCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {

				if (json != null) {
					userBean = (UserBean) JsonUtil.jsonToBean(json, UserBean.class);
					onLoginResponseListener.onLogin(userBean, status);
				} else {
					onLoginResponseListener.onLogin(null, status);
				}

			}
		});
	}

	public void changePassword(String user, String oldPassword, String newPassword,
			final OnPasswordChanged onPasswordChanged) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", user);
		map.put("password", oldPassword);
		map.put("newpwd", newPassword);
		aq.ajax(config.getChangePasswordUrl(), map, String.class, new AjaxCallback<String>() {

			@Override
			public void callback(String url, String ret, AjaxStatus status) {

				if (ret != null) {
					PasswrodChangeResponse resp = PasswrodChangeResponse.FAILED;
					if ("0".equals(ret)) {
						resp = PasswrodChangeResponse.SUCCESS;
					} else if ("1".equals(ret)) {
						resp = PasswrodChangeResponse.OLD_PASSWORD_ERROR;
					}
					onPasswordChanged.onPasswordChanged(resp);
				} else {
					onPasswordChanged.onPasswordChanged(null);
				}

			}

		});
	}

	public void getMonthQuota(List<Company> companys, String year, String month,
			final OnMonthQuotaResponseListener onMonthQuotaResponseListener) {
		Map<String, String> request = new HashMap<String, String>();
		request.put("menuqx", "3");
		request.put("companyID", userBean.getCompanyID());
		String companyQX = "";
		for (int i = 0, len = companys.size(); i < len; ++i) {
			companyQX += companys.get(i).getId() + ",";
		}
		request.put("companyQX", companyQX.substring(0, companyQX.length() - 1));
		request.put("year", year);
		request.put("month", month);

		aq.ajax(config.getTransferUrl(), request, JSONArray.class, new AjaxCallback<JSONArray>() {
			@Override
			public void callback(String urlret, JSONArray json, AjaxStatus status) {
				List<YDZBBean> ydzbBeans = null;
				if (json != null) {
					try {
						ydzbBeans = new ArrayList<YDZBBean>(json.length());

						for (int i = 0, len = json.length(); i < len; i++) {
							ydzbBeans.add((YDZBBean) JsonUtil.jsonToBean(json.getJSONObject(i), YDZBBean.class));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				onMonthQuotaResponseListener.onMonthQuota(ydzbBeans, status);
			}
		});
	}

	public void getFutures(final OnFuturesResponseListener onFuturesResponseListener) {
		Map<String, String> request = new HashMap<String, String>();
		request.put("menuqx", "2");
		request.put("companyID", userBean.getCompanyID());
		request.put("companyQX", userBean.getCompanyqx());
		request.put("year", "" + Calendar.getInstance().get(Calendar.YEAR));
		request.put("month", "" + (Calendar.getInstance().get(Calendar.MONTH) + 1));

		aq.ajax(config.getTransferUrl(), request, JSONArray.class, new AjaxCallback<JSONArray>() {
			@Override
			public void callback(String urlret, JSONArray json, AjaxStatus status) {

				List<QHMXBean> qhmxBeans_Copper = null;
				List<QHMXBean> qhmxBeans_Aluminium = null;

				if (json != null) {
					try {
						qhmxBeans_Copper = new ArrayList<QHMXBean>();
						qhmxBeans_Aluminium = new ArrayList<QHMXBean>();
						JSONArray qhmxBeans_Copper_JsonArray = json.getJSONArray(0);
						JSONArray qhmxBeans_Aluminium_JsonArray = json.getJSONArray(1);
						for (int i = 0; i < qhmxBeans_Copper_JsonArray.length(); i++) {
							QHMXBean qhmxBean = (QHMXBean) JsonUtil.jsonToBean(
									qhmxBeans_Copper_JsonArray.getJSONObject(i), QHMXBean.class);
							qhmxBeans_Copper.add(qhmxBean);
						}
						for (int i = 0; i < qhmxBeans_Aluminium_JsonArray.length(); i++) {
							QHMXBean qhmxBean = (QHMXBean) JsonUtil.jsonToBean(
									qhmxBeans_Aluminium_JsonArray.getJSONObject(i), QHMXBean.class);
							qhmxBeans_Aluminium.add(qhmxBean);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				onFuturesResponseListener.onFutures(qhmxBeans_Copper, qhmxBeans_Aluminium, status);
			}
		});
	}

	public Configurations getConfigurations(){
		return config;
	}
	
	public void getFunds(final OnFundsResponseListener onFundsResponseListener) {
		Map<String, String> request = new HashMap<String, String>();
		request.put("menuqx", "1");
		request.put("companyID", userBean.getCompanyID());
		request.put("companyQX", userBean.getCompanyqx());
		request.put("year", "" + Calendar.getInstance().get(Calendar.YEAR));
		request.put("month", "" + (Calendar.getInstance().get(Calendar.MONTH) + 1));
		aq.ajax(config.getTransferUrl(), request, JSONArray.class, new AjaxCallback<JSONArray>() {
			@Override
			public void callback(String urlret, JSONArray json, AjaxStatus status) {
				List<YSZKBean> yszkBeans = null;
				if (json != null) {
					try {
						yszkBeans = new ArrayList<YSZKBean>();
						for (int i = 0; i < json.length(); i++) {
							YSZKBean yszkBean = (YSZKBean) JsonUtil.jsonToBean(json.getJSONObject(i), YSZKBean.class);
							yszkBeans.add(yszkBean);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				onFundsResponseListener.onFunds(yszkBeans, status);
			}
		});
	}
}
