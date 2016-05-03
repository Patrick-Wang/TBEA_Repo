package com.tbea.ic.operation.controller.servlet.sbdscqyqk.xfcpqy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
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
import com.tbea.ic.operation.common.excel.SbdscqyqkSheetType;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler.NumberType;
import com.tbea.ic.operation.controller.servlet.sbdscqyqk.SbdscqyqkType;
import com.tbea.ic.operation.controller.servlet.wgcpqk.WgcpqkType;
import com.tbea.ic.operation.service.sbdscqyqk.xfcpqy.XfcpqyService;
import com.tbea.ic.operation.service.sbdscqyqk.xfcpqy.XfcpqyServiceImpl;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "xfcpqy")
public class XfcpqyServlet {
	@Resource(name=XfcpqyServiceImpl.NAME)
	XfcpqyService xfcpqyService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	private SbdscqyqkType getType(HttpServletRequest request){
		if (1 == Integer.valueOf(request.getParameter("type"))){
			return SbdscqyqkType.YLFX_WGCPYLNL_BYQ;
		} else if (2 == Integer.valueOf(request.getParameter("type"))) {
			return SbdscqyqkType.YLFX_WGCPYLNL_XL;
		}
		
		return SbdscqyqkType.YLFX_WGCPYLNL_BYQ;
	}
	
	
	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getXfcpqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = xfcpqyService.getXfcpqy(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateXfcpqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		
		List<List<String>> result = xfcpqyService.getXfcpqyEntry(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveXfcpqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		
		ErrorCode err = xfcpqyService.saveXfcpqy(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request), data);
		return Util.response(err);
	}
	
	
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitXfcpqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		
		ErrorCode err = xfcpqyService.submitXfcpqy(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request), data);
		return Util.response(err);
	}
	
	@RequestMapping(value = "export.do")
	public void exportXfcpqy(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
//		Date d = Date.valueOf(request.getParameter("date"));
//		CompanyType comp = CompanySelection.getCompany(request);
//		Company company = companyManager.getBMDBOrganization().getCompany(comp);
//		
//		List<List<String>> ret = xfcpqyService.getXfcpqy(d, company);
//		ExcelTemplate template = ExcelTemplate.createSbdscqyqkTemplate(SbdscqyqkSheetType.XFCPQY);
//	
//		FormatterHandler handler = new HeaderFormatterHandler(null, new Integer[]{0});
//		handler.next(new NumberFormatterHandler(NumberType.RESERVE_1));
//		HSSFWorkbook workbook = template.getWorkbook();
//		String name = workbook.getSheetName(0);
//		workbook.setSheetName(0, name);
//		HSSFSheet sheet = workbook.getSheetAt(0);
//		for (int i = 0; i < ret.size(); ++i){
//			HSSFRow row = sheet.createRow(i + 2);
//			for (int j = 0; j < ret.get(i).size(); ++j){
//				handler.handle(null, j, template, row.createCell(j), ret.get(i).get(j));
//			}
//		}
//		template.write(response, name + ".xls");
	}
}