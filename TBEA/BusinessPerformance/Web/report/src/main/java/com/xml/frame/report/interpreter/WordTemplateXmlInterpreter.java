package com.xml.frame.report.interpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
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
import com.xml.frame.report.util.DocxQuery;
import com.xml.frame.report.util.DocxQuery.OnEach;
import com.xml.frame.report.util.DocxUtil;
import com.xml.frame.report.util.v2.core.MergeRegion;
import com.xml.frame.report.util.xml.Loop;
import com.xml.frame.report.util.xml.XmlUtil;
import com.xml.frame.report.util.xml.XmlWalker;


public class WordTemplateXmlInterpreter implements XmlInterpreter {

	
	ELParser elp;
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isWordTemplete(e)){
			return false;
		}
		
		elp = new ELParser(component);

		WordprocessingMLPackage pkg = null;
		
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
		
		if (null != pkg) {
			Map<String, List<MergeRegion>> mrs = parseMRs(e);
			writeWord(pkg, mrs);
			component.put(e, pkg);
		}
		return true;
	}
	


	private Map<String, List<MergeRegion>> parseMRs(Element e) throws Exception {
		Map<String, List<MergeRegion>> mrs = new HashMap<String, List<MergeRegion>>();
		XmlWalker.each(e.getElementsByTagName("merge"), elp, new Loop() {

			@Override
			public void on(Element elem) throws Exception {
				MergeRegion mr = new MergeRegion();
				mr.setX(XmlUtil.getIntAttr(elem, "x", elp, 0));
				mr.setY(XmlUtil.getIntAttr(elem, "y", elp, 0));
				mr.setWidth(XmlUtil.getIntAttr(elem, "width", elp, 0));
				mr.setHeight(XmlUtil.getIntAttr(elem, "height", elp, 0));
				if (!mrs.containsKey(elem.getAttribute("ref"))) {
					mrs.put(elem.getAttribute("ref"), new ArrayList<MergeRegion>());
				}
				mrs.get(elem.getAttribute("ref")).add(mr);				
			}
			
		});
		return mrs;
	}



	private void writeWord(WordprocessingMLPackage pkg, Map<String, List<MergeRegion>> mrs) throws Exception {
		MainDocumentPart documentPart = pkg.getMainDocumentPart();
		DocxQuery dq = DocxQuery.find(documentPart, FldChar.class);
		dq.each(new OnEach() {

			@Override
			public boolean on(int i, Object elem) throws Exception {
				FldChar fdChar = (FldChar) elem;
				STFldCharType tp = fdChar.getFldCharType();
				if (tp == STFldCharType.BEGIN) {
					String val = DocxUtil.getDefaultText(fdChar);
					Object obj = XmlUtil.parseELText(val, elp);
					if (obj != null && obj instanceof List) {
						tableReplace((List)obj, fdChar, mrs.get(val.replace("${", "").replace("}", "")));
					}else {
						textReplace(obj + "", fdChar);
					}
				}
				return true;
			}
			
		});
	}
	
	void prepareTable(List<List> table, Tbl tbl, int rOffset, int cOffset, int rCount, int cCount, FldChar fdChar){
		textReplace("", fdChar);
		DocxQuery dqTb = DocxQuery.q(tbl);
		for (int i = 0; i < table.size(); ++i) {
			if (i >= rCount) {
				Tr trNew = (Tr) dqTb.q(Tr.class).eq(i - 1 + rOffset).clone().val(0);
				dqTb.append(trNew);
			}
			DocxQuery dqTr = dqTb.q(Tr.class).eq(i + rOffset);
			for (int j = 0; j < table.get(i).size() && j < cCount; ++j) {
				DocxQuery dqTc = dqTr.q(Tc.class).eq(j + cOffset);
				DocxQuery dq = dqTc.q(P.class).q(R.class);
				if (dq.isEmpty()) {
					if (i == 0) {
						dq = dqTc.pre().find(R.class);
						if (dq.isEmpty()) {
							dq = dqTc.up().find(R.class);
						}
					} else {
						dq = dqTc.up().find(R.class);
						if (dq.isEmpty()) {
							dq = dqTc.pre().find(R.class);
						}
					}					
				}
				R r = (R) dq.clone().val(0);
				dqTc.q(P.class).append(r);
			}
		}
	}
	

	private void updateMrs(List<MergeRegion> mrs, int rCount, int cCount) {
		for (MergeRegion mr : mrs) {
			if (mr.getWidth() > (cCount - mr.getX())){
				mr.setWidth(cCount - mr.getX());
			}
			if (mr.getHeight() > (rCount - mr.getY())){
				mr.setHeight(rCount - mr.getY());
			}
		}
	}
	
	private void tableReplace(List<List> table, FldChar fdChar, List<MergeRegion> mrs) {
		DocxQuery dqfd = DocxQuery.q(fdChar);
		DocxQuery dqTb = dqfd.parent(Tbl.class);
		if (dqTb.isEmpty()) {
			textReplace(table.toString(), fdChar);
		}else {
			int rOffset = (int) dqfd.parent(Tr.class).pos().val(0);
			int cOffset = (int) dqfd.parent(Tc.class).pos().val(0);
			int rCount =  dqTb.q(Tr.class).size() - rOffset;
			int cCount =  dqfd.parent(Tr.class).q(Tc.class).size() - cOffset;

			prepareTable(table, (Tbl) dqTb.val(0), rOffset, cOffset, rCount, cCount, fdChar);
			
			DocxQuery dqTrs = dqTb.q(Tr.class);
			
			List<MergeRegion> mrlist = null;
			if (null != mrs) {
			
				updateMrs(mrs, rCount, cCount);
				
				mrlist = new ArrayList<MergeRegion>();
				for (MergeRegion mr : mrs) {
					mrlist.addAll(detectMerge(table, mr));
				}
			}
			for (int i = 0; i < table.size(); ++i) {
				for (int j = 0; j < table.get(i).size() && j < cCount; ++j) {	
					Tc tc = (Tc) dqTrs.eq(i + rOffset).find(Tc.class).eq(j + cOffset).val(0);
					setTc(tc, table.get(i).get(j) + "");
				}
			}
			if (null != mrs) {
				for (MergeRegion m : mrlist) {
					for (int i = 0; i < m.getWidth(); ++i) {
						DocxUtil.mergeCellsVertically((Tbl) dqTb.val(0), cOffset + m.getX() + i, rOffset + m.getY(), rOffset + m.getY() + m.getHeight() - 1);
					}
				}
				
				for (MergeRegion m : mrlist) {			
					for (int i = 0; i < m.getHeight(); ++i) {
						DocxUtil.mergeCellsHorizontalByGridSpan((Tbl) dqTb.val(0), rOffset + m.getY() + i, cOffset + m.getX(), cOffset + m.getX() + m.getWidth() - 1);
					}
				}
			}
		}
	}
	
	List<MergeRegion> detectMerge(List<List> table, MergeRegion mr){
		String preText = null;
		List<MergeRegion> mrs = new ArrayList<MergeRegion>();
		for (int i = mr.getY(); i < mr.getY() + mr.getHeight(); ++i){
			preText = "" + table.get(i).get(mr.getX());
			int startCol = mr.getX();
			for(int j = mr.getX() + 1; j < mr.getX() + mr.getWidth(); ++j){
				if (!preText.equals(table.get(i).get(j) + "")){
					preText = table.get(i).get(j) + "";
					if (j - startCol > 1){
						mrs.add(new MergeRegion(startCol, i, j - startCol, 1));
					}
					startCol = j;
				}
			}
			
			if (mr.getX() + mr.getWidth() - startCol > 1){
				mrs.add(new MergeRegion(startCol, i, mr.getX() + mr.getWidth() - startCol, 1));
			}
		}
		
		for (int i = mr.getX(); i < mr.getX() + mr.getWidth(); ++i){
			preText = table.get(mr.getY()).get(i) + "";
			int startRow = mr.getY();
			for(int j = mr.getY() + 1; j < mr.getY() + mr.getHeight(); ++j){
				if (!preText.equals(table.get(j).get(i) + "")){
					preText = table.get(j).get(i) + "";
					if (j - startRow > 1){
						mrs.add(new MergeRegion(j - 1, startRow, 1, j - startRow));
					}
					startRow = j;
				}
			}
			
			if (mr.getY() + mr.getHeight() - startRow > 1){
				mrs.add(new MergeRegion(i, startRow, 1, mr.getY() + mr.getHeight() - startRow));
			}
		}
		return mrs;
	}

	private void setTc(Tc tc, String val) {
		R r = (R) DocxQuery.q(tc, P.class).first().q(R.class).first().val(0);
		Text text = (Text) DocxQuery.q(r, Text.class).first().val(0);
		if (null == text) {
			text = new Text();
			text.setParent(r);
			r.getContent().add(text);
		}
		text.setValue(val);
	}

	private void clean(Child r) {
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
	
	private void textReplace(String text, FldChar fdChar) {
		DocxQuery dq = DocxQuery.q(fdChar);
		DocxQuery p = dq.parent();
		dq.remove();
		Text t = new Text();
		t.setValue(text);
		p.append(t);
		clean((Child) p.anyNext().val(0));
	}
}