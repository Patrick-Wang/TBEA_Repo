package com.tbea.datatransfer.controller.servlet.fkfs.xl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.fkfs.xl.FKFSXLFDWTransferService;
import com.tbea.datatransfer.service.local.fkfs.xl.FKFSXLGWTransferService;
import com.tbea.datatransfer.service.local.fkfs.xl.FKFSXLNWTransferService;

@Controller
@RequestMapping(value = "fkfsxlTransfer")
public class FKFSXLTransferController {

	@Autowired
	private FKFSXLFDWTransferService fkfsxlfdwTransferService;

	@Autowired
	private FKFSXLGWTransferService fkfsxlgwTransferService;

	@Autowired
	private FKFSXLNWTransferService fkfsxlnwTransferService;

	private String view = "fkfsxlTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "fkfsxlTransfer.do", method = RequestMethod.GET)
	public ModelAndView fkfsxlTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean resultxlfdw = fkfsxlfdwTransferService.transferFKFSXLFDW();
		boolean resultxlgw = fkfsxlgwTransferService.transferFKFSXLGW();
		boolean resultxlnw = fkfsxlnwTransferService.transferFKFSXLNW();
		boolean result = resultxlfdw && resultxlgw && resultxlnw;
		return new ModelAndView(view, commandName, result);
	}

}
