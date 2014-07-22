package com.excel;

public interface TableSource {
	int getRowCount();
	int getColumCount();
	String getItem(int row, int colum);
}
