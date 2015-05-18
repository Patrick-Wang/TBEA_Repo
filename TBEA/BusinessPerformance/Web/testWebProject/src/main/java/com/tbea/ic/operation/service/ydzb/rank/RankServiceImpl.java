package com.tbea.ic.operation.service.ydzb.rank;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.service.ydzb.pipe.AdvancedPipe;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.ConfiguratorFactory;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.IPipeConfigurator;


@Service
@Transactional("transactionManager")
public class RankServiceImpl implements RankService {

	@Autowired
	SbdNdjhZbDao sbdNdjhzbDao;
	
	@Autowired
	NDJHZBDao ndjhzbDao;

	@Autowired
	YDJHZBDao ydjhzbDao;

	@Autowired
	YDZBZTDao ydzbztDao;

	@Autowired
	SJZBDao sjzbDao;

	@Autowired
	YJ20ZBDao yj20zbDao;

	@Autowired
	YJ28ZBDao yj28zbDao;

	@Autowired
	ZBXXDao zbxxDao;

	@Autowired
	DWXXDao dwxxDao;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	AccumulatorFactory accFac;
	
	ConfiguratorFactory configFac;

	@Autowired
	public void init() {
		accFac = new AccumulatorFactory(sjzbDao, yj20zbDao, yj28zbDao, ydzbztDao, ydjhzbDao, ndjhzbDao);
		configFac = new ConfiguratorFactory(sbdNdjhzbDao, accFac, companyManager);
	}
	
	private ConfiguratorFactory getConfiguratorFactory() {
		return configFac;
	}
	
	private List<String[]> makeResult(List<Double[]> values) {
		List<String[]> result = new ArrayList<String[]>();

		for (int i = 0; i < values.size(); ++i) {
			result.add(new String[values.get(i).length]);
			for (int j = 0; j < values.get(i).length; ++j) {
				if (values.get(i)[j] != null){
					result.get(i)[j] = values.get(i)[j] + "";
				}
			}
		}
		return result;
	}

	
	private List<Double[]> getRank(GSZB zb, Date date, IPipeConfigurator dwPipeConfig, IPipeConfigurator dataConfig){
		AdvancedPipe pipe = new AdvancedPipe(zb.getValue(), date, dwPipeConfig);
		List<Company> jydw = BMDepartmentDB.getJydw(companyManager);
		for (Company comp : jydw){
			pipe.add(comp, dataConfig);
		}
		return pipe.getData();
		
	}
	
	@Override
	public List<String[]> getJhlrRank(Date date) {
		List<Double[]> ret = getRank(GSZB.LRZE, date, getConfiguratorFactory().getJhlrRankConfigurator(), getConfiguratorFactory().getJhlrDataConfigurator());
		return makeResult(ret);
	}
	
	@Override
	public List<String[]> getLjlrRank(Date date) {
		List<Double[]> ret = getRank(GSZB.LRZE, date, getConfiguratorFactory().getLjlrRankConfigurator(), getConfiguratorFactory().getLjlrDataConfigurator());
		return makeResult(ret);
	}
	
	@Override
	public List<String[]> getJxjlRank(Date date) {
		List<Double[]> ret = getRank(GSZB.JYXJXJL, date, getConfiguratorFactory().getJxjlRankConfigurator(), getConfiguratorFactory().getJxjlDataConfigurator());
		return makeResult(ret);
	}

	@Override
	public List<String[]> getRjlrRank(Date date) {
		AdvancedPipe pipe = new AdvancedPipe(GSZB.RJLR.getValue(), date, getConfiguratorFactory().getRjlrRankConfigurator());
		List<Company> jydw = BMDepartmentDB.getJydw(companyManager);
		for (Company comp : jydw){
			pipe.add(comp, getConfiguratorFactory().getRjlrDataConfigurator());
		}
		pipe.add(GSZB.LRZE.getValue());
		pipe.add(GSZB.RS.getValue());
		return makeResult(pipe.getData());
	}

}
