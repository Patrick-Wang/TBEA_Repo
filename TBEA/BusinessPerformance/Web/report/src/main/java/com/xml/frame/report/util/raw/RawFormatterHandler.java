package com.xml.frame.report.util.raw;

public interface RawFormatterHandler {
	RawFormatterHandler next(RawFormatterHandler handler);
	String handle(String zbName, Integer col, String val);
}
