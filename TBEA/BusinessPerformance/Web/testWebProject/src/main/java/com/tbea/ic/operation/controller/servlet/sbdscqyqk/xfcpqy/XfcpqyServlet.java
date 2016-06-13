package com.tbea.ic.operation.controller.servlet.sbdscqyqk.xfcpqy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.excel.SbdscqyqkSheetType;
import com.tbea.ic.operation.common.formatter.v2.core.DefaultMatcher;
import com.tbea.ic.operation.common.formatter.v2.core.EmptyFormatter;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterServer;
import com.tbea.ic.operation.common.formatter.v2.core.Offset;
import com.tbea.ic.operation.common.formatter.v2.data.NumberFormatter;
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelHeaderFormatter;
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelOffsetFormatter;
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelTitleYearScrollerFilter;
import com.tbea.ic.operation.controller.servlet.sbdscqyqk.SbdscqyqkType;
import com.tbea.ic.operation.service.sbdscqyqk.xfcpqy.XfcpqyService;
import com.tbea.ic.operation.service.sbdscqyqk.xfcpqy.XfcpqyServiceImpl;

@Controller
@RequestMapping(value = "xfcpqy")
public class XfcpqyServlet {
	@Resource(name=XfcpqyServiceImpl.NAME)
	XfcpqyService xfcpqyService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	private SbdscqyqkType getType(HttpServletRequest request){
		if (0 == Integer.valueOf(request.getParameter("type"))){
			return SbdscqyqkType.YLFX_WGCPYLNL_BYQ;
		} else if (1 == Integer.valueOf(request.getParameter("type"))) {
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
		
		FormatterServer serv = new FormatterServer();
		serv.handlerBuilder()
			.add(new EmptyFormatter(DefaultMatcher.LEFT1_MATCHER))
			.add(new NumberFormatter(1))
			.server()
			.format(result);
		
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateXfcpqy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		
		List<List<String>> result = xfcpqyService.getXfcpqyEntry(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		FormatterServer serv = new FormatterServer();
		serv.handlerBuilder()
			.add(new NumberFormatter(1))
			.server()
			.acceptNullAs("")
			.format(result);
		
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
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
	
//	private SbdscqyqkSheetType getSheetType(SbdscqyqkType type, Date d){
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(d);
//		
//		Integer num = SbdscqyqkSheetType.XFCPQY_BYQ_1.ordinal() + type.value() * 12 + cal.get(Calendar.MONTH);
//		
//		SbdscqyqkSheetType sheetType = SbdscqyqkSheetType.values()[num];
//		
//		return sheetType;
//	}
	
	@RequestMapping(value = "export.do")
	public void exportXfcpqy(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		
		List<List<String>> ret = xfcpqyService.getXfcpqy(d, companyManager.getBMDBOrganization().getCompany(comp), getType(request));
		
		ExcelTemplate template = ExcelTemplate.createSbdscqyqkTemplate(SbdscqyqkSheetType.XFCPQY);
				
		FormatterServer serv = new FormatterServer();
		serv.handlerBuilder()
			.add(new EmptyFormatter(DefaultMatcher.LEFT1_MATCHER))
			.add(new NumberFormatter(1)) 
			.add(new ExcelTitleYearScrollerFilter(template, new Offset(0, 1), d))
			.to(FormatterServer.GROP_EXCEL)
			.add(new ExcelHeaderFormatter(DefaultMatcher.LEFT1_MATCHER, template, new Offset(2, 0)))
			.to(FormatterServer.GROP_EXCEL)
			.add(new ExcelOffsetFormatter(null, template, new Offset(2, 0)))
			.to(FormatterServer.GROP_EXCEL)
			.server()
			.format(ret);
		HSSFWorkbook workbook = template.getWorkbook();
		String name = companyManager.getBMDBOrganization().getCompany(comp).getName() + workbook.getSheetName(0);
		workbook.setSheetName(0, name);
		template.write(response, name + "月.xls");
	}
}