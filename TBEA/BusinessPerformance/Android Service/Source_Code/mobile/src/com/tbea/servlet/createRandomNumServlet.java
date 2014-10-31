package com.tbea.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.util.Properties;

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
 * Servlet implementation class createRandomNumServlet
 */
@WebServlet(name = "createRandomNum", urlPatterns = { "/createRandomNum" })
public class createRandomNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createRandomNumServlet() {
        super();       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(dataTransfer.class.getName());
		response.setContentType("text/html");  
		response.setCharacterEncoding("gbk");  
		request.setCharacterEncoding("gbk");
		PrintWriter out=response.getWriter();  
		String pwd=request.getParameter("pwd");
		String username=request.getParameter("username");		
		String userid=request.getParameter("userid");
		String menuqx=request.getParameter("menuqx");
		String companyqx=request.getParameter("companyqx");
		String randomNum="";
		getData getdata=new getData();
		String output="false";
		String user="";
		String yzm="";
		try{
			Properties prop = new Properties(); 
			String password="";
			prop.load(new FileInputStream(getClass().getClassLoader().getResource("/").getPath() + "..\\" + "config.properties"));   
			password=prop.getProperty("password");   	
			if(pwd.equals(password)){
				if(tools.isEmpty(userid)&&tools.isEmpty(menuqx)&&tools.isEmpty(companyqx)){
			//获取一个实例
			DBConnectionManager manager =  DBConnectionManager.getInstance("mobileSys");  
			//获取一个连接
			Connection conn = manager.getConnection("mobileSys");					
			String verificationDate=tools.getNowDate();		
				randomNum=tools.getRandomString(6);
				String result=getdata.findVerificationCode(randomNum, conn);	
				while(tools.isEmpty(result)){								
					randomNum=tools.getRandomString(6);
					result=getdata.findVerificationCode(randomNum, conn);	
				}
				if(getdata.insertUser(username,userid, menuqx, companyqx, randomNum, verificationDate, conn)){
					output="姓名："+username+",  手机号："+userid+",  验证码："+randomNum;
					user=username;
					yzm=randomNum;
				}	
				manager.freeConnection("mobileSys", conn); }
			}
			if(output.equals("false")){
				out.write("对不起注册失败！");
			}else{
				out.write("<div style='margin-left:100px;'> 尊敬的用户: "+user+",您好！");
				out.write("<p>");
				out.write("TBEA经营管理门户下载地址及用户手册地址如下：");
				out.write("<p>");
				out.write("外网(3G)用户：<br>");
				out.write("http://218.84.134.160:8081/mobile/Guide.html");
				out.write("<p>");
				out.write("内网(公司wifi)用户：<br>");
				out.write("http://192.168.7.22/mobile/Guide.html");
				out.write("<p>");
				out.write("首次登录需要使用验证码注册并设置密码：");
				out.write("<p>");
				out.write("您的注册码为： "+yzm);
				out.write("<p>");
				out.write("请在注册页面使用此验证码，有效期24小时。");
				out.write("<p></div>");			
			}			
			out.flush();  
			out.close(); 				
		}catch(Exception e){
			logger.error("_______________________"+new Date()+"用户注册失败：  "+e.getMessage());			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
