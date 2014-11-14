package com.tbea.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class DBConnectionPool {

	private BlockingQueue<Connection> freeConnections = new LinkedBlockingQueue<Connection>();

	private int maxConn;
	private String name;

	private String driver;
	private String url;

	private String user;

	private String password;

	public DBConnectionPool(String poolname, String driver, String URL,
			String user, String password, int maxConn) {
		this.name = poolname;
		this.driver = driver;
		this.url = URL;
		this.user = user;
		this.password = password;
		this.maxConn = maxConn;
		fillPool();
	}

	public void freeConnection(Connection con) {
		try {
			this.freeConnections.put(con);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			if (freeConnections.size() < 2) {
				fillPool();
			}

			return freeConnections.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void release() {
		Connection con = null;
		while (null != (con = freeConnections.poll())){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private Connection newConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url + ";SelectMethod=Cursor;",
					user, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return con;
	}
	
	private void fillPool() {
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						freeConnections.put(DBConnectionPool.this
								.newConnection());
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
