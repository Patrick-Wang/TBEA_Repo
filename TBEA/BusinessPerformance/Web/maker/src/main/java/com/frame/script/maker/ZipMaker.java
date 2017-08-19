package com.frame.script.maker;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.frame.script.config.excel.ConfigTable;

public class ZipMaker {

	String importTemplate = "components/import_data";
	String showTemplate = "framework/templates/dateReport/show";
	String entryTemplate = "framework/templates/dateReport/entry";
	String wrapperName = "";
	

	ByteArrayOutputStream zoaos;
	ZipOutputStream zout;
	BufferedOutputStream zbos;
	
	
	void beginZip() {
		zoaos = new ByteArrayOutputStream();
		zout = new ZipOutputStream(zoaos);
		zbos = new BufferedOutputStream(zout);
	}
	
	byte[] endZip() throws IOException {
		zout.close();
		return zoaos.toByteArray();
	}
	
	String getMakeValue(Maker maker, ConfigTable ct){
		try {
    		return maker.make(ct);
		} catch (MakerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	String getMakeValue(Maker maker){
		try {
    		return maker.makeAll();
		} catch (MakerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	void writeZip(String path, String val) throws IOException{
		zout.putNextEntry(new ZipEntry(path));
		zbos.write(val.getBytes("utf-8"));
		zbos.flush();
    	zout.closeEntry();
	}
	
	String[] getNames(String excelName, String sheetName) {
		return new String[] {
				excelName + "/" + "sql/detail/" + sheetName + ".sql",
				excelName + "/" + "xml/import/" + sheetName + "Import.xml",
				excelName + "/" + "xml/show/" + sheetName + "Show.xml",
				excelName + "/" + "xml/entry/" + sheetName + "Entry.xml",
		};
	}
	
	String[] getWrapperNames(String excelName) {
		return new String[] {
				excelName + "/" + "sql/" + excelName + ".sql",
				excelName + "/" + "xml/" + excelName + "ImportWrapper.xml",
				excelName + "/" + "xml/" + excelName + "ShowWrapper.xml",
				excelName + "/" + "xml/" + excelName + "EntryWrapper.xml",
		};
	}
	
	
	public byte[] makeZip(String excelName, XSSFWorkbook workbook) throws IOException {
		beginZip();
		
		Maker[] makers = new Maker[] {
				MakerFactory.createSqlMaker(),
				MakerFactory.createImportXmlMaker(importTemplate, wrapperName),
				MakerFactory.createShowXmlMaker(showTemplate, wrapperName),
				MakerFactory.createEntryXmlMaker(entryTemplate, wrapperName)
		};
		
		for (int i = 0; i < workbook.getNumberOfSheets(); ++i) {
			ConfigTable tb = ConfigTable.parse(workbook.getSheetAt(i));
			String[] names = this.getNames(excelName, workbook.getSheetName(i));
			for (int j = 0; j < names.length; ++j) {
				writeZip(names[j], getMakeValue(makers[j], tb));
			}
		}
		
		String[] names = this.getWrapperNames(excelName);
		for (int i = 0; i < names.length; ++i) {
			writeZip(names[i], getMakeValue(makers[i]));
		}
		
		return endZip();
	}


	public String getImportTemplate() {
		return importTemplate;
	}

	public void setImportTemplate(String importTemplate) {
		this.importTemplate = importTemplate;
	}

	public String getShowTemplate() {
		return showTemplate;
	}

	public void setShowTemplate(String showTemplate) {
		this.showTemplate = showTemplate;
	}

	public String getWrapperName() {
		return wrapperName;
	}

	public void setWrapperName(String wrapperName) {
		this.wrapperName = wrapperName;
	}
	
}
