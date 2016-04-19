package com.tbea.ic.operation.controller.servlet.wlydd.wlyddmlspcs;

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
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.tbea.ic.operation.common.excel.SbdddcbjpcqkSheetType;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler.NumberType;
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;
import com.tbea.ic.operation.service.wlydd.wlyddmlspcs.WlyddmlspcsService;
import com.tbea.ic.operation.service.wlydd.wlyddmlspcs.WlyddmlspcsServiceImpl;

@Controller
@RequestMapping(value = "wlydd")
public class WlyddmlspcsServlet {
	@Resource(name = WlyddmlspcsServiceImpl.NAME)
	WlyddmlspcsService wlyddmlspcsService;

	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	private WlyddType getType(HttpServletRequest request){
		if (11 == Integer.valueOf(request.getParameter("type"))){
			return WlyddType.YLFX_WLYMLSP_BYQ_ZH;
		} else if (12 == Integer.valueOf(request.getParameter("type"))) {
			return WlyddType.YLFX_WLYMLSP_BYQ_DYDJ;
		}else if (13 == Integer.valueOf(request.getParameter("type"))) {
			return WlyddType.YLFX_WLYMLSP_BYQ_CPFL;
		}else if (14 == Integer.valueOf(request.getParameter("type"))) {
			return WlyddType.YLFX_WLYMLSP_XL_ZH;
		}else if (15 == Integer.valueOf(request.getParameter("type"))) {
			return WlyddType.YLFX_WLYMLSP_XL_CPFL;
		}
		
		return WlyddType.YLFX_WLYMLSP_BYQ_ZH;
	}
	
	@RequestMapping(value = "wlyddmlspcs/update.do")
	public @ResponseBody byte[] updateWlyddmlspcs(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = wlyddmlspcsService.getWlyddmlspcs(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "wlyddmlspcs/entry/update.do")
	public @ResponseBody byte[] updateWlyddmlspcsEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = wlyddmlspcsService.getWlyddmlspcs(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
		//StatusData sd = new StatusData(false, result);
		//List<String> cplb = wlyddmlspcsService.getByqCplb();
		//EntryLyddData eld = new EntryLyddData();
		//eld.setStatusData(sd);
		//eld.setCplb(cplb);
		//return JSONObject.fromObject(result).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "wlyddmlspcs/entry/save.do")
	public @ResponseBody byte[] saveWlyddmlspcs(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = wlyddmlspcsService.saveWlyddmlspcs(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "wlyddmlspcs/entry/submit.do")
	public @ResponseBody byte[] submitWlyddmlspcs(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = wlyddmlspcsService.submitWlyddmlspcs(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "wlyddmlspcs/export.do")
	public void exportWlyddmlspcs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

	}
}
