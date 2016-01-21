package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.first;

import java.util.List;

import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;

enum ColumnType{
	qnjh, // 全年计划
	jdjh,// 季度计划
	dyjh,// 当月计划
	dysj,// 当月实际
	jhwcl,// 计划完成率
	qntq,// 去年同期
	tbzf,// 同比增幅
	cyyj,// 次月预计
	myyj,// 末月预计
	jdyjhj,// 季度预计合计
	jdyjwcl,// 季度预计完成率
	jdqntq,// 季度去年同期
	jdtbzf,// 同比增幅
	ndlj,// 年度累计
	ndljjhwcl,// 累计计划完成率
	ndqntq,// 去年同期
	ndtbzf,// 同比增幅
	end
} ;

public abstract class FirstSeasonPredictionConfigurator implements IPipeConfigurator {

	protected IAccumulator sjAcc;
	protected IAccumulator yjhAcc;
	protected IAccumulator njhAcc;
	protected SbdNdjhZbDao sbdzbDao;
	
	public FirstSeasonPredictionConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
		this.sbdzbDao = sbdzbDao;
		this.sjAcc = sjAcc;
		this.yjhAcc = yjhAcc;
		this.njhAcc = njhAcc;
	}
	
	@Override
	public int getColumnCount() {
		return 17;
	}
	
	@Override
	public void onConfiguring(IPipe pipe) {
		onStart(pipe);
		qnjh();// 全年计划
		jdjh();// 季度计划
		dyjh();// 当月计划
		dysj();// 当月实际
		jhwcl();// 计划完成率
		qntq();// 去年同期
		tbzf();// 同比增幅
		cyyj();// 次月预计
		myyj();// 末月预计
		jdyjhj();// 季度预计合计
		jdyjwcl();// 季度预计完成率
		jdqntq();// 季度去年同期
		jdtbzf();// 同比增幅
		ndlj();// 年度累计
		ndljjhwcl();// 累计计划完成率
		ndqntq();// 去年同期
		ndtbzf();// 同比增幅
		pipe.addFilter(onFinished());
	}

	abstract protected void onStart(IPipe pipe);

	abstract protected void qnjh();

	abstract protected void jdjh();
	
	abstract protected void dyjh();

	abstract protected void dysj();

	abstract protected void jhwcl();

	abstract protected void qntq();

	abstract protected void tbzf();

	abstract protected void cyyj();

	abstract protected void myyj();

	abstract protected void jdyjhj();

	abstract protected void jdyjwcl();

	abstract protected void jdqntq();

	abstract protected void jdtbzf();

	abstract protected void ndlj();

	abstract protected void ndljjhwcl();
	
	abstract protected void ndqntq();

	abstract protected void ndtbzf();

	abstract protected List<IPipeFilter> onFinished();

	

}
