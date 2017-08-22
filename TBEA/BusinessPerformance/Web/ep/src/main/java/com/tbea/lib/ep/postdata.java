package com.tbea.lib.ep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
public class postdata {
	   
	/**
	 * post提交
	 *
	 * @method sendPost Create on 2016-10-19 上午11:20:35 
	 * Copyright (c) 2016 by future-info. 
	 *
	 * @author caoj
	 * @tel 15991758179
	 * @mail caoj@landray.com.cn
	 * @version 0.1
	 *
	 * @param urlstr
	 * @param params
	 * @return
	 */
	public String sendPost(String urlstr, String params){
	    StringBuffer returnResult = new StringBuffer();
	    HttpURLConnection connection = null;
	    OutputStream outStr = null;
	    OutputStreamWriter outWriter = null;
	    InputStream inStr = null;
	    InputStreamReader inReader = null;
	    BufferedReader br = null;
	    try {
	        URL url = new URL(urlstr);
	        connection = (HttpURLConnection)url.openConnection();
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.setUseCaches(false);
	        connection.setInstanceFollowRedirects(true);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Accept", "application/json");
	        connection.setRequestProperty("Content-Type", "application/json");
	        connection.setConnectTimeout(6000);
	        connection.connect();
	        outStr = connection.getOutputStream();
	        outWriter = new OutputStreamWriter(outStr, "UTF-8");
	        outWriter.append(params);
	        outWriter.flush();
	        
	        int httpResult = connection.getResponseCode();
	        //200为成功
	        if(httpResult == 200) {
	        	inStr = connection.getInputStream();
	        	inReader = new InputStreamReader(inStr,"utf-8");
	            br = new BufferedReader(inReader);
	            String line = null;
	            while((line = br.readLine()) != null) {
	            	returnResult.append(line + "\n");
	            }
	        }else{
	        	System.out.println("error——returnState："+httpResult+"; params："+params);
	        	//logger.info("error——returnState："+httpResult+"; params："+params);
	        }
	        /*
	         	• 5000:一般参数错误,如：from/to不完整、必须传入参数xxx
				• 5001:公共号不存在或未审核
			    • 5002:数据长度超限错误，如：传入数据长度超过了1M
			    • 5003:发送的公司或用户错误，如:发送到其他企业,无发送用户或错误的openid
			    • 5004:公共号密钥验证失败，from.pubtoken=sha(from.no,from.pub,公共号.pubkey,from.nonce,from.time)
			    • 5005:发往公共号消息过多,请等x分钟
	         */
	    } catch (Exception e) {
//	    	if(logger.isInfoEnabled()){
//				logger.info(e);
//			}
	        e.printStackTrace();
	    }finally{
	    	try {
	    		if(null != br){
	    			br.close();
	    		}
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	try {
	    		if(null != inReader){
	    			inReader.close();
	    		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
	    		if(null != inReader){
	    			inReader.close();
	    		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
	    		if(null != outWriter){
	    			outWriter.close();
	    		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
	    		if(null != outStr){
	    			outStr.close();
	    		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return returnResult.toString();
	}
	    
	    
	    @SuppressWarnings("deprecation")
		public String sha(String [] str){
			Arrays.sort(str);//按字母顺序排序数组       
			return DigestUtils.shaHex(StringUtils.join(str));
		}
	}