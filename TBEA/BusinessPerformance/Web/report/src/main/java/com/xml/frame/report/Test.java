package com.xml.frame.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.util.tools.word.POIPdf;

public class Test {
    public static void main(String args[]) throws Exception {
        System.out.println(System.getProperty("sun.jnu.encoding"));
        System.out.println(System.getProperty("ibm.system.encoding"));
        File f = new File("D://test.docx");
        new FileOutputStream(new File("D://test.pdf")).write(POIPdf.docx2Pdf(new FileInputStream(f)));
    }
}
