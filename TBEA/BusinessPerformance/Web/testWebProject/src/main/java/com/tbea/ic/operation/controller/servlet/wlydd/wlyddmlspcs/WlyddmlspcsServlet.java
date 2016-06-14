package com.tbea.ic.operation.controller.servlet.wlydd.wlyddmlspcs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.StatusData;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.excel.YlfxwlyddmlspcsSheetType;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.FormatterServer;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
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
		}else if (16 == Integer.valueOf(request.getParameter("type"))) {
			return WlyddType.YLFX_WLYMLSP_BYQ_ZZY;
		}
		
		return WlyddType.YLFX_WLYMLSP_BYQ_ZH;
	}
	
	@RequestMapping(value = "wlyddmlspcs/update.do")
	public @ResponseBody byte[] updateWlyddmlspcs(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = wlyddmlspcsService.getWlyddmlspcs(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		RawFormatterHandler handler = new RawEmptyHandler(null, null);
		//handler.next(new RawNumberFormatterHandler(1));
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "wlyddmlspcs/entry/update.do")
	public @ResponseBody byte[] updateWlyddmlspcsEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = wlyddmlspcsService.getWlyddmlspcsEntry(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		
		ZBStatus status = wlyddmlspcsService.getWlyddmlspcsStatus(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		StatusData sd = new StatusData(ZBStatus.APPROVED == status, result);
		return JSONObject.fromObject(sd).toString().replaceAll("null", "\"\"").getBytes("utf-8");
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
	
	private YlfxwlyddmlspcsSheetType getYlfxwlyddmlspcsSheetType(WlyddType wlyddType, Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		Integer num = cal.get(Calendar.MONTH);
		
		YlfxwlyddmlspcsSheetType wlyddmlspcsSheetType = YlfxwlyddmlspcsSheetType.values()[num];
		
		return wlyddmlspcsSheetType;
	}
	
	@RequestMapping(value = "wlyddmlspcs/export.do")
	public void exportWlyddmlspcs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		WlyddType type = getType(request);
		
		List<List<String>> ret = wlyddmlspcsService.getWlyddmlspcs(d, company, type);
		
		ExcelTemplate template = ExcelTemplate.createYlfxwlyddmlspcsTemplate(getYlfxwlyddmlspcsSheetType(type, d));
				
		FormatterHandler handler = new HeaderFormatterHandler(null, null);
		//handler.next(new NumberFormatterHandler(1));
		
		
		
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		
		FormatterServer serv = new FormatterServer(handler, 0, 2);
		serv.format(ret, template);
		
//		HSSFSheet sheet = workbook.getSheetAt(0);
//		for (int i = 0; i < ret.size(); ++i){
//			for (int j = 0; j < ret.get(i).size(); ++j){
//				handler.handle(null, j, template, sheet.createRow(i + 2).createCell(j), ret.get(i).get(j));
//			}
//		}
		template.write(response, name + "月.xls");
	}
}
