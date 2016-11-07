package com.tbea.ic.operation.common.formatter.v2.excel;

public interface Rectangle {
	int row();
	int col();
	int width();
	int height();
	String value(int row, int col);
}
