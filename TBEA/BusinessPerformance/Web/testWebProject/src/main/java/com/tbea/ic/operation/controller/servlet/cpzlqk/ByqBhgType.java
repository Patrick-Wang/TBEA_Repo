package com.tbea.ic.operation.controller.servlet.cpzlqk;

public enum ByqBhgType {
	YBYSQFJYS, PBCP;
	public static ByqBhgType valueOf(Integer ord) {
		if (0 == ord) {
			return ByqBhgType.YBYSQFJYS;
		}
		return ByqBhgType.PBCP;
	}
}
