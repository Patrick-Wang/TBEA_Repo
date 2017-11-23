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
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;
import com.tbea.ic.operation.controller.servlet.sbdscqyqk.SbdscqyqkType;
import com.tbea.ic.operation.controller.servlet.wgcpqk.WgcpqkType;
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.service.chgb.ChgbService;
import com.tbea.ic.operation.service.chgb.ChgbServiceImpl;
import com.tbea.ic.operation.service.cwgbjyxxjl.jyxxjl.JyxxjlService;
import com.tbea.ic.operation.service.sbdczclwcqk.clylwcqk.ClylwcqkService;
import com.tbea.ic.operation.service.sbdczclwcqk.clylwcqk.ClylwcqkServiceImpl;
import com.tbea.ic.operation.service.sbdczclwcqk.cpclwcqk.CpclwcqkService;
import com.tbea.ic.operation.service.sbdczclwcqk.cpclwcqk.CpclwcqkServiceImpl;
import com.tbea.ic.operation.service.sbdczclwcqk.cpczwcqk.CpczwcqkService;
import com.tbea.ic.operation.service.sbdczclwcqk.cpczwcqk.CpczwcqkServiceImpl;
import com.tbea.ic.operation.service.sbdddcbjpcqk.SbdddcbjpcqkService;
import com.tbea.ic.operation.service.sbdddcbjpcqk.SbdddcbjpcqkServiceImpl;
import com.tbea.ic.operation.service.sbdscqyqk.xfcpqy.XfcpqyService;
import com.tbea.ic.operation.service.sbdscqyqk.xfscqy.XfscqyService;
import com.tbea.ic.operation.service.util.pipe.core.BasicPipe;
import com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs.WgcpylnlspcsService;
import com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs.WgcpylnlspcsServiceImpl;
import com.tbea.ic.operation.service.wgcpqk.yclbfqk.YclbfqkService;
import com.tbea.ic.operation.service.wgcpqk.yclbfqk.YclbfqkServiceImpl;
import com.tbea.ic.operation.service.wlydd.wlyddmlspcs.WlyddmlspcsService;
import com.tbea.ic.operation.service.wlydd.wlyddmlspcs.WlyddmlspcsServiceImpl;
import com.tbea.ic.operation.service.ydzb.pipe.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.ConfiguratorFactory;
import com.tbea.ic.operation.service.yszkgb.YszkgbService;

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
	JyxxjlService jyxxjlService;
	
	
	@Autowired
	YszkgbService yszkgbService;
	

	@Resource(name=ChgbServiceImpl.NAME)
	ChgbService chgbService;
	
	@Autowired
	XfscqyService xfscqyService;
	

	@Autowired
	XfcpqyService xfcpqyService;
	
	@Resource(name=CpczwcqkServiceImpl.NAME)
	CpczwcqkService cpczwcqkService;
	
	@Resource(name=ClylwcqkServiceImpl.NAME)
	ClylwcqkService clylwcqkService;
	
	@Resource(name=CpclwcqkServiceImpl.NAME)
	CpclwcqkService cpclwcqkService;
	
	@Resource(name = WgcpylnlspcsServiceImpl.NAME)
	WgcpylnlspcsService wgcpylnlspcsService;
	
	@Resource(name = WlyddmlspcsServiceImpl.NAME)
	WlyddmlspcsService wlyddmlspcsService;
	
	@Resource(name=SbdddcbjpcqkServiceImpl.NAME)
	SbdddcbjpcqkService sbdddcbjpcqkService;
	
	@Resource(name=YclbfqkServiceImpl.NAME)
	YclbfqkService yclbfqkService;
	
	@Autowired
	public void init() {
		accFac = new AccumulatorFactory(sjzbDao, yj20zbDao, yj28zbDao, ydzbztDao, ydjhzbDao, ndjhzbDao);
		configFac = new ConfiguratorFactory(sbdNdjhzbDao, accFac, companyManager);
	}
	
	@Override
	public List<Double[]> getJydwztzb(Integer compId, Date date) {
		return getStandardZb(compId, date, gsztzbs);
	}
	
	@Override
	public List<Double[]> getLrsrzb(Integer compId, Date date) {
		return getStandardZb(compId, date, lrsrzb);
	}
	
	@Override
	public List<Double[]> getStandardZb(Integer compId, Date date, List<Integer> zbs) {
		BasicPipe pipe = new BasicPipe(zbs, companyManager.getBMDBOrganization().getCompanyById(compId), date,
				configFac.getStandardConfigurator());
		return pipe.getData();
	}
	
	@Override 
	public List<List<String>> getJyxxjl(Integer compId, Date date){
		return jyxxjlService.getJyxxjlYD(date,  companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<String> getJyxxjlYdsj(Integer compId, Date date){
		return jyxxjlService.getJyxxjlYDSJ(date,  companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getYszkzmb(Integer compId, Date date){
		return yszkgbService.getZmb(date,  companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getYszkzlbhDy(Integer compId, Date date){
		return yszkgbService.getYszkzlbhDy(date,  companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getYszkYqysDy(Integer compId, Date date){
		return yszkgbService.getYqyszcsysDy(date,  companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getYszkKxxzDy(Integer compId, Date date){
		return yszkgbService.getYszkkxxzDy(date,  companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getYszkYjtzDy(Integer compId, Date date){
		return yszkgbService.getYszkyjtztjqsDy(date,  companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getChzmb(Integer compId, Date date){
		return chgbService.getChzmb(date,  companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getChzljgDy(Integer compId, Date date){
		return chgbService.getChzlbhqkDy(date,  companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getChxzDy(Integer compId, Date date){
		return chgbService.getChxzqkDy(date,  companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getChjykc(Integer compId, Date date){
		List<List<String>> ret = chgbService.getChjykcb(date,  companyManager.getBMDBOrganization().getCompanyById(compId));
		for (List row : ret) {
			row.remove(0);
		}
		return ret;
	}
	
	@Override 
	public List<List<String>> getXfscqy(Integer compId, Date date){
		return xfscqyService.getXfscqy(date, companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getXlXfcpqy(Integer compId, Date date){
		return xfcpqyService.getXfcpqy(date, companyManager.getBMDBOrganization().getCompanyById(compId), SbdscqyqkType.YLFX_WGCPYLNL_XL);
	}
	
	@Override 
	public List<List<String>> getByqXfcpqy(Integer compId, Date date){
		return xfcpqyService.getXfcpqy(date, companyManager.getBMDBOrganization().getCompanyById(compId), SbdscqyqkType.YLFX_WGCPYLNL_BYQ);
	}
	
	@Override 
	public List<List<String>> getXlCzwcqk(Integer compId, Date date){
		return cpczwcqkService.getCpczwcqk(date, companyManager.getBMDBOrganization().getCompanyById(compId), SbdczclwcqkType.SBDCZCLWCQK_CZ_XL);
	}
	
	@Override 
	public List<List<String>> getByqCzwcqk(Integer compId, Date date){
		return cpczwcqkService.getCpczwcqk(date, companyManager.getBMDBOrganization().getCompanyById(compId), SbdczclwcqkType.SBDCZCLWCQK_CZ_BYQ);
	}
	
	
	@Override 
	public List<List<String>> getByqClwcqk(Integer compId, Date date){
		return cpclwcqkService.getCpclwcqk(date, companyManager.getBMDBOrganization().getCompanyById(compId), SbdczclwcqkType.SBDCZCLWCQK_CL_BYQ);
	}
	
	@Override 
	public List<List<String>> getXlTlyl(Integer compId, Date date){
		return clylwcqkService.getClylwcqk(date, companyManager.getBMDBOrganization().getCompanyById(compId), SbdczclwcqkType.SBDCZCLWCQK_CLYLWCQK_XL);
	}
	
	@Override 
	public List<List<String>> getXlWgcpylnlMll(Integer compId, Date date){
		return wgcpylnlspcsService.getWgcpylnlspcs(date, companyManager.getBMDBOrganization().getCompanyById(compId), WgcpqkType.YLFX_WGCPYLNL_XL_MLL);
	}
	
	@Override 
	public List<List<String>> getByqWgcpylnlMll(Integer compId, Date date){
		return wgcpylnlspcsService.getWgcpylnlspcs(date, companyManager.getBMDBOrganization().getCompanyById(compId), WgcpqkType.YLFX_WGCPYLNL_BYQ_MLL);
	}
	
	@Override 
	public List<List<String>> getXlWlyddmlspcs(Integer compId, Date date){
		return wlyddmlspcsService.getWlyddmlspcs(date, companyManager.getBMDBOrganization().getCompanyById(compId), WlyddType.YLFX_WLYMLSP_XL_ZZY);
	}
	
	@Override 
	public List<List<String>> getByqWlyddmlspcs(Integer compId, Date date){
		return wlyddmlspcsService.getWlyddmlspcs(date, companyManager.getBMDBOrganization().getCompanyById(compId), WlyddType.YLFX_WLYMLSP_BYQ_ZZY);
	}
	
	@Override 
	public List<List<String>> getXlKglyddcplbkj(Integer compId, Date date){
		return sbdddcbjpcqkService.getXlkglydd(date, WlyddType.SCLB, companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getXlKglyddscdykj(Integer compId, Date date){
		return sbdddcbjpcqkService.getXlkglydd(date, WlyddType.SCDY, companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getByqKglyddcplbkj(Integer compId, Date date){
		return sbdddcbjpcqkService.getByqkglydd(date, WlyddType.SCLB, companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getByqKglyddscdykj(Integer compId, Date date){
		return sbdddcbjpcqkService.getByqkglydd(date, WlyddType.SCDY, companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
	@Override 
	public List<List<String>> getYclbfqk(Integer compId, Date date){
		return yclbfqkService.getYclbfqk(date, companyManager.getBMDBOrganization().getCompanyById(compId));
	}
	
}
