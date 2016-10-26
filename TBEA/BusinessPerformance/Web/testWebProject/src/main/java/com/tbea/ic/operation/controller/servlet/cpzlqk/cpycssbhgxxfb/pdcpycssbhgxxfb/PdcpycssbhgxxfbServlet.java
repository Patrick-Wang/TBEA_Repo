package com.tbea.ic.operation.controller.servlet.cpzlqk.cpycssbhgxxfb.pdcpycssbhgxxfb;

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
import com.tbea.ic.operation.service.cpzlqk.pdcpycssbhgxxfb.BhgxxfbResp;
import com.tbea.ic.operation.service.cpzlqk.pdcpycssbhgxxfb.PdcpycssbhgxxfbService;
import com.tbea.ic.operation.service.cpzlqk.pdcpycssbhgxxfb.PdcpycssbhgxxfbServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Controller
@RequestMapping(value = "pdcpycssbhgxxfb")
public class PdcpycssbhgxxfbServlet {
	@Resource(name=PdcpycssbhgxxfbServiceImpl.NAME)
	PdcpycssbhgxxfbService pdcpycssbhgxxfbService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Autowired
	CpzlqkService cpzlqkService;

	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getPdcpycssbhgxxfb(HttpServletRequest request,
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
		List<String> bhglbs = pdcpycssbhgxxfbService.getBhglbs();
		List<List<String>> result = null;
		List<WaveItem> wis = null;
		if (comp == CompanyType.PDCY){
			result = pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(d, yjType, zts);
			if (yjType == YDJDType.JD){
				EasyCalendar cal = new EasyCalendar();
				cal.setTime(d);
				result.addAll(pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(cal.getLastYear().getDate(), yjType, zts));
			}
			wis = pdcpycssbhgxxfbService.getWaveItems(d, yjType, zts);
		}else{
			result = pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(d, yjType, company, zts);
			if (yjType == YDJDType.JD){
				EasyCalendar cal = new EasyCalendar();
				cal.setTime(d);
				result.addAll(pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(cal.getLastYear().getDate(), yjType, company, zts));
			}
			wis = pdcpycssbhgxxfbService.getWaveItems(d, yjType, company, zts);
		}
		
		RawFormatterHandler handler = new RawEmptyHandler(null, null);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		resp.setBhglbs(bhglbs);
		resp.setResult(result);
		resp.setWaveItems(wis);
		
		return JSONObject.fromObject(new BhgxxfbResp(bhglbs, result, wis)).toString().getBytes("utf-8");
	}


	@RequestMapping(value = "export.do")
	public void exportPdcpycssbhgxxfb(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		
		List<List<String>> result = null;
		List<Integer> zts = new ArrayList<Integer>();
		zts.add(ZBStatus.APPROVED.ordinal());
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		if (comp == CompanyType.PDCY){
			result = pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(d, yjType, zts);
		}else{
			result = pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(d, yjType, company, zts);
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