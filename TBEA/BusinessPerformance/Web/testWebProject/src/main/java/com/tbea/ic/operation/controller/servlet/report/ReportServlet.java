package com.tbea.ic.operation.controller.servlet.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.ClosureMap;
import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.CompanySelection.Filter;
import com.tbea.ic.operation.common.Data;
import com.tbea.ic.operation.common.DataNode;
import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.EasyList;
import com.tbea.ic.operation.common.PropMap;
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
		
	
	@RequestMapping(value = "console/show.do")
	public ModelAndView consoleShow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DataNode tree = compMgr.getCSB();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("componentTree", JSONObject.fromObject(tree).toString());
		return new ModelAndView("report/report_console", model);
	}
	
	@RequestMapping(value = "{controllor}.do")
	public ModelAndView ssoLogin(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("controllor") String controllor) throws Exception {
		
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
			context.put("compMgr", companyManager);
			context.put("orgs", new CMGRClosureMap(companyManager){

				@Override
				protected Object onGetProp(Organization org, List<Object> args) throws Exception {
					if ("ids".equals(args.get(0))){
						return org.getCompany(CompanyType.valueOf((Integer)args.get(1))).getId();
					}else if ("types".equals(args.get(0))){
						return org.getCompany((Integer)args.get(1)).getType().ordinal();
					}else if ("companiesByIds".equals(args.get(0))){
						List<Integer> ids = (List) args.get(1);
						List<Company> comps = new ArrayList<Company>();
						for (Integer id : ids){
							comps.add(org.getCompany(id));
						}
						return comps;
					}else{
						return null;
					}
				}

				@Override
				protected boolean onValidating(List<Object> args) {
					return args.size() == 2;
				}
			});
			
			context.put("dataNodeFactory", new ClosureMap(){

				@Override
				protected boolean validate(List<Object> args) throws Exception {
					return args.size() == 2;
				}

				@Override
				protected Object onGetProp(List<Object> args) throws Exception {
					List<Integer> ids = (List<Integer>) args.get(0);
					List<String> vals = (List<String>) args.get(1);
					List<DataNode> nodes = new ArrayList<DataNode>(args.size());
					for (int i = 0; i < ids.size(); ++i){
						DataNode dn = new DataNode();
						Data d = new Data();
						d.setId(ids.get(i));
						d.setValue(vals.get(i));
						dn.setData(d);
						nodes.add(dn);
					}
					return nodes;
				}
			});
			

			context.put("authMgr", new PropMap(){

				@Override
				public Object getProperty(Object key) throws Exception {
					Integer authType = (Integer)key;
					List<Company> comps = extendAuthService.getAuthedCompanies(SessionManager.getAccount(request.getSession()), authType);
					if (authType == 43){
						Organization org = companyManager.getVirtualCYOrg();
						List<Company> byqs = new EasyList<Company>(org.getCompany(CompanyType.BYQCY).getSubCompanies()).clone();
						List<Company> xls = new EasyList<Company>(org.getCompany(CompanyType.XLCY).getSubCompanies()).clone();
						CompanySelection compSel = new CompanySelection(false, org.getTopCompany(), new Filter(){

							@Override
							public boolean keep(Company comp) {
								for (Company cp : comps){
									if (cp.getType() == comp.getType()){
										byqs.remove(comp);
										xls.remove(comp);
										return true;
									}
								}
								
								if (comp.getType() == CompanyType.BYQCY){
									if (byqs.isEmpty()){
										return true;
									}
								}
								
								if (comp.getType() == CompanyType.XLCY){
									if (xls.isEmpty()){
										return true;
									}
								}
								
								return false;
							}

							@Override
							public boolean keepGroup(Company comp) {
								return true;
							}
						});
						Map<String, Object> map = new HashMap<String, Object>();
						compSel.select(map);
						return map; 
					}else{
						CompanySelection compSel = new CompanySelection(true, comps);
						Map<String, Object> map = new HashMap<String, Object>();
						compSel.select(map);
						return map;
					}
				}
			});
			controller.run(context);
			return (ModelAndView) context.get(com.tbea.ic.operation.reportframe.component.controller.Controller.MODEL_AND_VIEW);
		}
		
		
		
		return null;
	}
}
