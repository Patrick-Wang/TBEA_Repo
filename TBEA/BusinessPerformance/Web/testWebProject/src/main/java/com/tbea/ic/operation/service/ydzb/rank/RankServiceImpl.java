package com.tbea.ic.operation.service.ydzb.rank;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
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
import com.tbea.ic.operation.service.ydzb.pipe.filter.composite.DoubleArrayComparator;
import com.util.tools.MathUtil;


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

	List<String> jydwNames;
	List<Company> jydws;
	
	@Autowired
	public void init() {
		accFac = new AccumulatorFactory(sjzbDao, yj20zbDao, yj28zbDao, ydzbztDao, ydjhzbDao, ndjhzbDao);
		configFac = new ConfiguratorFactory(sbdNdjhzbDao, accFac, companyManager);
		jydws = BMDepartmentDB.getMainlyJydw(companyManager);
		jydwNames = getCompanyNames(jydws);
	}
	
//	private ConfiguratorFactory configFac {
//		return configFac;
//	}
	
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

	
	private List<Double[]> getRank(GSZB zb, Date date, IPipeConfigurator rankPipeConfig, IPipeConfigurator dataConfig){
		CompositePipe pipe = new CompositePipe(zb.value(), date, rankPipeConfig);
		for (Company comp : jydws){
			pipe.addCompany(comp, dataConfig);
		}
		return pipe.getData();
	}
	
	@Override
	public List<String[]> getJhlrRank(Date date) {
		List<Double[]> ret = getRank(GSZB.LRZE1, date, configFac.getJhlrRankConfigurator(), configFac.getJhlrDataConfigurator());
		return makeResult(ret);
	}
	
	@Override
	public List<String[]> getLjlrRank(Date date) {
		List<Double[]> ret = getRank(GSZB.LRZE1, date, configFac.getLjlrRankConfigurator(), configFac.getLjlrDataConfigurator());
		return makeResult(ret);
	}
	
	@Override
	public List<String[]> getJxjlRank(Date date) {
		List<Double[]> ret = getRank(GSZB.JYXJXJL29, date, configFac.getJxjlRankConfigurator(), configFac.getJxjlDataConfigurator());
		return makeResult(ret);
	}

	@Override
	public List<String[]> getRjlrRank(Date date) {
		CompositePipe pipe = new CompositePipe(GSZB.RJLR62.value(), date, configFac.getRjlrRankConfigurator());
		//List<Company> jydw = BMDepartmentDB.getMainlyJydw(companyManager);
		for (Company comp : jydws){
			pipe.addCompany(comp, configFac.getRjlrDataConfigurator());
		}
		pipe.addDependentIndictor(GSZB.LRZE1.value());
		pipe.addDependentIndictor(GSZB.RS61.value());
		return makeResult(pipe.getData());
	}

	@Override
	public List<String[]> getRjsrRank(Date date) {
		CompositePipe pipe = new CompositePipe(GSZB.RJSR63.value(), date, configFac.getRjlrRankConfigurator());
		//List<Company> jydw = BMDepartmentDB.getMainlyJydw(companyManager);
		for (Company comp : jydws){
			pipe.addCompany(comp, configFac.getRjlrDataConfigurator());
		}
		pipe.addDependentIndictor(GSZB.XSSR6.value());
		pipe.addDependentIndictor(GSZB.RS61.value());
		return makeResult(pipe.getData());
	}
	
	@Override
	public List<String[]> getXmgsRjsrRank(Date date) {
		CompositePipe pipe = new CompositePipe(GSZB.RJSR63.value(), date, configFac.getRjlrRankConfigurator());
		//List<Company> jydw = BMDepartmentDB.getMainlyJydw(companyManager);
		List<String> compsName = new ArrayList<String>();
		for (Company comp : jydws){
			for (Company xmgs : comp.getSubCompanies()){
				pipe.addCompany(xmgs, configFac.getRjlrDataConfigurator());
				compsName.add(xmgs.getName());
			}
		}
		pipe.addDependentIndictor(GSZB.XSSR6.value());
		pipe.addDependentIndictor(GSZB.RS61.value());
		return makeResult(compsName, pipe.getData());
	}
	
	@Override
	public List<String[]> getXmgsRjlrRank(Date date) {
		CompositePipe pipe = new CompositePipe(GSZB.RJLR62.value(), date, configFac.getRjlrRankConfigurator());
		//List<Company> jydw = BMDepartmentDB.getMainlyJydw(companyManager);
		List<String> compsName = new ArrayList<String>();
		for (Company comp : jydws){
			for (Company xmgs : comp.getSubCompanies()){
				pipe.addCompany(xmgs, configFac.getRjlrDataConfigurator());
				compsName.add(xmgs.getName());
			}
		}
		pipe.addDependentIndictor(GSZB.LRZE1.value());
		pipe.addDependentIndictor(GSZB.RS61.value());
		return makeResult(compsName, pipe.getData());
	}

	private List<String[]> makeResult(List<String> compsName,
			List<Double[]> data) {
		if (!data.isEmpty()){
			return makeResult(compsName, data, data.get(0).length);
		}
		return null;
	}
	
	private List<String[]> makeResult(List<String> compsName,
			List<Double[]> data, int columnSize) {
		List<String[]> result = new ArrayList<String[]>();

		for (int i = 0; i < data.size(); ++i) {
			result.add(new String[data.get(i).length + 1]);
			result.get(i)[0] = compsName.get(i);
			for (int j = 0; j < columnSize; ++j) {
				if (data.get(i)[j] != null){
					result.get(i)[j + 1] = data.get(i)[j] + "";
				}
			}
		}
		return result;
	}

//	static private Set<String> excludedComps = new HashSet<String>();
//	static
//	{
//		excludedComps.add("新疆众和动力保障公司");
//		excludedComps.add("特变电工股份有限公司能源动力分公司动力厂");
//		excludedComps.add("特变电工新疆变压器厂国际公司");
//		excludedComps.add("新疆天池能源销售有限公司");
//		excludedComps.add("西北电线电缆检测中心有限公司");
//		excludedComps.add("服务公司");
//		excludedComps.add("特变电工股份有限公司能源动力分公司总配电车间");
//		excludedComps.add("新疆中特国际物流有限公司");
//		excludedComps.add("新疆新特国际物流贸易公司");
//		excludedComps.add("特变电工新疆新能源股份有限公司国际部");
//		excludedComps.add("特变电工沈阳变压器集团有限公司国际贸易成套分公司");
//	};
	
	@Override
	public List<String[]> getXmgsJhlrRank(Date date) {
		CompositePipe pipe = new CompositePipe(GSZB.LRZE1.value(), date, configFac.getJhlrRankConfigurator());
		//List<Company> jydw = BMDepartmentDB.getMainlyJydw(companyManager);
		List<String> compsName = new ArrayList<String>();
		for (Company comp : jydws){
			for (Company xmgs : comp.getSubCompanies()){
//				if (!excludedComps.contains(xmgs.getName())){
					pipe.addCompany(xmgs, configFac.getJhlrDataConfigurator());
					compsName.add(xmgs.getName());
//				}
			}
		}
		return makeResult(compsName, pipe.getData());
	}

	
	private List<String> getCompanyNames(List<Company> companies){
		List<String> compNames = new ArrayList<String>();
		for (Company comp : companies){
			compNames.add(comp.getName());
		}
		return compNames;
	}
	
	private Double getZbValues(Company comp, Integer zb, Date start, Date end){
		List<Integer> zbs = new ArrayList<Integer>();
		zbs.add(zb);
		List<Company> companies = new ArrayList<Company>();
		companies.add(comp);
		return accFac.getSjAcc().compute(0, start, end, zbs, companies).get(0);
	}
	
	@Override
	public List<String[]> getYszkzsrbRank(Date date) {		
		DateHelper dh = new DateHelper(date);
		Date end = date;
		Date start = dh.getFirstMonth();
		
		List<Double[]> results = new ArrayList<Double[]>();
		List<Double[]> resultTemp = new ArrayList<Double[]>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for (int i = 0; i < jydws.size(); ++i){
			results.add(new Double[4]);
			resultTemp.add(results.get(i));
			results.get(i)[0] = (getZbValues(jydws.get(i), GSZB.XSSR6.value(), start, end) / (cal.get(Calendar.MONTH) + 1)) * 12;
			results.get(i)[1] = getZbValues(jydws.get(i), GSZB.YSZK32.value(), end, end);
			if (results.get(i)[0] != null && MathUtil.isPositive(results.get(i)[0]) &&
				results.get(i)[1] != null && MathUtil.isPositive(results.get(i)[1])){
				results.get(i)[2] = results.get(i)[1] / results.get(i)[0];
			}
		}
		
		resultTemp.sort(new DoubleArrayComparator().setIndex(2).desc());
		
		for (int i = 0, size = resultTemp.size(); i < size; ++i){
			resultTemp.get(i)[3] = (double) (size - i);
		}
		
		return makeResult(jydwNames, results);
	}

	@Override
	public List<String[]> getYszkAndBlzbRank(Date date) {
		DateHelper dh = new DateHelper(date);
		Date end = date;
		Date start = dh.getFirstMonth();
		List<Double[]> results = new ArrayList<Double[]>();
		List<Double[]> resultTemp = new ArrayList<Double[]>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for (int i = 0; i < jydws.size(); ++i){
			results.add(new Double[5]);
			results.get(i)[0] = (getZbValues(jydws.get(i), GSZB.XSSR6.value(), start, end)/ (cal.get(Calendar.MONTH) + 1)) * 12;
			results.get(i)[1] = getZbValues(jydws.get(i), GSZB.YSZK32.value(), end, end);
			results.get(i)[2] = getZbValues(jydws.get(i), GSZB.BL34.value(), end, end);
			if (!(results.get(i)[1] == null && results.get(i)[2] == null)){
				Double tmpVal = Util.valueOf(results.get(i)[1]) + Util.valueOf(results.get(i)[2]);
				if (MathUtil.isPositive(tmpVal) && results.get(i)[0] != null && MathUtil.isPositive(results.get(i)[0])){
					results.get(i)[3] = tmpVal / results.get(i)[0];
				}
			}
			
			resultTemp.add(results.get(i));
		}
		
		resultTemp.sort(new DoubleArrayComparator().setIndex(3).desc());
		
		for (int i = 0, size = resultTemp.size(); i < size; ++i){
			resultTemp.get(i)[4] = (double) (size - i);
		}
		
		return makeResult(jydwNames, results);
	}

	@Override
	public List<String[]> getChzbRank(Date date) {
		DateHelper dh = new DateHelper(date);
		Date end = date;
		Date start = dh.getFirstMonth();
		
		List<Double[]> results = new ArrayList<Double[]>();
		List<Double[]> resultTemp = new ArrayList<Double[]>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for (int i = 0; i < jydws.size(); ++i){
			results.add(new Double[4]);
			results.get(i)[0] = (getZbValues(jydws.get(i), GSZB.XSSR6.value(), start, end) / (cal.get(Calendar.MONTH) + 1)) * 12;
			results.get(i)[1] = getZbValues(jydws.get(i), GSZB.CH35.value(), end, end);
			if (results.get(i)[0] != null && MathUtil.isPositive(results.get(i)[0]) &&
					results.get(i)[1] != null && MathUtil.isPositive(results.get(i)[1])){
					results.get(i)[2] = results.get(i)[1] / results.get(i)[0];
				}
			resultTemp.add(results.get(i));
		}
		
		resultTemp.sort(new DoubleArrayComparator().setIndex(2).desc());
		
		for (int i = 0, size = resultTemp.size(); i < size; ++i){
			resultTemp.get(i)[3] = (double) (size - i);
		}
		
		return makeResult(jydwNames, results);
	}

	@Override
	public List<String[]> getYsAndChzbRank(Date date) {

		DateHelper dh = new DateHelper(date);
		Date end = date;
		Date start = dh.getFirstMonth();
		
		List<Double[]> results = new ArrayList<Double[]>();
		List<Double[]> resultTemp = new ArrayList<Double[]>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for (int i = 0; i < jydws.size(); ++i){
			results.add(new Double[5]);
			results.get(i)[0] = (getZbValues(jydws.get(i), GSZB.XSSR6.value(), start, end)/ (cal.get(Calendar.MONTH) + 1)) * 12;
			results.get(i)[1] = getZbValues(jydws.get(i), GSZB.YSZK32.value(), end, end);
			results.get(i)[2] = getZbValues(jydws.get(i), GSZB.CH35.value(), end, end);
			if (!(results.get(i)[1] == null && results.get(i)[2] == null)){
				Double tmpVal = Util.valueOf(results.get(i)[1]) + Util.valueOf(results.get(i)[2]);
				if (MathUtil.isPositive(tmpVal) && results.get(i)[0] != null && MathUtil.isPositive(results.get(i)[0])){
					results.get(i)[3] = tmpVal / results.get(i)[0];
				}
			}
			resultTemp.add(results.get(i));
			
		}
		
		resultTemp.sort(new DoubleArrayComparator().setIndex(3).desc());
		
		for (int i = 0, size = resultTemp.size(); i < size; ++i){
			resultTemp.get(i)[4] = (double) (size - i);
		}
		
		return makeResult(jydwNames, results);
	}
}
