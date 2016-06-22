package com.tbea.ic.operation.controller.webservice.indicators;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.login.LoginService;
import com.tbea.ic.operation.service.ydzb.gszb.GszbService;

@WebService
public class IndicatorsProviderWS {
	
	@Autowired
	private LoginService loginServ;

	@Autowired
	private GszbService gszbService;
	
	public List<Double[]> provideCorpYearIndicators(
			@WebParam(name = "usrName") String usrName, 
			@WebParam(name = "year") int year, 
			@WebParam(name = "month") int month){
		Account account = loginServ.SSOLogin(usrName);
		List<Double[]> result = null;
		if (month > 0 && month <= 12 && null != account && loginServ.hasCorpAuth(account)){
			result = new ArrayList<Double[]>();
			EasyCalendar cal = new EasyCalendar(year, month, 1);
			List<Double[]> tmpVals = gszbService.getCorpIndicators(cal.getDate());
			for (Double[] row : tmpVals){
				result.add(new Double[]{row[11], row[13], row[14]});
			}
		}
		return result;
	}	
}
