package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.rjlr;

import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.complex.RankPipeFilter;

public class RjlrRankConfigurator  implements IPipeConfigurator{

	@Override
	public void onConfiguring(IPipe pipe) {
			//年度、月度排名
			pipe.add(new RankPipeFilter()
				.add(0, 1)
				.add(2, 3));
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
}
