package com.tbea.lib.ep;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class get
 */
@WebServlet("/get")
public class get extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SignTest sign=new SignTest();
		encryptLarger encrypt=new encryptLarger();
		String f="";
		try {
			Key key=sign.getPublicKey("D:\\101.key",null,null,null);
			byte[] data = "{\"eid\":\"101\",\"begin\":0,\"count\":1}".getBytes(); 
			byte[] d=encrypt.encryptLarger(data,key);
			f=sign.getBase64(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String sr= postdata.sendPost("http://xt.tbea.com:8060/openaccess/input/person/getall", f);
        System.out.println(sr);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
