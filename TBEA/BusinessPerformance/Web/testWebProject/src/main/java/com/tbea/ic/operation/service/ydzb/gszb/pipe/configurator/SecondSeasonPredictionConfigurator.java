package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.NjhzbAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.SjzbAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.YjhzbAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccSbdPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.CopyPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.SpecialPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;

public class SecondSeasonPredictionConfigurator implements IPipeConfigurator {

	NDJHZBDao ndjhzbDao;

	YDJHZBDao ydjhzbDao;

	YDZBZTDao ydzbztDao;

	SJZBDao sjzbDao;

	YJ20ZBDao yj20zbDao;

	YJ28ZBDao yj28zbDao;

	ZBXXDao zbxxDao;

	CompanyManager companyManager;

	static List<Integer> specialZbs = new ArrayList<Integer>();
	static {
		specialZbs.add(GSZB.YSZK.getValue());
		specialZbs.add(GSZB.CH.getValue());
		specialZbs.add(GSZB.RJLR.getValue());
		specialZbs.add(GSZB.RJSR.getValue());
		specialZbs.add(GSZB.SXFYL.getValue());
		specialZbs.add(GSZB.RS.getValue());
	}

	public SecondSeasonPredictionConfigurator(NDJHZBDao ndjhzbDao, YDJHZBDao ydjhzbDao,
			YDZBZTDao ydzbztDao, SJZBDao sjzbDao, YJ20ZBDao yj20zbDao,
			YJ28ZBDao yj28zbDao, ZBXXDao zbxxDao, CompanyManager companyManager) {
		this.ndjhzbDao = ndjhzbDao;
		this.ydjhzbDao = ydjhzbDao;
		this.ydzbztDao = ydzbztDao;
		this.sjzbDao = sjzbDao;
		this.yj20zbDao = yj20zbDao;
		this.yj28zbDao = yj28zbDao;
		this.zbxxDao = zbxxDao;
		this.companyManager = companyManager;
	}

	private List<Company> getNonSbdCompany(List<Company> companies) {
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

	private List<Company> getSbdCompany(List<Company> companies) {
		Organization org = companyManager.getBMDBOrganization();
		Company sbd = org.getCompany(CompanyType.SBDCYJT);
		List<Company> retComps = new ArrayList<Company>();
		for (Company comp : companies) {
			if (sbd.contains(comp)) {
				retComps.add(comp);
			}
		}
		return retComps;
	}

	@Override
	public void onConfiguring(GszbPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Company> nonSbdCompanies = getNonSbdCompany(allCompanies);
		List<Company> sbdCompanies = getSbdCompany(allCompanies);

		DateHelper dh = new DateHelper(pipe.getDate());
		IAccumulator sjAcc = new SjzbAccumulator(sjzbDao, yj20zbDao, yj28zbDao,
				ydzbztDao);
		IAccumulator yjhAcc = new YjhzbAccumulator(ydjhzbDao);
		IAccumulator njhAcc = new NjhzbAccumulator(ndjhzbDao);

		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();

		// 全年计划
		pipe.add(new AccPipeFilter(njhAcc, 0).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs).include(GSZB.RS)
				.include(GSZB.YSZK).include(GSZB.CH));
		
		

		// 当月计划
		if (sbdCompanies.isEmpty()) {
			pipe.add(new AccPipeFilter(yjhAcc, 2)
					.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
					.excludeZbs(specialZbs).include(GSZB.RS).include(GSZB.YSZK)
					.include(GSZB.CH));
		} else {
			pipe.add(
					new AccPipeFilter(yjhAcc, 2).includeCompanies(allCompanies)
							.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs)
							.include(GSZB.RS)).add(
					new AccSbdPipeFilter(yjhAcc, 2)
							.includeCompanies(sbdCompanies).include(GSZB.YSZK)
							.include(GSZB.CH));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.add(new AccPipeFilter(yjhAcc, 2)
						.includeCompanies(nonSbdCompanies).include(GSZB.YSZK)
						.include(GSZB.CH));
			}
		}
		// 季度计划
		pipe.add(new AccPipeFilter(yjhAcc, 1, dh.getJdStart(), dh.getCur())
				.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
				.excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 2, 1).add(GSZB.YSZK, 2, 1)
				.add(GSZB.RS, 2, 1))

		// 当月实际
		.add(
		new AccPipeFilter(sjAcc, 3).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs)
				.include(GSZB.YSZK).include(GSZB.CH).include(GSZB.RS))

		// 计划完成率
		.add(wclFilter.add(4, 3, 2))

		// 去年同期
		.add(new AccPipeFilter(sjAcc, 5, dh.getQntq())
				.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
				.excludeZbs(specialZbs).include(GSZB.YSZK)
				.include(GSZB.CH).include(GSZB.RS))

		// 同比增幅
		.add(tbzzFilter.add(6, 3, 5))
		
		
		// 季度累计
		.add(new AccPipeFilter(sjAcc, 7, dh.getJdStart(), dh.getCur())
				.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
				.excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 3, 7).add(GSZB.YSZK, 3, 7)
				.add(GSZB.RS, 3, 7))

		// 季度计划完成率
		.add(wclFilter.add(8, 7, 1))

		// 季度去年同期
		.add(new AccPipeFilter(sjAcc, 9, dh.getQntqJdStart(), dh
				.getQntq()).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
		.add(new AccPipeFilter(sjAcc, 9, dh.getQntq())
				.includeCompanies(allCompanies).include(GSZB.YSZK)
				.include(GSZB.CH).include(GSZB.RS))

		// 同比增幅
		.add(tbzzFilter.add(10, 7, 9))
		
		
		//末月预计
		.add(new AccPipeFilter(sjAcc, 11, dh.getLastMonthinSeason())
		.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
		.excludeZbs(specialZbs).include(GSZB.YSZK)
		.include(GSZB.CH).include(GSZB.RS))
		
		// 季度预计合计
		.add(new AccPipeFilter(sjAcc, 12, dh.getCur(), dh.getLastMonthinSeason())
				.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
				.excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 11, 12).add(GSZB.YSZK, 11, 12)
				.add(GSZB.RS, 11, 12))

		// 季度预计完成率
		.add(wclFilter.add(13, 12, 1))

		// 季度去年同期
		.add(new AccPipeFilter(sjAcc, 14, dh.getQntqJdStart(), dh
				.getQntqJdEnd()).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
		.add(new AccPipeFilter(sjAcc, 14, dh.getQntqJdStart())
				.includeCompanies(allCompanies).include(GSZB.YSZK)
				.include(GSZB.CH).include(GSZB.RS))

		// 同比增幅
		.add(tbzzFilter.add(15, 12, 14))

		// 年度累计
		.add(new AccPipeFilter(sjAcc, 16, dh.getFirstMonth(), dh
				.getCur()).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 3, 16).add(GSZB.YSZK, 3, 16)
				.add(GSZB.RS, 3, 16))

		// 累计计划完成率
		.add(wclFilter.add(17, 16, 0))

		// 去年同期
		.add(new AccPipeFilter(sjAcc, 18, dh.getQnfirstMonth(), dh
				.getQntq()).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 5, 18).add(GSZB.YSZK, 5, 18)
				.add(GSZB.RS, 5, 18))

		// 同比增幅
		.add(tbzzFilter.add(19, 16, 18))

		// 添加特殊指标过滤器
		.add(new SpecialPipeFilter().exclude(4)// 当月计划完成率
				.exclude(6)// 当月同比增幅
				.exclude(8)// 季度累计完成率
				.exclude(10)// 季度累计同比增幅
				.exclude(13)// 季度预计累计计划完成率
				.exclude(15)// 季度预计同比增幅
				.exclude(17)// 年度累计计划完成率
				.exclude(19))// 年度同比增幅
		.add(tbzzFilter).add(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 20;
	}

}
