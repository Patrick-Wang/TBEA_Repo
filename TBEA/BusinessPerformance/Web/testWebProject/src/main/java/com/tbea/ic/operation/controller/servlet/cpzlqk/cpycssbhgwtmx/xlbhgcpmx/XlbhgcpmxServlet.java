package com.tbea.ic.operation.controller.servlet.cpzlqk.cpycssbhgwtmx.xlbhgcpmx;

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
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.cpzlqk.CpzlqkService;
import com.tbea.ic.operation.service.cpzlqk.xlbhgcpmx.XlbhgcpmxService;
import com.tbea.ic.operation.service.cpzlqk.xlbhgcpmx.XlbhgcpmxServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.excel.FormatterHandler;
import com.xml.frame.report.util.excel.FormatterServer;
import com.xml.frame.report.util.excel.HeaderCenterFormatterHandler;
import com.xml.frame.report.util.excel.MergeRegion;
import com.xml.frame.report.util.excel.TextFormatterHandler;
import com.xml.frame.report.util.raw.RawEmptyHandler;
import com.xml.frame.report.util.raw.RawFormatterHandler;
import com.xml.frame.report.util.raw.RawFormatterServer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "xlbhgcpmx")
public class XlbhgcpmxServlet {
	@Resource(name=XlbhgcpmxServiceImpl.NAME)
	XlbhgcpmxService xlbhgcpmxService;

	@Autowired
	CpzlqkService cpzlqkService;

	@Autowired
	ExtendAuthorityService extendAuthService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getXlbhgcpmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
			
		Date d = Date.valueOf(request.getParameter("date"));
		PageType pageType = PageType.valueOf(Integer.valueOf(request.getParameter("pageType")));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		if (null == company) {
			company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		}
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

					
		List<List<String>> result = null;
		if (comp == CompanyType.XLCY){
			result = xlbhgcpmxService.getXlbhgcpmx(d, zts);
		}else{
			result = xlbhgcpmxService.getXlbhgcpmx(d, company, zts);
		}
		
		RawFormatterHandler handler = new RawEmptyHandler(null, null);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		resp.setTjjg(result);
		
		return JSONObject.fromObject(resp).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/update.do")
	public @ResponseBody byte[] updateXlbhgcpmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		if (null == company) {
			company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		}
		List<List<String>> result = xlbhgcpmxService.getXlbhgcpmxEntry(d, company);
		List<String> zrlb = xlbhgcpmxService.getZrlb();
		List<String> bhglx = xlbhgcpmxService.getBhglx();
		ZBStatus status = xlbhgcpmxService.getStatus(d, company);
		RawFormatterHandler handler = new RawEmptyHandler();
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("").format(result);
		CpzlqkResp resp = new CpzlqkResp(result, status, zrlb, bhglx);
		return JSONObject.fromObject(resp).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "entry/save.do")
	public @ResponseBody byte[] saveXlbhgcpmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		if (null == company) {
			company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		}
		ErrorCode err = xlbhgcpmxService.saveXlbhgcpmx(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "entry/submit.do")
	public @ResponseBody byte[] submitXlbhgcpmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		if (null == company) {
			company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		}
		ErrorCode err = xlbhgcpmxService.submitXlbhgcpmx(d, data, company);
		return Util.response(err);
	}
	
	
	@RequestMapping(value = "approve/update.do")
	public @ResponseBody byte[] updateApproveXlbhgcpmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		if (null == company) {
			company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		}
		List<List<String>> result = null;
		ZBStatus status = xlbhgcpmxService.getStatus(d, company);
		if (status == ZBStatus.APPROVED || status == ZBStatus.SUBMITTED){
			
			result = xlbhgcpmxService.getXlbhgcpmxEntry(d, company);
		
			RawFormatterHandler handler = new RawEmptyHandler();
			RawFormatterServer serv = new RawFormatterServer(handler);
			serv.acceptNullAs("--").format(result);
		}
		
		
		return JSONObject.fromObject(new CpzlqkResp(true, result, status)).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "updateStatus.do")
	public @ResponseBody byte[] updateStatus(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBStatus zt = ZBStatus.valueOf(Integer.valueOf(request.getParameter("zt")));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		if (null == company) {
			company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		}
		ErrorCode err = xlbhgcpmxService.updateStatus(d, company, zt);
		return Util.response(err);
	}
	
	@RequestMapping(value = "approve/approve.do")
	public @ResponseBody byte[] approveXlbhgcpmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		if (null == company) {
			company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		}
		ErrorCode err = xlbhgcpmxService.approveXlbhgcpmx(d, data, company);
		return Util.response(err);
	}
	
	@RequestMapping(value = "approve/unapprove.do")
	public @ResponseBody byte[] unapproveXlbhgcpmx(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		if (null == company) {
			company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		}
		ErrorCode err = xlbhgcpmxService.unapproveXlbhgcpmx(d, data, company);
		return Util.response(err);
	}	

	@RequestMapping(value = "export.do")
	public void exportXlbhgcpmx(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		
		List<List<String>> result = null;
		List<Integer> zts = new ArrayList<Integer>();
		zts.add(ZBStatus.APPROVED.ordinal());
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		if (null == company) {
			company = companyManager.getBMDBOrganization().getCompanyByType(comp);
		}
		if (comp == CompanyType.XLCY){
			result = xlbhgcpmxService.getXlbhgcpmx(d, zts);
		}else{
			result = xlbhgcpmxService.getXlbhgcpmx(d, company, zts);
		}
		
		ExcelHelper template = ExcelTemplate.createCpzlqkTemplate(CpzlqkSheetType.XLBHGCPMX);
	
		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0});
		handler.next(new TextFormatterHandler(null, null));
		FormatterServer serv = new FormatterServer(handler, 0, 1);
		serv.addMergeRegion(new MergeRegion(0, 1, 2, result.size()));
		serv.format(result, template);
	
		String name = template.getSheetName();

		template.write(response, name + ".xls");
	}
}