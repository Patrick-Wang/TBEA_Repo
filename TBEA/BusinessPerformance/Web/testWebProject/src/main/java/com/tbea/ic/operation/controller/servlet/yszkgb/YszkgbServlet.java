package com.tbea.ic.operation.controller.servlet.yszkgb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.StatusData;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.excel.YszkgbSheetType;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.tbea.ic.operation.service.yszkgb.YszkgbService;
import com.tbea.ic.operation.service.yszkgb.YszkgbServiceImpl;

@Controller
@RequestMapping(value = "yszkgb")
public class YszkgbServlet {
	@Resource(name=YszkgbServiceImpl.NAME)
	YszkgbService yszkgbService;

	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@RequestMapping(value = "show.do")
	public ModelAndView getYszkgb(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.YszkgbLookup);

		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		
		return new ModelAndView("yszkgb/yszkgb", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getYszkgbEntry(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.YszkgbEntry);

		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		
		return new ModelAndView("yszkgb/yszkgbEntry", map);
	}
	
	@RequestMapping(value = "zmb/update.do")
	public @ResponseBody byte[] getZmb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = yszkgbService.getZmb(d, companyManager.getBMDBOrganization().getCompany(comp));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "yszkzlbh/update.do")
	public @ResponseBody byte[] getYszkzlbh(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = yszkgbService.getYszkzlbh(d, companyManager.getBMDBOrganization().getCompany(comp));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}
	@RequestMapping(value = "yszkkxxz/update.do")
	public @ResponseBody byte[] getYszkkxxz(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = yszkgbService.getYszkkxxz(d, companyManager.getBMDBOrganization().getCompany(comp));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}
	
	
	@RequestMapping(value = "yqyszcsys/update.do")
	public @ResponseBody byte[] getYqyszcsys(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = yszkgbService.getYqyszcsys(d, companyManager.getBMDBOrganization().getCompany(comp));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "yszkyjtztjqs/update.do")
	public @ResponseBody byte[] getYszkyjtztjqs(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = yszkgbService.getYszkyjtztjqs(d, companyManager.getBMDBOrganization().getCompany(comp));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "yszkkxxz/entry/update.do")
	public @ResponseBody byte[] getYszkkxxzEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType type = CompanySelection.getCompany(request);
		Company comp = companyManager.getBMDBOrganization().getCompany(type);
		List<List<String>> result = yszkgbService.getYszkkxxzEntry(d, comp);
		ZBStatus status = yszkgbService.getYszkkxxzStatus(d, comp);
		StatusData sd = new StatusData(ZBStatus.APPROVED == status, result);
		return JSONObject.fromObject(sd).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	
	@RequestMapping(value = "yqyszcsys/entry/update.do")
	public @ResponseBody byte[] getYqyszcsysEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType type = CompanySelection.getCompany(request);
		Company comp = companyManager.getBMDBOrganization().getCompany(type);
		List<List<String>> result = yszkgbService.getYqyszcsysEntry(d, comp);
		ZBStatus status = yszkgbService.getYqyszcsysStatus(d, comp);
		StatusData sd = new StatusData(ZBStatus.APPROVED == status, result);
		return JSONObject.fromObject(sd).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "yszkyjtztjqs/entry/update.do")
	public @ResponseBody byte[] getYszkyjtztjqsEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType type = CompanySelection.getCompany(request);
		Company comp = companyManager.getBMDBOrganization().getCompany(type);
		List<List<String>> result = yszkgbService.getYszkyjtztjqsEntry(d, comp);
		ZBStatus status = yszkgbService.getYszkyjtztjqsStatus(d, comp);
		StatusData sd = new StatusData(ZBStatus.APPROVED == status, result);
		return JSONObject.fromObject(sd).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "yszkkxxz/entry/save.do")
	public @ResponseBody byte[] entryYszkkxxz(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = yszkgbService.saveYszkkxxz(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "yqyszcsys/entry/save.do")
	public @ResponseBody byte[] entryYqyszcsys(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = yszkgbService.saveYqyszcsys(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "yszkyjtztjqs/entry/save.do")
	public @ResponseBody byte[] entryYszkyjtztjqs(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = yszkgbService.saveYszkyjtztjqs(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "yszkkxxz/entry/submit.do")
	public @ResponseBody byte[] submitYszkkxxz(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = yszkgbService.submitYszkkxxz(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "yqyszcsys/entry/submit.do")
	public @ResponseBody byte[] submitYqyszcsys(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = yszkgbService.submitYqyszcsys(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "yszkyjtztjqs/entry/submit.do")
	public @ResponseBody byte[] submitYszkyjtztjqs(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = yszkgbService.submitYszkyjtztjqs(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "zmb/export.do")
	public @ResponseBody byte[] exportZmbx(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> ret = yszkgbService.getZmb(d, company);
		ExcelTemplate template = ExcelTemplate.createYszkgbTemplate(YszkgbSheetType.ZMB);
		
		FormatterHandler handler = new NumberFormatterHandler(1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		handler.handle(null, null, template, sheet.getRow(0).getCell(1), ret.get(0).get(0));
		handler.handle(null, null, template, sheet.getRow(0).getCell(3), ret.get(0).get(1));
		handler.handle(null, null, template, sheet.getRow(0).getCell(5), ret.get(0).get(2));
		template.write(response, name + ".xls");
		return "".getBytes("utf-8");
	}
	
	@RequestMapping(value = "yszkzlbh/export.do")
	public void exportYszkzlbh(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> ret = yszkgbService.getYszkzlbh(d, company);
		ExcelTemplate template = ExcelTemplate.createYszkgbTemplate(YszkgbSheetType.YSZKZLBH);
		
		FormatterHandler handler = new NumberFormatterHandler(1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		Calendar startMonth = Calendar.getInstance();
		startMonth.setTime(d);
		startMonth.add(Calendar.YEAR, -1);
		startMonth.add(Calendar.MONTH, 1);

		int last = 12 - (startMonth.get(Calendar.MONTH) + 1);
		for (int i = 0; i < 12; ++i){
			HSSFRow row = sheet.createRow(i + 1);
			HSSFCell cell = row.createCell(0);
			cell.setCellStyle(template.getCellStyleCenter());
			if (i <= last){
				cell.setCellValue("上年度");
			}else{
				cell.setCellValue("本年度");
			}
			cell = row.createCell(1);
			cell.setCellValue((startMonth.get(Calendar.MONTH) + 1) + "月");
			cell.setCellStyle(template.getCellStyleCenter());
			startMonth.add(Calendar.MONTH, 1);
		}
		
		sheet.addMergedRegion(new CellRangeAddress(1, last + 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(last + 2, 12, 0, 0));
		
		for (int i = 0; i < ret.size(); ++i){
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, null, template, sheet.getRow(i + 1).createCell(j + 2), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
	
	
	@RequestMapping(value = "yszkkxxz/export.do")
	public void exportYszkkxxz(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> ret = yszkgbService.getYszkkxxz(d, company);
		ExcelTemplate template = ExcelTemplate.createYszkgbTemplate(YszkgbSheetType.YSZKKXXZQK);
		
		FormatterHandler handler = new NumberFormatterHandler(1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		Calendar startMonth = Calendar.getInstance();
		startMonth.setTime(d);
		startMonth.add(Calendar.YEAR, -1);
		startMonth.add(Calendar.MONTH, 1);

		int last = 12 - (startMonth.get(Calendar.MONTH) + 1);
		for (int i = 0; i < 12; ++i){
			HSSFRow row = sheet.createRow(i + 2);
			HSSFCell cell = row.createCell(0);
			cell.setCellStyle(template.getCellStyleCenter());
			if (i <= last){
				cell.setCellValue("上年度");
			}else{
				cell.setCellValue("本年度");
			}
			cell = row.createCell(1);
			cell.setCellValue((startMonth.get(Calendar.MONTH) + 1) + "月");
			cell.setCellStyle(template.getCellStyleCenter());
			startMonth.add(Calendar.MONTH, 1);
		}
		
		sheet.addMergedRegion(new CellRangeAddress(2, last + 2, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(last + 3, 13, 0, 0));
		
		for (int i = 0; i < ret.size(); ++i){
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, null, template, sheet.getRow(i + 2).createCell(j + 2), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
	
	
	@RequestMapping(value = "yqyszcsys/export.do")
	public void exportYqyszcsys(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> ret = yszkgbService.getYqyszcsys(d, company);
		ExcelTemplate template = ExcelTemplate.createYszkgbTemplate(YszkgbSheetType.YQYSCSYS);
		
		FormatterHandler handler = new NumberFormatterHandler(1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		Calendar startMonth = Calendar.getInstance();
		startMonth.setTime(d);
		startMonth.add(Calendar.YEAR, -1);
		startMonth.add(Calendar.MONTH, 1);

		int last = 12 - (startMonth.get(Calendar.MONTH) + 1);
		for (int i = 0; i < 12; ++i){
			HSSFRow row = sheet.createRow(i + 1);
			HSSFCell cell = row.createCell(0);
			cell.setCellStyle(template.getCellStyleCenter());
			if (i <= last){
				cell.setCellValue("上年度");
			}else{
				cell.setCellValue("本年度");
			}
			cell = row.createCell(1);
			cell.setCellValue((startMonth.get(Calendar.MONTH) + 1) + "月");
			cell.setCellStyle(template.getCellStyleCenter());
			startMonth.add(Calendar.MONTH, 1);
		}
		
		sheet.addMergedRegion(new CellRangeAddress(1, last + 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(last + 2, 12, 0, 0));
		
		for (int i = 0; i < ret.size(); ++i){
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, null, template, sheet.getRow(i + 1).createCell(j + 2), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
	
	
	@RequestMapping(value = "yszkyjtztjqs/export.do")
	public void exportYszkyjtztjqs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> ret = yszkgbService.getYszkyjtztjqs(d, company);
		ExcelTemplate template = ExcelTemplate.createYszkgbTemplate(YszkgbSheetType.YSZKZMYYJTZTJQS);
		
		FormatterHandler handler = new NumberFormatterHandler(1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		Calendar startMonth = Calendar.getInstance();
		startMonth.setTime(d);
		startMonth.add(Calendar.YEAR, -1);
		startMonth.add(Calendar.MONTH, 1);

		int last = 12 - (startMonth.get(Calendar.MONTH) + 1);
		for (int i = 0; i < 12; ++i){
			HSSFRow row = sheet.createRow(i + 2);
			HSSFCell cell = row.createCell(0);
			cell.setCellStyle(template.getCellStyleCenter());
			if (i <= last){
				cell.setCellValue("上年度");
			}else{
				cell.setCellValue("本年度");
			}
			cell = row.createCell(1);
			cell.setCellValue((startMonth.get(Calendar.MONTH) + 1) + "月");
			cell.setCellStyle(template.getCellStyleCenter());
			startMonth.add(Calendar.MONTH, 1);
		}
		
		sheet.addMergedRegion(new CellRangeAddress(2, last + 2, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(last + 3, 13, 0, 0));
		for (int i = 0; i < ret.size(); ++i){
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, null, template, sheet.getRow(i + 2).createCell(j + 2), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 0 3-5 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime().toLocaleString() + "yszkgb import data from NC");
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		List<Company> sbdComps = new ArrayList<Company>();
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.SBGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XBC));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TBGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.LLGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XLC));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XTNYGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XNYGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TCNY));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.NDGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.GJGCGS_GFGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.JCKGS_JYDW));
		yszkgbService.importZbmFromNC(d, sbdComps);
		yszkgbService.importYszkzlbhFromNC(d, sbdComps);
	}
	
	
}
