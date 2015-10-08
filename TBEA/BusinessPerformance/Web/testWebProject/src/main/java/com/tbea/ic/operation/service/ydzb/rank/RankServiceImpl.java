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
import com.tbea.ic.operation.service.util.pipe.core.CompositePipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.ConfiguratorFactory;


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
		CompositePipe pipe = new CompositePipe(zb.getValue(), date, dwPipeConfig);
		List<Company> jydw = BMDepartmentDB.getMainlyJydw(companyManager);
		for (Company comp : jydw){
			pipe.addCompany(comp, dataConfig);
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
		CompositePipe pipe = new CompositePipe(GSZB.RJLR.getValue(), date, getConfiguratorFactory().getRjlrRankConfigurator());
		List<Company> jydw = BMDepartmentDB.getMainlyJydw(companyManager);
		for (Company comp : jydw){
			pipe.addCompany(comp, getConfiguratorFactory().getRjlrDataConfigurator());
		}
		pipe.addDependentIndictor(GSZB.LRZE.getValue());
		pipe.addDependentIndictor(GSZB.RS.getValue());
		return makeResult(pipe.getData());
	}

	@Override
	public List<String[]> getRjsrRank(Date date) {
		CompositePipe pipe = new CompositePipe(GSZB.RJSR.getValue(), date, getConfiguratorFactory().getRjlrRankConfigurator());
		List<Company> jydw = BMDepartmentDB.getMainlyJydw(companyManager);
		for (Company comp : jydw){
			pipe.addCompany(comp, getConfiguratorFactory().getRjlrDataConfigurator());
		}
		pipe.addDependentIndictor(GSZB.XSSR.getValue());
		pipe.addDependentIndictor(GSZB.RS.getValue());
		return makeResult(pipe.getData());
	}
	
	@Override
	public List<String[]> getXmgsRjsrRank(Date date) {
		CompositePipe pipe = new CompositePipe(GSZB.RJSR.getValue(), date, getConfiguratorFactory().getRjlrRankConfigurator());
		List<Company> jydw = BMDepartmentDB.getMainlyJydw(companyManager);
		List<String> compsName = new ArrayList<String>();
		for (Company comp : jydw){
			for (Company xmgs : comp.getSubCompanies()){
				pipe.addCompany(xmgs, getConfiguratorFactory().getRjlrDataConfigurator());
				compsName.add(xmgs.getName());
			}
		}
		pipe.addDependentIndictor(GSZB.XSSR.getValue());
		pipe.addDependentIndictor(GSZB.RS.getValue());
		return makeResult(compsName, pipe.getData());
	}
	
	@Override
	public List<String[]> getXmgsRjlrRank(Date date) {
		CompositePipe pipe = new CompositePipe(GSZB.RJLR.getValue(), date, getConfiguratorFactory().getRjlrRankConfigurator());
		List<Company> jydw = BMDepartmentDB.getMainlyJydw(companyManager);
		List<String> compsName = new ArrayList<String>();
		for (Company comp : jydw){
			for (Company xmgs : comp.getSubCompanies()){
				pipe.addCompany(xmgs, getConfiguratorFactory().getRjlrDataConfigurator());
				compsName.add(xmgs.getName());
			}
		}
		pipe.addDependentIndictor(GSZB.LRZE.getValue());
		pipe.addDependentIndictor(GSZB.RS.getValue());
		return makeResult(compsName, pipe.getData());
	}

	private List<String[]> makeResult(List<String> compsName,
			List<Double[]> data) {
		List<String[]> result = new ArrayList<String[]>();

		for (int i = 0; i < data.size(); ++i) {
			result.add(new String[data.get(i).length + 1]);
			result.get(i)[0] = compsName.get(i);
			for (int j = 0; j < data.get(i).length; ++j) {
				if (data.get(i)[j] != null){
					result.get(i)[j + 1] = data.get(i)[j] + "";
				}
			}
		}
		return result;
	}

	@Override
	public List<String[]> getXmgsJhlrRank(Date date) {
		CompositePipe pipe = new CompositePipe(GSZB.LRZE.getValue(), date, getConfiguratorFactory().getJhlrRankConfigurator());
		List<Company> jydw = BMDepartmentDB.getMainlyJydw(companyManager);
		List<String> compsName = new ArrayList<String>();
		for (Company comp : jydw){
			for (Company xmgs : comp.getSubCompanies()){
				pipe.addCompany(xmgs, getConfiguratorFactory().getJhlrDataConfigurator());
				compsName.add(xmgs.getName());
			}
		}
		return makeResult(compsName, pipe.getData());
	}
}
