package com.tbea.ic.operation.controller.servlet.cpzlqk.adwtjjg.byqadwtjjg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
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
import com.tbea.ic.operation.controller.servlet.cpzlqk.CpzlqkResp;
import com.tbea.ic.operation.controller.servlet.cpzlqk.PageType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.cpzlqk.byqadwtjjg.ByqadwtjjgService;
import com.tbea.ic.operation.service.cpzlqk.byqadwtjjg.ByqadwtjjgServiceImpl;

@Controller
@RequestMapping(value = "byqadwtjjg")
public class ByqadwtjjgServlet {
	@Resource(name=ByqadwtjjgServiceImpl.NAME)
	ByqadwtjjgService byqadwtjjgService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getByqadwtjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		PageType pageType = PageType.valueOf(Integer.valueOf(request.getParameter("pageType")));
		ZBStatus status = ZBStatus.NONE;
		if (request.getParameter("zt") != null){
			status = ZBStatus.valueOf(Integer.valueOf(request.getParameter("zt")));
		}		
		
		List<Integer> auths = JSONArray.fromObject(request.getParameter("auths"));
		List<Integer> zts = new ArrayList<Integer>();
		if (pageType == PageType.SHOW){
			zts.add(ZBStatus.APPROVED.ordinal());
		} else if (pageType == PageType.ENTRY){
			zts.add(ZBStatus.SAVED.ordinal());
			zts.add(ZBStatus.SUBMITTED.ordinal());
			zts.add(ZBStatus.APPROVED.ordinal());
			zts.add(ZBStatus.INTER_APPROVED_1.ordinal());
			zts.add(ZBStatus.INTER_APPROVED_2.ordinal());
		}  else if (pageType == PageType.APPROVE){
			
			if (status == ZBStatus.NONE || status == ZBStatus.SAVED ){

			} else if (status == ZBStatus.SUBMITTED){
				if (auths.contains(53)){
					zts.add(ZBStatus.SUBMITTED.ordinal());
				}
			} else if (status == ZBStatus.INTER_APPROVED_1){
				if (auths.contains(54) || auths.contains(53)){
					zts.add(ZBStatus.INTER_APPROVED_1.ordinal());
				}
			} else if (status == ZBStatus.INTER_APPROVED_2){
				if (auths.contains(53) || auths.contains(AuthType.QualityApprove.ordinal()) || auths.contains(54)){
					zts.add(ZBStatus.INTER_APPROVED_2.ordinal());
				}
			} else if (status == ZBStatus.APPROVED){
				if (auths.contains(53) || auths.contains(AuthType.QualityApprove.ordinal()) || auths.contains(54)){
					zts.add(ZBStatus.APPROVED.ordinal());
				}
			}
		}
		
		if (zts.isEmpty()){
			zts.add(ZBStatus.NONE.ordinal());
		}

		boolean all = Boolean.valueOf(request.getParameter("all"));
		CpzlqkResp resp = null;
		
		if (all){
			List<List<String>> result = byqadwtjjgService.getByqadwtjjg(d, yjType, zts);
			List<WaveItem> waveItems = null;
			if (yjType == YDJDType.YD){
				waveItems = byqadwtjjgService.getWaveItems(d, zts);
			}
			resp = new CpzlqkResp(result, waveItems);
		}else{
			CompanyType comp = CompanySelection.getCompany(request);
			Company company = companyManager.getVirtualCYOrg().getCompany(comp);
			List<List<String>> result = byqadwtjjgService.getByqadwtjjg(d, yjType, company, zts);
			List<WaveItem> waveItems = byqadwtjjgService.getByqYdAdwtjjgWaveItems(d, company, zts);
			resp = new CpzlqkResp(result, waveItems);
		}
		
		
		
	
		return JSONObject.fromObject(resp.format()).toString().getBytes("utf-8");
	}


	@RequestMapping(value = "export.do")
	public void exportByqadwtjjg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		List<List<String>> result = null;

		boolean all = Boolean.valueOf(request.getParameter("all"));
		if (all){
			//result = byqadwtjjgService.getByqadwtjjg(d, yjType, );
			
		}else{
			CompanyType comp = CompanySelection.getCompany(request);
			Company company = companyManager.getVirtualCYOrg().getCompany(comp);
			///result = byqadwtjjgService.getByqadwtjjg(d, yjType, company);
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