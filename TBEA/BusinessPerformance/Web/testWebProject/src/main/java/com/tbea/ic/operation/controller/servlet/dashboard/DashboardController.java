package com.tbea.ic.operation.controller.servlet.dashboard;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.EntryService;


@Controller
@RequestMapping(value = "dashboard")
public class DashboardController {
	
	@Autowired
	private EntryService entryService;

	@Autowired
	private ApproveService approveService;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

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
		if (Account.KNOWN_ACCOUNT_ZHGS.equals(account.getName())) {
			dws = companyManager.getBMDBOrganization().getCompany(CompanyType.ZHGS).getSubCompanies();
		} else if (companyType == 1){
			dws = BMDepartmentDB.getMainlyJydw(companyManager);
		} else if (companyType == 2){
			dws = BMDepartmentDB.getXmgs(companyManager.getBMDBOrganization().getCompany(CompanyType.SBDCYJT));
		} else if (companyType == 3){
			dws = BMDepartmentDB.getXmgs(companyManager.getBMDBOrganization().getCompany(CompanyType.NYSYB));
		} else if (companyType == 4){
			dws = BMDepartmentDB.getXmgs(companyManager.getBMDBOrganization().getCompany(CompanyType.XNYSYB));
		}
		// 1 all
		// 2 sbd
		// 3 ny
		// 4 xny
//		else if (Account.KNOWN_ACCOUNT_ADMIN.equals(account.getName())){
//			Organization org = companyManager.getBMDBOrganization();
//			List<CompanyType> compTypes = CompanySelection.getCompanys(request);
//			dws = new ArrayList<Company>();
//			for(int i = 0; i < compTypes.size(); ++i){
//				Company comp = org.getCompany(compTypes.get(i));
//				if (null != comp){
//					dws.add(comp);
//				} else{
//					dws.add(CompanyManager.getEmptyCompany());
//				}
//			}
//		}

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
		map.put("zhAuth", Account.KNOWN_ACCOUNT_ZHGS.equals(account.getName()));
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
		if (!SessionManager.getAcl(request.getSession(false)).isOpen("admin")) {
			return new ModelAndView("");
		}
		
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
					if (null == account || Account.KNOWN_ACCOUNT_ADMIN.equals(account.getName())) {
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
}
