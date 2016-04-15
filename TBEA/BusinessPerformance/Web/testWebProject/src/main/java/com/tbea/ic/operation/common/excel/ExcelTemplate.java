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
	
	HSSFWorkbook workbook;
	HSSFCellStyle cellStyleNull;
	HSSFCellStyle cellStyleNumber2;
	HSSFCellStyle cellStyleNumber4;
	HSSFCellStyle cellStyleNumber1;
	HSSFCellStyle cellStyleNumber0;
	HSSFCellStyle cellStylePercent;
	HSSFCellStyle cellStyleHeader;
	
	ExcelTemplate(HSSFWorkbook workbook){
		this.workbook = workbook;
		
		cellStyleNull = workbook
				.createCellStyle();
		cellStyleNull.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleNull.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleNull.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleNull.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleNull.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		
		cellStyleNumber0 = workbook.createCellStyle();
//		cellStyleNumber0.setDataFormat(HSSFDataFormat
//				.getBuiltinFormat("0"));
		cellStyleNumber0.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleNumber0.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleNumber0.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleNumber0.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		cellStyleNumber0.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleNumber2 = workbook
				.createCellStyle();
//		cellStyleNumber2.setDataFormat(HSSFDataFormat
//				.getBuiltinFormat("0.00"));
		cellStyleNumber2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleNumber2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleNumber2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleNumber2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		cellStyleNumber2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleNumber4 = workbook
				.createCellStyle();
//		cellStyleNumber4.setDataFormat(HSSFDataFormat
//				.getBuiltinFormat("0.0000"));
		cellStyleNumber4.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleNumber4.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleNumber4.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleNumber4.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		cellStyleNumber4.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleNumber1 = workbook
				.createCellStyle();
//		cellStyleNumber1.setDataFormat(HSSFDataFormat
//				.getBuiltinFormat("0.0"));
		cellStyleNumber1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleNumber1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleNumber1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleNumber1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		cellStyleNumber1.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		
		cellStylePercent = workbook
				.createCellStyle();
//		cellStylePercent.setDataFormat(HSSFDataFormat
//				.getBuiltinFormat("0.00%"));
		cellStylePercent.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
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
	public HSSFCellStyle getCellStyleNumber2() {
		return cellStyleNumber2;
	}
	
	public HSSFCellStyle getCellStyleNumber4() {
		return cellStyleNumber4;
	}
	
	public HSSFCellStyle getCellStyleNumber1() {
		return cellStyleNumber1;
	}
	
	public HSSFCellStyle getCellStyleNumber0() {
		return cellStyleNumber0;
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
	
	public void write(HttpServletResponse response, String fileName) throws IOException{
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition","attachment;filename=\""+ java.net.URLEncoder.encode(fileName, "UTF-8")  +"\"");
		this.write(response.getOutputStream());
	}
}
