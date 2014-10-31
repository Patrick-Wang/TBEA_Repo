//这是一个处理类，有些人把它叫做BO，主要是封装去对login表的各种操作
//主要是增、删、修、查......
package com.tbea.javaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class UserBeanCl {
	private Statement stmt = null;
	private ResultSet res = null;
	private PreparedStatement ps = null;

	private int pageSize = 15;
	private int rowCount = 0;// 该值从数据库中查询
	private int pageCount = 0;// 该值是通过pageSize和pageCount

	// 返回pageCount的值
	public int getPageCount(Connection conn) throws Exception{	
			// 创建Statement
			stmt = conn.createStatement();
			// 查询数据库
			res = stmt.executeQuery("select count(*) from phoneuser");

			if (res.next()) {
				rowCount = res.getInt(1);
			}

			// 计算pageCount,这里的算法很多，可以自己设计
			if (rowCount % pageSize == 0) {
				pageCount = rowCount / pageSize;
			} else {
				pageCount = rowCount / pageSize + 1;
			}
		return pageCount;
	}
	// 得到用户需要显示的用户信息（分页）
	public ArrayList<UserBean> getUserByPage(int pageNow,Connection conn) throws Exception{
		ArrayList<UserBean> al = new ArrayList<UserBean>();	
			// 创建Statement
			stmt = conn.createStatement();

			// 显示要查询的记录
			// where not in(select * from login limit "+pageSize*(pageNow-1)+")
			res = stmt.executeQuery("SELECT TOP " +pageSize+ " * FROM phoneuser WHERE userid NOT IN ( SELECT TOP "+((pageNow - 1) * pageSize) +" userid FROM phoneuser ORDER BY userid ) ORDER BY userid;");
			
			// 开始将res封装到ArrayList
			while (res.next()) {
				UserBean userbean = new UserBean();
				userbean.setImei(res.getString(1));
				userbean.setUserid(res.getString(3));
				userbean.setMenuqx(res.getString(5));
				userbean.setCompanyqx(res.getString(6));
				userbean.setVerificationCode(res.getString(7));
				userbean.setVerificateDate(res.getString(8));
				userbean.setUsername(res.getString(9));
				// 将userbean放到al
				al.add(userbean);
			}	
		return al;
	}
	
	// 删除用户
	public boolean deleteUser(String userid,Connection conn) throws Exception{
		boolean bool = false;		
			ps = conn.prepareStatement("delete from phoneuser where userid = "+
					"'"+ userid + "';");
			int a = ps.executeUpdate();
			if (a == 1) {
				// 删除成功
				bool = true;
			}		
		return bool;
	}
	
	// 修改用户
	public boolean updateUser(String username, String password,String realname,String glygh,String pq,String quanxian,Connection conn) throws Exception{
		boolean bool = false;
			ps = conn.prepareStatement("update users set quanxian='" + quanxian + "',pq='" + pq + "',glygh='" + glygh + "',realname='" + realname + "',password='" + password + "' where username = '" + username + "'");
			int i = ps.executeUpdate();
			if (i == 1) {
				bool = true;
			}		
		return bool;
	}
}
