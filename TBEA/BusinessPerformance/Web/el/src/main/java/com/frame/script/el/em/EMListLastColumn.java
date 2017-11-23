package com.frame.script.el.em;

import java.util.ArrayList;
import java.util.List;


public class EMListLastColumn extends NamedEM {

	
	
    public EMListLastColumn() {
        super("lastCol");
    }


    @Override
    public int paramCount() {
        return 1;
    }

    List getColFromList(List<List> tb, Integer col) {
        List ret = new ArrayList(tb.size());
        for (List row : tb) {
            ret.add(row.get(col));
        }
        return ret;
    }

    List getColFromArray(List<Object[]> tb, Integer col) {
        List ret = new ArrayList(tb.size());
        for (Object[] row : tb) {
            ret.add(row[col]);
        }
        return ret;
    }

    @Override
    public Object invoke(Object stub, List<Object> args) {
        if (stub instanceof List) {
            List list = (List) stub;
            if (!list.isEmpty()) {
                if (list.get(0) instanceof List) {
                    Integer col = list.size() - (Integer) args.get(0) - 1;
                    return getColFromList(list, col);
                } else if (list.get(0).getClass().isArray()) {
                	Integer col = list.size() - (Integer) args.get(0) - 1;
                    return getColFromArray(list, col);
                }
            }
        }
        return null;
    }
}
