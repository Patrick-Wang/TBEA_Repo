package com.frame.script.maker;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.frame.script.util.Util;

public class CodeMaker {
	public byte[] make(String fName, XSSFWorkbook workbook) throws IOException {
		ZipMaker zm = new ZipMaker();
		zm.setWrapperName(Util.getMD5(fName));
		byte[] zipBytes = zm.makeZip(fName, workbook);
		return zipBytes;
	}
}
