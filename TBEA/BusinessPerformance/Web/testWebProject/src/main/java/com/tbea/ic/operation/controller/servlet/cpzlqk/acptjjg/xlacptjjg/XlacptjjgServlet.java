package com.tbea.ic.operation.controller.servlet.cpzlqk.acptjjg.xlacptjjg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.tbea.ic.operation.common.querier.QuerierFactory;
import com.tbea.ic.operation.common.querier.ZBStatusQuerier;
import com.tbea.ic.operation.controller.servlet.cpzlqk.CpzlqkResp;
import com.tbea.ic.operation.controller.servlet.cpzlqk.PageType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.cpzlqk.CpzlqkService;
import com.tbea.ic.operation.service.cpzlqk.xlacptjjg.XlacptjjgService;
import com.tbea.ic.operation.service.cpzlqk.xlacptjjg.XlacptjjgServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.excel.FormatterHandler;
import com.xml.frame.report.util.excel.FormatterServer;
import com.xml.frame.report.util.excel.HeaderCenterFormatterHandler;
import com.xml.frame.report.util.excel.MergeRegion;
import com.xml.frame.report.util.excel.NumberFormatterHandler;
import com.xml.frame.report.util.raw.RawFormatterHandler;
import com.xml.frame.report.util.raw.RawFormatterServer;
import com.xml.frame.report.util.raw.RawNumberFormatterHandler;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "xlacptjjg")
public class XlacptjjgServlet {
	@Resource(name=XlacptjjgServiceImpl.NAME)
	XlacptjjgService xlacptjjgService;

	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Autowired
	CpzlqkService cpzlqkService;


	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getXlacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		PageType pageType = PageType.valueOf(Integer.valueOf(request.getParameter("pageType")));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		CpzlqkResp resp = new CpzlqkResp(true);
		List<Integer> zts = new ArrayList<Integer>();
		if (pageType == PageType.SHOW){
			zts.add(ZBStatus.APPROVED.ordinal());
		} else {
			Account account = SessionManager.getAccount(request.getSession());
			List<Integer> auths = extendAuthService.getAuths(account, company);
			if (request.getParameter("zt") == null){
				ZBStatus status = cpzlqkService.getCpzlqkStatus(d, company);
				resp.setZt(status.ordinal());
			}
			if (pageType == PageType.ENTRY){
				ZBStatusQuerier querier = QuerierFactory.createZlEntryQuerier();			
				zts = querier.queryStatus(auths);
			}  else if (pageType == PageType.APPROVE){
				ZBStatusQuerier querier = QuerierFactory.createZlApproveQuerier();			
				zts = querier.queryStatus(auths);
			}
			
			if (zts.isEmpty()){
				zts.add(ZBStatus.NONE.ordinal());
			}
		}
		List<List<String>> result = xlacptjjgService.getXlacptjjg(d, company, yjType, zts);
		
		List<WaveItem> waveItems = null;
		if (yjType == YDJDType.YD){
			waveItems  = xlacptjjgService.getWaveValues(d, company, zts);
		}
		
		resp.setWaveItems(waveItems);
		resp.setTjjg(result);
		
		return JSONObject.fromObject(resp.format()).toString().getBytes("utf-8");
	}

	
	@RequestMapping(value = "updateStatus.do")
	public @ResponseBody byte[] updateStatus(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBStatus zt = ZBStatus.valueOf(Integer.valueOf(request.getParameter("zt")));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		
		ErrorCode err = xlacptjjgService.updateStatus(d, company, zt);
		return Util.response(err);
	}
	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateXlacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		
		List<List<String>> result = xlacptjjgService.getXlacptjjgEntry(d, company);
		ZBStatus status = xlacptjjgService.getStatus(d, company);
		RawFormatterHandler handler = new RawNumberFormatterHandler(4, null, new Integer[]{3, 4}).trimZero(true);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("").format(result);
		CpzlqkResp resp = new CpzlqkResp(true, result, status);
		return JSONObject.fromObject(resp).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveXlacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		
		ErrorCode err = xlacptjjgService.saveXlacptjjg(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitXlacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		
		ErrorCode err = xlacptjjgService.submitXlacptjjg(d, data, company);
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "approve/update.do")
	public @ResponseBody byte[] updateApproveXlacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		List<List<String>> result = null;
		ZBStatus status = xlacptjjgService.getStatus(d, company);
		if (status == ZBStatus.APPROVED || status == ZBStatus.SUBMITTED){
			
			result = xlacptjjgService.getXlacptjjgEntry(d, company);
		
			RawFormatterHandler handler = new RawNumberFormatterHandler(4, null, new Integer[]{3, 4}).trimZero(true);
			RawFormatterServer serv = new RawFormatterServer(handler);
			serv.acceptNullAs("--").format(result);
		}
		
		return JSONObject.fromObject(new CpzlqkResp(true, result, status)).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "approve/approve.do")
	public @ResponseBody byte[] approveXlacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		
		ErrorCode err = xlacptjjgService.approveXlacptjjg(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "approve/unapprove.do")
	public @ResponseBody byte[] unapproveXlacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		
		ErrorCode err = xlacptjjgService.unapproveXlacptjjg(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "export.do")
	public void exportXlacptjjg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		List<Integer> zts = new ArrayList<Integer>();
		zts.add(ZBStatus.APPROVED.ordinal());
		List<List<String>> result = xlacptjjgService.getXlacptjjg(d, company, yjType, zts);
		ExcelHelper template = ExcelTemplate.createCpzlqkTemplate(CpzlqkSheetType.XLACPTJJG);
	
		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0, 1});
		handler.next(new NumberFormatterHandler(0));
		FormatterServer serv = new FormatterServer(handler, 0, 2);
		serv.addMergeRegion(new MergeRegion(0, 2, 1, result.size()));
		serv.format(result, template);

		String name = company.getName() + yjType.val() + template.getSheetName();

		template.write(response, name + ".xls");
	}
}