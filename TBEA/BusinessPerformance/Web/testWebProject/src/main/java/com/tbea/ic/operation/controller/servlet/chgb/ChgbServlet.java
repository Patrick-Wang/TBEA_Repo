package com.tbea.ic.operation.controller.servlet.chgb;

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
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.tbea.ic.operation.common.excel.ChgbSheetType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawNumberFormatterHandler;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.chgb.ChgbService;
import com.tbea.ic.operation.service.chgb.ChgbServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Controller
@RequestMapping(value = "chgb")
public class ChgbServlet {
	@Resource(name=ChgbServiceImpl.NAME)
	ChgbService chgbService;

	CompanyManager companyManager;
	List<Company> NCCOMPS = new ArrayList<Company>();
	List<Company> NCCOMPS4NYCH = new ArrayList<Company>();
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		this.companyManager = companyManager;
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.SBGS));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XBC));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TBGS));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.LLGS));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XLC));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XTNYGS));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XNYGS));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TCNY));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.NDGS));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.GJGCGS_GFGS));
		NCCOMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.JCKGS_JYDW));
		
		//NYCH List
		NCCOMPS4NYCH.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TCNY));
	}
	
	@Autowired
	ExtendAuthorityService extendAuthService;

	
	@RequestMapping(value = "chzmb/update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getChgbzmb_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = chgbService.getChzmb(d, companyManager.getBMDBOrganization().getCompany(comp));
		
		RawFormatterHandler handler = new RawNumberFormatterHandler(1);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "chjykcb/update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getChjykcb_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = chgbService.getChjykcb(d, companyManager.getBMDBOrganization().getCompany(comp));
		
		RawFormatterHandler handler = new RawEmptyHandler(null, new Integer[]{0});
		handler.next(new RawNumberFormatterHandler(1));
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	
	@RequestMapping(value = "chzlbhqk/update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getChzlbhqk_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = chgbService.getChzlbhqk(d, companyManager.getBMDBOrganization().getCompany(comp));
		RawFormatterHandler handler = new RawNumberFormatterHandler(1);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "chxzqk/update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getChxzqk_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = chgbService.getChxzqk(d, companyManager.getBMDBOrganization().getCompany(comp));
		RawFormatterHandler handler = new RawNumberFormatterHandler(1);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "chnych/update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getChnych_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = chgbService.getChnych(d, companyManager.getBMDBOrganization().getCompany(comp));
		RawFormatterHandler handler = new RawNumberFormatterHandler(1);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "show.do", method = RequestMethod.GET)
	public ModelAndView getChgb(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.ChgbLookup);

		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		
		return new ModelAndView("chgb/chgb", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getChgbEntry(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.ChgbEntry);

		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		
		return new ModelAndView("chgb/chgbEntry", map);
	}
	
	@RequestMapping(value = "chjykcb/entry/update.do")
	public @ResponseBody byte[] getChjykcbEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType type = CompanySelection.getCompany(request);
		Company comp = companyManager.getBMDBOrganization().getCompany(type);
		List<List<String>> result = chgbService.getChjykcbEntry(d, comp);
		ZBStatus status = chgbService.getChjykcbStatus(d, comp);
		StatusData sd = new StatusData(ZBStatus.APPROVED == status, result);
		return JSONObject.fromObject(sd).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "chjykcb/entry/save.do")
	public @ResponseBody byte[] entryChjykcb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = chgbService.saveChjykcb(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "chjykcb/entry/submit.do")
	public @ResponseBody byte[] submitChjykcb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = chgbService.submitChjykcb(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "chzlbhqk/entry/update.do")
	public @ResponseBody byte[] getChzlbhqkEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType type = CompanySelection.getCompany(request);
		Company comp = companyManager.getBMDBOrganization().getCompany(type);
		List<List<String>> result = chgbService.getChzlbhqkEntry(d, comp);
		ZBStatus status = chgbService.getChjykcbStatus(d, comp);
		StatusData sd = new StatusData(ZBStatus.APPROVED == status, result);
		return JSONObject.fromObject(sd).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "chzlbhqk/entry/save.do")
	public @ResponseBody byte[] entryChzlbhqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = chgbService.saveChzlbhqk(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "chxzqk/entry/submit.do")
	public @ResponseBody byte[] submitChxzqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = chgbService.submitChxzqk(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "chxzqk/entry/update.do")
	public @ResponseBody byte[] getChxzqkEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType type = CompanySelection.getCompany(request);
		Company comp = companyManager.getBMDBOrganization().getCompany(type);
		List<List<String>> result = chgbService.getChxzqkEntry(d, comp);
		ZBStatus status = chgbService.getChxzqkStatus(d, comp);
		StatusData sd = new StatusData(ZBStatus.APPROVED == status, result);
		return JSONObject.fromObject(sd).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "chxzqk/entry/save.do")
	public @ResponseBody byte[] entryChxzqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = chgbService.saveChxzqk(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "chzlbhqk/entry/submit.do")
	public @ResponseBody byte[] submitChzlbhqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = chgbService.submitChzlbhqk(d, companyManager.getBMDBOrganization().getCompany(comp), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "chzmb/export.do")
	public void exportChzmb(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> ret = chgbService.getChzmb(d, company);
		ExcelTemplate template = ExcelTemplate.createChgbTemplate(ChgbSheetType.ZMB);
		
		FormatterHandler handler = new NumberFormatterHandler(1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		if (!ret.isEmpty()){
			handler.handle(null, null, template, sheet.getRow(0).getCell(1), ret.get(0).get(0));
			handler.handle(null, null, template, sheet.getRow(0).getCell(3), ret.get(0).get(1));
			handler.handle(null, null, template, sheet.getRow(0).getCell(5), ret.get(0).get(2));
		}
		template.write(response, name + ".xls");
	}
	
	@RequestMapping(value = "chjykcb/export.do")
	public void exportChjykcb(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> ret = chgbService.getChjykcb(d, company);
		ExcelTemplate template = ExcelTemplate.createChgbTemplate(ChgbSheetType.JYKCB);
		
		FormatterHandler handler = new HeaderFormatterHandler(null, new Integer[]{0});
		handler.next(new NumberFormatterHandler(1));
		
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < ret.size(); ++i){
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, j, template, sheet.getRow(i + 1).getCell(j + 1), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
	
	@RequestMapping(value = "chzlbhqk/export.do")
	public void exportChzlbhqk(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> ret = chgbService.getChzlbhqk(d, company);
		ExcelTemplate template = ExcelTemplate.createChgbTemplate(ChgbSheetType.CHZLBHQK);
		
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
	
	@RequestMapping(value = "chxzqk/export.do")
	public void exportChxzqk(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> ret = chgbService.getChxzqk(d, company);
		ExcelTemplate template = ExcelTemplate.createChgbTemplate(ChgbSheetType.CHXZQK);
		
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
	
	@RequestMapping(value = "chnych/export.do")
	public void exportChnych(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> ret = chgbService.getChnych(d, company);
		ExcelTemplate template = ExcelTemplate.createChgbTemplate(ChgbSheetType.NYCH);
		
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
				handler.handle(null, null, template, sheet.getRow(i + 1).createCell(j + 2), ret.get(i).get(j));
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

		chgbService.importZbmFromNC(d, NCCOMPS);
		chgbService.importNychFromNC(d, NCCOMPS4NYCH);
	}
	
	@RequestMapping(value = "nctest.do")
	public @ResponseBody byte[] nctest(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		if (!(request.getParameter("date") == null)){
			d = Date.valueOf(request.getParameter("date"));
		}
		chgbService.importZbmFromNC(d, NCCOMPS);
		chgbService.importNychFromNC(d, NCCOMPS4NYCH);
		return "OK".getBytes("utf-8");
	}
}
