package com.tbea.ic.operation.service.cpzlqk.xlnwbzlztqk;

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

@Service(XlnwbzlztqkServiceImpl.NAME)
@Transactional("transactionManager")
public class XlnwbzlztqkServiceImpl implements XlnwbzlztqkService {

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	DwmcDao dwmcDao;
	
	@Autowired
	NbyclzlwtDao nbyclzlwtDao;

	@Autowired
	WbyclzlwtDao wbyclzlwtDao;
	
	public final static String NAME = "XlnwbzlztqkServiceImpl";

	@Override
	public List<WaveItem> getGyzlwtWaveItems(Date d, YDJDType yjType) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY).getSubCompanies();
		for (int i = 0; i < comps.size(); ++i){
			wis.addAll(getGyzlwtWaveItems(d, yjType, comps.get(i)));
		}
		return wis;
	}

	public List<WaveItem> getGyzlwtWaveItems(Date d, YDJDType yjType,
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
						nbyclzlwtDao.getGyzlwtCount(ec.getDate(), company) + "");
				wiwb.getData().add(
						wbyclzlwtDao.getGyzlwtCount(ec.getDate(), company) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<List<String>> getJdGyzlwt(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY).getSubCompanies();
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getGyzlwtCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps.get(i)),
					wbyclzlwtDao.getGyzlwtCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps.get(i)),
					nbyclzlwtDao.getGyzlwtCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i)),
					wbyclzlwtDao.getGyzlwtCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i))));
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getGyzlwtCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps),
				wbyclzlwtDao.getGyzlwtCount(cal.getSeasonStart().getDate(), cal.getSeasonEnd().getDate(), comps),
				nbyclzlwtDao.getGyzlwtCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps),
				wbyclzlwtDao.getGyzlwtCount(cal.getLastYear().getSeasonStart().getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps)));
		return result;
	}

	@Override
	public List<List<String>> getJdGyzlwt(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList("当期", 
				nbyclzlwtDao.getGyzlwtCount(ec.getSeasonEnd().getDate(), d, company),
				wbyclzlwtDao.getGyzlwtCount(ec.getSeasonEnd().getDate(), d, company)));
		result.add(toList("去年同期", 
				nbyclzlwtDao.getGyzlwtCount(ec.getLastYear().getSeasonEnd().getDate(), d, company),
				wbyclzlwtDao.getGyzlwtCount(ec.getLastYear().getSeasonEnd().getDate(), d, company)));
		return result;
	}

	@Override
	public List<List<String>> getJdnwbzlqk(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY).getSubCompanies();
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
				nbyclzlwtDao.getCount(ec.getSeasonStart().getDate(), ec.getSeasonEnd().getDate(), company),
				wbyclzlwtDao.getCount(ec.getSeasonStart().getDate(), ec.getSeasonEnd().getDate(), company)));
		result.add(toList("去年同期", 
				nbyclzlwtDao.getCount(ec.getLastYear().getSeasonEnd().getDate(), ec.getLastYear().getSeasonEnd().getDate(), company),
				wbyclzlwtDao.getCount(ec.getLastYear().getSeasonEnd().getDate(), ec.getLastYear().getSeasonEnd().getDate(), company)));
		return result;
	}

	@Override
	public List<List<String>> getJdSczzzlqk(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY).getSubCompanies();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getJdYclzlwt(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY).getSubCompanies();
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		Date seasonStart = cal.getSeasonStart().getDate();
		Date seasonEnd = cal.getSeasonEnd().getDate();
		Date qnSeasonStart = cal.getLastYear().getSeasonStart().getDate();
		Date qnSeasonEnd = cal.getLastYear().getSeasonEnd().getDate();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getYclzlRcjcwtCount(seasonStart, seasonEnd, comps.get(i)),
					nbyclzlwtDao.getYclzlCnzzwtCount(seasonStart, seasonEnd, comps.get(i)),
					wbyclzlwtDao.getYclzlCwxcwtCount(seasonStart, seasonEnd, comps.get(i)), 
					nbyclzlwtDao.getYclzlRcjcwtCount(qnSeasonStart, seasonEnd, comps.get(i)),
					nbyclzlwtDao.getYclzlCnzzwtCount(qnSeasonStart, seasonEnd, comps.get(i)),
					wbyclzlwtDao.getYclzlCwxcwtCount(qnSeasonStart, qnSeasonEnd, comps.get(i))));
		}
		result.add(toList(
				"合计",
				nbyclzlwtDao.getYclzlRcjcwtCount(seasonStart, seasonEnd, comps),
				nbyclzlwtDao.getYclzlCnzzwtCount(seasonStart, seasonEnd, comps),
				wbyclzlwtDao.getYclzlCwxcwtCount(seasonStart, seasonEnd, comps), 
				nbyclzlwtDao.getYclzlRcjcwtCount(qnSeasonStart, seasonEnd, comps),
				nbyclzlwtDao.getYclzlCnzzwtCount(qnSeasonStart, seasonEnd, comps),
				wbyclzlwtDao.getYclzlCwxcwtCount(qnSeasonStart, qnSeasonEnd, comps)));
		return result;
	}

	@Override
	public List<List<String>> getJdYclzlwt(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList("当期",
				nbyclzlwtDao.getYclzlRcjcwtCount(ec.getLastYear().getSeasonEnd().getDate(), company),
				nbyclzlwtDao.getYclzlCnzzwtCount(ec.getLastYear().getSeasonEnd().getDate(), company),
				wbyclzlwtDao.getYclzlCwxcwtCount(ec.getLastYear().getSeasonEnd().getDate(), company))); 
		result.add(toList("去年同期", 
				nbyclzlwtDao.getYclzlRcjcwtCount(ec.getLastYear().getLastYear().getSeasonEnd().getDate(), company),
				nbyclzlwtDao.getYclzlCnzzwtCount(ec.getLastYear().getLastYear().getSeasonEnd().getDate(), company),
				wbyclzlwtDao.getYclzlCwxcwtCount(ec.getLastYear().getLastYear().getSeasonEnd().getDate(), company)));
		return result;
	}

	@Override
	public List<WaveItem> getWaveItems(Date d, YDJDType yjType) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		if (yjType == YDJDType.YD){
			Company cy = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY);
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
			Company cy = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY);
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
	public List<WaveItem> getYclzlwtWaveItems(Date d, YDJDType yjType) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		if (yjType == YDJDType.YD){
			Company cy = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY);
			WaveItem wirc = new WaveItem(cy.getName() + "入场检测反馈", new ArrayList<String>());
			WaveItem wicn = new WaveItem(cy.getName() + "厂内制造反馈", new ArrayList<String>());
			WaveItem wicw = new WaveItem(cy.getName() + "厂外现场反馈", new ArrayList<String>());
			wis.add(wirc);
			wis.add(wicn);
			wis.add(wicw);
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				wirc.getData().add(
						nbyclzlwtDao.getYclzlRcjcwtCount(ec.getDate(), cy.getSubCompanies()) + "");
				wicn.getData().add(
						nbyclzlwtDao.getYclzlCnzzwtCount(ec.getDate(), cy.getSubCompanies()) + "");
				wicw.getData().add(
						wbyclzlwtDao.getYclzlCwxcwtCount(ec.getDate(), cy.getSubCompanies()) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getYclzlwtWaveItems(Date d, YDJDType yjType,
			Company company) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		WaveItem wirc = new WaveItem(company.getName() + "入厂检测", new ArrayList<String>());
		WaveItem wicn = new WaveItem(company.getName() + "厂内制造", new ArrayList<String>());
		WaveItem wicw = new WaveItem(company.getName() + "厂外现场", new ArrayList<String>());
		wis.add(wirc);
		wis.add(wicn);
		wis.add(wicw);
		if (yjType == YDJDType.YD){
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				wirc.getData().add(
						nbyclzlwtDao.getYclzlRcjcwtCount(ec.getDate(), company) + "");
				wicn.getData().add(
						nbyclzlwtDao.getYclzlCnzzwtCount(ec.getDate(), company) + "");
				wicw.getData().add(
						wbyclzlwtDao.getYclzlCwxcwtCount(ec.getDate(), company) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<List<String>> getYdGyzlwt(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY).getSubCompanies();
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getGyzlwtCount(d, comps.get(i)),
					wbyclzlwtDao.getGyzlwtCount(d, comps.get(i)),
					nbyclzlwtDao.getGyzlwtCount(cal.getMonths(1).getDate(), d, comps.get(i)),
					wbyclzlwtDao.getGyzlwtCount(cal.getMonths(1).getDate(), d, comps.get(i))));
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getGyzlwtCount(d, comps),
				wbyclzlwtDao.getGyzlwtCount(d, comps),
				nbyclzlwtDao.getGyzlwtCount(cal.getMonths(1).getDate(), d, comps),
				wbyclzlwtDao.getGyzlwtCount(cal.getMonths(1).getDate(), d, comps)));
		return result;
	}

	@Override
	public List<List<String>> getYdGyzlwt(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList(ec.getMonthFormat(), 
				nbyclzlwtDao.getGyzlwtCount(d, company),
				wbyclzlwtDao.getGyzlwtCount(d, company)));
		result.add(toList("累计", 
				nbyclzlwtDao.getGyzlwtCount(ec.getMonths(1).getDate(), d, company),
				wbyclzlwtDao.getGyzlwtCount(ec.getMonths(1).getDate(), d, company)));
		return result;
	}

	@Override
	public List<List<String>> getYdnwbzlqk(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY).getSubCompanies();
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
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY).getSubCompanies();
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
	public List<List<String>> getYdYclzlwt(Date d) {
		List<Company> comps = companyManager.getVirtualCYOrg().getCompany(CompanyType.XLCY).getSubCompanies();
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getYclzlRcjcwtCount(d, comps.get(i)),
					nbyclzlwtDao.getYclzlCnzzwtCount(d, comps.get(i)),
					wbyclzlwtDao.getYclzlCwxcwtCount(d, comps.get(i)), 
					nbyclzlwtDao.getYclzlRcjcwtCount(cal.getLastYear().getDate(), comps.get(i)),
					nbyclzlwtDao.getYclzlCnzzwtCount(cal.getLastYear().getDate(), comps.get(i)),
					wbyclzlwtDao.getYclzlCwxcwtCount(cal.getLastYear().getDate(), comps.get(i)))); 
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getYclzlRcjcwtCount(d, comps),
				nbyclzlwtDao.getYclzlCnzzwtCount(d, comps),
				wbyclzlwtDao.getYclzlCwxcwtCount(d, comps), 
				nbyclzlwtDao.getYclzlRcjcwtCount(cal.getLastYear().getDate(), comps),
				nbyclzlwtDao.getYclzlCnzzwtCount(cal.getLastYear().getDate(), comps),
				wbyclzlwtDao.getYclzlCwxcwtCount(cal.getLastYear().getDate(), comps))); 
		return result;
	}

	@Override
	public List<List<String>> getYdYclzlwt(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList(ec.getMonthFormat(), 
				nbyclzlwtDao.getYclzlRcjcwtCount(d, company),
				nbyclzlwtDao.getYclzlCnzzwtCount(d, company),
				wbyclzlwtDao.getYclzlCwxcwtCount(d, company))); 
		result.add(toList("累计", 
				nbyclzlwtDao.getYclzlRcjcwtCount(ec.getMonths(1).getDate(), d, company),
				nbyclzlwtDao.getYclzlCnzzwtCount(ec.getMonths(1).getDate(), d, company),
				wbyclzlwtDao.getYclzlCwxcwtCount(ec.getMonths(1).getDate(), d, company))); 
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

	private List<String> toList(String compName, Integer rcjc, Integer cnzz,
			Integer cwxc) {
		List<String> result = new ArrayList<String>();
		result.add(compName);
		result.add(rcjc + "");
		result.add(cnzz + "");
		result.add(cwxc + "");
		result.add(MathUtil.sum(new Integer[]{
				rcjc,cnzz,cwxc
		}) + "");
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

	private List<String> toList(String compName, Integer dynbrc, Integer dynbcn,
			Integer dynbcw, Integer dywbrc, Integer dywbcn, Integer dywbcw) {
		List<String> result = new ArrayList<String>();
		result.add(compName);
		result.add(dynbrc + "");
		result.add(dynbcn + "");
		result.add(dynbcw + "");
		result.add(MathUtil.sum(new Integer[]{
				dynbrc,dynbcn,dynbcw
		}) + "");
		result.add(dywbrc + "");
		result.add(dywbcn + "");
		result.add(dywbcw + "");
		result.add(MathUtil.sum(new Integer[]{
				dywbrc,dywbcn,dywbcw
		}) + "");
		return result;
	}

}
