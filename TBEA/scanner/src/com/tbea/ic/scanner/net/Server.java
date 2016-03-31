package com.tbea.ic.scanner.net;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.tbea.ic.scanner.net.config.Configurations;
import com.tbea.ic.scanner.net.config.LANConfigurations;
import com.tbea.ic.scanner.net.config.WANConfigurations;
import com.tbea.ic.scanner.net.entity.DataNode;
import com.tbea.ic.scanner.net.entity.ErrorMessage;
import com.tbea.ic.scanner.net.entity.User;
import com.tbea.ic.util.Tool;
import com.tbea.ic.util.json.JsonException;
import com.tbea.ic.util.json.JsonReflector;

import org.json.JSONArray;
public class Server {

	private AQuery query = null;
	private Configurations config = null;
	private User user = new User();
	private static Server instance = null;

	private Server(AQuery aq, Configurations config) {
		super();
		this.query = aq;
		this.config = config;
	}
	
	public static void resetAsLANServer(AQuery aq) {
		instance = new Server(aq, new LANConfigurations());
	}

	public static void resetAsWANServer(AQuery aq) {
		instance = new Server(aq, new WANConfigurations());
	}

	public User getUser() {
		return user;
	}

	public static Server getInstance() {
		return instance;
	}

	public Promise<String, AjaxStatus, Integer> download(String url,
			final String fileName) {
		File file = new File(fileName);
		final Deferred<String, AjaxStatus, Integer> deferred = new DeferredObject<String, AjaxStatus, Integer>();
		query.download(url, file, new AjaxCallback<File>() {
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
		query.ajax(config.getUpdateUrl(), map, ErrorMessage.class,
				new AjaxCallback<ErrorMessage>() {
					@Override
					public void callback(String url, ErrorMessage em,
							AjaxStatus status) {

						if (status.getCode() == 200) {
							if (em.getErrorCode() == 0) {
								deferred.resolve(config.getHost() + em.getMessage());
							} else {
								deferred.resolve(null);
							}
						} else {
							deferred.reject(status);
						}
					}
				}.timeout(5000));
		return deferred.promise();
	}
	
	public Promise<User, AjaxStatus, Integer> login(String userName,
			String password) throws NoSuchAlgorithmException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", userName);
		map.put("password", Tool.getMD5(password));
		user.setUserid(userName);
		user.setPwd(password);
		final Deferred<User, AjaxStatus, Integer> deferred = new DeferredObject<User, AjaxStatus, Integer>();
		query.ajax(config.getLoginUrl(), map, JSONArray.class,
				new AjaxCallback<JSONArray>() {

					@SuppressWarnings({ "unchecked", "rawtypes" })
					@Override
					public void callback(String url, JSONArray json,
							AjaxStatus status) {
						if (status.getCode() == 200){
							try {
								user.setRights((List)JsonReflector.toList(json, DataNode.class));
							} catch (JsonException e) {
								System.out.println(json.toString());
								e.printStackTrace();
							} catch (Exception e) {
								e.printStackTrace();
							}
							deferred.resolve(user);
							
						}else{
							deferred.reject(status);
						}
					}
				}.timeout(5000));
		return deferred.promise();
	}
}
