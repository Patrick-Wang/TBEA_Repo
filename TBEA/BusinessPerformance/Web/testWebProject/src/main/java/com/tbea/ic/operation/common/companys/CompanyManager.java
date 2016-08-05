package com.tbea.ic.operation.common.companys;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class CompanyManager {
	Organization pzgh = new BMDepartmentPzgh();
	Organization opera = new OperationDepartment();
	Organization BM = new BMDepartment();
	Organization zbhz = new OperationZBHZ(new OperationDepartment());
	Organization org15 = new Org15DB();
	Organization BMDB;
	Organization virtualYSZK;
	Organization virtualJYZB;
	Organization virtualGB;
	Organization virtualCY;
 
	private final static Company emptyCompany = new Company(-1, CompanyType.UNKNOWN);
	public static Company getEmptyCompany(){
		return emptyCompany;
	}
	
	
	@Resource(type=BMDepartmentDB.class)
	public void setBMDBOrganization(Organization org) {
		BMDB = org;
		virtualYSZK = new VirtualYSZKOrganization(BMDB);
		virtualJYZB = new VirtualJYZBOrganization((VirtualYSZKOrganization) virtualYSZK, BMDB);
		virtualGB = new VirtualGBOrganization(BMDB);
		virtualCY = new VirtualCYOrganization(BMDB);
	}
	
	public Organization getVirtualJYZBOrganization() {
		return virtualJYZB;
	}
	
	public Organization getVirtualCYOrg() {
		return virtualCY;
	}
	
	public Organization getVirtualGBOrg() {
		return virtualGB;
	}
	
	public Organization get15Org() {
		return org15;
	}
	
	public Organization getVirtualYSZKOrganization() {
		return virtualYSZK;
	}
	
	public Organization getBMDBOrganization() {
		return BMDB;
	}
	
	public Organization getBMOrganization() {
		return BM;
	}

	public Organization getPzghOrganization() {
		return pzgh;
	}
	
	public Organization getOperationOrganization() {
		return opera;
	}

	public Organization getOperationZBHZOrganization(){
		return zbhz;
	}

}
