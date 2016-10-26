package com.tbea.ic.operation.controller.servlet.cwcpdlml.cpdlml;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.excel.CwcpdlmlSheetType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.excel.FormatterServer;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderCenterFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.MergeRegion;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.PercentFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.TextFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawNumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawPercentFormatterHandler;
import com.tbea.ic.operation.common.formatter.v2.core.DefaultMatcher;
import com.tbea.ic.operation.common.formatter.v2.core.EmptyFormatter;
import com.tbea.ic.operation.common.formatter.v2.data.NumberFormatter;
import com.tbea.ic.operation.common.formatter.v2.data.PercentFormatter;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.cwcpdlml.cpdlml.CpdlmlService;
import com.tbea.ic.operation.service.cwcpdlml.cpdlml.CpdlmlServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Controller
@RequestMapping(value = "cpdlml")
public class CpdlmlServlet {
	@Resource(name=CpdlmlServiceImpl.NAME)
	CpdlmlService cpdlmlService;


	@Autowired
	ExtendAuthorityService extAuthServ;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getCpdlml(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		List<Company> comps = extAuthServ.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.FinanceLookup);
		
		List<List<String>> result = cpdlmlService.getCpdlml(d, comps);
		
		com.tbea.ic.operation.common.formatter.v2.core.FormatterServer serv = new com.tbea.ic.operation.common.formatter.v2.core.FormatterServer();
		serv.handlerBuilder()
			.add(new EmptyFormatter(DefaultMatcher.LEFT2_MATCHER))
			.add(new PercentFormatter(new DefaultMatcher(null, new Integer[]{3, 6, 7, 10, 11}), 1))
			.add(new NumberFormatter(1))
			.server()
			.format(result);
		
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "export.do")
	public void exportCpdlml(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		List<Company> comps = extAuthServ.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.FinanceLookup);
		List<List<String>> result = cpdlmlService.getCpdlml(d, comps);
		ExcelTemplate template = ExcelTemplate.createCwcpdlmlTemplate(CwcpdlmlSheetType.CPDLML);
		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0});
		handler.next(new TextFormatterHandler(null, new Integer[]{0, 1}))
		.next(new PercentFormatterHandler(1, null, new Integer[]{3, 6, 7, 10, 11}))
		.next(new NumberFormatterHandler(1));
		FormatterServer serv = new FormatterServer(handler, 0, 2);
		serv.addMergeRegion(new MergeRegion(0, 2, 1, result.size()));
		serv.format(result, template);
		template.write(response, template.getSheetName() + ".xls");
	}
}