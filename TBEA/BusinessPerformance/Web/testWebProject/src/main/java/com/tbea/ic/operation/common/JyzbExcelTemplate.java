package com.tbea.ic.operation.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tbea.ic.operation.controller.servlet.convertor.Convertor;

public class JyzbExcelTemplate {
	
	private static String pathJdzbTemplate = null;
	
	static 
	{
		try {
			pathJdzbTemplate = new URI(Convertor.class
					.getClassLoader().getResource("").getPath()).getPath().substring(1) + "META-INF/jyzb_template.xls";
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public enum SheetType{
		GSZTZB,
		SRQYFJG,
		GS_SYB,
		GSZT_ZYCWZB,
		GDWZBWCQK,
		JDYJZB_SY,
		JDFCYZBYJ_SY,
		JDFDWZBYJ_SY,
		JDYJZB_CY,
		JDFCYZBYJ_CY,
		JDFDWZBYJ_CY,
		JDYJZB_MY,
		JDFCYZBYJ_MY,
		JDFDWZBYJ_MY
	}

	public static JyzbExcelTemplate createTemplate(SheetType type) throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(
				pathJdzbTemplate)));
		for (int i = SheetType.JDFDWZBYJ_MY.ordinal(); i >= 0; --i){
			if (i != type.ordinal()){
				workbook.removeSheetAt(i);
			}
		}
		return new JyzbExcelTemplate(workbook);
	}
	
	
	HSSFWorkbook workbook;
	HSSFCellStyle cellStyleNull;
	HSSFCellStyle cellStyleNumber;
	HSSFCellStyle cellStylePercent;
	HSSFCellStyle cellStyleHeader;
	
	JyzbExcelTemplate(HSSFWorkbook workbook){
		this.workbook = workbook;
		
		cellStyleNull = workbook
				.createCellStyle();
		cellStyleNull.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleNull.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleNull.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleNull.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleNull.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		
		cellStyleNumber = workbook
				.createCellStyle();
		cellStyleNumber.setDataFormat(HSSFDataFormat
				.getBuiltinFormat("0.00"));
		cellStyleNumber.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleNumber.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleNumber.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleNumber.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		
		cellStylePercent = workbook
				.createCellStyle();
		cellStylePercent.setDataFormat(HSSFDataFormat
				.getBuiltinFormat("0.00%"));
		cellStylePercent.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStylePercent.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStylePercent.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStylePercent.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		
		cellStyleHeader = workbook
				.createCellStyle();
		HSSFFont font = workbook.createFont();    
		font.setFontName("宋体");    
		font.setFontHeightInPoints((short) 10);//设置字体大小 
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		cellStyleHeader.setFont(font);
		
			
	}
	/**
	 * @return the workbook
	 */
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}
	/**
	 * @return the cellStyleNull
	 */
	public HSSFCellStyle getCellStyleNull() {
		return cellStyleNull;
	}
	/**
	 * @return the cellStyleNumber
	 */
	public HSSFCellStyle getCellStyleNumber() {
		return cellStyleNumber;
	}
	/**
	 * @return the cellStylePercent
	 */
	public HSSFCellStyle getCellStylePercent() {
		return cellStylePercent;
	}
	/**
	 * @return the cellStyleHeader
	 */
	public HSSFCellStyle getCellStyleHeader() {
		return cellStyleHeader;
	}
	
	public void write(OutputStream os) throws IOException{
		HSSFSheet sheet = workbook.getSheetAt(0);
		int colCount = sheet.getRow(0).getLastCellNum();	
		for (int j = 0; j < colCount; ++j) {
			sheet.autoSizeColumn(j, true);
		}
		workbook.write(os);
	}
	
}
