package com.tbea.ic.operation.controller.servlet.nwbzlqk.sjzlqk.byqsjzlqk;

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
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.controller.servlet.cpzlqk.CpzlqkResp;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.service.cpzlqk.byqnwbzlztqk.ByqnwbzlztqkService;
import com.tbea.ic.operation.service.cpzlqk.byqnwbzlztqk.ByqnwbzlztqkServiceImpl;

@Controller
@RequestMapping(value = "byqsjzlqk")
public class ByqsjzlqkServlet {
	@Resource(name=ByqnwbzlztqkServiceImpl.NAME)
	ByqnwbzlztqkService byqnwbzlztqkService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getByqnwbzlztqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		boolean all = Boolean.valueOf(request.getParameter("all"));
		List<List<String>> result = null;
		List<WaveItem> wis = null;
		if (all){
			
			if (yjType == YDJDType.JD){
				result = byqnwbzlztqkService.getJdsjzlqk(d);
			}else{
				result = byqnwbzlztqkService.getYdsjzlqk(d);
			}
			wis = byqnwbzlztqkService.getSjzlqkWaveItems(d, yjType);
		}else{
			CompanyType comp = CompanySelection.getCompany(request);
			Company company = companyManager.getVirtualCYOrg().getCompany(comp);
			
			if (yjType == YDJDType.JD){
				result = byqnwbzlztqkService.getJdsjzlqk(d, company);
			}else{
				result = byqnwbzlztqkService.getYdsjzlqk(d, company);
			}
			wis = byqnwbzlztqkService.getSjzlqkWaveItems(d, yjType, company);
		}
		
		RawFormatterHandler handler = new RawEmptyHandler(null, null);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		
		return JSONObject.fromObject(new CpzlqkResp(result, wis)).toString().getBytes("utf-8");
	}
}