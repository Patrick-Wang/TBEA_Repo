package com.tbea.Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * ���ڲ��ඨ����һ�����ӳ�.���ܹ�����Ҫ�󴴽�������,ֱ��Ԥ�������������Ϊֹ.
 *      
 * �ڷ������Ӹ��ͻ�����֮ǰ,���ܹ���֤���ӵ���Ч��. 
 *  *   */
public class DBConnectionPool {
	/**    
	 * * ��˵�е����ӳ�   
	 * */ 
	private List<Connection> freeConnections = new ArrayList<Connection>(); 
	private Connection con = null; 
	// ʹ�õ�������   
	private int connect = 0; 
	// ������� 
	private int maxConn; 
	// ���ӳ����� 
	private String name; 
	// ����  
	private String driver; 
	// ���ݿ����ӵ�ַ 
	private String url;
	// �û���
	private String user; 
	// ���� 
	private String password;
	  
	/**    * �вι��촴�����ӳ�   
	 * *     * @param driver   
	 * * @param name   
	 * * @param URL   
	 * * @param user   
	 * * @param password   
	 * * @param maxConn   
	 * */  
	 public DBConnectionPool(String poolname, String driver, String URL,String user, String password, int maxConn) { 		
		 this.name=poolname;
		 this.driver = driver; 
		 this.url = URL;  
		 this.user = user;  
		 this.password = password;  
		 this.maxConn = maxConn;   
		 poolInfo();   }


	 /**    
	  * * ��ʾ׼���������ӳص���Ϣ   
	  * */   
	private void poolInfo() { 
		Connection conn = this.newConnection();  
		freeConnections.add(conn);   
		for (int i = 0; i < this.maxConn - 1; i++) {   
			Connection freeConn = conn;    freeConnections.add(freeConn);   
		}   
	}  
	/**    
	 * * ���꣬�ͷ�����   
	 * * @param con    
	 * *�ͷ�һ������  
	 */
	 public synchronized void freeConnection(Connection con) {  
		 this.freeConnections.add(con);  
		 this.connect--;
	 } 
	 /**    
	  * * �����ӳ��л�ȡһ����������   *     
	  * * ���޷��ӳ��л�ȡ��������ʱ,�´���һ������   *     
	  * * @return �������Ӷ���   */  
	 public synchronized Connection getConnection() {
		 if (this.freeConnections.size() > 0) { 
			 con = this.freeConnections.get(0);    
		/**      
		 * * ���ڳ���ȡ��һ�����Ӻ�,ɾ��������   
		 * */     
		this.freeConnections.remove(0);   
		 /** 
		  ** ��ȡ��������Ϊnullʱ,�ݹ�����Լ�,ֱ�����һ����������Ϊֹ     
		  **/  
		if (con == null){
			con = getConnection();    
		} else {
			con = newConnection(); 
		}
		}    
		if (this.maxConn == 0 || this.maxConn < this.connect) {  
		  /**
		   ** �ȴ� �����������ʱ     
		   **/   
		  con = null;    
		  }   
		if (con != null) {    
			this.connect++;   }    
		return con;   }   
	 /**    
	  ** �ͷ�ȫ������   *    
	  **/  
	 public synchronized void release() { 
		 Iterator<Connection> allConns = this.freeConnections.iterator();   
		 while (allConns.hasNext()) {   
			 Connection conn = (Connection) allConns.next();   
			 try {     
				 if (null != conn) {    
					 conn.close();}    
				 conn = null;     
			 } catch (SQLException e) { 
				 e.printStackTrace();
				 }    
		 }    
		 this.freeConnections.clear();
		 } 
	  /**
	   ** ����һ�����ݿ����Ӷ���   *     
	   ** @return �������ݿ�����  
	   **/
	  private Connection newConnection() {  
		  try {
			  Class.forName(driver); 
		  } catch (ClassNotFoundException e2) {   
			  e2.printStackTrace();    }   
		  try {
			  con = DriverManager.getConnection(url, user, password);   
		  } catch (SQLException e1) {   
			  e1.printStackTrace();
			  System.exit(0);    }  
		  return con;   
	}  
}
	 
