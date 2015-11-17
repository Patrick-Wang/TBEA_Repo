package com.tbea.ic.operation.controller.servlet.jygk.zzy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyExcelTemplate;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyFormatterHandler;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyHeaderFormatterHandler;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyNumberFormatterHandler;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyNumberFormatterHandler.NumberType;
import com.tbea.ic.operation.common.jygk.zzy.excel.JygkZzyPercentFormatterHandler;
import com.tbea.ic.operation.service.jygk.zzy.FxJkcbZbwcqkService;
import com.tbea.ic.operation.service.jygk.zzy.ReferBglxService;


@Controller
@RequestMapping(value = "fxjkcbzbwcqk")
public class FxJkcbZbwcqkController {
	
	@Autowired
	private FxJkcbZbwcqkService fxJkcbZbwcqkService;
	@Autowired
	private ReferBglxService referBglxService;
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	@RequestMapping(value = "openview.do", method = RequestMethod.GET)//打开页面
	public ModelAndView openView(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);		
		
		Map<String, Object> map = new HashMap<String, Object>();
		//设置可选年、月到map
		DateSelection dateSel = new DateSelection(year, month);
		dateSel.select(map);
		
		Organization org = companyManager.getPzghOrganization();
		CompanySelection compSel = new CompanySelection(true,org.getTopCompany());
		compSel.select(map);
		
		//设置可选表格类型到map
		return new ModelAndView("jygkzzy/fx_jkcb_zbwcqk_template", map);
	}	
	
	@RequestMapping(value = "readview.do", method = RequestMethod.GET)//选择
	public @ResponseBody byte[] readView(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String dwxxId="";
		String companyId = request.getParameter("companyId");
		if (null != companyId){
			int cid = Integer.parseInt(companyId);
			CompanyType companyType=CompanyType.valueOf(cid);
			Company company=companyManager.getBMDBOrganization().getCompany(companyType);
			dwxxId=company.getId().toString();
		}
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		List<String[]> ret =  fxJkcbZbwcqkService.getViewDataList(dwxxId,year,month);
		String retstr = JSONArray.fromObject(ret).toString().replace("null", "\"\"");
		String result = "{\"values\":" + retstr + "}"; 	
		return result.getBytes("utf-8");
	}
	
	@RequestMapping(value = "fxjkcbzbwcqk_export.do")
	public @ResponseBody byte[] export(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String dwxxId="";
		String dwxxname="";
		String companyId = request.getParameter("companyId");
		if (null != companyId){
			int cid = Integer.parseInt(companyId);
			CompanyType companyType=CompanyType.valueOf(cid);
			Company company=companyManager.getBMDBOrganization().getCompany(companyType);
			dwxxId=company.getId().toString();
			dwxxname=company.getName();
		}
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		List<String[]> data =  fxJkcbZbwcqkService.getViewDataList(dwxxId,year,month);
		String fileNameAndSheetName = dwxxname;
		fileNameAndSheetName += request.getParameter("year") + "年" + request.getParameter("month") + "月降控指标完成情况";		
		JygkZzyExcelTemplate template = JygkZzyExcelTemplate.createJygkTemplate("20004");		
		HSSFWorkbook workbook = template.getWorkbook();
		HSSFSheet sheet = workbook.getSheetAt(0);

		JygkZzyFormatterHandler formatterChain = this.getFormatterChainWithHeader(
				new Integer[]{4}, new Integer[]{1,2, 3});
		
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
		formatterChain.next(new JygkZzyNumberFormatterHandler(NumberType.RESERVE_0, null, jhCols));
		return formatterChain;
	}
}
