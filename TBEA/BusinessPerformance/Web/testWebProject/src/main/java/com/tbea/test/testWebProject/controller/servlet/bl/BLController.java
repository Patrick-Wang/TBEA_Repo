package com.tbea.test.testWebProject.controller.servlet.bl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.model.entity.BL;
import com.tbea.test.testWebProject.service.bl.BLService;

@Controller
@RequestMapping(value = "bl")
public class BLController {

	@Autowired
	private BLService blService;

	private String view = "blPage";

	private String commandName = "bl";

	@RequestMapping(value = "GetBL.do", method = RequestMethod.GET)
	public ModelAndView getBLById(HttpServletRequest request,
			HttpServletResponse response) {
		BL bl = blService.getBLById(327781);
		System.out.println(bl.getId());
		System.out.println(bl.getGxrq());
		System.out.println(bl.getBlbh());
		System.out.println(bl.getHtbh());

		return new ModelAndView(view, commandName, bl);
	}

}
