package com.tbea.ic.operation.controller.servlet.report;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.reportframe.component.Component;
import com.tbea.ic.operation.reportframe.component.ComponentManager;
import com.tbea.ic.operation.reportframe.component.ComponentManager.Config;
import com.tbea.ic.operation.reportframe.component.controller.ControllerRequest;
import com.tbea.ic.operation.reportframe.component.controller.ControllerSession;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.reportframe.component.service.Transaction;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;

@Controller
@RequestMapping(value = "report")
public class ReportServlet {

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	ComponentManager compMgr = new ComponentManager();

	@Autowired
	Transaction trProxy;
	
	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Autowired
	NDJHZBDao ndjhzbDao;

	@Autowired
	YDJHZBDao ydjhzbDao;

	@Autowired
	YDZBZTDao ydzbztDao;

	@Autowired
	SJZBDao sjzbDao;

	@Autowired
	YJ20ZBDao yj20zbDao;

	@Autowired
	YJ28ZBDao yj28zbDao;

	AccumulatorFactory accFac;
	
	@Autowired
	public void init() {
		accFac = new AccumulatorFactory(sjzbDao, yj20zbDao, yj28zbDao, ydzbztDao, ydjhzbDao, ndjhzbDao);
	}
	
	public static interface AuthManager{
		Map<String, Object> getAuthedCompanies(int authType);
	}
	
	public static interface CompanyTypeIdMapper{
		int getId(int type);
		int getType(int id);
		List<Company> getCompanies(List<Integer> ids);
	}
	
	@RequestMapping(value = "{controllor}.do")
	public ModelAndView ssoLogin(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("controllor") String controllor) throws Exception {
		if ("console".equals(controllor)){
//			String url = request.getRequestURI();
//			String rootUrl = url.substring(1);
//			int rootPos = rootUrl.indexOf('/');
//			rootUrl = rootUrl.substring(0, rootPos);
//			String redirUrl = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/" + rootUrl + "/report/";
			Map<String, Config> controllers = compMgr.getController();
			response.setCharacterEncoding("utf-8");       
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write("<html>");
			Map<String, List<String>> folderMap = new HashMap<String, List<String>>();
			for(Entry<String, Config> cid : controllers.entrySet()){
				
				Config cfg = cid.getValue();
				if (!folderMap.containsKey(cfg.getPath())){
					folderMap.put(cfg.getPath(), new ArrayList<String>());
				}
				folderMap.get(cfg.getPath()).add(cid.getKey());
			}
			Set<String> keys =  new TreeSet<String>();
			keys.addAll(folderMap.keySet());
			for (String path : keys){
				writer.write("<span>" + path + "</span></br>");
				for (String cid: folderMap.get(path)){
					writer.write("<a href='" + cid + ".do'  target='_blank'>" + cid + ".do</a></br>");
				}
			}
				
			writer.write("</html>");
		}else{
			com.tbea.ic.operation.reportframe.component.controller.Controller controller = compMgr.getController(controllor);
			if (null != controller){
				Context context = new Context();
				context.put(Component.REQUEST, new ControllerRequest(request));
				context.put(Component.SESSION, new ControllerSession(request.getSession()));
				context.put(Component.RESPONSE, response);
				context.put(Component.CALENDAR, new EasyCalendar());
				context.put("groupSum", new GroupSum());
				context.put("array", new Arrays());
				context.put("transactionManager", trProxy);
				context.put("accFactory", accFac);
				context.put("authManager", new AuthManager(){

					@Override
					public Map<String, Object> getAuthedCompanies(int authType) {
						List<Company> comps = extendAuthService.getAuthedCompanies(SessionManager.getAccount(request.getSession()), authType);
						CompanySelection compSel = new CompanySelection(true, comps);
						Map<String, Object> map = new HashMap<String, Object>();
						compSel.select(map);
						return map;
					}
					
				});
				
				context.put("compTypeIdMapper", new CompanyTypeIdMapper(){

					Organization org = companyManager.getBMDBOrganization();
					
					@Override
					public int getId(int type) {
						return org.getCompany(CompanyType.valueOf(type)).getId();
					}

					@Override
					public int getType(int id) {
						return org.getCompany(id).getType().ordinal();
					}

					@Override
					public List<Company> getCompanies(List<Integer> ids) {
						List<Company> comps = new ArrayList<Company>();
						for (Integer id : ids){
							comps.add(org.getCompany(id));
						}
						return comps;
					}
					
				});
				
				controller.run(context);
				return (ModelAndView) context.get(com.tbea.ic.operation.reportframe.component.controller.Controller.MODEL_AND_VIEW);
			}
		}
		
		
		return null;
	}
}
