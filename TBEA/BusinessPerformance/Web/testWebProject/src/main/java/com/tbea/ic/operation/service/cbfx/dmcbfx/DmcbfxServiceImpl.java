package com.tbea.ic.operation.service.cbfx.dmcbfx;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.cbfx.dmcbfx.DmcbfxDao;
import com.tbea.ic.operation.model.dao.cbfx.dmcbfx.DmcbfxDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cbfx.cbfl.Cbfl;
import com.tbea.ic.operation.model.dao.identifier.cbfx.cbfl.CbflDao;
import com.tbea.ic.operation.model.entity.cbfx.DmcbfxEntity;
import com.tbea.ic.operation.service.util.nc.NCCompanyCode;
import com.tbea.ic.operation.service.util.nc.NCConnection;

@Service(DmcbfxServiceImpl.NAME)
@Transactional("transactionManager")
public class DmcbfxServiceImpl implements DmcbfxService {
	@Resource(name=DmcbfxDaoImpl.NAME)
	DmcbfxDao dmcbfxDao;

	@Autowired
	CbflDao cbflDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	public final static String NAME = "DmcbfxServiceImpl";

	@Override
	public List<List<String>> getDmcbfx(Date d, Company company) {
		List<DmcbfxEntity> entities = dmcbfxDao.getByDate(d, company);
		List<List<String>> result  = new ArrayList<List<String>>();
		Util.resize(result, Cbfl.END.ordinal());
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		
		for (int i = 0; i < Cbfl.END.ordinal(); ++i){
			DmcbfxEntity entityTmp = null;
			for (DmcbfxEntity entity : entities){
				if (entity.getCbflid() == i){
					entityTmp = entity;
					entities.remove(entity);
					break;
				}
			}
			if (null != entityTmp){
			result.set(entityTmp.getCbflid(), toList(entityTmp, 
					dmcbfxDao.getByCpfl(entityTmp.getCbflid(), new Date(cal.getTimeInMillis()), company)));
			}else{
				List<String> list = new ArrayList<String>();
				Util.resize(list, 7);
				list.set(0, cbflDao.getById(i).getName());
				result.set(i, list);
			}
		}
		
		return result;
	}

	private List<String> toList(DmcbfxEntity entity, DmcbfxEntity qn) {
		List<String> list = new ArrayList<String>();
		list.add("" + cbflDao.getById(entity.getCbflid()).getName());
		list.add("" + entity.getJhz());
		list.add("" + entity.getSjz());
		list.add("" + Util.division(entity.getSjz(), entity.getJhz()));
		list.add("" + (qn == null ? null : qn.getSjz()));
		list.add("" + Util.minus(entity.getSjz(), qn == null ? null : qn.getSjz()));
		list.add("" + Util.division(Util.minus(entity.getSjz(), qn == null ? null : qn.getSjz()), entity.getSjz()));
		return list;
	}

	@Override
	public List<List<String>> getDmcbfxEntry(Date d, Company company) {
		List<DmcbfxEntity> entities = dmcbfxDao.getByDate(d, company);
		List<List<String>> result  = new ArrayList<List<String>>();
		Util.resize(result, Cbfl.END.ordinal());
		
		for (int i = 0; i < Cbfl.END.ordinal(); ++i){
			DmcbfxEntity entityTmp = null;
			for (DmcbfxEntity entity : entities){
				if (entity.getCbflid() == i){
					entityTmp = entity;
					entities.remove(entity);
					break;
				}
			}
			if (null != entityTmp){
				result.set(entityTmp.getCbflid(), toList(entityTmp));
			}else{
				List<String> list = new ArrayList<String>();
				Util.resize(list, 3);
				list.set(0, "" + i);
				list.set(1, cbflDao.getById(i).getName());
				result.set(i, list);
			}
		}
		return result;
	}

	private List<String> toList(DmcbfxEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getCbflid());
		list.add("" + cbflDao.getById(entity.getCbflid()).getName());
		list.add("" + entity.getJhz());
		return list;
	}
	
	private ErrorCode entryDmcbfx(Date d, JSONArray data, Company company, ZBStatus zt){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		for (int i = 0; i < data.size(); ++i){
			JSONArray row = data.getJSONArray(i);
			DmcbfxEntity entity = dmcbfxDao.getByCpfl(Integer.valueOf(row.getString(0)), d, company);
			if (entity == null){
				entity = new DmcbfxEntity();
				entity.setDwid(company.getId());
				entity.setCbflid(Integer.valueOf(row.getString(0)));
				entity.setNf(cal.get(Calendar.YEAR));
				entity.setYf(cal.get(Calendar.MONTH) + 1);
				entity.setZt(zt.ordinal());
			}

			entity.setJhz(Util.toDoubleNull(row.getString(1)));
			dmcbfxDao.merge(entity);
		}
		return ErrorCode.OK;
	}

	@Override
	public ErrorCode saveDmcbfx(Date d, JSONArray data, Company company) {
		return entryDmcbfx(d, data, company, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitDmcbfx(Date d, JSONArray data, Company company) {
		return entryDmcbfx(d, data, company, ZBStatus.SUBMITTED);
	}

	@Override
	public List<List<String>> getDmcbqsfx(Date d, Company company) {
		List<DmcbfxEntity> entities = dmcbfxDao.getByYear(d, company);
		List<List<String>> result  = new ArrayList<List<String>>();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		for (int i = 0; i < Cbfl.END.ordinal(); ++i){
			List<String> list = new ArrayList<String>();
			Util.resize(list, 17);
			result.add(list);
			list.set(0, cbflDao.getById(i).getName());
		}
		
		for (DmcbfxEntity entity : entities){
			setQsList(entity, result.get(entity.getCbflid()));
		}
		
		return result;
	}

	private void setQsList(DmcbfxEntity entity, List<String> list) {
		int area = (entity.getYf() - 1) / 3;
		int offset = (entity.getYf() - 1) % 3;
		list.set(area * 4 + offset + 1, "" + entity.getSjz());
		
		list.set(3 * 4 + 3 + 1, "" + Util.division(Util.sum(new Double[]{
				entity.getSjz(), Util.toDoubleNull(list.get(3 * 4 + 3))
		}), (3 + 1) * 3d));
		if (area < 3){
			list.set(2 * 4 + 3 + 1, "" + Util.division(Util.sum(new Double[]{
					entity.getSjz(), Util.toDoubleNull(list.get(2 * 4 + 3))
			}), (2 + 1) * 3d));
		}
		
		if (area < 2){
			list.set(4 + 3 + 1, "" + Util.division(Util.sum(new Double[]{
					entity.getSjz(), Util.toDoubleNull(list.get(4 + 3))
			}), (1 + 1) * 3d));
		}
		
		if (area < 1){
			list.set(3 + 1, "" + Util.division(Util.sum(new Double[]{
					entity.getSjz(), Util.toDoubleNull(list.get(3))
			}), 3d)); 
		}
	}
	
	private final static String ncSql = 
			"	select iui.unit_code,	" +
			"	       iui.unit_name, " + //单位名称 
			"	       inputdate,	" +
			"	       imdc.m10028   tfblbpcbsj, " + //土方剥离爆破成本实际
			"	       imdc.m10004   ymbpcbsj, " + //原煤爆破成本实际
			"	       imdc.m10040   ymcycbsj, " + //原煤采运成本实际
			"	       imdc.m10016   hsdycbsj, " + //回筛倒运成本实际
			"	       imdc.m10052   zccbsj, " + //装车成本实际
			"	       imdc.m10034   zjcbxjsj, " + //直接成本小计实际
			"	       imdc.m10010   fkkcbsj, " + //非可控成本实际
			"	       imdc.m10046   kkcbsj, " + //可控成本实际
			"	       imdc.m10022   zzfyxjsj, " + //制造费用小计实际
			"	       imdc.m10058   sccbhjsj, " + //生产成本合计实际
			"	       imdc.m6gtjtk  dyrklsj " + //当月入库量实际
			"	  from iufo_measure_data_c5lltnso imdc	" +
			"	  left join (select alone_id,	" +
			"	                    code,	" +
			"	                    inputdate,	" +
			"	                    keyword2,	" +
			"	                    keyword3,	" +
			"	                    time_code,	" +
			"	                    ts,	" +
			"	                    ver	" +
			"	               from iufo_measure_pubdata) imp	" +
			"	    on imdc.alone_id = imp.alone_id	" +
			"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +
			"	    on imp.code = iui.unit_id	" +
			"	 where imp.ver = 0	%s " +
			"	 order by unit_code, inputdate desc	";


	
	@Override
	public void importFromNC(Date d, List<Company> comps) {
		NCConnection connection = NCConnection.create();
		if (null != connection){
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			String whereSql = 
				" and iui.unit_code in (" + StringUtils.join(NCCompanyCode.toCodeList(comps).toArray(), ",") + ")" + 
				" and extract(year from to_date(inputdate,'yyyy-mm-dd')) =" + cal.get(Calendar.YEAR) + 
				" and extract(month from to_date(inputdate,'yyyy-mm-dd')) =" + (cal.get(Calendar.MONTH) + 1);
			
			Logger logger = Logger.getLogger("LOG-NC");
			logger.debug("成本分析  吨煤成本分析");
			ResultSet rs = connection.query(String.format(ncSql, whereSql));
			if (null != rs){	
				try {
					mergeEntity(cal, rs);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}

	private void mergeEntity(Calendar cal, ResultSet rs) throws SQLException {
	while (rs.next()) {
			String unitCode = String.valueOf(rs.getObject(1));
			CompanyType companyType = NCCompanyCode.getType(unitCode);
			Company comp = companyManager.getBMDBOrganization().getCompany(companyType);
			int nf = cal.get(Calendar.YEAR);
			int yf = cal.get(Calendar.MONTH) + 1;
			for (int i = 0; i < Cbfl.END.ordinal(); ++i){
				DmcbfxEntity entity = dmcbfxDao.getByCpfl(i, Util.toDate(cal), comp);
				if (entity == null){
					entity = new DmcbfxEntity();
					entity.setNf(nf);
					entity.setYf(yf);
					entity.setDwid(comp.getId());
				}
				entity.setSjz(Util.division(Util.division(rs.getDouble(i + 4), rs.getDouble(4 + Cbfl.END.ordinal())), 10000d));
				dmcbfxDao.merge(entity);
			}
		}
	}


}
