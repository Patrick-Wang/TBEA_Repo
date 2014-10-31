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
import com.tbea.javaBean.getData;
import com.tbea.javaBean.tools;

/**
 * Servlet implementation class beforeLoginServlet
 */
@WebServlet(name = "beforeLogin", urlPatterns = { "/beforeLogin" })
public class beforeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public beforeLoginServlet() {
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
		String IMEI =request.getParameter("IMEI");
//		IMEI = "357138050105888";
		PrintWriter out=response.getWriter();  
		String flag="false";
		getData getdata=new getData();
		try {
			//获取一个实例
			DBConnectionManager manager =  DBConnectionManager.getInstance("mobileSys");  
			//获取一个连接
			Connection conn = manager.getConnection("mobileSys");
			if(tools.isEmpty(IMEI)){
				String username="";
				username =getdata.checkIMEI(IMEI, conn);
				if(tools.isEmpty(username)){
					flag=username;
				}
			}
			out.write(flag);
			out.flush();  
			out.close();  
			manager.freeConnection("mobileSys", conn); 
		} catch (Exception e) {
			logger.error("_______________________"+new Date()+"用户登陆IMEI验证：  "+e.getMessage());			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
