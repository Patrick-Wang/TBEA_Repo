package com.tbea.ic.operation.service.market.pipe.configurator.mixed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.market.Indicator;
import com.tbea.ic.operation.service.market.pipe.filter.composite.CompositeRatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.configurator.composite.AbstractCompositeConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.composite.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.ZzlPipeFilter;



//配置表结构, 横线部分为不需要计算值
//			(?~?月)累計	去年同期累計		同比增幅
//投标金额 									
//中标金额
//中标率
//签约金额

public class IndustryMixedAnalysisCompositeConfigurator extends AbstractCompositeConfigurator{

	public IndustryMixedAnalysisCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		super(computeMap);
	}


	@Override
	public int getColumnCount() {
		return 3;
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
				.exclude(2))
			.addFilter(new ZzlPipeFilter()
				.add(2, 0, 1)
				.includeRow(startRow, step));
	}


}
