package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.rjlr;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.AbstractSbdPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.simple.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.simple.RatioPipeFilter;

public class RjlrDataConfigurator extends AbstractSbdPipeConfigurator{

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;
	
	public RjlrDataConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
		super(sbdzbDao);
		this.sjAcc = sjAcc;
		this.yjhAcc = yjhAcc;
		this.njhAcc = njhAcc;
	}
	
	@Override
	public void onConfiguring(IPipe pipe) {

			List<Company> allCompanies = pipe.getCompanies();
			DateHelper dh = new DateHelper(pipe.getDate());

			// 年度累计
			pipe.add(new AccPipeFilter(sjAcc, 0, dh.getFirstMonth(), dh.getCur())
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs())
					.excludeZbs(getTimePointNumberZbs())
					.excludeZbs(getZhHiddenZbs()))
			.add(new AccPipeFilter(sjAcc, 0)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs()))

			// 当月实际
			.add(new AccPipeFilter(sjAcc, 2)
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()))

			// 添加特殊指标过滤器
			.add(new RatioPipeFilter());
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
}
