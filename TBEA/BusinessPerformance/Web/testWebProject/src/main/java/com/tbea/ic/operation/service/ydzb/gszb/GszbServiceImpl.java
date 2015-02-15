package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.service.ydzb.gszb.acc.NjhzbAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.YjhzbAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.CopyPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.SpecialPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.TbzzPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.acc.*;

@Service
@Transactional("transactionManager")
public class GszbServiceImpl implements GszbService {

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

	@Autowired
	ZBXXDao zbxxDao;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	private static List<Integer> gsztzbs = new ArrayList<Integer>();
	static {
		gsztzbs.add(GSZB.LRZE.getValue());
		gsztzbs.add(GSZB.XSSR.getValue());
		gsztzbs.add(GSZB.JYXJXJL.getValue());
		gsztzbs.add(GSZB.YSZK.getValue());
		gsztzbs.add(GSZB.QZYQK.getValue());
		gsztzbs.add(GSZB.BL.getValue());
		gsztzbs.add(GSZB.CH.getValue());
		gsztzbs.add(GSZB.QZJYWY.getValue());
		gsztzbs.add(GSZB.HTQYE.getValue());
		gsztzbs.add(GSZB.ZJHL.getValue());
		gsztzbs.add(GSZB.BHSCZ.getValue());
		gsztzbs.add(GSZB.RS.getValue());
		gsztzbs.add(GSZB.RJLR.getValue());
		gsztzbs.add(GSZB.RJSR.getValue());
		gsztzbs.add(GSZB.SXFY.getValue());
		gsztzbs.add(GSZB.SXFYL.getValue());
		gsztzbs.add(GSZB.JZCSYL.getValue());
	}

	private static Map<Integer, ZBXX> zbxxMap = new HashMap<Integer, ZBXX>();

	private ZBXX getZbxx(Integer zbId) {
		if (zbxxMap.isEmpty()) {
			List<ZBXX> zbxxs = zbxxDao.getZbs(gsztzbs);
			for (ZBXX zbxx : zbxxs) {
				zbxxMap.put(zbxx.getId(), zbxx);
			}
		}
		return zbxxMap.get(zbId);
	}

	private List<Company> filterCompany(List<Company> companies) {
		List<Company> retComps = new ArrayList<Company>();
		for (Company comp : companies) {
			if (!comp.getSubCompanys().isEmpty()) {
				retComps.addAll(comp.getSubCompanys());
			}
		}
		return retComps;
	}

	
	private List<Company> getNonSbdCompany(List<Company> companies){
		Organization org = companyManager.getBMDBOrganization();
		Company sbd = org.getCompany(CompanyType.SBDCYJT);
		List<Company> retComps = new ArrayList<Company>();
		for (Company comp : companies) {
			if (!sbd.contains(comp)) {
				retComps.add(comp);
			}
		}
		return retComps;
	}
	
	private List<String[]> makeResult(List<Double[]> gszbs){

		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0, len = gsztzbs.size(); i < len; ++i) {
			String[] zbRow = new String[16];
			Double[] gszbRow = gszbs.get(i);
			zbRow[0] = getZbxx(gsztzbs.get(i)).getName();
			for (int j = zbRow.length - 1; j > 0; --j) {
				if (null != gszbRow[j - 1]) {
					zbRow[j] = gszbRow[j - 1] + "";
				}
			}
			result.add(zbRow);
		}
		return result;
	}
	
	@Override
	public List<String[]> getGsztzb(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -(cal.get(Calendar.MONTH) + 1) % 3 + 1);
		Date seasonStart = Util.toDate(cal);
		
		cal.setTime(seasonStart);
		cal.add(Calendar.YEAR, -1);
		Date qntqSeason = Util.toDate(cal);
		
		cal.setTime(date);
		cal.add(Calendar.YEAR, -1);
		Date qntq = Util.toDate(cal);
		
		cal.setTime(date);
		cal.set(Calendar.MONTH, 0);
		Date firstMonth = Util.toDate(cal);
		
		cal.setTime(qntq);
		cal.set(Calendar.MONTH, 0);
		Date qnfirstMonth = Util.toDate(cal);

		List<Company> allCompanies = filterCompany(org.getCompany(CompanyType.GFGS).getSubCompanys());
		List<Company> nonSbdCompanies = getNonSbdCompany(allCompanies);
		List<Company> sbdCompanies = org.getCompany(CompanyType.SBDCYJT).getSubCompanys();
		
		List<Integer> specialZbs = new ArrayList<Integer>();
		specialZbs.add(GSZB.YSZK.getValue());
		specialZbs.add(GSZB.CH.getValue());
		specialZbs.add(GSZB.RJLR.getValue());
		specialZbs.add(GSZB.RJSR.getValue());
		specialZbs.add(GSZB.SXFYL.getValue());
		specialZbs.add(GSZB.RS.getValue());
		
		GszbPipe pipe = new GszbPipe(gsztzbs, 15, allCompanies, date);
		
		IAccumulator sjAcc = new SjzbAccumulator(sjzbDao, yj20zbDao, yj28zbDao, ydzbztDao);
		IAccumulator yjhAcc = new YjhzbAccumulator(ydjhzbDao);
		IAccumulator njhAcc = new NjhzbAccumulator(ndjhzbDao);
		
		WclPipeFilter wclFilter = new WclPipeFilter();
		TbzzPipeFilter tbzzFilter = new TbzzPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();
		
			// 全年计划
		pipe.add(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(specialZbs)
				.include(GSZB.RS)
				.include(GSZB.YSZK)
				.include(GSZB.CH))
				
			// 当月计划
			.add(new AccPipeFilter(yjhAcc, 1)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(specialZbs)
				.include(GSZB.RS))
			.add(new AccPipeFilter(yjhAcc, 1)
				.includeCompanies(nonSbdCompanies)
				.include(GSZB.YSZK)
				.include(GSZB.CH))
			.add(new AccPipeFilter(yjhAcc, 1)
				.includeCompanies(sbdCompanies)
				.include(GSZB.YSZK)
				.include(GSZB.CH))

			// 当月实际
			.add(new AccPipeFilter(sjAcc, 2)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(specialZbs)
				.include(GSZB.YSZK)
				.include(GSZB.CH)
				.include(GSZB.RS))
			
			// 计划完成率
			.add(wclFilter
				.add(3, 2, 1))
			
			// 去年同期	
			.add(new AccPipeFilter(sjAcc, 4, qntq)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(specialZbs)
				.include(GSZB.YSZK)
				.include(GSZB.CH)
				.include(GSZB.RS))
			
			// 同比增幅
			.add(tbzzFilter
				.add(5, 2, 4))
				
			// 季度计划
			.add(new AccPipeFilter(yjhAcc, 6, seasonStart, date)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(specialZbs))
			.add(copyFilter
				.add(GSZB.CH, 1, 6)
				.add(GSZB.YSZK, 1, 6)
				.add(GSZB.RS, 1, 6))
				
			// 季度累计
			.add(new AccPipeFilter(sjAcc, 7, seasonStart, date)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(specialZbs))
			.add(copyFilter
				.add(GSZB.CH, 2, 7)
				.add(GSZB.YSZK, 2, 7)
				.add(GSZB.RS, 2, 7))
				
			// 季度计划完成率
			.add(wclFilter
				.add(8, 7, 6))
				
			// 季度去年同期
			.add(new AccPipeFilter(sjAcc, 9, qntqSeason, qntq)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(specialZbs))
			.add(new AccPipeFilter(sjAcc, 9, qntq)
				.includeCompanies(allCompanies)
				.include(GSZB.YSZK)
				.include(GSZB.CH)
				.include(GSZB.RS))
			
			 // 同比增幅
			.add(tbzzFilter
				.add(10, 7, 9))
	
			// 年度累计
			.add(new AccPipeFilter(sjAcc, 11, firstMonth, date)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(specialZbs))
			.add(copyFilter
				.add(GSZB.CH, 2, 11)
				.add(GSZB.YSZK, 2, 11)
				.add(GSZB.RS, 2, 11))
			
		
			// 累计计划完成率
			.add(wclFilter
				.add(12, 11, 0))
			
			// 去年同期
			.add(new AccPipeFilter(sjAcc, 13, qnfirstMonth, qntq)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(specialZbs))
			.add(copyFilter
				.add(GSZB.CH, 9, 13)
				.add(GSZB.YSZK, 9, 13)
				.add(GSZB.RS, 9, 13))
	
			// 同比增幅
			.add(tbzzFilter
				.add(14, 11, 13))

			//添加特殊指标过滤器
			.add(new SpecialPipeFilter()
				.exclude(3)// 计划完成率
				.exclude(5)// 同比增幅
				.exclude(8)// 季度计划完成率
				.exclude(10)// 同比增幅
				.exclude(12)// 累计计划完成率
				.exclude(14))// 同比增幅
			.add(tbzzFilter)
			.add(wclFilter);
		
		return makeResult(pipe.getGszb());
	}

}
