package com.tbea.ic.operation.controller.servlet.sbdscqyqk.xfscqy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
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
import com.tbea.ic.operation.common.excel.SbdscqyqkSheetType;
import com.tbea.ic.operation.service.sbdscqyqk.xfscqy.XfscqyService;
import com.tbea.ic.operation.service.sbdscqyqk.xfscqy.XfscqyServiceImpl;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.excel.FormatterHandler;
import com.xml.frame.report.util.excel.FormatterServer;
import com.xml.frame.report.util.excel.HeaderFormatterHandler;
import com.xml.frame.report.util.excel.MergeRegion;
import com.xml.frame.report.util.excel.NumberFormatterHandler;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "xfscqy")
public class XfscqyServlet {
	@Resource(name=XfscqyServiceImpl.NAME)
	XfscqyService xfscqyService;


	Company getCompany(CompanyType comp){
		Company bmCompany = companyManager.getBMDBOrganization().getCompanyByType(comp);
		Company vYszkCompany = companyManager.getVirtualGBOrg().getCompanyByType(comp);
		if (bmCompany == null || vYszkCompany != null && vYszkCompany.getId() != bmCompany.getId()){
			return vYszkCompany;
		}
		return bmCompany;
	}
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	private void swap(List<List<String>> result, int rowStart, int rowEnd){
		List<String> tmp = result.get(rowStart);
		result.set(rowStart, result.get(rowEnd));
		result.set(rowEnd, tmp);
	}
	
	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getXfscqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = getCompany(comp);
		List<List<String>> result = xfscqyService.getXfscqy(d, company);
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateXfscqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		List<List<String>> result = xfscqyService.getXfscqyEntry(d, company);
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveXfscqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		
		ErrorCode err = xfscqyService.saveXfscqy(d, data, company);
		return Util.response(err);
	}
	
	
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitXfscqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		
		ErrorCode err = xfscqyService.submitXfscqy(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "export.do")
	public void exportXfscqy(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = getCompany(comp);
		
		List<List<String>> ret = xfscqyService.getXfscqy(d, company);
		swap(ret, ret.size() - 2, ret.size() - 3);
		ExcelHelper template = ExcelTemplate.createSbdscqyqkTemplate(SbdscqyqkSheetType.XFSCQY);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		Calendar startMonth = Calendar.getInstance();
		startMonth.setTime(d);
		startMonth.add(Calendar.YEAR, -1);

		int last = 2 + 12 - (startMonth.get(Calendar.MONTH) + 1);
		for (int i = 0; i < 13; ++i){
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
			cell.setCellValue((startMonth.get(Calendar.MONTH) + 1) + "月");
			cell.setCellStyle(template.getCellStyleCenter());
			startMonth.add(Calendar.MONTH, 1);
		}
		
		FormatterHandler handler = new HeaderFormatterHandler(null, new Integer[]{0});
		handler.next(new NumberFormatterHandler(1));
		FormatterServer serv = new FormatterServer(handler, 1, 2);
		serv.addMergeRegion(new MergeRegion(2, 0, 13, 1));
		serv.format(ret, template);
		template.write(response, name + ".xls");
	}
}