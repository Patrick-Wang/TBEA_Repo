package com.tbea.ic.operation.service.cwcpdlml;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml.CpdlmlDao;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpfl.CpflDao;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpfl.CpflDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cwgb.cy.CyDao;
import com.tbea.ic.operation.model.dao.identifier.cwgb.cy.CyDaoImpl;
import com.tbea.ic.operation.model.entity.cwcpdlml.CpdlmlEntity;
import com.tbea.ic.operation.model.entity.cwcpdlml.CpflEntity;
import com.tbea.ic.operation.model.entity.cwgbjyxxjl.JyxxjlEntity;
import com.tbea.ic.operation.model.entity.identifier.cwgb.KmEntity;
import com.tbea.ic.operation.service.util.nc.NCCompanyCode;
import com.tbea.ic.operation.service.util.nc.NCConnection;

@Service(CwcpdlmlServiceImpl.NAME)
@Transactional("transactionManager")
public class CwcpdlmlServiceImpl implements CwcpdlmlService {
	@Resource(name=CyDaoImpl.NAME)
	CyDao cyDao;

	@Resource(name=CpflDaoImpl.NAME)
	CpflDao cpflDao;
	
	@Autowired
	CpdlmlDao cpdlmlDao;

	public final static String NAME = "CwcpdlmlServiceImpl";

	//	--------------------------变压器产业（按电压等级分类）-------------------------------		
	private static String sqlCbByqcyAdydjfl = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imd7.m10099 jlbyqcb,	" +	//今年累计交流变压器销售成本
	"	       imd7.m10073 jl35cb,	" +	//今年累计其中：35KV及以下销售成本
	"	       imd7.m10047 jl66110lcb,	" +	//今年累计66-110KV销售成本
	"	       imd7.m10021 jl220cb,	" +	//今年累计220KV销售成本
	"	       imdu.m10350 jl330cb,	" +	//今年累计330KV销售成本
	"	       imdu.m10324 jl500cb,	" +	//今年累计500KV销售成本
	"	       imdu.m10298 jl750cb,	" +	//今年累计750kV销售成本
	"	       imdu.m10272 jl1000cb,	" +	//今年累计1000kV销售成本
	"	       imdu.m10246 zlbyqcb,	" +	//今年累计直流变压器销售成本
	"	       imdu.m10220 zl400cb,	" +	//今年累计其中：±400kv及以下销售成本
	"	       imdu.m10194 zl500cb,	" +	//今年累计±500kv销售成本
	"	       imdu.m10168 zl600cb,	" +	//今年累计±600kv销售成本
	"	       imdu.m10142 zl800cb,	" +	//今年累计±800kv销售成本
	"	       imdu.m10116 zlpbdkqcb,	" +	//今年累计平波电抗器销售成本
	"	       imdu.m10090 dkqcb,	" +	//今年累计电抗器销售成本
	"	       imdu.m10064 dkq330cb,	" +	//今年累计其中：330kV及以下销售成本
	"	       imdu.m10038 dkq500cb,	" +	//今年累计500kV电销售成本
	"	       imdu.m10012 dkq750cb,	" +	//今年累计750kV电销售成本
	"	       imdz.m10341 dkq1000cb,	" +	//今年累计1000kV电销售成本
	"	       imdz.m10315 dydjljcb	" +	//今年累计小计销售成本（按产品电压等级分类）
	"	  from iufo_measure_data_7zz4hjkz imd7	" +	
	"	  left join iufo_measure_data_ukdj7hhy imdu	" +	
	"	    on imd7.alone_id = imdu.alone_id	" +	
	"	  left join iufo_measure_data_zwrb9cyz imdz	" +	
	"	    on imd7.alone_id = imdz.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imd7.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
		 		
		//------------------------变压器产业（按产品类型分类）-------------------------------		
	private static String sqlCbByqcyAcplxfl = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imdz.m10249 gsbyqcb,	" +	//今年累计干式变压器销售成本
	"	       imdz.m10223 gsfjcb,	" +	//今年累计其中：F级干变销售成本
	"	       imdz.m10197 gshjcb,	" +	//今年累计H级干变销售成本
	"	       imdz.m10171 gsxsbdcb,	" +	//今年累计箱式变电站销售成本
	"	       imdz.m10145 gsdkqcb,	" +	//今年累计干式电抗器销售成本
	"	       imdz.m10119 tzbyqcb,	" +	//今年累计特种变压器销售成本
	"	       imdz.m10093 tzgbcb,	" +	//今年累计其中：隔爆变销售成本
	"	       imdz.m10067 tzzlcb,	" +	//今年累计整流变销售成本
	"	       imdz.m10041 tzqycb,	" +	//今年累计牵引变销售成本
	"	       imdz.m10015 tzytcb,	" +	//今年累计油田变销售成本
	"	       imdq.m10344 tzqtcb,	" +	//今年累计其它销售成本
	"	       imdq.m10318 yslcb,	" +	//今年累计延伸类销售成本
	"	       imdq.m10292 yslpdzdhcb,	" +	//今年累计配电自动化销售成本
	"	       imdq.m10266 yslkgcb,	" +	//今年累计开关销售成本
	"	       imdq.m10240 ysltgcb,	" +	//今年累计套管销售成本
	"	       imdq.m10214 yslhgqcb,	" +	//今年累计互感器销售成本
	"	       imdq.m10188 yslwxbjcb,	" +	//今年累计维修备件销售成本
	"	       imdq.m10162 cplxflljcb	" +	//今年累计小计销售成本（按产品类型分类）
	"	  from iufo_measure_data_qp1i8fs7 imdq	" +	
	"	  left join iufo_measure_data_zwrb9cyz imdz	" +	
	"	    on imdq.alone_id = imdz.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imdq.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
		 		
	//	--------------------------线缆产业-------------------------------		
	private static String sqlCbXlcy = 
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imdq.m10064 dxcb,	" +	//今年累计导线销售成本
	"	       imdq.m10038 bdxcb,	" +	//今年累计布电线销售成本
	"	       imdq.m10012 jkxcb,	" +	//今年累计架空线销售成本
	"	       imdl.m10341 kzdlcb,	" +	//今年累计控制电缆销售成本
	"	       imdl.m10315 jldlcb,	" +	//今年累计交联电缆销售成本
	"	       imdl.m10289 dldlcb,	" +	//今年累计电力电缆销售成本
	"	       imdl.m10263 dcxcb,	" +	//今年累计电磁线销售成本
	"	       imdl.m10237 tzdlcb,	" +	//今年累计特种电缆销售成本
	"	       imdl.m10211 dlfjcb,	" +	//今年累计电缆附件销售成本
	"	       imdl.m10185 tgcb,	" +	//今年累计铜杆销售成本
	"	       imdl.m10159 lgcb,	" +	//今年累计铝杆销售成本
	"	       imdl.m10133 pvclcb,	" +	//今年累计PVC料销售成本
	"	       imdl.m10107 gzlcb,	" +	//今年累计工装轮销售成本
	"	       imdl.m10081 xlxjcb	" +	//今年累计小计销售成本
	"	  from iufo_measure_data_qp1i8fs7 imdq	" +	
	"	  left join iufo_measure_data_l1hnj73b imdl	" +	
	"	    on imdq.alone_id = imdl.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imdq.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
		 		
	//	-------------------------物流贸易类---------------------		
	private static String sqlCbWlmyl = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imd5.m10014 wlmycb,	" +	//今年累计物流贸易销售成本
	"	       imdg.m10343 wlmyxjcb	" +	//今年累计小计销售成本
	"	  from iufo_measure_data_5a801obu imd5	" +	
	"	  left join iufo_measure_data_gyin4hlu imdg	" +	
	"	    on imd5.alone_id = imdg.alone_id	" +	
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
	"	 where imp.ver = 0	";	
		 		
				
	//	-------------------------服务类-------------------------------		
	private static String sqlCbFwl = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imdg.m10317 hyfcb,	" +	//今年累计会议费收入销售成本
	"	       imdg.m10291 wyfcb,	" +	//今年累计物业费收入销售成本
	"	       imdg.m10265 lwfcb,	" +	//今年累计劳务收入销售成本
	"	       imdg.m10239 hmcb,	" +	//今年累计花苗收入销售成本
	"	       imdg.m10213 zscb,	" +	//今年累计住宿收入销售成本
	"	       imdg.m10187 jpdlcb,	" +	//今年累计机票代理收入销售成本
	"	       imdg.m10161 rybhcb,	" +	//今年累计日用百货收入销售成本
	"	       imdg.m10135 dfcb,	" +	//今年累计电费收入销售成本
	"	       imdg.m10109 sqncb,	" +	//今年累计水汽暖收入销售成本
	"	       imdg.m10083 cycb,	" +	//今年累计餐饮收入销售成本
	"	       imdg.m10057 qtcb,	" +	//今年累计其他收入销售成本
	"	       imdg.m10031 xjcb		" +	//今年累计小计销售成本
	"	  from iufo_measure_data_gyin4hlu imdg	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imdg.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
	
		//------------------------变压器产业（按电压等级分类）-------------------------------		
	private static String sqlSrByqcyAdydjfl = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imd7.m10100 jlbyqsr,	" +	//今年累计交流变压器销售收入
	"	       imd7.m10074 jl35sr,	" +	//今年累计其中：35KV及以下销售收入
	"	       imd7.m10048 jl66110lsr,	" +	//今年累计66-110KV销售收入
	"	       imd7.m10022 jl220sr,	" +	//今年累计220KV销售收入
	"	       imdu.m10351 jl330sr,	" +	//今年累计330KV销售收入
	"	       imdu.m10325 jl500sr,	" +	//今年累计500KV销售收入
	"	       imdu.m10299 jl750sr,	" +	//今年累计750kV销售收入
	"	       imdu.m10273 jl1000sr,	" +	//今年累计1000kV销售收入
	"	       imdu.m10247 zlbyqsr,	" +	//今年累计直流变压器销售收入
	"	       imdu.m10221 zl400sr,	" +	//今年累计今年累计其中：±400kv及以下销售收入
	"	       imdu.m10195 zl500sr,	" +	//今年累计±500kv销售收入
	"	       imdu.m10169 zl600sr,	" +	//今年累计±600kv销售收入
	"	       imdu.m10143 zl800sr,	" +	//今年累计±800kv销售收入
	"	       imdu.m10117 zlpbdkqsr,	" +	//今年累计平波电抗器销售收入
	"	       imdu.m10091 dkqsr,	" +	//今年累计电抗器销售收入
	"	       imdu.m10065 dkq330sr,	" +	//今年累计其中：330kV及以下销售收入
	"	       imdu.m10039 dkq500sr,	" +	//今年累计500kV电销售收入
	"	       imdu.m10013 dkq750sr,	" +	//今年累计750kV电销售收入
	"	       imdz.m10342 dkq1000sr,	" +	//今年累计1000kV电销售收入
	"	       imdz.m10316 dydjljsr	" +	//今年累计小计销售收入(按电压等级分类)
	"	  from iufo_measure_data_7zz4hjkz imd7	" +	
	"	  left join iufo_measure_data_ukdj7hhy imdu	" +	
	"	    on imd7.alone_id = imdu.alone_id	" +	
	"	  left join iufo_measure_data_zwrb9cyz imdz	" +	
	"	    on imd7.alone_id = imdz.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imd7.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
		 		
		 		
		//------------------------变压器产业（按产品类型分类）-------------------------------		
	private static String sqlSrByqcyAcplxfl = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imdz.m10250 gsbyqsr,	" +	//今年累计干式变压器销售收入
	"	       imdz.m10224 gsfjsr,	" +	//今年累计其中：F级干变销售收入
	"	       imdz.m10198 gshjsr,	" +	//今年累计H级干变销售收入
	"	       imdz.m10172 gsxsbdsr,	" +	//今年累计箱式变电站销售收入
	"	       imdz.m10146 gsdkqsr,	" +	//今年累计干式电抗器销售收入
	"	       imdz.m10120 tzbyqsr,	" +	//今年累计特种变压器销售收入
	"	       imdz.m10094 tzgbsr,	" +	//今年累计其中：隔爆变销售收入
	"	       imdz.m10068 tzzlsr,	" +	//今年累计整流变销售收入
	"	       imdz.m10042 tzqysr,	" +	//今年累计牵引变销售收入
	"	       imdz.m10016 tzytsr,	" +	//今年累计油田变销售收入
	"	       imdq.m10345 tzqtsr,	" +	//今年累计其它销售收入
	"	       imdq.m10319 yslsr,	" +	//今年累计延伸类销售收入
	"	       imdq.m10293 yslpdzdhsr,	" +	//今年累计配电自动化销售收入
	"	       imdq.m10267 yslkgsr,	" +	//今年累计开关销售收入
	"	       imdq.m10241 ysltg,	" +	//今年累计套管销售收入
	"	       imdq.m10215 yslhgqsr,	" +	//今年累计互感器销售收入
	"	       imdq.m10189 yslwxbjsr,	" +	//今年累计维修备件销售收入
	"	       imdq.m10163 cplxflljsr	" +	//今年累计小计销售收入(按产品类型分类)
	"	  from iufo_measure_data_qp1i8fs7 imdq	" +	
	"	  left join iufo_measure_data_zwrb9cyz imdz	" +	
	"	    on imdq.alone_id = imdz.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imdq.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
		 		
		 		
	//	--------------------------线缆产业-------------------------------		
	private static String sqlSrXlcy = 
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imdq.m10065 dxsr,	" +	//今年累计导线销售收入
	"	       imdq.m10039 bdxsr,	" +	//今年累计布电线销售收入
	"	       imdq.m10013 jkxsr,	" +	//今年累计架空线销售收入
	"	       imdl.m10342 kzdlsr,	" +	//今年累计控制电缆销售收入
	"	       imdl.m10316 jldlsr,	" +	//今年累计交联电缆销售收入
	"	       imdl.m10290 dldlsr,	" +	//今年累计电力电缆销售收入
	"	       imdl.m10264 dcxsr,	" +	//今年累计电磁线销售收入
	"	       imdl.m10238 tzdlsr,	" +	//今年累计特种电缆销售收入
	"	       imdl.m10212 dlfjsr,	" +	//今年累计电缆附件销售收入
	"	       imdl.m10186 tgsr,	" +	//今年累计铜杆销售收入
	"	       imdl.m10160 lgsr,	" +	//今年累计铝杆销售收入
	"	       imdl.m10134 pvclsr,	" +	//今年累计PVC料销售收入
	"	       imdl.m10108 gzlsr,	" +	//今年累计工装轮销售收入
	"	       imdl.m10082 xlxjsr	" +	//今年累计小计销售收入(线缆)
	"	  from iufo_measure_data_qp1i8fs7 imdq	" +	
	"	  left join iufo_measure_data_l1hnj73b imdl	" +	
	"	    on imdq.alone_id = imdl.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imdq.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
				
	//	-------------------------物流贸易类---------------------		
	private static String sqlSrWlmyl = 
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imd5.m10015 ,	" +	//今年累计物流贸易销售收入
	"	       imdg.m10344	" +	//今年累计小计销售收入
	"	  from iufo_measure_data_5a801obu imd5	" +	
	"	  left join iufo_measure_data_gyin4hlu imdg	" +	
	"	    on imd5.alone_id = imdg.alone_id	" +	
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
	"	 where imp.ver = 0	";	
				
	//	-------------------------服务类-------------------------------		
	private static String sqlSrFwl = 
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imdg.m10318 hyfsr,	" +	//今年累计会议费收入销售收入
	"	       imdg.m10292 wyfsr,	" +	//今年累计物业费收入销售收入
	"	       imdg.m10266 lwfsr,	" +	//今年累计劳务收入销售收入
	"	       imdg.m10240 hmsr,	" +	//今年累计花苗收入销售收入
	"	       imdg.m10214 zssr,	" +	//今年累计住宿收入销售收入
	"	       imdg.m10188 jpdlsr,	" +	//今年累计机票代理收入销售收入
	"	       imdg.m10162 rybhsr,	" +	//今年累计日用百货收入销售收入
	"	       imdg.m10136 dfsr,	" +	//今年累计电费收入销售收入
	"	       imdg.m10110 sqnsr,	" +	//今年累计水汽暖收入销售收入
	"	       imdg.m10084 cysr,	" +	//今年累计餐饮收入销售收入
	"	       imdg.m10058 qtsr,	" +	//今年累计其他收入销售收入
	"	       imdg.m10032 xjsr	" +	//今年累计小计销售收入
	"	  from iufo_measure_data_gyin4hlu imdg	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imdg.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
				
	//	-----------------------工程类收入20160422------------------------------------		
	private static String sqlSrGcl = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imdo.m10123 gnsr,	" +	//今年累计国内工程销售收入
	"	       imd0.m10304 gnsbdsr,	" +	//今年累计其中：输变电工程销售收入
	"	       imdo.m10102 gnsbdepcsr,	" +	//今年累计输变电-EPC模式销售收入
	"	       imdo.m10076 gnsbdbtsr,	" +	//今年累计输变电-BT模式销售收入
	"	       imdo.m10050 gnsbdqtsr,	" +	//今年累计输变电-其他模式销售收入
	"	       imd0.m10278 gngfsr,	" +	//今今年累计光伏工程销售收入
	"	       imdo.m10024 gngfepcsr,	" +	//今年累计光伏-EPC模式销售收入
	"	       imdz.m10353 gngfbtsr,	" +	//今年累计光伏-BT模式销售收入
	"	       imdz.m10327 gngfqtsr,	" +	//今年累计光伏-其他模式销售收入
	"	       imd0.m10252 gnfdsr,	" +	//今年累计风电工程销售收入
	"	       imdz.m10301 gnfdepcsr,	" +	//今年累计风电-EPC模式销售收入
	"	       imdz.m10275 gnfdbtsr,	" +	//今年累计风电-BT模式销售收入
	"	       imdz.m10249 gnfdqtsr,	" +	//今年累计风电-其他模式销售收入
	"	       imdz.m10235 gjsr,	" +	//今年累计国际工程销售收入
	"	       imd0.m10206 gjsbdsr,	" +	//今年累计其中：输变电工程销售收入
	"	       imdz.m10214 gjsbdepcsr,	" +	//今年累计输变电-EPC模式销售收入
	"	       imdz.m10188 gjsbdbtsr,	" +	//今年累计输变电-BT模式销售收入
	"	       imdz.m10162 gjsbdqtsr,	" +	//今年累计输变电-其他模式销售收入
	"	       imd0.m10180 gjgfsr,	" +	//今年累计今年累计 光伏工程销售收入
	"	       imdz.m10136 gjgfepcsr,	" +	//今年累计光伏-EPC模式销售收入
	"	       imdz.m10110 gjgfbtsr,	" +	//今年累计光伏-BT模式销售收入
	"	       imdz.m10084 gjgfqtsr,	" +	//今年累计光伏-其他模式销售收入
	"	       imd0.m10154 gjfdsr,	" +	//今年累计 风电工程销售收入
	"	       imdz.m10058 gjfdepcsr,	" +	//今年累计风电-EPC模式销售收入
	"	       imdz.m10032 gjfdbtsr,	" +	//今年累计风电-BT模式销售收入
	"	       imdz.m10006 gjfdqtsr,	" +	//今年累计风电-其他模式销售收入
	"	       imd0.m10128 xjsr	" +	//今年累计小计销售收入
	"	  from iufo_measure_data_oxpoeewv imdo	" +	
	"	  left join iufo_measure_data_0lmfwcux imd0	" +	
	"	    on imdo.alone_id = imd0.alone_id	" +	
	"	  left join iufo_measure_data_za2q47m4 imdz	" +	
	"	    on imdo.alone_id = imdz.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imdo.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
	
	//	//---------------------工程类成本20160422------------------------------------		
	private static String sqlCbGcl = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imdo.m10122 gncb,	" +	//今年累计国内工程销售成本
	"	       imd0.m10303 gnsbdcb,	" +	//今年累计其中：输变电工程销售成本
	"	       imdo.m10101 gnsbdepccb,	" +	//今年累计输变电-EPC模式销售成本
	"	       imdo.m10075 gnsbdbtcb,	" +	//今年累计输变电-BT模式销售成本
	"	       imdo.m10049 gnsbdqtcb,	" +	//今年累计输变电-其他模式销售成本
	"	       imd0.m10277 gngfcb,	" +	//今年累计光伏工程销售成本
	"	       imdo.m10023 gngfepccb,	" +	//今年累计光伏-EPC模式销售成本
	"	       imdz.m10352 gngfbtcb,	" +	//今年累计光伏-BT模式销售成本
	"	       imdz.m10326 gngfqtcb,	" +	//今年累计光伏-其他模式销售成本
	"	       imd0.m10251 gnfdcb,	" +	//今年累计风电工程销售成本
	"	       imdz.m10300 gnfdepccb,	" +	//今年累计风电-EPC模式销售成本
	"	       imdz.m10274 gnfdbtcb,	" +	//今年累计风电-BT模式销售成本
	"	       imdz.m10248 gnfdqtcb,	" +	//今年累计风电-其他模式销售成本
	"	       imdz.m10234 gjcb,	" +	//今年累计国际工程销售成本
	"	       imd0.m10205 gjsbdcb,	" +	//今年累计其中：输变电工程销售成本
	"	       imdz.m10213 gjsbdepccb,	" +	//今年累计输变电-EPC模式销售成本
	"	       imdz.m10187 gjsbdbtcb,	" +	//今年累计输变电-BT模式销售成本
	"	       imdz.m10161 gjsbdqtcb,	" +	//今年累计输变电-其他模式销售成本
	"	       imd0.m10179 gjgfcb,	" +	//今年累计光伏工程销售成本
	"	       imdz.m10135 gjgfepccb,	" +	//今年累计光伏-EPC模式销售成本
	"	       imdz.m10109 gjgfbtcb,	" +	//今年累计光伏-BT模式销售成本
	"	       imdz.m10083 gjgfqtcb,	" +	//今年累计光伏-其他模式销售成本
	"	       imd0.m10153 gjfdcb,	" +	//今年累计风电工程销售成本
	"	       imdz.m10057 gjfdepccb,	" +	//今年累计风电-EPC模式销售成本
	"	       imdz.m10031 gjfdbtcb,	" +	//今年累计风电-BT模式销售成本
	"	       imdz.m10005 gjfdqtcb,	" +	//今年累计风电-其他模式销售成本
	"	       imd0.m10127 xjcb	" +	//今年累计小计销售成本(工程类)
	"	  from iufo_measure_data_oxpoeewv imdo	" +	
	"	  left join iufo_measure_data_0lmfwcux imd0	" +	
	"	    on imdo.alone_id = imd0.alone_id	" +	
	"	  left join iufo_measure_data_za2q47m4 imdz	" +	
	"	    on imdo.alone_id = imdz.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imdo.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
				
		//---------------------新能源收入20160422------------------------------------		
	private static String sqlSrXny = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imdl.m10056 danjgpsr,	" +	//今年累计单晶硅片销售收入
	"	       imdl.m10030 duojgpsr,	" +	//今年累计多晶硅片销售收入
	"	       imdl.m10004 zfbsr,	" +	//今年累计准方棒销售收入
	"	       imdk.m10333 djdsr,	" +	//今年累计多晶锭销售收入
	"	       imdk.m10307 ybsr,	" +	//今年累计圆棒销售收入
	"	       imdk.m10281 bwdbqsr,	" +	//今年累计并网逆变器销售收入
	"	       imdk.m10255 hlxsr,	" +	//今年累计汇流箱销售收入
	"	       imdk.m10229 pdgsr,	" +	//今年累计配电柜销售收入
	"	       imdk.m10203 ythjfsr,	" +	//今年累计一体化机房销售收入
	"	       imdk.m10177 cdzcpsr,	" +	//今年累计充电桩产品销售收入
	"	       imdk.m10151 svgsr,	" +	//今年累计SVG销售收入
	"	       imdk.m10125 svgbcpsr,	" +	//今年累计SVG半成品销售收入
	"	       imdk.m10099 djgsr,	" +	//今年累计多晶硅销售收入
	"	       imdk.m10073 bthsr,	" +	//今年累计白炭黑销售收入
	"	       imdk.m10047 jqksr,	" +	//今年累计加气块销售收入
	"	       imdk.m10021 qtsr,	" +	//今年累计其他销售收入
	"	       imd0.m10350 xjsr	" +	//今年累计小计销售收入
	"	  from iufo_measure_data_l1hnj73b imdl	" +	
	"	  left join iufo_measure_data_kuxp4aaf imdk	" +	
	"	    on imdl.alone_id = imdk.alone_id	" +	
	"	  left join iufo_measure_data_0lmfwcux imd0	" +	
	"	    on imdl.alone_id = imd0.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imdl.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
				
		//---------------------新能源成本20160422------------------------------------		
	private static String sqlCbXny = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imdl.m10055 danjgpcb,	" +	//今年累计单晶硅片销售成本
	"	       imdl.m10029 duojgpcb,	" +	//今年累计多晶硅片销售成本
	"	       imdl.m10003 zfbcb,	" +	//今年累计准方棒销售成本
	"	       imdk.m10332 djdcb,	" +	//今年累计多晶锭销售成本
	"	       imdk.m10306 ybcb,	" +	//今年累计圆棒销售成本
	"	       imdk.m10280 bwdbqcb,	" +	//今年累计并网逆变器销售成本
	"	       imdk.m10254 hlxcb,	" +	//今年累计汇流箱销售成本
	"	       imdk.m10228 pdgcb,	" +	//今年累计配电柜销售成本
	"	       imdk.m10202 ythjfcb,	" +	//今年累计一体化机房销售成本
	"	       imdk.m10176 cdzcpcb,	" +	//今年累计充电桩产品销售成本
	"	       imdk.m10150 svgcb,	" +	//今年累计SVG销售成本
	"	       imdk.m10124 svgbcpcb,	" +	//今年累计SVG半成本品销售成本
	"	       imdk.m10098 djgcb,	" +	//今年累计多晶硅销售成本
	"	       imdk.m10072 bthcb,	" +	//今年累计白炭黑销售成本
	"	       imdk.m10046 jqkcb,	" +	//今年累计加气块销售成本
	"	       imdk.m10020 qtcb,	" +	//今年累计其他销售成本
	"	       imd0.m10349 xjcb	" +	//今年累计小计销售成本
	"	  from iufo_measure_data_l1hnj73b imdl	" +	
	"	  left join iufo_measure_data_kuxp4aaf imdk	" +	
	"	    on imdl.alone_id = imdk.alone_id	" +	
	"	  left join iufo_measure_data_0lmfwcux imd0	" +	
	"	    on imdl.alone_id = imd0.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imdl.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
				
		//---------------------煤炭产业收入20160422------------------------------------		
	private static String sqlSrMtcy = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imd5.m10249 dksr,	" +	//今年累计大块销售收入
	"	       imd5.m10223 zksr,	" +	//今年累计中块销售收入
	"	       imd5.m10197 xzksr,	" +	//今年累计小中块销售收入
	"	       imd5.m10171 sbksr,	" +	//今年累计三八块销售收入
	"	       imd5.m10145 ewksr,	" +	//今年累计二五块销售收入
	"	       imd5.m10119 slksr,	" +	//今年累计四六块销售收入
	"	       imd5.m10093 jcmsr,	" +	//今年累计锯采煤销售收入
	"	       imd5.m10067 mmsr,	" +	//今年累计末煤销售收入
	"	       imd5.m10041 xjsr	" +	//今年累计小计销售收入
	"	  from iufo_measure_data_5a801obu imd5	" +	
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
	"	 where imp.ver = 0	";
				
		//---------------------煤炭产业成本20160422------------------------------------		
	private static String sqlCbMtcy = 			
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imd5.m10248 dkcb,	" +	//今年累计大块销售成本
	"	       imd5.m10222 zkcb,	" +	//今年累计中块销售成本
	"	       imd5.m10196 xzkcb,	" +	//今年累计小中块销售成本
	"	       imd5.m10170 sbkcb,	" +	//今年累计三八块销售成本
	"	       imd5.m10144 ewkcb,	" +	//今年累计二五块销售成本
	"	       imd5.m10118 slkcb,	" +	//今年累计四六块销售成本
	"	       imd5.m10092 jcmcb,	" +	//今年累计锯采煤销售成本
	"	       imd5.m10066 mmcb,	" +	//今年累计末煤销售成本
	"	       imd5.m10040 xjcb	" +	//今年累计小计销售成本
	"	  from iufo_measure_data_5a801obu imd5	" +	
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
	"	 where imp.ver = 0	";	
				
	//	-----------------------运营商类收入20160422------------------------------------		
	private static String sqlSrYysl = 
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imd0.m10102 zbdcsr,	" +	//今年累计自备电厂销售收入
	"	       imd0.m10076 hddcsr,	" +	//今年累计火电电厂销售收入
	"	       imd0.m10050 gfdcsr,	" +	//今年累计光伏电厂销售收入
	"	       imd0.m10024 fndcsr,	" +	//今年累计风能电厂销售收入
	"	       imd5.m10353 cdzsr,	" +	//今年累计充电站销售收入
	"	       imd5.m10327 sdgssr,	" +	//今年累计售电公司销售收入
	"	       imd5.m10301 grgssr,	" +	//今年累计供热公司销售收入
	"	       imd5.m10275 xjsr	" +	//今年累计小计销售收入
	"	  from iufo_measure_data_0lmfwcux imd0	" +	
	"	  left join iufo_measure_data_5a801obu imd5	" +	
	"	    on imd0.alone_id = imd5.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imd0.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	
				
	//	-----------------------运营商类成本20160422------------------------------------		
	private static String sqlCbYysl = 
	"	select unit_code,	" +	
	"	       unit_name,	" +	
	"	       inputdate,	" +	
	"	       imd0.m10101 zbdccb,	" +	//今年累计自备电厂销售成本
	"	       imd0.m10075 hddccb,	" +	//今年累计火电电厂销售成本
	"	       imd0.m10049 gfdccb,	" +	//今年累计光伏电厂销售成本
	"	       imd0.m10023 fndccb,	" +	//今年累计风能电厂销售成本
	"	       imd5.m10352 cdzcb,	" +	//今年累计充电站销售成本
	"	       imd5.m10326 sdgscb,	" +	//今年累计售电公司销售成本
	"	       imd5.m10300 grgscb,	" +	//今年累计供热公司销售成本
	"	       imd5.m10274 xjcb	" +	//今年累计小计销售成本
	"	  from iufo_measure_data_0lmfwcux imd0	" +	
	"	  left join iufo_measure_data_5a801obu imd5	" +	
	"	    on imd0.alone_id = imd5.alone_id	" +	
	"	  left join (select alone_id,	" +	
	"	                    code,	" +	
	"	                    inputdate,	" +	
	"	                    keyword2,	" +	
	"	                    keyword3,	" +	
	"	                    time_code,	" +	
	"	                    ts,	" +	
	"	                    ver	" +	
	"	               from iufo_measure_pubdata) imp	" +	
	"	    on imd0.alone_id = imp.alone_id	" +	
	"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
	"	    on imp.code = iui.unit_id	" +	
	"	 where imp.ver = 0	";	


	@Override
	public void importFromNC(Date d) {
		NCConnection connection = NCConnection.create();
		if (null != connection){
			EasyCalendar cal = new EasyCalendar(d);
			String whereSql = 
					" and extract(year from to_date(inputdate,'yyyy-mm-dd')) =" + cal.getYear() + 
					" and extract(month from to_date(inputdate,'yyyy-mm-dd')) =" + cal.getMonth();

			List<CpflEntity> entities = cpflDao.getAll();
			Logger logger = LoggerFactory.getLogger("LOG-NC");
			logger.info("财务产品大类毛利 sqlCbByqcyAdydjfl");
			ResultSet rsCb = connection.query(sqlCbByqcyAdydjfl + whereSql);
			logger.info("财务产品大类毛利 sqlSrByqcyAdydjfl");
			ResultSet rsSr = connection.query(sqlSrByqcyAdydjfl + whereSql);
			
			
			CpdlmlEntity hjEntity = cpdlmlDao.getByDate(d, entities.get(entities.size() - 1).getId());
			if (hjEntity == null){
				hjEntity = new CpdlmlEntity();
				hjEntity.setCpdl(entities.get(entities.size() - 1).getId());
				hjEntity.setNf(cal.getYear());
				hjEntity.setYf(cal.getMonth());
			}
			
			
			mergerEntity(hjEntity, cal, rsCb, rsSr, entities);
			logger.info("财务产品大类毛利  sqlCbByqcyAcplxfl");
			rsCb = connection.query(sqlCbByqcyAcplxfl + whereSql);
			logger.info("财务产品大类毛利  sqlSrByqcyAcplxfl");
			rsSr = connection.query(sqlSrByqcyAcplxfl + whereSql);
			mergerEntity(hjEntity, cal, rsCb, rsSr, entities);
			
			logger.info("财务产品大类毛利  sqlCbXlcy");
			rsCb = connection.query(sqlCbXlcy + whereSql);
			logger.info("财务产品大类毛利  sqlSrXlcy");
			rsSr = connection.query(sqlSrXlcy + whereSql);
			mergerEntity(hjEntity, cal, rsCb, rsSr, entities);
			
			logger.info("财务产品大类毛利  sqlCbXny");
			rsCb = connection.query(sqlCbXny + whereSql);
			logger.info("财务产品大类毛利  sqlSrXny");
			rsSr = connection.query(sqlSrXny + whereSql);
			mergerEntity(hjEntity, cal, rsCb, rsSr, entities);
			
			logger.info("财务产品大类毛利  sqlCbGcl");
			rsCb = connection.query(sqlCbGcl + whereSql);
			logger.info("财务产品大类毛利  sqlSrGcl");
			rsSr = connection.query(sqlSrGcl + whereSql);
			mergerEntity(hjEntity, cal, rsCb, rsSr, entities);
			
			logger.info("财务产品大类毛利  sqlCbYysl");
			rsCb = connection.query(sqlCbYysl + whereSql);
			logger.info("财务产品大类毛利  sqlSrYysl");
			rsSr = connection.query(sqlSrYysl + whereSql);
			mergerEntity(hjEntity, cal, rsCb, rsSr, entities);
			
			logger.info("财务产品大类毛利  sqlCbMtcy");
			rsCb = connection.query(sqlCbMtcy + whereSql);
			logger.info("财务产品大类毛利  sqlSrMtcy");
			rsSr = connection.query(sqlSrMtcy + whereSql);
			mergerEntity(hjEntity, cal, rsCb, rsSr, entities);
			
			logger.info("财务产品大类毛利  sqlCbWlmyl");
			rsCb = connection.query(sqlCbWlmyl + whereSql);
			logger.info("财务产品大类毛利  sqlSrWlmyl");
			rsSr = connection.query(sqlSrWlmyl + whereSql);
			mergerEntity(hjEntity, cal, rsCb, rsSr, entities);
			
			cpdlmlDao.merge(hjEntity);
		}
	}


	private void mergerEntity(CpdlmlEntity hjEntity, EasyCalendar cal, ResultSet rsCb,
			ResultSet rsSr, List<CpflEntity> entities) {
		Date d = cal.getDate();
		try {
			boolean hasCb = rsCb.next(); 
			boolean hasSr = rsSr.next();
			while (hasCb || hasSr){
				CpdlmlEntity entity = null;
				for (int i = 4; i <= rsCb.getMetaData().getColumnCount(); ++i){
					entity = cpdlmlDao.getByDate(d, entities.get(i - 4).getId());
					if (null == entity){
						entity = new CpdlmlEntity();
						entity.setCpdl(entities.get(i - 4).getId());
						entity.setNf(cal.getYear());
						entity.setYf(cal.getMonth());
					}
					entity.setLjcb(hasCb ? rsCb.getDouble(i) : null);
					entity.setLjsr(hasSr ? rsSr.getDouble(i) : null);
					cpdlmlDao.merge(entity);
				}
				
				hjEntity.setLjcb(MathUtil.sum(hjEntity.getLjcb(), entity.getLjcb()));
				hjEntity.setLjsr(MathUtil.sum(hjEntity.getLjsr(), entity.getLjsr()));
				hasCb = rsCb.next(); 
				hasSr = rsSr.next();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





}
