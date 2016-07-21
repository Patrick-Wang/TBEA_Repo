package com.tbea.ic.operation.service.ydzb.pipe.acc;

import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.service.report.AccByComps;
import com.tbea.ic.operation.service.report.AdvanceAccByGroup;
import com.tbea.ic.operation.service.util.pipe.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.util.pipe.acc.composite.CompositeAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;


public class AccumulatorFactory {
	private IAccumulator sjAcc;
	private IAccumulator njhAcc;
	private IAccumulator yjhAcc;
	private IAccumulator sjByComps;
	private IAccumulator njhByComps;
	private IAccumulator yjhByComps;
	private AdvanceAccByGroup sjByGroup;
	private AdvanceAccByGroup njhByGroup;
	private AdvanceAccByGroup yjhByGroup;
	
	
	public AccumulatorFactory(SJZBDao sjzbDao, YJ20ZBDao yj20zbDao,
			YJ28ZBDao yj28zbDao, YDZBZTDao ydzbztDao, YDJHZBDao ydjhzbDao, NDJHZBDao ndjhzbDao){
		sjAcc = new SjzbAccumulator(sjzbDao, yj20zbDao,	yj28zbDao, ydzbztDao);
		njhAcc = new NjhzbAccumulator(ndjhzbDao);
		yjhAcc = new YjhzbAccumulator(ydjhzbDao);
		sjByComps = new AccByComps(sjAcc);
		njhByComps = new AccByComps(njhAcc);
		yjhByComps = new AccByComps(yjhAcc);
		sjByGroup = new AdvanceAccByGroup(sjAcc);
		njhByGroup = new AdvanceAccByGroup(njhAcc);
		yjhByGroup = new AdvanceAccByGroup(yjhAcc);
	}
	
	public IAccumulator getSjAcc(){
		return sjAcc;
	}
	
	public IAccumulator getNjhAcc(){
		return njhAcc;
	}
	
	public IAccumulator getYjhAcc(){
		return yjhAcc;
	}
	
	public IAccumulator getCompositeAcc(CompositeAccDataSource dataSource){
		return new CompositeAccumulator(dataSource);
	}

	public IAccumulator getSjByComps() {
		return sjByComps;
	}

	public IAccumulator getNjhByComps() {
		return njhByComps;
	}

	public IAccumulator getYjhByComps() {
		return yjhByComps;
	}

	public AdvanceAccByGroup getSjByGroup() {
		return sjByGroup;
	}

	public AdvanceAccByGroup getNjhByGroup() {
		return njhByGroup;
	}

	public AdvanceAccByGroup getYjhByGroup() {
		return yjhByGroup;
	}
	
}
