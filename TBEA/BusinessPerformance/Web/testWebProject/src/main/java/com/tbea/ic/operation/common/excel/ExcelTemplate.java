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
import com.xml.frame.report.util.ExcelHelper;

public class ExcelTemplate {
	
	
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
	
	public ExcelTemplate(HSSFWorkbook workbook){
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
	
	
	private static String resPath;
	static 
	{
		try {
			resPath = new URI(Convertor.class
					.getClassLoader().getResource("").getPath()).getPath().substring(1) + "META-INF/templates/";
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
	private static String pathCwgbjyxxjlTemplate = resPath + "cwgbjyxxjl_template.xls";
	private static String pathCpzlqkTemplate = resPath + "cpzlqk_template.xls";
	private static String pathAllCompanysNCTemplate = resPath + "companysNC_template.xls";
	
	
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
	
	public static ExcelHelper createSbdczclwcqkTemplate(SbdczclwcqkSheetType type) throws IOException {
		return createTemplate(pathSbdczclwcqkTemplate, type.ordinal(),
				SbdczclwcqkSheetType.END.ordinal());
	}

	public static ExcelHelper createJygkTemplate(JygkSheetType type)
			throws IOException {
		return createTemplate(pathJdzbTemplate, type.ordinal(),
				JygkSheetType.END.ordinal());
	}

	public static ExcelHelper createMarketTemplate(MarketSheetType type)
			throws IOException {
		return createTemplate(pathMarketTemplate, type.ordinal(),
				MarketSheetType.END.ordinal());
	}

	public static ExcelHelper createJYGKPhase2Template(
			JYGKPhase2SheetType type) throws IOException {
		return createTemplate(pathJYGKPhase2Template, type.ordinal(),
				JYGKPhase2SheetType.END.ordinal());
	}

	public static ExcelHelper createYszkgbTemplate(YszkgbSheetType type)
			throws IOException {
		return createTemplate(pathYszkgbTemplate, type.ordinal(),
				YszkgbSheetType.END.ordinal());
	}
	
	public static ExcelHelper createChgbTemplate(ChgbSheetType type)
			throws IOException {
		return createTemplate(pathChgbTemplate, type.ordinal(),
				ChgbSheetType.END.ordinal());
	}

	public static ExcelHelper createSbdddcbjpcqkTemplate(SbdddcbjpcqkSheetType type)
			throws IOException {
		return createTemplate(pathSbdddcbjpcqkTemplate, type.ordinal(),
				SbdddcbjpcqkSheetType.END.ordinal());
	}
	
	public static ExcelHelper createDzwzgbTemplate(DzwzgbSheetType type)
			throws IOException {
		return createTemplate(pathDzwzgbTemplate, type.ordinal(),
				DzwzgbSheetType.END.ordinal());
	}
	

	public static ExcelHelper createYlfxwlyddmlspcsTemplate(YlfxwlyddmlspcsSheetType type)
			throws IOException {
		return createTemplate(pathYlfxwlyddmlspcsTemplate, type.ordinal(),
				YlfxwlyddmlspcsSheetType.END.ordinal());
	}
	
	public static ExcelHelper createYlfxwgcpylnlspcsTemplate(YlfxwgcpylnlspcsSheetType type)
			throws IOException {
		return createTemplate(pathYlfxwgcpylnlspcsTemplate, type.ordinal(),
				YlfxwgcpylnlspcsSheetType.END.ordinal());
	}
	
	public static ExcelHelper createCbfxTemplate(CbfxSheetType type) throws IOException {
		return createTemplate(pathCbfxTemplate, type.ordinal(),
				CbfxSheetType.END.ordinal());
	}
	
	public static ExcelHelper createSbdscqyqkTemplate(
			SbdscqyqkSheetType type) throws IOException {
		return createTemplate(pathSbdscqyqkTemplate, type.ordinal(),
				SbdscqyqkSheetType.END.ordinal());
	}
	
	public static ExcelHelper createXnychTemplate(XnychSheetType type) throws IOException {
		return createTemplate(pathXnychTemplate, type.ordinal(),
				XnychSheetType.END.ordinal());
	}
	
	public static ExcelHelper createWgcpqkTemplate(WgcpqkSheetType type) throws IOException {
		return createTemplate(pathWgcpqkTemplate, type.ordinal(),
				XnychSheetType.END.ordinal());
	}
	
	public static ExcelHelper createNyzbscqkTemplate(
			NyzbscqkSheetType type)  throws IOException {
		return createTemplate(pathNyzbscqkTemplate, type.ordinal(),
				NyzbscqkSheetType.END.ordinal());
	}
	
	public static ExcelHelper createCwcpdlmlTemplate(CwcpdlmlSheetType type)  throws IOException {
		return createTemplate(pathCwcpdlmlTemplate, type.ordinal(),
				CwcpdlmlSheetType.END.ordinal());
	}
	

	public static ExcelHelper createCwyjsfTemplate(CwyjsfSheetType type)   throws IOException {
		return createTemplate(pathCwyjsfTemplate, type.ordinal(),
				CwyjsfSheetType.END.ordinal());
	}

	public static ExcelHelper createCwgbjyxxjlTemplate(CwgbjyxxjlSheetType type)   throws IOException {
		return createTemplate(pathCwgbjyxxjlTemplate, type.ordinal(),
				CwgbjyxxjlSheetType.END.ordinal());
	}

	public static ExcelHelper createCpzlqkTemplate(CpzlqkSheetType type) throws IOException {
		return createTemplate(pathCpzlqkTemplate, type.ordinal(),
				CpzlqkSheetType.END.ordinal());
	}

	public static ExcelHelper createCompanysNCTemplate(
			CompanysNCSheetType type) throws IOException {
		return createTemplate(pathAllCompanysNCTemplate, type.ordinal(),
				CompanysNCSheetType.END.ordinal());
	}

}
