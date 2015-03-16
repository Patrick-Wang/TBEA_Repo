package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;

//收入签约分结构
public class SrqyConfigurator implements IPipeConfigurator {

	StandardConfigurator standardConfigurator;
	
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
			return "     新能源产业签约";
		case nycyQyId:
			return "     能源产业签约";
		case zhgsQyId:
			return "     众和公司签约";
		}
		return null;
	}

	public static List<Integer> getSpecialZbs(){
		return specialZbs;
	}
	
	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;
	CompanyManager companyManager;

	public SrqyConfigurator(IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc, CompanyManager companyManager) {
		this.companyManager = companyManager;
		this.sjAcc = sjAcc;
		this.yjhAcc = yjhAcc;
		this.njhAcc = njhAcc;
	}

	@Override
	public void onConfiguring(GszbPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Integer> zbs = pipe.getZbIds();
		DateHelper dh = new DateHelper(pipe.getDate());

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
