package com.tbea.ic.scanner.net;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.json.JSONObject;

import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.tbea.ic.scanner.net.bean.User;
import com.tbea.ic.scanner.net.config.Configurations;
import com.tbea.ic.scanner.net.config.LANConfigurations;
import com.tbea.ic.scanner.net.config.WANConfigurations;
import com.tbea.ic.util.JsonUtil;

public class Server {

	private AQuery aq = null;
	private Configurations config = null;
	private User user = null;
	private static Server instance = null;

	public static void resetAsLANServer(AQuery aq) {
		instance = new Server(aq, new LANConfigurations());
	}

	public static void resetAsWANServer(AQuery aq) {
		instance = new Server(aq, new WANConfigurations());
	}

	public static String getServerDataUpdateTime() {
		Calendar calendar = Calendar.getInstance();

		int delta = 2;
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour >= 2) {
			delta = 1;
		}

		int day = calendar.get(Calendar.DAY_OF_MONTH) - delta;
		return calendar.get(Calendar.YEAR) + "/"
				+ (calendar.get(Calendar.MONTH) + 1) + "/" + day;
	}

	public User getUser() {
		return user;
	}

	public static Server getInstance() {
		return instance;
	}

	private Server(AQuery aq, Configurations config) {
		super();
		this.aq = aq;
		this.config = config;
	}

	public Promise<String, AjaxStatus, Integer> download(String url,
			final String fileName) {
		File file = new File(fileName);
		final Deferred<String, AjaxStatus, Integer> deferred = new DeferredObject<String, AjaxStatus, Integer>();
		aq.download(url, file, new AjaxCallback<File>() {
			@Override
			public void callback(String url, File file, AjaxStatus status) {
				if (200 == status.getCode()) {
					deferred.resolve(fileName);
				} else {
					deferred.reject(status);
				}
			}
		});
		return deferred.promise();
	}

	public Promise<String, AjaxStatus, Integer> hasNewVersion(int version) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("version", "" + version);
		final Deferred<String, AjaxStatus, Integer> deferred = new DeferredObject<String, AjaxStatus, Integer>();
		aq.ajax(config.getUpdateUrl(), map, String.class,
				new AjaxCallback<String>() {
					@Override
					public void callback(String url, String ret,
							AjaxStatus status) {

						if (status.getCode() == 200) {
							if (!"null".equals(ret)) {
								deferred.resolve(config.getHost() + ret);
							} else {
								deferred.resolve(null);
							}
						} else {
							deferred.reject(status);
						}
					}
				}.timeout(3000));
		return deferred.promise();
	}

	public Promise<User, AjaxStatus, Integer> login(String userName,
			String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", userName);
		map.put("password", password);
		final Deferred<User, AjaxStatus, Integer> deferred = new DeferredObject<User, AjaxStatus, Integer>();
		aq.ajax(config.getLoginUrl(), map, JSONObject.class,
				new AjaxCallback<JSONObject>() {

					@Override
					public void callback(String url, JSONObject json,
							AjaxStatus status) {
						if (json != null) {

							CookieSyncManager.createInstance(aq.getContext());
							CookieManager cookieManager = CookieManager
									.getInstance();
							cookieManager.setAcceptCookie(true);
							cookieManager.removeSessionCookie();
							List<Cookie> cookies = status.getCookies();
							if (!cookies.isEmpty()) {
								StringBuilder sbCookie = new StringBuilder();

								for (Cookie cook : cookies) {
									sbCookie.append(String.format(";%s=%s",
											cook.getName(), cook.getValue()));
								}

								cookieManager.setCookie(url, sbCookie
										.toString().substring(1));
							}
							CookieSyncManager.getInstance().sync();

							user = (User) JsonUtil.jsonToBean(json, User.class);
							deferred.resolve(user);
						} else {
							deferred.reject(status);
						}

					}
				});
		return deferred.promise();
	}
}
