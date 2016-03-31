package com.tbea.ic.scanner.net.config;

public class WANConfigurations implements Configurations {

	@Override
	public String getLoginUrl() {
		return "";
	}

	@Override
	public String getUpdateUrl() {
		return "";
	}
	
	@Override
	public String getNetType() {
		return "WAN";
	}

	@Override
	public String getHost() {
		return "";
	}
}
