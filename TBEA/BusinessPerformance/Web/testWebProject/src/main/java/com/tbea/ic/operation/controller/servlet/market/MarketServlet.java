package com.tbea.ic.operation.controller.servlet.market;

import java.io.IOException;
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

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.companys.CompanyManager;
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

	
	final static String FILETYPE_PROJECT = "2";
	final static String FILETYPE_SIGN = "4";
	final static String FILETYPE_BID = "3";
	
	final static String[] COMPANIES = new String[] { "全部单位", "沈变", "衡变", "新变",
			"天变", "鲁缆", "新缆", "德缆" };
	final static String[] ACCOUNTS = new String[] { "输变电产业集团市场部", "沈变公司市场部", "衡变公司市场部", "新变公司市场部",
		"天变公司市场部", "鲁缆公司市场部", "新缆公司市场部", "德缆公司市场部" };
	
	@RequestMapping(value = "import.do")
	public @ResponseBody byte[] importExcel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("upfile") CommonsMultipartFile file) throws IOException {

		String mktType = request.getParameter("filetype");
		String result = "filetype error";
		try{
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			if (FILETYPE_PROJECT.equals(mktType)){
				result = marketService.importProjectData(workbook);
			}else if (FILETYPE_SIGN.equals(mktType)) {
				result = marketService.importSignData(workbook);
			}else if (FILETYPE_BID.equals(mktType)) {
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
		String result = "";
		if (FILETYPE_PROJECT.equals(mktType)){
			result = marketService.importProjectData(arrData);
		}else if (FILETYPE_SIGN.equals(mktType)) {
			result = marketService.importSignData(arrData);
		}else if (FILETYPE_BID.equals(mktType)) {
			result = marketService.importBidData(arrData);
		}
		return result.getBytes("utf-8");
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
		//Company comp = companyManager.getBMOrganization().getCompany(compType);
		Account account = SessionManager.getAccount(request.getSession());
		String companyName =  getCompanyName(account);
		String rpttype = request.getParameter("docType");
		
		//request。getParameter("rpttype");
		List<String[][]> list = new ArrayList<String[][]>();
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		if (null != request.getParameter("year")){
			year = Integer.valueOf(request.getParameter("year"));
		}
		if(rpttype.equals("bid_info")){
			list.add(marketService.getBidData(companyName, year));
		}else if(rpttype.equals("project_info")){
			list.add(marketService.getPrjData(companyName, year));
		}else if(rpttype.equals("sign_contract")){
			list.add(marketService.getContData(companyName));
		}
		String listJson = JSONArray.fromObject(list).toString().replace("null", "\"\"");
//		System.out.println(listJson);
		return listJson.getBytes("utf-8");
	}

}
