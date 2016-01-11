package com.tbea.ic.weixin.controller.servlet.weixin;

import com.tbea.ic.weixin.service.weixin.WeiXinService;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.weixin.mp.aes.AesException;
import com.tbea.ic.auth.AuthException;
import com.tbea.ic.auth.Connection;
import com.tbea.ic.auth.Verifier;
import com.tbea.ic.message.sender.Messager;

@Controller
@RequestMapping(value = "weixin")
public class WeiXinServlet {
	
	@Autowired
	WeiXinService weiXinService;

		

	@RequestMapping(value = "verify.do")
	public void login(HttpServletRequest request,
			HttpServletResponse response) throws AesException, IOException {
		Verifier verifier = new Verifier("nH5", "wx40b71464a42adcf3", "fDYqXp5YJizTlMTLKu9b2L8RMH8jUaB1mivzfzV9As0");
		verifier.verify(request, response);
	}
	
	@RequestMapping(value = "test.do")
	public void test(HttpServletRequest request,
			HttpServletResponse response) throws AuthException, IOException  {
		Connection.getInstance().open("wx40b71464a42adcf3", "BW-Tuxi3fYjgjOOQv2d9iR_7Cz0mRSDVfVaHtjE-2Z1GaHQQIwV0awLsO17zPnPy");
		Messager msger = new Messager(25);
		msger.text("Hello!").toUser("sunfuda").send();
	}
	
	@RequestMapping(value = "transfer.do")
	public void transfer(HttpServletRequest request,
			HttpServletResponse response) throws AesException, IOException {
		weiXinService.transferOrg();
		weiXinService.transferPersion();
	}
}
