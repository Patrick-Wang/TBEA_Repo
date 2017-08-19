package com.xml.frame.report.util.v2.excel;

import java.util.ArrayList;
import java.util.List;


public class RectDetector {

	Rectangle background;

	public RectDetector(Rectangle background) {
		super();
		this.background = background;
	}
	
	List<Rectangle> detect(Rectangle front){
//		Map<String, List<List<Pair<>>>>
//		for (int i = 0; i < front.width(); ++i){
//			for (int j = 0; j < front.height(); ++j){
//				fo
//			}
//		}
		
		
		String reference = background.value(front.row(), front.col());
		return enlarge(reference, front);
	}

	private int maxEqualCol(String reference, int row, int startCol, int maxCol){
		for (int i = startCol + 1; i <= maxCol; ++i){
			if (!reference.equals(background.value(row, i))){
				return --i;
			}
		}
		return maxCol;
	}
	
	private int maxEqualRow(String reference, int col, int startRow, int maxRow){
		for (int i = startRow + 1; i <= maxRow; ++i){
			if (!reference.equals(background.value(i, col))){
				return --i;
			}
		}
		return maxRow;
	}
	
	private boolean isVertical(int cols, int rows){
		return cols < rows;
	}
	
	private List<Rectangle> enlarge(String reference, Rectangle fg) {
		List<Rectangle> rects = new ArrayList<Rectangle>();
		int maxEqualCol = maxEqualCol(reference, fg.row(), fg.col(), fg.width());
		int maxEqualRow = maxEqualCol(reference, fg.col(), fg.row(), fg.height());
		Rectangle rect = new Rect(
				fg.row(), 
				fg.col(), 
				maxEqualCol - fg.col() + 1, 
				maxEqualRow - fg.row() + 1, 
				null);
		if (isVertical(maxEqualCol - fg.col(),  maxEqualRow - fg.row())){
			rect = detectVertical(reference, rect);
		}else{
			rect = detectHorizontal(reference, rect);
		}
		
		if (rect.width() != 1 || rect.height() != 1){
			rects.add(rect);
		}
		
		if (rect.width() < fg.width()){
			String ref = background.value(rect.row(), rect.col() + rect.width());
			rects.addAll(enlarge(ref, new Rect(
					rect.row(), 
					rect.col() + rect.width(), 
					fg.width() - rect.width(), 
					rect.height(), 
					null)));
		}
		
		if (rect.height() < fg.height()){
			String ref = background.value(rect.row() + rect.height(), rect.col());
			rects.addAll(enlarge(ref, new Rect(
					rect.row() + rect.height(), 
					rect.col(), 
					fg.width(), 
					rect.height() - rect.height(), 
					null)));
		}
		
		if (rect.height() < fg.height() && rect.width() < fg.width()){
			String ref = background.value(rect.row() + rect.height(), rect.col() + rect.width());
			rects.addAll(enlarge(ref, new Rect(
					rect.row() + rect.height(), 
					rect.col() + rect.width(), 
					fg.width() - rect.width(), 
					rect.height() - rect.height(), 
					null)));
		}
		
		return rects;
	}

	private Rectangle detectHorizontal(String reference, Rectangle rect) {
		// TODO Auto-generated method stub
		return null;
	}

	private Rectangle detectVertical(String reference, Rectangle rect) {
		// TODO Auto-generated method stub
		return null;
	}
}
