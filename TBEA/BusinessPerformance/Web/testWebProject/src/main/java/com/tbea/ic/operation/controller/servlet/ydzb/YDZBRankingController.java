package com.tbea.ic.operation.controller.servlet.ydzb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
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
import com.tbea.ic.operation.common.excel.JYGKPhase2SheetType;
import com.tbea.ic.operation.common.formatter.excel.FormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.HeaderFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.NumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.excel.PercentFormatterHandler;
import com.tbea.ic.operation.service.ydzb.YDZBService;
import com.tbea.ic.operation.service.ydzb.rank.RankService;


@Controller
@RequestMapping(value = "ydzbRanking")
public class YDZBRankingController {
	
	@Autowired
	private YDZBService service;

	@Autowired
	private RankService rankService;
	
	
	@RequestMapping(value = "companys_ranking.do", method = RequestMethod.GET)
	public ModelAndView getCompanys_Ranking(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		return new ModelAndView("companys_ranking", map);
	}
	
	
	@RequestMapping(value = "companys_ranking_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getCompanys_Ranking_Update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = DateSelection.getDate(request);
		int rankingType = Integer.valueOf(request.getParameter("rankingType"));
		String ranking_val = null;
		if (rankingType == 1) {
			ranking_val = JSONArray.fromObject(rankService.getJhlrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 11) {
			ranking_val = JSONArray.fromObject(rankService.getXmgsJhlrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 2) {
			ranking_val = JSONArray.fromObject(rankService.getLjlrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 9) {
			ranking_val = JSONArray.fromObject(rankService.getJxjlRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 3) {
			ranking_val = JSONArray.fromObject(rankService.getRjlrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 4) {
			ranking_val = JSONArray.fromObject(rankService.getRjsrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 13) {
			ranking_val = JSONArray.fromObject(rankService.getXmgsRjsrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 14) {
			ranking_val = JSONArray.fromObject(rankService.getXmgsRjlrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 5) { //应收账款占收入比排名
			ranking_val = JSONArray.fromObject(rankService.getYszkzsrbRank(d))
					.toString().replace("null", "\"--\"");
		}else if (rankingType == 7) { //存货占比排名
			ranking_val = JSONArray.fromObject(rankService.getChzbRank(d))
					.toString().replace("null", "\"--\"");
		}else if (rankingType == 8) {//应收加存货排名
			ranking_val = JSONArray.fromObject(rankService.getYsAndChzbRank(d))
					.toString().replace("null", "\"--\"");
		}else if (rankingType == 6) { //应收加保理占比排名
			ranking_val = JSONArray.fromObject(rankService.getYszkAndBlzbRank(d))
					.toString().replace("null", "\"--\"");
		}
		return ranking_val.getBytes("utf-8");
	}

	//各单位指标指标排名导出
	@RequestMapping(value = "companys_ranking_export.do")
	public @ResponseBody byte[] companys_ranking_export(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = DateSelection.getDate(request);
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		int rankingType = Integer.parseInt(request.getParameter("rankingType"));
		String rankingName = null;
		String rank = null;
		ExcelTemplate template = null;
		List<String[]> data = null;
		String fileNameAndSheetName = null; 
		if(rankingType == 1){
			rankingName = "利润计划完成率排名";
		}else if(rankingType == 2){
			rankingName = "利润指标年度累计完成同比增长排名";
		}else if(rankingType == 3){
			rankingName = "人均利润实际完成排名";
		}else if(rankingType == 4){
			rankingName = "人均收入实际完成排名";
		}else if(rankingType == 5){
			rankingName = "应收账款占收入比排名";
		}else if(rankingType == 6){
			rankingName = "应收账款加保理占收入排名";
		}else if(rankingType == 7){
			rankingName = "存货占收入比排名";
		}else if(rankingType == 8){
			rankingName = "应收加存货占收入比排名";
		}else if(rankingType == 9){
			rankingName = "经营性净现金流实际完成排名";
		}else if(rankingType == 11){
			rankingName = "利润指标年度累计完成同比增长排名";
		}else if(rankingType == 13){
			rankingName = "人均收入完成排名";
		}else if(rankingType == 14){
			rankingName = "人均利润完成排名";
		}
		if(rankingType == 11 || rankingType == 13 || rankingType == 14 ){
			rank = "项目公司";
		}else{
			rank = "经营单位";
		}
		FormatterHandler formatterChain = null;
		fileNameAndSheetName = year + "年" + month + "月" + rank + rankingName;
		if (rankingType == 1) {
			formatterChain = this.getFormatterChainDataOnly(
					new Integer[]{2, 6}, new Integer[]{});
			data = rankService.getJhlrRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.JYDWLRRANK);
		}else if(rankingType == 2){
			formatterChain = this.getFormatterChainDataOnly(
					new Integer[]{2, 6}, new Integer[]{});
			data = rankService.getLjlrRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.LRTBRANK);
		}else if(rankingType == 3){
			formatterChain = this.getFormatterChainDataOnly_1(
					new Integer[]{}, new Integer[]{0,2});
			data = rankService.getRjlrRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.RJRANK);
		}else if(rankingType == 4){
			formatterChain = this.getFormatterChainDataOnly_1(
					new Integer[]{}, new Integer[]{0,2});
			data = rankService.getRjsrRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.RJRANK);
		}else if(rankingType == 5){
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{3}, new Integer[]{});
			data = rankService.getYszkzsrbRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.YSZKZSRBRANK);
		}else if(rankingType == 6){
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{4}, new Integer[]{});
			data = rankService.getYszkAndBlzbRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.YSZKJBLZSRRANK);
		}else if(rankingType == 7){
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{3}, new Integer[]{});
			data = rankService.getChzbRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.CHZSRBRANK);
		}else if(rankingType == 8){
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{4}, new Integer[]{});
			data = rankService.getYsAndChzbRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.YSJCHZSRBRANK);
		}else if(rankingType == 9){
			formatterChain = this.getFormatterChainDataOnly(
					new Integer[]{2, 6}, new Integer[]{});
			data = rankService.getJxjlRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.JYDWLRRANK);
		}else if(rankingType == 11){
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{3, 7}, new Integer[]{});
			data = rankService.getXmgsJhlrRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.XMGSLRTBRANK);
		}else if(rankingType == 13){
			formatterChain = this.getFormatterChainWithHeader_1(
					new Integer[]{}, new Integer[]{1,3});
			data = rankService.getXmgsRjsrRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.XMGSRJRANK);
		}else if(rankingType == 14){
			formatterChain = this.getFormatterChainWithHeader_1(
					new Integer[]{}, new Integer[]{1,3});
			data = rankService.getXmgsRjlrRank(d);
			template = ExcelTemplate.createJYGKPhase2Template(JYGKPhase2SheetType.XMGSRJRANK);
		}
		
		//创建一个无任何内容的excel
		HSSFWorkbook workbook = template.getWorkbook();
		workbook.setSheetName(0, fileNameAndSheetName);
		//在已创建的excel表对象中创建一个图表
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0, ilen = data.size(); i < ilen; ++i) {
			//在已创建的图表中创建行
			HSSFRow row = null;
			if(rankingType == 11 || rankingType == 13 || rankingType == 14){
				 row = sheet.createRow(2 + i);
			}else if(rankingType == 5 || rankingType == 6 || rankingType == 7 || rankingType == 8){
				 row = sheet.getRow(1 + i);
			}
			else{
				 row = sheet.getRow(2 + i);
			}
			for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
				//在已创建的行中创建单元格
				HSSFCell cell = null;
				if(rankingType == 11 || rankingType == 13 || rankingType == 14 || rankingType == 5 || rankingType == 6 || rankingType == 7 || rankingType == 8){
					cell = row.createCell(j);//.getCell(j + 1);
				}else{
					cell = row.createCell(j+1);//.getCell(j + 1);
				}
				if (null != cell){
					formatterChain.handle(null, j, template, cell, data.get(i)[j]);
				}
			}
		}
			
		template.write(response, fileNameAndSheetName + ".xls");
		return "".getBytes("utf-8");
	}
	
	private FormatterHandler getFormatterChainWithHeader(Integer[] percentCols, Integer[] jhCols){
		FormatterHandler formatterChain = new HeaderFormatterHandler(null, new Integer[]{0});
		formatterChain.next(getFormatterChainDataOnly(percentCols, jhCols));
		return formatterChain;
	}
	
	private FormatterHandler getFormatterChainWithHeader_1(Integer[] percentCols, Integer[] jhCols){
		FormatterHandler formatterChain = new HeaderFormatterHandler(null, new Integer[]{0});
		formatterChain.next(getFormatterChainDataOnly_1(percentCols, jhCols));
		return formatterChain;
	}
	
	private FormatterHandler getFormatterChainDataOnly(Integer[] percentCols, Integer[] jhCols){
		FormatterHandler formatterChain = new PercentFormatterHandler(null, percentCols);
		formatterChain.next(new NumberFormatterHandler(0));
		return formatterChain;
	}
	private FormatterHandler getFormatterChainDataOnly_1(Integer[] percentCols, Integer[] jhCols){
		FormatterHandler formatterChain = new PercentFormatterHandler(null, percentCols);
		formatterChain.next(new NumberFormatterHandler(1,null,jhCols)).next(new NumberFormatterHandler(0));
		return formatterChain;
	}
}
