package com.tbea.ic.operation.controller.servlet.nc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.NCZB;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.nc.NCService;

@Controller
@RequestMapping(value = "nc")
public class NCController {

	@Autowired
	EntryService entryService;
	
	@Autowired
	ApproveService approveService;

	@Autowired
	NCService ncService;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	private static List<Integer> zbList = new ArrayList<Integer>();

	static {
		zbList.add(GSZB.LRZE1.value());
		zbList.add(GSZB.XSSR6.value());
		zbList.add(GSZB.JYXJXJL29.value());
		zbList.add(GSZB.YSZK32.value());
		zbList.add(GSZB.CH35.value());
		zbList.add(GSZB.SXFY64.value());
		zbList.add(GSZB.JZCSYL_66.value());
	}
	

	@Scheduled(cron="0 0 0 4-5 * ?")
	public void scheduleImportNC(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		importNC2LocalNC(d);
		importLocalNC2Local(d);
	}
	
	
	private JSONArray createIndicator(String id, String value){
		JSONArray ret = new JSONArray();
		ret.add(0, id);
		ret.add(1, value);
		return ret;
	}
	
	
	private void importData(ZBStatus zbStatus, JSONArray jsonArray, Date date, Company comp){
		
		switch (zbStatus) {
		case APPROVED:
			List<Company> compsTmp = new ArrayList<Company>();
			compsTmp.add(comp);
			approveService.unapproveSjZb(Account.KNOWN_ACCOUNT_GFGS, compsTmp, date);
			entryService.submitZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray);
			approveService.approveSjZb(Account.KNOWN_ACCOUNT_GFGS, compsTmp, date, true);
			break;
		case APPROVED_2: 
			entryService.saveZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray);
			List<Company> compsTmp2 = new ArrayList<Company>();
			compsTmp2.add(comp);
			approveService.approveSjZb(Account.KNOWN_ACCOUNT_JYFZ, compsTmp2, date, true);
			break;
		case NONE:
		case SAVED:
			entryService.saveZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray);
			break;
		case SUBMITTED:
			entryService.submitZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray);
			break;
		case SUBMITTED_2:
			entryService.submitToDeputy(date, null, comp.getType(), ZBType.BYSJ, jsonArray);
			break;
		default:
			break;
		}
	} 
	
	
	private void importNC2LocalNC(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		// 存储NC对应指标    
		// 合并
		List<String> unitList = new ArrayList<String>();
		unitList.add("0202AA000000");
		unitList.add("0303AA000000");
		unitList.add("0304AA000000");
		unitList.add("CC15");
		unitList.add("CC02");
		unitList.add("CC03");
		unitList.add("040203AA0000");
		unitList.add("040202AA0000");
		unitList.add("CC11");
		unitList.add("CC10");
		unitList.add("CC04");
		ncService.connetToNCSystem("510", cal, unitList);
		
		// 单体
		List<String> singleList = new ArrayList<String>();
		singleList.add("060100000000");
		ncService.connetToNCSystem("0", cal, singleList);
	}
	
	private void importLocalNC2Local(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		// Calendar.MONTH获得月份正常情况下为自然月-1,
		// 且当前需求中数据的月份为存储时间的前一个月，所以在下面公式调用中不必+1
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);

		ZBStatus zbStatus = null;
		List<NCZB> NCZBList = ncService.getNCZBByDate(year, month);
		// // 需求中数据的月份为存储时间的前一个月
		// cal.add(Calendar.MONTH, -1);
		Date date = new Date(cal.getTimeInMillis());
		Company comp = null;
		JSONArray jsonArray = null;
		JSONArray zbArray = null;
		int zbid = 0;
		System.out.println("size" + NCZBList.size());
		List<Company> comps = new ArrayList<Company>();
		for (NCZB nczb : NCZBList) {
			zbid = nczb.getZbxx().getId();
			if (zbList.contains(zbid)) {
				comp = companyManager.getBMDBOrganization()
						.getCompany(nczb.getDwxx().getId());
				comps.add(comp);
				zbStatus = entryService.getZbStatus(date, comp.getType(), ZBType.BYSJ)
						.get(0);
				zbArray = createIndicator(String.valueOf(zbid), String.valueOf(nczb.getNczbz()));
				jsonArray = new JSONArray();
				jsonArray.add(zbArray);
				System.out.println("comp: " + comp.getName());
				System.out.println("json: " + jsonArray);
				System.out.println("date: " + date);
				System.out.println("zbStatus: " + zbStatus);
				importData(zbStatus, jsonArray, date, comp);
			}
		}
		
		for (Company comTmp : comps){
			List<String[]> sjzbs = entryService.getZb(date, null, comTmp.getType(), ZBType.BYSJ);
			JSONArray jsImportData = toImportData(sjzbs);
			zbStatus = entryService.getZbStatus(date, comTmp.getType(), ZBType.BYSJ)
					.get(0);
			importData(zbStatus, jsImportData, date, comTmp);
		}
	}
	
	private JSONArray toImportData(List<String[]> sjzbs) {
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < sjzbs.size(); ++i){
			jsonArray.add(createIndicator(sjzbs.get(i)[0], sjzbs.get(i)[2]));
		}
		return jsonArray;
	}

	@RequestMapping(value = "nctest.do", method = RequestMethod.GET)
	public void nctest(HttpServletRequest request,
			HttpServletResponse response) {
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=dm01-scan.tbea.com.cn)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=orcl)))";
		String userName = "iufo";
		String userPwd = "cwf5e7n9";

		Connection dbConn = null;
		try {
			Class.forName(driverName).newInstance();

			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			Statement statement = dbConn
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			String sql = 
					"	select unit_code,	"+
					"	       unit_name,	"+
					"	       inputdate,	"+
					"	       imd9.m10138 xssptglwsd, 	"+
					"	       imd9.m10052 sdsffh, 	"+
					"	       imd5.m10025 sddqtyjyhdygxj, 	"+
					"	       imd9.m10181 fksdxj, 	"+
					"	       imd5.m10008 zfbzsd, 	"+
					"	       imd9.m10242 sdbdwtbbzj, 	"+
					"	       imd9.m10148 sdwdwtbbzj, 	"+
					"	       imd9.m10100 rcywjzthssdxj, 	"+
					"	       imd5.m10053 yhcklxssdxj, 	"+
					"	       imd5.m10025 sddqtyjyhdygxj, 	"+
					"	       imd9.m10142 jyhdxjlr, 	"+
					"	       imd9.m10150 gmspjslwszfxj, 	"+
					"	       imd5.m10062 zfgzxj, 	"+
					"	       imd9.m10309 zfgxsf, 	"+
					"	       imd9.m10210 zfqtjyhdygxj, 	"+
					"	       imd9.m10337 zfbdwtbbzj, 	"+
					"	       imd5.m10040 tfwdwtbbzj, 	"+
					"	       imd9.m10111 dlzxfzfxj, 	"+
					"	       imd9.m10198 zbfwfzfxj, 	"+
					"	       imd9.m10177 rcywjzzfxj, 	"+
					"	       imd5.m10059 yhxgywsxfzfxj, 	"+
					"	       imd5.m10039 qtjyhdygxj, 	"+
					"	       imd5.m10061 jyhdxjlc, 	"+
					"	       imd9.m10029 jyhdcsdxjllje 	"+
					"	  from iufo_measure_data_9hzo24a7 imd9	"+
					"	  left join iufo_measure_data_56m8ewc1 imd5	"+
					"	    on imd9.alone_id = imd5.alone_id	"+
					"	  left join (select alone_id,	"+
					"	                    code,	"+
					"	                    inputdate,	"+
					"	                    keyword2,	"+
					"	                    keyword3,	"+
					"	                    time_code,	"+
					"	                    ts,	"+
					"	                    ver	"+
					"	               from iufo_measure_pubdata) imp	"+
					"	    on imd5.alone_id = imp.alone_id	"+
					"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	"+
					"	    on imp.code = iui.unit_id	"+
					"	 where imp.ver = 0	";

			ResultSet rs = statement.executeQuery(sql);

			String unitCode = null;
			CompanyType companyType = null;
			int nf = 0;
			int yf = 0;
			Double jzcqms = 0.0D;
			Double jzcqcs = 0.0D;
			Double jlr = 0.0D;
			Double jzcsyl = 0.0D;
			while (rs.next()) {
				
			}
			if (null != rs) {
				rs.close();
				rs = null;
			}
			if (null != statement) {
				statement.close();
				statement = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != dbConn) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				dbConn = null;
			}
		}
	}

	@RequestMapping(value = "importNC.do", method = RequestMethod.GET)
	public void importNC(HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
		importNC2LocalNC(d);
		importLocalNC2Local(d);
	}
	
	@RequestMapping(value = "importNC2LocalNC.do", method = RequestMethod.GET)
	public void importNC2LocalNC(HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
		importNC2LocalNC(d);
	}

}
