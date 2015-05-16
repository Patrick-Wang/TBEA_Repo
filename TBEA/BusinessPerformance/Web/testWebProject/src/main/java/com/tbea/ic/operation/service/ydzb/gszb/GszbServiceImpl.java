package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.service.ydzb.pipe.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.ydzb.pipe.ComplexPipe;
import com.tbea.ic.operation.service.ydzb.pipe.SimplePipe;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.pipe.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.ConfiguratorFactory;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.srqy.SrqyConfigurator;


@Service
@Transactional("transactionManager")
public class GszbServiceImpl implements GszbService {

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
	CompanyManager companyManager;

	AccumulatorFactory accFac;

	ConfiguratorFactory configFac;

	private static List<Integer> gsztzbs = new ArrayList<Integer>();
	static {
		gsztzbs.add(GSZB.LRZE.getValue());
		gsztzbs.add(GSZB.XSSR.getValue());
		gsztzbs.add(GSZB.JYXJXJL.getValue());
		gsztzbs.add(GSZB.YSZK.getValue());
		gsztzbs.add(GSZB.QZYQK.getValue());
		gsztzbs.add(GSZB.BL.getValue());
		gsztzbs.add(GSZB.CH.getValue());
		gsztzbs.add(GSZB.QZJYWY.getValue());
		gsztzbs.add(GSZB.HTQYE.getValue());
		gsztzbs.add(GSZB.ZJHL.getValue());
		gsztzbs.add(GSZB.BHSCZ.getValue());
		gsztzbs.add(GSZB.RS.getValue());
		gsztzbs.add(GSZB.RJLR.getValue());
		gsztzbs.add(GSZB.RJSR.getValue());
		gsztzbs.add(GSZB.SXFY.getValue());
		gsztzbs.add(GSZB.SXFYL.getValue());
		//gsztzbs.add(GSZB.JZCSYL.getValue());
	}

	private static List<Integer> topfivezbs = new ArrayList<Integer>();
	static {
		topfivezbs.add(GSZB.LRZE.getValue());
		topfivezbs.add(GSZB.XSSR.getValue());
		topfivezbs.add(GSZB.JYXJXJL.getValue());
		topfivezbs.add(GSZB.YSZK.getValue());
		topfivezbs.add(GSZB.CH.getValue());
	}

	
	private static Map<CompanyType, List<Company>> gdwTop5ComputeMap = new HashMap<CompanyType, List<Company>>();
	private static Map<CompanyType, List<Company>> gcyTop5ComputeMap = new HashMap<CompanyType, List<Company>>();


	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager mgr){
		companyManager = mgr;
		Organization org = mgr.getBMDBOrganization();
		List<Company> companies = new ArrayList<Company>();
		gcyTop5ComputeMap.put(CompanyType.GFGS, companies);
		companies.add(org.getCompany(CompanyType.SBDCYJT));
		companies.add(org.getCompany(CompanyType.XNYSYB));
		companies.add(org.getCompany(CompanyType.NYSYB));
		companies.add(org.getCompany(CompanyType.JCKGS_SYB));
		companies.add(org.getCompany(CompanyType.GJGCGS_SYB));
		companies = new ArrayList<Company>();
		gcyTop5ComputeMap.put(CompanyType.GJB, companies);
		companies.add(org.getCompany(CompanyType.GFGS));
		companies.add(org.getCompany(CompanyType.ZHGS_SYB));

		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(CompanyType.SBDCYJT, companies);
		companies.add(org.getCompany(CompanyType.SBGS));
		companies.add(org.getCompany(CompanyType.HBGS));
		companies.add(org.getCompany(CompanyType.XBC));
		companies.add(org.getCompany(CompanyType.LLGS));
		companies.add(org.getCompany(CompanyType.XLC));
		companies.add(org.getCompany(CompanyType.DLGS));
		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(CompanyType.XNYSYB, companies);
		companies.add(org.getCompany(CompanyType.XTNYGS));
		companies.add(org.getCompany(CompanyType.XNYGS));
		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(CompanyType.NYSYB, companies);
		companies.add(org.getCompany(CompanyType.TCNY));
		companies.add(org.getCompany(CompanyType.NDGS));
		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(CompanyType.GJGCGS_SYB, companies);
		companies.add(org.getCompany(CompanyType.JCKGS_JYDW));
		companies.add(org.getCompany(CompanyType.GJGCGS_GFGS));
		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(CompanyType.GFGS, companies);
		companies.add(org.getCompany(CompanyType.SBDCYJT));
		companies.add(org.getCompany(CompanyType.XNYSYB));
		companies.add(org.getCompany(CompanyType.NYSYB));
		companies.add(org.getCompany(CompanyType.GJGCGS_SYB));
		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(CompanyType.GJB, companies);
		companies.add(org.getCompany(CompanyType.ZHGS));
		companies.add(org.getCompany(CompanyType.GFGS));
	}
	
	
	
	// 公司整体2（收入签约分结构）
	private static List<Integer> srqyzbs = new ArrayList<Integer>();
	static {
		// 销售收入
		srqyzbs.add(GSZB.XSSR.getValue());// 营业收入
		srqyzbs.add(GSZB.ZZYSR.getValue());// 制造业收入（输变电收入、新能源收入、能源煤炭收入、众和制造业收入）
		srqyzbs.add(GSZB.GCXMSR.getValue());// 工程项目收入（输变电收入、新能源产业成套工程收入、能源产业工程收入、进出口公司收入、国际工程公司收入、众和公司收入）
		srqyzbs.add(GSZB.YYSSR.getValue());// 运营商收入（新能源产业运营商收入）
		srqyzbs.add(GSZB.MTXSSR.getValue());// 煤炭销售收入（能源产业收入）
		srqyzbs.add(GSZB.WLMYSR.getValue());// 物流贸易收入（输变电物流收入、能源产业贸易收入、众和公司物流业收入）
		srqyzbs.add(GSZB.FWLSR.getValue());// 服务类收入（新能源产业服务业收入、能源产业服务业收入、众和公司服务业收入）

		// 合同签约额
		srqyzbs.add(GSZB.HTQYE.getValue());// 合同签约额
		srqyzbs.add(GSZB.GJQY.getValue());// 输变电、进出口国际签约
		srqyzbs.add(GSZB.QZDJQY.getValue());// 单机签约（输变电国际单机签约）
		srqyzbs.add(GSZB.QZCTQY.getValue());// 成套签约（输变电国际成套签约、新能源产业国际成套签约、进出口公司国际成套签约、国际工程公司国际成套签约）
		srqyzbs.add(GSZB.GNQY.getValue());// 输变电国内签约
		srqyzbs.add(GSZB.QZDJQY_1.getValue());// 单机签约（输变电国内单机签约）
		srqyzbs.add(GSZB.QZCTQY_1.getValue());// 成套签约（输变电国内成套签约）
		// 新能源产业签约
		// 能源产业签约
		// 众和公司签约
		srqyzbs.addAll(SrqyConfigurator.getSpecialZbs());
		srqyzbs.add(GSZB.CL.getValue());// 产量
		srqyzbs.add(GSZB.BYQ.getValue());// 变压器(万KVA)
		srqyzbs.add(GSZB.XLYTL.getValue());// 线缆用铜量(吨)
		srqyzbs.add(GSZB.XLYLL.getValue());// 线缆用铝量(吨)
		srqyzbs.add(GSZB.DJG.getValue());// 多晶硅(吨)
		srqyzbs.add(GSZB.GP.getValue());// 硅片(片)
		srqyzbs.add(GSZB.FDLWD.getValue());// 发电量（万度）（众和公司、新特能源公司、能动公司）
		srqyzbs.add(GSZB.NBQmw.getValue());// 逆变器（MW）
		srqyzbs.add(GSZB.MT.getValue());// 煤炭(万吨)
		srqyzbs.add(GSZB.QZDYLBCLD.getValue());// 电子铝箔（吨）
		srqyzbs.add(GSZB.DJBHCLPM.getValue());// 电极箔化成量（平米）
		srqyzbs.add(GSZB.LBD.getValue());// 铝箔（吨）
		srqyzbs.add(GSZB.DJBPM.getValue());// 电极箔化成量（平米）
	}

	private static Map<Integer, ZBXX> zbxxMap = new Hashtable<Integer, ZBXX>();
	
	private AccumulatorFactory getAccFactory() {
		return accFac;
	}

	private ConfiguratorFactory getConfiguratorFactory() {
		return configFac;
	}
	
	@Autowired
	public void init() {
		accFac = new AccumulatorFactory(sjzbDao, yj20zbDao, yj28zbDao, ydzbztDao, ydjhzbDao, ndjhzbDao);
		configFac = new ConfiguratorFactory(sbdNdjhzbDao, accFac, companyManager);
	}

	private Set<Integer> addAll(Set<Integer> src, Set<ZBXX> dest) {
		for (ZBXX zbxx : dest) {
			src.add(zbxx.getId());
		}
		return src;
	}

	private List<Integer> getAllZbs(List<Company> comps) {
		Set<Integer> zbSet = new HashSet<Integer>();
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);
		for (DWXX dwxx : dwxxs) {
			addAll(zbSet, dwxx.getSjzbxxs());
		}
		Object[] key_arr = zbSet.toArray();
		Arrays.sort(key_arr);
		List<Integer> ret = new ArrayList<Integer>();
		for (Object id : key_arr) {
			ret.add((Integer) id);
		}
		return ret;
	}

	private ZBXX getZbxx(Integer zbId) {
		if (zbxxMap.isEmpty()) {

			List<Integer> zbs = new ArrayList<Integer>();
			zbs.addAll(gsztzbs);
			zbs.addAll(srqyzbs);

			List<ZBXX> zbxxs = zbxxDao.getZbs(zbs);
			for (ZBXX zbxx : zbxxs) {
				zbxxMap.put(zbxx.getId(), zbxx);
			}

			zbs = SrqyConfigurator.getSpecialZbs();
			ZBXX zbxx = null;
			for (int i = 0; i < zbs.size(); ++i) {
				zbxx = new ZBXX();
				zbxx.setId(zbs.get(i));
				zbxx.setName(SrqyConfigurator.getSpecialZbName(zbs.get(i)));
				zbxxMap.put(zbs.get(i), zbxx);
			}
		}

		ZBXX zbxx = zbxxMap.get(zbId);
		if (null == zbxx) {
			zbxx = zbxxDao.getById(zbId);
			zbxxMap.put(zbId, zbxx);
		}
		return zbxx;
	}

	private List<String[]> makeZbResult(List<Integer> zbs, List<Double[]> gszbs) {

		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0, len = zbs.size(); i < len; ++i) {
			Double[] gszbRow = gszbs.get(i);
			String[] zbRow = new String[gszbRow.length + 1];
			zbRow[0] = getZbxx(zbs.get(i)).getName();
			for (int j = zbRow.length - 1; j > 0; --j) {
				if (null != gszbRow[j - 1]) {
					zbRow[j] = gszbRow[j - 1] + "";
				}
			}
			result.add(zbRow);
		}
		return result;
	}

	@Override
	public List<String[]> getGsztzb(Date date) {
		SimplePipe pipe = new SimplePipe(gsztzbs, BMDepartmentDB.getMainlyJydw(companyManager), date,
				getConfiguratorFactory().getStandardConfigurator());
		return makeZbResult(gsztzbs, pipe.getData());
	}

	@Override
	public List<String[]> getGcyzb(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator option = getConfiguratorFactory()
				.getStandardConfigurator();

		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		ComplexPipe pipe = new ComplexPipe(topfivezbs, date, getConfiguratorFactory()
				.getZtzbCompositeConfigurator(getAccFactory().getCompositeAcc(dataSource), dataSource, gcyTop5ComputeMap));
		pipe.add(org.getCompany(CompanyType.SBDCYJT), option, org.getCompany(CompanyType.SBDCYJT).getSubCompanys())
			.add(org.getCompany(CompanyType.XNYSYB), option, org.getCompany(CompanyType.XNYSYB).getSubCompanys())
			.add(org.getCompany(CompanyType.NYSYB), option, org.getCompany(CompanyType.NYSYB).getSubCompanys())
			.add(org.getCompany(CompanyType.JCKGS_SYB), option, org.getCompany(CompanyType.JCKGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GJGCGS_SYB), option, org.getCompany(CompanyType.GJGCGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GFGS), null)
			.add(org.getCompany(CompanyType.ZHGS_SYB), option, org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GJB), null);

		return makeResult(pipe.getData());
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

	
	@Override
	public List<String[]> getSrqy(Date date) {	
		SimplePipe pipe = new SimplePipe(srqyzbs, BMDepartmentDB.getMainlyJydw(companyManager), date,
				getConfiguratorFactory().getSrqyConfigurator());
		return makeZbResult(srqyzbs, pipe.getData());
	}

	
	private List<String[]> getCompanyTop5zb(
			GSZB gsTop5zb, 
			Date date, 
			IPipeConfigurator standardConfig, 
			IPipeConfigurator compositeConfig,
			CompositeAccDataSource dataSource){
		
		Organization org = companyManager.getBMDBOrganization();
		ComplexPipe pipe = new ComplexPipe(gsTop5zb.getValue(), date, compositeConfig);
		pipe.add(org.getCompany(CompanyType.SBGS), standardConfig)
			.add(org.getCompany(CompanyType.HBGS), standardConfig)
			.add(org.getCompany(CompanyType.XBC), standardConfig)
			.add(org.getCompany(CompanyType.LLGS), standardConfig)
			.add(org.getCompany(CompanyType.XLC), standardConfig)
			.add(org.getCompany(CompanyType.DLGS), standardConfig)
			.add(org.getCompany(CompanyType.SBDCYJT), null)
			.add(org.getCompany(CompanyType.XTNYGS), standardConfig)
			.add(org.getCompany(CompanyType.XNYGS), standardConfig)
			.add(org.getCompany(CompanyType.XNYSYB), null)
			.add(org.getCompany(CompanyType.TCNY), standardConfig)
			.add(org.getCompany(CompanyType.NDGS), standardConfig)
			.add(org.getCompany(CompanyType.NYSYB), null)
			.add(org.getCompany(CompanyType.JCKGS_JYDW), standardConfig)
			.add(org.getCompany(CompanyType.GJGCGS_GFGS), standardConfig)
			.add(org.getCompany(CompanyType.GJGCGS_SYB), null)
			.add(org.getCompany(CompanyType.GFGS), null)
			.add(org.getCompany(CompanyType.ZHGS), standardConfig)
			.add(org.getCompany(CompanyType.GJB), null);

		return makeResult(pipe.getData());
		
	}
	
	// Get Top 5 indexes including "利润，存货、现金流、应收、收入"
	@Override
	public List<String[]> getCompanyTop5zb(GSZB gsTop5zb, Date date) {
		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		IAccumulator acc = this.getAccFactory().getCompositeAcc(dataSource);
		return getCompanyTop5zb(
				gsTop5zb, 
				date, 
				this.getConfiguratorFactory().getStandardConfigurator(),
				this.getConfiguratorFactory().getZtzbCompositeConfigurator(acc, dataSource, gdwTop5ComputeMap), 
				dataSource);
	}

	@Override
	public List<String[]> getGsFirstSeasonPredictionZBsOverview(Date date) {
		return getFirstSeasonPredictionZBsOverview(date, gsztzbs, BMDepartmentDB.getJydw(companyManager));
	}

	@Override
	public List<String[]> getGsSecondSeasonPredictionZBsOverview(Date date) {
		return getSecondSeasonPredictionZBsOverview(date, gsztzbs, BMDepartmentDB.getJydw(companyManager));
	}

	@Override
	public List<String[]> getGsThirdSeasonPredictionZBsOverview(Date date) {
		return getJDZBMY(date, gsztzbs, BMDepartmentDB.getJydw(companyManager));
	}

	@Override
	public List<String[]> getGcyThirdSeasonPredictionZBs(Date date) {
		
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator option = getConfiguratorFactory()
				.getThirdSeasonPredictionConfigurator();
		List<Double[]> values = new ArrayList<Double[]>();

		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		ComplexPipe pipe = new ComplexPipe(topfivezbs, date, getConfiguratorFactory()
				.getThirdSeasonPredictionCompositeConfigurator(getAccFactory().getCompositeAcc(dataSource), dataSource, gcyTop5ComputeMap));
		pipe.add(org.getCompany(CompanyType.SBDCYJT), option, org.getCompany(CompanyType.SBDCYJT).getSubCompanys())
			.add(org.getCompany(CompanyType.XNYSYB), option, org.getCompany(CompanyType.XNYSYB).getSubCompanys())
			.add(org.getCompany(CompanyType.NYSYB), option, org.getCompany(CompanyType.NYSYB).getSubCompanys())
			.add(org.getCompany(CompanyType.JCKGS_SYB), option, org.getCompany(CompanyType.JCKGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GJGCGS_SYB), option, org.getCompany(CompanyType.GJGCGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GFGS), null)
			.add(org.getCompany(CompanyType.ZHGS_SYB), option, org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GJB), null);
		values = pipe.getData();
		
		Double[] tempValues = null;
		int length = 0;
		List<Double[]> result = new ArrayList<Double[]>();

		for (Double[] JDZBMY : values) {
			length = JDZBMY.length - 1;
			tempValues = new Double[length];
			for (int i = 0; i < length; i++) {
				tempValues[i] = JDZBMY[i];
			}
			result.add(tempValues);
		}

		return makeResult(result);
	}

	@Override
	public List<String[]> getGdwzb(Date d, List<Company> comps) {
		List<Integer> zbs = this.getAllZbs(comps);
		IPipeConfigurator configurator;
		//Company xnySyb = companyManager.getBMDBOrganization().getCompany(
		//		CompanyType.XNYSYB);
		//CompanyType type = comps.get(0).getType();
		//if (type == xnySyb.getType() || xnySyb.contains(comps.get(0))) {
			//configurator = getConfiguratorFactory().getStandardConfigurator();
		//} else {
			configurator = getConfiguratorFactory().getStandardConfigurator();
		//}
		SimplePipe pipe = new SimplePipe(zbs, comps, d, configurator);
		return makeZbResult(zbs, pipe.getData());
	}

	@Override
	public List<String[]> getGcySecondSeasonPredictionZBs(Date date) {	
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator option = getConfiguratorFactory()
				.getSecondSeasonPredictionConfigurator();

		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		ComplexPipe pipe = new ComplexPipe(topfivezbs, date, getConfiguratorFactory()
				.getSecondSeasonPredictionCompositeConfigurator(getAccFactory().getCompositeAcc(dataSource), dataSource, gcyTop5ComputeMap));
		pipe.add(org.getCompany(CompanyType.SBDCYJT), option, org.getCompany(CompanyType.SBDCYJT).getSubCompanys())
			.add(org.getCompany(CompanyType.XNYSYB), option, org.getCompany(CompanyType.XNYSYB).getSubCompanys())
			.add(org.getCompany(CompanyType.NYSYB), option, org.getCompany(CompanyType.NYSYB).getSubCompanys())
			.add(org.getCompany(CompanyType.JCKGS_SYB), option, org.getCompany(CompanyType.JCKGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GJGCGS_SYB), option, org.getCompany(CompanyType.GJGCGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GFGS), null)
			.add(org.getCompany(CompanyType.ZHGS_SYB), option, org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GJB), null);

		return makeResult(pipe.getData());
	}

	@Override
	public List<String[]> getGcyFirstSeasonPredictionZBs(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator option = getConfiguratorFactory()
				.getFirstSeasonPredictionConfigurator();

		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		ComplexPipe pipe = new ComplexPipe(topfivezbs, date, getConfiguratorFactory()
				.getFirstSeasonPredictionCompositeConfigurator(getAccFactory().getCompositeAcc(dataSource), dataSource, gcyTop5ComputeMap));
		pipe.add(org.getCompany(CompanyType.SBDCYJT), option, org.getCompany(CompanyType.SBDCYJT).getSubCompanys())
			.add(org.getCompany(CompanyType.XNYSYB), option, org.getCompany(CompanyType.XNYSYB).getSubCompanys())
			.add(org.getCompany(CompanyType.NYSYB), option, org.getCompany(CompanyType.NYSYB).getSubCompanys())
			.add(org.getCompany(CompanyType.JCKGS_SYB), option, org.getCompany(CompanyType.JCKGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GJGCGS_SYB), option, org.getCompany(CompanyType.GJGCGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GFGS), null)
			.add(org.getCompany(CompanyType.ZHGS_SYB), option, org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys())
			.add(org.getCompany(CompanyType.GJB), null);

		return makeResult(pipe.getData());
	}

	@Override
	public List<String[]> getGdwFirstSeasonPredictionZBs(GSZB gszb, Date date) {
		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		IAccumulator acc = this.getAccFactory().getCompositeAcc(dataSource);
		return getCompanyTop5zb(
				gszb, 
				date, 
				this.getConfiguratorFactory().getFirstSeasonPredictionConfigurator(),
				this.getConfiguratorFactory().getFirstSeasonPredictionCompositeConfigurator(acc, dataSource, gdwTop5ComputeMap), 
				dataSource);
	}

	@Override
	public List<String[]> getGdwSecondSeasonPredictionZBs(GSZB gszb, Date date) {
		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		IAccumulator acc = this.getAccFactory().getCompositeAcc(dataSource);
		return getCompanyTop5zb(
				gszb, 
				date, 
				this.getConfiguratorFactory().getSecondSeasonPredictionConfigurator(),
				this.getConfiguratorFactory().getSecondSeasonPredictionCompositeConfigurator(acc, dataSource, gdwTop5ComputeMap), 
				dataSource);
	}

	@Override
	public List<String[]> getGdwThirdSeasonPredictionZBs(GSZB gszb, Date date) {
		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		IAccumulator acc = this.getAccFactory().getCompositeAcc(dataSource);
		return getCompanyTop5zb(
				gszb, 
				date, 
				this.getConfiguratorFactory().getThirdSeasonPredictionConfigurator(),
				this.getConfiguratorFactory().getThirdSeasonPredictionCompositeConfigurator(acc, dataSource, gdwTop5ComputeMap), 
				dataSource);
	}

	@Override
	public List<Company> getCompanies(Account account) {
		Set<DWXX> dwxxs = account.getDwxxs();
		List<Company> ret = new ArrayList<Company>();
		Organization org = companyManager.getBMDBOrganization();
		for(DWXX dwxx : dwxxs){
			ret.add(org.getCompany(dwxx.getId()));
		}
		return ret;
	}

	List<String[]> getFirstSeasonPredictionZBsOverview(Date date, List<Integer> zbs, 
			List<Company> comps) {
		SimplePipe pipe = new SimplePipe(zbs, comps, date,
				getConfiguratorFactory().getFirstSeasonPredictionConfigurator());
		return makeZbResult(zbs, pipe.getData());
	}
	
	List<String[]> getSecondSeasonPredictionZBsOverview(Date date, List<Integer> zbs, 
			List<Company> comps) {
		SimplePipe pipe = new SimplePipe(zbs, comps, date,
				getConfiguratorFactory().getSecondSeasonPredictionConfigurator());
		return makeZbResult(zbs, pipe.getData());
	}
	
	List<String[]> getJDZBMY(Date date, List<Integer> zbs, List<Company> comps) {
		SimplePipe pipe = new SimplePipe(zbs, comps, date,
				getConfiguratorFactory().getThirdSeasonPredictionConfigurator());
		List<Double[]> JDZBMYList = pipe.getData();
		List<Double[]> result = new ArrayList<Double[]>();
		Double[] values = null;
		int length = 0;
		for (Double[] JDZBMY : JDZBMYList) {
			length = JDZBMY.length - 1;
			values = new Double[length];
			for (int i = 0; i < length; i++) {
				values[i] = JDZBMY[i];
			}
			result.add(values);
		}
		return makeZbResult(zbs, result);
	}
	
	@Override
	public List<String[]> getFirstSeasonPredictionZBsOverview(Date date,
			List<Company> comps) {
		return getFirstSeasonPredictionZBsOverview(date, getAllZbs(comps), comps);
	}

	@Override
	public List<String[]> getSecondSeasonPredictionZBsOverview(Date date,
			List<Company> comps) {
		return getSecondSeasonPredictionZBsOverview(date, getAllZbs(comps), comps);
	}

	@Override
	public List<String[]> getThirdSeasonPredictionZBsOverview(Date date, List<Company> comps) {
		return getJDZBMY(date, getAllZbs(comps), comps);
	}
}
