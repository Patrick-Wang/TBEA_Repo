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
		
		for (int i = 0; i < forceMrs.size(); ++i){
			sheet.addMergedRegion(new CellRangeAddress(
					forceMrs.get(i).getY(), 
					forceMrs.get(i).getY() + forceMrs.get(i).getHeight() - 1, 
					forceMrs.get(i).getX(),
					forceMrs.get(i).getX() + forceMrs.get(i).getWidth() - 1));
		}
	}	
	
	private void merge(HSSFSheet sheet, MergeRegion mr) {
	
		if (mr.getWidth() <= 0 || mr.getHeight() <= 0){
			return;
		}
		
		String preText = null;
		
		for (int i = mr.getY(); i < mr.getY() + mr.getHeight(); ++i){
			preText = sheet.getRow(i).getCell(mr.getX()).getStringCellValue();
			int startCol = mr.getX();
			for(int j = mr.getX() + 1; j < mr.getX() + mr.getWidth(); ++j){
				if (!preText.equals(sheet.getRow(i).getCell(j).getStringCellValue())){
					preText = sheet.getRow(i).getCell(j).getStringCellValue();
					if (j - startCol > 1){
						sheet.addMergedRegion(new CellRangeAddress(i, i, startCol, j - 1));
					}
					startCol = j;
				}
			}
			
			if (mr.getX() + mr.getWidth() - startCol > 1){
				sheet.addMergedRegion(new CellRangeAddress(i, i, startCol, mr.getX() + mr.getWidth() - 1));
			}
		}
		
		for (int i = mr.getX(); i < mr.getX() + mr.getWidth(); ++i){
			preText = sheet.getRow(mr.getY()).getCell(i).getStringCellValue();
			int startRow = mr.getY();
			for(int j = mr.getY() + 1; j < mr.getY() + mr.getHeight(); ++j){
				if (!preText.equals(sheet.getRow(j).getCell(i).getStringCellValue())){
					preText = sheet.getRow(j).getCell(i).getStringCellValue();
					if (j - startRow > 1){
						sheet.addMergedRegion(new CellRangeAddress(startRow, j - 1, i, i));
					}
					startRow = j;
				}
			}
			
			if (mr.getY() + mr.getHeight() - startRow > 1){
				sheet.addMergedRegion(new CellRangeAddress(startRow, mr.getY() + mr.getHeight() - 1, i, i));
			}
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
