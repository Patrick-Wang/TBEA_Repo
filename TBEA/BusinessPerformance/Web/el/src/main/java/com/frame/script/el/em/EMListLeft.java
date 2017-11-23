package com.frame.script.el.em;

import java.util.List;

import com.util.tools.ListUtil;


public class EMListLeft extends NamedEM {

	
	
    public EMListLeft() {
        super("left");
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
        	return ListUtil.left(list, index);
        }
        return null;
    }
}
