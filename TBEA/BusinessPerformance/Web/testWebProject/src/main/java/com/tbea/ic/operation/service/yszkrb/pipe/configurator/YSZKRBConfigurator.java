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

		//oldOnConfiguringPipe(pipe,allCompanies,firstDayOfMonth,wclFilter,sumFilter);
		newOnConfiguringPipe(pipe,allCompanies,firstDayOfMonth,wclFilter,sumFilter);

	}

	@Override
	public int getColumnCount() {
		return 13;
	}

	/**
	 * 原本的pipe操作
	 * @param pipe
	 * @param allCompanies
	 * @param firstDayOfMonth
	 * @param wclFilter
	 * @param sumFilter
	 */
	private void oldOnConfiguringPipe(IPipe pipe,List<Company> allCompanies,Date firstDayOfMonth,WclPipeFilter wclFilter,SumPipeFilter sumFilter){
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

	/**
	 * 新的pipe操作
	 * @param pipe
	 * @param allCompanies
	 * @param firstDayOfMonth
	 * @param wclFilter
	 * @param sumFilter
	 */
	private void newOnConfiguringPipe(IPipe pipe,List<Company> allCompanies,Date firstDayOfMonth,WclPipeFilter wclFilter,SumPipeFilter sumFilter){
		// 应收账款指标
		pipe.addFilter(new AccPipeFilter(yszkrbAccFactory.getYszkzbAcc(pipe.getDate()), 0)
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators()))

			// 回款计划
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getHkjhAcc(pipe.getDate()), 1)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			// 确保款项
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getQbkxAcc(pipe.getDate()), 2)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			// 争取款项
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getZqkxAcc(pipe.getDate()), 3)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			// 上月应收余额
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getSyysyeAcc(pipe.getDate()), 4)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			// 今日新增应收账款
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getJrxzyszkAcc(pipe.getDate()), 5)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			// 本月累计新增应收
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getByljxzysAcc(pipe.getDate()), 6)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			// 今日回款
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getJrhkNewAcc(pipe.getDate()), 7)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			// 累计回款
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getLjhkAcc(pipe.getDate()), 8)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			// 回款完成率
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getHkwclAcc(pipe.getDate()), 9)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			// 累计可降应收回款
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getLjkjyshkAcc(pipe.getDate()), 10)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			// 今日应收账款余额
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getJryszkyeAcc(pipe.getDate()), 11)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()))

			// 应收账款完成率
			.addFilter(new AccPipeFilter(yszkrbAccFactory.getYszkwclAcc(pipe.getDate()), 12)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators()));
	}
}
