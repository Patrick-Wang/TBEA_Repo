package com.tbea.ic.operation.controller.servlet.yszkrb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.excel.ExcelTemplate.JYGKPhase2SheetType;
import com.tbea.ic.operation.common.excel.FormatterHandler;
import com.tbea.ic.operation.common.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.excel.PercentFormatterHandler;
import com.tbea.ic.operation.common.excel.PercentSingleFormatterHandler;
import com.tbea.ic.operation.common.excel.ExcelTemplate.JygkSheetType;
import com.tbea.ic.operation.common.excel.NumberFormatterHandler.NumberType;
import com.tbea.ic.operation.service.yszkrb.YSZKRBService;


@Controller
@RequestMapping(value = "yszkrb")
public class YSZKRBController {
	
	@Autowired
	private YSZKRBService yszkrbService;

	@RequestMapping(value = "yszk.do", method = RequestMethod.GET)
	public ModelAndView getYszk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		DateSelection dateSel = new DateSelection();
		Map<String, Object> map = new HashMap<String, Object>();
		dateSel.select(map);
		return new ModelAndView("yszkrb_view", map);
	}

	@RequestMapping(value = "yszk_update.do")
	public @ResponseBody byte[] getYszkUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date date = DateSelection.getDate(request);
		List<String[]> data = yszkrbService.getYszkData(date);
		return JSONArray.fromObject(data).toString().replace("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "yszk_view_export.do")
	public @ResponseBody byte[] getyszk_view_export(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = DateSelection.getDate(request);
		List<String[]> data = yszkrbService.getYszkData(d);
		ExcelTemplate template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.YSDialy);
		String fileAndSheetName = (1900 + d.getYear()) + "-" + (d.getMonth() + 1) +"-" + d.getDate() + "应收账款日报";
//		CellFormatter formatter = template.createCellFormatter()
//				.addType(3, CellFormatter.CellType.PERCENT)
//				.addType(5, CellFormatter.CellType.PERCENT)
//				.addType(8, CellFormatter.CellType.PERCENT)
//				.addType(10, CellFormatter.CellType.PERCENT)
//				.addType(12, CellFormatter.CellType.PERCENT)
//				.addType(14, CellFormatter.CellType.PERCENT);

		FormatterHandler formatterChain = this.getFormatterChainDataOnly(
				new Integer[]{4, 5, 11}, new Integer[]{0});
		
		HSSFWorkbook workbook = template.getWorkbook();
		workbook.setSheetName(0, fileAndSheetName);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		
		for (int i = 0, ilen = data.size(); i < ilen; ++i) {
			HSSFRow row = sheet.getRow(2 + i);
			for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j + 1);
//				formatter.format(j, cell, data.get(i)[j]);
				formatterChain.handle(null, j, template, cell, data.get(i)[j]);
			}
		}		
			
		template.write(response, fileAndSheetName + ".xls");
		return "".getBytes("utf-8");
	}
	
	private FormatterHandler getFormatterChainWithHeader(Integer[] percentCols, Integer[] jhCols){
		FormatterHandler formatterChain = new HeaderFormatterHandler(null, new Integer[]{0});
		formatterChain.next(getFormatterChainDataOnly(percentCols, jhCols));
		return formatterChain;
	}
	
	private FormatterHandler getFormatterChainDataOnly(Integer[] percentCols, Integer[] jhCols){
		FormatterHandler formatterChain = new PercentFormatterHandler(null, percentCols);
		formatterChain.next(new NumberFormatterHandler(NumberType.RESERVE_0));
		return formatterChain;
	}
}
