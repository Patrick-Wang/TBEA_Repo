package com.tbea.ic.operation.controller.servlet.cpzlqk.pdcpycssbhgxxfb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.EasyCalendar;
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
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.service.cpzlqk.pdcpycssbhgxxfb.BhgxxfbResp;
import com.tbea.ic.operation.service.cpzlqk.pdcpycssbhgxxfb.PdcpycssbhgxxfbService;
import com.tbea.ic.operation.service.cpzlqk.pdcpycssbhgxxfb.PdcpycssbhgxxfbServiceImpl;

@Controller
@RequestMapping(value = "pdcpycssbhgxxfb")
public class PdcpycssbhgxxfbServlet {
	@Resource(name=PdcpycssbhgxxfbServiceImpl.NAME)
	PdcpycssbhgxxfbService pdcpycssbhgxxfbService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getPdcpycssbhgxxfb(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		List<String> bhglbs = pdcpycssbhgxxfbService.getBhglbs();
		boolean all = Boolean.valueOf(request.getParameter("all"));
		List<List<String>> result = null;
		List<WaveItem> wis = null;
		if (all){
			result = pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(d, yjType);
			if (yjType == YDJDType.JD){
				EasyCalendar cal = new EasyCalendar();
				cal.setTime(d);
				result.addAll(pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(cal.getLastYear().getDate(), yjType));
			}
			wis = pdcpycssbhgxxfbService.getWaveItems(d, yjType);
		}else{
			CompanyType comp = CompanySelection.getCompany(request);
			Company company = companyManager.getVirtualCYOrg().getCompany(comp);
			result = pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(d, yjType, company);
			if (yjType == YDJDType.JD){
				EasyCalendar cal = new EasyCalendar();
				cal.setTime(d);
				result.addAll(pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(cal.getLastYear().getDate(), yjType, company));
			}
			wis = pdcpycssbhgxxfbService.getWaveItems(d, yjType, company);
		}
		
		RawFormatterHandler handler = new RawEmptyHandler(null, null);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		
		return JSONObject.fromObject(new BhgxxfbResp(bhglbs, result, wis)).toString().getBytes("utf-8");
	}


	@RequestMapping(value = "export.do")
	public void exportPdcpycssbhgxxfb(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
			
		boolean all = Boolean.valueOf(request.getParameter("all"));
		List<List<String>> result = null;
		if (all){
			result = pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(d, yjType);
		}else{
			CompanyType comp = CompanySelection.getCompany(request);
			Company company = companyManager.getVirtualCYOrg().getCompany(comp);
			result = pdcpycssbhgxxfbService.getPdcpycssbhgxxfb(d, yjType, company);
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