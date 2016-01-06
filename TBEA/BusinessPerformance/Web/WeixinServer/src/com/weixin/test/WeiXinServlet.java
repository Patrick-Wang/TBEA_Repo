package com.weixin.test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

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
@WebServlet("/WeiXinVerify")
public class WeiXinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeiXinServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI() + "?" + request.getQueryString();
		System.out.println(url);
		String sToken = "nH5";
		String sCorpID = "wx40b71464a42adcf3";
		String sEncodingAESKey = "fDYqXp5YJizTlMTLKu9b2L8RMH8jUaB1mivzfzV9As0";
		WXBizMsgCrypt wxcpt = null;
		try {
			wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String sVerifyMsgSig = HttpUtils.ParseUrl("msg_signature");
		String sVerifyMsgSig = request.getParameter("msg_signature");
		// String sVerifyTimeStamp = HttpUtils.ParseUrl("timestamp");
		String sVerifyTimeStamp = request.getParameter("timestamp");
		// String sVerifyNonce = HttpUtils.ParseUrl("nonce");
		String sVerifyNonce = request.getParameter("nonce");
		// String sVerifyEchoStr = HttpUtils.ParseUrl("echostr");
		String sVerifyEchoStr = request.getParameter("echostr");
		String sEchoStr = "test"; // 需要返回的明文
		try {
			sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
					sVerifyNonce, sVerifyEchoStr);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// response.getWriter().write(sEchoStr);
		System.out.println(sEchoStr);
		response.getOutputStream().write(sEchoStr.getBytes("utf-8"));
		response.getOutputStream().close();
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
