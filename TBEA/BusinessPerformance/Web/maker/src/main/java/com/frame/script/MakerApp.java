package com.frame.script;

import com.frame.script.maker.ZipMaker;
import com.frame.script.util.Util;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MakerApp {
    public static void main(String args[]) throws IOException, InvalidFormatException {
        File f = new File(args[0]);
        XSSFWorkbook workbook = new XSSFWorkbook(f);
        String fName = f.getAbsolutePath();
        String outfName = f.getAbsolutePath();
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
