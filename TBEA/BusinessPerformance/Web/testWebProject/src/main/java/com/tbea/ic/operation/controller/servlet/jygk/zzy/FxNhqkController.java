package com.tbea.ic.operation.controller.servlet.jygk.zzy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyExcelTemplate;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyFormatterHandler;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyHeaderFormatterHandler;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyNumberFormatterHandler;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyPercentFormatterHandler;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyNumberFormatterHandler.NumberType;
import com.tbea.ic.operation.service.jygk.zzy.FxNhqkService;


@Controller
@RequestMapping(value = "fxnhqk")
public class FxNhqkController {
	
	@Autowired
	private FxNhqkService fxNhqkService;
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	//录入入口
	@RequestMapping(value = "openview.do", method = RequestMethod.GET)
	public ModelAndView getZBBG(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		//给页面返回报表ID和名称
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bg", fxNhqkService.getCksjBgList());
		return new ModelAndView("jygkzzy/fx_nhqk_template", map);
	}

	@RequestMapping(value = "zb_entry.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getZBEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(year);
		dateSel.select(map);
		
		List<Map> comps = fxNhqkService.getBgCompanies(request.getParameter("entryType"));
		map.put("comps", comps);
		String result = JSONObject.fromObject(map).toString(); 
		return result.getBytes("utf-8");
	}

	@RequestMapping(value = "zb_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getZBEntryUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date date = DateSelection.getDate(request);
		List<String[]> ret =  fxNhqkService.getZb(date, request.getParameter("companyId"), request.getParameter("entryType"));
		String zb = JSONArray.fromObject(ret).toString().replace("null", "\"\"");
		String result = "{\"values\":" + zb + "}"; 
		
		return result.getBytes("utf-8");
	}

	@RequestMapping(value = "fx_nhqk_export.do")
	public @ResponseBody byte[] gethzb_companys_prediction_export(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Date date = DateSelection.getDate(request);
		String entryType = request.getParameter("entryType");
		Object compname = "";
		List<Map> comps = fxNhqkService.getBgCompanies(entryType);
		for(int i = 0; i < comps.size(); i++){
			if(request.getParameter("companyId").equals(comps.get(i).get("id"))){
				compname = comps.get(i).get("value");
			}
		}
		List<String[]> data = fxNhqkService.getZb(date, request.getParameter("companyId"), request.getParameter("entryType"));
		JygkZzyExcelTemplate template = JygkZzyExcelTemplate.createJygkTemplate(entryType);
		String fileNameAndSheetName = (String)compname;
		fileNameAndSheetName += request.getParameter("year") + "年能耗情况";
		HSSFWorkbook workbook = template.getWorkbook();
		HSSFSheet sheet = workbook.getSheetAt(0);

		JygkZzyFormatterHandler formatterChain = this.getFormatterChainWithHeader(
				new Integer[]{3, 5, 7}, new Integer[]{1, 2, 4, 6});

		for (int i = 0; i < 2; ++i) {
			HSSFRow row = sheet.createRow(2 + i);
			for (int j = 1, jlen = data.get(i).length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j - 1);
				formatterChain.handle(
						data.get(i)[0], 
						j - 1, template, cell, data.get(i)[j]);
			}
		}
		for (int i = 0; i < 2; ++i) {
			HSSFRow row = sheet.createRow(7 + i);
			for (int j = 1, jlen = data.get(i).length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j - 1);
				formatterChain.handle(
						data.get(i + 2)[0], 
						j - 1, template, cell, data.get(i + 2)[j]);
			}
		}
			
		template.write(response, fileNameAndSheetName + ".xls");
		
		return "".getBytes("utf-8");
	}
	private JygkZzyFormatterHandler getFormatterChainWithHeader(Integer[] percentCols, Integer[] jhCols){
		JygkZzyFormatterHandler formatterChain = new JygkZzyHeaderFormatterHandler(null, new Integer[]{0});
		formatterChain.next(getFormatterChainDataOnly(percentCols, jhCols));
		return formatterChain;
	}
	
	private JygkZzyFormatterHandler getFormatterChainDataOnly(Integer[] percentCols, Integer[] jhCols){
		JygkZzyFormatterHandler formatterChain = new JygkZzyPercentFormatterHandler(null, percentCols);
		formatterChain
			.next(new JygkZzyNumberFormatterHandler(NumberType.RESERVE_0, null, jhCols));
		return formatterChain;
	}
}