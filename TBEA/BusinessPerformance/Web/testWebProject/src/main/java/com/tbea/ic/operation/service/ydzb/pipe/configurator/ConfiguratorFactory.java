package com.tbea.ic.operation.service.ydzb.pipe.configurator;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.jhlr.JhlrDataConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.jhlr.JhlrRankConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.jxjl.JxjlDataConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.jxjl.JxjlRankConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.ljlr.LjlrDataConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.ljlr.LjlrRankConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.rjlr.RjlrDataConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.rjlr.RjlrRankConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.first.FirstSeasonPredictionCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.first.FirstSeasonPredictionConfiguratorProxy;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.second.SecondSeasonPredictionCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.second.SecondSeasonPredictionConfiguratorProxy;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.third.ThirdSeasonPredictionCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.third.ThirdSeasonPredictionConfiguratorProxy;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.srqy.SrqyConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.standard.StandardConfiguratorProxy;
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
	IPipeConfigurator ysAndBlzbRankConfigurator;
	IPipeConfigurator ysAndBlzbDataConfigurator;
	IPipeConfigurator ysAndChzbDataConfigurator;
	IPipeConfigurator ysAndChzbRankConfigurator;
	IPipeConfigurator yszbDataConfigurator;
	IPipeConfigurator yszbRankConfigurator;
	IPipeConfigurator chzbDataConfigurator;
	IPipeConfigurator chzbRankConfigurator;

	
	public ConfiguratorFactory(SbdNdjhZbDao sbdzbDao,
			AccumulatorFactory accFac, CompanyManager companyManager) {
		standardConfigurator = new StandardConfiguratorProxy(sbdzbDao,
				accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc());
		
		firstSeasonPredictionConfigurator = new FirstSeasonPredictionConfiguratorProxy(
				sbdzbDao, accFac.getSjAcc(), accFac.getYjhAcc(),
				accFac.getNjhAcc());
		
		secondSeasonPredictionConfigurator = new SecondSeasonPredictionConfiguratorProxy(
				sbdzbDao, accFac.getSjAcc(), accFac.getYjhAcc(),
				accFac.getNjhAcc());
		
		thirdSeasonPredictionConfigurator = new ThirdSeasonPredictionConfiguratorProxy(sbdzbDao,
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

	public IPipeConfigurator getZtzbCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		return new ZtzbCompositeConfigurator(computeMap);
	}

	public IPipeConfigurator getThirdSeasonPredictionCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		return new ThirdSeasonPredictionCompositeConfigurator(computeMap);
	}

	public IPipeConfigurator getSecondSeasonPredictionCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		return new SecondSeasonPredictionCompositeConfigurator(
				computeMap);
	}

	public IPipeConfigurator getFirstSeasonPredictionCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		return new FirstSeasonPredictionCompositeConfigurator(
				computeMap);
	}

	public IPipeConfigurator getYsAndBlzbRankConfigurator() {
		return ysAndBlzbRankConfigurator;
	}

	public IPipeConfigurator getYsAndBlzbDataConfigurator() {
		return ysAndBlzbDataConfigurator;
	}

	public IPipeConfigurator getYsAndChzbDataConfigurator() {
		return ysAndChzbDataConfigurator;
	}

	public IPipeConfigurator getYsAndChzbRankConfigurator() {
		return ysAndChzbRankConfigurator;
	}

	public IPipeConfigurator getYszbDataConfigurator() {
		return yszbDataConfigurator;
	}

	public IPipeConfigurator getYszbRankConfigurator() {
		return yszbRankConfigurator;
	}

	public IPipeConfigurator getChzbDataConfigurator() {
		return chzbDataConfigurator;
	}

	public IPipeConfigurator getChzbRankConfigurator() {
		return chzbRankConfigurator;
	}

}
