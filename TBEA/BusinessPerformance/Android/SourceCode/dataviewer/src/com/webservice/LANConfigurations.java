package com.webservice;

public class LANConfigurations implements Configurations {

	@Override
	public String getLoginUrl() {
		return "http://192.168.7.22/mobile/loginServlet";
	}

	@Override
	public String getTransferUrl() {
		return "http://192.168.7.22/mobile/dataTransfer";
	}

	@Override
	public String getUpdateUrl() {
		return "http://192.168.7.22/mobile/versionJudgmentServlet";
	}

	@Override
	public String getNetType() {
		return "LAN";
	}

	@Override
	public String getHome() {
		return "http://192.168.7.22";
	}

	@Override
	public String getChangePasswordUrl() {
		return "http://192.168.7.22/mobile/changePwd";
	}

	@Override
	public String getRegisterUrl() {
		return "http://192.168.7.22/mobile/verification";
	}

	@Override
	public String getUserValidationUrl() {
		return "http://192.168.7.22/mobile/beforeLogin";
	}

}
