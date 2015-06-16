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

	@RequestMapping(value = "importNC.do", method = RequestMethod.GET)
	public void importNC(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar cal = Calendar.getInstance();
		cal.set(2015, 5 - 1, 31);
		int month = cal.get(Calendar.MONTH) + 1;
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
		ncService.connetToNCSystem("501", cal, codeList);

		ZBStatus zbStatus = null;
		List<NCZB> NCZBList = ncService.getNCZBByDate(year, month);
		Date date = new Date(cal.getTimeInMillis());
		CompanyType comp = null;
		JSONArray jsonArray = null;
		for (NCZB nczb : NCZBList) {
			comp = companyManager.getBMDBOrganization()
					.getCompany(nczb.getDwxx().getId()).getType();
			zbStatus = entryService.getZbStatus(date, comp, ZBType.BYSJ).get(0);
			jsonArray = new JSONArray();
			jsonArray.add(0, nczb.getZbxx().getId());
			jsonArray.add(1, nczb.getNczbz());
			switch (zbStatus) {
			case NONE:
				entryService.saveZb(date, null, comp, ZBType.BYSJ, jsonArray);
				break;
			case SAVED:
				entryService.saveZb(date, null, comp, ZBType.BYSJ, jsonArray);
				break;
			case SUBMITTED_2:
				entryService.submitToDeputy(date, null, comp, ZBType.BYSJ,
						jsonArray);
				break;
			default:
				break;
			}
		}
		return;
	}

}
