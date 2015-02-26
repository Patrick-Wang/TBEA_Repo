package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.gszb.acc.CompositeAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.CopyPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.SpecialPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.TbzzPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;

public class CompositeConfigurator implements IPipeConfigurator {

	private IAccumulator acc;
	
	
	public CompositeConfigurator(IAccumulator acc) {
		super();
		this.acc = acc;
	}


	@Override
	public void onConfiguring(GszbPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();

	    DateHelper dh = new DateHelper(pipe.getDate());

		WclPipeFilter wclFilter = new WclPipeFilter();
		TbzzPipeFilter tbzzFilter = new TbzzPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();
		List<Integer> allZbs = pipe.getZbIds();
			// 全年计划
		pipe.add(new AccPipeFilter(acc, 0)
				.includeCompanies(allCompanies)
				.includeZbs(allZbs))
				
			// 当月计划
			.add(new AccPipeFilter(acc, 1)
				.includeCompanies(allCompanies)
				.includeZbs(allZbs))
		
			
			// 当月实际
			.add(new AccPipeFilter(acc, 2)
				.includeCompanies(allCompanies)
				.includeZbs(allZbs))
			
			// 计划完成率
			.add(wclFilter
				.add(3, 2, 1))
			
			// 去年同期	
			.add(new AccPipeFilter(acc, 4, dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(allZbs))
			
			// 同比增幅
			.add(tbzzFilter
				.add(5, 2, 4))
				
			// 季度计划
			.add(new AccPipeFilter(acc, 6, dh.getJdStart(), dh.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(allZbs))
			.add(copyFilter
				.add(GSZB.CH, 1, 6)
				.add(GSZB.YSZK, 1, 6)
				.add(GSZB.RS, 1, 6))
				
			// 季度累计
			.add(new AccPipeFilter(acc, 7, dh.getJdStart(), dh.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(allZbs))
			.add(copyFilter
				.add(GSZB.CH, 2, 7)
				.add(GSZB.YSZK, 2, 7)
				.add(GSZB.RS, 2, 7))
				
			// 季度计划完成率
			.add(wclFilter
				.add(8, 7, 6))
				
			// 季度去年同期
			.add(new AccPipeFilter(acc, 9, dh.getQntqJdStart(), dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(allZbs))
			
			 // 同比增幅
			.add(tbzzFilter
				.add(10, 7, 9))
	
			// 年度累计
			.add(new AccPipeFilter(acc, 11, dh.getFirstMonth(), dh.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(allZbs))
			.add(copyFilter
				.add(GSZB.CH, 2, 11)
				.add(GSZB.YSZK, 2, 11)
				.add(GSZB.RS, 2, 11))
			
		
			// 累计计划完成率
			.add(wclFilter
				.add(12, 11, 0))
			
			// 去年同期
			.add(new AccPipeFilter(acc, 13, dh.getQnfirstMonth(), dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(allZbs))
			.add(copyFilter
				.add(GSZB.CH, 9, 13)
				.add(GSZB.YSZK, 9, 13)
				.add(GSZB.RS, 9, 13))
	
			// 同比增幅
			.add(tbzzFilter
				.add(14, 11, 13))

			//添加特殊指标过滤器
			.add(new SpecialPipeFilter()
				.exclude(3)// 计划完成率
				.exclude(5)// 同比增幅
				.exclude(8)// 季度计划完成率
				.exclude(10)// 同比增幅
				.exclude(12)// 累计计划完成率
				.exclude(14))// 同比增幅
			.add(tbzzFilter)
			.add(wclFilter);

	}

	@Override
	public int getColumnCount() {
		return 15;
	}

}
