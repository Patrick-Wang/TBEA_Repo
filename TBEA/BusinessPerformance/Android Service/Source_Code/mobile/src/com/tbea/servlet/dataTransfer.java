package com.tbea.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tbea.Connection.DBConnectionManager;
import com.tbea.javaBean.*;

import net.sf.json.JSONArray;


/**
 * Servlet implementation class dataTransfer
 */
@WebServlet("/dataTransfer")
public class dataTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dataTransfer() {
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
		String menuqx=request.getParameter("menuqx");
		String companyID=request.getParameter("companyID");
		String companyQX=request.getParameter("companyQX");
		String year=request.getParameter("year");
		String month=request.getParameter("month");

		String companylist2="0";
		if(companyQX!=null&&companyQX!="null"&&companyQX!=""){
			companylist2=companyQX.replace(",", ";");
		}
		getData getData=new getData();
		List<YSZKBean> YSZKList=new ArrayList<YSZKBean>();
		List<QHMXBean> QHMXList1=new ArrayList<QHMXBean>();
		List<QHMXBean> QHMXList2=new ArrayList<QHMXBean>();
		List<YDZBBean> YDZBList=new ArrayList<YDZBBean>();
		JSONArray DATAjson = new JSONArray();

		try{
			//��ȡһ��ʵ��
			DBConnectionManager manager =  DBConnectionManager.getInstance("mobileSys");  
			//��ȡһ������
			Connection conn = manager.getConnection("mobileSys"); 		
			if(menuqx!=null&&menuqx!="null"&&menuqx!=""){
				//��ȡӦ���˿���Ϣ
				if(menuqx.equals("1")){							
					YSZKList=getData.getYSZKXXInfo(companyQX,conn);
					DATAjson.addAll(YSZKList);					
				}
				//����ڻ���ϸ��Ϣ
				if(menuqx.equals("2")){										
					QHMXList1=getData.getQHMXInfo(companyID, companylist2, "1",conn);
					QHMXList2=getData.getQHMXInfo(companyID, companylist2, "2",conn);
					DATAjson.add(QHMXList1);					
					DATAjson.add(QHMXList2);
				}
				//����¶�ָ����Ϣ
				if(menuqx.equals("3")){
					int yearYDZB=0;
					int monthYDZB=0;
					if(year!=null&&year!=""){
						yearYDZB=Integer.valueOf(year);
					}
					if(month!=null&&month!=""){
						monthYDZB=Integer.valueOf(month);
					}
					YDZBList=getData.getYDZBInfo(yearYDZB, monthYDZB, companylist2, 0,conn);	
					DATAjson.addAll(YDZBList);					
				}}
				out.write(DATAjson.toString());
				out.flush();  
				out.close(); 
				manager.freeConnection("mobileSys", conn); 		
			}
		catch(Exception e){			
	          logger.error("_______________________"+new Date()+"��ݴ������  "+e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
