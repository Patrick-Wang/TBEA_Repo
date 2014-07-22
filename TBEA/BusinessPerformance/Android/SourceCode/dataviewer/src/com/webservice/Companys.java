package com.webservice;

import java.util.ArrayList;
import java.util.List;

public class Companys {
	private static final Company companys[] = new Company[]{
		new Company("沈变", 5),
		new Company("衡变", 6),
		new Company("新变", 7),
		new Company("天变", 8),
		new Company("鲁缆", 9),
		new Company("新缆", 10),
		new Company("德缆", 11),
	};
	
	public static int count(){
		return companys.length;
	}
	
	public static Company getCompany(int index){
		return companys[index];
	}
	
	public static List<Company> getCompanys(){
		List<Company> companyList = new ArrayList<Company>(companys.length);
		for (int i = 0; i < companys.length; ++i){
			companyList.add(companys[i]);
		}
		return companyList;
	}
}
