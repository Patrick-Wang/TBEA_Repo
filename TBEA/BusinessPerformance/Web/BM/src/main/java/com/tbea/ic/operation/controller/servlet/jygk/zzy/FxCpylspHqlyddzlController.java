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
import com.tbea.ic.operation.service.jygk.zzy.DwxxDto;
import com.tbea.ic.operation.service.jygk.zzy.FxCpylspHqlyddzlService;
import com.tbea.ic.operation.service.jygk.zzy.ReferBglxService;
import com.tbea.ic.operation.service.jygk.zzy.SystemExtendAuthService;
import com.tbea.ic.operation.service.jygk.zzy.ZzyDWXXService;


@Controller
@RequestMapping(value = "fxcpylsphqlyddzl")
public class FxCpylspHqlyddzlController {
	
	@Autowired
	private FxCpylspHqlyddzlService fxCpylspHqlyddzlService;
	@Autowired
	private ReferBglxService referBglxService;
	@Autowired
	ZzyDWXXService zzyDWXXService;
	@Autowired
	private SystemExtendAuthService systemExtendAuthService;
	

	@RequestMapping(value = "open.do", method = RequestMethod.GET)//打开页面
	public ModelAndView open(HttpServletRequest request,
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
		return new ModelAndView("jygkzzy/fx_cpylsp_hqlyddzl_template", map);
	}

	@RequestMapping(value = "read.do", method = RequestMethod.GET)//选择
	public @ResponseBody byte[] read(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String dwxxId = request.getParameter("companyId");		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		List<String[]> ret =  fxCpylspHqlyddzlService.getWriteDataList(dwxxId,year,month);
		String retstr = JSONArray.fromObject(ret).toString().replace("null", "\"\"");
		String result = "{\"values\":" + retstr + "}"; 	
		return result.getBytes("utf-8");
	}
	
	@RequestMapping(value = "save.do", method = RequestMethod.POST)//保存
	public @ResponseBody byte[] save(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String dwxxId = request.getParameter("companyId");		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String data = request.getParameter("data");
		String ret = "" + fxCpylspHqlyddzlService.saveDataList(dwxxId, year, month, JSONArray.fromObject(data));
		String result = "{\"result\":\"" + ret + "\"}";
		return result.getBytes("utf-8");
	}
	
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
		return new ModelAndView("jygkzzy/fx_cpylsp_hqlyddzl_byq_template", map);
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
		return new ModelAndView("jygkzzy/fx_cpylsp_hqlyddzl_xl_template", map);
	}
	
	@RequestMapping(value = "readviewbyq.do", method = RequestMethod.GET)//选择
	public @ResponseBody byte[] readViewByq(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String dwxxId = request.getParameter("companyId");		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		List<String[]> ret =  fxCpylspHqlyddzlService.getViewDataListByq(dwxxId,year,month);
		String retstr = JSONArray.fromObject(ret).toString().replace("null", "\"\"");
		String result = "{\"values\":" + retstr + "}"; 	
		return result.getBytes("utf-8");
	}
	@RequestMapping(value = "readviewxl.do", method = RequestMethod.GET)//选择
	public @ResponseBody byte[] readViewXl(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String dwxxId = request.getParameter("companyId");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		List<String[]> ret =  fxCpylspHqlyddzlService.getViewDataListXl(dwxxId,year,month);
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
		List<String[]> data =  fxCpylspHqlyddzlService.getViewDataListByq(dwxxId,year,month);
		String fileNameAndSheetName = dwxxname;
		fileNameAndSheetName += request.getParameter("year") + "年" + Integer.parseInt(request.getParameter("month"))/3 + "季度后期履约订单质量";		
		JygkZzyExcelTemplate template = JygkZzyExcelTemplate.createJygkTemplate("20002");		
		HSSFWorkbook workbook = template.getWorkbook();
		HSSFSheet sheet = workbook.getSheetAt(0);

		JygkZzyFormatterHandler formatterChain = this.getFormatterChainWithHeader(
				new Integer[]{}, new Integer[]{1, 2, 3, 4,5});
		
		for (int i = 0, ilen = data.size(); i < ilen; ++i) {
			HSSFRow row = sheet.createRow(1 + i);
			for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j);
				formatterChain.handle(
						data.get(i)[0], 
						j, template, cell, data.get(i)[j]);
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
		List<String[]> data =  fxCpylspHqlyddzlService.getViewDataListXl(dwxxId,year,month);
		String fileNameAndSheetName = dwxxname;
		fileNameAndSheetName += request.getParameter("year") + "年" + Integer.parseInt(request.getParameter("month"))/3 + "季度后期履约订单质量";		
		JygkZzyExcelTemplate template = JygkZzyExcelTemplate.createJygkTemplate("20003");		
		HSSFWorkbook workbook = template.getWorkbook();
		HSSFSheet sheet = workbook.getSheetAt(0);

		JygkZzyFormatterHandler formatterChain = this.getFormatterChainWithHeader(
				new Integer[]{}, new Integer[]{1, 2, 3, 4});
		
		for (int i = 0, ilen = data.size(); i < ilen; ++i) {
			HSSFRow row = sheet.createRow(1 + i);
			for (int j = 0, jlen = data.get(i).length; j < jlen; ++j) {
				HSSFCell cell = row.createCell(j);
				formatterChain.handle(
						data.get(i)[0], 
						j, template, cell, data.get(i)[j]);
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
		formatterChain.next(new JygkZzyNumberFormatterHandler(NumberType.RESERVE_2, null, jhCols));
		return formatterChain;
	}
}
