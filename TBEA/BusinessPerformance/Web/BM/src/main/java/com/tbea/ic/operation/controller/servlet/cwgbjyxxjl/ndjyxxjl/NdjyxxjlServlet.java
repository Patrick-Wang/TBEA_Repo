package com.tbea.ic.operation.controller.servlet.cwgbjyxxjl.ndjyxxjl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.excel.CwgbjyxxjlSheetType;
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
import com.tbea.ic.operation.service.cwgbjyxxjl.jyxxjl.JyxxjlService;
import com.tbea.ic.operation.service.cwgbjyxxjl.jyxxjl.JyxxjlServiceImpl;

@Controller
@RequestMapping(value = "ndjyxxjl")
public class NdjyxxjlServlet {
	@Resource(name=JyxxjlServiceImpl.NAME)
	JyxxjlService jyxxjlService;



	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getNdjyxxjl(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> result = jyxxjlService.getJyxxjlND(d, company);
		
		RawFormatterHandler handler = new RawEmptyHandler(null, new Integer[]{0});
		handler.next(new RawNumberFormatterHandler(1));
		RawFormatterClient client = new RawFormatterClient(handler);
		client.acceptNullAs("--").format(result);
		
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateNdjyxxjl(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		return null;
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveNdjyxxjl(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		return null;
	}
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitNdjyxxjl(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		return null;
	}
	
	private CwgbjyxxjlSheetType getCwgbjyxxjlSheetType(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		Integer num = CwgbjyxxjlSheetType.ND_1.ordinal() + cal.get(Calendar.MONTH);
		
		CwgbjyxxjlSheetType sheetType = CwgbjyxxjlSheetType.values()[num];
		
		return sheetType;
	}
	
	@RequestMapping(value = "export.do")
	public void exportNdjyxxjl(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> result = jyxxjlService.getJyxxjlND(d, company);
		ExcelTemplate template = ExcelTemplate.createCwgbjyxxjlTemplate(getCwgbjyxxjlSheetType(d));
	
		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0});
		handler.next(new NumberFormatterHandler(1));
		FormatterClient client = new FormatterClient(handler, 0, 2);
		client.addMergeRegion(new MergeRegion(0, 2, 1, result.size()));
		client.format(result, template);
	
		template.write(response, template.getSheetName() + "月.xls");
	}
}