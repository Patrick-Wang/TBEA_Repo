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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.entity.jygk.NCZB;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.nc.NCService;

@Controller
@RequestMapping(value = "nc")
public class NCController {

	@Autowired
	EntryService entryService;

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

	@RequestMapping(value = "importNC.do", method = RequestMethod.GET)
	public void importNC(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar cal = Calendar.getInstance();
		cal.set(2015, 5 - 1, 31);
		// Calendar.MONTH获得月份正常情况下为自然月-1,
		// 且当前需求中数据的月份为存储时间的前一个月，所以在下面公式调用中不必+1
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);

		// 存储NC对应指标
		List<String> codeList = new ArrayList<String>();
		codeList.add("0202AA000000");
		codeList.add("0303AA000000");
		codeList.add("0304AA000000");
		codeList.add("0203AA000000");
		codeList.add("CC02");
		codeList.add("CC03");
		codeList.add("040203AA0000");
		codeList.add("040202AA0000");
		codeList.add("CC11");
		codeList.add("CC10");
		codeList.add("060100000000");
		codeList.add("CC04");
		ncService.connetToNCSystem("510", cal, codeList);

		ZBStatus zbStatus = null;
		List<NCZB> NCZBList = ncService.getNCZBByDate(year, month);
		// 需求中数据的月份为存储时间的前一个月
		cal.add(Calendar.MONTH, -1);
		Date date = new Date(cal.getTimeInMillis());
		CompanyType comp = null;
		JSONArray jsonArray = null;
		int zbid = 0;
		for (NCZB nczb : NCZBList) {
			zbid = nczb.getZbxx().getId();
			if (zbList.contains(zbid)) {
				comp = companyManager.getBMDBOrganization()
						.getCompany(nczb.getDwxx().getId()).getType();
				zbStatus = entryService.getZbStatus(date, comp, ZBType.BYSJ)
						.get(0);
				jsonArray = new JSONArray();
				jsonArray.add(0, zbid);
				jsonArray.add(1, nczb.getNczbz());
				switch (zbStatus) {
				case NONE:
					entryService.saveZb(date, null, comp, ZBType.BYSJ,
							jsonArray);
					break;
				case SAVED:
					entryService.saveZb(date, null, comp, ZBType.BYSJ,
							jsonArray);
					break;
				case SUBMITTED_2:
					entryService.submitToDeputy(date, null, comp, ZBType.BYSJ,
							jsonArray);
					break;
				default:
					break;
				}
			}
		}
		return;
	}

}
