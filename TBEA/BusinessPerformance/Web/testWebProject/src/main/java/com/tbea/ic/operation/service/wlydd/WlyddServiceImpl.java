package com.tbea.ic.operation.service.wlydd;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;
import com.tbea.ic.operation.model.dao.dl.kglydd.DlKglyddDao;
import com.tbea.ic.operation.model.dao.dl.mlspcs.DlMlspcsDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.byqcplx.ByqcplxDao;
import com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.byqcplx.ByqcplxDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.xlcplx.XlcplxDao;
import com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.xlcplx.XlcplxDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.byqkglydd.ByqkglyddDao;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.byqkglydd.ByqkglyddDaoImpl;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.xlkglydd.XlkglyddDao;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.xlkglydd.XlkglyddDaoImpl;
import com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs.WlyddmlspcsDao;
import com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs.WlyddmlspcsDaoImpl;
import com.tbea.ic.operation.model.entity.identifier.common.CpmcEntity;
import com.tbea.ic.operation.model.entity.identifier.sbdddcbjpcqk.XlcplxEntity;
import com.tbea.ic.operation.model.entity.sbdddcbjpcqk.XlkglyddEntity;
import com.tbea.ic.operation.model.entity.wlydd.wlyddmslspcs.WlyddmlspcsEntity;
import com.tbea.ic.operation.service.wlydd.wlyddmlspcs.WlyddmlspcsServiceImpl;


@Service(WlyddServiceImpl.NAME)
@Transactional("transactionManager")
public class WlyddServiceImpl implements WlyddService {
	@Resource(name = WlyddmlspcsDaoImpl.NAME)
	WlyddmlspcsDao wlyddmlspcsDao;
	
	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	DlKglyddDao dlKglyddDao;
	
	@Resource(name=XlcplxDaoImpl.NAME)
	XlcplxDao xlcplxDao;

	@Resource(name=ByqcplxDaoImpl.NAME)
	ByqcplxDao byqcplxDao;

	@Resource(name = XlkglyddDaoImpl.NAME)
	XlkglyddDao xlkglyddDao;

	@Resource(name = ByqkglyddDaoImpl.NAME)
	ByqkglyddDao byqkglyddDao;

	@Autowired
	DlMlspcsDao dlmlspcsDao;
	
	public final static String NAME = "WlyddServiceImpl";

	public List<String> getXlCplb() {
		List<XlcplxEntity> entities = xlcplxDao.getAll();
		List<String> list = new ArrayList<String>();
		for (XlcplxEntity entity : entities){
			list.add("" + entity.getName());
		}
		return list;
	}
	
	private void importXlKglydd(Date d, List<Object[]> result, Company comp) {
		List<String> xlCplxs = getXlCplb();
		EasyCalendar ec = new EasyCalendar(d);
		for (Object[] row : result){
			LoggerFactory.getLogger("WEBSERVICE").info(JSONArray.fromObject(row).toString());
			if (xlCplxs.contains(row[1])){
				WlyddType type = WlyddType.SCLB;
				if ("生产单元".equals(row[0])){
					type = WlyddType.SCDY;
				}else if ("生产类别".equals(row[0])){
					type = WlyddType.SCLB;
				}else{
					LoggerFactory.getLogger("WEBSERVICE").info("importXlKglydd 无法找到统计类型 : " + row[0]);
					continue;
				}
				
				XlkglyddEntity entity = xlkglyddDao.getKglydd(d, type, (String)row[1], comp);
				if (null == entity){
					entity = new XlkglyddEntity();
					entity.setNf(ec.getYear());
					entity.setYf(ec.getMonth());
					entity.setDwid(comp.getId());
					entity.setType(type.ordinal());
					entity.setSclx((String)row[1]);
					entity.setZt(ZBStatus.SUBMITTED.ordinal());
				}
				int index = 2;
				entity.setYccnl((Double)row[index++]);
				entity.setWlyddzl((Double)row[index++]);
				entity.setDnwlyddzl((Double)row[index++]);
				entity.setNj1yddlypc((Double)row[index++]);
				entity.setNj1yddlwpc((Double)row[index++]);
				entity.setNj2yddlypc((Double)row[index++]);
				entity.setNj2yddlwpc((Double)row[index++]);
				entity.setNj3yddlypc((Double)row[index++]);
				entity.setNj3yddlwpc((Double)row[index++]);
				entity.setNj3yyhlydd((Double)row[index++]);
				entity.setCnjyhkglyddpcz((Double)row[index++]);
				entity.setJhqdd((Double)row[index++]);
				entity.setWx((Double)row[index++]);
				xlkglyddDao.merge(entity);
			}
			else{
				LoggerFactory.getLogger("WEBSERVICE").info("importXlKglydd 无法找到统计项 : " + row[1]);
			}
		}
		
	}
	
	@Override
	public void importDlKglydd(Date d) {
		LoggerFactory.getLogger("WEBSERVICE").info("DL 可供履约订单");
		Company company = companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS);
		List<Object[]> rows = dlKglyddDao.getKglydd(d);
		importXlKglydd(d, rows, company);
	}


	@Override
	public void importDlMlspcs(Date d) {
		Company company = companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS);
		List<Object[]> rows = dlmlspcsDao.getZxyw(d);
		LoggerFactory.getLogger("WEBSERVICE").info("DL 盈利分析转型业务");
		importMlspcs(d, rows, WlyddType.YLFX_WLYMLSP_XL_ZXYW, company);
		rows = dlmlspcsDao.getZzy(d);
		LoggerFactory.getLogger("WEBSERVICE").info("DL 盈利分析制造业");
		importMlspcs(d, rows, WlyddType.YLFX_WLYMLSP_XL_ZZY, company);
		
	}


	private void importMlspcs(Date d, List<Object[]> rows, WlyddType type,  Company comp) {
		List<CpmcEntity> cpmcs = getCpList(WlyddmlspcsServiceImpl.getCpIdList(type));
		EasyCalendar ec = new EasyCalendar(d);
		for (Object[] row : rows){
			LoggerFactory.getLogger("WEBSERVICE").info(JSONArray.fromObject(row).toString());
			CpmcEntity mc = findCp(cpmcs, (String)row[0]);
			if (mc == null){
				LoggerFactory.getLogger("WEBSERVICE").info("importMlspcsZxyw 无法找到统计项 : " + row[0]);
			}else{
				WlyddmlspcsEntity entity = wlyddmlspcsDao.getByDate(d, comp, type, mc.getId());
				if (null == entity){
					entity = new WlyddmlspcsEntity();
					entity.setNf(ec.getYear());
					entity.setYf(ec.getMonth());
					entity.setDwxx(dwxxDao.getById(comp.getId()));
					entity.setTjfs(type.value());
					entity.setCpmc(mc);
					entity.setZt(ZBStatus.SUBMITTED.ordinal());
				}
				entity.setCb((Double)row[1]);
				entity.setSr((Double)row[2]);
				wlyddmlspcsDao.merge(entity);
			}
		}
	}

	private CpmcEntity findCp(List<CpmcEntity> cps, String name){
		for (CpmcEntity cp : cps){
			if (cp.getName().trim().equals(name)){
				return cp;
			}
		}
		return null;
	}

	private List<CpmcEntity> getCpList(List<Integer> cps) {
		List<CpmcEntity> ret  = new ArrayList<CpmcEntity>();
		for (Integer cp : cps){
			ret.add(cpmcDao.getById(cp));
		}
		return ret;
	}
	
}
