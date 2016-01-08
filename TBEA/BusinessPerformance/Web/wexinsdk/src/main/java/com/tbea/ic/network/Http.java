package com.tbea.ic.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {
	public String get(String httpUrl) throws IOException {
		URL url = new URL(httpUrl);
		HttpURLConnection urlConn = (HttpURLConnection) (url.openConnection());
		urlConn.setDoOutput(true); 
		HttpURLConnection.setFollowRedirects(true);
		InputStream is = urlConn.getInputStream();
		StringBuilder buidler = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
		String line;
		while ((line = br.readLine()) != null){
			buidler.append(line);
		}
		is.close();
		return buidler.toString();
	}
}
