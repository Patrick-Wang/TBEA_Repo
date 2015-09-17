package com.tbea.ic.operation.controller.servlet.market;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
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

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	private MarketService marketService;

	final static String TYPE_PROJECT = "2";
	final static String TYPE_SIGN = "4";
	final static String TYPE_BID = "3";
	
	final static String[] COMPANIES = new String[] { "输变电产业集团", "沈变", "衡变", "新变",
			"天变", "鲁缆", "新缆", "德缆" };
	final static String[] ACCOUNTS = new String[] { "输变电产业集团市场部", "沈变市场部", "衡变市场部", "新变市场部",
		"天变市场部", "鲁缆市场部", "新缆市场部", "德缆市场部" };
	
	@RequestMapping(value = "import.do")
	public @ResponseBody byte[] importExcel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("upfile") CommonsMultipartFile file) throws IOException {

		String mktType = request.getParameter("filetype");
		String result = "filetype error";
		try{
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			if (TYPE_PROJECT.equals(mktType)){
				result = marketService.importProjectData(workbook);
			}else if (TYPE_SIGN.equals(mktType)) {
				result = marketService.importSignData(workbook);
			}else if (TYPE_BID.equals(mktType)) {
				result = marketService.importBidData(workbook);
			}
		}catch(Exception e){
			result = "导入错误, 请检查文档格式";
			e.printStackTrace();
		}
		
		return JSONObject.fromObject("{\"result\" :  \"" + result + "\"}").toString().getBytes("utf-8");
	}
	
	
	@RequestMapping(value = "test_carry_down.do")
	public @ResponseBody byte[] testCarryDown(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		carryDown();
		return "finished".getBytes("utf-8");
	}
	
	@Scheduled(cron="0 0 0 1 1 ?")
	public void carryDown(){
		Calendar start = Calendar.getInstance();
		start.set(start.get(Calendar.YEAR) - 1, 0, 1, 0, 0, 0);
		Calendar end = Calendar.getInstance();
		end.set(end.get(Calendar.YEAR) - 1, 11, 31, 23, 59, 59);

		Calendar target = Calendar.getInstance();
		
		marketService.carryDownBidInfo(new Date(start.getTimeInMillis()), new Date(end.getTimeInMillis()), new Date(target.getTimeInMillis()));
		marketService.carryDownProjectInfo(new Date(start.getTimeInMillis()), new Date(end.getTimeInMillis()), new Date(target.getTimeInMillis()));
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
		String result = "false";
		if (TYPE_PROJECT.equals(mktType)){
			result = marketService.importProjectData(arrData);
		}else if (TYPE_SIGN.equals(mktType)) {
			result = marketService.importSignData(arrData);
		}else if (TYPE_BID.equals(mktType)) {
			result = marketService.importBidData(arrData);
		}
		
		if ("OK".equals(result)){
			result = "true";
		}
		return ("{\"result\":\"" + result + "\"}").getBytes("utf-8");
	}
	
	private String getCompanyName(Account account){
		if (ACCOUNTS[0].equals(account.getName())){
			return COMPANIES[0];
		} else if (ACCOUNTS[1].equals(account.getName())){
			return COMPANIES[1];
		} else if (ACCOUNTS[2].equals(account.getName())){
			return COMPANIES[2];
		} else if (ACCOUNTS[3].equals(account.getName())){
			return COMPANIES[3];
		} else if (ACCOUNTS[4].equals(account.getName())){
			return COMPANIES[4];
		} else if (ACCOUNTS[5].equals(account.getName())){
			return COMPANIES[5];
		} else if (ACCOUNTS[6].equals(account.getName())){
			return COMPANIES[6];
		} else if (ACCOUNTS[7].equals(account.getName())){
			return COMPANIES[7];
		}
		return "";
	}
	
	@RequestMapping(value = "mkt_view.do")
	public ModelAndView getMktView(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Account account = SessionManager.getAccount(request.getSession());
		map.put("companyName", getCompanyName(account));
		return new ModelAndView("mkt_view_data", map);
	}
	
	@RequestMapping(value = "mkt_view_update.do")
	public @ResponseBody byte[] getMktViewUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Account account = SessionManager.getAccount(request.getSession());
		String companyName =  getCompanyName(account);
		String rpttype = request.getParameter("docType");
		

		List<String[][]> list = new ArrayList<String[][]>();
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		if (null != request.getParameter("year")){
			year = Integer.valueOf(request.getParameter("year"));
		}

		if(rpttype.equals(TYPE_BID)){
			list.add(marketService.getBidData(companyName, year));
		}else if(rpttype.equals(TYPE_PROJECT)){
			list.add(marketService.getPrjData(companyName, year));
		}else if(rpttype.equals(TYPE_SIGN)){
			list.add(marketService.getContData(companyName));
		}
		String listJson = JSONArray.fromObject(list).toString().replace("null", "\"\"");
//		System.out.println(listJson);
		return listJson.getBytes("utf-8");
	}
	
	@RequestMapping(value = "mkt_view_export.do")
	public @ResponseBody byte[] mktViewExport(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Account account = SessionManager.getAccount(request.getSession());
		String companyName =  getCompanyName(account);
		String rpttype = request.getParameter("mktType");
		

		List<String[][]> list = new ArrayList<String[][]>();
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		if (null != request.getParameter("year")){
			year = Integer.valueOf(request.getParameter("year"));
		}
		String[] title = null;
		if(rpttype.equals(TYPE_BID)){
			list.add(marketService.getBidData(companyName, year));
			title = new String[]{
					"单位",
					"投标编号",
					"项目信息编号",
					"授权编号",
					"办事处或项目部",
					"投标月份",
					"投标日期",
					"所属行业",
					"所属系统",
					"项目所在区域",
					"项目名称",
					"业主单位",
					"产品型号",
					"电压等级",
					"投标数量",
					"投标容量(kVA)",
					"我厂投标价格",
					"中标厂家",
					"中标价格",
					"中标或未中标原因分析",
					"定标月份",
					"状态",
					"是否反馈投标总结",
					"具体投标单位"
			};
		}else if(rpttype.equals(TYPE_PROJECT)){
			list.add(marketService.getPrjData(companyName, year));
			title = new String[]{
					"单位",
					"办事处名称",
					"项目序号",
					"所属行业",
					"所属系统",
					"项目名称",
					"业主单位",
					"产品型号",
					"数量",
					"预计投标金额",
					"预计招标时间",
					"项目所在区域",
					"项目简介",
					"目前推进跟踪情况及后期计划",
					"本单位项目负责人及联系方式",
					"本单位负责该项目的主管领导",
					"跟踪该项目的其它内部企业名称",
					"投标情况",
					"备注"
			};
		}else if(rpttype.equals(TYPE_SIGN)){
			list.add(marketService.getContData(companyName));
			title = new String[]{
					"单位",
					"合同编号",
					"办事处或项目部",
					"签约月份",
					"所属行业",
					"所属系统",
					"项目所在区域",
					"项目名称",
					"业主单位",
					"产品型号/类型",
					"电压等级",
					"数量（台）",
					"签约容量(kVA)",
					"签约金额",
					"付款方式",
					"签订人",
					"具体签约单位"
			};
		}
		
		HSSFWorkbook workbook = new HSSFWorkbook();	
		HSSFSheet sheet = workbook.createSheet("市场部");
		
		HSSFRow row = sheet.createRow(0);
		for (int j = 0, jlen = title.length; j < jlen; ++j) {
			HSSFCell cell = row.createCell(j);
			cell.setCellValue(title[j]);
		}
		
		for (int i = 0, ilen = list.get(0).length; i < ilen; ++i) {
			row = sheet.createRow(i + 1);
			for (int j = 0, jlen = title.length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j);
				if (list.get(0)[i][j] != null){
					cell.setCellValue(list.get(0)[i][j]);
				}
			}
		}
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition","attachment;filename=\""+ java.net.URLEncoder.encode("市场部信息", "UTF-8")  +".xls\"");
		workbook.write(response.getOutputStream());
		return "".getBytes("utf-8");
	}

}
