package com.tbea.ic.operation.controller.servlet.cbfx.dmcbfx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.tbea.ic.operation.common.excel.CbfxSheetType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler.NumberType;
import com.tbea.ic.operation.controller.servlet.cbfx.CbfxType;
import com.tbea.ic.operation.service.cbfx.dmcbfx.DmcbfxService;
import com.tbea.ic.operation.service.cbfx.dmcbfx.DmcbfxServiceImpl;

@Controller
@RequestMapping(value = "dmcbfx")
public class DmcbfxServlet {
	@Resource(name=DmcbfxServiceImpl.NAME)
	DmcbfxService dmcbfxService;


	CbfxType getType(HttpServletRequest request){
		if (CbfxType.dmcbfx.ordinal()  == Integer.valueOf(request.getParameter("type")).intValue()){
			return CbfxType.dmcbfx;
		}
		return CbfxType.dmcbqsfx;
	}

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getDmcbfx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<List<String>> result = null;
		if (this.getType(request) == CbfxType.dmcbfx){
			result = dmcbfxService.getDmcbfx(d, company);
		}else{
			result = dmcbfxService.getDmcbqsfx(d, company);
		}
	
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"--\"").getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateDmcbfx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		List<List<String>> result = dmcbfxService.getDmcbfxEntry(d, company);
		return JSONArray.fromObject(result).toString().replaceAll("null", "\"\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveDmcbfx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = dmcbfxService.saveDmcbfx(d, data, company);
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitDmcbfx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = dmcbfxService.submitDmcbfx(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "export.do")
	public void exportDmcbfx(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		ExcelTemplate template = null;
		List<List<String>> ret = null;
		int base = 0;
		if (this.getType(request) == CbfxType.dmcbfx){
			template = ExcelTemplate.createCbfxTemplate(CbfxSheetType.DMCBFX);
			ret = dmcbfxService.getDmcbfx(d, company);
			base = 2;
		}else{
			base = 1;
			template = ExcelTemplate.createCbfxTemplate(CbfxSheetType.DMCBQSFX);
			ret = dmcbfxService.getDmcbqsfx(d, company);
		}
		
		FormatterHandler handler = new HeaderFormatterHandler(null, new Integer[]{0});
		handler.next(new NumberFormatterHandler(NumberType.RESERVE_1));
		
		HSSFWorkbook workbook = template.getWorkbook();
		String name = workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		for (int i = 0; i < ret.size(); ++i){
			HSSFRow row = sheet.createRow(i + base);
			for (int j = 0; j < ret.get(i).size(); ++j){
				handler.handle(null, j, template, row.createCell(j), ret.get(i).get(j));
			}
		}
		template.write(response, name + ".xls");
	}
	
}