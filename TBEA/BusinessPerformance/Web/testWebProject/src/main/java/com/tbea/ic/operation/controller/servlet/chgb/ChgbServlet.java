package com.tbea.ic.operation.controller.servlet.chgb;

import com.tbea.ic.operation.service.chgb.ChgbServiceImpl;
import com.tbea.ic.operation.service.chgb.ChgbService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "chgb")
public class ChgbServlet {
	@Resource(name=ChgbServiceImpl.NAME)
	ChgbService chgbService;

	
	
}
