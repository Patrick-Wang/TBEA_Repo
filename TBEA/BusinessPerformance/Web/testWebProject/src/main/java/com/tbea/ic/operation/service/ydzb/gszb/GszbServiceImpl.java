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
	
	private static List<Integer> zyzbs = new ArrayList<Integer>();
	static {
		zyzbs.add(GSZB.LRZE.getValue());
		zyzbs.add(GSZB.XSSR.getValue());
		zyzbs.add(GSZB.JYXJXJL.getValue());
		zyzbs.add(GSZB.YSZK.getValue());
		zyzbs.add(GSZB.CH.getValue());
	}
	

	private static List<Integer> srqyzbs = new ArrayList<Integer>();
	static {
		srqyzbs.add(GSZB.XSSR.getValue());
		srqyzbs.add(GSZB.ZZYSR.getValue());
		srqyzbs.add(GSZB.GCXMSR.getValue());
		srqyzbs.add(GSZB.YYSSR.getValue());
		srqyzbs.add(GSZB.WLMYSR.getValue());
		//srqyzbs.add(GSZB.DCSR.getValue());
		srqyzbs.add(GSZB.FWLSR.getValue());
		srqyzbs.add(GSZB.HTQYE.getValue());
		srqyzbs.add(GSZB.GJQY.getValue());
		srqyzbs.add(GSZB.QZDJQY.getValue());
		srqyzbs.add(GSZB.QZCTQY.getValue());
		srqyzbs.add(GSZB.GNQY.getValue());
		srqyzbs.add(GSZB.QZDJQY_1.getValue());
		srqyzbs.add(GSZB.QZCTQY_1.getValue());
		//srqyzbs.add(GSZB.X.getValue());
	}
	
	private static List<Integer> topfivezbs = new ArrayList<Integer>();
	static {
		zyzbs.add(GSZB.LRZE.getValue());
		zyzbs.add(GSZB.XSSR.getValue());
		zyzbs.add(GSZB.JYXJXJL.getValue());
		zyzbs.add(GSZB.YSZK.getValue());
		zyzbs.add(GSZB.CH.getValue());

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
		GszbPipe pipe = new GszbPipe(gsztzbs, filterCompany(org.getCompany(CompanyType.GFGS).getSubCompanys()), date, new StandardConfigurator(ndjhzbDao, ydjhzbDao,
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
		
		GszbPipe pipe = new GszbPipe(zyzbs, filterCompany(org.getCompany(CompanyType.SBDCYJT).getSubCompanys()), date, config);
		List<Double[]> sbdZbs = pipe.getGszb();
		
		pipe = new GszbPipe(zyzbs, filterCompany(org.getCompany(CompanyType.XNYSYB).getSubCompanys()), date, config);
		List<Double[]> xnyZbs = pipe.getGszb();
		
		pipe = new GszbPipe(zyzbs, filterCompany(org.getCompany(CompanyType.NYSYB).getSubCompanys()), date, config);
		List<Double[]> nyZbs = pipe.getGszb();
		
		pipe = new GszbPipe(zyzbs, org.getCompany(CompanyType.JCKGS_SYB).getSubCompanys(), date, config);
		List<Double[]> jckZbs = pipe.getGszb();
		
		pipe = new GszbPipe(zyzbs,  org.getCompany(CompanyType.GJGCGS_SYB).getSubCompanys(), date, config);
		List<Double[]> gjgcZbs = pipe.getGszb();
		
		pipe = new GszbPipe(zyzbs, org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys(), date, config);
		List<Double[]> zhgsZbs = pipe.getGszb();
		
		
		CompositeAccumulator acc = new CompositeAccumulator();
		for (int i = 0, len = zyzbs.size(); i < len; ++i){
			acc.addSrc(org.getCompany(CompanyType.SBDCYJT), zyzbs.get(i), sbdZbs.get(i));
			acc.addSrc(org.getCompany(CompanyType.XNYSYB), zyzbs.get(i), xnyZbs.get(i));
			acc.addSrc(org.getCompany(CompanyType.NYSYB), zyzbs.get(i), nyZbs.get(i));
			acc.addSrc(org.getCompany(CompanyType.JCKGS_SYB), zyzbs.get(i), jckZbs.get(i));
			acc.addSrc(org.getCompany(CompanyType.GJGCGS_SYB), zyzbs.get(i), gjgcZbs.get(i));
		}
		
		List<Company> gfhj = new ArrayList<Company>();
		gfhj.add(org.getCompany(CompanyType.SBDCYJT));
		gfhj.add(org.getCompany(CompanyType.XNYSYB));
		gfhj.add(org.getCompany(CompanyType.NYSYB));
		gfhj.add(org.getCompany(CompanyType.JCKGS_SYB));
		gfhj.add(org.getCompany(CompanyType.GJGCGS_SYB));
		config = new CompositeConfigurator(acc);
		pipe = new GszbPipe(zyzbs, gfhj, date, config);
		List<Double[]> gfhjZbs = pipe.getGszb();
	
		
		acc = new CompositeAccumulator();
		for (int i = 0, len = zyzbs.size(); i < len; ++i){
			acc.addSrc(org.getCompany(CompanyType.GFGS), zyzbs.get(i), gfhjZbs.get(i));
			acc.addSrc(org.getCompany(CompanyType.ZHGS_SYB), zyzbs.get(i), zhgsZbs.get(i));
		}
		
		List<Company> jthj = new ArrayList<Company>();
		jthj.add(org.getCompany(CompanyType.GFGS));
		jthj.add(org.getCompany(CompanyType.ZHGS_SYB));
		config = new CompositeConfigurator(acc);
		pipe = new GszbPipe(zyzbs, jthj, date, config);
		List<Double[]> jthjZbs = pipe.getGszb();
		
		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0; i < 40; ++i){
			result.add(new String[config.columnCount()]);
		}
		
		makeResult(result, sbdZbs, 0);
		makeResult(result, xnyZbs, 1);
		makeResult(result, nyZbs, 2);
		makeResult(result, jckZbs, 3);
		makeResult(result, gjgcZbs, 4);
		makeResult(result, gfhjZbs, 5);
		makeResult(result, zhgsZbs, 6);
		makeResult(result, jthjZbs, 7);
		
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
	public List<String[]> getCompanyTop5zb(GSZB gszb, Date date) {
		Organization org = companyManager.getBMDBOrganization();
		IPipeConfigurator config = new StandardConfigurator(ndjhzbDao, ydjhzbDao,
				ydzbztDao, sjzbDao, yj20zbDao,
				yj28zbDao, zbxxDao, companyManager);
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.SBGS).getSubCompanys()), date, config);
//		List<Double[]> sbgsZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.HBGS).getSubCompanys()), date, config);
//		List<Double[]> hbgsZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.XBGS).getSubCompanys()), date, config);
//		List<Double[]> xbgsZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.LLGS).getSubCompanys()), date, config);
//		List<Double[]> llgsZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.XLC).getSubCompanys()), date, config);
//		List<Double[]> xlcZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.DLGS).getSubCompanys()), date, config);
//		List<Double[]> dlZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.XTNYGS).getSubCompanys()), date, config);
//		List<Double[]> xtnyZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.XNYGS).getSubCompanys()), date, config);
//		List<Double[]> xnyZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.TCNY).getSubCompanys()), date, config);
//		List<Double[]> tcnyZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.NDGS).getSubCompanys()), date, config);
//		List<Double[]> ndgsZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.JCKGS_SYB).getSubCompanys()), date, config);
//		List<Double[]> jckZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.GJGCGS_SYB).getSubCompanys()), date, config);
//		List<Double[]> gjgcZbs = pipe.getGszb();
//		
//		GszbPipe pipe = new GszbPipe(gszb, filterCompany(org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys()), date, config);
//		List<Double[]> zhgsZbs = pipe.getGszb();
		return null;
	}

}
