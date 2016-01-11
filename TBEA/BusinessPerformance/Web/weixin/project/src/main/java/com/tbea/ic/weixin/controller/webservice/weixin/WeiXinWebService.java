package com.tbea.ic.weixin.controller.webservice.weixin;

import com.tbea.ic.weixin.service.weixin.WeiXinService;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@WebService
public class WeiXinWebService {
	@Autowired
	WeiXinService weiXinService;

	
	public String getResult(){
		return "";
	}
}
