package com.tbea.ic.operation.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.SheetUtil;

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
	HSSFCellStyle cellStyleHeader;
	
	ExcelTemplate(HSSFWorkbook workbook){
		this.workbook = workbook;
		
		HSSFFont font10 = workbook.createFont();    
		font10.setFontName("宋体");    
		font10.setFontHeightInPoints((short) 10);//设置字体大小 
		
		cellStyleCenter = workbook
				.createCellStyle();
		cellStyleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		cellStyleCenter.setFont(font10);
		
		cellStyleDefault = workbook
				.createCellStyle();
		cellStyleDefault.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleDefault.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleDefault.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleDefault.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleDefault.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		cellStyleDefault.setFont(font10);

		cellStyleHeader = workbook.createCellStyle();
		HSSFFont font10Bold = workbook.createFont();    
		font10Bold.setFontName("宋体");    
		font10Bold.setFontHeightInPoints((short) 10);//设置字体大小 
		font10Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyleHeader.setFont(font10Bold);
		cellStyleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		
		cellStyleCenterHeader = workbook
				.createCellStyle();
		cellStyleCenterHeader.setFont(font10Bold);
		cellStyleCenterHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleCenterHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyleCenterHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleCenterHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleCenterHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleCenterHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
			
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

}
