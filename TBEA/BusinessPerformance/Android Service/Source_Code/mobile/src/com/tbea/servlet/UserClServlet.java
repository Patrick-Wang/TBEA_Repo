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
		// ��ñ�ʶ��
		String flag = request.getParameter("flag");
		// ����UserBeanCl�ķ���
		UserBeanCl userbeancl = new UserBeanCl();
		try {
		//��ȡһ��ʵ��
		DBConnectionManager manager =  DBConnectionManager.getInstance("mobileSys");  
		//��ȡһ������
		Connection conn = manager.getConnection("mobileSys");
		if (flag.equals("paging")) {
			// �õ��û�ϣ����ʾ��pageNow
			String s_pageNow = request.getParameter("pageNow");
		
				int pageNow = Integer.parseInt(s_pageNow);

				// ����ת��welcome.jspҳ��ʱ��׼����welcome.jsp��Ҫ��ʾ������
				ArrayList<UserBean> al = userbeancl.getUserByPage(pageNow,conn);
				int pageCount = userbeancl.getPageCount(conn);
				// ��al,pageCount,pageNow����request��
				request.setAttribute("result", al);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageNow", pageNow);

				// ������ת��welcome.jsp
				request.getRequestDispatcher("usersList.jsp").forward(request,
						response);			
		}		
		// ɾ���û�
		else if (flag.equals("delete")) {

			String userid = request.getParameter("userid");
			if (userbeancl.deleteUser(userid,conn)) {
				// ɾ���ɹ�
				request.getRequestDispatcher("UserClServlet?pageNow=1&flag=paging").forward(request,
						response);
			} else {
				// ɾ��ʧ��
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}

		}	
		
		//�޸��û�
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
				//�޸ĳɹ�
				request.getRequestDispatcher("UserClServlet?pageNow=1&flag=paging").forward(request,response);
			}else{
				//���ʧ��
				request.getRequestDispatcher("main/error.jsp").forward(request, response);
			}
		}
		
		//ע���û�
		else if(flag.equals("cancleUser")){
			String username = request.getParameter("username");			
			if(userbeancl.deleteUser(username,conn)){
				//ע���ɹ�
				request.getRequestDispatcher("main/success.jsp").forward(request, response);
			}else{
				//ע��ʧ��
				request.getRequestDispatcher("main/error.jsp").forward(request, response);
			}
		}
		}catch(Exception e){
			logger.error("_______________________"+new Date()+"�û��������  "+e.getMessage());			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
