package com.tbea.ic.operation.service.sbdczclwcqk;

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
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;
import com.tbea.ic.operation.model.dao.dl.czcl.DlCzClDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.common.clmc.ClmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.clmc.ClmcDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.ll.czcl.LlCzClDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.clylwcqk.ClylwcqkDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.clylwcqk.ClylwcqkDaoImpl;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpclwcqk.CpclwcqkDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpclwcqk.CpclwcqkDaoImpl;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpczwcqk.CpczwcqkDao;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpczwcqk.CpczwcqkDaoImpl;
import com.tbea.ic.operation.model.dao.xl.czcl.XlCzClDao;
import com.tbea.ic.operation.model.entity.identifier.common.ClmcEntity;
import com.tbea.ic.operation.model.entity.identifier.common.CpmcEntity;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.ClylwcqkEntity;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.CpclwcqkEntity;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.CpczwcqkEntity;
import com.tbea.ic.operation.service.report.HBWebService;
import com.tbea.ic.operation.service.sbdczclwcqk.clylwcqk.ClylwcqkServiceImpl;
import com.tbea.ic.operation.service.sbdczclwcqk.cpclwcqk.CpclwcqkServiceImpl;
import com.tbea.ic.operation.service.sbdczclwcqk.cpczwcqk.CpczwcqkServiceImpl;

@Service(SbdczclwcqkServiceImpl.NAME)
@Transactional("transactionManager")
public class SbdczclwcqkServiceImpl implements SbdczclwcqkService {
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Resource(name=CpclwcqkDaoImpl.NAME)
	CpclwcqkDao cpclwcqkDao;

	@Resource(name=CpczwcqkDaoImpl.NAME)
	CpczwcqkDao cpczwcqkDao;
	
	@Resource(name=ClylwcqkDaoImpl.NAME)
	ClylwcqkDao clylwcqkDao;
	
	@Resource(name=ClmcDaoImpl.NAME)
	ClmcDao clmcDao;
	
	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	@Autowired
	DlCzClDao dlCzclDao;
	
	@Autowired
	XlCzClDao xlCzclDao;
	
	@Autowired
	LlCzClDao llCzclDao;
	
	public final static String NAME = "SbdczclwcqkServiceImpl";

	

	
	@Override
	public void importHBCzCl(java.sql.Date d) {
		LoggerFactory.getLogger("WEBSERVICE").info("importHBCzCl");
		HBWebService hbws = new HBWebService();
		List<String> cols = new ArrayList<String>();
		cols.add("statistical_type");
		cols.add("statistical_item");
		cols.add("value");
		List<Object[]> result = hbws.getHBClwcqk(cols, d);
		Company comp = companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS);
		importByqCzCl(d, result, comp);
	}
	
	@Override
	public void importDlCzCl(java.sql.Date d) {
		LoggerFactory.getLogger("WEBSERVICE").info("importDlCzCl");
		List<Object[]> result = dlCzclDao.getCzCl(d);
		Company comp = companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS);
		importXlCzCl(d, result, comp);
	}
	
	
	private void importXlCzCl(Date d, List<Object[]> result, Company comp) {
		EasyCalendar ec = new EasyCalendar(d);
		LoggerFactory.getLogger("WEBSERVICE").info("importXlCzCl {} {}", comp.getName(), Util.formatToMonth(d));
		List<CpczwcqkEntity> entitiesCz = cpczwcqkDao.getByDate(d, comp, SbdczclwcqkType.SBDCZCLWCQK_CZ_XL);
		ZBStatus czStatus = ZBStatus.SUBMITTED;
		if (null != entitiesCz && !entitiesCz.isEmpty()){
			czStatus = ZBStatus.valueOf(entitiesCz.get(0).getZt());
		}

		List<Integer> cpIdList = CpczwcqkServiceImpl.getCpIdList(SbdczclwcqkType.SBDCZCLWCQK_CZ_XL);
		List<CpmcEntity> czCps = getCpList(cpIdList);
		cpIdList = ClylwcqkServiceImpl.getCpIdList(SbdczclwcqkType.SBDCZCLWCQK_CL_XL);
		List<ClmcEntity> clCps = getClList(cpIdList);
		for (Object[] r: result){
			LoggerFactory.getLogger("WEBSERVICE").debug(JSONArray.fromObject(r).toString());
			if ("产值".equals(r[0])){
				CpmcEntity mc = findCp(czCps, (String)r[1]);
				if (null == mc){
					LoggerFactory.getLogger("WEBSERVICE").info("importXlCzCl 无法找到产值 : " + r[1]);
				}else{
					CpczwcqkEntity entityCz = cpczwcqkDao.getByDate(d, comp, SbdczclwcqkType.SBDCZCLWCQK_CZ_XL, mc.getId());
					
					if (null == entityCz){
						entityCz = new CpczwcqkEntity();

						entityCz.setNf(ec.getYear());
						entityCz.setYf(ec.getMonth());
						entityCz.setDwxx(dwxxDao.getById(comp.getId()));
						entityCz.setCpmc(mc);
						entityCz.setTjfs(SbdczclwcqkType.SBDCZCLWCQK_CZ_XL.value());
					}

					entityCz.setZt(czStatus.ordinal());
					entityCz.setCz(Util.toDoubleNull(
							r[2] != null ? r[2].toString().replaceAll(",", "") : null));
					
					cpczwcqkDao.merge(entityCz);	
				}
			}else{
				ClmcEntity mc = findCl(clCps, (String)r[1]);
				if (null == mc){
					LoggerFactory.getLogger("WEBSERVICE").info("importXlCzCl 无法找到产量 : " + r[1]);
				}else{
					ClylwcqkEntity entityCl = clylwcqkDao.getByDate(d, comp, SbdczclwcqkType.SBDCZCLWCQK_CL_XL, mc.getId());
					
					if (null == entityCl){
						entityCl = new ClylwcqkEntity();

						entityCl.setNf(ec.getYear());
						entityCl.setYf(ec.getMonth());
						entityCl.setDwxx(dwxxDao.getById(comp.getId()));
						entityCl.setClmc(mc);
						entityCl.setTjfs(SbdczclwcqkType.SBDCZCLWCQK_CL_XL.value());
					}

					entityCl.setZt(czStatus.ordinal());
					entityCl.setCl(Util.toDoubleNull(
							r[2] != null ? r[2].toString().replaceAll(",", "") : null));
					
					clylwcqkDao.merge(entityCl);	
				}
			}
		}
	}

	private List<ClmcEntity> getClList(List<Integer> cls) {
		List<ClmcEntity> ret  = new ArrayList<ClmcEntity>();
		for (Integer cp : cls){
			ret.add(clmcDao.getById(cp));
		}
		return ret;
	}

	private ClmcEntity findCl(List<ClmcEntity> cls, String name) {
		for (ClmcEntity cp : cls){
			if (cp.getName().trim().equals(name)){
				return cp;
			}
		}
		return null;
	}

	private void importByqCzCl(java.sql.Date d, List<Object[]> result, Company comp){
		
		LoggerFactory.getLogger("WEBSERVICE").info("importByqCzCl {} {}", comp.getName(), Util.formatToMonth(d));
		
		EasyCalendar ec = new EasyCalendar(d);
		List<CpclwcqkEntity> entitiesCl = cpclwcqkDao.getByDate(d, comp, SbdczclwcqkType.SBDCZCLWCQK_CL_BYQ);
		ZBStatus clStatus = ZBStatus.SUBMITTED;
		if (null != entitiesCl && !entitiesCl.isEmpty()){
			clStatus = ZBStatus.valueOf(entitiesCl.get(0).getZt());
		}
		
		List<CpczwcqkEntity> entitiesCz = cpczwcqkDao.getByDate(d, comp, SbdczclwcqkType.SBDCZCLWCQK_CZ_BYQ);
		ZBStatus czStatus = ZBStatus.SUBMITTED;
		if (null != entitiesCz && !entitiesCz.isEmpty()){
			czStatus = ZBStatus.valueOf(entitiesCz.get(0).getZt());
		}

		List<Integer> cpIdList = CpclwcqkServiceImpl.getCpIdList(SbdczclwcqkType.SBDCZCLWCQK_CL_BYQ);
		List<CpmcEntity> clCps = getCpList(cpIdList);
		cpIdList = CpczwcqkServiceImpl.getCpIdList(SbdczclwcqkType.SBDCZCLWCQK_CZ_BYQ);
		List<CpmcEntity> czCps = getCpList(cpIdList);
		for (Object[] r: result){
			LoggerFactory.getLogger("WEBSERVICE").debug(JSONArray.fromObject(r).toString());
			if ("产值".equals(r[0])){
				CpmcEntity mc = findCp(czCps, (String)r[1]);
				if (null == mc){
					LoggerFactory.getLogger("WEBSERVICE").info("importByqCzCl 无法找到产值 : " + r[1]);
				}else{
					CpczwcqkEntity entityCz = cpczwcqkDao.getByDate(d, comp, SbdczclwcqkType.SBDCZCLWCQK_CZ_BYQ, mc.getId());
					
					if (null == entityCz){
						entityCz = new CpczwcqkEntity();

						entityCz.setNf(ec.getYear());
						entityCz.setYf(ec.getMonth());
						entityCz.setDwxx(dwxxDao.getById(comp.getId()));
						entityCz.setCpmc(mc);
						entityCz.setTjfs(SbdczclwcqkType.SBDCZCLWCQK_CZ_BYQ.value());
					}

					entityCz.setZt(czStatus.ordinal());
					entityCz.setCz(Util.toDoubleNull(
							r[2] != null ? r[2].toString().replaceAll(",", "") : null));
					
					cpczwcqkDao.merge(entityCz);	
				}
			}else{
				CpmcEntity mc = findCp(clCps, (String)r[1]);
				if (null == mc){
					LoggerFactory.getLogger("WEBSERVICE").info("importByqCzCl 无法找到产量 : " + (String)r[1]);
				}else{
					CpclwcqkEntity entityCl = cpclwcqkDao.getByDate(d, comp, SbdczclwcqkType.SBDCZCLWCQK_CL_BYQ, mc.getId());
					
					if (null == entityCl){
						entityCl = new CpclwcqkEntity();

						entityCl.setNf(ec.getYear());
						entityCl.setYf(ec.getMonth());
						entityCl.setDwxx(dwxxDao.getById(comp.getId()));
						entityCl.setCpmc(mc);
						entityCl.setTjfs(SbdczclwcqkType.SBDCZCLWCQK_CL_BYQ.value());
					}

					entityCl.setZt(clStatus.ordinal());
					entityCl.setCl(Util.toDoubleNull(
							r[2] != null ? r[2].toString().replaceAll(",", "") : null));	
					cpclwcqkDao.merge(entityCl);
				}
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

	@Override
	public void importXlCzCl(Date d) {
		LoggerFactory.getLogger("WEBSERVICE").info("importXlCzCl");
		List<Object[]> result = xlCzclDao.getCzCl(d);
		Company comp = companyManager.getBMDBOrganization().getCompany(CompanyType.XLC);
		importXlCzCl(d, result, comp);
	}

	@Override
	public void importLlCzCl(Date d) {
		LoggerFactory.getLogger("WEBSERVICE").info("importLlCzCl");
		List<Object[]> result = llCzclDao.getCzCl(d);
		Company comp = companyManager.getBMDBOrganization().getCompany(CompanyType.LLGS);
		importXlCzCl(d, result, comp);
	}
}
