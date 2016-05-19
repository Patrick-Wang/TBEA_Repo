package com.tbea.ic.operation.service.nczb.pipe.configurator;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.configurator.ConfiguratorUtil;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.CopyPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.ZzlPipeFilter;

public class FinancialPipeConfigurator implements IPipeConfigurator {

	IAccumulator sjAcc;
	
	public FinancialPipeConfigurator(IAccumulator sjAcc) {
		this.sjAcc = sjAcc;
	}

	@Override
	public void onConfiguring(IPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();


		DateHelper dh = new DateHelper(pipe.getDate());


		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();

		
		// 当月实际
		pipe.addFilter(new AccPipeFilter(sjAcc, 0)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(ConfiguratorUtil.getRatioZbs()))

			// 去年同期
			.addFilter(new AccPipeFilter(sjAcc, 1, dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getRatioZbs()))

			// 同比增幅
			.addFilter(tbzzFilter.add(2, 0, 1))

			
			
			// 年度累计
			.addFilter(new AccPipeFilter(sjAcc, 3, dh.getFirstMonth(), dh.getCur())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getRatioZbs())
			.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
			.addFilter(copyFilter.add(ConfiguratorUtil.getTimePointNumberZbs(), 0, 3))
			
			// 去年同期
			.addFilter(new AccPipeFilter(sjAcc, 4, dh.getQnfirstMonth(), dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getRatioZbs())
			.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
			.addFilter(copyFilter.add(ConfiguratorUtil.getTimePointNumberZbs(), 1, 4))
			
			// 同比增幅
			.addFilter(tbzzFilter.add(5, 3, 4))

			// 添加特殊指标过滤器
			.addFilter(new RatioPipeFilter()
					.excludeCol(2)// 计划完成率
					.excludeCol(5))// 同比增幅

			.addFilter(copyFilter)
			.addFilter(tbzzFilter);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}

}
