package com.frame.script.el;

import java.util.ArrayList;
import java.util.List;

import com.frame.script.el.querier.ELBlockQuerier;
import com.frame.script.el.querier.Querier;

public class ELParser {

	public static interface ElContext {
		Object onGetObject(String key);
		boolean hasObject(String key);
		void storeObject(String key, Object obj);
	}

	ElContext elContext;

	public ELParser(ElContext loader) {
		this.elContext = loader;
	}

	public List<ELExpression> parser(String express) {
		List<ELExpression> exps = new ArrayList<ELExpression>();
		Querier querier = new ELBlockQuerier(express);
		while (querier.hasNext()){
			exps.add(new ELExpression(
					querier.start(), 
					querier.end(), 
					querier.next(),
					elContext));
		}
		return exps;
	}
}
