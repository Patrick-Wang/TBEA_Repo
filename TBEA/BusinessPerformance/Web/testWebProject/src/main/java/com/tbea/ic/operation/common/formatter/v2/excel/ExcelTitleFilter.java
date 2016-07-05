package com.tbea.ic.operation.common.formatter.v2.excel;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.v2.core.Offset;

public class ExcelTitleFilter extends ExcelAbstractOffsetFilter {

	List<List<String>> titles;
	public ExcelTitleFilter(ExcelTemplate template, Offset offset, List<List<String>> titles) {
		super(null, template, offset);
		this.titles = titles;
	}

	@Override
	protected void onFilter(List<List<String>> table, int row, int col,
			String val) {
		if (null != this.titles){

			for (int i = 0; i < titles.size(); ++i){
				for (int j = 0; j < titles.get(i).size(); ++j){
					HSSFCell cell = this.getCell(i, j);
					cell.setCellValue(titles.get(i).get(j));
					cell.setCellStyle(template.getCellStyleCenterHeader());
				}
			}
		}
	}
}
