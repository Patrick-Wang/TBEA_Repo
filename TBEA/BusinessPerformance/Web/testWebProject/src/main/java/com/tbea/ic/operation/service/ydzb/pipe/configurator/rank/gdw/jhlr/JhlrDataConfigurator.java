package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.jhlr;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.simple.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.simple.WclPipeFilter;

public class JhlrDataConfigurator  implements IPipeConfigurator{

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;


	public JhlrDataConfigurator(IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
		this.sjAcc = sjAcc;
		this.yjhAcc = yjhAcc;
		this.njhAcc = njhAcc;
	}
	
	@Override
	public void onConfiguring(IPipe pipe) {
		
			List<Company> allCompanies = pipe.getCompanies();
			DateHelper dh = new DateHelper(pipe.getDate());
			WclPipeFilter wclFilter = new WclPipeFilter();
			// 全年计划
			pipe.add(new AccPipeFilter(njhAcc, 0)
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators()))
	
			// 年度累计
			.add(new AccPipeFilter(sjAcc, 1, dh.getFirstMonth(), dh.getCur())
							.includeCompanies(allCompanies)
							.includeZbs(pipe.getIndicators()))
	
			// 累计计划完成率
			.add(wclFilter.add(2, 1, 0))
					
			// 当月计划
			.add(new AccPipeFilter(yjhAcc, 4)
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators()))
	
			// 当月实际
			.add(new AccPipeFilter(sjAcc, 5)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))
	
			// 计划完成率
			.add(wclFilter.add(6, 5, 4));
	}

	@Override
	public int getColumnCount() {
		return 8;
	}
}
