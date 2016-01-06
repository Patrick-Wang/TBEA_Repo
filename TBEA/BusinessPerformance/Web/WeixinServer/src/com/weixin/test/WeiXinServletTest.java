package com.weixin.test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
@WebServlet("/WeiXinTest")
public class WeiXinServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 private static void testEstimation(String downloadTimes)
	            throws NumberFormatException, InterruptedException {
	        int executeTimes = Integer.valueOf(downloadTimes);
	        ExecutorService threadExecutor = Executors.newFixedThreadPool(10);
	        int count = 0;
	        while (count < executeTimes) {
	            threadExecutor.execute(new HttpsRequestRunnable());
	            Thread.sleep(10);
	            ++count;
	        }
	    }
	 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeiXinServletTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			testEstimation("1");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
