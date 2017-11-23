package com.xml.frame.report.interpreter.word;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Br;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.STBrType;
import org.docx4j.wml.STFldCharType;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.frame.script.util.StringUtil;
import com.frame.script.util.TypeUtil;
import com.util.tools.xml.Loop;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.interpreter.Schema;
import com.xml.frame.report.interpreter.XmlInterpreter;
import com.xml.frame.report.util.DocxQuery;
import com.xml.frame.report.util.DocxQuery.OnEach;
import com.xml.frame.report.util.DocxUtil;
import com.xml.frame.report.util.v2.core.MergeRegion;
import com.xml.frame.report.util.xml.XmlElWalker;
import com.xml.frame.report.util.xml.XmlUtil;


public class WordTemplateXmlInterpreter implements XmlInterpreter {
	
	ELParser elp;
	AbstractXmlComponent component;
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {

		if (!Schema.isWordTemplete(e)){
			return false;
		}
		
		elp = new ELParser(component);
		this.component = component;
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
			preHandleWord(pkg, e);
			completePreHandle(pkg);
			writeWord(pkg, mrs);
            component.put(e, pkg);
		}
		return true;
	}

	private void completePreHandle(WordprocessingMLPackage pkg) throws Exception {
		DocxQuery dq = DocxQuery.find(pkg.getMainDocumentPart(), FldChar.class);
		dq.each(new OnEach() {
			@Override
			public boolean on(int i, Object elem) throws Exception {
				FldChar fdChar = (FldChar) elem;
				STFldCharType tp = fdChar.getFldCharType();
				if (tp == STFldCharType.BEGIN) {
					String el = DocxUtil.getDefaultText(fdChar);
					if (el.startsWith(WordCompileTag.PRECOMPILE)) {
						DocxUtil.setDefaultText(fdChar, el.substring(1));
					}
				}
				return true;
			}
		});
	}

	private void preHandleWord(WordprocessingMLPackage pkg, Element e) throws Exception {
	    XmlElWalker.each(e.getElementsByTagName("preHandler"), elp, new Loop(){

            @Override
            public void on(Element elem) throws Exception {
                if ("spreadWord".equals(elem.getAttribute("type"))){
                    handleSpreadWord(pkg);
                }
            }
        });
    }

    private Integer countPage(DocxQuery body) throws Exception {
		Integer[] pgCount = new Integer[]{1};
		body.find(Br.class).each(new OnEach() {
			@Override
			public boolean on(int i, Object elem) throws Exception {
				Br br = (Br) elem;
				if (br.getType() == STBrType.PAGE){
					++pgCount[0];
				}
				return true;
			}
		});
		return pgCount[0];
	}

	private Integer preCompile(DocxQuery dq, Map<String, List> tableGroup) throws Exception {
		Integer[] maxCount = new Integer[]{1};
		dq.each(new OnEach() {

			@Override
			public boolean on(int i, Object elem) throws Exception {
				FldChar fdChar = (FldChar) elem;
				STFldCharType tp = fdChar.getFldCharType();
				if (tp == STFldCharType.BEGIN) {
					DocxQuery dqFd = DocxQuery.q(elem);
					DocxQuery dqTb = dqFd.parent(Tbl.class);
					if (!dqTb.isEmpty()) {
						String el = DocxUtil.getDefaultText(fdChar);
						if (el.startsWith(WordCompileTag.PRECOMPILE)){
							el = el.substring(1);
							Object val = XmlUtil.parseELText(el, elp);
							if (val instanceof List){
								List table = (List) val;
								int rOffset = (int) dqFd.parent(Tr.class).pos().val(0);
								int rCount =  dqTb.children(Tr.class).size() - rOffset;
								Integer tableCount = table.size() / rCount;
								tableCount += (((table.size() % rCount) > 0) ? 1 : 0);
								maxCount[0] = ((maxCount[0] > tableCount) ? maxCount[0] : tableCount);
								tableGroup.put(el, table);
							}
						}
					}
				}
				return true;
			}
		});
		return maxCount[0];
	}

    private void handleSpreadWord(WordprocessingMLPackage pkg) throws Exception {
		DocxQuery body = DocxQuery.q(pkg.getMainDocumentPart());

        Map<String, List> tableGroup = new HashMap<String, List>();

        Integer docCount = preCompile(body.find(FldChar.class), tableGroup);

        spreadWord(body, docCount - 1, tableGroup);

		component.local("pageCount", countPage(body));

		component.local("docCount", docCount);
    }



    private void spreadWord(DocxQuery dqBody, Integer newDocCount, Map<String, List> tableGroup) throws Exception {
       	DocxQuery children = dqBody.children();
		DocxQuery docTemplate = children.clone();
		DocxQuery pagerTemplate = DocxQuery.q(DocxUtil.createPageSeperator());
        updateTableGroup(children, 0, tableGroup);
        for (int i = 0; i < newDocCount; ++i){
            DocxQuery newPg = docTemplate.clone();
			DocxQuery pager = pagerTemplate.clone();
			dqBody.children().last().after(pager);
			dqBody.children().last().after(newPg);
            updateTableGroup(newPg, (i + 1), tableGroup);
        }
    }

    private void updateTableGroup(DocxQuery dq, int pgIndex, Map<String, List> tableGroup) throws Exception {

        dq.find(FldChar.class).each(new OnEach() {

            @Override
            public boolean on(int i, Object elem) throws Exception {
                FldChar fdChar = (FldChar) elem;
                STFldCharType tp = fdChar.getFldCharType();
                if (tp == STFldCharType.BEGIN) {
                    DocxQuery dqFd = DocxQuery.q(elem);
                    DocxQuery dqTb = dqFd.parent(Tbl.class);;
                    if (!dqTb.isEmpty()) {
                        String el = DocxUtil.getDefaultText(fdChar);
                        if (el.startsWith("#")){
                        	el = el.substring(1);
                        	if (tableGroup.containsKey(el)) {
								List table = tableGroup.get(el);
								int rOffset = (int) dqFd.parent(Tr.class).pos().val(0);
								int rCount = dqTb.children(Tr.class).size() - rOffset;
								if (table.size() > pgIndex * rCount) {
									el = "${" + getELObjName(el) + ".subList[" + (pgIndex * rCount) + "][" + Math.min(table.size(), pgIndex * rCount + rCount) + "]}";
									DocxUtil.setDefaultText(fdChar, el);
								} else {
									DocxUtil.setDefaultText(fdChar, "");
								}
							}
                        }
                    }
                }
                return true;
            }
        });
    }


    private Map<String, List<MergeRegion>> parseMRs(Element e) throws Exception {
		Map<String, List<MergeRegion>> mrs = new HashMap<String, List<MergeRegion>>();
		XmlElWalker.each(e.getElementsByTagName("merge"), elp, new Loop() {

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


    private String getELText(String el){
	    String objExp = el.replace("${", "").replace("}", "");
        return StringUtil.trim(objExp);
    }
    
	private String getELObjName(String el){
	    String objExp = el.replace("${", "").replace("}", "");
	    int index = objExp.indexOf("[");
	    if (index > 0){
	        objExp = objExp.substring(0, index);
        }
        return StringUtil.trim(objExp);
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
					if (!val.startsWith(WordCompileTag.MERGECOMPILE)) {
						System.out.println(i + " " + val + " begin ----------------------");
						Object obj = XmlUtil.parseELText(val, elp);
						if (obj != null && obj instanceof List) {
							tableReplace((List) obj, fdChar, mrs.get(getELText(val)));
						} else if(null != obj &&
								(TypeUtil.isDouble(obj.getClass()) ||
								TypeUtil.isInt(obj.getClass()) ||
								TypeUtil.isLong(obj.getClass()) ||
								TypeUtil.isBoolean(obj.getClass()) ||
								TypeUtil.isString(obj.getClass()))) {
							DocxUtil.textReplace(obj + "", fdChar);
						} else {
							DocxUtil.textReplace("", fdChar);
						}

						System.out.println(i + " end ----------------------");
					}
				}
				return true;
			}
		});
	}
	
	void prepareTable(List<List> table, Tbl tbl, int rOffset, int cOffset, int rCount, int cCount, FldChar fdChar){
		DocxUtil.textReplace("", fdChar);
		DocxQuery dqTb = DocxQuery.q(tbl);
		for (int i = 0; i < table.size(); ++i) {
			if (i >= rCount) {
				Tr trNew = (Tr) dqTb.children(Tr.class).eq(i - 1 + rOffset).clone().val(0);
				dqTb.append(trNew);
			}
			DocxQuery dqTr = dqTb.children(Tr.class).eq(i + rOffset);
			for (int j = 0; j < table.get(i).size() && j < cCount; ++j) {
				DocxQuery dqTc = dqTr.children(Tc.class).eq(j + cOffset);
				DocxQuery dq = dqTc.children(P.class).children(R.class);
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
				dqTc.children(P.class).append(r);
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
			DocxUtil.textReplace(table.toString(), fdChar);
		}else {
			int rOffset = (int) dqfd.parent(Tr.class).pos().val(0);
			int cOffset = (int) dqfd.parent(Tc.class).pos().val(0);
			int rCount =  dqTb.children(Tr.class).size() - rOffset;
			int cCount =  dqfd.parent(Tr.class).children(Tc.class).size() - cOffset;

			prepareTable(table, (Tbl) dqTb.val(0), rOffset, cOffset, rCount, cCount, fdChar);
			
			DocxQuery dqTrs = dqTb.children(Tr.class);
			
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
		R r = (R) DocxQuery.children(tc, P.class).first().children(R.class).first().val(0);
		Text text = (Text) DocxQuery.children(r, Text.class).first().val(0);
		if (null == text) {
			text = new Text();
			text.setParent(r);
			r.getContent().add(text);
		}
		text.setValue(val);
	}


}