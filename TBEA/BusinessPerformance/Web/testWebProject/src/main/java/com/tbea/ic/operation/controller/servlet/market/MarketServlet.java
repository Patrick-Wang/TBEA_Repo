package com.tbea.ic.operation.controller.servlet.market;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
			result = "位置导入错误";
			e.printStackTrace();
		}
		
		return result.getBytes("utf-8");
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
	
	
	@RequestMapping(value = "mkt_view.do")
	public ModelAndView getMktView(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("mkt_view_data");
	}
	
	@RequestMapping(value = "mkt_view_update.do")
	public @ResponseBody byte[] getMktViewUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		//Company comp = companyManager.getBMOrganization().getCompany(compType);
		String companyName = request.getParameter("companyName");
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
