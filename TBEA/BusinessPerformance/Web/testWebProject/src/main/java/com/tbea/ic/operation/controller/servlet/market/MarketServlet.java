package com.tbea.ic.operation.controller.servlet.market;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.jyzbexcel.FormatterHandler;
import com.tbea.ic.operation.common.jyzbexcel.JyzbExcelTemplate;
import com.tbea.ic.operation.common.jyzbexcel.JyzbExcelTemplate.SheetType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.market.MarketService;

@Controller
@RequestMapping(value = "Market")
public class MarketServlet {

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Autowired
	private MarketService marketService;

	final static String TYPE_PROJECT = "2";
	final static String TYPE_SIGN = "4";
	final static String TYPE_BID = "3";

	final static Map<String, String> accountCompMap = new HashMap<String, String>();
	static {
		accountCompMap.put("股份公司市场部", "股份公司");
		accountCompMap.put("沈变市场部", "沈变");
		accountCompMap.put("衡变市场部", "衡变");
		accountCompMap.put("新变市场部", "新变");
		accountCompMap.put("天变市场部", "天变");
		accountCompMap.put("鲁缆市场部", "鲁缆");
		accountCompMap.put("新缆市场部", "新缆");
		accountCompMap.put("德缆市场部", "德缆");
	}
	
	@RequestMapping(value = "import.do")
	public @ResponseBody byte[] importExcel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("upfile") CommonsMultipartFile file)
			throws IOException {

		String mktType = request.getParameter("filetype");
		String result = "filetype error";
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			if (TYPE_PROJECT.equals(mktType)) {
				result = marketService.importProjectData(workbook);
			} else if (TYPE_SIGN.equals(mktType)) {
				result = marketService.importSignData(workbook);
			} else if (TYPE_BID.equals(mktType)) {
				result = marketService.importBidData(workbook);
			}
		} catch (Exception e) {
			result = "导入错误, 请检查文档格式";
			e.printStackTrace();
		}

		return JSONObject.fromObject("{\"result\" :  \"" + result + "\"}")
				.toString().getBytes("utf-8");
	}

	@RequestMapping(value = "test_carry_down.do")
	public @ResponseBody byte[] testCarryDown(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		carryDown();
		return "finished".getBytes("utf-8");
	}

	@Scheduled(cron = "0 0 0 1 1 ?")
	public void carryDown() {
		Calendar start = Calendar.getInstance();
		start.set(start.get(Calendar.YEAR) - 1, 0, 1, 0, 0, 0);
		Calendar end = Calendar.getInstance();
		end.set(end.get(Calendar.YEAR) - 1, 11, 31, 23, 59, 59);

		Calendar target = Calendar.getInstance();

		marketService.carryDownBidInfo(new Date(start.getTimeInMillis()),
				new Date(end.getTimeInMillis()),
				new Date(target.getTimeInMillis()));
		marketService.carryDownProjectInfo(new Date(start.getTimeInMillis()),
				new Date(end.getTimeInMillis()),
				new Date(target.getTimeInMillis()));
	}

	@RequestMapping(value = "mkt_import_data.do")
	public ModelAndView getMktImportData(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("mkt_import_data");
	}

	@RequestMapping(value = "mkt_entry_data.do")
	public @ResponseBody byte[] getMktEntryData(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String data = request.getParameter("data");
		JSONArray arrData = JSONArray.fromObject(data);
		String mktType = request.getParameter("mktType");
		String oper = request.getParameter("editOper");
		ErrorCode result = ErrorCode.OK;
		try {
			if ("edit".equals(oper)) {
				String rawKey = request.getParameter("editOriginalKey");
				if (TYPE_PROJECT.equals(mktType)) {
					result = marketService.editProjectData(
							arrData.getJSONArray(0), rawKey);
				} else if (TYPE_SIGN.equals(mktType)) {
					result = marketService.editSignData(
							arrData.getJSONArray(0), rawKey);
				} else if (TYPE_BID.equals(mktType)) {
					result = marketService.editBidData(arrData.getJSONArray(0),
							rawKey);
				}
			} else {
				if (TYPE_PROJECT.equals(mktType)) {
					result = marketService.addProjectData(arrData
							.getJSONArray(0));
				} else if (TYPE_SIGN.equals(mktType)) {
					result = marketService.addSignData(arrData.getJSONArray(0));
				} else if (TYPE_BID.equals(mktType)) {
					result = marketService.addBidData(arrData.getJSONArray(0));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ErrorCode.DATABASE_EXCEPTION;
		}

		return Util.response(result);
	}

	private String getCompanyName(Account account) {
		String comp = accountCompMap.get(account.getName());
		if (comp == null){
			return "";
		}
		return comp;
	}

	@RequestMapping(value = "mkt_view.do")
	public ModelAndView getMktView(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Account account = SessionManager.getAccount(request.getSession());
		map.put("companyName", getCompanyName(account));
		return new ModelAndView("mkt_view_data", map);
	}

	public static void compress(InputStream is, OutputStream os)  
            throws Exception {  
  
        GZIPOutputStream gos = new GZIPOutputStream(os);  
  
        int count;  
        byte data[] = new byte[1024];  
        while ((count = is.read(data, 0, 1024)) != -1) {  
            gos.write(data, 0, count);  
        }
  
        gos.finish();  
  
        gos.flush();  
        gos.close();  
    }  
	
	private static byte[] compress(byte[] data) throws Exception {  
        ByteArrayInputStream bais = new ByteArrayInputStream(data);  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
  
        // 压缩  
        compress(bais, baos);  
  
        byte[] output = baos.toByteArray();  
  
        baos.flush();  
        baos.close();  
  
        bais.close();  
  
        return output;  
    }  
	
	@RequestMapping(value = "mkt_view_update.do")
	public @ResponseBody byte[] getMktViewUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = SessionManager.getAccount(request.getSession());
		String companyName = getCompanyName(account);
		String rpttype = request.getParameter("docType");

		List<String[][]> list = new ArrayList<String[][]>();
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		if (null != request.getParameter("year")) {
			year = Integer.valueOf(request.getParameter("year"));
		}
//		Util.Elapse escape = new Util.Elapse();
//		escape.start();
		if (rpttype.equals(TYPE_BID)) {
			list.add(marketService.getBidData(companyName, year));
		} else if (rpttype.equals(TYPE_PROJECT)) {
			list.add(marketService.getPrjData(companyName, year));
		} else if (rpttype.equals(TYPE_SIGN)) {
			list.add(marketService.getContData(companyName));
		}
//		escape.end("getPrjData");
		
//		escape.start();
		String listJson = JSONArray.fromObject(list).toString()
				.replace("null", "\"\"");
//		escape.end("toJson");
		// System.out.println(listJson);
		
//		escape.start();
		byte[] result = listJson.getBytes("utf-8");
//		escape.end("tobytes " + result.length + " ");
		
//		escape.start();
		result = compress(result);
//		escape.end("tobytes " + result.length + " ");
		
		response.addHeader("Content-Encoding", "gzip"); 
		
		return result;
	}
	
	
	@RequestMapping(value = "mkt_bid_analysis.do")
	public ModelAndView getMktBidAnalysis(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection();
		dateSel.select(map);
		Account account = SessionManager.getAccount(request.getSession());
		map.put("companyName", getCompanyName(account));
		return new ModelAndView("mkt_bid_analysis", map);
	}

	@RequestMapping(value = "mkt_bid_analysis_update.do")
	public @ResponseBody byte[] getMktBidAnalysisUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date date = DateSelection.getDate(request);
		String companyName = request.getParameter("companyName");
		List<List<String>> result = null;
		String type = request.getParameter("type");
		if ("bid_industry".equals(type)){
			result = marketService.getIndustryBidData(companyName, date);
		} else if ("bid_company".equals(type)){
			result = marketService.getCompanyBidData(companyName, date);
		}
		
		return JSONArray.fromObject(result).toString().replace("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "mkt_contract_analysis.do")
	public ModelAndView getMktContractAnalysis(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection();
		dateSel.select(map);
		Account account = SessionManager.getAccount(request.getSession());
		map.put("companyName", getCompanyName(account));
		return new ModelAndView("mkt_contract_analysis", map);
	}

	@RequestMapping(value = "mkt_contract_analysis_update.do")
	public @ResponseBody byte[] getMktContractAnalysisUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date date = DateSelection.getDate(request);
		String companyName = request.getParameter("companyName");
		List<List<String>> result = null;
		String type = request.getParameter("type");
		if ("contract_industry".equals(type)){
			result = marketService.getIndustrySignData(companyName, date);
		} else if ("contract_company".equals(type)){
			result = marketService.getCompanySignData(companyName, date);
		}
		
		return JSONArray.fromObject(result).toString().replace("null", "\"--\"").getBytes("utf-8");
	}
	
	
	@RequestMapping(value = "mkt_region_analysis.do")
	public ModelAndView getMktRegionAnalysis(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection();
		dateSel.select(map);
		Account account = SessionManager.getAccount(request.getSession());
		map.put("companyName", getCompanyName(account));
		return new ModelAndView("mkt_region_analysis", map);
	}
	
	@RequestMapping(value = "mkt_region_analysis_update.do")
	public @ResponseBody byte[] getMktRegionAnalysisUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date endDate = DateSelection.getDate(request);
		Date startDate = DateSelection.getStartDate(request);
		String companyName = request.getParameter("companyName");
		List<List<String>> result = null;
		String type = request.getParameter("type");
		if ("region_index".equals(type)){
			result = marketService.getAreaMixedAnalysisData(companyName, startDate, endDate);
		} else if ("industry_index".equals(type)){
			result = marketService.getIndustryMixedAnalysisData(companyName, startDate, endDate);
		}
		
		return JSONArray.fromObject(result).toString().replace("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "mkt_view_export.do")
	public @ResponseBody byte[] mktViewExport(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Account account = SessionManager.getAccount(request.getSession());
		String companyName = getCompanyName(account);
		String rpttype = request.getParameter("mktType");

		List<String[][]> list = new ArrayList<String[][]>();
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		if (null != request.getParameter("year")) {
			year = Integer.valueOf(request.getParameter("year"));
		}
		String[] title = null;
		if (rpttype.equals(TYPE_BID)) {
			list.add(marketService.getBidData(companyName, year));
			title = new String[] { "单位", "投标编号", "项目信息编号", "授权编号", "办事处或项目部",
					"投标月份", "投标日期", "所属行业", "所属系统", "项目所在区域", "项目名称", "业主单位",
					"产品型号", "电压等级", "投标数量", "投标容量(kVA)", "我厂投标价格", "中标厂家",
					"中标价格", "中标或未中标原因分析", "定标月份", "状态", "是否反馈投标总结", "具体投标单位" };
		} else if (rpttype.equals(TYPE_PROJECT)) {
			list.add(marketService.getPrjData(companyName, year));
			title = new String[] { "单位", "办事处名称", "项目序号", "所属行业", "所属系统",
					"项目名称", "业主单位", "产品型号", "数量", "预计投标金额", "预计招标时间", "项目所在区域",
					"项目简介", "目前推进跟踪情况及后期计划", "本单位项目负责人及联系方式", "本单位负责该项目的主管领导",
					"跟踪该项目的其它内部企业名称", "投标限制", "投标情况", "备注" };
		} else if (rpttype.equals(TYPE_SIGN)) {
			list.add(marketService.getContData(companyName));
			title = new String[] { "单位", "合同编号", "办事处或项目部", "签约月份", "所属行业",
					"所属系统", "项目所在区域", "项目名称", "业主单位", "产品型号/类型", "电压等级",
					"数量（台）", "签约容量(kVA)", "签约金额", "付款方式", "签订人", "具体签约单位",
					"是否现款现货", "是否制造服务业" };
		}

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("市场部信息");

		HSSFRow row = sheet.createRow(0);
		for (int j = 0, jlen = title.length; j < jlen; ++j) {
			HSSFCell cell = row.createCell(j);
			cell.setCellValue(title[j]);
		}

		for (int i = 0, ilen = list.get(0).length; i < ilen; ++i) {
			row = sheet.createRow(i + 1);
			for (int j = 0, jlen = title.length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j);
				if (!"null".equals(list.get(0)[i][j])) {
					cell.setCellValue(list.get(0)[i][j]);
				} else {
					cell.setCellValue("");
				}
			}
		}

		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=\""
				+ java.net.URLEncoder.encode("市场部信息", "UTF-8") + ".xls\"");
		workbook.write(response.getOutputStream());
		return "".getBytes("utf-8");
	}

	// For projectinfo of marketdep specially processed
	@RequestMapping(value = "mkt_view_export1.do")
	public @ResponseBody byte[] mktViewExport1(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Account account = SessionManager.getAccount(request.getSession());
		String companyName = getCompanyName(account);
		List<String[][]> list = new ArrayList<String[][]>();
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		if (null != request.getParameter("year")) {
			year = Integer.valueOf(request.getParameter("year"));
		}

		list.add(marketService.getPrjData(companyName, year));

		JyzbExcelTemplate template = null;
		template = JyzbExcelTemplate.createTemplate(SheetType.MARKET_PRO);
		HSSFWorkbook workbook = template.getWorkbook();
		workbook.setSheetName(0, "市场部信息");
		HSSFSheet sheet = workbook.getSheetAt(0);

		for (int i = 0, ilen = list.size(); i < ilen; ++i) {
			for (int j = 0, jlen = list.get(i).length; j < jlen; ++j) {
				HSSFRow row = sheet.createRow(1 + j);
				for (int k = 0, klen = list.get(i)[j].length; k < klen; k++) {
					HSSFCell cell = row.createCell(k);
					if (!"null".equals(list.get(i)[j][k])) {
						cell.setCellValue(list.get(i)[j][k]);
					} else {
						cell.setCellValue("");
					}
				}
			}
		}
		template.write(response, "市场部信息" + ".xls");
		return "".getBytes("utf-8");

	}
	
	
//	@RequestMapping(value = "industry_bid_analysis_update.do")
//	public @ResponseBody byte[] getIndustryBidAnalysisUpdate(HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		Date date = DateSelection.getDate(request);
//		String companyName = request.getParameter("companyName");
//		List<List<String>> result = marketService.getIndustryBidData(companyName, date);
//		return JSONArray.fromObject(result).toString().getBytes("utf-8");
//
//	}
//	
//	@RequestMapping(value = "company_bid_analysis_update.do")
//	public @ResponseBody byte[] getCompanyBidAnalysisUpdate(HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		Date date = DateSelection.getDate(request);
//		String companyName = request.getParameter("companyName");
//		List<List<String>> result = marketService.getCompanyBidData(companyName, date);
//		return JSONArray.fromObject(result).toString().getBytes("utf-8");
//	}
}
