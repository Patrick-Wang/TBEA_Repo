package com.tbea.ic.operation.controller.servlet.sbdscqyqk.xfscqy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
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
import com.tbea.ic.operation.common.excel.SbdscqyqkSheetType;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler.NumberType;
import com.tbea.ic.operation.service.sbdscqyqk.xfscqy.XfscqyService;
import com.tbea.ic.operation.service.sbdscqyqk.xfscqy.XfscqyServiceImpl;

@Controller
@RequestMapping(value = "xfscqy")
public class XfscqyServlet {
	@Resource(name=XfscqyServiceImpl.NAME)
	XfscqyService xfscqyService;



	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getXfscqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> result = xfscqyService.getXfscqy(d, company);
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateXfscqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> result = xfscqyService.getXfscqyEntry(d, company);
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveXfscqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = xfscqyService.saveXfscqy(d, data, company);
		return Util.response(err);
	}
	
	
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitXfscqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = xfscqyService.submitXfscqy(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "export.do")
	public void exportXfscqy(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> ret = xfscqyService.getXfscqy(d, company);
		ExcelTemplate template = ExcelTemplate.createSbdscqyqkTemplate(SbdscqyqkSheetType.XFSCQY);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		Calendar calCur = Calendar.getInstance();
		calCur.setTime(d);
		Calendar calLastCur = Calendar.getInstance();
		calLastCur.setTime(d);
		calLastCur.add(Calendar.YEAR, -1);
		calLastCur.add(Calendar.MONTH, 1);
		Calendar calCurYear = Calendar.getInstance();
		calCurYear.setTime(d);
		calCurYear.set(Calendar.MONTH, 0);
		
		int last = 2 + 12 - (calLastCur.get(Calendar.MONTH) + 1);
		for (int i = 0; i < 12; ++i){
			HSSFRow title = sheet.getRow(0);
			HSSFCell cell = title.createCell(i + 2);
			cell.setCellStyle(template.getCellStyleCenter());
			if (i <= last - 2){
				cell.setCellValue("上年度");
			}else{
				cell.setCellValue("本年度");
			}
			
			HSSFRow row = sheet.getRow(1);
			cell = row.createCell(i + 2);
			cell.setCellValue((calLastCur.get(Calendar.MONTH) + 1) + "月");
			cell.setCellStyle(template.getCellStyleCenter());
			calLastCur.add(Calendar.MONTH, 1);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, last));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, last + 1, 13));
		
		FormatterHandler handler = new HeaderFormatterHandler(null, new Integer[]{0});
		handler.next(new NumberFormatterHandler(NumberType.RESERVE_1));
		for (int i = 0; i < ret.size(); ++i){
			HSSFRow row = sheet.getRow(i + 2);
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, j, template, row.createCell(j + 1), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
}