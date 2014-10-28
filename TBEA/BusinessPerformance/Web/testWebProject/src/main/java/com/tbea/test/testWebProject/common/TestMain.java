package com.tbea.test.testWebProject.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.tbea.test.testWebProject.model.dao.testTable.TestTableDao;
import com.tbea.test.testWebProject.model.dao.testTable.TestTableDaoImpl;
import com.tbea.test.testWebProject.model.entity.TestTable;

public class TestMain {

	public static void main(String[] args) {

//		TestTableDao testTableDao = new TestTableDaoImpl();
		// TestTable testTable = testTableDao.getById(1);
		// int iid = testTable.getId();
		// String name = testTable.getName();
		// System.out.println("id:" + iid);
		// System.out.println("\nname:" + name);

		// TODO Auto-generated method stub
		Statement sql;
		ResultSet rs;

		String driverName = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
		
		String dbURL = "jdbc:microsoft:sqlserver://192.168.1.26:1433;DatabaseName=zjktbeat"; // 连接服务器和数据库sample

		String userName = "gszjk"; // 默认用户名

		String userPwd = "gszjk"; // 密码

		Connection dbConn;

		try {

			Class.forName(driverName).newInstance();

			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			sql = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = sql.executeQuery("select 1");

			System.out.println("Connection Successful!"); // 如果连接成功
			// 控制台输出Connection
			// Successful!
//			while (rs.next()) {
//				System.out.println(rs.getInt(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getString(3));
//				System.out.println(rs.getString(4));
//			}
			dbConn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
