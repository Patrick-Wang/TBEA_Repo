package com.excel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class SizeManager {

	public interface SizeChangeObserver {
		void onWidthChanged(SizeManager mgr, int colum, int newSize);

		void onHeightChanged(SizeManager mgr, int row, int newSize);
	}

	private SizeChangeObserver sizeChangeObserver;
	private List<Integer> columWidth = new LinkedList<Integer>();
	private List<Integer> rowHeight = new LinkedList<Integer>();
	private int sumWidth = 0;
	private int sumHeight = 0;

	public boolean isActive(){
		return !columWidth.isEmpty();
	}
	
	public void active(int columCount){
		columWidth = new ArrayList<Integer>(columCount);
		for (int i = 0; i < columCount; ++i){
			columWidth.add(0);
		}
	}
	
	public int getWidth(){
		return sumWidth;
	}
	
	public int getHeight(){
		return sumHeight;
	}
	
	public void registerObserver(SizeChangeObserver sizeChangeObserver) {
		this.sizeChangeObserver = sizeChangeObserver;
	}

	public int getColumCount() {
		return columWidth.size();
	}

	public int getRowCount() {
		return rowHeight.size();
	}

	public int getWidth(int colum) {
		return columWidth.get(colum);
	}

	public int getHeight(int row) {
		return rowHeight.get(row);
	}

	public void increaseRow(int count){
		for (int i = 0; i < count; ++i){
			rowHeight.add(0);
		}
	}
	
	public void setColum(int colum, int val) {
		if (columWidth.get(colum) < val) {
			sumWidth += (val - columWidth.get(colum));
			columWidth.set(colum, val);
			sizeChangeObserver.onWidthChanged(this, colum, val);
		}
	}

	public void setRow(int row, int val) {

		if (rowHeight.get(row) < val) {
			sumHeight += (val - rowHeight.get(row));
			rowHeight.set(row, val);
			sizeChangeObserver.onHeightChanged(this, row, val);
		}
	}
}