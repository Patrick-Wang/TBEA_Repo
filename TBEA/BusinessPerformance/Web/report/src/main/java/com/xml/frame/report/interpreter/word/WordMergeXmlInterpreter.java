package com.xml.frame.report.interpreter.word;

import java.util.List;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.STFldCharType;
import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.util.tools.word.DocxQuery;
import com.util.tools.word.DocxUtil;
import com.util.tools.word.DocxQuery.OnEach;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.interpreter.Schema;
import com.xml.frame.report.interpreter.XmlInterpreter;
import com.xml.frame.report.util.xml.XmlUtil;


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
                    if (val.startsWith(WordCompileTag.MERGECOMPILE)) {
                        Object obj = XmlUtil.parseELText(val.substring(1), elp);
                        DocxUtil.textReplace(obj + "", fdChar);
                    }
                }
                return true;
            }
        });
    }
}