package com.tbea.ic.operation.controller.servlet.yszkpzjh;

public class YSZKPZJHBean {
	private Integer companyType = null;
	private String[] list1;
	private String[] list2;
	private String[] list3;
	private String[] list4;
	/**
	 * @return the list1
	 */
	public String[] getList1() {
		return list1;
	}
	/**
	 * @return the list2
	 */
	public String[] getList2() {
		return list2;
	}
	/**
	 * @return the list3
	 */
	public String[] getList3() {
		return list3;
	}
	/**
	 * @return the list4
	 */
	public String[] getList4() {
		return list4;
	}
	/**
	 * @param list1 the list1 to set
	 */
	public void setList1(String[] list1) {
		this.list1 = list1;
	}
	/**
	 * @param list2 the list2 to set
	 */
	public void setList2(String[] list2) {
		this.list2 = list2;
	}
	/**
	 * @param list3 the list3 to set
	 */
	public void setList3(String[] list3) {
		this.list3 = list3;
	}
	/**
	 * @param list4 the list4 to set
	 */
	public void setList4(String[] list4) {
		this.list4 = list4;
	}
	//@Column(name="[companyType]")
	public Integer getCompanyType() {
		return companyType;
	}
	/**
	 * @param companyType the companyType to set
	 */
	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

}
