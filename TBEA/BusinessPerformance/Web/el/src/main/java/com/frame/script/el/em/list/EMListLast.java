package com.frame.script.el.em.list;

import java.util.List;

import com.frame.script.el.em.NamedEM;


public class EMListLast extends NamedEM {

	
	
    public EMListLast() {
        super("last");
    }


    @Override
    public int paramCount() {
        return 1;
    }

    @Override
    public Object invoke(Object stub, List<Object> args) {
        if (stub instanceof List) {
            List list = (List) stub;
            if (!list.isEmpty()) {
            	Integer col = list.size() - (Integer) args.get(0) - 1;
            	 return list.get(col);
            }
        }else if (stub.getClass().isArray()) {
            Object[] list = (Object[]) stub;
            if (list.length > 0) {
            	Integer col = list.length - (Integer) args.get(0) - 1;
            	return list[col];
            }
        }
        return null;
    }
}
