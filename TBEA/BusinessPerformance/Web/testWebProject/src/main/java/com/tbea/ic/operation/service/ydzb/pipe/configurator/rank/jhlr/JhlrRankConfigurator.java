package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.jhlr;

import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.composite.RankPipeFilter;

public class JhlrRankConfigurator  implements IPipeConfigurator{

	@Override
	public void onConfiguring(IPipe pipe) {
			//年度、月度排名
			pipe.addFilter(new RankPipeFilter()
				.add(2, 3)
				.add(6, 7));
	}

	@Override
	public int getColumnCount() {
		return 8;
	}
}
