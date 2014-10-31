package com.tbea.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class versionJudgmentServlet
 */
@WebServlet("/versionJudgmentServlet")
public class versionJudgmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public versionJudgmentServlet() {
        super();   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		Logger logger = Logger.getLogger(versionJudgmentServlet.class.getName());
		response.setContentType("text/html");  
		response.setCharacterEncoding("utf-8");  
		request.setCharacterEncoding("utf-8");  
		PrintWriter out=response.getWriter(); 
		try{
		String version=request.getParameter("version");
		Properties prop = new Properties(); 
		String softwareVersion="";
		String softwareName ="";
		prop.load(new FileInputStream(getClass().getClassLoader().getResource("/").getPath() + "..\\" + "config.properties"));   
		softwareVersion=prop.getProperty("softwareVersion");  
		softwareName=prop.getProperty("softwareName");  	
		String url="null";
		if(version!=null&&version!=""&&softwareVersion!=null&&softwareVersion!=""){
			if(new BigDecimal(version).compareTo(new BigDecimal(softwareVersion))<0){
				url="/mobile/softwareVersion/"+softwareName;
			}
		}
		out.write(url);
		out.flush();  
		out.close();  
		}catch(Exception e){
			logger.error(" _______________________"+new Date()+"°æ±¾¸üÐÂ´íÎó£º  "+e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
