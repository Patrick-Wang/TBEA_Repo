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
			//��ȡһ��ʵ��
			DBConnectionManager manager =  DBConnectionManager.getInstance("mobileSys");  
			//��ȡһ������
			Connection conn = manager.getConnection("mobileSys");					
			String verificationDate=tools.getNowDate();		
				randomNum=tools.getRandomString(6);
				String result=getdata.findVerificationCode(randomNum, conn);	
				while(tools.isEmpty(result)){								
					randomNum=tools.getRandomString(6);
					result=getdata.findVerificationCode(randomNum, conn);	
				}
				if(getdata.insertUser(username,userid, menuqx, companyqx, randomNum, verificationDate, conn)){
					output="������"+username+",  �ֻ��ţ�"+userid+",  ��֤�룺"+randomNum;
					user=username;
					yzm=randomNum;
				}	
				manager.freeConnection("mobileSys", conn); }
			}
			if(output.equals("false")){
				out.write("�Բ���ע��ʧ�ܣ�");
			}else{
				out.write("<div style='margin-left:100px;'> �𾴵��û�: "+user+",���ã�");
				out.write("<p>");
				out.write("TBEA��Ӫ�����Ż����ص�ַ���û��ֲ��ַ���£�");
				out.write("<p>");
				out.write("����(3G)�û���<br>");
				out.write("http://218.84.134.160:8081/mobile/Guide.html");
				out.write("<p>");
				out.write("����(��˾wifi)�û���<br>");
				out.write("http://192.168.7.22/mobile/Guide.html");
				out.write("<p>");
				out.write("�״ε�¼��Ҫʹ����֤��ע�Ტ�������룺");
				out.write("<p>");
				out.write("����ע����Ϊ�� "+yzm);
				out.write("<p>");
				out.write("����ע��ҳ��ʹ�ô���֤�룬��Ч��24Сʱ��");
				out.write("<p></div>");			
			}			
			out.flush();  
			out.close(); 				
		}catch(Exception e){
			logger.error("_______________________"+new Date()+"�û�ע��ʧ�ܣ�  "+e.getMessage());			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
