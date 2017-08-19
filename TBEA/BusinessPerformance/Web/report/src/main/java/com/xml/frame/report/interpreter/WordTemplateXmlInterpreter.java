package com.xml.frame.report.interpreter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.CTFFData;
import org.docx4j.wml.CTFFTextInput;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.STFldCharType;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.jvnet.jaxb2_commons.ppp.Child;
import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.util.DocxUtil;
import com.xml.frame.report.util.XmlUtil;


public class WordTemplateXmlInterpreter implements XmlInterpreter {

	
	ELParser elp;
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isWordTemplete(e)){
			return false;
		}
		
		elp = new ELParser(component);

		WordprocessingMLPackage pkg = null;
		OutputStream os = null;
		
		if (e.hasAttribute("dest")) {
			Object dest = XmlUtil.parseELText(e.getAttribute("dest"), elp);
			if (null != dest) {
				if (dest instanceof OutputStream) {
					os = (OutputStream) dest;
				} else if (dest instanceof String) {
					os =  new FileOutputStream(new File((String) dest));
				}
			}
		} 
		
		if (os != null) {
			if (e.hasAttribute("src")) {
				Object src = XmlUtil.parseELText(e.getAttribute("src"), elp);
				if (null != src) {
					if (src instanceof WordprocessingMLPackage) {
						pkg = (WordprocessingMLPackage) src;
					} else if (src instanceof String) {
						pkg = WordprocessingMLPackage.load(new File((String) src));
					}
				}
			}
		}
		
		if (null != pkg) {
			writeWord(pkg);
			pkg.save(os);
		}
		return true;
	}
	


	private void writeWord(WordprocessingMLPackage pkg) throws Exception {
		MainDocumentPart documentPart = pkg.getMainDocumentPart();
		List<Object> objs = DocxUtil.getElementsByType(documentPart, FldChar.class);
		for (Object fldChar : objs) {
			FldChar fdChar = (FldChar) fldChar;
			STFldCharType tp = fdChar.getFldCharType();
			if (tp == STFldCharType.BEGIN) {
				String val = DocxUtil.getDefaultText(fdChar);
				Object obj = XmlUtil.parseELText(val, elp);
				if (obj != null && obj instanceof List) {
					tableReplace((List)obj, fdChar);
				}else {
					textReplace(obj + "", fdChar);
				}
			}
		}
	}
	
	void prepareTable(List<List> table, Tbl tbl, int rOffset, int cOffset, int rCount, int cCount, FldChar fdChar){
		textReplace("", fdChar);
		for (int i = 0; i < table.size(); ++i) {
			if (i >= rCount) {
				Tr trNew = (Tr) DocxUtil.clone(DocxUtil.tr(tbl, i - 1 + rOffset));
				trNew.setParent(tbl);
				tbl.getContent().add(trNew);
			}
			Tr tr = DocxUtil.tr(tbl, i + rOffset);
			
			for (int j = 0; j < table.get(i).size() && j < cCount; ++j) {
				Tc tc = DocxUtil.tc(tr, j + cOffset);
				P p = (P) DocxUtil.getFirstChild(tc, P.class);
				R r = (R) DocxUtil.getFirstChild(p, R.class);
				if (null == r) {
					List<Object> rs = null;
					if (i == 0) {
						rs = DocxUtil.getElementsByType(DocxUtil.preTc(tc), R.class);
						if (rs.isEmpty()) {
							rs = DocxUtil.getElementsByType(DocxUtil.upTc(tc), R.class);
						}
					} else {
						rs = DocxUtil.getElementsByType(DocxUtil.upTc(tc), R.class);
						if (rs.isEmpty()) {
							rs = DocxUtil.getElementsByType(DocxUtil.preTc(tc), R.class);
						}
					}					
					r = (R) DocxUtil.clone((Child) rs.get(0));
					r.setParent(p);
					p.getContent().add(r);
				}
			}
		}
	}
	

	private void tableReplace(List<List> table, FldChar fdChar) {
		Tbl tbl = (Tbl) DocxUtil.getParent(fdChar, Tbl.class);
		if (null == tbl) {
			textReplace(table.toString(), fdChar);
		}else {
			
			Tr tr = (Tr) DocxUtil.getParent(fdChar, Tr.class);
			Tc tc = (Tc) DocxUtil.getParent(fdChar, Tc.class);
			int rOffset = DocxUtil.findPosition(tr, Tr.class);
			int cOffset = DocxUtil.findPosition(tc, Tc.class);
			int rCount =  DocxUtil.rowCount(tbl, tr);
			int cCount =  DocxUtil.columnCount(tr, tc);

			prepareTable(table, tbl, rOffset, cOffset, rCount, cCount, fdChar);

			for (int i = 0; i < table.size(); ++i) {
				for (int j = 0; j < table.get(i).size() && j < cCount; ++j) {					
					tr = DocxUtil.tr(tbl, i + rOffset);
					tc = DocxUtil.tc(tr, j + cOffset);
					setTc(tc, table.get(i).get(j) + "");
				}
			}
		}
	}

	private void setTc(Tc tc, String val) {
		P p = (P) DocxUtil.getFirstChild(tc, P.class);
		R r = (R) DocxUtil.getFirstChild(p, R.class);
		Text text = (Text) DocxUtil.getFirstChild(r, Text.class);
		if (null == text) {
			text = new Text();
			text.setParent(r);
			r.getContent().add(text);
		}
		text.setValue(val);
	}

	private void clean(Child r) {
		List<Object> fdrs = new ArrayList<Object>();
		Object rNext = r;
		fdrs.add(r);
		STFldCharType tp = STFldCharType.BEGIN;
		while (tp != STFldCharType.END) {
			rNext = DocxUtil.nextSibling((Child) rNext);
			fdrs.add(rNext);
			if (rNext instanceof ContentAccessor) {
				List fdTmps = DocxUtil.getElementsByType(rNext, FldChar.class);
				if (!fdTmps.isEmpty()) {
					tp = ((FldChar)fdTmps.get(0)).getFldCharType();
				}
			}
		}
		
		for (int i = 0; i < fdrs.size(); ++i) {
			DocxUtil.removeFromDocument((Child) fdrs.get(i));
		}
	}
	
	private void textReplace(String text, FldChar fdChar) {
		R r = (R) fdChar.getParent();
		DocxUtil.removeFromDocument(fdChar);
		Text t = new Text();
		t.setValue(text);
		t.setParent(r);
		List<Object> cont = (List<Object>) r.getContent();
		cont.add(t);
		clean((Child) DocxUtil.nextSibling(r));
	}
}