package com.tbea.datatransfer.controller.servlet.local.ztyszkfxb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.ztyszkfxb.ZTYSZKFXBTransferService;

@Controller
@RequestMapping(value = "ztyszkfxbTransfer")
public class ZTYSZKFXBTransferController {

	@Autowired
	private ZTYSZKFXBTransferService ztyszkfxbTransferService;

	private String view = "ztyszkfxbTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "ztyszkfxbTransfer.do", method = RequestMethod.GET)
	public ModelAndView ztyszkfxbTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = ztyszkfxbTransferService.transferZTYSZKFXB();
		return new ModelAndView(view, commandName, result);
	}

}
