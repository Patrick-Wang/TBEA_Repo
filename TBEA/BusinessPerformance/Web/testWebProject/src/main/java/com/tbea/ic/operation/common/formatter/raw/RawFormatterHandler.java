package com.tbea.ic.operation.common.formatter.raw;

public interface RawFormatterHandler {
	RawFormatterHandler next(RawFormatterHandler handler);
	String handle(String zbName, Integer col, String val);
}
