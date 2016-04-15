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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.excel.YszkgbSheetType;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler.NumberType;
import com.tbea.ic.operation.common.formatter.raw.RawNumberFormatterHandler;
import com.tbea.ic.operation.service.yszkgb.YszkgbService;
import com.tbea.ic.operation.service.yszkgb.YszkgbServiceImpl;

@Controller
@RequestMapping(value = "yszkgb")
public class YszkgbServlet {
	@Resource(name=YszkgbServiceImpl.NAME)
	YszkgbService yszkgbService;

	
	CompanyManager companyManager;
	List<Company> yszkgbComps = new ArrayList<Company>();
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		this.companyManager = companyManager;
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.SBGS));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XBC));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TBGS));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.LLGS));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XLC));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS));
	}
	@RequestMapping(value = "show.do")
	public ModelAndView getYszkgb(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		CompanySelection compSel = new CompanySelection(true, yszkgbComps);
		compSel.select(map);
		
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		
		return new ModelAndView("yszkgb/yszkgb", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getYszkgbEntry(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		CompanySelection compSel = new CompanySelection(true, yszkgbComps);
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
		return JSONObject.fromObject(sd).toString().replaceAll("null", "").getBytes("utf-8");
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
		return JSONObject.fromObject(sd).toString().replaceAll("null", "").getBytes("utf-8");
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
		return JSONObject.fromObject(sd).toString().replaceAll("null", "").getBytes("utf-8");
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
		
		FormatterHandler handler = new NumberFormatterHandler(NumberType.RESERVE_1);
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
		
		FormatterHandler handler = new NumberFormatterHandler(NumberType.RESERVE_1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < ret.size(); ++i){
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, null, template, sheet.getRow(i + 1).getCell(j + 2), ret.get(i).get(j));
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
		
		FormatterHandler handler = new NumberFormatterHandler(NumberType.RESERVE_1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < ret.size(); ++i){
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, null, template, sheet.getRow(i + 2).getCell(j + 2), ret.get(i).get(j));
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
		
		FormatterHandler handler = new NumberFormatterHandler(NumberType.RESERVE_1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < ret.size(); ++i){
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, null, template, sheet.getRow(i + 1).getCell(j + 2), ret.get(i).get(j));
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
		ExcelTemplate template = ExcelTemplate.createYszkgbTemplate(YszkgbSheetType.YQYSCSYS);
		
		FormatterHandler handler = new NumberFormatterHandler(NumberType.RESERVE_1);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < ret.size(); ++i){
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, null, template, sheet.getRow(i + 1).getCell(j + 2), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
	
	
}
