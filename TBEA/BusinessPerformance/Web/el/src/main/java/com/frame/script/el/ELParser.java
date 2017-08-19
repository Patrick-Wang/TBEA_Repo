package com.frame.script.el;

import java.util.ArrayList;
import java.util.List;

import com.frame.script.el.querier.ELBlockQuerier;
import com.frame.script.el.querier.Querier;

public class ELParser {

	public static interface ObjectLoader {
		Object onGetObject(String key);
		boolean hasObject(String key);
	}

	ObjectLoader loader;

	public ELParser(ObjectLoader loader) {
		this.loader = loader;
	}

	public List<ELExpression> parser(String express) {
		List<ELExpression> exps = new ArrayList<ELExpression>();
		Querier querier = new ELBlockQuerier(express);
		while (querier.hasNext()){
			exps.add(new ELExpression(
					querier.start(), 
					querier.end(), 
					querier.next(),
					loader));
		}
		return exps;
	}
}
