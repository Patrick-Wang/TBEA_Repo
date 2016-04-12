package com.tbea.ic.operation.controller.servlet.sbdddcbjpcqk;

import com.tbea.ic.operation.service.sbdddcbjpcqk.SbdddcbjpcqkServiceImpl;
import com.tbea.ic.operation.service.sbdddcbjpcqk.SbdddcbjpcqkService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "sbdddcbjpcqk")
public class SbdddcbjpcqkServlet {
	@Resource(name=SbdddcbjpcqkServiceImpl.NAME)
	SbdddcbjpcqkService sbdddcbjpcqkService;

	
	
}
