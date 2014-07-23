package com.webservice;

import java.util.ArrayList;
import java.util.List;

public class Companys {
	private static final Company companys[] = new Company[]{
		new Company("沈变公司", 5),
		new Company("衡变公司", 6),
		new Company("新变公司", 7),
		new Company("天变公司", 8),
		new Company("鲁缆公司", 9),
		new Company("新缆厂", 10),
		new Company("德缆公司", 11),
	};
	
	public static int count(){
		return companys.length;
	}
	
	public static Company getCompany(int index){
		return companys[index];
	}
	
	public static Company getCompanyById(int id){
		for (Company company : companys){
			if (company.getId() == id){
				return company;
			}
		}
		return null;
	}

	public static List<Company> getCompanys(String companyqx){
		int start = 0;
		companyqx = companyqx.replace(" ", "");
		int index = companyqx.indexOf(',');
		List<Company> companyList = new ArrayList<Company>(companys.length);
		int id = 0;
		while (index > 0){
			id = Integer.parseInt(companyqx.substring(start, index));
			start = index + 1;
			index = companyqx.indexOf(',', start);
			
			if (id >= 5 && id <= 11){
				companyList.add(getCompanyById(id));
			}
		}
		
		id = Integer.parseInt(companyqx.substring(start));
		if (id >= 5 && id <= 11){
			companyList.add(getCompanyById(id));
		}
		
		return companyList;
	}
	
	public static List<Company> getCompanys(){
		List<Company> companyList = new ArrayList<Company>(companys.length);
		for (int i = 0; i < companys.length; ++i){
			companyList.add(companys[i]);
		}
		return companyList;
	}
}
