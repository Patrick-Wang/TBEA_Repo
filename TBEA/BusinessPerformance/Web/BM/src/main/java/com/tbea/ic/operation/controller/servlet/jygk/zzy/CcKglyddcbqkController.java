package com.tbea.ic.operation.controller.servlet.jygk.zzy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyExcelTemplate;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyFormatterHandler;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyHeaderFormatterHandler;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyNumberFormatterHandler;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyNumberFormatterHandler.NumberType;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyPercentFormatterHandler;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.jygk.zzy.CcKglyddcbqkService;
import com.tbea.ic.operation.service.jygk.zzy.DwxxDto;
import com.tbea.ic.operation.service.jygk.zzy.SystemExtendAuthService;
import com.tbea.ic.operation.service.jygk.zzy.ZzyDWXXService;


@Controller
@RequestMapping(value = "kglyddcbqk")
public class CcKglyddcbqkController {
	
	@Autowired
	private CcKglyddcbqkService zzyKglyddcbqkService;
	@Autowired
	ZzyDWXXService zzyDWXXService;
	@Autowired
	private SystemExtendAuthService systemExtendAuthService;
	
	@RequestMapping(value = "openviewbyq.do", method = RequestMethod.GET)//打开页面
	public ModelAndView openViewByq(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);		
		
		Map<String, Object> map = new HashMap<String, Object>();
		//设置可选年、月到map
		DateSelection dateSel = new DateSelection(year, month);
		dateSel.select(map);
		
		//设置可选公司		
		HttpSession session=request.getSession(false);
		Account account=SessionManager.getAccount(session);
		List<DwxxDto> dwxxList=systemExtendAuthService.getJygkZzyDwxxListView(account);
		map.put("comps", JSONArray.fromObject(dwxxList).toString());
		
		//设置可选表格类型到map
		return new ModelAndView("jygkzzy/cc_kglyddcbqk_byq_template", map);
	}	
	
	@RequestMapping(value = "readviewbyq.do", method = RequestMethod.GET)//选择
	public @ResponseBody byte[] readViewByq(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String dwxxId = request.getParameter("companyId");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		List<String[]> ret =  zzyKglyddcbqkService.getViewDataList(year, month, dwxxId,"20015");
		String retstr = JSONArray.fromObject(ret).toString().replace("null", "\"\"");
		String result = "{\"values\":" + retstr + "}"; 	
		return result.getBytes("utf-8");
	}
	
	@RequestMapping(value = "openviewxl.do", method = RequestMethod.GET)//打开页面
	public ModelAndView openViewXl(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);		
		
		Map<String, Object> map = new HashMap<String, Object>();
		//设置可选年、月到map
		DateSelection dateSel = new DateSelection(year, month);
		dateSel.select(map);
		
		//设置可选公司		
		HttpSession session=request.getSession(false);
		Account account=SessionManager.getAccount(session);
		List<DwxxDto> dwxxList=systemExtendAuthService.getJygkZzyDwxxListView(account);
		map.put("comps", JSONArray.fromObject(dwxxList).toString());
		
		//设置可选表格类型到map
		return new ModelAndView("jygkzzy/cc_kglyddcbqk_xl_template", map);
	}	
	
	@RequestMapping(value = "readviewxl.do", method = RequestMethod.GET)//选择
	public @ResponseBody byte[] readViewXl(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String dwxxId = request.getParameter("companyId");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		List<String[]> ret =  zzyKglyddcbqkService.getViewDataList(year, month ,dwxxId, "20016");
		String retstr = JSONArray.fromObject(ret).toString().replace("null", "\"\"");
		String result = "{\"values\":" + retstr + "}"; 	
		return result.getBytes("utf-8");
	}
	
	@RequestMapping(value = "exportbyq.do")
	public @ResponseBody byte[] exportbyq(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String dwxxId="";
		String dwxxname="";
		dwxxId = request.getParameter("companyId");
		if(dwxxId.equals("900000")){
			dwxxname="变压器产业";
		}else if(dwxxId.equals("800000")){
			dwxxname="线缆产业";
		}else{
			dwxxname=zzyDWXXService.getDwxx(Integer.parseInt(dwxxId)).getName();
		}
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		List<String[]> ret =  zzyKglyddcbqkService.getViewDataList(year,month,dwxxId, "20015");		
		
		JygkZzyExcelTemplate template = JygkZzyExcelTemplate.createJygkTemplate("20015");
		String fileNameAndSheetName = dwxxname;
		fileNameAndSheetName += year + "年" + month + "月可供履约订单储备情况（变压器）";		
		
		HSSFWorkbook workbook = template.getWorkbook();
		HSSFSheet sheet = workbook.getSheetAt(0);

		JygkZzyFormatterHandler formatterChain = this.getFormatterChainDataOnly(
				new Integer[]{9,12,15,18,21,24}, new Integer[]{1,2,3,4,5,6,7,8,10,11,13,14,16,17,19,20,22,23,25,26,27,28});
		
		HSSFRow row = sheet.getRow(3);
		for (int i = 0; i < ret.size(); ++i) {
			if(i > 0) {
				row = sheet.createRow(i+3);
			}
			
			for(int j=0; ret.get(i) != null && j<ret.get(i).length;j++) {
				HSSFCell cell = row.getCell(j);
				if(j==0) {
					cell.setCellValue(ret.get(i)[j]);
				} else {
					formatterChain.handle(
						ret.get(i)[0], 
						j, template, cell, ret.get(i)[j]);
				}
			}
		}
			
		template.write(response, fileNameAndSheetName + ".xls");
		
		return "".getBytes("utf-8");
	}
	
	@RequestMapping(value = "exportxl.do")
	public @ResponseBody byte[] exportxl(
			HttpServletRequest request, HttpServletResponse response)
					throws IOException {
		String dwxxId="";
		String dwxxname="";
		dwxxId = request.getParameter("companyId");
		if(dwxxId.equals("900000")){
			dwxxname="变压器产业";
		}else if(dwxxId.equals("800000")){
			dwxxname="线缆产业";
		}else{
			dwxxname=zzyDWXXService.getDwxx(Integer.parseInt(dwxxId)).getName();
		}
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		List<String[]> ret =  zzyKglyddcbqkService.getViewDataList(year, month, dwxxId, "20016");		
		
		JygkZzyExcelTemplate template = JygkZzyExcelTemplate.createJygkTemplate("20016");
		String fileNameAndSheetName = dwxxname;
		fileNameAndSheetName += year + "年" + month + "月可供履约订单储备情况（线缆）";		
		
		HSSFWorkbook workbook = template.getWorkbook();
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		JygkZzyFormatterHandler formatterChain = this.getFormatterChainWithHeader(
				new Integer[]{6,9,12}, new Integer[]{1,2,3,4,5,7,8,10,11,13,14,15});
		
		HSSFRow row = sheet.getRow(3);
		for (int i = 0; i < ret.size(); ++i) {
			if(i > 0) {
				row = sheet.createRow(i+3);
			}
			for(int j=0; ret.get(i) != null && j<ret.get(i).length;j++) {
				HSSFCell cell = row.getCell(j);
				if(j==0) {
					cell.setCellValue(ret.get(i)[j]);
				} else {
					formatterChain.handle(
						ret.get(i)[0], 
						j, template, cell, ret.get(i)[j]);
				}
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
			.next(new JygkZzyNumberFormatterHandler(NumberType.RESERVE_2, null, jhCols));
		return formatterChain;
	}
}
