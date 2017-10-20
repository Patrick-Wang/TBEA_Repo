package com.tbea.erp.report.controller.servlet.report;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.erp.report.service.report.ReportService;
import com.tbea.erp.report.service.report.ReportServiceImpl;
import com.util.tools.DataNode;
import com.xml.frame.report.component.controller.Controller;
import com.xml.frame.report.component.entity.Context;

import net.sf.json.JSONObject;

@org.springframework.stereotype.Controller
@RequestMapping(value = "report")
public class ReportServlet {
	@Resource(name=ReportServiceImpl.NAME)
	ReportService reportService;

	@RequestMapping(value = "console/show.do")
	public ModelAndView consoleShow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DataNode tree = reportService.getCSN();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("componentTree", JSONObject.fromObject(tree).toString());
		return new ModelAndView("report/report_console", model);
	}
	
	@RequestMapping(value = "{controller}.do")
	public ModelAndView onController(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("controller") String controllor) throws Exception {
		
		Context context = reportService.doController(request, response, controllor);
		if (null != context){
			ModelAndView mv = (ModelAndView) context.get(Controller.MODEL_AND_VIEW);
			return mv; 
		}
		return null;
	}
}
