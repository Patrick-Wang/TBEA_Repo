package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.first;

import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;

public class FirstSeasonPredictionConfiguratorProxy extends FirstSeasonPredictionConfigurator {

	FirstSeasonPredictionConfigurator stub;
	
	public FirstSeasonPredictionConfiguratorProxy(SbdNdjhZbDao sbdzbDao,
			IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
		super(sbdzbDao, sjAcc, yjhAcc, njhAcc);
	}

	@Override
	protected void onStart(IPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		Calendar cal = Calendar.getInstance();
		cal.setTime(pipe.getDate());
		if (cal.get(Calendar.YEAR) >= 2016 && allCompanies.size() == 1) {
			stub = new FSPC2016SingleCompany(sbdzbDao, sjAcc, yjhAcc, njhAcc);
		}else if (cal.get(Calendar.YEAR) >= 2016){
			stub = new FSPC2016(sbdzbDao, sjAcc, yjhAcc, njhAcc);  
		} else{
			stub = new FSPCDefault(sbdzbDao, sjAcc, yjhAcc, njhAcc);
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
	protected void cyyj() {
		stub.cyyj();		
	}

	@Override
	protected void myyj() {
		stub.myyj();		
	}

	@Override
	protected void jdyjhj() {
		stub.jdyjhj();		
	}

	@Override
	protected void jdyjwcl() {
		stub.jdyjwcl();		
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
	protected void ndqntq() {
		stub.ndqntq();	
	}

	@Override
	protected void ndtbzf() {
		stub.ndtbzf();	
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
//
//		// 全年计划
//		pipe.addFilter(new AccPipeFilter(njhAcc, 0)
//				.includeCompanies(allCompanies)
//				.includeZbs(pipe.getIndicators())
//				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//				.excludeZbs(ConfiguratorUtil.getRatioZbs()));
//		
//		
//
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(dh.getCur());
//		// 当月计划
//		if (sbdCompanies.isEmpty() || cal.get(Calendar.YEAR) >= 2016) {
//			pipe.addFilter(new AccPipeFilter(yjhAcc, 2)
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//					.excludeZbs(ConfiguratorUtil.getRatioZbs()));
//		} else {
//			pipe.addFilter(new AccPipeFilter(yjhAcc, 2)
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//					.excludeZbs(ConfiguratorUtil.getRatioZbs())
//					.exclude(GSZB.YSZK32)
//					.exclude(GSZB.CH35))
//						
//				.addFilter(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 2, dh.getFirstMonth(), dh.getCur())
//					.includeCompanies(sbdCompanies)
//					.include(GSZB.YSZK32)
//					.include(GSZB.CH35));
//
//			if (!nonSbdCompanies.isEmpty()) {
//				pipe.addFilter(new AccPipeFilter(yjhAcc, 2)
//						.includeCompanies(nonSbdCompanies)
//						.include(GSZB.YSZK32)
//						.include(GSZB.CH35));
//			}
//		}
//		// 季度计划
//		pipe.addFilter(new AccPipeFilter(yjhAcc, 1, dh.getJdStart(), DateHelper.getJdEnd(dh.getCur()))
//				.includeCompanies(allCompanies)
//				.includeZbs(pipe.getIndicators())
//				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//				.excludeZbs(ConfiguratorUtil.getRatioZbs())
//				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
//			.addFilter(copyFilter
//				.add(ConfiguratorUtil.getTimePointNumberZbs(), 2, 1))
//
//		// 当月实际
//		.addFilter(new AccPipeFilter(sjAcc, 3)
//				.includeCompanies(allCompanies)
//				.includeZbs(pipe.getIndicators())
//				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//				.excludeZbs(ConfiguratorUtil.getRatioZbs()))
//
//		// 计划完成率
//		.addFilter(wclFilter.add(4, 3, 2))
//
//		// 去年同期
//		.addFilter(new AccPipeFilter(sjAcc, 5, dh.getQntq())
//				.includeCompanies(allCompanies)
//				.includeZbs(pipe.getIndicators())
//				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//				.excludeZbs(ConfiguratorUtil.getRatioZbs()))
//
//		// 同比增幅
//		.addFilter(tbzzFilter.add(6, 3, 5))
//		
//		//次月预计		
//		.addFilter(new AccPipeFilter(sjAcc, 7, dh.getSecondMonthinSeason())
//			.includeCompanies(allCompanies)
//			.includeZbs(pipe.getIndicators())
//			.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//			.excludeZbs(ConfiguratorUtil.getRatioZbs()))
//
//		//末月预计
//		.addFilter(new AccPipeFilter(sjAcc, 8, dh.getLastMonthinSeason())
//			.includeCompanies(allCompanies)
//			.includeZbs(pipe.getIndicators())
//			.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//			.excludeZbs(ConfiguratorUtil.getRatioZbs()))
//		
//		// 季度预计合计
//		.addFilter(new AccPipeFilter(sjAcc, 9, dh.getCur(), dh.getLastMonthinSeason())
//				.includeCompanies(allCompanies)
//				.includeZbs(pipe.getIndicators())
//				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//				.excludeZbs(ConfiguratorUtil.getRatioZbs())
//				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
//		.addFilter(copyFilter
//				.add(ConfiguratorUtil.getTimePointNumberZbs(), 8, 9))
//
//		// 季度预计完成率
//		.addFilter(wclFilter.add(10, 9, 1))
//
//		// 季度去年同期
//		.addFilter(new AccPipeFilter(sjAcc, 11, dh.getQntqJdStart(), dh.getQntqJdEnd())
//				.includeCompanies(allCompanies)
//				.includeZbs(pipe.getIndicators())
//				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//				.excludeZbs(ConfiguratorUtil.getRatioZbs())
//				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
//				.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
//		.addFilter(new AccPipeFilter(sjAcc, 11, dh.getQntqJdStart())
//				.includeCompanies(allCompanies)
//				.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
//
//		// 同比增幅
//		.addFilter(tbzzFilter.add(12, 9, 11))
//
//		// 年度累计
//		.addFilter(new AccPipeFilter(sjAcc, 13, dh.getFirstMonth(), dh.getCur())
//				.includeCompanies(allCompanies)
//				.includeZbs(pipe.getIndicators())
//				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//				.excludeZbs(ConfiguratorUtil.getRatioZbs())
//				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
//				.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
//		.addFilter(copyFilter
//				.add(ConfiguratorUtil.getTimePointNumberZbs(), 3, 13))
//
//		// 累计计划完成率
//		.addFilter(wclFilter.add(14, 13, 0))
//
//		// 去年同期
//		.addFilter(new AccPipeFilter(sjAcc, 15, dh.getQnfirstMonth(), dh.getQntq())
//				.includeCompanies(allCompanies)
//				.includeZbs(pipe.getIndicators())
//				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
//				.excludeZbs(ConfiguratorUtil.getRatioZbs())
//				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
//				.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
//		.addFilter(copyFilter
//				.add(ConfiguratorUtil.getTimePointNumberZbs(), 5, 15))
//
//		// 同比增幅
//		.addFilter(tbzzFilter.add(16, 13, 15))
//
//		// 添加特殊指标过滤器
//		.addFilter(new RatioPipeFilter().excludeCol(4)// 计划完成率
//				.excludeCol(6)// 同比增幅
//				.excludeCol(10)// 季度计划完成率
//				.excludeCol(12)// 同比增幅
//				.excludeCol(14)// 累计计划完成率
//				.excludeCol(16))// 同比增幅
//		.addFilter(tbzzFilter)
//		.addFilter(wclFilter);
//	}

	

}
