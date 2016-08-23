package com.tbea.ic.operation.controller.servlet.cpzlqk.acptjjg.pdacptjjg;

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
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.PercentFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.controller.servlet.cpzlqk.CpzlqkResp;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.service.cpzlqk.pdacptjjg.PdacptjjgService;
import com.tbea.ic.operation.service.cpzlqk.pdacptjjg.PdacptjjgServiceImpl;

@Controller
@RequestMapping(value = "pdacptjjg")
public class PdacptjjgServlet {
	@Resource(name=PdacptjjgServiceImpl.NAME)
	PdacptjjgService pdacptjjgService;



	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getPdacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		
		
		List<List<String>> result = pdacptjjgService.getPdacptjjg(d, company, yjType);
		List<WaveItem> waveItems = null;
		if (yjType == YDJDType.YD){
			waveItems  = pdacptjjgService.getWaveValues(d, company);
		}else{
			waveItems  = pdacptjjgService.getJdWaveValues(d, company);
		}
		
		CpzlqkResp resp = new CpzlqkResp(result, waveItems);
		
		return JSONObject.fromObject(resp.format()).toString().getBytes("utf-8");
	}

	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updatePdacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		
		List<List<String>> result = pdacptjjgService.getPdacptjjgEntry(d, company);
		ZBStatus status = pdacptjjgService.getStatus(d, company);
		RawFormatterHandler handler = new RawEmptyHandler();
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("").format(result);
		CpzlqkResp resp = new CpzlqkResp(result, status);
		return JSONObject.fromObject(resp).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] savePdacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		
		ErrorCode err = pdacptjjgService.savePdacptjjg(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitPdacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		
		ErrorCode err = pdacptjjgService.submitPdacptjjg(d, data, company);
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "approve/update.do")
	public @ResponseBody byte[] updateApprovePdacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		
		List<List<String>> result = null;
		ZBStatus status = pdacptjjgService.getStatus(d, company);
		if (status == ZBStatus.APPROVED || status == ZBStatus.SUBMITTED){
		
			result = pdacptjjgService.getPdacptjjgEntry(d, company);
		
			RawFormatterHandler handler = new RawEmptyHandler();
			RawFormatterServer serv = new RawFormatterServer(handler);
			serv.acceptNullAs("--").format(result);
		}
		
		
		return JSONObject.fromObject(new CpzlqkResp(result, status)).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "approve/approve.do")
	public @ResponseBody byte[] approvePdacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		
		ErrorCode err = pdacptjjgService.approvePdacptjjg(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "approve/unapprove.do")
	public @ResponseBody byte[] unapprovePdacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		
		ErrorCode err = pdacptjjgService.unapprovePdacptjjg(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "export.do")
	public void exportPdacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		
		List<List<String>> result = pdacptjjgService.getPdacptjjg(d, company, yjType);
		ExcelTemplate template = ExcelTemplate.createCpzlqkTemplate(CpzlqkSheetType.BYQACPTJJG);
		
		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0, 1});
		handler.next(new PercentFormatterHandler(1, null, new Integer[]{4, 7}))
		.next(new NumberFormatterHandler(0));
		FormatterServer serv = new FormatterServer(handler, 0, 2);
		serv.addMergeRegion(new MergeRegion(0, 2, 2, result.size()));
		serv.format(result, template);

		String name = company.getName() + yjType.val() + template.getSheetName();

		template.write(response, name + ".xls");
	}
}