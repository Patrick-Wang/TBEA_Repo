package com.tbea.ic.operation.controller.servlet.cpzlqk.pdadwtjjg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
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
import com.tbea.ic.operation.common.excel.CpzlqkSheetType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.FormatterServer;
import com.tbea.ic.operation.common.formatter.excel.HeaderCenterFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.MergeRegion;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.PercentFormatterHandler;
import com.tbea.ic.operation.controller.servlet.cpzlqk.CpzlqkResp;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.service.cpzlqk.pdadwtjjg.PdadwtjjgService;
import com.tbea.ic.operation.service.cpzlqk.pdadwtjjg.PdadwtjjgServiceImpl;

@Controller
@RequestMapping(value = "pdadwtjjg")
public class PdadwtjjgServlet {
	@Resource(name=PdadwtjjgServiceImpl.NAME)
	PdadwtjjgService pdadwtjjgService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getPdadwtjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		
		boolean all = Boolean.valueOf(request.getParameter("all"));
		CpzlqkResp resp = null;
		
		if (all){
			List<List<String>> result = pdadwtjjgService.getPdadwtjjg(d, yjType);
			List<WaveItem> waveItems = new ArrayList<WaveItem>();
			List<String> waveX = new ArrayList<String>();

			WaveItem item = null;
			for (int i = 0; i < result.size(); ++i){
				if (waveX.isEmpty() || !waveX.contains(result.get(i).get(0))){
					waveX.add(result.get(i).get(0));
				}
				
				item = WaveItem.find(waveItems, result.get(i).get(1));
				if (null != item){
					item.getData().add(result.get(i).get(4));
				}else{
					item = new WaveItem(result.get(i).get(1), new ArrayList<String>());
					waveItems.add(item); 
					item.getData().add(result.get(i).get(4));
				}
			}
			resp = new CpzlqkResp(result, waveItems, waveX);
		}else{
			CompanyType comp = CompanySelection.getCompany(request);
			Company company = companyManager.getVirtualCYOrg().getCompany(comp);
			List<List<String>> result = pdadwtjjgService.getPdadwtjjg(d, yjType, company);
			List<WaveItem> waveItems = pdadwtjjgService.getPdYdAdwtjjgWaveItems(d, company);
			resp = new CpzlqkResp(result, waveItems);
		}
		
		
		
	
		return JSONObject.fromObject(resp.format()).toString().getBytes("utf-8");
	}


	@RequestMapping(value = "export.do")
	public void exportPdadwtjjg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		List<List<String>> result = null;
		
		boolean all = Boolean.valueOf(request.getParameter("all"));
	
		if (all){
			result = pdadwtjjgService.getPdadwtjjg(d, yjType);
			
		}else{
			CompanyType comp = CompanySelection.getCompany(request);
			Company company = companyManager.getVirtualCYOrg().getCompany(comp);
			result = pdadwtjjgService.getPdadwtjjg(d, yjType, company);
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