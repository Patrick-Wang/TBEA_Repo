package com.frame.script.el.em;

import java.util.List;


public class EMListRight extends NamedEM {

	
	
    public EMListRight() {
        super("right");
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
            	return list.subList(list.size() - index, list.size());
            }
        }
        return null;
    }
}
