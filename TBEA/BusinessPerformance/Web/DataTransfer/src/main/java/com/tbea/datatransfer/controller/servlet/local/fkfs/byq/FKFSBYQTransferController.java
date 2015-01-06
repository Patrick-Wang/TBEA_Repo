package com.tbea.datatransfer.controller.servlet.local.fkfs.byq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.fkfs.byq.FKFSBYQFDWTransferService;
import com.tbea.datatransfer.service.local.fkfs.byq.FKFSBYQGWTransferService;
import com.tbea.datatransfer.service.local.fkfs.byq.FKFSBYQNWTransferService;

@Controller
@RequestMapping(value = "fkfsbyqTransfer")
public class FKFSBYQTransferController {

	@Autowired
	private FKFSBYQFDWTransferService fkfsbyqfdwTransferService;

	@Autowired
	private FKFSBYQGWTransferService fkfsbyqgwTransferService;

	@Autowired
	private FKFSBYQNWTransferService fkfsbyqnwTransferService;

	private String view = "fkfsbyqTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "fkfsbyqTransfer.do", method = RequestMethod.GET)
	public ModelAndView fkfsbyqTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean resultbyqfdw = fkfsbyqfdwTransferService.transferFKFSBYQFDW();
		boolean resultbyqgw = fkfsbyqgwTransferService.transferFKFSBYQGW();
		boolean resultbyqnw = fkfsbyqnwTransferService.transferFKFSBYQNW();
		boolean result = resultbyqfdw && resultbyqgw && resultbyqnw;
		return new ModelAndView(view, commandName, result);
	}

}
