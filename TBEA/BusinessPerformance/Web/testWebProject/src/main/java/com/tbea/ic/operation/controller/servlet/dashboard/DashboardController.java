package com.tbea.ic.operation.controller.servlet.dashboard;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
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

	
	List<Company> mainCompanies = new ArrayList<Company>();
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		Organization org = companyManager.getBMDBOrganization();
		mainCompanies.add(org.getCompany(CompanyType.SBGS));
		mainCompanies.add(org.getCompany(CompanyType.HBGS));
		mainCompanies.add(org.getCompany(CompanyType.XBC));
		mainCompanies.add(org.getCompany(CompanyType.LLGS));
		mainCompanies.add(org.getCompany(CompanyType.XLC));
		mainCompanies.add(org.getCompany(CompanyType.DLGS));
		mainCompanies.add(org.getCompany(CompanyType.XTNYGS));
		mainCompanies.add(org.getCompany(CompanyType.XNYGS));
		mainCompanies.add(org.getCompany(CompanyType.TCNY));
		mainCompanies.add(org.getCompany(CompanyType.NDGS));
		mainCompanies.add(org.getCompany(CompanyType.JCKGS_JYDW));
		mainCompanies.add(org.getCompany(CompanyType.GJGCGS_GFGS));
		mainCompanies.add(org.getCompany(CompanyType.ZHGS));
	}
	
	//取得各个经营单位指标录入的情况
	@RequestMapping(value = "status_update.do", method = RequestMethod.GET)
	public  @ResponseBody byte[] getEntryStatus(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date date = DateSelection.getDate(request);
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("entryType")));
		List<String[]> entryStatus = entryService.getEntryStatus(date, entryType, mainCompanies);
		List<String[]> approveStatus = approveService.getApproveStatus(date, entryType, mainCompanies);
		List<String[]> aggStatus = new ArrayList<String[]>();
		for(int i = 0; i < entryStatus.size(); ++i){
			aggStatus.add(new String[]{
					entryStatus.get(i)[0],
					entryStatus.get(i)[1],
					entryStatus.get(i)[2],
					approveStatus.get(i)[2]});
		}
		String result = JSONArray.fromObject(aggStatus).toString().replace("null", "\"\"");
		return result.getBytes("utf-8");
	}
	
	@RequestMapping(value = "status.do", method = RequestMethod.GET)
	public ModelAndView getGdw_sjzb_summary(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection();
		dateSel.select(map);
		return new ModelAndView("gdw_indexInput_summary", map);
	}
	//End
	
	@RequestMapping(value = "user_status.do")
	public ModelAndView getUser_status(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<HttpSession> sessions = SessionListener.getSessions();
		JSONObject jRet = new JSONObject();
		
		JSONArray arrUsers = new JSONArray();
		HttpSession latestActiveSession = null;
		Account account;

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		int activeCount = 0;
		for (HttpSession session : sessions) {
			account = (Account) session.getAttribute("account");
			if (null == account || "sunfd".equals(account.getName())) {
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
			arrUsers.add(jUser);
		}
		jRet.element("active_user_count", activeCount);
		if (null != latestActiveSession) {
			account = (Account) latestActiveSession.getAttribute("account");
			jRet.element("latest_active_user", account.getName());
			jRet.element("last_accessed_time", sdf.format(new java.util.Date(
					latestActiveSession.getLastAccessedTime())));
		}
		jRet.element("users", arrUsers);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", jRet.toString());
		return new ModelAndView("UserStatusView", map);
		//return jRet.toString().getBytes("utf-8");
	}
}
