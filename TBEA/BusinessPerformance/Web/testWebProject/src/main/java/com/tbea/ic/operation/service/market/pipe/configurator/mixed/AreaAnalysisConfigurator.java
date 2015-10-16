package com.tbea.ic.operation.service.market.pipe.configurator.mixed;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.market.Indicator;
import com.tbea.ic.operation.service.market.pipe.filter.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.ZzlPipeFilter;



//配置表结构, 横线部分为不需要计算值
//			(?~?月)累計
//投标金额 									-
//中标金额
//中标率
//签约金额

public class AreaAnalysisConfigurator implements IPipeConfigurator{

	Date startDate;
	
	public AreaAnalysisConfigurator(IAccumulator acc, Date startDate) {
		super();
		this.acc = acc;
		this.startDate = startDate;
	}

	IAccumulator acc;
	
	@Override
	public void onConfiguring(IPipe pipe) {
		List<Company> comps = pipe.getCompanies();
		DateHelper dh = new DateHelper(pipe.getDate());
		pipe.addFilter(new AccPipeFilter(acc, 0, startDate, dh.getCur())
			.include(Indicator.TBJE.ordinal())
			.include(Indicator.ZBJE.ordinal())
			.include(Indicator.ZBL.ordinal())
			.include(Indicator.QYJE.ordinal())
			.includeCompanies(comps))
		.addFilter(new RatioPipeFilter());
	}

	@Override
	public int getColumnCount() {
		return 1;
	}


}
