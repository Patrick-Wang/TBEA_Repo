package com.tbea.ic.operation.service.util.nc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class NCConnection {
	private final static String driverName = "oracle.jdbc.driver.OracleDriver";
	private final static String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=dm01-scan.tbea.com.cn)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=orcl)))";
	private final static String userName = "iufo";
	private final static String userPwd = "cwf5e7n9";
	private Connection dbConn;
	static Logger logger = Logger.getLogger("LOG-NC");
	public static NCConnection create(){
		Connection dbConn = null;
		try {
			Class.forName(driverName).newInstance();
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			return new NCConnection(dbConn);
		} catch (Exception e) {
			e.printStackTrace();
			if (null != dbConn) {
				try {
					dbConn.close();
				} catch (SQLException se) {
					e.printStackTrace();
				}
				
			}
		}
		return null;
	}
	
	NCConnection(Connection dbConn){
		this.dbConn = dbConn;
	}
	
	public ResultSet query(String sql){
		try {
			logger.debug(sql.replaceAll("\\s+", " ").trim());
			ResultSet rs = dbConn
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			if (logger.isDebugEnabled()){
				ResultSetMetaData m = rs.getMetaData();
				while(rs.next()){
					StringBuilder builder = new StringBuilder();
					for(int i = 1; i<= m.getColumnCount();i++)
				    {
						builder.append(rs.getObject(i) + "\t");
				    }
					logger.debug(builder.toString());
				}
				rs.beforeFirst();
			}
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		};
		return null;
	}
	
	public void close(){
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
