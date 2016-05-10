package com.tbea.ic.operation.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tbea.ic.operation.controller.servlet.convertor.Convertor;

public class ExcelTemplate {
	
	private static String resPath;
	static 
	{
		try {
			resPath = new URI(Convertor.class
					.getClassLoader().getResource("").getPath()).getPath().substring(1) + "META-INF/";
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private static String pathJdzbTemplate = resPath + "jyzb_template.xls";
	private static String pathMarketTemplate = resPath + "market_template.xls";
	private static String pathJYGKPhase2Template = resPath + "jygk_Phase2_template.xls";
	private static String pathYszkgbTemplate = resPath + "yszkgb_template.xls";
	private static String pathChgbTemplate = resPath + "chgb_template.xls";
	private static String pathSbdddcbjpcqkTemplate = resPath + "sbdddcbjpcqk_template.xls";
	private static String pathDzwzgbTemplate = resPath + "dzwzgb_template.xls";
	private static String pathCbfxTemplate = resPath + "cbfx_template.xls";
	private static String pathYlfxwlyddmlspcsTemplate = resPath + "ylfxwlyddmlspcs_template.xls";
	private static String pathYlfxwgcpylnlspcsTemplate = resPath + "ylfxwgcpylnlspcs_template.xls";
	private static String pathSbdscqyqkTemplate = resPath + "sbdscqyqk_template.xls";
	private static String pathXnychTemplate = resPath + "xnych_template.xls";
	private static String pathWgcpqkTemplate = resPath + "wgcpqk_template.xls";
	private static String pathSbdczclwcqkTemplate = resPath + "sbdczclwcqk_template.xls";
	private static String pathNyzbscqkTemplate = resPath + "nyzbscqk_template.xls";
	private static String pathCwcpdlmlTemplate = resPath + "cwcpdlml_template.xls";
	private static String pathCwyjsfTemplate = resPath + "cwyjsf_template.xls";
	private static ExcelTemplate createTemplate(String path, int index, int size)
			throws FileNotFoundException, IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(
				path)));
		for (int i = 0; i < index; ++i) {
			workbook.removeSheetAt(0);
		}

		for (int i = index + 1; i < size; ++i) {
			workbook.removeSheetAt(1);
		}

		return new ExcelTemplate(workbook);
	}
	
	public static ExcelTemplate createSbdczclwcqkTemplate(SbdczclwcqkSheetType type) throws IOException {
		return createTemplate(pathSbdczclwcqkTemplate, type.ordinal(),
				SbdczclwcqkSheetType.END.ordinal());
	}

	public static ExcelTemplate createJygkTemplate(JygkSheetType type)
			throws IOException {
		return createTemplate(pathJdzbTemplate, type.ordinal(),
				JygkSheetType.END.ordinal());
	}

	public static ExcelTemplate createMarketTemplate(MarketSheetType type)
			throws IOException {
		return createTemplate(pathMarketTemplate, type.ordinal(),
				MarketSheetType.END.ordinal());
	}

	public static ExcelTemplate createJYGKPhase2Template(
			JYGKPhase2SheetType type) throws IOException {
		return createTemplate(pathJYGKPhase2Template, type.ordinal(),
				JYGKPhase2SheetType.END.ordinal());
	}

	public static ExcelTemplate createYszkgbTemplate(YszkgbSheetType type)
			throws IOException {
		return createTemplate(pathYszkgbTemplate, type.ordinal(),
				YszkgbSheetType.END.ordinal());
	}
	
	public static ExcelTemplate createChgbTemplate(ChgbSheetType type)
			throws IOException {
		return createTemplate(pathChgbTemplate, type.ordinal(),
				ChgbSheetType.END.ordinal());
	}

	public static ExcelTemplate createSbdddcbjpcqkTemplate(SbdddcbjpcqkSheetType type)
			throws IOException {
		return createTemplate(pathSbdddcbjpcqkTemplate, type.ordinal(),
				SbdddcbjpcqkSheetType.END.ordinal());
	}
	
	public static ExcelTemplate createDzwzgbTemplate(DzwzgbSheetType type)
			throws IOException {
		return createTemplate(pathDzwzgbTemplate, type.ordinal(),
				DzwzgbSheetType.END.ordinal());
	}
	

	public static ExcelTemplate createYlfxwlyddmlspcsTemplate(YlfxwlyddmlspcsSheetType type)
			throws IOException {
		return createTemplate(pathYlfxwlyddmlspcsTemplate, type.ordinal(),
				YlfxwlyddmlspcsSheetType.END.ordinal());
	}
	
	public static ExcelTemplate createYlfxwgcpylnlspcsTemplate(YlfxwgcpylnlspcsSheetType type)
			throws IOException {
		return createTemplate(pathYlfxwgcpylnlspcsTemplate, type.ordinal(),
				YlfxwgcpylnlspcsSheetType.END.ordinal());
	}
	
	public static ExcelTemplate createCbfxTemplate(CbfxSheetType type) throws IOException {
		return createTemplate(pathCbfxTemplate, type.ordinal(),
				CbfxSheetType.END.ordinal());
	}
	
	public static ExcelTemplate createSbdscqyqkTemplate(
			SbdscqyqkSheetType type) throws IOException {
		return createTemplate(pathSbdscqyqkTemplate, type.ordinal(),
				SbdscqyqkSheetType.END.ordinal());
	}
	
	public static ExcelTemplate createXnychTemplate(XnychSheetType type) throws IOException {
		return createTemplate(pathXnychTemplate, type.ordinal(),
				XnychSheetType.END.ordinal());
	}
	
	public static ExcelTemplate createWgcpqkTemplate(WgcpqkSheetType type) throws IOException {
		return createTemplate(pathWgcpqkTemplate, type.ordinal(),
				XnychSheetType.END.ordinal());
	}
	
	public static ExcelTemplate createNyzbscqkTemplate(
			NyzbscqkSheetType type)  throws IOException {
		return createTemplate(pathNyzbscqkTemplate, type.ordinal(),
				NyzbscqkSheetType.END.ordinal());
	}
	
	public static ExcelTemplate createCwcpdlmlTemplate(CwcpdlmlSheetType type)  throws IOException {
		return createTemplate(pathCwcpdlmlTemplate, type.ordinal(),
				CwcpdlmlSheetType.END.ordinal());
	}
	

	public static ExcelTemplate createCwyjsfTemplate(CwyjsfSheetType type)   throws IOException {
		return createTemplate(pathCwyjsfTemplate, type.ordinal(),
				CwyjsfSheetType.END.ordinal());
	}

	
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
	
	ExcelTemplate(HSSFWorkbook workbook){
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
		
		
		cellStyleDefault = border(workbook
				.createCellStyle());
		cellStyleDefault.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleDefault.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleDefault.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleDefault.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleDefault.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
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
	
	/**
	 * @return the cellStyleNull
	 */
	public HSSFCellStyle getCellStyleDefault() {
		return cellStyleDefault;
	}

	public HSSFCellStyle getCellStyleHeader() {
		return cellStyleHeader;
	}

	//磅（Point）
	//excel 字体单位是 磅（Point）
	public void write(OutputStream os) throws IOException{
		HSSFSheet sheet = workbook.getSheetAt(0);
		sheet.setDefaultRowHeightInPoints(16.5f);	// 16.5;
		
		//Set the width (in units of 1/256th of a character width)
		//它的api文档里写的很清楚了，以一个字符的1/256的宽度作为一个单位	
		sheet.setDefaultColumnWidth((short)(1832));// 16.5;  宋体 10号  字符0宽度为7pixel
//		sheet.setColumnWidth(columnIndex, width);
		int colCount = sheet.getRow(0).getLastCellNum();	
		for (int i = 0; i <= sheet.getLastRowNum(); ++i){
			sheet.getRow(i).setHeightInPoints(16.5f); // 16.5
			for (int j = 0; j < colCount; ++j) {
				sheet.setColumnWidth(j, (short)(1832));
			}
		}
		
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

}
