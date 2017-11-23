package com.frame.script.el.em;

import java.util.List;


public class EMListSub extends NamedEM {

	
	
    public EMListSub() {
        super("sub");
    }


    @Override
    public int paramCount() {
        return 1;
    }



   
    @Override
    public Object invoke(Object stub, List<Object> args) {
        if (stub instanceof List) {
            List list = (List) stub;
            Integer index = (Integer) args.get(0);
            if (list.size() > index) {
            	return list.subList(index, list.size());
            }
        }
        return null;
    }
}
