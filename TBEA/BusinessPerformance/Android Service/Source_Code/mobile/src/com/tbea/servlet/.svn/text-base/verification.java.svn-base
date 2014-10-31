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
 * Servlet implementation class verification
 */
@WebServlet("/verification")
public class verification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verification() {
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
		
		String verificationCode=request.getParameter("verificationCode");
		String initialPwd=request.getParameter("initialPwd");
		String IMEI=request.getParameter("IMEI");
//		verificationCode="000000";
//		initialPwd="123456";
//		IMEI ="357138050105888";
		getData getdata=new getData();
		String flag="false";
		String dateNow=tools.getNowDate();
		try {
			//获取一个实例
			DBConnectionManager manager =  DBConnectionManager.getInstance("mobileSys");  
			//获取一个连接
			Connection conn = manager.getConnection("mobileSys");
			if(tools.isEmpty(verificationCode)&&tools.isEmpty(initialPwd)&&tools.isEmpty(IMEI)){
				UserBean user=new UserBean();
				user=getdata.checkVerification(dateNow,verificationCode, conn);
				if(user!=null&&tools.isEmpty(user.getUserid())){
					if(getdata.initialUpdateUserInfo(user.getUserid(), initialPwd, IMEI, conn)){					
						if(getdata.deleteVerificationCode(IMEI, conn)){
							flag = user.getUsername();
						}
					}
				}
			}
			out.write(flag);
			out.flush();  
			out.close();  
			manager.freeConnection("mobileSys", conn); 
		} catch (Exception e) {
			logger.error("_______________________"+new Date()+"用户验证错误：  "+e.getMessage());			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
