package com.xml.frame.report.util.v2.excel;

public class Rect implements Rectangle {

	int row;
	int col;
	int width;
	int height;
	String val;
	
	public Rect(int row, int col, int width, int height, String val) {
		super();
		this.row = row;
		this.col = col;
		this.width = width;
		this.height = height;
		this.val = val;
	}

	@Override
	public int row() {
		// TODO Auto-generated method stub
		return row;
	}

	@Override
	public int col() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int width() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public String value(int row, int col) {
		// TODO Auto-generated method stub
		return val;
	}
	
}
