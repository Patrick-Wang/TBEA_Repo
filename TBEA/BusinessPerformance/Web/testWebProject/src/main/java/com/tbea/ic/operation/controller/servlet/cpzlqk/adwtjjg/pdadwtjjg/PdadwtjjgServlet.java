package com.tbea.ic.operation.controller.servlet.cpzlqk.adwtjjg.pdadwtjjg;

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
import com.tbea.ic.operation.common.querier.QuerierFactory;
import com.tbea.ic.operation.common.querier.ZBStatusQuerier;
import com.tbea.ic.operation.controller.servlet.cpzlqk.CpzlqkResp;
import com.tbea.ic.operation.controller.servlet.cpzlqk.PageType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.cpzlqk.CpzlqkService;
import com.tbea.ic.operation.service.cpzlqk.pdadwtjjg.PdadwtjjgService;
import com.tbea.ic.operation.service.cpzlqk.pdadwtjjg.PdadwtjjgServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Controller
@RequestMapping(value = "pdadwtjjg")
public class PdadwtjjgServlet {
	@Resource(name=PdadwtjjgServiceImpl.NAME)
	PdadwtjjgService pdadwtjjgService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Autowired
	CpzlqkService cpzlqkService;
	
	@Autowired
	ExtendAuthorityService extendAuthService;
	
	
	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getPdadwtjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		PageType pageType = PageType.valueOf(Integer.valueOf(request.getParameter("pageType")));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		
		CpzlqkResp resp = new CpzlqkResp();
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
				
		if (comp == CompanyType.PDCY){
			List<List<String>> result = pdadwtjjgService.getPdadwtjjg(d, yjType, zts);
			List<WaveItem> waveItems = new ArrayList<WaveItem>();
			if (yjType == YDJDType.YD){
				waveItems = pdadwtjjgService.getWaveItems(d, zts);
			}
			resp.setWaveItems(waveItems);
			resp.setTjjg(result);
		}else{
			List<List<String>> result = pdadwtjjgService.getPdadwtjjg(d, yjType, company, zts);
			List<WaveItem> waveItems = pdadwtjjgService.getPdYdAdwtjjgWaveItems(d, company, zts);
			resp.setWaveItems(waveItems);
			resp.setTjjg(result);
		}

		return JSONObject.fromObject(resp.format()).toString().getBytes("utf-8");
	}


	@RequestMapping(value = "export.do")
	public void exportPdadwtjjg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		List<List<String>> result = null;
		
		List<Integer> zts = new ArrayList<Integer>();
		zts.add(ZBStatus.APPROVED.ordinal());
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		if (comp == CompanyType.PDCY){
			result = pdadwtjjgService.getPdadwtjjg(d, yjType, zts);
		}else{
			result = pdadwtjjgService.getPdadwtjjg(d, yjType, company, zts);
		}
		
		ExcelTemplate template = ExcelTemplate.createCpzlqkTemplate(CpzlqkSheetType.BYQADWTJJG);
	
		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0, 1});
		handler.next(new PercentFormatterHandler(1, null, new Integer[]{4, 7}))
		.next(new NumberFormatterHandler(0));
		FormatterServer serv = new FormatterServer(handler, 0, 2);
		serv.addMergeRegion(new MergeRegion(0, 2, 1, result.size()));
		serv.format(result, template);

		String name = yjType.val() + template.getSheetName();

		template.write(response, name + ".xls");
	}
}