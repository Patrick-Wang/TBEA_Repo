package com.tbea.Connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
	 /**
	  ** 连接池的管理类,负责读取配置连接池的文件,并创建连接池  
	  ** 从池中获取,释放连接  
	  ** @author svse  
	  **/  
	public class DBConnectionManager {
		private String jdbcDriver = ""; // 数据库驱动
	    private String dbUrl = ""; // 数据 URL
	    private String dbUsername = ""; // 数据库用户名
	    private String dbPassword = ""; // 数据库用户密码	   
	    private int maxConnections = 50; // 连接池最大的大小	   
		 /**
		  ** 唯一数据库连接池管理实例类   
		  ** 使用单例模式创建   
		  **/   
		private static DBConnectionManager instance;  
		/**
		 ** 连接池的集合
		 **/   
		private Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>();
	    /**
	     ** 得到唯一实例管理类  
	     ** @return 一个连接池的管理类
	     **/   
		public static synchronized DBConnectionManager getInstance(String name) {
		    if (instance == null) {
		    	instance = new DBConnectionManager(name);
		    }
		    return instance;
	}
	   /** 
	    ** 只允许内部实例化管理类   
	    **/
	   private DBConnectionManager(String name) {
	   this.init(name);
	   }
	   /**
	    ** 加载驱动程序   
	    **/
	   private void init(String name) {
		   Properties prop = new Properties();  
		   String jdbcDriver="";
		   String dbUrl="";
		   String dbUsername="";
		   String dbPassword="";
	       String maxConnections="50";	
	   	try {   
				prop.load(new FileInputStream(getClass().getClassLoader().getResource("/").getPath() + "..\\" + "config.properties"));   
				jdbcDriver=prop.getProperty("jdbcDriver");  
				dbUrl=prop.getProperty("dbUrl"); 
				dbUsername=prop.getProperty("dbUsername");
				dbPassword=prop.getProperty("dbPassword");
				maxConnections=prop.getProperty("maxConnections");
			   } catch(IOException e) {   
			       e.printStackTrace(); 	      
			     }  
			this.jdbcDriver = jdbcDriver;
			this.dbUrl = dbUrl;
			this.dbUsername = dbUsername;
			this.dbPassword = dbPassword;

			this.maxConnections = Integer.valueOf(maxConnections);	   
		   DBConnectionPool pool = new DBConnectionPool(name,this.jdbcDriver, this.dbUrl,  this.dbUsername,
				   this.dbPassword, this.maxConnections);
		   pools.put(name,pool);
	   }
	   /**
	    ** 根据连接池的名字得到一个连接  
	    ** @param name    
	    ** 连接池的名称  
	    ** @return 池中的一个可用连接  
	    **/
	   public Connection getConnection(String name) {
		   DBConnectionPool pool = null;
		   Connection con = null;
		   pool = pools.get(name); 
		   try {
	    	    con = pool.getConnection();
	    	   } catch (Exception e) {
	    	     e.printStackTrace();
	    	   }
		   return con;
		}
	   /**   
	    ** 释放一个连接  
	    ** @param name 连接池的名称     
	    ** @param con  将要是释放的连接对象     
	    **/
	  public synchronized void freeConnection(String name, Connection con) {
		  DBConnectionPool pool = pools.get(name);
		  // 根据连接池名称得到连接池
		  if (pool != null)
			  pool.freeConnection(con);
		  // 释放连接   
		  }
	  /**
	   ** 释放所有连接   
	   **/
	  public synchronized void release() {
		  Enumeration<DBConnectionPool> allpools = pools.elements();
		  while (allpools.hasMoreElements()) {
			  DBConnectionPool pool = allpools.nextElement();
			  if (pool != null) 
				  pool.release();
		  }
		  pools.clear();
	  }
} 
    	    