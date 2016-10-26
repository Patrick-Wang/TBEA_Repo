package com.tbea.ic.operation.service.cpzlqk;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx.ByqBhgwtmxDao;
import com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx.ByqBhgwtmxDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.nbzlztqk.NbyclzlwtDao;
import com.tbea.ic.operation.model.dao.cpzlqk.nbzlztqk.NbyclzlwtDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.pdbhgwtmx.PdBhgwtmxDao;
import com.tbea.ic.operation.model.dao.cpzlqk.pdbhgwtmx.PdBhgwtmxDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.wbzlztqk.WbyclzlwtDao;
import com.tbea.ic.operation.model.dao.cpzlqk.wbzlztqk.WbyclzlwtDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.xlbhgwtmx.XlBhgwtmxDao;
import com.tbea.ic.operation.model.dao.cpzlqk.xlbhgwtmx.XlBhgwtmxDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.yclhgl.ZlYclhglDao;
import com.tbea.ic.operation.model.dao.cpzlqk.yclhgl.ZlYclhglDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqBhgwtmxEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.NbyclzlwtEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.PdBhgwtmxEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.WbyclzlwtEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.XlBhgwtmxEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZlYclhglEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Service
@Transactional("transactionManager")
public class CpzlqkServiceImpl implements CpzlqkService {
	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Resource(name=ZltjjgDaoImpl.NAME)
	ZltjjgDao zltjjgDao;
	
	@Resource(name=ByqBhgwtmxDaoImpl.NAME)
	ByqBhgwtmxDao byqBhgwtmxDao;
	
	@Resource(name=XlBhgwtmxDaoImpl.NAME)
	XlBhgwtmxDao xlBhgwtmxDao;
	
	@Resource(name=PdBhgwtmxDaoImpl.NAME)
	PdBhgwtmxDao pdBhgwtmxDao;

	@Resource(name=NbyclzlwtDaoImpl.NAME)
	NbyclzlwtDao nbyclzlwtDao;

	@Resource(name=WbyclzlwtDaoImpl.NAME)
	WbyclzlwtDao wbyclzlwtDao;
	
	@Resource(name=ZlYclhglDaoImpl.NAME)
	ZlYclhglDao zlYclhglDao;
	
	@Override
	public ErrorCode approve(Date d, Company company, ZBStatus status) {
		List<ZltjjgEntity> entities = zltjjgDao.getByDateIgnoreStatus(d, company);
		EasyCalendar ec = new EasyCalendar();
		for (int i = 0; i < entities.size(); ++i){
			ZltjjgEntity entity = entities.get(i);
			entity.setZt(status.ordinal());
			entity.setShsj(ec.getTimestamp());
			zltjjgDao.merge(entity);
		}

		List<ByqBhgwtmxEntity> entitiesBhgMx = byqBhgwtmxDao.getByDate(d, company);
		for (ByqBhgwtmxEntity entity : entitiesBhgMx){
			entity.setZt(status.ordinal());
			entity.setShsj(ec.getTimestamp());
			byqBhgwtmxDao.merge(entity);
		}
		
		List<XlBhgwtmxEntity> entitiesXlMx = xlBhgwtmxDao.getByDate(d, company);
		for (XlBhgwtmxEntity entity : entitiesXlMx){
			entity.setZt(status.ordinal());
			entity.setShsj(ec.getTimestamp());
			xlBhgwtmxDao.merge(entity);
		}
		
		List<PdBhgwtmxEntity> entitiesPdMx = pdBhgwtmxDao.getByDate(d, company);
		for (PdBhgwtmxEntity entity : entitiesPdMx){
			entity.setZt(status.ordinal());
			entity.setShsj(ec.getTimestamp());
			pdBhgwtmxDao.merge(entity);
		}

		return ErrorCode.OK;
	}


	public ZBStatus getZltjjgStatus(Date d, Company company) {
		List<ZltjjgEntity> zltjjgs = zltjjgDao.getByDateIgnoreStatus(d, company);
		if (!zltjjgs.isEmpty()){
			return ZBStatus.valueOf(zltjjgs.get(0).getZt());
		}
		return ZBStatus.NONE;
	}


	public ZBStatus getByqBhgwtmxStatus(Date d, Company company) {
		List<ByqBhgwtmxEntity> entitiesBhgMx = byqBhgwtmxDao.getByDate(d, company);
		if (!entitiesBhgMx.isEmpty()){
			return ZBStatus.valueOf(entitiesBhgMx.get(0).getZt());
		}
		return ZBStatus.NONE;
	}
	

	public ZBStatus getXlBhgwtmxStatus(Date d, Company company) {
		List<XlBhgwtmxEntity> entitiesBhgMx = xlBhgwtmxDao.getByDate(d, company);
		if (!entitiesBhgMx.isEmpty()){
			return ZBStatus.valueOf(entitiesBhgMx.get(0).getZt());
		}
		return ZBStatus.NONE;
	}
	

	public ZBStatus getPdBhgwtmxStatus(Date d, Company company) {
		List<PdBhgwtmxEntity> entitiesBhgMx = pdBhgwtmxDao.getByDate(d, company);
		if (!entitiesBhgMx.isEmpty()){
			return ZBStatus.valueOf(entitiesBhgMx.get(0).getZt());
		}
		return ZBStatus.NONE;
	}

	@Override
	public ErrorCode approveNwbzlqk(Date d, Company company, ZBStatus status) {
		List<NbyclzlwtEntity> nbwts = nbyclzlwtDao.getAllIgnoreStatus(d, company);
		Timestamp ts = new EasyCalendar().getTimestamp();
		for (NbyclzlwtEntity entity : nbwts){
			entity.setZt(status.ordinal());
			entity.setShsj(ts);
			nbyclzlwtDao.merge(entity);
		}
		
		List<WbyclzlwtEntity> wbwts = wbyclzlwtDao.getAllIgnoreStatus(d, company);
		for (WbyclzlwtEntity entity : wbwts){
			entity.setZt(status.ordinal());
			entity.setShsj(ts);
			wbyclzlwtDao.merge(entity);
		}
		return ErrorCode.OK;
	}

	@Override
	public ZBStatus getNwbzlwtStatus(Date d, Company company) {
		Set<ZBStatus> status = new HashSet<ZBStatus>();
		List<NbyclzlwtEntity> nbwts = nbyclzlwtDao.getAllIgnoreStatus(d, company);
		if (!nbwts.isEmpty()){
			status.add(ZBStatus.valueOf(nbwts.get(0).getZt()));
		}
			
		List<WbyclzlwtEntity> wbwts = wbyclzlwtDao.getAllIgnoreStatus(d, company);
		if (!wbwts.isEmpty()){
			status.add(ZBStatus.valueOf(wbwts.get(0).getZt()));
		}
	
		if (status.contains(ZBStatus.SAVED)){
			return ZBStatus.SAVED;
		}
		
		if (status.contains(ZBStatus.SUBMITTED)){
			return ZBStatus.SUBMITTED;
		}
		
		status.remove(ZBStatus.NONE);
		if (status.iterator().hasNext()){
			return status.iterator().next();
		}
		
		return ZBStatus.NONE;
	}

	@Override
	public ZBStatus getCpzlqkStatus(Date d, Company company) {
		Set<ZBStatus> status = new HashSet<ZBStatus>();
		status.add(this.getZltjjgStatus(d, company));
		status.add(this.getByqBhgwtmxStatus(d, company));
		status.add(this.getXlBhgwtmxStatus(d, company));
		status.add(this.getPdBhgwtmxStatus(d, company));
		status.add(this.getYclhglStatus(d, company));
		
		if (status.contains(ZBStatus.SAVED)){
			return ZBStatus.SAVED;
		}
		
		if (status.contains(ZBStatus.SUBMITTED)){
			return ZBStatus.SUBMITTED;
		}
		
		status.remove(ZBStatus.NONE);
		if (status.iterator().hasNext()){
			return status.iterator().next();
		}
		return ZBStatus.NONE;
	}


	private ZBStatus getYclhglStatus(Date d, Company company) {
		// TODO Auto-generated method stub
		ZlYclhglEntity entity = zlYclhglDao.getFirst(d, company);
		if (null != entity){
			return ZBStatus.valueOf(entity.getZt());
		}
		return ZBStatus.NONE;
	}
	

}
