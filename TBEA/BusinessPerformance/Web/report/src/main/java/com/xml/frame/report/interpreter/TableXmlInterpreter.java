package com.xml.frame.report.interpreter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.frame.script.el.ELParser;
import com.frame.script.util.StringUtil;
import com.util.tools.DateUtil;
import com.util.tools.ListUtil;
import com.util.tools.MathUtil;
import com.util.tools.Pair;
import com.util.tools.xml.Loop;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.entity.Table;
import com.xml.frame.report.util.xml.XmlElWalker;
import com.xml.frame.report.util.xml.XmlUtil;


public class TableXmlInterpreter implements XmlInterpreter {

    ELParser elp;

    ListXmlInterpreter listInterpreter = new ListXmlInterpreter();

    List<Integer> tempCols;
    List<Pair<Integer, Element>> delayCols = new ArrayList<Pair<Integer, Element>>();

    private void putTemp(Integer col) {
        if (tempCols == null) {
            tempCols = new ArrayList<Integer>();
        }

        boolean inserted = false;
        for (int i = 0; i < tempCols.size(); ++i) {
            if (tempCols.get(i) > col) {
                tempCols.add(i, col);
                inserted = true;
                break;
            } else if (tempCols.get(i).equals(col)) {
                inserted = true;
                break;
            }
        }

        if (!inserted) {
            tempCols.add(col);
        }
    }

    private void clearTemps(List<List<Object>> tbValues) {
        if (tempCols != null) {

            for (int i = tempCols.size() - 1; i >= 0; --i) {
                tbValues.remove(tempCols.get(i).intValue());
            }
            tempCols.clear();
        }
    }

    @Override
    public boolean accept(AbstractXmlComponent component, Element e)
            throws Exception {

        if (!Schema.isTable(e)) {
            return false;
        }
        // ReportLogger.trace().debug(component.getConfig().getTagName() + " : "
        // + XmlUtil.toStringFromDoc(e));
        elp = new ELParser(component);
        Table tb = new Table();
        tb.setIds((List) component.getVar(e.getAttribute("rowIds")));
        List<List<Object>> tbValues = new ArrayList<List<Object>>();
        tb.setValues(tbValues);

        if (e.hasAttribute("table")) {
            Object obj = XmlUtil.getObjectAttr(e, "table", elp);
            if (obj instanceof List) {
                parseTable((List) obj, tb);
            }
        }

        XmlElWalker.eachChildren(e, elp, new Loop() {

            @Override
            public void on(Element elem) throws Exception {
                if ("col".equals(elem.getTagName())
                        || "list".equals(elem.getTagName())) {
                    parseCol(component, tb, elem);
                } else if ("sumRow".equals(elem.getTagName())) {
                    parseSumRow(component, tb, elem);
                } else if ("sumCol".equals(elem.getTagName())) {
                    parseSumCol(component, tb, elem);
                } else if ("minusCol".equals(elem.getTagName())) {
                    parseMinusmCol(component, tb, elem);
                } else if ("divRow".equals(elem.getTagName())) {
                    parseDivRow(component, tb, elem);
                } else if ("divCol".equals(elem.getTagName())) {
                    parseDivCol(component, tb, elem);
                } else if ("growthRates".equals(elem.getTagName())) {
                    parseGrowthRates(component, tb, elem);
                } else if ("copyCol".equals(elem.getTagName())) {
                    parseCopyCol(tb, elem);
                }
            }

        });

        handleDelays(component, tb);

        if (e.hasAttribute("order")) {
            parseOrderby(component, tb, e);
        }

        this.clearTemps(tbValues);
        component.put(e, tb);
        return true;
    }


    protected void parseSumCol(AbstractXmlComponent component, Table tb,
                               Element elem) throws Exception {
        Integer target = XmlUtil.getIntAttr(elem, "toCol", elp, null);
        List<Object> targetCol = tb.getValues().get(target);
        if (elem.hasAttribute("from")) {
            List<Integer> from = XmlUtil.toIntList(elem.getAttribute("from"), elp);
            List<List<Object>> cols = new ArrayList<List<Object>>();
            for (Integer col : from) {
                cols.add(tb.getValues().get(col));
            }
            List<Double> tmpRow = new ArrayList<Double>();
            for (int j = 0; j < targetCol.size(); ++j) {
                tmpRow.clear();
                for (List<Object> col : cols) {
                    tmpRow.add(MathUtil.o2d(col.get(j)));
                }
                targetCol.set(j, MathUtil.sum(tmpRow));
            }
        } else {
            Integer first = XmlUtil.getIntAttr(elem, "first", elp, null);
            Integer second = XmlUtil.getIntAttr(elem, "second", elp, null);

            List<Object> firstCol = tb.getValues().get(first);
            List<Object> secondCol = tb.getValues().get(second);
            for (int j = 0; j < targetCol.size(); ++j) {
                targetCol.set(j, MathUtil.sum(
                        MathUtil.o2d(firstCol.get(j)),
                        MathUtil.o2d(secondCol.get(j))));
            }
        }


    }

    private void handleDelays(AbstractXmlComponent component, Table tb)
            throws Exception {
        for (Pair<Integer, Element> p : delayCols) {
            this.invokeFormul(tb.getValues().get(p.getFirst()), component, tb,
                    p.getSecond());
        }
    }

    private void parseOrderby(AbstractXmlComponent component, Table tb,
                              Element e) throws Exception {
        String orders = e.getAttribute("order");
        String[] orderList = orders.split(",");
        boolean asc = true;
        if (!orderList[0].contains("asc")) {
            asc = false;
        }
        String colVal = orderList[0].replaceAll("asc", "").replaceAll("desc",
                "");
        Integer col = MathUtil.toInteger(StringUtil.trim(colVal));
        if (null != col) {
            List<Comparable> copies = new ArrayList<Comparable>();
            copies.addAll((List) tb.getValues()
                    .get(col));
            List<Integer> newOrder = null;
            if (asc) {
                newOrder = parseAscOrder(copies);
            } else {
                newOrder = parseDescOrder(copies);
            }

            reorderTable(newOrder, tb);
        }
    }

    private List<Object> reorderList(List<Integer> newOrder, List oldVals,
                                     List target) {
        target.clear();
        for (int i = 0; i < newOrder.size(); ++i) {
            target.add(oldVals.get(newOrder.get(i)));
        }
        return oldVals;
    }

    private void reorderTable(List<Integer> newOrder, Table tb) {
        List workList = new ArrayList();
        List depList;
        for (int i = 0; i < tb.getValues().size(); ++i) {
            depList = reorderList(newOrder, tb.getValues().get(i), workList);
            tb.getValues().set(i, workList);
            workList = depList;
        }
        depList = reorderList(newOrder, tb.getIds(), workList);
        tb.setIds(workList);
    }

    private List<Integer> parseDescOrder(List<Comparable> copies) {
        List<Integer> orders = new ArrayList<Integer>();
        int index = 0;
        Set<Integer> excludes = new HashSet<Integer>();
        for (int i = copies.size() - 1; i >= 0; --i) {
            index = MathUtil.max(copies, excludes);
            orders.add(index);
            excludes.add(index);
        }
        return orders;
    }

    private List<Integer> parseAscOrder(List<Comparable> copies) {
        List<Integer> orders = new ArrayList<Integer>();
        int index = 0;
        Set<Integer> excludes = new HashSet<Integer>();
        for (int i = copies.size() - 1; i >= 0; --i) {
            index = MathUtil.min(copies, excludes);
            orders.add(index);
            excludes.add(index);
        }
        return orders;
    }

    private void parseTable(List rows, Table tb) {
        if (rows.isEmpty()) {
            return;
        }

        if (rows.get(0) instanceof List) {
            parseList(rows, tb);
            return;
        }

        if (rows.get(0) != null && rows.get(0).getClass().isArray()) {
            parseArray(rows, tb);
            return;
        }

        parseRow(rows, tb);
    }

    private void parseRow(List<List<Object>> rows, Table tb) {
        for (int i = 0; i < rows.size(); ++i) {
            for (int j = 0; j < rows.get(i).size(); ++j) {
                if (tb.getValues().size() <= j) {
                    tb.getValues().add(new ArrayList<Object>());
                }
                tb.getValues().get(j).add(o2s(rows.get(i).get(j)));
            }
        }
    }

    private String o2s(Object o) {
        String s = null;
        if (null != o) {
            if (o instanceof Date) {
                s = DateUtil.day((Date) o);
            } else if (o instanceof Timestamp) {
                s = DateUtil.second((Timestamp) o);
            } else {
                s = o.toString();
            }
        }
        return s;
    }

    private void parseArray(List<Object[]> rows, Table tb) {
        for (int i = 0; i < rows.size(); ++i) {
            for (int j = 0; j < rows.get(i).length; ++j) {
                if (tb.getValues().size() <= j) {
                    tb.getValues().add(new ArrayList<Object>());
                }
                tb.getValues().get(j).add(o2s(rows.get(i)[j]));
            }
        }
    }

    private void parseList(List<Object> row, Table tb) {
        for (int i = 0; i < row.size(); ++i) {
            if (tb.getValues().size() <= i) {
                tb.getValues().add(new ArrayList<Object>());
            }
            List<Object> col = new ArrayList<Object>();
            col.add(o2s(row.get(i)));
            tb.getValues().add(col);
        }
    }

    protected void parseGrowthRates(AbstractXmlComponent component, Table tb,
                                    Element elem) throws Exception {
        ELParser elp = new ELParser(component);
        Integer sj = XmlUtil.getIntAttr(elem, "sj", elp, null);
        Integer tq = XmlUtil.getIntAttr(elem, "tq", elp, null);
        Integer target = XmlUtil.getIntAttr(elem, "toCol", elp, null);
        if (sj != null && tq != null && target != null) {
            NodeList list = elem.getElementsByTagName("excludeCol");
            List<Integer> excludeRows = parserArray(XmlElWalker
                    .elementText(list, elp, 0));
            List<Object> tarCols = tb.getValues().get(target);
            List<Object> sjCols = tb.getValues().get(sj);
            List<Object> tqCols = tb.getValues().get(tq);
            for (int i = 0; i < tarCols.size(); ++i) {
                if (excludeRows.indexOf(i) < 0) {
                    tarCols.set(
                            i,
                            MathUtil.minus(
                                    div(MathUtil.o2d(sjCols.get(i)),
                                            MathUtil.o2d(tqCols.get(i))), 1.0));
                }
            }
        }
    }

    protected void parseCopyCol(Table tb, Element elem) throws Exception {
        Integer row = tb.getIds().indexOf(
                XmlUtil.getIntAttr(elem, "rowId", elp, null));
        Integer from = XmlUtil.getIntAttr(elem, "from", elp, null);
        List<Integer> targets = parserArray(elem.getAttribute("to"));
        if (row != null && from != null && !targets.isEmpty()) {
            for (Integer tar : targets) {
                tb.getValues().get(tar)
                        .set(row, tb.getValues().get(from).get(row));
            }
        }
    }

    protected void parseDivCol(AbstractXmlComponent component, Table tb,
                               Element elem) throws Exception {
        ELParser elp = new ELParser(component);
        Integer sub = XmlUtil.getIntAttr(elem, "sub", elp, null);
        Integer base = XmlUtil.getIntAttr(elem, "base", elp, null);
        Integer target = XmlUtil.getIntAttr(elem, "toCol", elp, null);
        if (sub != null && base != null && target != null) {
            NodeList list = elem.getElementsByTagName("excludeRow");
            List<Integer> excludeRows = parserArray(XmlElWalker
                    .elementText(list, elp, 0));
            List<Object> tarCols = tb.getValues().get(target);
            List<Object> subCols = tb.getValues().get(sub);
            List<Object> baseCols = tb.getValues().get(base);
            for (int i = 0; i < tarCols.size(); ++i) {
                if (excludeRows.indexOf(i) < 0) {
                    tarCols.set(
                            i,
                            div(MathUtil.o2d(subCols.get(i)),
                                    MathUtil.o2d(baseCols.get(i))));
                }
            }
        }
    }

    protected void parseCol(AbstractXmlComponent component, Table tb,
                            Element elem) throws Exception {
        List list = null;
        if ("col".equals(elem.getTagName())) {
            if (elem.hasAttribute("formul") || XmlUtil.hasText(elem)) {
                list = parseFormul(component, tb, elem);
            } else if (elem.hasAttribute("rank")) {
                int col = XmlUtil.getIntAttr(elem, "rank", elp, -1);
                if (col >= 0) {
                    list = parseRank(tb, col,
                            "true".equals(elem.getAttribute("desc")));
                }
            } else {
                if (component.hasObject(elem.getAttribute("list"))) {
                    list = (List) component.getVar(elem.getAttribute("list"));
                } else {
                    list = (List) XmlUtil.getObjectAttr(elem, "list", elp);
                }
            }
        } else {
            elem.setAttribute("id", "_tb_col_");
            listInterpreter.accept(component, elem);
            list = (List) component.removeLocal("_tb_col_");
        }
        if (null == list) {
            list = ListUtil.resize(new ArrayList<Object>(), tb.getIds().size());
        } else if (list.size() != tb.getIds().size()) {
            list = ListUtil.resize(list, tb.getIds().size());
        }
        tb.getValues().add(list);
        if ("true".equals(elem.getAttribute("temp"))) {
            this.putTemp(tb.getValues().size() - 1);
        }
    }

    private void invokeFormul(List ret, AbstractXmlComponent component,
                              Table tb, Element elem) throws Exception {
        String formul = elem.getAttribute("formul");
        if (formul.isEmpty()) {
            formul = XmlUtil.getText(elem);
        }
        component.local("cols", tb.getValues());
        for (int i = 0; i < tb.getIds().size(); ++i) {
            component.local("i", i);
            ret.set(i, XmlUtil.parseELText(formul, elp));
        }
        component.removeLocal("cols");
    }

    private List parseFormul(AbstractXmlComponent component, Table tb,
                             Element elem) throws Exception {
        List ret = null;
        if ("false".equals(elem.getAttribute("delay"))) {
            ret = ListUtil.resize(new ArrayList(), tb.getIds().size());
            this.invokeFormul(ret, component, tb, elem);
        } else {
            ret = ListUtil.resize(new ArrayList(), tb.getIds().size());
            this.delayCols.add(new Pair(tb.getValues().size(), elem));
        }
        return ret;
    }

    private List parseRank(Table tb, int col, boolean desc) {
        List column = tb.getValues().get(col);
        List<Comparable> compare = new ArrayList<Comparable>();
        boolean inserted = false;
        for (Object obj : column) {
            inserted = false;
            if (obj != null && obj instanceof Comparable) {
                for (int i = 0; i < compare.size(); ++i) {
                    if (compare.get(i).compareTo(obj) > 0) {
                        compare.add(i, (Comparable) obj);
                        inserted = true;
                        break;
                    }
                }
                if (!inserted) {
                    compare.add((Comparable) obj);
                }
            }
        }

        List<Integer> rank = new ArrayList<Integer>();
        for (Object obj : column) {
            if (obj != null) {
                int order = compare.indexOf(obj);
                if (order >= 0) {
                    if (desc) {
                        rank.add(compare.size() - order);
                    } else {
                        rank.add(order + 1);
                    }
                    continue;
                }
            }
            rank.add(null);
        }

        return rank;
    }

    private int getTargetIndex(AbstractXmlComponent component, Element item,
                               Table tb) throws Exception {
        ELParser elp = new ELParser(component);
        Integer target = XmlUtil.getIntAttr(item, "toId", elp, -1);
        if (target >= 0) {
            target = tb.getIds().indexOf(target);
        }

        if (target < 0) {
            target = XmlUtil.getIntAttr(item, "toRow", elp, -1);
        }
        return target;
    }

    private List<Integer> parserArray(String arr) throws Exception {
        return XmlUtil.toIntList(arr, elp);
    }

    private List<Integer> idsToRows(List<Integer> ids, Table tb) {
        List<Integer> ret = new ArrayList<Integer>();
        int index = 0;
        for (int i = 0; i < ids.size(); ++i) {
            index = tb.getIds().indexOf(ids.get(i));
            if (index >= 0) {
                ret.add(index);
            }
        }
        return ret;
    }

    Set<Integer> getAllRows(Element item, Table tb) throws Exception {
        NodeList list = item.getElementsByTagName("rangeIds");
        Set<Integer> rows = new HashSet<Integer>();
        if (list.getLength() > 0) {
            List<Integer> rangeRow = idsToRows(
                    parserArray(XmlElWalker.elementText(list, elp, 0)), tb);
            for (Integer row : rows) {
                if (row < rangeRow.get(0) || row > rangeRow.get(1)) {
                    rows.remove(row);
                }
            }

            for (int i = rangeRow.get(0); i <= rangeRow.get(1); ++i) {
                rows.add(i);
            }
        }

        list = item.getElementsByTagName("rangeRows");
        if (list.getLength() > 0) {
            List<Integer> rangeRow = parserArray(XmlElWalker.elementText(list, elp, 0));
            for (Integer row : rows) {
                if (row < rangeRow.get(0) || row > rangeRow.get(1)) {
                    rows.remove(row);
                }
            }

            for (int i = rangeRow.get(0); i <= rangeRow.get(1); ++i) {
                rows.add(i);
            }
        }

        if (rows.isEmpty()) {
            list = item.getElementsByTagName("inIds");

            if (list.getLength() > 0) {
                List<Integer> ids = parserArray(XmlElWalker.elementText(list, elp, 0));
                rows.addAll(idsToRows(ids, tb));
            }

            list = item.getElementsByTagName("inRows");
            if (list.getLength() > 0) {
                rows.addAll(parserArray(XmlElWalker.elementText(list, elp, 0)));
            }
        }

        list = item.getElementsByTagName("excIds");
        if (list.getLength() > 0) {
            List<Integer> excRows = idsToRows(
                    parserArray(XmlElWalker.elementText(list, elp, 0)), tb);
            for (Integer row : excRows) {
                rows.remove(row);
            }
        }

        list = item.getElementsByTagName("excRows");
        if (list.getLength() > 0) {
            List<Integer> excRows = parserArray(XmlElWalker.elementText(list, elp, 0));
            for (Integer row : excRows) {
                rows.remove(row);
            }
        }
        return rows;
    }

    Set<Integer> getExcludeCols(Element item) throws Exception {
        NodeList cols = item.getElementsByTagName("excludeCol");
        Set<Integer> excludeCols = null;
        if (cols.getLength() > 0) {
            excludeCols = XmlUtil.toIntSet(XmlElWalker.elementText(cols, elp, 0), elp);
        }
        return excludeCols;
    }

    private void parseSumRow(AbstractXmlComponent component, Table tb,
                             Element item) throws Exception {
        int targetRow = getTargetIndex(component, item, tb);
        if (targetRow < 0) {
            return;
        }
        Set<Integer> rows = getAllRows(item, tb);

        if (rows.isEmpty()) {
            return;
        }

        Set<Integer> excludeCols = getExcludeCols(item);

        Object val = null;
        List<Object> tbCol = null;
        if (excludeCols != null) {
            for (int i = 0, len = tb.getValues().size(); i < len; ++i) {
                if (excludeCols.contains(i)) {
                    continue;
                }
                tbCol = tb.getValues().get(i);
                for (Integer row : rows) {
                    val = tbCol.get(row);
                    if (val instanceof Double) {
                        tbCol.set(targetRow, MathUtil.sum(
                                (Double) tbCol.get(targetRow), (Double) val));
                    } else if (val instanceof Integer) {
                        tbCol.set(targetRow, MathUtil.sum(
                                (Integer) tbCol.get(targetRow), (Integer) val));
                    }
                }
            }
        } else {
            for (int i = 0, len = tb.getValues().size(); i < len; ++i) {
                tbCol = tb.getValues().get(i);
                for (Integer row : rows) {
                    val = tbCol.get(row);
                    if (val instanceof Double) {
                        tbCol.set(targetRow, MathUtil.sum(
                                (Double) tbCol.get(targetRow), (Double) val));
                    } else if (val instanceof Integer) {
                        tbCol.set(targetRow, MathUtil.sum(
                                (Integer) tbCol.get(targetRow), (Integer) val));
                    }
                }
            }
        }

    }

    private void parseMinusmCol(AbstractXmlComponent component, Table tb,
                                Element elem) throws Exception {
        ELParser elp = new ELParser(component);
        Integer sub = XmlUtil.getIntAttr(elem, "first", elp, null);
        Integer base = XmlUtil.getIntAttr(elem, "second", elp, null);
        Integer target = XmlUtil.getIntAttr(elem, "toCol", elp, null);
        if (sub != null && base != null && target != null) {
            NodeList list = elem.getElementsByTagName("excludeRow");
            List<Integer> excludeRows = parserArray(XmlElWalker
                    .elementText(list, elp, 0));
            List<Object> tarCols = tb.getValues().get(target);
            List<Object> subCols = tb.getValues().get(sub);
            List<Object> baseCols = tb.getValues().get(base);
            boolean abs = "true".equals(elem.getAttribute("abs"));
            for (int i = 0; i < tarCols.size(); ++i) {
                if (excludeRows.indexOf(i) < 0) {
                    Double tVal = MathUtil.minus(MathUtil.o2d(subCols.get(i)), MathUtil.o2d(baseCols.get(i)));
                    if (tVal != null) {
                        tarCols.set(i, abs ? Math.abs(tVal) : tVal);
                    }
                }
            }
        }

    }

    private Double div(Double sub, Double base) {
        if (sub != null
                && base != null
                && (MathUtil.isNegative(sub) || MathUtil.isNegative(base)
                || MathUtil.isZero(base) || MathUtil.isZero(sub))) {
            return null;
        }
        return MathUtil.division(sub, base);
    }

    private void parseDivRow(AbstractXmlComponent component, Table tb,
                             Element item) throws Exception {
        int index = getTargetIndex(component, item, tb);
        if (index >= 0) {

            Integer subRow = null;
            Integer baseRow = null;
            NodeList list = item.getElementsByTagName("subId");
            ELParser elp = new ELParser(component);
            if (list.getLength() > 0) {
                Integer subId = XmlUtil.getInt(XmlElWalker.elementText(list, elp, 0),
                        elp, null);
                subRow = tb.getIds().indexOf(subId);
            }

            if (null == subRow) {
                list = item.getElementsByTagName("subRow");
                if (list.getLength() > 0) {
                    subRow = XmlUtil.getInt(XmlElWalker.elementText(list, elp, 0), elp,
                            null);
                }
            }

            if (null != subRow) {
                list = item.getElementsByTagName("baseId");
                if (list.getLength() > 0) {
                    Integer baseId = XmlUtil.getInt(
                            XmlElWalker.elementText(list, elp, 0), elp, null);
                    baseRow = tb.getIds().indexOf(baseId);
                }

                if (null == baseRow) {
                    list = item.getElementsByTagName("baseRow");
                    if (list.getLength() > 0) {
                        baseRow = XmlUtil.getInt(XmlElWalker.elementText(list, elp, 0),
                                elp, null);
                    }
                }
            }

            if (subRow != null && baseRow != null) {
                list = item.getElementsByTagName("excludeCol");
                List<Integer> excludeCols = parserArray(XmlElWalker.elementText(
                        list, elp, 0));

                List<Object> col = null;
                for (int i = 0; i < tb.getValues().size(); ++i) {
                    if (excludeCols.indexOf(i) < 0) {
                        col = tb.getValues().get(i);
                        if (col.get(subRow) instanceof Double
                                || col.get(baseRow) instanceof Double) {
                            col.set(index,
                                    div((Double) col.get(subRow),
                                            (Double) col.get(baseRow)));
                        }
                    }
                }
            }
        }
    }
}