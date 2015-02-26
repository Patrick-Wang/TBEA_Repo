package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.service.ydzb.gszb.acc.CompositeAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.CompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.SrqyConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.StandardConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;

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

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
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
		gsztzbs.add(GSZB.JZCSYL.getValue());
	}
	
	private static List<Integer> topfivezbs = new ArrayList<Integer>();
	static {
		topfivezbs.add(GSZB.LRZE.getValue());
		topfivezbs.add(GSZB.XSSR.getValue());
		topfivezbs.add(GSZB.JYXJXJL.getValue());
		topfivezbs.add(GSZB.YSZK.getValue());
		topfivezbs.add(GSZB.CH.getValue());
	}
	
	//公司整体2（收入签约分结构）
	private static List<Integer> srqyzbs = new ArrayList<Integer>();
	static {
		//销售收入
		srqyzbs.add(GSZB.XSSR.getValue());//营业收入
		srqyzbs.add(GSZB.ZZYSR.getValue());//制造业收入（输变电收入、新能源收入、能源煤炭收入、众和制造业收入）
		srqyzbs.add(GSZB.GCXMSR.getValue());//工程项目收入（输变电收入、新能源产业成套工程收入、能源产业工程收入、进出口公司收入、国际工程公司收入、众和公司收入）
		srqyzbs.add(GSZB.YYSSR.getValue());//运营商收入（新能源产业运营商收入）
		srqyzbs.add(GSZB.MTXSSR.getValue());//煤炭销售收入（能源产业收入）
		srqyzbs.add(GSZB.WLMYSR.getValue());//物流贸易收入（输变电物流收入、能源产业贸易收入、众和公司物流业收入）
		srqyzbs.add(GSZB.FWLSR.getValue());//服务类收入（新能源产业服务业收入、能源产业服务业收入、众和公司服务业收入）
		
		//合同签约额
		srqyzbs.add(GSZB.HTQYE.getValue());//合同签约额
		srqyzbs.add(GSZB.GJQY.getValue());//输变电、进出口国际签约
		srqyzbs.add(GSZB.QZDJQY.getValue());//单机签约（输变电国际单机签约）
		srqyzbs.add(GSZB.QZCTQY.getValue());//成套签约（输变电国际成套签约、新能源产业国际成套签约、进出口公司国际成套签约、国际工程公司国际成套签约）
		srqyzbs.add(GSZB.GNQY.getValue());//输变电国内签约
		srqyzbs.add(GSZB.QZDJQY_1.getValue());//  单机签约（输变电国内单机签约）
		srqyzbs.add(GSZB.QZCTQY_1.getValue());//  成套签约（输变电国内成套签约）
		//新能源产业签约
		//能源产业签约
		//众和公司签约
		srqyzbs.addAll(SrqyConfigurator.getSpecialZbs());
		srqyzbs.add(GSZB.CL.getValue());//产量 
		srqyzbs.add(GSZB.BYQ.getValue());//变压器(万KVA)
		srqyzbs.add(GSZB.XLYTL.getValue());//线缆用铜量(吨)
		srqyzbs.add(GSZB.XLYLL.getValue());//线缆用铝量(吨)
		srqyzbs.add(GSZB.DJG.getValue());//多晶硅(吨) 
		srqyzbs.add(GSZB.GP.getValue());//硅片(片)
		srqyzbs.add(GSZB.FDLWD.getValue());//发电量（万度）（众和公司、新特能源公司、能动公司）
		srqyzbs.add(GSZB.NBQmw.getValue());//逆变器（MW）
		srqyzbs.add(GSZB.MT.getValue());//煤炭(万吨)
		srqyzbs.add(GSZB.QZDYLBCLD.getValue());//电子铝箔（吨）
		srqyzbs.add(GSZB.DJBHCLPM.getValue());//电极箔化成量（平米）
		srqyzbs.add(GSZB.LBD.getValue());//铝箔（吨）
		srqyzbs.add(GSZB.DJBPM.getValue());//电极箔化成量（平米）
	}
	

	private static Map<Integer, ZBXX> zbxxMap = new HashMap<Integer, ZBXX>();

	private ZBXX getZbxx(Integer zbId) {
		if (zbxxMap.isEmpty()) {
			List<ZBXX> zbxxs = zbxxDao.getZbs(gsztzbs);
			for (ZBXX zbxx : zbxxs) {
				zbxxMap.put(zbxx.getId(), zbxx);
			}
		}
		return zbxxMap.get(zbId);
	}

	private List<Company> filterCompany(List<Company> companies) {
		List<Company> retComps = new ArrayList<Company>();
		for (Company comp : companies) {
			if (!comp.getSubCompanys().isEmpty()) {
				retComps.addAll(comp.getSubCompanys());
			}
		}
		return retComps;
	}

	
	private List<String[]> makeResult(List<Double[]> gszbs){

		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0, len = gsztzbs.size(); i < len; ++i) {
			String[] zbRow = new String[16];
			Double[] gszbRow = gszbs.get(i);
			zbRow[0] = getZbxx(gsztzbs.get(i)).getName();
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
		Organization org = companyManager.getBMDBOrganization();
		List<Company> comps = org.getCompany(CompanyType.GFGS).getSubCompanys();
		comps.add(org.getCompany(CompanyType.ZHGS_SYB));
		GszbPipe pipe = new GszbPipe(gsztzbs, filterCompany(comps), date, new StandardConfigurator(ndjhzbDao, ydjhzbDao,
				ydzbztDao, sjzbDao, yj20zbDao,
				yj28zbDao, zbxxDao, companyManager));	
		return makeResult(pipe.getGszb());
	}
	
	@Override
	public List<String[]> getGcyzb(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator config = new StandardConfigurator(ndjhzbDao, ydjhzbDao,
				ydzbztDao, sjzbDao, yj20zbDao,
				yj28zbDao, zbxxDao, companyManager);
		List<Company> gfhj = new ArrayList<Company>();
		gfhj.add(org.getCompany(CompanyType.SBDCYJT));
		gfhj.add(org.getCompany(CompanyType.XNYSYB));
		gfhj.add(org.getCompany(CompanyType.NYSYB));
		gfhj.add(org.getCompany(CompanyType.JCKGS_SYB));
		gfhj.add(org.getCompany(CompanyType.GJGCGS_SYB));
		
		List<List<Double[]>> values = new ArrayList<List<Double[]>>();
		CompositeAccumulator acc = new CompositeAccumulator();
		GszbPipe pipe = null;
		for (Company comp : gfhj){
			pipe = new GszbPipe(topfivezbs, comp.getSubCompanys(), date, config);
			List<Double[]> ret = pipe.getGszb();
			values.add(ret);
			acc.addSrc(comp, topfivezbs, ret);
		}

		config = new CompositeConfigurator(acc);
		pipe = new GszbPipe(topfivezbs, gfhj, date, config);
		List<Double[]> gfhjZbs = pipe.getGszb();
		values.add(gfhjZbs);
		
		pipe = new GszbPipe(topfivezbs, org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys(), date, config);
		List<Double[]> zhgsZbs = pipe.getGszb();
		values.add(zhgsZbs);
		
		acc = new CompositeAccumulator();
		acc.addSrc(org.getCompany(CompanyType.GFGS), topfivezbs, gfhjZbs);
		acc.addSrc(org.getCompany(CompanyType.ZHGS_SYB), topfivezbs, zhgsZbs);
		
		List<Company> jthj = new ArrayList<Company>();
		jthj.add(org.getCompany(CompanyType.GFGS));
		jthj.add(org.getCompany(CompanyType.ZHGS_SYB));
		config = new CompositeConfigurator(acc);
		pipe = new GszbPipe(topfivezbs, jthj, date, config);
		List<Double[]> jthjZbs = pipe.getGszb();
		values.add(jthjZbs);
		
		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0; i < 40; ++i){
			result.add(new String[config.getColumnCount()]);
		}
		
		for (int i = 0, len = values.size(); i < len; ++i){
			makeResult(result, values.get(i), i);
		}
		
		return result;
	}

	private void makeResult(List<String[]> result, List<Double[]> zbs, int row) {
		for (int i = 0; i < zbs.size(); ++i){
			for (int j = 0; j < zbs.get(i).length; ++j){
				result.get(i * 8 + row)[j] = zbs.get(i)[j] + "";
			}
		}
	}
	
	@Override
	public List<String[]> getSrqy(Date date){
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator config = new SrqyConfigurator(ndjhzbDao, ydjhzbDao,
				ydzbztDao, sjzbDao, yj20zbDao,
				yj28zbDao, zbxxDao, companyManager);
		List<Company> comps = org.getCompany(CompanyType.GFGS).getSubCompanys();
		comps.add(org.getCompany(CompanyType.ZHGS_SYB));
		GszbPipe pipe = new GszbPipe(srqyzbs, filterCompany(comps), date, config);
		List<Double[]> zbs = pipe.getGszb();
		return null;
	}

	@Override
	public List<String[]> getCompanyTop5zb(GSZB gsTop5zb, Date date) {
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator config = new StandardConfigurator(ndjhzbDao, ydjhzbDao,
				ydzbztDao, sjzbDao, yj20zbDao,
				yj28zbDao, zbxxDao, companyManager);
		GszbPipe pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.SBGS), date, config);
		List<Double[]> sbgsZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.HBGS), date, config);
		List<Double[]> hbgsZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.XBC), date, config);
		List<Double[]> xbgsZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.LLGS), date, config);
		List<Double[]> llgsZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.XLC), date, config);
		List<Double[]> xlcZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.DLGS), date, config);
		List<Double[]> dlZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.XTNYGS), date, config);
		List<Double[]> xtnyZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.XNYGS), date, config);
		List<Double[]> xnyZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.TCNY), date, config);
		List<Double[]> tcnyZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.NDGS), date, config);
		List<Double[]> ndgsZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.JCKGS_SYB), date, config);
		List<Double[]> jckZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.GJGCGS_SYB), date, config);
		List<Double[]> gjgcZbs = pipe.getGszb();
		
		pipe = new GszbPipe(gsTop5zb.getValue(), org.getCompany(CompanyType.ZHGS_SYB), date, config);
		List<Double[]> zhgsZbs = pipe.getGszb();
		
		CompositeAccumulator acc = new CompositeAccumulator();
		
		acc.addSrc(org.getCompany(CompanyType.SBGS), gsTop5zb.getValue(), sbgsZbs.get(0));
		acc.addSrc(org.getCompany(CompanyType.HBGS), gsTop5zb.getValue(), hbgsZbs.get(0));
		acc.addSrc(org.getCompany(CompanyType.XBC), gsTop5zb.getValue(), xbgsZbs.get(0));
		acc.addSrc(org.getCompany(CompanyType.LLGS), gsTop5zb.getValue(), llgsZbs.get(0));
		acc.addSrc(org.getCompany(CompanyType.XLC), gsTop5zb.getValue(), xlcZbs.get(0));
		acc.addSrc(org.getCompany(CompanyType.DLGS), gsTop5zb.getValue(), dlZbs.get(0));
		
		acc.addSrc(org.getCompany(CompanyType.XTNYGS), gsTop5zb.getValue(), xtnyZbs.get(0));
		acc.addSrc(org.getCompany(CompanyType.XNYGS), gsTop5zb.getValue(), xnyZbs.get(0));
		acc.addSrc(org.getCompany(CompanyType.TCNY), gsTop5zb.getValue(), tcnyZbs.get(0));
		acc.addSrc(org.getCompany(CompanyType.NDGS), gsTop5zb.getValue(), ndgsZbs.get(0));
		acc.addSrc(org.getCompany(CompanyType.JCKGS_SYB), gsTop5zb.getValue(), jckZbs.get(0));
		acc.addSrc(org.getCompany(CompanyType.GJGCGS_SYB), gsTop5zb.getValue(), gjgcZbs.get(0));
		acc.addSrc(org.getCompany(CompanyType.ZHGS_SYB), gsTop5zb.getValue(), zhgsZbs.get(0));
		
		//输变电指标
		List<Company> sbdgs = new ArrayList<Company>();
		sbdgs.add(org.getCompany(CompanyType.SBGS));
		sbdgs.add(org.getCompany(CompanyType.HBGS));
		sbdgs.add(org.getCompany(CompanyType.XBC));
		sbdgs.add(org.getCompany(CompanyType.LLGS));
		sbdgs.add(org.getCompany(CompanyType.XLC));
		sbdgs.add(org.getCompany(CompanyType.DLGS));
		config = new CompositeConfigurator(acc);
		pipe = new GszbPipe(gsTop5zb.getValue(), sbdgs, date, config);
		List<Double[]> sbdhjZbs = pipe.getGszb();
		acc.addSrc(org.getCompany(CompanyType.SBDCYJT), gsTop5zb.getValue(), sbdhjZbs.get(0));
		
		//新能源指标
		List<Company> xnygs = new ArrayList<Company>();
		xnygs.add(org.getCompany(CompanyType.XTNYGS));
		xnygs.add(org.getCompany(CompanyType.XNYGS));
		config = new CompositeConfigurator(acc);
		pipe = new GszbPipe(gsTop5zb.getValue(), xnygs, date, config);
		List<Double[]> xnyhjZbs = pipe.getGszb();
		acc.addSrc(org.getCompany(CompanyType.XNYSYB), gsTop5zb.getValue(), xnyhjZbs.get(0));

		//能源指标
		List<Company> nygs = new ArrayList<Company>();
		nygs.add(org.getCompany(CompanyType.TCNY));
		nygs.add(org.getCompany(CompanyType.NDGS));
		config = new CompositeConfigurator(acc);
		pipe = new GszbPipe(gsTop5zb.getValue(), nygs, date, config);
		List<Double[]> nyhjZbs = pipe.getGszb();
		acc.addSrc(org.getCompany(CompanyType.NYSYB), gsTop5zb.getValue(), nyhjZbs.get(0));
		
		//工程公司指标
		List<Company> gcgs = new ArrayList<Company>();
		gcgs.add(org.getCompany(CompanyType.JCKGS_SYB));
		gcgs.add(org.getCompany(CompanyType.GJGCGS_SYB));
		config = new CompositeConfigurator(acc);
		pipe = new GszbPipe(gsTop5zb.getValue(), gcgs, date, config);
		List<Double[]> gcgshjZbs = pipe.getGszb();
		acc.addSrc(org.getCompany(CompanyType.GJGCGS_SYB), gsTop5zb.getValue(), gcgshjZbs.get(0));
		//股份合计指标
		List<Company> gfgs = new ArrayList<Company>();
		gfgs.add(org.getCompany(CompanyType.SBDCYJT));
		gfgs.add(org.getCompany(CompanyType.XNYSYB));
		gfgs.add(org.getCompany(CompanyType.NYSYB));
		gfgs.add(org.getCompany(CompanyType.GJGCGS_SYB));
		config = new CompositeConfigurator(acc);
		pipe = new GszbPipe(gsTop5zb.getValue(), gfgs, date, config);
		List<Double[]> gfgshjZbs = pipe.getGszb();
		acc.addSrc(org.getCompany(CompanyType.GFGS), gsTop5zb.getValue(), gfgshjZbs.get(0));
		
		//集团合计指标
		
		List<Company> jtgs = new ArrayList<Company>();
		jtgs.add(org.getCompany(CompanyType.GFGS));
		jtgs.add(org.getCompany(CompanyType.ZHGS_SYB));

		config = new CompositeConfigurator(acc);
		pipe = new GszbPipe(gsTop5zb.getValue(), jtgs, date, config);
		List<Double[]> jtgshjZbs = pipe.getGszb();

		
		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0; i < 40; ++i){
			result.add(new String[config.getColumnCount()]);
		}
		

		makeResult(result, sbgsZbs, 0);
		makeResult(result, hbgsZbs, 1);
		makeResult(result, xbgsZbs, 2);
		makeResult(result, llgsZbs, 3);
		makeResult(result, xlcZbs, 4);
		makeResult(result, dlZbs, 5);
		makeResult(result, sbdhjZbs, 6);
		makeResult(result, xtnyZbs, 7);
		
		makeResult(result, xnyZbs, 8);
		makeResult(result, xnyhjZbs, 9);
		makeResult(result, tcnyZbs, 10);
		makeResult(result, ndgsZbs, 11);
		makeResult(result, nyhjZbs, 12);
		makeResult(result, jckZbs, 13);
		makeResult(result, gjgcZbs, 14);
		makeResult(result, gcgshjZbs, 15);
		
		makeResult(result, gfgshjZbs, 16);
		makeResult(result, zhgsZbs, 17);
		makeResult(result, jtgshjZbs, 18);
		return result;
	}

}
