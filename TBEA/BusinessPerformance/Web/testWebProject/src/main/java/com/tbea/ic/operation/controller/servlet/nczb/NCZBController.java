package com.tbea.ic.operation.controller.servlet.nczb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.POIUtils;
import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.VirtualJYZBOrganization;
import com.tbea.ic.operation.common.excel.CompanysNCSheetType;
import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.controller.servlet.ydzb.CompanyTypeFilter;
import com.tbea.ic.operation.service.nczb.NCZBService;
import com.tbea.ic.operation.service.ydzb.YDZBService;
import com.tbea.ic.operation.service.ydzb.gszb.GszbService;
import com.xml.frame.report.util.DataNode;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.v2.core.DefaultMatcher;
import com.xml.frame.report.util.v2.core.EmptyFormatter;
import com.xml.frame.report.util.v2.core.FormatterServer;
import com.xml.frame.report.util.v2.core.IndicatorMatcher;
import com.xml.frame.report.util.v2.core.Offset;
import com.xml.frame.report.util.v2.data.NumberFormatter;
import com.xml.frame.report.util.v2.data.PercentFormatter;
import com.xml.frame.report.util.v2.excel.ExcelHeaderFormatter;
import com.xml.frame.report.util.v2.excel.ExcelOffsetFormatter;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "NCzb")
public class NCZBController {
	
	@Autowired
	private YDZBService service;
	
	@Autowired
	private GszbService gszbService;
	
	
	@Autowired
	private NCZBService nczbService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@RequestMapping(value = {"AllCompanysNC_overview.do", "v2/AllCompanysNC_overview.do"}, method = RequestMethod.GET)
	public ModelAndView getAllCompanysNC_overview(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "hzbNC_zbhz", map); 
	}
	
	@RequestMapping(value = {"CompanysNC.do", "v2/CompanysNC.do"}, method = RequestMethod.GET)
	public ModelAndView getCompanysNC(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		Organization org = companyManager.getVirtualJYZBOrganization();
		CompanySelection compSel = new CompanySelection(
				false,
				org.getCompany(CompanyType.GFGS).getSubCompanies(), 
				new CompanyTypeFilter(
						gszbService.getCompanies(SessionManager.getAccount(request.getSession(false))), 
						org));
		compSel.select(map, 2);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "hzb_companysNC", map);
	}
	
	private List<String[]> removeJydwzb(List<String[]> data){
		for (int i = 0; i < data.size(); ++i){
			if ("净资产收益率(%)".equals(data.get(i)[0])){
				data.remove(i);
				--i;
			} else if("负债率".equals(data.get(i)[0])){
				data.remove(i);
				--i;
			}
		}
		return data;
	}
	
	
	
	
	
	
	@RequestMapping(value = "AllCompanysNC_overview_export.do")
	public @ResponseBody void getAllCompanysNC_overview_export(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		Date d = DateSelection.getDate(request);
		List<String[]> ncGszbData = nczbService.getGSZB(d, BMDepartmentDB.getJydw(companyManager));
		removeJydwzb(ncGszbData);
		
		
		Offset offset = new Offset(1, 0);
		ExcelHelper template = ExcelTemplate.createCompanysNCTemplate(CompanysNCSheetType.AllCompanysNC);
		FormatterServer fs = new FormatterServer();
		fs.handlerBuilder()
			.add(new EmptyFormatter(DefaultMatcher.LEFT1_MATCHER))
			.add(new PercentFormatter(new IndicatorMatcher(new String[]{"三项费用率(%)", "净资产收益率(%)", "负债率"}, null), 1))
			.add(new PercentFormatter(new DefaultMatcher(null, new Integer[]{3, 6}), 1))
			.add(new NumberFormatter(0))
			.add(new ExcelHeaderFormatter(DefaultMatcher.LEFT1_MATCHER, template, offset))
			.to(FormatterServer.GROP_EXCEL)
			.add(new ExcelOffsetFormatter(template, offset))
			.to(FormatterServer.GROP_EXCEL)
			.server()
			.formatArray(ncGszbData);

		template.write(response, template.getSheetName().replaceAll("\\(", "_").replaceAll("\\)", "") + ".xls");
	}
	
	@RequestMapping(value = "AllCompanysNC_overview_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getAllCompanysNC_overview_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = DateSelection.getDate(request);
		List<String[]> ncGszbData = nczbService.getGSZB(d, BMDepartmentDB.getJydw(companyManager));
		JSONArray ja = JSONArray.fromObject(removeJydwzb(ncGszbData));
		return ja.toString().replace("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "CompanysNC_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getCompanysNC_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = DateSelection.getDate(request);
		CompanyType cm = CompanyType.valueOf(Integer.valueOf(request.getParameter("companyId")));
		List<String[]> ncGszbData = nczbService.getGSZB(d, VirtualJYZBOrganization.getJydw(companyManager, cm));
		if (VirtualJYZBOrganization.isSbdcy(cm) || VirtualJYZBOrganization.isSyb(cm)){
			ncGszbData = removeJydwzb(ncGszbData);
		}
		JSONArray ja = JSONArray.fromObject(ncGszbData);
		return ja.toString().replace("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "CompanysNC_export.do")
	public void getCompanysNC_export(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Date d = DateSelection.getDate(request);
		
		
		Organization org = companyManager.getVirtualJYZBOrganization();
		CompanySelection compSel = new CompanySelection(
				false,
				org.getCompany(CompanyType.GFGS).getSubCompanies(), 
				new CompanyTypeFilter(
						gszbService.getCompanies(SessionManager.getAccount(request.getSession(false))), 
						org));
		
		List<DataNode> nodes = compSel.select(2);
		ExcelHelper template = ExcelTemplate.createCompanysNCTemplate(CompanysNCSheetType.JYDWCompanysNC);
		int i = 0;
		for (DataNode node : nodes){
			for (DataNode sub : node.getSubNodes()){
				CompanyType cm = CompanyType.valueOf(Integer.valueOf(sub.getData().getId()));
				List<String[]> ncGszbData = nczbService.getGSZB(d, VirtualJYZBOrganization.getJydw(companyManager, cm));
				if (VirtualJYZBOrganization.isSbdcy(cm) || VirtualJYZBOrganization.isSyb(cm)){
					ncGszbData = removeJydwzb(ncGszbData);
				}
				
				template.getSheet().getRow(i).getCell(0).setCellValue(sub.getData().getValue());
				
				Offset offset = new Offset(i + 2, 0);
				FormatterServer fs = new FormatterServer();
				fs.handlerBuilder()
					.add(new EmptyFormatter(DefaultMatcher.LEFT1_MATCHER))
					.add(new PercentFormatter(new IndicatorMatcher(new String[]{"三项费用率(%)", "净资产收益率(%)", "负债率"}, null), 1))
					.add(new PercentFormatter(new DefaultMatcher(null, new Integer[]{3, 6}), 1))
					.add(new NumberFormatter(0))
					.add(new ExcelHeaderFormatter(DefaultMatcher.LEFT1_MATCHER, template, offset))
					.to(FormatterServer.GROP_EXCEL)
					.add(new ExcelOffsetFormatter(template, offset))
					.to(FormatterServer.GROP_EXCEL)
					.server()
					.formatArray(ncGszbData);
				i = i + 2 + ncGszbData.size() + 2;
				template.getSheet().createRow(i - 1);
				template.getSheet().createRow(i - 2);
				HSSFRow rowFrom = template.getSheet().getRow(0);
				HSSFRow rowTo = template.getSheet().createRow(i);
				POIUtils.copyRow(template.getWorkbook(), rowFrom, rowTo, true);
				rowFrom = template.getSheet().getRow(1);
				rowTo = template.getSheet().createRow(i + 1);
				POIUtils.copyRow(template.getWorkbook(), rowFrom, rowTo, true);
				template.getSheet().addMergedRegion(new CellRangeAddress(i, i, 0, rowFrom.getLastCellNum() - 1));
			}
		}
		template.getSheet().removeRow(template.getSheet().getRow(i));
		template.getSheet().removeRow(template.getSheet().getRow(1 + i)); 
		template.setRowHeight(0, template.getSheet().getLastRowNum(), 16.5f);
		template.setColumnWidth(0, template.getSheet().getRow(0).getLastCellNum() - 1, 6.5f);
		template.write(response, template.getSheetName().replaceAll("\\(", "_").replaceAll("\\)", "") + ".xls");
	}

}
