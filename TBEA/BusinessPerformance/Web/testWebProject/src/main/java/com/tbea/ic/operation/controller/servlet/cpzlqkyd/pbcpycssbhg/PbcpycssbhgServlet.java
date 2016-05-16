package com.tbea.ic.operation.controller.servlet.cpzlqkyd.pbcpycssbhg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.tbea.ic.operation.common.formatter.excel.FormatterClient;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderCenterFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.MergeRegion;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterClient;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawNumberFormatterHandler;
import com.tbea.ic.operation.service.cpzlqkyd.pbcpycssbhg.PbcpycssbhgService;
import com.tbea.ic.operation.service.cpzlqkyd.pbcpycssbhg.PbcpycssbhgServiceImpl;

@Controller
@RequestMapping(value = "pbcpycssbhg")
public class PbcpycssbhgServlet {
	@Resource(name=PbcpycssbhgServiceImpl.NAME)
	PbcpycssbhgService pbcpycssbhgService;



	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getPbcpycssbhg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> result = null; //pbcpycssbhgService.getPbcpycssbhg(d, company);
		
		RawFormatterHandler handler = new RawEmptyHandler(null, new Integer[]{0});
		handler.next(new RawNumberFormatterHandler(1));
		RawFormatterClient client = new RawFormatterClient(handler);
		client.acceptNullAs("--").format(result);
		
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updatePbcpycssbhg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> result = null; //pbcpycssbhgService.getPbcpycssbhgEntry(d, company);
		
		RawFormatterHandler handler = new RawNumberFormatterHandler(4, null, new Integer[]{3}).trimZero(true);
		RawFormatterClient client = new RawFormatterClient(handler);
		client.acceptNullAs("").format(result);
		
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] savePbcpycssbhg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = null; //pbcpycssbhgService.savePbcpycssbhg(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitPbcpycssbhg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = null; //pbcpycssbhgService.submitPbcpycssbhg(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "export.do")
	public void exportPbcpycssbhg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
//		Date d = Date.valueOf(request.getParameter("date"));
//		CompanyType comp = CompanySelection.getCompany(request);
//		Company company = companyManager.getBMDBOrganization().getCompany(comp);
//		
//		List<List<String>> result = pbcpycssbhgService.getPbcpycssbhg(d, company);
//		ExcelTemplate template = ExcelTemplate.createCpzlqkydTemplate(CpzlqkydSheetType.PBCPYCSSBHG);
//	
//		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0});
//		handler.next(new NumberFormatterHandler(1));
//		FormatterClient client = new FormatterClient(handler, 0, 2);
//		client.addMergeRegion(new MergeRegion(0, 2, 1, result.size()));
//		client.format(result, template);
//	
//		template.write(response, template.getSheetName() + ".xls");
	}
}