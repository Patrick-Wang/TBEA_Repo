package com.tbea.ic.operation.service.cpzlqk.pdnwbzlztqk;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.dwmc.DwmcDao;
import com.tbea.ic.operation.model.dao.cpzlqk.nbzlztqk.NbyclzlwtDao;
import com.tbea.ic.operation.model.dao.cpzlqk.wbzlztqk.WbyclzlwtDao;

@Service(PdnwbzlztqkServiceImpl.NAME)
@Transactional("transactionManager")
public class PdnwbzlztqkServiceImpl implements PdnwbzlztqkService {

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	DwmcDao dwmcDao;
	
	@Autowired
	NbyclzlwtDao nbyclzlwtDao;

	@Autowired
	WbyclzlwtDao wbyclzlwtDao;
	
	public final static String NAME = "PdnwbzlztqkServiceImpl";

	@Override
	public List<List<String>> getJdnwbzlqk(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.PDCY).getSubCompanies();
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps.get(i)),
					wbyclzlwtDao.getCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps.get(i)),
					nbyclzlwtDao.getCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i)),
					wbyclzlwtDao.getCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i))));
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps),
				wbyclzlwtDao.getCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps),
				nbyclzlwtDao.getCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps),
				wbyclzlwtDao.getCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps)));
		return result;
	}

	@Override
	public List<List<String>> getJdnwbzlqk(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList("当期", 
				nbyclzlwtDao.getCount(ec.getSeasonEnd().getDate(), d, company),
				wbyclzlwtDao.getCount(ec.getSeasonEnd().getDate(), d, company)));
		result.add(toList("去年同期", 
				nbyclzlwtDao.getCount(ec.getLastYear().getSeasonEnd().getDate(), d, company),
				wbyclzlwtDao.getCount(ec.getLastYear().getSeasonEnd().getDate(), d, company)));
		return result;
	}

	@Override
	public List<List<String>> getJdSczzzlqk(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.PDCY).getSubCompanies();
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getSczzzlqkCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps.get(i)),
					wbyclzlwtDao.getSczzzlqkCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps.get(i)),
					nbyclzlwtDao.getSczzzlqkCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i)),
					wbyclzlwtDao.getSczzzlqkCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i))));
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getSczzzlqkCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps),
				wbyclzlwtDao.getSczzzlqkCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps),
				nbyclzlwtDao.getSczzzlqkCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps),
				wbyclzlwtDao.getSczzzlqkCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps)));
		return result;
	}

	@Override
	public List<List<String>> getJdSczzzlqk(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList("当期", 
				nbyclzlwtDao.getCount(ec.getSeasonStart().getDate(), ec.getSeasonEnd().getDate(), company),
				wbyclzlwtDao.getCount(ec.getSeasonStart().getDate(), ec.getSeasonEnd().getDate(), company)));
		result.add(toList("去年同期", 
				nbyclzlwtDao.getCount(ec.getLastYear().getSeasonStart().getDate(), ec.getLastYear().getSeasonEnd().getDate(), company),
				wbyclzlwtDao.getCount(ec.getLastYear().getSeasonStart().getDate(), ec.getLastYear().getSeasonEnd().getDate(), company)));
		return result;
	}

	@Override
	public List<List<String>> getJdsjzlqk(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.PDCY).getSubCompanies();
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getSjzlqkCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps.get(i)),
					wbyclzlwtDao.getSjzlqkCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps.get(i)),
					nbyclzlwtDao.getSjzlqkCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i)),
					wbyclzlwtDao.getSjzlqkCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i))));
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getSjzlqkCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps),
				wbyclzlwtDao.getSjzlqkCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps),
				nbyclzlwtDao.getSjzlqkCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps),
				wbyclzlwtDao.getSjzlqkCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps)));
		return result;
	}

	@Override
	public List<List<String>> getJdSjzlqk(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList("当期", 
				nbyclzlwtDao.getSjzlqkCount(ec.getSeasonEnd().getDate(), d, company),
				wbyclzlwtDao.getSjzlqkCount(ec.getSeasonEnd().getDate(), d, company)));
		result.add(toList("去年同期", 
				nbyclzlwtDao.getSjzlqkCount(ec.getLastYear().getSeasonEnd().getDate(), d, company),
				wbyclzlwtDao.getSjzlqkCount(ec.getLastYear().getSeasonEnd().getDate(), d, company)));
		return result;
	}

	@Override
	public List<WaveItem> getSjzlqkWaveItems(Date d, YDJDType yjType) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		if (yjType == YDJDType.YD){
			Company cy = companyManager.getVirtualCYOrg().getCompany(CompanyType.PDCY);
			WaveItem nb = new WaveItem(cy.getName() + "内部质量问题", new ArrayList<String>());
			WaveItem wb = new WaveItem(cy.getName() + "外部质量问题", new ArrayList<String>());
			wis.add(nb);
			wis.add(wb);
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				nb.getData().add(
						nbyclzlwtDao.getSjzlqkCount(ec.getDate(), cy.getSubCompanies()) + "");
				wb.getData().add(
						wbyclzlwtDao.getSjzlqkCount(ec.getDate(), cy.getSubCompanies()) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getSjzlqkWaveItems(Date d, YDJDType yjType,
			Company company) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		WaveItem winb = new WaveItem(company.getName() + "内部", new ArrayList<String>());
		WaveItem wiwb = new WaveItem(company.getName() + "外部", new ArrayList<String>());
		wis.add(winb);
		wis.add(wiwb);
		if (yjType == YDJDType.YD){
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				winb.getData().add(
						nbyclzlwtDao.getSjzlqkCount(ec.getDate(), company) + "");
				wiwb.getData().add(
						wbyclzlwtDao.getSjzlqkCount(ec.getDate(), company) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItems(Date d, YDJDType yjType) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		if (yjType == YDJDType.YD){
			Company cy = companyManager.getVirtualCYOrg().getCompany(CompanyType.PDCY);
			WaveItem nb = new WaveItem(cy.getName() + "内部质量问题", new ArrayList<String>());
			WaveItem wb = new WaveItem(cy.getName() + "外部质量问题", new ArrayList<String>());
			wis.add(nb);
			wis.add(wb);
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				nb.getData().add(
						nbyclzlwtDao.getCount(ec.getDate(), cy.getSubCompanies()) + "");
				wb.getData().add(
						wbyclzlwtDao.getCount(ec.getDate(), cy.getSubCompanies()) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItems(Date d, YDJDType yjType, Company company) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		WaveItem winb = new WaveItem(company.getName() + "内部", new ArrayList<String>());
		WaveItem wiwb = new WaveItem(company.getName() + "外部", new ArrayList<String>());
		wis.add(winb);
		wis.add(wiwb);
		if (yjType == YDJDType.YD){
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				winb.getData().add(
						nbyclzlwtDao.getCount(ec.getDate(), company) + "");
				wiwb.getData().add(
						wbyclzlwtDao.getCount(ec.getDate(), company) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItemsSczzzlqk(Date d, YDJDType yjType) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		if (yjType == YDJDType.YD){
			Company cy = companyManager.getVirtualCYOrg().getCompany(CompanyType.PDCY);
			WaveItem nb = new WaveItem(cy.getName() + "内部质量问题", new ArrayList<String>());
			WaveItem wb = new WaveItem(cy.getName() + "外部质量问题", new ArrayList<String>());
			wis.add(nb);
			wis.add(wb);
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				nb.getData().add(
						nbyclzlwtDao.getSczzzlqkCount(ec.getDate(), cy.getSubCompanies()) + "");
				wb.getData().add(
						wbyclzlwtDao.getSczzzlqkCount(ec.getDate(), cy.getSubCompanies()) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItemsSczzzlqk(Date d, YDJDType yjType,
			Company company) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		WaveItem winb = new WaveItem(company.getName() + "内部", new ArrayList<String>());
		WaveItem wiwb = new WaveItem(company.getName() + "外部", new ArrayList<String>());
		wis.add(winb);
		wis.add(wiwb);
		if (yjType == YDJDType.YD){
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				winb.getData().add(
						nbyclzlwtDao.getSczzzlqkCount(ec.getDate(), company) + "");
				wiwb.getData().add(
						wbyclzlwtDao.getSczzzlqkCount(ec.getDate(), company) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<List<String>> getYdnwbzlqk(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.PDCY).getSubCompanies();
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getCount(d, comps.get(i)),
					wbyclzlwtDao.getCount(d, comps.get(i)),
					nbyclzlwtDao.getCount(cal.getMonths(1).getDate(), d, comps.get(i)),
					wbyclzlwtDao.getCount(cal.getMonths(1).getDate(), d, comps.get(i))));
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getCount(d, comps),
				wbyclzlwtDao.getCount(d, comps),
				nbyclzlwtDao.getCount(cal.getMonths(1).getDate(), d, comps),
				wbyclzlwtDao.getCount(cal.getMonths(1).getDate(), d, comps)));
		return result;
	}

	
	@Override
	public List<List<String>> getYdnwbzlqk(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList(ec.getMonthFormat(), 
				nbyclzlwtDao.getCount(d, company),
				wbyclzlwtDao.getCount(d, company)));
		result.add(toList("累计", 
				nbyclzlwtDao.getCount(ec.getMonths(1).getDate(), d, company),
				wbyclzlwtDao.getCount(ec.getMonths(1).getDate(), d, company)));
		return result;
	}

	
	@Override
	public List<List<String>> getYdSczzzlqk(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.PDCY).getSubCompanies();
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getSczzzlqkCount(d, comps.get(i)),
					wbyclzlwtDao.getSczzzlqkCount(d, comps.get(i)),
					nbyclzlwtDao.getSczzzlqkCount(cal.getMonths(1).getDate(), d, comps.get(i)),
					wbyclzlwtDao.getSczzzlqkCount(cal.getMonths(1).getDate(), d, comps.get(i))));
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getSczzzlqkCount(d, comps),
				wbyclzlwtDao.getSczzzlqkCount(d, comps),
				nbyclzlwtDao.getSczzzlqkCount(cal.getMonths(1).getDate(), d, comps),
				wbyclzlwtDao.getSczzzlqkCount(cal.getMonths(1).getDate(), d, comps)));
		return result;
	}

	
	@Override
	public List<List<String>> getYdSczzzlqk(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList(ec.getMonthFormat(), 
				nbyclzlwtDao.getSczzzlqkCount(d, company),
				wbyclzlwtDao.getSczzzlqkCount(d, company)));
		result.add(toList("累计", 
				nbyclzlwtDao.getSczzzlqkCount(ec.getMonths(1).getDate(), d, company),
				wbyclzlwtDao.getSczzzlqkCount(ec.getMonths(1).getDate(), d, company)));
		return result;
	}


	@Override
	public List<List<String>> getYdsjzlqk(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.PDCY).getSubCompanies();
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getSjzlqkCount(d, comps.get(i)),
					wbyclzlwtDao.getSjzlqkCount(d, comps.get(i)),
					nbyclzlwtDao.getSjzlqkCount(cal.getMonths(1).getDate(), d, comps.get(i)),
					wbyclzlwtDao.getSjzlqkCount(cal.getMonths(1).getDate(), d, comps.get(i))));
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getSjzlqkCount(d, comps),
				wbyclzlwtDao.getSjzlqkCount(d, comps),
				nbyclzlwtDao.getSjzlqkCount(cal.getMonths(1).getDate(), d, comps),
				wbyclzlwtDao.getSjzlqkCount(cal.getMonths(1).getDate(), d, comps)));
		return result;
	}

	
	@Override
	public List<List<String>> getYdSjzlqk(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList(ec.getMonthFormat(), 
				nbyclzlwtDao.getSjzlqkCount(d, company),
				wbyclzlwtDao.getSjzlqkCount(d, company)));
		result.add(toList("累计", 
				nbyclzlwtDao.getSjzlqkCount(ec.getMonths(1).getDate(), d, company),
				wbyclzlwtDao.getSjzlqkCount(ec.getMonths(1).getDate(), d, company)));
		return result;
	}

	
	private List<String> toList(String compName, Integer dynb, Integer dywb) {
		List<String> result = new ArrayList<String>();
		result.add(compName);
		result.add(dynb + "");
		result.add(dywb + "");
		result.add(MathUtil.sum(dywb, dywb) + "");
		return result;
	}

	
	private List<String> toList(String compName, Integer dynb, Integer dywb,
			Integer ljnb, Integer ljwb) {
		List<String> result = new ArrayList<String>();
		result.add(compName);
		result.add(dynb + "");
		result.add(dywb + "");
		result.add(MathUtil.sum(dywb, dywb) + "");
		result.add(ljnb + "");
		result.add(ljwb + "");
		result.add(MathUtil.sum(ljnb, ljwb) + "");
		return result;
	}
}
