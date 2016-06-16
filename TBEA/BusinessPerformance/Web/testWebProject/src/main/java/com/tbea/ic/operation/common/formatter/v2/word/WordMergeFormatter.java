package com.tbea.ic.operation.common.formatter.v2.word;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.v2.core.AbstractFormatter;
import com.tbea.ic.operation.common.formatter.v2.core.MergeRegion;

public class WordMergeFormatter extends AbstractFormatter {

	protected ExcelTemplate template;
	List<MergeRegion> mrs = new ArrayList<MergeRegion>();
	
	public WordMergeFormatter(ExcelTemplate template) {
		this.template = template;
	}
	
	public WordMergeFormatter addMergeRegion(MergeRegion mr){
		mrs.add(mr);
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
		
	}
	
	@Override
	protected String onHandle(List<List<String>> table, int row, int col,
			String val) {
		if (table.size() - 1 == row && table.get(0).size() - 1== col){
			merge(table);
		}
		return val;
	}

}
