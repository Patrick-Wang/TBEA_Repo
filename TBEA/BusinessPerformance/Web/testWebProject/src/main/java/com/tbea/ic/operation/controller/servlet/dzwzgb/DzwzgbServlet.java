package com.tbea.ic.operation.controller.servlet.dzwzgb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.excel.DzwzgbSheetType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.common.formatter.raw.RawNumberFormatterHandler;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.dzwzgb.DzwzgbService;
import com.tbea.ic.operation.service.dzwzgb.DzwzgbServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Controller
@RequestMapping(value = "dzwzgb")
public class DzwzgbServlet {
	@Resource(name=DzwzgbServiceImpl.NAME)
	DzwzgbService dzwzgbService;

	

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	
	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@RequestMapping(value = {"show.do", "v2/show.do"})
	public ModelAndView getSbdddcbjpcqk(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), false, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.SbdgbLookup);
		
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "dzwzgb/dzwzgb", map);
	}
	
	@RequestMapping(value = {"entry.do", "v2/entry.do"})
	public ModelAndView getByqkglyddEntry(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.SbdgbEntry);
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "dzwzgb/dzwzgbEntry", map);
	}
	
	@RequestMapping(value = "dzclcb/update.do")
	public @ResponseBody byte[] getDzclcb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = dzwzgbService.getDzclcb(d, companyManager.getBMDBOrganization().getCompany(comp));
		RawFormatterHandler handler = new RawNumberFormatterHandler(1);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	@RequestMapping(value = "dzclcb/entry/update.do")
	public @ResponseBody byte[] getDzclcbEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType type = CompanySelection.getCompany(request);
		Company comp = companyManager.getBMDBOrganization().getCompany(type);
		List<List<String>> result = dzwzgbService.getDzclcbEntry(d, comp);
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "dzclcb/entry/save.do")
	public @ResponseBody byte[] saveDzclcb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = dzwzgbService.saveDzclcb(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "dzclcb/entry/submit.do")
	public @ResponseBody byte[] submitDzclcb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = dzwzgbService.submitDzclcb(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "dzclcb/export.do")
	public void exportDzclcb(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> ret = dzwzgbService.getDzclcb(d, company);
		ExcelTemplate template = null;
		if (company.getType() == CompanyType.SBGS ||
				company.getType() == CompanyType.HBGS ||
				company.getType() == CompanyType.TBGS ||
				company.getType() == CompanyType.XBC ){
			template = ExcelTemplate.createDzwzgbTemplate(DzwzgbSheetType.BYQDZCLCB);
		}else{
			template = ExcelTemplate.createDzwzgbTemplate(DzwzgbSheetType.XLDZCLCB);
		}
		
		FormatterHandler handler = new NumberFormatterHandler(1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < ret.size(); ++i){
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, j, template, sheet.getRow(i + 2).getCell(j + 2), ret.get(i).get(j));
			}
		}
		template.writeWithRawSize(response, name + ".xls");
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 0 6-7 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime().toLocaleString() + "dzwzgb import data from 15db");
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		dzwzgbService.importDzclcb(d);
	}
	
	@RequestMapping(value = "importTest.do")
	public @ResponseBody byte[] nctest(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		if (!(request.getParameter("date") == null)){
			d = Date.valueOf(request.getParameter("date"));
		}
		dzwzgbService.importDzclcb(d);
		return "OK".getBytes("utf-8");
	}
}
