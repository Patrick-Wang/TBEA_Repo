package com.tbea.ic.operation.controller.servlet.xnych.xnych;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.tbea.ic.operation.common.excel.XnychSheetType;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler.NumberType;
import com.tbea.ic.operation.service.xnych.xnych.XnychService;
import com.tbea.ic.operation.service.xnych.xnych.XnychServiceImpl;

@Controller
@RequestMapping(value = "xnych")
public class XnychServlet {
	@Resource(name=XnychServiceImpl.NAME)
	XnychService xnychService;



	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getXnych(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> result = xnychService.getXnych(d, company);
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateXnych(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> result = xnychService.getXnychEntry(d, company);
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveXnych(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = xnychService.saveXnych(d, data, company);
		return Util.response(err);
	}
	
	
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitXnych(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = xnychService.submitXnych(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "export.do")
	public void exportXnych(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> ret = xnychService.getXnych(d, company);
		ExcelTemplate template = ExcelTemplate.createXnychTemplate(XnychSheetType.XNYCH);
	
		FormatterHandler handler = new NumberFormatterHandler(NumberType.RESERVE_1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < ret.size(); ++i){
			HSSFRow row = sheet.createRow(i + 1);
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, j, template, row.createCell(j), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
}