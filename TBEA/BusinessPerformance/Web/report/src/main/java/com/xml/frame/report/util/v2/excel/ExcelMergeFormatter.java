package com.xml.frame.report.util.v2.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.v2.core.AbstractFormatter;
import com.xml.frame.report.util.v2.core.MergeRegion;

public class ExcelMergeFormatter extends AbstractFormatter {

	protected ExcelHelper template;
	List<MergeRegion> mrs = new ArrayList<MergeRegion>();
	List<MergeRegion> forceMrs = new ArrayList<MergeRegion>();
	public ExcelMergeFormatter(ExcelHelper template) {
		this.template = template;
	}
	
	public ExcelMergeFormatter addMergeRegion(MergeRegion mr){
		mrs.add(mr);
		return this;
	}
	
	public ExcelMergeFormatter addFMR(MergeRegion mr){
		forceMrs.add(mr);
		return this;
	}

	private void merge(List<List<String>> table){
		HSSFSheet sheet = template.getSheet();
		for (int i = 0; i < this.mrs.size(); ++i){
			this.merge(sheet, this.mrs.get(i));
		}
	}	
	
	private void merge(HSSFSheet sheet, MergeRegion mergeRegion) {
	
		if (mergeRegion.getWidth() <= 0 || mergeRegion.getHeight() <= 0){
			return;
		}
		
		String preText = null;
		
		
		
		for (int i = mergeRegion.getY(); i < mergeRegion.getY() + mergeRegion.getHeight(); ++i){
			preText = sheet.getRow(i).getCell(mergeRegion.getX()).getStringCellValue();
			int startCol = mergeRegion.getX();
			for(int j = mergeRegion.getX() + 1; j < mergeRegion.getX() + mergeRegion.getWidth(); ++j){
				if (!preText.equals(sheet.getRow(i).getCell(j).getStringCellValue())){
					preText = sheet.getRow(i).getCell(j).getStringCellValue();
					if (j - startCol > 1){
						sheet.addMergedRegion(new CellRangeAddress(i, i, startCol, j - 1));
					}
					startCol = j;
				}
			}
			
			if (mergeRegion.getX() + mergeRegion.getWidth() - startCol > 1){
				sheet.addMergedRegion(new CellRangeAddress(i, i, startCol, mergeRegion.getX() + mergeRegion.getWidth() - 1));
			}
		}
		
		for (int i = mergeRegion.getX(); i < mergeRegion.getX() + mergeRegion.getWidth(); ++i){
			preText = sheet.getRow(mergeRegion.getY()).getCell(i).getStringCellValue();
			int startRow = mergeRegion.getY();
			for(int j = mergeRegion.getY() + 1; j < mergeRegion.getY() + mergeRegion.getHeight(); ++j){
				if (!preText.equals(sheet.getRow(j).getCell(i).getStringCellValue())){
					preText = sheet.getRow(j).getCell(i).getStringCellValue();
					if (j - startRow > 1){
						sheet.addMergedRegion(new CellRangeAddress(startRow, j - 1, i, i));
					}
					startRow = j;
				}
			}
			
			if (mergeRegion.getY() + mergeRegion.getHeight() - startRow > 1){
				sheet.addMergedRegion(new CellRangeAddress(startRow, mergeRegion.getY() + mergeRegion.getHeight() - 1, i, i));
			}
		}
		
		for (int i = 0; i < forceMrs.size(); ++i){
			sheet.addMergedRegion(new CellRangeAddress(
					forceMrs.get(i).getY(), 
					forceMrs.get(i).getY() + forceMrs.get(i).getHeight() - 1, 
					forceMrs.get(i).getX(),
					forceMrs.get(i).getX() + forceMrs.get(i).getWidth() - 1));
		}
		
	}
	
	
	@Override
	public String handle(List<List<String>> table, int row, int col, String val) {
		callNext(table, row, col, val);
		if (table.size() - 1 == row && table.get(0).size() - 1== col){
			merge(table);
		}
		return val;
	}

	@Override
	protected String onHandle(List<List<String>> table, int row, int col,
			String val) {
		return val;
	}

}
