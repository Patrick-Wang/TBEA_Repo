package com.frame.script.el.em;

import java.util.List;

import com.util.tools.ListUtil;


public class EMListMid extends NamedEM {

	
	
    public EMListMid() {
        super("mid");
    }


    @Override
    public int paramCount() {
        return 2;
    }



   
    @Override
    public Object invoke(Object stub, List<Object> args) {
        if (stub instanceof List) {
            List list = (List) stub;
            Integer from = (Integer) args.get(0);
            Integer to = (Integer) args.get(1);
        	return ListUtil.mid(list, from, to);
        }
        return null;
    }
}
