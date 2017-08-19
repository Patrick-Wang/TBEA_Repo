package com.tbea.ic.operation.service.cpzlqk.xknwbzlztqk;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.dwmc.DwmcDao;
import com.tbea.ic.operation.model.dao.cpzlqk.nbzlztqk.NbyclzlwtDao;
import com.tbea.ic.operation.model.dao.cpzlqk.wbzlztqk.WbyclzlwtDao;
import com.tbea.ic.operation.model.entity.cpzlqk.NbyclzlwtEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.WbyclzlwtEntity;
import com.tbea.ic.operation.service.cpzlqk.SeasonUtil;
import com.xml.frame.report.util.EasyCalendar;

@Service(XknwbzlztqkServiceImpl.NAME)
@Transactional("transactionManager")
public class XknwbzlztqkServiceImpl implements XknwbzlztqkService {

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	DwmcDao dwmcDao;
	
	@Autowired
	NbyclzlwtDao nbyclzlwtDao;

	@Autowired
	WbyclzlwtDao wbyclzlwtDao;

	public final static String NAME = "XknwbzlztqkServiceImpl";

	@Override
	public List<List<String>> getJdnwbzlqk(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps.get(i), zts),
					wbyclzlwtDao.getCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps.get(i), zts),
					nbyclzlwtDao.getCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i), zts),
					wbyclzlwtDao.getCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i), zts)));
		}
		result.add(toList(
				"合计",
				nbyclzlwtDao.getCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps, zts),
				wbyclzlwtDao.getCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps, zts),
				nbyclzlwtDao.getCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps, zts),
				wbyclzlwtDao.getCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps, zts)));
		return result;
	}

	@Override
	public List<List<String>> getJdnwbzlqk(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList("当期", 
				nbyclzlwtDao.getCount(SeasonUtil.getStart(ec).getDate(), ec.getSeasonEnd().getDate(), company, zts),
				wbyclzlwtDao.getCount(SeasonUtil.getStart(ec).getDate(), ec.getSeasonEnd().getDate(), company, zts)));
		result.add(toList("去年同期", 
				nbyclzlwtDao.getCount(SeasonUtil.getStart(ec.getLastYear()).getDate(), ec.getLastYear().getSeasonEnd().getDate(), company, zts),
				wbyclzlwtDao.getCount(SeasonUtil.getStart(ec.getLastYear()).getDate(), ec.getLastYear().getSeasonEnd().getDate(), company, zts)));
		return result;
	}

	@Override
	public List<List<String>> getJdSczzzlqk(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getSczzzlqkCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps.get(i), zts),
					wbyclzlwtDao.getSczzzlqkCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps.get(i), zts),
					nbyclzlwtDao.getSczzzlqkCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i), zts),
					wbyclzlwtDao.getSczzzlqkCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i), zts)));
		}
		result.add(toList(
				"合计",
				nbyclzlwtDao.getSczzzlqkCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps, zts),
				wbyclzlwtDao.getSczzzlqkCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps, zts),
				nbyclzlwtDao.getSczzzlqkCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps, zts),
				wbyclzlwtDao.getSczzzlqkCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps, zts)));
		return result;
	}

	@Override
	public List<List<String>> getJdSczzzlqk(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList("当期", 
				nbyclzlwtDao.getCount(SeasonUtil.getStart(ec).getDate(), ec.getSeasonEnd().getDate(), company, zts),
				wbyclzlwtDao.getCount(SeasonUtil.getStart(ec).getDate(), ec.getSeasonEnd().getDate(), company, zts)));
		result.add(toList("去年同期", 
				nbyclzlwtDao.getCount(SeasonUtil.getStart(ec.getLastYear()).getDate(), ec.getLastYear().getSeasonEnd().getDate(), company, zts),
				wbyclzlwtDao.getCount(SeasonUtil.getStart(ec.getLastYear()).getDate(), ec.getLastYear().getSeasonEnd().getDate(), company, zts)));
		return result;
	}

	@Override
	public List<List<String>> getJdSczzzlqkxxxx(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		List<List<String>> result = new ArrayList<List<String>>();
		Set<String> xmgs = new HashSet<String>();
		EasyCalendar ec = new EasyCalendar(d);
		for (int i = 0; i < comps.size(); ++i){
			 xmgs.addAll(wbyclzlwtDao.getXmgs(comps.get(i), zts));
			 xmgs.addAll(nbyclzlwtDao.getXmgs(comps.get(i), zts));
			 for (String gs : xmgs){
				 result.add(toList(
						 comps.get(i).getName(),
						 gs,
						 MathUtil.sum(
								 wbyclzlwtDao.getSczzzlqkxxxxCount(SeasonUtil.getStart(ec).getDate(), ec.getSeasonEnd().getDate(), comps.get(i), gs, zts), 
								 nbyclzlwtDao.getSczzzlqkxxxxCount(SeasonUtil.getStart(ec).getDate(), ec.getSeasonEnd().getDate(), comps.get(i), gs, zts)),
						 MathUtil.sum(
								 wbyclzlwtDao.getSczzzlqkxxxxCount(SeasonUtil.getStart(ec.getLastYear()).getDate(), ec.getLastYear().getSeasonEnd().getDate(), comps.get(i), gs, zts), 
								 nbyclzlwtDao.getSczzzlqkxxxxCount(SeasonUtil.getStart(ec.getLastYear()).getDate(), ec.getLastYear().getSeasonEnd().getDate(), comps.get(i), gs, zts))));
			 }
		}
		
		return result;
	}  

	@Override
	public List<List<String>> getJdSczzzlqkxxxx(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		Set<String> xmgs = new HashSet<String>();
		EasyCalendar ec = new EasyCalendar(d);
		
		 xmgs.addAll(wbyclzlwtDao.getXmgs(company, zts));
		 xmgs.addAll(nbyclzlwtDao.getXmgs(company, zts));
		 for (String gs : xmgs){
			 result.add(toList(
					 company.getName(),
					 gs,
					 MathUtil.sum(
							 wbyclzlwtDao.getSczzzlqkxxxxCount(SeasonUtil.getStart(ec).getDate(), ec.getSeasonEnd().getDate(), company, gs, zts), 
							 nbyclzlwtDao.getSczzzlqkxxxxCount(SeasonUtil.getStart(ec).getDate(), ec.getSeasonEnd().getDate(), company, gs, zts)),
					 MathUtil.sum(
							 wbyclzlwtDao.getSczzzlqkxxxxCount(SeasonUtil.getStart(ec.getLastYear()).getDate(), ec.getLastYear().getSeasonEnd().getDate(), company, gs, zts), 
							 nbyclzlwtDao.getSczzzlqkxxxxCount(SeasonUtil.getStart(ec.getLastYear()).getDate(), ec.getLastYear().getSeasonEnd().getDate(), company, gs, zts))));
		 }
		return result;
	}

	@Override
	public List<List<String>> getJdsjzlqk(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getSjzlqkCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps.get(i), zts),
					wbyclzlwtDao.getSjzlqkCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps.get(i), zts),
					nbyclzlwtDao.getSjzlqkCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i), zts),
					wbyclzlwtDao.getSjzlqkCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i), zts)));
		}
		result.add(toList(
				"合计",
				nbyclzlwtDao.getSjzlqkCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps, zts),
				wbyclzlwtDao.getSjzlqkCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps, zts),
				nbyclzlwtDao.getSjzlqkCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps, zts),
				wbyclzlwtDao.getSjzlqkCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps, zts)));
		return result;
	}

	@Override
	public List<List<String>> getJdsjzlqk(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList("当期", 
				nbyclzlwtDao.getSjzlqkCount(ec.getSeasonEnd().getDate(), d, company, zts),
				wbyclzlwtDao.getSjzlqkCount(ec.getSeasonEnd().getDate(), d, company, zts)));
		result.add(toList("去年同期", 
				nbyclzlwtDao.getSjzlqkCount(ec.getLastYear().getSeasonEnd().getDate(), d, company, zts),
				wbyclzlwtDao.getSjzlqkCount(ec.getLastYear().getSeasonEnd().getDate(), d, company, zts)));
		return result;
	}

	@Override
	public List<List<String>> getJdYclzlwt(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		Date seasonStart = SeasonUtil.getStart(cal).getDate();
		Date seasonEnd = cal.getSeasonEnd().getDate();
		Date qnSeasonStart = SeasonUtil.getStart(cal.getLastYear()).getDate();
		Date qnSeasonEnd = cal.getLastYear().getSeasonEnd().getDate();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getYclzlRcjcwtCount(seasonStart, seasonEnd, comps.get(i), zts),
					nbyclzlwtDao.getYclzlCnzzwtCount(seasonStart, seasonEnd, comps.get(i), zts),
					wbyclzlwtDao.getYclzlCwxcwtCount(seasonStart, seasonEnd, comps.get(i), zts), 
					nbyclzlwtDao.getYclzlRcjcwtCount(qnSeasonStart, seasonEnd, comps.get(i), zts),
					nbyclzlwtDao.getYclzlCnzzwtCount(qnSeasonStart, seasonEnd, comps.get(i), zts),
					wbyclzlwtDao.getYclzlCwxcwtCount(qnSeasonStart, qnSeasonEnd, comps.get(i), zts)));
		}
		result.add(toList(
				"合计",
				nbyclzlwtDao.getYclzlRcjcwtCount(seasonStart, seasonEnd, comps, zts),
				nbyclzlwtDao.getYclzlCnzzwtCount(seasonStart, seasonEnd, comps, zts),
				wbyclzlwtDao.getYclzlCwxcwtCount(seasonStart, seasonEnd, comps, zts), 
				nbyclzlwtDao.getYclzlRcjcwtCount(qnSeasonStart, seasonEnd, comps, zts),
				nbyclzlwtDao.getYclzlCnzzwtCount(qnSeasonStart, seasonEnd, comps, zts),
				wbyclzlwtDao.getYclzlCwxcwtCount(qnSeasonStart, qnSeasonEnd, comps, zts)));
		return result;
	}

	@Override
	public List<List<String>> getJdYclzlwt(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList("当期",
				nbyclzlwtDao.getYclzlRcjcwtCount(ec.getLastYear().getSeasonEnd().getDate(), company, zts),
				nbyclzlwtDao.getYclzlCnzzwtCount(ec.getLastYear().getSeasonEnd().getDate(), company, zts),
				wbyclzlwtDao.getYclzlCwxcwtCount(ec.getLastYear().getSeasonEnd().getDate(), company, zts))); 
		result.add(toList("去年同期", 
				nbyclzlwtDao.getYclzlRcjcwtCount(ec.getLastYear().getLastYear().getSeasonEnd().getDate(), company, zts),
				nbyclzlwtDao.getYclzlCnzzwtCount(ec.getLastYear().getLastYear().getSeasonEnd().getDate(), company, zts),
				wbyclzlwtDao.getYclzlCwxcwtCount(ec.getLastYear().getLastYear().getSeasonEnd().getDate(), company, zts)));
		return result;
	}

	@Override
	public List<List<String>> getJdYsazzlwt(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					MathUtil.sum(
							nbyclzlwtDao.getYsazzlwtCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps.get(i), zts),
							wbyclzlwtDao.getYsazzlwtCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps.get(i), zts)),
					MathUtil.sum(
							nbyclzlwtDao.getYsazzlwtCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i), zts),
							wbyclzlwtDao.getYsazzlwtCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps.get(i), zts))));
		}
		result.add(toList(
				"合计",
				MathUtil.sum(
						nbyclzlwtDao.getYsazzlwtCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps, zts),
						wbyclzlwtDao.getYsazzlwtCount(SeasonUtil.getStart(cal).getDate(), cal.getSeasonEnd().getDate(), comps, zts)),
				MathUtil.sum(
						nbyclzlwtDao.getYsazzlwtCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps, zts),
						wbyclzlwtDao.getYsazzlwtCount(SeasonUtil.getStart(cal.getLastYear()).getDate(), cal.getLastYear().getSeasonEnd().getDate(), comps, zts))));
		return result;
	}

	@Override
	public List<List<String>> getJdYsazzlwt(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList(company.getName(), 
				MathUtil.sum(
						nbyclzlwtDao.getYsazzlwtCount(ec.getSeasonEnd().getDate(), d, company, zts),
						wbyclzlwtDao.getYsazzlwtCount(ec.getSeasonEnd().getDate(), d, company, zts)),
				MathUtil.sum(
						nbyclzlwtDao.getYsazzlwtCount(ec.getLastYear().getSeasonEnd().getDate(), d, company, zts),
						wbyclzlwtDao.getYsazzlwtCount(ec.getLastYear().getSeasonEnd().getDate(), d, company, zts))));
		return result;
	}

	@Override
	public List<WaveItem> getSjzlqkWaveItems(Date d, YDJDType yjType, List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		if (yjType == YDJDType.YD){
			List<Company> comps = new ArrayList<Company>();
			Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
			comps.add(xkgs);
			WaveItem nb = new WaveItem(xkgs.getName() + "内部质量问题", new ArrayList<String>());
			WaveItem wb = new WaveItem(xkgs.getName() + "外部质量问题", new ArrayList<String>());
			wis.add(nb);
			wis.add(wb);
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				nb.getData().add(
						nbyclzlwtDao.getSjzlqkCount(ec.getDate(), comps, zts) + "");
				wb.getData().add(
						wbyclzlwtDao.getSjzlqkCount(ec.getDate(), comps, zts) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getSjzlqkWaveItems(Date d, YDJDType yjType,
			Company company, List<Integer> zts) {
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
						nbyclzlwtDao.getSjzlqkCount(ec.getDate(), company, zts) + "");
				wiwb.getData().add(
						wbyclzlwtDao.getSjzlqkCount(ec.getDate(), company, zts) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItems(Date d, YDJDType yjType, List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		if (yjType == YDJDType.YD){
			List<Company> comps = new ArrayList<Company>();
			Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
			comps.add(xkgs);
			WaveItem nb = new WaveItem(xkgs.getName() + "内部质量问题", new ArrayList<String>());
			WaveItem wb = new WaveItem(xkgs.getName() + "外部质量问题", new ArrayList<String>());
			wis.add(nb);
			wis.add(wb);
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				nb.getData().add(
						nbyclzlwtDao.getCount(ec.getDate(), comps, zts) + "");
				wb.getData().add(
						wbyclzlwtDao.getCount(ec.getDate(), comps, zts) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItems(Date d, YDJDType yjType, Company company, List<Integer> zts) {
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
						nbyclzlwtDao.getCount(ec.getDate(), company, zts) + "");
				wiwb.getData().add(
						wbyclzlwtDao.getCount(ec.getDate(), company, zts) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItemsSczzzlqk(Date d, YDJDType yjType, List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		if (yjType == YDJDType.YD){
			List<Company> comps = new ArrayList<Company>();
			Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
			comps.add(xkgs);
			WaveItem nb = new WaveItem(xkgs.getName() + "内部质量问题", new ArrayList<String>());
			WaveItem wb = new WaveItem(xkgs.getName() + "外部质量问题", new ArrayList<String>());
			wis.add(nb);
			wis.add(wb);
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				nb.getData().add(
						nbyclzlwtDao.getSczzzlqkCount(ec.getDate(), comps, zts) + "");
				wb.getData().add(
						wbyclzlwtDao.getSczzzlqkCount(ec.getDate(), comps, zts) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItemsSczzzlqk(Date d, YDJDType yjType,
			Company company, List<Integer> zts) {
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
						nbyclzlwtDao.getSczzzlqkCount(ec.getDate(), company, zts) + "");
				wiwb.getData().add(
						wbyclzlwtDao.getSczzzlqkCount(ec.getDate(), company, zts) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItemsYclzlwt(Date d, YDJDType yjType, List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		if (yjType == YDJDType.YD){
			List<Company> comps = new ArrayList<Company>();
			Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
			comps.add(xkgs);
			WaveItem wirc = new WaveItem(xkgs.getName() + "入场检测反馈", new ArrayList<String>());
			WaveItem wicn = new WaveItem(xkgs.getName() + "厂内制造反馈", new ArrayList<String>());
			WaveItem wicw = new WaveItem(xkgs.getName() + "厂外现场反馈", new ArrayList<String>());
			wis.add(wirc);
			wis.add(wicn);
			wis.add(wicw);
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				wirc.getData().add(
						nbyclzlwtDao.getYclzlRcjcwtCount(ec.getDate(), comps, zts) + "");
				wicn.getData().add(
						nbyclzlwtDao.getYclzlCnzzwtCount(ec.getDate(), comps, zts) + "");
				wicw.getData().add(
						wbyclzlwtDao.getYclzlCwxcwtCount(ec.getDate(), comps, zts) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItemsYclzlwt(Date d, YDJDType yjType,
			Company company, List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		WaveItem wirc = new WaveItem(company.getName() + "入场检测反馈", new ArrayList<String>());
		WaveItem wicn = new WaveItem(company.getName() + "厂内制造反馈", new ArrayList<String>());
		WaveItem wicw = new WaveItem(company.getName() + "厂外现场反馈", new ArrayList<String>());
		wis.add(wirc);
		wis.add(wicn);
		wis.add(wicw);
		if (yjType == YDJDType.YD){
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				wirc.getData().add(
						nbyclzlwtDao.getYclzlRcjcwtCount(ec.getDate(), company, zts) + "");
				wicn.getData().add(
						nbyclzlwtDao.getYclzlCnzzwtCount(ec.getDate(), company, zts) + "");
				wicw.getData().add(
						wbyclzlwtDao.getYclzlCwxcwtCount(ec.getDate(), company, zts) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItemsYsazzlwt(Date d, YDJDType yjType, List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		if (yjType == YDJDType.YD){
			List<Company> comps = new ArrayList<Company>();
			Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
			comps.add(xkgs);
			WaveItem hj = new WaveItem(xkgs.getName() + "运输安装质量问题情况", new ArrayList<String>());
			wis.add(hj);
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				hj.getData().add(
						MathUtil.sum(
						nbyclzlwtDao.getYsazzlwtCount(ec.getDate(), comps, zts),
						wbyclzlwtDao.getYsazzlwtCount(ec.getDate(), comps, zts)) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}
	
	@Override
	public List<WaveItem> getWaveItemsYsazzlwt(Date d, YDJDType yjType,
			Company company, List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		WaveItem hj = new WaveItem(company.getName() + "运输安装质量问题情况", new ArrayList<String>());
		wis.add(hj);
		if (yjType == YDJDType.YD){
			EasyCalendar ec = new EasyCalendar(d);
			ec.setMonth(1);
			for (int i = 0; i < 12; ++i){
				hj.getData().add(
						MathUtil.sum(
								nbyclzlwtDao.getYsazzlwtCount(ec.getDate(), company, zts),
								wbyclzlwtDao.getYsazzlwtCount(ec.getDate(), company, zts)) + "");
				ec.addMonth(1);
			}
		}
		return wis;
	}
	
	@Override
	public List<List<String>> getYdnwbzlqk(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getCount(d, comps.get(i), zts),
					wbyclzlwtDao.getCount(d, comps.get(i), zts),
					nbyclzlwtDao.getCount(cal.getMonths(1).getDate(), d, comps.get(i), zts),
					wbyclzlwtDao.getCount(cal.getMonths(1).getDate(), d, comps.get(i), zts)));
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getCount(d, comps, zts),
				wbyclzlwtDao.getCount(d, comps, zts),
				nbyclzlwtDao.getCount(cal.getMonths(1).getDate(), d, comps, zts),
				wbyclzlwtDao.getCount(cal.getMonths(1).getDate(), d, comps, zts)));
		return result;
	}

	@Override
	public List<List<String>> getYdnwbzlqk(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList(ec.getMonthFormat(), 
				nbyclzlwtDao.getCount(d, company, zts),
				wbyclzlwtDao.getCount(d, company, zts)));
		result.add(toList("累计", 
				nbyclzlwtDao.getCount(ec.getMonths(1).getDate(), d, company, zts),
				wbyclzlwtDao.getCount(ec.getMonths(1).getDate(), d, company, zts)));
		return result;
	}

	@Override
	public List<List<String>> getYdSczzzlqk(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getSczzzlqkCount(d, comps.get(i), zts),
					wbyclzlwtDao.getSczzzlqkCount(d, comps.get(i), zts),
					nbyclzlwtDao.getSczzzlqkCount(cal.getMonths(1).getDate(), d, comps.get(i), zts),
					wbyclzlwtDao.getSczzzlqkCount(cal.getMonths(1).getDate(), d, comps.get(i), zts)));
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getSczzzlqkCount(d, comps, zts),
				wbyclzlwtDao.getSczzzlqkCount(d, comps, zts),
				nbyclzlwtDao.getSczzzlqkCount(cal.getMonths(1).getDate(), d, comps, zts),
				wbyclzlwtDao.getSczzzlqkCount(cal.getMonths(1).getDate(), d, comps, zts)));
		return result;
	}

	@Override
	public List<List<String>> getYdSczzzlqk(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList(ec.getMonthFormat(), 
				nbyclzlwtDao.getSczzzlqkCount(d, company, zts),
				wbyclzlwtDao.getSczzzlqkCount(d, company, zts)));
		result.add(toList("累计", 
				nbyclzlwtDao.getSczzzlqkCount(ec.getMonths(1).getDate(), d, company, zts),
				wbyclzlwtDao.getSczzzlqkCount(ec.getMonths(1).getDate(), d, company, zts)));
		return result;
	}

	@Override
	public List<List<String>> getYdSczzzlqkxxxx(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		List<List<String>> result = new ArrayList<List<String>>();
		Set<String> xmgs = new HashSet<String>();
		EasyCalendar ec = new EasyCalendar(d);
		for (int i = 0; i < comps.size(); ++i){
			 xmgs.addAll(wbyclzlwtDao.getXmgs(comps.get(i), zts));
			 xmgs.addAll(nbyclzlwtDao.getXmgs(comps.get(i), zts));
			 for (String gs : xmgs){
				 result.add(toList(
						 comps.get(i).getName(),
						 gs,
						 MathUtil.sum(
								 wbyclzlwtDao.getSczzzlqkxxxxCount(d, comps.get(i), gs, zts), 
								 nbyclzlwtDao.getSczzzlqkxxxxCount(d, comps.get(i), gs, zts)),
						 MathUtil.sum(
								 wbyclzlwtDao.getSczzzlqkxxxxCount(ec.getMonths(1).getDate(), d, comps.get(i), gs, zts), 
								 nbyclzlwtDao.getSczzzlqkxxxxCount(ec.getMonths(1).getDate(), d, comps.get(i), gs, zts))));
			 }
		}
		
		return result;
	}

	@Override
	public List<List<String>> getYdSczzzlqkxxxx(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		Set<String> xmgs = new HashSet<String>();
		EasyCalendar ec = new EasyCalendar(d);

		xmgs.addAll(wbyclzlwtDao.getXmgs(company, zts));
		xmgs.addAll(nbyclzlwtDao.getXmgs(company, zts));
		for (String gs : xmgs){
			 result.add(toList(
					 company.getName(),
					 gs,
					 MathUtil.sum(
							 wbyclzlwtDao.getSczzzlqkxxxxCount(d, company, gs, zts), 
							 nbyclzlwtDao.getSczzzlqkxxxxCount(d, company, gs, zts)),
					 MathUtil.sum(
							 wbyclzlwtDao.getSczzzlqkxxxxCount(ec.getMonths(1).getDate(), d, company, gs, zts), 
							 nbyclzlwtDao.getSczzzlqkxxxxCount(ec.getMonths(1).getDate(), d, company, gs, zts))));
			 }

		
		return result;
	}

	@Override
	public List<List<String>> getYdsjzlqk(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getSjzlqkCount(d, comps.get(i), zts),
					wbyclzlwtDao.getSjzlqkCount(d, comps.get(i), zts),
					nbyclzlwtDao.getSjzlqkCount(cal.getMonths(1).getDate(), d, comps.get(i), zts),
					wbyclzlwtDao.getSjzlqkCount(cal.getMonths(1).getDate(), d, comps.get(i), zts)));
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getSjzlqkCount(d, comps, zts),
				wbyclzlwtDao.getSjzlqkCount(d, comps, zts),
				nbyclzlwtDao.getSjzlqkCount(cal.getMonths(1).getDate(), d, comps, zts),
				wbyclzlwtDao.getSjzlqkCount(cal.getMonths(1).getDate(), d, comps, zts)));
		return result;
	}

	@Override
	public List<List<String>> getYdsjzlqk(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList(ec.getMonthFormat(), 
				nbyclzlwtDao.getSjzlqkCount(d, company, zts),
				wbyclzlwtDao.getSjzlqkCount(d, company, zts)));
		result.add(toList("累计", 
				nbyclzlwtDao.getSjzlqkCount(ec.getMonths(1).getDate(), d, company, zts),
				wbyclzlwtDao.getSjzlqkCount(ec.getMonths(1).getDate(), d, company, zts)));
		return result;
	}

	@Override
	public List<List<String>> getYdYclzlwt(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					nbyclzlwtDao.getYclzlRcjcwtCount(d, comps.get(i), zts),
					nbyclzlwtDao.getYclzlCnzzwtCount(d, comps.get(i), zts),
					wbyclzlwtDao.getYclzlCwxcwtCount(d, comps.get(i), zts), 
					nbyclzlwtDao.getYclzlRcjcwtCount(cal.getMonths(1).getDate(), d, comps.get(i), zts),
					nbyclzlwtDao.getYclzlCnzzwtCount(cal.getMonths(1).getDate(), d,  comps.get(i), zts),
					wbyclzlwtDao.getYclzlCwxcwtCount(cal.getMonths(1).getDate(), d,  comps.get(i), zts))); 
		}
		result.add(toList(
				"合计", 
				nbyclzlwtDao.getYclzlRcjcwtCount(d, comps, zts),
				nbyclzlwtDao.getYclzlCnzzwtCount(d, comps, zts),
				wbyclzlwtDao.getYclzlCwxcwtCount(d, comps, zts), 
				nbyclzlwtDao.getYclzlRcjcwtCount(cal.getMonths(1).getDate(), d,  comps, zts),
				nbyclzlwtDao.getYclzlCnzzwtCount(cal.getMonths(1).getDate(), d,  comps, zts),
				wbyclzlwtDao.getYclzlCwxcwtCount(cal.getMonths(1).getDate(), d,  comps, zts))); 
		return result;
	}

	@Override
	public List<List<String>> getYdYclzlwt(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList(ec.getMonthFormat(), 
				nbyclzlwtDao.getYclzlRcjcwtCount(d, company, zts),
				nbyclzlwtDao.getYclzlCnzzwtCount(d, company, zts),
				wbyclzlwtDao.getYclzlCwxcwtCount(d, company, zts))); 
		result.add(toList("累计", 
				nbyclzlwtDao.getYclzlRcjcwtCount(ec.getMonths(1).getDate(), d, company, zts),
				nbyclzlwtDao.getYclzlCnzzwtCount(ec.getMonths(1).getDate(), d, company, zts),
				wbyclzlwtDao.getYclzlCwxcwtCount(ec.getMonths(1).getDate(), d, company, zts))); 
		return result;
	}

	@Override
	public List<List<String>> getYdYsazzlwt(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		EasyCalendar cal = new EasyCalendar(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < comps.size(); ++i){
			result.add(toList(
					comps.get(i).getName(), 
					MathUtil.sum(
						nbyclzlwtDao.getYsazzlwtCount(d, comps.get(i), zts),
						wbyclzlwtDao.getYsazzlwtCount(d, comps.get(i), zts)),
					MathUtil.sum(
						nbyclzlwtDao.getYsazzlwtCount(cal.getMonths(1).getDate(), d, comps.get(i), zts),
						wbyclzlwtDao.getYsazzlwtCount(cal.getMonths(1).getDate(), d, comps.get(i), zts))));
		}
		result.add(toList(
				"合计", 
				MathUtil.sum(
					nbyclzlwtDao.getYsazzlwtCount(d, comps, zts),
					wbyclzlwtDao.getYsazzlwtCount(d, comps, zts)),
				MathUtil.sum(
					nbyclzlwtDao.getYsazzlwtCount(cal.getMonths(1).getDate(), d, comps, zts),
					wbyclzlwtDao.getYsazzlwtCount(cal.getMonths(1).getDate(), d, comps, zts))));
		return result;
	}

	@Override
	public List<List<String>> getYdYsazzlwt(Date d, Company company, List<Integer> zts) {
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		result.add(toList(company.getName(), 
				MathUtil.sum(
					nbyclzlwtDao.getYsazzlwtCount(d, company, zts),
					wbyclzlwtDao.getYsazzlwtCount(d, company, zts)),
				MathUtil.sum(
					nbyclzlwtDao.getYsazzlwtCount(ec.getMonths(1).getDate(), d, company, zts),
					wbyclzlwtDao.getYsazzlwtCount(ec.getMonths(1).getDate(), d, company, zts))));
		return result;
	}

	private List<String> toList(String compName, Integer dynb, Integer dywb) {
		List<String> result = new ArrayList<String>();
		result.add(compName);
		result.add(dynb + "");
		result.add(dywb + "");
		result.add(MathUtil.sum(dynb, dywb) + "");
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
		result.add(MathUtil.sum(dynb, dywb) + "");
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

	private List<String> toList(String jydw, String xmgs, Integer yd,
			Integer lj) {
		List<String> list = new ArrayList<String>();
		list.add(jydw);
		list.add(xmgs);
		list.add(yd + "");
		list.add(lj + "");
		return list;
	}

	@Override
	public List<List<String>> getJdNbzlwtfl(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		List<String> subIssues = nbyclzlwtDao.getIssues(comps, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		Integer hjdq = null;
		Integer hjqn = null;
		Integer tmp;
		for (String issue : subIssues){
			List<String> list = new ArrayList<String>();
			result.add(list);
			list.add(issue);
			for (Company comp : comps){
				tmp = nbyclzlwtDao.getNbzlwtflCount(
						SeasonUtil.getStart(ec).getDate(), ec.getSeasonEnd().getDate(), comp, issue, zts);
				hjdq = MathUtil.sum(tmp, hjdq);
				list.add(tmp + "");
				tmp = nbyclzlwtDao.getNbzlwtflCount(
						SeasonUtil.getStart(ec.getLastYear()).getDate(), ec.getLastYear().getSeasonEnd().getDate(), comp, issue, zts);
				hjqn = MathUtil.sum(tmp, hjqn);
				list.add(tmp + "");
			}
			list.add(hjdq + "");
			list.add(hjqn + "");
		}
		return result;
	}

	@Override
	public List<List<String>> getYdNbzlwtfl(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		List<String> subIssues = nbyclzlwtDao.getIssues(comps, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		Integer hjdq = null;
		Integer hjqn = null;
		Integer tmp;
		for (String issue : subIssues){
			List<String> list = new ArrayList<String>();
			result.add(list);
			list.add(issue);
			for (Company comp : comps){
				tmp = nbyclzlwtDao.getNbzlwtflCount(
						ec.getDate(), comp, issue, zts);
				hjdq = MathUtil.sum(tmp, hjdq);
				list.add(tmp + "");
				tmp = nbyclzlwtDao.getNbzlwtflCount(ec.getMonths(1).getDate(),
						ec.getDate(), comp, issue, zts);
				hjqn = MathUtil.sum(tmp, hjqn);
				list.add(tmp + "");
			}
			list.add(hjdq + "");
			list.add(hjqn + "");
		}
		return result;
	}

	@Override
	public List<List<String>> getJdNbzlwtfl(Date d, Company comp, List<Integer> zts) {
		List<String> subIssues = nbyclzlwtDao.getIssues(comp, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		Integer tmp;
		for (String issue : subIssues){
			List<String> list = new ArrayList<String>();
			result.add(list);
			list.add(issue);
			tmp = nbyclzlwtDao.getNbzlwtflCount(
					SeasonUtil.getStart(ec).getDate(), 
					ec.getSeasonEnd().getDate(), 
					comp, 
					issue, zts);
			list.add(tmp + "");
			tmp = nbyclzlwtDao.getNbzlwtflCount(
					SeasonUtil.getStart(ec.getLastYear()).getDate(), 
					ec.getLastYear().getSeasonEnd().getDate(), 
					comp, 
					issue, zts);
			list.add(tmp + "");
		}
		return result;
	}

	@Override
	public List<List<String>> getYdNbzlwtfl(Date d, Company comp, List<Integer> zts) {
		List<String> subIssues = nbyclzlwtDao.getIssues(comp, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		Integer tmp;
		for (String issue : subIssues){
			List<String> list = new ArrayList<String>();
			result.add(list);
			list.add(issue);
			tmp = nbyclzlwtDao.getNbzlwtflCount(
					ec.getDate(), comp, issue, zts);
			list.add(tmp + "");
			tmp = nbyclzlwtDao.getNbzlwtflCount(ec.getMonths(1).getDate(),
					ec.getDate(), comp, issue, zts);
			list.add(tmp + "");
		}
		return result;
	}

	@Override
	public List<List<String>> getJdWbzlwtfl(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		List<String> subIssues = wbyclzlwtDao.getIssues(comps, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		Integer hjdq = null;
		Integer hjqn = null;
		Integer tmp;
		for (String issue : subIssues){
			List<String> list = new ArrayList<String>();
			result.add(list);
			list.add(issue);
			for (Company comp : comps){
				tmp = wbyclzlwtDao.getWbzlwtflCount(
						SeasonUtil.getStart(ec).getDate(), ec.getSeasonEnd().getDate(), comp, issue, zts);
				hjdq = MathUtil.sum(tmp, hjdq);
				list.add(tmp + "");
				tmp = wbyclzlwtDao.getWbzlwtflCount(
						SeasonUtil.getStart(ec.getLastYear()).getDate(), ec.getLastYear().getSeasonEnd().getDate(), comp, issue, zts);
				hjqn = MathUtil.sum(tmp, hjqn);
				list.add(tmp + "");
			}
			list.add(hjdq + "");
			list.add(hjqn + "");
		}
		return result;
	}

	@Override
	public List<List<String>> getYdWbzlwtfl(Date d, List<Integer> zts) {
		List<Company> comps = new ArrayList<Company>();
		Company xkgs = companyManager.getVirtualCYOrg().getCompany(CompanyType.XKGS);
		comps.add(xkgs);
		List<String> subIssues = wbyclzlwtDao.getIssues(comps, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		Integer hjdq = null;
		Integer hjqn = null;
		Integer tmp;
		for (String issue : subIssues){
			List<String> list = new ArrayList<String>();
			result.add(list);
			list.add(issue);
			for (Company comp : comps){
				tmp = wbyclzlwtDao.getWbzlwtflCount(
						ec.getDate(), comp, issue, zts);
				hjdq = MathUtil.sum(tmp, hjdq);
				list.add(tmp + "");
				tmp = wbyclzlwtDao.getWbzlwtflCount(ec.getMonths(1).getDate(),
						ec.getDate(), comp, issue, zts);
				hjqn = MathUtil.sum(tmp, hjqn);
				list.add(tmp + "");
			}
			list.add(hjdq + "");
			list.add(hjqn + "");
		}
		return result;
	}

	@Override
	public List<List<String>> getJdWbzlwtfl(Date d, Company comp, List<Integer> zts) {
		List<String> subIssues = wbyclzlwtDao.getIssues(comp, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		Integer tmp;
		for (String issue : subIssues){
			List<String> list = new ArrayList<String>();
			result.add(list);
			list.add(issue);
			tmp = wbyclzlwtDao.getWbzlwtflCount(
					SeasonUtil.getStart(ec).getDate(), 
					ec.getSeasonEnd().getDate(), 
					comp, 
					issue, zts);
			list.add(tmp + "");
			tmp = wbyclzlwtDao.getWbzlwtflCount(
					SeasonUtil.getStart(ec.getLastYear()).getDate(), 
					ec.getLastYear().getSeasonEnd().getDate(), 
					comp, 
					issue, zts);
			list.add(tmp + "");
		}
		return result;
	}

	@Override
	public List<List<String>> getYdWbzlwtfl(Date d, Company comp, List<Integer> zts) {
		List<String> subIssues = wbyclzlwtDao.getIssues(comp, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		EasyCalendar ec = new EasyCalendar(d);
		Integer tmp;
		for (String issue : subIssues){
			List<String> list = new ArrayList<String>();
			result.add(list);
			list.add(issue);
			tmp = wbyclzlwtDao.getWbzlwtflCount(
					ec.getDate(), comp, issue, zts);
			list.add(tmp + "");
			tmp = wbyclzlwtDao.getWbzlwtflCount(ec.getMonths(1).getDate(),
					ec.getDate(), comp, issue, zts);
			list.add(tmp + "");
		}
		return result;
	}

	@Override
	public List<List<String>> getNbzlwttjqk(Date d, Company company, List<Integer> zts) {
		List<NbyclzlwtEntity> entities = nbyclzlwtDao.getAll(d, company, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		for (NbyclzlwtEntity entity : entities){
			result.add(toList(entity));
		}
		return result;
	}

	private List<String> toList(NbyclzlwtEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add(entity.getCompany_name());
		list.add(Util.formatToDay(entity.getIssue_happen_date()));
		list.add(entity.getProduct_type());
		list.add(entity.getProduction_num());
		list.add(entity.getProduction_model());
		list.add(entity.getIssue_type());
		list.add(entity.getSub_issue_type());
		//list.add(entity.getCategory_code());
		list.add(entity.getMaterial_quality_phenomenon());
		list.add(entity.getDetail());
		list.add(entity.getMaterial_happen_phase());
		list.add(entity.getMaterial_count() + "");
		list.add(entity.getMeasurement_units());
		list.add(entity.getSuppier());
		list.add(entity.getIssue_process());
		list.add(entity.getResponsibility_department());
		list.add(entity.getMaterial_treatment_measure());
		list.add(entity.getOnsite_treatment_measure());
		list.add(entity.getOnsite_treatment_result());
		list.add(entity.getCausa_analysis());
		list.add(entity.getAssessment());
		list.add(entity.getFilling_personnel());
		return list;
	}

	@Override
	public List<List<String>> getWbzlwttjqk(Date d, Company company, List<Integer> zts) {
		List<WbyclzlwtEntity> entities = wbyclzlwtDao.getAll(d, company, zts);
		List<List<String>> result = new ArrayList<List<String>>();
		for (WbyclzlwtEntity entity : entities){
			result.add(toList(entity));
		}
		return result;
	}

	private List<String> toList(WbyclzlwtEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add(entity.getCompany_name());
		list.add(Util.formatToDay(entity.getIssue_happen_date()));
		list.add(entity.getProduct_type());
		list.add(entity.getProduction_num());
		list.add(entity.getProduction_model());
		list.add(entity.getIssue_type());
		list.add(entity.getSub_issue_type());
		//list.add(entity.getCategory_code());
		list.add(entity.getQuality_phenomenon());
		list.add(entity.getDetail());
		list.add("" + entity.getMaterial_count());
		list.add(entity.getMeasurement_units());
		list.add(entity.getSuppier());
		list.add(entity.getResponsibility_department());
		list.add(entity.getFilling_personnel());
		list.add(Util.formatToDay(entity.getProduct_delivery_date()));
		list.add(entity.getFailure_subject());
		list.add(entity.getMaterial_treatment_measure());
		list.add(entity.getOnsite_treatment_measure());
		list.add(entity.getOnsite_treatment_result());
		list.add(entity.getUser_unit());
		list.add(entity.getOnsite_after_sales());
		list.add(entity.getAfter_sales_tel());
		return list;
	}


}
