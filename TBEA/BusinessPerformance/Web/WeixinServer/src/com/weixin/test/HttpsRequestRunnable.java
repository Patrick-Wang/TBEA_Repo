package com.weixin.test;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.DeflaterInputStream;
import java.util.zip.InflaterInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

class HttpsRequestRunnable implements Runnable {

    String outPath = "D:\\post_result\\";

    // String url = "http://54.248.97.19:8080/Messaging/messaging.do";
    // String url = "http://10.10.117.136:8080/Messaging/messaging.do";
    // String url = "http://10.10.118.149:8082/Messaging/messaging.do";
    // String url = "http://176.34.6.248:8080/Messaging/messaging.do";
    // String url =
    // "https://test.messagecenter.sony.com/Messaging/messaging.do";
    // String url =
    // "https://ec2-54-216-76-242.eu-west-1.compute.amazonaws.com/Messaging/messaging.do";
    // String url =
    // "https://prod.messagecenter.sony.com/Messaging/messaging.do";
    // String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    String getURL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken"
            + "?corpid=wx40b71464a42adcf3"
            + "&corpsecret=BW-Tuxi3fYjgjOOQv2d9iR_7Cz0mRSDVfVaHtjE-2Z1GaHQQIwV0awLsO17zPnPy";
    String postURL = "https://qyapi.weixin.qq.com/cgi-bin/message/send"
            + "?access_token=2R7Lk-uTArE8zUauh1fPjBfh45hpAKWM2KYG3elC7nDktz5zwgbulSKRA78YVuaTr3r55nw9iMFfRfAtWWWVag"
            + "&debug=1";

//    String inPath = "D:\\post.txt";

    public HttpsRequestRunnable() {

    }

    static int successCount = 0;

    static int errorCount = 0;

    static int totalCount = 0;

    @Override
    public void run() {
        HttpsURLConnection connection = null;
        DataOutputStream out = null;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        DeflaterInputStream inflaterInputStream = null;
        InputStream input = null;
        InflaterInputStream iis = null;
        FileOutputStream fop = null;
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
            String executeUrl = postURL;
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
            String content = "{\"touser\": \"anfengling\",\"toparty\": \"\",\"totag\": \"\",\"msgtype\": \"text\",\"agentid\": \"25\",\"text\": {\"content\": \""
//                    + URLEncoder.encode("中文", "UTF8")
                    + "中文<a href='http://www.baidu.com'>baidu</a>"
                    + "\"},\"safe\":\"0\"}";
           
            out.write(content.getBytes());
            out.flush();

            input = connection.getInputStream();

            byte[] bytes = new byte[1000];
            int r;
            fop = new FileOutputStream(new File(outPath + "post_result" + "_"
                    + time + ".txt"));
            while ((r = input.read(bytes)) != -1) {
                fop.write(bytes, 0, r);
            }
            ++errorCount;
            fop.flush();
        } catch (Exception e) {
            ++errorCount;
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
                ++totalCount;
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

}