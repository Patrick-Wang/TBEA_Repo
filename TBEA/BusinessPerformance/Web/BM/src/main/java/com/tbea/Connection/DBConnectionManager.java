package com.tbea.Connection;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
	 /**
	  ** ���ӳصĹ�����,�����ȡ�������ӳص��ļ�,���������ӳ�  
	  ** �ӳ��л�ȡ,�ͷ�����  
	  ** @author svse  
	  **/  
	public class DBConnectionManager {
		private String jdbcDriver = ""; // ���ݿ�����
	    private String dbUrl = ""; // ���� URL
	    private String dbUsername = ""; // ���ݿ��û���
	    private String dbPassword = ""; // ���ݿ��û�����	   
	    private int maxConnections = 50; // ���ӳ����Ĵ�С	   
		 /**
		  ** Ψһ���ݿ����ӳع���ʵ����   
		  ** ʹ�õ���ģʽ����   
		  **/   
		private static DBConnectionManager instance;  
		/**
		 ** ���ӳصļ���
		 **/   
		private Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>();
	    /**
	     ** �õ�Ψһʵ��������  
	     ** @return һ�����ӳصĹ�����
	     **/   
		public static synchronized DBConnectionManager getInstance(String name) {
		    if (instance == null) {
		    	instance = new DBConnectionManager(name);
		    }
		    return instance;
	}
	   /** 
	    ** ֻ�����ڲ�ʵ����������   
	    **/
	   private DBConnectionManager(String name) {
	   this.init(name);
	   }
	   /**
	    ** ������������   
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
	    ** �������ӳص����ֵõ�һ������  
	    ** @param name    
	    ** ���ӳص�����  
	    ** @return ���е�һ����������  
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
	    ** �ͷ�һ������  
	    ** @param name ���ӳص�����     
	    ** @param con  ��Ҫ���ͷŵ����Ӷ���     
	    **/
	  public synchronized void freeConnection(String name, Connection con) {
		  DBConnectionPool pool = pools.get(name);
		  // �������ӳ����Ƶõ����ӳ�
		  if (pool != null)
			  pool.freeConnection(con);
		  // �ͷ�����   
		  }
	  /**
	   ** �ͷ���������   
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
    	    