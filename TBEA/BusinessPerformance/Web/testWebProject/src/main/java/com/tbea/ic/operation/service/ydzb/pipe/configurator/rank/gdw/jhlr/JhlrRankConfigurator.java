package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.jhlr;

import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.companybased.RankPipeFilter;

public class JhlrRankConfigurator  implements IPipeConfigurator{

	@Override
	public void onConfiguring(IPipe pipe) {
			//年度、月度排名
			pipe.add(new RankPipeFilter()
				.add(2, 3)
				.add(6, 7));
	}

	@Override
	public int getColumnCount() {
		return 8;
	}
}
