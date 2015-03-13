package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.service.ydzb.gszb.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.gszb.acc.CompositeAccDataSource;
import com.tbea.ic.operation.service.ydzb.gszb.acc.CompositeAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.ConfiguratorFactory;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.SrqyConfigurator;

@Service
@Transactional("transactionManager")
public class GszbServiceImpl implements GszbService {

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
		configFac = new ConfiguratorFactory(accFac, companyManager);
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

	private List<Company> getJydw(List<Company> sybs) {
		List<Company> jydws = new ArrayList<Company>();
		for (Company syb : sybs) {
			if (!syb.getSubCompanys().isEmpty()) {
				jydws.addAll(syb.getSubCompanys());
			}
		}
		return jydws;
	}
	
	private List<Company> getMainlyJydw(){
		Organization org = companyManager.getBMDBOrganization();
		List<Company> sybs = org.getCompany(CompanyType.GFGS).getSubCompanys();
		sybs.add(org.getCompany(CompanyType.ZHGS_SYB));
		return getJydw(sybs);
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
		GszbPipe pipe = new GszbPipe(gsztzbs, getMainlyJydw(), date,
				getConfiguratorFactory().getStandardConfigurator());
		return makeZbResult(gsztzbs, pipe.getGszb());
	}

	@Override
	public List<String[]> getGcyzb(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator standardConfig = getConfiguratorFactory()
				.getStandardConfigurator();
		List<Company> gfhj = new ArrayList<Company>();
		gfhj.add(org.getCompany(CompanyType.SBDCYJT));
		gfhj.add(org.getCompany(CompanyType.XNYSYB));
		gfhj.add(org.getCompany(CompanyType.NYSYB));
		gfhj.add(org.getCompany(CompanyType.JCKGS_SYB));
		gfhj.add(org.getCompany(CompanyType.GJGCGS_SYB));

		List<List<Double[]>> values = new ArrayList<List<Double[]>>();
		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		IPipeConfigurator compositeConfig = getConfiguratorFactory()
				.getZtzbCompositeConfigurator(getAccFactory().getCompositeAcc(dataSource));
		GszbPipe pipe = null;
		for (Company comp : gfhj) {
			pipe = new GszbPipe(topfivezbs, comp.getSubCompanys(), date,
					standardConfig);
			List<Double[]> ret = pipe.getGszb();
			values.add(ret);
			dataSource.add(comp, topfivezbs, ret);
		}

		pipe = new GszbPipe(topfivezbs, gfhj, date, compositeConfig);
		List<Double[]> gfhjZbs = pipe.getGszb();
		values.add(gfhjZbs);

		pipe = new GszbPipe(topfivezbs, org.getCompany(CompanyType.ZHGS_SYB)
				.getSubCompanys(), date, standardConfig);
		List<Double[]> zhgsZbs = pipe.getGszb();
		values.add(zhgsZbs);

		dataSource.add(org.getCompany(CompanyType.GFGS), topfivezbs, gfhjZbs);
		dataSource.add(org.getCompany(CompanyType.ZHGS_SYB), topfivezbs, zhgsZbs);

		List<Company> jthj = new ArrayList<Company>();
		jthj.add(org.getCompany(CompanyType.GFGS));
		jthj.add(org.getCompany(CompanyType.ZHGS_SYB));

		pipe = new GszbPipe(topfivezbs, jthj, date, compositeConfig);
		List<Double[]> jthjZbs = pipe.getGszb();
		values.add(jthjZbs);

		return makeGroupResult(values);
	}

	private List<String[]> makeGroupResult(List<List<Double[]>> values) {
		List<Double[]> zbs;
		List<String[]> result = new ArrayList<String[]>();
		int size = values.size() * values.get(0).size();
		for (int i = 0; i < size; ++i) {
			result.add(new String[values.get(0).get(0).length]);
		}

		for (int k = 0, len = values.size(); k < len; ++k) {
			zbs = values.get(k);
			for (int i = 0; i < zbs.size(); ++i) {
				for (int j = 0; j < zbs.get(i).length; ++j) {
					result.get(i * len + k)[j] = zbs.get(i)[j] + "";
				}
			}
		}
		return result;
	}
	
	@Override
	public List<String[]> getSrqy(Date date) {
		GszbPipe pipe = new GszbPipe(srqyzbs, getMainlyJydw(), date,
				getConfiguratorFactory().getSrqyConfigurator());
		return makeZbResult(srqyzbs, pipe.getGszb());
	}

	
	private List<String[]> getCompanyTop5zb(
			GSZB gsTop5zb, 
			Date date, 
			IPipeConfigurator standardConfig, 
			IPipeConfigurator compositeConfig,
			CompositeAccDataSource dataSource){
		
		Organization org = companyManager.getBMDBOrganization();
		GszbPipe pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.SBGS), date, standardConfig);
		List<Double[]> sbgsZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.HBGS), date, standardConfig);
		List<Double[]> hbgsZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.XBC), date, standardConfig);
		List<Double[]> xbgsZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.LLGS), date, standardConfig);
		List<Double[]> llgsZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.XLC), date, standardConfig);
		List<Double[]> xlcZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.DLGS), date, standardConfig);
		List<Double[]> dlZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.XTNYGS), date, standardConfig);
		List<Double[]> xtnyZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.XNYGS), date, standardConfig);
		List<Double[]> xnyZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.TCNY), date, standardConfig);
		List<Double[]> tcnyZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.NDGS), date, standardConfig);
		List<Double[]> ndgsZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.JCKGS_JYDW), date, standardConfig);
		List<Double[]> jckZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.GJGCGS_GFGS), date, standardConfig);
		List<Double[]> gjgcZbs = pipe.getGszb();

		pipe = new GszbPipe(gsTop5zb.getValue(),
				org.getCompany(CompanyType.ZHGS), date, standardConfig);
		List<Double[]> zhgsZbs = pipe.getGszb();

		dataSource.add(org.getCompany(CompanyType.SBGS), gsTop5zb.getValue(),
				sbgsZbs.get(0));
		dataSource.add(org.getCompany(CompanyType.HBGS), gsTop5zb.getValue(),
				hbgsZbs.get(0));
		dataSource.add(org.getCompany(CompanyType.XBC), gsTop5zb.getValue(),
				xbgsZbs.get(0));
		dataSource.add(org.getCompany(CompanyType.LLGS), gsTop5zb.getValue(),
				llgsZbs.get(0));
		dataSource.add(org.getCompany(CompanyType.XLC), gsTop5zb.getValue(),
				xlcZbs.get(0));
		dataSource.add(org.getCompany(CompanyType.DLGS), gsTop5zb.getValue(),
				dlZbs.get(0));

		dataSource.add(org.getCompany(CompanyType.XTNYGS), gsTop5zb.getValue(),
				xtnyZbs.get(0));
		dataSource.add(org.getCompany(CompanyType.XNYGS), gsTop5zb.getValue(),
				xnyZbs.get(0));
		dataSource.add(org.getCompany(CompanyType.TCNY), gsTop5zb.getValue(),
				tcnyZbs.get(0));
		dataSource.add(org.getCompany(CompanyType.NDGS), gsTop5zb.getValue(),
				ndgsZbs.get(0));
		dataSource.add(org.getCompany(CompanyType.JCKGS_JYDW), gsTop5zb.getValue(),
				jckZbs.get(0));
		dataSource.add(org.getCompany(CompanyType.GJGCGS_GFGS),
				gsTop5zb.getValue(), gjgcZbs.get(0));
		dataSource.add(org.getCompany(CompanyType.ZHGS), gsTop5zb.getValue(),
				zhgsZbs.get(0));

		// 输变电指标
		List<Company> sbdgs = new ArrayList<Company>();
		sbdgs.add(org.getCompany(CompanyType.SBGS));
		sbdgs.add(org.getCompany(CompanyType.HBGS));
		sbdgs.add(org.getCompany(CompanyType.XBC));
		sbdgs.add(org.getCompany(CompanyType.LLGS));
		sbdgs.add(org.getCompany(CompanyType.XLC));
		sbdgs.add(org.getCompany(CompanyType.DLGS));
		pipe = new GszbPipe(gsTop5zb.getValue(), sbdgs, date, compositeConfig);
		List<Double[]> sbdhjZbs = pipe.getGszb();
		dataSource.add(org.getCompany(CompanyType.SBDCYJT), gsTop5zb.getValue(),
				sbdhjZbs.get(0));

		// 新能源指标
		List<Company> xnygs = new ArrayList<Company>();
		xnygs.add(org.getCompany(CompanyType.XTNYGS));
		xnygs.add(org.getCompany(CompanyType.XNYGS));
		pipe = new GszbPipe(gsTop5zb.getValue(), xnygs, date, compositeConfig);
		List<Double[]> xnyhjZbs = pipe.getGszb();
		dataSource.add(org.getCompany(CompanyType.XNYSYB), gsTop5zb.getValue(),
				xnyhjZbs.get(0));

		// 能源指标
		List<Company> nygs = new ArrayList<Company>();
		nygs.add(org.getCompany(CompanyType.TCNY));
		nygs.add(org.getCompany(CompanyType.NDGS));
		pipe = new GszbPipe(gsTop5zb.getValue(), nygs, date, compositeConfig);
		List<Double[]> nyhjZbs = pipe.getGszb();
		dataSource.add(org.getCompany(CompanyType.NYSYB), gsTop5zb.getValue(),
				nyhjZbs.get(0));

		// 工程公司指标
		List<Company> gcgs = new ArrayList<Company>();
		gcgs.add(org.getCompany(CompanyType.JCKGS_JYDW));
		gcgs.add(org.getCompany(CompanyType.GJGCGS_GFGS));
		pipe = new GszbPipe(gsTop5zb.getValue(), gcgs, date, compositeConfig);
		List<Double[]> gcgshjZbs = pipe.getGszb();
		dataSource.add(org.getCompany(CompanyType.GJGCGS_SYB), gsTop5zb.getValue(),
				gcgshjZbs.get(0));

		// 股份合计指标
		List<Company> gfgs = new ArrayList<Company>();
		gfgs.add(org.getCompany(CompanyType.SBDCYJT));
		gfgs.add(org.getCompany(CompanyType.XNYSYB));
		gfgs.add(org.getCompany(CompanyType.NYSYB));
		gfgs.add(org.getCompany(CompanyType.GJGCGS_SYB));
		pipe = new GszbPipe(gsTop5zb.getValue(), gfgs, date, compositeConfig);
		List<Double[]> gfgshjZbs = pipe.getGszb();
		dataSource.add(org.getCompany(CompanyType.GFGS), gsTop5zb.getValue(),
				gfgshjZbs.get(0));

		// 集团合计指标
		List<Company> jtgs = new ArrayList<Company>();
		jtgs.add(org.getCompany(CompanyType.GFGS));
		jtgs.add(org.getCompany(CompanyType.ZHGS));
		pipe = new GszbPipe(gsTop5zb.getValue(), jtgs, date, compositeConfig);
		List<Double[]> jtgshjZbs = pipe.getGszb();

		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0; i < 40; ++i) {
			result.add(new String[compositeConfig.getColumnCount()]);
		}

		List<List<Double[]>> values = new ArrayList<List<Double[]>>();
		values.add(sbgsZbs);// 0);
		values.add(hbgsZbs);// 1);
		values.add(xbgsZbs);// 2);
		values.add(llgsZbs);// 3);
		values.add(xlcZbs);// 4);
		values.add(dlZbs);// 5);
		values.add(sbdhjZbs);// 6);
		values.add(xtnyZbs);// 7);

		values.add(xnyZbs);// 8);
		values.add(xnyhjZbs);// 9);
		values.add(tcnyZbs);// 10);
		values.add(ndgsZbs);// 11);
		values.add(nyhjZbs);// 12);
		values.add(jckZbs);// 13);
		values.add(gjgcZbs);// 14);
		values.add(gcgshjZbs);// 15);

		values.add(gfgshjZbs);// 16);
		values.add(zhgsZbs);// 17);
		values.add(jtgshjZbs);// 18);
		return makeGroupResult(values);
		
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
				this.getConfiguratorFactory().getZtzbCompositeConfigurator(acc), 
				dataSource);
	}

	@Override
	public List<String[]> getFirstSeasonPredictionZBsOverview(Date date) {
		GszbPipe pipe = new GszbPipe(gsztzbs, getMainlyJydw(), date,
				getConfiguratorFactory().getFirstSeasonPredictionConfigurator());
		return makeZbResult(gsztzbs, pipe.getGszb());
	}

	@Override
	public List<String[]> getSecondSeasonPredictionZBsOverview(Date date) {
		GszbPipe pipe = new GszbPipe(gsztzbs, getMainlyJydw(), date,
				getConfiguratorFactory()
						.getSecondSeasonPredictionConfigurator());
		return makeZbResult(gsztzbs, pipe.getGszb());
	}

	@Override
	public List<String[]> getJDZBMY(Date date) {
		GszbPipe pipe = new GszbPipe(gsztzbs, getMainlyJydw(), date,
				getConfiguratorFactory().getJDZBMYConfigurator());
		List<Double[]> JDZBMYList = pipe.getGszb();
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
		return makeZbResult(gsztzbs, result);
	}

	@Override
	public List<String[]> getGcyJDZBMY(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator standardConfig = getConfiguratorFactory()
				.getJDZBMYConfigurator();
		List<Company> gfhj = new ArrayList<Company>();
		gfhj.add(org.getCompany(CompanyType.SBDCYJT));
		gfhj.add(org.getCompany(CompanyType.XNYSYB));
		gfhj.add(org.getCompany(CompanyType.NYSYB));
		gfhj.add(org.getCompany(CompanyType.JCKGS_SYB));
		gfhj.add(org.getCompany(CompanyType.GJGCGS_SYB));

		List<List<Double[]>> values = new ArrayList<List<Double[]>>();
		CompositeAccDataSource dataSource = new CompositeAccDataSource();

		IPipeConfigurator compositeConfig = getConfiguratorFactory()
				.getJdzbmyCompositeConfigurator(getAccFactory().getCompositeAcc(dataSource));
		GszbPipe pipe = null;
		for (Company comp : gfhj) {
			pipe = new GszbPipe(topfivezbs, comp.getSubCompanys(), date,
					standardConfig);
			List<Double[]> ret = pipe.getGszb();
			values.add(ret);
			dataSource.add(comp, topfivezbs, ret);
		}

		pipe = new GszbPipe(topfivezbs, gfhj, date, compositeConfig);
		List<Double[]> gfhjZbs = pipe.getGszb();
		values.add(gfhjZbs);

		pipe = new GszbPipe(topfivezbs, org.getCompany(CompanyType.ZHGS_SYB)
				.getSubCompanys(), date, standardConfig);
		List<Double[]> zhgsZbs = pipe.getGszb();
		values.add(zhgsZbs);

		dataSource.add(org.getCompany(CompanyType.GFGS), topfivezbs, gfhjZbs);
		dataSource.add(org.getCompany(CompanyType.ZHGS_SYB), topfivezbs, zhgsZbs);

		List<Company> jthj = new ArrayList<Company>();
		jthj.add(org.getCompany(CompanyType.GFGS));
		jthj.add(org.getCompany(CompanyType.ZHGS_SYB));

		pipe = new GszbPipe(topfivezbs, jthj, date, compositeConfig);
		List<Double[]> jthjZbs = pipe.getGszb();
		values.add(jthjZbs);

		List<Double[]> ret = new ArrayList<Double[]>();
		Double[] tempValues = null;
		int length = 0;
		List<List<Double[]>> result = new ArrayList<List<Double[]>>();

		for (List<Double[]> JDZBMYList : values) {
			for (Double[] JDZBMY : JDZBMYList) {
				length = JDZBMY.length - 1;
				tempValues = new Double[length];
				for (int i = 0; i < length; i++) {
					tempValues[i] = JDZBMY[i];
				}
				ret.add(tempValues);
			}
			result.add(ret);
			ret = new ArrayList<Double[]>();
		}

		return makeGroupResult(result);
	}

	@Override
	public List<String[]> getGdwzb(Date d, List<Company> comps) {
		List<Integer> zbs = this.getAllZbs(comps);
		IPipeConfigurator configurator;
		Company xnySyb = companyManager.getBMDBOrganization().getCompany(
				CompanyType.XNYSYB);
		CompanyType type = comps.get(0).getType();
		//if (type == xnySyb.getType() || xnySyb.contains(comps.get(0))) {
			//configurator = getConfiguratorFactory().getStandardConfigurator();
		//} else {
			configurator = getConfiguratorFactory().getStandardConfigurator();
		//}
		GszbPipe pipe = new GszbPipe(zbs, comps, d, configurator);
		return makeZbResult(zbs, pipe.getGszb());
	}

	@Override
	public List<String[]> getGcySecondSeasonPredictionZBs(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator standardConfig = getConfiguratorFactory()
				.getSecondSeasonPredictionConfigurator();
		List<Company> gfhj = new ArrayList<Company>();
		gfhj.add(org.getCompany(CompanyType.SBDCYJT));
		gfhj.add(org.getCompany(CompanyType.XNYSYB));
		gfhj.add(org.getCompany(CompanyType.NYSYB));
		gfhj.add(org.getCompany(CompanyType.JCKGS_SYB));
		gfhj.add(org.getCompany(CompanyType.GJGCGS_SYB));
		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		List<List<Double[]>> values = new ArrayList<List<Double[]>>();

		IPipeConfigurator compositeConfig = getConfiguratorFactory()
				.getSecondSeasonPredictionCompositeConfigurator(getAccFactory().getCompositeAcc(dataSource));
		GszbPipe pipe = null;
		for (Company comp : gfhj) {
			pipe = new GszbPipe(topfivezbs, comp.getSubCompanys(), date,
					standardConfig);
			List<Double[]> ret = pipe.getGszb();
			values.add(ret);
			dataSource.add(comp, topfivezbs, ret);
		}

		pipe = new GszbPipe(topfivezbs, gfhj, date, compositeConfig);
		List<Double[]> gfhjZbs = pipe.getGszb();
		values.add(gfhjZbs);

		pipe = new GszbPipe(topfivezbs, org.getCompany(CompanyType.ZHGS_SYB)
				.getSubCompanys(), date, standardConfig);
		List<Double[]> zhgsZbs = pipe.getGszb();
		values.add(zhgsZbs);

		dataSource.add(org.getCompany(CompanyType.GFGS), topfivezbs, gfhjZbs);
		dataSource.add(org.getCompany(CompanyType.ZHGS_SYB), topfivezbs, zhgsZbs);

		List<Company> jthj = new ArrayList<Company>();
		jthj.add(org.getCompany(CompanyType.GFGS));
		jthj.add(org.getCompany(CompanyType.ZHGS_SYB));

		pipe = new GszbPipe(topfivezbs, jthj, date, compositeConfig);
		List<Double[]> jthjZbs = pipe.getGszb();
		values.add(jthjZbs);

		return makeGroupResult(values);
	}

	@Override
	public List<String[]> getGcyFirstSeasonPredictionZBs(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator standardConfig = getConfiguratorFactory()
				.getFirstSeasonPredictionConfigurator();
		List<Company> gfhj = new ArrayList<Company>();
		gfhj.add(org.getCompany(CompanyType.SBDCYJT));
		gfhj.add(org.getCompany(CompanyType.XNYSYB));
		gfhj.add(org.getCompany(CompanyType.NYSYB));
		gfhj.add(org.getCompany(CompanyType.JCKGS_SYB));
		gfhj.add(org.getCompany(CompanyType.GJGCGS_SYB));

		List<List<Double[]>> values = new ArrayList<List<Double[]>>();
		CompositeAccDataSource dataSource = new CompositeAccDataSource();

		IPipeConfigurator compositeConfig = getConfiguratorFactory()
				.getFirstSeasonPredictionCompositeConfigurator(getAccFactory().getCompositeAcc(dataSource));
		GszbPipe pipe = null;
		for (Company comp : gfhj) {
			pipe = new GszbPipe(topfivezbs, comp.getSubCompanys(), date,
					standardConfig);
			List<Double[]> ret = pipe.getGszb();
			values.add(ret);
			dataSource.add(comp, topfivezbs, ret);
		}

		pipe = new GszbPipe(topfivezbs, gfhj, date, compositeConfig);
		List<Double[]> gfhjZbs = pipe.getGszb();
		values.add(gfhjZbs);

		pipe = new GszbPipe(topfivezbs, org.getCompany(CompanyType.ZHGS_SYB)
				.getSubCompanys(), date, standardConfig);
		List<Double[]> zhgsZbs = pipe.getGszb();
		values.add(zhgsZbs);

		dataSource.add(org.getCompany(CompanyType.GFGS), topfivezbs, gfhjZbs);
		dataSource.add(org.getCompany(CompanyType.ZHGS_SYB), topfivezbs, zhgsZbs);

		List<Company> jthj = new ArrayList<Company>();
		jthj.add(org.getCompany(CompanyType.GFGS));
		jthj.add(org.getCompany(CompanyType.ZHGS_SYB));

		pipe = new GszbPipe(topfivezbs, jthj, date, compositeConfig);
		List<Double[]> jthjZbs = pipe.getGszb();
		values.add(jthjZbs);

		return makeGroupResult(values);
	}

	@Override
	public List<String[]> getGdwFirstSeasonPredictionZBs(GSZB gszb, Date date) {
		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		IAccumulator acc = this.getAccFactory().getCompositeAcc(dataSource);
		return getCompanyTop5zb(
				gszb, 
				date, 
				this.getConfiguratorFactory().getFirstSeasonPredictionConfigurator(),
				this.getConfiguratorFactory().getFirstSeasonPredictionCompositeConfigurator(acc), 
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
				this.getConfiguratorFactory().getSecondSeasonPredictionCompositeConfigurator(acc), 
				dataSource);
	}

	@Override
	public List<String[]> getGdwJDZBMY(GSZB gszb, Date date) {
		CompositeAccDataSource dataSource = new CompositeAccDataSource();
		IAccumulator acc = this.getAccFactory().getCompositeAcc(dataSource);
		return getCompanyTop5zb(
				gszb, 
				date, 
				this.getConfiguratorFactory().getJDZBMYConfigurator(),
				this.getConfiguratorFactory().getJdzbmyCompositeConfigurator(acc), 
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
}
