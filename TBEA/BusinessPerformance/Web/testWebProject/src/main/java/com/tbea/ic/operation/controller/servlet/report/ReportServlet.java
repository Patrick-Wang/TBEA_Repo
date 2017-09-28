package com.tbea.ic.operation.controller.servlet.report;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.service.report.ComponentManagerService;
import com.util.tools.DataNode;
import com.xml.frame.report.component.controller.Controller;
import com.xml.frame.report.component.entity.Context;

import net.sf.json.JSONObject;

@org.springframework.stereotype.Controller
@RequestMapping(value = {"report", "report/v2"})
public class ReportServlet{
	
	@Autowired
	ComponentManagerService cms;
	
	@RequestMapping(value = "console/show.do")
	public ModelAndView consoleShow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DataNode tree = cms.getCSN();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("componentTree", JSONObject.fromObject(tree).toString());
		return new ModelAndView("report/report_console", model);
	}
	
	@RequestMapping(value = "{controller}.do")
	public ModelAndView onController(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("controller") String controllor) throws Exception {
		
		Context context = cms.doController(request, response, controllor);
		if (null != context){
			ModelAndView mv = (ModelAndView) context.get(Controller.MODEL_AND_VIEW);
			if (mv != null){
				if (Url.isV2(request) && !mv.getViewName().startsWith("ui2")){
					mv.setViewName("ui2/pages/" + mv.getViewName());
				}
			}
			return mv; 
		}
		return null;
	}
}
