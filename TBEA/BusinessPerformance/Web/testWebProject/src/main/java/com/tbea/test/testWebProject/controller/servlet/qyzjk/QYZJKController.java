package com.tbea.test.testWebProject.controller.servlet.qyzjk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.model.entity.QYZJK;
import com.tbea.test.testWebProject.service.qyzjk.QYZJKService;

@Controller
@RequestMapping(value = "QYZJK")
public class QYZJKController {

	@Autowired
	private QYZJKService qyzjkService;

	private String view = "qyzjkPage";

	private String commandName = "qyzjk";

	@RequestMapping(value = "GetQYZJK.do", method = RequestMethod.GET)
	public ModelAndView getTestTableById(HttpServletRequest request,
			HttpServletResponse response) {
		QYZJK qyzjk = qyzjkService.getQYZJKById(1);
		int id = qyzjk.getId();
		String qybh = qyzjk.getQYBH();
		String qymc = qyzjk.getQYMC();
		System.out.println("id:" + id);
		System.out.println("qybh:" + qybh);
		System.out.println("qymc:" + qymc);

		return new ModelAndView(view, commandName, qyzjk);
	}

}
