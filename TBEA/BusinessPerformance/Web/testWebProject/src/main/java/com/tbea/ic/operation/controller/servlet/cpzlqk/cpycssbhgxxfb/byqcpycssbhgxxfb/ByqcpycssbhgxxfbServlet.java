package com.tbea.ic.operation.controller.servlet.cpzlqk.cpycssbhgxxfb.byqcpycssbhgxxfb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.EasyCalendar;
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
import com.tbea.ic.operation.common.querier.QuerierFactory;
import com.tbea.ic.operation.common.querier.ZBStatusQuerier;
import com.tbea.ic.operation.controller.servlet.cpzlqk.PageType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.cpzlqk.CpzlqkService;
import com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgxxfb.BhgxxfbResp;
import com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgxxfb.ByqcpycssbhgxxfbService;
import com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgxxfb.ByqcpycssbhgxxfbServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Controller
@RequestMapping(value = "byqcpycssbhgxxfb")
public class ByqcpycssbhgxxfbServlet {
	@Resource(name=ByqcpycssbhgxxfbServiceImpl.NAME)
	ByqcpycssbhgxxfbService byqcpycssbhgxxfbService;

	@Autowired
	CpzlqkService cpzlqkService;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getByqcpycssbhgxxfb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		PageType pageType = PageType.valueOf(Integer.valueOf(request.getParameter("pageType")));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		BhgxxfbResp resp = new BhgxxfbResp();
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
		List<String> bhglbs = byqcpycssbhgxxfbService.getBhglbs();
		List<List<String>> result = null;
		List<WaveItem> wis = null;

		if (comp == CompanyType.BYQCY){
			result = byqcpycssbhgxxfbService.getByqcpycssbhgxxfb(d, yjType, zts);
			if (yjType == YDJDType.JD){
				EasyCalendar cal = new EasyCalendar();
				cal.setTime(d);
				result.addAll(byqcpycssbhgxxfbService.getByqcpycssbhgxxfb(cal.getLastYear().getDate(), yjType, zts));
			}
			wis = byqcpycssbhgxxfbService.getWaveItems(d, yjType, zts);
		}else{
			result = byqcpycssbhgxxfbService.getByqcpycssbhgxxfb(d, yjType, company, zts);
			if (yjType == YDJDType.JD){
				EasyCalendar cal = new EasyCalendar();
				cal.setTime(d);
				result.addAll(byqcpycssbhgxxfbService.getByqcpycssbhgxxfb(cal.getLastYear().getDate(), yjType, company, zts));
			}
			wis = byqcpycssbhgxxfbService.getWaveItems(d, yjType, company, zts);
		}
		
		RawFormatterHandler handler = new RawEmptyHandler(null, null);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		resp.setBhglbs(bhglbs);
		resp.setResult(result);
		resp.setWaveItems(wis);
		
		return JSONObject.fromObject(resp).toString().getBytes("utf-8");
	}


	@RequestMapping(value = "export.do")
	public void exportByqcpycssbhgxxfb(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		
		List<List<String>> result = null;
		List<Integer> zts = new ArrayList<Integer>();
		zts.add(ZBStatus.APPROVED.ordinal());
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		if (comp == CompanyType.BYQCY){
			result = byqcpycssbhgxxfbService.getByqcpycssbhgxxfb(d, yjType, zts);
		}else{
			result = byqcpycssbhgxxfbService.getByqcpycssbhgxxfb(d, yjType, company, zts);
		}
		
		ExcelTemplate template = ExcelTemplate.createCpzlqkTemplate(CpzlqkSheetType.BYQCPYCSSBHGXXFB);
	
		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0, 1});
		handler.next(new TextFormatterHandler());
		FormatterServer serv = new FormatterServer(handler, 0, 1);
		serv.addMergeRegion(new MergeRegion(0, 1, 1, result.size()));
		serv.format(result, template);

		String name = yjType.val() + template.getSheetName();

		template.write(response, name + ".xls");
	}
}