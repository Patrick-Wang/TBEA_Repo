package com.tbea.ic.operation.controller.servlet.cwcpdlml.cpdlml;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.excel.CwcpdlmlSheetType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.cwcpdlml.cpdlml.CpdlmlService;
import com.tbea.ic.operation.service.cwcpdlml.cpdlml.CpdlmlServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.excel.FormatterHandler;
import com.xml.frame.report.util.excel.FormatterServer;
import com.xml.frame.report.util.excel.HeaderCenterFormatterHandler;
import com.xml.frame.report.util.excel.MergeRegion;
import com.xml.frame.report.util.excel.NumberFormatterHandler;
import com.xml.frame.report.util.excel.PercentFormatterHandler;
import com.xml.frame.report.util.excel.TextFormatterHandler;
import com.xml.frame.report.util.v2.core.DefaultMatcher;
import com.xml.frame.report.util.v2.core.EmptyFormatter;
import com.xml.frame.report.util.v2.data.NumberFormatter;
import com.xml.frame.report.util.v2.data.PercentFormatter;

import net.sf.json.JSONArray;

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
		List<Company> comps;
		
		CompanyType comp = CompanySelection.getCompany(request);
		if (comp != CompanyType.GFGS){
			comps = new ArrayList<Company>();
			Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
			comps.add(company);
		}else{
			comps = extAuthServ.getAuthedCompanies(
					SessionManager.getAccount(request.getSession()),
					AuthType.FinanceLookup);
		}
		
		List<List<String>> result = cpdlmlService.getCpdlml(d, comps);
		
		com.xml.frame.report.util.v2.core.FormatterServer serv = new com.xml.frame.report.util.v2.core.FormatterServer();
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
		List<Company> comps;
		
		CompanyType comp = CompanySelection.getCompany(request);
		if (comp != CompanyType.GFGS){
			comps = new ArrayList<Company>();
			Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
			comps.add(company);
		}else{
			comps = extAuthServ.getAuthedCompanies(
					SessionManager.getAccount(request.getSession()),
					AuthType.FinanceLookup);
		}
		
		List<List<String>> result = cpdlmlService.getCpdlml(d, comps);
		ExcelHelper template = ExcelTemplate.createCwcpdlmlTemplate(CwcpdlmlSheetType.CPDLML);
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