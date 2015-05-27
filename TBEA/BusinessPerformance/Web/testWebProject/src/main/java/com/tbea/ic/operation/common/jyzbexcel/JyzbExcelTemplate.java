package com.tbea.ic.operation.common.jyzbexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
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
		GCY_ZBWC,
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
	


//	public static class CellFormatter{
//		public enum CellType{
//			HEADER,
//			TEXT,
//			DOUBLE,
//			PERCENT
//		}
//		JyzbExcelTemplate template;
//		Map<Integer, CellType> colTypeMap = new HashMap<Integer, CellType>();
//		private CellFormatter(JyzbExcelTemplate template){
//			this.template = template;
//		} 
//		
//		public CellFormatter addType(int col, CellType type){
//			colTypeMap.put(col, type);
//			return this;
//		}
//		
//		public CellFormatter format(int col, HSSFCell cell, String val) {
//			if (null != val) {
//				if (colTypeMap.containsKey(col)) {
//					switch (colTypeMap.get(col)) {
//					case DOUBLE:
//						BigDecimal b = new BigDecimal(Double.valueOf(val));
//						cell.setCellValue(b.setScale(1,
//								BigDecimal.ROUND_HALF_UP).doubleValue());
//						cell.setCellStyle(template.getCellStyleNumber1());
//						break;
//					case HEADER:
//						cell.setCellValue(val);
//						cell.setCellStyle(template.getCellStyleHeader());
//						break;
//					case PERCENT:
//						cell.setCellValue(String.format("%.1f",
//								Double.valueOf(val) * 100)
//								+ "%");
//						cell.setCellStyle(template.getCellStylePercent());
//						break;
//					case TEXT:
//						break;
//					default:
//						break;
//					}
//				} else {
//					BigDecimal b = new BigDecimal(Double.valueOf(val));
//					cell.setCellValue(b.setScale(0, BigDecimal.ROUND_HALF_UP)
//							.doubleValue());
//					cell.setCellStyle(template.getCellStyleNumber1());
//				}
//			} else {
//				cell.setCellValue("--");
//				cell.setCellStyle(template.getCellStyleNull());
//			}
//			return this;
//		}
//	}
	
	public static JyzbExcelTemplate createTemplate(SheetType type) throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(
				pathJdzbTemplate)));	
//		workbook.cloneSheet(type.ordinal());
//		String name = workbook.getSheetName(type.ordinal());
//		for (int i = 0; i <= SheetType.JDFDWZBYJ_MY.ordinal(); ++i){
//			workbook.removeSheetAt(0);
//		}
//		workbook.setSheetName(0, name);
		
		for (int i = 0; i < type.ordinal(); ++i){
			workbook.removeSheetAt(0);
		}
		
		for (int i = type.ordinal() + 1; i <= SheetType.JDFDWZBYJ_MY.ordinal(); ++i){
			workbook.removeSheetAt(1);
		}
		return new JyzbExcelTemplate(workbook);
	}
	
	
	HSSFWorkbook workbook;
	HSSFCellStyle cellStyleNull;
	HSSFCellStyle cellStyleNumber2;
	HSSFCellStyle cellStyleNumber4;
	HSSFCellStyle cellStyleNumber1;
	HSSFCellStyle cellStyleNumber0;
	HSSFCellStyle cellStylePercent;
	HSSFCellStyle cellStyleHeader;
	
	JyzbExcelTemplate(HSSFWorkbook workbook){
		this.workbook = workbook;
		
		cellStyleNull = workbook
				.createCellStyle();
		cellStyleNull.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleNull.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleNull.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleNull.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleNull.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		
		cellStyleNumber0 = workbook
				.createCellStyle();
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
	
//	public CellFormatter createCellFormatter(){
//		return new CellFormatter(this);
//	}
	
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
