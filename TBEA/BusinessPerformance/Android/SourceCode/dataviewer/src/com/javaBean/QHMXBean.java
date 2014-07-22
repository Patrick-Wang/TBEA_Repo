package com.javaBean;

public class QHMXBean {
	//企业编号
	private String qybh;
	//企业名称
	private String qymc;	
	//类型（铜、铝）
	private String type;
	//持仓量
	private String ccl;	
	//持仓均价
	private String ccjj;
	//现价
	private String price;
	//盈亏比例
	private String yk;
	//盈亏金额
	private String ykje;
	//日期(年月)
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public String getQybh() {
		return qybh;
	}
	public void setQybh(String qybh) {
		this.qybh = qybh;
	}
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCcl() {
		return ccl;
	}
	public void setCcl(String ccl) {
		this.ccl = ccl;
	}
	public String getCcjj() {
		return ccjj;
	}
	public void setCcjj(String ccjj) {
		this.ccjj = ccjj;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getYk() {
		return yk;
	}
	public void setYk(String yk) {
		this.yk = yk;
	}
	public String getYkje() {
		return ykje;
	}
	public void setYkje(String ykje) {
		this.ykje = ykje;
	}
}
