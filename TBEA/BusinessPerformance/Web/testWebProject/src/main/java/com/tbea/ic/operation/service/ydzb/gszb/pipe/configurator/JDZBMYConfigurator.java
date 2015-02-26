package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.TbzzPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;

public class JDZBMYConfigurator implements IPipeConfigurator {

	NDJHZBDao ndjhzbDao;

	YDJHZBDao ydjhzbDao;

	YDZBZTDao ydzbztDao;

	SJZBDao sjzbDao;

	YJ20ZBDao yj20zbDao;

	YJ28ZBDao yj28zbDao;

	ZBXXDao zbxxDao;

	CompanyManager companyManager;

	static List<Integer> gsztzbs = new ArrayList<Integer>();
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

	static List<Integer> specialZbs = new ArrayList<Integer>();
	static {
		specialZbs.add(GSZB.YSZK.getValue());
		specialZbs.add(GSZB.CH.getValue());
		specialZbs.add(GSZB.RJLR.getValue());
		specialZbs.add(GSZB.RJSR.getValue());
		specialZbs.add(GSZB.SXFYL.getValue());
		specialZbs.add(GSZB.RS.getValue());
	}

	public JDZBMYConfigurator(NDJHZBDao ndjhzbDao, YDJHZBDao ydjhzbDao,
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
		TbzzPipeFilter tbzzFilter = new TbzzPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();

		// 全年计划
		pipe.add(new AccPipeFilter(njhAcc, 0).includeCompanies(allCompanies)
				.includeZbs(gsztzbs).excludeZbs(specialZbs).include(GSZB.RS)
				.include(GSZB.YSZK).include(GSZB.CH));
		// 下季度全年计划
		pipe.add(new AccPipeFilter(njhAcc, 25, dh.getXjdLastMonth())
				.includeCompanies(allCompanies).includeZbs(gsztzbs)
				.excludeZbs(specialZbs).include(GSZB.RS).include(GSZB.YSZK)
				.include(GSZB.CH));

		// 当月计划
		if (sbdCompanies.isEmpty()) {
			pipe.add(new AccPipeFilter(yjhAcc, 3)
					.includeCompanies(allCompanies).includeZbs(gsztzbs)
					.excludeZbs(specialZbs).include(GSZB.RS).include(GSZB.YSZK)
					.include(GSZB.CH));
		} else {
			pipe.add(
					new AccPipeFilter(yjhAcc, 3).includeCompanies(allCompanies)
							.includeZbs(gsztzbs).excludeZbs(specialZbs)
							.include(GSZB.RS)).add(
					new AccSbdPipeFilter(yjhAcc, 3)
							.includeCompanies(sbdCompanies).include(GSZB.YSZK)
							.include(GSZB.CH));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.add(new AccPipeFilter(yjhAcc, 3)
						.includeCompanies(nonSbdCompanies).include(GSZB.YSZK)
						.include(GSZB.CH));
			}
		}

		// 当月实际
		pipe.add(
				new AccPipeFilter(sjAcc, 4).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs)
						.include(GSZB.YSZK).include(GSZB.CH).include(GSZB.RS))

				// 计划完成率
				.add(wclFilter.add(5, 4, 3))

				// 去年同期
				.add(new AccPipeFilter(sjAcc, 6, dh.getQntq())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 同比增幅
				.add(tbzzFilter.add(7, 4, 6))

				// 季度计划
				.add(new AccPipeFilter(yjhAcc, 1, dh.getJdStart(), dh.getCur())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 3, 1).add(GSZB.YSZK, 3, 1)
						.add(GSZB.RS, 3, 1))

				// 下季度计划
				.add(new AccPipeFilter(yjhAcc, 2, dh.getXjdFirstMonth(), dh
						.getXjdLastMonth()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(new AccPipeFilter(yjhAcc, 2, dh.getXjdLastMonth())
						.includeCompanies(allCompanies).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 季度累计
				.add(new AccPipeFilter(sjAcc, 8, dh.getJdStart(), dh.getCur())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 4, 8).add(GSZB.YSZK, 4, 8)
						.add(GSZB.RS, 4, 8))

				// 季度计划完成率
				.add(wclFilter.add(9, 8, 5))

				// 季度去年同期
				.add(new AccPipeFilter(sjAcc, 10, dh.getQntqJdStart(), dh
						.getQntq()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(new AccPipeFilter(sjAcc, 10, dh.getQntq())
						.includeCompanies(allCompanies).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 同比增幅
				.add(tbzzFilter.add(11, 8, 10))

				// 年度累计
				.add(new AccPipeFilter(sjAcc, 12, dh.getFirstMonth(), dh
						.getCur()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 4, 12).add(GSZB.YSZK, 4, 12)
						.add(GSZB.RS, 4, 12))

				// 累计计划完成率
				.add(wclFilter.add(13, 12, 0))

				// 去年同期
				.add(new AccPipeFilter(sjAcc, 14, dh.getQnfirstMonth(), dh
						.getQntq()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 10, 14).add(GSZB.YSZK, 10, 14)
						.add(GSZB.RS, 10, 14))

				// 同比增幅
				.add(tbzzFilter.add(15, 12, 14))

				// 下季度首月预计
				.add(new AccPipeFilter(sjAcc, 16, dh.getXjdFirstMonth())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 下季度次月预计
				.add(new AccPipeFilter(sjAcc, 17, dh.getXjdSecondMonth())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 下季度末月预计
				.add(new AccPipeFilter(sjAcc, 18, dh.getXjdLastMonth())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 下季度预计合计
				.add(new AccPipeFilter(sjAcc, 19, dh.getXjdFirstMonth(), dh
						.getXjdLastMonth()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(new AccPipeFilter(sjAcc, 19, dh.getXjdLastMonth())
						.includeCompanies(allCompanies).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 下季度预计完成率
				.add(wclFilter.add(20, 19, 2))

				// 下季度年度累计
				.add(new AccPipeFilter(sjAcc, 21, dh.getXjdDnFirstMonth(), dh
						.getXjdLastMonth()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 19, 21).add(GSZB.YSZK, 19, 21)
						.add(GSZB.RS, 19, 21))

				// 下季度年度累计完成率
				.add(wclFilter.add(22, 21, 25))

				// 下季度去年同期年度累计
				.add(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdDnFirstMonth(),
						dh.getQntqXjdLastMonth())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs))
				.add(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdLastMonth())
						.includeCompanies(allCompanies).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 下季度年度累计同比增幅
				.add(tbzzFilter.add(24, 21, 23))

				// 添加特殊指标过滤器
				.add(new SpecialPipeFilter().exclude(5)// 计划完成率
						.exclude(7)// 同比增幅
						.exclude(9)// 季度计划完成率
						.exclude(11)// 同比增幅
						.exclude(13)// 累计计划完成率
						.exclude(15)// 同比增幅
						.exclude(20)// 下季度预计完成率
						.exclude(22)// 下季度年度累计完成率
						.exclude(24))// 下季度年度累计同比增幅
				.add(tbzzFilter).add(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 26;
	}

}
