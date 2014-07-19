package com.javaBean;

public class UserBean {
	// 手机串号
	private String imei;
	// sim卡号
	private String simcard;
	// 用户名
	private String userid;
	// 密码
	private String pwd;
	// 菜单功能权限
	private String menuqx;
	// 公司ID
	private String companyID;
	// 公司权限
	private String companyqx;

	private boolean loginFlag;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getSimcard() {
		return simcard;
	}

	public void setSimcard(String simcard) {
		this.simcard = simcard;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMenuqx() {
		return menuqx;
	}

	public void setMenuqx(String menuqx) {
		this.menuqx = menuqx;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getCompanyqx() {
		return companyqx;
	}

	public void setCompanyqx(String companyqx) {
		this.companyqx = companyqx;
	}

	public boolean isLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}

}
