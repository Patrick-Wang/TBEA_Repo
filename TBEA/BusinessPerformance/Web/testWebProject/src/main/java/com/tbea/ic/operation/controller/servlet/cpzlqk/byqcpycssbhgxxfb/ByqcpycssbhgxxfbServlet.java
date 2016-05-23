package com.tbea.ic.operation.controller.servlet.cpzlqk.byqcpycssbhgxxfb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.excel.CpzlqkSheetType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.excel.FormatterServer;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderCenterFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.MergeRegion;
import com.tbea.ic.operation.common.formatter.excel.TextFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.controller.servlet.cpzlqk.ByqBhgType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgxxfb.BhgxxfbResp;
import com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgxxfb.ByqcpycssbhgxxfbService;
import com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgxxfb.ByqcpycssbhgxxfbServiceImpl;

@Controller
@RequestMapping(value = "byqcpycssbhgxxfb")
public class ByqcpycssbhgxxfbServlet {
	@Resource(name=ByqcpycssbhgxxfbServiceImpl.NAME)
	ByqcpycssbhgxxfbService byqcpycssbhgxxfbService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getByqcpycssbhgxxfb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		ByqBhgType bhgType = ByqBhgType.valueOf(Integer.valueOf(request.getParameter("bhgType")));
		
		List<String> bhglbs = byqcpycssbhgxxfbService.getBhglbs();
		
		List<List<String>> result = byqcpycssbhgxxfbService.getByqcpycssbhgxxfb(d, yjType, bhgType);
		
		RawFormatterHandler handler = new RawEmptyHandler(null, null);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		
		return JSONObject.fromObject(new BhgxxfbResp(bhglbs, result)).toString().getBytes("utf-8");
	}


	@RequestMapping(value = "export.do")
	public void exportByqcpycssbhgxxfb(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		ByqBhgType bhgType = ByqBhgType.valueOf(Integer.valueOf(request.getParameter("bhgType")));
		
		List<List<String>> result = byqcpycssbhgxxfbService.getByqcpycssbhgxxfb(d, yjType, bhgType);
		ExcelTemplate template = ExcelTemplate.createCpzlqkTemplate(CpzlqkSheetType.BYQCPYCSSBHGXXFB);
	
		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0, 1});
		handler.next(new TextFormatterHandler());
		FormatterServer serv = new FormatterServer(handler, 0, 1);
		serv.addMergeRegion(new MergeRegion(0, 1, 1, result.size()));
		serv.format(result, template);
	
		template.write(response, template.getSheetName() + ".xls");
	}
}