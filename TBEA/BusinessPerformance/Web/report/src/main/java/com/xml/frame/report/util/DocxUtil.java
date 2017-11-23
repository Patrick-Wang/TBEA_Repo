package com.xml.frame.report.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Br;
import org.docx4j.wml.CTFFData;
import org.docx4j.wml.CTFFStatusText;
import org.docx4j.wml.CTFFTextInput;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;
import org.docx4j.wml.STBrType;
import org.docx4j.wml.STFldCharType;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.TcPr;
import org.docx4j.wml.TcPrInner.GridSpan;
import org.docx4j.wml.TcPrInner.HMerge;
import org.docx4j.wml.TcPrInner.VMerge;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.jvnet.jaxb2_commons.ppp.Child;

import com.frame.script.util.StringUtil;

public class DocxUtil {

	public static void setDefaultText(FldChar fldChar, String text) {
		CTFFData fdData = fldChar.getFfData();
		if (fdData != null) {
			JAXBElement<?> jaxbStatus = null;
			List<JAXBElement<?>> jaxbes = fdData.getNameOrEnabledOrCalcOnExit();
			for (JAXBElement<?> jaxb : jaxbes) {
				if (jaxb.getValue() instanceof CTFFTextInput) {
					CTFFTextInput input = (CTFFTextInput) jaxb.getValue();
					if (null != input.getDefault()) {
						input.getDefault().setVal(text);
					}
				}else if (jaxb.getValue() instanceof CTFFStatusText) {
					jaxbStatus = jaxb;
				}
			}
			if (null != jaxbStatus) {
				jaxbes.remove(jaxbStatus);
			}
		}
	}

	public static String getDefaultText(FldChar fldChar) {
		String val = "";
		CTFFData fdData = fldChar.getFfData();
		if (fdData != null) {
			List<JAXBElement<?>> jaxbes = fdData.getNameOrEnabledOrCalcOnExit();
			for (JAXBElement<?> jaxb : jaxbes) {
				if (jaxb.getValue() instanceof CTFFTextInput) {
					CTFFTextInput input = (CTFFTextInput) jaxb.getValue();
					if (null != input.getDefault() && null != input.getDefault().getVal()) {
						val = input.getDefault().getVal() + " ";
					}
				} else if (jaxb.getValue() instanceof CTFFStatusText) {
					CTFFStatusText statusText = (CTFFStatusText) jaxb.getValue();
					if (null != statusText.getVal()) {
						val = statusText.getVal();
						break;
					}
				}
			}
		}
		return val;
	}

	public static String getStatusText(FldChar fldChar) {
		String val = "";
		CTFFData fdData = fldChar.getFfData();
		if (fdData != null) {
			List<JAXBElement<?>> jaxbes = fdData.getNameOrEnabledOrCalcOnExit();
			for (JAXBElement<?> jaxb : jaxbes) {
				if (jaxb.getValue() instanceof CTFFStatusText) {
					CTFFStatusText statusText = (CTFFStatusText) jaxb.getValue();
					val = statusText.getVal();
					break;
				}
			}
		}
		return val;
	}
	
	 public static void mergeCellsHorizontalByGridSpan(Tbl tbl, int row, int fromCell,  
	            int toCell) {  
	        if (row < 0 || fromCell < 0 || toCell < 0 || fromCell == toCell) {  
	            return;  
	        }  
	        List<Tr> trList = getTblAllTr(tbl);  
	        if (row > trList.size()) {  
	            return;  
	        }  
	        Tr tr = trList.get(row);  
	        List<Tc> tcList = getTrAllCell(tr);  
	        for (int i = toCell; i >= fromCell; i--) {  
	            Tc tc = tcList.get(i);  
	            TcPr tcPr = getTcPr(tc);  
	            if (i == fromCell) {  
	                GridSpan gridSpan = tcPr.getGridSpan();  
	                if (gridSpan == null) {  
	                    gridSpan = new GridSpan();  
	                    tcPr.setGridSpan(gridSpan);  
	                }  
	                gridSpan.setVal(BigInteger.valueOf(toCell - fromCell + 1));
	            } else {  
	                tr.getContent().remove(i);  
	            }  
	        }  
	    }  
	  
	    /** 
	     * @Description: 跨列合并 
	     */  
	    public static void mergeCellsHorizontal(Tbl tbl, int row, int fromCell, int toCell) {  
	        if (row < 0 || fromCell < 0 || toCell < 0) {  
	            return;  
	        }  
	        List<Tr> trList = getTblAllTr(tbl);  
	        if (row > trList.size()) {  
	            return;  
	        }  
	        Tr tr = trList.get(row);  
	        List<Tc> tcList = getTrAllCell(tr);  
	        for (int cellIndex = fromCell, len = Math  
	                .min(tcList.size() - 1, toCell); cellIndex <= len; cellIndex++) {  
	            Tc tc = tcList.get(cellIndex);  
	            TcPr tcPr = getTcPr(tc);  
	            HMerge hMerge = tcPr.getHMerge();  
	            if (hMerge == null) {  
	                hMerge = new HMerge();  
	                tcPr.setHMerge(hMerge);  
	            }  
	            if (cellIndex == fromCell) {  
	                hMerge.setVal("restart");  
	            } else {  
	                hMerge.setVal("continue");  
	            }  
	        }  
	    }  
	  
	    /** 
	     * @Description: 跨行合并 
	     */  
	    public static void mergeCellsVertically(Tbl tbl, int col, int fromRow, int toRow) {  
	        if (col < 0 || fromRow < 0 || toRow < 0) {  
	            return;  
	        }  
	        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {  
	            Tc tc = getTc(tbl, rowIndex, col);  
	            if (tc == null) {  
	                break;  
	            }  
	            TcPr tcPr = getTcPr(tc);  
	            VMerge vMerge = tcPr.getVMerge();  
	            if (vMerge == null) {  
	                vMerge = new VMerge();  
	                tcPr.setVMerge(vMerge);  
	            }  
	            if (rowIndex == fromRow) {  
	                vMerge.setVal("restart");  
	            } else {  
	                vMerge.setVal("continue");  
	            }  
	        }  
	    }  
	  
	    /** 
	     * @Description:得到指定位置的表格 
	     */  
	    public static Tc getTc(Tbl tbl, int row, int cell) {  
	        if (row < 0 || cell < 0) {  
	            return null;  
	        }  
	        List<Tr> trList = getTblAllTr(tbl);  
	        if (row >= trList.size()) {  
	            return null;  
	        }  
	        List<Tc> tcList = getTrAllCell(trList.get(row));  
	        if (cell >= tcList.size()) {  
	            return null;  
	        }  
	        return tcList.get(cell);  
	    }  
	  
	    /** 
	     * @Description: 获取所有的单元格 
	     */  
	    public static List<Tc> getTrAllCell(Tr tr) {  
	        List<Object> objList = DocxQuery.find(tr, Tc.class).val();  
	        List<Tc> tcList = new ArrayList<Tc>();  
	        if (objList == null) {  
	            return tcList;  
	        }  
	        for (Object tcObj : objList) {  
	            if (tcObj instanceof Tc) {  
	                Tc objTc = (Tc) tcObj;  
	                tcList.add(objTc);  
	            }  
	        }  
	        return tcList;  
	    }  
	  
	    public static TcPr getTcPr(Tc tc) {  
	        TcPr tcPr = tc.getTcPr();  
	        if (tcPr == null) {  
	            tcPr = new TcPr();  
	            tc.setTcPr(tcPr);  
	        }  
	        return tcPr;  
	    }  
	  
	    /** 
	     * @Description: 得到表格所有的行 
	     */  
	    public static List<Tr> getTblAllTr(Tbl tbl) {  
	        List<Object> objList = DocxQuery.find(tbl, Tr.class).val();  
	        List<Tr> trList = new ArrayList<Tr>();  
	        if (objList == null) {  
	            return trList;  
	        }  
	        for (Object obj : objList) {  
	            if (obj instanceof Tr) {  
	                Tr tr = (Tr) obj;  
	                trList.add(tr);  
	            }  
	        }  
	        return trList;  
	    }


//	public static void mergeDocx(List<String> list,String path){
//		List<InputStream>  inList=new ArrayList<InputStream>();
//		for(int i=0;i<list.size();i++)
//			try {
//				inList.add(new FileInputStream(list.get(i)));
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		try {
//			InputStream   inputStream=mergeDocx(inList);
//			saveTemplate(inputStream,path);
//		} catch (Docx4JException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	//	 <w:p w:rsidR="007F5D8A" w:rsidRDefault="007F5D8A">
//		<w:pPr>
//			<w:widowControl/>
//			<w:jc w:val="left"/>
//		</w:pPr>
//		<w:r>
//			<w:br w:type="page"/>
//		</w:r>
//	</w:p>
	public static P createPageSeperator(){
		P pager = new P();
		PPr ppr = new PPr();
		pager.getContent().add(ppr);
		ppr.setParent(pager);

		Jc jc = new Jc();
		jc.setVal(JcEnumeration.LEFT);
		ppr.setJc(jc);
		jc.setParent(ppr);

		R r = new R();
		pager.getContent().add(r);
		r.setParent(pager);

		Br br = new Br();
		br.setType(STBrType.PAGE);
		r.getContent().add(br);
		br.setParent(r);
		return pager;
	}

	public static WordprocessingMLPackage clone (WordprocessingMLPackage raw) throws Docx4JException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		raw.save(baos);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		return WordprocessingMLPackage.load(bais);
	}

	public static WordprocessingMLPackage mergeDocx(final List<WordprocessingMLPackage> streams)
			throws Docx4JException, IOException {
		WordprocessingMLPackage target = clone(streams.get(0));
		DocxQuery dqRet = DocxQuery.q(target.getMainDocumentPart());
		for (int i = 1; i < streams.size(); ++i){
			dqRet.children().last().after(DocxQuery.q(DocxUtil.createPageSeperator()));
			dqRet.children().last().after(DocxQuery.q(streams.get(i).getMainDocumentPart()).children());
		}
		return target;
	}

	public static byte[] toPdf(WordprocessingMLPackage word)
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
		return toPdf(new ByteArrayInputStream(baos.toByteArray()));
	}

	public static byte[] toPdf(InputStream word)
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

//	public static byte[] toPdf2(InputStream wi)
//			throws Exception {
//		WordprocessingMLPackage word = WordprocessingMLPackage.load(wi);
//		Mapper fontMapper = new IdentityPlusMapper();
////		fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
////		fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
////		fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
////		fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
////		fontMapper.put("宋体", PhysicalFonts.get("simsun"));
//		word.setFontMapper(fontMapper);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		word.save(baos);
////		WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(new File(docxPath));
//		//Mapper fontMapper = new BestMatchingMapper();
//
//
////		os = new java.io.FileOutputStream(pdfPath);
//
//		FOSettings foSettings = Docx4J.createFOSettings();
//		foSettings.setWmlPackage(word);
//		Docx4J.toFO(foSettings, baos, Docx4J.FLAG_EXPORT_PREFER_XSL);
//
//		return baos.toByteArray();
//	}

//	public byte[] toPdf3(InputStream is) throws IOException, ParserConfigurationException {
//		XWPFDocument wordDocument = new XWPFDocument(is);//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));
//		//兼容2007 以上版本
////        XSSFWorkbook xssfwork=new XSSFWorkbook(is);
////		WordToFoUtils.loadDoc(is);
//		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
//				DocumentBuilderFactory.newInstance().newDocumentBuilder()
//						.newDocument());
//		wordToHtmlConverter.setPicturesManager( new PicturesManager()
//		{
//			public String savePicture(byte[] content,
//									  PictureType pictureType, String suggestedName,
//									  float widthInches, float heightInches )
//			{
//				return "test/"+suggestedName;
//			}
//		} );
//		wordToHtmlConverter.proces
//		//save pictures
//		List pics=wordDocument.getPicturesTable().getAllPictures();
//		if(pics!=null){
//			for(int i=0;i<pics.size();i++){
//				Picture pic = (Picture)pics.get(i);
//				System.out.println();
//				try {
//					pic.writeImageContent(new FileOutputStream("D:/test/"
//							+ pic.suggestFullFileName()));
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		Document htmlDocument = wordToHtmlConverter.getDocument();
//
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		DOMSource domSource = new DOMSource(htmlDocument);
//		StreamResult streamResult = new StreamResult(out);
//
//
//		TransformerFactory tf = TransformerFactory.newInstance();
//		Transformer serializer = tf.newTransformer();
//		serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//		serializer.setOutputProperty(OutputKeys.METHOD, "HTML");
//		serializer.transform(domSource, streamResult);
//		out.close();
//		writeFile(new String(out.toByteArray()), outPutFile);
//	}

//	// 插入文档
//	private static void insertDocx(MainDocumentPart main, byte[] bytes, int chunkId) {
//		try {
//			AlternativeFormatInputPart afiPart = new AlternativeFormatInputPart(
//					new PartName("/part" + chunkId + ".docx"));
//			//   afiPart.setContentType(new ContentType(CONTENT_TYPE));
//			afiPart.setBinaryData(bytes);
//			Relationship altChunkRel = main.addTargetPart(afiPart);
//
//			CTAltChunk chunk = Context.getWmlObjectFactory().createCTAltChunk();
//			chunk.setId(altChunkRel.getId());
//
//			main.addObject(chunk);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void clean(Child r) {
		List<Object> fdrs = new ArrayList<Object>();
		DocxQuery dq = DocxQuery.q(r);
		fdrs.add(r);
		STFldCharType tp = STFldCharType.BEGIN;
		while (tp != STFldCharType.END) {
			dq = dq.anyNext();
			fdrs.add(dq.val(0));
			List fdTmps = dq.find(FldChar.class).val();
			if (!fdTmps.isEmpty()) {
				tp = ((FldChar)fdTmps.get(0)).getFldCharType();
			}
		}
		DocxQuery.q(fdrs).remove();
	}

	public static void textReplace(String text, FldChar fdChar) {
		if (StringUtil.shrink(text).isEmpty()) {
			text = "";
		}
		DocxQuery dq = DocxQuery.q(fdChar);
		DocxQuery p = dq.parent();
		dq.remove();
		Text t = new Text();
		t.setValue(text);
		p.append(t);
		clean((Child) p.anyNext().val(0));
	}

//	public  static void saveTemplate(InputStream fis,String toDocPath){
//		FileOutputStream fos;
//		int bytesum = 0;
//		int byteread = 0;
//		try {
//			fos = new FileOutputStream(toDocPath);
//			byte[] buffer = new byte[1444];
//			while ( (byteread = fis.read(buffer)) != -1) {
//				bytesum += byteread; //字节数 文件大小
//				fos.write(buffer, 0, byteread);
//			}
//			fis.close();
//			fos.close();
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
