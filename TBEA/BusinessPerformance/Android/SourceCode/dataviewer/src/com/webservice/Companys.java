package com.webservice;

import java.util.ArrayList;
import java.util.List;

public class Companys {
	private static final Company companys[] = new Company[] { new Company("沈变公司", 5), new Company("衡变公司", 6),
			new Company("新变厂", 7), new Company("天变公司", 8), new Company("鲁缆公司", 9), new Company("新缆厂", 10),
			new Company("德缆公司", 11), new Company("进出口公司", 23), new Company("能动公司", 25), new Company("众和公司", 27),
			new Company("新能源", 29), new Company("新特能源公司", 30), new Company("天池能源", 66), new Company("输变电产业集团", 67),
			new Company("新能源事业部", 68), new Company("能源事业部", 69), new Company("国际工程公司", 70), new Company("中疆物流", 74),
			new Company("公司机关", 120), new Company("香港公司", 122) };

	public static int count() {
		return companys.length;
	}

	public static Company getCompany(int index) {
		return companys[index];
	}

	public static boolean hasCompany(String id) {
		try {
			Integer companyId = Integer.valueOf(id);
			return hasCompany(companyId.intValue());
		} catch (Exception e) {

		}
		return false;
	}

	public static boolean hasCompany(int id) {
		return null != getCompanyById(id);
	}

	public static Company getCompanyById(int id) {
		for (Company company : companys) {
			if (company.getId() == id) {
				return company;
			}
		}
		return null;
	}

	public static List<Company> getCompanys(String companyqx) {
		int start = 0;
		companyqx = companyqx.replace(" ", "");
		int index = companyqx.indexOf(',');
		List<Company> companyList = new ArrayList<Company>(companys.length);
		try {
			int id = 0;
			Company tmpCompany = null;
			while (index > 0) {
				id = Integer.parseInt(companyqx.substring(start, index));
				start = index + 1;
				index = companyqx.indexOf(',', start);
				tmpCompany = getCompanyById(id);

				if (null != tmpCompany) {
					companyList.add(tmpCompany);
				}
			}

			id = Integer.parseInt(companyqx.substring(start));
			tmpCompany = getCompanyById(id);
			if (null != tmpCompany) {
				companyList.add(tmpCompany);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return companyList;
	}

	// public static List<Company> getCompanys(){
	// List<Company> companyList = new ArrayList<Company>(companys.length);
	// for (int i = 0; i < companys.length; ++i){
	// companyList.add(companys[i]);
	// }
	// return companyList;
	// }
}
