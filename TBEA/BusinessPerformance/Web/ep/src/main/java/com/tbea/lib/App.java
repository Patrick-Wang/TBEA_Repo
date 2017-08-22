package com.tbea.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tbea.lib.ep.postdata;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
		postdata post=new postdata();
		String data = "";
		 InputStreamReader read = new InputStreamReader(
		 new FileInputStream(new File(args[0])),"utf-8");//考虑到编码格式
		 BufferedReader bufferedReader = new BufferedReader(read);
		 String lineTxt = null;
		 while((lineTxt = bufferedReader.readLine()) != null){
			 data += lineTxt;
		 }
		 read.close();
		
//		String random = String.valueOf(Math.random() * 100);
//		long currentTime = System.currentTimeMillis();		
//		//发送方EID
//		String fEId = "101";
//		//接收方EID
//		String tEId = "101";
//		//PUBACC
//		String pubacc = "XT-2e63fc1d-4cd0-424e-bd76-650dce3c8f99";
//		//PUBACC_KEY
//		String pubacc_key = "78fc663cf44039ccedbdc5d30bd5e44a";
//		//APPID
//		String appId = "10103";
//		//员工之家serverUrl
		String serverUrl = "http://xt.tbea.com:8060";
//		//员工之家单点跳转Url
//		//String ssoUrl = "http://www.baidu.com/";
//		//待办消息内容
//		String text="公司整体情况:\r\n利润总额:214903；销售收入：2232715；应收账款：1124483；存货：1228512；经营性净现金流：-58702";
//		//接收消息员工的openid或员工号
//		List<String> userIds =new ArrayList<String>();
//		userIds.add("395566");
//		//组装form参数
//		JSONObject from = new JSONObject();
//		from.put("no", fEId);
//		from.put("pub", pubacc);
//		from.put("time", currentTime);
//		from.put("nonce", random);
//		String[] data = { fEId, pubacc, pubacc_key, random,String.valueOf(currentTime) };
//		from.put("pubtoken", post.sha(data));
//		//组装to参数
//		JSONArray tos = new JSONArray();
//		JSONObject to = new JSONObject();
//		to.put("no", tEId);
//		to.put("user", userIds);
//		to.put("code", "2");
//		tos.add(to);
//		//组装msg(type=5对应的msg格式)
//		JSONObject msg = new JSONObject();			
//		//msg.put("url", ssoUrl);
//		//如果打开的是轻应用页面需要传入appid
//		msg.put("appid", appId);
//		msg.put("todo", 0);			
//		msg.put("text", text);
//		//将OA中待办fdId作为员工之家消息id
//		//msg.put("sourceid", mesId);
//		//组装所有参数param
//		JSONObject params = new JSONObject();
//		params.put("from", from);
//		params.put("to", tos);
//		params.put("type", 2);
//		params.put("msg", msg);
			
		System.out.println(post.sendPost(serverUrl+"/pubacc/pubsend", data));
    }
}
