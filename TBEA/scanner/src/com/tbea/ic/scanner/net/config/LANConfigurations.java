package com.tbea.ic.scanner.net.config;

public class LANConfigurations implements Configurations {

	@Override
	public String getLoginUrl() {
		return "http://10.1.6.187:8080/ScannerWeb/account/login.do";
		//		return "http://10.1.4.106:8080/ScannerWeb/account/login.do";
	}

	@Override
	public String getUpdateUrl() {
		return "";
	}

	@Override
	public String getNetType() {
		return "LAN";
	}

	@Override
	public String getHost() {
		return "";
	}
}
