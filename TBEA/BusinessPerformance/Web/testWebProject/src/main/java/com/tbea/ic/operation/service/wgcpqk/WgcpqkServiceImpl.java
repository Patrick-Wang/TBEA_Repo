package com.tbea.ic.operation.service.wgcpqk;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Pair;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.wgcpqk.WgcpqkType;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.wgcpqk.wgcpylnlspcs.WgcpylnlspcsDao;
import com.tbea.ic.operation.model.dao.wgcpqk.wgcpylnlspcs.WgcpylnlspcsDaoImpl;
import com.tbea.ic.operation.model.entity.wgcpqk.wgcpylnlspcs.WgcpylnlspcsEntity;
import com.tbea.ic.operation.service.util.nc.NCCompanyCode;
import com.tbea.ic.operation.service.util.nc.NCConnection;
import com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs.WgcpylnlspcsService;

@Service(WgcpqkServiceImpl.NAME)
@Transactional("transactionManager")
public class WgcpqkServiceImpl implements WgcpqkService {
	@Resource(name = WgcpylnlspcsDaoImpl.NAME)
	WgcpylnlspcsDao wgcpylnlspcsDao;
		
	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	@Autowired
	WgcpylnlspcsService wgcpylnlspcsService;
	
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	public final static String NAME = "WgcpqkServiceImpl";

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
	
	//---------------------工程类成本20160422------------------------------------		
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


	@Override
	public void importByqFromNC(Date d, List<Company> comps) {
		NCConnection connection = NCConnection.create();
		if (null != connection){
			EasyCalendar cal = new EasyCalendar(d);
			String whereSql = 
					" and extract(year from to_date(inputdate,'yyyy-mm-dd')) =" + cal.getYear() + 
					" and extract(month from to_date(inputdate,'yyyy-mm-dd')) =" + cal.getMonth() +
					" and unit_code in (" + StringUtils.join(NCCompanyCode.toCodeList(comps).toArray(), ",") + ")";
			
			Logger logger = Logger.getLogger("LOG-NC");
//			logger.debug("完工成品情况  sqlCbByqcyAdydjfl");
//			ResultSet rsCb = connection.query(sqlCbByqcyAdydjfl + whereSql);
//			logger.debug("完工成品情况  sqlSrByqcyAdydjfl");
//			ResultSet rsSr = connection.query(sqlSrByqcyAdydjfl + whereSql);
//			List<Integer> cpList = wgcpylnlspcsService.getCpIdList(WgcpqkType.YLFX_WGCPYLNL_BYQ_DYDJ);
//			mergeResultSets(
//					d, 
//					rsCb, 
//					rsSr, 
//					WgcpqkType.YLFX_WGCPYLNL_BYQ_DYDJ, 
//					cpList, 
//					0, 
//					4, 
//					cpList.size());
//			logger.debug("完工成品情况  sqlCbByqcyAcplxfl");
//			rsCb = connection.query(sqlCbByqcyAcplxfl + whereSql);
//			logger.debug("完工成品情况  sqlSrByqcyAcplxfl");
//			rsSr = connection.query(sqlSrByqcyAcplxfl + whereSql);
//			cpList = wgcpylnlspcsService.getCpIdList(WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL);
//			mergeResultSets(
//					d, 
//					rsCb, 
//					rsSr, 
//					WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL, 
//					cpList, 
//					0, 
//					4, 
//					cpList.size());
			logger.debug("完工成品情况  sqlCbGcl");
			ResultSet rsCb = connection.query(sqlCbGcl + whereSql);
			logger.debug("完工成品情况  sqlSrGcl");
			ResultSet rsSr = connection.query(sqlSrGcl + whereSql);
			List<Integer> cpList = wgcpylnlspcsService.getCpIdList(WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH);
			mergeResultSets(
					d, 
					rsCb, 
					rsSr, 
					WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH, 
					cpList, 
					0, 
					5, 
					4);
			
			try {
				rsCb.beforeFirst();
				rsSr.beforeFirst();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mergeResultSets(
					d, 
					rsCb, 
					rsSr, 
					WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH, 
					cpList, 
					4, 
					18, 
					4);
			logger.debug("完工成品情况  sqlCbWlmyl");
			rsCb = connection.query(sqlCbWlmyl + whereSql);
			logger.debug("完工成品情况  sqlSrWlmyl");
			rsSr = connection.query(sqlSrWlmyl + whereSql);
			mergeResultSets(
					d, 
					rsCb, 
					rsSr, 
					WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH, 
					cpList, 
					8, 
					4, 
					1);
			logger.debug("完工成品情况  sqlCbFwl");
			rsCb = connection.query(sqlCbFwl + whereSql);
			logger.debug("完工成品情况  sqlSrFwl");
			rsSr = connection.query(sqlSrFwl + whereSql);
			mergeResultSets(
					d, 
					rsCb, 
					rsSr, 
					WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH, 
					cpList, 
					9, 
					cpList.size() - 9 - 1 + 4, 
					1);
			try {
				rsCb.beforeFirst();
				rsSr.beforeFirst();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mergeResultSets(
					d, 
					rsCb, 
					rsSr, 
					WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH, 
					cpList, 
					10, 
					4, 
					cpList.size() - 9 - 1 - 1);
		}
	}

	private void mergeResultSets(
			Date d, 
			ResultSet rsCb, 
			ResultSet rsSr, 
			WgcpqkType type, 
			List<Integer> cpIds, 
			int cpflStart, 
			int rsStart, 
			int size) {
		Map<Company, Pair<List<Double>, List<Double>>> compCbSrMap = new HashMap<Company, Pair<List<Double>, List<Double>>>();
		try {
			while (rsCb.next()){
				String unitCode = String.valueOf(rsCb.getObject(1));
				CompanyType companyType = NCCompanyCode.getType(unitCode);
				Company comp = companyManager.getBMDBOrganization().getCompany(companyType);
				Pair<List<Double>, List<Double>> cbsr = compCbSrMap.get(comp);
				if (cbsr == null){
					cbsr = new Pair<List<Double>, List<Double>>(new ArrayList<Double>(), new ArrayList<Double>());
					compCbSrMap.put(comp, cbsr);
				}
				
				for (int i = cpflStart; i < size; ++i){
					cbsr.getFirst().add(rsCb.getDouble(i + rsStart));
				}				
			}
			
			while (rsSr.next()){
				String unitCode = String.valueOf(rsCb.getObject(1));
				CompanyType companyType = NCCompanyCode.getType(unitCode);
				Company comp = companyManager.getBMDBOrganization().getCompany(companyType);
				Pair<List<Double>, List<Double>> cbsr = compCbSrMap.get(comp);
				if (cbsr == null){
					cbsr = new Pair<List<Double>, List<Double>>(new ArrayList<Double>(), new ArrayList<Double>());
					compCbSrMap.put(comp, cbsr);
				}
				
				for (int i = cpflStart; i < size; ++i){
					cbsr.getSecond().add(rsSr.getDouble(i + rsStart));
				}				
			}
			for (Company comp : compCbSrMap.keySet()){
				for (int i = cpflStart; i < size; ++i){
					mergeEntity(d, 
							comp, 
							type, 
							cpIds.get(i), 
							compCbSrMap.get(comp).getFirst().get(i - cpflStart), 
							compCbSrMap.get(comp).getSecond().get(i - cpflStart));
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void mergeEntity(Date d, Company company, WgcpqkType type, Integer cpId, Double cb, Double sr){
		WgcpylnlspcsEntity entity = wgcpylnlspcsDao.getByDate(d, company, type, cpId);
		if (entity == null){
			entity = new WgcpylnlspcsEntity();
			entity.setDwxx(dwxxDao.getById(company.getId()));
			EasyCalendar ec = new EasyCalendar(d);
			entity.setNf(ec.getYear());
			entity.setYf(ec.getMonth());
			entity.setCpmc(cpmcDao.getById(cpId));
			entity.setZt(ZBStatus.SUBMITTED.ordinal());
			entity.setTjfs(type.value());
		}
		entity.setSr(MathUtil.division(sr, 10000d));
		entity.setCb(MathUtil.division(cb, 10000d));
		wgcpylnlspcsDao.merge(entity);
	}
	
	@Override
	public void importXlFromNC(Date d, List<Company> comps) {
		NCConnection connection = NCConnection.create();
		if (null != connection){
			EasyCalendar cal = new EasyCalendar(d);
			String whereSql = 
					" and extract(year from to_date(inputdate,'yyyy-mm-dd')) =" + cal.getYear() + 
					" and extract(month from to_date(inputdate,'yyyy-mm-dd')) =" + cal.getMonth() +
					" and unit_code in (" + StringUtils.join(NCCompanyCode.toCodeList(comps).toArray(), ",") + ")"; 

			Logger logger = Logger.getLogger("LOG-NC");
			logger.debug("完工成品情况  sqlCbXlcy");
			ResultSet rsCb = connection.query(sqlCbXlcy + whereSql);
			logger.debug("完工成品情况  sqlCbXlcy");
			ResultSet rsSr = connection.query(sqlSrXlcy + whereSql);
			List<Integer> cpList = wgcpylnlspcsService.getCpIdList(WgcpqkType.YLFX_WGCPYLNL_XL_CPFL);
			mergeResultSets(
					d, 
					rsCb, 
					rsSr, 
					WgcpqkType.YLFX_WGCPYLNL_XL_CPFL, 
					cpList, 
					0, 
					4, 
					cpList.size());
			logger.debug("完工成品情况  sqlCbGcl");
			rsCb = connection.query(sqlCbGcl + whereSql);
			logger.debug("完工成品情况  sqlSrGcl");
			rsSr = connection.query(sqlSrGcl + whereSql);
			cpList = wgcpylnlspcsService.getCpIdList(WgcpqkType.YLFX_WGCPYLNL_XL_ZH);
			mergeResultSets(
					d, 
					rsCb, 
					rsSr, 
					WgcpqkType.YLFX_WGCPYLNL_XL_ZH, 
					cpList, 
					0, 
					5, 
					4);
			
			try {
				rsCb.beforeFirst();
				rsSr.beforeFirst();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mergeResultSets(
					d, 
					rsCb, 
					rsSr, 
					WgcpqkType.YLFX_WGCPYLNL_XL_ZH, 
					cpList, 
					4, 
					18, 
					4);
			logger.debug("完工成品情况  sqlCbWlmyl");
			rsCb = connection.query(sqlCbWlmyl + whereSql);
			logger.debug("完工成品情况  sqlSrWlmyl");
			rsSr = connection.query(sqlSrWlmyl + whereSql);
			mergeResultSets(
					d, 
					rsCb, 
					rsSr, 
					WgcpqkType.YLFX_WGCPYLNL_XL_ZH, 
					cpList, 
					8, 
					4, 
					1);
			logger.debug("完工成品情况  sqlCbFwl");
			rsCb = connection.query(sqlCbFwl + whereSql);
			logger.debug("完工成品情况  sqlSrFwl");
			rsSr = connection.query(sqlSrFwl + whereSql);
			mergeResultSets(
					d, 
					rsCb, 
					rsSr, 
					WgcpqkType.YLFX_WGCPYLNL_XL_ZH, 
					cpList, 
					9, 
					cpList.size() - 9 - 1 + 4, 
					1);
			try {
				rsCb.beforeFirst();
				rsSr.beforeFirst();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mergeResultSets(
					d, 
					rsCb, 
					rsSr, 
					WgcpqkType.YLFX_WGCPYLNL_XL_ZH, 
					cpList, 
					10, 
					4, 
					cpList.size() - 9 - 1 - 1);
		}
	}




}
