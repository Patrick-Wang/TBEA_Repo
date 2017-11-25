package com.frame.script.el.em.list.copy;

import java.util.List;

import com.frame.script.el.em.NamedEM;
import com.util.tools.ListUtil;


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
        	return ListUtil.sub(list, index);
        }
        return null;
    }
}
