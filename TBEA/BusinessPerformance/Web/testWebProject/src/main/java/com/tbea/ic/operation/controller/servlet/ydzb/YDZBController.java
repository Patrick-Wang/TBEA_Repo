package com.tbea.ic.operation.controller.servlet.ydzb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.POIUtils;
import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.VirtualJYZBOrganization;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.excel.JygkSheetType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.service.ydzb.YDZBService;
import com.tbea.ic.operation.service.ydzb.gszb.GszbService;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.excel.FormatterHandler;
import com.xml.frame.report.util.excel.HeaderFormatterHandler;
import com.xml.frame.report.util.excel.NumberFormatterHandler;
import com.xml.frame.report.util.excel.PercentFormatterHandler;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "ydzb")
public class YDZBController {

	static List<CompanyType> compTypes = new ArrayList<CompanyType>();
	static{
		compTypes.add(CompanyType.SBDCYJT);
		compTypes.add(CompanyType.BYQCY);
		compTypes.add(CompanyType.XLCY);
		compTypes.add(CompanyType.DBSBDCYJT);
		compTypes.add(CompanyType.NFSBDCYJT);
		compTypes.add(CompanyType.SBGS);
		compTypes.add(CompanyType.HBGS);
		compTypes.add(CompanyType.XBC);
		compTypes.add(CompanyType.LLGS);
		compTypes.add(CompanyType.XLC);
		compTypes.add(CompanyType.DLGS);
		compTypes.add(CompanyType.XNYSYB);
		compTypes.add(CompanyType.XNYGS);
		compTypes.add(CompanyType.XTNYGS);
		compTypes.add(CompanyType.NYSYB);
		compTypes.add(CompanyType.TCNY);
		compTypes.add(CompanyType.NDGS);
		compTypes.add(CompanyType.ZHGS);
		compTypes.add(CompanyType.JCKGS_JYDW);
		compTypes.add(CompanyType.GJGCGS_GFGS);
	}
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	Map<String, Object> tmpCache = Collections.synchronizedMap(new HashMap<String, Object>());
	
	@Autowired
	private YDZBService service;

	@Autowired
	private GszbService gszbService;

	private final static String INDICATOR_JZCSYL = "净资产收益率(%)";
	private final static String INDICATOR_DJQY = "其中：单机签约";
	private final static String INDICATOR_CTQY_WMY = "其中：成套签约(万美元)";
	private final static String INDICATOR_CTQY_WY = "其中：成套签约(万元)";
	
	private List<String[]> getSybOrJydwData(Date d, CompanyType sybOrJydw){
		List<Company> jydws = VirtualJYZBOrganization.getJydw(companyManager, sybOrJydw);
		List<String[]> ret = gszbService.getGdwzb(d, jydws);
		removeJzcsyl(sybOrJydw, ret);
		return ret;
	}
	
	private List<String[]> getXmgsData(Date d, Company xmgs){
		List<Company> xmgses = new ArrayList<Company>();
		xmgses.add(xmgs);
		return gszbService.getGdwzb(d, xmgses);
	}
	
	@RequestMapping(value = {"hzb_zbhz_jydw_compute.do", "v2/hzb_zbhz_jydw_compute.do"})
	public @ResponseBody byte[] gethzb_zbhz_jydw_compute(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Date d = DateSelection.getDate(request);
		String type = request.getParameter("type");
		ExcelHelper template = null;
		List<String[]> data = null;
		if ("0".equals(type)) {	
			data = gszbService.getGsztzb(d);
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.GSZTZB);
//			CellFormatter formatter = template.createCellFormatter()
//					.addType(0, CellFormatter.CellType.HEADER)
//					.addType(4, CellFormatter.CellType.PERCENT)
//					.addType(6, CellFormatter.CellType.PERCENT)
//					.addType(9, CellFormatter.CellType.PERCENT)
//					.addType(11, CellFormatter.CellType.PERCENT)
//					.addType(13, CellFormatter.CellType.PERCENT)
//					.addType(15, CellFormatter.CellType.PERCENT);

			FormatterHandler formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{4, 6, 9, 11, 13, 15}, new Integer[]{1, 2});
			
			
			HSSFWorkbook workbook = template.getWorkbook();  
			String fileNameAndSheetName = request.getParameter("fileName");
			workbook.setSheetName(0, fileNameAndSheetName);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 0, ilen = data.size(); i < ilen; ++i) {
				HSSFRow row = sheet.createRow(3 + i);
				for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
					HSSFCell cell = row.createCell(j);
					formatterChain.handle(data.get(i)[0], j, template, cell, data.get(i)[j]);
				}
			}

			template.setRowHeight(3, 3 + data.size() - 1, 16.5f);
			template.setColumnWidth(0, data.get(0).length - 1, 6.5f);
			int sheetMergerCount = sheet.getNumMergedRegions();
			 
			String compName;
			for (CompanyType ct : compTypes) {
				
				data = getSybOrJydwData(d, ct);
				compName = ct.getValue();
				
				int lastRow = sheet.getLastRowNum() + 1;
				HSSFRow rowFrom = sheet.getRow(0);
				HSSFRow rowTo = sheet.createRow(lastRow);
				POIUtils.copyRow(workbook, rowFrom, rowTo, true);
				rowTo.getCell(0).setCellValue(compName + "当月指标汇总");

				rowFrom = sheet.getRow(1);
				rowTo = sheet.createRow(lastRow + 1);
				POIUtils.copyRow(workbook, rowFrom, rowTo, true);

				rowFrom = sheet.getRow(2);
				rowTo = sheet.createRow(lastRow + 2);
				POIUtils.copyRow(workbook, rowFrom, rowTo, true);

				for (int i = 0; i < sheetMergerCount; i++) {
					CellRangeAddress range = sheet.getMergedRegion(i);
					range = range.copy();
					range.setLastRow(range.getLastRow() + lastRow);
					range.setFirstRow(range.getFirstRow() + lastRow);
					sheet.addMergedRegion(range);
				}

				lastRow += 3;
				for (int i = 0, ilen = data.size(); i < ilen; ++i) {
					HSSFRow row = sheet.createRow(lastRow + i);
					for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
						HSSFCell cell = row.createCell(j);
						formatterChain.handle(data.get(i)[0], j, template, cell, data.get(i)[j]);
//						formatter.format(j, cell, data.get(i)[j]);
					}
				}
				
				template.setRowHeight(lastRow, lastRow + data.size() - 1, 16.5f);
				template.setColumnWidth(0, data.get(0).length - 1, 6.5f);
			}
		} else {
			data = gszbService.getSrqy(d);
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.SRQYFJG);
			FormatterHandler formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{4, 6, 8, 10}, 
					new Integer[]{1, 2});
			
//			CellFormatter formatter = template.createCellFormatter()
//					.addType(0, CellFormatter.CellType.HEADER)
//					.addType(4, CellFormatter.CellType.PERCENT)
//					.addType(6, CellFormatter.CellType.PERCENT)
//					.addType(8, CellFormatter.CellType.PERCENT)
//					.addType(10, CellFormatter.CellType.PERCENT);
			
			HSSFWorkbook workbook = template.getWorkbook();
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 0, ilen = data.size(); i < ilen; ++i) {
				HSSFRow row = sheet.createRow(3 + i);
				for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
					HSSFCell cell = row.createCell(j);
					formatterChain.handle(data.get(i)[0], j, template, cell, data.get(i)[j]);
				}
			}
			template.setRowHeight(3, 3 + data.size() - 1, 16.5f);
			template.setColumnWidth(0, data.get(0).length - 1, 6.5f);
		}
		String timeStamp = "" + Calendar.getInstance().getTimeInMillis();
		tmpCache.put(timeStamp + "template", template);
		tmpCache.put(timeStamp + "fileName", request.getParameter("fileName") + ".xls");
		return ("{\"timeStamp\" : \"" + timeStamp + "\"}").getBytes("utf-8");

	}
	
	@RequestMapping(value = "hzb_zbhz_xmgs_compute.do")
	public @ResponseBody byte[] getHzb_zbhz_xmgs_compute(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Date d = DateSelection.getDate(request);
		ExcelHelper template = null;
		List<String[]> data = null;

		//data = gszbService.getGsztzb(d);
		template = ExcelTemplate.createJygkTemplate(JygkSheetType.GSZTZB);
//		CellFormatter formatter = template.createCellFormatter()
//				.addType(0, CellFormatter.CellType.HEADER)
//				.addType(4, CellFormatter.CellType.PERCENT)
//				.addType(6, CellFormatter.CellType.PERCENT)
//				.addType(9, CellFormatter.CellType.PERCENT)
//				.addType(11, CellFormatter.CellType.PERCENT)
//				.addType(13, CellFormatter.CellType.PERCENT)
//				.addType(15, CellFormatter.CellType.PERCENT);

		FormatterHandler formatterChain = this.getFormatterChainWithHeader(new Integer[] {
				4, 6, 9, 11, 13, 15 }, new Integer[] { 1, 2 });

		HSSFWorkbook workbook = template.getWorkbook();
		String fileNameAndSheetName = request.getParameter("fileName");
		workbook.setSheetName(0, fileNameAndSheetName);
		HSSFSheet sheet = workbook.getSheetAt(0);

		int sheetMergerCount = sheet.getNumMergedRegions();
		int lastRow = 0;
		String compName;
		List<Company> jydws = BMDepartmentDB.getMainlyJydw(companyManager);
		for (Company jydw : jydws) {
			
			List<Company> comps = jydw.getSubCompanies();
			for (Company comp : comps) {
				if (isInvalidXmgs(comp)){
					continue;
				}
				
				data = getXmgsData(d, comp);
				compName = comp.getName();
				if (0 == lastRow){
					HSSFRow rowFrom = sheet.getRow(0);
					rowFrom.getCell(0).setCellValue(compName + "当月指标汇总");
					lastRow += 3;
				}else{
					lastRow = sheet.getLastRowNum() + 1;
					HSSFRow rowFrom = sheet.getRow(0);
					HSSFRow rowTo = sheet.createRow(lastRow);
					POIUtils.copyRow(workbook, rowFrom, rowTo, true);
					rowTo.getCell(0).setCellValue(compName + "当月指标汇总");

					rowFrom = sheet.getRow(1);
					rowTo = sheet.createRow(lastRow + 1);
					POIUtils.copyRow(workbook, rowFrom, rowTo, true);

					rowFrom = sheet.getRow(2);
					rowTo = sheet.createRow(lastRow + 2);
					POIUtils.copyRow(workbook, rowFrom, rowTo, true);

					for (int i = 0; i < sheetMergerCount; i++) {
						CellRangeAddress range = sheet.getMergedRegion(i);
						range = range.copy();
						range.setLastRow(range.getLastRow() + lastRow);
						range.setFirstRow(range.getFirstRow() + lastRow);
						sheet.addMergedRegion(range);
					}

					lastRow += 3;
				}
				
				for (int i = 0, ilen = data.size(); i < ilen; ++i) {
					HSSFRow row = sheet.createRow(lastRow + i);
					for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
						HSSFCell cell = row.createCell(j);
						formatterChain.handle(data.get(i)[0], j, template, cell, data.get(i)[j]);
						//formatter.format(j, cell, data.get(i)[j]);
					}
				}
			}
		}

		String timeStamp = "" + Calendar.getInstance().getTimeInMillis();
		tmpCache.put(timeStamp + "template", template);
		tmpCache.put(timeStamp + "fileName", request.getParameter("fileName") + ".xls");
		return ("{\"timeStamp\" : \"" + timeStamp + "\"}").getBytes("utf-8");
	}
	
	
	@RequestMapping(value = {"hzb_zbhz_update.do", "v2/hzb_zbhz_update.do"}, method = RequestMethod.GET)
	public @ResponseBody byte[] getHzb_zbhz_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = DateSelection.getDate(request);
		String type = request.getParameter("type");
		String hzb_zbhz;
		if ("0".equals(type)) {
			hzb_zbhz = JSONArray.fromObject(gszbService.getGsztzb(d))
					.toString().replace("null", "\"--\"");
		} else {
			hzb_zbhz = JSONArray.fromObject(gszbService.getSrqy(d)).toString()
					.replace("null", "\"--\"");
		}
		return hzb_zbhz.getBytes("utf-8");
	}

	@RequestMapping(value = {"hzb_zbhz.do", "v2/hzb_zbhz.do"}, method = RequestMethod.GET)
	public ModelAndView getGszb_zbhz(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "hzb_zbhz", map);
	}

	private void removeJzcsyl(CompanyType compType, List<String[]> zbData){
		if (VirtualJYZBOrganization.isSyb(compType) || VirtualJYZBOrganization.isSbdcy(compType)){
			for (int i = 0; i < zbData.size(); ++i){
				if (INDICATOR_JZCSYL.equals(zbData.get(i)[0])){
					for (int j = 1; j < zbData.get(i).length; ++j){
						zbData.get(i)[j] = null;
					}
					break;
				}
			}
		}
	}
	
	private boolean contains(String[] zbs, String val){
		for(int i = 0; i < zbs.length; ++i){
			if(val.contains(zbs[i])){
				return true;
			}
		}
		return false;
	}
	
	private void removeZbs(List<String[]> zbData, String[] zbs){
		for (int i = zbData.size() - 1; i >= 0; --i){
			if (contains(zbs, zbData.get(i)[0])){
				zbData.remove(i);
			}
		}
	}
	
	
	private static String[] zhZb = new String[]{
		 "人均发电量（万度/人）", 
		 "外购电单位成本（元/度）", 
		 "铝杆棒一次综合成品率（%）", 
		 "其中：5154合金杆一次成品率（%）", 
		 "4043&8030&6201合金杆一次成品率（%）", 
		 "高纯铝杆产品一次成品率（%）", 
		 "铝棒产品一次成品率（%）", 
		 "铝电解高品质槽99.90%以上等级13项元素符合率（二级以上）（%）", 
		 "失败成本率1（%）", 
		 "外部客诉率（%）", 
		 "4N6精铝块一次成品率（%）", 
		 "精铝杆一次成品率（%）", 
		 "综合成品率（%）", 
		 "基材成品率（%）", 
		 "粉末喷涂成品率（%）", 
		 "隔热产品成品率（%）", 
		 "失败成本率（%）", 
		 "自产箔综合符单率（%）", 
		 "委托加工化成箔符单率（%）", 
		 "架空电缆（1KV、10KV）合格率（%）", 
		 "钢芯铝绞线合格率（%）", 
		 "布电线合格率（%）",
		 "失败成本率1（%）",
         "外部客诉率(合金材料公司)（%）",
         "外部客诉率(铝箔公司)（%）",
         "外部客诉率(电极箔公司)（%）",
         "失败成本率(金属结构与炭素材料公司)（%）"
    };
	
	private FormatterHandler getFormatterChainWithHeader(Integer[] percentCols, Integer[] jhCols){
		FormatterHandler formatterChain = new HeaderFormatterHandler(null, new Integer[]{0});
		formatterChain.next(getFormatterChainDataOnly(percentCols, jhCols));
		return formatterChain;
	}
	
	private FormatterHandler getFormatterChainDataOnly(Integer[] percentCols, Integer[] jhCols){
		FormatterHandler formatterChain = new PercentFormatterHandler(null, percentCols);
		formatterChain
			.next(new NumberFormatterHandler(0, new String[]{"人数"}))
			//.next(new PercentSingleFormatterHandler(new String[]{"净资产收益率(%)"}))
			.next(new PercentFormatterHandler(new String[]{"净资产收益率(%)", "三项费用率(%)", "销售利润率(%)"}))
			.next(new NumberFormatterHandler(1, new String[]{"人均利润", "人均收入", "精铝块13项元素和值（ppm）"}))
			.next(new NumberFormatterHandler(0, new String[]{"标煤单耗（g/度）", "厂用电率（%）"}, jhCols))
			.next(new NumberFormatterHandler(2, new String[]{"标煤单耗（g/度）", "厂用电率（%）"}))
			.next(new NumberFormatterHandler(2, zhZb))
			.next(new NumberFormatterHandler(4, new String[]{"单位供电成本（元/度）"}))
			.next(new NumberFormatterHandler(0));
		return formatterChain;
	}
	
	//各单位经营指标完成情况update
	@RequestMapping(value = "hzb_companys_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getHzb_companys_update(
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Date d = DateSelection.getDate(request);
		CompanyType compType = CompanySelection.getCompany(request);	
		String hzb_zbhz = JSONArray.fromObject(this.getSybOrJydwData(d, compType)).toString()
				.replace("null", "\"--\"");
		return hzb_zbhz.getBytes("utf-8");
	}
	
	@RequestMapping(value = "companys_zbhz_export.do")
	public @ResponseBody byte[] getcompanys_zbhz_export(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Date d = DateSelection.getDate(request);
		ExcelHelper template = null;
		CompanyType compType = CompanySelection.getCompany(request);
		String compName = compType.getValue();
		String fileName = compName + "经营指标完成情况";
		List<String[]> ret = this.getSybOrJydwData(d, compType);
		this.removeZbs(ret, new String[]{
				INDICATOR_DJQY,
				INDICATOR_CTQY_WY,
				INDICATOR_CTQY_WMY
		});
		template = ExcelTemplate.createJygkTemplate(JygkSheetType.GS_SYB);
	
		FormatterHandler formatterChain = this.getFormatterChainWithHeader(
				new Integer[]{4, 6, 9, 11, 13, 15}, new Integer[]{1, 2});
		
		HSSFWorkbook workbook = template.getWorkbook();
		workbook.setSheetName(0, compName + "经营指标完成情况");
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0, ilen = ret.size(); i < ilen; ++i) {
			HSSFRow row = sheet.createRow(2 + i);
			for (int j = 0, jlen = ret.get(i).length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j);
				formatterChain.handle(ret.get(i)[0], j, template, cell, ret.get(i)[j]);
			}
		}
		template.write(response, fileName + ".xls");
		return "".getBytes("utf-8");
	}

	//各单位经营指标完成情况
	@RequestMapping(value = {"hzb_companys.do", "v2/hzb_companys.do"}, method = RequestMethod.GET)
	public ModelAndView getHzb_companys(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();

		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true,
				false);
		dateSel.select(map);
		Organization org = companyManager.getVirtualJYZBOrganization();
		CompanySelection compSel = new CompanySelection(
				false,
				org.getCompanyByType(CompanyType.GFGS).getSubCompanies(), 
				new CompanyTypeFilter(
						gszbService.getCompanies(SessionManager.getAccount(request.getSession(false))), 
						org));

		compSel.select(map, 3);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "hzb_companys", map);
	}
	
	@RequestMapping(value = "gcy_zbhz_export.do")
	public @ResponseBody byte[] getgcy_zbhz_export(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = DateSelection.getDate(request);
		ExcelHelper template = null;
		List<String[]> data = null;
		data = gszbService.getGcyzb(d);
		template = ExcelTemplate.createJygkTemplate(JygkSheetType.GCY_ZBWC);
//		CellFormatter formatter = template.createCellFormatter()
//				.addType(3, CellFormatter.CellType.PERCENT)
//				.addType(5, CellFormatter.CellType.PERCENT)
//				.addType(8, CellFormatter.CellType.PERCENT)
//				.addType(10, CellFormatter.CellType.PERCENT)
//				.addType(12, CellFormatter.CellType.PERCENT)
//				.addType(14, CellFormatter.CellType.PERCENT);

		FormatterHandler formatterChain = this.getFormatterChainDataOnly(
				new Integer[]{3, 5, 8, 10, 12, 14}, new Integer[]{1, 2});
		
		HSSFWorkbook workbook = template.getWorkbook();
		workbook.setSheetName(0, request.getParameter("fileName"));
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		
		for (int i = 0, ilen = data.size(); i < ilen; ++i) {
			HSSFRow row = sheet.getRow(3 + i);
			for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j + 2);
//				formatter.format(j, cell, data.get(i)[j]);
				formatterChain.handle(null, j, template, cell, data.get(i)[j]);
			}
		}		
			
		template.write(response, request.getParameter("fileName") + ".xls");
		return "".getBytes("utf-8");
	}
	
	@RequestMapping(value = "gcy_zbhz_prediction_export.do")
	public @ResponseBody byte[] getgcy_zbhz_prediction_export(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//Integer iMonth = Integer.parseInt(request.getParameter("month"));
		//String year = request.getParameter("year");
		Date d = DateSelection.getDate(request);
		int month = Integer.parseInt(request.getParameter("month"));
		String year = request.getParameter("year");
		ExcelHelper template = null;
		List<String[]> data = null;
		String fileNameAndSheetName = null; 
		FormatterHandler formatterChain = null;
		if (0 == month % 3) {
			data = gszbService.getGcyThirdSeasonPredictionZBs(d);
			fileNameAndSheetName = year + "年第" + DateHelper.getJdCount(month) + "季度末月五大经营指标预测完成情况";
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDFCYZBYJ_MY);
//			formatter = template.createCellFormatter()
//					.addType(5, CellFormatter.CellType.PERCENT)
//					.addType(7, CellFormatter.CellType.PERCENT)					
//					.addType(9, CellFormatter.CellType.PERCENT)
//					.addType(11, CellFormatter.CellType.PERCENT)
//					.addType(13, CellFormatter.CellType.PERCENT)
//					.addType(15, CellFormatter.CellType.PERCENT)
//					.addType(20, CellFormatter.CellType.PERCENT)
//					.addType(22, CellFormatter.CellType.PERCENT)
//					.addType(24, CellFormatter.CellType.PERCENT);
			formatterChain = this.getFormatterChainDataOnly(
					new Integer[]{5, 7, 9, 11, 13, 15, 20, 22, 24}, new Integer[]{1, 2, 3, 4});
			
		}

		if (1 == month % 3) {
			data = gszbService.getGcyFirstSeasonPredictionZBs(d);
			fileNameAndSheetName = year + "年第" + DateHelper.getJdCount(month) + "季度首月五大经营指标预测完成情况";
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDFCYZBYJ_SY);
//			formatter = template.createCellFormatter()
//					.addType(4, CellFormatter.CellType.PERCENT)
//					.addType(6, CellFormatter.CellType.PERCENT)					
//					.addType(10, CellFormatter.CellType.PERCENT)
//					.addType(12, CellFormatter.CellType.PERCENT)
//					.addType(14, CellFormatter.CellType.PERCENT)
//					.addType(16, CellFormatter.CellType.PERCENT);
			formatterChain = this.getFormatterChainDataOnly(
					new Integer[]{4,6,10,12,14,16}, new Integer[]{1, 2, 3});		
		}

		if (2 == month % 3) {
			data = gszbService.getGcySecondSeasonPredictionZBs(d);
			fileNameAndSheetName = year + "年第" + DateHelper.getJdCount(month) + "季度次月五大经营指标预测完成情况";
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDFCYZBYJ_CY);
//			formatter = template.createCellFormatter()
//					.addType(4, CellFormatter.CellType.PERCENT)
//					.addType(6, CellFormatter.CellType.PERCENT)	
//					.addType(8, CellFormatter.CellType.PERCENT)	
//					.addType(10, CellFormatter.CellType.PERCENT)
//					.addType(13, CellFormatter.CellType.PERCENT)
//					.addType(15, CellFormatter.CellType.PERCENT)
//					.addType(17, CellFormatter.CellType.PERCENT)
//					.addType(19, CellFormatter.CellType.PERCENT);
			formatterChain = this.getFormatterChainDataOnly(
					new Integer[]{4, 6, 8, 10, 13, 15, 17, 19}, new Integer[]{1, 2, 3});		
		}
		

		HSSFWorkbook workbook = template.getWorkbook();
		workbook.setSheetName(0, fileNameAndSheetName);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0, ilen = data.size(); i < ilen; ++i) {
			HSSFRow row = sheet.getRow(2 + i);
			for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
				HSSFCell cell = row.getCell(j + 2);
//				formatter.format(j, cell, data.get(i)[j]);
				formatterChain.handle(null, j, template, cell, data.get(i)[j]);
			}
		}		
			
		template.write(response, fileNameAndSheetName + ".xls");
		return "".getBytes("utf-8");
	}
	
	//各产业经营指标完成情况update
	@RequestMapping(value = "gcy_zbhz_update.do", method = RequestMethod.GET)
	public @ResponseBody String getGcy_zbhz_update(HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
		String gcy_zbhz = JSONArray.fromObject(gszbService.getGcyzb(d))
				.toString().replace("null", "\"--\"");
		return gcy_zbhz;
	}
	
	//各产业经营指标完成情况
	@RequestMapping(value = {"gcy_zbhz.do", "v2/gcy_zbhz.do"}, method = RequestMethod.GET)
	public ModelAndView getGcy_zbhz(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "gcy_zbhz", map);
	}
	
	
	@RequestMapping(value = "gdw_zbhz_export.do")
	public @ResponseBody byte[] getgdw_zbhz_export(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = DateSelection.getDate(request);
		String gszb = request.getParameter("top5index");
		ExcelHelper template = null;
		List<String[]> data = null;
		data = gszbService.getCompanyTop5zb(GSZB.valueOf(Integer.valueOf(gszb)), d);
		template = ExcelTemplate.createJygkTemplate(JygkSheetType.GDWZBWCQK);
//		CellFormatter formatter = template.createCellFormatter()
//				.addType(3, CellFormatter.CellType.PERCENT)
//				.addType(5, CellFormatter.CellType.PERCENT)
//				.addType(8, CellFormatter.CellType.PERCENT)
//				.addType(10, CellFormatter.CellType.PERCENT)
//				.addType(12, CellFormatter.CellType.PERCENT)
//				.addType(14, CellFormatter.CellType.PERCENT);
		FormatterHandler formatterChain = this.getFormatterChainDataOnly(
				new Integer[]{3, 5, 8, 10, 12, 14}, new Integer[]{1, 2});
		HSSFWorkbook workbook = template.getWorkbook();
		String fileNameAndSheetName = "各单位" + request.getParameter("year") + "年" + request.getParameter("month") + "月" + request.getParameter("zbName") + "完成情况";
		workbook.setSheetName(0, fileNameAndSheetName);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0, ilen = data.size(); i < ilen; ++i) {
			HSSFRow row = sheet.getRow(3 + i);
			for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j + 1);
				formatterChain.handle(null, j, template, cell, data.get(i)[j]);
			}
		}		
			
		template.write(response, fileNameAndSheetName + ".xls");
		return "".getBytes("utf-8");
	}
	
	
	
	//各单位经营指标完成情况update
	@RequestMapping(value = "gdw_zbhz_update.do", method = RequestMethod.GET)
	public @ResponseBody String getGdw_zbhz_update(HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
		String gszb = request.getParameter("zbId");
		String gdw_zbhz = JSONArray
				.fromObject(
						gszbService.getCompanyTop5zb(
								GSZB.valueOf(Integer.valueOf(gszb)), d))
				.toString().replace("null", "\"--\"");
		return gdw_zbhz;
	}
	
	//各单位经营指标完成情况
	@RequestMapping(value = {"gdw_zbhz.do", "v2/gdw_zbhz.do"}, method = RequestMethod.GET)
	public ModelAndView getGdw_zbhz(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "gdw_zbhz", map);
	}

	@RequestMapping(value = "xjlrb_update.do", method = RequestMethod.GET)
	public @ResponseBody String getXjlrb_update(HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
		String xjlrb = JSONArray.fromObject(service.getXjlrbData(d)).toString()
				.replace("null", "0.00");
		return xjlrb;
	}

	@RequestMapping(value = {"xjlrb.do", "v2/xjlrb.do"}, method = RequestMethod.GET)
	public ModelAndView getXjlrb(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();

		DateSelection dateSel = new DateSelection(service.getLatestXjlDate());
		dateSel.select(map);

		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "xjlrb", map);
	}


	private String getZbhz_overviewData(Date d, int companyId, String zbid) {

		Organization org = companyManager.getOperationOrganization();
		Company comp = org.getCompanyByType(CompanyType.valueOf(companyId));

		String zbhz_overview_yd = JSONArray
				.fromObject(service.getYdZbhz_overviewData(d, comp, zbid))
				.toString().replace("null", "0.00");
		String zbhz_overview_jd = JSONArray
				.fromObject(service.getJdZbhz_overviewData(d, comp, zbid))
				.toString().replace("null", "0.00");
		String zbhz_overview_nd = JSONArray
				.fromObject(service.getNdZbhz_overviewData(d, comp, zbid))
				.toString().replace("null", "0.00");
		String zbhz_overview_ydtb = JSONArray
				.fromObject(service.getYdtbZbhz_overviewData(d, comp, zbid))
				.toString().replace("null", "0.00");
		String zbhz_overview_jdtb = JSONArray
				.fromObject(service.getJdtbZbhz_overviewData(d, comp, zbid))
				.toString().replace("null", "0.00");

		return "{\"yd\":" + zbhz_overview_yd + ", \"jd\" : " + zbhz_overview_jd
				+ ", \"nd\":" + zbhz_overview_nd + " , \"ydtb\":"
				+ zbhz_overview_ydtb + ", \"jdtb\":" + zbhz_overview_jdtb + "}";
	}
	
	// 整体指标预测update
	@RequestMapping(value = "zbhz_overview_update.do", method = RequestMethod.GET)
	public @ResponseBody String updateZbhz_overview(HttpServletRequest request,
			HttpServletResponse response) {
		String companyId = request.getParameter("companyId");
		if (companyId == null) {
			companyId = CompanyType.JT.ordinal() + "";
		}

		int cid = Integer.parseInt(companyId);
		String zb = request.getParameter("zb");
		if (zb == null) {
			zb = "5";
		}

		Date d = service.getLatestGcyDate();
		return getZbhz_overviewData(d, cid, zb);
	}
	
	
	// 整体指标预测
	@RequestMapping(value = {"zbhz_overview.do", "v2/zbhz_overview.do"}, method = RequestMethod.GET)
	public ModelAndView getZbhz_overview(HttpServletRequest request,
			HttpServletResponse response) {

		String zb = request.getParameter("zb");
		if (zb == null) {
			zb = "5";
		}

		Map<String, Object> map = new HashMap<String, Object>();

		DateSelection dateSel = new DateSelection(service.getLatestGcyDate(),
				true, false);
		dateSel.select(map);

		map.put("zbid", zb);
		map.put("zbmc", service.getZbmc(zb));

		Organization org = companyManager.getOperationZBHZOrganization();

		CompanySelection compSel = new CompanySelection(false,
				org.getTopCompany());
		compSel.select(map);

		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "zbhz_overview", map);
	}

	@RequestMapping(value = "hzb_companys_prediction_export.do")
	public @ResponseBody byte[] gethzb_companys_prediction_export(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		Date d = DateSelection.getDate(request);
		CompanyType compType = CompanySelection.getCompany(request);	
		List<Company> comps = VirtualJYZBOrganization.getJydw(companyManager, compType);
		String month = request.getParameter("month");
		int iMonth = Integer.valueOf(month);
		List<String[]> data = null;
		ExcelHelper template = null;
//		CellFormatter formatter = null;
//		;
		String fileNameAndSheetName = compType.getValue() + request.getParameter("year") + "年第" + DateHelper.getJdCount(iMonth) + "季度";
		FormatterHandler formatterChain = null;
		if (0 == iMonth % 3) {
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{6, 8, 10, 12, 14, 16, 21, 23, 25}, 
					new Integer[]{1, 2, 3, 4});
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDYJZB_MY);
//			formatter = template.createCellFormatter()
//					.addType(0, CellFormatter.CellType.HEADER)
//					.addType(6, CellFormatter.CellType.PERCENT)
//					.addType(8, CellFormatter.CellType.PERCENT)
//					.addType(10, CellFormatter.CellType.PERCENT)
//					.addType(12, CellFormatter.CellType.PERCENT)
//					.addType(14, CellFormatter.CellType.PERCENT)
//					.addType(16, CellFormatter.CellType.PERCENT)
//					.addType(21, CellFormatter.CellType.PERCENT)
//					.addType(23, CellFormatter.CellType.PERCENT)
//					.addType(25, CellFormatter.CellType.PERCENT);
			data = gszbService.getThirdSeasonPredictionZBsOverview(d, comps);
			fileNameAndSheetName += "末月";
		}

		if (1 == iMonth % 3) {
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{5, 7, 11, 13, 15, 17}, 
					new Integer[]{1, 2, 3});
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDYJZB_SY);
//			formatter = template.createCellFormatter()
//					.addType(0, CellFormatter.CellType.HEADER)
//					.addType(5, CellFormatter.CellType.PERCENT)
//					.addType(7, CellFormatter.CellType.PERCENT)
//					.addType(11, CellFormatter.CellType.PERCENT)
//					.addType(13, CellFormatter.CellType.PERCENT)
//					.addType(15, CellFormatter.CellType.PERCENT)
//					.addType(17, CellFormatter.CellType.PERCENT);
			data = gszbService.getFirstSeasonPredictionZBsOverview(d, comps);
			fileNameAndSheetName += "首月";
		}

		if (2 == iMonth % 3) {
			data = gszbService.getSecondSeasonPredictionZBsOverview(d, comps);
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDYJZB_CY);
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{5, 7, 9, 11, 14, 16, 18, 20}, 
					new Integer[]{1, 2, 3});
//			formatter = template.createCellFormatter()
//					.addType(0, CellFormatter.CellType.HEADER)
//					.addType(5, CellFormatter.CellType.PERCENT)
//					.addType(7, CellFormatter.CellType.PERCENT)
//					.addType(9, CellFormatter.CellType.PERCENT)
//					.addType(11, CellFormatter.CellType.PERCENT)
//					.addType(14, CellFormatter.CellType.PERCENT)
//					.addType(16, CellFormatter.CellType.PERCENT)
//					.addType(18, CellFormatter.CellType.PERCENT)
//					.addType(20, CellFormatter.CellType.PERCENT);	
			fileNameAndSheetName += "次月";
		}
		
		HSSFWorkbook workbook = template.getWorkbook();
		fileNameAndSheetName += "预计指标完成情况";
		workbook.setSheetName(0, fileNameAndSheetName);
		HSSFSheet sheet = workbook.getSheetAt(0);
		//sheet.getRow(0).getCell(0).setCellValue(compType.getValue() + "预计指标完成情况");
		
		for (int i = 0, ilen = data.size(); i < ilen; ++i) {
			HSSFRow row = sheet.createRow(3 + i);
			for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j);
				formatterChain.handle(
						data.get(i)[0], 
						j, template, cell, data.get(i)[j]);
//				formatter.format(j, cell, hzb_zbhz_prediction.get(i)[j]);
			}
		}
			
		template.write(response, fileNameAndSheetName + ".xls");
		
		return "".getBytes("utf-8");
	}
	
	//整体指标预测Update
	@RequestMapping(value = {"hzb_companys_prediction.do", "v2/hzb_companys_prediction.do"}, method = RequestMethod.GET)
	public ModelAndView gethzb_companys_prediction(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection();
		dateSel.select(map);
		Organization org = companyManager.getVirtualJYZBOrganization();
		CompanySelection compSel = new CompanySelection(
				false,
				org.getCompanyByType(CompanyType.GFGS).getSubCompanies(), 
				new CompanyTypeFilter(
						gszbService.getCompanies(SessionManager.getAccount(request.getSession(false))), 
						org));
		compSel.select(map, 3);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "companys_zbhz_prediction", map);
	}
		
	@RequestMapping(value = "hzb_companys_prediction_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] gethzb_companys_prediction_update(
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		
		Date d = DateSelection.getDate(request);
		CompanyType compType = CompanySelection.getCompany(request);	
		List<Company> comps = VirtualJYZBOrganization.getJydw(companyManager, compType);
		String month = request.getParameter("month");
		int iMonth = Integer.valueOf(month);
		String hzb_zbhz_prediction = null;
		if (0 == iMonth % 3) {
			hzb_zbhz_prediction = JSONArray
					.fromObject(gszbService.getThirdSeasonPredictionZBsOverview(d, comps)).toString()
					.replace("null", "\"--\"");
		}

		if (1 == iMonth % 3) {
			hzb_zbhz_prediction = JSONArray
					.fromObject(
							gszbService.getFirstSeasonPredictionZBsOverview(d, comps))
					.toString().replace("null", "\"--\"");
		}

		if (2 == iMonth % 3) {
			hzb_zbhz_prediction = JSONArray
					.fromObject(
							gszbService.getSecondSeasonPredictionZBsOverview(d, comps))
					.toString().replace("null", "\"--\"");
		}

		return hzb_zbhz_prediction.getBytes("utf-8");
	}
	
	
	private boolean isInvalidXmgs(Company comp){
		if (CompanyType.XLGGS == comp.getType() ||
				CompanyType.SBZXGS == comp.getType() ||
				CompanyType.KGYJS == comp.getType() ||
				CompanyType.SBXNY == comp.getType() ||
				CompanyType.GJSYB == comp.getType() ||
				CompanyType.JJWL == comp.getType() ||
				CompanyType.XJZXGS == comp.getType() ||
				CompanyType.FNSYB == comp.getType() ||
				CompanyType.XNYYJY == comp.getType()){
			return true;
		}
		return false;
	}
	
	// 项目公司整体指标预测export
	@RequestMapping(value = "hzb_zbhz_prediction_xmgs_compute.do")
	public @ResponseBody byte[] gethzb_zbhz_prediction_xmgs_compute(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Date d = DateSelection.getDate(request);
		String month = request.getParameter("month");
		int iMonth = Integer.valueOf(month);
		ExcelHelper template = null;
		String fileNameAndSheetName = request.getParameter("year") + "年第" + DateHelper.getJdCount(iMonth) + "季度";
		FormatterHandler formatterChain = null;
		if (0 == iMonth % 3) {
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDYJZB_MY);
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{6, 8, 10, 12, 14, 16, 21, 23, 25}, new Integer[]{1, 2, 3, 4});
			fileNameAndSheetName += "末月";
		}

		if (1 == iMonth % 3) {
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDYJZB_SY);
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{5, 7, 11, 13, 15, 17}, new Integer[]{1, 2, 3});
			fileNameAndSheetName += "首月";
		}

		if (2 == iMonth % 3) {
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDYJZB_CY);
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{5, 7, 9, 11, 14, 16, 18, 20}, new Integer[]{1, 2, 3});
			fileNameAndSheetName += "次月";
		}
		
		HSSFWorkbook workbook = template.getWorkbook();
		fileNameAndSheetName += "指标汇总预测";
		workbook.setSheetName(0, fileNameAndSheetName);
		HSSFSheet sheet = workbook.getSheetAt(0);		
		
		int sheetMergerCount = sheet.getNumMergedRegions();
		List<String[]> data = null;
		int lastRow = 0;
		List<Company> jydws = BMDepartmentDB.getMainlyJydw(companyManager);
		for (Company jydw : jydws) {
			List<Company> comps = jydw.getSubCompanies();
			for (Company xmgs : comps) {
				if (isInvalidXmgs(xmgs)){
					continue;
				}
				
				if (lastRow == 0) {
					HSSFRow rowTo = sheet.getRow(0);
					rowTo.getCell(0).setCellValue(xmgs.getName() + "指标预测");
				} else {
					lastRow = sheet.getLastRowNum() + 1;
					HSSFRow rowFrom = sheet.getRow(0);
					HSSFRow rowTo = sheet.createRow(lastRow);
					POIUtils.copyRow(workbook, rowFrom, rowTo, true);
					rowTo.getCell(0).setCellValue(xmgs.getName() + "指标预测");

					rowFrom = sheet.getRow(1);
					rowTo = sheet.createRow(lastRow + 1);
					POIUtils.copyRow(workbook, rowFrom, rowTo, true);

					rowFrom = sheet.getRow(2);
					rowTo = sheet.createRow(lastRow + 2);
					POIUtils.copyRow(workbook, rowFrom, rowTo, true);

					for (int i = 0; i < sheetMergerCount; i++) {
						CellRangeAddress range = sheet.getMergedRegion(i);
						range = range.copy();
						range.setLastRow(range.getLastRow() + lastRow);
						range.setFirstRow(range.getFirstRow() + lastRow);
						sheet.addMergedRegion(range);
					}
				}
				
				lastRow += 3;
				if (0 == iMonth % 3) {
					List<Company> xmgsTmp = new ArrayList<Company>();
					xmgsTmp.add(xmgs);
					data = gszbService.getThirdSeasonPredictionZBsOverview(d, xmgsTmp);
				} else if (1 == iMonth % 3) {
					List<Company> xmgsTmp = new ArrayList<Company>();
					xmgsTmp.add(xmgs);
					data = gszbService.getFirstSeasonPredictionZBsOverview(d,
							xmgsTmp);
				} else if (2 == iMonth % 3) {
					List<Company> xmgsTmp = new ArrayList<Company>();
					xmgsTmp.add(xmgs);
					data = gszbService.getSecondSeasonPredictionZBsOverview(d,
							xmgsTmp);
				}

				for (int i = 0, ilen = data.size(); i < ilen; ++i) {
					HSSFRow row = sheet.createRow(lastRow + i);
					for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
						HSSFCell cell = row.createCell(j);
						formatterChain.handle(data.get(i)[0], j, template,
								cell, data.get(i)[j]);
					}
				}
			}
		}
		
		String timeStamp = "" + Calendar.getInstance().getTimeInMillis();
		tmpCache.put(timeStamp + "template", template);
		tmpCache.put(timeStamp + "fileName", fileNameAndSheetName + ".xls");
		
		return ("{\"timeStamp\" : \"" + timeStamp + "\"}").getBytes("utf-8");
	}


	@RequestMapping(value = {"general_export.do", "v2/general_export.do"})
	public @ResponseBody byte[] general_export(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String timeStamp = request.getParameter("timeStamp");
		ExcelHelper template = (ExcelHelper) tmpCache.get(timeStamp + "template");
		template.write(response, (String) tmpCache.get(timeStamp + "fileName"));
		tmpCache.remove(timeStamp + "template");
		tmpCache.remove(timeStamp + "fileName");
		return "".getBytes("utf-8");
	}

	// 整体指标预测export
	@RequestMapping(value = "hzb_zbhz_prediction_jydw_compute.do")
	public @ResponseBody byte[] gethzb_zbhz_prediction_jydw_compute(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Date d = DateSelection.getDate(request);
		String month = request.getParameter("month");
		int iMonth = Integer.valueOf(month);
		List<String[]> data = null;
		ExcelHelper template = null;
		String fileNameAndSheetName = request.getParameter("year") + "年第" + DateHelper.getJdCount(iMonth) + "季度";
		FormatterHandler formatterChain = null;
		if (0 == iMonth % 3) {
			data = gszbService.getGsThirdSeasonPredictionZBsOverview(d);
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDYJZB_MY);
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{6, 8, 10, 12, 14, 16, 21, 23, 25}, new Integer[]{1, 2, 3, 4});
			fileNameAndSheetName += "末月";
		}

		if (1 == iMonth % 3) {
			data = gszbService.getGsFirstSeasonPredictionZBsOverview(d);
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDYJZB_SY);
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{5, 7, 11, 13, 15, 17}, new Integer[]{1, 2, 3});
			fileNameAndSheetName += "首月";
		}

		if (2 == iMonth % 3) {
			data = gszbService.getGsSecondSeasonPredictionZBsOverview(d);
			template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDYJZB_CY);
			formatterChain = this.getFormatterChainWithHeader(
					new Integer[]{5, 7, 9, 11, 14, 16, 18, 20}, new Integer[]{1, 2, 3});
			fileNameAndSheetName += "次月";
		}
		
		HSSFWorkbook workbook = template.getWorkbook();
		fileNameAndSheetName += "指标汇总预测";
		workbook.setSheetName(0, fileNameAndSheetName);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0, ilen = data.size(); i < ilen; ++i) {
			HSSFRow row = sheet.createRow(3 + i);
			for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j);
				formatterChain.handle(data.get(i)[0], j, template, cell, data.get(i)[j]);
//				formatter.format(j, cell, hzb_zbhz_prediction.get(i)[j]);
			}
		}		
			
		
		int sheetMergerCount = sheet.getNumMergedRegions();
		data = null;
		for (CompanyType ct : compTypes) {
			int lastRow = sheet.getLastRowNum() + 1;

			HSSFRow rowFrom = sheet.getRow(0);
			HSSFRow rowTo = sheet.createRow(lastRow);
			POIUtils.copyRow(workbook, rowFrom, rowTo, true);
			rowTo.getCell(0).setCellValue(ct.getValue() + "指标预测");
			
			rowFrom = sheet.getRow(1);
			rowTo = sheet.createRow(lastRow + 1);
			POIUtils.copyRow(workbook, rowFrom, rowTo, true);

			rowFrom = sheet.getRow(2);
			rowTo = sheet.createRow(lastRow + 2);
			POIUtils.copyRow(workbook, rowFrom, rowTo, true);		

			for (int i = 0; i < sheetMergerCount; i++) {
				CellRangeAddress range = sheet.getMergedRegion(i);
				range = range.copy();
				range.setLastRow(range.getLastRow() + lastRow);
				range.setFirstRow(range.getFirstRow() + lastRow);
				sheet.addMergedRegion(range);
			}

			lastRow += 3;
			if (0 == iMonth % 3) {
				data = gszbService.getThirdSeasonPredictionZBsOverview(d, VirtualJYZBOrganization.getJydw(companyManager, ct));
			}else if (1 == iMonth % 3) {
				data = gszbService.getFirstSeasonPredictionZBsOverview(d, VirtualJYZBOrganization.getJydw(companyManager, ct));
			}else if (2 == iMonth % 3) {
				data = gszbService.getSecondSeasonPredictionZBsOverview(d, VirtualJYZBOrganization.getJydw(companyManager, ct));
			}
			removeJzcsyl(ct, data);  
			for (int i = 0, ilen = data.size(); i < ilen; ++i) {
				HSSFRow row = sheet.createRow(lastRow + i);
				for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
					HSSFCell cell = row.createCell(j);
					formatterChain.handle(data.get(i)[0], j, template, cell, data.get(i)[j]);
//					formatter.format(j, cell, data.get(i)[j]);
				}
			}
		}
		
		String timeStamp = "" + Calendar.getInstance().getTimeInMillis();
		tmpCache.put(timeStamp + "template", template);
		tmpCache.put(timeStamp + "fileName", fileNameAndSheetName + ".xls");
		
		return ("{\"timeStamp\" : \"" + timeStamp + "\"}").getBytes("utf-8");
	}

	// 整体指标预测update
	@RequestMapping(value = "hzb_zbhz_prediction_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] gethzb_zbhz_prediction_update(
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Date d = DateSelection.getDate(request);
		String month = request.getParameter("month");
		int iMonth = Integer.valueOf(month);
		String hzb_zbhz_prediction = null;
		if (0 == iMonth % 3) {
			hzb_zbhz_prediction = JSONArray
					.fromObject(gszbService.getGsThirdSeasonPredictionZBsOverview(d)).toString()
					.replace("null", "\"--\"");
		}

		if (1 == iMonth % 3) {
			hzb_zbhz_prediction = JSONArray
					.fromObject(
							gszbService.getGsFirstSeasonPredictionZBsOverview(d))
					.toString().replace("null", "\"--\"");
		}

		if (2 == iMonth % 3) {
			hzb_zbhz_prediction = JSONArray
					.fromObject(
							gszbService.getGsSecondSeasonPredictionZBsOverview(d))
					.toString().replace("null", "\"--\"");
		}

		return hzb_zbhz_prediction.getBytes("utf-8");
	}
	
	//整体指标预测Update
	@RequestMapping(value = {"hzb_zbhz_prediction.do", "v2/hzb_zbhz_prediction.do"}, method = RequestMethod.GET)
	public ModelAndView gethzb_zbhz_prediction(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection();
		dateSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "hzb_zbhz_prediction", map);
	}

	//各产业五大经营指标预测update
	@RequestMapping(value = "financial_zbhz_prediction_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getfinancial_zbhz_prediction_update(
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Date d = DateSelection.getDate(request);
		String month = request.getParameter("month");
		int iMonth = Integer.valueOf(month);
		String financial_zbhz_prediction = null;
		if (0 == iMonth % 3) {
			financial_zbhz_prediction = JSONArray
					.fromObject(gszbService.getGcyThirdSeasonPredictionZBs(d)).toString()
					.replace("null", "\"--\"");

		}

		if (1 == iMonth % 3) {
			financial_zbhz_prediction = JSONArray
					.fromObject(
							gszbService.getGcyFirstSeasonPredictionZBs(d))
					.toString().replace("null", "\"--\"");
		}

		if (2 == iMonth % 3) {
			financial_zbhz_prediction = JSONArray
					.fromObject(gszbService.getGcySecondSeasonPredictionZBs(d)).toString()
					.replace("null", "\"--\"");
		}

		return financial_zbhz_prediction.getBytes("utf-8");
	}
	
	//各产业五大经营指标预测
	@RequestMapping(value = {"financial_zbhz_prediction.do", "v2/financial_zbhz_prediction.do"}, method = RequestMethod.GET)
	public ModelAndView getFinancial_zbhz_prediction(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection();
		dateSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "financial_zbhz_prediction", map);
	}
	
	
	// 各单位top5指标预测
		@RequestMapping(value = "gdw_zbhz_prediction_update.do", method = RequestMethod.GET)
		public @ResponseBody byte[] getGdw_zbhz_prediction_update(
				HttpServletRequest request, HttpServletResponse response)
				throws UnsupportedEncodingException {
			Date d = DateSelection.getDate(request);
			String month = request.getParameter("month");
			int iMonth = Integer.valueOf(month);
			String financial_zbhz_prediction = null;
			String zb = request.getParameter("zb");
			GSZB gszb = GSZB.valueOf(Integer.valueOf(zb));
			if (0 == iMonth % 3) {
				financial_zbhz_prediction = JSONArray
						.fromObject(gszbService.getGdwThirdSeasonPredictionZBs(gszb, d)).toString()
						.replace("null", "\"--\"");

			}

			if (1 == iMonth % 3) {
				financial_zbhz_prediction = JSONArray
						.fromObject(
								gszbService.getGdwFirstSeasonPredictionZBs(gszb, d))
						.toString().replace("null", "\"--\"");
			}

			if (2 == iMonth % 3) {
				financial_zbhz_prediction = JSONArray
						.fromObject(gszbService.getGdwSecondSeasonPredictionZBs(gszb, d)).toString()
						.replace("null", "\"--\"");
			}

			return financial_zbhz_prediction.getBytes("utf-8");
		}

		@RequestMapping(value = {"gdw_zbhz_prediction.do", "v2/gdw_zbhz_prediction.do"}, method = RequestMethod.GET)
		public ModelAndView getGdw_zbhz_prediction(HttpServletRequest request,
				HttpServletResponse response) {
			//String gszb = request.getParameter("zb");	
			//int zb = Integer.parseInt(request.getParameter("zb"));
			//String zbName  = service.getZBNameById(zb);
			Map<String, Object> map = new HashMap<String, Object>();
			DateSelection dateSel = new DateSelection();
			dateSel.select(map);
			//map.put("zbName", zbName);
			//map.put("zbId", zb);
			return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "gdw_zbhz_prediction", map);
		}
		
		//各单位指标预测导出
		@RequestMapping(value = "gdw_zbhz_prediction_export.do")
		public @ResponseBody byte[] getgdw_zbhz_prediction_export(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			Date d = DateSelection.getDate(request);
			int month = Integer.parseInt(request.getParameter("month"));
			String zb = request.getParameter("top5index");
			GSZB gszb = GSZB.valueOf(Integer.valueOf(zb));
			String zbName = request.getParameter("zbName");
			String year = request.getParameter("year");
			ExcelHelper template = null;
			List<String[]> data = null;
			String fileNameAndSheetName = null; 
//			CellFormatter formatter = null;
			FormatterHandler formatterChain = null;
			if (0 == month % 3) {
				data = gszbService.getGdwThirdSeasonPredictionZBs(gszb, d);
				fileNameAndSheetName = year + "年第" + DateHelper.getJdCount(month) + "季度末月"+ zbName + "预测完成情况";
				template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDFDWZBYJ_MY);
//				formatter = template.createCellFormatter()
//						.addType(5, CellFormatter.CellType.PERCENT)
//						.addType(7, CellFormatter.CellType.PERCENT)					
//						.addType(9, CellFormatter.CellType.PERCENT)
//						.addType(11, CellFormatter.CellType.PERCENT)
//						.addType(13, CellFormatter.CellType.PERCENT)
//						.addType(15, CellFormatter.CellType.PERCENT)
//						.addType(20, CellFormatter.CellType.PERCENT)
//						.addType(22, CellFormatter.CellType.PERCENT)
//						.addType(24, CellFormatter.CellType.PERCENT);
				formatterChain = this.getFormatterChainDataOnly(
						new Integer[]{5, 7, 9, 11, 13, 15, 20, 22, 24}, new Integer[]{1, 2, 3, 4});
			}

			if (1 == month % 3) {
				data = gszbService.getGdwFirstSeasonPredictionZBs(gszb, d);
				fileNameAndSheetName = year + "年第" + DateHelper.getJdCount(month) + "季度首月"+ zbName + "预测完成情况";
				template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDFDWZBYJ_SY);
//				formatter = template.createCellFormatter()
//						.addType(4, CellFormatter.CellType.PERCENT)
//						.addType(6, CellFormatter.CellType.PERCENT)					
//						.addType(10, CellFormatter.CellType.PERCENT)
//						.addType(12, CellFormatter.CellType.PERCENT)
//						.addType(14, CellFormatter.CellType.PERCENT)
//						.addType(16, CellFormatter.CellType.PERCENT);
				formatterChain = this.getFormatterChainDataOnly(
						new Integer[]{4, 6, 10, 12, 14, 16}, new Integer[]{1, 2, 3});		
			}

			if (2 == month % 3) {
				data = gszbService.getGdwSecondSeasonPredictionZBs(gszb, d);
				fileNameAndSheetName = year + "年第" + DateHelper.getJdCount(month) + "季度次月"+ zbName + "预测完成情况";
				template = ExcelTemplate.createJygkTemplate(JygkSheetType.JDFDWZBYJ_CY);
//				formatter = template.createCellFormatter()
//						.addType(4, CellFormatter.CellType.PERCENT)
//						.addType(6, CellFormatter.CellType.PERCENT)	
//						.addType(8, CellFormatter.CellType.PERCENT)	
//						.addType(10, CellFormatter.CellType.PERCENT)
//						.addType(13, CellFormatter.CellType.PERCENT)
//						.addType(15, CellFormatter.CellType.PERCENT)
//						.addType(17, CellFormatter.CellType.PERCENT)
//						.addType(19, CellFormatter.CellType.PERCENT);
				formatterChain = this.getFormatterChainDataOnly(
						new Integer[]{4, 6, 8, 10, 13, 15, 17, 19}, new Integer[]{1, 2, 3});	
			}
			

			HSSFWorkbook workbook = template.getWorkbook();
			workbook.setSheetName(0, fileNameAndSheetName);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 0, ilen = data.size(); i < ilen; ++i) {
				HSSFRow row = sheet.getRow(2 + i);
				for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
					HSSFCell cell = row.getCell(j + 1);
					if (null != cell){
						formatterChain.handle(null, j, template, cell, data.get(i)[j]);
					}
				}
			}
				
			template.write(response, fileNameAndSheetName + ".xls");
			return "".getBytes("utf-8");
		}
			
		
}
