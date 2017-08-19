package com.xml.frame.report.util.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.xml.frame.report.util.ExcelHelper;

public class FormatterServer {
	FormatterHandler handler;
	int xOffset = 0;
	int yOffset = 0;
	List<MergeRegion> mrs = new ArrayList<MergeRegion>();
	public FormatterServer addMergeRegion(MergeRegion mr){
		mrs.add(mr);
		return this;
	}
	
	public FormatterServer(FormatterHandler handler) {
		super();
		this.handler = handler;
	}
	
	public FormatterServer(FormatterHandler handler, int xOffset, int yOffset) {
		super();
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void format(List<List<String>> table, ExcelHelper template){
		HSSFSheet sheet = template.getSheet();
		for (int i = 0; i < table.size(); ++i){
			HSSFRow row = sheet.getRow(i + yOffset) == null ? sheet.createRow(i + yOffset) : sheet.getRow(i + yOffset);
			for (int j = 0; j < table.get(i).size(); ++j){
				handler.handle(
						table.get(i).get(0),
						j,
						template,
						row.getCell(j + xOffset) == null ?  row.createCell(j + xOffset) :  row.getCell(j + xOffset),
						table.get(i).get(j));
			}
		}
		
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
		
	}
}
