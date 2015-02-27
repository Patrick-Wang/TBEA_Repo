package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccSbdPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.CopyPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.SpecialPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;

public class StandardConfigurator implements IPipeConfigurator {

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;
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
	
	public StandardConfigurator(IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc, CompanyManager companyManager) {
		this.sjAcc = sjAcc;
		this.yjhAcc = yjhAcc;
		this.njhAcc = njhAcc;
		this.companyManager = companyManager;
	}

	public List<Company> getNonSbdCompany(List<Company> companies) {
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

	public List<Company> getSbdCompany(List<Company> companies) {
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

	public List<Integer> getSpecialZbs(){
		return specialZbs;
	}
	
	@Override
	public void onConfiguring(GszbPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Company> nonSbdCompanies = getNonSbdCompany(allCompanies);
		List<Company> sbdCompanies = getSbdCompany(allCompanies);

		DateHelper dh = new DateHelper(pipe.getDate());

		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();

		// 全年计划
		pipe.add(new AccPipeFilter(njhAcc, 0).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs).include(GSZB.RS)
				.include(GSZB.YSZK).include(GSZB.CH));

		// 当月计划
		if (sbdCompanies.isEmpty()) {
			pipe.add(new AccPipeFilter(yjhAcc, 1)
					.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
					.excludeZbs(specialZbs).include(GSZB.RS).include(GSZB.YSZK)
					.include(GSZB.CH));
		} else {
			pipe.add(
					new AccPipeFilter(yjhAcc, 1).includeCompanies(allCompanies)
							.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs)
							.include(GSZB.RS)).add(
					new AccSbdPipeFilter(yjhAcc, 1)
							.includeCompanies(sbdCompanies).include(GSZB.YSZK)
							.include(GSZB.CH));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.add(new AccPipeFilter(yjhAcc, 1)
						.includeCompanies(nonSbdCompanies).include(GSZB.YSZK)
						.include(GSZB.CH));
			}
		}

				// 当月实际
			pipe.add(
				new AccPipeFilter(sjAcc, 2).includeCompanies(allCompanies)
						.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs)
						.include(GSZB.YSZK).include(GSZB.CH).include(GSZB.RS))

				// 计划完成率
				.add(wclFilter.add(3, 2, 1))

				// 去年同期
				.add(new AccPipeFilter(sjAcc, 4, dh.getQntq())
						.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
						.excludeZbs(specialZbs).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 同比增幅
				.add(tbzzFilter.add(5, 2, 4))

				// 季度计划
				.add(new AccPipeFilter(yjhAcc, 6, dh.getJdStart(), dh.getCur())
						.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
						.excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 1, 6).add(GSZB.YSZK, 1, 6)
						.add(GSZB.RS, 1, 6))

				// 季度累计
				.add(new AccPipeFilter(sjAcc, 7, dh.getJdStart(), dh.getCur())
						.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
						.excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 2, 7).add(GSZB.YSZK, 2, 7)
						.add(GSZB.RS, 2, 7))

				// 季度计划完成率
				.add(wclFilter.add(8, 7, 6))

				// 季度去年同期
				.add(new AccPipeFilter(sjAcc, 9, dh.getQntqJdStart(), dh
						.getQntq()).includeCompanies(allCompanies)
						.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
				.add(new AccPipeFilter(sjAcc, 9, dh.getQntq())
						.includeCompanies(allCompanies).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 同比增幅
				.add(tbzzFilter.add(10, 7, 9))

				// 年度累计
				.add(new AccPipeFilter(sjAcc, 11, dh.getFirstMonth(), dh
						.getCur()).includeCompanies(allCompanies)
						.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 2, 11).add(GSZB.YSZK, 2, 11)
						.add(GSZB.RS, 2, 11))

				// 累计计划完成率
				.add(wclFilter.add(12, 11, 0))

				// 去年同期
				.add(new AccPipeFilter(sjAcc, 13, dh.getQnfirstMonth(), dh
						.getQntq()).includeCompanies(allCompanies)
						.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 9, 13).add(GSZB.YSZK, 9, 13)
						.add(GSZB.RS, 9, 13))

				// 同比增幅
				.add(tbzzFilter.add(14, 11, 13))

				// 添加特殊指标过滤器
				.add(new SpecialPipeFilter().exclude(3)// 计划完成率
						.exclude(5)// 同比增幅
						.exclude(8)// 季度计划完成率
						.exclude(10)// 同比增幅
						.exclude(12)// 累计计划完成率
						.exclude(14))// 同比增幅
				.add(tbzzFilter).add(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 15;
	}

	/**
	 * @return the sjAcc
	 */
	public IAccumulator getSjAcc() {
		return sjAcc;
	}

	/**
	 * @return the yjhAcc
	 */
	public IAccumulator getYjhAcc() {
		return yjhAcc;
	}

	/**
	 * @return the njhAcc
	 */
	public IAccumulator getNjhAcc() {
		return njhAcc;
	}

	/**
	 * @return the companyManager
	 */
	public CompanyManager getCompanyManager() {
		return companyManager;
	}

}
