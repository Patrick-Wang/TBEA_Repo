package com.tbea.ic.scanner.net.config;

public class WANConfigurations implements Configurations {

	@Override
	public String getLoginUrl() {
		return "http://218.84.134.160:8081/mobile/loginServlet";
	}

	@Override
	public String getTransferUrl() {
		return "http://218.84.134.160:8081/mobile/dataTransfer";
	}

	@Override
	public String getUpdateUrl() {
		return "http://218.84.134.160:8081/mobile/versionJudgmentServlet";
	}
	
	@Override
	public String getNetType() {
		return "WAN";
	}

	@Override
	public String getHost() {
		return "http://218.84.134.160:8081";
	}

	@Override
	public String getChangePasswordUrl() {
		return "http://218.84.134.160:8081/mobile/changePwd";
	}

	@Override
	public String getRegisterUrl() {
		return "http://218.84.134.160:8081/mobile/verification";
	}

	@Override
	public String getUserValidationUrl() {
		return "http://218.84.134.160:8081/mobile/beforeLogin";
	}
}
