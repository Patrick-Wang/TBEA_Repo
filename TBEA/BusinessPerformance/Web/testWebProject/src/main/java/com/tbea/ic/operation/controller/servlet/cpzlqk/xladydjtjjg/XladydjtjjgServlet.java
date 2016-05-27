package com.tbea.ic.operation.controller.servlet.cpzlqk.xladydjtjjg;

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

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.excel.CpzlqkSheetType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.excel.FormatterServer;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderCenterFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.MergeRegion;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawNumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawPercentFormatterHandler;
import com.tbea.ic.operation.controller.servlet.cpzlqk.CpzlqkResp;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.service.cpzlqk.xladydjtjjg.XladydjtjjgService;
import com.tbea.ic.operation.service.cpzlqk.xladydjtjjg.XladydjtjjgServiceImpl;

@Controller
@RequestMapping(value = "xladydjtjjg")
public class XladydjtjjgServlet {
	@Resource(name=XladydjtjjgServiceImpl.NAME)
	XladydjtjjgService xladydjtjjgService;



	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] getXladydjtjjg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		
		List<List<String>> result = xladydjtjjgService.getXladydjtjjg(d, yjType);
		
		List<String> waveX = new ArrayList<String>();
		WaveItem item = null;
		List<WaveItem> waveItems = new ArrayList<WaveItem>();
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
		
		return JSONObject.fromObject(new CpzlqkResp(result, waveItems, waveX).format()).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "export.do")
	public void exportXladydjtjjg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = Date.valueOf(request.getParameter("date"));
		YDJDType yjType = YDJDType.valueOf(Integer.valueOf(request.getParameter("ydjd")));
		
		List<List<String>> result = xladydjtjjgService.getXladydjtjjg(d, yjType);
		ExcelTemplate template = ExcelTemplate.createCpzlqkTemplate(CpzlqkSheetType.XLADYDJTJJG);
	
		FormatterHandler handler = new HeaderCenterFormatterHandler(null, new Integer[]{0, 1});
		handler.next(new NumberFormatterHandler(0));
		FormatterServer serv = new FormatterServer(handler, 0, 2);
		serv.addMergeRegion(new MergeRegion(0, 2, 2, result.size()));
		serv.format(result, template);

		String name = yjType.val() + template.getSheetName();

		template.write(response, name + ".xls");
	}
}