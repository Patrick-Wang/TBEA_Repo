package com.tbea.ic.operation.reportframe.el;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.tbea.ic.operation.reportframe.util.StringUtil;

public class ELParser {

	static Pattern elPattern = Pattern.compile("\\$\\{[^\\$^\\}]*\\}");

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
		int start = express.indexOf("${");
		int end = -1;
		if (start >= 0){
			end = StringUtil.findClose(express, start + 1, '{', '}');
		}else{
			end = -1;
		}
		while (end >= 0) {
			String val = express.substring(start + 2, end - 1);
			exps.add(new ELExpression(
					start, 
					end, 
					val,
					loader));
			start = express.indexOf("${", end + 1);
			if (start >= 0){
				end = StringUtil.findClose(express, start + 1, '{', '}');
			}else{
				break;
			}
		}
		return exps;
	}
}
