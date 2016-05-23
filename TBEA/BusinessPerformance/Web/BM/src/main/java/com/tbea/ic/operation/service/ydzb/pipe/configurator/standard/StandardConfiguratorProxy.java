package com.tbea.ic.operation.service.ydzb.pipe.configurator.standard;

import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

public class StandardConfiguratorProxy extends StandardConfigurator {

//	public StandardConfiguratorProxy(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc,
//			IAccumulator yjhAcc, IAccumulator njhAcc) {
//		super(sbdzbDao, sjAcc, yjhAcc, njhAcc);
//	}
//
//	private void config2016SingleCompany(IPipe pipe, List<Company> allCompanies){
//		List<Company> nonSbdCompanies = new ArrayList<Company>();
//		List<Company> sbdCompanies = new ArrayList<Company>();
//		seperate(allCompanies, sbdCompanies, nonSbdCompanies);
//	
//		DateHelper dh = new DateHelper(pipe.getDate());
//
//		WclPipeFilter wclFilter = new WclPipeFilter();
//		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
//		CopyPipeFilter copyFilter = new CopyPipeFilter();
//
//		// 全年计划
//		pipe.addFilter(new AccPipeFilter(njhAcc, 0)
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.include(GSZB.XSLRL_28.getValue()));
//
//		// 当月计划
//		if (sbdCompanies.isEmpty()) {
//			pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.include(GSZB.XSLRL_28.getValue()));
//
//		} else {
//			pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.include(GSZB.XSLRL_28.getValue())
//					.exclude(GSZB.YSZK32)
//					.exclude(GSZB.CH35))
//					
//				.addFilter(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 1, dh.getFirstMonth(), dh.getCur())
//					.includeCompanies(sbdCompanies)
//					.include(GSZB.YSZK32)
//					.include(GSZB.CH35));
//
//			if (!nonSbdCompanies.isEmpty()) {
//				pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
//						.includeCompanies(nonSbdCompanies)
//						.include(GSZB.YSZK32)
//						.include(GSZB.CH35));
//			}
//		}
//
//			// 当月实际
//		pipe.addFilter(new AccPipeFilter(sjAcc, 2)
//				.includeCompanies(allCompanies)
//				.includeZbs(pipe.getIndicators())
//				.excludeZbs(getInvisiableZbs())
//				.excludeZbs(getRatioZbs()))
//
//			// 计划完成率
//			.addFilter(wclFilter.add(3, 2, 1))
//
//			// 去年同期
//			.addFilter(new AccPipeFilter(sjAcc, 4, dh.getQntq())
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs()))
//
//			// 同比增幅
//			.addFilter(tbzzFilter.add(5, 2, 4))
//
//			// 季度计划
//			.addFilter(new AccPipeFilter(yjhAcc, 6, dh.getJdStart(), DateHelper.getJdEnd(dh.getCur()))
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.excludeZbs(getTimePointNumberZbs()))
//			.addFilter(copyFilter
//					.add(getTimePointNumberZbs(), 1, 6))
//
//
//			// 季度累计
//			.addFilter(new AccPipeFilter(sjAcc, 7, dh.getJdStart(), dh.getCur())
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.excludeZbs(getTimePointNumberZbs())
//					.excludeZbs(getZhHiddenZbs()))
//			.addFilter(copyFilter
//					.add(getTimePointNumberZbs(), 2, 7))
//
//			// 季度计划完成率
//			.addFilter(wclFilter.add(8, 7, 6))
//
//			// 季度去年同期
//			.addFilter(new AccPipeFilter(sjAcc, 9, dh.getQntqJdStart(), dh.getQntq())
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.excludeZbs(getTimePointNumberZbs())
//					.excludeZbs(getZhHiddenZbs()))
//			.addFilter(copyFilter
//					.add(getTimePointNumberZbs(), 4, 9))
//
//
//			// 同比增幅
//			.addFilter(tbzzFilter.add(10, 7, 9))
//
//			// 年度累计
//			.addFilter(new AccPipeFilter(sjAcc, 11, dh.getFirstMonth(), dh.getCur())
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.excludeZbs(getTimePointNumberZbs())
//					.excludeZbs(getZhHiddenZbs()))
//			.addFilter(copyFilter
//					.add(getTimePointNumberZbs(), 2, 11))
//
//			// 累计计划完成率
//			.addFilter(wclFilter.add(12, 11, 0))
//
//			// 去年同期
//			.addFilter(new AccPipeFilter(sjAcc, 13, dh.getQnfirstMonth(), dh.getQntq())
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.excludeZbs(getTimePointNumberZbs())
//					.excludeZbs(getZhHiddenZbs()))
//			.addFilter(copyFilter
//					.add(getTimePointNumberZbs(), 9, 13))
//
//
//			// 同比增幅
//			.addFilter(tbzzFilter.add(14, 11, 13))
//
//			// 添加特殊指标过滤器
//			.addFilter(new RatioPipeFilter()
//					.excludeCol(3)// 计划完成率
//					.excludeCol(5)// 同比增幅
//					.excludeCol(8)// 季度计划完成率
//					.excludeCol(10)// 同比增幅
//					.excludeCol(12)// 累计计划完成率
//					.excludeCol(14)// 同比增幅
//					.exclude(GSZB.XSLRL_28.getValue(), 0)
//					.exclude(GSZB.XSLRL_28.getValue(), 1))
//					
//			.addFilter(tbzzFilter)
//			.addFilter(wclFilter);
//	}
//	
//	private void configCompany(IPipe pipe, List<Company> allCompanies){
//		List<Company> nonSbdCompanies = new ArrayList<Company>();
//		List<Company> sbdCompanies = new ArrayList<Company>();
//		seperate(allCompanies, sbdCompanies, nonSbdCompanies);
//	
//		DateHelper dh = new DateHelper(pipe.getDate());
//
//		WclPipeFilter wclFilter = new WclPipeFilter();
//		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
//		CopyPipeFilter copyFilter = new CopyPipeFilter();
//
//		// 全年计划
//		pipe.addFilter(new AccPipeFilter(njhAcc, 0)
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs()));
//
//		// 当月计划
//		if (sbdCompanies.isEmpty()) {
//			pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs()));
//
//		} else {
//			pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.exclude(GSZB.YSZK32)
//					.exclude(GSZB.CH35))
//					
//				.addFilter(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 1, dh.getFirstMonth(), dh.getCur())
//					.includeCompanies(sbdCompanies)
//					.include(GSZB.YSZK32)
//					.include(GSZB.CH35));
//
//			if (!nonSbdCompanies.isEmpty()) {
//				pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
//						.includeCompanies(nonSbdCompanies)
//						.include(GSZB.YSZK32)
//						.include(GSZB.CH35));
//			}
//		}
//
//			// 当月实际
//		pipe.addFilter(new AccPipeFilter(sjAcc, 2)
//				.includeCompanies(allCompanies)
//				.includeZbs(pipe.getIndicators())
//				.excludeZbs(getInvisiableZbs())
//				.excludeZbs(getRatioZbs()))
//
//			// 计划完成率
//			.addFilter(wclFilter.add(3, 2, 1))
//
//			// 去年同期
//			.addFilter(new AccPipeFilter(sjAcc, 4, dh.getQntq())
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs()))
//
//			// 同比增幅
//			.addFilter(tbzzFilter.add(5, 2, 4))
//
//			// 季度计划
//			.addFilter(new AccPipeFilter(yjhAcc, 6, dh.getJdStart(), DateHelper.getJdEnd(dh.getCur()))
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.excludeZbs(getTimePointNumberZbs()))
//			.addFilter(copyFilter
//					.add(getTimePointNumberZbs(), 1, 6))
//
//
//			// 季度累计
//			.addFilter(new AccPipeFilter(sjAcc, 7, dh.getJdStart(), dh.getCur())
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.excludeZbs(getTimePointNumberZbs())
//					.excludeZbs(getZhHiddenZbs()))
//			.addFilter(copyFilter
//					.add(getTimePointNumberZbs(), 2, 7))
//
//			// 季度计划完成率
//			.addFilter(wclFilter.add(8, 7, 6))
//
//			// 季度去年同期
//			.addFilter(new AccPipeFilter(sjAcc, 9, dh.getQntqJdStart(), dh.getQntq())
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.excludeZbs(getTimePointNumberZbs())
//					.excludeZbs(getZhHiddenZbs()))
//			.addFilter(copyFilter
//					.add(getTimePointNumberZbs(), 4, 9))
//
//
//			// 同比增幅
//			.addFilter(tbzzFilter.add(10, 7, 9))
//
//			// 年度累计
//			.addFilter(new AccPipeFilter(sjAcc, 11, dh.getFirstMonth(), dh.getCur())
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.excludeZbs(getTimePointNumberZbs())
//					.excludeZbs(getZhHiddenZbs()))
//			.addFilter(copyFilter
//					.add(getTimePointNumberZbs(), 2, 11))
//
//			// 累计计划完成率
//			.addFilter(wclFilter.add(12, 11, 0))
//
//			// 去年同期
//			.addFilter(new AccPipeFilter(sjAcc, 13, dh.getQnfirstMonth(), dh.getQntq())
//					.includeCompanies(allCompanies)
//					.includeZbs(pipe.getIndicators())
//					.excludeZbs(getInvisiableZbs())
//					.excludeZbs(getRatioZbs())
//					.excludeZbs(getTimePointNumberZbs())
//					.excludeZbs(getZhHiddenZbs()))
//			.addFilter(copyFilter
//					.add(getTimePointNumberZbs(), 9, 13))
//
//
//			// 同比增幅
//			.addFilter(tbzzFilter.add(14, 11, 13))
//
//			// 添加特殊指标过滤器
//			.addFilter(new RatioPipeFilter()
//					.excludeCol(3)// 计划完成率
//					.excludeCol(5)// 同比增幅
//					.excludeCol(8)// 季度计划完成率
//					.excludeCol(10)// 同比增幅
//					.excludeCol(12)// 累计计划完成率
//					.excludeCol(14))// 同比增幅
//			.addFilter(tbzzFilter)
//			.addFilter(wclFilter);
//	}
	
	
	StandardConfigurator stub;
	
	public StandardConfiguratorProxy(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc,
			IAccumulator yjhAcc, IAccumulator njhAcc) {
		super(sbdzbDao, sjAcc, yjhAcc, njhAcc);
	}

	@Override
	protected void onStart(IPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		Calendar cal = Calendar.getInstance();
		cal.setTime(pipe.getDate());

		if (cal.get(Calendar.YEAR) >= 2016 && allCompanies.size() == 1) {
			stub = new SC2016SingleCompany(sbdzbDao, sjAcc, yjhAcc, njhAcc);
		}else if (cal.get(Calendar.YEAR) >= 2016){
			stub = new SC2016(sbdzbDao, sjAcc, yjhAcc, njhAcc);
		} else{
			stub = new SCDefault(sbdzbDao, sjAcc, yjhAcc, njhAcc);
		}
		
		stub.onStart(pipe);
	}

	@Override
	protected void  qnjh() {
		stub.qnjh();
	}

	@Override
	protected void  dyjh() {
		stub.dyjh();
	}

	@Override
	protected void  dysj() {
		stub.dysj();
	}

	@Override
	protected void  jhwcl() {
		stub.jhwcl();
	}

	@Override
	protected void  qntq() {
		stub.qntq();
	}

	@Override
	protected void  tbzf() {
		stub.tbzf();
	}

	@Override
	protected void  jdjh() {
		stub.jdjh();
	}

	@Override
	protected void  jdlj() {
		stub.jdlj();
	}

	@Override
	protected void  jdjhwcl() {
		stub.jdjhwcl();
	}

	@Override
	protected void  jdqntq() {
		stub.jdqntq();
	}

	@Override
	protected void  jdtbzf() {
		stub.jdtbzf();
	}

	@Override
	protected void  ndlj() {
		stub.ndlj();
	}

	@Override
	protected void  ljjhwcl() {
		stub.ljjhwcl();
	}

	@Override
	protected void  ndqntq() {
		stub.ndqntq();
	}

	@Override
	protected void  ndtbzf() {
		stub.ndtbzf();
	}

	@Override
	protected List<IPipeFilter> onFinished() {
		return stub.onFinished();
	}

}
