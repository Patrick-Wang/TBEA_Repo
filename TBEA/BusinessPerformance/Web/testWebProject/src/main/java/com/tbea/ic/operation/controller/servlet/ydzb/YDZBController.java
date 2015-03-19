package com.tbea.ic.operation.controller.servlet.ydzb;

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
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.ydzb.YDZBService;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.service.ydzb.gszb.GszbService;

class CompanyTypeFilter implements CompanySelection.Filter{
	
	private List<Company> companies;
	Organization org;
	Company dbsbd;
	Company nfsbd;
	Company xjtc;
	public CompanyTypeFilter(List<Company> companies, Organization org){
		this.org = org;
		this.companies = companies;
		dbsbd = org.getCompany(CompanyType.DBSBDCYJT);
		nfsbd = org.getCompany(CompanyType.NFSBDCYJT);
		xjtc = org.getCompany(CompanyType.TCNY_and_XJNY);	
		updateCompanies();
	}
		
	private void addCategory(List<Company> comps, Company category){
		int count = 0;
		for (Company comp : category.getSubCompanys()){
			if(keep(org.getCompany(comp.getType()))){
				++count;
			}
		}
		if (category.getSubCompanys().size() == count){
			comps.add(category);
		}
	}
	
	private void updateCompanies(){
		List<Company> ret = new ArrayList<Company>();
		for (int i = 0; i < this.companies.size(); ++i){
			ret.add(org.getCompany(this.companies.get(i).getType()));
			companies.set(i, ret.get(i));
		}
		addCategory(ret, dbsbd);
		addCategory(ret, nfsbd);
		addCategory(ret, xjtc);		
		this.companies = ret;
	}
	
	private boolean contains(Company comp){
		for (Company tmpComp : companies){
			if (null != tmpComp && tmpComp.contains(comp)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean keep(Company comp) {
		return !dbsbd.contains(comp) && 
			!nfsbd.contains(comp) && 
			!xjtc.contains(comp) && 
			(companies.contains(comp) || contains(comp));
	}
	
	@Override
	public boolean keepGroup(Company comp) {
		for (Company tmpComp : companies){
			if (comp.contains(tmpComp) ||
				comp == tmpComp ||
				tmpComp.contains(comp)){
				return true;
			}
		}
		return false;
	}

}

@Controller
@RequestMapping(value = "ydzb")
public class YDZBController {

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Autowired
	private YDZBService service;

	@Autowired
	private GszbService gszbService;

	@RequestMapping(value = "hzb_zbhz_update.do", method = RequestMethod.GET)
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

	@RequestMapping(value = "hzb_zbhz.do", method = RequestMethod.GET)
	public ModelAndView getGszb_zbhz(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestHzbDate(),
				true, false);
		dateSel.select(map);
		return new ModelAndView("hzb_zbhz", map);
	}

	private void removeJzcsyl(List<String[]> zbData){
		for (int i = 0; i < zbData.size(); ++i){
			if ("净资产收益率(%)".equals(zbData.get(i)[0])){
				for (int j = 1; j < zbData.get(i).length; ++j){
					zbData.get(i)[j] = null;
				}
				break;
			}
		}
	}
	
	//各单位经营指标完成情况update
	@RequestMapping(value = "hzb_companys_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getHzb_companys_update(
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Date d = DateSelection.getDate(request);
		CompanyType compType = CompanySelection.getCompany(request);
		Organization org = companyManager.getBMDBOrganization();
		List<Company> comps;
		boolean removeJzcsyl = false;
		if (CompanyType.SBDCYJT == compType || CompanyType.XNYSYB == compType || CompanyType.NYSYB == compType){
			comps = org.getCompany(compType).getSubCompanys();
			removeJzcsyl = true;
		} else if (
				CompanyType.TCNY_and_XJNY == compType ||
				CompanyType.BYQCY == compType ||
				CompanyType.XLCY == compType ||
				CompanyType.DBSBDCYJT == compType ||
				CompanyType.NFSBDCYJT == compType){
			removeJzcsyl = true;
			Organization orgJyzb = companyManager.getVirtualJYZBOrganization();
			comps = orgJyzb.getCompany(compType).getSubCompanys();
		} else {
			comps = new ArrayList<Company>();
			comps.add(org.getCompany(compType));
		}

		List<String[]> ret = gszbService.getGdwzb(d, comps);
		if (removeJzcsyl){
			removeJzcsyl(ret);
		}
		
		String hzb_zbhz = JSONArray.fromObject(ret).toString()
				.replace("null", "\"--\"");
		return hzb_zbhz.getBytes("utf-8");
	}
	
	//各单位经营指标完成情况
	@RequestMapping(value = "hzb_companys.do", method = RequestMethod.GET)
	public ModelAndView getHzb_companys(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true,
				false);
		dateSel.select(map);
		Organization org = companyManager.getVirtualJYZBOrganization();
		CompanySelection compSel = new CompanySelection(
				false,
				org.getCompany(CompanyType.GFGS).getSubCompanys(), 
				new CompanyTypeFilter(
						gszbService.getCompanies((Account)request.getSession(false).getAttribute("account")), 
						org));

		compSel.select(map, 3);
		return new ModelAndView("hzb_companys", map);
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
	@RequestMapping(value = "gcy_zbhz.do", method = RequestMethod.GET)
	public ModelAndView getGcy_zbhz(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestGcyDate(),
				true, false);
		dateSel.select(map);
		return new ModelAndView("gcy_zbhz", map);
	}
	
	//各单位经营指标完成情况update
	@RequestMapping(value = "gdw_zbhz_update.do", method = RequestMethod.GET)
	public @ResponseBody String getGdw_zbhz_update(HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
		// String gdw_zbhz =
		// JSONArray.fromObject(service.getGdw_zbhzData(d)).toString().replace("null",
		// "0.00");
		String gszb = request.getParameter("zbId");
		String gdw_zbhz = JSONArray
				.fromObject(
						gszbService.getCompanyTop5zb(
								GSZB.valueOf(Integer.valueOf(gszb)), d))
				.toString().replace("null", "\"--\"");
		return gdw_zbhz;
	}
	
	//各单位经营指标完成情况
	@RequestMapping(value = "gdw_zbhz.do", method = RequestMethod.GET)
	public ModelAndView getGdw_zbhz(HttpServletRequest request,
			HttpServletResponse response) {

		//int zb = Integer.parseInt(request.getParameter("zb"));
		//String zbName  = service.getZBNameById(zb);
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("zbName", zbName);
		//map.put("zbId", zb);
		DateSelection dateSel = new DateSelection(service.getLatestGcyDate(),
				true, false);
		dateSel.select(map);
		return new ModelAndView("gdw_zbhz", map);
	}

	@RequestMapping(value = "xjlrb_update.do", method = RequestMethod.GET)
	public @ResponseBody String getXjlrb_update(HttpServletRequest request,
			HttpServletResponse response) {
		// int month = Integer.parseInt(request.getParameter("month"));
		// int year = Integer.parseInt(request.getParameter("year"));
		// int day = Integer.parseInt(request.getParameter("day"));
		Date d = DateSelection.getDate(request);
		String xjlrb = JSONArray.fromObject(service.getXjlrbData(d)).toString()
				.replace("null", "0.00");
		return xjlrb;
	}

	@RequestMapping(value = "xjlrb.do", method = RequestMethod.GET)
	public ModelAndView getXjlrb(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();

		DateSelection dateSel = new DateSelection(service.getLatestXjlDate());
		dateSel.select(map);

		return new ModelAndView("xjlrb", map);
	}


	private String getZbhz_overviewData(Date d, int companyId, String zbid) {

		Organization org = companyManager.getOperationOrganization();
		Company comp = org.getCompany(CompanyType.valueOf(companyId));

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
	@RequestMapping(value = "zbhz_overview.do", method = RequestMethod.GET)
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

		return new ModelAndView("zbhz_overview", map);
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
					.fromObject(gszbService.getJDZBMY(d)).toString()
					.replace("null", "\"--\"");
		}

		if (1 == iMonth % 3) {
			hzb_zbhz_prediction = JSONArray
					.fromObject(
							gszbService.getFirstSeasonPredictionZBsOverview(d))
					.toString().replace("null", "\"--\"");
		}

		if (2 == iMonth % 3) {
			hzb_zbhz_prediction = JSONArray
					.fromObject(
							gszbService.getSecondSeasonPredictionZBsOverview(d))
					.toString().replace("null", "\"--\"");
		}

		return hzb_zbhz_prediction.getBytes("utf-8");
	}
	
	//整体指标预测Update
	@RequestMapping(value = "hzb_zbhz_prediction.do", method = RequestMethod.GET)
	public ModelAndView gethzb_zbhz_prediction(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection();
		dateSel.select(map);
		return new ModelAndView("hzb_zbhz_prediction", map);
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
					.fromObject(gszbService.getGcyJDZBMY(d)).toString()
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
	@RequestMapping(value = "financial_zbhz_prediction.do", method = RequestMethod.GET)
	public ModelAndView getFinancial_zbhz_prediction(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection();
		dateSel.select(map);
		return new ModelAndView("financial_zbhz_prediction", map);
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
						.fromObject(gszbService.getGdwJDZBMY(gszb, d)).toString()
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

		@RequestMapping(value = "gdw_zbhz_prediction.do", method = RequestMethod.GET)
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
			return new ModelAndView("gdw_zbhz_prediction", map);
		}
			
		
}
