package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
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
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.NjhzbAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.SjzbAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.YjhzbAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;

//收入签约分结构
public class SrqyConfigurator implements IPipeConfigurator {

	NDJHZBDao ndjhzbDao;

	YDJHZBDao ydjhzbDao;

	YDZBZTDao ydzbztDao;

	SJZBDao sjzbDao;

	YJ20ZBDao yj20zbDao;

	YJ28ZBDao yj28zbDao;

	ZBXXDao zbxxDao;

	CompanyManager companyManager;
	
	private final static int xnycyQyId = 100021;
	private final static int nycyQyId = 100022;
	private final static int zhgsQyId = 100023;
	
	private static List<Integer> specialZbs = new ArrayList<Integer>();
	static{
		specialZbs.add(xnycyQyId);//新能源产业签约
		specialZbs.add(nycyQyId);//能源产业签约
		specialZbs.add(zhgsQyId);//众和公司签约
	}
	
	public static String getSpecialZbName(Integer zb){
		switch(zb){
		case xnycyQyId:
			return "新能源产业签约";
		case nycyQyId:
			return "能源产业签约";
		case zhgsQyId:
			return "众和公司签约";
		}
		return null;
	}

	public static List<Integer> getSpecialZbs(){
		return specialZbs;
	}
	
	public SrqyConfigurator(NDJHZBDao ndjhzbDao, YDJHZBDao ydjhzbDao,
			YDZBZTDao ydzbztDao, SJZBDao sjzbDao, YJ20ZBDao yj20zbDao,
			YJ28ZBDao yj28zbDao, ZBXXDao zbxxDao, CompanyManager companyManager) {
		this.ndjhzbDao = ndjhzbDao;
		this.ydjhzbDao = ydjhzbDao;
		this.ydzbztDao = ydzbztDao;
		this.sjzbDao = sjzbDao;
		this.yj20zbDao = yj20zbDao;
		this.yj28zbDao = yj28zbDao;
		this.zbxxDao = zbxxDao;
		this.companyManager = companyManager;
	}

	@Override
	public void onConfiguring(GszbPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Integer> zbs = pipe.getZbIds();
		DateHelper dh = new DateHelper(pipe.getDate());
		IAccumulator sjAcc = new SjzbAccumulator(sjzbDao, yj20zbDao, yj28zbDao,
				ydzbztDao);
		IAccumulator yjhAcc = new YjhzbAccumulator(ydjhzbDao);
		IAccumulator njhAcc = new NjhzbAccumulator(ndjhzbDao);

		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		
		Organization org = companyManager.getBMDBOrganization();
		// 全年计划
		pipe.add(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(allCompanies)
				.includeZbs(zbs)
				.excludeZbs(specialZbs)
				.exclude(GSZB.CL))
			.add(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(org.getCompany(CompanyType.XNYSYB).getSubCompanys())
				.include(xnycyQyId))
			.add(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(org.getCompany(CompanyType.NYSYB).getSubCompanys())
				.include(nycyQyId))
			.add(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys())
				.include(zhgsQyId))

			// 当月计划
			.add(new AccPipeFilter(yjhAcc, 1)
				.includeCompanies(allCompanies)
				.includeZbs(zbs)
				.excludeZbs(specialZbs)
				.exclude(GSZB.CL))
			.add(new AccPipeFilter(yjhAcc, 1)
				.includeCompanies(org.getCompany(CompanyType.XNYSYB).getSubCompanys())
				.include(xnycyQyId))
			.add(new AccPipeFilter(yjhAcc, 1)
				.includeCompanies(org.getCompany(CompanyType.NYSYB).getSubCompanys())
				.include(nycyQyId))
			.add(new AccPipeFilter(yjhAcc, 1)
				.includeCompanies(org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys())
				.include(zhgsQyId))
				
			// 当月实际
			.add(new AccPipeFilter(sjAcc, 2)
				.includeCompanies(allCompanies)
				.includeZbs(zbs)
				.excludeZbs(specialZbs)
				.exclude(GSZB.CL))
			.add(new AccPipeFilter(sjAcc, 2)
				.includeCompanies(org.getCompany(CompanyType.XNYSYB).getSubCompanys())
				.include(xnycyQyId))
			.add(new AccPipeFilter(sjAcc, 2)
				.includeCompanies(org.getCompany(CompanyType.NYSYB).getSubCompanys())
				.include(nycyQyId))
			.add(new AccPipeFilter(sjAcc, 2)
				.includeCompanies(org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys())
				.include(zhgsQyId))
				
			// 计划完成率
			.add(wclFilter.add(3, 2, 1))

			
			// 年度累计
			.add(new AccPipeFilter(sjAcc, 4, dh.getFirstMonth(), dh.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(zbs)
				.excludeZbs(specialZbs)
				.exclude(GSZB.CL))
			.add(new AccPipeFilter(sjAcc, 4, dh.getFirstMonth(), dh.getCur())
				.includeCompanies(org.getCompany(CompanyType.XNYSYB).getSubCompanys())
				.include(xnycyQyId))
			.add(new AccPipeFilter(sjAcc, 4, dh.getFirstMonth(), dh.getCur())
				.includeCompanies(org.getCompany(CompanyType.NYSYB).getSubCompanys())
				.include(nycyQyId))
			.add(new AccPipeFilter(sjAcc, 4, dh.getFirstMonth(), dh.getCur())
				.includeCompanies(org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys())
				.include(zhgsQyId))
				
			// 累计计划完成率
			.add(wclFilter.add(5, 4, 0))	
				
			// 去年同期
			.add(new AccPipeFilter(sjAcc, 6, dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(zbs)
				.excludeZbs(specialZbs)
				.exclude(GSZB.CL))
			.add(new AccPipeFilter(sjAcc, 6, dh.getQntq())
				.includeCompanies(org.getCompany(CompanyType.XNYSYB).getSubCompanys())
				.include(xnycyQyId))
			.add(new AccPipeFilter(sjAcc, 6, dh.getQntq())
				.includeCompanies(org.getCompany(CompanyType.NYSYB).getSubCompanys())
				.include(nycyQyId))
			.add(new AccPipeFilter(sjAcc, 6, dh.getQntq())
				.includeCompanies(org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys())
				.include(zhgsQyId))
				
			// 同比增幅
			.add(tbzzFilter.add(7, 2, 6))

			// 去年同期累计
			.add(new AccPipeFilter(sjAcc, 8, dh.getQnfirstMonth(), dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(zbs)
				.excludeZbs(specialZbs)
				.exclude(GSZB.CL))				
			.add(new AccPipeFilter(sjAcc, 8, dh.getQnfirstMonth(), dh.getQntq())
				.includeCompanies(org.getCompany(CompanyType.XNYSYB).getSubCompanys())
				.include(xnycyQyId))
			.add(new AccPipeFilter(sjAcc, 8, dh.getQnfirstMonth(), dh.getQntq())
				.includeCompanies(org.getCompany(CompanyType.NYSYB).getSubCompanys())
				.include(nycyQyId))
			.add(new AccPipeFilter(sjAcc, 8, dh.getQnfirstMonth(), dh.getQntq())
				.includeCompanies(org.getCompany(CompanyType.ZHGS_SYB).getSubCompanys())
				.include(zhgsQyId))
				
			// 同比增幅
			.add(tbzzFilter.add(9, 4, 8))
			.add(tbzzFilter.exclude(GSZB.CL))
			.add(wclFilter.exclude(GSZB.CL));
	}

	@Override
	public int getColumnCount() {
		return 10;
	}

}
