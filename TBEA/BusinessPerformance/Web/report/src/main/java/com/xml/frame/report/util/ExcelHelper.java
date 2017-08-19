package com.xml.frame.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelHelper {
	
	
	HSSFWorkbook workbook;
	HSSFCellStyle cellStyleDefault;
	HSSFCellStyle cellStyleCenter;
	HSSFCellStyle cellStyleCenterHeader;
	HSSFCellStyle cellStyleText;
	HSSFCellStyle cellStyleHeader;
	
	private HSSFCellStyle border(HSSFCellStyle style){
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		return style;
	}
	
	public ExcelHelper(HSSFWorkbook workbook){
		this.workbook = workbook;
		HSSFFont fontDefault = workbook.createFont();    
		fontDefault.setFontName("宋体");    
		fontDefault.setFontHeightInPoints((short) 10);//设置字体大小 

		
		HSSFFont fontBold = workbook.createFont();    
		fontBold.setFontName("宋体");    
		fontBold.setFontHeightInPoints((short) 10);//设置字体大小 
		fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		cellStyleCenter = border(workbook
				.createCellStyle());
		cellStyleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleCenter.setFont(fontDefault);
		
		
		cellStyleDefault = border(workbook
				.createCellStyle());
		cellStyleDefault.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleDefault.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleDefault.setFont(fontDefault);

		cellStyleHeader = border(workbook
				.createCellStyle());
		cellStyleHeader.setFont(fontBold);

		
		cellStyleCenterHeader = border(workbook
				.createCellStyle());
		cellStyleCenterHeader.setFont(fontBold);
		cellStyleCenterHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleCenterHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		cellStyleText = border(workbook
				.createCellStyle());
		cellStyleText.setFont(fontDefault);
		cellStyleText.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyleText.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);			
	}

	/**
	 * @return the workbook
	 */
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}
	public HSSFCellStyle getCellStyleCenter() {
		return cellStyleCenter;
	}
	
	public HSSFCellStyle getCellStyleText() {
		return cellStyleText;
	}
	
	public HSSFCellStyle getCellStyleCenterHeader() {
		return cellStyleCenterHeader;
	}
	
	public HSSFSheet getSheet(){
		return this.workbook.getSheetAt(0);
	}
	
	public String getSheetName(){
		return this.workbook.getSheetName(0);
	}
	
	public void setSheetName(String name){
		this.workbook.setSheetName(0, name);
	}
	
	public HSSFCellStyle getCellStyleDefault() {
		return cellStyleDefault;
	}

	public HSSFCellStyle getCellStyleHeader() {
		return cellStyleHeader;
	}

	//width 为excel中查看到的宽度，非pixel
	public void setColumnWidth(int colFrom, int colTo, float width) {
		//在默认 宋体  11号字情况下     6.5  ->  1832
 		int poiWidth = (int)((1832  * width) / 6.5);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = colFrom; i <= colTo; ++i){
			sheet.setColumnWidth(i, (short)(poiWidth));
		}
	}

	//磅（Point）
	//excel 字体单位是 磅（Point）
	public void setRowHeight(int rowFrom, int rowTo, float point){
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = rowFrom; i <= rowTo; ++i){
			sheet.getRow(i).setHeightInPoints(point);
		}
	}
	
	
	public void write(OutputStream os) throws IOException{
		HSSFSheet sheet = workbook.getSheetAt(0);
		
//		sheet.setColumnWidth(columnIndex, width);
//		for (int j = 0; j < colCount; ++j) {
//			sheet.autoSizeColumn(j, true);
//		}
		workbook.write(os);
	}
	
	public void write(HttpServletResponse response, String fileName) throws IOException{
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition","attachment;filename=\""+ java.net.URLEncoder.encode(fileName, "UTF-8")  +"\"");
		this.write(response.getOutputStream());
	}
	
	public void writeWithRawSize(OutputStream os) throws IOException{
		workbook.write(os);
	}
	
	public void writeWithRawSize(HttpServletResponse response, String fileName) throws IOException{
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition","attachment;filename=\""+ java.net.URLEncoder.encode(fileName, "UTF-8")  +"\"");
		this.writeWithRawSize(response.getOutputStream());
	}

	
	private static ExcelHelper createTemplate(String path, int index, int size)
			throws FileNotFoundException, IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(
				path)));
		for (int i = 0; i < index; ++i) {
			workbook.removeSheetAt(0);
		}

		for (int i = index + 1; i < size; ++i) {
			workbook.removeSheetAt(1);
		}

		return new ExcelHelper(workbook);
	}
}
