package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.NjhzbAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.SjzbAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.YjhzbAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.TbzzPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;

//收入签约分结构
public class SrqyConfigurator implements IPipeConfigurator {

	NDJHZBDao ndjhzbDao;

	YDJHZBDao ydjhzbDao;

	YDZBZTDao ydzbztDao;

	SJZBDao sjzbDao;

	YJ20ZBDao yj20zbDao;

	YJ28ZBDao yj28zbDao;

	ZBXXDao zbxxDao;

	CompanyManager companyManager;

	public SrqyConfigurator(NDJHZBDao ndjhzbDao, YDJHZBDao ydjhzbDao,
			YDZBZTDao ydzbztDao, SJZBDao sjzbDao, YJ20ZBDao yj20zbDao,
			YJ28ZBDao yj28zbDao, ZBXXDao zbxxDao, CompanyManager companyManager) {
		this.ndjhzbDao = ndjhzbDao;
		this.ydjhzbDao = ydjhzbDao;
		this.ydzbztDao = ydzbztDao;
		this.sjzbDao = sjzbDao;
		this.yj20zbDao = yj20zbDao;
		this.yj28zbDao = yj28zbDao;
		this.zbxxDao = zbxxDao;
		this.companyManager = companyManager;
	}

	@Override
	public void onConfiguring(GszbPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Integer> zbs = pipe.getZbIds();
		DateHelper dh = new DateHelper(pipe.getDate());
		IAccumulator sjAcc = new SjzbAccumulator(sjzbDao, yj20zbDao, yj28zbDao,
				ydzbztDao);
		IAccumulator yjhAcc = new YjhzbAccumulator(ydjhzbDao);
		IAccumulator njhAcc = new NjhzbAccumulator(ndjhzbDao);

		WclPipeFilter wclFilter = new WclPipeFilter();
		TbzzPipeFilter tbzzFilter = new TbzzPipeFilter();
		

		// 全年计划
		pipe.add(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(allCompanies)
				.includeZbs(zbs))

			// 当月计划
			.add(new AccPipeFilter(yjhAcc, 1)
				.includeCompanies(allCompanies)
				.includeZbs(zbs))

			// 当月实际
			.add(new AccPipeFilter(sjAcc, 2)
				.includeCompanies(allCompanies)
				.includeZbs(zbs))

			// 计划完成率
			.add(wclFilter.add(3, 2, 1))

			
			// 年度累计
			.add(new AccPipeFilter(sjAcc, 4, dh.getFirstMonth(), dh.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(zbs))
			
			// 累计计划完成率
			.add(wclFilter.add(5, 4, 0))	
				
			// 去年同期
			.add(new AccPipeFilter(sjAcc, 6, dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(zbs))

			// 同比增幅
			.add(tbzzFilter.add(7, 2, 6))

			// 去年同期累计
			.add(new AccPipeFilter(sjAcc, 8, dh.getQnfirstMonth(), dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(zbs))				
			
			// 同比增幅
			.add(tbzzFilter.add(9, 4, 8))
			.add(tbzzFilter)
			.add(wclFilter);
	}

	@Override
	public int columnCount() {
		return 10;
	}

}
