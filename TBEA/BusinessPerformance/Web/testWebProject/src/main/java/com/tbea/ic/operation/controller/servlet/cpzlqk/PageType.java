package com.tbea.ic.operation.controller.servlet.cpzlqk;


public enum PageType {
	NONE,
	APPROVE,
	ENTRY,
	SHOW;
	public static PageType valueOf(Integer tp){
		PageType[] types = PageType.values();
		for (int i = types.length - 1; i >= 0; --i) {
			if (types[i].ordinal() == tp) {
				return types[i];
			}
		}
		return null;
	}
}
