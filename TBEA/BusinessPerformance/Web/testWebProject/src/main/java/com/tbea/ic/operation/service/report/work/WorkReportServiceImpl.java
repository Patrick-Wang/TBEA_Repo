package com.tbea.ic.operation.service.report.work;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.service.util.pipe.core.BasicPipe;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.ConfiguratorFactory;

@Service
@Transactional("transactionManager")
public class WorkReportServiceImpl implements WorkReportService {
	
	private static List<Integer> gsztzbs = new ArrayList<Integer>();
	static {
		gsztzbs.add(GSZB.LRZE1.value());
		gsztzbs.add(GSZB.XSSR6.value());
		gsztzbs.add(GSZB.JYXJXJL29.value());
		gsztzbs.add(GSZB.YSZK32.value());
		gsztzbs.add(GSZB.CH35.value());
		gsztzbs.add(GSZB.HTQYE48.value());
		
		gsztzbs.add(GSZB.HTQY_ZZYQY290.value());
		gsztzbs.add(GSZB.QZ_GNQY291.value());
		gsztzbs.add(GSZB.QZ_GJQY295.value());
		
		gsztzbs.add(GSZB.JCFWYW_HGCHJCXS_QY299.value()); 
		gsztzbs.add(GSZB.QZ_JCFWYWGNQY300.value()); 
		gsztzbs.add(GSZB.QZ_JCFWYWGJQY302.value()); 
		
		gsztzbs.add(GSZB.ZJHL57.value());
		gsztzbs.add(GSZB.BHSCZ60.value());
		gsztzbs.add(GSZB.BYQ_WKVA_68.value());
		gsztzbs.add(GSZB.RS61.value());
		gsztzbs.add(GSZB.SXFY64.value());
		gsztzbs.add(GSZB.SXFYL_65.value());
	}
	
	private static List<Integer> lrsrzb = new ArrayList<Integer>();
	static {
		lrsrzb.add(1);
		lrsrzb.add(195);
		lrsrzb.add(196);
		lrsrzb.add(197);
		lrsrzb.add(198);
		lrsrzb.add(199);
		
		lrsrzb.add(6);
		lrsrzb.add(7);
		lrsrzb.add(12);
		lrsrzb.add(16); 
		lrsrzb.add(253);
	}
		
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
	
	ConfiguratorFactory configFac;
	
	AccumulatorFactory accFac;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	public void init() {
		accFac = new AccumulatorFactory(sjzbDao, yj20zbDao, yj28zbDao, ydzbztDao, ydjhzbDao, ndjhzbDao);
		configFac = new ConfiguratorFactory(sbdNdjhzbDao, accFac, companyManager);
	}
	
	@Override
	public List<Double[]> getJydwztzb(Integer compId, Date date) {
		BasicPipe pipe = new BasicPipe(gsztzbs, companyManager.getBMDBOrganization().getCompanyById(compId), date,
				configFac.getStandardConfigurator());
		return pipe.getData();
	}
	
	@Override
	public List<Double[]> getLrsrzb(Integer compId, Date date) {
		BasicPipe pipe = new BasicPipe(lrsrzb, companyManager.getBMDBOrganization().getCompanyById(compId), date,
				configFac.getStandardConfigurator());
		return pipe.getData();
	}
}
