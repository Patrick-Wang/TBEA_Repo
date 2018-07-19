package com.tbea.ic.operation.service.yszkrb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.ysdaily.YSDAILYDao;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.tbea.ic.operation.service.util.pipe.core.BasicPipe;
import com.tbea.ic.operation.service.util.pipe.core.CompositePipe;
import com.tbea.ic.operation.service.yszkrb.pipe.configurator.ConfiguratorFactory;

@Service
@Transactional("transactionManager")
public class YSZKRBServiceImpl implements YSZKRBService{

	@Autowired
	YSDAILYDao ysdailyDao;
	
	ConfiguratorFactory configFactory;
	
	CompanyManager companyManager;
	
	@Autowired
	ExtendAuthorityService extAuthServ;
	
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
		companies.add(org.getCompanyByType(CompanyType.SBGS));
		companies.add(org.getCompanyByType(CompanyType.HBGS));
		companies.add(org.getCompanyByType(CompanyType.XBC));
		companies.add(org.getCompanyByType(CompanyType.LLGS));
		companies.add(org.getCompanyByType(CompanyType.XLC));
		companies.add(org.getCompanyByType(CompanyType.DLGS));
		computeMap.put(org.getCompanyByType(CompanyType.SBDCYJT), companies);
		
		companies = new ArrayList<Company>();
		companies.add(org.getCompanyByType(CompanyType.XNYGS));
		companies.add(org.getCompanyByType(CompanyType.XTNYGS));
		computeMap.put(org.getCompanyByType(CompanyType.XNYSYB), companies);

		companies = new ArrayList<Company>();
		companies.add(org.getCompanyByType(CompanyType.TCNY));
		companies.add(org.getCompanyByType(CompanyType.NDGS));
		computeMap.put(org.getCompanyByType(CompanyType.NYSYB), companies);
	
		companies = new ArrayList<Company>();
		companies.add(org.getCompanyByType(CompanyType.JCKGS_JYDW));
		companies.add(org.getCompanyByType(CompanyType.GJGCGS_GFGS));
		computeMap.put(org.getCompanyByType(CompanyType.GJGCGS_SYB), companies);
		
		companies = new ArrayList<Company>();
		companies.add(org.getCompanyByType(CompanyType.SBDCYJT));
		companies.add(org.getCompanyByType(CompanyType.XNYSYB));
		companies.add(org.getCompanyByType(CompanyType.NYSYB));
		companies.add(org.getCompanyByType(CompanyType.GJGCGS_SYB));
		companies.add(org.getCompanyByType(CompanyType.ZHGS));
		computeMap.put(org.getCompanyByType(CompanyType.GFGS), companies);
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
	public List<String[]> getYszkData(Date date, Account account) {
		if (extAuthServ.hasAuthority(account, 27)){
			CompositePipe pipe = new CompositePipe(GSZB.YSZK_DAILY_REPORT.value(), date, configFactory.getYszkrbCompositeConfigurator(computeMap));
			Organization org = companyManager.getBMDBOrganization();
			
			List<Company> companies = new ArrayList<Company>();
			companies.add(org.getCompanyByType(CompanyType.SBGS));
			companies.add(org.getCompanyByType(CompanyType.HBGS));
			companies.add(org.getCompanyByType(CompanyType.XBC));
			companies.add(org.getCompanyByType(CompanyType.TBGS));
			companies.add(org.getCompanyByType(CompanyType.LLGS));
			companies.add(org.getCompanyByType(CompanyType.XLC));
			companies.add(org.getCompanyByType(CompanyType.DLGS));
			
			
			pipe.addCompany(companies, configFactory.getYszkrbConfigurator())
				.addCompany(org.getCompanyByType(CompanyType.SBDCYJT), null)
				.addCompany(computeMap.get(org.getCompanyByType(CompanyType.XNYSYB)), configFactory.getYszkrbConfigurator())
				.addCompany(org.getCompanyByType(CompanyType.XNYSYB), null)
				.addCompany(computeMap.get(org.getCompanyByType(CompanyType.NYSYB)), configFactory.getYszkrbConfigurator())
				.addCompany(org.getCompanyByType(CompanyType.NYSYB), null)
				.addCompany(computeMap.get(org.getCompanyByType(CompanyType.GJGCGS_SYB)), configFactory.getYszkrbConfigurator())
				.addCompany(org.getCompanyByType(CompanyType.GJGCGS_SYB), null)			
				.addCompany(org.getCompanyByType(CompanyType.ZHGS), configFactory.getYszkrbConfigurator())
				.addCompany(org.getCompanyByType(CompanyType.GFGS), null);			
			return makeResult(pipe.getData());
		}else{			
			if (extAuthServ.hasAuthority(account, 28)){
				Company comp = extAuthServ.getAuthedCompanies(account, 28).get(0);
				BasicPipe pipe = new BasicPipe(GSZB.YSZK_DAILY_REPORT.value(), 
						comp,
						date,
						configFactory.getYszkrbConfigurator());
				List<String[]> ret = makeResult(pipe.getData());
				String[] dest = new String[ret.get(0).length + 1];
				dest[0] = comp.getName();
				for (int i = 0; i < ret.get(0).length; ++i){
					dest[i + 1] = ret.get(0)[i];
				}
				ret.set(0, dest);
				return ret;
			}
		}
		return null;
	}

	/**
	 * 获取应收账款日报表信息
	 * @param date
	 * @param account
	 * @return
	 */
	@Override
	public List<String[]> getYszkRbData(Date date, Account account) {
		if (extAuthServ.hasAuthority(account, 27)){
			CompositePipe pipe = new CompositePipe(GSZB.YSZK_DAILY_REPORT.value(), date, configFactory.getYszkrbCompositeConfigurator(computeMap));
			Organization org = companyManager.getBMDBOrganization();

			List<Company> companies = new ArrayList<Company>();
			companies.add(org.getCompanyByType(CompanyType.SBGS));
			companies.add(org.getCompanyByType(CompanyType.HBGS));
			companies.add(org.getCompanyByType(CompanyType.XBC));
			companies.add(org.getCompanyByType(CompanyType.TBGS));
			companies.add(org.getCompanyByType(CompanyType.LLGS));
			companies.add(org.getCompanyByType(CompanyType.XLC));
			companies.add(org.getCompanyByType(CompanyType.DLGS));


			pipe.addCompany(companies, configFactory.getYszkrbConfigurator())
					.addCompany(org.getCompanyByType(CompanyType.SBDCYJT), null)
					.addCompany(computeMap.get(org.getCompanyByType(CompanyType.XNYSYB)), configFactory.getYszkrbConfigurator())
					.addCompany(org.getCompanyByType(CompanyType.XNYSYB), null)
					.addCompany(computeMap.get(org.getCompanyByType(CompanyType.NYSYB)), configFactory.getYszkrbConfigurator())
					.addCompany(org.getCompanyByType(CompanyType.NYSYB), null)
					.addCompany(computeMap.get(org.getCompanyByType(CompanyType.GJGCGS_SYB)), configFactory.getYszkrbConfigurator())
					.addCompany(org.getCompanyByType(CompanyType.GJGCGS_SYB), null)
					.addCompany(org.getCompanyByType(CompanyType.ZHGS), configFactory.getYszkrbConfigurator())
					.addCompany(org.getCompanyByType(CompanyType.GFGS), null);
			List<String[]> s = makeResult(pipe.getData());
			for(int i = 0; i < s.size(); i++){
				if(i == 7){
					yszkrbAllDwFormatHeji(s,0,i);
				}else if(i == 10){
					yszkrbAllDwFormatHeji(s,8,i);
				}else if(i == 13){
					yszkrbAllDwFormatHeji(s,11,i);
				}else if(i == 16){
					yszkrbAllDwFormatHeji(s,14,i);
				}else if(i == 18){
					s.get(i)[0] = Double.valueOf(s.get(7)[0]==null?"0.0":s.get(7)[0])+Double.valueOf(s.get(10)[0]==null?"0.0":s.get(10)[0])+Double.valueOf(s.get(13)[0]==null?"0.0":s.get(13)[0])+Double.valueOf(s.get(16)[0]==null?"0.0":s.get(16)[0])+"";
					s.get(i)[1] = Double.valueOf(s.get(7)[1]==null?"0.0":s.get(7)[1])+Double.valueOf(s.get(10)[1]==null?"0.0":s.get(10)[1])+Double.valueOf(s.get(13)[1]==null?"0.0":s.get(13)[1])+Double.valueOf(s.get(16)[1]==null?"0.0":s.get(16)[1])+"";
					s.get(i)[2] = Double.valueOf(s.get(7)[2]==null?"0.0":s.get(7)[2])+Double.valueOf(s.get(10)[2]==null?"0.0":s.get(10)[2])+Double.valueOf(s.get(13)[2]==null?"0.0":s.get(13)[2])+Double.valueOf(s.get(16)[2]==null?"0.0":s.get(16)[2])+"";
					s.get(i)[3] = Double.valueOf(s.get(7)[3]==null?"0.0":s.get(7)[3])+Double.valueOf(s.get(10)[3]==null?"0.0":s.get(10)[3])+Double.valueOf(s.get(13)[3]==null?"0.0":s.get(13)[3])+Double.valueOf(s.get(16)[3]==null?"0.0":s.get(16)[3])+"";
					s.get(i)[4] = Double.valueOf(s.get(7)[4]==null?"0.0":s.get(7)[4])+Double.valueOf(s.get(10)[4]==null?"0.0":s.get(10)[4])+Double.valueOf(s.get(13)[4]==null?"0.0":s.get(13)[4])+Double.valueOf(s.get(16)[4]==null?"0.0":s.get(16)[4])+"";
					s.get(i)[5] = Double.valueOf(s.get(7)[5]==null?"0.0":s.get(7)[5])+Double.valueOf(s.get(10)[5]==null?"0.0":s.get(10)[5])+Double.valueOf(s.get(13)[5]==null?"0.0":s.get(13)[5])+Double.valueOf(s.get(16)[5]==null?"0.0":s.get(16)[5])+"";
					s.get(i)[6] = Double.valueOf(s.get(7)[6]==null?"0.0":s.get(7)[6])+Double.valueOf(s.get(10)[6]==null?"0.0":s.get(10)[6])+Double.valueOf(s.get(13)[6]==null?"0.0":s.get(13)[6])+Double.valueOf(s.get(16)[6]==null?"0.0":s.get(16)[6])+"";
					s.get(i)[7] = Double.valueOf(s.get(7)[7]==null?"0.0":s.get(7)[7])+Double.valueOf(s.get(10)[7]==null?"0.0":s.get(10)[7])+Double.valueOf(s.get(13)[7]==null?"0.0":s.get(13)[7])+Double.valueOf(s.get(16)[7]==null?"0.0":s.get(16)[7])+"";
					s.get(i)[8] = Double.valueOf(s.get(7)[8]==null?"0.0":s.get(7)[8])+Double.valueOf(s.get(10)[8]==null?"0.0":s.get(10)[8])+Double.valueOf(s.get(13)[8]==null?"0.0":s.get(13)[8])+Double.valueOf(s.get(16)[8]==null?"0.0":s.get(16)[8])+"";
					s.get(i)[10] = Double.valueOf(s.get(7)[10]==null?"0.0":s.get(7)[10])+Double.valueOf(s.get(10)[10]==null?"0.0":s.get(10)[10])+Double.valueOf(s.get(13)[10]==null?"0.0":s.get(13)[10])+Double.valueOf(s.get(16)[10]==null?"0.0":s.get(16)[10])+"";
					s.get(i)[11] = Double.valueOf(s.get(7)[11]==null?"0.0":s.get(7)[11])+Double.valueOf(s.get(10)[11]==null?"0.0":s.get(10)[11])+Double.valueOf(s.get(13)[11]==null?"0.0":s.get(13)[11])+Double.valueOf(s.get(16)[11]==null?"0.0":s.get(16)[11])+"";
					if(s.get(i)[0].equals("0.0")){s.get(i)[0]=null;}
					if(s.get(i)[1].equals("0.0")){s.get(i)[1]=null;}
					if(s.get(i)[2].equals("0.0")){s.get(i)[2]=null;}
					if(s.get(i)[3].equals("0.0")){s.get(i)[3]=null;}
					if(s.get(i)[4].equals("0.0")){s.get(i)[4]=null;}
					if(s.get(i)[5].equals("0.0")){s.get(i)[5]=null;}
					if(s.get(i)[6].equals("0.0")){s.get(i)[6]=null;}
					if(s.get(i)[7].equals("0.0")){s.get(i)[7]=null;}
					if(s.get(i)[8].equals("0.0")){s.get(i)[8]=null;}
					if(s.get(i)[10].equals("0.0")){s.get(i)[10]=null;}
					if(s.get(i)[11].equals("0.0")){s.get(i)[11]=null;}
					if(s.get(i)[1]==null || s.get(i)[8]==null){s.get(i)[9]=null;}else{s.get(i)[9]=Double.valueOf(s.get(i)[8])/Double.valueOf(s.get(i)[1])+"";}
					if(s.get(i)[0]==null || s.get(i)[11]==null){s.get(i)[12]=null;}else{s.get(i)[12]=Double.valueOf(s.get(i)[11])/Double.valueOf(s.get(i)[0])+"";}
				}
			}
			for(int i = 0; i < s.size(); i++){
				for(int j = 0; j < s.get(i).length; j++){
					if(j == 9 || j ==12){
						if(s.get(i)[j] != null){
							double d = Double.valueOf(s.get(i)[j]);
							double newd = d*100;
							java.text.DecimalFormat df =new java.text.DecimalFormat("0.0");
							s.get(i)[j] = df.format(newd)+"%";
						}
					}
				}
			}
			return s;
		}else{
			if (extAuthServ.hasAuthority(account, 28)){
				Company comp = extAuthServ.getAuthedCompanies(account, 28).get(0);
				BasicPipe basicPipe = new BasicPipe(GSZB.YSZK_DAILY_REPORT.value(),comp,date,configFactory.getYszkrbConfigurator());
				List<String[]> retList = makeResult(basicPipe.getData());
				String[] destArr = new String[retList.get(0).length + 1];
				destArr[0] = comp.getName();
				for (int i = 0; i < retList.get(0).length; ++i){
					destArr[i + 1] = retList.get(0)[i];
				}
				retList.set(0, destArr);
				for(int i = 0; i < retList.size(); i++){
					for(int j = 0; j < retList.get(i).length; j++){
						if(j == 10 || j ==13){
							if(retList.get(i)[j] != null){
								double d = Double.valueOf(retList.get(i)[j]);
								double newd = d*100;
								java.text.DecimalFormat df =new java.text.DecimalFormat("0.0");
								retList.get(i)[j] = df.format(newd)+"%";
							}
						}
					}
				}
				return retList;
			}
		}
		return null;
	}

	/**
	 * 将得到全部公司的日报信息进行格式化与合计重新处理
	 * @param s
	 * @param start
	 * @param end
	 */
	private void yszkrbAllDwFormatHeji(List<String[]> s, int start,int end){
		double yszkzbSum = 0.0,hkjhSum = 0.0,qbkxSum = 0.0,zqkxSum = 0.0,syysyeSum = 0.0,jrxzyszkSum = 0.0,byljxzysSum = 0.0
				,jrhkSum = 0.0,ljhkSum = 0.0,hkwclSum = 0.0,ljkjyshkSum = 0.0,jryszkyeSum = 0.0,yszkwclSum = 0.0;
		for(int j = start; j < end; j++){
			if(s.get(j)[0]==null){yszkzbSum = yszkzbSum + 0.0;}else{yszkzbSum = yszkzbSum + Double.valueOf(s.get(j)[0]);}
			if(s.get(j)[1]==null){hkjhSum = hkjhSum + 0.0;}else{hkjhSum = hkjhSum + Double.valueOf(s.get(j)[1]);}
			if(s.get(j)[2]==null){qbkxSum = qbkxSum + 0.0;}else{qbkxSum = qbkxSum + Double.valueOf(s.get(j)[2]);}
			if(s.get(j)[3]==null){zqkxSum = zqkxSum + 0.0;}else{zqkxSum = zqkxSum + Double.valueOf(s.get(j)[3]);}
			if(s.get(j)[4]==null){syysyeSum = syysyeSum + 0.0;}else{syysyeSum = syysyeSum + Double.valueOf(s.get(j)[4]);}
			if(s.get(j)[5]==null){jrxzyszkSum = jrxzyszkSum + 0.0;}else{jrxzyszkSum = jrxzyszkSum + Double.valueOf(s.get(j)[5]);}
			if(s.get(j)[6]==null){byljxzysSum = byljxzysSum + 0.0;}else{byljxzysSum = byljxzysSum + Double.valueOf(s.get(j)[6]);}
			if(s.get(j)[7]==null){jrhkSum = jrhkSum + 0.0;}else{jrhkSum = jrhkSum + Double.valueOf(s.get(j)[7]);}
			if(s.get(j)[8]==null){ljhkSum = ljhkSum + 0.0;}else{ljhkSum = ljhkSum + Double.valueOf(s.get(j)[8]);}
			if(s.get(j)[9]==null){hkwclSum = hkwclSum + 0.0;}else{hkwclSum = hkwclSum + Double.valueOf(s.get(j)[9]);}
			if(s.get(j)[10]==null){ljkjyshkSum = ljkjyshkSum + 0.0;}else{ljkjyshkSum = ljkjyshkSum + Double.valueOf(s.get(j)[10]);}
			if(s.get(j)[11]==null){jryszkyeSum = jryszkyeSum + 0.0;}else{jryszkyeSum = jryszkyeSum + Double.valueOf(s.get(j)[11]);}
			if(s.get(j)[12]==null){yszkwclSum = yszkwclSum + 0.0;}else{yszkwclSum = yszkwclSum + Double.valueOf(s.get(j)[12]);}
		}
		if(yszkzbSum==0.0){s.get(end)[0]=null;s.get(end)[12]=null;}else{s.get(end)[0] = yszkzbSum+"";if(jryszkyeSum/yszkzbSum==0.0){s.get(end)[12]=null;}else{s.get(end)[12]=jryszkyeSum/yszkzbSum+"";}}
		if(hkjhSum==0.0){s.get(end)[1]=null;s.get(end)[9]=null;}else{s.get(end)[1] = hkjhSum+"";if(ljhkSum/hkjhSum==0.0){s.get(end)[9]=null;}else{s.get(end)[9]=ljhkSum/hkjhSum+"";}}
		if(qbkxSum==0.0){s.get(end)[2]=null;}else{s.get(end)[2] = qbkxSum+"";}
		if(zqkxSum==0.0){s.get(end)[3]=null;}else{s.get(end)[3] = zqkxSum+"";}
		if(syysyeSum==0.0){s.get(end)[4]=null;}else{s.get(end)[4] = syysyeSum+"";}
		if(jrxzyszkSum==0.0){s.get(end)[5]=null;}else{s.get(end)[5] = jrxzyszkSum+"";}
		if(byljxzysSum==0.0){s.get(end)[6]=null;}else{s.get(end)[6] = byljxzysSum+"";}
		if(jrhkSum==0.0){s.get(end)[7]=null;}else{s.get(end)[7] = jrhkSum+"";}
		if(ljhkSum==0.0){s.get(end)[8]=null;}else{s.get(end)[8] = ljhkSum+"";}
		if(ljkjyshkSum==0.0){s.get(end)[10]=null;}else{s.get(end)[10] = ljkjyshkSum+"";}
		if(jryszkyeSum==0.0){s.get(end)[11]=null;}else{s.get(end)[11] = jryszkyeSum+"";}
		if(yszkzbSum==0.0){s.get(end)[0]=null;}else{s.get(end)[0] = yszkzbSum+"";}
	}
}
