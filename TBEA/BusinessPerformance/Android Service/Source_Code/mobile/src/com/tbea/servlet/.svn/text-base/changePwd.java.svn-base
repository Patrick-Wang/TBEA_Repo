package com.tbea.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tbea.Connection.DBConnectionManager;
import com.tbea.javaBean.UserBean;
import com.tbea.javaBean.getData;
import com.tbea.javaBean.tools;

/**
 * Servlet implementation class changePwd
 */
@WebServlet("/changePwd")
public class changePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changePwd() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(dataTransfer.class.getName());
		response.setContentType("text/html");  
		response.setCharacterEncoding("utf-8");  
		request.setCharacterEncoding("utf-8");  
		PrintWriter out=response.getWriter();  

		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String newpwd=request.getParameter("newpwd");
//		username ="357138050105888";
//		password = "123";
//		newpwd = "www";
		getData getData=new getData();
		//返回结果 0：修改成功 1：原始密码错误 2：修改失败
		String result="2";
		try{
			//获取一个实例
			DBConnectionManager manager =  DBConnectionManager.getInstance("mobileSys");  
			//获取一个连接
			Connection conn = manager.getConnection("mobileSys");
			if(tools.isEmpty(username)&&tools.isEmpty(password)){
			UserBean userbean=new UserBean();
			userbean=getData.getUserInfo(username,password,conn);
			if(userbean!=null&&tools.isEmpty(userbean.getUserid())&&tools.isEmpty(userbean.getPwd())){
				if(tools.isEmpty(newpwd)){
					if(getData.changeUserPwd(newpwd,userbean,conn)){
						result="0";
					}else{
						result="2";
					}
				}else{
					result="2";
				}
			}else{
				result="1";
			}
			}			
			out.write(result);
			out.flush();  
			out.close();  
			manager.freeConnection("mobileSys", conn); 
			}catch(Exception e){
				logger.error("_______________________"+new Date()+"用户修改密码错误：  "+e.getMessage());
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
