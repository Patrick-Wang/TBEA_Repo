package com.webservice;

public class WANConfigurations implements Configurations {

	@Override
	public String getLoginUrl() {
		return "http://10.1.4.107:8080/mobile/loginServlet";
	}

	@Override
	public String getTransferUrl() {
		return "http://10.1.4.107:8080/mobile/dataTransfer";
	}

	@Override
	public String getUpdateUrl() {
		return "http://10.1.4.107:8080/mobile/versionJudgmentServlet";
	}
	
	@Override
	public String getNetType() {
		return "WAN";
	}

	@Override
	public String getHome() {
		return "http://10.1.4.107:8080";
	}

	@Override
	public String getChangePasswordUrl() {
		return "http://10.1.4.107:8080/mobile/changePwd";
	}

	@Override
	public String getRegisterUrl() {
		return "http://10.1.4.107:8080/mobile/verification";
	}

	@Override
	public String getUserValidationUrl() {
		return "http://10.1.4.107:8080/mobile/beforeLogin";
	}
}
