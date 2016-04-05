package com.tbea.ic.operation.service.yszkrb;

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
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.ysdaily.YSDAILYDao;
import com.tbea.ic.operation.service.util.pipe.core.CompositePipe;
import com.tbea.ic.operation.service.yszkrb.pipe.configurator.ConfiguratorFactory;

@Service
@Transactional("transactionManager")
public class YSZKRBServiceImpl implements YSZKRBService{

	@Autowired
	YSDAILYDao ysdailyDao;
	
	ConfiguratorFactory configFactory;
	
	CompanyManager companyManager;
	
	private static Map<Company, List<Company>> computeMap = new HashMap<Company, List<Company>>();

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager mgr){
		companyManager = mgr;
		Organization org = mgr.getBMDBOrganization();
		
//		输变电	沈变公司
//		衡变公司
//		新变厂
//		其中：天变公司
//		鲁缆公司
//		新缆厂
//		德缆公司
//		小计
//	新能源	新能源公司
//		新特能源公司
//		小计
//	能源	天池能源公司
//		能动公司
//		小计
//	工程	进出口公司
//		国际工程公司
//		小计
//	众和公司	
//	公司合计	

		List<Company> companies = new ArrayList<Company>();
		companies.add(org.getCompany(CompanyType.SBGS));
		companies.add(org.getCompany(CompanyType.HBGS));
		companies.add(org.getCompany(CompanyType.XBC));
		companies.add(org.getCompany(CompanyType.TBGS));
		companies.add(org.getCompany(CompanyType.LLGS));
		companies.add(org.getCompany(CompanyType.XLC));
		companies.add(org.getCompany(CompanyType.DLGS));
		computeMap.put(org.getCompany(CompanyType.SBDCYJT), companies);
		
		companies = new ArrayList<Company>();
		companies.add(org.getCompany(CompanyType.XTNYGS));
		companies.add(org.getCompany(CompanyType.XNYGS));
		computeMap.put(org.getCompany(CompanyType.XNYSYB), companies);

		companies = new ArrayList<Company>();
		companies.add(org.getCompany(CompanyType.TCNY));
		companies.add(org.getCompany(CompanyType.NDGS));
		computeMap.put(org.getCompany(CompanyType.NYSYB), companies);
	
		companies = new ArrayList<Company>();
		companies.add(org.getCompany(CompanyType.JCKGS_JYDW));
		companies.add(org.getCompany(CompanyType.GJGCGS_GFGS));
		computeMap.put(org.getCompany(CompanyType.GJGCGS_SYB), companies);
		
		companies = new ArrayList<Company>();
		companies.add(org.getCompany(CompanyType.SBDCYJT));
		companies.add(org.getCompany(CompanyType.XNYSYB));
		companies.add(org.getCompany(CompanyType.NYSYB));
		companies.add(org.getCompany(CompanyType.GJGCGS_SYB));
		companies.add(org.getCompany(CompanyType.ZHGS));
		computeMap.put(org.getCompany(CompanyType.GFGS), companies);
	}
	
	@Autowired
	public void init(){
		configFactory = new ConfiguratorFactory(ysdailyDao);
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
	public List<String[]> getYszkData(Date date) {
		CompositePipe pipe = new CompositePipe(GSZB.YSZK_DAILY_REPORT.value(), date, configFactory.getYszkrbCompositeConfigurator(computeMap));
		Organization org = companyManager.getBMDBOrganization();
		pipe.addCompany(computeMap.get(org.getCompany(CompanyType.SBDCYJT)), configFactory.getYszkrbConfigurator())
			.addCompany(org.getCompany(CompanyType.SBDCYJT), null)
			.addCompany(computeMap.get(org.getCompany(CompanyType.XNYSYB)), configFactory.getYszkrbConfigurator())
			.addCompany(org.getCompany(CompanyType.XNYSYB), null)
			.addCompany(computeMap.get(org.getCompany(CompanyType.NYSYB)), configFactory.getYszkrbConfigurator())
			.addCompany(org.getCompany(CompanyType.NYSYB), null)
			.addCompany(computeMap.get(org.getCompany(CompanyType.GJGCGS_SYB)), configFactory.getYszkrbConfigurator())
			.addCompany(org.getCompany(CompanyType.GJGCGS_SYB), null)			
			.addCompany(org.getCompany(CompanyType.ZHGS), configFactory.getYszkrbConfigurator())
			.addCompany(org.getCompany(CompanyType.GFGS), null);			
		return makeResult(pipe.getData());
	}


}
