package com.tbea.ic.operation.controller.servlet.sbdczclwcqk.cpclwcqk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;
import com.tbea.ic.operation.service.sbdczclwcqk.cpclwcqk.CpclwcqkService;
import com.tbea.ic.operation.service.sbdczclwcqk.cpclwcqk.CpclwcqkServiceImpl;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.excel.FormatterHandler;
import com.xml.frame.report.util.excel.HeaderFormatterHandler;
import com.xml.frame.report.util.excel.NumberFormatterHandler;
import com.xml.frame.report.util.v2.core.DefaultMatcher;
import com.xml.frame.report.util.v2.core.EmptyFormatter;
import com.xml.frame.report.util.v2.core.FormatterServer;
import com.xml.frame.report.util.v2.data.NumberFormatter;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "cpclwcqk")
public class CpclwcqkServlet {
	@Resource(name=CpclwcqkServiceImpl.NAME)
	CpclwcqkService cpclwcqkService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	private SbdczclwcqkType getType(HttpServletRequest request){
		return SbdczclwcqkType.valueOf(Integer.valueOf(request.getParameter("sbdczclwcqkType")));
//		if (SbdczclwcqkType.SBDCZCLWCQK_CL_BYQ.value() == Integer.valueOf(request.getParameter("sbdczclwcqkType"))){
//			return SbdczclwcqkType.SBDCZCLWCQK_CL_BYQ;
//		}		
//		return SbdczclwcqkType.SBDCZCLWCQK_CL_XL;
	}
	Company getCompany(CompanyType comp){
		Company bmCompany = companyManager.getBMDBOrganization().getCompanyByType(comp);
		Company vYszkCompany = companyManager.getVirtualGBOrg().getCompanyByType(comp);
		if (bmCompany == null || vYszkCompany != null && vYszkCompany.getId() != bmCompany.getId()){
			return vYszkCompany;
		}
		return bmCompany;
	}
	
	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getCpclwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = getCompany(comp);
		List<List<String>> result = cpclwcqkService.getCpclwcqk(d, company, getType(request));
		FormatterServer serv = new FormatterServer();
		serv.handlerBuilder()
			.add(new EmptyFormatter(DefaultMatcher.LEFT1_MATCHER))
			.add(new NumberFormatter(1))
			.server()
			.format(result);
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateCpclwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		
		List<List<String>> result = cpclwcqkService.getCpclwcqkEntry(d, company, getType(request));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	// 录入数据包含产值 和 产量
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveCpclwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		
		ErrorCode err = cpclwcqkService.saveCpclwcqk(d, company, getType(request), data);
		return Util.response(err);
	}
	
	
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitCpclwcqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		
		ErrorCode err = cpclwcqkService.submitCpclwcqk(d, company, getType(request), data);
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
	public void exportCpclwcqk(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = getCompany(comp);
		
		List<List<String>> ret = cpclwcqkService.getCpclwcqk(d, company, getType(request));
		ExcelHelper template = ExcelTemplate.createSbdczclwcqkTemplate(getSbdczclwcqkSheetType(getType(request), d));
	
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