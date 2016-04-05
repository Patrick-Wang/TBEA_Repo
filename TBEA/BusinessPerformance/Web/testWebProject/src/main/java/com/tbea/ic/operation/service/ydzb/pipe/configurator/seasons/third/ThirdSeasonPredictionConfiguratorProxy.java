package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.third;

import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

public class ThirdSeasonPredictionConfiguratorProxy extends ThirdSeasonPredictionConfigurator {


	ThirdSeasonPredictionConfigurator stub;
	
	public ThirdSeasonPredictionConfiguratorProxy(SbdNdjhZbDao sbdzbDao,
			IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
		super(sbdzbDao, sjAcc, yjhAcc, njhAcc);
	}

	@Override
	protected void onStart(IPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		Calendar cal = Calendar.getInstance();
		cal.setTime(pipe.getDate());

		if (cal.get(Calendar.YEAR) >= 2016 && allCompanies.size() == 1) {
			stub = new TSPC2016SingleCompany(sbdzbDao, sjAcc, yjhAcc, njhAcc);
		}else if (cal.get(Calendar.YEAR) >= 2016){
			stub = new TSPC2016(sbdzbDao, sjAcc, yjhAcc, njhAcc);
		} else{
			stub = new TSPCDefault(sbdzbDao, sjAcc, yjhAcc, njhAcc);
		}
		
		stub.onStart(pipe);
	}

	@Override
	protected void qnjh() {
		stub.qnjh();
	}

	@Override
	protected void jdjh() {
		stub.jdjh();
	}

	@Override
	protected void xjdjh() {
		stub.xjdjh();
	}

	@Override
	protected void dyjh() {
		stub.dyjh();
	}

	@Override
	protected void dysj() {
		stub.dysj();
	}

	@Override
	protected void jhwcl() {
		stub.jhwcl();
	}

	@Override
	protected void qntq() {
		stub.qntq();
	}

	@Override
	protected void tbzf() {
		stub.tbzf();
	}

	@Override
	protected void jdlj() {
		stub.jdlj();
	}

	@Override
	protected void jdjhwcl() {
		stub.jdjhwcl();
	}

	@Override
	protected void jdqntq() {
		stub.jdqntq();
	}

	@Override
	protected void jdtbzf() {
		stub.jdtbzf();
	}

	@Override
	protected void ndlj() {
		stub.ndlj();
	}

	@Override
	protected void ndljjhwcl() {
		stub.ndljjhwcl();
	}

	@Override
	protected void ndljqntq() {
		stub.ndljqntq();
	}

	@Override
	protected void ndljtbzf() {
		stub.ndljtbzf();
	}

	@Override
	protected void xjdsyyj() {
		stub.xjdsyyj();
	}

	@Override
	protected void xjdcyyj() {
		stub.xjdcyyj();
	}

	@Override
	protected void xjdmyyj() {
		stub.xjdmyyj();
	}

	@Override
	protected void xjdyjhj() {
		stub.xjdyjhj();
	}

	@Override
	protected void xjdyjwcl() {
		stub.xjdyjwcl();
	}

	@Override
	protected void xjdndlj() {
		stub.xjdndlj();
	}

	@Override
	protected void xjdndljwcl() {
		stub.xjdndljwcl();
	}

	@Override
	protected void xjdqntqndlj() {
		stub.xjdqntqndlj();
	}

	@Override
	protected void xjdndljtbzf() {
		stub.xjdndljtbzf();
	}

	@Override
	protected void xjdqnjh() {
		stub.xjdqnjh();		
	}
	
	@Override
	protected List<IPipeFilter> onFinished() {
		return stub.onFinished();
	}


//	@Override
//	public void onConfiguring(IPipe pipe) {
//		List<Company> allCompanies = pipe.getCompanies();
//		List<Company> nonSbdCompanies = new ArrayList<Company>();
//		List<Company> sbdCompanies = new ArrayList<Company>();
//		ConfiguratorUtil.seperate(sbdzbDao, allCompanies, sbdCompanies, nonSbdCompanies);
//	
//		DateHelper dh = new DateHelper(pipe.getDate());
//
//		WclPipeFilter wclFilter = new WclPipeFilter();
//		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
//		CopyPipeFilter copyFilter = new CopyPipeFilter();
//		List<Integer> gsztzbs = pipe.getIndicators();
//		
//		// 全年计划
//		pipe.addFilter(new AccPipeFilter(njhAcc, 0)
//				.includeCompanies(allCompanies)
//				.includeZbs(gsztzbs)
//				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//				.excludeZbs(ConfiguratorUtil.getRatioZbs()));
//		
//		// 下季度全年计划
//		pipe.addFilter(new AccPipeFilter(njhAcc, 25, dh.getXjdLastMonth())
//				.includeCompanies(allCompanies)
//				.includeZbs(gsztzbs)
//				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//				.excludeZbs(ConfiguratorUtil.getRatioZbs()));
//
//
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(dh.getCur());
//		// 当月计划
//		if (sbdCompanies.isEmpty() || cal.get(Calendar.YEAR) >= 2016) {
//			pipe.addFilter(new AccPipeFilter(yjhAcc, 3)
//					.includeCompanies(allCompanies)
//					.includeZbs(gsztzbs)
//					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//					.excludeZbs(ConfiguratorUtil.getRatioZbs()));
//
//		} else {
//			pipe.addFilter(new AccPipeFilter(yjhAcc, 3)
//					.includeCompanies(allCompanies)
//					.includeZbs(gsztzbs)
//					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//					.excludeZbs(ConfiguratorUtil.getRatioZbs())
//					.exclude(GSZB.YSZK32)
//					.exclude(GSZB.CH35))
//							
//				.addFilter(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 3, dh.getFirstMonth(), dh.getCur())
//					.includeCompanies(sbdCompanies)
//					.include(GSZB.YSZK32)
//					.include(GSZB.CH35));
//
//			if (!nonSbdCompanies.isEmpty()) {
//				pipe.addFilter(new AccPipeFilter(yjhAcc, 3)
//						.includeCompanies(nonSbdCompanies)
//						.include(GSZB.YSZK32)
//						.include(GSZB.CH35));
//			}
//		}
//
//		// 当月实际
//		pipe.addFilter(new AccPipeFilter(sjAcc, 4)
//				.includeCompanies(allCompanies)
//				.includeZbs(gsztzbs)
//				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//				.excludeZbs(ConfiguratorUtil.getRatioZbs()))
//
//				// 计划完成率
//				.addFilter(wclFilter.add(5, 4, 3))
//
//				// 去年同期
//				.addFilter(new AccPipeFilter(sjAcc, 6, dh.getQntq())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs()))
//
//				// 同比增幅
//				.addFilter(tbzzFilter.add(7, 4, 6))
//
//				// 季度计划
//				.addFilter(new AccPipeFilter(yjhAcc, 1, dh.getJdStart(), dh.getJdEnd(dh.getCur()))
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs())
//						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
//				.addFilter(copyFilter
//						.add(ConfiguratorUtil.getTimePointNumberZbs(), 3, 1))
//
//				// 下季度计划
//				.addFilter(new AccPipeFilter(yjhAcc, 2, dh.getXjdFirstMonth(), dh
//						.getXjdLastMonth())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs())
//						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
//				.addFilter(new AccPipeFilter(yjhAcc, 2, dh.getXjdLastMonth())
//						.includeCompanies(allCompanies)
//						.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
//
//				// 季度累计
//				.addFilter(new AccPipeFilter(sjAcc, 8, dh.getJdStart(), dh.getCur())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs())
//						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
//						.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
//				.addFilter(copyFilter
//						.add(ConfiguratorUtil.getTimePointNumberZbs(), 4, 8))
//
//
//				// 季度计划完成率
//				.addFilter(wclFilter.add(9, 8, 1))
//
//				// 季度去年同期
//				.addFilter(new AccPipeFilter(sjAcc, 10, dh.getQntqJdStart(), dh
//						.getQntq())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs())
//						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
//						.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
//				.addFilter(new AccPipeFilter(sjAcc, 10, dh.getQntq())
//						.includeCompanies(allCompanies)
//						.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
//
//				// 同比增幅
//				.addFilter(tbzzFilter
//						.add(11, 8, 10))
//
//				// 年度累计
//				.addFilter(new AccPipeFilter(sjAcc, 12, dh.getFirstMonth(), dh
//						.getCur())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs())
//						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
//						.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
//				.addFilter(copyFilter
//						.add(ConfiguratorUtil.getTimePointNumberZbs(), 4, 12))
//
//				// 累计计划完成率
//				.addFilter(wclFilter.add(13, 12, 0))
//
//				// 去年同期
//				.addFilter(new AccPipeFilter(sjAcc, 14, dh.getQnfirstMonth(), dh
//						.getQntq())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs())
//						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
//						.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
//				.addFilter(copyFilter
//						.add(ConfiguratorUtil.getTimePointNumberZbs(), 10, 14))
//
//				// 同比增幅
//				.addFilter(tbzzFilter.add(15, 12, 14))
//
//				// 下季度首月预计
//				.addFilter(new AccPipeFilter(sjAcc, 16, dh.getXjdFirstMonth())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs()))
//
//				// 下季度次月预计
//				.addFilter(new AccPipeFilter(sjAcc, 17, dh.getXjdSecondMonth())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs()))
//
//				// 下季度末月预计
//				.addFilter(new AccPipeFilter(sjAcc, 18, dh.getXjdLastMonth())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs()))
//
//				// 下季度预计合计
//				.addFilter(new AccPipeFilter(sjAcc, 19, dh.getXjdFirstMonth(), dh
//						.getXjdLastMonth())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs())
//						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
//				.addFilter(new AccPipeFilter(sjAcc, 19, dh.getXjdLastMonth())
//						.includeCompanies(allCompanies)
//						.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
//
//
//				// 下季度预计完成率
//				.addFilter(wclFilter.add(20, 19, 2))
//
//				// 下季度年度累计
//				.addFilter(new AccPipeFilter(sjAcc, 21, dh.getXjdDnFirstMonth(), dh
//						.getXjdLastMonth())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs())
//						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
//						.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
//				.addFilter(copyFilter
//						.add(ConfiguratorUtil.getTimePointNumberZbs(), 19, 21))
//
//				// 下季度年度累计完成率
//				.addFilter(wclFilter
//						.add(22, 21, 25))
//
//				// 下季度去年同期年度累计
//				.addFilter(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdDnFirstMonth(),
//						dh.getQntqXjdLastMonth())
//						.includeCompanies(allCompanies)
//						.includeZbs(gsztzbs)
//						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//						.excludeZbs(ConfiguratorUtil.getRatioZbs())
//						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
//						.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
//				.addFilter(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdLastMonth())
//						.includeCompanies(allCompanies)
//						.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
//
//				// 下季度年度累计同比增幅
//				.addFilter(tbzzFilter
//						.add(24, 21, 23))
//
//				// 添加特殊指标过滤器
//				.addFilter(new RatioPipeFilter().excludeCol(5)// 计划完成率
//						.excludeCol(7)// 同比增幅
//						.excludeCol(9)// 季度计划完成率
//						.excludeCol(11)// 同比增幅
//						.excludeCol(13)// 累计计划完成率
//						.excludeCol(15)// 同比增幅
//						.excludeCol(20)// 下季度预计完成率
//						.excludeCol(22)// 下季度年度累计完成率
//						.excludeCol(24))// 下季度年度累计同比增幅
//				.addFilter(tbzzFilter)
//				.addFilter(wclFilter);
//	}



}
