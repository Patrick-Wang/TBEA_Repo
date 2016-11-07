package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//		HBWebService hbws = new HBWebService();
		
//		System.out.println("getHBSjzb++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		List<List<Object>> result = hbws.getHBSjzb(new ArrayList<String>(), Date.valueOf("2016-9-1"));
//		for (List<Object> r: result){
//			System.out.println(JSONArray.fromObject(r).toString());
//		}
//		System.out.println("getHBCpqy++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		result = hbws.getHBCpqy(new ArrayList<String>(), Date.valueOf("2016-9-1"));
//		for (List<Object> r: result){
//			System.out.println(JSONArray.fromObject(r).toString());
//		}
//		System.out.println("getHBScqy++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		result = hbws.getHBScqy(new ArrayList<String>(), Date.valueOf("2016-9-1"));
//		for (List<Object> r: result){
//			System.out.println(JSONArray.fromObject(r).toString());
//		}
//		System.out.println("getHBClwcqk++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		result = hbws.getHBClwcqk(new ArrayList<String>(), Date.valueOf("2016-9-1"));
//		for (List<Object> r: result){
//			System.out.println(JSONArray.fromObject(r).toString());
//		}
		Connection conn = null;
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();//加入oracle的驱动，“”里面是驱动的路径
		   
		  String url = "jdbc:oracle:thin:@172.28.11.11:1521:ORCL2";// 数据库连接，oracle代表链接的是oracle数据库；thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:）；1521代表链接数据库的端口号；ORCL代表的是数据库名称

		  String UserName = "IUFO_ZB_DL";// 数据库用户登陆名 ( 也有说是 schema 名字的 )

		  String Password = "IUFO_ZB_DL";// 密码

		  conn = DriverManager.getConnection(url, UserName, Password);

	}

}
