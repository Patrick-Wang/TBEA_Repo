package com.xml.frame.report.interpreter;

import com.frame.script.el.ELParser;
import com.frame.script.util.StringUtil;
import com.util.tools.xml.Loop;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.util.DocxQuery;
import com.xml.frame.report.util.DocxQuery.OnEach;
import com.xml.frame.report.util.DocxUtil;
import com.xml.frame.report.util.v2.core.MergeRegion;
import com.xml.frame.report.util.xml.XmlElWalker;
import com.xml.frame.report.util.xml.XmlUtil;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.jvnet.jaxb2_commons.ppp.Child;
import org.w3c.dom.Element;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WordMergeXmlInterpreter implements XmlInterpreter {

	ELParser elp;
	AbstractXmlComponent component;
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {

		if (!Schema.isWordMerge(e)){
			return false;
		}
		
		elp = new ELParser(component);
		this.component = component;
		List<WordprocessingMLPackage> wordlist = null;
		if (e.hasAttribute("src")) {
			Object src = component.getVar(e.getAttribute("src"));
			if (null == src){
				src = XmlUtil.parseELText(e.getAttribute("src"), elp);
			}
			if (src instanceof List){
			    wordlist = (List) src;
            }
		}
		
		if (null != wordlist) {
            WordprocessingMLPackage wmlpkg = DocxUtil.mergeDocx(wordlist);

            compileMergeWord(wmlpkg);

            component.put(e, wmlpkg);
		}
		return true;
	}

    private void compileMergeWord(WordprocessingMLPackage wmlpkg) throws Exception {
        MainDocumentPart documentPart = wmlpkg.getMainDocumentPart();
        DocxQuery dq = DocxQuery.find(documentPart, FldChar.class);
        dq.each(new OnEach() {

            @Override
            public boolean on(int i, Object elem) throws Exception {
                FldChar fdChar = (FldChar) elem;
                STFldCharType tp = fdChar.getFldCharType();
                if (tp == STFldCharType.BEGIN) {
                    String val = DocxUtil.getDefaultText(fdChar);
                    if (val.startsWith("M")) {
                        Object obj = XmlUtil.parseELText(val.substring(1), elp);
                        DocxUtil.textReplace(obj + "", fdChar);
                    }
                }
                return true;
            }
        });
    }
}