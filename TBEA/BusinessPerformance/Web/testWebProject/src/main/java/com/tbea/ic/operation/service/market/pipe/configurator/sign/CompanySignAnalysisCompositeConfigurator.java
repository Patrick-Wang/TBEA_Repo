package com.tbea.ic.operation.service.market.pipe.configurator.sign;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.market.Indicator;
import com.tbea.ic.operation.service.market.pipe.filter.composite.CompositeRatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.configurator.composite.AbstractCompositeConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.composite.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.RatioIndicatorPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.ZzlPipeFilter;



//配置表结构, 横线部分为不需要计算值
//当月情况		年度累计		去年同期累计		同比增幅
//合同数量	cp1											--
//合同数量	cp2											--
//合同数量	cp3											--
//合同数量												--
//合同金额												
//签约占比												--



public class CompanySignAnalysisCompositeConfigurator extends AbstractCompositeConfigurator{

	public CompanySignAnalysisCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		super(computeMap);
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	protected void onConfiguring(
			IPipe pipe,
			IAccumulator acc,
			List<Integer> zbs,
			int startRow,
			int step,
			List<Company> subComps,
			WclPipeFilter wclFilter,
			ZzlPipeFilter tbzzFilter) {
		pipe.addFilter(new AccPipeFilter(acc, 0, startRow, step)
			.includeCompanies(subComps)
			.exclude(Indicator.ZBL.ordinal()))
		.addFilter(new AccPipeFilter(acc, 1, startRow, step)
			.includeCompanies(subComps)
			.exclude(Indicator.ZBL.ordinal()))
		.addFilter(new CompositeRatioPipeFilter(startRow, step)
			.exclude(0))
		.addFilter(new RatioIndicatorPipeFilter(startRow, step)
			.add(Indicator.TBZB.ordinal(), Indicator.TBJE.ordinal()));
	}


}
