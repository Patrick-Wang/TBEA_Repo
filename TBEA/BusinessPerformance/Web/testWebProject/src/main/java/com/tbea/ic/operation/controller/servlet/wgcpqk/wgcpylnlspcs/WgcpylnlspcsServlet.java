package com.tbea.ic.operation.controller.servlet.wgcpqk.wgcpylnlspcs;

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

import org.apache.poi.hssf.usermodel.HSSFSheet;
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
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler.NumberType;
import com.tbea.ic.operation.controller.servlet.wgcpqk.WgcpqkType;
import com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs.WgcpylnlspcsService;
import com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs.WgcpylnlspcsServiceImpl;
import com.tbea.ic.operation.common.excel.YlfxwgcpylnlspcsSheetType;

@Controller
@RequestMapping(value = "wgcpylnlspcs")
public class WgcpylnlspcsServlet {
	@Resource(name = WgcpylnlspcsServiceImpl.NAME)
	WgcpylnlspcsService wgcpylnlspcsService;

	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	private WgcpqkType getType(HttpServletRequest request){
		if (11 == Integer.valueOf(request.getParameter("wgcpqkType"))){
			return WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH;
		} else if (12 == Integer.valueOf(request.getParameter("wgcpqkType"))) {
			return WgcpqkType.YLFX_WGCPYLNL_BYQ_DYDJ;
		}else if (13 == Integer.valueOf(request.getParameter("wgcpqkType"))) {
			return WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL;
		}else if (14 == Integer.valueOf(request.getParameter("wgcpqkType"))) {
			return WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL_T1;
		}else if (15 == Integer.valueOf(request.getParameter("wgcpqkType"))) {
			return WgcpqkType.YLFX_WGCPYLNL_XL_ZH;
		}else if (16 == Integer.valueOf(request.getParameter("wgcpqkType"))) {
			return WgcpqkType.YLFX_WGCPYLNL_XL_CPFL;
		}
		
		return WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH;
	}
	
	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] updateWgcpylnlspcs(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = wgcpylnlspcsService.getWgcpylnlspcs(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateWgcpylnlspcsEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = wgcpylnlspcsService.getWgcpylnlspcsEntry(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		
		ZBStatus status = wgcpylnlspcsService.getWgcpylnlspcsStatus(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		StatusData sd = new StatusData(ZBStatus.APPROVED == status, result);
		return JSONObject.fromObject(sd).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveWgcpylnlspcs(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = wgcpylnlspcsService.saveWgcpylnlspcs(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitWgcpylnlspcs(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		ErrorCode err = wgcpylnlspcsService.submitWgcpylnlspcs(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request), data);
		return Util.response(err);
	}
	
	private YlfxwgcpylnlspcsSheetType getYlfxwgcpylnlspcsSheetType(WgcpqkType wgcpqkType, Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		Integer num = (wgcpqkType.value() % WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH.value()) * 12 + cal.get(Calendar.MONTH);
		
		YlfxwgcpylnlspcsSheetType wgcpylnlspcsSheetType = YlfxwgcpylnlspcsSheetType.values()[num];
		
		return wgcpylnlspcsSheetType;
	}
	
	@RequestMapping(value = "export.do")
	public void exportWgcpylnlspcs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		WgcpqkType type = getType(request);
		
		List<List<String>> ret = wgcpylnlspcsService.getWgcpylnlspcs(d, company, type);
		
		ExcelTemplate template = ExcelTemplate.createYlfxwgcpylnlspcsTemplate(getYlfxwgcpylnlspcsSheetType(type, d));
				
		FormatterHandler handler = new HeaderFormatterHandler(null, new Integer[]{0});
		handler.next(new NumberFormatterHandler(NumberType.RESERVE_1));
		
		HSSFWorkbook workbook = template.getWorkbook();
		String name = company.getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < ret.size(); ++i){
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, j, template, sheet.getRow(i + 2).getCell(j), ret.get(i).get(j));
			}
		}
		template.write(response, name + "鏈�.xls");
	}
}
