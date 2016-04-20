package com.tbea.ic.operation.controller.servlet.dzwzgb;

import com.tbea.ic.operation.service.dzwzgb.DzwzgbServiceImpl;
import com.tbea.ic.operation.service.dzwzgb.DzwzgbService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "dzwzgb")
public class DzwzgbServlet {
	@Resource(name=DzwzgbServiceImpl.NAME)
	DzwzgbService dzwzgbService;

	
	
}
