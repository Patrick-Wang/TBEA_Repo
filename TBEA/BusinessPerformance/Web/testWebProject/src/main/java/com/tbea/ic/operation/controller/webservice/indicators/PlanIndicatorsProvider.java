package com.tbea.ic.operation.controller.webservice.indicators;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.login.LoginService;
import com.tbea.ic.operation.service.planindicators.PlanIndicators;

@WebService
public class PlanIndicatorsProvider {
	
	@Autowired
	private LoginService loginServ;

	@Autowired
	private PlanIndicators planService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	public List<CompanyIndicator> getYearPlan(
			@WebParam(name = "appId") String appId, 
			@WebParam(name = "companyIds") List<Integer> companyIds, 
			@WebParam(name = "year") int year){
		Account account = loginServ.getAppAccount(appId);
		if (account != null) {
			List<CompanyIndicator> result = new ArrayList<CompanyIndicator>();
			if (!companyIds.isEmpty()){
				for (Integer compId : companyIds) {
					Company comp =  companyManager.getBMDBOrganization().getCompany(compId);
					if (null != comp) {
						CompanyIndicator ci = new CompanyIndicator();
						ci.setCompanyId(compId);
						ci.setTotalProfit(planService.getYearPlan(GSZB.LRZE1.value(), comp, year));
						ci.setSalesIncome(planService.getYearPlan(GSZB.XSSR6.value(), comp, year));
						result.add(ci);
					}
				}
			}
			return result;
		}

		return null;
	}	
	
	public List<CompanyIndicator> getMonthPlan(
			@WebParam(name = "appId") String appId, 
			@WebParam(name = "companyIds") List<Integer> companyIds, 
			@WebParam(name = "year") int year, 
			@WebParam(name = "month") int month){
		Account account = loginServ.getAppAccount(appId);
		if (account != null) {
			List<CompanyIndicator> result = new ArrayList<CompanyIndicator>();
			if (!companyIds.isEmpty()){
				for (Integer compId : companyIds) {
					Company comp =  companyManager.getBMDBOrganization().getCompany(compId);
					if (null != comp) {
						CompanyIndicator ci = new CompanyIndicator();
						ci.setCompanyId(compId);
						ci.setTotalProfit(planService.getMonthPlan(GSZB.LRZE1.value(), comp, year, month));
						ci.setSalesIncome(planService.getMonthPlan(GSZB.XSSR6.value(), comp, year, month));
						result.add(ci);
					}
				}
			}
			return result;
		}

		return null;
	}
}
