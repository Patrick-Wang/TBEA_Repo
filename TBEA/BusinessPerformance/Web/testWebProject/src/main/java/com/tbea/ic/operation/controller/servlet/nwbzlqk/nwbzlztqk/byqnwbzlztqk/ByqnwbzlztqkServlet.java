package com.tbea.ic.operation.controller.servlet.nwbzlqk.nwbzlztqk.byqnwbzlztqk;

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
import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.common.querier.QuerierFactory;
import com.tbea.ic.operation.common.querier.ZBStatusQuerier;
import com.tbea.ic.operation.controller.servlet.nwbzlqk.CpzlqkResp;
import com.tbea.ic.operation.controller.servlet.cpzlqk.PageType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.cpzlqk.CpzlqkService;
import com.tbea.ic.operation.service.cpzlqk.byqnwbzlztqk.ByqnwbzlztqkService;
import com.tbea.ic.operation.service.cpzlqk.byqnwbzlztqk.ByqnwbzlztqkServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Controller
@RequestMapping(value = "byqnwbzlztqk")
public class ByqnwbzlztqkServlet {
	@Resource(name=ByqnwbzlztqkServiceImpl.NAME)
	ByqnwbzlztqkService byqnwbzlztqkService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Autowired
	CpzlqkService cpzlqkService;
	
	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getByqnwbzlztqk(HttpServletRequest request,
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
				ZBStatus status = cpzlqkService.getNwbzlwtStatus(d, company);
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
		List<WaveItem> wis = null;
		if (comp == CompanyType.BYQCY){
			
			if (yjType == YDJDType.JD){
				result = byqnwbzlztqkService.getJdnwbzlqk(d, zts);
			}else{
				result = byqnwbzlztqkService.getYdnwbzlqk(d, zts);
			}
			wis = byqnwbzlztqkService.getWaveItems(d, yjType, zts);
		}else{
			if (yjType == YDJDType.JD){
				result = byqnwbzlztqkService.getJdnwbzlqk(d, company, zts);
			}else{
				result = byqnwbzlztqkService.getYdnwbzlqk(d, company, zts);
			}
			wis = byqnwbzlztqkService.getWaveItems(d, yjType, company, zts);
		}
		
		RawFormatterHandler handler = new RawEmptyHandler(null, null);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		
		resp.setTjjg(result);
		resp.setWaveItems(wis);
		return JSONObject.fromObject(resp).toString().getBytes("utf-8");
	}


}