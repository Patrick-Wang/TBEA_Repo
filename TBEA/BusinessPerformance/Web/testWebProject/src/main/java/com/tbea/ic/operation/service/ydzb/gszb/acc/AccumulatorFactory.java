package com.tbea.ic.operation.service.ydzb.gszb.acc;

import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;

public class AccumulatorFactory {
	private IAccumulator sjAcc;
	private IAccumulator njhAcc;
	private IAccumulator yjhcc;
	
	public AccumulatorFactory(SJZBDao sjzbDao, YJ20ZBDao yj20zbDao,
			YJ28ZBDao yj28zbDao, YDZBZTDao ydzbztDao, YDJHZBDao ydjhzbDao, NDJHZBDao ndjhzbDao){
		sjAcc = new SjzbAccumulator(sjzbDao, yj20zbDao,	yj28zbDao, ydzbztDao);
		njhAcc = new NjhzbAccumulator(ndjhzbDao);
		yjhcc = new YjhzbAccumulator(ydjhzbDao);
	}
	
	public IAccumulator getSjAcc(){
		return sjAcc;
	}
	
	public IAccumulator getNjhAcc(){
		return njhAcc;
	}
	
	public IAccumulator getYjhAcc(){
		return yjhcc;
	}
	
	public CompositeAccumulator getCompositeAcc(CompositeAccDataSource dataSource){
		return new CompositeAccumulator(dataSource);
	}
	
}
