package com.tbea.ic.operation.controller.servlet.dashboard;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.entity.ExchangeRate;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.dashboard.DashboardService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.tbea.ic.operation.service.ydzb.gszb.GszbService;
import com.tbea.ic.operation.service.yszkgb.YszkgbService;
import com.tbea.ic.operation.service.yszkgb.YszkgbServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "dashboard")
public class DashboardController {
	
	@Autowired
	private EntryService entryService;

	@Autowired
	private ApproveService approveService;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Autowired
	GszbService gszbService;
	
	@Autowired 
	DashboardService dashboardService;
	

	@Resource(name=YszkgbServiceImpl.NAME)
	YszkgbService yszkgbService;
	
	@Autowired
	ExtendAuthorityService extAuthServ;
	
	CompanyType[] cps = new CompanyType[]{
			CompanyType.SBGS,
			CompanyType.HBGS,
			CompanyType.XBC,
			CompanyType.LLGS,
			CompanyType.XLC,
			CompanyType.DLGS,
			CompanyType.XNYGS,
			CompanyType.XTNYGS,
			CompanyType.TCNY,
			CompanyType.NDGS,
			CompanyType.ZHGS,
			CompanyType.JCKGS_JYDW,
			CompanyType.GJGCGS_GFGS
	};
	
	@RequestMapping(value = "status_update.do")
	public @ResponseBody byte[] getEntryStatus(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date date = DateSelection.getDate(request);
		Account account = SessionManager.getAccount(request.getSession(false));
		List<Company> dws = null;
		Integer companyType = 0;
		if (null != request.getParameter("companyType")){
			companyType = Integer.valueOf((String)request.getParameter("companyType"));
		}
		if (this.extAuthServ.hasAuthority(account, 81)) {
			dws = companyManager.getBMDBOrganization().getCompanyByType(CompanyType.ZHGS).getSubCompanies();
		} else if (companyType == 1){
			dws = BMDepartmentDB.getMainlyJydw(companyManager);
		} else if (companyType == 2){
			dws = BMDepartmentDB.getXmgs(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.SBDCYJT));
		} else if (companyType == 3){
			dws = BMDepartmentDB.getXmgs(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.NYSYB));
		} else if (companyType == 4){
			dws = BMDepartmentDB.getXmgs(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.XNYSYB));
		}


		String result = "[]";
		if (null != dws && !dws.isEmpty()) {
			ZBType entryType = ZBType.valueOf(Integer.valueOf(request
					.getParameter("entryType")));
			List<String[]> entryStatus = entryService.getEntryStatus(date,
					entryType, dws);
			List<String[]> approveStatus = approveService.getApproveStatus(
					date, entryType, dws);
			List<String[]> aggStatus = new ArrayList<String[]>();
			for (int i = 0; i < entryStatus.size(); ++i) {
				aggStatus.add(new String[] { entryStatus.get(i)[0],
						entryStatus.get(i)[1], entryStatus.get(i)[2],
						approveStatus.get(i)[2] });
			}
			result = JSONArray.fromObject(aggStatus).toString()
					.replace("null", "\"\"");
		}
		return result.getBytes("utf-8");
	}

	@RequestMapping(value = {"status.do","v2/status.do"}, method = RequestMethod.GET)
	public ModelAndView getGdw_sjzb_summary(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection();
		dateSel.select(map);
		Account account = SessionManager.getAccount(request.getSession(false));
		map.put("zhAuth", this.extAuthServ.hasAuthority(account, 81));
		SessionManager.getAcl(request.getSession()).select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") +"gdw_indexInput_summary", map);
	}
	
	@RequestMapping(value = "user_status.do")
	public ModelAndView getUser_status(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		
		JSONObject jRet = new JSONObject();
		
		JSONArray arrUsers = new JSONArray();
		HttpSession latestActiveSession = null;
		Account account = SessionManager.getAccount(request.getSession(false));
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		int activeCount = 0;

		Map<String, HttpSession> sessions = SessionManager.getOnlineSessions();
	    Set<String> keys = sessions.keySet();
	    synchronized (keys) {
	        Iterator<String> i = keys.iterator(); // Must be in the synchronized block
	        while (i.hasNext()){
	        	try{
		        	HttpSession session = sessions.get(i.next());
		        	account = SessionManager.getAccount(session);
					if (null == account || extAuthServ.hasAuthority(account, 80)) {
						continue;
					}
					++activeCount;
					if (null == latestActiveSession) {
						latestActiveSession = session;
					} else if (latestActiveSession.getLastAccessedTime() < session
							.getLastAccessedTime()) {
						latestActiveSession = session;
					}
					JSONObject jUser = new JSONObject();
					jUser.element("name", account.getName());
					jUser.element("sid", session.getId());
					jUser.element("login_time",
							sdf.format(new Date(session.getCreationTime())));
					jUser.element("last_accessed_time", sdf.format(new java.util.Date(
							session.getLastAccessedTime())));
					jUser.element("ip", session.getAttribute("remoteIP"));
					arrUsers.add(jUser);
	        	}catch(Exception e){
	        		e.printStackTrace();
	        	}
	        }
	    }

		jRet.element("active_user_count", activeCount);
		if (null != latestActiveSession) {
			account = SessionManager.getAccount(latestActiveSession);
			jRet.element("latest_active_user", account.getName());
			jRet.element("last_accessed_time", sdf.format(new java.util.Date(
					latestActiveSession.getLastAccessedTime())));
		}
		jRet.element("users", arrUsers);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", jRet.toString());
		return new ModelAndView("UserStatusView", map);
	}
	
	private String getPercent(Double val){
		if (val == null){
			return "--";
		}
		return String.format("%.0f",  (val * 100)) + "%";		
	}
	
	private String getNumber(Double val){
		if (val == null){
			return "--";
		}
		return String.format("%.2f",  val);		
	}
	
	private String getNumber(String val){
		if ("null".equals(val) || val == null){
			return "--";
		}
		return String.format("%.2f",  Double.valueOf(val));		
	}
	
	private String getNumber0(Double val){
		if (val == null){
			return "--";
		}
		return String.format("%.0f",  val);		
	}
	
	@RequestMapping(value = "dashboard.do")
	public ModelAndView getDashboard(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date current = new java.sql.Date(System.currentTimeMillis());
		JSONObject jRet = new JSONObject();
		List<Double[]> zbs = dashboardService.getDashboardGsztzb(current);
		
		JSONArray zt = new JSONArray();
		
		for(int i = 0; i < zbs.size(); ++i){
			JSONObject item = new JSONObject();
			item.put("ndjhwcl", getPercent(zbs.get(i)[4]));
			item.put("ndlj", getNumber0(zbs.get(i)[3]));
			item.put("ndljtbzf", getPercent(zbs.get(i)[6]));
			zt.add(item);
		}

		jRet.put("zt", zt);
		
		
		
		ExchangeRate er = entryService.getExchangeRate(current);
		
		List<Double> ljzbs = dashboardService.getScqyLjzb(current);
		List<Double> ydzbs = dashboardService.getScqyZtydzb(Calendar.getInstance().get(Calendar.YEAR));
		
		JSONObject scqy = new JSONObject();
		scqy.put("gszt", getNumber0(ljzbs.get(0)));
		scqy.put("zzy", getNumber0(ljzbs.get(1)));
		scqy.put("jcfw", getNumber0(ljzbs.get(2)));
		scqy.put("qt", getNumber0(ljzbs.get(3)));
		
		JSONArray scqyYds = new JSONArray();
		for (Double zb :ydzbs){
			scqyYds.add(getNumber(zb));
		}
		scqy.put("ydzbs", scqyYds);
		
		JSONObject scqypm = new JSONObject();
		
		List<String[]> gnsc = dashboardService.getSbdgnscqye(current);
		List<String[]> gjsc = dashboardService.getSbdgjscqye(current);
		Double sbdzt = dashboardService.getSbdztqye(current);
		scqypm.put("sbdztqy", getNumber0(sbdzt));
		scqypm.put("gnsc", JSONArray.fromObject(gnsc));
		scqypm.put("gjsc", JSONArray.fromObject(gjsc));
		
		Double sumgn = 0.0;
		Double sumgj = 0.0;
		for (int i = 0; i < gnsc.size(); ++i){
			sumgn += Util.toDouble(gnsc.get(i)[1]);
			sumgj += Util.toDouble(gjsc.get(i)[1]) * er.getRate();
		}
		
		Double gnzb = sumgj + sumgn;
		gnzb = (gnzb) < 0.000001 ? 0 : (sumgn / gnzb);
		scqypm.put("gnzb", String.format("%.0f", gnzb * 100) + "%");
		
		jRet.put("scqypm", scqypm);
		jRet.put("scqy", scqy);
		

		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", jRet.toString().replaceAll("null", ""));
		return new ModelAndView("ui2/dashboard/dashboard", map);
	}
	
	
	@RequestMapping(value = "dashboard_update.do")
	public @ResponseBody byte[] dashboardUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date current = new java.sql.Date(System.currentTimeMillis());
		JSONObject jRet = new JSONObject();
		JSONArray jydw = new JSONArray();
		List<String[]> zbs = gszbService.getDashboardGsztzb(current);
		for(int i = 0; i < zbs.size(); ++i){
			jydw.add(new JSONArray());
		}
		for (CompanyType ct : cps){
			zbs = gszbService.getDashboardGdwzb(current, ct);
			for (int i = 0; i < zbs.size(); ++i){
				JSONObject item = new JSONObject();
				item.put("ndjh",  getNumber(zbs.get(i)[1]));
				item.put("ndlj",  getNumber(zbs.get(i)[12]));
				item.put("qntq", getNumber(zbs.get(i)[14]));
				jydw.getJSONArray(i).add(item);
			}
		}
		
		jRet.put("jydw", jydw);
		return jRet.toString().replaceAll("null", "").getBytes("utf-8");
	}
	
	@RequestMapping(value = "yszk.do")
	public ModelAndView getDashboardYszk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date current = new java.sql.Date(System.currentTimeMillis());
		JSONObject jRet = new JSONObject();
		
		JSONArray zl = JSONArray.fromObject(this.yszkgbService.getDashboardYszkzlbh(current));
		JSONArray kxxz = JSONArray.fromObject(this.yszkgbService.getDashboardYszkkxxz(current));
		JSONArray yqys = JSONArray.fromObject(this.yszkgbService.getDashboardYqyszcsys(current));
		

		JSONArray yjtz = JSONArray.fromObject(this.yszkgbService.getDashboardYszkyjtztjqs(current));
		
		jRet.put("zl", zl);
		jRet.put("kxxz", kxxz);
		jRet.put("yqys", yqys);
		jRet.put("yjtz", yjtz);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", jRet.toString().replaceAll("null", "0"));
		return new ModelAndView("ui2/dashboard/yszk/yszk", map);
	}
	
	@RequestMapping(value = "lrze.do")
	public ModelAndView getDashboardLrze(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date current = new java.sql.Date(System.currentTimeMillis());
		JSONObject jRet = new JSONObject();
		
		JSONArray qsfx = JSONArray.fromObject(this.yszkgbService.getDashboardYszkzlbh(current));
//		JSONArray kxxz = JSONArray.fromObject(this.yszkgbService.getDashboardYszkkxxz(current));
//		JSONArray yqys = JSONArray.fromObject(this.yszkgbService.getDashboardYqyszcsys(current));
//		
//
//		JSONArray yjtz = JSONArray.fromObject(this.yszkgbService.getDashboardYszkyjtztjqs(current));
//		
//		jRet.put("zl", zl);
//		jRet.put("kxxz", kxxz);
//		jRet.put("yqys", yqys);
//		jRet.put("yjtz", yjtz);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", jRet.toString().replaceAll("null", "0"));
		return new ModelAndView("ui2/dashboard/lrze/lrze", map);
	}
	
}
