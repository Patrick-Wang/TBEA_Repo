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

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.tbea.Connection.DBConnectionManager;
import com.tbea.javaBean.UserBean;
import com.tbea.javaBean.getData;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
    }

	/**
	 * @return 
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
//		username="357138050105888";
//		password="123";
		getData getData=new getData();
		try{
			//获取一个实例
			DBConnectionManager manager =  DBConnectionManager.getInstance("mobileSys");  
			//获取一个连接
			Connection conn = manager.getConnection("mobileSys"); 
			UserBean userbean=new UserBean();
			JSONObject USERjson=new JSONObject();
			if(username==null||username=="null"){
				username="";
			}
			if(password==null||password=="null"){
				password="";
			}
			userbean=getData.getUserInfo(username,password,conn);
			//判断用户权限
			if(userbean!=null&&userbean.getUserid()!=null&&!userbean.getUserid().equals("")){
				//根据公司ID取得用户的公司权限				
				String compID=userbean.getCompanyID();
				int[] companies = new int[21];
				int[] qxlist = new int[21];
				qxlist[0]= 4 ;
				qxlist[1]= 5 ;
				qxlist[2]= 6 ;
				qxlist[3]= 7 ;
				qxlist[4]= 8 ;
				qxlist[5]= 9 ;
				qxlist[6]= 10 ;
				qxlist[7]= 11 ;
				qxlist[8]= 23 ;
				qxlist[9]= 25 ;
				qxlist[10]= 27 ;
				qxlist[11]= 29 ;
				qxlist[12]= 30 ;
				qxlist[13]= 66 ;
				qxlist[14]= 67 ;
				qxlist[15]= 68 ;
				qxlist[16]= 69 ;
				qxlist[17]= 70 ;
				qxlist[18]= 74 ;
				qxlist[19]= 120 ;
				qxlist[20]= 122 ;
				for(int i=0;i<qxlist.length;i++){
					if(getData.getCompanyQx(compID,qxlist[i],conn)==1){
						companies[i]=qxlist[i];
					}else{
						companies[i]=-1;
					}
				}
				String companylist="";
				for(int j=0;j<companies.length;j++){	
						if(companies[j]!=-1){
						companylist+=companies[j]+",";	}									
				}
				if(companylist.length()>0){
				companylist=companylist.substring(0, companylist.length()-1);}
				userbean.setCompanyqx(companylist);
				userbean.setLoginFlag(true);				
				USERjson=USERjson.fromObject(userbean);
				}
			else{
				userbean.setLoginFlag(false);
				USERjson=USERjson.fromObject(userbean);
			}
			out.write(USERjson.toString());
			out.flush();  
			out.close();  
			//释放连接
			manager.freeConnection("mobileSys", conn); 
			}catch(Exception e){
				logger.error("_______________________"+new Date()+"用户登录错误：  "+e.getMessage());
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
