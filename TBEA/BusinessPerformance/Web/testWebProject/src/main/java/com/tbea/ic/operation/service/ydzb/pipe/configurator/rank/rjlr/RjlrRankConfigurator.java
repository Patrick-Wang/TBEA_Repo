package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.rjlr;

import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.composite.RankPipeFilter;

public class RjlrRankConfigurator  implements IPipeConfigurator{

	@Override
	public void onConfiguring(IPipe pipe) {
			//年度、月度排名
			pipe.addFilter(new RankPipeFilter()
				.add(0, 1)
				.add(2, 3));
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
}
