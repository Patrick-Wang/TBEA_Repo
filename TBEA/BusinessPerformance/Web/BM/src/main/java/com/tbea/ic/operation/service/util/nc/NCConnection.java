package com.tbea.ic.operation.service.util.nc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NCConnection {
	private final static String driverName = "oracle.jdbc.driver.OracleDriver";
	private final static String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=dm01-scan.tbea.com.cn)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=orcl)))";
	private final static String userName = "iufo";
	private final static String userPwd = "cwf5e7n9";
	private Statement statement;
	private Connection dbConn;
	public static NCConnection create(){
		Connection dbConn = null;
		try {
			Class.forName(driverName).newInstance();

			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			Statement statement = dbConn
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			return new NCConnection(dbConn, statement);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != dbConn) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				dbConn = null;
			}
		}
		return null;
	}
	
	NCConnection(Connection dbConn, Statement statement){
		this.dbConn = dbConn;
		this.statement = statement;
	}
	
	public ResultSet query(String sql){
		try {
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		};
		return null;
	}
	
	public void close(){
		try {
		if (null != statement) {
			
			statement.close();
			statement = null;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null != dbConn) {
			try {
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbConn = null;
		}
	}
	
}
