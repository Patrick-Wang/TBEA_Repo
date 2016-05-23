package com.tbea.ic.operation.service.yszkrb.pipe.configurator;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.ysdaily.YSDAILYDao;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.SumPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.WclPipeFilter;
import com.tbea.ic.operation.service.yszkrb.pipe.acc.YSZKRBAccFactory;

public class YSZKRBConfigurator implements  IPipeConfigurator {


	YSZKRBAccFactory yszkrbAccFactory;
	
	public YSZKRBConfigurator(YSDAILYDao ysdailyDao) {
		yszkrbAccFactory = new YSZKRBAccFactory(ysdailyDao);
	}
	
	@Override
	public void onConfiguring(IPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		
		Date firstDayOfMonth = DateHelper.getFirstDayOfMonth(pipe.getDate());
		WclPipeFilter wclFilter = new WclPipeFilter();
		SumPipeFilter sumFilter = new SumPipeFilter();

		// 集团下达月度资金回笼指标
		pipe.addFilter(new AccPipeFilter(yszkrbAccFactory.getJtxdydzjhlzbAcc(), 0)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

		// 各单位自行制定的回款计划
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getGdwzxzdhkjhAcc(), 1)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

		//今日回款
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getJrhkAcc(), 2)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))
					
		//月累计（截止今日）月度从1号累计到月末31号汇总总额（即：今日回款中1号-31号回款累计）
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getJrhkAcc(), 3, firstDayOfMonth, pipe.getDate())
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))
					
		// 资金回笼指标完成    月度累计回款/集团下达的计划
			.addFilter(wclFilter.add(4, 3, 0))
			
		// 回款计划完成率 等于：月度累计回款/各单位自行制定的回款计划.
			.addFilter(wclFilter.add(5, 3, 1))
			
			
		//已回款中可降应收的回款金额
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getYhkzkjyshkjeAcc(), 6)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

		//确保办出
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getQbbcAcc(), 7)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

		//争取办出
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getZqbcAcc(), 8)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))
		
		//两者合计   等于：确保办出+争取办出
			.addFilter(sumFilter.add(9, 8, 7))
		
		//全月确保 等于：确保办出+月累计回款
			.addFilter(sumFilter.add(10, 7, 3))

		//预计全月计划完成率  等于：全月确保/各单位自行定的计划
			.addFilter(wclFilter.add(11, 10, 1))
			
		//截止月底应收账款账面余额
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getJzydyszkzmyeAcc(), 12)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))			
		
			.addFilter(sumFilter)
			.addFilter(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 13;
	}
}
