package com.xml.frame.report.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.wml.CTFFData;
import org.docx4j.wml.CTFFTextInput;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.TcPr;
import org.docx4j.wml.TcPrInner.GridSpan;
import org.docx4j.wml.TcPrInner.HMerge;
import org.docx4j.wml.TcPrInner.VMerge;
import org.docx4j.wml.Tr;

public class DocxUtil {
	
	public static String getDefaultText(FldChar fldChar) {
		String val = "";
		CTFFData fdData = fldChar.getFfData();
		List<JAXBElement<?>> jaxbes = fdData.getNameOrEnabledOrCalcOnExit();
		for (JAXBElement<?> jaxb : jaxbes) {
			if (jaxb.getValue() instanceof CTFFTextInput) {
				CTFFTextInput input = (CTFFTextInput) jaxb.getValue();
				val = input.getDefault().getVal();
				break;
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
}
