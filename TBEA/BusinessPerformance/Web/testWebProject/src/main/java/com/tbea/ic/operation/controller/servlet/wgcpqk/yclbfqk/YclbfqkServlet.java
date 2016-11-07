package com.tbea.ic.operation.controller.servlet.wgcpqk.yclbfqk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
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
import com.tbea.ic.operation.common.excel.WgcpqkSheetType;
import com.tbea.ic.operation.common.formatter.v2.core.DefaultMatcher;
import com.tbea.ic.operation.common.formatter.v2.core.EmptyFormatter;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterServer;
import com.tbea.ic.operation.common.formatter.v2.core.Offset;
import com.tbea.ic.operation.common.formatter.v2.data.NumberFormatter;
import com.tbea.ic.operation.common.formatter.v2.data.PercentFormatter;
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelHeaderFormatter;
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelOffsetFormatter;
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelTitleYearScrollerFilter;
import com.tbea.ic.operation.service.wgcpqk.yclbfqk.YclbfqkService;
import com.tbea.ic.operation.service.wgcpqk.yclbfqk.YclbfqkServiceImpl;

@Controller
@RequestMapping(value = "yclbfqk")
public class YclbfqkServlet {
	@Resource(name=YclbfqkServiceImpl.NAME)
	YclbfqkService yclbfqkService;

	Company getCompany(CompanyType comp){
		Company bmCompany = companyManager.getBMDBOrganization().getCompany(comp);
		Company vYszkCompany = companyManager.getVirtualGBOrg().getCompany(comp);
		if (bmCompany == null || vYszkCompany != null && vYszkCompany.getId() != bmCompany.getId()){
			return vYszkCompany;
		}
		return bmCompany;
	}

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getYclbfqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = getCompany(comp);
		List<List<String>> result = yclbfqkService.getYclbfqk(d, company);
		FormatterServer serv = new FormatterServer();
		serv.handlerBuilder()
			.add(new EmptyFormatter(DefaultMatcher.LEFT1_MATCHER))
			.add(new NumberFormatter(new DefaultMatcher(null, new Integer[]{1, 2}), 1)) 
			.add(new PercentFormatter(null, 1))
			.server()
			.format(result);
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateYclbfqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> result = yclbfqkService.getYclbfqkEntry(d, company);
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveYclbfqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = yclbfqkService.saveYclbfqk(d, data, company);
		return Util.response(err);
	}
	
	
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitYclbfqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = yclbfqkService.submitYclbfqk(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "export.do")
	public void exportYclbfqk(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = getCompany(comp);
		
		List<List<String>> ret = yclbfqkService.getYclbfqk(d, company);
		ExcelTemplate template = ExcelTemplate.createWgcpqkTemplate(WgcpqkSheetType.YCLBFQK);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = workbook.getSheetName(0);
		workbook.setSheetName(0, name);
//		HSSFSheet sheet = workbook.getSheetAt(0);
		FormatterServer serv = new FormatterServer();
		serv.handlerBuilder()
			.add(new EmptyFormatter(DefaultMatcher.LEFT1_MATCHER))
			.add(new NumberFormatter(new DefaultMatcher(null, new Integer[]{1, 2}), 1)) 
			.add(new PercentFormatter(null, 1))
			.add(new ExcelTitleYearScrollerFilter(template, new Offset(0, 4), d))
			.to(FormatterServer.GROP_EXCEL)
			.add(new ExcelHeaderFormatter(DefaultMatcher.LEFT1_MATCHER, template, new Offset(2, 0)))
			.to(FormatterServer.GROP_EXCEL)
			.add(new ExcelOffsetFormatter(null, template, new Offset(2, 0)))
			.to(FormatterServer.GROP_EXCEL)
			.server()
			.format(ret);

		template.write(response, name + ".xls");
	}
	
	@RequestMapping(value = "schedule.do")
	public @ResponseBody byte[] schedule(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		if (request.getParameter("date") != null){
			d = Date.valueOf(request.getParameter("date"));
		}
		
		yclbfqkService.importDlYclbfqk(d);
		
		String result = "{\"result\":\"OK\"}";
		return result.getBytes("utf-8");
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 12 4 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		yclbfqkService.importDlYclbfqk(d);
	}
}