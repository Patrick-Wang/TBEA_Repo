package com.tbea.ic.operation.controller.servlet.cpzlqk.byqcpycssbhgwtmx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.excel.CpzlqkSheetType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.FormatterServer;
import com.tbea.ic.operation.common.formatter.excel.HeaderCenterFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.MergeRegion;
import com.tbea.ic.operation.common.formatter.excel.TextFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.controller.servlet.cpzlqk.ByqBhgType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.CpzlqkResp;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgwtmx.ByqcpycssbhgwtmxService;
import com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgwtmx.ByqcpycssbhgwtmxServiceImpl;

@Controller
@RequestMapping(value = "byqcpycssbhgwtmx")
public class ByqcpycssbhgwtmxServlet {
	@Resource(name=ByqcpycssbhgwtmxServiceImpl.NAME)
	ByqcpycssbhgwtmxService byqcpycssbhgwtmxService;



	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getByqcpycssbhgwtmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		ByqBhgType bhgType = ByqBhgType.valueOf(Integer.valueOf(request.getParameter("bhgType")));
		List<List<String>> result = byqcpycssbhgwtmxService.getByqcpycssbhgwtmx(d, yjType, bhgType);
		
		RawFormatterHandler handler = new RawEmptyHandler(null, null);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateByqcpycssbhgwtmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		ByqBhgType bhgType = ByqBhgType.valueOf(Integer.valueOf(request.getParameter("bhgType")));
		List<List<String>> result = byqcpycssbhgwtmxService.getByqcpycssbhgwtmxEntry(d, company, bhgType);
		List<String> zrlb = byqcpycssbhgwtmxService.getZrlb();
		List<String> bhglx = byqcpycssbhgwtmxService.getBhglx();
		ZBStatus status = byqcpycssbhgwtmxService.getStatus(d, company, bhgType);
		RawFormatterHandler handler = new RawEmptyHandler();
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("").format(result);
		CpzlqkResp resp = new CpzlqkResp(result, status, zrlb, bhglx);
		return JSONObject.fromObject(resp).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveByqcpycssbhgwtmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		ByqBhgType bhgType = ByqBhgType.valueOf(Integer.valueOf(request.getParameter("bhgType")));
		ErrorCode err = byqcpycssbhgwtmxService.saveByqcpycssbhgwtmx(d, data, company, bhgType);
		return Util.response(err);
	}
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitByqcpycssbhgwtmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		ByqBhgType bhgType = ByqBhgType.valueOf(Integer.valueOf(request.getParameter("bhgType")));
		ErrorCode err = byqcpycssbhgwtmxService.submitByqcpycssbhgwtmx(d, data, company, bhgType);
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "approve/update.do")
	public @ResponseBody byte[] updateApproveByqcpycssbhgwtmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		ByqBhgType bhgType = ByqBhgType.valueOf(Integer.valueOf(request.getParameter("bhgType")));
		List<List<String>> result = null;
		ZBStatus status = byqcpycssbhgwtmxService.getStatus(d, company, bhgType);
		if (status == ZBStatus.APPROVED || status == ZBStatus.SUBMITTED){
			
			result = byqcpycssbhgwtmxService.getByqcpycssbhgwtmxEntry(d, company, bhgType);
		
			RawFormatterHandler handler = new RawEmptyHandler();
			RawFormatterServer serv = new RawFormatterServer(handler);
			serv.acceptNullAs("--").format(result);
		}
		
		
		return JSONObject.fromObject(new CpzlqkResp(result, status)).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "approve/approve.do")
	public @ResponseBody byte[] approveByqcpycssbhgwtmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = byqcpycssbhgwtmxService.approveByqcpycssbhgwtmx(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "approve/unapprove.do")
	public @ResponseBody byte[] unapproveByqcpycssbhgwtmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		
		ErrorCode err = byqcpycssbhgwtmxService.unapproveByqcpycssbhgwtmx(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "export.do")
	public void exportByqcpycssbhgwtmx(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		ByqBhgType bhgType = ByqBhgType.valueOf(Integer.valueOf(request.getParameter("bhgType")));
		
		List<List<String>> result = byqcpycssbhgwtmxService.getByqcpycssbhgwtmx(d, yjType, bhgType);
		ExcelTemplate template = ExcelTemplate.createCpzlqkTemplate(CpzlqkSheetType.BYQCPYCSSBHGWTMX);
	
		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0});
		handler.next(new TextFormatterHandler(null, null));
		FormatterServer serv = new FormatterServer(handler, 0, 1);
		serv.addMergeRegion(new MergeRegion(0, 1, 1, result.size()));
		serv.format(result, template);
	
		String name = yjType.val() + bhgType.val() + template.getSheetName();

		template.write(response, name + ".xls");
	}
}