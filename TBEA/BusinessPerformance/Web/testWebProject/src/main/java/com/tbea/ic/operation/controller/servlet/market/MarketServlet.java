package com.tbea.ic.operation.controller.servlet.market;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.service.market.MarketService;

@Controller
@RequestMapping(value = "Market")
public class MarketServlet {

	@Autowired
	private MarketService marketService;

	
	final static String FILETYPE_PROJECT = "2";
	final static String FILETYPE_SIGN = "4";
	final static String FILETYPE_BID = "3";
	
	@RequestMapping(value = "import.do")
	public @ResponseBody byte[] importExcel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("upfile") CommonsMultipartFile file,
			@RequestParam("filetype") String mktType) throws IOException {
		String result = "filetype error";
		try{
			HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
			if (FILETYPE_PROJECT.equals(mktType)){
				result = marketService.importProjectData(workbook);
			}else if (FILETYPE_SIGN.equals(mktType)) {
				result = marketService.importSignData(workbook);
			}else if (FILETYPE_BID.equals(mktType)) {
				result = marketService.importBidData(workbook);
			}
		}catch(Exception e){
			result = "exception occurrence";
		}
		
		return result.getBytes("utf-8");
	}
	
	
	
	@RequestMapping(value = "project_info.do")
	public ModelAndView getProjectInfo(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "project_info_update.do")
	public @ResponseBody byte[] getProjectInfoUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		

		return null;
	}

	
	@RequestMapping(value = "bid_info.do")
	public ModelAndView getBidInfo(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "bid_info_update.do")
	public @ResponseBody byte[] getBidInfoUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		

		return null;
	}
	
	@RequestMapping(value = "sign_contract.do")
	public ModelAndView getSignContract(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "sign_contract_update.do")
	public @ResponseBody byte[] getSignContractUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		 

		return null;
	}

}
