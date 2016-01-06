package com.weixin.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * Servlet implementation class WeiXinServlet
 */
@WebServlet("/WeiXinVerify1")
public class WeiXinServletProxy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeiXinServletProxy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String redirUrl = "http://10.1.4.107:8080" + request.getRequestURI() + "Stub?" + request.getQueryString();
		System.out.println(redirUrl);
		StringBuffer sb = new StringBuffer(1024 * 30);
		String val = "";
		try {
			URL url = new URL(redirUrl);
			URLConnection urlConn = (URLConnection) (url.openConnection());
			urlConn.setDoOutput(true); 
			InputStream is = urlConn.getInputStream();
			byte[] b = new byte[256];
			int i = 0;
			while ((i = is.read(b)) > 0) {
				sb.append(new String(b, 0, i, "utf-8"));
			}
			is.close();
			val = sb.toString();
			System.out.println("resp : " + val);
			System.out.println("size : " + val.length());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().print(val);
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
