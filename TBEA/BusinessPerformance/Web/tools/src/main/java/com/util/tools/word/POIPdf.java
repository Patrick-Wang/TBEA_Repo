package com.util.tools.word;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class POIPdf {
	public static byte[] docx2Pdf(WordprocessingMLPackage word)
			throws Exception {
//		Mapper fontMapper = new IdentityPlusMapper();
//		fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
//		fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
//		fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
//		word.setFontMapper(fontMapper);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
////		word.save(baos);
////		WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(new File(docxPath));
//		//Mapper fontMapper = new BestMatchingMapper();
//		Mapper fontMapper = new IdentityPlusMapper();
//		fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
//		fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
//		fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
//		fontMapper.put("宋体", PhysicalFonts.get("SimSun"));
//		word.setFontMapper(fontMapper);
//
////		os = new java.io.FileOutputStream(pdfPath);
//
//		FOSettings foSettings = Docx4J.createFOSettings();
//		foSettings.setWmlPackage(word);
//		Docx4J.toFO(foSettings, baos, Docx4J.FLAG_EXPORT_PREFER_XSL);


		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		word.save(baos);
		return docx2Pdf(new ByteArrayInputStream(baos.toByteArray()));
	}
	
	public static byte[] docx2Pdf(InputStream word)
			throws Exception {

		XWPFDocument document = new XWPFDocument(word);

		// 2) Prepare Pdf options
		PdfOptions options = PdfOptions.create();
//		options.fontEncoding("GB2312");

//		ExtITextFontRegistry fontProvider=ExtITextFontRegistry.getRegistry();
//		options.fontProvider(fontProvider);

		// 3) Convert XWPFDocument to HTML
		ByteArrayOutputStream baos2 =  new ByteArrayOutputStream();

		PdfConverter.getInstance().convert(document, baos2, options);

		return baos2.toByteArray();
	}
}
