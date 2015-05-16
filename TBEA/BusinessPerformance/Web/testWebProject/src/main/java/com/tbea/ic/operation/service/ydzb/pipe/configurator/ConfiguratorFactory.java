package com.tbea.ic.operation.service.ydzb.pipe.configurator;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.ydzb.pipe.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.pipe.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.jhlr.JhlrDataConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.jhlr.JhlrRankConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.jxjl.JxjlDataConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.jxjl.JxjlRankConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.ljlr.LjlrDataConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.ljlr.LjlrRankConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.rjlr.RjlrDataConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.rjlr.RjlrRankConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.first.FirstSeasonPredictionCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.first.FirstSeasonPredictionConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.second.SecondSeasonPredictionCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.second.SecondSeasonPredictionConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.third.ThirdSeasonPredictionCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.third.ThirdSeasonPredictionConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.srqy.SrqyConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.ydhb.YdhbConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.ztzb.ZtzbCompositeConfigurator;

public class ConfiguratorFactory {
	IPipeConfigurator standardConfigurator;
	IPipeConfigurator srqyConfigurator;
	IPipeConfigurator firstSeasonPredictionConfigurator;
	IPipeConfigurator secondSeasonPredictionConfigurator;
	IPipeConfigurator thirdSeasonPredictionConfigurator;
	IPipeConfigurator ydhbConfigurator;
	IPipeConfigurator jhlrDataConfigurator;
	IPipeConfigurator jhlrRankConfigurator = new JhlrRankConfigurator();
	IPipeConfigurator jxjlDataConfigurator;
	IPipeConfigurator jxjlRankConfigurator = new JxjlRankConfigurator((JhlrRankConfigurator) jhlrRankConfigurator);
	IPipeConfigurator ljlrDataConfigurator;
	IPipeConfigurator ljlrRankConfigurator = new LjlrRankConfigurator((JhlrRankConfigurator) jhlrRankConfigurator);
	IPipeConfigurator rjlrDataConfigurator;
	IPipeConfigurator rjlrRankConfigurator = new RjlrRankConfigurator();

	
	public ConfiguratorFactory(SbdNdjhZbDao sbdzbDao,
			AccumulatorFactory accFac, CompanyManager companyManager) {
		standardConfigurator = new StandardConfigurator(sbdzbDao,
				accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc());
		firstSeasonPredictionConfigurator = new FirstSeasonPredictionConfigurator(
				sbdzbDao, accFac.getSjAcc(), accFac.getYjhAcc(),
				accFac.getNjhAcc());
		secondSeasonPredictionConfigurator = new SecondSeasonPredictionConfigurator(
				sbdzbDao, accFac.getSjAcc(), accFac.getYjhAcc(),
				accFac.getNjhAcc());
		thirdSeasonPredictionConfigurator = new ThirdSeasonPredictionConfigurator(sbdzbDao,
				accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc());
		ydhbConfigurator = new YdhbConfigurator(sbdzbDao, accFac.getSjAcc(),
				accFac.getYjhAcc(), accFac.getNjhAcc());
		srqyConfigurator = new SrqyConfigurator(accFac.getSjAcc(),
				accFac.getYjhAcc(), accFac.getNjhAcc(), companyManager);
		jhlrDataConfigurator = new JhlrDataConfigurator(accFac.getSjAcc(),
				accFac.getYjhAcc(), accFac.getNjhAcc());
		jxjlDataConfigurator = new JxjlDataConfigurator((JhlrDataConfigurator) jhlrDataConfigurator);
		ljlrDataConfigurator = new LjlrDataConfigurator(accFac.getSjAcc(),
				accFac.getYjhAcc(), accFac.getNjhAcc());
		rjlrDataConfigurator = new RjlrDataConfigurator(sbdzbDao, accFac.getSjAcc(),
				accFac.getYjhAcc(), accFac.getNjhAcc());
	}

	/**
	 * @return the rjlrDataConfigurator
	 */
	public IPipeConfigurator getRjlrDataConfigurator() {
		return rjlrDataConfigurator;
	}

	/**
	 * @return the rjlrRankConfigurator
	 */
	public IPipeConfigurator getRjlrRankConfigurator() {
		return rjlrRankConfigurator;
	}

	public IPipeConfigurator getJxjlDataConfigurator() {
		return jxjlDataConfigurator;
	}

	public IPipeConfigurator getJxjlRankConfigurator() {
		return jxjlRankConfigurator;
	}

	public IPipeConfigurator getLjlrDataConfigurator() {
		return ljlrDataConfigurator;
	}

	public IPipeConfigurator getLjlrRankConfigurator() {
		return ljlrRankConfigurator;
	}

	public IPipeConfigurator getJhlrDataConfigurator() {
		return jhlrDataConfigurator;
	}

	public IPipeConfigurator getJhlrRankConfigurator() {
		return jhlrRankConfigurator;
	}

	public IPipeConfigurator getStandardConfigurator() {
		return standardConfigurator;
	}

	public IPipeConfigurator getSrqyConfigurator() {
		return srqyConfigurator;
	}

	public IPipeConfigurator getFirstSeasonPredictionConfigurator() {
		return firstSeasonPredictionConfigurator;
	}

	public IPipeConfigurator getSecondSeasonPredictionConfigurator() {
		return secondSeasonPredictionConfigurator;
	}

	public IPipeConfigurator getThirdSeasonPredictionConfigurator() {
		return thirdSeasonPredictionConfigurator;
	}

	public IPipeConfigurator getYdhbConfigurator() {
		return ydhbConfigurator;
	}

	public IPipeConfigurator getZtzbCompositeConfigurator(IAccumulator acc,
			CompositeAccDataSource cads,
			Map<CompanyType, List<Company>> computeMap) {
		return new ZtzbCompositeConfigurator(acc, cads, computeMap);
	}

	public IPipeConfigurator getThirdSeasonPredictionCompositeConfigurator(IAccumulator acc,
			CompositeAccDataSource cads,
			Map<CompanyType, List<Company>> computeMap) {
		return new ThirdSeasonPredictionCompositeConfigurator(acc, cads, computeMap);
	}

	public IPipeConfigurator getSecondSeasonPredictionCompositeConfigurator(
			IAccumulator acc, CompositeAccDataSource cads,
			Map<CompanyType, List<Company>> computeMap) {
		return new SecondSeasonPredictionCompositeConfigurator(acc, cads,
				computeMap);
	}

	public IPipeConfigurator getFirstSeasonPredictionCompositeConfigurator(
			IAccumulator acc, CompositeAccDataSource cads,
			Map<CompanyType, List<Company>> computeMap) {
		return new FirstSeasonPredictionCompositeConfigurator(acc, cads,
				computeMap);
	}

}
