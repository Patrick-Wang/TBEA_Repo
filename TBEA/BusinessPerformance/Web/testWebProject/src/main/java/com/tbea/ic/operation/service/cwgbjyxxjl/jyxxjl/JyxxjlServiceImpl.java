package com.tbea.ic.operation.service.cwgbjyxxjl.jyxxjl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cwgbjyxxjl.jyxxjl.jyxxjlDao;
import com.tbea.ic.operation.model.dao.cwgbjyxxjl.jyxxjl.jyxxjlDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cwgb.km.KmDao;
import com.tbea.ic.operation.model.dao.identifier.cwgb.km.KmDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.entity.cwgbjyxxjl.JyxxjlEntity;

@Service(JyxxjlServiceImpl.NAME)
@Transactional("transactionManager")
public class JyxxjlServiceImpl implements JyxxjlService {
	@Resource(name=jyxxjlDaoImpl.NAME)
	jyxxjlDao jyxxjlDao;

	@Resource(name=KmDaoImpl.NAME)
	KmDao kmDao;
	
	@Autowired
	DWXXDao dwxxDao;

	
	public final static String NAME = "JyxxjlServiceImpl";

	private List<Integer> getKmIdList() {
		
		List<Integer> kmIdList = new ArrayList<Integer>();
		
		for (JYXXJL_KM_Type cp : JYXXJL_KM_Type.values()) {
			kmIdList.add(cp.value());
		}

		return kmIdList;
	}

	@Override
	public List<List<String>> getJyxxjlND(Date d, Company company) {
		
		List<List<String>> result = new ArrayList<List<String>>();
		List<Integer> kmIdList = getKmIdList();
		
		for (int cp = 0; cp < kmIdList.size(); cp++) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.YEAR, -1);
			cal.add(Calendar.MONTH, 1);
			
			List<JyxxjlEntity> entities= jyxxjlDao.getByDate(new Date(cal.getTimeInMillis()), d, company, kmIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(kmDao.getById(kmIdList.get(cp)).getName());
			
			for (int i = 0; i < 12; ++i){

				Boolean bFind = false;
				for (JyxxjlEntity entity : entities){
					if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
						bFind = true;
						oneLine.add("" + entity.getSjz());
						
						entities.remove(entity);
						break;
					}
				}
				if (!bFind) {
					oneLine.add("null");
				}
				cal.add(Calendar.MONTH, 1);
			}
			
			result.add(oneLine);
		}
		
		return result;
	}
	
	private String getJhWcl(Double jh, Double sj) {
		return "" + MathUtil.division(sj, jh);
	}
	
	private String getTbzf(Double sj, Double tq) {
		return "" + MathUtil.division(MathUtil.minus(sj, tq), tq);
	}
	
	
	@Override
	public List<List<String>> getJyxxjlYD(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<Integer> kmIdList = getKmIdList();
		
		for (int cp = 0; cp < kmIdList.size(); cp++) {
			
			JyxxjlEntity entity= jyxxjlDao.getByDate(d, company, kmIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(kmDao.getById(kmIdList.get(cp)).getName());
			
			if (entity != null) {

				oneLine.add("" + entity.getJhz());
				oneLine.add("" + entity.getSjz());

				oneLine.add("" + getJhWcl(entity.getJhz(), entity.getSjz()));
				
				
				Calendar calQn = Calendar.getInstance();
				calQn.setTime(d);
				calQn.add(Calendar.YEAR, -1);
				
				JyxxjlEntity quEntity = jyxxjlDao.getByDate(new Date(calQn.getTimeInMillis()), company, kmIdList.get(cp));

				if (quEntity != null) {
					oneLine.add("" + quEntity.getSjz());
					oneLine.add("" + getTbzf(entity.getSjz(), quEntity.getSjz()));
				} else {
					oneLine.add("null");
					oneLine.add("null");
				}

				oneLine.add("" + entity.getNdlj());

				if (quEntity != null) {
					oneLine.add("" + quEntity.getNdlj());
					oneLine.add("" + getTbzf(entity.getNdlj(), quEntity.getNdlj()));
				} else {
					oneLine.add("null");
					oneLine.add("null");
				}
				
			} else {
				oneLine.add("null");
				oneLine.add("null");
				oneLine.add("null");
				oneLine.add("null");
				oneLine.add("null");
				oneLine.add("null");
				oneLine.add("null");
				oneLine.add("null");
			}
			
			result.add(oneLine);
		}
		
		return result;
	}
	
	@Override
	public List<List<String>> getJyxxjlEntry(Date d, Company company) {

		List<List<String>> result = new ArrayList<List<String>>();
		List<Integer> kmIdList = getKmIdList();

		for (int cp = 0; cp < kmIdList.size(); cp++) {

			JyxxjlEntity entity = jyxxjlDao.getByDate(d, company, kmIdList.get(cp));
			List<String> oneLine = new ArrayList<String>();

			oneLine.add(kmDao.getById(kmIdList.get(cp)).getName());
			
			if (entity != null) {
				oneLine.add("" + entity.getJhz());
			} else {
				oneLine.add("null");
			}

			result.add(oneLine);
		}

		return result;
	}

	
	ErrorCode entryJyxxjl(Date d, Company company, JSONArray data, ZBStatus status) {

		ErrorCode err = ErrorCode.OK;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<Integer> kmIdList = getKmIdList();
		
		for (int cp = 0; cp < kmIdList.size(); cp++) {
			JyxxjlEntity entity= jyxxjlDao.getByDate(d, company, kmIdList.get(cp));
			
			if (null == entity){
				entity = new JyxxjlEntity();

				entity.setNf(cal.get(Calendar.YEAR));
				entity.setYf(cal.get(Calendar.MONTH) + 1);
				entity.setDwxx(dwxxDao.getById(company.getId()));
				entity.setKm(kmDao.getById(kmIdList.get(cp)));
			}

			entity.setZt(status.ordinal());
			entity.setJhz(Util.toDoubleNull(data.getJSONArray(cp).getString(0)));
			
			jyxxjlDao.merge(entity);
		}
		
		return err;
	}
	
	
	@Override
	public ErrorCode saveJyxxjl(Date d, Company company, JSONArray data) {

		return entryJyxxjl(d, company, data, ZBStatus.SAVED);		
	}

	@Override
	public ErrorCode submitJyxxjl(Date d, Company company, JSONArray data) {

		return entryJyxxjl(d, company, data, ZBStatus.SUBMITTED);	
	}
	
	@Override
	public ZBStatus getJyxxjlStatus(Date d, Company comp) {
		return ZBStatus.SAVED;	
	}
	
}
