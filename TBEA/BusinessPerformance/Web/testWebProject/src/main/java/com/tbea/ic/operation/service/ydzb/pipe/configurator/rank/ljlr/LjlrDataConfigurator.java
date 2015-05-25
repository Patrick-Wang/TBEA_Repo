package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.ljlr;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.ZzlPipeFilter;

public class LjlrDataConfigurator  implements IPipeConfigurator{

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;


	public LjlrDataConfigurator(IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
		this.sjAcc = sjAcc;
		this.yjhAcc = yjhAcc;
		this.njhAcc = njhAcc;
	}
	
	@Override
	public void onConfiguring(IPipe pipe) {

		List<Company> allCompanies = pipe.getCompanies();
		DateHelper dh = new DateHelper(pipe.getDate());
		ZzlPipeFilter zzlFilter = new ZzlPipeFilter();
			// 年度累计
		pipe.addFilter(new AccPipeFilter(sjAcc, 0, dh.getFirstMonth(), dh.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators()))
	
			 //去年同期累计
			.addFilter(new AccPipeFilter(sjAcc, 1, dh.getQnfirstMonth(), dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators()))
				
			//同比增长
			.addFilter(zzlFilter.add(2, 0, 1))
	
			// 当月实际
			.addFilter(new AccPipeFilter(sjAcc, 4, dh.getCur())
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))
	
			// 去年同期
			.addFilter(new AccPipeFilter(sjAcc, 5, dh.getQntq())
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			//同比增长
			.addFilter(zzlFilter.add(6, 4, 5));
	}

	@Override
	public int getColumnCount() {
		return 8;
	}
}
