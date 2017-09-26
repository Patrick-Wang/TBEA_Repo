package com.tbea.ic.operation.controller.servlet.nyzbscqk.nyzbscjg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.excel.NyzbscqkSheetType;
import com.tbea.ic.operation.service.nyzbscqk.nyzbscjg.NyzbscjgService;
import com.tbea.ic.operation.service.nyzbscqk.nyzbscjg.NyzbscjgServiceImpl;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.excel.FormatterHandler;
import com.xml.frame.report.util.excel.FormatterServer;
import com.xml.frame.report.util.excel.HeaderCenterFormatterHandler;
import com.xml.frame.report.util.excel.MergeRegion;
import com.xml.frame.report.util.excel.NumberFormatterHandler;
import com.xml.frame.report.util.raw.RawEmptyHandler;
import com.xml.frame.report.util.raw.RawFormatterHandler;
import com.xml.frame.report.util.raw.RawFormatterServer;
import com.xml.frame.report.util.raw.RawNumberFormatterHandler;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "nyzbscjg")
public class NyzbscjgServlet {
	@Resource(name=NyzbscjgServiceImpl.NAME)
	NyzbscjgService nyzbscjgService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getNyzbscjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		List<List<String>> result = nyzbscjgService.getNyzbscjg(d, company);
		
		RawFormatterHandler handler = new RawEmptyHandler(null, new Integer[]{0, 1});
		handler.next(new RawNumberFormatterHandler(1));
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateNyzbscjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		
		List<List<String>> result = nyzbscjgService.getNyzbscjgEntry(d, company);
		
		RawFormatterHandler handler = new RawNumberFormatterHandler(4, null, new Integer[]{3}).trimZero(true);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("").format(result);
		
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveNyzbscjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		
		ErrorCode err = nyzbscjgService.saveNyzbscjg(d, data, company);
		return Util.response(err);
	}
	
	
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitNyzbscjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		
		ErrorCode err = nyzbscjgService.submitNyzbscjg(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "export.do")
	public void exportNyzbscjg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		
		List<List<String>> result = nyzbscjgService.getNyzbscjg(d, company);
		ExcelHelper template = ExcelTemplate.createNyzbscqkTemplate(NyzbscqkSheetType.NYZBSCJG);
	
		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0, 1});
		handler.next(new NumberFormatterHandler(1));
		FormatterServer serv = new FormatterServer(handler, 0, 2);
		serv.addMergeRegion(new MergeRegion(0, 2, 1, result.size()));
		serv.format(result, template);
		
		template.write(response, template.getSheetName() + ".xls");
	}
}