package com.tbea.ic.operation.service.cwgbjyxxjl;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.CompanyNCCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.cwgbjyxxjl.jyxxjl.jyxxjlDao;
import com.tbea.ic.operation.model.dao.cwgbjyxxjl.jyxxjl.jyxxjlDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cwgb.km.KmDao;
import com.tbea.ic.operation.model.dao.identifier.cwgb.km.KmDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.entity.cwgbjyxxjl.JyxxjlEntity;
import com.tbea.ic.operation.model.entity.identifier.cwgb.KmEntity;
import com.tbea.ic.operation.service.util.nc.NCConnection;

@Service(CwgbjyxxjlServiceImpl.NAME)
@Transactional("transactionManager")
public class CwgbjyxxjlServiceImpl implements CwgbjyxxjlService {
	@Resource(name=jyxxjlDaoImpl.NAME)
	jyxxjlDao jyxxjlDao;

	@Resource(name=KmDaoImpl.NAME)
	KmDao kmDao;
	
	@Autowired
	DWXXDao dwxxDao;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	public final static String NAME = "CwgbjyxxjlServiceImpl";

	
	//	---------------------------经营性现金流（当月值）--------------------------------		
	private static String sqlDyz =			
		"	select unit_code,	" +	
		"	       unit_name,	" +	
		"	       inputdate,	" +	
		"	       imd9.m10138 xssptglwsd,	" +	//销售商品、提供劳务收到的现金
		"	       imd9.m10052 sdsffh,	" +	//收到的税费返还
		"	       imd5.m10025 sddqtyjyhdygxj,	" +	//收到的其他与经营活动有关的现金
		"	       imd9.m10181 fksdxj,	" +	//其中：罚款所收到的现金
		"	       imd5.m10008 zfbzsd,	" +	//其中：政府补助所收到的现金
		"	       imd9.m10242 sdbdwtbbzj,	" +	//其中：收到本单位向外投标退回所收到的投标保证金
		"	       imd9.m10148 sdwdwtbbzj,	" +	//其中：收到外单位投标保证金所收到的现金
		"	       imd9.m10100 rcywjzthssdxj,	" +	//其中：日常业务借支退回所收到的现金
		"	       imd5.m10053 yhcklxssdxj,	" +	//其中：银行存款利息所收到到的现金
		"	       imd5.m10025 sddqtyjyhdygxj,	" +	//其中：收到的其他与经营活动有关的现金
		"	       imd9.m10142 jyhdxjlr,	" +	//现金流入小计
		"	       imd9.m10150 gmspjslwszfxj,	" +	//购买商品、接受劳务所支付的现金
		"	       imd5.m10062 zfgzxj,	" +	//支付给职工以及为职工支付的现金
		"	       imd9.m10309 zfgxsf,	" +	//支付的各项税费
		"	       imd9.m10210 zfqtjyhdygxj,	" +	//支付的其他与经营活动有关的现金
		"	       imd9.m10337 zfbdwtbbzj,	" +	//其中：本单位向外投标所支付的投标保证金
		"	       imd5.m10040 tfwdwtbbzj,	" +	//其中：退付外单位投标保证金所支付的现金
		"	       imd9.m10111 dlzxfzfxj,	" +	//其中：代理咨询费所支付的现金
		"	       imd9.m10198 zbfwfzfxj,	" +	//其中：中标服务费所支付的现金
		"	       imd9.m10177 rcywjzzfxj,	" +	//其中：日常业务借支所支付的现金
		"	       imd5.m10059 yhxgywsxfzfxj,	" +	//其中：银行相关业务手续费所支付的现金
		"	       imd5.m10039 qtjyhdygxj,	" +	//其中：支付的其他与经营活动有关的现金
		"	       imd5.m10061 jyhdxjlc,	" +	//现金流出小计
		"	       imd9.m10029 jyhdcsdxjllje 	" +	//经营活动产生的现金流量净额
		"	  from iufo_measure_data_9hzo24a7 imd9	" +	
		"	  left join iufo_measure_data_56m8ewc1 imd5	" +	
		"	    on imd9.alone_id = imd5.alone_id	" +	
		"	  left join (select alone_id,	" +	
		"	                    code,	" +	
		"	                    inputdate,	" +	
		"	                    keyword2,	" +	
		"	                    keyword3,	" +	
		"	                    time_code,	" +	
		"	                    ts,	" +	
		"	                    ver	" +	
		"	               from iufo_measure_pubdata) imp	" +	
		"	    on imd5.alone_id = imp.alone_id	" +	
		"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
		"	    on imp.code = iui.unit_id	" +	
		"	 where imp.ver = 510	%s ";	
			/*   and iui.unit_name like '%新疆变压器厂%'		
			   and substr(inputdate, 1, 7) = '2016-03'*/		
	
	//---------------------------经营性现金流（本年累计数）--------------------------------		
	private static String sqlBnljs =			
		"	select unit_code,	" +	
		"	       unit_name,	" +	
		"	       inputdate,	" +	
		"	       imd5.m10034 xssptglwsdlj,	" +	//销售商品、提供劳务收到的现金(本年累计数)
		"	       imd9.m10056 sdsffhlj,	" +	//收到的税费返还(本年累计数)
		"	       imd9.m10304 sddqtyjyhdygxjlj,	" +	//收到的其他与经营活动有关的现金(本年累计数)
		"	       imd9.m10178 fksdxjlj,	" +	//其中：罚款所收到的现金(本年累计数)
		"	       imd9.m10197 zfbzsdlj,	" +	//其中：政府补助所收到的现金(本年累计数)
		"	       imd9.m10283 sdbdwtbbzjlj,	" +	//其中：收到本单位向外投标退回所收到的投标保证金(本年累计数)
		"	       imd9.m10037 sdwdwtbbzjlj,	" +	//其中：收到外单位投标保证金所收到的现金(本年累计数)
		"	       imd9.m10345 rcywjzthssdxjlj,	" +	//其中：日常业务借支退回所收到的现金(本年累计数)
		"	       imd9.m10232 yhcklxssdxjlj,	" +	//其中：银行存款利息所收到到的现金(本年累计数)
		"	       imd5.m10035 sddqtyjyhdygxjlj,	" +	//其中：收到的其他与经营活动有关的现金(本年累计数)
		"	       imd9.m10120 jyhdxjlrlj,	" +	//现金流入小计(本年累计数)
		"	       imd9.m10228 gmspjslwszfxjlj,	" +	//购买商品、接受劳务所支付的现金(本年累计数)
		"	       imd9.m10008 zfgzxjlj,	" +	//支付给职工以及为职工支付的现金(本年累计数)
		"	       imd9.m10066 zfgxsflj,	" +	//支付的各项税费(本年累计数)
		"	       imd5.m10046 zfqtjyhdygxjlj,	" +	//支付的其他与经营活动有关的现金(本年累计数)
		"	       imd5.m10052 zfbdwtbbzjlj,	" +	//其中：本单位向外投标所支付的投标保证金(本年累计数)
		"	       imd9.m10306 tfwdwtbbzjlj,	" +	//其中：退付外单位投标保证金所支付的现金(本年累计数)
		"	       imd9.m10108 dlzxfzfxjlj,	" +	//其中：代理咨询费所支付的现金(本年累计数)
		"	       imd9.m10224 zbfwfzfxjlj,	" +	//其中：中标服务费所支付的现金(本年累计数)
		"	       imd9.m10110 rcywjzzfxjlj,	" +	//其中：日常业务借支所支付的现金(本年累计数)
		"	       imd9.m10124 yhxgywsxfzfxjlj,	" +	//其中：银行相关业务手续费所支付的现金(本年累计数)
		"	       imd5.m10046 qtjyhdygxjlj,	" +	//其中：支付的其他与经营活动有关的现金(本年累计数)
		"	       imd5.m10064 jyhdxjlclj,	" +	//现金流出小计(本年累计数)
		"	       imd9.m10114 jyhdcsdxjlljelj 	" +	
		"	  from iufo_measure_data_9hzo24a7 imd9	" +	
		"	  left join iufo_measure_data_56m8ewc1 imd5	" +	
		"	    on imd9.alone_id = imd5.alone_id	" +	
		"	  left join (select alone_id,	" +	
		"	                    code,	" +	
		"	                    inputdate,	" +	
		"	                    keyword2,	" +	
		"	                    keyword3,	" +	
		"	                    time_code,	" +	
		"	                    ts,	" +	
		"	                    ver	" +	
		"	               from iufo_measure_pubdata) imp	" +	
		"	    on imd5.alone_id = imp.alone_id	" +	
		"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
		"	    on imp.code = iui.unit_id	" +	
		"	 where imp.ver = 510	%s ";	
		/*and iui.unit_name like '%新疆变压器厂%'		
		and substr(inputdate,1,7) = '2016-03'*/


	public interface OnSetValue {
		void onSet(JyxxjlEntity entity, Double val);
	}


	@Override
	public void importFromNC(Date d, List<Company> comps) {
		NCConnection connection = NCConnection.create();
		if (null != connection){
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			String whereSql = 
				" and unit_code in (" + StringUtils.join(CompanyNCCode.toCodeList(comps).toArray(), ",") + ")" + 
						" and extract(year from to_date(inputdate,'yyyy-mm-dd')) =" + cal.get(Calendar.YEAR) + 
						" and extract(month from to_date(inputdate,'yyyy-mm-dd')) =" + (cal.get(Calendar.MONTH) + 1);

			Map<String, JyxxjlEntity> cacheMap = new HashMap<String, JyxxjlEntity>();
			
			Logger logger = Logger.getLogger("LOG-NC");
			logger.debug("财务经营性现金流 sqlDyz");
			ResultSet rs = connection.query(String.format(sqlDyz, whereSql));
			mergersEntity(cal, cacheMap, rs, new OnSetValue(){
				@Override
				public void onSet(JyxxjlEntity entity, Double val) {
					entity.setSjz(val);
				}
			});
			
			logger.debug("财务经营性现金流 sqlBnljs");
			rs = connection.query(String.format(sqlBnljs, whereSql));
			mergersEntity(cal, cacheMap, rs, new OnSetValue(){
				@Override
				public void onSet(JyxxjlEntity entity, Double val) {
					entity.setNdlj(val);
				}
			});
			for (String key : cacheMap.keySet()){
				jyxxjlDao.merge(cacheMap.get(key));
			}
		}
		
	}


	private void mergersEntity(Calendar cal,
			Map<String, JyxxjlEntity> cacheMap, ResultSet rs,
			OnSetValue onSetValue) {
		try {
			
			List<KmEntity> kms = kmDao.getAll();
			int nf = cal.get(Calendar.YEAR);
			int yf = cal.get(Calendar.MONTH) + 1;
			Date d = Util.toDate(cal);
			while (rs.next()) {

				String unitCode = String.valueOf(rs.getObject(1));
				CompanyType companyType = CompanyNCCode.getType(unitCode);
				Company comp = companyManager.getBMDBOrganization().getCompanyByType(companyType);
				
				
				for (int i = 0; i < kms.size(); ++i){
					String key = "dwid" + comp.getId() + "km" + kms.get(i).getId();
					JyxxjlEntity entity = cacheMap.get(key);

					if (null == entity){
						entity = jyxxjlDao.getByDate(d, comp, kms.get(i).getId());
						
					}
					
					if (null == entity) {
						entity = new JyxxjlEntity();
						entity.setDwxx(dwxxDao.getById(comp.getId()));
						entity.setNf(nf);
						entity.setYf(yf);
						entity.setKm(kms.get(i));
					}
					cacheMap.put(key, entity);
					onSetValue.onSet(entity, MathUtil.division(rs.getDouble(i + 4), 10000d));
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
