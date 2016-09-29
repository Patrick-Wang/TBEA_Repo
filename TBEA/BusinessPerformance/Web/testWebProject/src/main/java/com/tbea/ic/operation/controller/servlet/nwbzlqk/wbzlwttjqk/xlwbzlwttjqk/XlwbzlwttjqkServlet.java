package com.tbea.ic.operation.controller.servlet.nwbzlqk.wbzlwttjqk.xlwbzlwttjqk;

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
import com.tbea.ic.operation.service.cpzlqk.xlnwbzlztqk.XlnwbzlztqkService;
import com.tbea.ic.operation.service.cpzlqk.xlnwbzlztqk.XlnwbzlztqkServiceImpl;

@Controller
@RequestMapping(value = "xlwbzlwttjqk")
public class XlwbzlwttjqkServlet {
	@Resource(name=XlnwbzlztqkServiceImpl.NAME)
	XlnwbzlztqkService xlnwbzlztqkService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getByqnbzlwttjqk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		List<List<String>> result = xlnwbzlztqkService.getWbzlwttjqk(d, company);
		
		RawFormatterHandler handler = new RawEmptyHandler(null, null);
		RawFormatterServer serv = new RawFormatterServer(handler);
		serv.acceptNullAs("--").format(result);
		
		return JSONObject.fromObject(new CpzlqkResp(result)).toString().getBytes("utf-8");
	}


}