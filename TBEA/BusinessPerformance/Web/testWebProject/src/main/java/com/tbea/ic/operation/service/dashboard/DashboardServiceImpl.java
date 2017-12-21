package com.tbea.ic.operation.service.dashboard;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.service.util.pipe.core.BasicPipe;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;
import com.xml.frame.report.util.EasyList;

@Service
@Transactional("transactionManager")
public class DashboardServiceImpl implements DashboardService {

	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
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
	
	AccumulatorFactory accFac;
	
	@Autowired
	public void init() {
		accFac = new AccumulatorFactory(sjzbDao, yj20zbDao, yj28zbDao, ydzbztDao, ydjhzbDao, ndjhzbDao);

	}
	
	CompanyType[] sbdcomps = new CompanyType[]{
			CompanyType.SBGS,
			CompanyType.HBGS,
			CompanyType.XBC,
			CompanyType.LLGS,
			CompanyType.XLC,
			CompanyType.DLGS
	};

//	private static List<Integer> qyztzb = new ArrayList<Integer>();
//	static {
//		qyztzb.add(GSZB.HTQYE48.value());
//		qyztzb.add(GSZB.HTQY_ZZYQY290.value()); //三期加入指标
//		qyztzb.add(GSZB.JCFWYW_HGCHJCXS_QY299.value()); //三期加入指标
//		qyztzb.add(GSZB.HTQY_QT304.value()); //三期加入指标
//	}
	
	@Override
	public List<Double> getScqyLjzb(Date date) {
		List zbs = new EasyList<Integer>(new Integer[]{
				GSZB.HTQYE48.value(),
				GSZB.HTQY_ZZYQY290.value(),
				GSZB.JCFWYW_HGCHJCXS_QY299.value(),
				GSZB.HTQY_QT304.value()}).toList();
		
		BasicPipe pipe = new BasicPipe(zbs, BMDepartmentDB.getMainlyJydw(companyManager), date,
				new NdljScqyConfigurator(sbdNdjhzbDao, accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc()));
		 List<Double> ret = new ArrayList<Double>();
		 List<Double[]> retTmp = pipe.getData();
		 for (int i = 0; i < retTmp.size(); ++i){
			 ret.add(retTmp.get(i)[1]);
		 }
		 
		return ret;
	}

	@Override
	public List<Double> getScqyZtydzb(Integer nf) {
		List zbs = new EasyList<Integer>(new Integer[]{
					GSZB.HTQYE48.value()}).toList();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, nf);
		cal.set(Calendar.MONTH, 0);
		List<Double> ret = new ArrayList<Double>();
		for (int i = 0; i < 12; ++i){
			BasicPipe pipe = new BasicPipe(zbs, BMDepartmentDB.getMainlyJydw(companyManager), new Date(cal.getTimeInMillis()),
					new DyScqyConfigurator(sbdNdjhzbDao, accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc()));
			ret.add(pipe.getData().get(0)[0]);
			cal.add(Calendar.MONTH, 1);
		}
		return ret;
	}
	

	private String getNumber0(Double val){
		if (val == null){
			return "--";
		}
		return String.format("%.0f", val);		
	}
	
	private Double sumScqy(List zbs, Company comp, Date d){
		BasicPipe pipe = new BasicPipe(zbs, comp, d,
				new NdljScqyConfigurator(sbdNdjhzbDao, accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc()));

		List<Double[]> data = pipe.getData();
		Double sum = null;
		for (int i = 0; i < data.size(); ++i){
			if (null == sum){
				sum = data.get(i)[1];
			}else{
				sum += data.get(i)[1];
			}
		}
		return sum;
	}
	
	@Override
	public List<String[]> getSbdgnscqye(Date date) {
		List zbs = new EasyList<Integer>(new Integer[]{
				GSZB.QZ_GNQY291.value(),
				GSZB.QZ_JCFWYWGNQY300.value(),
				GSZB.QZ_GNQY305.value()}).toList();
		
		List<Company> sbds = BMDepartmentDB.valueOf(companyManager, sbdcomps);
		List<String[]> result = new ArrayList<String[]>();
		for (Company comp : sbds){
			result.add(new String[]{comp.getName(), getNumber0(sumScqy(zbs, comp, date))});
		}
		result.sort(new Comparator<String[]>(){

			@Override
			public int compare(String[] arg0, String[] arg1) {
				int ret = 0;
				if ("--".equals(arg0[1])){
					ret = -1;
				}else if ("--".equals(arg1[1])){
					ret = 1;
				}else{
					Double v1 = Double.valueOf(arg0[1]);
					Double v2 = Double.valueOf(arg1[1]);
					if (v1 > v2){
						ret = 1;
					}			
					else{
						ret = -1;
					}
				}
				
				return -1 * ret;
			}
			
		});
		return result;
	}

	@Override
	public List<String[]> getSbdgjscqye(Date date) {	
		List zbs = new EasyList<Integer>(new Integer[]{
				GSZB.QZ_GJQY295.value(),
				GSZB.QZ_JCFWYWGJQY302.value(),
				GSZB.QZ_GJQY306.value()}).toList();
		
		List<Company> sbds = BMDepartmentDB.valueOf(companyManager, sbdcomps);
		List<String[]> result = new ArrayList<String[]>();
		for (Company comp : sbds){
			result.add(new String[]{comp.getName(), getNumber0(sumScqy(zbs, comp, date))});
		}
		result.sort(new Comparator<String[]>(){

			@Override
			public int compare(String[] arg0, String[] arg1) {
				int ret = 0;
				if ("--".equals(arg0[1])){
					ret = -1;
				}else if ("--".equals(arg1[1])){
					ret = 1;
				}else{
					Double v1 = Double.valueOf(arg0[1]);
					Double v2 = Double.valueOf(arg1[1]);
					if (v1 > v2){
						ret = 1;
					}			
					else{
						ret = -1;
					}
				}
				
				return -1 * ret;
			}
			
		});
		return result;
	}

	@Override
	public Double getSbdztqye(Date current) {
		
		List zbs = new EasyList<Integer>(new Integer[]{
				GSZB.HTQYE48.value()}).toList();
		
		List<Company> sbds = BMDepartmentDB.valueOf(companyManager, sbdcomps);
		BasicPipe pipe = new BasicPipe(zbs, sbds, current,
				new NdljScqyConfigurator(sbdNdjhzbDao, accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc()));

		List<Double[]> data = pipe.getData();
		return data.get(0)[1];
	}

	@Override
	public List<Double[]> getDashboardGsztzb(Date date) {
		List zbs = new EasyList<Integer>(new Integer[]{
				GSZB.LRZE1.value(),
				GSZB.XSSR6.value(),
				GSZB.YSZK32.value(),
				GSZB.CH35.value(),
				GSZB.HTQYE48.value(),
				GSZB.HTQY_ZZYQY290.value(),
				GSZB.JCFWYW_HGCHJCXS_QY299.value(),
				GSZB.HTQY_QT304.value()}).toList();
		BasicPipe pipe = new BasicPipe(zbs, BMDepartmentDB.getMainlyJydw(companyManager), date,
				new NdGsztConfigurator(sbdNdjhzbDao, accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc()));
		return pipe.getData();
	}
}
