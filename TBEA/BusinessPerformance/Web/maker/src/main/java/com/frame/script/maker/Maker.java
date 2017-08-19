package com.frame.script.maker;

import com.frame.script.config.excel.ConfigTable;

public interface Maker {
	String make(ConfigTable src) throws MakerException;
	String makeAll() throws MakerException;
}
