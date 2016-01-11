package com.tbea.ic.auth;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.tbea.ic.network.Http;
import com.tbea.ic.network.Https;

import net.sf.json.JSONObject;

public class Connection {
    String tokenURL;
	String accessToken;
	Timer timer = new Timer();  
	Https https = new Https();
	static Connection ins;
	
	
	private Connection(){};
	
	public static Connection getInstance(){
		if (null == ins){
			return ins = new Connection();
		}
		return ins;
	}
	
	public boolean isValid(){
		return null != accessToken;
	}
	
	private void schedule(int delay) {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					Connection.this.active();
				} catch (Exception e) {
					Connection.this.accessToken = null;
					e.printStackTrace();
				}
			}
		}, delay);
	}
	
	public Connection open(String corpid, String corpsecret) throws AuthException, IOException{
		tokenURL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken"
	            + "?corpid=" + corpid
	            + "&corpsecret=" + corpsecret;
		active();
		return this;
	}

	private void active() throws AuthException, IOException{
		Http http = new Http();
		JSONObject jResp = JSONObject.fromObject(http.get(tokenURL));
		accessToken = jResp.getString("access_token");
		if (null != accessToken){
			schedule((jResp.getInt("expires_in")) * 500);
		}else{
			if (!isValid()){
				throw new AuthException(jResp.toString());
			}
		}
	}
	
	public String httpsPost(String url, String data){
		return https.post(url + "access_token=" + accessToken, data);
	}
	
	public String httpsGet(String url){
		return https.get(url + "access_token=" + accessToken);
	}
	
	public String getToken(){
		return accessToken;
	}
}
