package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyList;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.account.AccountDao;
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
import com.tbea.ic.operation.service.util.pipe.core.CompositePipe;
import com.tbea.ic.operation.service.util.pipe.core.BasicPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.ConfiguratorFactory;
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
	
	@Autowired
	AccountDao accountDao;
	
	CompanyManager companyManager;

	AccumulatorFactory accFac;

	ConfiguratorFactory configFac;

	private static List<Integer> gsztzbs = new ArrayList<Integer>();
	static {
		gsztzbs.add(GSZB.LRZE1.value());
		gsztzbs.add(GSZB.KHLR.value());
		gsztzbs.add(GSZB.XSSR6.value());
		gsztzbs.add(GSZB.XSLRL_28.value()); //三期加入指标
		gsztzbs.add(GSZB.JYXJXJL29.value());
		gsztzbs.add(GSZB.YSZK32.value());
		gsztzbs.add(GSZB.YQK33.value());
		gsztzbs.add(GSZB.BL34.value());
		gsztzbs.add(GSZB.CH35.value());
		gsztzbs.add(GSZB.QZ_JYWZ36.value());
		gsztzbs.add(GSZB.HTQYE48.value());
		gsztzbs.add(GSZB.HTQY_ZZYQY290.value()); //三期加入指标
		gsztzbs.add(GSZB.JCFWYW_HGCHJCXS_QY299.value()); //三期加入指标
		gsztzbs.add(GSZB.HTQY_QT304.value()); //三期加入指标
		gsztzbs.add(GSZB.ZJHL57.value());
		gsztzbs.add(GSZB.BHSCZ60.value());
		gsztzbs.add(GSZB.RS61.value());
		gsztzbs.add(GSZB.RJLR62.value());
		gsztzbs.add(GSZB.RJSR63.value());
		gsztzbs.add(GSZB.SXFY64.value());
		gsztzbs.add(GSZB.SXFYL_65.value());
		//gsztzbs.add(GSZB.JZCSYL.getValue());
	}
	
	private static List<Integer> CorpIndicators = new ArrayList<Integer>();
	static {
		CorpIndicators.add(GSZB.LRZE1.value());
		CorpIndicators.add(GSZB.XSSR6.value());
		CorpIndicators.add(GSZB.JYXJXJL29.value());
		CorpIndicators.add(GSZB.YSZK32.value());
		CorpIndicators.add(GSZB.CH35.value());
	}
	
	private static List<Integer> gsztzbsNC = new ArrayList<Integer>();
	static {
		gsztzbsNC.add(GSZB.LRZE1.value());
		gsztzbsNC.add(GSZB.XSSR6.value());
		gsztzbsNC.add(GSZB.JYXJXJL29.value());
		gsztzbsNC.add(GSZB.YSZK32.value());
		gsztzbsNC.add(GSZB.CH35.value());
		gsztzbsNC.add(GSZB.SXFY64.value());
		gsztzbsNC.add(GSZB.SXFYL_65.value());
	}

	private static List<Integer> topfivezbs = new ArrayList<Integer>();
	static {
		topfivezbs.add(GSZB.LRZE1.value());
		topfivezbs.add(GSZB.XSSR6.value());
		topfivezbs.add(GSZB.JYXJXJL29.value());
		topfivezbs.add(GSZB.YSZK32.value());
		topfivezbs.add(GSZB.CH35.value());
	}

	
	private static Map<Company, List<Company>> gdwTop5ComputeMap = new HashMap<Company, List<Company>>();
	private static Map<Company, List<Company>> gcyTop5ComputeMap = new HashMap<Company, List<Company>>();


	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager mgr){
		companyManager = mgr;
		Organization org = mgr.getBMDBOrganization();
		List<Company> companies = new ArrayList<Company>();
		gcyTop5ComputeMap.put(org.getCompany(CompanyType.GFGS), companies);
		companies.add(org.getCompany(CompanyType.SBDCYJT));
		companies.add(org.getCompany(CompanyType.XNYSYB));
		companies.add(org.getCompany(CompanyType.NYSYB));
		companies.add(org.getCompany(CompanyType.JCKGS_SYB));
		companies.add(org.getCompany(CompanyType.GJGCGS_SYB));
		companies = new ArrayList<Company>();
		gcyTop5ComputeMap.put(org.getCompany(CompanyType.GJB), companies);
		companies.add(org.getCompany(CompanyType.GFGS));
		companies.add(org.getCompany(CompanyType.ZHGS_SYB));

		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(org.getCompany(CompanyType.SBDCYJT), companies);
		companies.add(org.getCompany(CompanyType.SBGS));
		companies.add(org.getCompany(CompanyType.HBGS));
		companies.add(org.getCompany(CompanyType.XBC));
		companies.add(org.getCompany(CompanyType.LLGS));
		companies.add(org.getCompany(CompanyType.XLC));
		companies.add(org.getCompany(CompanyType.DLGS));
		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(org.getCompany(CompanyType.XNYSYB), companies);
		companies.add(org.getCompany(CompanyType.XTNYGS));
		companies.add(org.getCompany(CompanyType.XNYGS));
		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(org.getCompany(CompanyType.NYSYB), companies);
		companies.add(org.getCompany(CompanyType.TCNY));
		companies.add(org.getCompany(CompanyType.NDGS));
		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(org.getCompany(CompanyType.GJGCGS_SYB), companies);
		companies.add(org.getCompany(CompanyType.JCKGS_JYDW));
		companies.add(org.getCompany(CompanyType.GJGCGS_GFGS));
		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(org.getCompany(CompanyType.GFGS), companies);
		companies.add(org.getCompany(CompanyType.SBDCYJT));
		companies.add(org.getCompany(CompanyType.XNYSYB));
		companies.add(org.getCompany(CompanyType.NYSYB));
		companies.add(org.getCompany(CompanyType.GJGCGS_SYB));
		companies = new ArrayList<Company>();
		gdwTop5ComputeMap.put(org.getCompany(CompanyType.GJB), companies);
		companies.add(org.getCompany(CompanyType.ZHGS));
		companies.add(org.getCompany(CompanyType.GFGS));
	}
	
	
	
	// 公司整体2（收入签约分结构）
	private static List<Integer> srqyzbs = new ArrayList<Integer>();
	static {
		// 销售收入
		srqyzbs.add(GSZB.XSSR6.value());// 营业收入
		srqyzbs.add(GSZB.XSSR_ZZYSR7.value());// 制造业收入（输变电收入、新能源收入、能源煤炭收入、众和制造业收入）
		srqyzbs.add(GSZB.XSSR_GCXMSR12.value());// 工程项目收入（输变电收入、新能源产业成套工程收入、能源产业工程收入、进出口公司收入、国际工程公司收入、众和公司收入）
		srqyzbs.add(GSZB.YYSSR15.value());// 运营商收入（新能源产业运营商收入）
		srqyzbs.add(GSZB.MTXSSR19.value());// 煤炭销售收入（能源产业收入）
		srqyzbs.add(GSZB.XSSR_WLMYSR16.value());// 物流贸易收入（输变电物流收入、能源产业贸易收入、众和公司物流业收入）
		srqyzbs.add(GSZB.FWLSR20.value());// 服务类收入（新能源产业服务业收入、能源产业服务业收入、众和公司服务业收入）

		// 合同签约额
		srqyzbs.add(GSZB.HTQYE48.value());// 合同签约额
		srqyzbs.add(GSZB.GJQY_WMY_49.value());// 输变电、进出口国际签约
		srqyzbs.add(GSZB.QZ_DJQY50.value());// 单机签约（输变电国际单机签约）
		srqyzbs.add(GSZB.QZ_CTQY_WMY_51.value());// 成套签约（输变电国际成套签约、新能源产业国际成套签约、进出口公司国际成套签约、国际工程公司国际成套签约）
		srqyzbs.add(GSZB.GNQY52.value());// 输变电国内签约
		srqyzbs.add(GSZB.QZ_DJQY53.value());// 单机签约（输变电国内单机签约）
		srqyzbs.add(GSZB.QZ_CTQY_WY_54.value());// 成套签约（输变电国内成套签约）
		
		// 新能源产业签约
		srqyzbs.addAll(SrqyConfigurator.getSpecialZbs());
		
		srqyzbs.add(GSZB.CL67.value());// 产量
		srqyzbs.add(GSZB.BYQ_WKVA_68.value());// 变压器(万KVA)
		srqyzbs.add(GSZB.XLYTL_D_69.value());// 线缆用铜量(吨)
		srqyzbs.add(GSZB.XLYLL_D_70.value());// 线缆用铝量(吨)
		srqyzbs.add(GSZB.CL_DJG_D_71.value());// 多晶硅(吨)
		srqyzbs.add(GSZB.CL_GP_WP_72.value());// 硅片(片)
		srqyzbs.add(GSZB.FDL_WD_73.value());// 发电量（万度）（众和公司、新特能源公司、能动公司）
		srqyzbs.add(GSZB.CL_NBQ_MW_74.value());// 逆变器（MW）
		srqyzbs.add(GSZB.MTCL_WD_75.value());// 煤炭(万吨)
		srqyzbs.add(GSZB.LB_D_78.value());// 铝箔（吨）
		srqyzbs.add(GSZB.DJB_PM_81.value());// 电极箔化成量（平米）
	}

	private static Map<Integer, ZBXX> zbxxMap = new Hashtable<Integer, ZBXX>();
	

	private ConfiguratorFactory getConfiguratorFactory() {
		return configFac;
	}
	
	@Autowired
	public void init() {
		accFac = new AccumulatorFactory(sjzbDao, yj20zbDao, yj28zbDao, ydzbztDao, ydjhzbDao, ndjhzbDao);
		configFac = new ConfiguratorFactory(sbdNdjhzbDao, accFac, companyManager);
	}

	private Map<Integer, ZBXX> addAll(Map<Integer, ZBXX> src, Set<ZBXX> dest) {
		for (ZBXX zbxx : dest) {
			src.put(zbxx.getSequence(), zbxx);
		}
		return src;
	}

	private List<Integer> getAllZbs(List<Company> comps) {
		Map<Integer, ZBXX> zbSet = new HashMap<Integer, ZBXX>();
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);
		for (DWXX dwxx : dwxxs) {
			addAll(zbSet, dwxx.getSjzbxxs());
		}
		Object[] key_arr = zbSet.keySet().toArray();
		Arrays.sort(key_arr);
		List<Integer> ret = new ArrayList<Integer>();
		for (Object id : key_arr) {
			ret.add(zbSet.get(id).getId());
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
		BasicPipe pipe = new BasicPipe(gsztzbs, BMDepartmentDB.getMainlyJydw(companyManager), date,
				getConfiguratorFactory().getStandardConfigurator());
		return makeZbResult(gsztzbs, pipe.getData());
	}
	
	@Override
	public List<String[]> getDashboardGsztzb(Date date) {
		List zbs = new EasyList<Integer>(new Integer[]{
				GSZB.LRZE1.value(),
				GSZB.XSSR6.value(),
				GSZB.YSZK32.value(),
				GSZB.CH35.value()}).toList();
		BasicPipe pipe = new BasicPipe(zbs, BMDepartmentDB.getMainlyJydw(companyManager), date,
				getConfiguratorFactory().getStandardConfigurator());
		return makeZbResult(zbs, pipe.getData());
	}
	
	@Override
	public List<String[]> getDashboardGdwzb(Date date, CompanyType cpType) {
		Organization org = companyManager.getBMDBOrganization();
		Company comp = org.getCompany(cpType);
		List zbs = new EasyList<Integer>(new Integer[]{
				GSZB.LRZE1.value(),
				GSZB.XSSR6.value(),
				GSZB.YSZK32.value(),
				GSZB.CH35.value()}).toList();
		BasicPipe pipe = new BasicPipe(zbs, comp, date,
				getConfiguratorFactory().getStandardConfigurator());
		return makeZbResult(zbs, pipe.getData());
	}

	@Override
	public List<Double[]> getCorpIndicators(Date date) {
		BasicPipe pipe = new BasicPipe(CorpIndicators, BMDepartmentDB.getMainlyJydw(companyManager), date,
				getConfiguratorFactory().getStandardConfigurator());
		return pipe.getData();
	}
	
	@Override
	public List<String[]> getGcyzb(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator option = getConfiguratorFactory()
				.getStandardConfigurator();

		CompositePipe pipe = new CompositePipe(topfivezbs, date, getConfiguratorFactory()
				.getZtzbCompositeConfigurator( gcyTop5ComputeMap));
		pipe.addCompany(org.getCompany(CompanyType.SBDCYJT), option, org.getCompany(CompanyType.SBDCYJT).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.XNYSYB), option, org.getCompany(CompanyType.XNYSYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.NYSYB), option, org.getCompany(CompanyType.NYSYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.JCKGS_SYB), option, org.getCompany(CompanyType.JCKGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GJGCGS_SYB), option, org.getCompany(CompanyType.GJGCGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GFGS), null)
			.addCompany(org.getCompany(CompanyType.ZHGS_SYB), option, org.getCompany(CompanyType.ZHGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GJB), null);

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
		BasicPipe pipe = new BasicPipe(srqyzbs, BMDepartmentDB.getMainlyJydw(companyManager), date,
				getConfiguratorFactory().getSrqyConfigurator());
		List<String[]> allCompResult = makeZbResult(srqyzbs, pipe.getData());
		
		pipe = new BasicPipe(GSZB.HTQYE48.value(), companyManager.getBMDBOrganization().getCompany(CompanyType.XNYSYB).getSubCompanies(), date,
				getConfiguratorFactory().getSrqyConfigurator());
		List<Integer> zbTmp = new ArrayList<Integer>();
		zbTmp.addAll(SrqyConfigurator.getSpecialZbs());
		List<String[]> xnysybResult = makeZbResult(zbTmp, pipe.getData());
		
		List<Company> comps = new ArrayList<Company>();
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DJGYFGS));
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DJGEGS));
		pipe = new BasicPipe(GSZB.CL_DJG_D_71.value(), comps, date, getConfiguratorFactory().getSrqyConfigurator());
		
		zbTmp.clear();
		zbTmp.add(GSZB.CL_DJG_D_71.value());
		List<String[]> djggsResult = makeZbResult(zbTmp, pipe.getData());
		
		for (int i = 0; i < allCompResult.size(); ++i){
			if (allCompResult.get(i)[0].equals(xnysybResult.get(0)[0])){
				allCompResult.set(i, xnysybResult.get(0));
			} else if(allCompResult.get(i)[0].equals(djggsResult.get(0)[0])){
				allCompResult.set(i, djggsResult.get(0));
			}
		}
		
		return allCompResult;
	}

	
	private List<String[]> getCompanyTop5zb(
			GSZB gsTop5zb, 
			Date date, 
			IPipeConfigurator standardConfig, 
			IPipeConfigurator compositeConfig){
		
		Organization org = companyManager.getBMDBOrganization();
		CompositePipe pipe = new CompositePipe(gsTop5zb.value(), date, compositeConfig);
		pipe.addCompany(org.getCompany(CompanyType.SBGS), standardConfig)
			.addCompany(org.getCompany(CompanyType.HBGS), standardConfig)
			.addCompany(org.getCompany(CompanyType.XBC), standardConfig)
			.addCompany(org.getCompany(CompanyType.LLGS), standardConfig)
			.addCompany(org.getCompany(CompanyType.XLC), standardConfig)
			.addCompany(org.getCompany(CompanyType.DLGS), standardConfig)
			.addCompany(org.getCompany(CompanyType.SBDCYJT), null)
			.addCompany(org.getCompany(CompanyType.XTNYGS), standardConfig)
			.addCompany(org.getCompany(CompanyType.XNYGS), standardConfig)
			.addCompany(org.getCompany(CompanyType.XNYSYB), null)
			.addCompany(org.getCompany(CompanyType.TCNY), standardConfig)
			.addCompany(org.getCompany(CompanyType.NDGS), standardConfig)
			.addCompany(org.getCompany(CompanyType.NYSYB), null)
			.addCompany(org.getCompany(CompanyType.JCKGS_JYDW), standardConfig)
			.addCompany(org.getCompany(CompanyType.GJGCGS_GFGS), standardConfig)
			.addCompany(org.getCompany(CompanyType.GJGCGS_SYB), null)
			.addCompany(org.getCompany(CompanyType.GFGS), null)
			.addCompany(org.getCompany(CompanyType.ZHGS), standardConfig)
			.addCompany(org.getCompany(CompanyType.GJB), null);

		return makeResult(pipe.getData());
		
	}
	
	// Get Top 5 indexes including "利润，存货、现金流、应收、收入"
	@Override
	public List<String[]> getCompanyTop5zb(GSZB gsTop5zb, Date date) {
		return getCompanyTop5zb(
				gsTop5zb, 
				date, 
				this.getConfiguratorFactory().getStandardConfigurator(),
				this.getConfiguratorFactory().getZtzbCompositeConfigurator(gdwTop5ComputeMap));
	}

	@Override
	public List<String[]> getGsFirstSeasonPredictionZBsOverview(Date date) {
		return getFirstSeasonPredictionZBsOverview(date, gsztzbs, BMDepartmentDB.getMainlyJydw(companyManager));
	}

	@Override
	public List<String[]> getGsSecondSeasonPredictionZBsOverview(Date date) {
		return getSecondSeasonPredictionZBsOverview(date, gsztzbs, BMDepartmentDB.getMainlyJydw(companyManager));
	}

	@Override
	public List<String[]> getGsThirdSeasonPredictionZBsOverview(Date date) {
		return getJDZBMY(date, gsztzbs, BMDepartmentDB.getMainlyJydw(companyManager));
	}

	@Override
	public List<String[]> getGcyThirdSeasonPredictionZBs(Date date) {
		
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator option = getConfiguratorFactory()
				.getThirdSeasonPredictionConfigurator();
		List<Double[]> values = new ArrayList<Double[]>();

		CompositePipe pipe = new CompositePipe(topfivezbs, date, getConfiguratorFactory()
				.getThirdSeasonPredictionCompositeConfigurator(gcyTop5ComputeMap));
		pipe.addCompany(org.getCompany(CompanyType.SBDCYJT), option, org.getCompany(CompanyType.SBDCYJT).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.XNYSYB), option, org.getCompany(CompanyType.XNYSYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.NYSYB), option, org.getCompany(CompanyType.NYSYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.JCKGS_SYB), option, org.getCompany(CompanyType.JCKGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GJGCGS_SYB), option, org.getCompany(CompanyType.GJGCGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GFGS), null)
			.addCompany(org.getCompany(CompanyType.ZHGS_SYB), option, org.getCompany(CompanyType.ZHGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GJB), null);
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
		BasicPipe pipe = new BasicPipe(zbs, comps, d, configurator);
		return makeZbResult(zbs, pipe.getData());
	}

	@Override
	public List<String[]> getGcySecondSeasonPredictionZBs(Date date) {	
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator option = getConfiguratorFactory()
				.getSecondSeasonPredictionConfigurator();

		CompositePipe pipe = new CompositePipe(topfivezbs, date, getConfiguratorFactory()
				.getSecondSeasonPredictionCompositeConfigurator(gcyTop5ComputeMap));
		pipe.addCompany(org.getCompany(CompanyType.SBDCYJT), option, org.getCompany(CompanyType.SBDCYJT).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.XNYSYB), option, org.getCompany(CompanyType.XNYSYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.NYSYB), option, org.getCompany(CompanyType.NYSYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.JCKGS_SYB), option, org.getCompany(CompanyType.JCKGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GJGCGS_SYB), option, org.getCompany(CompanyType.GJGCGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GFGS), null)
			.addCompany(org.getCompany(CompanyType.ZHGS_SYB), option, org.getCompany(CompanyType.ZHGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GJB), null);

		return makeResult(pipe.getData());
	}

	@Override
	public List<String[]> getGcyFirstSeasonPredictionZBs(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator option = getConfiguratorFactory()
				.getFirstSeasonPredictionConfigurator();

		CompositePipe pipe = new CompositePipe(topfivezbs, date, getConfiguratorFactory()
				.getFirstSeasonPredictionCompositeConfigurator(gcyTop5ComputeMap));
		pipe.addCompany(org.getCompany(CompanyType.SBDCYJT), option, org.getCompany(CompanyType.SBDCYJT).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.XNYSYB), option, org.getCompany(CompanyType.XNYSYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.NYSYB), option, org.getCompany(CompanyType.NYSYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.JCKGS_SYB), option, org.getCompany(CompanyType.JCKGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GJGCGS_SYB), option, org.getCompany(CompanyType.GJGCGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GFGS), null)
			.addCompany(org.getCompany(CompanyType.ZHGS_SYB), option, org.getCompany(CompanyType.ZHGS_SYB).getSubCompanies())
			.addCompany(org.getCompany(CompanyType.GJB), null);

		return makeResult(pipe.getData());
	}

	@Override
	public List<String[]> getGdwFirstSeasonPredictionZBs(GSZB gszb, Date date) {
		return getCompanyTop5zb(
				gszb, 
				date, 
				this.getConfiguratorFactory().getFirstSeasonPredictionConfigurator(),
				this.getConfiguratorFactory().getFirstSeasonPredictionCompositeConfigurator(gdwTop5ComputeMap));
	}

	@Override
	public List<String[]> getGdwSecondSeasonPredictionZBs(GSZB gszb, Date date) {
		return getCompanyTop5zb(
				gszb, 
				date, 
				this.getConfiguratorFactory().getSecondSeasonPredictionConfigurator(),
				this.getConfiguratorFactory().getSecondSeasonPredictionCompositeConfigurator(gdwTop5ComputeMap));
	}

	@Override
	public List<String[]> getGdwThirdSeasonPredictionZBs(GSZB gszb, Date date) {
		return getCompanyTop5zb(
				gszb, 
				date, 
				this.getConfiguratorFactory().getThirdSeasonPredictionConfigurator(),
				this.getConfiguratorFactory().getThirdSeasonPredictionCompositeConfigurator(gdwTop5ComputeMap));
	}

	@Override
	public List<Company> getCompanies(Account account) {
		if (account.getDwxxs().isEmpty()){
			account = accountDao.getAccount(account.getName());
		}
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
		BasicPipe pipe = new BasicPipe(zbs, comps, date,
				getConfiguratorFactory().getFirstSeasonPredictionConfigurator());
		return makeZbResult(zbs, pipe.getData());
	}
	
	List<String[]> getSecondSeasonPredictionZBsOverview(Date date, List<Integer> zbs, 
			List<Company> comps) {
		BasicPipe pipe = new BasicPipe(zbs, comps, date,
				getConfiguratorFactory().getSecondSeasonPredictionConfigurator());
		return makeZbResult(zbs, pipe.getData());
	}
	
	List<String[]> getJDZBMY(Date date, List<Integer> zbs, List<Company> comps) {
		BasicPipe pipe = new BasicPipe(zbs, comps, date,
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

	@Override
	public List<String[]> getGsztzbNC(Date d,  com.tbea.ic.operation.service.nczb.pipe.configurator.ConfiguratorFactory  fac) {
		BasicPipe pipe = new BasicPipe(gsztzbsNC, BMDepartmentDB.getJydw(companyManager), d, fac.createFinancialPipeConfigurator(accFac.getSjAcc()));
		return makeZbResult(gsztzbsNC, pipe.getData());
	}
	
	
}
