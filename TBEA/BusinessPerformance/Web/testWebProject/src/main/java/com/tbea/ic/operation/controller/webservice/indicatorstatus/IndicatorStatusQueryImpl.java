package com.tbea.ic.operation.controller.webservice.indicatorstatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.controller.webservice.account.ServiceSession;
import com.tbea.ic.operation.controller.webservice.account.ServiceUser;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.EntryService;


@Controller
@WebService(endpointInterface = "com.tbea.ic.operation.controller.webservice.indicatorstatus.IndicatorStatusQuery",
serviceName = "IndicatorStatusQuery")
public class IndicatorStatusQueryImpl implements IndicatorStatusQuery{
	
	@Autowired
	private EntryService entryService;

	@Autowired
	private ApproveService approveService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	public List<ZBStatus> getStatus(ServiceUser usr, ZBType entryType, Date date, List<Integer> comps){
		if (null != ServiceSession.getSession(usr)){
			Organization org = companyManager.getBMDBOrganization();
			
			List<Company> dws = new ArrayList<Company>();
			for(int i = 0; i < comps.size(); ++i){
				Company comp = org.getCompany(comps.get(i));
				if (null != comp){
					dws.add(comp);
				} else{
					dws.add(CompanyManager.getEmptyCompany());
				}
			}
			List<ZBStatus> result = new ArrayList<ZBStatus>();
			if (!dws.isEmpty()) {
				List<String[]> entryStatus = entryService.getEntryStatus(new java.sql.Date(date.getTime()),
						entryType, dws);
				for (int i = 0; i < entryStatus.size(); ++i) {
					result.add(ZBStatus.fromString(entryStatus.get(i)[1]));
				}
			}
			return result;
		}
		return null;
	}
	
}
