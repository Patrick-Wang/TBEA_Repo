package com.tbea.datatransfer.controller.servlet.inner.ztyszkfxb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.inner.ztyszkfxb.ZTYSZKFXBService;

@Controller
@RequestMapping(value = "ZTYSZKFXB")
public class ZTYSZKFXBController {

	@Autowired
	private ZTYSZKFXBService ztyszkfxbService;

	private String view = "ztyszkfxbPage";

	private String commandName = "result";

	@RequestMapping(value = "importZTYSZKFXB.do", method = RequestMethod.GET)
	public ModelAndView importZTYSZKFXB(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = ztyszkfxbService.importZTYSZKFXB();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
