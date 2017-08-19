package com.frame.script.maker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.frame.script.util.Util;

import junit.framework.TestCase;

public class ZipMakerTest extends TestCase {

	public void testMakeZip() throws InvalidFormatException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(new File("D:\\dev-template.xlsx"));
		String fName = "D:\\dev-template.xlsx";
		String outfName = "D:\\dev-template.xlsx";
		int index = fName.lastIndexOf("/");
		if (index < 0) {
			index = fName.lastIndexOf("\\");
		}
		if (index >= 0) {
			fName = fName.substring(index + 1);
			outfName = outfName.substring(0, index);
		} else {
			outfName = "";
		}
		index = fName.lastIndexOf(".");
		if (index >= 0) {
			fName = fName.substring(0, index);
		}

		ZipMaker zm = new ZipMaker();
		zm.setWrapperName(Util.getMD5(fName));
		byte[] zipBytes = zm.makeZip(fName, workbook);
		outfName = outfName + "/" + fName + ".zip";
		OutputStream out = new FileOutputStream(new File(outfName));
		out.write(zipBytes);
		out.close();
	}

}
