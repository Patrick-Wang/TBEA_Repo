package com.tbea.ic.operation.controller.servlet.sbdddcbjpcqk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.StatusData;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.excel.SbdddcbjpcqkSheetType;
import com.tbea.ic.operation.common.excel.YszkgbSheetType;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler.NumberType;
import com.tbea.ic.operation.service.sbdddcbjpcqk.SbdddcbjpcqkServiceImpl;
import com.tbea.ic.operation.service.sbdddcbjpcqk.SbdddcbjpcqkService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "sbdddcbjpcqk")
public class SbdddcbjpcqkServlet {
	@Resource(name=SbdddcbjpcqkServiceImpl.NAME)
	SbdddcbjpcqkService sbdddcbjpcqkService;

	@RequestMapping(value = "show.do")
	public ModelAndView getSbdddcbjpcqk(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		return new ModelAndView("sbdddcbjpcqk/sbdddcbjpcqk", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getByqkglyddEntry(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		
		return new ModelAndView("sbdddcbjpcqk/sbdddcbjpcqkEntry", map);
	}
	
	private KglyddType getType(HttpServletRequest request){
		if (0 == Integer.valueOf(request.getParameter("type"))){
			return KglyddType.SCDY;
		}
		return KglyddType.SCLB;
	}
	
	@RequestMapping(value = "byqkglydd/update.do")
	public @ResponseBody byte[] getByqkglydd(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		List<List<String>> result = sbdddcbjpcqkService.getByqkglydd(d, getType(request));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "xlkglydd/update.do")
	public @ResponseBody byte[] updateXlkglydd(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		List<List<String>> result = sbdddcbjpcqkService.getXlkglydd(d, getType(request));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "byqkglydd/entry/update.do")
	public @ResponseBody byte[] updateByqkglyddEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		List<List<String>> result = sbdddcbjpcqkService.getByqkglyddEntry(d, getType(request));
		StatusData sd = new StatusData(false, result);
		return JSONObject.fromObject(sd).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	
	@RequestMapping(value = "xlkglydd/entry/update.do")
	public @ResponseBody byte[] updateXlkglyddEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		List<List<String>> result = sbdddcbjpcqkService.getXlkglyddEntry(d, getType(request));
		StatusData sd = new StatusData(false, result);
		return JSONObject.fromObject(sd).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "xlkglydd/entry/save.do")
	public @ResponseBody byte[] saveXlkglydd(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		ErrorCode err = sbdddcbjpcqkService.saveXlkglydd(d, getType(request));
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "byqkglydd/entry/save.do")
	public @ResponseBody byte[] saveByqkglydd(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		ErrorCode err = sbdddcbjpcqkService.saveByqkglydd(d, getType(request));
		return Util.response(err);
	}
	
	@RequestMapping(value = "byqkglydd/entry/submit.do")
	public @ResponseBody byte[] submitByqkglydd(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = sbdddcbjpcqkService.submitByqkglydd(d, getType(request));
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "xlkglydd/entry/submit.do")
	public @ResponseBody byte[] submitXlkglydd(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = sbdddcbjpcqkService.submitXlkglydd(d, getType(request));
		return Util.response(err);
	}
	
	@RequestMapping(value = "xlkglydd/export.do")
	public void exportXlkglydd(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		KglyddType type = getType(request);
		List<List<String>> ret = sbdddcbjpcqkService.getXlkglydd(d, type);
		ExcelTemplate template = null;
		if (type == KglyddType.SCDY){
			template = ExcelTemplate.createSbdddcbjpcqkTemplate(SbdddcbjpcqkSheetType.XLKGLYDD_SCDY);
		}else{
			template = ExcelTemplate.createSbdddcbjpcqkTemplate(SbdddcbjpcqkSheetType.XLKGLYDD_SCLB);
		}
				
		FormatterHandler handler = new HeaderFormatterHandler(null, new Integer[]{0});
		handler.next(new NumberFormatterHandler(NumberType.RESERVE_1));
		HSSFWorkbook workbook = template.getWorkbook();
		String name = workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < ret.size(); ++i){
			HSSFRow row = sheet.createRow(i + 3);
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, j, template, row.createCell(j), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
	
	
	@RequestMapping(value = "byqkglydd/export.do")
	public void exportByqkglydd(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		KglyddType type = getType(request);
		List<List<String>> ret = sbdddcbjpcqkService.getByqkglydd(d, type);
		ExcelTemplate template = null;
		if (type == KglyddType.SCDY){
			template = ExcelTemplate.createSbdddcbjpcqkTemplate(SbdddcbjpcqkSheetType.BYQKGLYDD_SCDY);
		}else{
			template = ExcelTemplate.createSbdddcbjpcqkTemplate(SbdddcbjpcqkSheetType.BYQKGLYDD_SCLB);
		}
		FormatterHandler handler = new HeaderFormatterHandler(null, new Integer[]{0});
		handler.next(new NumberFormatterHandler(NumberType.RESERVE_1));
		HSSFWorkbook workbook = template.getWorkbook();
		String name = workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < ret.size(); ++i){
			HSSFRow row = sheet.createRow(i + 3);
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, j, template, row.createCell(j), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
	
}
