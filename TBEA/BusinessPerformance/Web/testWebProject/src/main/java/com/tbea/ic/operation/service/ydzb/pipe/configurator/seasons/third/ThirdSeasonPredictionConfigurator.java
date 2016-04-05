package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.third;

import java.util.List;

import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

enum ColumnType{
	qnjh,// 全年计划
	jdjh,// 季度计划
	xjdjh,// 下季度计划
	dyjh,// 当月计划
	dysj,// 当月实际
	jhwcl,// 计划完成率
	qntq,// 去年同期
	tbzf,// 同比增幅
	jdlj,// 季度累计
	jdjhwcl,// 季度计划完成率
	jdqntq,// 季度去年同期
	jdtbzf,// 同比增幅
	ndlj,// 年度累计
	ndljjhwcl,// 累计计划完成率
	ndljqntq,// 去年同期
	ndljtbzf,// 同比增幅
	xjdsyyj,// 下季度首月预计
	xjdcyyj,// 下季度次月预计
	xjdmyyj,// 下季度末月预计
	xjdyjhj,// 下季度预计合计
	xjdyjwcl,// 下季度预计完成率
	xjdndlj,// 下季度年度累计
	xjdndljwcl,// 下季度年度累计完成率
	xjdqntqndlj,// 下季度去年同期年度累计
	xjdndljtbzf,// 下季度年度累计同比增幅
	xjdqnjh,// 下季度全年计划
	end
} ;

public abstract class ThirdSeasonPredictionConfigurator implements IPipeConfigurator {

	protected IAccumulator sjAcc;
	protected IAccumulator yjhAcc;
	protected IAccumulator njhAcc;
	protected SbdNdjhZbDao sbdzbDao;
	
	public ThirdSeasonPredictionConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
		this.sbdzbDao = sbdzbDao;
		this.sjAcc = sjAcc;
		this.yjhAcc = yjhAcc;
		this.njhAcc = njhAcc;
	}
	
	@Override
	public int getColumnCount() {
		return 26;
	}
	
	@Override
	public void onConfiguring(IPipe pipe) {
		onStart(pipe);
		qnjh();// 全年计划
		jdjh();// 季度计划
		xjdjh();// 下季度计划
		dyjh();// 当月计划
		dysj();// 当月实际
		jhwcl();// 计划完成率
		qntq();// 去年同期
		tbzf();// 同比增幅
		jdlj();// 季度累计
		jdjhwcl();// 季度计划完成率
		jdqntq();// 季度去年同期
		jdtbzf();// 同比增幅
		ndlj();// 年度累计
		ndljjhwcl();// 累计计划完成率
		ndljqntq();// 去年同期
		ndljtbzf();// 同比增幅
		xjdsyyj();// 下季度首月预计
		xjdcyyj();// 下季度次月预计
		xjdmyyj();// 下季度末月预计
		xjdyjhj();// 下季度预计合计
		xjdyjwcl();// 下季度预计完成率
		xjdndlj();// 下季度年度累计
		xjdndljwcl();// 下季度年度累计完成率
		xjdqntqndlj();// 下季度去年同期年度累计
		xjdndljtbzf();// 下季度年度累计同比增幅
		xjdqnjh();// 下季度全年计划
		pipe.addFilter(onFinished());
	}

	abstract protected void onStart(IPipe pipe);

	abstract protected void qnjh();

	abstract protected void jdjh();

	abstract protected void xjdjh();
	
	abstract protected void dyjh();

	abstract protected void dysj();
	
	abstract protected void jhwcl();

	abstract protected void qntq();

	abstract protected void tbzf();

	abstract protected void jdlj();

	abstract protected void jdjhwcl();

	abstract protected void jdqntq();

	abstract protected void jdtbzf();
	
	abstract protected void ndlj();

	abstract protected void ndljjhwcl();

	abstract protected void ndljqntq();

	abstract protected void ndljtbzf();

	abstract protected void xjdsyyj();

	abstract protected void xjdcyyj();

	abstract protected void xjdmyyj();

	abstract protected void xjdyjhj();

	abstract protected void xjdyjwcl();

	abstract protected void xjdndlj();

	abstract protected void xjdndljwcl();

	abstract protected void xjdqntqndlj();

	abstract protected void xjdndljtbzf();

	abstract protected void xjdqnjh();
	
	abstract protected List<IPipeFilter> onFinished();

	

}
