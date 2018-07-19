package com.util.tools.word;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.dml.CTPoint2D;
import org.docx4j.dml.wordprocessingDrawing.*;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.org.apache.xml.security.utils.Base64;
import org.docx4j.wml.*;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.TcPrInner.GridSpan;
import org.docx4j.wml.TcPrInner.HMerge;
import org.docx4j.wml.TcPrInner.VMerge;
import org.jvnet.jaxb2_commons.ppp.Child;

import com.util.tools.StringUtil;

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

    public static BinaryPartAbstractImage base642Image(WordprocessingMLPackage wmlp, String img) throws Exception {
        return BinaryPartAbstractImage.createImagePart(wmlp, Base64.decode(img));
    }

    public static BinaryPartAbstractImage toImage(WordprocessingMLPackage wmlp, byte[] img) throws Exception {
        return BinaryPartAbstractImage.createImagePart(wmlp, img);
    }

    public static void imageReplace(BinaryPartAbstractImage imagePart, FldChar fdChar) throws Exception {

        int docPrId = 1;
        int cNvPrId = 2;
        Inline inline = imagePart.createImageInline("Filename hint","Alternative text", docPrId, cNvPrId, false);

        DocxQuery dq = DocxQuery.q(fdChar);
        DocxQuery p = dq.parent();
        dq.remove();
        ObjectFactory factory = new ObjectFactory();
        String namespaces = " xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" "
                + "xmlns:v=\"urn:schemas-microsoft-com:vml\" "
                + "xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
                + "xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" "
                + "xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\""
                ;
        String wordml = "<wp:anchor " + namespaces + " simplePos=\"0\" allowOverlap=\"1\" distR=\"114300\" distB=\"0\" behindDoc=\"0\" distT=\"0\" layoutInCell=\"1\" locked=\"0\" relativeHeight=\"251658240\" distL=\"114300\">\n" +
        "    <wp:simplePos x=\"0\" y=\"0\"/>\n" +
        "    <wp:positionH relativeFrom=\"column\">\n" +
        "        <wp:posOffset>41910</wp:posOffset>\n" +
        "    </wp:positionH>\n" +
        "    <wp:positionV relativeFrom=\"paragraph\">\n" +
        "        <wp:posOffset>-5715</wp:posOffset>\n" +
        "    </wp:positionV>\n" +
        "    <wp:extent cx=\"4286250\" cy=\"3810000\"/>\n" +
        "    <wp:effectExtent r=\"0\" b=\"0\" t=\"0\" l=\"0\"/>\n" +
        "    <wp:wrapSquare wrapText=\"bothSides\"/>\n" +
        "    <wp:docPr id=\"1\" descr=\"Alternative text\" name=\"Filename hint\"/>\n" +
        "</wp:anchor>\n";
//        JAXBElement jac = (JAXBElement) org.docx4j.XmlUtils.unmarshalString(wordml);
        Anchor ac = new Anchor();


        ac.setSimplePosAttr(false);
        CTPoint2D point = new CTPoint2D();
        point.setX(0);
        point.setY(0);
        ac.setSimplePos(point);
        CTPosH ph = new CTPosH();
        STRelFromH rfh = STRelFromH.fromValue("column");
        ph.setRelativeFrom(rfh);
        ph.setPosOffset(0);
        ac.setPositionH(ph);

        CTPosV pv = new CTPosV();
        STRelFromV rfv = STRelFromV.fromValue("paragraph");
        pv.setRelativeFrom(rfv);
        pv.setPosOffset(0);
        ac.setPositionV(pv);

        ac.setBehindDoc(false);
        ac.setLayoutInCell(true);
        ac.setAllowOverlap(false);
        ac.setLocked(false);
        ac.setDistB(inline.getDistB());
        ac.setDistL(inline.getDistL());
        ac.setDistT(inline.getDistT());
        ac.setDistR(inline.getDistR());
        ac.setExtent(inline.getExtent());
        ac.setEffectExtent(inline.getEffectExtent());
//        CTWrapSquare ws = new CTWrapSquare();
//        STWrapText stwt = STWrapText.fromValue("bothSides");
//        ws.setWrapText(stwt);
//        ac.setWrapSquare(ws);

        CTWrapTopBottom ctb = new CTWrapTopBottom();

        ac.setWrapTopAndBottom(ctb);

        ac.setDocPr(inline.getDocPr());
        ac.setCNvGraphicFramePr(inline.getCNvGraphicFramePr());

        ac.setGraphic(inline.getGraphic());
        Drawing drawing = factory.createDrawing();

//        org.docx4j.XmlUtils.marshaltoString()

        drawing.getAnchorOrInline().add(ac);

//        ac.setParent(drawing);

        p.append(drawing);

        clean((Child) p.anyNext().val(0));
    }

//    /**
//     *  创建一个对象工厂并用它创建一个段落和一个可运行块R.
//     *  然后将可运行块添加到段落中. 接下来创建一个图画并将其添加到可运行块R中. 最后我们将内联
//     *  对象添加到图画中并返回段落对象.
//     *
//     * @param   inline 包含图片的内联对象.
//     * @return  包含图片的段落
//     */
//    private static P addInlineImageToParagraph(Inline inline) {
//        // 添加内联对象到一个段落中
//
//        P paragraph = factory.createP();
//        R run = factory.createR();
//        paragraph.getContent().add(run);
//        Drawing drawing = factory.createDrawing();
//        run.getContent().add(drawing);
//        drawing.getAnchorOrInline().add(inline);
//        return paragraph;
//    }

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
