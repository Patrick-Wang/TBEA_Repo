package com.tbea.ic.operation.service.report.handlers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.xml.frame.report.ReportLogger;

import net.sf.json.JSONArray;

public class SJZBImporter {
	CompanyManager companyManager;
	
	ApproveService approveService;
	
	EntryService entryService;

	public SJZBImporter(CompanyManager companyManager,
			ApproveService approveService, EntryService entryService) {
		super();
		this.companyManager = companyManager;
		this.approveService = approveService;
		this.entryService = entryService;
	}
	
	private void importData(ZBStatus zbStatus, JSONArray jsonArray, Date date,
			Company comp) {
		Calendar time = null;
		switch (zbStatus) {
		case APPROVED:
			List<Company> compsTmp = new ArrayList<Company>();
			compsTmp.add(comp);
			approveService.unapproveSjZb(Account.KNOWN_ACCOUNT_GFGS, compsTmp,
					date);
			entryService.submitZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray, time);
			approveService.approveSjZb(Account.KNOWN_ACCOUNT_GFGS, compsTmp,
					date, true);
			break;
		case APPROVED_2:
			entryService.saveZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray, time);
			List<Company> compsTmp2 = new ArrayList<Company>();
			compsTmp2.add(comp);
			approveService.approveSjZb(Account.KNOWN_ACCOUNT_JYFZ, compsTmp2,
					date, true);
			break;
		case NONE:
		case SAVED:
			entryService.saveZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray, time);
			break;
		case SUBMITTED:
			entryService.submitZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray, time);
			break;
		case SUBMITTED_2:
			entryService.submitToDeputy(date, null, comp.getType(),
					ZBType.BYSJ, jsonArray, time);
			break;
		default:
			break;
		}
	}
	
	public void impData(Date d, CompanyType type, List<Object> dataArray) {
		
		JSONArray jd = JSONArray.fromObject(dataArray);
		ReportLogger.logger().debug("{} {} {}", new Object[]{Util.formatToMonth(d), type.getValue(), jd.toString()});
		ZBStatus zbStatus = entryService.getZbStatus(d, type,
				ZBType.BYSJ).get(0);
		Organization org = companyManager.getBMDBOrganization();
		importData(zbStatus, jd, d, org.getCompany(type));		
	}
}
