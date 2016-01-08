package com.tbea.ic.network;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.DeflaterInputStream;
import java.util.zip.InflaterInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class Https {
	public String post(String httpsUrl, String data) {
		HttpsURLConnection connection = null;
		DataOutputStream out = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		DeflaterInputStream inflaterInputStream = null;
		InputStream input = null;
		InflaterInputStream iis = null;
		FileOutputStream fop = null;
		StringBuilder buidler = new StringBuilder();
		long time = System.currentTimeMillis();
		// String connectdir = HttpRequestRunnable.class.getResource(
		// "log4j.properties").getPath();
		try {
			// Authenticator.setDefault(new Authenticator() {
			// protected PasswordAuthentication getPasswordAuthentication() {
			// return new PasswordAuthentication("xu.yd", new String(
			// "2wsx@WSX").toCharArray());
			// }
			// });
			// System.setProperty("https.proxyType", "4");
			// System.setProperty("https.proxySet", "true");
			// System.setProperty("https.proxyHost", "proxy.neusoft.com");
			// System.setProperty("https.proxyPort", "8080");
			// System.setProperty("javax.net.ssl.trustStore",
			// "tomcat.keystore");
			// System.setProperty("javax.net.ssl.trustStorePassword",
			// "changeit");

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			// String executeUrl = getURL;
			String executeUrl = httpsUrl;
			URL url = new URL(executeUrl);

			connection = (HttpsURLConnection) url.openConnection();

			connection.setSSLSocketFactory(sc.getSocketFactory());
			connection.setHostnameVerifier(new TrustAnyHostnameVerifier());

			connection.setDoOutput(true);
			// Read from the connection. Default is true.
			connection.setDoInput(true);
			// Set the post method. Default is GET
			connection.setRequestMethod("POST");
			// Post cannot use caches
			connection.setUseCaches(false);

			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");
			// connection.addRequestProperty("isZip", "true");
			// TODO attributes
			// connection.addRequestProperty("corpid", "wx40b71464a42adcf3");
			// connection
			// .addRequestProperty("corpsecret",
			// "BW-Tuxi3fYjgjOOQv2d9iR_7Cz0mRSDVfVaHtjE-2Z1GaHQQIwV0awLsO17zPnPy");
			// connection.
			connection.connect();
			out = new DataOutputStream(connection.getOutputStream());
			// byte[] buffer = null;
			// File file = new File(inPath);
			// fis = new FileInputStream(file);
			// inflaterInputStream = new DeflaterInputStream(fis);
			// bos = new ByteArrayOutputStream(1000);
			// byte[] b = new byte[1000];
			// int n;
			// while ((n = inflaterInputStream.read(b)) != -1) {
			// bos.write(b, 0, n);
			// }
			//
			// buffer = bos.toByteArray();

			out.write(data.getBytes("utf-8"));
			out.flush();

			input = connection.getInputStream();

			byte[] buffer = new byte[256];

			int i = 0;
			while ((i = input.read(buffer)) > 0) {
				buidler.append(new String(buffer, 0, i, "utf-8"));
			}
			input.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.close();
				}
				if (null != fis) {
					fis.close();
				}
				if (null != bos) {
					bos.close();
				}
				if (null != inflaterInputStream) {
					inflaterInputStream.close();
				}
				if (null != input) {
					input.close();
				}
				if (null != iis) {
					iis.close();
				}
				if (null != connection) {
					connection.disconnect();
				}
				if (null != fop) {
					fop.close();
				}
				if (null != input) {
					input.close();
				}

			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return buidler.toString();
	}

	public String get(String httpsUrl) {
		HttpsURLConnection connection = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		DeflaterInputStream inflaterInputStream = null;
		InputStream input = null;
		InflaterInputStream iis = null;
		StringBuilder buidler = new StringBuilder();
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			// String executeUrl = getURL;
			String executeUrl = httpsUrl;
			URL url = new URL(executeUrl);

			connection = (HttpsURLConnection) url.openConnection();

			connection.setSSLSocketFactory(sc.getSocketFactory());
			connection.setHostnameVerifier(new TrustAnyHostnameVerifier());

			connection.setDoOutput(true);
			// Read from the connection. Default is true.
			connection.setDoInput(true);
			// Set the post method. Default is GET
			connection.setRequestMethod("GET");
			// Post cannot use caches
			connection.setUseCaches(false);

			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");

			connection.connect();
			input = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(input, "utf-8"));
			String line;
			while ((line = br.readLine()) != null){
				buidler.append(line);
			}
			input.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != fis) {
					fis.close();
				}
				if (null != bos) {
					bos.close();
				}
				if (null != inflaterInputStream) {
					inflaterInputStream.close();
				}
				if (null != input) {
					input.close();
				}
				if (null != iis) {
					iis.close();
				}
				if (null != connection) {
					connection.disconnect();
				}
				if (null != input) {
					input.close();
				}

			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return buidler.toString();
	}
}
