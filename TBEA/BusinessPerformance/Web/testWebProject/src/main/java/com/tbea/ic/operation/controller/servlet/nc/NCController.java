package com.tbea.ic.operation.controller.servlet.nc;

import java.sql.Date;
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
		zbList.add(GSZB.LRZE.getValue());
		zbList.add(GSZB.XSSR.getValue());
		zbList.add(GSZB.JYXJXJL.getValue());
		zbList.add(GSZB.YSZK.getValue());
		zbList.add(GSZB.CH.getValue());
		zbList.add(GSZB.SXFY.getValue());
		zbList.add(GSZB.JZCSYL.getValue());
	}
	

	@Scheduled(cron="0 0 0 8-9 * ?")
	public void scheduleImportNC(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		importNC(Util.toDate(cal));
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
			approveService.approveSjZb(Account.KNOWN_ACCOUNT_GFGS, compsTmp, date);
			break;
		case APPROVED_2: 
			entryService.saveZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray);
			List<Company> compsTmp2 = new ArrayList<Company>();
			compsTmp2.add(comp);
			approveService.approveSjZb(Account.KNOWN_ACCOUNT_JYFZ, compsTmp2, date);
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
	
	private void importNC(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		// Calendar.MONTH获得月份正常情况下为自然月-1,
		// 且当前需求中数据的月份为存储时间的前一个月，所以在下面公式调用中不必+1
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);

		// 存储NC对应指标
		// 合并
		List<String> unitList = new ArrayList<String>();
		unitList.add("0202AA000000");
		unitList.add("0303AA000000");
		unitList.add("0304AA000000");
		unitList.add("0203AA000000");
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


	@RequestMapping(value = "importNC.do", method = RequestMethod.GET)
	public void importNC(HttpServletRequest request,
			HttpServletResponse response) {
		importNC(DateSelection.getDate(request));
	}

}
