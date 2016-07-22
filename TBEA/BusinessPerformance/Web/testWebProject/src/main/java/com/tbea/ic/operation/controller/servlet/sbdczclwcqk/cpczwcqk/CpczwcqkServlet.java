package com.tbea.ic.operation.controller.servlet.sbdczclwcqk.cpczwcqk;

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
import com.tbea.ic.operation.common.formatter.v2.core.DefaultMatcher;
import com.tbea.ic.operation.common.formatter.v2.core.EmptyFormatter;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterServer;
import com.tbea.ic.operation.common.formatter.v2.data.NumberFormatter;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;
import com.tbea.ic.operation.service.sbdczclwcqk.cpczwcqk.CpczwcqkService;
import com.tbea.ic.operation.service.sbdczclwcqk.cpczwcqk.CpczwcqkServiceImpl;

@Controller
@RequestMapping(value = "cpczwcqk")
public class CpczwcqkServlet {
	@Resource(name=CpczwcqkServiceImpl.NAME)
	CpczwcqkService cpczwcqkService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	Company getCompany(CompanyType comp){
		Company bmCompany = companyManager.getBMDBOrganization().getCompany(comp);
		Company vYszkCompany = companyManager.getVirtualGBOrg().getCompany(comp);
		if (bmCompany == null || vYszkCompany != null && vYszkCompany.getId() != bmCompany.getId()){
			return vYszkCompany;
		}
		return bmCompany;
	}
	
	private SbdczclwcqkType getType(HttpServletRequest request){
		if (11 == Integer.valueOf(request.getParameter("sbdczclwcqkType"))){
			return SbdczclwcqkType.SBDCZCLWCQK_CZ_BYQ;
		} else if (12 == Integer.valueOf(request.getParameter("sbdczclwcqkType"))) {
			return SbdczclwcqkType.SBDCZCLWCQK_CZ_XL;
		}
		
		return SbdczclwcqkType.SBDCZCLWCQK_CZ_BYQ;
	}

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getCpczwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = getCompany(comp);
		List<List<String>> result = cpczwcqkService.getCpczwcqk(d, company, getType(request));
		FormatterServer serv = new FormatterServer();
		serv.handlerBuilder()
			.add(new EmptyFormatter(DefaultMatcher.LEFT1_MATCHER))
			.add(new NumberFormatter(1))
			.server()
			.format(result);
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateCpczwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> result = cpczwcqkService.getCpczwcqkEntry(d, company, getType(request));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveCpczwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = cpczwcqkService.saveCpczwcqk(d, company, getType(request), data);
		return Util.response(err);
	}
	
	
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitCpczwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = cpczwcqkService.submitCpczwcqk(d, company, getType(request), data);
		return Util.response(err);
	}
	
	private SbdczclwcqkSheetType getSbdczclwcqkSheetType(SbdczclwcqkType type, Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		Integer num = cal.get(Calendar.MONTH);
		
		SbdczclwcqkSheetType sheetType = SbdczclwcqkSheetType.values()[num];
		
		return sheetType;
	}
	
	@RequestMapping(value = "export.do")
	public void exportCpczwcqk(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = getCompany(comp);
		
		List<List<String>> ret = cpczwcqkService.getCpczwcqk(d, company, getType(request));
		ExcelTemplate template = ExcelTemplate.createSbdczclwcqkTemplate(getSbdczclwcqkSheetType(getType(request), d));
	
		FormatterHandler handler = new HeaderFormatterHandler(null, new Integer[]{0});
		handler.next(new NumberFormatterHandler(1));
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