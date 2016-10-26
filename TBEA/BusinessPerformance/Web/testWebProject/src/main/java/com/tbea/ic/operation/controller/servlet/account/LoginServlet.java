package com.tbea.ic.operation.controller.servlet.account;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager.OnSessionChangedListener;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.DailyReportService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.tbea.ic.operation.service.login.LoginService;
import com.tbea.ic.operation.service.report.HBWebService;

@Controller
@RequestMapping(value = "Login")
public class LoginServlet implements OnSessionChangedListener {

	class Logic{
		boolean ret = false;
		
		public Logic(boolean ret) {
			super();
			this.ret = ret;
		}
		public boolean or(boolean val){
			ret = ret || val;
			return val;
		}
		public boolean and(boolean val){
			ret = ret && val;
			return val;
		}
		public boolean value(){
			return ret;
		}
	}
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	// private String view = "index";
	// private static Logger logger = Logger.getLogger(LoginServlet.class);
	@Autowired
	private EntryService entryServ;

	@Autowired
	private ApproveService approveServ;

	@Autowired
	private LoginService loginServ;

	@Autowired
	private DailyReportService drServ;

	@Autowired
	ExtendAuthorityService extAuthServ;

	@Autowired
	public void init(){
		SessionManager.onChange(this);
	}
	
	@RequestMapping(value = "ssoLogin.do")
	public ModelAndView ssoLogin(HttpServletRequest request,
			HttpServletResponse response) {

		String userName = request.getHeader("SSO_USER");
		Account account = loginServ.SSOLogin(userName);

		if (null != account) {
			setAuthority(request.getSession(), account);
			request.getSession().setAttribute("sso", true);
			return new ModelAndView("redirect:/Login/index.do");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", true);
		return new ModelAndView("login", map);
	}

	@RequestMapping(value = "ssoLogout.do")
	public void ssoLogout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (null != session) {
			session.invalidate();
		}
//		response.sendRedirect("http://192.168.10.68:10008/cas/logout");
	}

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		if (SessionManager.isOnline(session)) {
			return new ModelAndView("redirect:/Login/index.do");
		}
//		WordprocessingMLPackage pkg = Docx4jUtils.createWordprocessingMLPackage();
//		BASE64Decoder dec = new BASE64Decoder();
//		
//		P  p = Docx4jUtils.getPImage(pkg, pkg.getMainDocumentPart(), dec.decodeBuffer("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2LX+bHd39/D7tIf/GgIECBAgQIAAAQIECBAgQIDAygs0GayurDqdTh//fLBa/vvFYnHXVcHqrb7vz6+vr993rT+bz+fvX+ul7O3t9Sv/igAQIECAAAECBAgQIECAAAECf0Sg67qmu8v3cvKm4a4OVuPx+KZ/w2oZrDzA7+UT9bMJECBAgAABAgQIECBAgMAPnoBekG22UsFqMpncW0p5eHNz87krv8NqfX39tVOnTj116dKlH7/Wn+3s7Hx0LWYPMHuArgkQIECAAAECBAgQIECAQIsCekG26koFq8v/lMA3aq0v7O3tfdB13SN9399/dHT09OnTp2+vtX7HPzs4OLgkWGUPzTUBAgQIECBAgAABAgQIEFglAcEqW3ulglUppXZd92Qp5ZW+72+tte6cnJw8cfHixff+mD+7prIHmD1A1wQIECBAgAABAgQIECBAoEUBvSBbtelgldF8d9ce4Hfn5D9FgAABAgQIECBAgAABAgRWSUAvyNYWrDK/4gGGgM4JECBAgAABAgQIECBAgECDAnpBNqpglfkJVqGfcwIECBAgQIAAAQIECBAg0KKAYJWtKlhlfoJV6OecAAECBAgQIECAAAECBAi0KCBYZasKVpmfYBX6OSdAgAABAgQIECBAgAABAi0KCFbZqoJV5idYhX7OCRAgQIAAAQIECBAgQIBAiwKCVbaqYJX5CVahn3MCBAgQIECAAAECBAgQINCigGCVrSpYZX6CVejnnAABAgQIECBAgAABAgQItCggWGWrClaZn2AV+jknQIAAAQIECBAgQIAAAQItCghW2aqCVeYnWIV+zgkQIECAAAECBAgQIECAQIsCglW2qmCV+QlWoZ9zAgQIECBAgAABAgQIECDQooBgla0qWGV+glXo55wAAQIECBAgQIAAAQIECLQoIFhlqwpWmZ9gFfo5J0CAAAECBAgQIECAAAECLQoIVtmqglXmJ1iFfs4JECBAgAABAgQIECBAgECLAoJVtqpglfkJVqGfcwIECBAgQIAAAQIECBAg0KKAYJWtKlhlfoJV6OecAAECBAgQIECAAAECBAi0KCBYZasKVpmfYBX6OSdAgAABAgQIECBAgAABAi0KCFbZqoJV5idYhX7OCRAgQIAAAQIECBAgQIBAiwKCVbaqYJX5CVahn3MCBAgQIECAAAECBAgQINCigGCVrSpYZX6CVejnnAABAgQIECBAgAABAgQItCggWGWrClaZn2AV+jknQIAAAQIECBAgQIAAAQItCghW2aqCVeYnWIV+zgkQIECAAAECBAgQIECAQIsCglW2qmCV+QlWoZ9zAgQIECBAgAABAgQIECDQooBgla0qWGV+glXo55wAAQIECBAgQIAAAQIECLQoIFhlqwpWmZ9gFfo5J0CAAAECBAgQIECAAAECLQoIVtmqglXmJ1iFfs4JECBAgAABAgQIECBAgECLAoJVtqpglfkJVqGfcwIECBAgQIAAAQIECBAg0KKAYJWtKlhlfoJV6OecAAECBAgQIECAAAECBAi0KCBYZasKVpmfYBX6OSdAgAABAgQIECBAgAABAi0KCFbZqoJV5idYhX7OCRAgQIAAAQIECBAgQIBAiwKCVbaqYJX5CVahn3MCBAgQIECAAAECBAgQINCigGCVrSpYZX6CVejnnAABAgQIECBAgAABAgQItCggWGWrClaZn2AV+jknQIAAAQIECBAgQIAAAQItCghW2aqCVeYnWIV+zgkQIECAAAECBAgQIECAQIsCglW2qmCV+QlWoZ9zAgQIECBAgAABAgQIECDQooBgla0qWGV+glXo55wAAQIECBAgQIAAAQIECLQoIFhlq65csJpOp9O+7/993/d/qdb6W2tra//8nXfe+cNSSh2Px4/WWl8tpdxWa317sVi8OJ/PZ9cj9gCzB+iaAAECBAgQIECAAAECBAi0KKAXZKuuVLCaTCY/VEr51dFo9PLu7u7/nEwmf6uU8hdms9mr4/H4jlrr67XWl86cOfPh4eHhY6WUuzc2Ns5vb28fX4vZA8weoGsCBAgQIECAAAECBAgQINCigF6QrbpSwWo8Hv9ErfW1Wus/3Nvb+4PpdHr/YrF4stb61b7vx7XWh64Eqq2trTuXYev4+Pjc/v7+x4JV9tBcEyBAgAABAgQIECBAgACBVRIQrLK1VypY/THfsHqg1vrgbDZ7fknadd3tfd+/ORqNnt3d3T0UrLKH5poAAQIECBAgQIAAAQIECKySgGCVrb1SwepyiDpbSvmtvu/vLKX81/X19Z9/5513fn86nT6+WCzuuipYvdX3/fn5fP7+9YJVNoFrAgQIECBAgAABAgQIECBAoEWBrutWrrvcrB1XCm48Hi9/0fozo9Ho3O7u7if33HPP1snJyT89Ojp65vTp0/f6htXNelZ+DgECBAgQIECAAAECBAgQWG0B37DK9l+pYNV13d/r+/4vf6dvUdVav1hKeXhzc/O55S9ZX/4Oq/X19ddOnTr11M7OzkfXYvYAswfomgABAgQIECBAgAABAgQItCigF2SrrlSwWn7Dam1t7RduueWWX1lGqMvfsPrlUso/7vv+T9Va36i1vrC3t/dB13WP9H1//9HR0dMHBweXBKvsobkmQIAAAQIECBAgQIAAAQKrJCBYZWuvVLAqpdTJZPLXSymvllL+Yq317Vrr0xcuXPjd5Z91XfdkKeWVvu9vrbXunJycPHHx4sX3rkfsAWYP0DUBAgQIECBAgAABAgQIEGhRQC/IVl21YJVpfYdrD/Cmk/qBBAgQIECAAAECBAgQIEDgB15AL8gmFKwyv+IBhoDOCRAgQIAAAQIECBAgQIBAgwJ6QTaqYJX5CVahn3MCBAgQIECAAAECBAgQINCigGCVrSpYZX6CVejnnAABAgQIECBAgAABAgQItCggWGWrClaZn2AV+jknQIAAAQIECBAgQIAAAQItCghW2aqCVeYnWIV+zgkQIECAAAECBAgQIECAQIsCglW2qmCV+QlWoZ9zAgQIECBAgAABAgQIECDQooBgla0qWGV+glXo55wAAQIECBAgQIAAAQIECLQoIFhlqwpWmZ9gFfo5J0CAAAECBAgQIECAAAECLQoIVtmqglXmJ1iFfs4JECBAgAABAgQIECBAgECLAoJVtqpglfkJVqGfcwIECBAgQIAAAQIECBAg0KKAYJWtKlhlfoJV6OecAAECBAgQIECAAAECBAi0KCBYZasKVpmfYBX6OSdAgAABAgQIECBAgAABAi0KCFbZqoJV5idYhX7OCRAgQIAAAQIECBAgQIBAiwKCVbaqYJX5CVahn3MCBAgQIECAAAECBAgQINCigGCVrSpYZX6CVejnnAABAgQIECBAgAABAgQItCggWGWrClaZn2AV+jknQIAAAQIECBAgQIAAAQItCghW2aqCVeYnWIV+zgkQIECAAAECBAgQIECAQIsCglW2qmCV+QlWoZ9zAgQIECBAgAABAgQIECDQooBgla0qWGV+glXo55wAAQIECBAgQIAAAQIECLQoIFhlqwpWmZ9gFfo5J0CAAAECBAgQIECAAAECLQoIVtmqglXmJ1iFfs4JECBAgAABAgQI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weoG3sJ4PL6j1vp6rfWlM2fOfHh4ePhYKeXujY2N89vb28c38COdECBAgAABAgQIECBAgAABAgQIXBYQrG7gKUwmk6/UWh+6Eqi2trbuHI1GLx8fH5/b39//+AZ+pBMCBAgQIECAAAECBAgQIECAAAHB6sbfwHg8fqDW+uBsNnt++VO6rru97/s3R6PRs7u7u4c3/pNdEiBAgAABAgQIECBAgAABAgQI+IbVDbyB6XT6+GKxuOuqYPVW3/fn5/P5+zfwI50QIECAAAECBAgQIECAAAECBAhcFhCsbuAp+IbVDaA5IUCAAAECBAgQIECAAAECBAh8lwKC1XcJ9fn/2GQyubeU8vDm5uZzy1+yvvwdVuvr66+dOnXqqZ2dnY9u4Ec6IUCAAAECBAgQIECAAAECBAgQuCwgWN3AU7j8Twl8o9b6wt7e3gdd1z3S9/39R0dHTx8cHFy6gR/phAABAgQIECBAgAABAgQIECBAQLCK3kDtuu7JUsorfd/fWmvdOTk5eeLixYvvRT/VMQECBAgQIECAAAECBAgQIECAQPENK4+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgQEK2+AAAECBAgQIECAAAECBAgQIEBgUAKC1aDm8GEIECBAgAABAgQIECBAgAABAgT+H1mNJQ+n8vYpAAAAAElFTkSuQmCC"), "123", "422", 12567, 12456);
//		Docx4jUtils.addObject(pkg, p, false);
//		pkg.save(new File("D://wd.doc"));
		return new ModelAndView("login");
	}

	@RequestMapping(value = "exitSystem.do", method = RequestMethod.GET)
	public @ResponseBody byte[] logout(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		HttpSession session = request.getSession(false);
		Boolean isSSOLogin = false;
		if (null != session) {
			isSSOLogin = null != request.getSession().getAttribute("sso");
			session.invalidate();
		}

		AjaxRedirect ajaxRedirect;
		if (isSSOLogin) {
			ajaxRedirect = new AjaxRedirect(
					"http://soa1.tbea.com/oam/server/logout?end_url=http://soa.tbea.com");
		} else {
			ajaxRedirect = new AjaxRedirect();
		}
		return ajaxRedirect.toUtf8Bytes();
	}

	private void setAuthority(HttpSession session, Account account) {
		ACL acl = new ACL();
		SessionManager.setAccount(session, account);
		SessionManager.setAcl(session, acl);
		boolean yszkrb = false;
		Logic lookup = new Logic(false);
		Logic entry = new Logic(false);
		Logic jyfxEntry = new Logic(false);
		Logic comGbLookup = new Logic(false);
		Logic comGbEntry = new Logic(false);
		acl
		.add("entryPlan", entryServ.hasEntryPlanPermission(account))
		.add("entryPredict", entryServ.hasEntryPredictPermission(account))
		.add("approvePlan", approveServ.hasApprovePlanPermission(account))
		.add("approvePredict", approveServ.hasApprovePredictPermission(account))
		.add("CorpAuth", loginServ.hasCorpAuth(account))
		.add("SbdAuth", loginServ.hasSbdAuth(account))
		.add("MarketAuth", entryServ.hasMarketPermission(account))
		.add("isJydw", jyfxEntry.or(drServ.hasYszkAuthority(account)))
		.add("JYAnalysisEntry", drServ.hasJYAnalysisEntryAuthority(account))
		.add("JYAnalysisSummary", drServ.hasJYAnalysisLookupAuthority(account))
		.add("YSZKDialyLookup", yszkrb = (drServ.hasYSZKDialyLookupAuthority(account) ||
				extAuthServ.hasAuthority(account, 28)))
		.add("XJLDialyLookup", drServ.hasXJLDialyLookupAuthority(account))
		.add("JYAnalysisLookup", yszkrb || drServ.hasJYAnalysisLookupAuthority(account)
								|| drServ.hasYSZKDialyLookupAuthority(account))
		.add("JYEntryLookup", drServ.hasJYEntryLookupAuthority(account))
		.add("PriceLibAuth", extAuthServ.hasAuthority(account, AuthType.PriceLib))
		.add("ChgbLookup", comGbLookup.or(lookup.or(extAuthServ.hasAuthority(account, AuthType.ChgbLookup))))
		.add("YszkgbLookup", comGbLookup.or(lookup.or(extAuthServ.hasAuthority(account, AuthType.YszkgbLookup))))
		.add("SbdgbLookup", lookup.or(extAuthServ.hasAuthority(account, AuthType.SbdgbLookup)))
		.add("XnygbLookup", lookup.or(extAuthServ.hasAuthority(account, AuthType.XnygbLookup)))
		.add("NygbLookup", lookup.or(extAuthServ.hasAuthority(account, AuthType.NygbLookup)))
		.add("SbdgbLookup", lookup.or(extAuthServ.hasAuthority(account, AuthType.SbdgbLookup)))
		.add("ChgbEntry", comGbEntry.or(entry.or(extAuthServ.hasAuthority(account, AuthType.ChgbEntry))))
		.add("YszkgbEntry", comGbEntry.or(entry.or(extAuthServ.hasAuthority(account, AuthType.YszkgbEntry))))
		.add("SbdgbEntry", entry.or(extAuthServ.hasAuthority(account, AuthType.SbdgbEntry)))
		.add("XnygbEntry", entry.or(extAuthServ.hasAuthority(account, AuthType.XnygbEntry)))
		.add("NygbEntry", entry.or(extAuthServ.hasAuthority(account, AuthType.NygbEntry)))
		.add("NYzbscqkEntry", entry.or(extAuthServ.hasAuthority(account, AuthType.NYzbscqkEntry)))
		.add("QualityEntry", extAuthServ.hasAuthority(account, AuthType.QualityEntry))
		.add("QualityApprove", extAuthServ.hasAuthority(account, AuthType.QualityApprove) ||
				extAuthServ.hasAuthority(account, 53) ||
				extAuthServ.hasAuthority(account, 54) ||
				extAuthServ.hasAuthority(account, 55))
		.add("QualityLookup", extAuthServ.hasAuthority(account, AuthType.QualityLookup))
		.add("FinanceLookup", extAuthServ.hasAuthority(account, AuthType.FinanceLookup))
		.add("FinanceEntry", entry.or(extAuthServ.hasAuthority(account, AuthType.FinanceEntry)))
//		.add("GbLookup", lookup.value())
		.add("GbEntry", entry.value())
		.add("ComGbLookup", comGbLookup.value())
		.add("ComGbEntry", comGbEntry.value())
		.add("GbEntry", entry.value())
		.add("isByq", account.getId() == 9 || account.getId() == 25 || account.getId() == 33)
		.add("isXl", account.getId() == 43 || account.getId() == 50 || account.getId() == 61)
		.add("isSbdcy",	account.getId() == 8 || account.getId() == 6
						|| account.getId() == 147 || account.getId() == 119
						|| account.getId() == 146)
		.add("zhAuth", Account.KNOWN_ACCOUNT_ZHGS.equals(account.getName()))
		.add("notSbqgb", !Account.KNOWN_ACCOUNT_QGB.equals(account.getName()))
		.add("admin", Account.KNOWN_ACCOUNT_ADMIN.equals(account.getName()))
		.add("debug", false)
		.add("xnyJyfxEntryAuth", jyfxEntry.or(extAuthServ.hasAuthority(account, 25)||
				extAuthServ.hasAuthority(account, 29) ||
				extAuthServ.hasAuthority(account, 31)))
		.add("xnyJyfxLookupAuth", extAuthServ.hasAuthority(account, 26)||
				extAuthServ.hasAuthority(account, 30) ||
				extAuthServ.hasAuthority(account, 32))
		.add("zhJyfxLookupAuth", extAuthServ.hasAuthority(account, 34))
		.add("xtnyrbEntryAuth", jyfxEntry.or(extAuthServ.hasAuthority(account, 35)))
		.add("xtnyrbLookupAuth", extAuthServ.hasAuthority(account, 36))
		.add("QualityAuth", account.getRole() == 3 || account.getRole() == 4)
		.add("I_EQualityImport", account.getName().equals("鲁缆质量部") || account.getName().equals("新变质量部"))
		.add("jyfxEntry", jyfxEntry.value());
		
		
		if (Account.KNOWN_ACCOUNT_AFL.equals(account.getName())){
			acl.openAll();
			acl.close("MarketAuth");
		}
	}

	@RequestMapping(value = "validate.do")
	public ModelAndView validate(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "j_username") String j_username,
			@RequestParam(value = "j_password") String j_password) {

		Account account = loginServ.Login(j_username, j_password);
		if (null != account) {

			HttpSession currentSession = request.getSession(false);
			if (null != currentSession) {
				currentSession.invalidate();
			}

			HttpSession newSession = request.getSession(true);

			setAuthority(newSession, account);

			return new ModelAndView("redirect:/Login/index.do");

		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", true);
			return new ModelAndView("login", map);
		}
	}

	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public ModelAndView getIndex(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession currentSession = request.getSession(false);

		Map<String, Object> map = new HashMap<String, Object>();
		Account account = SessionManager.getAccount(currentSession);
		map.put("userName", account.getName());
		
		SessionManager.getAcl(currentSession).select(map);
				

		return new ModelAndView("index", map);

	}

	@Override
	public void onCreated(HttpSession session) {
		extAuthServ.removeCache(SessionManager.getAccount(session));
	}

	@Override
	public void onDestroyed(HttpSession session) {
		extAuthServ.removeCache(SessionManager.getAccount(session));
	}
}
