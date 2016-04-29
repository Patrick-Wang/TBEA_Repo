package com.tbea.ic.operation.controller.servlet.sbdczclwcqk.clylwcqk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
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
import com.tbea.ic.operation.common.excel.SbdczclwcqkSheetType;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler.NumberType;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;
import com.tbea.ic.operation.service.sbdczclwcqk.clylwcqk.ClylwcqkService;
import com.tbea.ic.operation.service.sbdczclwcqk.clylwcqk.ClylwcqkServiceImpl;

@Controller
@RequestMapping(value = "clylwcqk")
public class ClylwcqkServlet {
	@Resource(name=ClylwcqkServiceImpl.NAME)
	ClylwcqkService clylwcqkService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	private SbdczclwcqkType getType(HttpServletRequest request){
		if (14 == Integer.valueOf(request.getParameter("sbdczclwcqkType"))){
			return SbdczclwcqkType.SBDCZCLWCQK_CL_XL;
		}
		
		return SbdczclwcqkType.SBDCZCLWCQK_CL_XL;
	}
	
	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getClylwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> result = clylwcqkService.getClylwcqk(d, company, getType(request));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateClylwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> result = clylwcqkService.getClylwcqkEntry(d, company, getType(request));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveClylwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = clylwcqkService.saveClylwcqk(d, company, getType(request), data);
		return Util.response(err);
	}
	
	
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitClylwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = clylwcqkService.submitClylwcqk(d, company, getType(request), data);
		return Util.response(err);
	}
	
	private SbdczclwcqkSheetType getSbdczclwcqkSheetType(SbdczclwcqkType type, Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		Integer num = (type.value() % SbdczclwcqkType.SBDCZCLWCQK_CZ_BYQ.value()) * 12 + cal.get(Calendar.MONTH);
		
		SbdczclwcqkSheetType sheetType = SbdczclwcqkSheetType.values()[num];
		
		return sheetType;
	}
	
	@RequestMapping(value = "export.do")
	public void exportClylwcqk(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> ret = clylwcqkService.getClylwcqk(d, company, getType(request));
		ExcelTemplate template = ExcelTemplate.createSbdczclwcqkTemplate(getSbdczclwcqkSheetType(getType(request), d));
	
		FormatterHandler handler = new HeaderFormatterHandler(null, new Integer[]{0});
		handler.next(new NumberFormatterHandler(NumberType.RESERVE_1));
		HSSFWorkbook workbook = template.getWorkbook();
		String name = workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < ret.size(); ++i){
			HSSFRow row = sheet.createRow(i + 2);
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, j, template, row.createCell(j), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
}