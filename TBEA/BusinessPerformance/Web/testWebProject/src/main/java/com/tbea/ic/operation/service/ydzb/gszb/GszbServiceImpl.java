package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.List;

public class GszbServiceImpl implements GszbService{

	@Override
	public List<String[]> getGsztzb(Date date) {
		GszbPipe pipe = new GszbPipe(null, 0, null, null);
		pipe.add(new QnjhPipeFilter(null, 0));
		List<Double[]> ret = pipe.getGszb();
		return null;
	}
	
}
