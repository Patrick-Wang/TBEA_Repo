package com.tbea.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tbea.Connection.DBConnectionManager;
import com.tbea.javaBean.UserBean;
import com.tbea.javaBean.UserBeanCl;

/**
 * Servlet implementation class UserClServlet
 */
@WebServlet("/UserClServlet")
public class UserClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserClServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		Logger logger = Logger.getLogger(dataTransfer.class.getName());
		// 获得标识符
		String flag = request.getParameter("flag");
		// 调用UserBeanCl的方法
		UserBeanCl userbeancl = new UserBeanCl();
		try {
		//获取一个实例
		DBConnectionManager manager =  DBConnectionManager.getInstance("mobileSys");  
		//获取一个连接
		Connection conn = manager.getConnection("mobileSys");
		if (flag.equals("paging")) {
			// 得到用户希望显示的pageNow
			String s_pageNow = request.getParameter("pageNow");
		
				int pageNow = Integer.parseInt(s_pageNow);

				// 在跳转到welcome.jsp页面时，准备好welcome.jsp需要显示的数据
				ArrayList<UserBean> al = userbeancl.getUserByPage(pageNow,conn);
				int pageCount = userbeancl.getPageCount(conn);
				// 将al,pageCount,pageNow放入request中
				request.setAttribute("result", al);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageNow", pageNow);

				// 重新跳转回welcome.jsp
				request.getRequestDispatcher("usersList.jsp").forward(request,
						response);			
		}		
		// 删除用户
		else if (flag.equals("delete")) {

			String userid = request.getParameter("userid");
			if (userbeancl.deleteUser(userid,conn)) {
				// 删除成功
				request.getRequestDispatcher("UserClServlet?pageNow=1&flag=paging").forward(request,
						response);
			} else {
				// 删除失败
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}

		}	
		
		//修改用户
		else if(flag.equals("updateUser")){
			String username = request.getParameter("username");			
			String password = request.getParameter("password");
			String realname = request.getParameter("realname");
			realname=new String(realname.getBytes("ISO8859_1"),"gb2312");
			String glygh = request.getParameter("glygh");
			String pq = request.getParameter("pq");
			pq=new String(pq.getBytes("ISO8859_1"),"gb2312");
			String quanxian = request.getParameter("quanxian");						
			if(userbeancl.updateUser(username, password,realname,glygh,pq,quanxian,conn)){
				//修改成功
				request.getRequestDispatcher("UserClServlet?pageNow=1&flag=paging").forward(request,response);
			}else{
				//添加失败
				request.getRequestDispatcher("main/error.jsp").forward(request, response);
			}
		}
		
		//注销用户
		else if(flag.equals("cancleUser")){
			String username = request.getParameter("username");			
			if(userbeancl.deleteUser(username,conn)){
				//注销成功
				request.getRequestDispatcher("main/success.jsp").forward(request, response);
			}else{
				//注销失败
				request.getRequestDispatcher("main/error.jsp").forward(request, response);
			}
		}
		}catch(Exception e){
			logger.error("_______________________"+new Date()+"用户管理错误：  "+e.getMessage());			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
